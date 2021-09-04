package Model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import Controller.TenThousandThrowerController;
import Interfaces.IGame;
import Interfaces.IThrowable;
import Interfaces.IThrower;
import Interfaces.IThrowerController;
import Interfaces.IThrowerListener;

public class GameTenThousand implements IGame, IThrowerListener {
	
	private ArrayList<IThrowable> throwables = new ArrayList<IThrowable>();
	private ArrayList<IThrowable> setAside = new ArrayList<IThrowable>();
	private ArrayList<Player> throwers = new ArrayList<Player>();
	private IThrowerController controller = new TenThousandThrowerController(this);
	private HashMap<IThrower,Integer> scores = new HashMap<IThrower,Integer>();
	
	public GameTenThousand(int throwableAmount, int throwableFaces,int players){
		this.throwables = new DiceFactory(throwableFaces, new Randomizer()).generateThrowables(throwableAmount);
		this.throwers = new PlayerFactory().generatePlayers(players, new ThrowerFactory(), this);
	}

	@Override
	public void setUp() {

		for (IThrower t : this.throwers){
			this.scores.put(t, 0);
		}
		this.start();
	}

	@Override
	public void update() {
	}

	@Override
	public void onThrow(IThrower thrower, IThrowable throwable) {
	}

	@Override
	public void onThrowStart(IThrower thrower) {
	}

	@Override
	public void onThrowStop(IThrower thrower) {

	}

	public boolean setAside(int...args){
		if (this.checkSetAsideValidity(args)){
			for (int i : args){
				this.setAside.add(this.throwables.get(i-1));
			}
			this.throwables.removeAll(this.setAside);
			return true;
		}
		return false;
	}

	//TODO:Hacer que itere entre los jugadores hasta que el juego termine
	public void start(){
		for(int i = 0; i < this.throwers.size(); i++ ){
			this.resetThrowables();
			this.controller.control(this.throwers.get(i));
			this.controller.play();
		}
	}

	//TODO: cambiar por un for convencional

	public void resetThrowables(){
		for(IThrowable t : this.setAside){
			this.throwables.add(t);
			this.setAside.remove(t);
		}
	}

	public ArrayList<IThrowable> getThrowables(){
		return this.throwables;
	}
	//TODO: crear una iterfaz y separar funcionalidad en distintos validadores
	public boolean checkSetAsideValidity(int...args){
		
		boolean validity = true;

		//Obtengo valores de dados lanzados
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (int i : args){
			values.add(this.throwables.get(i-1).getValue());
		}

		//Creo un hashmap y lo lleno con los valores que se repitan 3 o mas veces
		HashSet<Integer> repetitons = new HashSet<Integer>();		
		for(int i = 0; i < values.size(); i++) {
			if(Collections.frequency(values, values.get(i)) >= 3) {
				repetitons.add(values.get(i));
			}
		}
		//Si cualquiera de los dados no es un 1 o 5 o se repite 3 o mas veces la validacion es falsa
		for (int i : values) {
			if( (i != 1 ) && (i != 5) && !repetitons.contains(i)) {
				validity = false;
				break;
			}
		}
		return validity;
	}
	
}
