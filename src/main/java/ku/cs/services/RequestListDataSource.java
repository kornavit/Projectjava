package ku.cs.services;

import ku.cs.models.modelRegister;
import ku.cs.models.modelRegisterList;
import ku.cs.models.modelRequest;
import ku.cs.models.modelRequestList;
import ku.cs.models.request.modelBuilding;
import ku.cs.models.request.modelLearning;

import java.io.*;

public class RequestListDataSource {
    private String directoryName;
    private String fileName;

    public RequestListDataSource(String directoryName, String fileName){
        this.directoryName = directoryName;
        this.fileName = fileName;
    }

    public RequestListDataSource(String directoryName) {
        this.directoryName = directoryName;
    }


    public modelRequestList readfileRequest(String name){
        modelRequestList requestList = new modelRequestList();
        String buildingPath = "data" + File.separator + "category" + File.separator + "building.csv";
        String financePath = "data" + File.separator + "category" + File.separator + "finance.csv";
        String learningPath = "data" + File.separator + "category" + File.separator + "learning.csv";
        String otherPath = "data" + File.separator + "category" + File.separator + "other.csv";
        String trafficPath = "data" + File.separator + "category" + File.separator + "traffic.csv";

        File file = new File(buildingPath);


        FileReader reader = null;
        BufferedReader buffer = null;

        try{
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            //read_building
            buffer.readLine();
            String request = "";
            while (( request = buffer.readLine()) != null){
                String[] data = request.split(",");
                //String username, String subject,String time, String status, int votePoint
                if (data[0].equals(name)){
                    modelRequest dataRequest = new modelRequest(data[0],data[2],data[9],data[3],Integer.parseInt(data[4]));
                    dataRequest.setCategory(data[1]);
                    dataRequest.setRequestDetail(data[7]);
                    dataRequest.setManageDetail(data[11]);
                    //add request
                    requestList.addRequest(dataRequest);
                }
            }
            buffer.close();

            //read_finance
            file = new File(financePath);
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            while (( request = buffer.readLine()) != null){
                String[] data = request.split(",");
                if (data[0].equals(name)){
                    modelRequest dataRequest = new modelRequest(data[0],data[2],data[7],data[3],Integer.parseInt(data[4]));
                    dataRequest.setCategory(data[1]);
                    dataRequest.setRequestDetail(data[6]);
                    dataRequest.setManageDetail(data[9]);
                    //add request
                    requestList.addRequest(dataRequest);
                }
            }
            buffer.close();

            //read_learning
            file = new File(learningPath);
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            while (( request = buffer.readLine()) != null){
                String[] data = request.split(",");
                if (data[0].equals(name)){
                    modelRequest dataRequest = new modelRequest(data[0],data[2],data[9],data[3],Integer.parseInt(data[4]));
                    dataRequest.setCategory(data[1]);
                    dataRequest.setRequestDetail(data[8]);
                    dataRequest.setManageDetail(data[11]);
                    //add request
                    requestList.addRequest(dataRequest);
                }
            }
            buffer.close();

            //read_other
            file = new File(otherPath);
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            while (( request = buffer.readLine()) != null){
                String[] data = request.split(",");
                if (data[0].equals(name)){
                    modelRequest dataRequest = new modelRequest(data[0],data[2],data[7],data[3],Integer.parseInt(data[4]));
                    dataRequest.setCategory(data[1]);
                    dataRequest.setRequestDetail(data[5]);
                    dataRequest.setManageDetail(data[8]);
                    //add request
                    requestList.addRequest(dataRequest);
                }
            }
            buffer.close();

            //read_traffic
            file = new File(trafficPath);
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            while (( request = buffer.readLine()) != null){
                String[] data = request.split(",");
                if (data[0].equals(name)){
                    modelRequest dataRequest = new modelRequest(data[0],data[2],data[9],data[3],Integer.parseInt(data[4]));
                    dataRequest.setCategory(data[1]);
                    dataRequest.setRequestDetail(data[7]);
                    dataRequest.setManageDetail(data[10]);
                    //add request
                    requestList.addRequest(dataRequest);
                }
            }
            buffer.close();

            return requestList;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFileRequest(modelRequest request){
        String filePath = directoryName + File.separator + request.getCategory() + ".csv";
        File file = new File(filePath);
        System.out.println(request.getCategory());
        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            //building
            if (request.getCategory().equals("building")){
                writer = new FileWriter(file,true);
                buffer = new BufferedWriter(writer);
                buffer.append(request.getUsername() + ","
                        +request.getCategory() + ","
                        +request.getSubject() + ","
                        +request.getStatus() + ","
                        +request.getVotePoint() + ","
                        +request.getBuilding().getEquipment() + ","
                        +request.getBuilding().getLocation() + ","
                        +request.getBuilding().getDetail().replace("\n","|") + ","
                        +request.getBuilding().getImagePath()+ ","
                        +request.getTime() + ",ยังไม่มีเจ้าหน้าที่รับเรื่อง,-,"
                );
                buffer.newLine();
                buffer.close();
            }
            //finance
            if (request.getCategory().equals("finance")){
                writer = new FileWriter(file, true);
                buffer = new BufferedWriter(writer);
                buffer.append(request.getUsername() + ","
                        +request.getCategory() + ","
                        +request.getSubject() + ","
                        +request.getStatus() + ","
                        +request.getVotePoint() + ","
                        +request.getFinance().getType() + ","
                        +request.getFinance().getDetail().replace("\n","|")+ ","
                        +request.getTime() + ",ยังไม่มีเจ้าหน้าที่รับเรื่อง,-,"
                );
                buffer.newLine();
                buffer.close();
            }
            //learning
            if (request.getCategory().equals("learning")){
                writer = new FileWriter(file,true);
                buffer = new BufferedWriter(writer);
                buffer.append(request.getUsername() + ","
                        +request.getCategory() + ","
                        +request.getSubject() + ","
                        +request.getStatus() + ","
                        +request.getVotePoint() + ","
                        +request.getLearning().getCourse() + ","
                        +request.getLearning().getTeacher() + ","
                        +request.getLearning().getGroup() + ","
                        +request.getLearning().getDetail().replace("\n","|")+ "," // staffGroup,staffName,การแก้ปัญหา(คันด้วย | พอขึ้นบรรทัดใหม่),คนที่มากด like(คันด้วย | )
                        +request.getTime() + ",ยังไม่มีเจ้าหน้าที่รับเรื่อง,-,"
                );
                buffer.newLine();
                buffer.close();
            }
            //other
            if (request.getCategory().equals("other")) {
                writer = new FileWriter(file, true);
                buffer = new BufferedWriter(writer);
                buffer.append(request.getUsername() + ","
                        + request.getCategory() + ","
                        + request.getSubject() + ","
                        + request.getStatus() + ","
                        + request.getVotePoint() + ","
                        +request.getOther().getDetail().replace("\n","|")+ ","
                        +request.getTime() + ",ยังไม่มีเจ้าหน้าที่รับเรื่อง,-,"
                );
                buffer.newLine();
                buffer.close();
            }
            //traffic
            if (request.getCategory().equals("traffic")) {
                writer = new FileWriter(file, true);
                buffer = new BufferedWriter(writer);
                buffer.append(request.getUsername() + ","
                        + request.getCategory() + ","
                        + request.getSubject() + ","
                        + request.getStatus() + ","
                        + request.getVotePoint() + ","
                        +request.getTraffic().getLocation() + ","
                        +request.getTraffic().getDetail().replace("\n","|") + ","
                        +request.getTraffic().getImagePath()+ ","
                        +request.getTime() + ",ยังไม่ได้รับเรื่องร้องเรียน,-,"
                );
                buffer.newLine();
                buffer.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
     public modelRequestList readData(String username) {
         modelRequestList list = new modelRequestList();
         String filePath = directoryName + File.separator + fileName;
         File file = new File(filePath);
         FileReader reader = null;
         BufferedReader buffer = null;

         try {
             reader = new FileReader(file);
             buffer = new BufferedReader(reader);

             String userDataPath = "";
             while ((userDataPath = buffer.readLine()) != null) {
                 String[] data = userDataPath.split(",");
                 modelRequest user = new modelRequest(
                         data[0].trim(), //User Name
                         data[1].trim(), //category
                         data[2].trim(), // head
                         data[3].trim(), // status
                         data[4].trim()); //vote
                 list.addRequest(user);
             }

         } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
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
         return list;
     }

}
