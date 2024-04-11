package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.entities.*;
import com.mycompany.spring_mvc_project_final.repository.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.security.Timestamp;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class BookingController {
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    CinemaRepository cinemaRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    BookTicketRepository bookTicketRepository;
    @Autowired
    RoomShowTimeRepository roomShowTimeRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    MovieShowtimeRepository movieShowtimeRepository;
    @Autowired
    ShowTimeRepository showTimeRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "/cinema",method = GET)
    public String showCinema(Model model){
        List<Cinema> cinemaList = (List<Cinema>) cinemaRepository.findAll();

        model.addAttribute("cinemaList", cinemaList);
        return "Cinema";
    }

    @RequestMapping(value = "/room",method = GET)
    public String showRoom(Model model){
        List<Room> roomList = (List<Room>) roomRepository.findByCinema_CinemaId(1L);

        model.addAttribute("roomList", roomList);
        return "Room";
    }
    @GetMapping(value = "/seat")
    public String bookSeatget(@RequestParam("roomId") Long roomId,
                              @RequestParam("movieId") Long movieId,
                              @RequestParam("showTimeId") Long showTimeId,
                                  HttpServletRequest request, Model model) {
        // Lấy session từ request
        HttpSession session = request.getSession();
        // Lưu giá trị roomId và showTimeId vào session
        session.setAttribute("roomId", roomId);
        session.setAttribute("showTimeId", showTimeId);
        session.setAttribute("movieId", movieId);
        // Lấy danh sách ghế từ bảng seat
        List<Seat> seatList = (List<Seat>) seatRepository.findByRoom_RoomId(roomId);

        // Lặp qua danh sách ghế
        for (Seat seat : seatList) {
            // Kiểm tra xem ghế có trong bảng bookticket hay không
            Optional<BookTicket> optionalBookTicket = bookTicketRepository.findFirstBySeatIdAndRoomIdAndShowTimeIdAndMovieId(seat.getSeatId(), roomId, showTimeId, movieId );

            BookTicket bookTicket = optionalBookTicket.orElse(null);

            if (bookTicket != null) {
                // Nếu ghế có trong bảng bookticket
                if ("1".equals(bookTicket.getStatus())) {
                    // Trạng thái 1: Ghế đã được chọn
                    seat.setStatus("1");
                } else if ("2".equals(bookTicket.getStatus())) {
                    // Trạng thái 2: Ghế đã được đặt
                    seat.setStatus("2");
                }
            }
        }

        // Truyền danh sách ghế đã được cập nhật vào model
        model.addAttribute("seatList", seatList);

        // Truyền danh sách các ghế đã chọn vào model
        List<Long> selectedSeats = getSelectedSeatIds(seatList);
        model.addAttribute("selectedSeats", selectedSeats);

        return "BookingPage";
    }



    @PostMapping(value = "/seat")
    public String bookSeat(@RequestParam("roomId") Long roomId,
                           @RequestParam("showTimeId") Long showTimeId,
                           HttpServletRequest request, Model model) {
        // Lấy session từ request
        HttpSession session = request.getSession();
        Long movieId = (Long) session.getAttribute("movieId");
        // Lưu giá trị roomId và showTimeId vào session
        session.setAttribute("roomId", roomId);
        session.setAttribute("showTimeId", showTimeId);

        // Lấy danh sách ghế từ bảng seat
        List<Seat> seatList = (List<Seat>) seatRepository.findByRoom_RoomId(roomId);

        // Lặp qua danh sách ghế
        for (Seat seat : seatList) {
            // Kiểm tra xem ghế có trong bảng bookticket hay không
            Optional<BookTicket> optionalBookTicket = bookTicketRepository.findFirstBySeatIdAndRoomIdAndShowTimeIdAndMovieId(seat.getSeatId(), roomId, showTimeId, movieId );

            BookTicket bookTicket = optionalBookTicket.orElse(null);

            if (bookTicket != null) {
                // Nếu ghế có trong bảng bookticket
                if ("1".equals(bookTicket.getStatus())) {
                    // Trạng thái 1: Ghế đã được chọn
                    seat.setStatus("1");
                } else if ("2".equals(bookTicket.getStatus())) {
                    // Trạng thái 2: Ghế đã được đặt
                    seat.setStatus("2");
                }
            }
        }

        // Truyền danh sách ghế đã được cập nhật vào model
        model.addAttribute("seatList", seatList);

        // Truyền danh sách các ghế đã chọn vào model
        List<Long> selectedSeats = getSelectedSeatIds(seatList);
        model.addAttribute("selectedSeats", selectedSeats);

        return "BookingPage";
    }

    private List<Long> getSelectedSeatIds(List<Seat> seatList) {
        List<Long> selectedSeats = new ArrayList<>();
        for (Seat seat : seatList) {
            if ("1".equals(seat.getStatus())) {
                selectedSeats.add(seat.getSeatId());
            }
        }
        return selectedSeats;
    }

    @Transactional
    @PostMapping("/selectSeat")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> selectSeat(@RequestParam("seatId") String seatIdRequest, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        Long roomId = (Long) session.getAttribute("roomId");
        Long showTimeId = (Long) session.getAttribute("showTimeId");
        Long userId = (Long) session.getAttribute("userId");
        Long movieId = (Long) session.getAttribute("movieId");

        try {
            // Kiểm tra xem seatIdRequest có rỗng không
            if (seatIdRequest.isEmpty()) {
                response.put("status", "error");
                response.put("message", "Seat ID không được rỗng");
                return ResponseEntity.badRequest().body(response);
            }

            // Chuyển đổi seatIdRequest thành Long
            Long seatId = Long.parseLong(seatIdRequest);

            // Tìm kiếm BookTicket với seatId và status là "1"
            Optional<BookTicket> existingBookTicket = bookTicketRepository.findBySeatIdAndStatus(seatId, "1");

            if (existingBookTicket.isPresent()) {
                // Nếu tìm thấy bản ghi, xóa nó khỏi bảng BookTicket
                bookTicketRepository.delete(existingBookTicket.get());

                response.put("status", "success");
                response.put("message", "Hủy chọn ghế thành công");
            } else {
                // Tạo mới một BookTicket và lưu vào database
                LocalDateTime currentTime = LocalDateTime.now();
                BookTicket bookTicket = new BookTicket();
                bookTicket.setLastSelectedTime(currentTime);
                bookTicket.setSeatId(seatId);
                bookTicket.setShowTimeId(showTimeId);
                bookTicket.setRoomId(roomId);
                bookTicket.setUserId(userId);
                bookTicket.setMovieId(movieId);
                bookTicket.setStatus("1");
                System.out.println("a");

                bookTicketRepository.save(bookTicket);
                System.out.println("thanhf coong");

                response.put("status", "success");
                response.put("message", "Ghế đã được đặt thành công");
            }
            return ResponseEntity.ok().body(response);
        } catch (NumberFormatException e) {
            // Xử lý lỗi khi không thể chuyển đổi seatIdRequest thành Long
            response.put("status", "error");
            response.put("message", "Không thể chuyển đổi Seat ID thành Long");
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            // Xử lý các trường hợp lỗi khác
            response.put("status", "error");
            response.put("message", "Có lỗi xảy ra: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }



    @RequestMapping(value = "/booking", method = GET)
    public String handleBooking(@RequestParam("movieId") Long movieId, Model model, HttpSession session) {
        // Lưu movieId vào session
        session.setAttribute("movieId", movieId);

        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();

        // Chuyển định dạng ngày theo yêu cầu (VD: "yyyy-MM-dd")
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Chuyển đổi đối tượng LocalDate thành chuỗi theo định dạng "yyyy-MM-dd"
        String showDate = dateFormatter.format(currentDate);

        // In ra ngày đã định dạng
        System.out.println("Ngày hiện tại: " + showDate);

        // Tìm kiếm thông tin phim theo movieId
        Movie movie = movieRepository.findByMovieId(movieId);

        // Chuyển đổi chuỗi showDate sang kiểu java.sql.Date
        java.sql.Date sqlShowDate = java.sql.Date.valueOf(currentDate);

        // Tìm kiếm các lịch chiếu dựa trên movieId và ngày hiện tại
        List<ShowTime> showTimes = showTimeRepository.findShowTimesByMovieIdAndShowDate(movieId, sqlShowDate);
        List<Long> showtimeIds = new ArrayList<>();
        for (ShowTime showTime : showTimes) {
            showtimeIds.add(showTime.getShowTimeId());
        }
        System.out.println(showTimes.size());

        List<RoomShowtime> roomShowTimes = roomShowTimeRepository.findByShowTime_ShowTimeIdIn(showtimeIds);
        System.out.println(roomShowTimes.size());
        // Thêm thông tin phim và các lịch chiếu vào model để hiển thị trên trang booking
        model.addAttribute("roomShowTimes", roomShowTimes);
        model.addAttribute("movie", movie);
        model.addAttribute("showTimes", showTimes);

        LocalDate currentDates = LocalDate.now();
        DateTimeFormatter dateFormatters = DateTimeFormatter.ofPattern("dd MM yyyy");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE", new Locale("vi", "VN"));
        List<String> dates = new ArrayList<>();
        List<String> daysOfWeek = new ArrayList<>();

        // Add current date and day of week
        dates.add(currentDates.format(dateFormatters));
        daysOfWeek.add(currentDate.format(dayFormatter));

        // Add next 6 days and their corresponding days of week
        for (int i = 1; i < 7; i++) {
            LocalDate nextDate = currentDate.plusDays(i);
            dates.add(nextDate.format(dateFormatters));
            daysOfWeek.add(nextDate.format(dayFormatter));
        }

        model.addAttribute("dates", dates);
        model.addAttribute("daysOfWeek", daysOfWeek);
        return "BookingShowTime";
    }
    @RequestMapping(value = "/booking", method = POST)
    public String PostBooking(@RequestParam("selectedDate") String selectedDate,Model model, HttpSession session) {
        // Lưu movieId vào session
        Long movieId = (Long) session.getAttribute("movieId");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        LocalDate localDate = LocalDate.parse(selectedDate, formatter);
        java.sql.Date sqlSelectedDate = java.sql.Date.valueOf(localDate);

        List<ShowTime> showTimes = showTimeRepository.findShowTimesByMovieIdAndShowDate(movieId, sqlSelectedDate);
        Movie movie = movieRepository.findByMovieId(movieId);
        List<Long> showtimeIds = new ArrayList<>();
        for (ShowTime showTime : showTimes) {
            showtimeIds.add(showTime.getShowTimeId());
        }
        System.out.println(showTimes.size());

        List<RoomShowtime> roomShowTimes = roomShowTimeRepository.findByShowTime_ShowTimeIdIn(showtimeIds);
        System.out.println(roomShowTimes.size());
        // Thêm thông tin phim và các lịch chiếu vào model để hiển thị trên trang booking
        model.addAttribute("roomShowTimes", roomShowTimes);
        model.addAttribute("movie", movie);
        model.addAttribute("showTimes", showTimes);

        LocalDate currentDates = LocalDate.now();
        DateTimeFormatter dateFormatters = DateTimeFormatter.ofPattern("dd MM yyyy");
        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE", new Locale("vi", "VN"));
        List<String> dates = new ArrayList<>();
        List<String> daysOfWeek = new ArrayList<>();

        // Add current date and day of week
        LocalDate currentDate = LocalDate.now();
        dates.add(currentDates.format(dateFormatters));
        daysOfWeek.add(currentDate.format(dayFormatter));

        // Add next 6 days and their corresponding days of week
        for (int i = 1; i < 7; i++) {
            LocalDate nextDate = currentDate.plusDays(i);
            dates.add(nextDate.format(dateFormatters));
            daysOfWeek.add(nextDate.format(dayFormatter));
        }

        model.addAttribute("dates", dates);
        model.addAttribute("daysOfWeek", daysOfWeek);
        return "BookingShowTime";
    }

    @GetMapping("/payment")
    public String processPayment(HttpSession session, Model model) {
        Long movieId = (Long) session.getAttribute("movieId");
        Long showTimeId = (Long) session.getAttribute("showTimeId");
        Long userId = (Long) session.getAttribute("userId");
        Long roomId = (Long) session.getAttribute("roomId");
        Optional<User> user = userRepository.findById(userId);
        Optional<Movie> movie = movieRepository.findById(movieId);
        Optional<ShowTime> showTime = showTimeRepository.findById(showTimeId);
        List<BookTicket> bookTickets = bookTicketRepository.findByUserIdAndShowTimeIdAndMovieIdAndRoomId(userId, showTimeId, movieId, roomId);

        List<Long> seatIds = new ArrayList<>(); // Mảng chứa các seatId

        for (BookTicket bookTicket : bookTickets) {
            seatIds.add(bookTicket.getSeatId());
        }

        // Cập nhật trạng thái ghế từ 1 thành 2
        for (Long seatId : seatIds) {
            Optional<BookTicket> bookTicket = bookTicketRepository.findBySeatIdAndShowTimeIdAndMovieIdAndRoomIdAndStatus(seatId, showTimeId, movieId, roomId, "1");
            if (bookTicket.isPresent()) {
                bookTicket.get().setStatus("2");
                bookTicketRepository.save(bookTicket.get());
            }
        }

        List<Seat> seats = seatRepository.findAllBySeatIdIn(seatIds);
        model.addAttribute("seats", seats);
        model.addAttribute("user", user);
        model.addAttribute("movie", movie);
        model.addAttribute("showTime", showTime);
        return "PaymentPage";
    }


    @RequestMapping(value = "/paypal", method = GET)
    public String paypal(@RequestParam("total") int total, Model model) {
        System.out.println("A");
        // Đặt giá trị total vào model để truyền đến JSP
        model.addAttribute("total", total);
        return "paypal"; // Đảm bảo có một tệp JSP hoặc Thymeleaf tương ứng có tên "paypal"
    }

    @RequestMapping(value = "/paymentsuccess", method = GET)
    public String payment(HttpSession session){
        // Lấy thông tin từ session
        Long userId = (Long) session.getAttribute("userId");
        Long movieId = (Long) session.getAttribute("movieId");
        Long showTimeId = (Long) session.getAttribute("showTimeId");
        Long roomId = (Long) session.getAttribute("roomId");

        // Tìm thông tin người dùng từ cơ sở dữ liệu
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return "redirect:/paymenterror"; // Nếu không tìm thấy người dùng, chuyển hướng đến trang lỗi
        }
        User user = userOptional.get();

        // Lấy thông tin vé từ cơ sở dữ liệu
        List<BookTicket> bookTickets = bookTicketRepository.findByUserIdAndShowTimeIdAndMovieIdAndRoomId(userId, showTimeId, movieId, roomId);

        // Tạo nội dung email
        StringBuilder emailContent = new StringBuilder();
//        emailContent.append("Chúc mừng bạn đã đặt vé thành công.\n\n");
        emailContent.append("Chúc mừng ").append(userRepository.findById(userId).orElse(new User()).getLastName()).append(" đã đặt vé thành công\n");
        emailContent.append("Thông tin vé:\n");
        for (BookTicket bookTicket : bookTickets) {
            emailContent.append("- Ghế số: ").append(bookTicket.getSeatId()).append("\n");
        }
        emailContent.append("Tên phim: ").append(movieRepository.findById(movieId).orElse(new Movie()).getMovieName()).append("\n");
        emailContent.append("Suất chiếu: ");
        emailContent.append(" từ: ").append(showTimeRepository.findById(showTimeId).orElse(new ShowTime()).getTimeStart());
        emailContent.append(" đến: ").append(showTimeRepository.findById(showTimeId).orElse(new ShowTime()).getTimeEnd());
        emailContent.append(" ngày: ").append(showTimeRepository.findById(showTimeId).orElse(new ShowTime()).getShowDate());
        emailContent.append("\n");
        emailContent.append("Phòng chiếu: ").append(roomId).append("\n");
        emailContent.append("\nCảm ơn bạn đã lựa chọn DoubleT. Chúc bạn có một trải nghiệm thú vị khi xem phim!");

        // Tạo và gửi email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(user.getEmail());
            helper.setSubject("Xác nhận đặt vé thành công");
            helper.setText(emailContent.toString());
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Xử lý lỗi gửi email (nếu cần)
            return "redirect:/paymenterror";
        }

        return "paymentsuccess"; // Trả về trang thành công

    }
//    @RequestMapping(value = "/paymenerror", method = GET)
//    public String paymenterror(){
//        return "PaymentPage";
//    }


}