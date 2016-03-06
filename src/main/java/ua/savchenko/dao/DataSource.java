package ua.savchenko.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataSource {
    private String url;
    private String login;
    private String password;
    private String jdbcDriver;
    public Connection getConnection(){
        try {
            Class.forName(jdbcDriver);
            return DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }

    public void setJdbcDriver(String jdbcDriver) {
        this.jdbcDriver = jdbcDriver;
    }

}
