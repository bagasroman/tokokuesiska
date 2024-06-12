/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax. swing. *;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author admin
 */
public class tampilanAwal extends javax.swing.JFrame {

    /**
     * Creates new form tampilanAwal
     */
    
   Connection con = null;
   ResultSet rs = null;
   Statement statement = null;
   PreparedStatement pst, pst2;
ResultSet rst;
int istok, istok2, iharga, ijumlah, kstok, tstok;
String harga, barang, dbarang, KD, jam, tanggals,ssub, idbarang;
String tanggal2, sql;
   
   void updateTableauto() throws SQLException{
       rs = statement.executeQuery("select * from barang");
       Tdatabarang.setModel(DbUtils.resultSetToTableModel(rs));
   }
   
           
    void updateTableautoakun() throws SQLException{
       rs = statement.executeQuery("select id, email, status from akun");
       DPtable.setModel(DbUtils.resultSetToTableModel(rs));
   }        
   
   void updateTableautotransaksi() throws SQLException{
       rs = statement.executeQuery("select * from barang");
       jTableTransaksi.setModel(DbUtils.resultSetToTableModel(rs));
   }
   void updateTableautoDS() throws SQLException{
       rs = statement.executeQuery("select * from data_supplier");
       DStable.setModel(DbUtils.resultSetToTableModel(rs));
   }
   
    void updateTableautoTBDP() throws SQLException{
       rs = statement.executeQuery("select * from data_pelanggan");
       TBDP.setModel(DbUtils.resultSetToTableModel(rs));
   }
    
    void updateTableautobel() throws SQLException{
       rs = statement.executeQuery("select * from data_barang_supplier");
       beltable.setModel(DbUtils.resultSetToTableModel(rs));
   }
   
   void kosong(){
       
       tfNamaBarang.setText("");
       tfDBStok.setText("");
       tfDBSatuan.setText("");
       tfDBHarga.setText("");
       tfDBKodeBarang.requestFocus();
   }
   
  
   
   void buttonfalse(){
       belclear.setEnabled(false);
       belsimpan.setEnabled(false);
     
       belhapus.setEnabled(false);
     
       DBhapus.setEnabled(false);
       DSclear.setEnabled(false);
       DSEdit.setEnabled(false);
       DSHapus.setEnabled(false);
       DSSimpan.setEnabled(false);
       DBedit.setEnabled(false);
       DBhapus.setEnabled(false);
       DBsimpan.setEnabled(false);
       DBclear.setEnabled(false);
   }
   
   void buttonfalsebeforeselect(){
       
     
     
       belhapus.setEnabled(false);
      
       DBhapus.setEnabled(false);
     
       DSEdit.setEnabled(false);
       DSHapus.setEnabled(false);
      
       DBedit.setEnabled(false);
       DBhapus.setEnabled(false);
     
   }
   
  public void buttontrue(){
       belclear.setEnabled(true);
       belsimpan.setEnabled(true);
      
       belhapus.setEnabled(true);
      
       DBhapus.setEnabled(true);
       DSclear.setEnabled(true);
       DSEdit.setEnabled(true);
       DSHapus.setEnabled(true);
       DSSimpan.setEnabled(true);
       DBedit.setEnabled(true);
       DBhapus.setEnabled(true);
       DBsimpan.setEnabled(true);
       DBclear.setEnabled(true);
   }
   
   void DSkosong(){
    DS_idbarang.setText("");
    DSnama.setText("");
    DStanggal.setDate(null);
    DSalamat.setText("");
    DSnotlpn.setText("");
    DSemail.setText("");
   
   }
   
  
   
   void belkosong(){


    belbarang.setText("");
    belharga.setText("");
    belpcs.setText("");

   }
   
   void kosongDPP(){
       DPPid.setText("default auto number");
       penggunanama.setText("");
       penggunapassword.setText("");
   }
   
   
    private void userLogin(){
    lbluserlogin.setText(userSession.getUserLogin());
    }
    
    private void hakakses() {
    String user=lbluserlogin.getText();
        if(user.equals("admin")){
       
        jDataBarang.setEnabled(true);
        jDataSupplier.setEnabled(true);
        jPembelianBarang.setEnabled(true);
        jTransaksi.setEnabled(true);
        jLaporanTransaksi.setEnabled(true);
        }
        else {
        
        jDataBarang.setEnabled(true);
        jDataSupplier.setEnabled(true);
        jPembelianBarang.setEnabled(true);
        jTransaksi.setEnabled(true);
        jLaporanTransaksi.setEnabled(false);
        }
        }
    
   
    
public tampilanAwal() throws SQLException {
        initComponents();
//        buttonfalse();
//buttonfalsebeforeselect();
//userLogin();
//hakakses();   
      con = koneksi.koneksiDb();     
      statement = con.createStatement();
//        delay();
        updateTableautobel();
        updateTableautotransaksi();
        updateTableautoTBDP();
        updateTableautoakun();
        cari();
        detail();    
        autonumber();
        sum();
        listed();
        listedDS();
        listedbel();
        listedDP();
        listedAkun();
        kode_barang_otomatis();
        kode_barang_supplier();
        kode_barang_pembelian();
        setExtendedState(JFrame.MAXIMIZED_HORIZ);
        setVisible(true);
        setResizable(false);
        jDashboard.setBackground(new Color(204,204,255));
        jDataBarang.setBackground(new Color(204,204,255));
        jDataSupplier.setBackground(new Color(204,204,255));
        jPembelianBarang.setBackground(new Color(204,204,255));
        jTransaksi.setBackground(new Color(204,204,255));
        jLaporanTransaksi.setBackground(new Color(204,204,255));
        jDataPelanggan.setBackground(new Color(204,204,255));
        TABpengguna.setBackground(new Color(204,204,255));
        jExit.setBackground(new Color(204,204,255));
        setTanggal();
        Tampil_Jam();
    

    }
    
    public String Kodesu, Namasu;

    public String Kodesup() {
        return Kodesu;
    }
    public String Namasup() {
        return Namasu;
    }
    
    public void setTanggal(){
java.util.Date skrg = new java.util.Date();
java.text.SimpleDateFormat kal = new
java.text.SimpleDateFormat("yyyy-MM-dd");
tanggal.setText(kal.format(skrg));
}
    
   public void Tampil_Jam(){
        ActionListener taskPerformer = new ActionListener() {
 
        @Override
            public void actionPerformed(ActionEvent evt) {
            String nol_jam = "", nol_menit = "",nol_detik = "";
 
            java.util.Date dateTime = new java.util.Date();
            int nilai_jam = dateTime.getHours();
            int nilai_menit = dateTime.getMinutes();
            int nilai_detik = dateTime.getSeconds();
 
            if(nilai_jam <= 9) nol_jam= "0";
            if(nilai_menit <= 9) nol_menit= "0";
            if(nilai_detik <= 9) nol_detik= "0";
 
            String jam = nol_jam + Integer.toString(nilai_jam);
            String menit = nol_menit + Integer.toString(nilai_menit);
            String detik = nol_detik + Integer.toString(nilai_detik);
 
            ljam.setText(jam+":"+menit+":"+detik+"");
            }
        };
    new Timer(1000, taskPerformer).start();
    }   
 
   public void listed()
    {
        DefaultTableModel table = new DefaultTableModel();
     
        table.addColumn("Kode Barang");
        table.addColumn("Nama Barang");
        table.addColumn("Stok");
        table.addColumn("Satuan");
        table.addColumn("Harga");
        
        try
        {
            Connection kon = koneksi.koneksiDb();
            String sql = "SELECT * FROM barang";
            Statement S = kon.createStatement();
            ResultSet R = S.executeQuery(sql);
            
            while(R.next())
            {
                table.addRow(new Object[]{
                    R.getString(1),
                    R.getString(2),
                    R.getString(3),
                    R.getString(4),                
                    R.getString(5),
                    
                });
            }
            Tdatabarang.setModel(table);
        }
        catch(Exception e){
    
}
    }
   
   public void listedDP()
    {
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Id");
        table.addColumn("Nama");
        table.addColumn("Email");
        table.addColumn("No HP");
        table.addColumn("Alamat");
   
        
        try
        {
            Connection kon = koneksi.koneksiDb();
            String sql = "SELECT * FROM `data_pelanggan`";
            Statement S = kon.createStatement();
            ResultSet R = S.executeQuery(sql);
            
            while(R.next())
            {
                table.addRow(new Object[]{
                    R.getString(1),
                    R.getString(2),
                    R.getString(3),
                    R.getString(4),
                    R.getString(5),
                  
                });
            }
            TBDP.setModel(table);
        }
        catch(SQLException e){
    
}
    }
   
   public void listedDS()
    {
        DefaultTableModel table = new DefaultTableModel();
      
         table.addColumn("Kode Barang");
        table.addColumn("Nama Supplier");
        table.addColumn("Tanggal");
        table.addColumn("Alamat");
        table.addColumn("No Tlpn");
        table.addColumn("Email");
      
        
        
        try
        {
            Connection kon = koneksi.koneksiDb();
            String sql = "SELECT * FROM `data_supplier`";
            Statement S = kon.createStatement();
            ResultSet R = S.executeQuery(sql);
            
            while(R.next())
            {
                table.addRow(new Object[]{
                    R.getString(1),
                    R.getString(2),
                    R.getString(3),
                    R.getString(4),                
                    R.getString(5),
                    R.getString(6),
                   
                });
            }
            DStable.setModel(table);
        }
        catch(Exception e){
     System.out.println(e.getMessage());
}
    }
   
  
   
