/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Herencia;
import java.util.Calendar;
public class Prestamo {

    private MaterialBibliografico material;
    private Calendar fechaInicio;
    private Calendar fechaPrevista;
    private Calendar fechaDevolucion; 
    private boolean activo;
    private int diasRetraso;
    private double multa;

    public Prestamo(MaterialBibliografico material) {
        this(material, Calendar.getInstance());
    }

    public Prestamo(MaterialBibliografico material, Calendar inicio) {
        this.material = material;
        this.fechaInicio = normalizar(inicio);
        this.fechaPrevista = (Calendar) this.fechaInicio.clone();
        // El plazo lo determina el objeto concreto (Libro, Revista, Tesis)
        this.fechaPrevista.add(Calendar.DAY_OF_MONTH, material.getPlazoPrestamo());
        this.fechaDevolucion = null;
        this.activo = true;
        this.diasRetraso = 0;
        this.multa = 0.0;
    }

   
    public void registrarDevolucion() throws PrestamoNoActivoException {
        registrarDevolucion(Calendar.getInstance());
    }

    public void registrarDevolucion(Calendar fecha) throws PrestamoNoActivoException {
        if (!activo) {
            throw new PrestamoNoActivoException(
                    "El prestamo del material " + material.getCodigo()
                    + " ya fue devuelto.");
        }
        this.fechaDevolucion = normalizar(fecha);
        this.diasRetraso = calcularDiasRetraso(fechaPrevista, fechaDevolucion);
        // Polimorfismo: la multa depende del objeto concreto
        this.multa = material.calcularMulta(diasRetraso);
        this.activo = false;
    }

 
    private static Calendar normalizar(Calendar original) {
        Calendar copia = (Calendar) original.clone();
        copia.set(Calendar.HOUR_OF_DAY, 0);
        copia.set(Calendar.MINUTE, 0);
        copia.set(Calendar.SECOND, 0);
        copia.set(Calendar.MILLISECOND, 0);
        return copia;
    }

    
    private static int calcularDiasRetraso(Calendar prevista, Calendar devolucion) {
        if (!devolucion.after(prevista)) {
            return 0;
        }
        Calendar cursor = (Calendar) prevista.clone();
        int dias = 0;
        while (cursor.before(devolucion)) {
            cursor.add(Calendar.DAY_OF_MONTH, 1);
            dias++;
        }
        return dias;
    }

  
    public static String formatear(Calendar fecha) {
        if (fecha == null) {
            return "-";
        }
        return String.format("%02d/%02d/%04d",
                fecha.get(Calendar.DAY_OF_MONTH),
                fecha.get(Calendar.MONTH) + 1,   
                fecha.get(Calendar.YEAR));
    }


    public MaterialBibliografico getMaterial() {
        return material;
    }

    public Calendar getFechaInicio() {
        return (Calendar) fechaInicio.clone();
    }

    public Calendar getFechaPrevista() {
        return (Calendar) fechaPrevista.clone();
    }

    public Calendar getFechaDevolucion() {
        return fechaDevolucion == null ? null : (Calendar) fechaDevolucion.clone();
    }

    public boolean isActivo() {
        return activo;
    }

    public int getDiasRetraso() {
        return diasRetraso;
    }

    public double getMulta() {
        return multa;
    }

    public String getEstado() {
        return activo ? "Activo" : "Devuelto";
    }

    public String getFechaInicioTexto() {
        return formatear(fechaInicio);
    }

    public String getFechaPrevistaTexto() {
        return formatear(fechaPrevista);
    }

    public String getFechaDevolucionTexto() {
        return formatear(fechaDevolucion);
    }

    @Override
    public String toString() {
        return "Prestamo de " + material.getCodigo() + " (" + material.getTitulo() + ")"
                + " | Inicio: " + getFechaInicioTexto()
                + " | Prevista: " + getFechaPrevistaTexto()
                + " | Estado: " + getEstado();
    }
}