package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    private final Map<String, Integer> wordMap;
    private final List<Character> rusAlphabet;


    public Parser() {
        wordMap = new TreeMap<>();
        rusAlphabet = new ArrayList<>();


        for (char i = 'а'; i <= 'я'; i++) {
            rusAlphabet.add(i);
        }
    }

    private boolean isRussianLetter(char symbol) {
        return rusAlphabet.contains(Character.toLowerCase(symbol));
    }

    public Map<String, Integer> getWordMap() {
        return wordMap;
    }

    public void parse(String html) {
        String bodyText = html.substring(html.indexOf("<body"), html.lastIndexOf("</body"));
        for (int i = 0; i < bodyText.length(); i++) {
            char c = bodyText.charAt(i);
            if (isRussianLetter(c)) {
                int start = i;

                while (isRussianLetter(c)) {
                    i++;
                    c = bodyText.charAt(i);
                }

                final String word = clean(bodyText.substring(start, i).toUpperCase());

                if (wordMap.containsKey(word)) {
                    Integer countRepeatWord = wordMap.get(word);
                    wordMap.put(word, countRepeatWord + 1);
                } else {
                    wordMap.put(word, 1);
                }
            }

        }

    }

    private String clean(String word) {
        word = word.replaceAll("\\s+", "");
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (!isRussianLetter(c)) {
                return word.substring(0, i);
            }
        }
        return word;
    }


}
