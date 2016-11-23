/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Consultas.vcproducto;
import DATOS.dproducto;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author MXCATTV12
 */
public class BusquedadProducto extends javax.swing.JInternalFrame {

    /**
     * Creates new form BusquedadProducto
     */
    public dproducto objpro = new dproducto();
    public vcproducto objproconsu=new vcproducto();
    
    
    public BusquedadProducto() {
        initComponents();
        combobuscar.setModel(objproconsu.nombrepro(null));
        combobuscar.getEditor().setItem(null);
        combobusquedad();
        tablaproupd(null);
    }
    public void tablaproupd(String letritas){
        DefaultTableModel tablapro=objproconsu.mostrar_productos(letritas);
        tablaproducto.setModel(tablapro);
    }
    public void combobusquedad(){
        combobuscar.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt){
                String textoescrito=combobuscar.getEditor().getItem().toString();
                if(evt.getKeyCode()>=65 && evt.getKeyCode()<=90 || evt.getKeyCode()>=96 && evt.getKeyCode()<=105 || evt.getKeyCode()==8){
                    if(rbpornom.isSelected()){
                        combobuscar.setModel(objproconsu.nombrepro(textoescrito));
                        tablaproupd(textoescrito);
                        if(combobuscar.getItemCount()>0){
                            combobuscar.showPopup();
                            if(evt.getKeyCode()!=8){
                                ((JTextComponent)combobuscar.getEditor().getEditorComponent()).select(textoescrito.length(), 
                                        combobuscar.getEditor().getItem().toString().length());

                            }
                            else{
                                combobuscar.getEditor().setItem(textoescrito);
                            }
                        }
                        else{
                            combobuscar.addItem(textoescrito);

                        }
                    }
                    else if(rbporcod.isSelected()){
                        combobuscar.setModel(objproconsu.codpro(textoescrito));
                        tablaproupd(textoescrito);
                        if(combobuscar.getItemCount()>0){
                            combobuscar.showPopup();
                            if(evt.getKeyCode()!=8){
                                ((JTextComponent)combobuscar.getEditor().getEditorComponent()).select(textoescrito.length(), 
                                        combobuscar.getEditor().getItem().toString().length());

                            }
                            else{
                                combobuscar.getEditor().setItem(textoescrito);
                            }
                        }
                        else{
                            combobuscar.addItem(textoescrito);

                        }
                    }
                    
                }
                else if(evt.getKeyCode()==32){
                    getToolkit().beep();
                    evt.consume();
                    jLabel3.setText("NO SE PERMITEN ESPACIOS");
                    combobuscar.getEditor().setItem(combobuscar.getEditor().getItem().toString().trim());
                    combobuscar.setCursor(null);
                }
            }

        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btngrupeleccion = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaproducto = new javax.swing.JTable();
        rbporcod = new javax.swing.JRadioButton();
        rbpornom = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        combobuscar = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);

        jLabel1.setText("ELIJA EL TIPO DE BUSQUEDAD");

        tablaproducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaproducto);

        btngrupeleccion.add(rbporcod);
        rbporcod.setText("POR CODIGO");
        rbporcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbporcodActionPerformed(evt);
            }
        });

        btngrupeleccion.add(rbpornom);
        rbpornom.setSelected(true);
        rbpornom.setText("POR NOMBRE");
        rbpornom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbpornomActionPerformed(evt);
            }
        });

        jLabel2.setText("ESCRIBA EL NOMBRE O CODIGO A BUSCAR");

        combobuscar.setEditable(true);
        combobuscar.setEnabled(false);

        jLabel3.setText("Mensaje");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbporcod)
                                .addGap(18, 18, 18)
                                .addComponent(rbpornom)))
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(combobuscar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbporcod)
                    .addComponent(rbpornom)
                    .addComponent(combobuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbporcodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbporcodActionPerformed
        // TODO add your handling code here:
        if(rbporcod.isSelected()){
            combobuscar.setEnabled(true);
        }
        else{
            combobuscar.setEnabled(false);
        }
    }//GEN-LAST:event_rbporcodActionPerformed

    private void rbpornomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbpornomActionPerformed
        // TODO add your handling code here:
        if(rbpornom.isSelected()){
            combobuscar.setEnabled(true);
        }
        else{
            combobuscar.setEnabled(false);
        }
    }//GEN-LAST:event_rbpornomActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btngrupeleccion;
    private javax.swing.JComboBox<String> combobuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbporcod;
    private javax.swing.JRadioButton rbpornom;
    private javax.swing.JTable tablaproducto;
    // End of variables declaration//GEN-END:variables
}
