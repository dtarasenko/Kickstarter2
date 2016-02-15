package com.kickstarter;


import java.util.Random;

public class KickstarterRunner {

    public static void main(String[] args) {
        //TODO 4 - 1.26min
        Category category1 = new Category("Photo");
        Category category2 = new Category("Video");
        Category category3 = new Category("Music");

        Categories categories = new Categories();
        categories.add(category1);
        categories.add(category2);
        categories.add(category3);


        Project project1 = new Project("Фильм \"Как кодить на java\"", 100000, 15,
                "http://youtube.com/tg67f347fg",
                "Фильм о том, как самому научится кодить на Java");
        Project project2 = new Project("Фильм \"GoJava\"", 2345, 10,
                "http://youtube.com/fh4iy990",
                "Фильм о том, как ребята учатся с GoIt");

        project1.setCategory(category2);
        project2.setCategory(category2);

        Projects projects = new Projects();
        projects.add(project1);
        projects.add(project2);

        Kickstarter application = new Kickstarter(categories, projects, new ConsoleIO(),
                new QuoteGenerator(new Random()));
        project1.setHistory("История этого проекта корнями уходит в ....");
        project2.setHistory("Мы только начали и потому особо нечего рассказывать.\n" +
                "Продолжение следует!");

        project1.setQuestionAnswers("Q: Какая продолжытельность фильма?\n" +
                "A: два часа");

        application.run();
    }
}
