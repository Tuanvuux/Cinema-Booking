//package com.mycompany.spring_mvc_project_final.controller;
//
//import com.mycompany.spring_mvc_project_final.entities.BookTicket;
//import com.mycompany.spring_mvc_project_final.entities.Cinema;
//import com.mycompany.spring_mvc_project_final.entities.Room;
//import com.mycompany.spring_mvc_project_final.entities.Seat;
//import com.mycompany.spring_mvc_project_final.repository.BookTicketRepository;
//import com.mycompany.spring_mvc_project_final.repository.CinemaRepository;
//import com.mycompany.spring_mvc_project_final.repository.RoomRepository;
//import com.mycompany.spring_mvc_project_final.repository.SeatRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import javax.servlet.http.HttpSession;
//import javax.transaction.Transactional;
//import java.security.Timestamp;
//import java.time.LocalDateTime;
//import java.util.*;
//
//import static org.springframework.web.bind.annotation.RequestMethod.GET;
//
//@Controller
//public class BookingController {
//    @Autowired
//    SeatRepository seatRepository;
//    @Autowired
//    CinemaRepository cinemaRepository;
//    @Autowired
//    RoomRepository roomRepository;
//    @Autowired
//    BookTicketRepository bookTicketRepository;
//
//    @RequestMapping(value = "/cinema",method = GET)
//    public String showCinema(Model model){
//        List<Cinema> cinemaList = (List<Cinema>) cinemaRepository.findAll();
//
//        model.addAttribute("cinemaList", cinemaList);
//        return "Cinema";
//    }
//
//    @RequestMapping(value = "/room",method = GET)
//    public String showRoom(Model model){
//        List<Room> roomList = (List<Room>) roomRepository.findByCinema_CinemaId(1L);
//
//        model.addAttribute("roomList", roomList);
//        return "Room";
//    }
//
//    @RequestMapping(value = "/seat", method = GET)
//    public String showSeat(Model model) {
//        // Lấy danh sách ghế từ bảng seat
//        List<Seat> seatList = (List<Seat>) seatRepository.findByRoom_RoomId(1L);
//
//        // Lặp qua danh sách ghế
//        for (Seat seat : seatList) {
//            // Kiểm tra xem ghế có trong bảng bookticket hay không
//            Optional<BookTicket> optionalBookTicket = bookTicketRepository.findFirstBySeatId(seat.getSeatId());
//
//            BookTicket bookTicket = optionalBookTicket.orElse(null);
//
//            if (bookTicket != null) {
//                // Nếu ghế có trong bảng bookticket
//                if ("1".equals(bookTicket.getStatus())) {
//                    // Trạng thái 1: Ghế đã được chọn
//                    seat.setStatus("1");
//                } else if ("2".equals(bookTicket.getStatus())) {
//                    // Trạng thái 2: Ghế đã được đặt
//                    seat.setStatus("2");
//                }
//            }
//        }
//
//        // Truyền danh sách ghế đã được cập nhật vào model
//        model.addAttribute("seatList", seatList);
//
//        // Truyền danh sách các ghế đã chọn vào model
//        List<Long> selectedSeats = getSelectedSeatIds(seatList);
//        model.addAttribute("selectedSeats", selectedSeats);
//
//        return "BookingPage";
//    }
//
//    private List<Long> getSelectedSeatIds(List<Seat> seatList) {
//        List<Long> selectedSeats = new ArrayList<>();
//        for (Seat seat : seatList) {
//            if ("1".equals(seat.getStatus())) {
//                selectedSeats.add(seat.getSeatId());
//            }
//        }
//        return selectedSeats;
//    }
//
//    @Transactional
//    @PostMapping("/selectSeat")
//    @ResponseBody
//    public ResponseEntity<Map<String, Object>> selectSeat(@RequestParam("seatId") String seatIdRequest) {
//        Map<String, Object> response = new HashMap<>();
//
//        try {
//            // Kiểm tra xem seatIdRequest có rỗng không
//            if (seatIdRequest.isEmpty()) {
//                response.put("status", "error");
//                response.put("message", "Seat ID không được rỗng");
//                return ResponseEntity.badRequest().body(response);
//            }
//
//            // Chuyển đổi seatIdRequest thành Long
//            Long seatId = Long.parseLong(seatIdRequest);
//
//            // Tìm kiếm BookTicket với seatId và status là "1"
//            Optional<BookTicket> existingBookTicket = bookTicketRepository.findBySeatIdAndStatus(seatId, "1");
//
//            if (existingBookTicket.isPresent()) {
//                // Nếu tìm thấy bản ghi, xóa nó khỏi bảng BookTicket
//                bookTicketRepository.delete(existingBookTicket.get());
//
//                response.put("status", "success");
//                response.put("message", "Hủy chọn ghế thành công");
//            } else {
//                // Tạo mới một BookTicket và lưu vào database
//                LocalDateTime currentTime = LocalDateTime.now();
//                BookTicket bookTicket = new BookTicket();
//                bookTicket.setLastSelectedTime(currentTime);
//                bookTicket.setSeatId(seatId);
//                bookTicket.setStatus("1");
//
//                bookTicketRepository.save(bookTicket);
//
//                response.put("status", "success");
//                response.put("message", "Ghế đã được đặt thành công");
//            }
//            return ResponseEntity.ok().body(response);
//        } catch (NumberFormatException e) {
//            // Xử lý lỗi khi không thể chuyển đổi seatIdRequest thành Long
//            response.put("status", "error");
//            response.put("message", "Không thể chuyển đổi Seat ID thành Long");
//            return ResponseEntity.badRequest().body(response);
//        } catch (Exception e) {
//            // Xử lý các trường hợp lỗi khác
//            response.put("status", "error");
//            response.put("message", "Có lỗi xảy ra: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//        }
//    }
//
//    @GetMapping("/booking")
//    public String handleBooking(@RequestParam("movieId") Long movieId, HttpSession session) {
//        // Lưu movieId vào session
//        session.setAttribute("movieId", movieId);
//        // Chuyển hướng tới trang showtime
//        return "ShowTime";
//    }
//    @RequestMapping(value = "/payment", method = RequestMethod.GET)
//    public String payment(){
//        return "PaymentPage";
//    }
//
//    @RequestMapping(value = "/paypal", method = RequestMethod.GET)
//    public String paypal(){
//        return "payment/index";
//    }
//
//
//}
