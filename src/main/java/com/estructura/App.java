package com.estructura;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) {
        VectorHeap<Paciente> emergencyQueue = new VectorHeap<>();

        // Leer datos de pacientes del archivo
        try {
            File file = new File("src/main/java/com/estructura/pacientes.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String nombre = parts[0].trim();
                    String sintoma = parts[1].trim();
                    char codigoEmergencia = parts[2].trim().charAt(0);
                    Paciente paciente = new Paciente(nombre, sintoma, codigoEmergencia);
                    emergencyQueue.add(paciente);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
            return;
        }

        // Atender pacientes en orden de prioridad
        while (!emergencyQueue.isEmpty()) {
            Paciente nextPatient = emergencyQueue.remove();
            System.out.println("Nombre: " + nextPatient.getNombre() + ", Síntoma: " + nextPatient.getSintoma() +
                    ", Prioridad: " + nextPatient.getCodigoEmergencia());
        }
    }
}
