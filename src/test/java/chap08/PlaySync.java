package chap08;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlaySync {

    private String filePath = "/Users/zidol/Desktop/data/pay/cp001.csv";

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void sync(String filePath) throws IOException {
        Path path = Paths.get(filePath);
    }
}
