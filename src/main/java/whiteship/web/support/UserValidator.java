package whiteship.web.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import whiteship.domain.User;
import whiteship.repository.UserRepository;

/**
 * @author Keeun Baik
 */
@Component
public class UserValidator {

    @Autowired
    UserRepository repository;

    public boolean validateNewUser(User user, BindingResult result) {
        User existingUser = repository.findByEmail(user.getEmail());
        if(existingUser != null) {
            result.rejectValue("email", "user.email.duplicate", "user.email.duplicate");
        }

        existingUser = repository.findByLoginId(user.getLoginId());
        if(existingUser != null) {
            result.rejectValue("loginId", "user.loginId.duplicate", "user.loginId.duplicate");
        }

        return result.hasErrors();
    }
}
