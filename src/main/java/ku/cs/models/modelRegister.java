package ku.cs.models;

import ku.cs.services.UserDataSource;

public class modelRegister {
    private String realname;
    private String username;
    private String password;
    private String imagePath; // keep path
    private String role;
    private String valueBan;
    private String category;
    private String time;
    private UserDataSource person;

    public modelRegister(String name,String username,String password,String role,String image){
        this.realname = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.imagePath = image;
    }
    public modelRegister(String username){
        this.username = username;
    }

    // use on login
    public modelRegister(String username, String password){
        this.username = username;
        this.password = password;
    }
    public modelRegister(){}
    public modelRegister(String name, String username, String imagePath){ //name,username,category,date and time,image_name form admin
        this.realname = name;
        this.username = username;
        this.imagePath = imagePath;
    }

    public boolean checkUsername(){
        person = new UserDataSource("data","user.csv");
        return person.readfile_user(username);
    }

    public void add(modelRegister user){
        person = new UserDataSource("data","user.csv");
        person.writefile_user(user);
    }

    public String role() {
        person = new UserDataSource("data", "user.csv");
        return person.search_role(this);
    }

    public void change_image(String imagePath){
        person = new UserDataSource("data", "user.csv");
        person.change_image(person.readData(), imagePath, this);
    }

    public String getName() {return realname;}
    public String getUsername(){return username;}
    public String getPassword() {return password;}
    public String getRole(){return role;}
    public String getImagePath() {return imagePath;}
    public String getValue_ban(){return valueBan;}
    public String getCategory(){return category;}
    public String getTime(){return time;}

    public void setName(String name) {this.realname = name;}
    public void setUsername(String username){this.username = username;}
    public void setPassword(String password) {this.password = password;}
    public void setValue_ban(String valueBan){this.valueBan = valueBan;}
    public void setCategory(String category){this.category = category;}
    public void setTime(String time){this.time = time;}
    public void setImagePath(String image){this.imagePath = image;}
}