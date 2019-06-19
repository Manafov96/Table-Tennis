/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewTeam;

import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import Tools.DropDown;
import static Tools.getConnection.getConnection;
import static Tools.setValuesComboBox.setValuesComboBox;

/**
 *
 * @author Viko
 */
public class NewTeam extends javax.swing.JFrame {

    String ImgPath = null;
    int pos = 0;

    /**
     * Creates new form TableTennis
     */
    public NewTeam() {
        initComponents();
        setValuesComboBox("select C.ID, C.CITY from CITIES C", jcbCity, true, -1, false);
    }

    public boolean checkInputs() {
        if (jtxtTeamName.getText() == null
                || jDateEstablishment.getDate() == null
                || jcbCity.getSelectedIndex() == 0
                || jtaAddress.getText() == null
                || jtxtMobilePhone.getText() == null) {
            return false;
        }
        return true;
    }

    public ImageIcon ResizeImage(String ImagePath, byte[] pic) {
        ImageIcon myImage = null;
        if (ImagePath != null) {
            myImage = new ImageIcon(ImagePath);
        } else {
            myImage = new ImageIcon(pic);
        }

        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(jPhoto.getWidth(), jPhoto.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jDate = new javax.swing.JLabel();
        jtxtTeamName = new javax.swing.JTextField();
        jPhoto = new javax.swing.JLabel();
        jbtnChoose = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jDateEstablishment = new com.toedter.calendar.JDateChooser();
        jFirstName1 = new javax.swing.JLabel();
        jCity = new javax.swing.JLabel();
        jcbCity = new javax.swing.JComboBox<>();
        jAddress = new javax.swing.JLabel();
        jtxtMobilePhone = new javax.swing.JTextField();
        jMobilePhone1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaAddress = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setTitle("New Team");
        setBackground(new java.awt.Color(255, 255, 51));
        setMaximumSize(null);
        setPreferredSize(new java.awt.Dimension(778, 670));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel4.setFont(new java.awt.Font("Maiandra GD", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("NEW TEAM");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 340, 100));

        jDate.setBackground(new java.awt.Color(255, 255, 255));
        jDate.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jDate.setForeground(new java.awt.Color(255, 255, 255));
        jDate.setText("Date of Establishment:");
        getContentPane().add(jDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 210, 40));
        getContentPane().add(jtxtTeamName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, 220, 30));

        jPhoto.setBackground(new java.awt.Color(0, 0, 153));
        jPhoto.setOpaque(true);
        getContentPane().add(jPhoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 170, 140));

        jbtnChoose.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnChoose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NewTeam/Choose Photo.png"))); // NOI18N
        jbtnChoose.setText("Choose Photo");
        jbtnChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnChooseActionPerformed(evt);
            }
        });
        getContentPane().add(jbtnChoose, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, 170, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NewTeam/Insert.png"))); // NOI18N
        jButton2.setText("Insert");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 560, 100, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PlayerInfo/back.png"))); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 100, -1));
        getContentPane().add(jDateEstablishment, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 220, 30));

        jFirstName1.setBackground(new java.awt.Color(255, 255, 255));
        jFirstName1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jFirstName1.setForeground(new java.awt.Color(255, 255, 255));
        jFirstName1.setText("Team Name :");
        getContentPane().add(jFirstName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 160, 40));

        jCity.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jCity.setForeground(new java.awt.Color(255, 255, 255));
        jCity.setText("City:");
        getContentPane().add(jCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 160, 40));

        jcbCity.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(jcbCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 220, 30));

        jAddress.setBackground(new java.awt.Color(255, 255, 255));
        jAddress.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jAddress.setForeground(new java.awt.Color(255, 255, 255));
        jAddress.setText("Address:");
        getContentPane().add(jAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 160, 40));
        getContentPane().add(jtxtMobilePhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 360, 220, 30));

        jMobilePhone1.setBackground(new java.awt.Color(255, 255, 255));
        jMobilePhone1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jMobilePhone1.setForeground(new java.awt.Color(255, 255, 255));
        jMobilePhone1.setText("Mobile Phone:");
        getContentPane().add(jMobilePhone1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, 160, 40));

        jtaAddress.setColumns(20);
        jtaAddress.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jtaAddress.setRows(5);
        jScrollPane2.setViewportView(jtaAddress);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 420, 220, 90));

        jPanel2.setBackground(new java.awt.Color(0, 51, 204));
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 80));

        jPanel3.setBackground(new java.awt.Color(51, 51, 255));
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 780, 590));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnChooseActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            jPhoto.setIcon(ResizeImage(path, null));
            ImgPath = path;
        } else {
            System.out.println("No File selected");
        }

    }//GEN-LAST:event_jbtnChooseActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (checkInputs() && ImgPath != null) {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("insert into TEAMS (TEAM, PICTURE, ADDRESS, ESTABLISHMENT, CITY_REF, MOBILE_PHONE)"
                        + "values (?,?,?,?,?,?)");

                ps.setString(1, jtxtTeamName.getText());
                InputStream img = new FileInputStream(new File(ImgPath));
                ps.setBlob(2, img);
                ps.setString(3, jtaAddress.getText());
                java.sql.Date sqldate = new java.sql.Date(jDateEstablishment.getDate().getTime());
                ps.setDate(4, sqldate);
                ps.setInt(5, ((DropDown) jcbCity.getSelectedItem()).getId());
                ps.setString(6, jtxtMobilePhone.getText());

                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Inserted!");
            } catch (HeadlessException | FileNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "One or more fields are empty!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(NewTeam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewTeam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewTeam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewTeam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new NewTeam().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jAddress;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jCity;
    private javax.swing.JLabel jDate;
    private com.toedter.calendar.JDateChooser jDateEstablishment;
    private javax.swing.JLabel jFirstName1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JLabel jMobilePhone1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jPhoto;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnChoose;
    private javax.swing.JComboBox<String> jcbCity;
    private javax.swing.JTextArea jtaAddress;
    private javax.swing.JTextField jtxtMobilePhone;
    private javax.swing.JTextField jtxtTeamName;
    // End of variables declaration//GEN-END:variables
}
