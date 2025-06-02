package unl.dance.base.controller.practica3.parte1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QuickSort {

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

    public void quickSort(Integer[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Integer[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public void PrintResult(Integer[] arreglo, String titulo) {
        System.out.println(titulo + ":");
        for (int i = 0; i < arreglo.length; i++) {
            System.out.print(arreglo[i]);
            if (i < arreglo.length - 1) {
                System.out.print(", ");
            }
        }
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
            return;
        }

        Long InitialTime = System.nanoTime();

        quickSort(arreglo, 0, arreglo.length - 1);

        Long FinalTime = System.nanoTime();
        Long Duration = FinalTime - InitialTime;

        PrintResult(arreglo, "Arreglo ordenado");
        System.out.println("\nTiempo de ejecución QuickSort: " + Duration + " ns");
    }

    // Método main
    public static void main(String[] args) {
        QuickSort app = new QuickSort();
        app.DataProcess();
    }
}
