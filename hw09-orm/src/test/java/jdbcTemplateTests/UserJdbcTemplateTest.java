package jdbcTemplateTests;

import org.junit.jupiter.api.Test;
import ru.otus.data.jdbctemplate.JdbcTemplate;
import ru.otus.data.jdbctemplate.impl.JdbcTemplateImpl;
import ru.otus.objects.User;
import ru.otus.source.impl.DaoImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserJdbcTemplateTest {

    @Test
    void create() {
        JdbcTemplate<User> userJdbcTemplate = new JdbcTemplateImpl<>(new DaoImpl("jdbc:h2:mem:default1"));
        userJdbcTemplate.create(new User(0L, "UserName", 18));
        assertEquals(new User(0L, "UserName", 18), userJdbcTemplate.load(0L, User.class));
    }

    @Test
    void update() {
        JdbcTemplate<User> userJdbcTemplate = new JdbcTemplateImpl<>(new DaoImpl("jdbc:h2:mem:default2"));
        userJdbcTemplate.create(new User(0L, "UserName", 20));
        userJdbcTemplate.update(new User(0L, "UserName2", 21));
        assertEquals(new User(0L, "UserName2", 21), userJdbcTemplate.load(0L, User.class));
    }

    @Test
    void createOrUpdateIfNotExist() {
        JdbcTemplate<User> userJdbcTemplate = new JdbcTemplateImpl<>(new DaoImpl("jdbc:h2:mem:default3"));
        userJdbcTemplate.createOrUpdate(new User(0L, "UserName", 18));
        assertEquals(new User(0L, "UserName", 18), userJdbcTemplate.load(0L, User.class));
    }

    @Test
    void createOrUpdateIfExist() {
        JdbcTemplate<User> userJdbcTemplate = new JdbcTemplateImpl<>(new DaoImpl("jdbc:h2:mem:default4"));
        userJdbcTemplate.create(new User(0L, "UserName", 18));
        userJdbcTemplate.createOrUpdate(new User(0L, "UserName2", 25));
        assertEquals(new User(0L, "UserName2", 25), userJdbcTemplate.load(0L, User.class));
    }

    @Test
    void load() {
        JdbcTemplate<User> userJdbcTemplate = new JdbcTemplateImpl<>(new DaoImpl("jdbc:h2:mem:default5"));
        userJdbcTemplate.createOrUpdate(new User(123L, "UserName111", 30));
        assertEquals(new User(123L, "UserName111", 30), userJdbcTemplate.load(123L, User.class));
    }
}
