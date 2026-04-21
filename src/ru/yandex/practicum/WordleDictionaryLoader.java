package ru.yandex.practicum;

import java.io.*;
import java.nio.charset.StandardCharsets;

/*
этот класс содержит в себе всю рутину по работе с файлами словарей и с кодировками
    ему нужны методы по загрузке списка слов из файла по имени файла
    на выходе должен быть класс WordleDictionary
 */
public class WordleDictionaryLoader {
    private final String nameOfLogFile;
    private final String nameOfDictionary;

    WordleDictionaryLoader(String nameOfLogFile, String nameOfDictionary) {
        this.nameOfLogFile = nameOfLogFile;
        this.nameOfDictionary = nameOfDictionary;
    }

    public WordleDictionary loadFileWithWords() {
        WordleDictionary wordleDictionary = new WordleDictionary();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(nameOfDictionary),
                StandardCharsets.UTF_8))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.length() == 5) {
                    line = line.toLowerCase().replace("ё", "е");
                    wordleDictionary.getWords().add(line);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            LogTxt.saveStackTraceToFile(e, nameOfLogFile);
        }

        return wordleDictionary;
    }
}
