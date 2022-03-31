package com.codinglab.encryption.des;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DES {
    Map<Character, String> hexToBinMap = new HashMap<>();
    int[] permutationKey = DESTable.getPC1Table();
    int[] ip = DESTable.getInitialPermutationTable();

    private void buildMap(){
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

    public String initialPermutation(String bin){
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

        return Stream.of(Arrays.copyOfRange(permuted,1,65))
                .map(String::new)
                .collect(Collectors.joining());
    }

    public String divide(String bin){

        return bin.substring(0,bin.length()/2) +" " + bin.substring(bin.length()/2);
    }

    public String hexToBin(String hex) {
        buildMap();
        StringBuilder bin = new StringBuilder();
        char[] hexTemp = hex.toCharArray();

        for(char bit: hexTemp){
            bin.append(hexToBinMap.get(bit));
        }
        return bin.toString();
    }
}
