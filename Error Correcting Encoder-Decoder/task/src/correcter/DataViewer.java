package correcter;

public class DataViewer {

    public static String textView(byte[] data) {
        return new String(data);
    }

    public static String hexView(byte[] data) {
        StringBuilder hex = new StringBuilder(data.length * 2);
        for (byte b: data) {
            String hexString = Integer.toHexString(b & 0xFF);
            if (hexString.length() < 2) {
                hex.append('0');
            }
            hex.append(hexString).append(" ");
        }
        return hex.toString();
    }

    public static String binView(byte[] data) {
        StringBuilder bin = new StringBuilder(data.length * 8);
        for (byte b: data) {
            String binaryString = padWithZero(Integer.toBinaryString(b & 0xFF));
            bin.append(binaryString).append(" ");
        }
        return bin.toString();
    }

    public static String expandedView(byte[] data) {
        StringBuilder bin = new StringBuilder();
        for (byte b: data) {
            String binaryString = padWithZero(Integer.toBinaryString(b & 0xFF));
            bin.append(binaryString, 0, binaryString.length() - 2);
            bin.append(".. ");
        }
        return bin.toString();
    }

    public static String hammingExpandedView(byte[] data) {
        StringBuilder bin = new StringBuilder();
        StringBuilder binaryString = new StringBuilder();
        for (byte b: data) {
            binaryString.setLength(0);
            binaryString.append(padWithZero(Integer.toBinaryString(b & 0xFF)));
            binaryString.setCharAt(0, '.');
            binaryString.setCharAt(1, '.');
            binaryString.setCharAt(3, '.');
            binaryString.setCharAt(7, '.');
            bin.append(binaryString).append(' ');
        }
        return bin.toString();
    }

    private static String padWithZero(String str) {
        return String.format("%1$8s", str).replace(' ', '0');
    }
}
