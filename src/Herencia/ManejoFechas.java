
package Herencia;

import java.util.Calendar;

/**
 *
 * @author gigiw
 */
public class ManejoFechas {
    
    public static Calendar sumarDias(Calendar fecha, int dias) {
        Calendar nuevaFecha = (Calendar) fecha.clone();
        nuevaFecha.add(Calendar.DAY_OF_MONTH, dias);
        return nuevaFecha;
    }

    public static int calcularRetraso(Calendar fechaPrevista, Calendar fechaDevolucion) {

        Calendar prevista = (Calendar) fechaPrevista.clone();
        Calendar devolucion = (Calendar) fechaDevolucion.clone();

        prevista.set(Calendar.HOUR_OF_DAY, 0);
        prevista.set(Calendar.MINUTE, 0);
        prevista.set(Calendar.SECOND, 0);
        prevista.set(Calendar.MILLISECOND, 0);

        devolucion.set(Calendar.HOUR_OF_DAY, 0);
        devolucion.set(Calendar.MINUTE, 0);
        devolucion.set(Calendar.SECOND, 0);
        devolucion.set(Calendar.MILLISECOND, 0);

        int diasRetraso = 0;

        while (prevista.before(devolucion)) {
            prevista.add(Calendar.DAY_OF_MONTH, 1);
            diasRetraso++;
        }

        return diasRetraso;
    }

    public static String mostrarFecha(Calendar fecha) {
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int year = fecha.get(Calendar.YEAR);

        return dia + "/" + mes + "/" + year;
    }
}
