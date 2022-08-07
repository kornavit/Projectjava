package ku.cs.models;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

//    public String checkusername(){
//        while(/*check csv file in username*/){
//            if (/*check username.equals(username)*/){
//                return "please change the username";
//            }
//        }
//        return "Correct";
//    }

    public int checkpassword(String password_one,String password_two){
        if (password_one.equals(password_two)){
            return 1;
        }
        return 0;
    }

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getUsername(){return username;}

    public String getPassword() {return password;}

    public String getImage() {return image;}

    public void setPassword(String password) {this.password = password;}
}
