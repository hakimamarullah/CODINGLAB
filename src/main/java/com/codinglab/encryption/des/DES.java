package com.codinglab.encryption.des;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DES {
    Map<Character, String> hexToBinMap = new HashMap<>();
    Map<String, String> binToHexMap = new HashMap<>();
    int[] permutationKey = DESTable.getPC1Table();
    int[] ip = DESTable.getInitialPermutationTable();
    int[] pc2 = DESTable.getPC2Table();
    int[] shift = DESTable.getShift();
    int[] expandRTable = DESTable.getExpansionPermutationTable();
    int[] permutationFunctionTable = DESTable.getPermutationFunctionTable();

    private void buildMap() {
        hexToBinMap.put('0', "0000");
        hexToBinMap.put('1', "0001");
        hexToBinMap.put('2', "0010");
        hexToBinMap.put('3', "0011");
        hexToBinMap.put('4', "0100");
        hexToBinMap.put('5', "0101");
        hexToBinMap.put('6', "0110");
        hexToBinMap.put('7', "0111");
        hexToBinMap.put('8', "1000");
        hexToBinMap.put('9', "1001");
        hexToBinMap.put('A', "1010");
        hexToBinMap.put('B', "1011");
        hexToBinMap.put('C', "1100");
        hexToBinMap.put('D', "1101");
        hexToBinMap.put('E', "1110");
        hexToBinMap.put('F', "1111");
    }

    private void buildMapBin(){
        binToHexMap.put("0000","0");
        binToHexMap.put("0001","1");
        binToHexMap.put("0010","2");
        binToHexMap.put("0011","3");
        binToHexMap.put("0100","4");
        binToHexMap.put("0101","5");
        binToHexMap.put("0110","6");
        binToHexMap.put("0111","7");
        binToHexMap.put("1000","8");
        binToHexMap.put("1001","9");
        binToHexMap.put("1010","A");
        binToHexMap.put("1011","B");
        binToHexMap.put("1100","C");
        binToHexMap.put("1101","D");
        binToHexMap.put("1110","E");
        binToHexMap.put("1111","F");
    }

    public String permutePC1(String bin){
        char[] binX = new char[65];
        char[] permuted = new char[65];
        char[] in = bin.toCharArray();
        permuted[0] = 'X';
        for(int i=1; i<permuted.length; i++){
            permuted[i] = '0';
        }
        binX[0] = 'X';
        for(int i=0; i<in.length; i++){
            binX[i+1] = in[i];
        }

        for(int i=0; i<this.permutationKey.length; i++){
            permuted[i+1] = binX[permutationKey[i]];
        }

        return Stream.of(Arrays.copyOfRange(permuted,1,57))
                .limit(7)
                .map(String::new)
                .collect(Collectors.joining());
    }

    public String permutePC2(String bin){
        char[] binX = new char[57];
        char[] permuted = new char[57];
        char[] in = bin.toCharArray();
        permuted[0] = 'X';
        for(int i=1; i<permuted.length; i++){
            permuted[i] = '0';
        }
        binX[0] = 'X';
        for(int i=0; i<in.length; i++){
            binX[i+1] = in[i];
        }

        for(int i=0; i<this.pc2.length; i++){
            permuted[i+1] = binX[pc2[i]];
        }

        return Stream.of(Arrays.copyOfRange(permuted,1,49))
                .limit(7)
                .map(String::new)
                .collect(Collectors.joining());
    }

    public String[][] getSixteenSubKeys(String bin) {
        String[] CnDn = divide(bin).split(" ");
        String[][] subKeys = new String[16][2];

        subKeys[0][0] = rotateLeft(CnDn[0], shift[0]);
        subKeys[1][1] = rotateLeft(CnDn[1], shift[0]);

        for (int subn = 1; subn < shift.length; subn++) {
            subKeys[subn][0] = rotateLeft(subKeys[subn][0], shift[subn]);
            subKeys[subn][1] = rotateLeft(subKeys[subn][1], shift[subn]);
        }
        return subKeys;
    }

    public String expandR(String bin) {
        String res = "";
        for (int bit : expandRTable) {
            res += bin.charAt(bit - 1);
        }
        return res;

    }

    public String finalStageFPermutation(String bin) {
        String res = "";
        for (int bit : permutationFunctionTable) {
            res += bin.charAt(bit - 1);
        }
        return res;

    }

    public String rotateLeft(String bin, int amount) {
        return bin.substring(amount % bin.length()) + bin.substring(0, amount % bin.length());
    }

    public String initialPermutation(String bin) {
        char[] binX = new char[65];
        char[] permuted = new char[65];
        char[] in = bin.toCharArray();
        permuted[0] = 'X';
        for(int i=1; i<permuted.length; i++){
            permuted[i] = '0';
        }
        binX[0] = 'X';
        System.arraycopy(in, 0, binX, 1, in.length);

        for(int i=0; i<this.ip.length; i++){
            permuted[i+1] = binX[ip[i]];
        }

        return binToHex(Stream.of(Arrays.copyOfRange(permuted,1,65))
                .map(String::new)
                .collect(Collectors.joining()));
    }

    public String divide(String bin){

        return bin.substring(0,bin.length()/2) +" " + bin.substring(bin.length()/2);
    }

    public String binToHex(String bin){
        buildMapBin();
        StringBuilder builder = new StringBuilder();
        ArrayList<String> temp = tokenizeBin(bin, 4);

        for(String bytes: temp){
            builder.append(binToHexMap.get(bytes));
        }
        return builder.toString();
    }

    public ArrayList<String> tokenizeBin(String bin, int size) {
        ArrayList<String> out = new ArrayList<>();
        for (int i = 0; i < bin.length(); i += size) {
            out.add(bin.substring(i, i + size));
        }
        return out;
    }

    public int sBox(String bin, int sboxNum) {
        int row = Integer.parseInt(String.valueOf(bin.charAt(0))
                + bin.charAt(bin.length() - 1), 2);
        int col = Integer.parseInt(bin.substring(1, bin.length() - 1), 2);
        return sBoxChooser(sboxNum)[row][col];

    }

    public String transformRXORKeySBox(String bin) {
        List<String> token = tokenizeBin(bin, 6);
        String result = "";
        for (int i = 0; i < 8; i++) {
            result += Integer.toHexString(sBox(token.get(i), i + 1));
        }
        return result;
    }

    public int[][] sBoxChooser(int num) {
        return switch (num) {
            case 1 -> DESTable.getS1();
            case 2 -> DESTable.getS2();
            case 3 -> DESTable.getS3();
            case 4 -> DESTable.getS4();
            case 5 -> DESTable.getS5();
            case 6 -> DESTable.getS6();
            case 7 -> DESTable.getS7();
            case 8 -> DESTable.getS8();
            default -> new int[][]{};
        };
    }


    public String hexToBin(String hex) {
        buildMap();
        hex = hex.toUpperCase(Locale.ROOT);
        StringBuilder bin = new StringBuilder();
        char[] hexTemp = hex.toCharArray();

        for (char bit : hexTemp) {
            bin.append(hexToBinMap.get(bit));
        }
        return bin.toString();
    }
}
