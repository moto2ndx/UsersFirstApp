package com.company.ui;

import com.company.entity.User;
import com.company.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class UiPart {

    UserService userService;

    public UiPart() {
        this.userService = new UserService();
    }

    public void appLoop() throws IOException {

        showMainMenu();

        InputStreamReader sr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(sr);

        int choise = Integer.parseInt(br.readLine().trim());

        while (choise != 0) {

            switch (choise) {

                case 1:
                    crudOperations();
                    showMainMenu();
                    choise = Integer.parseInt(br.readLine().trim());
                    break;

                case 2:
                    queryOperations();
                    showMainMenu();
                    choise = Integer.parseInt(br.readLine().trim());
                    break;

                case 0: System.exit(200);
                default:break;
            }

        }
        System.exit(200);


    }

    public void queryOperations() {

        System.out.println("Its query operations: choose somth");
        System.out.println("Not realized!!!!");
    }

    public void crudOperations() throws IOException {

        showCrudMenu();

        InputStreamReader sr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(sr);
        int choise = Integer.parseInt(br.readLine().trim());

        while (choise != 0) {

            switch (choise) {
                case 1:
                    createUser();
                    showCrudMenu();
                    choise = Integer.parseInt(br.readLine().trim());
                    break;
                case 2:
                    deleteUser();
                    showCrudMenu();
                    choise = Integer.parseInt(br.readLine().trim());
                    break;
                case 3:
                    System.out.println(Arrays.toString(userService.getAllUsers()));
                    showCrudMenu();
                    choise = Integer.parseInt(br.readLine().trim());
                    break;
                default:
                    break;
            }

        }



    }

    public void deleteUser() throws IOException {


        System.out.println("Please enter user id for delete");
        System.out.println("Users : " + Arrays.toString(userService.getAllUsers()));
        InputStreamReader sr = new InputStreamReader(System.in); // створити екземпляр InputStreamReader
        BufferedReader br = new BufferedReader(sr); // екземпляр класу буферизації
        long id = Long.parseLong(br.readLine());

        User user = userService.getById(id);
        userService.delete(user);

    }

    public void createUser() throws IOException {

        InputStreamReader sr = new InputStreamReader(System.in); // створити екземпляр InputStreamReader
        BufferedReader br = new BufferedReader(sr); // екземпляр класу буферизації
        String s;

        System.out.println("create User:");

        User user = new User();

        int counter = 0;
        while (counter < 2) {


            System.out.println("Enter name");
            String name = br.readLine();
            user.setName(name);
            counter++;

            System.out.println("Enter age");
            int age = Integer.parseInt(br.readLine());
            user.setAge(age);
            counter++;
        }

        userService.addUser(user);

    }

    private void showCrudMenu() {
        System.out.println("Make a choose of opearation:");
        System.out.println("1 == create new user");
        System.out.println("2 == delete user by id");
        System.out.println("3 == print all users");
        System.out.println("0 == return back");
    }

    private void showMainMenu() {
        System.out.println("Hello, make choose type of opearations");

        System.out.println("1 == CREATE, READ, UPDATE, DELETE");
        System.out.println("2 == QUERY by ***");
        System.out.println("0 == EXIT");
    }


}
