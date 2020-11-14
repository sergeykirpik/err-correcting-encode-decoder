package correcter;

public class MutableByte {

    private byte value;

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public void setValue(int value) {
        setValue((byte) value);
    }

    public int getBit(int index) {
        int bit = value & (1 << 7 - index);
        return bit == 0 ? 0 : 1;
    }

    public void setBit(int index) {
        setBit(index, 1);
    }

    public void setBit(int index, int bit) {
        value |= (bit << 7 - index);
    }

    public void clearBit(int index) {
        value &= (1 << 7 - index);
    }

    public void toggleBit(int index) {
        value ^= (1 << 7 - index);
    }

    public void copyBitsToArray(int[] d, int fromIndex) {
        for (int n = fromIndex; n < d.length; n++) {
            d[n] = getBit(n - fromIndex);
        }
    }
}
