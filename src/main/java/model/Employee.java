package model;

import static model.CompanyDepartaments.*;

public class Employee {
    private String firstName;
    private String lastName;
    private String pesel;
    private String departament;
    private double salary;

    public Employee(String firstName, String lastName, String pesel, String departament, double salary) {
        checkIfSalaryIsPositive(salary);
        checkIfDepartamentIsValid(departament);
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.departament = departament;
        this.salary = salary;
    }

    String getDepartament() {
        return departament;
    }

    double getSalary() {
        return salary;
    }

    private void checkIfSalaryIsPositive(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Wypłata nie może być ujemna");
        }
    }

    private void checkIfDepartamentIsValid(String departament) {
        switch (departament) {
            case IT:
            case MANAGEMENT:
            case SUPPORT:
                break;
            default:
                throw new IllegalArgumentException("Nieznany dział. Dozwolone działy: IT, Support, Management");
        }
    }
}