   public void listedbel()
    {
        DefaultTableModel table = new DefaultTableModel();
       
        table.addColumn("Kode Barang");
        table.addColumn("Nama Barang");
        table.addColumn("Harga");
        table.addColumn("Satuan");
        table.addColumn("Stok");
      
        try
        {
            Connection kon = koneksi.koneksiDb();
            String sql = "SELECT * FROM `data_barang_supplier`";
            Statement S = kon.createStatement();
            ResultSet R = S.executeQuery(sql);
            
            while(R.next())
            {
                table.addRow(new Object[]{
                    R.getString(1),
                    R.getString(2),
                    R.getString(3),
                    R.getString(4),                
                    R.getString(5),
                   
                  
                });
            }
            beltable.setModel(table);
        }
        catch(Exception e){
     System.out.println(e.getMessage());
}
    }
   
   
public void listedAkun()
    {
        DefaultTableModel table = new DefaultTableModel();
       
        table.addColumn("Id");
        table.addColumn("Email");
        table.addColumn("Status");
    
      
        try
        {
            Connection kon = koneksi.koneksiDb();
            String sql = "SELECT id, email, status FROM akun";
            Statement S = kon.createStatement();
            ResultSet R = S.executeQuery(sql);
            
            while(R.next())
            {
                table.addRow(new Object[]{
                    R.getString(1),
                    R.getString(2),
                    R.getString(3),
                              
                  
                });
            }
            DPtable.setModel(table);
        }
        catch(Exception e){
     System.out.println(e.getMessage());
}
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        Jam = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tanggal = new javax.swing.JLabel();
        ljam = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jDashboard = new javax.swing.JButton();
        jDataBarang = new javax.swing.JButton();
        jDataSupplier = new javax.swing.JButton();
        jPembelianBarang = new javax.swing.JButton();
        jTransaksi = new javax.swing.JButton();
        jLaporanTransaksi = new javax.swing.JButton();
        btentang = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jExit = new javax.swing.JButton();
        jDataPelanggan = new javax.swing.JButton();
        TABpengguna = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        pnldashboard = new javax.swing.JLabel();
        pnlpembelianbarang = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        belharga = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        bel_kodebarang = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        belpcs = new javax.swing.JTextField();
        belsearch = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        beltable = new javax.swing.JTable();
        belclear = new javax.swing.JButton();
        belsimpan = new javax.swing.JButton();
        belhapus = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        belbarang = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        PBSatuan = new javax.swing.JTextField();
        pnlpenjualanbarang = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        Tsearch = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        Tjumlah = new javax.swing.JTextField();
        Tdiscount = new javax.swing.JTextField();
        Ttambah = new javax.swing.JButton();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel47 = new javax.swing.JLabel();
        Tkodetransaksi = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        Thapus = new javax.swing.JButton();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        Tkembalian = new javax.swing.JTextField();
        Tbayar = new javax.swing.JTextField();
        Ttotal = new javax.swing.JTextField();
        TBbayar = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableTransaksi = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        pnldatasupplier = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        DSnama = new javax.swing.JTextField();
        DSnotlpn = new javax.swing.JTextField();
        DSemail = new javax.swing.JTextField();
        tfDSSearch = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        DSclear = new javax.swing.JButton();
        DSEdit = new javax.swing.JButton();
        DSSimpan = new javax.swing.JButton();
        DSHapus = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        DSalamat = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        DS_idbarang = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        DS_databarangsup = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        DStable = new javax.swing.JTable();
        DStanggal = new com.toedter.calendar.JDateChooser();
        pnldatabarang = new javax.swing.JPanel();
        ldatabarang = new javax.swing.JLabel();
        DBsimpan = new javax.swing.JButton();
        DBedit = new javax.swing.JButton();
        DBhapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tdatabarang = new javax.swing.JTable();
        tfDBStok = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfDBSatuan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfDBHarga = new javax.swing.JTextField();
        tfSearch = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfDBKodeBarang = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        DBclear = new javax.swing.JButton();
        tfNamaBarang = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        pnllaporantransaksi = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        FLcetak = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        pnldatapelanggan = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        DPnama = new javax.swing.JTextField();
        DPno = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        DPalamat = new javax.swing.JTextArea();
        btnsimpan = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btnhapus = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        DPemail = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        DPid = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TBDP = new javax.swing.JTable();
        btDPclear = new javax.swing.JButton();
        pnlpengguna = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        penggunanama = new javax.swing.JTextField();
        penggunapassword = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        DPtable = new javax.swing.JTable();
        CBstatus = new javax.swing.JComboBox<>();
        DPsimpan = new javax.swing.JButton();
        DPedit = new javax.swing.JButton();
        DPhapus = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        DPPid = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        lbluserlogin = new javax.swing.JLabel();

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 0));

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        Jam.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 204));
        jLabel1.setText("Toko Kue Siska");

        tanggal.setBackground(new java.awt.Color(255, 255, 255));
        tanggal.setForeground(new java.awt.Color(255, 51, 204));
        tanggal.setText("tanggal");
        tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tanggalKeyPressed(evt);
            }
        });

        ljam.setForeground(new java.awt.Color(255, 51, 204));
        ljam.setText("jam");

        javax.swing.GroupLayout JamLayout = new javax.swing.GroupLayout(Jam);
        Jam.setLayout(JamLayout);
        JamLayout.setHorizontalGroup(
            JamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JamLayout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(149, 149, 149)
                .addGroup(JamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tanggal)
                    .addComponent(ljam))
                .addGap(44, 44, 44))
        );
        JamLayout.setVerticalGroup(
            JamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JamLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(JamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JamLayout.createSequentialGroup()
                        .addComponent(ljam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tanggal)))
                .addGap(15, 15, 15))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));
        jPanel1.setMaximumSize(new java.awt.Dimension(527, 527));

        jDashboard.setBackground(new java.awt.Color(255, 255, 255));
        jDashboard.setForeground(new java.awt.Color(255, 153, 153));
        jDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/form/icon/Continuous Mode.png"))); // NOI18N
        jDashboard.setText("Dashboard");
        jDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDashboardActionPerformed(evt);
            }
        });

        jDataBarang.setForeground(new java.awt.Color(255, 153, 153));
        jDataBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/form/icon/Checklist.png"))); // NOI18N
        jDataBarang.setText("Data Barang");
        jDataBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDataBarangActionPerformed(evt);
            }
        });

        jDataSupplier.setForeground(new java.awt.Color(255, 153, 153));
        jDataSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/form/icon/Mind Map.png"))); // NOI18N
        jDataSupplier.setText("Data Supplier");
        jDataSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDataSupplierActionPerformed(evt);
            }
        });

        jPembelianBarang.setForeground(new java.awt.Color(255, 153, 153));
        jPembelianBarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/form/icon/Finance Portal.png"))); // NOI18N
        jPembelianBarang.setText("Pembelian\nBarang");
        jPembelianBarang.setToolTipText("");
        jPembelianBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPembelianBarangActionPerformed(evt);
            }
        });

        jTransaksi.setForeground(new java.awt.Color(255, 153, 153));
        jTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/form/icon/Login.png"))); // NOI18N
        jTransaksi.setText("Transaksi");
        jTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTransaksiActionPerformed(evt);
            }
        });

        jLaporanTransaksi.setForeground(new java.awt.Color(255, 153, 153));
        jLaporanTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/form/icon/data user.png"))); // NOI18N
        jLaporanTransaksi.setText("Laporan Transaksi");
        jLaporanTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLaporanTransaksiActionPerformed(evt);
            }
        });

        btentang.setForeground(new java.awt.Color(255, 153, 153));
        btentang.setText("! Tentang");
        btentang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btentangActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/form/icon/Kawaicake.png"))); // NOI18N

        jExit.setForeground(new java.awt.Color(255, 153, 153));
        jExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/form/icon/Save as.png"))); // NOI18N
        jExit.setText("Exit");
        jExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jExitActionPerformed(evt);
            }
        });

        jDataPelanggan.setForeground(new java.awt.Color(255, 153, 153));
        jDataPelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/form/icon/data pelanggann.png"))); // NOI18N
        jDataPelanggan.setText("Data Pelanggan");
        jDataPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDataPelangganActionPerformed(evt);
            }
        });

        TABpengguna.setForeground(new java.awt.Color(255, 153, 153));
        TABpengguna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/form/icon/data user.png"))); // NOI18N
        TABpengguna.setText("Data Pengguna");
        TABpengguna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TABpenggunaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(btentang, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2))
                    .addComponent(jDataBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDataSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPembelianBarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDashboard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLaporanTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDataPelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TABpengguna, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btentang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDataBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDataSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPembelianBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLaporanTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDataPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TABpengguna, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jExit, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(188, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(247, 211, 223));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black, java.awt.Color.black));
        jPanel3.setLayout(new java.awt.CardLayout());

        pnldashboard.setBackground(new java.awt.Color(255, 204, 204));
        pnldashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/form/icon/backpink.png"))); // NOI18N
        jPanel3.add(pnldashboard, "card2");

        pnlpembelianbarang.setBackground(new java.awt.Color(255, 51, 102));

        jLabel6.setFont(new java.awt.Font("Lucida Grande", 3, 14)); // NOI18N
        jLabel6.setText("Pembelian Barang");

        jLabel21.setText("Kode Barang");

        belharga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                belhargaActionPerformed(evt);
            }
        });

        jLabel22.setText("Nama Barang");

        bel_kodebarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bel_kodebarangActionPerformed(evt);
            }
        });

        jLabel23.setText("Harga");

        jLabel24.setText("Pcs");

        belpcs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                belpcsActionPerformed(evt);
            }
        });

        belsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                belsearchActionPerformed(evt);
            }
        });
        belsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                belsearchKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                belsearchKeyPressed(evt);
            }
        });

        beltable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                beltableMouseClicked(evt);
            }
        });
        beltable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                beltableKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(beltable);

        belclear.setText("clear");
        belclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                belclearActionPerformed(evt);
            }
        });

        belsimpan.setText("simpan");
        belsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                belsimpanActionPerformed(evt);
            }
        });

        belhapus.setText("hapus");
        belhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                belhapusActionPerformed(evt);
            }
        });

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/form/icon/search.png"))); // NOI18N

        belbarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                belbarangActionPerformed(evt);
            }
        });

        jLabel19.setText("Satuan");

        javax.swing.GroupLayout pnlpembelianbarangLayout = new javax.swing.GroupLayout(pnlpembelianbarang);
        pnlpembelianbarang.setLayout(pnlpembelianbarangLayout);
        pnlpembelianbarangLayout.setHorizontalGroup(
            pnlpembelianbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlpembelianbarangLayout.createSequentialGroup()
                .addGroup(pnlpembelianbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlpembelianbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(pnlpembelianbarangLayout.createSequentialGroup()
                            .addGap(9, 9, 9)
                            .addGroup(pnlpembelianbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21)
                                .addComponent(bel_kodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlpembelianbarangLayout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(jLabel23)))
                    .addGroup(pnlpembelianbarangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlpembelianbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlpembelianbarangLayout.createSequentialGroup()
                                .addComponent(belclear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(belsimpan))
                            .addComponent(jLabel22)
                            .addComponent(jLabel24)
                            .addGroup(pnlpembelianbarangLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel19))
                            .addGroup(pnlpembelianbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(PBSatuan, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(belpcs, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                .addComponent(belbarang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                                .addComponent(belharga, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(pnlpembelianbarangLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(belhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(pnlpembelianbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlpembelianbarangLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(belsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        pnlpembelianbarangLayout.setVerticalGroup(
            pnlpembelianbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlpembelianbarangLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(pnlpembelianbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlpembelianbarangLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bel_kodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(belbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel23)
                        .addGap(1, 1, 1)
                        .addComponent(belharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PBSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(belpcs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlpembelianbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(belclear)
                            .addComponent(belsimpan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(belhapus))
                    .addGroup(pnlpembelianbarangLayout.createSequentialGroup()
                        .addGroup(pnlpembelianbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlpembelianbarangLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(belsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6)
                            .addComponent(jLabel38))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(312, Short.MAX_VALUE))
        );

        jPanel3.add(pnlpembelianbarang, "card5");

        pnlpenjualanbarang.setBackground(new java.awt.Color(153, 255, 255));

        jLabel7.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel7.setText("Transaksi");

        Tsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TsearchActionPerformed(evt);
            }
        });
        Tsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TsearchKeyPressed(evt);
            }
        });

        jLabel44.setText("Masukkan Nama Barang");

        Ttambah.setText("Tambahkan");
        Ttambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TtambahActionPerformed(evt);
            }
        });

        jLabel45.setText("Jumlah");

        jLabel46.setText("Discount");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode_Detail", "Kode_Barang", "Harga", "Jumlah", "Discount", "Subtotal"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable3);

        jLabel47.setText("Kode Transaksi");

        Tkodetransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TkodetransaksiActionPerformed(evt);
            }
        });

        jLabel48.setText("Data Barang Yang DiBeli");

        Thapus.setText("Hapus");
        Thapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ThapusActionPerformed(evt);
            }
        });

        jLabel49.setText("Total");

        jLabel50.setText("Bayar");

        jLabel51.setText("Kembalian");

        Tkembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TkembalianActionPerformed(evt);
            }
        });

        Tbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TbayarActionPerformed(evt);
            }
        });

        Ttotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TtotalActionPerformed(evt);
            }
        });

        TBbayar.setText("Bayar");
        TBbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TBbayarActionPerformed(evt);
            }
        });

        jTableTransaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode_Barang", "Nama_Barang", "Stok", "Satuan", "Harga"
            }
        ));
        jTableTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTransaksiMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTableTransaksi);

        jButton4.setText("Cari");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlpenjualanbarangLayout = new javax.swing.GroupLayout(pnlpenjualanbarang);
        pnlpenjualanbarang.setLayout(pnlpenjualanbarangLayout);
        pnlpenjualanbarangLayout.setHorizontalGroup(
            pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(411, 411, 411))
            .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                        .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                                        .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(jLabel45))
                                            .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel46)))
                                        .addGap(24, 24, 24)
                                        .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(Tjumlah)
                                            .addComponent(Tdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Ttambah))))
                            .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Tkodetransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                        .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Tsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel48))
                            .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                                        .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel50)
                                            .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                                                .addComponent(Thapus)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel49))
                                            .addComponent(jLabel51))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Ttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Tbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Tkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlpenjualanbarangLayout.createSequentialGroup()
                                        .addComponent(TBbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(17, 17, 17)))))
                        .addContainerGap(116, Short.MAX_VALUE))))
        );
        pnlpenjualanbarangLayout.setVerticalGroup(
            pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel44)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                        .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Tjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45))
                        .addGap(18, 18, 18)
                        .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Tdiscount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46))
                        .addGap(18, 18, 18)
                        .addComponent(Ttambah)
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlpenjualanbarangLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tkodetransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47))
                .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlpenjualanbarangLayout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlpenjualanbarangLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Thapus)
                            .addComponent(jLabel49)
                            .addComponent(Ttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50)
                            .addComponent(Tbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlpenjualanbarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(Tkembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TBbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(pnlpenjualanbarang, "card6");

        pnldatasupplier.setBackground(new java.awt.Color(204, 255, 204));

        jLabel5.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel5.setText("Data Supplier");

        jLabel12.setText("Nama Supplier");

        jLabel13.setText("Tanggal");

        jLabel14.setText("Alamat");

        jLabel15.setText("No Tlp");

        jLabel16.setText("Email");

        DSnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DSnamaActionPerformed(evt);
            }
        });

        tfDSSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfDSSearchKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfDSSearchKeyPressed(evt);
            }
        });

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/form/icon/search.png"))); // NOI18N

        DSclear.setText("clear");
        DSclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DSclearActionPerformed(evt);
            }
        });

        DSEdit.setText("edit");
        DSEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DSEditActionPerformed(evt);
            }
        });

        DSSimpan.setText("simpan");
        DSSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DSSimpanActionPerformed(evt);
            }
        });

        DSHapus.setText("hapus");
        DSHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DSHapusActionPerformed(evt);
            }
        });

        DSalamat.setColumns(20);
        DSalamat.setRows(5);
        jScrollPane4.setViewportView(DSalamat);

        jLabel18.setText("Kode Barang");

        DS_databarangsup.setModel(new javax.swing.table.DefaultTableModel(
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
        DS_databarangsup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DS_databarangsupMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(DS_databarangsup);

        DStable.setModel(new javax.swing.table.DefaultTableModel(
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
        DStable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DStableMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(DStable);

        javax.swing.GroupLayout pnldatasupplierLayout = new javax.swing.GroupLayout(pnldatasupplier);
        pnldatasupplier.setLayout(pnldatasupplierLayout);
        pnldatasupplierLayout.setHorizontalGroup(
            pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldatasupplierLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnldatasupplierLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(DSemail, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnldatasupplierLayout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addGroup(pnldatasupplierLayout.createSequentialGroup()
                                        .addComponent(DSclear)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DSSimpan))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnldatasupplierLayout.createSequentialGroup()
                                        .addComponent(DSEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DSHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(pnldatasupplierLayout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addGap(18, 18, 18)
                            .addComponent(DSnotlpn, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel14)
                    .addGroup(pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(DS_idbarang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                        .addComponent(DSnama, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(DStanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnldatasupplierLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(pnldatasupplierLayout.createSequentialGroup()
                        .addGroup(pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnldatasupplierLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(63, 63, 63)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tfDSSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnldatasupplierLayout.setVerticalGroup(
            pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldatasupplierLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnldatasupplierLayout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(3, 3, 3)
                        .addComponent(DS_idbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(pnldatasupplierLayout.createSequentialGroup()
                        .addGroup(pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnldatasupplierLayout.createSequentialGroup()
                                    .addComponent(tfDSSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(19, 19, 19)))
                            .addComponent(jLabel5))
                        .addGap(8, 8, 8)))
                .addGroup(pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnldatasupplierLayout.createSequentialGroup()
                        .addComponent(DSnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addGap(9, 9, 9)
                        .addComponent(DStanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(DSnotlpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DSemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DSclear)
                            .addComponent(DSSimpan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnldatasupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DSEdit)
                            .addComponent(DSHapus)))
                    .addGroup(pnldatasupplierLayout.createSequentialGroup()
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(240, Short.MAX_VALUE))
        );

        jPanel3.add(pnldatasupplier, "card4");

        pnldatabarang.setBackground(new java.awt.Color(255, 255, 204));

        ldatabarang.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        ldatabarang.setText("Data Barang");

        DBsimpan.setText("Simpan");
        DBsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DBsimpanActionPerformed(evt);
            }
        });

        DBedit.setText("Edit");
        DBedit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DBeditMouseClicked(evt);
            }
        });
        DBedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DBeditActionPerformed(evt);
            }
        });

        DBhapus.setText("Hapus");
        DBhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DBhapusActionPerformed(evt);
            }
        });

        Tdatabarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kode_Barang", "Nama_Barang", "Stok", "Satuan", "Harga"
            }
        ));
        Tdatabarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TdatabarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tdatabarang);

        jLabel3.setText("Stok");

        jLabel4.setText("Satuan");

        tfDBSatuan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfDBSatuanKeyTyped(evt);
            }
        });

        jLabel8.setText("Harga");

        tfDBHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfDBHargaKeyTyped(evt);
            }
        });

        tfSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSearchActionPerformed(evt);
            }
        });
        tfSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfSearchKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfSearchKeyPressed(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/form/icon/search.png"))); // NOI18N

        jLabel10.setText("Kode Barang");

        tfDBKodeBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfDBKodeBarangActionPerformed(evt);
            }
        });
        tfDBKodeBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfDBKodeBarangKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfDBKodeBarangKeyPressed(evt);
            }
        });

        jLabel11.setText("Nama Barang");

        DBclear.setText("Clear");
        DBclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DBclearActionPerformed(evt);
            }
        });

        tfNamaBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNamaBarangActionPerformed(evt);
            }
        });
        tfNamaBarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNamaBarangKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfNamaBarangKeyPressed(evt);
            }
        });

        jButton6.setText("Cetak Data");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnldatabarangLayout = new javax.swing.GroupLayout(pnldatabarang);
        pnldatabarang.setLayout(pnldatabarangLayout);
        pnldatabarangLayout.setHorizontalGroup(
            pnldatabarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldatabarangLayout.createSequentialGroup()
                .addGap(441, 441, 441)
                .addComponent(ldatabarang)
                .addGap(36, 36, 36)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnldatabarangLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnldatabarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnldatabarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(tfDBStok, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnldatabarangLayout.createSequentialGroup()
                            .addGap(5, 5, 5)
                            .addGroup(pnldatabarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)
                                .addComponent(tfDBKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(tfDBSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfDBHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnldatabarangLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8))
                    .addGroup(pnldatabarangLayout.createSequentialGroup()
                        .addGroup(pnldatabarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(DBedit)
                            .addComponent(DBclear))
                        .addGroup(pnldatabarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnldatabarangLayout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(DBhapus))
                            .addGroup(pnldatabarangLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(DBsimpan))))
                    .addComponent(tfNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(pnldatabarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addGap(89, 89, 89))
        );

        pnldatabarangLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {DBclear, DBedit, DBhapus, DBsimpan});

        pnldatabarangLayout.setVerticalGroup(
            pnldatabarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldatabarangLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDBKodeBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addGap(5, 5, 5)
                .addComponent(tfNamaBarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDBStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDBSatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfDBHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addGroup(pnldatabarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DBsimpan)
                    .addComponent(DBclear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnldatabarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DBhapus)
                    .addComponent(DBedit))
                .addGap(213, 213, 213))
            .addGroup(pnldatabarangLayout.createSequentialGroup()
                .addGroup(pnldatabarangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnldatabarangLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(ldatabarang))
                    .addGroup(pnldatabarangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9))
                    .addGroup(pnldatabarangLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(tfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.add(pnldatabarang, "card3");

        pnllaporantransaksi.setBackground(new java.awt.Color(204, 255, 204));

        jLabel29.setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        jLabel29.setText("Form Laporan");

        jLabel31.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel31.setText("Transaksi Sebelum Tanggal");

        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel32.setText("Transaksi Antara Tanggal");

        jButton2.setText("Cari");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel33.setText("Transaksi Setelah Tanggal");

        jButton3.setText("Cari");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        FLcetak.setText("Cetak");
        FLcetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FLcetakActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Agency FB", 1, 18)); // NOI18N
        jLabel25.setText("Transaksi Kurang Dari 3 Hari");

        jButton5.setText("Tampilkan");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnllaporantransaksiLayout = new javax.swing.GroupLayout(pnllaporantransaksi);
        pnllaporantransaksi.setLayout(pnllaporantransaksiLayout);
        pnllaporantransaksiLayout.setHorizontalGroup(
            pnllaporantransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnllaporantransaksiLayout.createSequentialGroup()
                .addContainerGap(373, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addGap(235, 235, 235))
            .addGroup(pnllaporantransaksiLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnllaporantransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addGroup(pnllaporantransaksiLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton5))
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addGroup(pnllaporantransaksiLayout.createSequentialGroup()
                        .addGroup(pnllaporantransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pnllaporantransaksiLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnllaporantransaksiLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(pnllaporantransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnllaporantransaksiLayout.createSequentialGroup()
                                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(pnllaporantransaksiLayout.createSequentialGroup()
                                        .addGroup(pnllaporantransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(pnllaporantransaksiLayout.createSequentialGroup()
                                                .addGap(7, 7, 7)
                                                .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pnllaporantransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButton3)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnllaporantransaksiLayout.createSequentialGroup()
                                                .addGap(229, 229, 229)
                                                .addComponent(jButton2)))))))
                        .addGap(46, 46, 46)
                        .addComponent(FLcetak)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnllaporantransaksiLayout.setVerticalGroup(
            pnllaporantransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnllaporantransaksiLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnllaporantransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnllaporantransaksiLayout.createSequentialGroup()
                        .addGroup(pnllaporantransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnllaporantransaksiLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGroup(pnllaporantransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FLcetak))
                                .addGap(41, 41, 41)
                                .addComponent(jLabel32))
                            .addGroup(pnllaporantransaksiLayout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)
                        .addGroup(pnllaporantransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.add(pnllaporantransaksi, "card7");

        pnldatapelanggan.setBackground(new java.awt.Color(153, 255, 204));

        jLabel30.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel30.setText("Data Pelanggan");

        jLabel40.setText("Nama");

        jLabel41.setText("Alamat");

        jLabel42.setText("No HP");

        DPnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DPnamaActionPerformed(evt);
            }
        });

        DPalamat.setColumns(20);
        DPalamat.setRows(5);
        jScrollPane6.setViewportView(DPalamat);

        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnedit.setText("Edit");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
            }
        });

        btnhapus.setText("Hapus");
        btnhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnhapusActionPerformed(evt);
            }
        });

        jLabel28.setText("Email");

        DPemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DPemailActionPerformed(evt);
            }
        });

        jLabel20.setText("ID");

        DPid.setText("default auto number");

        TBDP.setModel(new javax.swing.table.DefaultTableModel(
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
        TBDP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBDPMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TBDP);

        btDPclear.setText("Clear");
        btDPclear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDPclearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnldatapelangganLayout = new javax.swing.GroupLayout(pnldatapelanggan);
        pnldatapelanggan.setLayout(pnldatapelangganLayout);
        pnldatapelangganLayout.setHorizontalGroup(
            pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnldatapelangganLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnldatapelangganLayout.createSequentialGroup()
                        .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnldatapelangganLayout.createSequentialGroup()
                                .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel40)
                                    .addComponent(jLabel28)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel42))
                                .addGap(25, 25, 25)
                                .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(DPno, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(DPid, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(DPemail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                                            .addComponent(DPnama, javax.swing.GroupLayout.Alignment.LEADING)))))
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE))
                    .addGroup(pnldatapelangganLayout.createSequentialGroup()
                        .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnldatapelangganLayout.createSequentialGroup()
                                .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnldatapelangganLayout.createSequentialGroup()
                                .addComponent(btDPclear, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(64, 64, 64)))
                .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnldatapelangganLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel30)
                        .addGap(324, 324, 324))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnldatapelangganLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );
        pnldatapelangganLayout.setVerticalGroup(
            pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnldatapelangganLayout.createSequentialGroup()
                .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnldatapelangganLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel30)
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnldatapelangganLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(DPid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnldatapelangganLayout.createSequentialGroup()
                        .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(DPnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(DPemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DPno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42))
                        .addGap(23, 23, 23)
                        .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41))
                        .addGap(42, 42, 42)
                        .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(pnldatapelangganLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnhapus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btDPclear, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnldatapelangganLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(265, Short.MAX_VALUE))
        );

        jPanel3.add(pnldatapelanggan, "card8");

        pnlpengguna.setBackground(new java.awt.Color(255, 255, 0));

        jLabel26.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel26.setText("Daftar Pengguna");

        jLabel27.setText("Email / username");

        jLabel34.setText("Password");

        jLabel35.setText("Status");

        penggunanama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penggunanamaActionPerformed(evt);
            }
        });

        penggunapassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                penggunapasswordActionPerformed(evt);
            }
        });

        DPtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DPtable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DPtableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(DPtable);

        CBstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));
        CBstatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBstatusActionPerformed(evt);
            }
        });

        DPsimpan.setText("Simpan");
        DPsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DPsimpanActionPerformed(evt);
            }
        });

        DPedit.setText("Edit");
        DPedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DPeditActionPerformed(evt);
            }
        });

        DPhapus.setText("Hapus");
        DPhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DPhapusActionPerformed(evt);
            }
        });

        jLabel36.setText("id");

        jButton7.setText("Clear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlpenggunaLayout = new javax.swing.GroupLayout(pnlpengguna);
        pnlpengguna.setLayout(pnlpenggunaLayout);
        pnlpenggunaLayout.setHorizontalGroup(
            pnlpenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlpenggunaLayout.createSequentialGroup()
                .addGroup(pnlpenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlpenggunaLayout.createSequentialGroup()
                        .addGap(441, 441, 441)
                        .addComponent(jLabel26))
                    .addGroup(pnlpenggunaLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(pnlpenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel35)
                            .addGroup(pnlpenggunaLayout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addGap(15, 15, 15)))
                        .addGap(18, 18, 18)
                        .addGroup(pnlpenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)
                            .addGroup(pnlpenggunaLayout.createSequentialGroup()
                                .addGroup(pnlpenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(DPPid, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(penggunanama, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CBstatus, javax.swing.GroupLayout.Alignment.LEADING, 0, 220, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlpenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlpenggunaLayout.createSequentialGroup()
                                        .addComponent(jLabel34)
                                        .addGap(35, 35, 35)
                                        .addComponent(penggunapassword, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(80, 80, 80))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlpenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlpenggunaLayout.createSequentialGroup()
                                            .addComponent(DPsimpan)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton7))
                                        .addGroup(pnlpenggunaLayout.createSequentialGroup()
                                            .addGap(195, 195, 195)
                                            .addComponent(DPhapus)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(DPedit))))))))
                .addGap(67, 67, 67))
        );
        pnlpenggunaLayout.setVerticalGroup(
            pnlpenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlpenggunaLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel26)
                .addGap(28, 28, 28)
                .addGroup(pnlpenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(DPPid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlpenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(penggunanama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(penggunapassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addGap(18, 18, 18)
                .addGroup(pnlpenggunaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(CBstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DPsimpan)
                    .addComponent(DPedit)
                    .addComponent(DPhapus)
                    .addComponent(jButton7))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        jPanel3.add(pnlpengguna, "card9");

        lbluserlogin.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        lbluserlogin.setForeground(new java.awt.Color(255, 153, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 869, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbluserlogin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Jam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Jam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbluserlogin))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDashboardActionPerformed
        // TODO add your handling code here:
//mengganti panel
pnldashboard.setVisible(true);
pnldatabarang.setVisible(false);
pnldatasupplier.setVisible(false);
pnlpembelianbarang.setVisible(false);
pnlpenjualanbarang.setVisible(false);
pnllaporantransaksi.setVisible(false);
pnldatapelanggan.setVisible(false);
pnlpengguna.setVisible(false);
//Warna tombol
jDashboard.setBackground(Color.white);
jDataBarang.setBackground(new Color(204,204,255));
jDataSupplier.setBackground(new Color(204,204,255));
jPembelianBarang.setBackground(new Color(204,204,255));
jTransaksi.setBackground(new Color(204,204,255));
jLaporanTransaksi.setBackground(new Color(204,204,255));
jDataPelanggan.setBackground(new Color(204,204,255));
pnlpengguna.setBackground(new Color(204,204,255));
jExit.setBackground(new Color(204,204,255));
//add panel baru
         
    }//GEN-LAST:event_jDashboardActionPerformed

    private void jPembelianBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPembelianBarangActionPerformed
        // TODO add your handling code here:
pnldashboard.setVisible(false);
pnldatabarang.setVisible(false);
pnldatasupplier.setVisible(false);
pnlpembelianbarang.setVisible(true);
pnlpenjualanbarang.setVisible(false);
pnllaporantransaksi.setVisible(false);
pnldatapelanggan.setVisible(false);
pnlpengguna.setVisible(false);
jDashboard.setBackground(new Color(204,204,255));
jDataBarang.setBackground(new Color(204,204,255));
jDataSupplier.setBackground(new Color(204,204,255));
jPembelianBarang.setBackground(Color.white);
jTransaksi.setBackground(new Color(204,204,255));
jLaporanTransaksi.setBackground(new Color(204,204,255));
jDataPelanggan.setBackground(new Color(204,204,255));
pnlpengguna.setBackground(new Color(204,204,255));
jExit.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_jPembelianBarangActionPerformed

    private void jTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTransaksiActionPerformed
        // TODO add your handling code here:
pnldashboard.setVisible(false);
pnldatabarang.setVisible(false);
pnldatasupplier.setVisible(false);
pnlpembelianbarang.setVisible(false);
pnlpenjualanbarang.setVisible(true);
pnllaporantransaksi.setVisible(false);
pnldatapelanggan.setVisible(false);
pnlpengguna.setVisible(false);
jDashboard.setBackground(new Color(204,204,255));
jDataBarang.setBackground(new Color(204,204,255));
jDataSupplier.setBackground(new Color(204,204,255));
jPembelianBarang.setBackground(new Color(204,204,255));
jTransaksi.setBackground(Color.white);
jLaporanTransaksi.setBackground(new Color(204,204,255));
jDataPelanggan.setBackground(new Color(204,204,255));
pnlpengguna.setBackground(new Color(204,204,255));
jExit.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_jTransaksiActionPerformed

    private void jLaporanTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLaporanTransaksiActionPerformed
        // TODO add your handling code here:
pnldashboard.setVisible(false);
pnldatabarang.setVisible(false);
pnldatasupplier.setVisible(false);
pnlpembelianbarang.setVisible(false);
pnlpenjualanbarang.setVisible(false);
pnllaporantransaksi.setVisible(true);
pnldatapelanggan.setVisible(false);        
  pnlpengguna.setVisible(false);      
        
jDashboard.setBackground(new Color(204,204,255));
jDataBarang.setBackground(new Color(204,204,255));
jDataSupplier.setBackground(new Color(204,204,255));
jPembelianBarang.setBackground(new Color(204,204,255));
jTransaksi.setBackground(new Color(204,204,255));
jLaporanTransaksi.setBackground(Color.white);
jDataPelanggan.setBackground(new Color(204,204,255));
pnlpengguna.setBackground(new Color(204,204,255));
jExit.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_jLaporanTransaksiActionPerformed

    private void btentangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btentangActionPerformed
        // TODO add your handling code here:
        tentang t = new tentang();
                t.setVisible(true);
        
    }//GEN-LAST:event_btentangActionPerformed

    private void jDataBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDataBarangActionPerformed
        // TODO add your handling code here:
pnldashboard.setVisible(false);
pnldatabarang.setVisible(true);
pnldatasupplier.setVisible(false);
pnlpembelianbarang.setVisible(false);
pnlpenjualanbarang.setVisible(false);
pnllaporantransaksi.setVisible(false);
pnldatapelanggan.setVisible(false);
pnlpengguna.setVisible(false);
jDashboard.setBackground(new Color(204,204,255));
jDataBarang.setBackground(Color.white);
jDataSupplier.setBackground(new Color(204,204,255));
jPembelianBarang.setBackground(new Color(204,204,255));
jTransaksi.setBackground(new Color(204,204,255));
jLaporanTransaksi.setBackground(new Color(204,204,255));
jDataPelanggan.setBackground(new Color(204,204,255));
pnlpengguna.setBackground(new Color(204,204,255));
jExit.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_jDataBarangActionPerformed

    private void jDataSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDataSupplierActionPerformed
        // TODO add your handling code here:
pnldashboard.setVisible(false);
pnldatabarang.setVisible(false);
pnldatasupplier.setVisible(true);
pnlpembelianbarang.setVisible(false);
pnlpenjualanbarang.setVisible(false);
pnllaporantransaksi.setVisible(false);
pnldatapelanggan.setVisible(false);
pnlpengguna.setVisible(false);
jDashboard.setBackground(new Color(204,204,255));
jDataBarang.setBackground(new Color(204,204,255));
jDataSupplier.setBackground(Color.white);
jPembelianBarang.setBackground(new Color(204,204,255));
jTransaksi.setBackground(new Color(204,204,255));
jLaporanTransaksi.setBackground(new Color(204,204,255));
jDataPelanggan.setBackground(new Color(204,204,255));
pnlpengguna.setBackground(new Color(204,204,255));
jExit.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_jDataSupplierActionPerformed

    private void tanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tanggalKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tanggalKeyPressed

    private void kode_barang_otomatis(){
        try{
           
            String sql = "SELECT * FROM barang order by Kode_Barang desc";
             Statement S = con.createStatement(); 
            ResultSet R = S.executeQuery(sql);
            
           if(R.next()){
               String kode= R.getString("Kode_Barang").substring(2);
               String AN = "" + (Integer.parseInt(kode) + 1);
               String Nol = "";
               
               if(AN.length()== 1)
               {Nol = "00";}
               else if(AN.length()==2)
               {Nol = "0";}
               else if(AN.length()==3)
               {Nol = "";}
               
               tfDBKodeBarang.setText("KD" + Nol + AN);
               
           }else{
               tfDBKodeBarang.setText("KD001");
           }
           
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    private void kode_barang_supplier(){
        try{
           
            String sql = "SELECT * FROM data_supplier order by Kode_Barang desc";
             Statement S = con.createStatement(); 
            ResultSet R = S.executeQuery(sql);
            
           if(R.next()){
               String kode= R.getString("Kode_Barang").substring(2);
               String AN = "" + (Integer.parseInt(kode) + 1);
               String Nol = "";
               
               if(AN.length()== 1)
               {Nol = "00";}
               else if(AN.length()==2)
               {Nol = "0";}
               else if(AN.length()==3)
               {Nol = "";}
               
               DS_idbarang.setText("KD" + Nol + AN);
               
           }else{
               DS_idbarang.setText("KD001");
           }
           
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
    private void kode_barang_pembelian(){
        try{
           
            String sql = "SELECT * FROM barang order by Kode_Barang desc";
             Statement S = con.createStatement(); 
            ResultSet R = S.executeQuery(sql);
            
           if(R.next()){
               String kode= R.getString("Kode_Barang").substring(2);
               String AN = "" + (Integer.parseInt(kode) + 1);
               String Nol = "";
               
               if(AN.length()== 1)
               {Nol = "00";}
               else if(AN.length()==2)
               {Nol = "0";}
               else if(AN.length()==3)
               {Nol = "";}
               
               bel_kodebarang.setText("KD" + Nol + AN);
               
           }else{
               bel_kodebarang.setText("KD001");
           }
           
           
        }catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
    private void DBsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DBsimpanActionPerformed
        // TODO add your handling code here:
        String tampilan = "dd-MM-yyyy";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
//        String tanggal = String.valueOf(fm.format(jDateChooser1.getDate()));
        try {
            String sql = "INSERT INTO barang VALUES ('"
                  
                    +tfDBKodeBarang.getText()+"','"
                    +tfNamaBarang.getText()+"','"
                    +tfDBStok.getText()+"','"
                    +tfDBSatuan.getText()+"','"
                    +tfDBHarga.getText()+"')";
            Statement statement = con.createStatement(); 
            statement.executeUpdate(sql);
            
           cari();
           updateTableauto();
           updateTableautotransaksi();
           kode_barang_otomatis();
           JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_DBsimpanActionPerformed

    private void DBeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DBeditActionPerformed
                
        String sql ="Update barang set Kode_Barang = ?, Nama_Barang = ?,Stok = ?,Satuan = ?,Harga = ?where Kode_Barang = '"+tfDBKodeBarang.getText()+"'";
          try {                 
            PreparedStatement stat = con.prepareStatement(sql);
            stat.setString(1, tfDBKodeBarang.getText());
            stat.setString(2, tfNamaBarang.getText());
            stat.setString(3, tfDBStok.getText());
            stat.setString(4, tfDBSatuan.getText());
            stat.setString(5, tfDBHarga.getText());
            
            stat.executeUpdate();
            updateTableauto();
            updateTableautotransaksi();
             System.out.println(stat);
            JOptionPane.showMessageDialog(null, "Data Berhasil DiUpdate");
            
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
      
           
      
       
    }//GEN-LAST:event_DBeditActionPerformed

    private void DBeditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DBeditMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_DBeditMouseClicked

    private void DBhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DBhapusActionPerformed
        // TODO add your handling code here:
        try{
        String sql = "delete from barang where Kode_Barang='"+tfDBKodeBarang.getText() +"'";
         Statement statement = con.createStatement(); 
                    statement.executeUpdate(sql);
        java.sql.Connection conn=koneksi.koneksiDb();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.execute();
                    updateTableauto();
                    kosong();
                       updateTableautotransaksi();
        JOptionPane.showMessageDialog(null,"Data berhasil di hapus");

        }catch (Exception e){

        JOptionPane.showMessageDialog(null,"Proses hapus gagal/koneksi gagal..");

        System.out.println(e.getMessage());
 
}
 

    }//GEN-LAST:event_DBhapusActionPerformed

    private void tfSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
        DefaultTableModel table = new DefaultTableModel();
        
        table.addColumn("Kode Barang");
        table.addColumn("Nama Barang");
        table.addColumn("Stok");
        table.addColumn("Satuan");
        table.addColumn("Harga");        
        try
        {    
            String sql = "SELECT * FROM barang WHERE Kode_Barang='"+tfSearch.getText()+"'";
            Statement S = koneksi.koneksiDb().createStatement();
            ResultSet R = S.executeQuery(sql);
            
            while(R.next())
            {
                table.addRow(new Object[]{
                    R.getString(1),
                    R.getString(2),
                    R.getString(3),
                    R.getString(4),
                    R.getString(5),
                        
                });
            }
            Tdatabarang.setModel(table);
        }
        catch(Exception e){
    
}
        }
    }//GEN-LAST:event_tfSearchKeyPressed

    private void tfSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchKeyTyped
        // TODO add your handling code here:
        if("".equals(tfSearch.getText())){
            listed();
        }
    }//GEN-LAST:event_tfSearchKeyTyped

    private void tfDBKodeBarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDBKodeBarangKeyTyped
        // TODO add your handling code here:       
//        char karakter = evt.getKeyChar();
//if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))){
//    getToolkit().beep();
//    evt.consume();
//}
    }//GEN-LAST:event_tfDBKodeBarangKeyTyped

    private void tfDBSatuanKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDBSatuanKeyTyped
        // TODO add your handling code here:
//        char karakter = evt.getKeyChar();
//if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))){
//    getToolkit().beep();
//    evt.consume();
//}
    }//GEN-LAST:event_tfDBSatuanKeyTyped

    private void tfDBHargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDBHargaKeyTyped
        // TODO add your handling code here:
//        char karakter = evt.getKeyChar();
//if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))){
//    getToolkit().beep();
//    evt.consume();
//}
    }//GEN-LAST:event_tfDBHargaKeyTyped

    private void DBclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DBclearActionPerformed
        // TODO add your handling code here:
        kosong();
        kode_barang_otomatis();
    }//GEN-LAST:event_DBclearActionPerformed

    private void tfSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSearchActionPerformed

    private void tfDBKodeBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfDBKodeBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfDBKodeBarangActionPerformed

    private void tfDBKodeBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDBKodeBarangKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tfDBKodeBarangKeyPressed

    private void belhargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_belhargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_belhargaActionPerformed

    private void bel_kodebarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bel_kodebarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bel_kodebarangActionPerformed

    private void belpcsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_belpcsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_belpcsActionPerformed

    private void belsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_belsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_belsearchActionPerformed

    private void DSnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DSnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DSnamaActionPerformed

    private void DSSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DSSimpanActionPerformed
        // TODO add your handling code here:
        
        String tampilan = "dd-MM-yyyy";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(DStanggal.getDate()));
        int id = 0;
        try {
            String sql = "INSERT INTO data_supplier(Kode_Barang, nama_supplier, tanggal, alamat, no_telepon, email) VALUES ('"
                    +DS_idbarang.getText()+"','"
                    +DSnama.getText()+"','"
                    +tanggal+"','"
                    +DSalamat.getText()+"','"
                    +DSnotlpn.getText()+"','"
                    +DSemail.getText()+"')";
            Statement statement = con.createStatement(); 
            statement.executeUpdate(sql);

            updateTableautoDS();
            kode_barang_supplier();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_DSSimpanActionPerformed

    private void DSclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DSclearActionPerformed
        // TODO add your handling code here:
        DSkosong();
        kode_barang_supplier();
    }//GEN-LAST:event_DSclearActionPerformed

    private void DSHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DSHapusActionPerformed
 try{
        String sql = "delete from data_supplier where Kode_Barang='"+DS_idbarang.getText() +"'";
         Statement statement = con.createStatement(); 
                    statement.executeUpdate(sql);
        java.sql.Connection conn=koneksi.koneksiDb();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.execute();
                    updateTableautoDS();
                    kode_barang_supplier();
                    DSkosong();

        JOptionPane.showMessageDialog(null,"Data berhasil di hapus");

        }catch (Exception e){

        JOptionPane.showMessageDialog(null,"Proses hapus gagal/koneksi gagal..");

        System.out.println(e.getMessage());
 
}        // TODO add your handling code here:
    }//GEN-LAST:event_DSHapusActionPerformed

    private void DSEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DSEditActionPerformed
        // TODO add your handling code here:
         String tampilan = "dd-MM-yyyy";
        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
        String tanggal = String.valueOf(fm.format(DStanggal.getDate()));

        String kodebarang = DS_idbarang.getText();
        String namasupplier =DSnama.getText();
        String tgl =  tanggal;
        String alamat = DSalamat.getText();
        String notlpn = DSnotlpn.getText();
        String email = DSemail.getText();
        
        
          try {
            String sql ="UPDATE data_supplier SET Kode_Barang= '"+kodebarang+"',nama_supplier = '"+namasupplier+"', tanggal = '"+tgl+"',alamat = '"+alamat+"',no_telepon = '"+notlpn+"',email = '"+email+"' WHERE Kode_Barang = '"+kodebarang+"'";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.execute();
            updateTableautoDS();
            kode_barang_supplier();
            JOptionPane.showMessageDialog(null, "Data Berhasil DiUpdate");
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
    }//GEN-LAST:event_DSEditActionPerformed

    private void tfDSSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDSSearchKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
        DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Kode Barang");
        table.addColumn("Nama Supplier");
        table.addColumn("Tanggal");
        table.addColumn("Alamat");
        table.addColumn("No Tlp");
        table.addColumn("Email");
        
        try
        {    
            String sql = "SELECT * FROM data_supplier WHERE nama_supplier='"+tfDSSearch.getText()+"'";
            Statement S = koneksi.koneksiDb().createStatement();
            ResultSet R = S.executeQuery(sql);
            
            while(R.next())
            {
                table.addRow(new Object[]{
                    R.getString(1),
                    R.getString(2),
                    R.getString(3),
                    R.getString(4),
                    R.getString(5),
                    R.getString(6),  
                   
                });
            }
            DStable.setModel(table);
        }
        catch(Exception e){
    
}
        }
    }//GEN-LAST:event_tfDSSearchKeyPressed

    private void tfDSSearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfDSSearchKeyTyped
        // TODO add your handling code here:
         if("".equals(tfDSSearch.getText())){
            listedDS();
        }
    }//GEN-LAST:event_tfDSSearchKeyTyped

    
    private void belsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_belsimpanActionPerformed
        // TODO add your handling code here:
