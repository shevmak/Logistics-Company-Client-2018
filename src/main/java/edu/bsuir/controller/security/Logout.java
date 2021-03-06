package edu.bsuir.controller.security;

import edu.bsuir.model.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class Logout {

    @Value("${message.logoutSuccessful}")
    private String messageLogout;

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(Model model, @SessionAttribute("userForm") Users userForm){

        System.out.println(userForm.getName() + " successfully logout!");

        model.addAttribute("logoutMessage", messageLogout);

        return "redirect:/authenticate";
    }
}
