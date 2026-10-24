
package Herencia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Principal extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Principal.class.getName());
   
    private Biblioteca biblioteca = new Biblioteca();
    
    private JTable tablaMateriales;
    private DefaultTableModel modeloTabla;
    private JTextField txtCodigoBuscar;
    private JButton btnBuscar;
    
    private JComboBox <String> comboTipo;
    private JTextField txtCodigo;
    private JTextField txtTitulo;
    private JTextField txtAnio;
    private JLabel lblCampoEspecifico;
    private JTextField txtCampoEspecifico;
    private JTextField txtRutaImagen;
    private JButton btnSeleccionarImagen;
    private JButton btnRegistrar;

    // Para prestamo
    private JTextField txtCodigoPrestar;
    private JButton btnPrestar;

    // Para devolucion
    private JTextField txtCodigoDevolver;
    private JButton btnDevolver;
    
    public Principal() {
        initComponents();
        
        setTitle("Sistema de Gestion Bibliotecaria");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        getContentPane().setLayout(new BorderLayout());
        
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Inventario y Busqueda", crearPanelInventario());
        tabbedPane.addTab("Registro de Materiales", crearPanelRegistro());
        tabbedPane.addTab("Prestamos y Devoluciones", crearPanelPrestamosDevoluciones());
        
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
        actualizarTabla();
    }
    
    private JPanel crearPanelInventario(){
        JPanel panel = new JPanel(new BorderLayout(10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Busqueda Recursiva por Codigo"));
        
        panelBusqueda.add(new JLabel("Codigo del Material: "));
        txtCodigoBuscar = new JTextField(15);
        panelBusqueda.add(txtCodigoBuscar);
        
        btnBuscar = new JButton("Buscar");
        panelBusqueda.add(btnBuscar);
        
        panel.add(panelBusqueda, BorderLayout.NORTH);
        
        String[] columnas = {"Codigo", "Titulo", "Tipo", "Año", "Dato Especifico", "Ruta Imagen", "Disponibilidad"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
             public boolean isCellEditable(int row, int column){
                 return false;
             }   
        };
        
        tablaMateriales = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaMateriales);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarBusquedaRecursiva();
            }
        });
        
        return panel;
    }
    
    private JPanel crearPanelRegistro(){
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Registrar Nuevo Material Bibliografico"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // tipo de material
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Tipo de Material:"),gbc);
        gbc.gridx = 1;
        comboTipo = new JComboBox<>(new String[]{"Libro","Revista","Tesis"});
        panel.add(comboTipo, gbc);
        
        // codigo
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Codigo Unico:"),gbc);
        gbc.gridx = 1;
        
        // titulo
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Título:"), gbc);
        gbc.gridx = 1;
        txtTitulo = new JTextField(20);
        panel.add(txtTitulo, gbc);

        // año de publicación
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Año de Publicacion:"), gbc);
        gbc.gridx = 1;
        txtAnio = new JTextField(20);
        panel.add(txtAnio, gbc);

        // campo especifico (Autor / Edición / Tesista)
        gbc.gridx = 0; gbc.gridy = 4;
        lblCampoEspecifico = new JLabel("Autor:");
        panel.add(lblCampoEspecifico, gbc);
        gbc.gridx = 1;
        txtCampoEspecifico = new JTextField(20);
        panel.add(txtCampoEspecifico, gbc);

        // ruta de la Imagen
        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Imagen:"), gbc);
        gbc.gridx = 1;
        JPanel panelImagen = new JPanel(new BorderLayout(5, 0));
        txtRutaImagen = new JTextField(15);
        txtRutaImagen.setEditable(false);
        btnSeleccionarImagen = new JButton("Buscar...");
        panelImagen.add(txtRutaImagen, BorderLayout.CENTER);
        panelImagen.add(btnSeleccionarImagen, BorderLayout.EAST);
        panel.add(panelImagen, gbc);

        // boton para registrar
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2;
        btnRegistrar = new JButton("Registrar Material");
        panel.add(btnRegistrar, gbc);

        // para cambiar dinamicamente la etiqueta segun lo seleccionado del comboBox
        comboTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = (String) comboTipo.getSelectedItem();
                if ("Libro".equals(tipo)) {
                    lblCampoEspecifico.setText("Autor:");
                } else if ("Revista".equals(tipo)) {
                    lblCampoEspecifico.setText("Número de Edición:");
                } else if ("Tesis".equals(tipo)) {
                    lblCampoEspecifico.setText("Tesista / Autor Académico:");
                }
            }
        });
        
        btnSeleccionarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int resultado = fileChooser.showOpenDialog(Principal.this);
                if (resultado == JFileChooser.APPROVE_OPTION) {
                    File archivo = fileChooser.getSelectedFile();
                    txtRutaImagen.setText(archivo.getAbsolutePath());
                }
            }
        });
        
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarMaterial();
            }
        });
        
        return panel;
    }
    

    private void registrarMaterial() {
        String tipo = (String) comboTipo.getSelectedItem();
        String codigo = txtCodigo.getText().trim();
        String titulo = txtTitulo.getText().trim();
        String anioTexto = txtAnio.getText().trim();
        String campoEspec = txtCampoEspecifico.getText().trim();
        String rutaImagen = txtRutaImagen.getText().trim();
        
        if (codigo.isEmpty() || titulo.isEmpty() || anioTexto.isEmpty() || campoEspec.isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor complete todos los campos obligatorios.", "Error de Validacion", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        int anio;
        try{
            anio = Integer.parseInt(anioTexto);
            if (anio <= 0 || anio > anioActual){
                JOptionPane.showMessageDialog(this, "El anio debe ser un valor valido entre 1 y " + anioActual + ".", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(this, "El anio de publicacion debe ser un numero entero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        /*
        if (biblioteca.buscarMaterialRecursivo(codigo, 0) != null) {
            JOptionPane.showMessageDialog(this, "El codigo '" + codigo + "' ya se encuentra registrado.", "Codigo Duplicado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        */
        if (rutaImagen.isEmpty()) {
            rutaImagen = "Sin imagen";
        }

        MaterialBibliografico nuevoMaterial = null;
        if ("Libro".equals(tipo)) {
            nuevoMaterial = new Libro(codigo, titulo, anio, rutaImagen, campoEspec);
        } else if ("Revista".equals(tipo)) {
            int edicion = 1;
            try {
                edicion = Integer.parseInt(campoEspec);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El numero de edicion debe ser un entero valido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            nuevoMaterial = new Revista(codigo, titulo, anio, rutaImagen, campoEspec);
        } else if ("Tesis".equals(tipo)) {
            nuevoMaterial = new Tesis(codigo, titulo, anio, rutaImagen, campoEspec);
        }
        /*
        if (nuevoMaterial != null) {
            biblioteca.agregarMaterial(nuevoMaterial);
            JOptionPane.showMessageDialog(this, "Material registrado exitosamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormularioRegistro();
            actualizarTabla();
        }
        */
    }
    
    private JPanel crearPanelPrestamosDevoluciones(){
        JPanel panel = new JPanel(new GridLayout(1,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
        
        return panel;

   private JPanel crearPanelPrestamosDevoluciones() {
    return new PanelPrestamosDevoluciones(biblioteca, this::actualizarTabla);
    }
    
    public void actualizarTabla(){
        
    }
    
    private void ejecutarBusquedaRecursiva() {
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Principal().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
