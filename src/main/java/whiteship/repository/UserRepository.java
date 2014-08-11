package whiteship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import whiteship.domain.User;

/**
 * @author Keeun Baik
 */
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    User findByLoginId(String loginId);

    User findByEmail(String email);
}
