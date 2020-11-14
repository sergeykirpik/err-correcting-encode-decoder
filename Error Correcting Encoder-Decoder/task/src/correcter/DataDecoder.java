package correcter;

import java.util.Arrays;

public class DataDecoder {

    public byte[] correct(byte[] receivedData) {
        MutableByte receivedByte = new MutableByte();
        MutableByte correctedByte = new MutableByte();
        byte[] correctedData = Arrays.copyOf(receivedData, receivedData.length);

        int[] d = new int[9];
        for (int i = 0; i < correctedData.length; i++) {
            receivedByte.setValue(receivedData[i]);
            correctedByte.setValue(correctedData[i]);
            receivedByte.copyBitsToArray(d, 1);
            int p1 = d[3] ^ d[5] ^ d[7];
            int p2 = d[3] ^ d[6] ^ d[7];
            int p4 = d[5] ^ d[6] ^ d[7];

            int errBit = 0;
            errBit += p1 != d[1] ? 1 : 0;
            errBit += p2 != d[2] ? 2 : 0;
            errBit += p4 != d[4] ? 4 : 0;

            if (errBit != 0) {
                correctedByte.toggleBit(errBit - 1);
            }
            if (correctedByte.getBit(7) != 0) {
                correctedByte.toggleBit(7);
            }
            correctedData[i] = correctedByte.getValue();
        }
        return correctedData;
    }

    public byte[] decode(byte[] receivedData) {
        byte[] encoded = correct(receivedData);
        BitArray decoded = new BitArray(new byte[encoded.length / 2]);
        MutableByte encodedByte = new MutableByte();

        int bitIndex = 0;
        int[] d = new int[9];
        for (byte eb: encoded) {
            encodedByte.setValue(eb);
            encodedByte.copyBitsToArray(d, 1);
            decoded.setBit(bitIndex++, d[3]);
            decoded.setBit(bitIndex++, d[5]);
            decoded.setBit(bitIndex++, d[6]);
            decoded.setBit(bitIndex++, d[7]);
        }
        return decoded.getData();
    }
}
