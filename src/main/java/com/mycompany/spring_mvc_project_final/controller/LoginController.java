package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.entities.User;
import com.mycompany.spring_mvc_project_final.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String email, String password, Model model, HttpSession session) {
        // Kiểm tra xem email và mật khẩu có tồn tại trong database không
        User user = userRepository.findByEmailAndPassword(email, password);

        if (user == null) {
            model.addAttribute("errorMessage", "Email hoặc mật khẩu không đúng!");
            return "login";
        }

        // Lưu thông tin đăng nhập vào session
        session.setAttribute("userId", user.getUserId());
        session.setAttribute("lastName", user.getLastName());

        // Đăng nhập thành công, chuyển hướng đến trang chính sau khi đăng nhập
        return "redirect:/";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Xóa tất cả các thuộc tính của session và hủy session
        session.invalidate();
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(User user, Model model) {
        // Kiểm tra xem email đã tồn tại trong database chưa
        if (userRepository.existsByEmail(user.getEmail())) {
            model.addAttribute("errorMessage", "Email đã tồn tại!");
            return "register";
        }

        // Lưu người dùng vào database
        user.setStatus("active"); // Thiết lập trạng thái active mặc định
        userRepository.save(user);

        // Chuyển hướng đến trang đăng nhập sau khi đăng ký thành công
        return "redirect:/login";
    }
}
