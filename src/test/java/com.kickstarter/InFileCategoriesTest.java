package com.kickstarter;

import org.junit.After;
import org.junit.Before;

import java.io.File;

/**
 * Created by DTarasenko on 2/21/2016.
 */
public class InFileCategoriesTest extends CategoriesTest {

    @Override
    Categories getCategories() {
        return new InFileCategories("test-categories.txt");
    }

    @After
    public void cleanUp() {
        new File("test-categories.txt").delete();
    }

}
