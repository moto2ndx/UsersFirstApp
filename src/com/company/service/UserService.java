package com.company.service;

import com.company.entity.User;
import com.company.repository.UserRepository;

import java.util.Arrays;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User[] getAllUsers() {
        return userRepository.getAllUsersFromStore();
    }

    public User getById(Long id) {

        User[] users = userRepository.getAllUsersFromStore();

        for (int i = 0; i < users.length ; i++) {

            User user = users[i];

            if (user.getId() == id) {
                return user;
            }
        }

        throw new RuntimeException("User not found");
    }

    public User getByName(String name) {

        User[] users = userRepository.getAllUsersFromStore();

        for (int i = 0; i < users.length; i++) {
            User user = users[i];
            if (user.getName().equals(name)) {
                return user;
            }
        }

        throw new RuntimeException("User not found");
    }

    //todo Realize this method
    public User getByAge(Integer age) {

        User[] users = userRepository.getAllUsersFromStore();

        for (int i = 0; i < users.length; i++) {
            User user = users[i];
            if (user.getAge() == age) {
                return user;
            }
        }

        throw new RuntimeException("User not found");
    }


    public User update(User user) {

        User[] users = userRepository.getAllUsersFromStore();

        for (int i = 0; i < users.length ; i++) {

            if (users[i].getId() == user.getId()) {
                users[i] = user;
                userRepository.saveAll(users);
                return user;
            }
        }

       throw new RuntimeException("User not found");
    }

    public User delete(User user) {

        userRepository.delete(user);

        return user;

    }

    public User addUser(User user) {

        User[] allUsers = getAllUsers();

        if (allUsers.length == 0) {
            user.setId(0);
        } else {

            long[] ids = new long[allUsers.length];

            for (int i = 0; i < allUsers.length; i++) {
                ids[i] = allUsers[i].getId();
            }
            Arrays.sort(ids);

            long id = ids[ids.length - 1] + 1;

            user.setId(id);
        }

        userRepository.save(user);

        return user;
    }
}
