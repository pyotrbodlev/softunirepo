import P04_DetailPrinter.Clerk;
import P04_DetailPrinter.DetailsPrinter;
import P04_DetailPrinter.Employee;
import P04_DetailPrinter.Manager;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Employee manager = new Manager("Ivan", new ArrayList<>(){{ add("Doc 1"); add("Dogovor");}});
        Employee clerk = new Clerk("Mitio", 123);

        List<Employee> employees = new ArrayList<>(){{
            add(manager);
            add(clerk);
        }};

        DetailsPrinter dp = new DetailsPrinter(employees);

        dp.printDetails();
    }
}
