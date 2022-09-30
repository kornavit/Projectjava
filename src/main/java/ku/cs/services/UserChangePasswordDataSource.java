package ku.cs.services;


import ku.cs.models.modelRegisterList;
import ku.cs.models.modelRegister;

import java.io.*;

public class UserChangePasswordDataSource implements DataSource<modelRegisterList>{
    private String directoryName;
    private String fileName;

    public boolean checkUsername(String userName){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;

        try{
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while( (line = buffer.readLine()) != null ){
                String[] collect_data = line.split(",");
                if ( userName.equals(collect_data[1]) ) {
                    return true;
                }
            }
            return false;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public String checkPassword(String userName,String oldPassword){
        String filePath = "data" + File.separator + "user.csv";
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;

        try{
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while( (line = buffer.readLine()) != null ){
                String[] collect_data = line.split(",") ;
                if ( userName.equals(collect_data[1]) && oldPassword.equals(collect_data[2]) ){
                    return "รหัสผ่านเดิมถูกต้อง";
                }
            }
            return "รหัสผ่านเดิมไม่ถูกต้อง";

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
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

    public UserChangePasswordDataSource(String directoryName, String fileName){
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
                modelRegister user = new modelRegister(
                        data[0].trim(), //Real Name
                        data[1].trim(), //User Name
                        data[2].trim(), // Password
                        data[3].trim(), // role
                        data[5].trim()); //User Picture
                user.setCategory(data[4]); // category
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

    public void writeData(modelRegisterList user_changePassword, String user_checkUsername, String user_newPassword){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try{
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            for (modelRegister user : user_changePassword.getAllUsers()){
                if (user.getUsername().equals(user_checkUsername) ){
                    user.setPassword(user_newPassword);
                }
                String input_user = user.getName() + ","
                        +user.getUsername() + ","
                        +user.getPassword() + ","
                        +user.getrole() + ","
                        +user.getCategory() + ","
                        +user.getImagePath();

                buffer.append(input_user);
                buffer.newLine();
            }
            buffer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
