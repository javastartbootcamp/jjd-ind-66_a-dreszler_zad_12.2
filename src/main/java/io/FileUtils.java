package io;

import io.exception.FileCreationFailureException;
import io.exception.FileReadFailureException;
import io.exception.FileSaveFailureException;
import model.Employee;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class FileUtils {
    public static Employee[] loadEmployeesFromFile(String filePath) {
        checkIfFilePathIsNull(filePath);
        Employee[] employees;

        try (
                Scanner input = new Scanner(new File(filePath))
        ) {
            int lines = countLines(filePath);
            int employeesRead = 0;
            employees = new Employee[lines];

            for (int i = 0; input.hasNextLine(); ) {
                String[] splitEmployee = input.nextLine().split(";");
                employees[i] = fillEmployee(splitEmployee);
                if (employees[i] != null) {
                    i++;
                    employeesRead++;
                }
            }
            if (employeesRead != lines) {
                employees = Arrays.copyOf(employees, employeesRead);
            }
        } catch (FileNotFoundException e) {
            throw new FileReadFailureException("Nie znaleziono pliku: " + filePath, e);
        }

        return employees;
    }

    private static int countLines(String filePath) {
        int lines = 0;
        try (
                Scanner input = new Scanner(new File(filePath))
        ) {
            while (input.hasNextLine()) {
                input.nextLine();
                lines++;
            }
        } catch (FileNotFoundException e) {
            throw new FileReadFailureException("Nie znaleziono pliku: " + filePath, e);
        }

        return lines;
    }

    private static Employee fillEmployee(String[] splitEmployee) {
        Employee employee = null;
        try {
            employee = new Employee(splitEmployee[0], splitEmployee[1], splitEmployee[2], splitEmployee[3],
                    Double.parseDouble(splitEmployee[4]));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Nie udało się wczytać pracownika: Dopuszczalny format linii: " +
                    "imię;nazwisko;pesel;dział;wypłata");
        } catch (NumberFormatException e) {
            System.err.println("Nie udało się wczytać operacji: jako wypłatę można wprowadzić tylko liczbę");
        } catch (IllegalArgumentException e) {
            System.err.println("Nie załadowano pracownika: " + e.getMessage());
        }

        return employee;
    }

    public static void saveToFile(String stats, String filePath) {
        checkIfStatsIsNull(stats);
        checkIfFilePathIsNull(filePath);
        try {
            createFile(filePath);
            var writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(stats);
            writer.close();
        } catch (IOException | FileCreationFailureException e) {
            throw new FileSaveFailureException("Zapis do pliku " + filePath + " nie powiódł się", e);
        }
    }

    private static void createFile(String fileName) {
        try {
            File file = new File(fileName);

            boolean fileExists = file.exists();
            if (!fileExists) {
                fileExists = file.createNewFile();
            }

            if (fileExists) {
                System.out.println("Plik " + fileName + " istnieje lub został utworzony");
            }
        } catch (IOException e) {
            throw new FileCreationFailureException("Nie udało się utworzyć pliku " + fileName, e);
        }
    }

    private static void checkIfFilePathIsNull(String filePath) {
        if (filePath == null) {
            throw new NullPointerException("Nazwa pliku nie może być nullem");
        }
    }

    private static void checkIfStatsIsNull(String stats) {
        if (stats == null) {
            throw new NullPointerException("Statystyki nie mogą być nullem");
        }
    }
}
