package ku.cs.models;

import ku.cs.services.UserDataSource;

public class modelRegister {
    private String name;
    private String username;
    private String password;
    private String imagePath; // keep path
    private String role;
    private String value_ban;
    private String category;

    private String time;
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

    public modelRegister(){}

    public modelRegister(String name, String username, String imagePath){ //name,username,category,date and time,image_name form admin
        this.name = name;
        this.username = username;
        this.imagePath = imagePath;
    }

    public boolean checkusername(){
        person = new UserDataSource("data","test_user_ban.csv");
        return person.readfile_user(username);
    }

    public void add(modelRegister user){
        person = new UserDataSource("data","test_user_ban.csv");
        person.writefile_user(user);
    }

    public String role() {
        person = new UserDataSource("data", "test_user_ban.csv");
        return person.search_role(this);
    }


    public String getName() {return name;}
    public String getUsername(){return username;}
    public String getPassword() {return password;}
    public String getrole(){return role;}
    public String getImagePath() {return imagePath;}
    public String getValue_ban(){return value_ban;}
    public String getCategory(){return category;}
    public String getTime(){return time;}

    public void setName(String name) {this.name = name;}
    public void setPassword(String password) {this.password = password;}
    public void setValue_ban(String value_ban){this.value_ban = value_ban;}
    public void setCategory(String category){this.category = category;}
    public void setTime(String time){this.time = time;}
}