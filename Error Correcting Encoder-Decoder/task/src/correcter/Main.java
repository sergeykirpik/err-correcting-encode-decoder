package correcter;

import java.io.*;

import static correcter.DataViewer.*;

public class Main {

    private static final String SEND_FILE = "send.txt";
    private static final String ENCODED_FILE = "encoded.txt";
    private static final String RECEIVED_FILE = "received.txt";
    private static final String DECODED_FILE = "decoded.txt";

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static final DataEncoder encoder = new DataEncoder();
    private static final DataErrorGenerator errorGen = new DataErrorGenerator();
    private static final DataDecoder decoder = new DataDecoder();

    public static void main(String[] args) throws IOException {
        //initSendFile();
        System.out.print("Write a mode: ");
        String mode = reader.readLine();
        switch (mode) {
            case "encode":
                encode();
                break;
            case "send":
                send();
                break;
            case "decode":
                decode();
                break;
        }
    }

    static void encode() {
        byte[] sendFileData = readFile(SEND_FILE);
        System.out.println("\nsend.txt:");
        System.out.printf("text view: %s\n", textView(sendFileData));
        System.out.printf("hex view: %s\n", hexView(sendFileData));
        System.out.printf("bin view: %s\n", binView(sendFileData));

        byte[] encodedData = encoder.encode(sendFileData);

        System.out.println("\nencoded.txt:");
        System.out.printf("expand: %s\n", hammingExpandedView(encodedData));
        System.out.printf("parity: %s\n", binView(encodedData));
        System.out.printf("hex view: %s\n", hexView(encodedData));

        writeFile(ENCODED_FILE, encodedData);
    }

    static void send() {
        byte[] encodedFileData = readFile(ENCODED_FILE);
        System.out.println("\nencoded.txt:");
        System.out.printf("hex view: %s\n", hexView(encodedFileData));
        System.out.printf("bin view: %s\n", binView(encodedFileData));

        byte[] receivedData = errorGen.generate(encodedFileData);

        System.out.println("\nreceived.txt:");
        System.out.printf("bin view: %s\n", binView(receivedData));
        System.out.printf("hex view: %s\n", hexView(receivedData));

        writeFile(RECEIVED_FILE, receivedData);
    }

    static void decode() {
        byte[] receivedData = readFile(RECEIVED_FILE);
        System.out.println("\nreceived.txt:");
        System.out.printf("hex view: %s\n", hexView(receivedData));
        System.out.printf("bin view: %s\n", binView(receivedData));

        byte[] correctedData = decoder.correct(receivedData);
        byte[] decodedData = decoder.decode(receivedData);

        System.out.println("\ndecoded.txt:");
        System.out.printf("correct: %s\n", binView(correctedData));
        System.out.printf("decode: %s\n", binView(decodedData));
        System.out.printf("hex view: %s\n", hexView(decodedData));
        System.out.printf("text view: %s\n", textView(decodedData));

        writeFile(DECODED_FILE, decodedData);
    }

    private static byte[] readFile(String filename) {
        byte[] data = new byte[0];
        try (InputStream fileInputStream = new FileInputStream(filename)) {
            data = fileInputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static void writeFile(String filename, byte[] data) {
        File file = new File(filename);
        try (OutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initSendFile() throws IOException {
        File sendFile = new File(SEND_FILE);
        try(FileWriter fileWriter = new FileWriter(sendFile)) {
            fileWriter.write("Test");
        };
    }

}


