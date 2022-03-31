package com.codinglab.encryption.aes;

import java.util.ArrayList;
import java.util.Locale;

/**
 * This class can mix columns used in AES Encryption / Decryption
 *
 * @author Andrew Thursby, Felix Behrendt
 */
public class MixColumns {

    private static final byte[][] matrix = {{0x03, 0x0b}, {0x01, 0x0d},
            {0x01, 0x09}, {0x02, 0x0e}};

    private final byte a, b, c, d;

    public static void main(String[] args) {
        MixColumns aes = new MixColumns(false);
        byte[][] input = {{(byte) 0xdb, 0x47, (byte) 0xa2, (byte) 0xf0},
                {(byte) 0x13, 0x63, (byte) 0xc5, (byte) 0xf2},
                {0x53, 0x7c, (byte) 0xf0, (byte) 0xab},
                {(byte) 0x45, (byte) 0xaf, 0x76, 0x76}
        };

        for (byte[] x : aes.mixColumns(input)) {
            for (byte y : x) {
                System.out.print(String.format("%02X", y) + " ");
            }
            System.out.println();
        }
        System.out.println(tokenizer("1f7331f6ef768be725236c99953e9657".toUpperCase(Locale.ROOT)));

    }

    static ArrayList<String> tokenizer(String hex) {
        ArrayList<String> out = new ArrayList<>();
        String[][] out2 = new String[4][4];
        for (int i = 0; i < hex.length(); i += 2) {
            out.add(hex.substring(i, i + 2));
        }


        int d = 0;
        for (int i = 0; i < out2.length; i++) {
            for (int j = 0; j < out2[i].length; j++) {
                out2[j][i] = out.get(d);
                d++;
            }
        }
        System.out.println("TOKENIZER");
        for (String[] x : out2) {
            for (String y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }


        return out;
    }

    public MixColumns(boolean decrypt) {
        int arrayIndex = decrypt ? 1 : 0;
        a = matrix[0][arrayIndex];
        b = matrix[1][arrayIndex];
        c = matrix[2][arrayIndex];
        d = matrix[3][arrayIndex];
    }

    // Multiplies two bytes in garlois field 2^8
    private byte multiply(byte a, byte b) {
        byte returnValue = 0;
        byte temp = 0;
        while (a != 0) {
            if ((a & 1) != 0)
                returnValue = (byte) (returnValue ^ b);
            temp = (byte) (b & 0x80);
            b = (byte) (b << 1);
            if (temp != 0)
                b = (byte) (b ^ 0x1b);
            a = (byte) ((a & 0xff) >> 1);
        }
        return returnValue;
    }

    public byte[][] mixColumns(byte[][] input) {
        int[] temp = new int[4];
        for (int i = 0; i < 4; i++) {
            temp[0] = multiply(d, input[0][i]) ^ multiply(a, input[1][i])
                    ^ multiply(b, input[2][i]) ^ multiply(c, input[3][i]);
            temp[1] = multiply(c, input[0][i]) ^ multiply(d, input[1][i])
                    ^ multiply(a, input[2][i]) ^ multiply(b, input[3][i]);
            temp[2] = multiply(b, input[0][i]) ^ multiply(c, input[1][i])
                    ^ multiply(d, input[2][i]) ^ multiply(a, input[3][i]);
            temp[3] = multiply(a, input[0][i]) ^ multiply(b, input[1][i])
                    ^ multiply(c, input[2][i]) ^ multiply(d, input[3][i]);
            for (int j = 0; j < 4; j++)
                input[j][i] = (byte) (temp[j]);
        }
        return input;
    }
}