
package Herencia;

import java.util.Calendar;

/**
 *
 * @author gigiw
 */
public class Biblioteca {
    private MaterialBibliografico[] materiales;
    private Prestamo[] prestamos;
    private int cantidadMateriales;
    private int cantidadPrestamos;

    public Biblioteca() {
        materiales = new MaterialBibliografico[100];
        prestamos = new Prestamo[100];
        cantidadMateriales = 0;
        cantidadPrestamos = 0;
    }

    public void registrarMaterial(MaterialBibliografico material) {
        if (material == null) {
            throw new IllegalArgumentException("Material invalido");
        }
        if (material.getCodigo() == null ||
                material.getCodigo().trim().isEmpty()) {
            throw new IllegalArgumentException("Codigo invalido");
        }
        if (buscarPorCodigo(material.getCodigo()) != null) {
            throw new IllegalArgumentException("Codigo duplicado");
        }
        if (cantidadMateriales >= materiales.length) {
            throw new IllegalStateException("Biblioteca llena");
        }
        materiales[cantidadMateriales] = material;
        cantidadMateriales++;
    }

    public MaterialBibliografico buscarPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("Codigo invalido");
        }
        return buscarRecursivo(codigo.trim(), 0);
    }

    public MaterialBibliografico buscarRecursivo(String codigo, int posicion) {
        if (posicion >= cantidadMateriales) {
            return null;
        }
        if (materiales[posicion].getCodigo().trim()
                .equalsIgnoreCase(codigo)) {
            return materiales[posicion];
        }
        return buscarRecursivo(codigo, posicion + 1);
    }

    public Prestamo prestarMaterial(String codigo) {
        MaterialBibliografico material = buscarPorCodigo(codigo);
        if (material == null) {
            throw new IllegalArgumentException("Material no encontrado");
        }
        if (!material.isDisponible()) {
            throw new IllegalStateException("Material no disponible");
        }
        if (cantidadPrestamos >= prestamos.length) {
            throw new IllegalStateException("Limite de prestamos alcanzado");
        }

        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaPrevista = ManejoFechas.sumarDias(fechaInicio, material.getPlazoPrestamo());

        Prestamo nuevoPrestamo = new Prestamo(material, fechaInicio, fechaPrevista);

        prestamos[cantidadPrestamos] = nuevoPrestamo;
        cantidadPrestamos++;

        material.setDisponible(false);

        return nuevoPrestamo;
    }

    public Prestamo buscarPrestamoActivo(String codigo) {

        MaterialBibliografico material = buscarPorCodigo(codigo);

        if (material == null) {
            return null;
        }

        for (int i = 0; i < cantidadPrestamos; i++) {

            if (prestamos[i].getMaterial() == material
                    && prestamos[i].isActivo()) {
                return prestamos[i];
            }
        }

        return null;
    }

    public double devolverMaterial(String codigo) {

        MaterialBibliografico material = buscarPorCodigo(codigo);

        if (material == null) {
            throw new IllegalArgumentException("Material no encontrado");
        }

        Prestamo prestamo = buscarPrestamoActivo(codigo);

        if (prestamo == null) {
            throw new IllegalStateException(
                    "El material no tiene un prestamo activo");
        }

        Calendar fechaDevolucion = Calendar.getInstance();
        int diasRetraso = ManejoFechas.calcularRetraso(prestamo.getFechaPrevista(), fechaDevolucion);
        double multa = material.calcularMulta(diasRetraso);

        prestamo.setActivo(false);
        material.setDisponible(true);

        return multa;
    }

    public int getCantidadMateriales() {
        return cantidadMateriales;
    }

    public MaterialBibliografico getMaterial(int posicion) {
        if (posicion < 0 || posicion >= cantidadMateriales) {
            throw new IllegalArgumentException("Posicion invalida");
        }
        return materiales[posicion];
    }

    public MaterialBibliografico[] getMateriales() {
        MaterialBibliografico[] lista = new MaterialBibliografico[cantidadMateriales];
        for (int i = 0; i < cantidadMateriales; i++) {
            lista[i] = materiales[i];
        }
        return lista;
    }

    public int getCantidadPrestamos() {
        return cantidadPrestamos;
    }

    public Prestamo getPrestamo(int posicion) {
        if (posicion < 0 || posicion >= cantidadPrestamos) {
            throw new IllegalArgumentException("Posicion invalida");
        }
        return prestamos[posicion];
    }
}


