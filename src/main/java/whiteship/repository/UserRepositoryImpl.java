package whiteship.repository;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;
import whiteship.domain.QUser;
import whiteship.domain.User;

import java.util.List;

/**
 * @author Keeun Baik
 */
@Repository
public class UserRepositoryImpl extends QueryDslRepositorySupport implements UserRepositoryCustom {

    public UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public List<User> allUsers() {
        QUser qUser = QUser.user;
        return from(qUser).list(qUser);
    }

}
