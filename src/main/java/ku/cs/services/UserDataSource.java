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

    public void writefile_request(modelRequest user){
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
                    +user.getDetail());
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
                    +user.getDetailTraffic());
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

            String input_user = "";
            while (     (input_user = buffer.readLine())    != null     ){
                String[] data = input_user.split(","); // name,username,category,date and time,image_name
                modelRegister user = new modelRegister(
                        data[0].trim(), // name
                        data[1].trim(), // username
                        data[4].trim() // image_name
                );
                user.setCategory(data[2]); // category
                user.setTime(data[3]); // date and time
                user_list.addUser(user);
            }
            for (int i = user_list.length() - 1;i > 0; i--){
                user_list_sort.addUser(user_list.getuser(i));
            }
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
        return user_list_sort;
    }
}