//        String tampilan = "dd-MM-yyyy";
//        SimpleDateFormat fm = new SimpleDateFormat(tampilan);
//        String tanggal = String.valueOf(fm.format(beltanggal.getDate()));
        try {
            String sql = "INSERT INTO data_barang_supplier(Kode_Barang, Nama_Barang, Harga, Satuan, Stok) VALUES ('"   
                    +bel_kodebarang.getText()+"','"
                    +belbarang.getText()+"','"
                    +belharga.getText()+"','"
                    +PBSatuan.getText()+"','"
                    +belpcs.getText()+"')";
            
             Statement statement = con.createStatement(); 
            statement.executeUpdate(sql);
//                   
            String sql2 = "INSERT INTO barang VALUES ('"
                  
                    +bel_kodebarang.getText()+"','"
                    +belbarang.getText()+"','"
                    +belpcs.getText()+"','"
                    +PBSatuan.getText()+"','"
                    +belharga.getText()+"')";
            Statement stat = con.createStatement(); 
            stat.executeUpdate(sql2);
            
           
           updateTableauto();      
           updateTableautobel();
           updateTableautotransaksi();
           kode_barang_pembelian();
   
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_belsimpanActionPerformed

    private void belclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_belclearActionPerformed
        // TODO add your handling code here:
        belkosong();
        kode_barang_pembelian();
    }//GEN-LAST:event_belclearActionPerformed

    private void belhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_belhapusActionPerformed
        // TODO add your handling code here:
        try{
        String sql = "delete from data_barang_supplier where Kode_Barang='"+bel_kodebarang.getText() +"'";
         Statement statement = con.createStatement(); 
                    statement.executeUpdate(sql);
        java.sql.Connection conn=koneksi.koneksiDb();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.execute();
                    updateTableautobel();
                    updateTableautotransaksi();
                    belkosong();

        JOptionPane.showMessageDialog(null,"Data berhasil di hapus");

        }catch (Exception e){

        JOptionPane.showMessageDialog(null,"Proses hapus gagal/koneksi gagal..");

        System.out.println(e.getMessage());
 
}
    }//GEN-LAST:event_belhapusActionPerformed

    private void belsearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_belsearchKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
         DefaultTableModel table = new DefaultTableModel();
        table.addColumn("Id barang");
        table.addColumn("Kode Barang");
        table.addColumn("Barang");
        table.addColumn("Harga");
        table.addColumn("Pcs");
          
        try
        {    
            String sql = "SELECT * FROM data_barang_supplier WHERE nama='"+belsearch.getText()+"'";
            Statement S = koneksi.koneksiDb().createStatement();
            ResultSet R = S.executeQuery(sql);
            
            while(R.next())
            {
                table.addRow(new Object[]{
                    R.getString(1),
                    R.getString(2),
                    R.getString(3),
                    R.getString(4),
                   R.getString(5),
                });
            }
            beltable.setModel(table);
        }
        catch(Exception e){
    
}
        }
    }//GEN-LAST:event_belsearchKeyPressed

    private void belsearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_belsearchKeyTyped
        // TODO add your handling code here:
        if("".equals(belsearch.getText())){
            listedbel();
        }
    }//GEN-LAST:event_belsearchKeyTyped

    private void beltableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_beltableKeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_beltableKeyPressed

    private void beltableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_beltableMouseClicked
        // TODO add your handling code here:
          DefaultTableModel model = (DefaultTableModel)beltable.getModel(); 
         int index = beltable.getSelectedRow();
