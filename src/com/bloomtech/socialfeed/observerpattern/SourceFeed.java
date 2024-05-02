package com.bloomtech.socialfeed.observerpattern;

import com.bloomtech.socialfeed.models.Post;
import com.bloomtech.socialfeed.models.User;
import com.bloomtech.socialfeed.repositories.PostRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//TODO: Implement Observer Pattern
public class SourceFeed implements Source {
    private final PostRepository postRepository = new PostRepository();

    private final List<Post> posts;
    private final List<Observer> observers;

    public SourceFeed() {

        this.posts = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void getAllPosts() {
        postRepository.getAllPosts();

    }

    public Post addPost(User user, String body) {
        Post post = new Post(user.getUsername(), LocalDateTime.now().toString(), body);

        // Add the post to the repository
        posts.add(post);
        postRepository.addPost(post);

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

