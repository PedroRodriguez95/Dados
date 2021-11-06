package Model;

import Interfaces.IAction;
import Interfaces.IActionIntParameter;

public class ActionWrapperIntParameter implements IAction {
    
    private IActionIntParameter action;
    private int parameter; 
    
    public ActionWrapperIntParameter(IActionIntParameter action, int parameter) {
        this.action = action;
        this.parameter = parameter;

    }

    @Override
    public void run() {
        this.action.run(this.parameter);
        
    }
}
