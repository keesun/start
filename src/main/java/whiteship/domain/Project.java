package whiteship.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

/**
 * @author Keeun Baik
 */
@Entity
@Data
public class Project {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String overview;
    private String vcs;
    private String siteurl;
    private String owner;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectUser> projectUser;

}
