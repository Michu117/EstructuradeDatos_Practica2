package unl.dance.base.controller.practica3.parte1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Shell {

    private BufferedReader FileReadM(String fileName) throws Exception {
        InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
        if (input == null) {
            throw new Exception("Archivo '" + fileName + "' no encontrado en recursos");
        }
        return new BufferedReader(new InputStreamReader(input));
    }

    public Integer countLin() {
        Integer cantidad = 0;
        try (BufferedReader br = FileReadM("data.txt")) {
            while (br.readLine() != null) {
                cantidad++;
            }
        } catch (Exception e) {
            System.out.println("Archivo no encontrado " + e.getMessage());
        }
        return cantidad;
    }

    // Algoritmo shell
    public static Integer[] shell(Integer[] arreglo){
        int inta, i, aux;
        boolean band;
        inta = arreglo.length;
        while(inta > 0){
            inta = inta / 2;
            band = true;
            while(band){
                band = false;
                i = 0;
                while ((i+inta) <=arreglo.length-1){
                    if (arreglo[i] > arreglo[i + inta]){
                        aux = arreglo[i];
                        arreglo[i] = arreglo[i+inta];
                        arreglo[i+inta] = aux;
                        band = true;
                    }
                    i = i +1;
                }
            }
        }
        return arreglo;
    }

    public void DataProcess() {
        Integer cantidadLineas = countLin();
        Integer[] arreglo = new Integer[cantidadLineas];
        int i = 0;

        try (BufferedReader br = FileReadM("data.txt")) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    arreglo[i] = Integer.parseInt(linea.trim());
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        System.out.println("Arreglo original:");
        printArray(arreglo);

        long startTime = System.nanoTime();

        shell(arreglo);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.println("\nArreglo ordenado:");
        printArray(arreglo);

        System.out.println("\nTiempo de ejecución del Shell: " + duration + " ns");
    }

    public void printArray(Integer[] arreglo) {
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i]);
            if (i < arreglo.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Shell app = new Shell();
        app.DataProcess();
    }
}
