package ua.savchenko.dao;

import ua.savchenko.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Month;

public class UserMapper {
    public static User map(ResultSet resultSet){
        User user = new User();
        try {
            Timestamp timestamp = resultSet.getTimestamp("dateOfBirth");
            int year = timestamp.toLocalDateTime().getYear();
            Month month = timestamp.toLocalDateTime().getMonth();
            int day = timestamp.toLocalDateTime().getDayOfMonth();

            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setDateOfBirth(LocalDate.of(year, month, day));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
