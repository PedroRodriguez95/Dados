package Controller;

import Interfaces.IThrower;
import Interfaces.IThrowerController;

public class ThrowerController implements IThrowerController{

    IThrower thrower;

    @Override
    public void control(IThrower thrower) {
        this.thrower = thrower;
    }

    @Override
    public void play() {
        
    }
    
}
