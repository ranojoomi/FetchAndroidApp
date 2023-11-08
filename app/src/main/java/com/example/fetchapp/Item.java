package com.example.fetchapp;

public class Item {

    private final String name;
    private final int listId;
    private final int id;

    public Item(String name, int listId, int id) {
        this.name = name;
        this.listId = listId;
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public int getListId() {
        return this.listId;
    }

    public int getId() {
        return this.id;
    }
}
