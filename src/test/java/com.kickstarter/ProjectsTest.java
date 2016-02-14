package com.kickstarter;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;


public class ProjectsTest {

    @Test
    public void testGetProjects() throws Exception {
        Projects projects = new Projects();

        Project[] found = projects.getProjects(new Category("name"));

        assertEquals(0, found.length);

        Category category = new Category("category");

        Project project1 = new Project("name", 100, 10, "video", "description");
        Project project2 = new Project("name2", 200, 20, "video2", "description2");

        project1.setCategory(category);
        project2.setCategory(category);

        projects.add(project1);
        projects.add(project2);

        found = projects.getProjects(new Category("name"));
        assertEquals(0, found.length);

        found = projects.getProjects(category);
        assertEquals(2, found.length);
    }

    @Test
    public void testGet() throws Exception {
        Projects projects = new Projects();
        Project project1 = new Project("Фильм \"Как кодить на java\"", 100000, 15,
                "http://youtube.com/tg67f347fg",
                "Фильм о том, как самому научится кодить на Java");
        projects.add(project1);
        Project project2 = new Project("Фильм \"GoJava\"", 2345, 10,
                "http://youtube.com/fh4iy990",
                "Фильм о том, как ребята учатся с GoIt");
        projects.add(project2);

        assertEquals(project1, projects.get(0));
        assertEquals(project2, projects.get(1));
    }
}