package com.kickstarter;

import java.util.Random;

public class QuoteGenerator {

    private Random random;

    public QuoteGenerator(Random random) {
        this.random = random;
    }

    public String nextQuote() {
        String[] strings = new String[] {
                "Каждый человек - творческая личность (с) Саня",
                "У тебя получится - стоит только начать (с) Саня",
                "Иногда чтобы закончить требуется помощь (с) Саня",
                "У тебя получится! (с) Саня",
                "Все будет хорошо! (с) Саня"
        };
        int index = random.nextInt(strings.length);
        return strings[index];
    }
}
