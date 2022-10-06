package ku.cs.services;

import ku.cs.models.modelRegister;
import ku.cs.models.modelRegisterList;

import java.io.*;

public class UserDataSource { // login and register
    private String directoryName;
    private String fileName;

    public UserDataSource(String directoryName, String fileName){
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

    public boolean readfile_user(String username){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line_name = "";
            while ( (line_name = buffer.readLine()) != null){
                String[] data = line_name.split(",");
                // name,username,password,image path
                if (data[1].equals(username)){
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public modelRegisterList readData() {
        modelRegisterList list = new modelRegisterList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String input_user = "";
            while (     (input_user = buffer.readLine())    != null     ){
                String[] data = input_user.split(",");
                //realName,userName,password,role,category,ban or unban,image path
                modelRegister user = new modelRegister(
                        data[0].trim(), //Real Name
                        data[1].trim(), //User Name
                        data[2].trim(), // Password
                        data[3].trim(), // role
                        data[6].trim()); //User Picture
                user.setCategory(data[4]);
                user.setValue_ban(data[5]);
                list.addUser(user);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                buffer.close();
                reader.close();
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    public void writeImage(modelRegisterList userChangePicture, modelRegister person){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try{
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            for (modelRegister user : userChangePicture.getAllUsers()){
                if (user.getUsername().equals(person.getUsername())){
                    user.setImagePath(person.getImagePath());
                    //realName,userName,password,role,team,image path
                }
                String userInfo = user.getName() + ","
                        +user.getUsername() + ","
                        +user.getPassword() + ","
                        +user.getRole() + ","
                        +user.getValue_ban() + ","
                        +user.getCategory() + ","
                        +user.getImagePath();

                buffer.append(userInfo);
                buffer.newLine();
            }
            buffer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writefile_user(modelRegister user){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
            buffer.append(user.getName() + ","
                    +user.getUsername() + ","
                    +user.getPassword() + ","
                    +user.getRole() + ","
                    +user.getImagePath() + ","
                    +user.getValue_ban() + ","
                    +user.getCategory());
            buffer.newLine();
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String search_role(modelRegister user){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line_name = "";
            while ( (line_name = buffer.readLine()) != null){
                String[] data = line_name.split(",");
                // realName,userName,password,role,category,ban or unban,image path
                if (data[1].equals(user.getUsername()) && data[2].equals(user.getPassword())){
                    user.setName(data[0]);// realName
                    user.setCategory(data[4]); // category
                    user.setValue_ban(data[5]);// ban or unban
                    user.setImagePath(data[6]); // image
                    return data[3];
                }
            }
            return "กรอก username หรือ password ผิด";
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try{
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
