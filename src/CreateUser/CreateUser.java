/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CreateUser;

import java.awt.HeadlessException;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Tools.DropDown;
import static Tools.getConnection.getConnection;
import static Tools.setValuesComboBox.setValuesComboBox;

/**
 *
 * @author Viko
 */
public class CreateUser extends javax.swing.JFrame {

    /**
     * Creates new form CreateUser
     */
    public CreateUser() {
        initComponents();
        Show_Users_In_JTable();
        setValuesComboBox("select C.ID, C.CITY from CITIES C", jcbCity, true, -1, false);
    }
    int pos = 0;

    public boolean checkInputs() {
        if (jtxtFName.getText() == null
                || jtxtLName.getText() == null
                || jcbCity.getSelectedIndex() == 0
                || jtxtPhone.getText() == null
                || jtaAddress.getText() == null) {
            return false;
        }
        return true;
    }

    private ArrayList<Users> getUsersList() {
        ArrayList<Users> UsersList = new ArrayList<>();
        Connection con = getConnection();
        String query = "select\n"
                + "  EC.ID, EC.FIRST_NAME, EC.LAST_NAME,  C.ID CITY_ID, C.CITY CITY, EC.ADDRESS, EC.PHONE_NUMBER\n"
                + "from\n"
                + "  EMPLOYMENT_CONTRACTS EC\n"
                + "  left join CITIES C on C.ID = EC.CITY_REF";

        Statement st;
        ResultSet rs;
        Vector<DropDown> tableCity = new Vector<>();
        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Users users;

            while (rs.next()) {
                tableCity.addElement(new DropDown(rs.getInt("CITY_ID"), rs.getString("CITY")));
                jcbCityTable.setModel(new DefaultComboBoxModel(tableCity));
                users = new Users(rs.getInt("ID"), rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"),
                        jcbCityTable.getModel().getSelectedItem(), rs.getString("ADDRESS"), rs.getString("PHONE_NUMBER"));
                UsersList.add(users);
                tableCity.clear();
            }

        } catch (SQLException ex) {
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return UsersList;

    }

