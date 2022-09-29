package ku.cs.services;

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
