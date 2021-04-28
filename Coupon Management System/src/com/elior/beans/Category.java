package com.elior.beans;

import java.util.HashMap;
import java.util.Map;

public enum Category{
	FOOD(1), BABY(2), PETS(3), FASHION(4), TOYS(5), SHOPPING(6), HEALTH(7), SPORT(8), ELECTRONICS(9), PC(10),
	VACTION(11), RESTAURANTS(12);

	private int value;
	private static Map<Object, Object> map = new HashMap<>();

    private Category(int value) {
        this.value = value;
    }

    static {
        for (Category category : Category.values()) {
            map.put(category.value, category);
        }
    }

    public static Category valueOf(int category) {
        return (Category) map.get(category);
    }

    public int getValue() {
        return value;
    }
}
