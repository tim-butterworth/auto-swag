package com.example.demo.json;

import java.util.LinkedList;
import java.util.List;

public class ErrorMessages {

    private List<String> messages = new LinkedList<>();

    public void addMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return new LinkedList<>(messages);
    }
}
