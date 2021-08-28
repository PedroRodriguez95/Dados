package Model;
import java.util.ArrayList;

import Interfaces.IThrowerListener;
import Interfaces.IThrower;

//TODO: crear interfaz que devuelva un solo jugador
public class PlayerFactory {

	//TODO: volar este valor
	private int amount;

	public PlayerFactory(int amount){
		this.amount = amount;
	}
//TODO: hacer de cuenta que esto no exite
	public ArrayList<IThrower> generatePlayers(IThrowerListener thrower){
		
		ArrayList<IThrower> players = new ArrayList<IThrower>();
		
		for (int i= 0; i < this.amount; i++) {
			Player newPlayer = new Player("Jugador " + (i+1));
			newPlayer.subscribe(thrower);
			players.add(newPlayer);
		}
		return players;
	}

}
