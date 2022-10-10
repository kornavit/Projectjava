package ku.cs.services;

import ku.cs.models.modelRequest;
import ku.cs.models.modelRequestList;

import java.io.*;

public class RequestListDataSource {
    private String directoryName;
    private String fileName;

    public RequestListDataSource(String directoryName, String fileName){
        this.directoryName = directoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }
    public RequestListDataSource(){}

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

    public boolean readfile_request(String subject){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line_subject = "";
            while ( (line_subject = buffer.readLine()) != null){
                String[] data = line_subject.split(",");
                // name,username,password,image path
                if (data[1].equals(subject)){
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

    // admin read Request for delete
    public modelRequestList readAdminRequest(String category){
        modelRequestList list = new modelRequestList();
        String filePath = "data" + File.separator + "category" + File.separator + category + ".csv";
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;

        try{
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String input = "";
            while( (input = buffer.readLine()) != null){
                // username,category,head
                String[] data = input.split(",",4);
                // realName,subject,category
                modelRequest request = new modelRequest(data[0],data[2],data[1]);
                request.setRequestDetail(data[3]);
//                123
//                ถนนชอบติดออกมาจากโรงเรียนสาธิตเวลาเลิกเรียน
//                traffic
//                ยังไม่ดำเนินการ,0,-,ซอยจักรพันธ์,พอถึงเวลาเลิกเรียนของนักเรียนโรงเรียนสาธิตทำให้ผู้ปกตรองที่รับนักเรียนมากันเยอะจนรถติดมาถึง ซอยสุวรรณวาจกกสิกิจ,-,ยังไม่มีคนรับเรื่อง,,
                list.addRequest(request);
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
}
