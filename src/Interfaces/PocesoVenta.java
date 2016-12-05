/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Consultas.vccliente;
import Consultas.vcempleado;
import Consultas.vcproducto;
import Consultas.vcventas;
import DATOS.dventas;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author SOFTWARE
 */
public class PocesoVenta extends javax.swing.JFrame {

    /**
     * Creates new form PocesoVenta
     */
    public String user;
    public dventas objdataventa=new dventas();
    public vcventas objventas=new vcventas();
    public vcempleado objemp=new vcempleado();
    public vccliente objcli=new vccliente();
    public vcproducto objpro=new vcproducto();
    public SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    public String[] ceros=new String[10];
    public String codcli[]=objcli.listacod_cli();
    public String[] codpro=objpro.codproducto();
    String codeemps;
    public int n,cantidad,filadelatabla;
    public double total,preparcial;
    DefaultTableModel tablapro;
    public String cod_venta,cod_pro,proaleminar;
        
    String[] titulos ={"COD PRODUC","DESCRIPCION","PRECIO","CANTIDAD","TOTAL PARCIAL"};
    
    
    public PocesoVenta(String user) {
        this.user=user;
        initComponents();
        llenadocombopro();
        fechayemp();
        arreglo();
        tablapro=new DefaultTableModel(null,titulos);
    }
    public String comparar(){
        codeemps="";
        n=0;
        
        while(codeemps.equals("")){
            if(comboBuscar.getSelectedIndex()==n){
                codeemps=codcli[n];
            }
            else{
                codeemps="";
                n++;
            }  
        }
        return codeemps;
    }
    public String compararpro(){
        String codpros="";
        int n=0;
        while(codpros.equals("")){
            if(comboBuscarProdu.getSelectedIndex()==n){
                codpros=codpro[n];
            }
            else{
                codpros="";
                n++;
            }
            
        }
        return codpros;
    }
    public void llenadocombopro(){
        DefaultComboBoxModel combo=objpro.nombrepro_preu();
        DefaultComboBoxModel combocli=objcli.listaclienom();
        comboBuscarProdu.setModel(combo);
        comboBuscar.setModel(combocli);
    }
    public void fechayemp(){
        Date fechaactual=new Date();
        lblempleado.setText(objemp.codigoempuser(this.user));
        lblfecha.setText(String.valueOf(sdf.format(fechaactual)));
    }
    public void arreglo(){
        String cero="0";
        ceros[0]="";
        ceros[1]=cero;
        ceros[2]=cero+cero;
        ceros[3]=cero+cero+cero;
        ceros[4]=cero+cero+cero+cero;
        ceros[5]=cero+cero+cero+cero+cero;
        ceros[6]=cero+cero+cero+cero+cero+cero;
        ceros[7]=cero+cero+cero+cero+cero+cero+cero;
        ceros[8]=cero+cero+cero+cero+cero+cero+cero+cero;
        ceros[9]=cero+cero+cero+cero+cero+cero+cero+cero+cero;
    }
    public void nuevaventaveri(){
        if(btnnuevo.isSelected()){
            if(rbfactura.isSelected()){
                String f="F",codventa="";
                int c=10-objventas.nueva_venta(f).length();;
                if(c<10 && c>0){
                    codventa=f+ceros[c]+objventas.nueva_venta(f);
                    lblcodcompro.setText(codventa);
                    cod_venta=f+objventas.nueva_venta(f);
                }
                rbfactura.setEnabled(false);
                rbboleta.setEnabled(false);
                btnnuevo.setEnabled(false);
                butAgregar.setEnabled(true);
                butEliminar.setEnabled(true);
            }
            else if(rbboleta.isSelected()){
                String b="B",codventa="";
                int c =10-objventas.nueva_venta(b).length();
                if(c<10 && c>0){
                    codventa=b+ceros[c]+objventas.nueva_venta(b);
                    lblcodcompro.setText(codventa);
                    cod_venta=b+objventas.nueva_venta(b);
                    
                }
                rbfactura.setEnabled(false);
                rbboleta.setEnabled(false);
                btnnuevo.setEnabled(false);
                butAgregar.setEnabled(true);
                butEliminar.setEnabled(true);
            }
            else{
                JOptionPane.showMessageDialog(null,"SELECIONA ENTRE BOLE O FACTUARA PARA CONTINUAR LA COMPRA" );
                btnnuevo.setSelected(false);
            }
            
        }
        else{
            lblcodcompro.setText("");
            btnnuevo.setSelected(false);
            butAgregar.setEnabled(false);
            butEliminar.setEnabled(false);
        }
    }

