package View;

import java.util.ArrayList;

import Interfaces.IThrowable;
import Model.Player;

public class TenThousandControllerDrawer {


    public void printThrower(Player thrower){
        this.clearScreen();
        System.out.println("*************************");
        System.out.println(thrower.getName());
        System.out.println("*************************");
    }

    public void printThrowables(ArrayList<IThrowable> throwables){
        System.out.println("***********TIRA*********");
        for (int i = 0; i < throwables.size();i++){
            System.out.println((i+1)+") " + throwables.get(i).getValue() );
        }
        System.out.println("*************************");
    }
    public void printInitialOptions(){
        System.out.println("1)Anotar Puntos");
        System.out.println("2)Pasar turno");
    }

    public void printAsideOptions(){
        this.clearScreen();
        System.out.println("1)Seleccionar dados");
        System.out.println("2)cancelar");
    }

    public void printSetAsideOptions(ArrayList<IThrowable> throwables, ArrayList<Integer> selectedDices){
        this.clearScreen();
        System.out.println("Dados seleccionados: ");
        System.out.println(" "); 
        for (int i : selectedDices){
            System.out.println(i);
        }
        System.out.println("*************************");
        for (int i = 0; i < throwables.size();i++){
            System.out.println((i+1)+") " + throwables.get(i).getValue() );
        }
        System.out.println("*************************");
        System.out.println((throwables.size() + 1) +") Terminado" );
        System.out.println("Ingrese dado que desea anotar: ");

    }

    public void printMessage(String message){
        System.out.println(message);
    }

    public void clearScreen(){
        for(int i = 0; i<30;i++){
            System.out.println(" "); 
        }
    }
    
}
