package space.delusive.tversu.dao;

import space.delusive.tversu.entity.User;

public interface UserDao {
    User getUserById(long id);

    boolean addUser(User user);

    boolean updateUser(User user);
}