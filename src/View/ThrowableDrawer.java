package View;

import java.util.ArrayList;

import Interfaces.IPrinter;
import Interfaces.IThrowable;
import Interfaces.IThrowableDrawer;

public class ThrowableDrawer implements IThrowableDrawer {

    private IPrinter printer;

    public ThrowableDrawer(IPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void drawThrowable(IThrowable throwable) {
        this.printer.print(throwable.getValue());   
    }

    @Override
    public void drawThrowables(ArrayList<IThrowable> throwables) {
        for (IThrowable t : throwables) {
            this.drawThrowable(t);
        } 
    }
  
}
