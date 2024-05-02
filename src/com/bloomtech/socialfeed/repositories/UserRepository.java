package com.bloomtech.socialfeed.repositories;

import com.bloomtech.socialfeed.models.User;
import com.bloomtech.socialfeed.validators.UserInfoValidator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepository {
    private static final String USER_DATA_PATH = "src/resources/UserData.json";

    private static final UserInfoValidator userInfoValidator = new UserInfoValidator();

    public UserRepository() {
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        Gson gson = new Gson();

        try (Reader reader = new FileReader(USER_DATA_PATH)) {
            User[] users = gson.fromJson(reader, User[].class);
            if (users != null) {
                for (User user : users) {
                    allUsers.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allUsers;
        //TODO: return parsed list of Users from UserData.json

    }

    public Optional<User> findByUsername(String username) {
        return getAllUsers()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    public void save(User user) {
        List<User> allUsers = getAllUsers();

        Optional<User> existingUser = allUsers.stream()
                .filter(u -> u.getUsername().equals(user.getUsername()))
                .findFirst();

        if (!existingUser.isEmpty()) {
            throw new RuntimeException("User with name: " + user.getUsername() + " already exists!");
        }
        allUsers.add(user);
        try (Writer writer = new FileWriter(USER_DATA_PATH)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(allUsers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        //TODO: Write allUsers to UserData.json
    }
}}
