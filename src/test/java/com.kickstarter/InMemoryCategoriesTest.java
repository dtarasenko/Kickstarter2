package com.kickstarter;

/**
 * Created by DTarasenko on 2/21/2016.
 */
public class InMemoryCategoriesTest extends CategoriesTest {

    @Override
    Categories getCategories() {
        return new InMemoryCategories();
    }

}
