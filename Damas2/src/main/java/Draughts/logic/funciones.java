package Draughts.logic;

import java.util.Scanner;

public class funciones {
    public static void main(String[] args) {
    int num[] = {3,4,6,2,0,5,4,7,3,6,1};
    System.out.println(formating(num));
    }
    public static String formating(int input[]){
        char [] output = {'(','+','x','x',')','x','x','x','-','x','x','x','-','x','x','x'};
        String output2 = "";
        for(int i = 0; i < output.length; i++){
            for(int j = 0; j < input.length; j++){
                if(output[i] == 'x') {
                    output[i] = Integer.toString(input[j]).charAt(j);
                    output2 += output[i];
                }
            }
        }
        return output2;
    }
}
