package ru.yandex.practicum;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LogTxt {
    public static void saveStackTraceToFile(Exception e, String nameOfLogFile) {
        try (PrintWriter writer = new PrintWriter(
                new BufferedWriter(new FileWriter(nameOfLogFile, true)))) {
            e.printStackTrace(writer);
            writer.println();
        } catch (IOException ex) {

        }
    }
}
