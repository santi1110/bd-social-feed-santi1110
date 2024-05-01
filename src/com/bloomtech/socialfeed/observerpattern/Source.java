package com.bloomtech.socialfeed.observerpattern;


public interface Source {
    void attach(Observer observer);
    void detach(Observer observer);
    void updateAll();
}
