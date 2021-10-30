package View;

import Interfaces.IPrinter;

public class PrinterConsole implements IPrinter {

    @Override
    public void print(String message) {
        System.out.println(message);  
    }

    @Override
    public void print(int value) {
        System.out.println(value); 
    }
    
}
