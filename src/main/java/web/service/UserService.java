package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void addUser(User user);
    List<User> listUsers();
    void removeUser(Integer id);
    void updateUser(User user);
    User getUserById(Integer id);
    User getUserByName(String name);
    Set<Role> getAllRoles();
}
