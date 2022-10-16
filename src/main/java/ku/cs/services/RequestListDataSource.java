package ku.cs.services;

import javafx.scene.layout.Pane;
import ku.cs.models.modelRegister;
import ku.cs.models.modelRegisterList;
import ku.cs.models.modelRequest;
import ku.cs.models.modelRequestList;
import ku.cs.models.request.modelBuilding;
import ku.cs.models.request.modelLearning;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class RequestListDataSource {
    private String directoryName;
    private String fileName;

    public RequestListDataSource(){}

    public RequestListDataSource(String directoryName) {
        this.directoryName = directoryName;
    }
//total_request
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
                            data[9]);//time
                dataRequest.setManageDetail(data[11]);
                dataRequest.setExtra("อุปกรณ์ที่ชำรุด : " + data[5] + "\n" + "สถานที่ : \n" + data[6]);
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
                            data[6], //detail
                            "", //imagePath
                            data[7]);  //time);
                dataRequest.setManageDetail(data[9]);
                dataRequest.setExtra("ประเภทการทำธุรกรรม : " + data[5]);
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
                    modelRequest dataRequest = new modelRequest(
                            data[0], //usename
                            data[1], //category
                            data[2], //head
                            data[3], //status
                            Integer.parseInt(data[4]),// vote
                            data[8], // detail data[5,6,7] = course,teacher,sec
                            "", //image
                            data[9]); //time
                dataRequest.setManageDetail(data[11]);
                dataRequest.setExtra("รายวิชา : " + data[5] + "\nอาจารย์ผู้คุมรายวิชา : " + data[6] +"\nหมู่ : "+ data[7]);
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
                    modelRequest dataRequest = new modelRequest(
                            data[0], //username
                            data[1], //category
                            data[2], //head
                            data[3], //status
                            Integer.parseInt(data[4]), //vote
                            data[5], // detail
                            "", // imagePath
                            data[6]); //time
                dataRequest.setManageDetail(data[8]);
                dataRequest.setExtra("ไม่มีในหมวดหมู่นี้");
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
                dataRequest.setManageDetail(data[11]);
                dataRequest.setExtra("สถานที่ : \n" + data[5]);
                    //add request
                    requestList.addRequest(dataRequest);
            }
            buffer.close();

            return requestList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//request
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
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
//                ยังไม่ดำเนินการ,0,ซอยจักรพันธ์,พอถึงเวลาเลิกเรียนของนักเรียนโรงเรียนสาธิตทำให้ผู้ปกตรองที่รับนักเรียนมากันเยอะจนรถติดมาถึง ซอยสุวรรณวาจกกสิกิจ,-,ยังไม่มีคนรับเรื่อง,,
                list.addRequest(request);
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

    public modelRequestList searchStatus(modelRequestList requestList,String status){
        modelRequestList requestStatus = new modelRequestList();
        for (modelRequest request : requestList.getAllRequest()){
            if (request.getStatus().equals(status)){
                requestStatus.addRequest(request);
            }
        }
        return requestStatus;
    }
    public void sortTime(modelRequestList requestList,String time) {
        TimeComparator timeComparator = new TimeComparator();
        if (time.equals("ล่าสุดไปเก่าสุด")) {
            timeComparator.setDescending(true);
            Collections.sort(requestList.getAllRequest(), timeComparator);

        } else {
            Collections.sort(requestList.getAllRequest(), timeComparator);
        }

    }

    public void sortVote(modelRequestList requestList, String vote){
        VoteComparator voteComparator = new VoteComparator();
        if (vote.equals("มากสุดไปน้อยสุด")){
            voteComparator.setDescending(true);
            Collections.sort(requestList.getAllRequest(), voteComparator);
        }else {
            Collections.sort(requestList.getAllRequest(), voteComparator);
        }
    }
    public modelRequestList searchMoreThan(modelRequestList requestList,Integer more){
        modelRequestList requestMorethan = new modelRequestList();
        for (modelRequest request : requestList.getAllRequest()){
            if (request.getVotePoint() > more){
                requestMorethan.addRequest(request);
            }
        }
        return requestMorethan;
    }

    public modelRequestList searchUntil(modelRequestList requestList, Integer until, Integer to){
        modelRequestList requestUntil = new modelRequestList();
        for (modelRequest request : requestList.getAllRequest()){
            if (request.getVotePoint() >= until && request.getVotePoint() <= to){
                requestUntil.addRequest(request);
            }
        }
        return requestUntil;

    }
    public modelRequestList sortCategory(modelRequestList requestList, String category){

        modelRequestList requestCategory = new modelRequestList();

        if (category.equals("การเรียนการสอน")){
            for (modelRequest request : requestList.getAllRequest()){
                if (request.getCategory().equals("learning")){
                    requestCategory.addRequest(request);
                }
            }
        } else if ((category.equals("อาคาร สถานที่และสิ่งอำนวยความสะดวก"))){
            for (modelRequest request : requestList.getAllRequest()){
                if (request.getCategory().equals("building")){
                    requestCategory.addRequest(request);
                }
            }
        } else if (category.equals("การจราจรในมหาวิทยาลัย")){
            for (modelRequest request : requestList.getAllRequest()){
                if (request.getCategory().equals("traffic")){
                    requestCategory.addRequest(request);
                }
            }
        } else if (category.equals("การเงินในมหาวิทยาลัย")){
            for (modelRequest request : requestList.getAllRequest()){
                if (request.getCategory().equals("finance")){
                    requestCategory.addRequest(request);
                }
            }
        }else if (category.equals("อื่นๆ")){
            for (modelRequest request : requestList.getAllRequest()){
                if (request.getCategory().equals("other")){
                    requestCategory.addRequest(request);
                }
            }
        }
        return requestCategory;
    }

    public void writeFileRequest(modelRequest request){
        String filePath = directoryName + File.separator + request.getCategory() + ".csv";
        File file = new File(filePath);

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
                        +request.getTime() + ",ยังไม่มีเจ้าหน้าที่รับเรื่อง,-,-"
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
                        +request.getTime() + ",ยังไม่มีเจ้าหน้าที่รับเรื่อง,-,-"
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
                        +request.getTime() + ",ยังไม่มีเจ้าหน้าที่รับเรื่อง,-,-"
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
                        +request.getTime() + ",ยังไม่มีเจ้าหน้าที่รับเรื่อง,-,-"
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
                        +request.getTime() + ",ยังไม่ได้รับเรื่องร้องเรียน,-,-"
                );
                buffer.newLine();
                buffer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // check from selected totalRequests
    public boolean checkLike(modelRequest request,String guest){
        String filePath = "data" + File.separator + "category" + File.separator + request.getCategory() + ".csv";
        File file = new File(filePath);

        FileReader reader = null;
        BufferedReader buffer = null;

        try{
            reader = new FileReader(file);
            buffer = new BufferedReader(reader);

            String vote = "";
            while( (vote = buffer.readLine()) != null){
                String[] data = vote.split(",");
                if (data[0].equals(request.getUsername()) && data[2].equals(request.getSubject())){
                    String[] userLike = data[data.length - 1].split("\\|");
                    for (String s : userLike){
                        if (s.equals(guest)){
                            return true;
                        }
                    }return false;
                }
            }return false;
        }catch (IOException e) {
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

    public int writeVote(modelRequestList requestList,modelRequest requestSelected,String guest,String photoLike){
        String filePath = "data" + File.separator + "category" +File.separator + requestSelected.getCategory() + ".csv";
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;
        try{
            writer = new FileWriter(file);
            buffer = new BufferedWriter(writer);
            if (photoLike.equals("unlike")){
                if (!checkLike(requestSelected,guest)){ // เอาไปลด
                    System.out.println("มีชื่อคนนี้ like แล้ว");
                    for (modelRequest request: requestList.getAllRequest()){
                        if (request.getUsername().equals(requestSelected.getUsername()) && request.getSubject().equals(requestSelected.getSubject())){
                            String[] data = request.getRequestDetail().split(","); // check มีคนนี้ไหมแล้วเอาไป ลดตัวของ request vote
                            data[1] = String.valueOf(Integer.parseInt(data[1]) - 1);
                            requestSelected.setVotePoint(Integer.parseInt(data[1]));
                            String userLike = "";
                            for (String s : data[data.length - 1].split("\\|")){
                                if (!s.equals(guest)){
                                    userLike += s + "|";
                                }
                            }
                            if (data[1].equals("0")){
                                data[data.length - 1] = "-";
                            }else{
                                data[data.length - 1] = userLike;
                            }
                            // String[]
                            String all = "";
                            for (String v : data){
                                all += v + ",";
                            }
                            request.setRequestDetail(all);
                        }
                        // writing

                        buffer.append(request.getUsername() + ","
                                + request.getCategory() + ","
                                + request.getSubject() + ","
                                + request.getRequestDetail());
                        buffer.newLine();
                    }
                }else{
                    for (modelRequest request : requestList.getAllRequest()){
                        // writing
                        buffer.append(request.getUsername() + ","
                                + request.getCategory() + ","
                                + request.getSubject() + ","
                                + request.getRequestDetail());
                        buffer.newLine();
                    }
                }
            }else{
                // like
                if (!checkLike(requestSelected, guest)){ // เอาไปบวก
                    System.out.println("คนนี้ยังไม่ได้ like");
                    for (modelRequest request: requestList.getAllRequest()){
                        if (request.getUsername().equals(requestSelected.getUsername()) && request.getSubject().equals(requestSelected.getSubject())){
                            String[] data = request.getRequestDetail().split(","); // check มีคนนี้ไหมแล้วเอาไป เพิ่มตัวของ request vote
                            if (data[1].equals("0")){
                                data[data.length - 1] = "";
                            }
                            data[1] = String.valueOf(Integer.parseInt(data[1]) + 1);
                            requestSelected.setVotePoint(Integer.parseInt(data[1]));
                            data[data.length - 1] += guest + "|";
                            // String[]

                            String all = "";
                            for (String v : data){
                                all += v + ",";
                            }
                            request.setRequestDetail(all);
                        }
                        // writing
                        buffer.append(request.getUsername() + ","
                                + request.getCategory() + ","
                                + request.getSubject() + ","
                                + request.getRequestDetail());
                        buffer.newLine();
                    }
                }else{
                    for (modelRequest request : requestList.getAllRequest()){
                        // writing
                        buffer.append(request.getUsername() + ","
                                + request.getCategory() + ","
                                + request.getSubject() + ","
                                + request.getRequestDetail());
                        buffer.newLine();
                    }
                }
            }
            buffer.close();
            return requestSelected.getVotePoint();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
