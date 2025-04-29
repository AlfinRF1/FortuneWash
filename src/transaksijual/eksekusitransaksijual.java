/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package transaksijual;

import transaksibeli.transaksibeli;
import barang.barang;
import halamanutama.halamanutama;
import java.sql.Connection;
import login.LOGIN1;
import koneksi.DatabaseManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import koneksi.koneksi;
import transaksijual.transaksijual;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.Date;



public class eksekusitransaksijual extends javax.swing.JFrame {
 private JDateChooser dateChooser;

    public eksekusitransaksijual() {
        initComponents();
         loadData(); 
        setupComboBox();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        kodejual = new javax.swing.JTextField();
        platnomor = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setBackground(new java.awt.Color(204, 204, 204));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "kode_penjualan", "tanggal_penjualan", "harga_jasa", "Plat_Nomor"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 167, 690, 460));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/blok biru.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 30, 410, 70));
        getContentPane().add(kodejual, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 270, 280, -1));
        getContentPane().add(platnomor, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 410, 280, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MOBIL : 30.000", "MOTOR : 15.000", " ", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 360, 130, 30));
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 320, 280, -1));

        jLabel8.setFont(new java.awt.Font("STKaiti", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Barang");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, -1));

        jLabel9.setFont(new java.awt.Font("STKaiti", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Transaksi Jual");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, -1, -1));

        jLabel10.setFont(new java.awt.Font("STKaiti", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Transaksi Beli");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, -1, -1));

        jLabel11.setFont(new java.awt.Font("STKaiti", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Beranda");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/fc ekctrxj owner buwetull.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 110, 70));

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 110, 100));

        jButton3.setText("jButton3");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 110, 80));

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 110, 80));

        jButton5.setText("jButton5");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 30, -1, 60));

        jButton7.setText("jButton7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1405, 20, 90, 80));

        btndelete.setBackground(new java.awt.Color(51, 204, 255));
        btndelete.setText("delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btndelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 670, 190, 50));

        jButton6.setBackground(new java.awt.Color(51, 204, 255));
        jButton6.setText("ENTER");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 550, 190, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setupComboBox() {
        jComboBox1.removeAllItems();
        jComboBox1.addItem("MOBIL : 30000");
        jComboBox1.addItem("MOTOR : 15000");
    }
    private void loadData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        try (Connection conn = koneksi.koneksiDB();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM transaksi_jual");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("kode_penjualan"),
                    rs.getString("tanggal_penjualan"),
                    rs.getInt("harga_jasa"),
                    rs.getString("Plat_Nomor")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Error loading data: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        kodejual.setText("");
        platnomor.setText("");
        jComboBox1.setSelectedIndex(0);
        jDateChooser1.setDate(null);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    halamanutama hal = new halamanutama();
        hal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       barang b  = new barang();
        b.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
transaksijual j  = new transaksijual();
        j.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        transaksibeli b  = new transaksibeli();
        b.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
          int pilihan = JOptionPane.showConfirmDialog(this, 
            "Apakah anda yakin ingin logout?",
            "Konfirmasi Logout",
            JOptionPane.YES_NO_OPTION);
            
    if (pilihan == JOptionPane.YES_OPTION) {
        this.dispose();
        LOGIN1 L = new LOGIN1();
        L.setVisible(true);
    }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
 try {
            String kodePenjualan = kodejual.getText().trim();
            java.util.Date selectedDate = jDateChooser1.getDate();
            if (selectedDate == null) {
                JOptionPane.showMessageDialog(this, "Tanggal Penjualan tidak boleh kosong!");
                return;
            }
            String tanggalPenjualan = new java.text.SimpleDateFormat("yyyy-MM-dd").format(selectedDate);
            String hargaJasaStr = jComboBox1.getSelectedItem().toString();
            int hargaJasa = Integer.parseInt(hargaJasaStr.split(":")[1].trim());
            String platNomor = platnomor.getText();

            if (kodePenjualan.isEmpty() || platNomor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
                return;
            }
            try (Connection conn = koneksi.koneksiDB();
                 PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO transaksi_jual (kode_penjualan, tanggal_penjualan, harga_jasa, Plat_Nomor) VALUES (?, ?, ?, ?)")) {
                stmt.setString(1, kodePenjualan);
                stmt.setString(2, tanggalPenjualan);
                stmt.setInt(3, hargaJasa);
                stmt.setString(4, platNomor);

                int result = stmt.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Data berhasil disimpan!");
                    loadData();
                    clearFields();
                    transaksijual j = new transaksijual();
                     j.setVisible(true);
                     this.dispose();
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, 
                "Error saving data: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Invalid number format!", 
                "Input Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        transaksijual j  = new transaksijual();
        j.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
                                           
    int row = jTable1.getSelectedRow();
    if (row >= 0) {
        kodejual.setText(jTable1.getValueAt(row, 0).toString());
        String dateStr = jTable1.getValueAt(row, 1).toString();
        try {
            java.util.Date date = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            jDateChooser1.setDate(date); 
        } catch (java.text.ParseException ex) {
            JOptionPane.showMessageDialog(this, "Error parsing date: " + ex.getMessage(), 
                    "Date Error", JOptionPane.ERROR_MESSAGE);
        }

        String hargaJasa = jTable1.getValueAt(row, 2).toString();
        if (hargaJasa.equals("30000")) {
            jComboBox1.setSelectedIndex(0);
        } else if (hargaJasa.equals("15000")) {
            jComboBox1.setSelectedIndex(1);
        }
        platnomor.setText(jTable1.getValueAt(row, 3).toString());
    }
    }//GEN-LAST:event_jTable1MouseClicked

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        int row = jTable1.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Silahkan pilih data yang akan dihapus",
                "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String platNomor = jTable1.getValueAt(row, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(this,
            "Apakah anda yakin ingin menghapus data kendaraan dengan plat nomor " + platNomor + "?",
            "Konfirmasi Hapus",
            JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = koneksi.koneksiDB();
                PreparedStatement stmt = conn.prepareStatement(
                    "DELETE FROM kendaraan WHERE Plat_Nomor = ?")) {

                stmt.setString(1, platNomor);

                int result = stmt.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(this,
                        "Data berhasil dihapus!",
                        "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    clearFields();
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Gagal menghapus data",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,
                    "Error menghapus data: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btndeleteActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(transaksijual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksijual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksijual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksijual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new transaksijual().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndelete;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField kodejual;
    private javax.swing.JTextField platnomor;
    // End of variables declaration//GEN-END:variables
}
