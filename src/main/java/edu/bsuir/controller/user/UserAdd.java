package edu.bsuir.controller.user;

import edu.bsuir.model.Users;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserAdd {

    @Value("${message.error}")
    private String messageError;

    @Value("${url.user}")
    private String URL_USER;

    @Value("${url.user.login}")
    private String URL_USER_LOGIN;

    @RequestMapping(value = {"/addUser"}, method = RequestMethod.GET)
    public String showAddUserPage(Model model) {

        Users userObj = new Users();
        model.addAttribute("userObj", userObj);

        return "addUser";
    }

    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    public String savePerson(Model model, @ModelAttribute("userObj") Users userObj) {

        RestTemplate restTemplate = new RestTemplate();

        Users user = restTemplate.getForObject(URL_USER_LOGIN + "/" + userObj.getLogin(), Users.class);

        if (user.getId() == 0) {
            HttpEntity<Users> requestBody = new HttpEntity<>(userObj);
            ResponseEntity<Users> result = restTemplate.postForEntity(URL_USER, requestBody, Users.class);
            if (result.getStatusCode() == HttpStatus.OK) {
                return "redirect:/index";
            }
        }
        model.addAttribute("errorMessage", messageError);
        return "redirect:/index";
    }
}