    public void datosdelcli(){
        
        
        String[] datoscli=objcli.buscar_cli_nom(comparar());
        
        txtcod.setText(datoscli[0]);
        txtnombre.setText(datoscli[1]);
        txtApellido.setText(datoscli[2]);
        txtFono.setText(datoscli[3]);
        txtDNI.setText(datoscli[4]);
        if(datoscli[6].equals("F")){
            rbFemenino.setSelected(true);
        }
        else if (datoscli[6].equals("H")){
            rbmasculino.setSelected(true);
        }
        txtdistrito.setText(datoscli[7]);
        txtDirec.setText(datoscli[8]);
    }
    public void agregar_productos(){
        String[] datosproducto=new String[6];
        String[] productosbase=objpro.regrosdatospro1(compararpro());
        cantidad=(int)spncanti.getValue();
        preparcial=Double.valueOf(productosbase[2])*cantidad;
        total=total+preparcial;
        datosproducto[0]=productosbase[0];
        datosproducto[1]=productosbase[1];
        datosproducto[2]=productosbase[2];
        datosproducto[3]=String.valueOf(cantidad);
        datosproducto[4]=String.valueOf(preparcial);
        cod_pro=datosproducto[0];

        tablapro.addRow(datosproducto);
        lbltotal.setText(String.valueOf(total));
        
    }
    public void datoscabe(){
       objdataventa.setCod_cabe(cod_venta);
       objdataventa.setCod_cli(txtcod.getText());
       objdataventa.setNombre(txtnombre.getText());
       objdataventa.setFecha(lblfecha.getText());
       objdataventa.setTotal(total);
       objdataventa.setCod_emp(lblempleado.getText());
       
    }
    public void datosdetalle(){
        objdataventa.setCod_cabe(cod_venta);
        objdataventa.setCod_pro(cod_pro);
        objdataventa.setCanti(cantidad);
        objdataventa.setParcial(preparcial);
    }
    
