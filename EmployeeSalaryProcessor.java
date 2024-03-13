/**
 *  Program Description: To construct a Java program based on the given class diagram related to the
    employee salary data
 *  Name: Adam Isyraf
 *  Student ID: AM2307013941
 *  Section: 1
*/

//List out KJava import statements
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.PrintWriter;

public class EmployeeSalaryProcessor {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        //List out name of files
        String inputFile = "C:/Users/user/Downloads/LabTest/employeeSalaries.txt";
        String outputFile = "C:/Users/user/Downloads/LabTest/employeeData.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, "|");
                if (st.countTokens() != 3) {
                    System.out.println("Employee Data: " + line);
                    continue;
                }
                String name = st.nextToken().trim();
                double salary = Double.parseDouble(st.nextToken().trim());
                int yearsOfService = Integer.parseInt(st.nextToken().trim());
                employees.add(new Employee(name, salary, yearsOfService));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
        
        Employee topEmployee = null;
        double topSalary = -1;
        Employee leastEmployee = null;
        int leastYears = Integer.MAX_VALUE;

        for (Employee employee : employees) {
            double annualSalary = employee.salary + (employee.salary * 0.05 * employee.yearsOfService);
            if (annualSalary > topSalary) {
                topSalary = annualSalary;
                topEmployee = employee;
            }
            if (employee.yearsOfService < leastYears) {
                leastYears = employee.yearsOfService;
                leastEmployee = employee;
            }
        }
        //For annual salary + top performing employee
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputFile))) {
            for (Employee employee : employees) {
                double annualSalary = employee.salary + (employee.salary * 0.05 * employee.yearsOfService);
                pw.println(employee.name + "," + annualSalary + "," + employee.yearsOfService);
                System.out.println(employee.name + ": Annual Salary = " + annualSalary);
            }
            System.out.println("Top Employee: " + topEmployee.name + ", Annual Salary: " + topSalary);
            System.out.println("Employee with least years of service: " + leastEmployee.name + ", Years of Service: " + leastYears);
        } catch (IOException e) {
            System.out.println("Problem: " + e.getMessage());
        }
    }
}

class Employee {
    String name;
    double salary;
    int yearsOfService;

    public Employee(String name, double salary, int yearsOfService) {
        this.name = name;
        this.salary = salary;
        this.yearsOfService = yearsOfService;
}
}
