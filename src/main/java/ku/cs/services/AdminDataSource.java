package ku.cs.services;
import ku.cs.models.*;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class AdminDataSource implements DataSource<modelRegisterList>{
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

    @Override
    public modelRegisterList readData() { // main admin program
        modelRegisterList user_list = new modelRegisterList();
        modelRegisterList user_list_sort = new modelRegisterList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(file, StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);
            buffer.readLine();
            String input_user = "";
            while ((input_user = buffer.readLine()) != null) {
                String[] data = input_user.split(","); // name,username,category,date and time,image_name
                modelRegister user = new modelRegister();
                user.setName(data[0]); // name
                user.setUsername(data[1]); // username
                user.setCategory(data[2]); // category
                user.setTime(data[3]); // date and time
                user_list.addUser(user);
            }
            for (int i = user_list.length() - 1; i != -1; i--) {
                user_list_sort.addUser(user_list.getuser(i));
            }
            return user_list_sort;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
                buffer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String pickImageUser(modelRegister user){ // choose from admin
        String filePath = "data" + File.separator + "user.csv";
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;

        try{
            reader = new FileReader(file,StandardCharsets.UTF_8);
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
    public void writeTimeLogin(modelRegister user){ // when user login program
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try { // name,username,category,date and time
            writer = new FileWriter(file,StandardCharsets.UTF_8,true);
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

    public boolean checkUserBan(String username){ // check on user is banned login
        String filePath = "data" + File.separator + "ban.csv";
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(file,StandardCharsets.UTF_8);
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
                buffer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public modelBanUserList readBanUser(String directoryName,String fileName){ // read form ban.csv
        modelBanUserList list = new modelBanUserList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file,StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);
            String Ban = "";
            buffer.readLine();
            while ((Ban = buffer.readLine()) != null ){
                String[] data = Ban.split(","); // username,login,detail
                modelBanUser user;
                if (fileName.equals("preBan.csv")){
                    user = new modelBanUser(data[0],data[1]);
                }else{
                    user = new modelBanUser(data[0],Integer.parseInt(data[1]),data[2]);
                }
                list.addBanUsers(user);
            }return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                reader.close();
                buffer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public modelRequestList readRequestReport(){
        modelRequestList list = new modelRequestList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try{
            reader = new FileReader(file,StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);
            String report = "";
            buffer.readLine();
            while( (report = buffer.readLine()) != null){
                String[] data = report.split(","); // username,head,category,detail(ของการรายงานเนื้อหานั้น)
                modelRequest dataReport = new modelRequest(data[0],data[1],data[2]);
                dataReport.setReportDetail(data[3]);
                list.addRequest(dataReport);
            }return list;
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
    public void readRequestDetailReport(modelRequest request){
        String filePath = "data" + File.separator + "category" + File.separator + request.getCategory() + ".csv";
        File file = new File(filePath);
        
        FileReader reader = null;
        BufferedReader buffer = null;
        
        try{
            reader = new FileReader(file,StandardCharsets.UTF_8);
            buffer = new BufferedReader(reader);
            String readDetail = "";
            if (request.getCategory().equals("building")){
                while((readDetail = buffer.readLine()) != null ){
                    String[] data = readDetail.split(",");
                    if (data[0].equals(request.getUsername()) && data[2].equals(request.getSubject())){
                        request.setRequestDetail(data[7]);
                        break;
                    }
                }
            }else if (request.getCategory().equals("finance")) {
                while((readDetail = buffer.readLine()) != null ){
                    String[] data = readDetail.split(",");
                    if (data[0].equals(request.getUsername()) && data[2].equals(request.getSubject())){
                        request.setRequestDetail(data[6]);
                        break;
                    }
                }
            }else if (request.getCategory().equals("learning")){
                while((readDetail = buffer.readLine()) != null ){
                    String[] data = readDetail.split(",");
                    if (data[0].equals(request.getUsername()) && data[2].equals(request.getSubject())){
                        request.setRequestDetail(data[8]);
                        break;
                    }
                }
            }else if (request.getCategory().equals("traffic")){
                while((readDetail = buffer.readLine()) != null ){
                    String[] data = readDetail.split(",");
                    if (data[0].equals(request.getUsername()) && data[2].equals(request.getSubject())){
                        request.setRequestDetail(data[6]);
                        break;
                    }
                }
            }else if (request.getCategory().equals("other")){
                while((readDetail = buffer.readLine()) != null ){
                    String[] data = readDetail.split(",");
                    if (data[0].equals(request.getUsername()) && data[2].equals(request.getSubject())){
                        request.setRequestDetail(data[5]);
                        break;
                    }
                }
            }buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteRequest(modelRequestList requestList,modelRequestList reportRequest, modelRequest deleteRequest){
        String filePath = "data" + File.separator + "category" + File.separator + deleteRequest.getCategory() + ".csv";
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try{
            writer = new FileWriter(file,StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);
            for (modelRequest request : requestList.getAllRequest()){
                if (!(request.getUsername().equals(deleteRequest.getUsername()) && request.getSubject().equals(deleteRequest.getSubject()))){
                    // username,category,head
                    String writeRequest = request.getUsername() + "," + request.getCategory() + "," + request.getSubject() + request.getRequestDetail();
                    buffer.append(writeRequest);
                    buffer.newLine();
                }
            }
            buffer.close();
            filePath = "data" + File.separator + "deleteDetail.csv";
            file = new File(filePath);

            writer = new FileWriter(file,StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);
            buffer.append("username,head,category,detail(ของการรายงานเนื้อหานั้น)");
            buffer.newLine();
            for (modelRequest request : reportRequest.getAllRequest()){
                if (!(request.getUsername().equals(deleteRequest.getUsername()) && request.getSubject().equals(deleteRequest.getSubject()))){
                    // username,head,category,detail(ของการรายงานเนื้อหานั้น)
                    String writeReport = request.getUsername() + "," + request.getSubject() + "," + request.getCategory() + "," + request.getReportDetail();
                    buffer.append(writeReport);
                    buffer.newLine();
                }
            }
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void countLoginBan(modelBanUserList userBan,modelRegister person){ // count user is banned login
        String filePath = "data" + File.separator + "ban.csv";
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try{
            writer = new FileWriter(file,StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);
            buffer.append("username,login,detail");
            buffer.newLine();
            for (modelBanUser user : userBan.getAllUsers()){
                if (user.getUsername().equals(person.getUsername())){
                    user.setCountLogin(user.getCountLogin() + 1);
                }
                String input_user = user.getUsername()+ ","
                        + user.getCountLogin() + ","
                        + user.getDetailBan();
                buffer.append(input_user);
                buffer.newLine();
            }
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeBanDetailUser(modelBanUserList userBan, String detail, String username){ // detail to unban
        String filePath = "data" + File.separator + "ban.csv";
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try{
            writer = new FileWriter(file,StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);
            buffer.append("username,login,detail");
            buffer.newLine();
            for (modelBanUser user : userBan.getAllUsers()){
                if (user.getUsername().equals(username)){
                    user.setDetailBan(detail.replace("\n","|"));
                }
                String input_user = user.getUsername()+ ","
                        + user.getCountLogin() + ","
                        + user.getDetailBan();
                buffer.append(input_user);
                buffer.newLine();
            }
            buffer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void unBanUser(modelBanUserList usersBan,modelRegisterList users,modelBanUser userBan){ // unbanUser two file
        String filePath = "data" + File.separator + "ban.csv";
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try{
            writer = new FileWriter(file,StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);
            buffer.append("username,login,detail");
            buffer.newLine();
            for (modelBanUser user : usersBan.getAllUsers()){
                if (!user.getUsername().equals(userBan.getUsername())){
                    String input_user = user.getUsername()+ ","
                            + userBan.getCountLogin() + ","
                            + user.getDetailBan();
                    buffer.append(input_user);
                    buffer.newLine();
                }
            }
            buffer.close();

            filePath = "data" + File.separator + "user.csv";
            file = new File(filePath);

            writer = new FileWriter(file,StandardCharsets.UTF_8);
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
    public void banUser(modelBanUserList banUserList,modelRegisterList users,modelBanUser banUser){
        String filePath = "data" + File.separator + "preBan.csv";
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try{
            writer = new FileWriter(file,StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);
            buffer.append("username,detail");
            buffer.newLine();
            for (modelBanUser user : banUserList.getAllUsers()){
                if (!user.getUsername().equals(banUser.getUsername())){
                    String input_user = user.getUsername()+ ","
                            + user.getDetailBan();
                    buffer.append(input_user);
                    buffer.newLine();
                }
            }
            buffer.close();

            filePath = "data" + File.separator + "user.csv";
            file = new File(filePath);

            writer = new FileWriter(file,StandardCharsets.UTF_8);
            buffer = new BufferedWriter(writer);
            for (modelRegister user : users.getAllUsers()){
                if (user.getUsername().equals(banUser.getUsername()) && user.getValue_ban().equals("true")){
                    user.setValue_ban("false");
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

            filePath = "data" + File.separator + "ban.csv";
            file = new File(filePath);

            writer = new FileWriter(file,StandardCharsets.UTF_8,true);
            buffer = new BufferedWriter(writer);
            buffer.append(banUser.getUsername()).append(",0,ยังไม่มีการขอคืนสิทธิ์");
            buffer.newLine();
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
