package ru.yandex.practicum;

public class DictionaryIsEmpty extends Exception {
    @Override
    public String getMessage() {
        return "Словарь пустой!";
    }
}