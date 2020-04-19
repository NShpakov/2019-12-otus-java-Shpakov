package jdbcTemplateTests;

import ru.otus.data.jdbctemplate.JdbcTemplate;
import ru.otus.data.jdbctemplate.impl.JdbcTemplateImpl;
import ru.otus.objects.Account;
import ru.otus.source.impl.DaoImpl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountJdbcTemplateTest {

    @Test
    void create() {
        JdbcTemplate<Account> userJdbcTemplate = new JdbcTemplateImpl<>(new DaoImpl("jdbc:h2:mem:default1"));
        userJdbcTemplate.create(new Account(0L, "AccountType", 123));
        assertEquals(new Account(0L, "AccountType", 123), userJdbcTemplate.load(0L, Account.class));
    }

    @Test
    void update() {
        JdbcTemplate<Account> userJdbcTemplate = new JdbcTemplateImpl<>(new DaoImpl("jdbc:h2:mem:default2"));
        userJdbcTemplate.create(new Account(0L, "AccountType", 123));
        userJdbcTemplate.update(new Account(0L, "NewAccountType", 123));
        assertEquals(new Account(0L, "NewAccountType", 123), userJdbcTemplate.load(0L, Account.class));
    }

    @Test
    void createOrUpdateIfNotExist() {
        JdbcTemplate<Account> userJdbcTemplate = new JdbcTemplateImpl<>(new DaoImpl("jdbc:h2:mem:default3"));
        userJdbcTemplate.createOrUpdate(new Account(0L, "AccountType", 123));
        assertEquals(new Account(0L, "AccountType", 123), userJdbcTemplate.load(0L, Account.class));
    }

    @Test
    void createOrUpdateIfExist() {
        JdbcTemplate<Account> userJdbcTemplate = new JdbcTemplateImpl<>(new DaoImpl("jdbc:h2:mem:default4"));
        userJdbcTemplate.create(new Account(0L, "AccountType", 123));
        userJdbcTemplate.createOrUpdate(new Account(0L, "NewAccountType", 123));
        assertEquals(new Account(0L, "NewAccountType", 123), userJdbcTemplate.load(0L, Account.class));
    }

    @Test
    void load() {
        JdbcTemplate<Account> userJdbcTemplate = new JdbcTemplateImpl<>(new DaoImpl("jdbc:h2:mem:default5"));
        userJdbcTemplate.create(new Account(0L, "AccountType1", 111));
        userJdbcTemplate.create(new Account(1L, "AccountType2", 222));
        userJdbcTemplate.create(new Account(2L, "AccountType3", 333));
        userJdbcTemplate.load(2L, Account.class);
        assertEquals(new Account(2L, "AccountType3", 333), userJdbcTemplate.load(2L, Account.class));
    }
}
