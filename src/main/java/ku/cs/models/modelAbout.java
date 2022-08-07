package ku.cs.models;

public class modelAbout {
    private String name;
    private String nickname;
    private String codename;

    public modelAbout(String name,String nickname,String codename){
        this.name = name;
        this.nickname = nickname;
        this.codename = codename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    @Override
    public String toString(){
        return name + " [" + codename + "]";
    }
}
