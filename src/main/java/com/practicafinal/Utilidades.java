package com.practicafinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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

    public static void reemplazarLinea(File archivo, String regex, String textoNuevo){
        try {
            Scanner lector = new Scanner(archivo);
            ArrayList<String> lineas = new ArrayList<>();

            while(lector.hasNext()){
                lineas.add(lector.nextLine());
            }

            lector.close();

            for(int i = 0; i < lineas.size(); i++){
                if(lineas.get(i).matches(regex)){
                    lineas.set(i, textoNuevo);
                }
            }

            FileWriter writer = new FileWriter(archivo);

            for(String i : lineas){
                writer.write(i);
                if(!i.isBlank()) {
                    writer.write("\n");
                }
            }

            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            System.out.println("Cosa rara");
        }
    }
}
