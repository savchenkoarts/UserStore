package ua.savchenko.servlet;

import ua.savchenko.entity.User;
import ua.savchenko.service.UserService;
import ua.savchenko.servlet.util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServlet extends HttpServlet {

    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userService.getAll();
        Map<String, Object> map = new HashMap<>();
        map.put("users", users);
        String page = PageGenerator.instance().getPage("users.html", map);
        response.getWriter().print(page);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/html; charset=utf-8");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userService.save();
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/users");
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
