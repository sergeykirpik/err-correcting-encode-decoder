package correcter;

public class BitArray {

    private final byte[] data;

    public BitArray(byte[] data) {
        this.data = data;
    }

    public int getBit(int index) {
        int bitIndexInByte = index % 8;
        int mask = 1 << (7 - bitIndexInByte);
        int byteIndex = index / 8;
        if (byteIndex > data.length - 1) {
            return 0;
        }
        if ((data[byteIndex] & mask) == 0) {
            return 0;
        }
        return 1;
    }

    public void setBit(int index, int bit) {
        int bitIndexInByte = index % 8;
        int mask = bit << (7 - bitIndexInByte);
        int byteIndex = index / 8;
        if (byteIndex > data.length - 1) {
            return;
        }
        data[byteIndex] |= mask;
    }

    public byte[] getData() {
        return data;
    }
}
