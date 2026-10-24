/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Herencia;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class CargadorImagenes {

    public static final int ANCHO_TABLA = 50;
    public static final int ALTO_TABLA = 65;

    private CargadorImagenes() {
    }

    
    public static ImageIcon cargarParaTabla(String ruta) {
        return cargar(ruta, ANCHO_TABLA, ALTO_TABLA);
    }

   
    public static ImageIcon cargar(String ruta, int ancho, int alto) {
        ImageIcon def;
        
        if (ruta != null && !ruta.trim().isEmpty()) {
            try {
                File archivo = new File(ruta.trim());
                if (archivo.exists() && archivo.isFile()) {
                    BufferedImage original = ImageIO.read(archivo);
                    if (original != null) {
                        return new ImageIcon(escalar(original, ancho, alto));
                    }
                }
            } catch (IOException e) {
            }}
            //Cargo imagen default
           
                ImageIcon imagenDefault= new ImageIcon("src/Imagenes_Def/def.png");
                    
                return imagenDefault;
                }

   
    public static boolean esImagenValida(String ruta) {
        if (ruta == null || ruta.trim().isEmpty()) {
            return false;
        }
        try {
            File archivo = new File(ruta.trim());
            return archivo.exists() && archivo.isFile()
                    && ImageIO.read(archivo) != null;
        } catch (IOException e) {
            return false;
        }
    }

 
    private static Image escalar(BufferedImage original, int ancho, int alto) {
        BufferedImage destino = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = destino.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.drawImage(original, 0, 0, ancho, alto, null);
        g.dispose();
        return destino;
    }

 
}