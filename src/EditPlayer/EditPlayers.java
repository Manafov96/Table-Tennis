package EditPlayer;

import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import Tools.DropDown;
import static Tools.getConnection.getConnection;
import static Tools.setValuesComboBox.setValuesComboBox;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Viko
 */
public class EditPlayers extends javax.swing.JFrame {

    /**
     * Creates new form MainWindows
     */
    public EditPlayers() {
        initComponents();
        Show_Products_In_JTable();
    }
    String ImgPath = null;
    int pos = 0;

    public ImageIcon ResizeImage(String ImagePath, byte[] pic) {
        ImageIcon myImage = null;
        if (ImagePath != null) {
            myImage = new ImageIcon(ImagePath);
        } else {
            myImage = new ImageIcon(pic);
        }

        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(jChoosedPicture.getWidth(), jChoosedPicture.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }

    public boolean checkInputs() {
        if (jtxtMobilePhone.getText() == null
                || jtxtLastName.getText() == null
                || jcbTeam.getSelectedIndex() == -1
                || ImgPath == null) {
            return false;

        }
        return false;
    }

    private ArrayList<EditPlayer> getProductList() {
        ArrayList<EditPlayer> productList = new ArrayList<>();
        Connection con = getConnection();
        String query = "select  "
                + " P.ID, P.FIRSTNAME, P.LASTNAME, T.ID TEAM_ID, T.TEAM TEAM, P.PICTURE, P.PHONE\n"
                + " from "
                + " PLAYERS P\n"
                + " join TEAMS  T on T.ID = P.TEAM_REF\n"
                + "order by P.ID";

        Statement st;
        ResultSet rs;
        Vector<DropDown> tableTeam = new Vector<>();
        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            EditPlayer EditPlayer;

            while (rs.next()) {
                tableTeam.addElement(new DropDown(rs.getInt("TEAM_ID"), rs.getString("TEAM")));
                jcbTeamTable.setModel(new DefaultComboBoxModel(tableTeam));
                EditPlayer = new EditPlayer(rs.getInt("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), jcbTeamTable.getSelectedItem(), rs.getString("PHONE"), rs.getBytes("PICTURE"));
                productList.add(EditPlayer);
                tableTeam.clear();
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditPlayers.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productList;

    }

    private void Show_Products_In_JTable() {
        ArrayList<EditPlayer> list = getProductList();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getFirstName();
            row[2] = list.get(i).getLastName();
            row[3] = list.get(i).getTeam();
            row[4] = list.get(i).getMobilePhone();
            model.addRow(row);
        }

    }

    private void setTeams() {
        String sql = "Select T.ID, T.TEAM from TEAMS T \n"
                + "where\n"
                + "  (select count(0) from PLAYERS P where P.TEAM_REF = T.ID) < 2";
        setValuesComboBox(sql, jcbTeam, true, -1, false);
    }

    // Show Data In Inputs
    private void ShowItem(int index) {
        setTeams();
        jtxtID.setText(Integer.toString(getProductList().get(index).getId()));
        jtxtFirstName.setText(getProductList().get(index).getFirstName());
        jtxtLastName.setText(getProductList().get(index).getLastName());
        jcbTeam.getModel().setSelectedItem(getProductList().get(index).getTeam());
        jChoosedPicture.setIcon(ResizeImage(null, getProductList().get(index).getPicture()));
        jtxtMobilePhone.setText(getProductList().get(index).getMobilePhone());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcbTeamTable = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jChoosedPicture = new javax.swing.JLabel();
        jbtnChoosePicture = new javax.swing.JButton();
        jbtnDelete = new javax.swing.JButton();
        jbtnUpdate = new javax.swing.JButton();
        jbtnFirst = new javax.swing.JButton();
        jbtnNext = new javax.swing.JButton();
        jbtnPrevious = new javax.swing.JButton();
        jbtnLast = new javax.swing.JButton();
        jFirstName = new javax.swing.JLabel();
        jtxtMobilePhone = new javax.swing.JTextField();
        jLastName = new javax.swing.JLabel();
        jTeam = new javax.swing.JLabel();
        jcbTeam = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jtxtID = new javax.swing.JTextField();
        jtxtLastName = new javax.swing.JTextField();
        jMobilePhone = new javax.swing.JLabel();
        jtxtFirstName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jFirstName2 = new javax.swing.JLabel();

        setTitle("Edit Player");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jChoosedPicture.setBackground(new java.awt.Color(0, 0, 204));
        jChoosedPicture.setOpaque(true);
        jPanel1.add(jChoosedPicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 159, 117));

        jbtnChoosePicture.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jbtnChoosePicture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EditPlayer/Choose Photo.png"))); // NOI18N
        jbtnChoosePicture.setText("CHOOSE PICTURE");
        jbtnChoosePicture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnChoosePictureActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnChoosePicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, -1, -1));

        jbtnDelete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EditPlayer/delete.png"))); // NOI18N
        jbtnDelete.setText("DELETE");
        jbtnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnDeleteActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 480, -1, -1));

        jbtnUpdate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EditPlayer/update.png"))); // NOI18N
        jbtnUpdate.setText("UPDATE");
        jbtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 480, -1, -1));

        jbtnFirst.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EditPlayer/first.png"))); // NOI18N
        jbtnFirst.setText("FIRST");
        jbtnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFirstActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 420, 107, -1));

        jbtnNext.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EditPlayer/next.png"))); // NOI18N
        jbtnNext.setText("NEXT");
        jbtnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNextActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 420, 107, -1));

        jbtnPrevious.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EditPlayer/previous.png"))); // NOI18N
        jbtnPrevious.setText("PREVIOUS");
        jbtnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPreviousActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnPrevious, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 420, -1, -1));

        jbtnLast.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EditPlayer/last.png"))); // NOI18N
        jbtnLast.setText("LAST");
        jbtnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLastActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 420, 107, -1));

        jFirstName.setBackground(new java.awt.Color(255, 255, 255));
        jFirstName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jFirstName.setForeground(new java.awt.Color(255, 255, 255));
        jFirstName.setText("ID:");
        jPanel1.add(jFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 40, 40));
        jPanel1.add(jtxtMobilePhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 400, 220, 30));

        jLastName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLastName.setForeground(new java.awt.Color(255, 255, 255));
        jLastName.setText("Last Name:");
        jPanel1.add(jLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 300, 160, 40));

        jTeam.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTeam.setForeground(new java.awt.Color(255, 255, 255));
        jTeam.setText("Team:");
        jPanel1.add(jTeam, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 160, 40));

        jPanel1.add(jcbTeam, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 220, 30));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EditPlayer/back.png"))); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 100, -1));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "FirstName", "LastName", "Team", "Mobile Phone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTable1.setName(""); // NOI18N
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 70, 682, 318));

        jtxtID.setEditable(false);
        jtxtID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(jtxtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 270, 70, 30));

        jtxtLastName.setEditable(false);
        jPanel1.add(jtxtLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 220, 30));

        jMobilePhone.setBackground(new java.awt.Color(255, 255, 255));
        jMobilePhone.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jMobilePhone.setForeground(new java.awt.Color(255, 255, 255));
        jMobilePhone.setText("Mobile Phone:");
        jPanel1.add(jMobilePhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, 160, 40));

        jtxtFirstName.setEditable(false);
        jPanel1.add(jtxtFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 220, 30));

        jLabel4.setFont(new java.awt.Font("Maiandra GD", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("EDIT PLAYER");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, -10, 340, 100));

        jFirstName2.setBackground(new java.awt.Color(255, 255, 255));
        jFirstName2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jFirstName2.setForeground(new java.awt.Color(255, 255, 255));
        jFirstName2.setText("First Name:");
        jPanel1.add(jFirstName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 160, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 594, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdateActionPerformed
        // if(checkInputs())
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();

            // update without image
            if (ImgPath == null) {
                try {
                    UpdateQuery = "UPDATE PLAYERS SET"
                            + " TEAM_REF = ?, PHONE = ? WHERE ID = ?";
                    ps = con.prepareStatement(UpdateQuery);

                    ps.setInt(1, ((DropDown) jcbTeam.getSelectedItem()).getId());
                    ps.setString(2, jtxtMobilePhone.getText());
                    int ID = Integer.parseInt(jtxtID.getText());
                    ps.setInt(3, ID);
                    ps.executeUpdate();
                    Show_Products_In_JTable();
                    JOptionPane.showMessageDialog(null, "Player Updated");

                } catch (SQLException ex) {
                    Logger.getLogger(EditPlayers.class.getName()).log(Level.SEVERE, null, ex);
                }

            } // update With Image
            else {
                try {
                    InputStream img = new FileInputStream(new File(ImgPath));

                    UpdateQuery = "UPDATE PLAYERS SET "
                            + " TEAM_REF = ?, PICTURE = ?, PHONE = ? WHERE ID = ?";

                    ps = con.prepareStatement(UpdateQuery);

                    ps.setInt(1, ((DropDown) jcbTeam.getSelectedItem()).getId());

                    ps.setBlob(2, img);
                    ps.setString(3, jtxtMobilePhone.getText());
                    int ID = Integer.parseInt(jtxtID.getText());
                    ps.setInt(4, ID);
                    ps.executeUpdate();
                    Show_Products_In_JTable();
                    JOptionPane.showMessageDialog(null, "Player Updated");

                } catch (HeadlessException | FileNotFoundException | NumberFormatException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
            //}else{
            //    JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
        }

    }//GEN-LAST:event_jbtnUpdateActionPerformed

    private void jbtnChoosePictureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnChoosePictureActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            jChoosedPicture.setIcon(ResizeImage(path, null));
            ImgPath = path;
        } else {
            System.out.println("No File selected");
        }

    }//GEN-LAST:event_jbtnChoosePictureActionPerformed

    private void jbtnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFirstActionPerformed
        pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_jbtnFirstActionPerformed

    private void jbtnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNextActionPerformed
        pos++;

        if (pos >= getProductList().size()) {
            pos = getProductList().size() - 1;
        }

        ShowItem(pos);
    }//GEN-LAST:event_jbtnNextActionPerformed

    private void jbtnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnPreviousActionPerformed
        pos--;

        if (pos < 0) {
            pos = 0;
        }

        ShowItem(pos);
    }//GEN-LAST:event_jbtnPreviousActionPerformed

    private void jbtnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLastActionPerformed
        pos = getProductList().size() - 1;
        ShowItem(pos);
    }//GEN-LAST:event_jbtnLastActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jbtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnDeleteActionPerformed
        String UpdateQuery = null;
        PreparedStatement ps = null;
        Connection con = getConnection();
        try {
            UpdateQuery = "DELETE FROM PLAYERS  WHERE ID = ?";
            ps = con.prepareStatement(UpdateQuery);

            ps.setInt(1, Integer.parseInt(jtxtID.getText()));

            ps.executeUpdate();
            Show_Products_In_JTable();
            JOptionPane.showMessageDialog(null, "Player Deleted");

        } catch (SQLException ex) {
            Logger.getLogger(EditPlayers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jbtnDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(EditPlayers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditPlayers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditPlayers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditPlayers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new EditPlayers().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jChoosedPicture;
    private javax.swing.JLabel jFirstName;
    private javax.swing.JLabel jFirstName2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLastName;
    private javax.swing.JLabel jMobilePhone;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jTeam;
    private javax.swing.JButton jbtnChoosePicture;
    private javax.swing.JButton jbtnDelete;
    private javax.swing.JButton jbtnFirst;
    private javax.swing.JButton jbtnLast;
    private javax.swing.JButton jbtnNext;
    private javax.swing.JButton jbtnPrevious;
    private javax.swing.JButton jbtnUpdate;
    private javax.swing.JComboBox<String> jcbTeam;
    private javax.swing.JComboBox<String> jcbTeamTable;
    private javax.swing.JTextField jtxtFirstName;
    private javax.swing.JTextField jtxtID;
    private javax.swing.JTextField jtxtLastName;
    private javax.swing.JTextField jtxtMobilePhone;
    // End of variables declaration//GEN-END:variables
}
