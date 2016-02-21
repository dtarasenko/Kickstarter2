package com.kickstarter;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class InMemoryCategoriesTest {

    @Test
    public void testGetCategories() throws Exception {
        Categories categories = new InMemoryCategories();
        String[] list = categories.getCategories();
        assertEquals("[]", Arrays.toString(list));

        categories.add(new Category("name1"));
        categories.add(new Category("name2"));
        list = categories.getCategories();
        assertEquals("[1 - name1, 2 - name2]", Arrays.toString(list));
    }

    @Test
    public void testGet() throws Exception {
        Categories list = new InMemoryCategories();
        Category category1 = new Category("name1");
        list.add(category1);
        Category category2 = new Category("name2");
        list.add(category2);
        assertEquals(category1, list.get(0));
        assertEquals(category2, list.get(1));
    }

    @Test
    public void testSize() throws Exception {
        Categories list = new InMemoryCategories();
        assertEquals(0, list.size());

        list.add(new Category("name1"));
        list.add(new Category("name2"));
        assertEquals(2, list.size());
    }
}