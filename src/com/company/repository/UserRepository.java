package com.company.repository;

import com.company.entity.User;

import java.io.*;

public class UserRepository {


    public void saveAll(User[] users) {

        for (int i = 0; i < users.length; i++) {

            User user = users[i];

            if (user != null) {
                save(user);
            }
        }

    }

    public void delete(User user) {

        User[] users = getAllUsersFromStore();

        User[] newUsers = new User[users.length - 1];

        boolean isFound = false;

        for (int i = 0; i < users.length; i++) {

            if (users[i].getId() == user.getId()) {
                users[i] = null;
                isFound = true;
                System.out.printf("User with id=%s , is found%n", user.getId());
                break;
            }
        }

        if (!isFound) {
            throw new RuntimeException(String.format("User with id=%s, is not found!%n", user.getId()));
        }

        int count = 0;

        for (int i = 0; i < users.length; i++) {

            if (users[i] != null) {
                newUsers[count] = users[i];
                count++;
            }
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("people");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.flush();

            objectOutputStream.writeObject(newUsers);
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.printf("User=%s, successfully deleted%n", user);

    }


    public void save(User user) {

        User[] newAllUsers;

        try {

            User[] oldAllUsers = getAllUsersFromStore();

            if (oldAllUsers == null) {
                newAllUsers = new User[]{user};
            } else {

                newAllUsers = new User[oldAllUsers.length + 1];
                newAllUsers[oldAllUsers.length] = user;

                for (int i = 0; i < oldAllUsers.length; i++) {
                    newAllUsers[i] = oldAllUsers[i];
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream("people");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.flush();

            objectOutputStream.writeObject(newAllUsers);
            objectOutputStream.close();

        } catch (IOException e) {
            System.out.println("Cant read users, check details in code!");
        }


    }

    public User[] getAllUsersFromStore() {

        try {

            FileInputStream fileInputStream = new FileInputStream("people");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            User[] users = (User[]) objectInputStream.readObject();
            objectInputStream.close();

            return users;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Cant read users, check details in code!");
            return new User[0];
        }

    }

    public boolean clearFile() {
    //todo realize cleaning file
    //todo for 1 day
        return true;
    }
}
