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

    public modelRequestList readfileRequest(){
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
                    modelRequest dataRequest = new modelRequest(data[0], //name
                            data[1], //category
                            data[2], //head
                            data[3], // status
                            Integer.parseInt(data[4]),  //vote
                            data[7], //detail   data[5] = equiment data[6] = location
                            data[8], //imagePath
                            data[9]); //time
                    //add request
                    requestList.addRequest(dataRequest);

            }
            buffer.close();

            //read_finance
            file = new File(financePath);
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            while (( request = buffer.readLine()) != null){
                String[] data = request.split(",");
                    modelRequest dataRequest = new modelRequest(data[0], //name
                            data[1],//category
                            data[2],  //head
                            data[3], //status
                            Integer.parseInt(data[4]),  //vote
                            data[6], //detail   //data[5] = amount
                            data[7], //imagePath
                            data[8]);  //time);
                    //add request
                    requestList.addRequest(dataRequest);


            }
            buffer.close();

            //read_learning
            file = new File(learningPath);
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            while (( request = buffer.readLine()) != null){
                String[] data = request.split(",");
                    modelRequest dataRequest = new modelRequest(data[0], //usename
                                                                data[1], //category
                                                                data[2], //head
                                                                data[3], //status
                                                                Integer.parseInt(data[4]),// vote
                                                                data[8], // detail data[5,6,7] = course,teacher,sec
                                                                "", //image
                                                                data[9]); //time
                    //add request
                    requestList.addRequest(dataRequest);



            }
            buffer.close();

            //read_other
            file = new File(otherPath);
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            while (( request = buffer.readLine()) != null){
                String[] data = request.split(",");
                    modelRequest dataRequest = new modelRequest(data[0], //username
                                                                data[1], //category
                                                                data[2], //head
                                                                data[3], //status
                                                                Integer.parseInt(data[4]), //vote
                                                                data[5], // detail
                                                                "", // imagePath
                                                                data[6]); //time
                    //add request
                    requestList.addRequest(dataRequest);

            }
            buffer.close();

            //read_traffic
            file = new File(trafficPath);
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);
            while (( request = buffer.readLine()) != null){
                String[] data = request.split(",");
                    modelRequest dataRequest = new modelRequest(data[0], //username
                            data[1], //category
                            data[2], //head
                            data[3], //status
                            Integer.parseInt(data[4]), //vote
                            data[6], //detail     //data[5] = location
                            data[7], //image
                            data[8]); //time
                    //add request
                    requestList.addRequest(dataRequest);
            }
            buffer.close();

            return requestList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
                if (data[0].equals(name)){
                    modelRequest dataRequest = new modelRequest(data[2],data[1],data[9],data[3],Integer.parseInt(data[4]));
                    dataRequest.setRequestDetail(data[7]);
                    dataRequest.setManageDetail(data[12]);
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
                    modelRequest dataRequest = new modelRequest(data[2],data[1],data[7],data[3],Integer.parseInt(data[4]));
                    dataRequest.setRequestDetail(data[6]);
                    dataRequest.setManageDetail(data[11]);
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
                    modelRequest dataRequest = new modelRequest(data[2],data[1],data[9],data[3],Integer.parseInt(data[4]));
                    dataRequest.setRequestDetail(data[8]);
                    dataRequest.setManageDetail(data[12]);
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
                    modelRequest dataRequest = new modelRequest(data[2],data[1],data[7],data[3],Integer.parseInt(data[4]));
                    dataRequest.setRequestDetail(data[5]);
                    dataRequest.setManageDetail(data[10]);
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
                    modelRequest dataRequest = new modelRequest(data[2],data[1],data[9],data[3],Integer.parseInt(data[4]));
                    dataRequest.setRequestDetail(data[7]);
                    dataRequest.setManageDetail(data[12]);
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
}
