package ua.savchenko.dao;

import ua.savchenko.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users = new ArrayList<>();

    private final static UserRepository INSTANCE = new UserRepository();

    private UserRepository(){

    }

    public static UserRepository getInstance(){
        return INSTANCE;
    }

    public void add(User user){
        users.add(user);
    }

    public void add(List<User> usersToAdd){
        users.addAll(usersToAdd);
    }

    public List<User> getAll() {
        return users;
    }

    public void clear() {
        users.clear();
    }
}
