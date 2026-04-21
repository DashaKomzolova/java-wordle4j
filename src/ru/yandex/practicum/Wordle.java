package ru.yandex.practicum;

import java.util.Scanner;

/*
в главном классе нам нужно:
    создать лог-файл (он должен передаваться во все классы)
    создать загрузчик словарей WordleDictionaryLoader
    загрузить словарь WordleDictionary с помощью класса WordleDictionaryLoader
    затем создать игру WordleGame и передать ей словарь
    вызвать игровой метод в котором в цикле опрашивать пользователя и передавать информацию в игру
    вывести состояние игры и конечный результат
 */
public class Wordle {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            WordleDictionaryLoader loader = new WordleDictionaryLoader("log.txt",
                    "words_ru.txt");
            WordleDictionary dictionary = loader.loadFileWithWords();

            if (dictionary.isEmpty()) {
                throw new DictionaryIsEmpty();
            }

            WordleGame game = new WordleGame(dictionary);
            startOfGame();

            while (!game.isGameOver()) {
                String attempt = scanner.nextLine();

                try {
                    String result = game.processOneAttempt(attempt);

                    if (result.equals("WIN")) {
                        System.out.println("ВЫ УГАДАЛИ СЛОВО! ПОЗДРАВЛЯЮ!");
                        System.out.println("Загаданное слово - " + game.getAnswer());
                        return;
                    }

                    System.out.println(result);

                } catch (LongException | NotRussianWord | NotInDictionary e) {
                    System.out.println(e.getMessage());
                }
            }

            System.out.println("К сожалению, ваши попытки закончились (((");
            System.out.println("Загаданное слово - " + game.getAnswer());

        } catch (DictionaryIsEmpty e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            LogTxt.saveStackTraceToFile(e, "log.txt");
        }
    }

    public static void startOfGame() {
        System.out.println("Добро пожаловать в игру Wordle!");
        System.out.println("===============================");
        System.out.println("Я загадал для вас слово из 5 букв, которое вы должны отгадать!");
        System.out.println("===============================================================");
        System.out.println("У вас есть 6 попыток, чтобы угадать, какое слово я загадал.");
        System.out.println("Ну и давайте приступим. Ваша 1-ая попытка! Вводите своё слово!");
    }
}