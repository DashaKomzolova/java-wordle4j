package ru.yandex.practicum;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
этот класс содержит в себе список слов List<String>
    его методы похожи на методы списка, но учитывают особенности игры
    также этот класс может содержать рутинные функции по сравнению слов, букв и т.д.
 */
public class WordleDictionary {
    private final Random random = new Random();
    private final List<String> words;

    WordleDictionary() {
        this.words = new ArrayList<>();
    }

    public String wordGeneration() {
        int min = 0;
        int max = words.size() - 1;
        int num = random.nextInt(max - min + 1) + min;

        return words.get(num);
    }

    public StringBuilder compareWords(String hiddenWord, String attempt) {
        StringBuilder result = new StringBuilder("-----");

        boolean[] used = new boolean[hiddenWord.length()];

        for (int i = 0; i < hiddenWord.length(); i++) {
            if (hiddenWord.charAt(i) == attempt.charAt(i)) {
                result.setCharAt(i, '+');
                used[i] = true;
            }
        }

        for (int i = 0; i < hiddenWord.length(); i++) {
            if (result.charAt(i) == '+') {
                continue;
            }

            char ch = attempt.charAt(i);

            for (int j = 0; j < hiddenWord.length(); j++) {
                if (!used[j] && hiddenWord.charAt(j) == ch) {
                    result.setCharAt(i, '^');
                    used[j] = true;
                    break;
                }
            }
        }

        return result;
    }

    public boolean isRussianWord(String word) {
        for (char c : word.toCharArray()) {
            if (!(c >= 'а' && c <= 'я')) {
                return false;
            }
        }
        return true;
    }

    public boolean contains(String s) {
        return words.contains(s);
    }

    public boolean isEmpty() {
        return words.isEmpty();
    }

    public int countOfPlus(String s) {
        int cnt = 0;
        for (char e : s.toCharArray()) {
            if (e == '+') {
                cnt += 1;
            }
        }
        return cnt;
    }

    public List<String> getWords() {
        return words;
    }
}
