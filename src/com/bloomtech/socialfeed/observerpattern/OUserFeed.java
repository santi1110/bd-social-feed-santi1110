package com.bloomtech.socialfeed.observerpattern;

import com.bloomtech.socialfeed.App;
import com.bloomtech.socialfeed.models.Post;
import com.bloomtech.socialfeed.models.User;

import java.util.ArrayList;
import java.util.List;

//TODO: Implement Observer Pattern
public class OUserFeed implements Observer{
    private final User user;
    private final List<Post> feed;

    public OUserFeed(User user) {
        this.user = user;
        this.feed= new ArrayList<>();
        App.sourceFeed.attach(this);
        //TODO: update OUserFeed in constructor after implementing observer pattern
    }
    @Override
    public void update(List<Post> posts) {
        this.feed.clear();

        // Iterate through the received posts
        for (Post post : posts) {
            // Check if the current user is following the author of the post
            if (user.isFollowing(post.getUsername())) {
                // Add the post to the feed
                this.feed.add(post);
            }
        }
    }



    public User getUser() {
        return user;
    }

    public List<Post> getFeed() {
        return feed;
    }
}
