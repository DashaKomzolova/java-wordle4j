package ru.yandex.practicum;

public class LongException extends Exception {
    @Override
    public String getMessage() {
        return "Слово должно состоять из 5 букв!";
    }
}
