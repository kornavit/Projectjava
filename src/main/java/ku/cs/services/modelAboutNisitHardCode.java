package ku.cs.services;

import ku.cs.models.modelAbout;
import ku.cs.models.modelAboutList;

public class modelAboutNisitHardCode {
    private modelAboutList nisitList;
    public modelAboutNisitHardCode() {
        nisitList = new modelAboutList();
        readData();
    }
    public void readData() {
        modelAbout boss = new modelAbout("Kornavit Pattanachokvanich","boss","6410450079");
        modelAbout joe = new modelAbout("Sornsiri Hongsa","joe","6410451431");
        modelAbout poy = new modelAbout("Waralee Srimakplaem","poy","6410451377");
        modelAbout Forrence = new modelAbout("Porrin Khongcharoenket","Forrence","6410451156");
        nisitList.addNisit(boss);
        nisitList.addNisit(joe);
        nisitList.addNisit(poy);
        nisitList.addNisit(Forrence);
    }
    public modelAboutList getNisitList() {
        return nisitList;
    }
}
