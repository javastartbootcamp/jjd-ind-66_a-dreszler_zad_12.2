package model;

import static model.CompanyDepartaments.*;

public class Company {
    private Employee[] employees;
    private double averageSalary;
    private double lowestSalary;
    private double highestSalary;
    private int itEmployees;
    private int supportEmployees;
    private int managementEmployees;

    public Company(Employee[] employees) {
        checkIfEmployeesIsNull(employees);
        this.employees = employees;
        this.averageSalary = calculateAverageSalary();
        this.lowestSalary = calculateLowestSalary();
        this.highestSalary = calculateHighestSalary();
        calculateEmployeesOfEachDepartament();
    }

    public String getCompanyStats() {
        return String.format("Średnia wypłata: %.2f\nMinimalna wypłata: %.2f\nMaksymalna wypłata: %.2f\n" +
                        "Liczba pracowników IT: %d\nLiczba pracowników Support: %d\nLiczba pracowników Management: %d",
                averageSalary, lowestSalary, highestSalary, itEmployees, supportEmployees, managementEmployees);
    }

    private double calculateAverageSalary() {
        double averageSalary = 0;
        for (Employee employee : employees) {
            averageSalary += employee.getSalary() / employees.length;
        }

        return averageSalary;
    }

    private double calculateLowestSalary() {
        double lowestSalary = Double.MAX_VALUE;
        for (Employee employee : employees) {
            double salary = employee.getSalary();
            if (salary < lowestSalary) {
                lowestSalary = salary;
            }
        }

        return lowestSalary;
    }

    private double calculateHighestSalary() {
        double highestSalary = Double.MIN_VALUE;
        for (Employee employee : employees) {
            double salary = employee.getSalary();
            if (salary > highestSalary) {
                highestSalary = salary;
            }
        }

        return highestSalary;
    }

    private void calculateEmployeesOfEachDepartament() {
        for (Employee employee : employees) {
            String departament = employee.getDepartament();
            switch (departament) {
                case IT -> itEmployees++;
                case MANAGEMENT -> managementEmployees++;
                case SUPPORT -> supportEmployees++;
                default -> {
                    //Brak możliwości innej, ponieważ przy wczytywaniu z pliku weryfikowany jest dział
                }
            }
        }
    }

    private void checkIfEmployeesIsNull(Employee[] employees) {
        if (employees == null) {
            throw new NullPointerException("Nazwa pliku nie może być nullem");
        }
    }
}