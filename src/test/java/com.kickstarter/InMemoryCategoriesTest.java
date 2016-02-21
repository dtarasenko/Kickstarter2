package com.kickstarter;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class InMemoryCategoriesTest {

    private Categories list;

    @Before
    public void setup() {
        list = new InMemoryCategories();
    }

    @Test
    public void shouldCategoriesList_whenAddCategories() throws Exception {
        //given
        list.add(new Category("name1"));
        list.add(new Category("name2"));

        //when
        String[] list = this.list.getCategories();

        //then
        assertEquals("[1 - name1, 2 - name2]", Arrays.toString(list));
    }

    @Test
    public void shouldCategoriesList_whenNoCategories() throws Exception {
        //when
        String[] list = this.list.getCategories();

        //then
        assertEquals("[]", Arrays.toString(list));
    }

    @Test
    public void shouldGetCategoryByIndex() throws Exception {
        Category category1 = new Category("name1");
        list.add(category1);

        Category category2 = new Category("name2");
        list.add(category2);

        // when then
        assertEquals(category1, list.get(0));
        assertEquals(category2, list.get(1));
    }

    @Test
    public void shouldGetCategoriesListSize_whenAddCategories() throws Exception {
        assertEquals(0, list.size());

        // when
        list.add(new Category("name1"));
        list.add(new Category("name2"));

        //then
        assertEquals(2, list.size());
    }
}