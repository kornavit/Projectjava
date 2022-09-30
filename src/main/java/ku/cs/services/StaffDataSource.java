package ku.cs.services;

import ku.cs.models.modelRegister;
import ku.cs.models.modelRequest;
import ku.cs.models.modelRequestList;

import java.io.*;

public class StaffDataSource {
    private String directoryName;
    private String fileName;


    public StaffDataSource(String directoryName, String fileName){
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

    public void StaffLogin(modelRegister login){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String staffLogin = "";
            while ( (staffLogin = buffer.readLine()) != null ){
                String[] data = staffLogin.split(",");
                if(data[1].equals(login.getUsername()) && data[2].equals(login.getPassword())   ){
                    login.setCategory(data[4]);
                    break;
                }
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            try{
                buffer.close();
                reader.close();
            } catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }

        public modelRequestList readData() {
        modelRequestList list = new modelRequestList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader reader = null;
        BufferedReader buffer = null;

        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String userComplain = "";
            while (     (userComplain = buffer.readLine())    != null     ){
                String[] data = userComplain.split(",");
                modelRequest request = new modelRequest(
                        data[0].trim(), //requestSubject
                        data[1].trim(), //staffGroup
                        data[2].trim(), //category
                        data[4].trim(), //requestStatus
                        data[5].trim()); //staffName
                request.setRequestDetail(data[3].trim()); //requestDetail
                list.addRequest(request);
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

    public void writeData(modelRequestList reportProblemList, modelRequest reportProblem){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try{
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            String save = "";

            for (modelRequest report : reportProblemList.getAllRequest()){
                if ( report.getSubject().equals(reportProblem.getSubject()) &&
                report.getCategory().equals(reportProblem.getCategory()) &&
                report.getStaffGroup().equals(reportProblem.getStaffGroup()) ){
                    save = report.getSubject() + ","
                            +report.getStaffGroup() + ","
                            +report.getCategory() + ","
                            +report.getRequestDetail() + ","
                            +reportProblem.getRequestStatus() + ","
                            +reportProblem.getStaffName() + ","
                            +reportProblem.getManageDetail();

                }
                else {
                    save = report.getSubject() + ","
                            +report.getStaffGroup() + ","
                            +report.getCategory() + ","
                            +report.getRequestDetail() + ","
                            +report.getRequestStatus() + ","
                            +report.getStaffName() + ","
                            +report.getManageDetail();
                }
                buffer.append(save);
                buffer.newLine();
            }
            buffer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
