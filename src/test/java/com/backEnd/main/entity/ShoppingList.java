package com.backEnd.main.entity;

import lombok.Getter;
import lombok.Setter;

public class ShoppingList {
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Boolean primary;

    public ShoppingList(String name, Boolean primary) {
        this.setName(name);
        this.setPrimary(primary);
    }

    public ShoppingList(String name) {
        this.setName(name);
        this.setPrimary(false);
    }

}
