package com.kickstarter;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class QuoteGeneratorTest {

    class FakeRandom extends Random {

        private List<Integer> numbers;

        public FakeRandom(Integer... numbers) {
            this.numbers = new LinkedList(Arrays.asList(numbers));
        }

        @Override
        public int nextInt(int i) {
            return numbers.remove(0);
        }
    }

    @Test
    public void testNextQuote() throws Exception {
        QuoteGenerator generator = new QuoteGenerator(new FakeRandom(0, 1));

        String qutote1 = generator.nextQuote();

        assertEquals("Каждый человек - творческая личность (с) Саня", qutote1);

        String qutote2 = generator.nextQuote();

        assertEquals("У тебя получится - стоит только начать (с) Саня", qutote2);
    }
}