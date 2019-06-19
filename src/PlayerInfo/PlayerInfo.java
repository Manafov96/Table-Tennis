/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlayerInfo;

import static Tools.getConnection.getConnection;
import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Viko
 */
public class PlayerInfo extends javax.swing.JFrame {

    /**
     * Creates new form PlayerInfo
     */
    public PlayerInfo() {
        initComponents();
        Show_Players_In_JTable();
    }
    String ImgPath = null;

    public ImageIcon ResizeImage(byte[] pic) {
        ImageIcon myImage = null;
        myImage = new ImageIcon(pic);

        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(jChoosedPicture.getWidth(), jChoosedPicture.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }

    private ArrayList<Players> getPlayersList() {
        ArrayList<Players> playersList = new ArrayList<>();
        Connection con = getConnection();
        String query = "select\n"
                + "  P.ID, P.FIRSTNAME, P.LASTNAME, C.CITY, P.ADDRESS, "
                + "G.GENDER, P.PHONE, T.TEAM, P.PICTURE, P.BIRTHDAY_DATE, P.AGE, P.DATE_OF_CREATED\n"
                + "from\n"
                + "  PLAYERS P\n"
                + "  join CITIES C on C.ID = P.CITY_REF\n"
                + "  join GENDERS G on G.ID = P.GENDER_REF\n"
                + "  join TEAMS T on T.ID = P.TEAM_REF\n"
                + "order by P.ID";

        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Players players;

            while (rs.next()) {
                players = new Players(rs.getInt("ID"), rs.getString("FirstName"), rs.getString("LastName"),
                        rs.getString("CITY"), rs.getString("ADDRESS"), rs.getDate("Birthday_Date"),
                        rs.getString("AGE"), rs.getString("GENDER"), rs.getString("PHONE"),
                        rs.getBytes("PICTURE"), rs.getString("TEAM"));
                playersList.add(players);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return playersList;

    }

    private void Show_Players_In_JTable() {
        ArrayList<Players> list = getPlayersList();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        Object[] row = new Object[10];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getFirstName();
            row[2] = list.get(i).getLastName();
            row[3] = list.get(i).getCity();
            row[4] = list.get(i).getAddress();
            row[5] = list.get(i).getBirthdayDate();
            row[6] = list.get(i).getAge();
            row[7] = list.get(i).getGender();
            row[8] = list.get(i).getMobilePhone();
            row[9] = list.get(i).getTeam();

            model.addRow(row);

        }
    }

    private ArrayList<Players> getPlayersListDetail(String ID) {
        ArrayList<Players> playersList = new ArrayList<>();
        Connection con = getConnection();
        String query = "select first 3\n"
                + "  D.ID, P1.FIRSTNAME || ' ' || P1.LASTNAME PLAYER1_FULLNAME, P2.FIRSTNAME || ' ' || "
                + "P2.LASTNAME PLAYER2_FULLNAME, D.RESULT\n"
                + "from\n"
                + "  DUELS D\n"
                + "  join WINNERS W on W.DUEL_ID = D.ID\n"
                + "  join PLAYERS P1 on D.PLAYER1_REF = P1.ID\n"
                + "  join PLAYERS P2 on D.PLAYER2_REF = P2.ID\n"
                + "where\n"
                + "  W.PLAYER_ID = " + ID + "\n"
                + "order by\n"
                + "  D.ID desc";

        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Players players;

            while (rs.next()) {
                players = new Players(rs.getString("PLAYER1_FULLNAME"), rs.getString("PLAYER2_FULLNAME"), rs.getString("RESULT"));
                playersList.add(players);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlayerInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return playersList;

    }

    private void Show_Players_In_JTableDetail() {
        ArrayList<Players> list = getPlayersListDetail(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        Object[] row = new Object[3];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getFirstPlayer();
            row[1] = list.get(i).getSecondPlayer();
            row[2] = list.get(i).getResult();

            model.addRow(row);
        }
    }

    public void ShowItem(int index) {
        jChoosedPicture.setIcon(ResizeImage(getPlayersList().get(index).getPicture()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jChoosedPicture = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setTitle("Player Information");
        setMaximumSize(null);
        setPreferredSize(new java.awt.Dimension(1248, 600));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 360, -1, 69));

        jPanel2.setBackground(new java.awt.Color(51, 0, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "City", "Address", "Birthday Date", "Age", "Gender", "Mobile Phone", "Team"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 950, 210));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PlayerInfo/back.png"))); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, -1, -1));

        jLabel4.setFont(new java.awt.Font("Maiandra GD", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Player Information");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 11, 509, 100));

        jChoosedPicture.setBackground(new java.awt.Color(102, 153, 231));
        jChoosedPicture.setOpaque(true);
        jPanel2.add(jChoosedPicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 230, 160));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Player", "Second Player", "Result"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, -1, 170));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1400, 840));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int index = jTable1.getSelectedRow();
        ShowItem(index);
        Show_Players_In_JTableDetail();
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jChoosedPicture;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
