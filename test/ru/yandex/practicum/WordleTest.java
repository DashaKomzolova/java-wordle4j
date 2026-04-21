package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest {
    private WordleDictionaryLoader loader = new WordleDictionaryLoader("log.txt",
            "words_ru.txt");
    private WordleDictionary dictionary = loader.loadFileWithWords();
    private WordleGame wordleGame = new WordleGame(dictionary);

    @Test
    void lengthNotFive() {
        LongException e = assertThrows(LongException.class, () -> {
            wordleGame.processOneAttempt("пароход");
        });

        assertEquals("Слово должно состоять из 5 букв!", e.getMessage());
    }

    @Test
    void wordNotInDictionaryMessage() {
        NotInDictionary e = assertThrows(NotInDictionary.class, () -> {
            wordleGame.processOneAttempt("абвгд");
        });

        assertEquals("Такого слова нет в словаре!", e.getMessage());
    }

    @Test
    void notRussianWordMessage() {
        NotRussianWord e = assertThrows(NotRussianWord.class, () -> {
            wordleGame.processOneAttempt("abcde");
        });

        assertEquals("Слово состоит не из русских букв!", e.getMessage());
    }

}