//         int baris = Tdatabarang.rowAtPoint(evt.getPoint());
         Date date;
//       try {
//           date = new SimpleDateFormat("dd-MM-yyyy").parse((String)model.getValueAt(index, 1));
//           beltanggal.setDate(date);
//       } catch (ParseException ex) {
//           Logger.getLogger(tampilanAwal.class.getName()).log(Level.SEVERE, null, ex);
//       }
         
         String Kode_barang = beltable.getValueAt(index, 0).toString();
         bel_kodebarang.setText(Kode_barang);
         String barang = beltable.getValueAt(index, 1).toString();
         belbarang.setText(barang); 
         String harga = beltable.getValueAt(index, 2).toString();
         belharga.setText(harga); 
         String satuan = beltable.getValueAt(index, 3).toString();
         PBSatuan.setText(satuan); 
         String pcs = beltable.getValueAt(index, 4).toString();
         belpcs.setText(pcs);
        
         
//         belhapus.setEnabled(true);
//         beledit.setEnabled(true);
    }//GEN-LAST:event_beltableMouseClicked

    
    
    private void jExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jExitActionPerformed
        // TODO add your handling code here:
jDashboard.setBackground(new Color(204,204,255));
jDataBarang.setBackground(new Color(204,204,255));
jDataSupplier.setBackground(new Color(204,204,255));
jPembelianBarang.setBackground(new Color(204,204,255));
jTransaksi.setBackground(new Color(204,204,255));
jLaporanTransaksi.setBackground(new Color(204,204,255));
jDataPelanggan.setBackground(new Color(204,204,255));
pnlpengguna.setBackground(new Color(204,204,255));
jExit.setBackground(Color.white);

        exit e = new exit();
        e.setVisible(true);
    }//GEN-LAST:event_jExitActionPerformed

    private void jDataPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDataPelangganActionPerformed
        // TODO add your handling code here:
