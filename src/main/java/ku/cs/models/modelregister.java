package ku.cs.models;

public class modelRegister {
    private String name;
    private String username;
    private String password;
    private String imagePath; // keep path

    public modelRegister(String name, String username, String password){
        this.name = name;
        this.username = username;
        this.password = password;
        this.imagePath = getClass().getResource("/ku/cs/images/default-profile.jpg").toExternalForm();
    }

    public boolean checkpassword(String password_one,String password_two){
        if (password_one.equals(password_two)){
            return true;
        }
        return false;
    }
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getUsername(){return username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}


}
