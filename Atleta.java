import java.util.ArrayList;
import java.util.List;

public class Atleta {
    private String nombres;
    private String apellidos;
    private String nacionalidad;
    private int edad;
    private String prefe_deport;
    private Deporte deporte;
    private List<SesionEntrenamiento> sesiones = new ArrayList<>();

    
    public Atleta() {
    }

    public void registrar(String nombres, String apellidos, String nacionalidad, int edad, String prefe_deport){
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
        this.prefe_deport = prefe_deport;
        this.edad = edad;
        System.out.println("Atleta registrado: " + this.nombres + " " + this.apellidos);
    }
    
    public void estadisticas_debut(){
        System.out.println("--- Estadísticas de Debut ---");
        System.out.println("Nombre: " + this.nombres);
        System.out.println("Apellidos: " + this.apellidos);
        System.out.println("Edad: " + this.edad);
        System.out.println("Nacionalidad: " + this.nacionalidad);
        System.out.println("Deporte Preferido: " + this.prefe_deport);
        System.out.println("-----------------------------");
    }

    public String getNombres(){
        return nombres;
    }

    public void setNombres(String nombres){
        this.nombres = nombres;
    }

    public String getApellidos(){
        return apellidos;
    }

    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }

    public String getNacionalidad(){
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad){
        this.nacionalidad = nacionalidad;
    }
    
    public int getEdad(){
        return edad;
    }

    public void setEdad(int edad){
        this.edad = edad;
    }

    public String getPrefe_deport(){
        return prefe_deport;
    }

    public void setPrefe_deport(String prefe_deport){
        this.prefe_deport = prefe_deport;
    }

    public Deporte getDeporte(){
        return deporte;
    }

    public void setDeporte(Deporte deporte){
        this.deporte = deporte;
    }
    
    public List<SesionEntrenamiento> getSesiones() {
        return sesiones;
    }

    public void agregarSesion(SesionEntrenamiento sesion) {
        this.sesiones.add(sesion);
    }
}