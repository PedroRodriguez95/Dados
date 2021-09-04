package View;

import Interfaces.IPrinter;

public class PrinterConsole implements IPrinter {

    @Override
    public void print(String message) {
        System.out.println(message);  
    }
    
}
