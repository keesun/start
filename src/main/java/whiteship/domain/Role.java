package whiteship.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Keeun Baik
 */
@Entity
@Data
public class Role {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private boolean active;


}
