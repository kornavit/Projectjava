package ku.cs.models;
import java.util.ArrayList;

public class modelAboutList {
    private ArrayList<modelAbout> nisits;
    public modelAboutList() {
        nisits = new ArrayList<>();
    }

    public void addnisit(modelAbout nisit) {
        nisits.add(nisit);
    }

    public ArrayList<modelAbout> getAllnisits() {
        return nisits;
    }
}