    public void limpiar(){
        txtApellido.setText(null);
        txtDNI.setText(null);
        txtFono.setText(null);
        txtDirec.setText(null);
        txtcod.setText(null);
        txtnombre.setText(null);
        txtdistrito.setText(null);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        btngrupsexo = new javax.swing.ButtonGroup();
        btngruptipocomprobante = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        comboBuscar = new javax.swing.JComboBox<>();
        txtbuscarcli = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        btncancelarcompra = new javax.swing.JButton();
        butEliminar = new javax.swing.JButton();
        bustSalir = new javax.swing.JButton();
        btnnuevo = new javax.swing.JToggleButton();
        btnempezar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaproductos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        butAgregar = new javax.swing.JButton();
        comboBuscarProdu = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        spncanti = new javax.swing.JSpinner();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtFono = new javax.swing.JTextField();
        txtDNI = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rbmasculino = new javax.swing.JRadioButton();
        rbFemenino = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtApellido = new javax.swing.JTextField();
        txtDirec = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        rbboleta = new javax.swing.JRadioButton();
        rbfactura = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblempleado = new javax.swing.JLabel();
        txtdistrito = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        lblcodcompro = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbltotal = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jLabel2.setFont(new java.awt.Font("Agency FB", 0, 36)); // NOI18N
        jLabel2.setText("REGISTRO VENTAS");

        jLabel5.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 0, 14))); // NOI18N
        jPanel2.setToolTipText("");

        comboBuscar.setEnabled(false);
        comboBuscar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBuscarItemStateChanged(evt);
            }
        });

        txtbuscarcli.setText("Buscar");
        txtbuscarcli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarcliActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboBuscar, 0, 160, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtbuscarcli)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtbuscarcli)))
        );

        btncancelarcompra.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        btncancelarcompra.setText("CANCELAR ");
        btncancelarcompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarcompraActionPerformed(evt);
            }
        });

        butEliminar.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        butEliminar.setText("Quitar Producto");
        butEliminar.setEnabled(false);
        butEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEliminarActionPerformed(evt);
            }
        });

        bustSalir.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        bustSalir.setText("Salir");
        bustSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bustSalirActionPerformed(evt);
            }
        });

        btnnuevo.setText("NUEVO");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnempezar.setText("COMENZAR VENTA");
        btnempezar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnempezarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btncancelarcompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(butEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bustSalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 11, Short.MAX_VALUE))
                    .addComponent(btnnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnempezar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnnuevo)
                .addGap(18, 18, 18)
                .addComponent(btnempezar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(btncancelarcompra)
                .addGap(90, 90, 90)
                .addComponent(butEliminar)
                .addGap(92, 92, 92)
                .addComponent(bustSalir)
                .addGap(40, 40, 40))
        );

        tablaproductos.setAutoCreateRowSorter(true);
        tablaproductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "COD PRODUC", "DESCRIPCION", "PRECIO", "CANTIDAD", "TOTAL"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaproductosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tablaproductos);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar Producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial Narrow", 0, 14))); // NOI18N
        jPanel3.setToolTipText("");

        butAgregar.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        butAgregar.setText("Agregar");
        butAgregar.setEnabled(false);
        butAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAgregarActionPerformed(evt);
            }
        });

        comboBuscarProdu.setEditable(true);

        jLabel11.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel11.setText("Cantidad:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboBuscarProdu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spncanti, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(butAgregar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butAgregar)
                    .addComponent(comboBuscarProdu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(spncanti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jLabel3.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel3.setText("Apellidos:");

        jLabel4.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel4.setText("Codigo:");

        txtcod.setEditable(false);
        txtcod.setEnabled(false);
        txtcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel1.setText("Nombre:");

        txtFono.setEditable(false);
        txtFono.setEnabled(false);
        txtFono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFonoActionPerformed(evt);
            }
        });

        txtDNI.setEditable(false);
        txtDNI.setEnabled(false);
        txtDNI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIActionPerformed(evt);
            }
        });

        txtnombre.setEnabled(false);
        txtnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombreActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel6.setText("Distrito:");

        jLabel7.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel7.setText("Direccion:");

        jLabel8.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel8.setText("DNI:");

        btngrupsexo.add(rbmasculino);
        rbmasculino.setText("Masculino");
        rbmasculino.setEnabled(false);
        rbmasculino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbmasculinoActionPerformed(evt);
            }
        });

        btngrupsexo.add(rbFemenino);
        rbFemenino.setText("Femenino");
        rbFemenino.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel9.setText("Telèfono:");

        jLabel10.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jLabel10.setText("Sexo:");

        txtApellido.setEditable(false);
        txtApellido.setEnabled(false);
        txtApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoActionPerformed(evt);
            }
        });

        txtDirec.setEditable(false);
        txtDirec.setEnabled(false);
        txtDirec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDirecActionPerformed(evt);
            }
        });

        jLabel13.setText("FECHA : ");

        lblfecha.setText("---------------------------");

        btngruptipocomprobante.add(rbboleta);
        rbboleta.setText("Boleta");

        btngruptipocomprobante.add(rbfactura);
        rbfactura.setText("Factura");

        jLabel14.setText("TIPO DE COMPROBANTE");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbfactura)
                    .addComponent(rbboleta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbfactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbboleta)
                .addGap(25, 25, 25))
        );

        jLabel15.setText("EMPLEADO: ");

        lblempleado.setText("----------");

        txtdistrito.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtnombre, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtApellido, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDirec, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblempleado, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFono, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtdistrito, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(rbmasculino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbFemenino)))
                        .addContainerGap(27, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(lblempleado))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDirec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtdistrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtFono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(rbmasculino)
                    .addComponent(rbFemenino))
                .addGap(19, 19, 19))
        );

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("FACTURA/BOLETA");

        lblcodcompro.setText("---------------");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel16.setText("TOTAL");

        lbltotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lbltotal.setText("S/.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel16))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblcodcompro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(lblcodcompro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(243, 243, 243)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbltotal)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDNIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIActionPerformed

    private void txtApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoActionPerformed

    private void txtDirecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDirecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDirecActionPerformed

    private void txtcodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodActionPerformed

    private void txtFonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFonoActionPerformed

    private void txtnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombreActionPerformed

    private void rbmasculinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbmasculinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbmasculinoActionPerformed

    private void bustSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bustSalirActionPerformed
        // TODO add your handling code here:
        Principal.menuitemventa.setEnabled(true);
        this.dispose();
    }//GEN-LAST:event_bustSalirActionPerformed

    private void butEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEliminarActionPerformed

        objventas.eliminarpro(proaleminar);
        total=total-Double.valueOf(tablaproductos.getValueAt(filadelatabla, 4).toString());
        tablapro.removeRow(filadelatabla);
        tablaproductos.setModel(tablapro);
        lbltotal.setText(String.valueOf(total));
        
        objventas.actualizatotal1(total,cod_venta);
    }//GEN-LAST:event_butEliminarActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        nuevaventaveri();
        txtnombre.setEnabled(true);
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btncancelarcompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarcompraActionPerformed
        // TODO add your handling code here:
        lblcodcompro.setText(null);
        rbfactura.setEnabled(true);
        rbboleta.setEnabled(true);
        btnnuevo.setEnabled(true);
        btnempezar.setEnabled(true);
        limpiar();
        DefaultTableModel tablabacia= new DefaultTableModel();
        tablaproductos.setModel(tablabacia);
        
        objventas.eliminarcompra(cod_venta);
        
    }//GEN-LAST:event_btncancelarcompraActionPerformed

    private void butAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAgregarActionPerformed
        // TODO add your handling code here:
        agregar_productos();
        tablaproductos.setModel(tablapro);
        datosdetalle();
        datoscabe();
        if(btnnuevo.isSelected() && txtnombre.getText().trim().length()>0){
            if(objventas.ingreventadetalle(objdataventa)){
                System.out.print("\nBien agregado");
                objventas.actualizatotal(objdataventa);
                
            }
            else{
                JOptionPane.showMessageDialog(null, "HA OCURRIDO UN ERROR");
            }
            
        }
        else{
            JOptionPane.showMessageDialog(null, "NO HAY NINGUN NOMBRE DE CLIENTE");
        }
        
    }//GEN-LAST:event_butAgregarActionPerformed

    private void txtbuscarcliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarcliActionPerformed
        // TODO add your handling code here:
        
        if(txtbuscarcli.isSelected()){
            comboBuscar.setEnabled(true);
            txtnombre.setEnabled(false);
        }
        else{
            comboBuscar.setEnabled(false);
            limpiar();
        }
    }//GEN-LAST:event_txtbuscarcliActionPerformed

    private void comboBuscarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBuscarItemStateChanged
        // TODO add your handling code here:
        datosdelcli();
    }//GEN-LAST:event_comboBuscarItemStateChanged

    private void btnempezarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnempezarActionPerformed
        // TODO add your handling code here:
        if(btnnuevo.isSelected() && txtnombre.getText().trim().length()>0){
            datoscabe();
            if(objventas.ingresoventacabe(objdataventa)){
                JOptionPane.showMessageDialog(null, "AHORA ELIJE LOS PRODUCTOS A COMPRAR");
            }
            else{
                JOptionPane.showMessageDialog(null, "ha ocurrido un error");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "NO HAY NINGUN NOMBRE DE CLIENTE");
        }
    }//GEN-LAST:event_btnempezarActionPerformed

    private void tablaproductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaproductosMouseClicked
        // TODO add your handling code here:
        filadelatabla=tablaproductos.rowAtPoint(evt.getPoint());
        proaleminar=tablaproductos.getValueAt(filadelatabla, 0).toString();
        
    }//GEN-LAST:event_tablaproductosMouseClicked

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
            java.util.logging.Logger.getLogger(PocesoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PocesoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PocesoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PocesoVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PocesoVenta(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancelarcompra;
    private javax.swing.JButton btnempezar;
    private javax.swing.ButtonGroup btngrupsexo;
    private javax.swing.ButtonGroup btngruptipocomprobante;
    private javax.swing.JToggleButton btnnuevo;
    private javax.swing.JButton bustSalir;
    private javax.swing.JButton butAgregar;
    private javax.swing.JButton butEliminar;
    private javax.swing.JComboBox<String> comboBuscar;
    private javax.swing.JComboBox<String> comboBuscarProdu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblcodcompro;
    private javax.swing.JLabel lblempleado;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JLabel lbltotal;
    private javax.swing.JRadioButton rbFemenino;
    private javax.swing.JRadioButton rbboleta;
    private javax.swing.JRadioButton rbfactura;
    private javax.swing.JRadioButton rbmasculino;
    private javax.swing.JSpinner spncanti;
    private javax.swing.JTable tablaproductos;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDirec;
    private javax.swing.JTextField txtFono;
    private javax.swing.JToggleButton txtbuscarcli;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtdistrito;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
