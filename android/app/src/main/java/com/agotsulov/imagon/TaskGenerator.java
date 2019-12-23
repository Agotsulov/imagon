package com.agotsulov.imagon;

import com.agotsulov.imagon.magic.Vocabulary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskGenerator {

    private final int MIN = 2;
    private final int MAX = 5;

    private Vocabulary vocabulary;

    private Random random;

    public TaskGenerator(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
        random = new Random();
    }

    public Task generate() {
        List<String> words = new ArrayList<>();

        int count = MIN + random.nextInt(MAX - MIN + 1);
        for (int i = 0;i < count; i++) {
            words.add(vocabulary.idxToWord(random.nextInt(vocabulary.size())));
        }

        return new Task(words);
    }

}
