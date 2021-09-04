package Controller;


import java.util.Scanner;

import Interfaces.IPrinter;
import Interfaces.IValueReader;

public class ValueReaderConsole implements IValueReader {

    private IPrinter printer;

    public ValueReaderConsole(IPrinter printer){
        this.printer = printer;
    }

    Scanner scanner = new Scanner(System.in);

    @Override
    public int readValue(int min, int max) {
        int option = scanner.nextInt();
        while (option < min || option > max) {
            this.printer.print("Opcion fuera de el limite");
            option = scanner.nextInt();
        }
        return option;
    }
    
    
}
