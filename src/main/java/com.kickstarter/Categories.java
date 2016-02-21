package com.kickstarter;

/**
 * Created by DTarasenko on 2/21/2016.
 */
public interface Categories {

    void add(Category category);

    String[] getCategories();

    Category get(int index);

    int size();
}
