package ua.savchenko.dao;

import org.junit.Before;
import org.junit.Test;
import ua.savchenko.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoTest {
    UserDao userDao = new UserDao();

    @Before
    public void before(){
        DataSource dataSource = new DataSource();
        dataSource.setJdbcDriver("com.mysql.jdbc.Driver");
        dataSource.setLogin("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost/userstore");
        userDao.setDataSource(dataSource);
    }

    @Test
    public void testGetAllUsersFromDb(){
        List<User> users = userDao.getAll();
        User firstUser = users.get(0);
        assertEquals(new Integer(1), firstUser.getId());
        assertEquals("Aleksandr", firstUser.getName());
        assertNotNull(firstUser.getDateOfBirth());

        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSaveUsersToDB(){
        User user1 = new User();
        user1.setName("Valera");
        user1.setDateOfBirth(LocalDate.of(1994, 12, 3));
        User user2 = new User();
        user2.setName("Semen");
        user2.setDateOfBirth(LocalDate.of(1984, 11, 4));

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        userDao.save(users);
    }
}