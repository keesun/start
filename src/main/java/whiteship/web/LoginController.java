package whiteship.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import whiteship.domain.User;

/**
 * @author Keeun Baik
 */
@Controller
public class LoginController {

    @RequestMapping("/users/loginform")
    public String signin(Model model) {
        model.addAttribute("user", new User());
        return "/users/loginform";
    }

    @RequestMapping("/users/loginfailure")
    public String signinFailure(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("isFailed", true);
        return "redirect:/users/loginform";
    }

}
