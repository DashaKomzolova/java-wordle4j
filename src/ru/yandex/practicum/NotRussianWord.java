package ru.yandex.practicum;

public class NotRussianWord extends Exception {
    @Override
    public String getMessage() {
        return "Слово состоит не из русских букв!";
    }
}