/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Herencia;

import java.util.Calendar;

/**
 *
 * @author Abril
 */
public class Herencia {
    public static void main(String[] args) {
        Calendar hoy = Calendar.getInstance();

        Calendar fechaLimite = ManejoFechas.sumarDias(hoy, 14);

        System.out.println("Fecha actual: "
                + ManejoFechas.mostrarFecha(hoy));

        System.out.println("Fecha limite: "
                + ManejoFechas.mostrarFecha(fechaLimite));

        Calendar fechaTardia = ManejoFechas.sumarDias(fechaLimite, 3);

        int retraso = ManejoFechas.calcularRetraso(
                fechaLimite, fechaTardia);

        System.out.println("Dias de retraso: " + retraso);
    }
}

