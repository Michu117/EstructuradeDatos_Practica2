package unl.dance.base.controller.practica3.parte1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import unl.dance.base.controller.data_struct.list.LinkedList;

public class QuickSort {

    private BufferedReader FileReadM(String fileName) throws Exception {
        InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
        if (input == null) {
            throw new Exception("Archivo '" + fileName + "' no encontrado en recursos");
        }
        return new BufferedReader(new InputStreamReader(input));
    }


    public void quickSort(LinkedList<Integer> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private int partition(LinkedList<Integer> list, int low, int high) {
        int pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (list.get(j) <= pivot) {
                i++;
                
                int temp = list.get(i);
                list.update(list.get(j), i);
                list.update(temp, j);
            }
        }
        int temp = list.get(i + 1);
        list.update(list.get(high), i + 1);
        list.update(temp, high);
        return i + 1;
    }

    public void PrintResult(LinkedList<Integer> list, String titulo) {
        System.out.println(titulo + ":");
        for (int i = 0; i < list.getLength(); i++) {
            System.out.print(list.get(i));
            if (i < list.getLength() - 1) {
                System.out.print(", ");
            }
        }
    }

    public void DataProcess() {
        LinkedList<Integer> list = new LinkedList<>();
        int i = 0;

        try (BufferedReader br = FileReadM("data.txt")) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    list.add(Integer.parseInt(linea.trim()));
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        Long InitialTime = System.nanoTime();

        quickSort(list, 0, list.getLength() - 1);

        Long FinalTime = System.nanoTime();
        Long Duration = FinalTime - InitialTime;

        PrintResult(list, "Lista ordenada");
        System.out.println("\nTiempo de ejecución QuickSort: " + Duration + " ns");
    }

    public static void main(String[] args) {
        QuickSort app = new QuickSort();
        app.DataProcess();
    }
}
