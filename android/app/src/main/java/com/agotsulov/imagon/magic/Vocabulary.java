package com.agotsulov.imagon.magic;

import android.util.Log;

import com.agotsulov.imagon.ImagonException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Vocabulary {

    private List<String> words;
    private List<String> taskWords;

    public Vocabulary(InputStreamReader allWords, InputStreamReader taskWords) throws ImagonException {
        this.words = loadWordsFromFile(allWords);
        this.taskWords = loadWordsFromFile(taskWords);
    }

    private List<String> loadWordsFromFile(final InputStreamReader inputStreamReader) throws ImagonException {
        List<String> words = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(inputStreamReader);
            String line;

            while ((line = br.readLine()) != null) {
                words.add(line);
            }
            br.close();
        }
        catch (IOException e) {
            throw new ImagonException();
        }
        return words;
    }


    public String idxToWords(final long[] ids) {
        StringBuilder result = new StringBuilder();
        float maxIdx = this.words.size();
        String currentWord = null;
        for (int i = 0; i < ids.length; i++) {
            int idx = (int) ids[i];
            if (idx < maxIdx) {
                currentWord = this.words.get(idx);
            } else {
                currentWord = "<unk>";
            }
            result.append(currentWord);
            result.append(" ");
            Log.i("RESULT", "i = " + i + " idx = " + idx + " word = " + currentWord);
            if ("<end>".equals(currentWord)) {
                break;
            }
        }
        return result.toString();
    }
}
