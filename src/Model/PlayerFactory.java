package Model;

import java.util.ArrayList;

import Interfaces.IPlayerFactory;
import Interfaces.IThrower;
import Interfaces.IThrowerFactory;
import Interfaces.IThrowerListener;

public class PlayerFactory implements IPlayerFactory {

    @Override
    public Player generatePlayer(String name, IThrower wrappedThrower) {
        return new Player(name, wrappedThrower);
    }

    public ArrayList<Player> generatePlayers(int ammount, IThrowerFactory factory, IThrowerListener listener){

        ArrayList<Player> players = new ArrayList<Player>();
        for(int i = 0; i < ammount; i++){
            players.add(this.generatePlayer("Jugador " + (i+1),factory.generateThrower() ));
            players.get(players.size()-1).subscribe(listener);
        }

        return players;
    }
    
}
