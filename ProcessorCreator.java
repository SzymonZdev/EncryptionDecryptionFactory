package encryptdecrypt;

public class ProcessorCreator {
    String mode = "enc";
    int key = 0;
    String data = "";
    String inFile = "";
    String outFile = "";
    String algorithm = "shift";
    boolean hasFile = false;


    public ProcessorCreator(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode" -> this.mode = args[++i];
                case "-key" -> this.key = Integer.parseInt(args[++i]);
                case "-data" -> this.data = args[++i];
                case "-in" -> this.inFile = args[++i];
                case "-out" -> this.outFile = args[++i];
                case "-alg" -> this.algorithm = args[++i];
            }
        }
        if (this.inFile.length() > 1 && this.data.equals("")) {
            this.hasFile = true;
        }
    }


    public ProcessInput processor() {
        if (algorithm.equals("shift")) {
            return new ProcessShift(mode, hasFile, key, data, inFile, outFile);
        } else {
            return new ProcessUnicode(mode, hasFile, key, data, inFile, outFile);
        }
    }
}
