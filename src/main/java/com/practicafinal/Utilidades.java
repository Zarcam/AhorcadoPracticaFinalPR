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

    public static void reemplazarLinea(File archivo, String textoAntiguo, String textoNuevo){
        try {
            Scanner lector = new Scanner(archivo);
            System.out.println(archivo.getPath());
            ArrayList<String> lineas = new ArrayList<>();
            System.out.println("Furulas");

            while(lector.hasNext()){
                lineas.add(lector.nextLine());
            }

            lector.close();

            for(int i = 0; i < lineas.size(); i++){
                System.out.println(i);
                if(lineas.get(i).matches(textoAntiguo+".*")){
                    lineas.set(i, textoNuevo);
                    System.out.println(lineas.get(i));
                }
            }

            FileWriter writer = new FileWriter(archivo);

            for(String i : lineas){
                writer.write(i);
                writer.write("\n");
            }

            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            System.out.println("Cosa rara");
        }
    }
}
