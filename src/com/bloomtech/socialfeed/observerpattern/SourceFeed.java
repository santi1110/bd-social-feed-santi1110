package com.bloomtech.socialfeed.observerpattern;

import com.bloomtech.socialfeed.helpers.LocalDateTimeAdapter;
import com.bloomtech.socialfeed.models.Post;
import com.bloomtech.socialfeed.models.User;
import com.bloomtech.socialfeed.repositories.PostRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//TODO: Implement Observer Pattern
public class SourceFeed implements Source {
    private final PostRepository postRepository = new PostRepository();

    private final List<Post> posts;
    private final List<Observer> observers;
    private Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .setPrettyPrinting()
            .create();



    public SourceFeed() {

        this.posts = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void getAllPosts() {
        postRepository.getAllPosts();

    }

    public Post addPost(User user, String body) {

        LocalDateTime currentDateTime = LocalDateTime.now(); // Get current date and time

        Post post = new Post(user.getUsername(), currentDateTime, body);

        // Serialize the post to JSON
        String postJson = gson.toJson(post);

        // Deserialize the JSON string back into a Post object
        Post deserializedPost = gson.fromJson(postJson, Post.class);

        // Add the deserialized post to the repository
        postRepository.addPost(deserializedPost);

        // Add the post to the local list (if needed)
        posts.add(post);

        // Notify all attached observers about the new post
        notifyObservers();

        return post;
    }
    public List<Observer> getObservers() {
        return observers;
    }

    public List<Post> getPosts() {
        return posts;
    }

@Override
public void attach(Observer observer) {
    observers.add(observer);
}

@Override
public void detach(Observer observer) {
    observers.remove(observer);
}

@Override
public void updateAll() {
    for (Observer observer : observers) {
        observer.update(posts);
    }}
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(posts); // Update each observer with the new posts
        }

}}

