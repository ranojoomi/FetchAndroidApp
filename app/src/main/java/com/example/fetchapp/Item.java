package com.example.fetchapp;

public class Item {

    private String name;
    private int listId;
    private int id;

    public Item(String name, int listId, int id) {
        this.name = name;
        this.listId = listId;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getItemId() {
        return this.listId;
    }

    public int getId() {
        return this.id;
    }
}
