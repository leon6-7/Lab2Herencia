/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Herencia;

/**
 *
 * @author Abril
 */
public class Libro extends MaterialBibliografico{
    private String nombreAutor;
    private static final int PLAZO_LIBRO = 14;
    private static final double MULTA_LIBRO = 1.50;
    
    
     public Libro(String codigo, String titulo, int anioPublicacion, String rutaImagen, String nombreAutor) {
        super(codigo, titulo, anioPublicacion, rutaImagen);
        this.nombreAutor=nombreAutor;
     }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }
     
    
    
    @Override
    public String getDatoEspecifico() {
        return nombreAutor;
    }
    
     @Override
    public int getPlazoPrestamo() {
        return PLAZO_LIBRO;
    }
 
    @Override
    public double getMultaDiaria() {
        return MULTA_LIBRO;
    }
    
    @Override
    public String getTipo() {
        return "Libro";
    }
    @Override
    public String toString() {
        return super.toString() + " - Autor: " + nombreAutor;
    }
}