pnldashboard.setVisible(false);
pnldatabarang.setVisible(false);
pnldatasupplier.setVisible(false);
pnlpembelianbarang.setVisible(false);
pnlpenjualanbarang.setVisible(false);
pnllaporantransaksi.setVisible(false);
pnldatapelanggan.setVisible(true);
pnlpengguna.setVisible(false);
jDashboard.setBackground(new Color(204,204,255));
jDataBarang.setBackground(new Color(204,204,255));
jDataSupplier.setBackground(new Color(204,204,255));
jPembelianBarang.setBackground(new Color(204,204,255));
jTransaksi.setBackground(new Color(204,204,255));
jLaporanTransaksi.setBackground(new Color(204,204,255));
jDataPelanggan.setBackground(Color.white);
pnlpengguna.setBackground(new Color(204,204,255));
jExit.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_jDataPelangganActionPerformed

    private void DPnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DPnamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DPnamaActionPerformed

    private void TtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TtotalActionPerformed

    private void TbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TbayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TbayarActionPerformed

    private void TkembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TkembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TkembalianActionPerformed

    private void TkodetransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TkodetransaksiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TkodetransaksiActionPerformed

    private void TsearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TsearchKeyPressed
        // TODO add your handling code here:
//        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
//        {
//            DefaultTableModel table = new DefaultTableModel();
//
//            table.addColumn("Kode");
//            table.addColumn("Nama");
//            table.addColumn("Tgl");
//            table.addColumn("Nama Kue");
//            table.addColumn("Jumlah");
//            table.addColumn("Harga");
//            table.addColumn("Metode Pembayaran");
//            table.addColumn("Total Bayar");
//            try
//            {
//                String sql = "SELECT * FROM penjualan_barang WHERE kodebarang='"+Tsearch.getText()+"'";
//                Statement S = koneksi.koneksiDb().createStatement();
//                ResultSet R = S.executeQuery(sql);
//
//                while(R.next())
//                {
//                    table.addRow(new Object[]{
//                        R.getString(1),
//                        R.getString(2),
//                        R.getString(3),
//                        R.getString(4),
//                        R.getString(5),
//                        R.getString(6),
//                        R.getString(7),
//                        R.getString(8),
//
//                    });
//                }
//                PBTable.setModel(table);
//            }
//            catch(Exception e){
//
//            }
//        }
    }//GEN-LAST:event_TsearchKeyPressed

    private void TsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TsearchActionPerformed

    private void jTableTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTransaksiMouseClicked
        // TODO add your handling code here:
         try {
    int row=jTableTransaksi.getSelectedRow();
    String tabel_klik=(jTableTransaksi.getModel().getValueAt(row, 0).toString());
    String sql="select * from barang where Kode_Barang='"+tabel_klik+"'";
    pst=con.prepareStatement(sql);
    ResultSet rst=pst.executeQuery();
    if (rst.next()) {
     barang =rst.getString(("Kode_Barang"));    
     String stok=rst.getString(("Stok"));
     istok= Integer.parseInt(stok);
     harga=rst.getString(("Harga"));
     iharga= Integer.parseInt(harga);
    }
}catch (Exception e) 
{JOptionPane.showMessageDialog(null, e);}

    }//GEN-LAST:event_jTableTransaksiMouseClicked

       public void cari(){
    try {
        String sql="select * from barang where Nama_Barang LIKE '%"+Tsearch.getText()+"%'";
        pst=con.prepareStatement(sql);
        rst=pst.executeQuery();
        jTableTransaksi.setModel(DbUtils.resultSetToTableModel(rst));
       } catch (Exception e)
       { JOptionPane.showMessageDialog(null, e);} }
    
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        cari();
    
    }//GEN-LAST:event_jButton4ActionPerformed
 private void subtotal(){
    int diskon, jumlah, sub;
            if (Tdiscount.getText().equals("")) {diskon=0;}
            else {diskon= Integer.parseInt(Tdiscount.getText());}
         jumlah= Integer.parseInt(Tjumlah.getText());
         sub=(jumlah*iharga)-diskon;
         ssub=String.valueOf(sub);     
    }
 public void kurangi_stok(){
    int qty;
    qty=Integer.parseInt(Tjumlah.getText());
    kstok=istok-qty;
    }
 public void detail(){
    try {
        String Kode_detail=Tkodetransaksi.getText();
        String KD=Kode_detail;
        String sql="select * from detail_barang where Kode_Detail='"+KD+"'";
        pst=con.prepareStatement(sql);
        rst=pst.executeQuery();
        jTable3.setModel(DbUtils.resultSetToTableModel(rst));
       } catch (Exception e){ JOptionPane.showMessageDialog(null, e);} 
    }
 
 public void autonumber(){
    try{
        String sql = "SELECT MAX(RIGHT(Kode_Transaksi,3)) AS NO FROM transaksi";
        pst=con.prepareStatement(sql);
        rst=pst.executeQuery();
        while (rst.next()) {
                if (rst.first() == false) {
                    Tkodetransaksi.setText("TRX001");
                } else {
                    rst.last();
                    int auto_id = rst.getInt(1) + 1;
                    String no = String.valueOf(auto_id);
                    int NomorJual = no.length();
                    for (int j = 0; j < 3 - NomorJual; j++) {
                        no = "0" + no;
                    }
                    Tkodetransaksi.setText("TRX" + no);
                }
            }
        rst.close();
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
  public void sum(){
        int totalBiaya = 0;
        int subtotal;
        DefaultTableModel dataModel = (DefaultTableModel) jTable3.getModel();
        int jumlah = jTable3.getRowCount();
        for (int i=0; i<jumlah; i++){
        subtotal = Integer.parseInt(dataModel.getValueAt(i, 5).toString());
        totalBiaya += subtotal;
        }
        Ttotal.setText(String.valueOf(totalBiaya));
    }
  public void clsr(){
    Tjumlah.setText("");
    Tdiscount.setText("");
    }
  
    private void TtambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TtambahActionPerformed
        // TODO add your handling code here:
        subtotal();
        kurangi_stok();
        try {
        String diskon;
            if (Tdiscount.getText().equals("")) {diskon="0";}
            else {diskon=Tdiscount.getText();}
        String Kode_detail=Tkodetransaksi.getText();
        KD=Kode_detail;
            String sql="insert into detail_barang (Kode_Detail,Kode_Barang,Harga,Jumlah,Discount,Subtotal) value (?,?,?,?,?,?)";
            String update="update barang set Stok='"+kstok+"' where Kode_Barang='"+barang+"'";
            pst=con.prepareStatement(sql);
            pst2=con.prepareStatement(update);
            pst.setString(1, KD);
            pst.setString(2, barang);
            pst.setString(3, harga);
            pst.setString(4, Tjumlah.getText());
            pst.setString(5, diskon);
            pst.setString(6, ssub);
            pst.execute();
            pst2.execute();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            }
        detail();
        sum();
        cari();
        clsr();
    }//GEN-LAST:event_TtambahActionPerformed
 
    public void tambah_stok(){
    tstok=ijumlah+istok2;
        try {
        String update="update barang set Stok='"+tstok+"' where Kode_Barang='"+barang+"'";
        pst2=con.prepareStatement(update);
        pst2.execute();
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
    }
    private void ThapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ThapusActionPerformed
        // TODO add your handling code here:
        try {
            String sql="delete from detail_barang where Kode_Barang=?";
            pst=con.prepareStatement(sql);
            pst.setString(1, dbarang);
            pst.execute();
        }catch (Exception e){JOptionPane.showMessageDialog(null, e);}
        detail();
        sum();
        tambah_stok();
        cari();
    }//GEN-LAST:event_ThapusActionPerformed
public void ambil_stock(){
    try {
    String sql="select * from barang where Kode_Barang='"+barang+"'";
    pst=con.prepareStatement(sql);
    rst=pst.executeQuery();
    if (rst.next()) {    
    String stok=rst.getString(("Stok"));
    istok2= Integer.parseInt(stok);
    }
}catch (Exception e) {JOptionPane.showMessageDialog(null, e);}
    }


    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // TODO add your handling code here:
         try {
    int row=jTable3.getSelectedRow();
    dbarang=(jTable3.getModel().getValueAt(row, 1).toString());
    String sql="select * from detail_barang where Kode_Barang='"+dbarang+"'";
    pst=con.prepareStatement(sql);
    rst=pst.executeQuery();
    if (rst.next()) {   
    String jumlah=rst.getString(("Jumlah"));
    ijumlah= Integer.parseInt(jumlah);
    }
 }catch (Exception e) {JOptionPane.showMessageDialog(null, e);}
     ambil_stock();
    
    }//GEN-LAST:event_jTable3MouseClicked

    private void total(){
    int total, bayar, kembali;
        total= Integer.parseInt(Tbayar.getText());
        bayar= Integer.parseInt(Ttotal.getText());
        kembali=total-bayar;
        String ssub=String.valueOf(kembali);
        Tkembalian.setText(ssub);
    }
    
    private void simpan(){
        String tgl=tanggal.getText();
        String jam=ljam.getText();
      try {
            String sql="insert into transaksi (Kode_Transaksi,Kode_Detail,Tanggal,Jam,Total) value (?,?,?,?,?)";
            pst=con.prepareStatement(sql);
            pst.setString(1, Tkodetransaksi.getText());
            pst.setString(2, KD);
            pst.setString(3, tgl);
            pst.setString(4, jam);
            pst.setString(5, Ttotal.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Tersimpan");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            }
    }
    
    private void TBbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TBbayarActionPerformed
        // TODO add your handling code here:
        
      total();
      simpan();
      autonumber();
      detail();
       try {
           updateTableauto();
       } catch (SQLException ex) {
           Logger.getLogger(tampilanAwal.class.getName()).log(Level.SEVERE, null, ex);
       }
      Ttotal.setText("");
      Tbayar.setText("");
      Tkembalian.setText("");
      jTextField1.setText("");
      cari();
       
    }//GEN-LAST:event_TBbayarActionPerformed

    private void tfNamaBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNamaBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaBarangActionPerformed

    private void tfNamaBarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNamaBarangKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaBarangKeyTyped

    private void tfNamaBarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNamaBarangKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNamaBarangKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        tanggals=format.format(jDateChooser1.getDate());
        sql="select * from transaksi where Tanggal <'"+tanggals+"'";
        new popup(sql).setVisible(true);
     
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        tanggals=format.format(jDateChooser2.getDate());
        tanggal2=format.format(jDateChooser3.getDate());
        sql="select * from transaksi where Tanggal between '"+tanggals+"' and '"+tanggal2+"'";
        new popup(sql).setVisible(true);}
        catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try{
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        tanggals=format.format(jDateChooser4.getDate());
        sql="select * from transaksi where Tanggal >'"+tanggals+"'";
        new popup(sql).setVisible(true);
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    
    
     JasperReport JasRep;
    JasperPrint JasPri;
    Map param = new HashMap();
    JasperDesign JasDes;
    private void FLcetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FLcetakActionPerformed
        // TODO add your handling code here:
          try {
            Connection konn =  koneksi.koneksiDb();
            File file = new File("src/form/report/reporttransaksi.jrxml");
            JasDes = JRXmlLoader.load(file);
            param.clear();
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPri = JasperFillManager.fillReport(JasRep, param, konn);
            //JasperViewer.viewReport(JasPri, false);
            JasperViewer jasperViewer = new JasperViewer(JasPri, false);
            jasperViewer.setExtendedState(jasperViewer.getExtendedState()|javax.swing.JFrame.MAXIMIZED_BOTH);
            jasperViewer.setVisible(true);
//            tampilanAwal.this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Cetak laporan", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_FLcetakActionPerformed

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "INSERT INTO data_pelanggan(nama, email, no_hp, alamat) VALUES ('"
                    +DPnama.getText()+"','"
                    +DPemail.getText()+"','"
                    +DPno.getText()+"','"
                    +DPalamat.getText()+"')";
            Statement statement = con.createStatement(); 
            statement.executeUpdate(sql);

            updateTableautoTBDP();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    
    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        // TODO add your handling code here:
        
        String id =DPid.getText();
        String nama = DPnama.getText();
        String email = DPemail.getText();
        String no = DPno.getText();
        String alamat = DPalamat.getText();
        
          try {
            String sql ="UPDATE data_pelanggan SET id = '"+id+"',nama = '"+nama+"',email = '"+email+"', no_hp = '"+no+"',alamat = '"+alamat+"' WHERE id='"+id+"'";
             Statement statement = con.createStatement(); 
            statement.executeUpdate(sql);
            updateTableautoTBDP();
            JOptionPane.showMessageDialog(null, "Data Berhasil DiUpdate");
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
    }//GEN-LAST:event_btneditActionPerformed

    private void DPemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DPemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DPemailActionPerformed

    private void btnhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnhapusActionPerformed
        // TODO add your handling code here:
                String id =DPid.getText();
        try{
            
        String sql = "delete from data_pelanggan where id='"+id+"'";
         Statement statement = con.createStatement(); 
                    statement.executeUpdate(sql);
                    updateTableautoTBDP();
                    kosong();

        JOptionPane.showMessageDialog(null,"Data berhasil di hapus");

        }catch (Exception e){

        JOptionPane.showMessageDialog(null,"Proses hapus gagal/koneksi gagal..");

        System.out.println(e.getMessage());
 
}
    }//GEN-LAST:event_btnhapusActionPerformed

    private void TdatabarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TdatabarangMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)Tdatabarang.getModel();
