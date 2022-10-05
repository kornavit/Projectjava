package ku.cs.services;

import ku.cs.models.modelRegister;
import ku.cs.models.modelRequest;
import ku.cs.models.request.modelBuilding;
import ku.cs.models.request.modelLearning;
import ku.cs.models.request.modelOther;
import ku.cs.models.request.modelTraffic;
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

    public boolean readfile_user(String username){ //check username
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
    public modelRegisterList readData() {
        modelRegisterList list = new modelRegisterList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String userDataPath = "";
            while (     (userDataPath = buffer.readLine())    != null     ){
                String[] data = userDataPath.split(",");
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

    public void writeData(modelRegisterList userChangePicture, modelRegister person){
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
                    user.setImagePath(data[5]);
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

    public void writefile_request(modelRequest user){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
            buffer.append(user.getRealName() + ","
                    +user.getCategory() + ","
                    +user.getSubject() + ","
                    +user.getStatus());
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writefile_learning(modelLearning user){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file, true);
            buffer = new BufferedWriter(writer);
            buffer.append("," + user.getVote() + ","
                    + user.getCourse() + ","
                    + user.getTeacher() + ","
                    + user.getGroup() + ","
                    + user.getDetail());
            buffer.newLine();
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void writefile_building(modelBuilding user){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
            buffer.append("," + user.getVote() + ","
                    +user.getEquiument() + ","
                    +user.getLocation() + ","
                    +user.getDetail() + ","
                    +user.getImagePath());
            buffer.newLine();
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writefile_traffic(modelTraffic user){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file, true);
            buffer = new BufferedWriter(writer);
            buffer.append("," + user.getVote() + ","
                    +user.getLocation() + ","
                    +user.getDetailTraffic() + ","
                    +user.getImagePath());
            buffer.newLine();
            buffer.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public void writefile_other(modelOther user){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
            buffer.append("," + user.getVote() + ","
                    +user.getDetail());
            buffer.newLine();
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}