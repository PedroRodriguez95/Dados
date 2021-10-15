package Model;

import java.util.Comparator;

import Interfaces.IThrowable;

public class ThrowableComparator implements Comparator<IThrowable> {

    @Override
    public int compare(IThrowable o1, IThrowable o2) {
        return o1.getValue() - o2.getValue();
    }
    
}
