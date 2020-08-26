package com.enums;

public enum MenuItem {
    HOME("Home"),
    RECIPES("RecipeS"),
    PLANNER("Planner"),
    SHOPPING("Shopping");

    private String value;

    MenuItem(String menuName) {
        this.value = menuName;
    }

    public String getValue() {
        return value;
    }

    public static MenuItem getByName(String value) {
        for (MenuItem v : values()) {
            if (v.value.replaceAll("(\\s+|_)", "").toLowerCase().contains(value.replaceAll("(\\s+|_)", "").toLowerCase())) {
                return v;
            }
            if(value.toLowerCase().contains(v.value.toLowerCase())){
                return v;
            }
        }
        throw new EnumConstantNotPresentException(MenuItem.class, value);
    }
}
