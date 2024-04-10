package com.estructura;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AppTest {

    static class Paciente implements Comparable<Paciente> {
        private String nombre;
        private String sintoma;
        private char codigoEmergencia;

        public Paciente(String nombre, String sintoma, char codigoEmergencia) {
            this.nombre = nombre;
            this.sintoma = sintoma;
            this.codigoEmergencia = codigoEmergencia;
        }

        public String getNombre() {
            return nombre;
        }

        public String getSintoma() {
            return sintoma;
        }

        public char getCodigoEmergencia() {
            return codigoEmergencia;
        }

        @Override
        public int compareTo(Paciente otro) {
            return Character.compare(this.codigoEmergencia, otro.codigoEmergencia);
        }
    }

    @Test
    public void testAgregarPacienteYAtenderSiguientePaciente() {
        VectorHeap<Paciente> emergencyQueue = new VectorHeap<>();
        try {
            File file = new File("src/test/java/com/estructura/pacientes.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                agregarPaciente(emergencyQueue, line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        }

        Paciente pacienteAtendido = atenderSiguientePaciente(emergencyQueue);

        assertEquals("Juan Perez", pacienteAtendido.getNombre());
        assertEquals("fractura de pierna", pacienteAtendido.getSintoma());
        assertEquals('C', pacienteAtendido.getCodigoEmergencia());
        assertEquals(0, emergencyQueue.size());
    }

    private void agregarPaciente(VectorHeap<Paciente> emergencyQueue, String pacienteStr) {
        String[] parts = pacienteStr.split(",");
        if (parts.length == 3) {
            String nombre = parts[0].trim();
            String sintoma = parts[1].trim();
            char codigoEmergencia = parts[2].trim().charAt(0);
            Paciente paciente = new Paciente(nombre, sintoma, codigoEmergencia);
            emergencyQueue.add(paciente);
        }
    }

    private Paciente atenderSiguientePaciente(VectorHeap<Paciente> emergencyQueue) {
        if (emergencyQueue.isEmpty()) {
            return null;
        }
        return emergencyQueue.atenderSiguientePaciente();
    }
}
