package whiteship.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import whiteship.domain.User;
import whiteship.repository.UserRepository;
import whiteship.service.UserService;
import whiteship.web.support.UserValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SignupController {

    @Autowired
    UserValidator userValidator;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users/signupform", method = RequestMethod.GET)
    public String signupForm(Model model) {
        model.addAttribute("user", new User());
        return "/users/signupform";
    }

    @RequestMapping(value = "/users/signup", method = RequestMethod.POST)
    public String newUser(@ModelAttribute @Valid User user, BindingResult result) {
        userValidator.validateNewUser(user, result);
        if(result.hasErrors()) {
            return "/users/signupform";
        }
        User newUser = userService.addNewUser(user);
        userService.login(newUser);
        return "redirect:/";
    }

}
