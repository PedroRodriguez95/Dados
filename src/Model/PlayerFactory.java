package Model;
import java.util.ArrayList;

import Interfaces.IThrowerListener;
import Interfaces.IThrower;

public class PlayerFactory {
	
	private int amount;
	
	public PlayerFactory(int amount){
		this.amount = amount;
	}
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
