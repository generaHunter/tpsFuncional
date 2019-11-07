/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;
import Datos.DB;
import Logica_Negocio.Usuario;
import Logica_Negocio.MateriaUsuario;
import Logica_Negocio.AlumnoProfesor;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Datos.NotaController;
import Logica_Negocio.Alumno;
import Logica_Negocio.Validaciones;
import Logica_Negocio.Calificacion;
import Logica_Negocio.Materia;
import Logica_Negocio.Nota;
import Logica_Negocio.Periodo;
import java.math.BigDecimal;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author JoseM
 */
public class NotasForm extends javax.swing.JFrame {

    /**
     * Creates new form NotasForm
     */
    DefaultTableModel modeloTable;
    DefaultTableModel modeloTable2;
    Usuario NUsuario=new Usuario();
    Periodo NPeriodo=new Periodo();
    MateriaUsuario NMatusu=new MateriaUsuario();
    AlumnoProfesor NAlumPro= new AlumnoProfesor();
    DB conn;
    NotaController CargarNota=new NotaController();
    
    public NotasForm() {
        initComponents();
        modeloTable = (DefaultTableModel) this.tablaNota.getModel();
       // modeloTable2 = (DefaultTableModel) this.tablaDetalleNota.getModel();
        modeloTable2=new DefaultTableModel(
         new Object [][] {

            },
            new String [] {
                "idNota", "Periodo", "Nota", "Nota", "Nota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
            @Override
            public void setValueAt(Object value,int row,int column)
            {
                if(column>=2 && column<=4)
                {
                  
                        if(Validaciones.Double(value.toString(), 0.00, 10.00))
                        {
                            super.setValueAt(value, row, column);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Solo se permiten números desde 0.00 hasta 10.00");
                        }
                }
            }
        };
        this.tablaDetalleNota.setModel(modeloTable2);
         jScrollPane1.setViewportView(tablaDetalleNota);
        if (tablaDetalleNota.getColumnModel().getColumnCount() > 0) {
            tablaDetalleNota.getColumnModel().getColumn(0).setMinWidth(0);
            tablaDetalleNota.getColumnModel().getColumn(0).setPreferredWidth(0);
            tablaDetalleNota.getColumnModel().getColumn(0).setMaxWidth(0);
        }
        NUsuario.ComboUsuario(cbProfesor);
        NPeriodo.ComboPeriodo(cbPeriodo);
        CargarNota.Consultar(tablaNota,modeloTable);
       // ocultarColumna();
    }

    public void ocultarColumna()
    {
       tablaNota.getColumnModel().getColumn(0).setMaxWidth(0);
       tablaNota.getColumnModel().getColumn(0).setMinWidth(0);
       tablaNota.getColumnModel().getColumn(0).setPreferredWidth(0);
       tablaNota.getColumnModel().getColumn(5).setMaxWidth(0);
       tablaNota.getColumnModel().getColumn(5).setMinWidth(0);
       tablaNota.getColumnModel().getColumn(5).setPreferredWidth(0);
       tablaNota.getColumnModel().getColumn(7).setMaxWidth(0);
       tablaNota.getColumnModel().getColumn(7).setMinWidth(0);
       tablaNota.getColumnModel().getColumn(7).setPreferredWidth(0);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btGuardar = new javax.swing.JButton();
        cbMateria = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbProfesor = new javax.swing.JComboBox<>();
        cbAlumno = new javax.swing.JComboBox<>();
        cbPeriodo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaNota = new javax.swing.JTable();
        txtNota3 = new javax.swing.JTextField();
        txtNota2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDetalleNota = new javax.swing.JTable();
        btEdit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btGuardar.setText("Ingresar nota");
        btGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarActionPerformed(evt);
            }
        });

        cbMateria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbMateriaItemStateChanged(evt);
            }
        });

        jLabel1.setText("Alumno:");

        jLabel2.setText("Materia:");

        jLabel3.setText("Profesor:");

        cbProfesor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbProfesorItemStateChanged(evt);
            }
        });

        jLabel4.setText("Periodo:");

        jLabel5.setText("Nota:");

        tablaNota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idNotaP1", "Profesor", "idAlumno", "Alumno", "Materia", "Primero Periodo", "idNotaP2", "Segundo Periodo", "idNotaP3", "Tercer Periodo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaNota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaNotaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaNota);
        if (tablaNota.getColumnModel().getColumnCount() > 0) {
            tablaNota.getColumnModel().getColumn(0).setMinWidth(0);
            tablaNota.getColumnModel().getColumn(0).setPreferredWidth(0);
            tablaNota.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaNota.getColumnModel().getColumn(2).setMinWidth(0);
            tablaNota.getColumnModel().getColumn(2).setPreferredWidth(0);
            tablaNota.getColumnModel().getColumn(2).setMaxWidth(0);
            tablaNota.getColumnModel().getColumn(6).setMinWidth(0);
            tablaNota.getColumnModel().getColumn(6).setPreferredWidth(0);
            tablaNota.getColumnModel().getColumn(6).setMaxWidth(0);
            tablaNota.getColumnModel().getColumn(8).setMinWidth(0);
            tablaNota.getColumnModel().getColumn(8).setPreferredWidth(0);
            tablaNota.getColumnModel().getColumn(8).setMaxWidth(0);
        }

        tablaDetalleNota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idNota", "Periodo", "Nota", "Nota", "Nota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaDetalleNota);
        if (tablaDetalleNota.getColumnModel().getColumnCount() > 0) {
            tablaDetalleNota.getColumnModel().getColumn(0).setMinWidth(0);
            tablaDetalleNota.getColumnModel().getColumn(0).setPreferredWidth(0);
            tablaDetalleNota.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        btEdit.setText("Editar");
        btEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNota)
                    .addComponent(cbProfesor, 0, 108, Short.MAX_VALUE)
                    .addComponent(cbMateria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbAlumno, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbPeriodo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
                        .addComponent(btGuardar)
                        .addGap(120, 120, 120))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(txtNota2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(txtNota3, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btEdit)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btGuardar)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtNota3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNota2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btEdit)
                .addContainerGap(298, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbProfesorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbProfesorItemStateChanged
        // TODO add your handling code here:
        NMatusu.ComboMateriaUsuario(cbMateria, cbProfesor.getItemAt(cbProfesor.getSelectedIndex()).getIdUsuario());
        
    }//GEN-LAST:event_cbProfesorItemStateChanged

    private void btGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarActionPerformed
        // TODO add your handling code here:
