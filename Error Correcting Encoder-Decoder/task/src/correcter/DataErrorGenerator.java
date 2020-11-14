package correcter;

import java.util.Arrays;
import java.util.Random;

public class DataErrorGenerator {

    private static final Random randomGen = new Random();

    public byte[] generate(byte[] original) {
        byte[] corrupted = Arrays.copyOf(original, original.length);
        for (int i = 0; i < corrupted.length; i++) {
            int toggleBit = 1 << randomGen.nextInt(8);
            corrupted[i] ^= toggleBit;
        }
        return corrupted;
    }
}
