/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package application.views;

import application.dao.AlternatifDao;
import application.dao.HasilTukDao;
import application.dao.RangkingDao;
import application.dao.TukDao;
import application.daoimpl.AlternatifDaoImpl;
import application.daoimpl.HasilTukDaoImpl;
import application.daoimpl.RangkingDaoImpl;
import application.daoimpl.TukDaoImpl;
import application.models.AlternatifModel;
import application.models.FuzzyResult;
import application.models.FuzzyValue;
import application.models.HasilTukModel;
import application.models.RangkingModel;
import application.models.TukModel;
import application.utils.DatabaseUtil;
import application.utils.FuzzyLogic;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mhdja
 */
public class PerhitunganSAW extends javax.swing.JPanel {
    public final TukDao tukDao;
    public final HasilTukDao hasilTukDao;
    public int selectedIdTuk;
    public int hasilNilaiAkhir;
    public String remarksAkhir;
    
     public void getFuzzyResult(List<FuzzyResult> hasilFuzzy) {
        // Ambil data karyawan dari database
        List<FuzzyResult> fuzzyResult = hasilFuzzy;

        // Set Model untuk JTable
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{
            "Nama", "Nilai Keanggotaan"
        });

        // Masukkan data karyawan ke dalam model JTable
        for (FuzzyResult fuzzyList : fuzzyResult) {
            model.addRow(new Object[]{
                fuzzyList.getVariabel(),
                fuzzyList.getNilaiMembership()
            });
        }

