package ku.cs.services;

import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

public class ImageDataSource {

    public String chooseImage(String image_directory){
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(new File(System.getProperty("user.dir")));
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("images JPG PNG", "*.png", "*.jpg", "*.jpeg"));
        File file = chooser.showOpenDialog(new Stage());
        if (file != null){
            try {
                File destDir = new File("image" + System.getProperty("file.separator") + image_directory);
                if (!destDir.exists()) destDir.mkdirs();
                String[] fileSplit = file.getName().split("\\.");
                String filename = LocalDate.now() + "_"+System.currentTimeMillis() + "."
                        + fileSplit[fileSplit.length - 1];
                Path pic_target = FileSystems.getDefault().getPath(
                        destDir.getAbsolutePath()+System.getProperty("file.separator")+filename);
                Files.copy(file.toPath(), pic_target, StandardCopyOption.REPLACE_EXISTING );
                return filename;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
