package ua.savchenko;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ua.savchenko.dao.DataSource;
import ua.savchenko.dao.UserDao;
import ua.savchenko.service.UserService;
import ua.savchenko.servlet.AddUserServlet;
import ua.savchenko.servlet.UserServlet;

public class Starter {
    public static void main(String[] args) throws Exception {
        // dao
        DataSource dataSource = new DataSource();
        dataSource.setJdbcDriver("com.mysql.jdbc.Driver");
        dataSource.setLogin("root");
        dataSource.setPassword("root");
        dataSource.setUrl("jdbc:mysql://localhost/userstore");

        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource);

        // service
        UserService userService = new UserService();
        userService.setUserDao(userDao);
        userService.initialize();

        // servlet
        UserServlet userServlet = new UserServlet();
        AddUserServlet addUserServlet = new AddUserServlet();
        userServlet.setUserService(userService);
        addUserServlet.setUserService(userService);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.addServlet(new ServletHolder(userServlet), "/users");
        servletContextHandler.addServlet(new ServletHolder(addUserServlet), "/addUser");

        Server server = new Server(8080);
        server.setHandler(servletContextHandler);
        server.start();
    }
}
