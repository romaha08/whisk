package com.enums;

public enum Product {
    MILK("Milk"),
    BREAD("Bread"),
    BUTTER("Butter"),
    CHEESE("Cheese"),
    EGGS("Eggs");

    private String value;

    Product(String productName) {
        this.value = productName;
    }

    public String getValue() {
        return value;
    }

    public static Product getByName(String value) {
        for (Product v : values()) {
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
