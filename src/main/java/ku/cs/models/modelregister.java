package ku.cs.models;

public class modelregister {
    private String name;
    private String username;
    private String password;
    private String image; // keep path

    public modelregister(String name,String username,String password,String image){
        this.name = name;
        this.username = username;
        this.password = password;
        this.image = image;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getUsername(){return username;}

    public String getPassword() {return password;}

    public String getImage() {return image;}

    public void setPassword(String password) {this.password = password;}
}
