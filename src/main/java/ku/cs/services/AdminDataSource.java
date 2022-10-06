package ku.cs.services;

import ku.cs.models.modelBanUser;
import ku.cs.models.modelBanUserList;
import ku.cs.models.modelRegister;
import ku.cs.models.modelRegisterList;

import java.io.*;

public class AdminDataSource {
    private String directoryName;
    private String fileName;

    public AdminDataSource(String directoryName, String fileName){
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted(){
        File file = new File(directoryName);
        if ( !file.exists()){
            file.mkdirs();
        }

        String filePath = directoryName + File.separator + fileName;
        file = new File(filePath);
        if ( ! file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public modelRegisterList read_admin() {
        modelRegisterList user_list = new modelRegisterList();
        modelRegisterList user_list_sort = new modelRegisterList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try{
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            buffer.readLine();
            String input_user = "";
            while (     (input_user = buffer.readLine())    != null     ){
                String[] data = input_user.split(","); // name,username,category,date and time,image_name
                modelRegister user = new modelRegister();
                user.setName(data[0]); // name
                user.setUsername(data[1]); // username
                user.setCategory(data[2]); // category
                user.setTime(data[3]); // date and time
                user_list.addUser(user);
            }
            for (int i = user_list.length() - 1;i != -1; i--){
                user_list_sort.addUser(user_list.getuser(i));
            }
            return user_list_sort;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                reader.close();
                buffer.close();
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }

    public boolean checkuserban(String username){
        String filePath = "data" + File.separator + "ban.csv";
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            buffer.readLine();
            String user = "";
            while(  (user = buffer.readLine())  != null   ){
                String[] data = user.split(",");
                if (username.equals(data[0])){
                    return true;
                }
            }
            return false;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                reader.close();
                buffer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public modelBanUserList readBanUser(){
        modelBanUserList list = new modelBanUserList();
        String filePath = "data" + File.separator + "ban.csv";
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            String Ban = "";
            buffer.readLine();
            while ((Ban = buffer.readLine()) != null ){
                String[] data = Ban.split(",");
                modelBanUser user = new modelBanUser(data[0],data[1]);
                list.addBanUsers(user);
            }return list;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                reader.close();
                buffer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void writeBanUser(modelBanUserList userBan, String detail, String username){
        String filePath = "data" + File.separator + "ban.csv";
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try{
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            buffer.append("username,detail");
            for (modelBanUser user : userBan.getAllUsers()){
                if (user.getUsername().equals(username)){
                    user.setDetailBan(detail);
                }
                String input_user = user.getUsername()+ ","
                        + user.getDetailBan();
                buffer.append(input_user);
                buffer.newLine();
            }
            buffer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void unBanUser(modelBanUserList usersBan,modelRegisterList users,modelBanUser userBan){
        String filePath = "data" + File.separator + "ban.csv";
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try{
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            for (modelBanUser user : usersBan.getAllUsers()){
                if (!user.getUsername().equals(userBan.getUsername())){
                    String input_user = user.getUsername()+ ","
                            + user.getDetailBan();
                    buffer.append(input_user);
                    buffer.newLine();
                }
            }
            buffer.close();

            filePath = "data" + File.separator + "test_user_ban.csv";
            file = new File(filePath);

            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            for (modelRegister user : users.getAllUsers()){
                if (user.getUsername().equals(userBan.getUsername()) && user.getValue_ban().equals("false")){
                    user.setValue_ban("true");
                } //realName,userName,password,role,category,ban or unban,image path
                String input_user = user.getName() + ","
                        + user.getUsername() + ","
                        + user.getPassword() + ","
                        + user.getRole() + ","
                        + user.getCategory() + ","
                        + user.getValue_ban() + ","
                        + user.getImagePath();
                buffer.append(input_user);
                buffer.newLine();
            }
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeTimeLogin(modelRegister user){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try { // name,username,category,date and time
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
            buffer.append(user.getName() + ","
                        + user.getUsername() + ","
                        + user.getCategory() + ","
                        + user.getTime());
            buffer.newLine();
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String pickImageUser(modelRegister user){
        String filePath = "data" + File.separator + "test_user_ban.csv";
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;

        try{
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            String fileRegister = ""; // realName,username,password,role,category,ban or unban,image path
            while ( (fileRegister = buffer.readLine()) != null){
                String[] imageUser = fileRegister.split(",");
                if (user.getUsername().equals(imageUser[1])){
                    return imageUser[6];
                }
            }
            return "";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                reader.close();
                buffer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
