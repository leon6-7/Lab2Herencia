/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Herencia;

/**
 *
 * @author Abril
 */
public class MaterialBibliografico {
 
    // Valores por defecto de un material generico
    private static final int PLAZO_POR_DEFECTO = 7;
    private static final double MULTA_POR_DEFECTO = 1.00;
 
    private String codigo;
    private String titulo;
    private int anioPublicacion;
    private boolean disponible;
    private String rutaImagen;
 
    public MaterialBibliografico(String codigo, String titulo,
                                 int anioPublicacion, String rutaImagen) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.rutaImagen = rutaImagen;
        this.disponible = true;
    }
 
    public int getPlazoPrestamo() {
        return PLAZO_POR_DEFECTO;
    }
 
   
    public double getMultaDiaria() {
        return MULTA_POR_DEFECTO;
    }
 
    public String getTipo() {
        return "Material";
    }
 
    
    public String getDatoEspecifico() {
        return "";
    }

    public double calcularMulta(int diasRetraso) {
        if (diasRetraso <= 0) {
            return 0.0;
        }
        return diasRetraso * getMultaDiaria();
    }
 
 
    public String getCodigo() {
        return codigo;
    }
 
    public String getTitulo() {
        return titulo;
    }
 
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
 
    public int getAnioPublicacion() {
        return anioPublicacion;
    }
 
    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
 
    public boolean isDisponible() {
        return disponible;
    }
 
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
 
    public String getRutaImagen() {
        return rutaImagen;
    }
 
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
 
 
    @Override
    public String toString() {
        return getTipo() + " [" + codigo + "] " + titulo
                + " (" + anioPublicacion + ")"
                + (disponible ? " - Disponible" : " - No disponible");
    }
}
 
   

