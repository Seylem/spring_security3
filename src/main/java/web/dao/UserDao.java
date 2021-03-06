package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import web.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    List<User> listUsers();
    void removeUser(Integer id);
    void updateUser(User user);
    User getUserById(Integer id);
    User getUserByName(String name);
}