        // Set model ke JTable
        jTable1.setModel(model);
     }
    
     public void setHasil (String hasil) {
      jLabel2.setText("Evaluasi Tempat Uji Kelayakan LAS menyatakan bahwa ini: " + hasil);
     }

    /**
     * Creates new form PerhitunganSAW
     */
    public PerhitunganSAW() {
        this.tukDao = new TukDaoImpl();
        this.hasilTukDao = new HasilTukDaoImpl();
        
        initComponents();
        
        jPanel2.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10); // jarak antar komponen
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;

        // Baris 1: Dropdown
        gbc.gridx = 0; gbc.gridy = row;
        jPanel2.add(new JLabel("Tempat Uji Kompetensi:"), gbc);

        gbc.gridx = 1;
        
        List<TukModel> tukList = tukDao.findAll();
        
        JComboBox<String> dropdown = new JComboBox<>();
        
        // Tambahkan item ke dropdown
        for (TukModel tuk : tukList) {
            dropdown.addItem(tuk.getNamaTuk());
            this.selectedIdTuk = tuk.getId();
        }
        
        dropdown.setPreferredSize(new Dimension(200, 25));
        jPanel2.add(dropdown, gbc);

        // Baris 2: Nilai Kompetensi
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        jPanel2.add(new JLabel("Nilai Kompetensi (1 - 100):"), gbc);

        gbc.gridx = 1;
        JTextField kompetensiField = new JTextField(15);
        jPanel2.add(kompetensiField, gbc);

        // Baris 3: Nilai Sarana
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        jPanel2.add(new JLabel("Nilai Sarana (1 - 100):"), gbc);

        gbc.gridx = 1;
        JTextField saranaField = new JTextField(15);
        jPanel2.add(saranaField, gbc);

        // Baris 4: Nilai Dokumen
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        jPanel2.add(new JLabel("Nilai Dokumen (1 - 100):"), gbc);

        gbc.gridx = 1;
        JTextField dokumenField = new JTextField(15);
        jPanel2.add(dokumenField, gbc);

        // Baris 5: Nilai Peralatan
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        jPanel2.add(new JLabel("Nilai Peralatan (1 - 100):"), gbc);

        gbc.gridx = 1;
        JTextField peralatanField = new JTextField(15);
        jPanel2.add(peralatanField, gbc);

        // Tambahkan tombol simpan di bawah (opsional)
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton simpanButton = new JButton("Hasil Perhitungan");
        jPanel2.add(simpanButton, gbc);
   
        jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.X_AXIS));
        jPanel3.setBorder(BorderFactory.createTitledBorder("Rumus Fungsi Keanggotaan"));

        // Rumus segitiga
        JLabel rumusSegitiga = new JLabel("<html><body>" +
                "<b>Segitiga:</b><br>" +
                "μ(x) = {<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;0, jika x ≤ a atau x ≥ c<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;(x - a)/(b - a), jika a &lt; x &lt; b<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;1, jika x = b<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;(c - x)/(c - b), jika b &lt; x &lt; c<br>" +
                "}" +
                "</body></html>");

        // Rumus segitiga naik
        JLabel rumusNaik = new JLabel("<html><body>" +
                "<b>Segitiga Naik:</b><br>" +
                "μ(x) = {<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;0, jika x ≤ a<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;(x - a)/(b - a), jika a &lt; x &lt; b<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;1, jika x ≥ b<br>" +
                "}" +
                "</body></html>");

        // Rumus segitiga turun
        JLabel rumusTurun = new JLabel("<html><body>" +
                "<b>Segitiga Turun:</b><br>" +
                "μ(x) = {<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;1, jika x ≤ a<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;(b - x)/(b - a), jika a &lt; x &lt; b<br>" +
                "&nbsp;&nbsp;&nbsp;&nbsp;0, jika x ≥ b<br>" +
                "}" +
                "</body></html>");

        // Tambahkan semua ke panel
        jPanel3.add(rumusSegitiga);
        jPanel3.add(rumusNaik);
        jPanel3.add(rumusTurun);
        
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ambil nilai dari dropdown
                String tempatUji = (String) dropdown.getSelectedItem();

                // Ambil nilai dari inputan
                String nilaiKompetensi = kompetensiField.getText();
                String nilaiSarana = saranaField.getText();
                String nilaiDokumen = dokumenField.getText();
                String nilaiPeralatan = peralatanField.getText();

                // Optional: Validasi input angka 1–100
                try {
                    double kompetensi = Integer.parseInt(nilaiKompetensi);
                    double sarana = Integer.parseInt(nilaiSarana);
                    double dokumen = Integer.parseInt(nilaiDokumen);
                    double peralatan = Integer.parseInt(nilaiPeralatan);

                    if (kompetensi < 1 || kompetensi > 100 || 
                        sarana < 1 || sarana > 100 || 
                        dokumen < 1 || dokumen > 100 || 
                        peralatan < 1 || peralatan > 100) {
                        JOptionPane.showMessageDialog(null, "Semua nilai harus antara 1 dan 100", "Validasi", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    
                    handleFuzzyLogic(kompetensi, sarana, dokumen, peralatan);


                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Masukkan nilai angka yang valid!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
    
    public void handleFuzzyLogic (double kompetensi, double sarana, double dokumen, double peralatan) {
        Map<String, Double> inputs = Map.of(
            "kompetensi", kompetensi,
            "sarana", sarana,
            "dokumen", dokumen,
            "peralatan", peralatan // ← Tambahkan ini
        );
        
        FuzzyLogic fuzzy = new FuzzyLogic();
        
        Map<String, FuzzyValue> hasilFuzzyValue = new HashMap<>();
        List<FuzzyResult> hasilFuzzy = new ArrayList<>();

        for (Map.Entry<String, Double> entry : inputs.entrySet()) {
            String variabel = entry.getKey();
            double nilai = entry.getValue();

            FuzzyValue fv = fuzzy.fuzzify(nilai);
            hasilFuzzyValue.put(variabel, fv); // simpan untuk proses rule selanjutnya  

            if (fv.rendah > 0)
            hasilFuzzy.add(new FuzzyResult(
                variabel, 
                nilai, 
                "rendah", 
                fv.rendah, 
                "segitiga turun"
            ));

        if (fv.cukup > 0)
            hasilFuzzy.add(new FuzzyResult(
                variabel, 
                nilai, 
                "cukup", 
                fv.cukup, 
                "segitiga"
            ));

        if (fv.tinggi > 0)
            hasilFuzzy.add(new FuzzyResult(
                variabel, 
                nilai, 
                "tinggi", 
                fv.tinggi, 
                "segitiga naik"
            ));

        }
        
        System.out.println("=== Hasil Fuzzifikasi ===");
        for (FuzzyResult fr : hasilFuzzy) {
            System.out.println(fr);
        }

        // Gunakan untuk rule
        double defuzzifikasi = fuzzy.applyRules(
            hasilFuzzyValue.get("kompetensi"),
            hasilFuzzyValue.get("sarana"),
            hasilFuzzyValue.get("dokumen"),
            hasilFuzzyValue.get("peralatan")
        );

        System.out.println("\n== Hasil Akhir ==");
        System.out.println("Nilai Defuzzifikasi Z: " + defuzzifikasi);
        System.out.println("Kesimpulan: " + fuzzy.finalConclusion(defuzzifikasi));
        
        this.hasilNilaiAkhir = (int) defuzzifikasi;
        this.remarksAkhir = fuzzy.finalConclusion(defuzzifikasi);
        
        getFuzzyResult(hasilFuzzy);
        setHasil(fuzzy.finalConclusion(defuzzifikasi).toUpperCase());
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("EVALUASI KELAYAKAN ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 222, Short.MAX_VALUE)
        );

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
        jScrollPane2.setViewportView(jTable1);

        jButton1.setText("RESET");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("SIMPAN HASIL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(255, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 837, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Reset semua baris lama
        
        jLabel2.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            // Buat model dan set datanya
            HasilTukModel hasil = new HasilTukModel();
            hasil.setIdTuk(this.selectedIdTuk);
            hasil.setNilai(this.hasilNilaiAkhir);
            hasil.setRemarks(this.remarksAkhir);

            int idBaru = hasilTukDao.create(hasil);

            if (idBaru > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan dengan ID: " + idBaru);
                // reset form atau refresh table jika perlu
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan data.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Pastikan nilai angka diisi dengan benar.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
