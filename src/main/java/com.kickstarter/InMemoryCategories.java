package com.kickstarter;

import java.util.LinkedList;
import java.util.List;

class InMemoryCategories implements Categories {

    private List<Category> data = new LinkedList<>();

    @Override
    public void add(Category category) {
        data.add(category);
    }

    @Override
    public String[] getCategories() {
        String[] result = new String[data.size()];
        for (int index = 0; index < data.size(); index++) {
            result[index] = String.valueOf(index + 1) + " - " + data.get(index).getName();
        }
        return result;
    }

    @Override
    public Category get(int index) {
        return data.get(index);
    }

    @Override
    public int size() {
        return data.size();
    }
}
