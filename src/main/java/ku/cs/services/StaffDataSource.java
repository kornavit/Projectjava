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
                if(  data[1].equals(login.getUsername()) && data[2].equals(login.getPassword())  ){
                    login.setCategory(data[4]);
                    System.out.println(login.getCategory());
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
            while ((userComplain = buffer.readLine()) != null) {
                String[] data = userComplain.split(",");
                modelRequest request = new modelRequest(
                        data[1].trim(), //category
                        data[2].trim(), //head
                        data[3].trim(), //status
                        data[4].trim()); //staffName
                if (fileName.equals("building.csv")){
                    request.setUsername(data[0]);
                    request.setVotePoint(Integer.parseInt(data[4]));
                    request.setExtra(data[5] + "," + data[6]);
                    request.setExtraDetail("รายละเอียดความเสียหาย : " + data[5].trim() + "\n"
                            + "สถานที่ : " + data[6].trim());
                    request.setRequestDetail(data[7].trim());
                    request.setImagePath(data[8].trim());
                    request.setTime(data[9].trim());
                    request.setStaffName(data[10].trim());
                    request.setReport(data[11].trim());
                    request.setVote(data[12].split("\\|"));
                }

                if (fileName.equals("finance.csv")) {
                    request.setUsername(data[0]);
                    request.setVotePoint(Integer.parseInt(data[4]));
                    request.setExtra(data[5]);
                    request.setExtraDetail("การทำธุรกรรมเกี่ยวกับการเงิน : " + data[5].trim());
                    request.setRequestDetail(data[6].trim());
                    request.setTime(data[7].trim());
                    request.setStaffName(data[8].trim());
                    request.setReport(data[9].trim());
                    request.setVote(data[10].split("\\|"));
                }

                if (fileName.equals("learning.csv")){
                    request.setUsername(data[0]);
                    request.setVotePoint(Integer.parseInt(data[4]));
                    request.setExtra(data[5] + "," + data[6] + "," + data[7]);
                    request.setExtraDetail("วิชา : " + data[5].trim() + "\n"
                            + "อาจารย์ : " + data[6].trim() + "\n"
                            + "หมู่เรียน : " + data[7].trim() + "\n");
                    request.setRequestDetail(data[8].trim());
                    request.setTime(data[9].trim());
                    request.setStaffName(data[10].trim());
                    request.setReport(data[11].trim());
                    request.setVote(data[12].split("\\|"));

                }

                if (fileName.equals("other.csv")){
                    request.setUsername(data[0]);
                    request.setVotePoint(Integer.parseInt(data[4]));
                    request.setRequestDetail(data[5].trim());
                    request.setTime(data[6].trim());
                    request.setStaffName(data[7].trim());
                    request.setReport(data[8].trim());
                    request.setVote(data[9].split("\\|"));
                }

                if (fileName.equals("traffic.csv")){
                    request.setUsername(data[0]);
                    request.setVotePoint(Integer.parseInt(data[4]));
                    request.setExtra(data[5]);
                    request.setExtraDetail("สถานที่ : " + data[5].trim());
                    request.setRequestDetail(data[6].trim().replace("|", "\n"));
                    request.setImagePath(data[7].trim());
                    request.setTime(data[8].trim());
                    request.setStaffName(data[9].trim());
                    request.setReport(data[10].trim());
                    request.setVote(data[11].split("\\|"));
                }
                list.addRequest(request);
            }
            return list;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void writeData(modelRequestList reportProblemList, modelRequest reportProblem,String staffName,String status){
        //reportProblem = data that staff change
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try{
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);

            String save = "";

            for (modelRequest report : reportProblemList.getAllRequest()) {
                if (fileName.equals("building.csv")) {
                    if (report.getSubject().equals(reportProblem.getSubject()) &&
                            report.getUsername().equals(reportProblem.getUsername()) &&
                            report.getTime().equals(reportProblem.getTime())) {
                        report.setStatus(status);
                        report.setStaffName(staffName);
                        report.setReport(reportProblem.getReport());
                    }
                    save = report.getUsername() + ","
                            + report.getCategory() + ","
                            + report.getSubject() + ","
                            + report.getStatus() + ","
                            + report.getVotePoint() + ","
                            + report.getExtra() + ","
                            + report.getRequestDetail() + ","
                            + report.getImagePath() + ","
                            + report.getTime() + ","
                            + report.getStaffName() + ","
                            + report.getReport() + ","
                            + report.getVote();
                    buffer.append(save);
                    buffer.newLine();
                }

                else if (fileName.equals("finance.csv")) {
                    if (report.getSubject().equals(reportProblem.getSubject()) &&
                            report.getUsername().equals(reportProblem.getUsername()) &&
                            report.getTime().equals(reportProblem.getTime())) {
                        report.setStatus(status);
                        report.setStaffName(staffName);
                        report.setReport(reportProblem.getReport());
                    }
                    save = report.getUsername() + ","
                            + report.getCategory() + ","
                            + report.getSubject() + ","
                            + report.getStatus() + ","
                            + report.getVotePoint() + ","
                            + report.getExtra() + ","
                            + report.getRequestDetail() + ","
                            + report.getTime() + ","
                            + report.getStaffName() + ","
                            + report.getReport() + ","
                            + report.getVote();
                        buffer.append(save);
                        buffer.newLine();
                    }

                else if (fileName.equals("learning.csv")) {
                    if (report.getSubject().equals(reportProblem.getSubject()) &&
                            report.getUsername().equals(reportProblem.getUsername()) &&
                            report.getTime().equals(reportProblem.getTime())) {
                        report.setStatus(status);
                        report.setStaffName(staffName);
                        report.setReport(reportProblem.getReport());
                    }
                    save = report.getUsername() + ","
                            + report.getCategory() + ","
                            + report.getSubject() + ","
                            + report.getStatus() + ","
                            + report.getVotePoint() + ","
                            + report.getExtra() + ","
                            + report.getRequestDetail() + ","
                            + report.getTime() + ","
                            + report.getStaffName() + ","
                            + report.getReport() + ","
                            + report.getVote();
                        buffer.append(save);
                        buffer.newLine();
                }

                else if (fileName.equals("other.csv")) {
                    if (report.getSubject().equals(reportProblem.getSubject()) &&
                            report.getUsername().equals(reportProblem.getUsername()) &&
                            report.getTime().equals(reportProblem.getTime())) {
                        report.setStatus(status);
                        report.setStaffName(staffName);
                        report.setReport(reportProblem.getReport());
                    }
                    save = report.getUsername() + ","
                            + report.getCategory() + ","
                            + report.getSubject() + ","
                            + report.getStatus() + ","
                            + report.getVotePoint() + ","
                            + report.getExtra() + ","
                            + report.getRequestDetail() + ","
                            + report.getTime() + ","
                            + report.getStaffName() + ","
                            + report.getReport() + ","
                            + report.getVote();
                        buffer.append(save);
                        buffer.newLine();
                    }

                else if (fileName.equals("traffic.csv")) {
                    if (report.getSubject().equals(reportProblem.getSubject()) &&
                            report.getUsername().equals(reportProblem.getUsername()) &&
                            report.getTime().equals(reportProblem.getTime())) {
                        report.setStatus(status);
                        report.setStaffName(staffName);
                        report.setReport(reportProblem.getReport());
                    }
                    save = report.getUsername() + ","
                            + report.getCategory() + ","
                            + report.getSubject() + ","
                            + report.getStatus() + ","
                            + report.getVotePoint() + ","
                            + report.getExtra() + ","
                            + report.getRequestDetail().replace("\n","|") + ","
                            + report.getImagePath() + ","
                            + report.getTime() + ","
                            + report.getStaffName() + ","
                            + report.getReport() + ","
                            + report.getVote();
                        buffer.append(save);
                        buffer.newLine();
                    }
            }
            buffer.close();
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }
}