//        int index = Tdatabarang.getSelectedRow();
                 int index = Tdatabarang.rowAtPoint(evt.getPoint());
//        Date date;
//        try {
//            date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(index, 1));
//            //           jDateChooser1.setDate(date);
//        } catch (ParseException ex) {
//            Logger.getLogger(tampilanAwal.class.getName()).log(Level.SEVERE, null, ex);
//        }

        String kode = Tdatabarang.getValueAt(index, 0).toString();
        tfDBKodeBarang.setText(kode);
        String nama = Tdatabarang.getValueAt(index, 1).toString();
        tfNamaBarang.setText(nama);
        String stok = Tdatabarang.getValueAt(index, 2).toString();
        tfDBStok.setText(stok);
        String satuan = Tdatabarang.getValueAt(index, 3).toString();
        tfDBSatuan.setText(satuan);
        String harga = Tdatabarang.getValueAt(index, 4).toString();
        tfDBHarga.setText(harga);
        
        
        System.out.println(index);
    }//GEN-LAST:event_TdatabarangMouseClicked

    private void belbarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_belbarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_belbarangActionPerformed

    private void DS_databarangsupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DS_databarangsupMouseClicked
        // TODO add your handling code here:
//         DefaultTableModel model = (DefaultTableModel)DS_databarangsup.getModel();
//        int index = DS_databarangsup.getSelectedRow();
//
//        String kodebarang = DS_databarangsup.getValueAt(index, 0).toString();
//        DS_idbarang.setText(kodebarang);
//        String barang = DS_databarangsup.getValueAt(index, 1).toString();
//        DSnama.setText(barang);
//        String harga = DS_databarangsup.getValueAt(index, 2).toString();
//        DSnama.setText(harga);
//        String Pcs = DS_databarangsup.getValueAt(index, 3).toString();
//        DSnama.setText(Pcs);
    }//GEN-LAST:event_DS_databarangsupMouseClicked

    private void DStableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DStableMouseClicked
        // TODO add your handling code here:
        try {
            
     DefaultTableModel model = (DefaultTableModel)DStable.getModel();
        int index = DStable.getSelectedRow();
        //         int baris = Tdatabarang.rowAtPoint(evt.getPoint());
        
          SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Date dateValue = null;
        try {
            dateValue = date.parse((String) DStable.getValueAt(index, 2).toString());
            DStanggal.setDate(dateValue);
        } catch (ParseException ex) {
            Logger.getLogger(tampilanAwal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String kode = DStable.getValueAt(index, 0).toString();
        DS_idbarang.setText(kode); 
        String nama = DStable.getValueAt(index, 1).toString();
        DSnama.setText(nama);   
        
        String alamat = DStable.getValueAt(index, 3).toString();
        DSalamat.setText(alamat);
        String no = DStable.getValueAt(index, 4).toString();
        DSnotlpn.setText(no);
        String email = DStable.getValueAt(index, 5).toString();
        DSemail.setText(email);
            
    int row=DStable.getSelectedRow();
    String tabel_klik=(DStable.getModel().getValueAt(row, 0).toString());
    String sql="select * from data_barang_supplier where Kode_Barang=?";
    pst=con.prepareStatement(sql);
        pst.setString(1,tabel_klik);
        rst=pst.executeQuery();
        DS_databarangsup.setModel(DbUtils.resultSetToTableModel(rst));   
        System.out.println(pst);
}catch (Exception e) {JOptionPane.showMessageDialog(null, e);}
                           
         
        
    }//GEN-LAST:event_DStableMouseClicked

    private void TBDPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBDPMouseClicked
        // TODO add your handling code here:
         DefaultTableModel model = (DefaultTableModel)TBDP.getModel();
        int index = TBDP.getSelectedRow();
        //         int baris = TBDP.rowAtPoint(evt.getPoint());
        //         Date date;
        //       try {
            //           date = new SimpleDateFormat("dd-MM-yyyy").parse((String)model.getValueAt(index, 1));
            ////           jDateChooser1.setDate(date);
            //       } catch (ParseException ex) {
            //           Logger.getLogger(tampilanAwal.class.getName()).log(Level.SEVERE, null, ex);
            //       }

        String id = TBDP.getValueAt(index, 0).toString();
        DPid.setText(id);
        String nama = TBDP.getValueAt(index, 1).toString();
        DPnama.setText(nama);
        String email = TBDP.getValueAt(index, 2).toString();
        DPemail.setText(email);
        String no = TBDP.getValueAt(index, 3).toString();
        DPno.setText(no);
        String alamat = TBDP.getValueAt(index, 4).toString();
        DPalamat.setText(alamat);
    }//GEN-LAST:event_TBDPMouseClicked

    void kosongDP(){
         DPid.setText("default auto number");
         DPnama.setText("");
         DPemail.setText("");
         DPno.setText("");
         DPalamat.setText("");
     }
    private void btDPclearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDPclearActionPerformed
        // TODO add your handling code here:
       kosongDP(); 
    }//GEN-LAST:event_btDPclearActionPerformed

    private void TABpenggunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TABpenggunaActionPerformed
        // TODO add your handling code here:
