package View;

import Interfaces.IMenu;
import Interfaces.IMenuDrawer;
import Interfaces.IOption;
import Interfaces.IPrinter;

public class MenuDrawer implements IMenuDrawer {

    private IPrinter printer;

    public MenuDrawer(IPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void drawMenu(IMenu menu) {
        int iterator = 1;
        for ( IOption o : menu.getOptions()) {
            this.printer.print(iterator + ") " + o.getOptionName());
            iterator ++;
        }
        this.printer.print("Ingrese opcion: ");
    }
    
}
