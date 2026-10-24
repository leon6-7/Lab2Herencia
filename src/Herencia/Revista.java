/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Herencia;

/**
 *
 * @author Abril
 */
public class Revista extends MaterialBibliografico{
    private String edicion;
    private static final int PLAZO_REVISTA = 6;
    private static final double MULTA_REVISTA = 1.20;
    
    
    public Revista(String codigo, String titulo, int anioPublicacion, String rutaImagen, String edicion){
        super(codigo, titulo, anioPublicacion, rutaImagen);
        this.edicion = edicion;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

     
    
    
    @Override
    public String getDatoEspecifico() {
        return edicion;
    }
    
     @Override
    public int getPlazoPrestamo() {
        return PLAZO_REVISTA;
    }
 
    @Override
    public double getMultaDiaria() {
        return MULTA_REVISTA;
    }
    
    @Override
    public String getTipo() {
        return "Revista";
    }
    @Override
    public String toString() {
        return super.toString() + " - Edicion: " + edicion;
    }
}

