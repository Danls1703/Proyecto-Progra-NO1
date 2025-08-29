import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        Deporte natacion = new Deporte();
        natacion.setEspecialidad("Acuatico");
        natacion.setTipo("Estilo libre");
        
        Atleta miAtleta = new Atleta();
        miAtleta.registrar("Marta", "Wendi", "España", 25, "Natacion");
        miAtleta.setDeporte(natacion);
        
        Marca marca1 = new Marca(50.2, "segundos", new Date());
        Marca marca2 = new Marca(49.8, "segundos", new Date());

        SesionEntrenamiento sesion1 = new SesionEntrenamiento();
        sesion1.setEspecialidad("Natacion");
        sesion1.setTipo("Resistencia");
        sesion1.agregarMarcas(marca1);
        sesion1.agregarMarcas(marca2);

        
        miAtleta.agregarSesion(sesion1);

        
        miAtleta.estadisticas_debut();
        System.out.println("Deporte del atleta: " + miAtleta.getDeporte().getEspecialidad());
        
        System.out.println("\n--- Estadísticas de la Sesión ---");
        System.out.println("Mejor marca: " + sesion1.mejorMarca().getValor() + " " + sesion1.mejorMarca().getUnidad());
        System.out.println("Promedio de marcas: " + sesion1.promedio() + " " + marca1.getUnidad());
        
        
        guardarAtleta(miAtleta, "atleta.json");
        
        Atleta atletaCargado = cargarAtleta("atleta.json");
        if (atletaCargado != null) {
            System.out.println("\n--- Atleta cargado desde archivo ---");
            atletaCargado.estadisticas_debut();
        }
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
            e.printStackTrace();
            return null;
        }
    }
}