package whiteship.common;

import com.mysql.jdbc.Driver;
import org.h2.engine.Role;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.tool.hbm2ddl.DatabaseMetadata;
import org.hibernate.tool.hbm2ddl.SchemaUpdateScript;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import whiteship.Application;
import whiteship.TestConfiguration;
import whiteship.domain.Project;
import whiteship.domain.ProjectUser;
import whiteship.domain.User;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * @author Keeun Baik
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class HibernateSchemaGeneratorTest {

    @Autowired DataSource dataSource;
    Configuration cfg;
    MySQL5Dialect dialect = new MySQL5Dialect();

    @Before
    public void setup() {
        cfg = new Configuration();
        cfg.setProperty("hibernate.dialect", MySQL5Dialect.class.getName());
        cfg.setProperty("hibernate.connection.driver_class", Driver.class.getName());
        cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost/yobi");
        cfg.setProperty("hibernate.connection.username", "yobi");
        cfg.setProperty("hibernate.connection.password", "yobi");
        cfg.setNamingStrategy(new org.hibernate.cfg.ImprovedNamingStrategy());
        cfg.addAnnotatedClass(Role.class);
        cfg.addAnnotatedClass(Project.class);
        cfg.addAnnotatedClass(User.class);
        cfg.addAnnotatedClass(ProjectUser.class);
    }

    @Test
    @Ignore
    public void createSchema() throws SQLException {
        String[] sqls = cfg.generateSchemaCreationScript(dialect);
        for(String sql : sqls) {
            System.out.println(sql);
        }
    }

    @Test
    @Ignore
    public void updateSchema() throws SQLException {
        DatabaseMetadata meta = new DatabaseMetadata(dataSource.getConnection(), dialect, cfg);
        List<SchemaUpdateScript> scripts = cfg.generateSchemaUpdateScriptList(dialect, meta);
        for(SchemaUpdateScript script : scripts) {
            System.out.println(script.getScript());
        }
    }

}
