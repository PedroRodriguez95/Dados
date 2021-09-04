package Interfaces;

import Model.Player;

public interface IPlayerFactory {

    Player generatePlayer(String name, IThrower wrappedThrower);
    
}
