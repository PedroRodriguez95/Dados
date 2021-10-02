package Model;

import java.util.ArrayList;

import Interfaces.IMenu;
import Interfaces.IOption;

public class Menu implements IMenu {

    private ArrayList<IOption> options = new ArrayList<IOption>();

    @Override
    public IMenu addOption(IOption option) {
        this.options.add(option);
        return this;
    }

    @Override
    public ArrayList<IOption> getOptions() {
        return this.options;
    }

    @Override
    public boolean execute(int choice) {
        if (choice < this.options.size() && choice >= 0) {
            this.options.get(choice).execute();
            return true;
        }
        return false;
    }
    
}
