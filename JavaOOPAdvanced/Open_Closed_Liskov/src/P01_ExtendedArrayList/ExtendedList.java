package P01_ExtendedArrayList;

import java.util.ArrayList;

public class ExtendedList<T extends Comparable<T>> extends ArrayList<T> {
    public T min(){
        return super.stream().min(Comparable::compareTo).orElse(null);
    }

    public T max(){
        return super.stream().max(Comparable::compareTo).orElse(null);
    }
}
