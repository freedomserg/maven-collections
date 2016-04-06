package com.goit.projects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class OutputUtil {
    public static final int STANDARD_LINE_WIDTH = 16;
    public static final int NUMBER_OF_COLUMNS = 8;

    public static void printHeader(int inputCollectionDataVolume) throws IOException {
        String header = createHeader(inputCollectionDataVolume);
        printHeaderToConsole(header);
        writeHeaderToFile(header);
    }

    private static String createHeader(int inputCollectionDataVolume) {
        String firstLine = String.format("%s%,d%s", "Efficiency measurements for collections of ",
                inputCollectionDataVolume, " items, ms");
        String collection = String.format("|%-16.16s|", alignByCenter("Collection"));
        String add = String.format("|%-16.16s|", alignByCenter("add"));
        String contains = String.format("|%-16.16s|", alignByCenter("contains"));
        String get = String.format("|%-16.16s|", alignByCenter("get"));
        String iteratorAdd = String.format("|%-16.16s|", alignByCenter("iterator.add"));
        String iteratorRemove = String.format("|%-16.16s|", alignByCenter("iterator.remove"));
        String populate = String.format("|%-16.16s|", alignByCenter("populate"));
        String remove = String.format("|%-16.16s|", alignByCenter("remove"));

        return String.format("%n%s%n%s%n%s%s%s%s%s%s%s%s%n%s%n", firstLine, createLineSeparator(), collection, add,
                contains, get, iteratorAdd, iteratorRemove, populate, remove, createLineSeparator());
    }

    private static String alignByCenter(String content) {
        if (content == null || content.equals("")) {
            return "";
        }
        StringBuilder alignedString = new StringBuilder();
        int outputLength = content.length();
        int freeSpaceBefore = (STANDARD_LINE_WIDTH - outputLength) / 2;
        for (int i = 0; i < freeSpaceBefore; i++) {
            alignedString.append(" ");
        }
        alignedString.append(content);
        return alignedString.toString();
    }

    private static String createLineSeparator() {
        StringBuilder lineSeparator = new StringBuilder();
        int numberOfColumnDelimiters = NUMBER_OF_COLUMNS * 2;
        int lineSeparatorWidth = STANDARD_LINE_WIDTH * NUMBER_OF_COLUMNS + numberOfColumnDelimiters;
        for (int i = 0; i < lineSeparatorWidth; i++) {
            lineSeparator.append("-");
        }
        return lineSeparator.toString();
    }

    private static void printHeaderToConsole(String header) {
        System.out.print(header);
    }

    private static void writeHeaderToFile(String header) throws IOException {
        String userHome = System.getProperty("user.home");
        String fileSeparator = System.getProperty("file.separator");
        String fileName = userHome + fileSeparator + "results.txt";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(header);
        }
    }

    public static void printAndSaveResults(String collectionType, Map<String, String> measurementResults) throws IOException {
        String content = createContent(collectionType, measurementResults);
        printResultsToConsole(content);
        writeResultsToFile(content);
    }

    private static String createContent(String collectionType, Map<String, String> measurementResults) {
        StringBuilder content = new StringBuilder();
        String type = String.format("|%-16.16s|", alignByCenter(collectionType));
        content.append(type);
        for (Map.Entry<String, String> entry : measurementResults.entrySet()) {
            String value = entry.getValue();
            String field = String.format("|%-16.16s|", alignByCenter(value));
            content.append(field);
        }
        content.append("\n").append(createLineSeparator()).append("\n");
        return content.toString();
    }

    private static void printResultsToConsole(String content) {
        System.out.print(content);
    }

    private static void writeResultsToFile(String content) throws IOException {
        String userHome = System.getProperty("user.home");
        String fileSeparator = System.getProperty("file.separator");
        String fileName = userHome + fileSeparator + "results.txt";
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(content);
        }
    }
}
