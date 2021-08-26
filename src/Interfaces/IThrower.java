package Interfaces;
import java.util.ArrayList;

public interface IThrower {
	
	String getName();
	void throwAll(ArrayList<IThrowable> throwable);
	void subscribe(IGame game);
}
