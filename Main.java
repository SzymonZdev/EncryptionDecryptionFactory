package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        new ProcessorCreator(args).processor().process().printConverted();
    }
}