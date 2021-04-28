package com.elior.factory;
import java.util.ArrayList;
import java.util.List;

import com.elior.beans.Category;

public class FactoryCategory {
	public static List<String> initList() {
		List<String> categoryList = new ArrayList<>();
		for (Category category : Category.values()) {
			String name = category.name();
			categoryList.add(name);
		}
		return categoryList;
	}
}
