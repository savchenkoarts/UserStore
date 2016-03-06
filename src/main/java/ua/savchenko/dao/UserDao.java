package ua.savchenko.dao;

import ua.savchenko.entity.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String GET_ALL_USERS_QUERY = "select * from user;";
    private static final String SAVE_USER_QUERY = "insert into user(name, dateOfBirth) value(?,?);";

    private DataSource dataSource;

    public void save(List<User> users){
        try(Connection connection = dataSource.getConnection()) {
            for (User user : users) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(SAVE_USER_QUERY)){
                    preparedStatement.setString(1, user.getName());
                    LocalDate dateOfBirth = user.getDateOfBirth();
                    Timestamp timestamp = Timestamp.valueOf(dateOfBirth.atStartOfDay());
                    preparedStatement.setTimestamp(2, timestamp);
                    preparedStatement.execute();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAll(){
        System.out.println("Getting all users from DB");
        List<User> users = new ArrayList<>();

        Connection connection = dataSource.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                users.add(UserMapper.map(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
