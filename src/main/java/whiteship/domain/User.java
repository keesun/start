/**
 * Yobi, Project Hosting SW
 *
 * Copyright 2012 NAVER Corp.
 * http://yobi.io
 *
 * @Author Ahn Hyeok Jun
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package whiteship.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import whiteship.domain.enumeration.UserRole;
import whiteship.domain.enumeration.UserState;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = {"id", "name", "loginId", "email"})
public class User {

    public static final String LOGIN_ID_PATTERN = "^[a-zA-Z0-9-]+([_.][a-zA-Z0-9-]+)*$";

    @Id @GeneratedValue
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    @NotEmpty
    @Pattern(regexp = LOGIN_ID_PATTERN, message = "user.wrongloginId.alert")
    private String loginId;

    @Column(nullable = false)
    @NotEmpty
    private String password;

    @Column(unique = true)
    @Email
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private String lang;

    private String url;

    @OneToMany(mappedBy = "user")
    private List<ProjectUser> projectUser;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private UserState userState;

}
