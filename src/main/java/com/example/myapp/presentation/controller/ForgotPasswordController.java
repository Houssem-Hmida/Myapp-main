package com.example.myapp.presentation.controller;

import com.example.myapp.business.service.IUtilisateurService;
import com.example.myapp.persistence.model.Utilisateur;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class ForgotPasswordController {

    @Autowired
    private final IUtilisateurService iUtilisateurService;

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        Utilisateur utilisateur = iUtilisateurService.getByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (utilisateur == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }

        return "reset_password_form.html";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        Utilisateur utilisateur = iUtilisateurService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset youuuuur password");

        if (utilisateur == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            iUtilisateurService.updatePassword(utilisateur, password);

            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "message";
    }
}
