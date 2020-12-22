/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql.ide;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author User
 */
public class SqlUserRegister extends javax.swing.JFrame {

    /**
     * Creates new form SqlUserRegister
     */
    public SqlUserRegister() {
        initComponents();
        generateUserId();
    }
    Connection con;
public void generateUserId()
{
    try{
         Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlide","root","root1799@");
           String sql="select uid from userdata order by uid desc limit 1";
            PreparedStatement pstmt=con.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
              if(rs.next())
            {
                String gc = rs.getString("uid");
        int gcn = Integer.parseInt(gc);
        gcn++;
                
                uid.setText(Integer.toString(gcn));
        
            }
            
            else
            {
               uid.setText("1");
            }
          
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog( null, e);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        uid = new javax.swing.JLabel();
        rSButtonPane2 = new rojerusan.RSButtonPane();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rSButtonPane3 = new rojerusan.RSButtonPane();
        jLabel11 = new javax.swing.JLabel();
        usname = new javax.swing.JTextField();
        upass = new javax.swing.JTextField();
        uname = new javax.swing.JTextField();
        ucpass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 181, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 40)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/database.png"))); // NOI18N
        jLabel1.setText("MySQL");
        jLabel1.setIconTextGap(10);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/error (1).png"))); // NOI18N
        jLabel3.setToolTipText("Close");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(241, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 700, 100);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/new-user.png"))); // NOI18N
        jLabel2.setText(" New User Registration");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(210, 100, 280, 40);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel4.setText("User Name :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(80, 290, 110, 30);

        uid.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jPanel1.add(uid);
        uid.setBounds(640, 140, 34, 30);

        rSButtonPane2.setBackground(new java.awt.Color(0, 181, 204));
        rSButtonPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 2));
        rSButtonPane2.setColorHover(new java.awt.Color(0, 204, 204));
        rSButtonPane2.setColorNormal(new java.awt.Color(0, 204, 204));
        rSButtonPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonPane2MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel10.setText("Create User");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout rSButtonPane2Layout = new javax.swing.GroupLayout(rSButtonPane2);
        rSButtonPane2.setLayout(rSButtonPane2Layout);
        rSButtonPane2Layout.setHorizontalGroup(
            rSButtonPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSButtonPane2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        rSButtonPane2Layout.setVerticalGroup(
            rSButtonPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSButtonPane2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(rSButtonPane2);
        rSButtonPane2.setBounds(170, 550, 120, 40);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel6.setText(" Password :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(90, 370, 110, 30);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel7.setText("Confirm Password :");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 450, 180, 30);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel8.setText(" Name :");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(120, 210, 70, 30);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setText("New User Id :");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(520, 140, 110, 30);

        rSButtonPane3.setBackground(new java.awt.Color(0, 181, 204));
        rSButtonPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 2));
        rSButtonPane3.setColorHover(new java.awt.Color(0, 204, 204));
        rSButtonPane3.setColorNormal(new java.awt.Color(0, 204, 204));
        rSButtonPane3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonPane3MouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setText("    Login");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout rSButtonPane3Layout = new javax.swing.GroupLayout(rSButtonPane3);
        rSButtonPane3.setLayout(rSButtonPane3Layout);
        rSButtonPane3Layout.setHorizontalGroup(
            rSButtonPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSButtonPane3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        rSButtonPane3Layout.setVerticalGroup(
            rSButtonPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rSButtonPane3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(rSButtonPane3);
        rSButtonPane3.setBounds(410, 550, 120, 40);

        usname.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        usname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        usname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usnameActionPerformed(evt);
            }
        });
        usname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usnameKeyPressed(evt);
            }
        });
        jPanel1.add(usname);
        usname.setBounds(230, 290, 280, 30);

        upass.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        upass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        upass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upassActionPerformed(evt);
            }
        });
        upass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                upassKeyPressed(evt);
            }
        });
        jPanel1.add(upass);
        upass.setBounds(230, 370, 280, 30);

        uname.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        uname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        uname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unameActionPerformed(evt);
            }
        });
        uname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                unameKeyPressed(evt);
            }
        });
        jPanel1.add(uname);
        uname.setBounds(230, 210, 280, 30);

        ucpass.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ucpass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        ucpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ucpassActionPerformed(evt);
            }
        });
        jPanel1.add(ucpass);
        ucpass.setBounds(230, 450, 280, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void rSButtonPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonPane2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonPane2MouseClicked

    private void rSButtonPane3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonPane3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rSButtonPane3MouseClicked

    private void usnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usnameActionPerformed

    private void upassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_upassActionPerformed

    private void unameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_unameActionPerformed

    private void ucpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ucpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ucpassActionPerformed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        if(uname.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Name is Empty");
        }else if(usname.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "UserName is Empty");
        }else if(upass.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Password is Empty");
        }else if(ucpass.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null, "Confirm Password");
        }else if(!upass.getText().equals(ucpass.getText()))
        {
            JOptionPane.showMessageDialog(null, "Password Not Matched");
        }
        else
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlide", "root", "root1799@");
           String qry = "insert into userdata(uid,username,password,name)values(?,?,?,?)";
                PreparedStatement st = con.prepareStatement(qry);
                 st.setInt(1, Integer.parseInt(uid.getText()));
                 st.setString(2, usname.getText());
                 st.setString(3, upass.getText());
                 st.setString(4, uname.getText());
          st.executeUpdate();
          
           JOptionPane.showMessageDialog(null, "New User Created.");
            } catch (Exception ex) {
                String e=ex.toString();
               
                if(e.substring(0, 66).equals("java.sql.SQLIntegrityConstraintViolationException: Duplicate entry"))
                JOptionPane.showMessageDialog(null, "Username Already Exist");
                else
                    JOptionPane.showMessageDialog(null, ex);
                
            }
            
        }
    }//GEN-LAST:event_jLabel10MouseClicked

    private void unameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unameKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
        {
            usname.requestFocus();
        }
    }//GEN-LAST:event_unameKeyPressed

    private void usnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usnameKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
        {
            upass.requestFocus();
        }
    }//GEN-LAST:event_usnameKeyPressed

    private void upassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_upassKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
        {
            ucpass.requestFocus();
        }
    }//GEN-LAST:event_upassKeyPressed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new SqlLogin().setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked

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
            java.util.logging.Logger.getLogger(SqlUserRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SqlUserRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SqlUserRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SqlUserRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SqlUserRegister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private rojerusan.RSButtonPane rSButtonPane2;
    private rojerusan.RSButtonPane rSButtonPane3;
    private javax.swing.JPasswordField ucpass;
    private javax.swing.JLabel uid;
    private javax.swing.JTextField uname;
    private javax.swing.JTextField upass;
    private javax.swing.JTextField usname;
    // End of variables declaration//GEN-END:variables
}