//            conn=new DB();
//            CallableStatement pstm;
//        try {
//            pstm = conn.getConection().prepareCall("{call insertar_nota(?,?,?,?,?)}");
//                    
//                pstm.setInt(1, 1);
//                pstm.setInt(2, 62);
//                pstm.setInt(3, 262);
//                pstm.setString(4, "Primero");
//                pstm.setDouble(5, 9.95);
//                pstm.execute();
//                conn.getConection().close();
//    } catch (SQLException ex) {
//            Logger.getLogger(NotasForm.class.getName()).log(Level.SEVERE, null, ex);
//            
//        }

        NotaController IngresarNota=new NotaController();
        Alumno NotaAlumno= new Alumno();
        Materia NotaMateria=new Materia();
        Usuario NotaUsuario=new Usuario();
        Periodo NotaPeriodo=new Periodo();
       // Calificacion NotaNueva=new Calificacion();
        Nota NotaNueva=new Nota();

        
        NotaAlumno.setIdAlumno(this.cbAlumno.getItemAt(this.cbAlumno.getSelectedIndex()).getIdMatricula().getIdAlumno().getIdAlumno());
        NotaMateria.setIdMateria(this.cbMateria.getItemAt(this.cbMateria.getSelectedIndex()).getIdMateria().getIdMateria());
        NotaUsuario.setIdUsuario(this.cbProfesor.getItemAt(this.cbProfesor.getSelectedIndex()).getIdUsuario());
        NotaPeriodo.setPeriodo(this.cbPeriodo.getItemAt(this.cbPeriodo.getSelectedIndex()).getPeriodo());
        NotaNueva.setNota1(Double.parseDouble(this.txtNota.getText()));
        NotaNueva.setNota2(Double.parseDouble(this.txtNota2.getText()));
        NotaNueva.setNota3(Double.parseDouble(this.txtNota3.getText()));
        JOptionPane.showMessageDialog(null, NotaAlumno.getIdAlumno()+"alumno,materia "+NotaMateria.getIdMateria()+" usuario "+NotaUsuario.getIdUsuario());
        try {
            IngresarNota.insertar_nota(NotaAlumno, NotaMateria, NotaUsuario, NotaPeriodo, NotaNueva);
           
        } catch (SQLException ex) {
            Logger.getLogger(matriculaForm.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }//GEN-LAST:event_btGuardarActionPerformed

    private void cbMateriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbMateriaItemStateChanged
        // TODO add your handling code here:
        NAlumPro.ComboAlumnoProfesor(this.cbAlumno, cbMateria.getItemAt(cbMateria.getSelectedIndex()).getIdMatusu());
        
    }//GEN-LAST:event_cbMateriaItemStateChanged

    private void tablaNotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaNotaMouseClicked
        // TODO add your handling code here:
        int indice = this.tablaNota.getSelectedRow();
        if (indice > -1) {
            try {               
                CargarNota.detalleNota(this.tablaDetalleNota, Integer.parseInt(tablaNota.getValueAt(indice, 0).toString()), Integer.parseInt(tablaNota.getValueAt(indice, 6).toString()), Integer.parseInt(tablaNota.getValueAt(indice, 8).toString()),modeloTable2); 
            } 
            catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }//GEN-LAST:event_tablaNotaMouseClicked

    private void btEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditActionPerformed
        // TODO add your handling code here:
       
        for (int i = 0; i <=this.tablaDetalleNota.getRowCount()-1; i++) {
            try {
                Nota NotaEdit=new Nota();
                BigDecimal bigDecimalValue = new BigDecimal(this.tablaDetalleNota.getValueAt(i,0).toString());
                NotaEdit.setIdNota(bigDecimalValue);
                NotaEdit.setNota1(Double.parseDouble(this.tablaDetalleNota.getValueAt(i,2).toString()));
                NotaEdit.setNota2(Double.parseDouble(this.tablaDetalleNota.getValueAt(i,3).toString()));
                NotaEdit.setNota3(Double.parseDouble(this.tablaDetalleNota.getValueAt(i,4).toString()));
                CargarNota.EditarNota(NotaEdit);
            } catch ( Exception ex) {
                //Logger.getLogger(NotasForm.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex.toString());
            }
        }
        CargarNota.Consultar(tablaNota,modeloTable);
    }//GEN-LAST:event_btEditActionPerformed

   
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NotasForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NotasForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NotasForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NotasForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NotasForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEdit;
    private javax.swing.JButton btGuardar;
    private javax.swing.JComboBox<AlumnoProfesor> cbAlumno;
    private javax.swing.JComboBox<MateriaUsuario> cbMateria;
    private javax.swing.JComboBox<Periodo> cbPeriodo;
    private javax.swing.JComboBox<Usuario> cbProfesor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaDetalleNota;
    private javax.swing.JTable tablaNota;
    private javax.swing.JTextField txtNota;
    private javax.swing.JTextField txtNota2;
    private javax.swing.JTextField txtNota3;
    // End of variables declaration//GEN-END:variables
}
