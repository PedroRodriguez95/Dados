package Model;

import Interfaces.IAction;
import Interfaces.IOption;

public class Option implements IOption {

    private String name;
    private IAction action;

    public Option(String name, IAction action) {
        this.name = name;
        this.action = action;
    }


    @Override
    public void execute() {
        this.action.run();
        
    }

    @Override
    public String getOptionName() {
        return this.name;
    }
    
}
