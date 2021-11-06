package Interfaces;

import java.util.ArrayList;

public interface IThrowableListManager {

    ArrayList<IThrowable> getList(int listID);
    void addToList(int listID, IThrowable throwable);
    void removeFromList(int listID, IThrowable throwable);
    void move(int fromListID, int toListID, IThrowable throwable);
    void clearList(int listID);
    void createList(int listID);
}
