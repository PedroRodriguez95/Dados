package Model;

import java.util.ArrayList;

import Interfaces.IThrowable;

public class ThrowableListManagerTenThousand extends ThrowableListManager {

    public final static int ALL_THROWABLES = 1;
    public final static int UNTHTOWABLE_THROWABLES = 2;
    public final static int THROWABLES_TO_THROW = 3;
    public final static int SEPARATED_THROWABLES = 4;

    public ThrowableListManagerTenThousand() {
        this.createList(ALL_THROWABLES);
        this.createList(UNTHTOWABLE_THROWABLES);
        this.createList(THROWABLES_TO_THROW);
    }

    @Override 
    public ArrayList<IThrowable> getList(int listID) {
        if ( listID == SEPARATED_THROWABLES) {
            ArrayList<IThrowable> tempThrowables = new ArrayList<IThrowable>();
            for (IThrowable t : this.getList(ALL_THROWABLES)) {
                if (!this.getList(UNTHTOWABLE_THROWABLES).contains(t) && !this.getList(THROWABLES_TO_THROW).contains(t)) {
                    tempThrowables.add(t);
                }
            }
		    return tempThrowables;
        }
        return super.getList(listID);
    }
    
}
