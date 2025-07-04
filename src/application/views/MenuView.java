/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package application.views;

import application.models.UserModel;
import application.utils.DatabaseUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mhdja
 */
public class MenuView extends javax.swing.JFrame {
    private UserModel loggedInUser;
    /**
     * Creates new form MenuView
     */
     private void showSubMenu(JPanel subMenu){
        if(subMenu.isVisible() == false){
            subMenu.setVisible(true);
        }else{
            subMenu.setVisible(false);
        }
    }
    
    public MenuView(UserModel user) {
        this.loggedInUser = user; // Simpan data user yang login
        initComponents();
        
        MainContent.add(Home);
        MainContent.repaint();
        MainContent.revalidate();
        
        slideMenuLaporan.setVisible(false);
        
//        restrictAccess();
    }
   
    public void start(UserModel user){
        JFrame frame = new MenuView(user);
        frame.setTitle("Menu Utama");
        
        UIManager.put("OptionPane.yesButtonText", "Ya");
        UIManager.put("OptionPane.noButtonText", "Tidak");
        
        // Membuka jendela dalam mode fullscreen
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame)e.getSource();

                int result = JOptionPane.showConfirmDialog(
                    frame,
                    "Apakah Anda yakin ingin keluar dari aplikasi?",
                    "Keluar dari Aplikasi",
                    JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION){
                    DatabaseUtil.getInstance().closeConnection();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    System.exit(0);
                }
            }
        });
        
       Home.setLayout(new GridBagLayout()); // agar isi panel di tengah otomatis

        JLabel homeLabel = new JLabel("SELAMAT DATANG DI APLIKASI");
        JLabel homeLabel2 = new JLabel("SISTEM PAKAR UNTUK EVALUASI KELAYAKAN DAN AKREDITASI TEMPAT UJI KOMPETENSI");
        JLabel homeLabel3 = new JLabel("(TUK) LAS BERDASARKAN STANDAR NASIONAL MENGGUNAKAN METODE FUZZY LOGIC");

        homeLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        homeLabel2.setFont(new Font("SansSerif", Font.BOLD, 24)); // bisa pakai font lain
        homeLabel3.setFont(new Font("SansSerif", Font.BOLD, 24)); // bisa pakai font lain

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0); // spasi antar komponen (atas, kiri, bawah, kanan)

        Home.add(homeLabel, gbc);

        gbc.gridy = 1; // baris kedua
        Home.add(homeLabel2, gbc);
        
        gbc.gridy = 2; // baris kedua
        Home.add(homeLabel3, gbc);

        
        // Panel utama
        jPanel1.setLayout(new BorderLayout()); // penting supaya anak komponen fleksibel ukurannya

        Sidebar.setBackground(Color.decode("#00BD4F"));
        Sidebar.setPreferredSize(new Dimension(250, 0)); // 200px lebar, tinggi otomatis
        jPanel1.add(Sidebar, BorderLayout.WEST);
        
        jPanel2.setLayout(new BorderLayout());
        
        Header.setLayout(new BorderLayout());
        Header.setBackground(Color.decode("#1b4526"));
        Header.setPreferredSize(new Dimension(0, 120));
        Header.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 40)); // Padding: atas, kiri, bawah, kanan

        // Panel kanan atas buat label user
        JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setOpaque(false); // Biar background-nya transparan

        // Label 1: Selamat datang
        JLabel welcomeLabel = new JLabel("Selamat datang, Admin");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        welcomeLabel.setAlignmentX(Component.RIGHT_ALIGNMENT); // Penting buat posisi kanan

        // Label 2: Role
        JLabel roleLabel = new JLabel("Role Kamu adalah, Admin");
        roleLabel.setForeground(Color.WHITE);
        roleLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        roleLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        // Tambahkan ke panel userInfo
        userInfoPanel.add(welcomeLabel);
        userInfoPanel.add(Box.createVerticalStrut(5)); // Jarak antar label
        userInfoPanel.add(roleLabel);

        // Tambahkan ke Header di kanan
        Header.add(userInfoPanel, BorderLayout.EAST);

        // MainContent (di bawah header)
        MainContent.setBackground(Color.WHITE);
        MainContent.setPreferredSize(new Dimension(0, 600));
        
        // Tambahkan header dan main content ke panel tengah
        jPanel2.add(Header, BorderLayout.NORTH);
        jPanel2.add(MainContent, BorderLayout.CENTER);
        
        jPanel1.add(jPanel2, BorderLayout.CENTER);
        
        // Set ke frame
        frame.setContentPane(jPanel1);
        
        frame.setVisible( true );   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Home = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Sidebar = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        listData = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        perhitungan = new javax.swing.JLabel();
        perhitungan1 = new javax.swing.JLabel();
        slideMenuLaporan = new javax.swing.JPanel();
        laporanCalonPelamar = new javax.swing.JLabel();
        laporanPrioritasKriteria = new javax.swing.JLabel();
        laporanHasilSeleksi = new javax.swing.JLabel();
        perhitungan2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        MainContent = new javax.swing.JPanel();

        Home.setPreferredSize(new java.awt.Dimension(700, 700));

        javax.swing.GroupLayout HomeLayout = new javax.swing.GroupLayout(Home);
        Home.setLayout(HomeLayout);
        HomeLayout.setHorizontalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );
        HomeLayout.setVerticalGroup(
            HomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        Sidebar.setBackground(new java.awt.Color(0, 0, 0));
        Sidebar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SidebarMouseEntered(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("              KELUAR");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });

        listData.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        listData.setForeground(new java.awt.Color(255, 255, 255));
        listData.setText("      MASTER DATA TUK");
        listData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listDataMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                listDataMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                listDataMouseExited(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/logo_lsp-removebg-preview.png"))); // NOI18N

        perhitungan.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        perhitungan.setForeground(new java.awt.Color(255, 255, 255));
        perhitungan.setText("      EVALUASI KELAYAKAN");
        perhitungan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perhitunganMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                perhitunganMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                perhitunganMouseExited(evt);
            }
        });

        perhitungan1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        perhitungan1.setForeground(new java.awt.Color(255, 255, 255));
        perhitungan1.setText("      PRESENTASE");
        perhitungan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perhitungan1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                perhitungan1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                perhitungan1MouseExited(evt);
            }
        });

        slideMenuLaporan.setBackground(new java.awt.Color(168, 140, 59));
        slideMenuLaporan.setForeground(new java.awt.Color(255, 255, 255));

        laporanCalonPelamar.setBackground(new java.awt.Color(168, 140, 59));
        laporanCalonPelamar.setForeground(new java.awt.Color(255, 255, 255));
        laporanCalonPelamar.setText("    Laporan Data Tempat Uji Kompetensi");
        laporanCalonPelamar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        laporanCalonPelamar.setOpaque(true);
        laporanCalonPelamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laporanCalonPelamarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                laporanCalonPelamarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                laporanCalonPelamarMouseExited(evt);
            }
        });

        laporanPrioritasKriteria.setBackground(new java.awt.Color(168, 140, 59));
        laporanPrioritasKriteria.setForeground(new java.awt.Color(255, 255, 255));
        laporanPrioritasKriteria.setText("    Laporan Data Hasil Evaluasi");
        laporanPrioritasKriteria.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        laporanPrioritasKriteria.setOpaque(true);
        laporanPrioritasKriteria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laporanPrioritasKriteriaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                laporanPrioritasKriteriaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                laporanPrioritasKriteriaMouseExited(evt);
            }
        });

        laporanHasilSeleksi.setBackground(new java.awt.Color(168, 140, 59));
        laporanHasilSeleksi.setForeground(new java.awt.Color(255, 255, 255));
        laporanHasilSeleksi.setText("    Laporan Data Presentase Hasil");
        laporanHasilSeleksi.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        laporanHasilSeleksi.setOpaque(true);
        laporanHasilSeleksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laporanHasilSeleksiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                laporanHasilSeleksiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                laporanHasilSeleksiMouseExited(evt);
            }
        });

        javax.swing.GroupLayout slideMenuLaporanLayout = new javax.swing.GroupLayout(slideMenuLaporan);
        slideMenuLaporan.setLayout(slideMenuLaporanLayout);
        slideMenuLaporanLayout.setHorizontalGroup(
            slideMenuLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(laporanPrioritasKriteria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(laporanHasilSeleksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(laporanCalonPelamar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        slideMenuLaporanLayout.setVerticalGroup(
            slideMenuLaporanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(slideMenuLaporanLayout.createSequentialGroup()
                .addComponent(laporanCalonPelamar, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(laporanPrioritasKriteria, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(laporanHasilSeleksi, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        perhitungan2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        perhitungan2.setForeground(new java.awt.Color(255, 255, 255));
        perhitungan2.setText("      CETAK LAPORAN");
        perhitungan2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                perhitungan2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                perhitungan2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                perhitungan2MouseExited(evt);
            }
        });

        javax.swing.GroupLayout SidebarLayout = new javax.swing.GroupLayout(Sidebar);
        Sidebar.setLayout(SidebarLayout);
        SidebarLayout.setHorizontalGroup(
            SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SidebarLayout.createSequentialGroup()
                .addGroup(SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(listData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(SidebarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(perhitungan, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
                    .addComponent(perhitungan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(perhitungan2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(slideMenuLaporan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SidebarLayout.setVerticalGroup(
            SidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidebarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(listData)
                .addGap(18, 18, 18)
                .addComponent(perhitungan)
                .addGap(18, 18, 18)
                .addComponent(perhitungan1)
                .addGap(18, 18, 18)
                .addComponent(perhitungan2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(slideMenuLaporan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout HeaderLayout = new javax.swing.GroupLayout(Header);
        Header.setLayout(HeaderLayout);
        HeaderLayout.setHorizontalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1771, Short.MAX_VALUE)
        );
        HeaderLayout.setVerticalGroup(
            HeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 117, Short.MAX_VALUE)
        );

        MainContent.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(MainContent, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 990, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(MainContent, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(923, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(Sidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(114, 114, 114))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Sidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 168, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SidebarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SidebarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_SidebarMouseEntered

    private void listDataMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listDataMouseEntered
        // TODO add your handling code here:
        listData.setBackground(new Color(41,44,69));
        listData.setCursor(new Cursor(Cursor.HAND_CURSOR));
        listData.setForeground(new Color(255, 187, 0));
    }//GEN-LAST:event_listDataMouseEntered

    private void listDataMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listDataMouseExited
        // TODO add your handling code here:
        listData.setBackground(new Color(45,49,74));
        listData.setForeground(Color.white);
    }//GEN-LAST:event_listDataMouseExited

    private void listDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listDataMouseClicked
        // TODO add your handling code here:
        MainContent.removeAll();
        MainContent.repaint();
        MainContent.revalidate();

        // add Panel, add panel
        MainContent.add(new MasterDataTUKView());
        MainContent.repaint();
        MainContent.revalidate();
    }//GEN-LAST:event_listDataMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
       int result = JOptionPane.showConfirmDialog(
            this,
            "Apakah Anda yakin ingin keluar dari aplikasi?",
            "Konfirmasi Keluar",
            JOptionPane.YES_NO_OPTION
        );

        if (result == JOptionPane.YES_OPTION) {
            DatabaseUtil.getInstance().closeConnection(); // Tutup koneksi DB (kalau ada)
            System.exit(0); // Keluar dari aplikasi
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered
        jLabel3.setBackground(new Color(41,44,69));
        jLabel3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jLabel3.setForeground(new Color(255, 187, 0));
    }//GEN-LAST:event_jLabel3MouseEntered

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited
        // TODO add your handling code here:
        jLabel3.setBackground(new Color(45,49,74));
        jLabel3.setForeground(Color.white);
    }//GEN-LAST:event_jLabel3MouseExited

    private void perhitunganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perhitunganMouseClicked
        // TODO add your handling code here:
        MainContent.removeAll();
        MainContent.repaint();
        MainContent.revalidate();

        // add Panel, add panel
        MainContent.add(new PerhitunganSAW());
        MainContent.repaint();
        MainContent.revalidate();
    }//GEN-LAST:event_perhitunganMouseClicked

    private void perhitunganMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perhitunganMouseEntered
        // TODO add your handling code here:
        perhitungan.setBackground(new Color(41,44,69));
        perhitungan.setCursor(new Cursor(Cursor.HAND_CURSOR));
        perhitungan.setForeground(new Color(255, 187, 0));
    }//GEN-LAST:event_perhitunganMouseEntered

    private void perhitunganMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perhitunganMouseExited
        // TODO add your handling code here:
        perhitungan.setBackground(new Color(45,49,74));
        perhitungan.setForeground(Color.white);
    }//GEN-LAST:event_perhitunganMouseExited

    private void perhitungan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perhitungan1MouseClicked
        // TODO add your handling code here:
        MainContent.removeAll();
        MainContent.repaint();
        MainContent.revalidate();

        // add Panel, add panel
        MainContent.add(new HasilFuzzy());
        MainContent.repaint();
        MainContent.revalidate();
    }//GEN-LAST:event_perhitungan1MouseClicked

    private void perhitungan1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perhitungan1MouseEntered
        // TODO add your handling code here:
        perhitungan1.setBackground(new Color(41,44,69));
        perhitungan1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        perhitungan1.setForeground(new Color(255, 187, 0));
    }//GEN-LAST:event_perhitungan1MouseEntered

    private void perhitungan1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perhitungan1MouseExited
        // TODO add your handling code here:
        perhitungan1.setBackground(new Color(45,49,74));
        perhitungan1.setForeground(Color.white);
    }//GEN-LAST:event_perhitungan1MouseExited

    private void laporanCalonPelamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanCalonPelamarMouseClicked
        // TODO add your handling code here:
        try {
            String templateName = "LaporanTUKFuzzy.jrxml";
            InputStream reportStream = ReportView.class.getResourceAsStream("/resources/reports/" + templateName);
            JasperDesign jd = JRXmlLoader.load(reportStream);

            Connection dbConnection = DatabaseUtil.getInstance().getConnection();

            JasperReport jr = JasperCompileManager.compileReport(jd);

            HashMap parameter = new HashMap();
            parameter.put("PATH","src/resources/images/");

            JasperPrint jp = JasperFillManager.fillReport(jr,parameter, dbConnection);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(ReportView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_laporanCalonPelamarMouseClicked

    private void laporanCalonPelamarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanCalonPelamarMouseEntered
        // TODO add your handling code here:
        laporanCalonPelamar.setBackground(new Color(51,51,51));
        laporanCalonPelamar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        laporanCalonPelamar.setForeground(new Color(255, 187, 0));
    }//GEN-LAST:event_laporanCalonPelamarMouseEntered

    private void laporanCalonPelamarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanCalonPelamarMouseExited
        // TODO add your handling code here:
        laporanCalonPelamar.setBackground(new Color(168, 140, 59));
        laporanCalonPelamar.setForeground(Color.white);
    }//GEN-LAST:event_laporanCalonPelamarMouseExited

    private void laporanPrioritasKriteriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanPrioritasKriteriaMouseClicked
        // TODO add your handling code here:
        try {
            String templateName = "LaporanTUKHasilEvaluasi.jrxml";
            InputStream reportStream = ReportView.class.getResourceAsStream("/resources/reports/" + templateName);
            JasperDesign jd = JRXmlLoader.load(reportStream);

            Connection dbConnection = DatabaseUtil.getInstance().getConnection();

            JasperReport jr = JasperCompileManager.compileReport(jd);

            HashMap parameter = new HashMap();
            parameter.put("PATH","src/resources/images/");

            JasperPrint jp = JasperFillManager.fillReport(jr,parameter, dbConnection);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(ReportView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_laporanPrioritasKriteriaMouseClicked

    private void laporanPrioritasKriteriaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanPrioritasKriteriaMouseEntered
        // TODO add your handling code here:
        laporanPrioritasKriteria.setBackground(new Color(51,51,51));
        laporanPrioritasKriteria.setCursor(new Cursor(Cursor.HAND_CURSOR));
        laporanPrioritasKriteria.setForeground(new Color(255, 187, 0));
    }//GEN-LAST:event_laporanPrioritasKriteriaMouseEntered

    private void laporanPrioritasKriteriaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanPrioritasKriteriaMouseExited
        // TODO add your handling code here:
        laporanPrioritasKriteria.setBackground(new Color(168, 140, 59));
        laporanPrioritasKriteria.setForeground(Color.white);
    }//GEN-LAST:event_laporanPrioritasKriteriaMouseExited

    private void laporanHasilSeleksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanHasilSeleksiMouseClicked
        // TODO add your handling code here:
        try {
            String templateName = "LaporanTUKPresentase.jrxml";
            InputStream reportStream = ReportView.class.getResourceAsStream("/resources/reports/" + templateName);
            JasperDesign jd = JRXmlLoader.load(reportStream);

            Connection dbConnection = DatabaseUtil.getInstance().getConnection();

            JasperReport jr = JasperCompileManager.compileReport(jd);

            HashMap parameter = new HashMap();
            parameter.put("PATH","src/resources/images/");

            JasperPrint jp = JasperFillManager.fillReport(jr,parameter, dbConnection);
            JasperViewer.viewReport(jp, false);
        } catch (JRException ex) {
            Logger.getLogger(ReportView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_laporanHasilSeleksiMouseClicked

    private void laporanHasilSeleksiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanHasilSeleksiMouseEntered
        // TODO add your handling code here:
        laporanHasilSeleksi.setBackground(new Color(51,51,51));
        laporanHasilSeleksi.setCursor(new Cursor(Cursor.HAND_CURSOR));
        laporanHasilSeleksi.setForeground(new Color(255, 187, 0));
    }//GEN-LAST:event_laporanHasilSeleksiMouseEntered

    private void laporanHasilSeleksiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laporanHasilSeleksiMouseExited
        // TODO add your handling code here:
        laporanHasilSeleksi.setBackground(new Color(168, 140, 59));
        laporanHasilSeleksi.setForeground(Color.white);
    }//GEN-LAST:event_laporanHasilSeleksiMouseExited

    private void perhitungan2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perhitungan2MouseClicked
        // TODO add your handling code here:
        showSubMenu(slideMenuLaporan);
    }//GEN-LAST:event_perhitungan2MouseClicked

    private void perhitungan2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perhitungan2MouseEntered
        // TODO add your handling code here:
        perhitungan2.setBackground(new Color(41,44,69));
        perhitungan2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        perhitungan2.setForeground(new Color(255, 187, 0));
    }//GEN-LAST:event_perhitungan2MouseEntered

    private void perhitungan2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_perhitungan2MouseExited
        // TODO add your handling code here:
        perhitungan2.setBackground(new Color(45,49,74));
        perhitungan2.setForeground(Color.white);
    }//GEN-LAST:event_perhitungan2MouseExited

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
            java.util.logging.Logger.getLogger(MenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MenuView().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel MainContent;
    private javax.swing.JPanel Sidebar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel laporanCalonPelamar;
    private javax.swing.JLabel laporanHasilSeleksi;
    private javax.swing.JLabel laporanPrioritasKriteria;
    private javax.swing.JLabel listData;
    private javax.swing.JLabel perhitungan;
    private javax.swing.JLabel perhitungan1;
    private javax.swing.JLabel perhitungan2;
    private javax.swing.JPanel slideMenuLaporan;
    // End of variables declaration//GEN-END:variables
}
