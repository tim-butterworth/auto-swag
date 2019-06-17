package com.example.demo.controllers;

public class LargeObject {

    private String name;
    private Long id;
    private ChildObject child;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChildObject getChild() {
        return child;
    }

    public void setChild(ChildObject child) {
        this.child = child;
    }
}
