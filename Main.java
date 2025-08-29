import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Ingrese los datos del atleta ---");
        System.out.print("Nombre: ");
        String nombreAtleta = scanner.nextLine();
        System.out.print("Apellido: ");
        String apellidoAtleta = scanner.nextLine();
        System.out.print("País: ");
        String paisAtleta = scanner.nextLine();
        System.out.print("Edad: ");
        int edadAtleta = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Deporte (ej: Natacion, Atletismo): ");
        String nombreDeporte = scanner.nextLine();
        System.out.print("Especialidad del Deporte (ej: Acuatico, Pista): ");
        String especialidadDeporte = scanner.nextLine();
        System.out.print("Tipo de Deporte (ej: Estilo libre, 100m lisos): ");
        String tipoDeporte = scanner.nextLine();

        Deporte deporte = new Deporte();
        deporte.setEspecialidad(especialidadDeporte);
        deporte.setTipo(tipoDeporte);

        Atleta miAtleta = new Atleta();
        miAtleta.registrar(nombreAtleta, apellidoAtleta, paisAtleta, edadAtleta, nombreDeporte);
        miAtleta.setDeporte(deporte);

        System.out.println("\n--- Ingrese los datos de la sesión de entrenamiento ---");
        System.out.print("Especialidad de la sesión (ej: Natacion): ");
        String especialidadSesion = scanner.nextLine();
        System.out.print("Tipo de sesión (ej: Resistencia): ");
        String tipoSesion = scanner.nextLine();
        
        SesionEntrenamiento sesion = new SesionEntrenamiento();
        sesion.setEspecialidad(especialidadSesion);
        sesion.setTipo(tipoSesion);

        System.out.println("\n--- Ingrese las marcas para la sesión ---");
        System.out.print("Unidad de medida de las marcas (ej: segundos, metros): ");
        String unidadMarca = scanner.nextLine();
        
        while (true) {
            System.out.print("Ingrese el valor de la marca (o 'fin' para terminar): ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("fin")) {
                break;
            }
            try {
                double valorMarca = Double.parseDouble(input);
                Marca marca = new Marca(valorMarca, unidadMarca, new Date());
                sesion.agregarMarcas(marca);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
            }
        }
        
        miAtleta.agregarSesion(sesion);

        System.out.println("\n--- Estadísticas del Atleta ---");
        miAtleta.estadisticas_debut();
        System.out.println("Deporte del atleta: " + miAtleta.getDeporte().getEspecialidad());
        
        if (!sesion.getMarcas().isEmpty()) {
            System.out.println("\n--- Estadísticas de la Sesión ---");
            System.out.println("Mejor marca: " + sesion.mejorMarca().getValor() + " " + sesion.mejorMarca().getUnidad());
            System.out.println("Promedio de marcas: " + sesion.promedio() + " " + unidadMarca);
        } else {
            System.out.println("\nNo se ingresaron marcas para esta sesión.");
        }
        
        guardarAtleta(miAtleta, "atleta.json");
        
        Atleta atletaCargado = cargarAtleta("atleta.json");
        if (atletaCargado != null) {
            System.out.println("\n--- Atleta cargado desde archivo ---");
            atletaCargado.estadisticas_debut();
        }

        scanner.close();
    }

    public static void guardarAtleta(Atleta atleta, String nombreArchivo) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(nombreArchivo), atleta);
            System.out.println("Información guardada en " + nombreArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Atleta cargarAtleta(String nombreArchivo) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(nombreArchivo), Atleta.class);
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo. Asegúrese de que existe y es válido.");
            return null;
        }
    }
}
