package Controller;

import java.util.ArrayList;

import Model.GameTenThousand;
import View.PrinterConsole;
import View.TenThousandControllerDrawer;

public class TenThousandThrowerController extends ThrowerController {

    private GameTenThousand game;
    private TenThousandControllerDrawer drawer = new TenThousandControllerDrawer();
    private ValueReaderConsole console = new ValueReaderConsole(new PrinterConsole());
    private int scoreInHand;
    private boolean keepPlaying;


    public TenThousandThrowerController(GameTenThousand game){
        this.game = game;
    }

    @Override
    public void play(){
        this.scoreInHand = 0;
        this.keepPlaying = true;
        this.thrower.throwAll(this.game.getThrowables());
        this.drawer.printThrowables(this.game.getThrowables());
        this.drawer.printInitialOptions();
        int option = this.console.readValue(1,2);
        switch(option){
            case 1:
            anotarPuntos();
            break;

            case 2:
            break;
        }

    }

    public void anotarPuntos(){
        ArrayList<Integer> setAsideDices = new ArrayList<Integer>();
        this.drawer.printAsideOptions();
        int option = this.console.readValue(1,2);
        switch(option){
            case 1:
            while (true){
                this.drawer.printSetAsideOptions(this.game.getThrowables(),setAsideDices);
                int selectedDice = this.console.readValue(1, this.game.getThrowables().size() + 1);
                if(selectedDice > this.game.getThrowables().size()){
                    break;
                }
                if(!setAsideDices.contains(selectedDice)){
                    setAsideDices.add(selectedDice);
                }else{
                    this.drawer.printMessage("Ese dado ya fue seleccionado");
                }
            }
            if(!setAsideDices.isEmpty()){
                int[] setAsideParameters = new int[setAsideDices.size()];
                for (int i=0; i < setAsideParameters.length; i++)
                {
                    setAsideParameters[i] = setAsideDices.get(i).intValue();
                }
                this.game.setAside(setAsideParameters);
                break;
            }
            case 2:
            break;
        }

    }

}
