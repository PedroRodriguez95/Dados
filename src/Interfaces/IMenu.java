package Interfaces;

import java.util.ArrayList;

public interface IMenu {

    IMenu addOption(IOption option);
    ArrayList<IOption> getOptions();
    boolean execute(int choice);
    
}
