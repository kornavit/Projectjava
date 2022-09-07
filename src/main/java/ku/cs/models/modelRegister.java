package ku.cs.models;

import ku.cs.services.UserDataSource;

public class modelRegister {
    private String name;
    private String username;
    private String password;
    private String imagePath; // keep path
    private String role;
    private UserDataSource person;
    public modelRegister(String name, String username, String password, String role, String image){
        this.name = name;
        this.username = username;
        this.password = password;
        this.imagePath = image;
        this.role = role;
    }

    // use on login
    public modelRegister(String username, String password){
        this("",username,password,"","");
    }

    public modelRegister(){
        this("","","","","");
    }

    public modelRegister(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
        this.imagePath = getClass().getResource("/ku/cs/images/default-profile.jpg").toExternalForm();
    }

    public boolean checkusername(){
        person = new UserDataSource("data","user.csv");
        return person.readfile_user(username);
    }

    public void add(modelRegister user){
        person = new UserDataSource("data","user.csv");
        person.writefile_user(user);
    }

    public String role() {
        person = new UserDataSource("data", "user.csv");
        return person.search_role(this.username, this.password);
    }


    public String getName() {return name;}
    public String getUsername(){return username;}
    public String getPassword() {return password;}
    public String getrole(){return role;}
    public String getImagePath() {return imagePath;}

    public void setName(String name) {this.name = name;}
    public void setPassword(String password) {this.password = password;}
}