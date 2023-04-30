package app;

import io.FileUtils;
import io.exception.FileReadFailureException;
import io.exception.FileSaveFailureException;
import model.Company;
import model.Employee;

public class Main {

    public static void main(String[] args) {
        try {
            String filePath = "employees.csv";
            Employee[] employees = FileUtils.loadEmployeesFromFile(filePath);
            Company company = new Company(employees);
            String stats = company.getCompanyStats();
            FileUtils.saveToFile(stats, "stats.txt");
        } catch (FileReadFailureException | NullPointerException | FileSaveFailureException e) {
            System.err.println(e.getMessage());
        }
    }
}