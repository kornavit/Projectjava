package ku.cs.services;

import ku.cs.models.modelReport;
import ku.cs.models.modelRequest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportDataSource {
    private String directoryName;
    private String fileName;

    public ReportDataSource(String directoryName, String fileName){
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

    public void writeFilePreBan(modelReport report){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
            buffer.append(report.getRealName()+ ","
                    +report.getReportDetail()+ ",");
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void writeFileDeleteDetail(modelReport report){
        String filePath = directoryName + File.separator + fileName;
        File file = new File(filePath);

        FileWriter writer = null;
        BufferedWriter buffer = null;

        try {
            writer = new FileWriter(file,true);
            buffer = new BufferedWriter(writer);
            buffer.append(report.getRealName()+ ","
                    +report.getHead()+ ","
                    +report.getCategory()+ ","
                    +report.getReportDetail()+ ",");
            buffer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
