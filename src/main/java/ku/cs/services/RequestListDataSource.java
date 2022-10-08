package ku.cs.services;

import ku.cs.models.modelRequest;

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

//    public void writefile_request(modelRequest request){
//        String filePath = directoryName + File.separator + fileName;
//        File file = new File(filePath);
//
//        FileWriter writer = null;
//        BufferedWriter buffer = null;
//
//        try {
//            writer = new FileWriter(file,true);
//            buffer = new BufferedWriter(writer);
//            buffer.append(request.getName() + ","
//                    +request.getCategory() + ","
//                    +request.getSubject() + ","
//                    +request.getName() + ","
//                    +request.getFaculty() + ","
//                    +request.getDepartment() + ","
//                    +request.getTelephone() + ","
//                    +request.getStatus());
//            buffer.newLine();
//            buffer.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
