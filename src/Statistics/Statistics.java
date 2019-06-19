/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Statistics;

import static Tools.getConnection.getConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Viko
 */
public class Statistics extends javax.swing.JFrame {

    /**
     * Creates new form Statistics
     */
    public Statistics() {
        initComponents();
    }

    public ArrayList<Statistic> getMenPlayersList() {
        ArrayList<Statistic> playersList = new ArrayList<>();
        Connection con = getConnection();
        String query = "select BWM.FULLNAME, BWM.WIN from  BEST_WINNERS_MEN BWM";

        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Statistic players;

            while (rs.next()) {
                players = new Statistic(rs.getString("FULLNAME"), rs.getInt("WIN"));
                playersList.add(players);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Statistic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return playersList;

    }

    public ArrayList<Statistic> getGirlPlayersList() {
        ArrayList<Statistic> playersList = new ArrayList<>();
        Connection con = getConnection();
        String query = "select BWG.FULLNAME, BWG.WIN from BEST_WINNERS_GIRLS BWG";

        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Statistic players;

            while (rs.next()) {
                players = new Statistic(rs.getString("FULLNAME"), rs.getInt("WIN"));
                playersList.add(players);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Statistic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return playersList;

    }

    public ArrayList<Statistic> getTeamList() {
        ArrayList<Statistic> TeamList = new ArrayList<>();
        Connection con = getConnection();
        String query = "select BWT.TEAM, BWT.WIN from BEST_WINNER_TEAMS BWT";

        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Statistic players;

            while (rs.next()) {
                players = new Statistic(rs.getString("TEAM"), rs.getInt("WIN"));
                TeamList.add(players);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Statistic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return TeamList;

    }

    public ArrayList<Statistic> getLastDuelList() {
        ArrayList<Statistic> DuelList = new ArrayList<>();
        Connection con = getConnection();
        String query = "select LMP.FIRSTPLAYER, LMP.SECONDPLAYER, LMP.RESULT from LAST_MATCH_PLAYERS  LMP";

        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Statistic duels;

            while (rs.next()) {
                duels = new Statistic(rs.getString("FIRSTPLAYER"), rs.getString("SECONDPLAYER"), rs.getString("RESULT"));
                DuelList.add(duels);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Statistic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return DuelList;

    }

    public ArrayList<Statistic> getLastDuelTeamList() {
        ArrayList<Statistic> DuelList = new ArrayList<>();
        Connection con = getConnection();
        String query = "select LMT.TEAM, LMT.TEAM1, LMT.RESULT from LAST_MATCH_TEAMS LMT";

        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Statistic duels;

            while (rs.next()) {
                duels = new Statistic(rs.getString("TEAM"), rs.getString("TEAM1"), rs.getString("RESULT"));
                DuelList.add(duels);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Statistic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return DuelList;

    }

    public void Show_Players_In_JTable_Men() {
        ArrayList<Statistic> list = getMenPlayersList();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setColumnCount(0);
        model.addColumn("Player");
        model.addColumn("Win");
        model.setRowCount(0);
        Object[] row = new Object[2];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getFullName();
            row[1] = list.get(i).getCountWin();
            model.addRow(row);
        }
    }

    public void Show_Players_In_JTable_Girl() {
        ArrayList<Statistic> list = getGirlPlayersList();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setColumnCount(0);
        model.addColumn("Player");
        model.addColumn("Win");
        model.setRowCount(0);
        Object[] row = new Object[2];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getFullName();
            row[1] = list.get(i).getCountWin();
            model.addRow(row);
        }

    }

    public void Show_TEAMS_In_JTable() {
        ArrayList<Statistic> list = getTeamList();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setColumnCount(0);
        model.addColumn("Team");
        model.addColumn("Win");
        model.setRowCount(0);
        Object[] row = new Object[2];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getFullName();
            row[1] = list.get(i).getCountWin();
            model.addRow(row);
        }
    }

    public void Show_LastDuels_In_JTable() {
        ArrayList<Statistic> list = getLastDuelList();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setColumnCount(0);
        model.addColumn("First Player");
        model.addColumn("Second Player");
        model.addColumn("Result");
        model.setRowCount(0);
        Object[] row = new Object[3];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getFirstPlayer();
            row[1] = list.get(i).getSecondPlayer();
            row[2] = list.get(i).getResult();
            model.addRow(row);
        }
    }

    public void Show_LastDuelsTeams_In_JTable() {
        ArrayList<Statistic> list = getLastDuelTeamList();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setColumnCount(0);
        model.addColumn("First Team");
        model.addColumn("Second Team");
        model.addColumn("Result");
        model.setRowCount(0);
        Object[] row = new Object[3];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getFirstPlayer();
            row[1] = list.get(i).getSecondPlayer();
            row[2] = list.get(i).getResult();
            model.addRow(row);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jbtnBestGirl = new javax.swing.JButton();
        jbtnBestTeam = new javax.swing.JButton();
        jbtnLastPlayers = new javax.swing.JButton();
        jbtnLastTeams = new javax.swing.JButton();
        jbtnBestMen = new javax.swing.JButton();

        setTitle("Statistics");

        jPanel1.setBackground(new java.awt.Color(0, 0, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Maiandra GD", 1, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 0));
        jLabel4.setText("Statistics");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 214, 89));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Statistics/back.png"))); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 630, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 650, 440));

        jbtnBestGirl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Statistics/Top Players Girls.png"))); // NOI18N
        jbtnBestGirl.setText("TOP 5 PLAYERS GIRLS");
        jbtnBestGirl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBestGirlActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnBestGirl, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 260, 60));

