/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Consultas.vccliente;
import DATOS.Clientes;
import com.sun.imageio.plugins.jpeg.JPEG;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author SOFTWARE
 */
public class ManteCliente extends javax.swing.JInternalFrame {

    /**
     * Creates new form ManteCliente
     */
    DefaultComboBoxModel cbmodelo=new DefaultComboBoxModel();
    DefaultComboBoxModel distritos=new DefaultComboBoxModel();
    vccliente objdclie=new vccliente();
    public String codcli;
    public Clientes dtscli=new Clientes();
    
    public ManteCliente() {
        initComponents();
        AutoCompleteDecorator.decorate(comboBuscar);
        cbmodelo=objdclie.listaclienom();
        distritos=objdclie.dtdistritos();
        comboBuscar.setModel(cbmodelo);
        codcli=objdclie.nuevocodclie();
        comoboDistrito.setModel(distritos);
        dtscli.setCodclie(comparar_nom_cod());
        datosdelcli();
    }
    public String comparar_nom_cod(){
        int n=0;
        String codclientes[]=objdclie.listacod_cli();
        String cod_cliente="";
        while(cod_cliente.equals("")){
            if(comboBuscar.getSelectedIndex()==n){
                cod_cliente=codclientes[n];
            }
            else{
                cod_cliente="";
                n++;
            }
            
        }
        return cod_cliente;
    }
    public boolean comporbaciondatos(){
        if(txtApellidos.getText().trim().length()>0 && txtNombres.getText().trim().length()>0 && txtDireccion.getText().trim().length()>0 
                && txtTelefono.getText().trim().length()>0 && txtDNI.getText().trim().length()>0){
            butGuardar.setEnabled(true);
            return true;
        }
        else{
            butGuardar.setEnabled(false);
            return false;
        }
    }
    public void n_cliente(){
        int ncodi,n=codcli.length(),n1=1,numc;
        String codigo="C",num="";
        String ncodig;
        while (n1<=n-1){
            ncodig=String.valueOf(codcli.charAt(n1));
            ncodi=Integer.valueOf(ncodig); 
            num+=String.valueOf(ncodi);
            n1++;
        }
        numc=Integer.valueOf(num)+1;
        codigo+=String.valueOf(numc);
        txtCodigo.setText(codigo);
    }
    public void comprobarselecion(){
        if (comboBuscar.getSelectedIndex()>=0){
            txtApellidos.setEnabled(true);
            txtDNI.setEnabled(true);
            txtDireccion.setEnabled(true);
            txtNombres.setEnabled(true);
            txtTelefono.setEnabled(true);
            comoboDistrito.setEnabled(true);
            rbFemenino.setEnabled(true);
            rbmasculino.setEnabled(true);
            
        }
        else{
            JOptionPane.showMessageDialog(null, "ERROR SELECIONE EL NOMBRE DEL CLIENTE ");
        }
    }
    public void desabilitar(){
            txtApellidos.setEnabled(false);
            txtDNI.setEnabled(false);
            txtDireccion.setEnabled(false);
            txtNombres.setEnabled(false);
            txtTelefono.setEnabled(false);
            comoboDistrito.setEnabled(false);
            rbFemenino.setEnabled(false);
            rbmasculino.setEnabled(false);
            
    }
    public void datosdelcli(){
        String datosclie[]=objdclie.busquedadclie(dtscli);
        try{
            txtCodigo.setText(datosclie[0]);
            txtNombres.setText(datosclie[1]);
            txtApellidos.setText(datosclie[2]);
            txtTelefono.setText(datosclie[3]);
            txtDNI.setText(datosclie[4]);
            txtruc.setText(datosclie[5]);
            if(datosclie[6].equals("H")){
                rbmasculino.setSelected(true);
            }
            else{
                if(datosclie[6].equals("M")){
                    rbFemenino.setSelected(true);
                }
            }
            switch(Integer.valueOf(datosclie[7])){
                case 1:
                    comoboDistrito.setSelectedIndex(0);
                    break;
                case 2:
                    comoboDistrito.setSelectedIndex(1);
                    break;
                case 3:
                    comoboDistrito.setSelectedIndex(2);
                    break;
                case 4:
                    comoboDistrito.setSelectedIndex(3);
                    break;
                default:
                    break;

            }
            txtDireccion.setText(datosclie[8]);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR "+e);
        }
        
        
    }
    public void savedatos(){
        dtscli.setCodclie(txtCodigo.getText());
        dtscli.setNombre(txtNombres.getText());
        dtscli.setApellido(txtApellidos.getText());
        dtscli.setFonoclie(txtTelefono.getText());
        dtscli.setDni(txtDNI.getText());
        dtscli.setRucclie(txtruc.getText());
        String sexocli;
        if(rbFemenino.isSelected()){
            sexocli="F";
        }
        else{
            sexocli="H";
        }
        dtscli.setSexo(sexocli);
        dtscli.setCoddist(comoboDistrito.getSelectedIndex()+1);
        dtscli.setDirecli(txtDireccion.getText());
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grSexo = new javax.swing.ButtonGroup();
        txtTelefono = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        comboBuscar = new javax.swing.JComboBox<String>();
        txtNombres = new javax.swing.JTextField();
        comoboDistrito = new javax.swing.JComboBox<String>();
        rbmasculino = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        rbFemenino = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        butGuardar = new javax.swing.JButton();
        bustSalir = new javax.swing.JButton();
        btneditar = new javax.swing.JToggleButton();
        btnnuevo = new javax.swing.JToggleButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        cbruc = new javax.swing.JCheckBox();
        txtruc = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTelefono.setEnabled(false);
        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 126, 111, -1));

        txtDNI.setEnabled(false);
        txtDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNIKeyTyped(evt);
            }
        });
        getContentPane().add(txtDNI, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 152, 111, -1));

        txtApellidos.setEnabled(false);
        getContentPane().add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 126, 155, -1));

        txtDireccion.setEnabled(false);
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 152, 200, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 0, 14))); // NOI18N
        jPanel2.setToolTipText("");

        comboBuscar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBuscarItemStateChanged(evt);
            }
        });
        comboBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboBuscar, 0, 226, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 4, Short.MAX_VALUE)
                .addComponent(comboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, 260, -1));

        txtNombres.setEnabled(false);
        getContentPane().add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 100, 155, -1));

        comoboDistrito.setToolTipText("");
        comoboDistrito.setEnabled(false);
        comoboDistrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comoboDistritoActionPerformed(evt);
            }
        });
        getContentPane().add(comoboDistrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 150, -1));

        grSexo.add(rbmasculino);
        rbmasculino.setText("Masculino");
        rbmasculino.setEnabled(false);
        rbmasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbmasculinoActionPerformed(evt);
            }
        });
        getContentPane().add(rbmasculino, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 177, -1, -1));

        jLabel1.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel1.setText("Manteimiento Cliente");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 11, -1, -1));

        grSexo.add(rbFemenino);
        rbFemenino.setText("Femenino");
        rbFemenino.setEnabled(false);
        getContentPane().add(rbFemenino, new org.netbeans.lib.awtextra.AbsoluteConstraints(153, 177, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel2.setText("Código:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 100, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel9.setText("Sexo:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 178, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel3.setText("Apellidos:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 126, -1, -1));

        butGuardar.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        butGuardar.setText("Guardar");
        butGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butGuardarActionPerformed(evt);
            }
        });

        bustSalir.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        bustSalir.setText("Salir");
        bustSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bustSalirActionPerformed(evt);
            }
        });

        btneditar.setText("EDITAR");
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });

        btnnuevo.setText("NUEVO");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btneditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(butGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bustSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnnuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btneditar)
                .addGap(18, 18, 18)
                .addComponent(butGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
                .addComponent(bustSalir)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(501, 11, -1, 329));

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel4.setText("Direccion:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 152, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel5.setText("Distrito:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 240, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel6.setText("DNI:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 152, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel7.setText("Teléfono:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 126, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel8.setText("Nombres:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 100, -1, -1));

        txtCodigo.setEnabled(false);
        getContentPane().add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 100, 155, -1));

        cbruc.setText("ACTIVAR RUC");
        cbruc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbrucActionPerformed(evt);
            }
        });
        getContentPane().add(cbruc, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, 140, -1));

        txtruc.setEnabled(false);
        txtruc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrucActionPerformed(evt);
            }
        });
        txtruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrucKeyTyped(evt);
            }
        });
        getContentPane().add(txtruc, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 110, -1));

        jLabel10.setText("RUC:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comoboDistritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comoboDistritoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comoboDistritoActionPerformed

    private void rbmasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbmasculinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbmasculinoActionPerformed

    private void bustSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bustSalirActionPerformed
        // TODO add your handling code here:

        this.dispose();
    }//GEN-LAST:event_bustSalirActionPerformed

    private void txtrucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrucActionPerformed

    private void comboBuscarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBuscarItemStateChanged
        // TODO add your handling code here:
        dtscli.setCodclie(comparar_nom_cod());
        datosdelcli();
    }//GEN-LAST:event_comboBuscarItemStateChanged

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
        // TODO add your handling code here:
        if (btneditar.isSelected()){
            comprobarselecion();
            btnnuevo.setEnabled(false);
            comboBuscar.setEnabled(false);
        }
        else{
            desabilitar();
            btnnuevo.setEnabled(true);
            dtscli.setCodclie(comparar_nom_cod());
            datosdelcli();
            comboBuscar.setEnabled(true);
        }
        
        
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        if(btnnuevo.isSelected()){
            txtApellidos.setEnabled(true);
            txtDNI.setEnabled(true);
            txtDireccion.setEnabled(true);
            txtNombres.setEnabled(true);
            txtTelefono.setEnabled(true);
            comoboDistrito.setEnabled(true);
            rbFemenino.setEnabled(true);
            rbmasculino.setEnabled(true);
            txtApellidos.setText("");
            /*txtCodigo.setText(String.valueOf(codcli));*/
            n_cliente();
            txtDNI.setText("");
            txtDireccion.setText("");
            txtNombres.setText("");
            txtTelefono.setText("");
            btneditar.setEnabled(false);
            comboBuscar.setEnabled(false);
        }
        else{
            desabilitar();
            btneditar.setEnabled(true);
            dtscli.setCodclie(comparar_nom_cod());
            datosdelcli();
            comboBuscar.setEnabled(true);
        }

    }//GEN-LAST:event_btnnuevoActionPerformed

    private void cbrucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbrucActionPerformed
        // TODO add your handling code here:
        if (cbruc.isSelected()){
            txtruc.setEnabled(true);
        }
        else{
            txtruc.setEnabled(false);
        }
    }//GEN-LAST:event_cbrucActionPerformed

    private void butGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butGuardarActionPerformed
        // TODO add your handling code here:
        savedatos();
        
        if(comporbaciondatos()){
        
            if(btneditar.isSelected() && !btnnuevo.isSelected()){
                if(objdclie.actualizardatos(dtscli)){
                    JOptionPane.showMessageDialog(null, "DATOS ACTUALIZADOS CON EXITO");
                    desabilitar();
                    btneditar.setSelected(false);
                    btnnuevo.setEnabled(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "LOS DATOS NO SE HAN PODIDO ACTUALIZAR");
                }



            }
            else{
                if(btnnuevo.isSelected() && !btneditar.isSelected()){
                    btneditar.setEnabled(false);
                    if(objdclie.insertarnuevocliente(dtscli)){
                        JOptionPane.showMessageDialog(null, "NUEVO CLIENTE AÑADIDO CON EXITO");
                        desabilitar();
                        btnnuevo.setSelected(false);
                        btneditar.setEnabled(true);
                        this.dispose();
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "ERROR LOS DATOS NO SE HAN PODIDO GUARDAR");
                }

            }
        }
        else{
            JOptionPane.showMessageDialog(null, "FALTAN LLENAR DATOS");
            butGuardar.setEnabled(true);
        }
    }//GEN-LAST:event_butGuardarActionPerformed

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        // TODO add your handling code here:
        int ncara=9;
        char c=evt.getKeyChar();
        if(Character.isLetter(c)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS");
            txtTelefono.setCursor(null);
        }
        if(txtTelefono.getText().length()>=ncara){
            evt.consume();
            JOptionPane.showMessageDialog(null, "SOLO ES PERMITIDO 9 CARACTERES");
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtDNIKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIKeyTyped
        // TODO add your handling code here:
        int ncara=8;
        char c=evt.getKeyChar();
        if(Character.isLetter(c)){
            getToolkit().beep();
            evt.consume();
            //JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS");
            txtDNI.setCursor(null);
        }
        if(txtDNI.getText().length()>=ncara){
            evt.consume();
            //JOptionPane.showMessageDialog(null, "SOLO ES PERMITIDO 8 CARACTERES");
        }
    }//GEN-LAST:event_txtDNIKeyTyped

    private void txtrucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrucKeyTyped
        // TODO add your handling code here:
        int ncara=11;
        char c=evt.getKeyChar();
        if(Character.isLetter(c)){
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "INGRESE SOLO NUMEROS");/*codigo para solo poder ingresar numeros para solo letras seria 
            Character.isDiggit(c)*/
            txtruc.setCursor(null);
        }
        if(txtruc.getText().length()>=ncara){
            evt.consume();
            JOptionPane.showMessageDialog(null, "SOLO ES PERMITIDO 11 CARACTERES");
        }
    }//GEN-LAST:event_txtrucKeyTyped

    private void comboBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btneditar;
    private javax.swing.JToggleButton btnnuevo;
    private javax.swing.JButton bustSalir;
    private javax.swing.JButton butGuardar;
    private javax.swing.JCheckBox cbruc;
    private javax.swing.JComboBox<String> comboBuscar;
    private javax.swing.JComboBox<String> comoboDistrito;
    private javax.swing.ButtonGroup grSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton rbFemenino;
    private javax.swing.JRadioButton rbmasculino;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtruc;
    // End of variables declaration//GEN-END:variables
}
