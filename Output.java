package encryptdecrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Output {
    String converted;
    String fileOutput;

    public Output(String converted, String fileOutput) {
        this.converted = converted;
        this.fileOutput = fileOutput;
    }

    public void printConverted() {
        if (fileOutput.equals("")) {
            System.out.println(converted);
        } else {
            writeFile();
        }
    }

    private void writeFile() {
        System.out.println(converted);
        Path filePath = Path.of(fileOutput);
        try {
            Files.writeString(filePath, converted);
        } catch (IOException e) {
            System.out.println("Error: something wrong with file passed. (converted)");
        }
    }
}
