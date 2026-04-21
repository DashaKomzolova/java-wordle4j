package ru.yandex.practicum;

import java.util.*;

/*
в этом классе хранится словарь и состояние игры
    текущий шаг
    всё что пользователь вводил
    правильный ответ

в этом классе нужны методы, которые
    проанализируют совпадение слова с ответом
    предложат слово-подсказку с учётом всего, что вводил пользователь ранее

не забудьте про специальные типы исключений для игровых и неигровых ошибок
 */
public class WordleGame {
    private final String answer;
    private int steps;
    private final WordleDictionary dictionary;
    private final Map<String, String> hints;

    WordleGame(WordleDictionary dictionary) {
        this.dictionary = dictionary;
        this.steps = 0;
        this.answer = dictionary.wordGeneration();
        this.hints = new HashMap<>();
    }

    public String processOneAttempt(String attempt) throws LongException, NotRussianWord, NotInDictionary {
        attempt = attempt.toLowerCase().replace('ё', 'е');

        if (attempt.isEmpty() && !hints.isEmpty()) {
            return giveHint();
        } else if (attempt.isEmpty() && hints.isEmpty()) {
            return "Сложно вам дать подсказку, так как вы не ввели еще ни одного слова";
        }

        if (attempt.equals(answer)) {
            return "WIN";
        }

        if (attempt.length() != 5) {
            throw new LongException();
        }

        if (!dictionary.isRussianWord(attempt)) {
            throw new NotRussianWord();
        }

        if (!dictionary.contains(attempt)) {
            throw new NotInDictionary();
        }

        steps++;
        hints.put(attempt, dictionary.compareWords(answer, attempt).toString());

        return dictionary.compareWords(answer, attempt).toString();
    }

    public String giveHint() {
        int cnt = -1;
        String bestAttempt = "";
        String bestMask = "";

        for (String attempt : hints.keySet()) {
            String currentMask = hints.get(attempt);
            int currentCount = dictionary.countOfPlus(currentMask);

            if (currentCount > cnt) {
                cnt = currentCount;
                bestAttempt = attempt;
                bestMask = currentMask;
            }
        }

        for (String word : dictionary.getWords()) {
            if (hints.containsKey(word)) {
                continue;
            }

            String currentMask = dictionary.compareWords(word, bestAttempt).toString();
            if (currentMask.equals(bestMask)) {
                return word;
            }
        }

        return "Подходящих подсказок не найдено";
    }

    public boolean isGameOver() {
        return steps == 6;
    }

    public String getAnswer() {
        return answer;
    }
}