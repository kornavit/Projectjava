package ku.cs.services;

import ku.cs.models.modelRegister;

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
                    +user.getrole() + ","
                    +user.getImagePath());
            buffer.newLine();
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String search_role(String username,String password){
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
                modelRegister nisit = new modelRegister(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim(),
                        null
                );
                if (nisit.getUsername().equals(username) && nisit.getPassword().equals(password)){
                    return nisit.getrole();
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