        jbtnBestTeam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Statistics/Top Teams.png"))); // NOI18N
        jbtnBestTeam.setText("TOP 5 TEAMS");
        jbtnBestTeam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBestTeamActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnBestTeam, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 260, 60));

        jbtnLastPlayers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Statistics/Last Duels Players.png"))); // NOI18N
        jbtnLastPlayers.setText("LAST 5 DUELS PLAYERS");
        jbtnLastPlayers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLastPlayersActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnLastPlayers, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 340, 260, 60));

        jbtnLastTeams.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Statistics/Last Duels Team.png"))); // NOI18N
        jbtnLastTeams.setText("LAST 5 DUELS TEAMS");
        jbtnLastTeams.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnLastTeamsActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnLastTeams, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 410, 260, 60));

        jbtnBestMen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Statistics/Top Players Men.png"))); // NOI18N
        jbtnBestMen.setText("TOP 5 PLAYERS MENS");
        jbtnBestMen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnBestMenActionPerformed(evt);
            }
        });
        jPanel1.add(jbtnBestMen, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 259, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1276, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnBestMenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBestMenActionPerformed
        Show_Players_In_JTable_Men();
    }//GEN-LAST:event_jbtnBestMenActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jbtnBestGirlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBestGirlActionPerformed
        Show_Players_In_JTable_Girl();
    }//GEN-LAST:event_jbtnBestGirlActionPerformed

    private void jbtnBestTeamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnBestTeamActionPerformed
        Show_TEAMS_In_JTable();
    }//GEN-LAST:event_jbtnBestTeamActionPerformed

    private void jbtnLastPlayersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLastPlayersActionPerformed
        Show_LastDuels_In_JTable();
    }//GEN-LAST:event_jbtnLastPlayersActionPerformed

    private void jbtnLastTeamsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnLastTeamsActionPerformed
        Show_LastDuelsTeams_In_JTable();
    }//GEN-LAST:event_jbtnLastTeamsActionPerformed

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
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Statistics.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Statistics().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbtnBestGirl;
    private javax.swing.JButton jbtnBestMen;
    private javax.swing.JButton jbtnBestTeam;
    private javax.swing.JButton jbtnLastPlayers;
    private javax.swing.JButton jbtnLastTeams;
    // End of variables declaration//GEN-END:variables
}
