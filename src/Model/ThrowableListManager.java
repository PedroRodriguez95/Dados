package Model;

import java.util.ArrayList;
import java.util.HashMap;

import Interfaces.IThrowable;
import Interfaces.IThrowableListManager;

public class ThrowableListManager implements IThrowableListManager {

    private HashMap<Integer, ArrayList<IThrowable>> lists;

    @Override
    public ArrayList<IThrowable> getList(int listID) {
        return lists.get(listID);
    }

    @Override
    public void addToList(int listID, IThrowable throwable) {
        lists.get(listID).add(throwable);
        
    }

    @Override
    public void removeFromList(int listID, IThrowable throwable) {
        lists.get(listID).remove(throwable);
    }

    @Override
    public void move(int fromListID, int toListID, IThrowable throwable) {
        this.removeFromList(fromListID, throwable);
        this.addToList(toListID, throwable);
    }

    @Override
    public void clearList(int listID) {
        this.lists.get(listID).clear();
    }

    @Override
    public void createList(int listID) {
        this.lists.put(listID, new ArrayList<IThrowable>());
    }
    
}
