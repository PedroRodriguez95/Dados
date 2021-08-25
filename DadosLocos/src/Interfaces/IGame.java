package Interfaces;
import java.util.ArrayList;

public interface IGame {
	
	void setUp(int throwableAmount, int throwableFaces,int players);
	ArrayList<IThrower> getThrowers();
	ArrayList<IThrowable> getThrowables();
	void update(IThrower thrower);
	void start();
	
}
