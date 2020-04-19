package sourceTest;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.source.Dao;
import ru.otus.source.impl.DaoImpl;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DaoTest {
    private static final Logger logger = LoggerFactory.getLogger(DaoTest.class);

    @Test
    void createTest() {
        Dao dao = new DaoImpl("jdbc:h2:mem:default1");
        dao.create("create table user (id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3));");
        int count = dao.select("select * from user;");
        logger.info("count = " + count);
        assertTrue(count == 0);
    }


    @Test
    void selectTest() {
        Dao dao = new DaoImpl("jdbc:h2:mem:default2");
        dao.create("create table user (id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3));");
        int count = dao.select("select * from user;");
        assertTrue(count == 0);
    }

    @Test
    void insertTest() {
        Dao dao = new DaoImpl("jdbc:h2:mem:default3");
        dao.create("create table user (id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3));");
        dao.insert("insert into user(name, age) values('aaa', 11);");
        dao.insert("insert into user(name, age) values('bbb', 22);");
        dao.insert("insert into user(name, age) values('ccc', 33);");

        int count = dao.select("select * from user;");
        assertTrue(count == 3);
    }

    @Test
    void updateTest() {
        Dao dao = new DaoImpl("jdbc:h2:mem:default4");
        dao.create("create table user (id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3));");
        dao.insert("insert into user(id, name, age) values(1, 'aaa', 11);");

        int count = dao.update("update user set name = 'ddd' where id = 1");
        assertTrue(count == 1);
    }

    @Test
    void deleteTest() {
        Dao dao = new DaoImpl("jdbc:h2:mem:default5");
        dao.create("create table user (id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3));");
        dao.insert("insert into user(name, age) values('aaa', 11);");
        dao.insert("insert into user(name, age) values('bbb', 22);");

        dao.delete("delete user where id = 1;");

        int count = dao.select("select * from user;");
        assertTrue(count == 1);
    }

    @Test
    void selectWithParamTest() {
        Dao dao = new DaoImpl("jdbc:h2:mem:default6");
        dao.create("create table user (id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3));");
        dao.insert("insert into user(name, age) values('aaa', 11);");
        dao.insert("insert into user(name, age) values('ddd', 33);");
        dao.insert("insert into user(name, age) values('bbb', 22);");

        int count = dao.select("select * from user where id = ? and name = ?;", Arrays.asList(2, "ddd"));
        assertTrue(count == 1);
    }

    @Test
    void updateWithParamTest() {
        Dao dao = new DaoImpl("jdbc:h2:mem:default7");
        dao.create("create table user (id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3));");
        dao.insert("insert into user(name, age) values('aaa', 11);");
        dao.insert("insert into user(name, age) values('ddd', 33);");

        int count = dao.update("update user set name = 'aaa' where id = ?", Collections.singletonList(2));
        assertTrue(count == 1);
    }
}
