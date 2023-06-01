package com.practicafinal;

public class Utilidades {
    public static int aleatorioEntre(int MIN, int MAX){
        return (int) (MIN + Math.random() * (MAX - MIN + 1));
    }

    public static boolean arrayContiene(char letra, char[] array){
        for(char i : array){
            if(i == letra){
                return true;
            }
        }
        return false;
    }

    public static int[] encontrarTodosLosIndicesDe(char letra, char[] array){
        int totOcurrencias = 0;
        for(char i : array){
            if(i == letra){
                totOcurrencias++;
            }
        }

        int[] indices = new int[totOcurrencias];

        int j = 0;
        for(int i = 0; i < array.length; i++){
            if(array[i] == letra){
                indices[j] = i;
                j++;
            }
        }

        return indices;
    }
}
