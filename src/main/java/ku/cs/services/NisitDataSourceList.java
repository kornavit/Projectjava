package ku.cs.services;


import ku.cs.models.modelRegisterList;
import ku.cs.models.modelregister;

import java.io.*;

public class NisitDataSourceList implements DataSource<modelRegisterList> {
    private String directoryName;
    private String fileName;

    public NisitDataSourceList(String directoryName, String fileName){
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
                modelregister user = new modelregister(
                        data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        data[3].trim(),
                        null
                );
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

            for (modelregister user : user_changePassword.getAllUsers()){
                if (user.getUsername().equals(user_checkUsername) ){
                    user.setPassword(user_newPassword);
                }
                String input_user = user.getName() + ","
                        +user.getUsername() + ","
                        +user.getPassword() + ","
                        +user.getrole() + ","
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
