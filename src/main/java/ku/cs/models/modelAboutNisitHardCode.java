package ku.cs.models;

public class modelAboutNisitHardCode {
    private modelAboutList nisitList;
    public modelAboutNisitHardCode() {
        nisitList = new modelAboutList();
        readData();
    }
    public void readData() {
        modelAbout boss = new modelAbout("Kornavit Pattanachokvanich","boss","6410450079");
        modelAbout jo = new modelAbout("Sornsiri Hongsa","jo","6410451431");
        modelAbout poy = new modelAbout("Waralee Srimakplaem","poy","6410451377");
        modelAbout Forrence = new modelAbout("Porrin Khongcharoenket","Forrence","6410451156");
        nisitList.addnisit(boss);
        nisitList.addnisit(jo);
        nisitList.addnisit(poy);
        nisitList.addnisit(Forrence);
    }
    public modelAboutList getnisitList() {
        return nisitList;
    }
}
