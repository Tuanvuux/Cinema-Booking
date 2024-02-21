/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring_mvc_project_final.controller;

import com.mycompany.spring_mvc_project_final.entities.AccountEntity;
import com.mycompany.spring_mvc_project_final.entities.Role;
import com.mycompany.spring_mvc_project_final.enums.UserStatus;
import com.mycompany.spring_mvc_project_final.repository.AccountRepository;
import com.mycompany.spring_mvc_project_final.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class LoginController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    RoleRepository roleRepository;

    @RequestMapping("/login")
    public String loginPage(Model model, @RequestParam(value = "error", required = false) boolean error) {

        if (error) {
            model.addAttribute("message", "Login Fail!!!");
        }
        return "login";
    }
    @RequestMapping("/admin/home")
    public String viewHome(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        }

        model.addAttribute("message", "Hello Admin: " + username);
        return "admin/home";
    }

    @GetMapping(value = "/admin/add")
    public String addAccount(){
        return "/admin/add";
    }
    @PostMapping(value = "/admin/add")
    public String addAccount(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             Model model) {

        if (email.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "Email và mật khẩu không thể để trống.");
            return "/admin/add";
        }

        // Kiểm tra xem email đã được sử dụng chưa
        AccountEntity existingAccount = accountRepository.findByEmail(email);
        if (existingAccount != null) {
            model.addAttribute("error", "Email đã được sử dụng.");
            return "/admin/add";
        }

        AccountEntity newAccount = new AccountEntity();
        newAccount.setEmail(email);
        newAccount.setPassword(bCryptPasswordEncoder.encode(password));
        newAccount.setStatus(UserStatus.ACTIVE);

        // muon lay role tu DB thi sd RoleRepository de select toan bo list role

        List<Role> roleList = roleRepository.findAll();
//       Set<RoleEntity> roleEntitySet = new HashSet<>();
//       RoleEntity roleEntity = new RoleEntity();
//       roleEntity.setRole(Role.ROLE_USER);
//       roleEntitySet.add(roleEntity);
        Set<Role> roleSet = new HashSet<Role>(roleList);

        newAccount.setUserRoles(roleSet);

        accountRepository.save(newAccount);

        return "redirect:/admin/home"; // Change the redirect path to the appropriate page
    }



    @RequestMapping("/user/home")
    public String viewHome(Model model, HttpSession session) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = principal.toString();

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
            session.setAttribute("username", username);

        }

        return "student/index";
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        return "home";
    }

    @RequestMapping(value = {"/test2"}, method = RequestMethod.GET)
    public String welcomePage44 (Model model) {
        return "home";
    }

}
