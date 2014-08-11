package whiteship.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import whiteship.repository.UserRepository;

/**
 * @author Keeun Baik
 */
@Controller
public class TestController {

    @Autowired UserRepository userRepository;

    @RequestMapping("/test/delete/all")
    public String deleteAll() {
        userRepository.deleteAll();
        return "redirect:/";
    }

}