    private void Show_Users_In_JTable() {
        ArrayList<Users> list = getUsersList();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[6];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getID();
            row[1] = list.get(i).getFName();
            row[2] = list.get(i).getLName();
            row[3] = list.get(i).getCity();
            row[4] = list.get(i).getAddress();
            row[5] = list.get(i).getPhone();
            model.addRow(row);
        }
    }

    private ArrayList<Users> getUsersListDetail(String ID) {
        ArrayList<Users> UsersList = new ArrayList<>();
        Connection con = getConnection();
        String query = "select\n"
                + "  U.ID, U.USERNAME, U.\"PASSWORD\"\n"
                + "from\n"
                + "  USERS U\n"
                + "where\n"
                + "   U.\"EMPLOYMENT CONTRACT\" =  " + ID;

        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Users users;

            while (rs.next()) {
                users = new Users(rs.getInt("ID"), rs.getString("USERNAME"), rs.getString("PASSWORD"));
                UsersList.add(users);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CreateUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return UsersList;

    }

    private void Show_Users_In_JTableDetail() {
        ArrayList<Users> list = getUsersListDetail(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        Object[] row = new Object[3];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getUserID();
            row[1] = list.get(i).getUser();
            row[2] = list.get(i).getPassword();
            model.addRow(row);

        }
    }

    private void InsertMaster() {
        try {

            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("insert into EMPLOYMENT_CONTRACTS (FIRST_NAME, LAST_NAME, "
                    + "CITY_REF, ADDRESS, PHONE_NUMBER)\n"
                    + "values (?,?,?,?,?)");
            ps.setString(1, jtxtFName.getText());
            ps.setString(2, jtxtLName.getText());
            ps.setInt(3, ((DropDown) jcbCity.getSelectedItem()).getId());
            ps.setString(4, jtaAddress.getText());
            ps.setString(5, jtxtPhone.getText());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Inserted!");
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void InsertDetail() {
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("insert into USERS (\"EMPLOYMENT CONTRACT\", USERNAME, \"PASSWORD\")\n"
                    + "values (?,?,?)");
            int ID = Integer.parseInt(jtxtID.getText());
            ps.setInt(1, ID);
            ps.setString(2, jtxtUsername.getText());
            ps.setString(3, jtxtPassword.getText());

            ps.executeUpdate();
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        JOptionPane.showMessageDialog(null, "Data Inserted");
    }

    private void ShowItem(int index) {
        jtxtID.setText(Integer.toString(getUsersList().get(index).getID()));
        jtxtFName.setText(getUsersList().get(index).getFName());
        jtxtLName.setText(getUsersList().get(index).getLName());
        jcbCity.setSelectedItem(getUsersList().get(index).getCity());
        jtaAddress.setText(getUsersList().get(index).getAddress());
        jtxtPhone.setText(getUsersList().get(index).getPhone());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcbCityTable = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jtxtID = new javax.swing.JTextField();
        jtxtFName = new javax.swing.JTextField();
        jtxtLName = new javax.swing.JTextField();
        jcbCity = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtaAddress = new javax.swing.JTextArea();
        jtxtPhone = new javax.swing.JTextField();
        jtxtUsername = new javax.swing.JTextField();
        jtxtPassword = new javax.swing.JPasswordField();
        jlPassword = new javax.swing.JLabel();
        jlID = new javax.swing.JLabel();
        jlFName = new javax.swing.JLabel();
        jlLName = new javax.swing.JLabel();
        jlCity = new javax.swing.JLabel();
        jlAddress = new javax.swing.JLabel();
        jlPhone = new javax.swing.JLabel();
        jlUser = new javax.swing.JLabel();
        jbtnFirst = new javax.swing.JButton();
        jbtnNext = new javax.swing.JButton();
        jbtnPrevious = new javax.swing.JButton();
        jbtnLast = new javax.swing.JButton();
        jbtnInsert = new javax.swing.JButton();
        jbtnUpdate = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setTitle("Create User");
        setAutoRequestFocus(false);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 153));
        jPanel1.setName(""); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Duels/back.png"))); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 510, -1, -1));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "User", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, 390, 140));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "City", "Address", "Mobile Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 610, 140));

        jtxtID.setEditable(false);
        jPanel1.add(jtxtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 40, 20));
        jPanel1.add(jtxtFName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 110, -1));
        jPanel1.add(jtxtLName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 110, -1));

        jPanel1.add(jcbCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 110, -1));

        jtaAddress.setColumns(20);
        jtaAddress.setRows(5);
        jScrollPane3.setViewportView(jtaAddress);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 240, 70));
        jPanel1.add(jtxtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 250, 110, -1));
        jPanel1.add(jtxtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 110, 20));
        jPanel1.add(jtxtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 110, -1));

        jlPassword.setForeground(new java.awt.Color(255, 255, 255));
        jlPassword.setText("Password:");
        jPanel1.add(jlPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, -1, -1));

        jlID.setForeground(new java.awt.Color(255, 255, 255));
        jlID.setText("ID:");
        jPanel1.add(jlID, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        jlFName.setForeground(new java.awt.Color(255, 255, 255));
        jlFName.setText("First Name:");
        jPanel1.add(jlFName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jlLName.setForeground(new java.awt.Color(255, 255, 255));
        jlLName.setText("Last Name:");
        jPanel1.add(jlLName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, -1, -1));

        jlCity.setForeground(new java.awt.Color(255, 255, 255));
        jlCity.setText("City:");
        jPanel1.add(jlCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, -1, -1));

        jlAddress.setForeground(new java.awt.Color(255, 255, 255));
        jlAddress.setText("Address:");
        jPanel1.add(jlAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, -1, -1));

        jlPhone.setForeground(new java.awt.Color(255, 255, 255));
        jlPhone.setText("Mobile Number:");
        jPanel1.add(jlPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, -1, -1));

        jlUser.setForeground(new java.awt.Color(255, 255, 255));
        jlUser.setText("User:");
        jPanel1.add(jlUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, -1, -1));

        jbtnFirst.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EditPlayer/first.png"))); // NOI18N
        jbtnFirst.setText("FIRST");
        jbtnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnFirstActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 107, -1));

        jbtnNext.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EditPlayer/next.png"))); // NOI18N
        jbtnNext.setText("NEXT");
        jbtnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnNextActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 230, 107, -1));

        jbtnPrevious.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EditPlayer/previous.png"))); // NOI18N
        jbtnPrevious.setText("PREVIOUS");
        jbtnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnPreviousActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnPrevious, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 230, -1, -1));

        jbtnLast.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jbtnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EditPlayer/last.png"))); // NOI18N
        jbtnLast.setText("LAST");
        jbtnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLastActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 230, 107, -1));

        jbtnInsert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/NewPlayer/Insert.png"))); // NOI18N
        jbtnInsert.setText("Insert");
        jbtnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnInsertActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 470, -1, -1));

        jbtnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/EditPlayer/update.png"))); // NOI18N
        jbtnUpdate.setText("Update");
        jbtnUpdate.setToolTipText("");
        jbtnUpdate.setEnabled(false);
        jbtnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnUpdateActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 470, -1, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 153));
        jPanel2.setForeground(new java.awt.Color(0, 0, 153));

        jLabel4.setFont(new java.awt.Font("Maiandra GD", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Create User");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(804, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 80));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();
        ShowItem(index);
        Show_Users_In_JTableDetail();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jbtnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnFirstActionPerformed
        pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_jbtnFirstActionPerformed

    private void jbtnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnNextActionPerformed
        pos++;

        if (pos >= getUsersList().size()) {
            pos = getUsersList().size() - 1;
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
        pos = getUsersList().size() - 1;
        ShowItem(pos);
    }//GEN-LAST:event_jbtnLastActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        jtxtUsername.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString());
        jtxtPassword.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 2).toString());
    }//GEN-LAST:event_jTable2MouseClicked

    private void jbtnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnInsertActionPerformed
        if (checkInputs() && jtxtID.getText().isEmpty()) {
            InsertMaster();
        } else if (checkInputs() && jtxtID.getText() != null) {
            InsertDetail();
        } else {
            JOptionPane.showMessageDialog(null, "One or more fields are empty!!");
        }
    }//GEN-LAST:event_jbtnInsertActionPerformed

    private void jbtnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnUpdateActionPerformed

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
            java.util.logging.Logger.getLogger(CreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new CreateUser().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JButton jbtnFirst;
    private javax.swing.JButton jbtnInsert;
    private javax.swing.JButton jbtnLast;
    private javax.swing.JButton jbtnNext;
    private javax.swing.JButton jbtnPrevious;
    private javax.swing.JButton jbtnUpdate;
    private javax.swing.JComboBox<String> jcbCity;
    private javax.swing.JComboBox<String> jcbCityTable;
    private javax.swing.JLabel jlAddress;
    private javax.swing.JLabel jlCity;
    private javax.swing.JLabel jlFName;
    private javax.swing.JLabel jlID;
    private javax.swing.JLabel jlLName;
    private javax.swing.JLabel jlPassword;
    private javax.swing.JLabel jlPhone;
    private javax.swing.JLabel jlUser;
    private javax.swing.JTextArea jtaAddress;
    private javax.swing.JTextField jtxtFName;
    private javax.swing.JTextField jtxtID;
    private javax.swing.JTextField jtxtLName;
    private javax.swing.JPasswordField jtxtPassword;
    private javax.swing.JTextField jtxtPhone;
    private javax.swing.JTextField jtxtUsername;
    // End of variables declaration//GEN-END:variables
}
