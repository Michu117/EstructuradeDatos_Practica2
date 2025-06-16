package unl.dance.base.controller.practica3.parte1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import unl.dance.base.controller.data_struct.list.LinkedList;

public class Shell {

    private BufferedReader FileReadM(String fileName) throws Exception {
        InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
        if (input == null) {
            throw new Exception("Archivo '" + fileName + "' no encontrado en recursos");
        }
        return new BufferedReader(new InputStreamReader(input));
    }


    public static void shell(LinkedList<Integer> list){
        int inta, i, aux;
        boolean band;
        inta = list.getLength();
        while(inta > 0){
            inta = inta / 2;
            band = true;
            while(band){
                band = false;
                i = 0;
                while ((i+inta) <= list.getLength()-1){
                    if (list.get(i) > list.get(i + inta)){
                        aux = list.get(i);
                        list.update(list.get(i+inta), i);
                        list.update(aux, i+inta);
                        band = true;
                    }
                    i = i +1;
                }
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
        }


        long startTime = System.nanoTime();

        shell(list);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;

        System.out.println("\nLista ordenada:");
        printList(list);

        System.out.println("\nTiempo de ejecución del Shell: " + duration + " ns");
    }

    public void printList(LinkedList<Integer> list) {
        for (int i = 0; i < list.getLength(); i++) {
            System.out.print(list.get(i));
            if (i < list.getLength() - 1) {
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
