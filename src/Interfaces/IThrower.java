package Interfaces;
import java.util.ArrayList;

public interface IThrower {
	
	void throwAll(ArrayList<IThrowable> throwables);
	void subscribe(IThrowerListener listener);
}
