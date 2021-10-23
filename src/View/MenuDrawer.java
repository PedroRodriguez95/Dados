package View;

import Interfaces.IMenu;
import Interfaces.IMenuDrawer;
import Interfaces.IOption;

public class MenuDrawer implements IMenuDrawer {

    @Override
    public void drawMenu(IMenu menu) {
        for ( IOption o : menu.getOptions()) {
            System.out.println(o.getOptionName());
        }
        
    }
    
}
