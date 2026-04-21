package ru.yandex.practicum;

public class NotInDictionary extends Exception {
    @Override
    public String getMessage() {
        return "Такого слова нет в словаре!";
    }
}
