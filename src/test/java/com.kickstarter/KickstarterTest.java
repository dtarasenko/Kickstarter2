package com.kickstarter;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;


public class KickstarterTest {

    @Test
    public void testRun() throws Exception {
        Categories categories = new Categories();
        categories.add(new Category("category1"));
        categories.add(new Category("category2"));
        Projects projects = new Projects();
        FakeIO io = new FakeIO(1, 0, 0);
        Kickstarter kickstarter = new Kickstarter(categories, projects, io, new StubQuoteGenerator());

        kickstarter.run();

        assertEquals("[quote\n" +
                ", Выбери категорию (или 0 для выхода):\n" +
                ", [1 - category1, 2 - category2]\n" +
                ", Вы выбрали категорию: category1\n" +
                ", -----------------------------------------\n" +
                ", Проектов в категории нет. Нажми 0 для выхода.\n" +
                ", Выбери категорию (или 0 для выхода):\n" +
                ", [1 - category1, 2 - category2]\n" +
                ", Спасибо за использование нашей программы!\n" +
                "]", io.getMessages().toString());
    }

    @Test
    public void testRun2() throws Exception {
        Categories categories = new Categories();
        Category category = new Category("category1");
        categories.add(category);

        Projects projects = new Projects();
        Project project1 = new Project("project1", 100, 1000, "video1", "dexcription1");
        projects.add(project1);
        project1.setCategory(category);

        Project project2 = new Project("project2", 200, 2000, "video2", "dexcription2");
        projects.add(project2);
        project2.setHistory("hestory2");
        project2.setQuestionAnswers("QA");
        project2.setCategory(category);

        FakeIO io = new FakeIO(1, 2, 0, 0, 0);
        Kickstarter kickstarter = new Kickstarter(categories, projects, io, new StubQuoteGenerator());

        kickstarter.run();

        assertEquals("[quote\n" +
                ", Выбери категорию (или 0 для выхода):\n" +
                ", [1 - category1]\n" +
                ", Вы выбрали категорию: category1\n" +
                ", -----------------------------------------\n" +
                ", 1 - , project1\n" +
                ", dexcription1\n" +
                ", Надо собрать 100 грн за 1000 дней\n" +
                ", Уже собрали 0 грн\n" +
                ", -----------------------------------------\n" +
                ", 2 - , project2\n" +
                ", dexcription2\n" +
                ", Надо собрать 200 грн за 2000 дней\n" +
                ", Уже собрали 0 грн\n" +
                ", -----------------------------------------\n" +
                ", Выбери проект [1...2] или 0 для выхода\n" +
                ", Вы выбрали проект: project2\n" +
                ", -----------------------------------------\n" +
                ", project2\n" +
                ", dexcription2\n" +
                ", Надо собрать 200 грн за 2000 дней\n" +
                ", Уже собрали 0 грн\n" +
                ", -----------------------------------------\n" +
                ", hestory2\n" +
                ", video2\n" +
                ", QA\n" +
                ", -----------------------------------------\n" +
                ", Выбери проект [1...2] или 0 для выхода\n" +
                ", Выбери категорию (или 0 для выхода):\n" +
                ", [1 - category1]\n" +
                ", Спасибо за использование нашей программы!\n" +
                "]", io.getMessages().toString());
    }

    private class FakeIO implements IO {

        private List<String> messages = new LinkedList<>();
        private List<Integer> input = new LinkedList<>();

        public FakeIO(Integer... input) {
            this.input = new LinkedList<>(Arrays.asList(input));
        }

        public int read() {
            return input.remove(0);
        }

        public void print(String message) {
            messages.add(message);
        }

        public List<String> getMessages() {
            return messages;
        }
    }

    private class StubQuoteGenerator extends QuoteGenerator {

        public StubQuoteGenerator() {
            super(new Random());
        }

        @Override
        public String nextQuote() {
            return "quote";
        }

    }

    @Test
    public void mock() {
        Categories categories = new Categories();
        categories.add(new Category("category1"));
        categories.add(new Category("category2"));

        Projects projects = new Projects();

        IO io = Mockito.mock(IO.class);
        QuoteGenerator generator = Mockito.mock(QuoteGenerator.class);

        Kickstarter kickstarter = new Kickstarter(categories, projects, io, generator);

        Mockito.when(generator.nextQuote()).thenReturn("quote");
//        FakeIO io = new FakeIO(1, 0, 0);
        Mockito.when(io.read()).thenReturn(1, 0, 0);

        kickstarter.run();

        Mockito.verify(io).print("quote\n");
        Mockito.verify(io, Mockito.times(2)).print("Выбери категорию (или 0 для выхода):\n");
        Mockito.verify(io, Mockito.times(2)).print("[1 - category1, 2 - category2]\n");
        Mockito.verify(io).print("Вы выбрали категорию: category1\n");
        Mockito.verify(io).print("-----------------------------------------\n");
        Mockito.verify(io).print("Проектов в категории нет. Нажми 0 для выхода.\n");
        Mockito.verify(io, Mockito.times(2)).print("Выбери категорию (или 0 для выхода):\n");
        Mockito.verify(io, Mockito.times(2)).print("[1 - category1, 2 - category2]\n");
        Mockito.verify(io).print("Спасибо за использование нашей программы!\n");

    }
}