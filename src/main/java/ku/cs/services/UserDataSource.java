package ku.cs.services;

import ku.cs.models.modelRegister;
import ku.cs.models.modelRequest;
import ku.cs.models.request.modelLearning;

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
                // name,username,password,image path
                if (data[1].equals(user.getUsername()) && data[2].equals(user.getPassword())){
                    user.setName(data[0]);
                    user.setImagePath(data[4]);
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

    public void writefile_learning1(modelRequest user){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
            buffer.append(user.getName() + ","
                    +user.getCategory() + ","
                    +user.getSubject() + ","
                    +user.getStatus());
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writefile_learning2(modelLearning user){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
            buffer.append("," + user.getVote() + ","
                    +user.getCourse() + ","
                    +user.getTeacher() + ","
                    +user.getGroup() + ","
                    +user.getDetail());
            buffer.newLine();
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
