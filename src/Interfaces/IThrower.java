package Interfaces;
import java.util.ArrayList;

public interface IThrower {
	
	// TODO: obtener otra forma de conseguir el nombre de un thrower 
	String getName();
	void throwAll(ArrayList<IThrowable> throwable);
	void subscribe(IThrowerListener listener);
}
