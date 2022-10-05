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

    public modelRequestList readfileRequest(){
        modelRequestList requestList = new modelRequestList();
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;
        try {
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String line = "";
            while((line = buffer.readLine()) != null){
                String[] data = line.split(",");
                modelRequest request = new modelRequest(data[0], //name
                        data[1], //category
                        data[2], //head
                        data[3]); //status
                request.setVotePoint(Integer.parseInt(data[4])); //vote
                request.setDetail(data[5]); //detail
                requestList.addRequest(request);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                buffer.close();
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return requestList;
    }

    public void writefileRequest(modelRequest request){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
            buffer.append(request.getName() + ","
                    +request.getCategory() + ","
                    +request.getSubject() + ","
                    +request.getName() + ","
                    +request.getStatus());
            buffer.newLine();
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
