package com.bloomtech.socialfeed.repositories;

import com.bloomtech.socialfeed.models.Post;
import com.bloomtech.socialfeed.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostRepository {
    private static final String POST_DATA_PATH = "src/resources/PostData.json";

    public PostRepository() {
    }

    public List<Post> getAllPosts() {
        List<Post> allPosts = new ArrayList<>();
        Gson gson = new Gson();

        try (Reader reader = new FileReader(POST_DATA_PATH)) {
            Post[] posts = gson.fromJson(reader, Post[].class);
            if (posts != null) {
                for (Post post : posts) {
                    allPosts.add(post);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allPosts;

        //TODO: return all posts from the PostData.json file
    }

    public List<Post> findByUsername(String username) {
        return getAllPosts()
                .stream()
                .filter(p -> p.getUsername().equals(username))
                .collect(Collectors.toList());
    }
    public void addPost(Post post) {
        List<Post> allPosts = getAllPosts();

        // Add the new post to the list of all posts
        allPosts.add(post);

        // Write the updated list of posts back to the PostData.json file
        try (Writer writer = new FileWriter(POST_DATA_PATH)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(allPosts, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}
        //TODO: Write the new Post data to the PostData.json file

        //TODO: Return an updated list of all posts


