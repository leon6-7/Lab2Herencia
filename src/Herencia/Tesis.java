/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Herencia;

/**
 *
 * @author Abril
 */
public class Tesis extends MaterialBibliografico{
    private String tesista;
    private static final int PLAZO_TESIS = 6;
    private static final double MULTA_TESIS = 1.20;
    
    
    public Tesis(String codigo, String titulo, int anioPublicacion, String rutaImagen, String tesista){
        super(codigo, titulo, anioPublicacion, rutaImagen);
        this.tesista = tesista;
    }

    public String getTesista() {
        return tesista;
    }

    public void setTesista(String tesista) {
        this.tesista = tesista;
    }

     
    
    
    @Override
    public String getDatoEspecifico() {
        return tesista;
    }
    
     @Override
    public int getPlazoPrestamo() {
        return PLAZO_TESIS;
    }
 
    @Override
    public double getMultaDiaria() {
        return MULTA_TESIS;
    }
    
    @Override
    public String getTipo() {
        return "Tesis";
    }
    @Override
    public String toString() {
        return super.toString() + " - Tesista: " + tesista;
    }
}

