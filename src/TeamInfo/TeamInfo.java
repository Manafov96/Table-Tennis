/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TeamInfo;

import NewPlayer.NewPlayer;
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
public class TeamInfo extends javax.swing.JFrame {

    /**
     * Creates new form PlayerInfo
     */
    public TeamInfo() {
        initComponents();
        Show_Players_In_JTable();
    }

    public ImageIcon ResizeImage(byte[] pic) {
        ImageIcon myImage = null;
        myImage = new ImageIcon(pic);

        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(jChoosedPicture.getWidth(), jChoosedPicture.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }

    public ArrayList<Team> getPlayersList() {
        ArrayList<Team> playersList = new ArrayList<>();
        Connection con = getConnection();
        String query = "select\n"
                + "  T.ID, T.TEAM, T.DATE_OF_CREATED, C.CITY, T.ADDRESS, T.ESTABLISHMENT, T.MOBILE_PHONE, T.PICTURE\n"
                + "from\n"
                + "  TEAMS T\n"
                + "  join CITIES C on C.ID = T.CITY_REF\n"
                + "order by T.ID";

        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Team teams;

            while (rs.next()) {
                teams = new Team(rs.getInt("ID"), rs.getString("TEAM"), rs.getString("CITY"), rs.getString("ADDRESS"), rs.getString("MOBILE_PHONE"), rs.getDate("ESTABLISHMENT"), rs.getDate("DATE_OF_CREATED"), rs.getBytes("PICTURE"));
                playersList.add(teams);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TeamInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return playersList;

    }
    public void Show_Players_In_JTable() {
        ArrayList<Team> list = getPlayersList();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
       
        model.setRowCount(0);
        Object[] row = new Object[7];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getTeamName();
            row[2] = list.get(i).getDateCreated();
            row[3] = list.get(i).getCity();
            row[4] = list.get(i).getAddress();
            row[5] = list.get(i).getEstablishment();
            row[6] = list.get(i).getMobilePhone();
            model.addRow(row);
        }
    }

    public ArrayList<Team> getTeamssListDetail(String ID) {
        ArrayList<Team> TeamsList = new ArrayList<>();
        Connection con = getConnection();
        String query = "select\n"
                + "  P.FIRSTNAME, P.LASTNAME, C.CITY, P.ADDRESS, P.PHONE\n"
                + "from\n"
                + "  PLAYERS P\n"
                + "  join CITIES C on C.ID = P.CITY_REF\n"
                + "where\n"
                + "  P.TEAM_REF = " + ID;

        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Team players;

            while (rs.next()) {
                players = new Team(rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("CITY"), rs.getString("ADDRESS"), rs.getString("PHONE"));
                TeamsList.add(players);
            }

        } catch (SQLException ex) {
            Logger.getLogger(TeamInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return TeamsList;

    }

    public void Show_Teams_In_JTableDetail() {
        ArrayList<Team> list = getTeamssListDetail(jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString());
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getFirstName();
            row[1] = list.get(i).getLastName();
            row[2] = list.get(i).getPlayerCity();
            row[3] = list.get(i).getPlayerAddress();
            row[4] = list.get(i).getPlayerPhone();
            model.addRow(row);

        }
    }

    public void ShowItem(int index) {
        jChoosedPicture.setIcon(ResizeImage(getPlayersList().get(index).getImage()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jChoosedPicture = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setTitle("Team Information");
        setMaximumSize(null);
        setPreferredSize(new java.awt.Dimension(1248, 600));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Maiandra GD", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Teams Informations");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 100));

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
                "ID", "Team", "Date of Created", "City", "Address", "Date Establishment", "Mobile Phone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 910, 200));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TeamInfo/back.png"))); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 520, -1, -1));

        jChoosedPicture.setBackground(new java.awt.Color(51, 0, 204));
        jChoosedPicture.setOpaque(true);
        jPanel2.add(jChoosedPicture, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 230, 160));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "City", "Address", "Mobile Phone"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane2.setViewportView(jTable2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 360, 600, 130));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 790));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if (evt.getClickCount() == 2) {
            setVisible(false);
            NewPlayer newPlayer = new NewPlayer();
            newPlayer.setVisible(true);
            int row = this.jTable1.getSelectedRow();
            int column = 1;
            String setTeam = this.jTable1.getValueAt(row, 0) + "." + (String) this.jTable1.getValueAt(row, column);
            newPlayer.jcbTeam.setSelectedItem(setTeam);
        } else {
            int index = jTable1.getSelectedRow();
            ShowItem(index);
            Show_Teams_In_JTableDetail();
        }
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