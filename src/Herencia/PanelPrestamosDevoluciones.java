/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herencia;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class PanelPrestamosDevoluciones extends JPanel {

    private Biblioteca biblioteca;
    private Runnable actualizarTabla;

    private JTextField txtCodigoPrestar;
    private JTextField txtCodigoDevolver;

    private Color azulOscuro = new Color(21, 61, 115);
    private Color azul = new Color(32, 88, 164);
    private Color fondo = new Color(238, 243, 250);
    private Color gris = new Color(105, 120, 140);

    public PanelPrestamosDevoluciones(Biblioteca biblioteca,
                                     Runnable actualizarTabla) {

        this.biblioteca = biblioteca;
        this.actualizarTabla = actualizarTabla;

        setLayout(new BorderLayout(0, 25));
        setBackground(fondo);
        setBorder(BorderFactory.createEmptyBorder(30, 35, 30, 35));

        crearInterfaz();
    }

    private void crearInterfaz() {

        JPanel encabezado = new JPanel(new GridLayout(2, 1, 0, 5));
        encabezado.setOpaque(false);

        JLabel titulo = new JLabel("Prestamos y Devoluciones");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 27));
        titulo.setForeground(azulOscuro);

        JLabel subtitulo = new JLabel(
                "Administra los materiales de la biblioteca de forma sencilla.");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitulo.setForeground(gris);

        encabezado.add(titulo);
        encabezado.add(subtitulo);

        add(encabezado, BorderLayout.NORTH);

        JPanel tarjetas = new JPanel(new GridLayout(1, 2, 25, 0));
        tarjetas.setOpaque(false);

        tarjetas.add(crearTarjetaPrestamo());
        tarjetas.add(crearTarjetaDevolucion());

        add(tarjetas, BorderLayout.CENTER);

        JLabel nota = new JLabel(
                "Las fechas y multas se calculan automaticamente.");
        nota.setForeground(gris);
        nota.setFont(new Font("SansSerif", Font.ITALIC, 12));

        add(nota, BorderLayout.SOUTH);
    }

    private JPanel crearTarjetaPrestamo() {

        JPanel tarjeta = crearTarjeta();

        JLabel icono = new JLabel("  +  ");
        icono.setFont(new Font("SansSerif", Font.BOLD, 22));
        icono.setForeground(azul);
        icono.setOpaque(true);
        icono.setBackground(new Color(229, 240, 255));
        icono.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JLabel titulo = crearTitulo("Nuevo prestamo");

        JLabel descripcion = crearDescripcion(
                "Registra la salida de un material bibliografico.");

        JLabel etiqueta = crearEtiqueta("Codigo del material");

        txtCodigoPrestar = crearCampo();

        JButton btnPrestar = crearBoton(
                "Registrar prestamo", azul);

        tarjeta.add(icono);
        tarjeta.add(Box.createVerticalStrut(20));
        tarjeta.add(titulo);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(descripcion);
        tarjeta.add(Box.createVerticalStrut(35));
        tarjeta.add(etiqueta);
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(txtCodigoPrestar);
        tarjeta.add(Box.createVerticalStrut(25));
        tarjeta.add(btnPrestar);
        tarjeta.add(Box.createVerticalGlue());

        btnPrestar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prestarMaterial();
            }
        });

        return tarjeta;
    }

    private JPanel crearTarjetaDevolucion() {

        JPanel tarjeta = crearTarjeta();

        JLabel icono = new JLabel("  \u2713  ");
        icono.setFont(new Font("SansSerif", Font.BOLD, 22));
        icono.setForeground(new Color(22, 135, 119));
        icono.setOpaque(true);
        icono.setBackground(new Color(228, 245, 242));
        icono.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        JLabel titulo = crearTitulo("Devolucion");

        JLabel descripcion = crearDescripcion(
                "Registra el retorno y calcula la multa.");

        JLabel etiqueta = crearEtiqueta("Codigo del material");

        txtCodigoDevolver = crearCampo();

        JButton btnDevolver = crearBoton(
                "Registrar devolucion", azulOscuro);

        tarjeta.add(icono);
        tarjeta.add(Box.createVerticalStrut(20));
        tarjeta.add(titulo);
        tarjeta.add(Box.createVerticalStrut(8));
        tarjeta.add(descripcion);
        tarjeta.add(Box.createVerticalStrut(35));
        tarjeta.add(etiqueta);
        tarjeta.add(Box.createVerticalStrut(10));
        tarjeta.add(txtCodigoDevolver);
        tarjeta.add(Box.createVerticalStrut(25));
        tarjeta.add(btnDevolver);
        tarjeta.add(Box.createVerticalGlue());

        btnDevolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolverMaterial();
            }
        });

        return tarjeta;
    }

    private JPanel crearTarjeta() {

        JPanel tarjeta = new JPanel();

        tarjeta.setLayout(
                new BoxLayout(tarjeta, BoxLayout.Y_AXIS));

        tarjeta.setBackground(Color.WHITE);

        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(220, 230, 242), 1),
                BorderFactory.createEmptyBorder(30, 25, 30, 25)
        ));

        return tarjeta;
    }

    private JLabel crearTitulo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.BOLD, 21));
        label.setForeground(azulOscuro);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JLabel crearDescripcion(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.PLAIN, 12));
        label.setForeground(gris);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JLabel crearEtiqueta(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("SansSerif", Font.BOLD, 13));
        label.setForeground(azulOscuro);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField crearCampo() {

        JTextField campo = new JTextField();

        campo.setFont(new Font("SansSerif", Font.PLAIN, 15));
        campo.setMaximumSize(new Dimension(
                Integer.MAX_VALUE, 45));

        campo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(210, 222, 238), 1),
                BorderFactory.createEmptyBorder(10, 12, 10, 12)
        ));

        campo.setAlignmentX(Component.LEFT_ALIGNMENT);

        return campo;
    }

    private JButton crearBoton(String texto, Color color) {

        JButton boton = new JButton(texto);

        boton.setFont(new Font("SansSerif", Font.BOLD, 14));
        boton.setForeground(Color.WHITE);
        boton.setBackground(color);

        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        boton.setMaximumSize(new Dimension(
                Integer.MAX_VALUE, 48));

        boton.setAlignmentX(Component.LEFT_ALIGNMENT);

        return boton;
    }

    private void prestarMaterial() {
        String codigo = txtCodigoPrestar.getText().trim();

        if (codigo.isEmpty()) {
            mostrarError("Ingrese el codigo del material.");
            return;
        }

        try {

            Prestamo prestamo = biblioteca.prestarMaterial(codigo);

            JOptionPane.showMessageDialog(this,
                    "Prestamo registrado correctamente.\n\n"
                    + "Material: "
                    + prestamo.getMaterial().getTitulo() + "\n"
                    + "Fecha de inicio: "
                    + prestamo.getFechaInicioTexto() + "\n"
                    + "Fecha limite: "
                    + prestamo.getFechaPrevistaTexto(),
                    "Prestamo exitoso",
                    JOptionPane.INFORMATION_MESSAGE);

            txtCodigoPrestar.setText("");
            actualizarTabla.run();

        } catch (IllegalArgumentException | IllegalStateException ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void devolverMaterial() {
        String codigo = txtCodigoDevolver.getText().trim();

        if (codigo.isEmpty()) {
            mostrarError("Ingrese el codigo del material.");
            return;
        }

        try {
            double multa = biblioteca.devolverMaterial(codigo);
            JOptionPane.showMessageDialog(this, "Devolucion registrada correctamente.\n\n" + "Multa: " + String.format("%.2f", multa), "Devolucion exitosa", JOptionPane.INFORMATION_MESSAGE);
            txtCodigoDevolver.setText("");
            actualizarTabla.run();

        } catch (IllegalArgumentException | IllegalStateException ex) {
            mostrarError(ex.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}

