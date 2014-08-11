package whiteship.service;

import whiteship.domain.User;

/**
 * @author Keeun Baik
 */
public interface UserService {

    User addNewUser(User user);

    void login(User user);
}
