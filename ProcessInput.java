package encryptdecrypt;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class ProcessInput {
    String mode;
    boolean hasFile;
    int key;
    String data;
    String inFile;
    String outFile;
    String converted = "";

    public ProcessInput(String mode, boolean hasFile, int key, String data, String inFile, String outFile) {
        this.mode = mode;
        this.hasFile = hasFile;
        this.key = key;
        this.data = data;
        this.inFile = inFile;
        this.outFile = outFile;
    }

    String readFile(String inFile){
        Path filePath = Path.of(inFile);
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            System.out.println("Error: something wrong with file passed.");
        }
        return null;
    }

    Output process() {
        return new Output(converted, outFile);
    }
}

class ProcessShift extends ProcessInput {
    public ProcessShift(String mode, boolean hasFile, int key, String data, String inFile, String outFile) {
        super(mode, hasFile, key, data, inFile, outFile);
    }

    @Override
    Output process() {
        if (mode.equals("enc")) {
            if (hasFile) {
                converted = encodeShift(readFile(inFile), key);
            } else {
                converted = encodeShift(data, key);
            }
        } else {
            if (hasFile) {
                converted = decodeShift(readFile(inFile), key);
            } else {
                converted = decodeShift(data, key);
            }
        }
        return super.process();
    }

    public String encodeShift(String toConvert, int key) {
        StringBuilder convertedString = new StringBuilder();
        for (Character c : toConvert.toCharArray()
        ) {
            int newCharVal;
            if ((c > 64 && c < 91)) {
                newCharVal = c + key;
                if (newCharVal > 91) {
                    newCharVal = 64 + newCharVal - 90;
                }
                convertedString.append((char)(newCharVal));
            } else if ((c > 96 && c < 123)) {
                newCharVal = c + key;
                if (newCharVal > 123) {
                    newCharVal = 96 + newCharVal - 122;
                }
                convertedString.append((char)(newCharVal));
            } else {
                convertedString.append(c);
            }
        }
        return convertedString.toString();
    }

    public String decodeShift(String toConvert, int key) {
        StringBuilder convertedString = new StringBuilder();
        for (Character c : toConvert.toCharArray()
        ) {
            int newCharVal;
            if ((c > 64 && c < 91)) {
                newCharVal = c - key;
                if (newCharVal < 64) {
                    newCharVal = 90 - (64 - newCharVal);
                }
                convertedString.append((char)(newCharVal));
            } else if ((c > 96 && c < 123)) {
                newCharVal = c - key;
                if (newCharVal < 96) {
                    newCharVal = 122 - (96 - newCharVal);
                }
                convertedString.append((char)(newCharVal));
            } else {
                convertedString.append(c);
            }
        }
        return convertedString.toString();
    }
}

class ProcessUnicode extends ProcessInput {
    public ProcessUnicode(String mode, boolean hasFile, int key, String data, String inFile, String outFile) {
        super(mode, hasFile, key, data, inFile, outFile);
    }

    @Override
    Output process() {
        if (mode.equals("enc")) {
            if (hasFile) {
                converted = encodeUnicode(readFile(inFile), key);
            } else {
                converted = encodeUnicode(data, key);
            }
        } else {
            if (hasFile) {
                converted = decodeUnicode(readFile(inFile), key);
            } else {
                converted = decodeUnicode(data, key);
            }
        }
        return super.process();
    }

    public String encodeUnicode(String toConvert, int key) {
        StringBuilder convertedString = new StringBuilder();
        for (Character c : toConvert.toCharArray()
        ) {
            convertedString.append((char)(c + key));
        }
        return convertedString.toString();
    }

    public String decodeUnicode(String toConvert, int key) {
        StringBuilder convertedString = new StringBuilder();
        for (Character c : toConvert.toCharArray()
        ) {
            convertedString.append((char)(c - key));
        }
        return convertedString.toString();
    }
}
