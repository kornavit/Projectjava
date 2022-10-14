package ku.cs.services;

import ku.cs.models.modelRequest;

import java.util.Comparator;

public class CategoryComparator implements Comparator<modelRequest> {
    private boolean isDescending = false;
    // true = new -> old
    // false = old -> new

    public void setDescending(boolean descending){
        isDescending = descending;
    }
    @Override
    public int compare(modelRequest o1, modelRequest o2) {
        return Integer.compare(o1.getVotePoint(), o2.getVotePoint())  * (isDescending ? -1 : 1);
    }
}
