package ku.cs.models;

import ku.cs.services.NisitDataSource;

public class modelregister {
    private String name;
    private String username;
    private String password;
    private String imagePath; // keep path
    private String role;
    private NisitDataSource person;
    public modelregister(String name,String username,String password,String role,String image){
        this.name = name;
        this.username = username;
        this.password = password;
        this.imagePath = image;
        this.role = role;
    }

    // use on login
    public modelregister(String username,String password){
        this("",username,password,"","");
    }

    public boolean checkusername(){
        person = new NisitDataSource("data","user.csv");
        return person.readfile_user(username);
    }

    public void add(modelregister user){
        person = new NisitDataSource("data","user.csv");
        person.writefile_user(user);
    }

    public String role(){
        person = new NisitDataSource("data","user.csv");
        return person.search_role(this.username,this.password);
    }

    public String getName() {return name;}
    public String getUsername(){return username;}
    public String getPassword() {return password;}
    public String getImagePath() {return imagePath;}

    public String getrole(){return role;}

    public void setName(String name) {this.name = name;}
    public void setPassword(String password) {this.password = password;}
}
