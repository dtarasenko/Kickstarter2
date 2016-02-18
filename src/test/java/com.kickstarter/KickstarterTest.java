package com.kickstarter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class KickstarterTest {

    private QuoteGenerator generator;
    private IO io;
    private Categories categories;
    private Projects projects;
    private Kickstarter kickstarter;

    @Before
    public void setup() {
        generator = mock(QuoteGenerator.class);
        when(generator.nextQuote()).thenReturn("quote");

        io = mock(IO.class);

        categories = new Categories();
        projects = new Projects();

        kickstarter = new Kickstarter(categories, projects, io, generator);
    }

    @Test
    public void shouldExitFromProgramm_whenExitFromCategoriesMenu() {
        // given

        // when
        when(io.read()).thenReturn("0");

        kickstarter.run();
    }

    @Test
    public void shouldNoCategoriesInProject_whenSelectCategory() throws Exception {
        // given
        categories.add(new Category("category1"));
        categories.add(new Category("category2"));

        // when
        when(io.read()).thenReturn("1", "0", "0");
        kickstarter.run();

        // then
        List<String> values = assertPrinted(io, 9);

        assertEquals("[quote\n" +
                ", Выбери категорию (или 0 для выхода):\n" +
                ", [1 - category1, 2 - category2]\n" +
                ", Вы выбрали категорию: category1\n" +
                ", -----------------------------------------\n" +
                ", Проектов в категории нет. Нажми 0 для выхода.\n" +
                ", Выбери категорию (или 0 для выхода):\n" +
                ", [1 - category1, 2 - category2]\n" +
                ", Спасибо за использование нашей программы!\n" +
                "]", values.toString());
    }

    @Test
    public void showMenuWithProjects() throws Exception {
        // given
        Category category = new Category("category1");
        categories.add(category);

        Project project1 = new Project("project1", 100, 1000, "video1", "dexcription1");
        projects.add(project1);
        project1.setCategory(category);

        Project project2 = new Project("project2", 200, 2000, "video2", "dexcription2");
        projects.add(project2);
        project2.setHistory("hestory2");
        project2.setQuestionAnswers("QA");
        project2.setCategory(category);

        // when
        // выбрали категорию
        // выбрали проект
        // вышли из проекта
        // вышли из списка проектов
        // вышли из списка категорий (программы)
        when(io.read()).thenReturn("1", "2", "0", "0", "0", "0");
        kickstarter.run();

        // then
        List<String> values = assertPrinted(io, 34);

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
                ", Выберите что хотите сделать с проектом:\n" +
                "[0 - выйти к списку проектов, 1 - инвестировать в проект]\n" +
                ", Выбери проект [1...2] или 0 для выхода\n" +
                ", Выбери категорию (или 0 для выхода):\n" +
                ", [1 - category1]\n" +
                ", Спасибо за использование нашей программы!\n" +
                "]", values.toString());
    }

    @Test
    public void shouldSelectCategoryWithoutProjects() {
        // given
        categories.add(new Category("category1"));
        categories.add(new Category("category2"));

        // when
        // выбрали категорию
        // вышли из проектов
        // вышли из списка категорий (программы)
        when(io.read()).thenReturn("1", "0", "0");
        kickstarter.run();

        // then
        List<String> values = assertPrinted(io, 9);

        assertPrinted(values, "quote\n");
        assertPrinted(values,"Выбери категорию (или 0 для выхода):\n");
        assertPrinted(values,"[1 - category1, 2 - category2]\n");
        assertPrinted(values,"Вы выбрали категорию: category1\n");
        assertPrinted(values,"Проектов в категории нет. Нажми 0 для выхода.\n");
        assertPrinted(values,"Спасибо за использование нашей программы!\n");
    }

    @Test
    public void shouldPrintProjectMenu_whenSelectIt() {
        // given
        Category category = new Category("category1");
        categories.add(category);

        Project project = new Project("project1", 100, 1000, "video1", "description1");
        projects.add(project);
        project.setCategory(category);

        // when
        // выбрали категорию
        // выбрали проект
        // выбрали оплату
        // вышли из проекта
        // вышли из проектов
        // вышли из списка категорий (программы)
        when(io.read()).thenReturn("1", "1", "1", "0", "0", "0");
        kickstarter.run();

        // then
        List<String> values = assertPrinted(io, 34);

        assertPrinted(values, "Выберите что хотите сделать с проектом:" +
                "\n[0 - выйти к списку проектов, 1 - инвестировать в проект]\n");
        assertPrinted(values, "Спасибо, что хотите помочь проекту!\n");

    }

    @Test
    public void shouldIncomeAmountToProject_whenDonate() {
        // given
        int TOTAL = 100;
        int DONATE = 25;
        int STILL_NEEDED = 75;

        Category category = new Category("category1");
        categories.add(category);

        Project project = new Project("project1", TOTAL, 1000, "video1", "description1");
        projects.add(project);
        project.setCategory(category);

        // when
        // 1 выбрали категорию
        // 1 выбрали проект
        // 1 выбрали оплату
        // ввели имя
        // ввели номер карточки
        // ввели сумму донейшена
        // 000 вышли из всех меню

        when(io.read()).thenReturn("1", "1", "1", "Дима", "78914245325", "" + DONATE, "0", "0", "0");
        kickstarter.run();

        // then
        List<String> values = assertPrinted(io, 34);

        assertPrinted(values, "Выберите что хотите сделать с проектом:" +
                "\n[0 - выйти к списку проектов, 1 - инвестировать в проект]\n");
        assertPrinted(values, "Спасибо, что хотите помочь проекту!\n");
        assertPrinted(values, "Введите имя:\n");
        assertPrinted(values, "Введите номер вашей карточки:\n");
        assertPrinted(values, "Введите размер суммы:\n");
        assertPrinted(values, "Спасибо Дима, Ваши деньги в размере 25 успешно зачислены на счет проекта\n");

        assertEquals(STILL_NEEDED, project.getAmount());
    }

    private void assertPrinted(List<String> values, String expected) {
        assertTrue("Actual data: \n" + values.toString() + "\n\ndoesn't contain: \n" + expected, values.contains(expected));
    }

    private List<String> assertPrinted(IO io, int times) {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(io, Mockito.times(times)).print(captor.capture());
        return captor.getAllValues();
    }

    @Test
    public void shouldMyQuestionOnProject_whenAddItOnProjectMenu() {
        // given
        Category category = new Category("category1");
        categories.add(category);

        Project project = new Project("project1", 100, 1000, "video1", "description1");
        projects.add(project);
        project.setCategory(category);

        // when
        // 1 выбрали категорию
        // 1 выбрали проект
        // 2 выбрали задать вопрос
        // ввели вопрос
        // 000 вышли из всех меню
        when(io.read()).thenReturn("1", "1", "2", "А когда собираетесь выпускать фильм?", "0", "0", "0");

        kickstarter.run();

        // then
        List<String> values = assertPrinted(io, 30);

        assertPrinted(values, "Выберите что хотите сделать с проектом:" +
                "\n[0 - выйти к списку проектов, 1 - инвестировать в проект, 2 - задать вопрос Авторам]\n");
        assertPrinted(values, "Введите ваш вопрос:\n");
        assertPrinted(values, "Спасибо за ваш вопрос, вскоре Автора с вами свяжутся\n");

        assertEquals("А когда собираетесь выпускать фильм?", project.getQuestionAnswers());
    }




    // fake example
    private class FakeIO implements IO {

        private List<String> messages = new LinkedList<>();
        private List<String> input = new LinkedList<>();

        public FakeIO(String... input) {
            this.input = new LinkedList<>(Arrays.asList(input));
        }

        public String read() {
            return input.remove(0);
        }

        public void print(String message) {
            messages.add(message);
        }

        public List<String> getMessages() {
            return messages;
        }
    }

    // stub example
    private class StubQuoteGenerator extends QuoteGenerator {

        public StubQuoteGenerator() {
            super(new Random());
        }

        @Override
        public String nextQuote() {
            return "quote";
        }

    }
}