package ua.savchenko.servlet;

import ua.savchenko.entity.User;
import ua.savchenko.service.UserService;
import ua.savchenko.servlet.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddUserServlet extends HttpServlet{
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = PageGenerator.instance().getPage("addUser.html", null);
        response.getWriter().print(page);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html; charset=utf-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setName(request.getParameter("name"));
        String dateOfBirth = request.getParameter("dateOfBirth");
        user.setDateOfBirth(LocalDate.parse(dateOfBirth, formatter));
        userService.addToRepository(user);
        response.sendRedirect("/users");
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
