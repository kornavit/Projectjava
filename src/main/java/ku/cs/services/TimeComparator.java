package ku.cs.services;
import ku.cs.models.modelRequest;

import java.util.Comparator;

public class TimeComparator implements Comparator<modelRequest>{
    private boolean isDescending = false;
    // true = new -> old
    // false = old -> new

    public void setDescending(boolean descending){
        isDescending = descending;
    }
    @Override
    public int compare(modelRequest o1, modelRequest o2) {
        String[] data1 = o1.getTime().split(" ");//12-10-2022 11:11:38 -> 12-10-2022,11:11:38
        String[] day1 = data1[0].split("-"); //12-10-2022 -> 12,10,2022
        String[] time1 = data1[1].split(":"); //11:11:38 -> 11,11,38

        String[] data2 = o2.getTime().split(" ");
        String[] day2 = data2[0].split("-");
        String[] time2 = data2[1].split(":");


//        return Integer.compare(Integer.parseInt(day1[0]),Integer.parseInt(day2[0])) * (isDescending ? -1 : 1);
         if (isDescending){
            if (Integer.parseInt(day1[1]) < Integer.parseInt(day2[1])) {
                // return o1;
                return 1;
            } else if (Integer.parseInt(day1[1]) == Integer.parseInt(day2[1])) {
                //เทียบวันที่
                if (Integer.parseInt(day1[0]) < Integer.parseInt(day2[0])) {
                    return 1;
                } else if (Integer.parseInt(day1[0]) == Integer.parseInt(day2[0])) {
                    int totalTime1 = (Integer.parseInt(time1[0]) * 3600) + (Integer.parseInt(time1[1]) * 60) + (Integer.parseInt(time1[2]));
                    int totalTime2 = (Integer.parseInt(time2[0]) * 3600) + (Integer.parseInt(time2[1]) * 60) + (Integer.parseInt(time2[2]));
                    if (totalTime1 < totalTime2) {
                        return 1;
                    }else{
                        return -1;
                    }
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }else {
            if (Integer.parseInt(day1[1]) > Integer.parseInt(day2[1])) {
                // return o1;
                return 1;
            } else if (Integer.parseInt(day1[1]) == Integer.parseInt(day2[1])) {
                //เทียบวันที่
                if (Integer.parseInt(day1[0]) > Integer.parseInt(day2[0])) {
                    return 1;
                } else if (Integer.parseInt(day1[0]) == Integer.parseInt(day2[0])) {
                    int totalTime1 = (Integer.parseInt(time1[0]) * 3600) + (Integer.parseInt(time1[1]) * 60) + (Integer.parseInt(time1[2]));
                    int totalTime2 = (Integer.parseInt(time2[0]) * 3600) + (Integer.parseInt(time2[1]) * 60) + (Integer.parseInt(time2[2]));
                    if (totalTime1 > totalTime2) {
                        return 1;
                    }else{
                        return -1;
                    }
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
    }
}
