package ua.savchenko.service;

import ua.savchenko.dao.UserDao;
import ua.savchenko.dao.UserRepository;
import ua.savchenko.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private UserDao userDao;

    public void initialize(){
        UserRepository userRepository = UserRepository.getInstance();
        userRepository.clear();
        userRepository.add(userDao.getAll());
    }

    public void save(){
        List<User> allUsers = UserRepository.getInstance().getAll();
        List<User> usersToSave = allUsers.stream().filter(t -> t.getId() == null).collect(Collectors.toList());
        userDao.save(usersToSave);
        initialize();
    }

    public List<User> getAll(){
        return UserRepository.getInstance().getAll();
    }

    public void addToRepository(User user){
        UserRepository.getInstance().add(user);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