pnldashboard.setVisible(false);
pnldatabarang.setVisible(false);
pnldatasupplier.setVisible(false);
pnlpembelianbarang.setVisible(false);
pnlpenjualanbarang.setVisible(false);
pnllaporantransaksi.setVisible(false);
pnldatapelanggan.setVisible(false);
pnlpengguna.setVisible(true);
jDashboard.setBackground(new Color(204,204,255));
jDataBarang.setBackground(new Color(204,204,255));
jDataSupplier.setBackground(new Color(204,204,255));
jPembelianBarang.setBackground(new Color(204,204,255));
jTransaksi.setBackground(new Color(204,204,255));
jLaporanTransaksi.setBackground(new Color(204,204,255));
jDataPelanggan.setBackground(new Color(204,204,255));
pnlpengguna.setBackground(Color.white);
jExit.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_TABpenggunaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            SimpleDateFormat format_date=new SimpleDateFormat("yyyy-MM-dd");
            String ambil_tgl=tanggal.getText();
            Date tgl = format_date.parse(ambil_tgl);
            Calendar kalender=Calendar.getInstance();
            kalender.setTime(tgl);
            kalender.add(Calendar.DAY_OF_WEEK, -3);
            tanggals=String.valueOf(format_date.format(kalender.getTime()));
            sql="select * from transaksi where Tanggal ='"+tanggals+"'";
            new popup(sql).setVisible(true);
        } catch (ParseException ex) { Logger.getLogger(tampilanAwal.class.getName()).log(Level.SEVERE, null, ex); }
        
    }//GEN-LAST:event_jButton5ActionPerformed
  
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
                  
        // TODO add your handling code here:
          try {
            Connection konn =  koneksi.koneksiDb();
            File file = new File("src/form/report/reportbarang.jrxml");
            JasDes = JRXmlLoader.load(file);
            param.clear();
            JasRep = JasperCompileManager.compileReport(JasDes);
            JasPri = JasperFillManager.fillReport(JasRep, param, konn);
            //JasperViewer.viewReport(JasPri, false);
            JasperViewer jasperViewer = new JasperViewer(JasPri, false);
            jasperViewer.setExtendedState(jasperViewer.getExtendedState()|javax.swing.JFrame.MAXIMIZED_BOTH);
            jasperViewer.setVisible(true);
//            tampilanAwal.this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Cetak laporan", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void DPtableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DPtableMouseClicked
        // TODO add your handling code here:
         DefaultTableModel model = (DefaultTableModel)DPtable.getModel(); 
         int index = DPtable.getSelectedRow();
//         int baris = Tdatabarang.rowAtPoint(evt.getPoint());
  
//       try {
//           date = new SimpleDateFormat("dd-MM-yyyy").parse((String)model.getValueAt(index, 1));
//           beltanggal.setDate(date);
//       } catch (ParseException ex) {
//           Logger.getLogger(tampilanAwal.class.getName()).log(Level.SEVERE, null, ex);
//       }
         
         String id = DPtable.getValueAt(index, 0).toString();
         DPPid.setText(id);
         String email = DPtable.getValueAt(index, 1).toString();
         penggunanama.setText(email); 
         String pass = DPtable.getValueAt(index, 2).toString();
         penggunapassword.setText(pass); 
       
          
    }//GEN-LAST:event_DPtableMouseClicked

    private void penggunapasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penggunapasswordActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_penggunapasswordActionPerformed

    private void CBstatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBstatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CBstatusActionPerformed

    private void DPsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DPsimpanActionPerformed
        // TODO add your handling code here:
      
        try {
            String sql = "INSERT INTO akun(email,password,status) VALUES (?,?,?)";
            
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, penggunanama.getText());
            statement.setString(2, penggunapassword.getText());
            String value = CBstatus.getSelectedItem().toString();
            statement.setString(3, value);
            
            statement.executeUpdate();
            
            updateTableautoakun();
            
            
           JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
             
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_DPsimpanActionPerformed

    private void DPhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DPhapusActionPerformed
        // TODO add your handling code here:
       
      int id = Integer.parseInt(DPPid.getText());
       try{
            String sql = "DELETE FROM akun WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            
            statement.executeUpdate();
            updateTableautoakun();
           
       }catch(Exception e){
           JOptionPane.showMessageDialog(this, e.getMessage());
       }

    }//GEN-LAST:event_DPhapusActionPerformed

    private void penggunanamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_penggunanamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_penggunanamaActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        kosongDPP();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void DPeditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DPeditActionPerformed
        // TODO add your handling code here:
       
          int id = Integer.parseInt(DPPid.getText());
        try{
            String sql = "UPDATE akun SET id= ?, email=?, password=?, status=? WHERE id ='"+id+"'";
            PreparedStatement statement = con.prepareStatement(sql);
            
            statement.setInt(1, id);
            statement.setString(2, penggunanama.getText());
            statement.setString(3, penggunapassword.getText());
            String value = CBstatus.getSelectedItem().toString();
            statement.setString(4, value);
           
            statement.executeUpdate();
            updateTableautoakun();
            
            JOptionPane.showMessageDialog(null, "Update Data Berhasil");
        }catch(Exception e){
          JOptionPane.showMessageDialog(this, e.getMessage());
    }
    }//GEN-LAST:event_DPeditActionPerformed
    
    
    
   

    
    
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
            java.util.logging.Logger.getLogger(tampilanAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tampilanAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tampilanAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tampilanAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new tampilanAwal().setVisible(true);
                } catch (SQLException e) {
//                    Logger.getLogger(tampilanAwal.class.getName()).log(Level.SEVERE, null, ex);
e.printStackTrace();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CBstatus;
    private javax.swing.JButton DBclear;
    private javax.swing.JButton DBedit;
    private javax.swing.JButton DBhapus;
    private javax.swing.JButton DBsimpan;
    private javax.swing.JTextField DPPid;
    private javax.swing.JTextArea DPalamat;
    private javax.swing.JButton DPedit;
    private javax.swing.JTextField DPemail;
    private javax.swing.JButton DPhapus;
    private javax.swing.JTextField DPid;
    private javax.swing.JTextField DPnama;
    private javax.swing.JTextField DPno;
    private javax.swing.JButton DPsimpan;
    private javax.swing.JTable DPtable;
    private javax.swing.JButton DSEdit;
    private javax.swing.JButton DSHapus;
    private javax.swing.JButton DSSimpan;
    private javax.swing.JTable DS_databarangsup;
    private javax.swing.JTextField DS_idbarang;
    private javax.swing.JTextArea DSalamat;
    private javax.swing.JButton DSclear;
    private javax.swing.JTextField DSemail;
    private javax.swing.JTextField DSnama;
    private javax.swing.JTextField DSnotlpn;
    private javax.swing.JTable DStable;
    private com.toedter.calendar.JDateChooser DStanggal;
    private javax.swing.JButton FLcetak;
    private javax.swing.JPanel Jam;
    private javax.swing.JTextField PBSatuan;
    private javax.swing.JButton TABpengguna;
    private javax.swing.JTable TBDP;
    private javax.swing.JButton TBbayar;
    private javax.swing.JTextField Tbayar;
    private javax.swing.JTable Tdatabarang;
    private javax.swing.JTextField Tdiscount;
    private javax.swing.JButton Thapus;
    private javax.swing.JTextField Tjumlah;
    private javax.swing.JTextField Tkembalian;
    private javax.swing.JTextField Tkodetransaksi;
    private javax.swing.JTextField Tsearch;
    private javax.swing.JButton Ttambah;
    private javax.swing.JTextField Ttotal;
    private javax.swing.JTextField bel_kodebarang;
    private javax.swing.JTextField belbarang;
    private javax.swing.JButton belclear;
    private javax.swing.JButton belhapus;
    private javax.swing.JTextField belharga;
    private javax.swing.JTextField belpcs;
    private javax.swing.JTextField belsearch;
    private javax.swing.JButton belsimpan;
    private javax.swing.JTable beltable;
    private javax.swing.JButton btDPclear;
    private javax.swing.JButton btentang;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnhapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jDashboard;
    private javax.swing.JButton jDataBarang;
    private javax.swing.JButton jDataPelanggan;
    private javax.swing.JButton jDataSupplier;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JButton jExit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jLaporanTransaksi;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton jPembelianBarang;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTableTransaksi;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jTransaksi;
    private javax.swing.JLabel lbluserlogin;
    private javax.swing.JLabel ldatabarang;
    private javax.swing.JLabel ljam;
    private javax.swing.JTextField penggunanama;
    private javax.swing.JTextField penggunapassword;
    private javax.swing.JLabel pnldashboard;
    private javax.swing.JPanel pnldatabarang;
    private javax.swing.JPanel pnldatapelanggan;
    private javax.swing.JPanel pnldatasupplier;
    private javax.swing.JPanel pnllaporantransaksi;
    private javax.swing.JPanel pnlpembelianbarang;
    private javax.swing.JPanel pnlpengguna;
    private javax.swing.JPanel pnlpenjualanbarang;
    private javax.swing.JLabel tanggal;
    private javax.swing.JTextField tfDBHarga;
    private javax.swing.JTextField tfDBKodeBarang;
    private javax.swing.JTextField tfDBSatuan;
    private javax.swing.JTextField tfDBStok;
    private javax.swing.JTextField tfDSSearch;
    private javax.swing.JTextField tfNamaBarang;
    private javax.swing.JTextField tfSearch;
    // End of variables declaration//GEN-END:variables
}
