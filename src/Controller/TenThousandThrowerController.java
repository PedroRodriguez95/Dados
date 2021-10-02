package Controller;

import Interfaces.IThrower;
import Interfaces.IThrowerController;
import Model.GameTenThousand;
import View.PrinterConsole;
import View.TenThousandControllerDrawer;

public class TenThousandThrowerController implements IThrowerController{

    private IThrower thrower;
    private GameTenThousand game;
    private TenThousandControllerDrawer drawer = new TenThousandControllerDrawer();
    private ValueReaderConsole console = new ValueReaderConsole(new PrinterConsole());


    public TenThousandThrowerController(GameTenThousand game){
        this.game = game;
    }

    @Override
    public void control(IThrower thrower) {
        this.thrower = thrower;
    }

    @Override
    public void play() {
        this.thrower.throwAll(game.getThrowables());
    }
    
}
