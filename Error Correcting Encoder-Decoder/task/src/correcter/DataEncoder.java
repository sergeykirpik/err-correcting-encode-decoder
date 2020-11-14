package correcter;

public class DataEncoder {

    public byte[] encode(byte[] data) {
        BitArray bitArray = new BitArray(data);

        byte[] encoded = new byte[data.length * 2];

        int[] d = new int[9];
        for (int i = 0; i < encoded.length; i++) {
            d[3] = bitArray.getBit(4 * i);
            d[5] = bitArray.getBit(4 * i + 1);
            d[6] = bitArray.getBit(4 * i + 2);
            d[7] = bitArray.getBit(4 * i + 3);

            d[1] = d[3] ^ d[5] ^ d[7];
            d[2] = d[3] ^ d[6] ^ d[7];
            d[4] = d[5] ^ d[6] ^ d[7];
            d[8] = 0;

            for (int bit = 1; bit <= 8; bit++) {
                d[bit] <<= 8 - bit;
                encoded[i] |= d[bit];
            }
        }
        return encoded;
    }
}
