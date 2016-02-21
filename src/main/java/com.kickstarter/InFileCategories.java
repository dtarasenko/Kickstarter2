package com.kickstarter;

/**
 * Created by DTarasenko on 2/21/2016.
 */
public class InFileCategories implements Categories {

    @Override
    public void add(Category category) {

    }

    @Override
    public String[] getCategories() {
        return new String[0];
    }

    @Override
    public Category get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
