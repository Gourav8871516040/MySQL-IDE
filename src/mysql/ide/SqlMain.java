
package mysql.ide;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.*;

import javax.swing.table.*;
import net.proteanit.sql.DbUtils;
public class SqlMain extends javax.swing.JFrame {
String na,uid;
private String db,fkdt,pkcd,pcol;
private int flag60 ,ctflag=0, acflag=0;
public String prival[]= new String[1000] ;
    private String uppkc;
    private int upflag=0;
   public int insertflag=0,insertflag2=0;public  String pkcol,pkv;
    public SqlMain() {
        initComponents();
        uid="1";
        na="Gourav Shrinivas";
       
        slideShow();
        HomeMain.removeAll();
        HomeMain.repaint();
        HomeMain.revalidate();
showDate();
        HomeMain.add(Home);
        HomeMain.repaint();
        HomeMain.revalidate();
    }
public SqlMain(String na,String uid)
{
    initComponents();
    slideShow();
    shcol.setEnabled(false);
         shval.setEnabled(false);
    this.na=na;
    this.uid=uid;
     HomeMain.removeAll();
        HomeMain.repaint();
        HomeMain.revalidate();

        HomeMain.add(Home);
        HomeMain.repaint();
        HomeMain.revalidate();
  
        showDate();
        showDB();
    
}
ResultSet rs;
public void slideShow()
{
    
    new Thread (new Runnable()
    {
        @Override
        public void run()
        {
            
            try{
                while(true)
                {
                     Thread.sleep(1500);
                             
                            hmpic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/g1.jpg")));
                            
                            Thread.sleep(5000);
                             
                            hmpic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/hpic.jpg")));
                            
                            Thread.sleep(1500); 
                            hmpic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/g2.jpg")));
                            
                            Thread.sleep(1500);
                            hmpic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/hpic2.jpg")));
                                            
                }
            }catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }
    ).start();
        
    
    
}
public boolean checkValidation(String val[],String dtp[],int c)
{
    int z=0;
    for(int i=0;i<val.length;i++)
    {
        
        if(dtp[i].substring(0, 3).toUpperCase().equals("INT"))
        {
           char x [] =val[i].toCharArray();
          while(z!=x.length)
          {
              if(x[z]>='0'&&x[z]<='9')
              {
                  z++;
              }
              else
              {
                  JOptionPane.showMessageDialog(this.InsertData, "Invalid value at "+(c+1)+" row");
                  return false;
              }
          }
        }/*else if(dtp[i].substring(0, 3).toUpperCase().equals("VAR"))
        {
           char x [] =val[i].toCharArray();
          while(z!=x.length)
          {
              if(x[z]>='0'&&x[z]<='9')
              {
                  JOptionPane.showMessageDialog(null, "Invalid value at "+(c+1)+" row");
                  return false;
              }
              else
              {
                  z++;
              }
          }
        }*/else if(dtp[i].substring(0, 3).toUpperCase().equals("DAT"))
         {
             char x [] =val[i].toCharArray();
          while(z!=x.length)
          {
              if(z==2||z==5)
              {
                  if(x[z]=='-'||x[z]=='/')
                      z++;
                  else
                  {
                          JOptionPane.showMessageDialog(this.InsertData, "Invalid date at "+(c+1)+" row");
                  return false;
                  }
              }else
              {
              if(x[z]>='0'&&x[z]<='9')
              {
                  z++;
              }
              else
              {
                     JOptionPane.showMessageDialog(this.InsertData, "Invalid date at "+(c+1)+" row");
                  return false;
              }
              }
          }
          
                    
         }
        
    z=0;
    }
    return true;
}
public boolean chekAllEntry()
{

        for (int i =0;i<dti.getRowCount();i++)
        {
            for (int j =0;j<dti.getColumnCount();j++)
            {
                 
                try{
                    
                if(dti.getModel().getValueAt(i, j).toString().isEmpty())
                {
                      JOptionPane.showMessageDialog(this.InsertData, dti.getColumnName(j)+" is empty");
                      insertflag2=1;
                      return false;
                }
                        }
                catch(Exception e)
                {
                     String c = e.toString();
                     
                    if(c.equals("java.lang.NullPointerException")){
                     JOptionPane.showMessageDialog(this.InsertData, dti.getColumnName(j)+" is empty in "+(i+1)+" row");
                     
                    insertflag2=1; 
                     return false;
                    }else
                     JOptionPane.showMessageDialog(this.InsertData, e);
                     //JOptionPane.showMessageDialog(null, dt.getColumnName(j)+" is empty");
                }
                
            }
            
        }
        insertflag2=0;
        return true;
}
public void showDB()
{
         
        
        try {

        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();        

               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqlide","root","root1799@");
                
                String sql="select created_databases from dbrecord where uid = ?";
                PreparedStatement pstmt=conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(uid));
               
                hdb.setModel(DbUtils.resultSetToTableModel(pstmt.executeQuery()));           
                 ctdb.setModel(DbUtils.resultSetToTableModel(pstmt.executeQuery()));
                  iddb.setModel(DbUtils.resultSetToTableModel(pstmt.executeQuery()));
                  dddb.setModel(DbUtils.resultSetToTableModel(pstmt.executeQuery()));
                   uddb.setModel(DbUtils.resultSetToTableModel(pstmt.executeQuery()));
                   acdb.setModel(DbUtils.resultSetToTableModel(pstmt.executeQuery()));
              dcdb.setModel(DbUtils.resultSetToTableModel(pstmt.executeQuery()));
              mcdb.setModel(DbUtils.resultSetToTableModel(pstmt.executeQuery()));
                  
        } 
         catch(Exception e)
           {
            JOptionPane.showMessageDialog(this.ShowData, e);
            
           }
   
}
public void showDate()
    {
         java.util.Date d = new java.util.Date ();
         SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
         
        
         hdate.setText(s.format(d));
         
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Header = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        hdate = new javax.swing.JLabel();
        hnam = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        HomeMain = new javax.swing.JPanel();
        Home = new javax.swing.JPanel();
        hmpic = new javax.swing.JLabel();
        ShowData = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        hdb = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        testt = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        desct = new javax.swing.JTable();
        jScrollPane10 = new javax.swing.JScrollPane();
        st = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        shjr = new javax.swing.JRadioButton();
        jLabel55 = new javax.swing.JLabel();
        shcol = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        shval = new javax.swing.JTextField();
        dcl13 = new javax.swing.JLabel();
        CreateTable = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ctdb = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        pk = new javax.swing.JTextField();
        tn = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        fk = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        fkt = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        s = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        cn = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        dt = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabe = new javax.swing.JTable();
        dcl1 = new javax.swing.JLabel();
        dcl2 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        dcl3 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        ctab = new javax.swing.JTable();
        InsertData = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        iddb = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        dti = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        it = new javax.swing.JTable();
        jScrollPane13 = new javax.swing.JScrollPane();
        tp = new javax.swing.JTable();
        dcl4 = new javax.swing.JLabel();
        dcl5 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        DeleteData = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        dddb = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        dtd = new javax.swing.JTable();
        jScrollPane15 = new javax.swing.JScrollPane();
        itd = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        dcl6 = new javax.swing.JLabel();
        dcl7 = new javax.swing.JLabel();
        dcl8 = new javax.swing.JLabel();
        delc = new javax.swing.JTextField();
        delv = new javax.swing.JTextField();
        jrd = new javax.swing.JRadioButton();
        jLabel37 = new javax.swing.JLabel();
        UpdateData = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        uddb = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        itud = new javax.swing.JTable();
        jScrollPane17 = new javax.swing.JScrollPane();
        dtud = new javax.swing.JTable();
        jLabel38 = new javax.swing.JLabel();
        dcl9 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        upcn = new javax.swing.JTextField();
        upv = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jdr = new javax.swing.JRadioButton();
        jLabel40 = new javax.swing.JLabel();
        wcn = new javax.swing.JTextField();
        wv = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        dcl10 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        AddColumn = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane18 = new javax.swing.JScrollPane();
        acdb = new javax.swing.JTable();
        jScrollPane19 = new javax.swing.JScrollPane();
        actb = new javax.swing.JTable();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        acdesc = new javax.swing.JTable();
        jLabel48 = new javax.swing.JLabel();
        colsel = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        acn = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        acdt = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        acsi = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        acfk = new javax.swing.JTextField();
        dcl11 = new javax.swing.JLabel();
        ModifyColumn = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane26 = new javax.swing.JScrollPane();
        mcdb = new javax.swing.JTable();
        jScrollPane27 = new javax.swing.JScrollPane();
        mct = new javax.swing.JTable();
        jScrollPane28 = new javax.swing.JScrollPane();
        mcdesc = new javax.swing.JTable();
        jScrollPane29 = new javax.swing.JScrollPane();
        mcc = new javax.swing.JTable();
        mcpk = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        dcl14 = new javax.swing.JLabel();
        mcjdr2 = new javax.swing.JRadioButton();
        mcjdr = new javax.swing.JRadioButton();
        jLabel59 = new javax.swing.JLabel();
        mcs = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        mccn = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        mcdt = new javax.swing.JTextField();
        DeleteColumn = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        dcdb = new javax.swing.JTable();
        jScrollPane22 = new javax.swing.JScrollPane();
        dctb = new javax.swing.JTable();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane23 = new javax.swing.JScrollPane();
        dcdesc = new javax.swing.JTable();
        jScrollPane24 = new javax.swing.JScrollPane();
        dcol = new javax.swing.JTable();
        jLabel54 = new javax.swing.JLabel();
        dcl12 = new javax.swing.JLabel();
        Rough = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        ct = new javax.swing.JTable();
        SideBar = new javax.swing.JPanel();
        dashboard = new javax.swing.JLabel();
        cdl = new javax.swing.JLabel();
        idl = new javax.swing.JLabel();
        udl = new javax.swing.JLabel();
        ddl = new javax.swing.JLabel();
        dtl = new javax.swing.JLabel();
        dcl = new javax.swing.JLabel();
        mcl = new javax.swing.JLabel();
        acl = new javax.swing.JLabel();
        dd = new javax.swing.JLabel();
        hm = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MySQL IDE");
        setAlwaysOnTop(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        Header.setBackground(new java.awt.Color(0, 181, 240));
        Header.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 45)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/database.png"))); // NOI18N
        jLabel3.setText("MySQL-IDE");
        jLabel3.setIconTextGap(10);
        Header.add(jLabel3);
        jLabel3.setBounds(420, 0, 350, 70);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/database.png"))); // NOI18N
        Header.add(jLabel10);
        jLabel10.setBounds(750, 0, 60, 70);

        hdate.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        Header.add(hdate);
        hdate.setBounds(990, 10, 140, 30);

        hnam.setFont(new java.awt.Font("Times New Roman", 3, 25)); // NOI18N
        Header.add(hnam);
        hnam.setBounds(20, 30, 310, 30);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 25)); // NOI18N
        jLabel2.setText("      Welcome");
        Header.add(jLabel2);
        jLabel2.setBounds(20, 0, 150, 30);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setText("Delete Database");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });
        Header.add(jLabel1);
        jLabel1.setBounds(990, 80, 150, 30);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel5.setText("Delete Table");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        Header.add(jLabel5);
        jLabel5.setBounds(760, 80, 130, 30);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel6.setText("Create Database");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        Header.add(jLabel6);
        jLabel6.setBounds(0, 80, 150, 30);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel7.setText("Rename Table");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        Header.add(jLabel7);
        jLabel7.setBounds(280, 80, 130, 30);

        jPanel1.add(Header);
        Header.setBounds(210, 0, 1150, 110);

        HomeMain.setBackground(new java.awt.Color(255, 255, 255));
        HomeMain.setLayout(null);

        Home.setBackground(new java.awt.Color(255, 255, 255));
        Home.setLayout(null);

        hmpic.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hmpic.setForeground(new java.awt.Color(255, 255, 255));
        hmpic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/g1.jpg"))); // NOI18N
        hmpic.setText("hello");
        Home.add(hmpic);
        hmpic.setBounds(0, 0, 1150, 630);

        HomeMain.add(Home);
        Home.setBounds(0, 0, 1150, 630);

        ShowData.setBackground(new java.awt.Color(255, 255, 255));
        ShowData.setLayout(null);

        hdb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Databases"
            }
        ));
        hdb.setGridColor(new java.awt.Color(0, 181, 240));
        hdb.setSelectionBackground(new java.awt.Color(0, 181, 240));
        hdb.setSelectionForeground(new java.awt.Color(0, 0, 0));
        hdb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hdbMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(hdb);

        ShowData.add(jScrollPane1);
        jScrollPane1.setBounds(940, 70, 210, 200);

        testt.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Table_Data"
            }
        ));
        testt.setSelectionBackground(new java.awt.Color(0, 181, 240));
        testt.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane8.setViewportView(testt);

        ShowData.add(jScrollPane8);
        jScrollPane8.setBounds(12, 280, 1140, 350);

        desct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Table_description"
            }
        ));
        desct.setSelectionBackground(new java.awt.Color(0, 181, 240));
        desct.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane9.setViewportView(desct);

        ShowData.add(jScrollPane9);
        jScrollPane9.setBounds(10, 70, 370, 200);

        st.setBorder(new javax.swing.border.MatteBorder(null));
        st.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tables"
            }
        ));
        st.setSelectionBackground(new java.awt.Color(0, 181, 240));
        st.setSelectionForeground(new java.awt.Color(0, 0, 0));
        st.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(st);

        ShowData.add(jScrollPane10);
        jScrollPane10.setBounds(730, 70, 180, 200);

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Show Data");
        jLabel11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        ShowData.add(jLabel11);
        jLabel11.setBounds(0, 0, 1150, 30);

        shjr.setBackground(new java.awt.Color(255, 255, 255));
        shjr.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        shjr.setText("Show Data Where :-");
        shjr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shjrActionPerformed(evt);
            }
        });
        ShowData.add(shjr);
        shjr.setBounds(410, 60, 200, 30);

        jLabel55.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel55.setText("Column Name ");
        ShowData.add(jLabel55);
        jLabel55.setBounds(410, 100, 118, 30);
        ShowData.add(shcol);
        shcol.setBounds(410, 130, 120, 30);

        jLabel56.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel56.setText("=");
        ShowData.add(jLabel56);
        jLabel56.setBounds(550, 130, 20, 30);

        jLabel57.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel57.setText("Value ");
        ShowData.add(jLabel57);
        jLabel57.setBounds(580, 100, 50, 30);
        ShowData.add(shval);
        shval.setBounds(580, 130, 120, 30);

        dcl13.setBackground(new java.awt.Color(0, 181, 240));
        dcl13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dcl13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dcl13.setText("Show");
        dcl13.setToolTipText("");
        dcl13.setOpaque(true);
        dcl13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcl13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dcl13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dcl13MouseExited(evt);
            }
        });
        ShowData.add(dcl13);
        dcl13.setBounds(590, 190, 100, 30);

        HomeMain.add(ShowData);
        ShowData.setBounds(0, 0, 1150, 630);

        CreateTable.setBackground(new java.awt.Color(255, 255, 255));
        CreateTable.setLayout(null);

        ctdb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Databases"
            }
        ));
        ctdb.setGridColor(new java.awt.Color(0, 181, 240));
        ctdb.setSelectionBackground(new java.awt.Color(0, 181, 240));
        ctdb.setSelectionForeground(new java.awt.Color(0, 0, 0));
        ctdb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ctdbMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ctdb);

        CreateTable.add(jScrollPane2);
        jScrollPane2.setBounds(940, 70, 210, 340);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Primary Key Column :");
        CreateTable.add(jLabel8);
        jLabel8.setBounds(30, 170, 190, 30);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("*");
        CreateTable.add(jLabel9);
        jLabel9.setBounds(20, 340, 10, 10);

        pk.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        pk.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        pk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pkKeyPressed(evt);
            }
        });
        CreateTable.add(pk);
        pk.setBounds(230, 170, 190, 30);

        tn.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        tn.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        tn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tnKeyPressed(evt);
            }
        });
        CreateTable.add(tn);
        tn.setBounds(230, 90, 190, 30);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Select a Database");
        CreateTable.add(jLabel12);
        jLabel12.setBounds(940, 40, 110, 30);

        fk.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        fk.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        fk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fkKeyPressed(evt);
            }
        });
        CreateTable.add(fk);
        fk.setBounds(230, 250, 190, 30);

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setText("FK Reference Table :");
        CreateTable.add(jLabel13);
        jLabel13.setBounds(490, 250, 170, 30);

        fkt.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        fkt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        fkt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fktKeyPressed(evt);
            }
        });
        CreateTable.add(fkt);
        fkt.setBounds(670, 250, 190, 30);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setText("Size :");
        CreateTable.add(jLabel14);
        jLabel14.setBounds(640, 330, 60, 30);

        s.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        s.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        s.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                sKeyPressed(evt);
            }
        });
        CreateTable.add(s);
        s.setBounds(690, 330, 90, 30);

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel15.setText("Table Name :");
        CreateTable.add(jLabel15);
        jLabel15.setBounds(110, 90, 120, 30);

        jTextField6.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        jTextField6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        CreateTable.add(jTextField6);
        jTextField6.setBounds(230, 90, 190, 30);

        cn.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        cn.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        cn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cnKeyPressed(evt);
            }
        });
        CreateTable.add(cn);
        cn.setBounds(170, 330, 140, 30);

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel16.setText("Column Name :");
        CreateTable.add(jLabel16);
        jLabel16.setBounds(30, 330, 130, 30);

        dt.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        dt.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        dt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dtKeyPressed(evt);
            }
        });
        CreateTable.add(dt);
        dt.setBounds(450, 330, 130, 30);

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel17.setText("Datatype :");
        CreateTable.add(jLabel17);
        jLabel17.setBounds(360, 330, 100, 30);

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 51, 51));
        jLabel18.setText("*");
        CreateTable.add(jLabel18);
        jLabel18.setBounds(350, 340, 10, 10);

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 51, 51));
        jLabel19.setText("*");
        CreateTable.add(jLabel19);
        jLabel19.setBounds(630, 330, 10, 22);

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 51, 51));
        jLabel20.setText("*");
        CreateTable.add(jLabel20);
        jLabel20.setBounds(100, 100, 10, 10);

        tabe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Column_Name", "Data_type", "Size"
            }
        ));
        tabe.setSelectionBackground(new java.awt.Color(0, 181, 240));
        tabe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tabe);

        CreateTable.add(jScrollPane6);
        jScrollPane6.setBounds(450, 390, 452, 210);

        dcl1.setBackground(new java.awt.Color(0, 181, 240));
        dcl1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dcl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dcl1.setText("Add Row");
        dcl1.setToolTipText("");
        dcl1.setOpaque(true);
        dcl1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcl1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dcl1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dcl1MouseExited(evt);
            }
        });
        CreateTable.add(dcl1);
        dcl1.setBounds(810, 330, 100, 30);

        dcl2.setBackground(new java.awt.Color(0, 181, 240));
        dcl2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dcl2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dcl2.setText("Create Table");
        dcl2.setToolTipText("");
        dcl2.setOpaque(true);
        dcl2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcl2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dcl2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dcl2MouseExited(evt);
            }
        });
        CreateTable.add(dcl2);
        dcl2.setBounds(70, 550, 120, 30);

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel21.setText("Foreign Key Column :");
        CreateTable.add(jLabel21);
        jLabel21.setBounds(30, 250, 190, 30);

        dcl3.setBackground(new java.awt.Color(0, 181, 240));
        dcl3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dcl3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dcl3.setText("Remove Row");
        dcl3.setToolTipText("");
        dcl3.setOpaque(true);
        dcl3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcl3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dcl3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dcl3MouseExited(evt);
            }
        });
        CreateTable.add(dcl3);
        dcl3.setBounds(270, 550, 120, 30);

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Create Table");
        jLabel26.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        CreateTable.add(jLabel26);
        jLabel26.setBounds(0, 0, 1150, 30);

        ctab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tables"
            }
        ));
        ctab.setSelectionBackground(new java.awt.Color(0, 181, 240));
        ctab.setSelectionForeground(new java.awt.Color(0, 0, 0));
        ctab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ctabMouseClicked(evt);
            }
        });
        jScrollPane25.setViewportView(ctab);

        CreateTable.add(jScrollPane25);
        jScrollPane25.setBounds(720, 40, 160, 180);

        HomeMain.add(CreateTable);
        CreateTable.setBounds(0, 0, 1150, 630);

        InsertData.setBackground(new java.awt.Color(255, 255, 255));
        InsertData.setLayout(null);

        iddb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Databases"
            }
        ));
        iddb.setGridColor(new java.awt.Color(0, 181, 240));
        iddb.setSelectionBackground(new java.awt.Color(0, 181, 240));
        iddb.setSelectionForeground(new java.awt.Color(0, 0, 0));
        iddb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iddbMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(iddb);

        InsertData.add(jScrollPane3);
        jScrollPane3.setBounds(940, 70, 210, 200);

        jLabel22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel22.setText("Select a Database");
        InsertData.add(jLabel22);
        jLabel22.setBounds(940, 40, 110, 30);

        dti.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data_Table"
            }
        ));
        dti.setGridColor(new java.awt.Color(0, 181, 240));
        dti.setSelectionBackground(new java.awt.Color(0, 181, 240));
        dti.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane11.setViewportView(dti);

        InsertData.add(jScrollPane11);
        jScrollPane11.setBounds(0, 320, 1150, 310);

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setText("Select a Table");
        InsertData.add(jLabel25);
        jLabel25.setBounds(630, 40, 110, 30);

        it.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tables"
            }
        ));
        it.setGridColor(new java.awt.Color(0, 181, 240));
        it.setSelectionBackground(new java.awt.Color(0, 181, 240));
        it.setSelectionForeground(new java.awt.Color(0, 0, 0));
        it.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(it);

        InsertData.add(jScrollPane12);
        jScrollPane12.setBounds(630, 70, 210, 200);

        tp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Table_description"
            }
        ));
        tp.setGridColor(new java.awt.Color(0, 181, 240));
        tp.setSelectionBackground(new java.awt.Color(0, 181, 240));
        tp.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane13.setViewportView(tp);

        InsertData.add(jScrollPane13);
        jScrollPane13.setBounds(0, 70, 510, 200);

        dcl4.setBackground(new java.awt.Color(0, 181, 240));
        dcl4.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        dcl4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dcl4.setText("Insert");
        dcl4.setToolTipText("");
        dcl4.setOpaque(true);
        dcl4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcl4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dcl4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dcl4MouseExited(evt);
            }
        });
        InsertData.add(dcl4);
        dcl4.setBounds(840, 280, 100, 30);

        dcl5.setBackground(new java.awt.Color(0, 181, 240));
        dcl5.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        dcl5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dcl5.setText("Add Row");
        dcl5.setToolTipText("");
        dcl5.setOpaque(true);
        dcl5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcl5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dcl5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dcl5MouseExited(evt);
            }
        });
        InsertData.add(dcl5);
        dcl5.setBounds(510, 280, 120, 30);

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Insert Data");
        jLabel27.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        InsertData.add(jLabel27);
        jLabel27.setBounds(0, 0, 1150, 30);

        HomeMain.add(InsertData);
        InsertData.setBounds(0, 0, 1150, 630);

        DeleteData.setBackground(new java.awt.Color(255, 255, 255));
        DeleteData.setLayout(null);

        dddb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Databases"
            }
        ));
        dddb.setGridColor(new java.awt.Color(0, 181, 240));
        dddb.setSelectionBackground(new java.awt.Color(0, 181, 240));
        dddb.setSelectionForeground(new java.awt.Color(0, 0, 0));
        dddb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dddbMouseClicked(evt);
            }
        });
        dddb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dddbKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(dddb);

        DeleteData.add(jScrollPane4);
        jScrollPane4.setBounds(940, 70, 210, 220);

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Delete Data");
        jLabel28.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        DeleteData.add(jLabel28);
        jLabel28.setBounds(0, 0, 1150, 30);

        dtd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Databases"
            }
        ));
        dtd.setGridColor(new java.awt.Color(0, 181, 240));
        dtd.setSelectionBackground(new java.awt.Color(0, 181, 240));
        dtd.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane14.setViewportView(dtd);

        DeleteData.add(jScrollPane14);
        jScrollPane14.setBounds(0, 310, 1150, 320);

        itd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tables"
            }
        ));
        itd.setGridColor(new java.awt.Color(0, 181, 240));
        itd.setSelectionBackground(new java.awt.Color(0, 181, 240));
        itd.setSelectionForeground(new java.awt.Color(0, 0, 0));
        itd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itdMouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(itd);

        DeleteData.add(jScrollPane15);
        jScrollPane15.setBounds(630, 70, 210, 220);

        jLabel33.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel33.setText("Select a Database");
        DeleteData.add(jLabel33);
        jLabel33.setBounds(940, 40, 110, 30);

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel34.setText("=");
        DeleteData.add(jLabel34);
        jLabel34.setBounds(250, 130, 20, 30);

        jLabel35.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel35.setText("Select a Table");
        DeleteData.add(jLabel35);
        jLabel35.setBounds(630, 40, 110, 30);

        jLabel36.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel36.setText("Column Name ");
        DeleteData.add(jLabel36);
        jLabel36.setBounds(70, 100, 130, 30);

        dcl6.setBackground(new java.awt.Color(0, 181, 240));
        dcl6.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dcl6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dcl6.setText("Delete Selected Rows");
        dcl6.setToolTipText("");
        dcl6.setOpaque(true);
        dcl6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcl6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dcl6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dcl6MouseExited(evt);
            }
        });
        DeleteData.add(dcl6);
        dcl6.setBounds(90, 250, 170, 30);

        dcl7.setBackground(new java.awt.Color(0, 181, 240));
        dcl7.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dcl7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dcl7.setText("Delete All Rows");
        dcl7.setToolTipText("");
        dcl7.setOpaque(true);
        dcl7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcl7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dcl7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dcl7MouseExited(evt);
            }
        });
        DeleteData.add(dcl7);
        dcl7.setBounds(360, 250, 160, 30);

        dcl8.setBackground(new java.awt.Color(0, 181, 240));
        dcl8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dcl8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dcl8.setText("Delete");
        dcl8.setToolTipText("");
        dcl8.setOpaque(true);
        dcl8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcl8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dcl8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dcl8MouseExited(evt);
            }
        });
        DeleteData.add(dcl8);
        dcl8.setBounds(480, 130, 100, 30);
        DeleteData.add(delc);
        delc.setBounds(70, 130, 160, 30);
        DeleteData.add(delv);
        delv.setBounds(280, 130, 160, 30);

        jrd.setBackground(new java.awt.Color(255, 255, 255));
        jrd.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jrd.setText("Delete all Where :-");
        jrd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrdActionPerformed(evt);
            }
        });
        DeleteData.add(jrd);
        jrd.setBounds(60, 50, 200, 30);

        jLabel37.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel37.setText("Value ");
        DeleteData.add(jLabel37);
        jLabel37.setBounds(280, 100, 60, 30);

        HomeMain.add(DeleteData);
        DeleteData.setBounds(0, 0, 1150, 630);

        UpdateData.setBackground(new java.awt.Color(255, 255, 255));
        UpdateData.setLayout(null);

        uddb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Databases"
            }
        ));
        uddb.setGridColor(new java.awt.Color(0, 181, 240));
        uddb.setSelectionBackground(new java.awt.Color(0, 181, 240));
        uddb.setSelectionForeground(new java.awt.Color(0, 0, 0));
        uddb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                uddbMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(uddb);

        UpdateData.add(jScrollPane5);
        jScrollPane5.setBounds(940, 60, 210, 220);

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel24.setText("Column Name");
        UpdateData.add(jLabel24);
        jLabel24.setBounds(60, 80, 120, 30);

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Update Data");
        jLabel29.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        UpdateData.add(jLabel29);
        jLabel29.setBounds(0, 0, 1150, 30);

        itud.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tables"
            }
        ));
        itud.setGridColor(new java.awt.Color(0, 181, 240));
        itud.setSelectionBackground(new java.awt.Color(0, 181, 240));
        itud.setSelectionForeground(new java.awt.Color(0, 0, 0));
        itud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itudMouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(itud);

        UpdateData.add(jScrollPane16);
        jScrollPane16.setBounds(650, 60, 210, 220);

        dtud.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data"
            }
        ));
        dtud.setGridColor(new java.awt.Color(0, 181, 240));
        dtud.setSelectionBackground(new java.awt.Color(0, 181, 240));
        dtud.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane17.setViewportView(dtud);

        UpdateData.add(jScrollPane17);
        jScrollPane17.setBounds(0, 300, 1150, 330);

        jLabel38.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel38.setText("Select a Database");
        UpdateData.add(jLabel38);
        jLabel38.setBounds(940, 30, 110, 30);

        dcl9.setBackground(new java.awt.Color(0, 181, 240));
        dcl9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dcl9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dcl9.setText("Update All");
        dcl9.setToolTipText("");
        dcl9.setOpaque(true);
        dcl9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcl9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dcl9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dcl9MouseExited(evt);
            }
        });
        UpdateData.add(dcl9);
        dcl9.setBounds(490, 260, 130, 30);

        jLabel39.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel39.setText("Select a Table");
        UpdateData.add(jLabel39);
        jLabel39.setBounds(650, 30, 110, 30);

        upcn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        upcn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upcnActionPerformed(evt);
            }
        });
        upcn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                upcnKeyPressed(evt);
            }
        });
        UpdateData.add(upcn);
        upcn.setBounds(60, 110, 160, 30);

        upv.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        upv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upvActionPerformed(evt);
            }
        });
        upv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                upvKeyPressed(evt);
            }
        });
        UpdateData.add(upv);
        upv.setBounds(280, 110, 160, 30);

        jLabel41.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel41.setText("Value");
        UpdateData.add(jLabel41);
        jLabel41.setBounds(280, 80, 120, 30);

        jdr.setBackground(new java.awt.Color(255, 255, 255));
        jdr.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jdr.setText("Update :-");
        jdr.setIconTextGap(8);
        jdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jdrActionPerformed(evt);
            }
        });
        UpdateData.add(jdr);
        jdr.setBounds(40, 43, 200, 30);

        jLabel40.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel40.setText("Where :-");
        UpdateData.add(jLabel40);
        jLabel40.setBounds(60, 150, 120, 30);

        wcn.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        wcn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wcnActionPerformed(evt);
            }
        });
        wcn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                wcnKeyPressed(evt);
            }
        });
        UpdateData.add(wcn);
        wcn.setBounds(60, 210, 160, 30);

        wv.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        wv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wvActionPerformed(evt);
            }
        });
        UpdateData.add(wv);
        wv.setBounds(280, 210, 160, 30);

        jLabel42.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel42.setText("Value");
        UpdateData.add(jLabel42);
        jLabel42.setBounds(280, 180, 120, 30);

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel43.setText("Column Name");
        UpdateData.add(jLabel43);
        jLabel43.setBounds(60, 180, 120, 30);

        dcl10.setBackground(new java.awt.Color(0, 181, 240));
        dcl10.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dcl10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dcl10.setText("Update Table");
        dcl10.setToolTipText("");
        dcl10.setOpaque(true);
        dcl10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcl10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dcl10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dcl10MouseExited(evt);
            }
        });
        UpdateData.add(dcl10);
        dcl10.setBounds(490, 210, 130, 30);

        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel44.setText("=");
        UpdateData.add(jLabel44);
        jLabel44.setBounds(240, 210, 20, 30);

        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel45.setText("=");
        UpdateData.add(jLabel45);
        jLabel45.setBounds(240, 110, 20, 30);

        HomeMain.add(UpdateData);
        UpdateData.setBounds(0, 0, 1150, 630);

        AddColumn.setBackground(new java.awt.Color(255, 255, 255));
        AddColumn.setLayout(null);

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Add Column");
        jLabel30.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        AddColumn.add(jLabel30);
        jLabel30.setBounds(0, 0, 1150, 30);

        acdb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Databases"
            }
        ));
        acdb.setGridColor(new java.awt.Color(0, 181, 240));
        acdb.setSelectionBackground(new java.awt.Color(0, 181, 240));
        acdb.setSelectionForeground(new java.awt.Color(0, 0, 0));
        acdb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acdbMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(acdb);

        AddColumn.add(jScrollPane18);
        jScrollPane18.setBounds(940, 60, 210, 220);

        actb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tables"
            }
        ));
        actb.setGridColor(new java.awt.Color(0, 181, 240));
        actb.setSelectionBackground(new java.awt.Color(0, 181, 240));
        actb.setSelectionForeground(new java.awt.Color(0, 0, 0));
        actb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actbMouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(actb);

        AddColumn.add(jScrollPane19);
        jScrollPane19.setBounds(650, 60, 210, 220);

        jLabel46.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel46.setText("Column Type :");
        AddColumn.add(jLabel46);
        jLabel46.setBounds(20, 310, 130, 30);

        jLabel47.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel47.setText("Select a Database");
        AddColumn.add(jLabel47);
        jLabel47.setBounds(940, 30, 110, 30);

        acdesc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Table_description"
            }
        ));
        acdesc.setSelectionBackground(new java.awt.Color(0, 181, 240));
        acdesc.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane20.setViewportView(acdesc);

        AddColumn.add(jScrollPane20);
        jScrollPane20.setBounds(10, 70, 470, 200);

        jLabel48.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel48.setText("Select a Table");
        AddColumn.add(jLabel48);
        jLabel48.setBounds(650, 30, 110, 30);

        colsel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        colsel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal Column", "Primary Key Column", "Foreign Key Column" }));
        colsel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colselActionPerformed(evt);
            }
        });
        AddColumn.add(colsel);
        colsel.setBounds(150, 310, 190, 30);

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel23.setText("Column Name ");
        AddColumn.add(jLabel23);
        jLabel23.setBounds(10, 380, 130, 30);

        acn.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        acn.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        acn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                acnKeyPressed(evt);
            }
        });
        AddColumn.add(acn);
        acn.setBounds(10, 410, 170, 30);

        jLabel49.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel49.setText("Datatype ");
        AddColumn.add(jLabel49);
        jLabel49.setBounds(310, 380, 100, 30);

        acdt.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        acdt.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        acdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acdtActionPerformed(evt);
            }
        });
        acdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                acdtKeyPressed(evt);
            }
        });
        AddColumn.add(acdt);
        acdt.setBounds(310, 410, 130, 30);

        jLabel50.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel50.setText("Size ");
        AddColumn.add(jLabel50);
        jLabel50.setBounds(580, 380, 60, 30);

        acsi.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        acsi.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        acsi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                acsiKeyPressed(evt);
            }
        });
        AddColumn.add(acsi);
        acsi.setBounds(580, 410, 90, 30);

        jLabel51.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel51.setText("FK Reference Table  Name");
        AddColumn.add(jLabel51);
        jLabel51.setBounds(800, 380, 220, 30);

        acfk.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        acfk.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        acfk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                acfkKeyPressed(evt);
            }
        });
        AddColumn.add(acfk);
        acfk.setBounds(800, 410, 210, 30);

        dcl11.setBackground(new java.awt.Color(0, 181, 240));
        dcl11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dcl11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dcl11.setText("Add Column");
        dcl11.setToolTipText("");
        dcl11.setOpaque(true);
        dcl11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcl11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dcl11MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dcl11MouseExited(evt);
            }
        });
        AddColumn.add(dcl11);
        dcl11.setBounds(990, 460, 130, 40);

        HomeMain.add(AddColumn);
        AddColumn.setBounds(0, 0, 1150, 630);

        ModifyColumn.setBackground(new java.awt.Color(255, 255, 255));
        ModifyColumn.setLayout(null);

        jLabel31.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Modify Column");
        jLabel31.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        ModifyColumn.add(jLabel31);
        jLabel31.setBounds(0, 0, 1150, 30);

        mcdb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Databases"
            }
        ));
        mcdb.setGridColor(new java.awt.Color(0, 181, 240));
        mcdb.setSelectionBackground(new java.awt.Color(0, 181, 240));
        mcdb.setSelectionForeground(new java.awt.Color(0, 0, 0));
        mcdb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mcdbMouseClicked(evt);
            }
        });
        jScrollPane26.setViewportView(mcdb);

        ModifyColumn.add(jScrollPane26);
        jScrollPane26.setBounds(940, 70, 210, 200);

        mct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tables"
            }
        ));
        mct.setSelectionBackground(new java.awt.Color(0, 181, 240));
        mct.setSelectionForeground(new java.awt.Color(0, 0, 0));
        mct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mctMouseClicked(evt);
            }
        });
        jScrollPane27.setViewportView(mct);

        ModifyColumn.add(jScrollPane27);
        jScrollPane27.setBounds(710, 70, 180, 200);

        mcdesc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Table_description"
            }
        ));
        mcdesc.setSelectionBackground(new java.awt.Color(0, 181, 240));
        mcdesc.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane28.setViewportView(mcdesc);

        ModifyColumn.add(jScrollPane28);
        jScrollPane28.setBounds(10, 70, 390, 200);

        mcc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tables_columns"
            }
        ));
        mcc.setSelectionBackground(new java.awt.Color(0, 181, 240));
        mcc.setSelectionForeground(new java.awt.Color(0, 0, 0));
        mcc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mccMouseClicked(evt);
            }
        });
        jScrollPane29.setViewportView(mcc);

        ModifyColumn.add(jScrollPane29);
        jScrollPane29.setBounds(470, 70, 180, 200);

        mcpk.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        mcpk.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        mcpk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mcpkMouseClicked(evt);
            }
        });
        mcpk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mcpkActionPerformed(evt);
            }
        });
        mcpk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mcpkKeyPressed(evt);
            }
        });
        ModifyColumn.add(mcpk);
        mcpk.setBounds(240, 390, 150, 30);

        jLabel58.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel58.setText("Column Name :");
        ModifyColumn.add(jLabel58);
        jLabel58.setBounds(90, 390, 130, 30);

        dcl14.setBackground(new java.awt.Color(0, 181, 240));
        dcl14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dcl14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dcl14.setText("Modify Column");
        dcl14.setToolTipText("");
        dcl14.setOpaque(true);
        dcl14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcl14MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dcl14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dcl14MouseExited(evt);
            }
        });
        ModifyColumn.add(dcl14);
        dcl14.setBounds(920, 560, 130, 40);

        mcjdr2.setBackground(new java.awt.Color(255, 255, 255));
        mcjdr2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        mcjdr2.setText("Modify Column :-");
        mcjdr2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mcjdr2ActionPerformed(evt);
            }
        });
        ModifyColumn.add(mcjdr2);
        mcjdr2.setBounds(10, 450, 250, 30);

        mcjdr.setBackground(new java.awt.Color(255, 255, 255));
        mcjdr.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        mcjdr.setText("Make Primary Key :-");
        mcjdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mcjdrActionPerformed(evt);
            }
        });
        ModifyColumn.add(mcjdr);
        mcjdr.setBounds(10, 340, 250, 30);

        jLabel59.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel59.setText("Size :");
        ModifyColumn.add(jLabel59);
        jLabel59.setBounds(710, 500, 60, 30);

        mcs.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        mcs.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        mcs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mcsActionPerformed(evt);
            }
        });
        mcs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mcsKeyPressed(evt);
            }
        });
        ModifyColumn.add(mcs);
        mcs.setBounds(770, 500, 110, 30);

        jLabel60.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel60.setText("Column Name :");
        ModifyColumn.add(jLabel60);
        jLabel60.setBounds(90, 500, 130, 30);

        mccn.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        mccn.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        mccn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mccnMouseClicked(evt);
            }
        });
        mccn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mccnActionPerformed(evt);
            }
        });
        mccn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mccnKeyPressed(evt);
            }
        });
        ModifyColumn.add(mccn);
        mccn.setBounds(240, 500, 150, 30);

        jLabel61.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel61.setText("Datatype :");
        ModifyColumn.add(jLabel61);
        jLabel61.setBounds(430, 500, 90, 30);

        mcdt.setFont(new java.awt.Font("Times New Roman", 1, 19)); // NOI18N
        mcdt.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 0, 0)));
        mcdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mcdtActionPerformed(evt);
            }
        });
        mcdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                mcdtKeyPressed(evt);
            }
        });
        ModifyColumn.add(mcdt);
        mcdt.setBounds(530, 500, 150, 30);

        HomeMain.add(ModifyColumn);
        ModifyColumn.setBounds(0, 0, 1150, 630);

        DeleteColumn.setBackground(new java.awt.Color(255, 255, 255));
        DeleteColumn.setLayout(null);

        jLabel32.setFont(new java.awt.Font("Times New Roman", 1, 28)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Delete Column");
        jLabel32.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        DeleteColumn.add(jLabel32);
        jLabel32.setBounds(0, 0, 1150, 30);

        dcdb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Databases"
            }
        ));
        dcdb.setGridColor(new java.awt.Color(0, 181, 240));
        dcdb.setSelectionBackground(new java.awt.Color(0, 181, 240));
        dcdb.setSelectionForeground(new java.awt.Color(0, 0, 0));
        dcdb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcdbMouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(dcdb);

        DeleteColumn.add(jScrollPane21);
        jScrollPane21.setBounds(940, 60, 210, 220);

        dctb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tables"
            }
        ));
        dctb.setGridColor(new java.awt.Color(0, 181, 240));
        dctb.setSelectionBackground(new java.awt.Color(0, 181, 240));
        dctb.setSelectionForeground(new java.awt.Color(0, 0, 0));
        dctb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dctbMouseClicked(evt);
            }
        });
        jScrollPane22.setViewportView(dctb);

        DeleteColumn.add(jScrollPane22);
        jScrollPane22.setBounds(650, 60, 210, 220);

        jLabel52.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel52.setText("Select a Table");
        DeleteColumn.add(jLabel52);
        jLabel52.setBounds(650, 30, 110, 30);

        jLabel53.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel53.setText("Select a Database");
        DeleteColumn.add(jLabel53);
        jLabel53.setBounds(940, 30, 110, 30);

        dcdesc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Table_description"
            }
        ));
        dcdesc.setSelectionBackground(new java.awt.Color(0, 181, 240));
        dcdesc.setSelectionForeground(new java.awt.Color(0, 0, 0));
        jScrollPane23.setViewportView(dcdesc);

        DeleteColumn.add(jScrollPane23);
        jScrollPane23.setBounds(10, 70, 470, 200);

        dcol.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Table_Columns"
            }
        ));
        dcol.setGridColor(new java.awt.Color(0, 0, 0));
        dcol.setSelectionBackground(new java.awt.Color(0, 181, 240));
        dcol.setSelectionForeground(new java.awt.Color(0, 0, 0));
        dcol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcolMouseClicked(evt);
            }
        });
        jScrollPane24.setViewportView(dcol);

        DeleteColumn.add(jScrollPane24);
        jScrollPane24.setBounds(490, 320, 180, 260);

        jLabel54.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel54.setText("Select Columns");
        DeleteColumn.add(jLabel54);
        jLabel54.setBounds(490, 290, 110, 30);

        dcl12.setBackground(new java.awt.Color(0, 181, 240));
        dcl12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dcl12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dcl12.setText("Delete Column");
        dcl12.setToolTipText("");
        dcl12.setOpaque(true);
        dcl12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dcl12MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dcl12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dcl12MouseExited(evt);
            }
        });
        DeleteColumn.add(dcl12);
        dcl12.setBounds(740, 430, 130, 40);

        HomeMain.add(DeleteColumn);
        DeleteColumn.setBounds(0, 0, 1150, 630);

        Rough.setBackground(new java.awt.Color(255, 255, 255));
        Rough.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        Rough.add(jPanel2);
        jPanel2.setBounds(780, 20, 190, 110);

        ct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(ct);

        Rough.add(jScrollPane7);
        jScrollPane7.setBounds(830, 60, 100, 40);

        HomeMain.add(Rough);
        Rough.setBounds(0, 0, 1150, 630);

        jPanel1.add(HomeMain);
        HomeMain.setBounds(210, 110, 1150, 630);

        SideBar.setBackground(new java.awt.Color(255, 255, 255));

        dashboard.setBackground(new java.awt.Color(255, 255, 255));
        dashboard.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        dashboard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/menu (1).png"))); // NOI18N
        dashboard.setText(" DashBoard");
        dashboard.setOpaque(true);

        cdl.setBackground(new java.awt.Color(0, 181, 240));
        cdl.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        cdl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cdl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/table-for-data.png"))); // NOI18N
        cdl.setText("Create Table");
        cdl.setToolTipText("");
        cdl.setOpaque(true);
        cdl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cdlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cdlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cdlMouseExited(evt);
            }
        });

        idl.setBackground(new java.awt.Color(0, 181, 240));
        idl.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        idl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        idl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/input-data.png"))); // NOI18N
        idl.setText("Insert Data");
        idl.setToolTipText("");
        idl.setOpaque(true);
        idl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                idlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                idlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                idlMouseExited(evt);
            }
        });

        udl.setBackground(new java.awt.Color(0, 181, 240));
        udl.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        udl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        udl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/refresh-arrows.png"))); // NOI18N
        udl.setText("Update Data");
        udl.setToolTipText("");
        udl.setOpaque(true);
        udl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                udlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                udlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                udlMouseExited(evt);
            }
        });

        ddl.setBackground(new java.awt.Color(0, 181, 240));
        ddl.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        ddl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ddl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/deldatabase.png"))); // NOI18N
        ddl.setText("Delete Data");
        ddl.setToolTipText("");
        ddl.setOpaque(true);
        ddl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ddlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ddlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ddlMouseExited(evt);
            }
        });

        dtl.setBackground(new java.awt.Color(0, 181, 240));
        dtl.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        dtl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dtl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/data.png"))); // NOI18N
        dtl.setText("Show Data");
        dtl.setToolTipText("");
        dtl.setOpaque(true);
        dtl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dtlMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dtlMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dtlMouseExited(evt);
            }
        });

        dcl.setBackground(new java.awt.Color(0, 181, 240));
        dcl.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        dcl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dcl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/insert.png"))); // NOI18N
        dcl.setText("Delete Column");
        dcl.setToolTipText("");
        dcl.setOpaque(true);
        dcl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dclMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dclMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dclMouseExited(evt);
            }
        });

        mcl.setBackground(new java.awt.Color(0, 181, 240));
        mcl.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        mcl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mcl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/erase.png"))); // NOI18N
        mcl.setText("Modify Column");
        mcl.setToolTipText("");
        mcl.setOpaque(true);
        mcl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mclMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mclMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mclMouseExited(evt);
            }
        });

        acl.setBackground(new java.awt.Color(0, 181, 240));
        acl.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        acl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/add-column.png"))); // NOI18N
        acl.setText("Add Column");
        acl.setToolTipText("");
        acl.setOpaque(true);
        acl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aclMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aclMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aclMouseExited(evt);
            }
        });

        dd.setBackground(new java.awt.Color(0, 181, 240));
        dd.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        dd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/logout.png"))); // NOI18N
        dd.setText("Logout");
        dd.setToolTipText("");
        dd.setOpaque(true);
        dd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ddMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ddMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ddMouseExited(evt);
            }
        });

        hm.setBackground(new java.awt.Color(0, 181, 240));
        hm.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        hm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mysql/ide/server.png"))); // NOI18N
        hm.setText("Home");
        hm.setToolTipText("");
        hm.setIconTextGap(8);
        hm.setOpaque(true);
        hm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hmMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                hmMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                hmMouseExited(evt);
            }
        });

        javax.swing.GroupLayout SideBarLayout = new javax.swing.GroupLayout(SideBar);
        SideBar.setLayout(SideBarLayout);
        SideBarLayout.setHorizontalGroup(
            SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBarLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(idl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ddl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(udl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(acl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dtl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dcl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mcl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(dd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(cdl, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(hm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        SideBarLayout.setVerticalGroup(
            SideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SideBarLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(dashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(hm, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(dtl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(cdl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(idl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(ddl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(udl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(acl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(mcl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(dcl, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(dd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jPanel1.add(SideBar);
        SideBar.setBounds(0, 0, 210, 740);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1356, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cdlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdlMouseEntered
        // TODO add your handling code here:
        cdl.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_cdlMouseEntered

    private void cdlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdlMouseExited
        // TODO add your handling code here:
        cdl.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_cdlMouseExited

    private void idlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idlMouseEntered
        // TODO add your handling code here:
        idl.setBackground(new Color(0,255,255));        
    }//GEN-LAST:event_idlMouseEntered

    private void idlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idlMouseExited
        // TODO add your handling code here:
        idl.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_idlMouseExited

    private void ddlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ddlMouseEntered
        // TODO add your handling code here:
        ddl.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_ddlMouseEntered

    private void ddlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ddlMouseExited
        // TODO add your handling code here:
        ddl.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_ddlMouseExited

    private void udlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_udlMouseEntered
        // TODO add your handling code here:
        udl.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_udlMouseEntered

    private void udlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_udlMouseExited
        // TODO add your handling code here:
        udl.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_udlMouseExited

    private void aclMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aclMouseEntered
        // TODO add your handling code here:
        acl.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_aclMouseEntered

    private void aclMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aclMouseExited
        // TODO add your handling code here:
        acl.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_aclMouseExited

    private void mclMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mclMouseEntered
        // TODO add your handling code here:
        mcl.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_mclMouseEntered

    private void mclMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mclMouseExited
        // TODO add your handling code here:
        mcl.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_mclMouseExited

    private void dclMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dclMouseEntered
        // TODO add your handling code here:
        dcl.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_dclMouseEntered

    private void dclMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dclMouseExited
        // TODO add your handling code here:
        dcl.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_dclMouseExited

    private void dtlMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlMouseEntered
        // TODO add your handling code here:
        dtl.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_dtlMouseEntered

    private void dtlMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlMouseExited
        // TODO add your handling code here:
        dtl.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_dtlMouseExited

    private void ddMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ddMouseEntered
        // TODO add your handling code here:
        dd.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_ddMouseEntered

    private void ddMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ddMouseExited
        // TODO add your handling code here:
        dd.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_ddMouseExited

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
       showDate();
        hnam.setText(na);
        showDB();
        
    }//GEN-LAST:event_formWindowOpened

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        new DeleteDB(uid ,na).setVisible(true);
        showDB();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        new DeleteTable(uid).setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        new CreateDB(uid,na).setVisible(true);
        showDB();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
        new RenameTable(uid).setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void dtlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dtlMouseClicked
        // TODO add your handling code here:
         HomeMain.removeAll();
        HomeMain.repaint();
        HomeMain.revalidate();

        HomeMain.add(ShowData);
        HomeMain.repaint();
        HomeMain.revalidate();
         shcol.setEnabled(false);
         shval.setEnabled(false);
         
        showDB();
        for (int j=st.getRowCount()-1;j>=0;j--)
            ((DefaultTableModel)st.getModel()).removeRow(j);
       for (int j=testt.getRowCount()-1;j>=0;j--)
            ((DefaultTableModel)testt.getModel()).removeRow(j);
       for (int j=desct.getRowCount()-1;j>=0;j--)
            ((DefaultTableModel)desct.getModel()).removeRow(j);
    }//GEN-LAST:event_dtlMouseClicked

    private void cdlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cdlMouseClicked
        // TODO add your handling code here:
                HomeMain.removeAll();
        HomeMain.repaint();
        HomeMain.revalidate();

        HomeMain.add(CreateTable);
        HomeMain.repaint();
        HomeMain.revalidate();
 showDB();
    }//GEN-LAST:event_cdlMouseClicked

    private void idlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idlMouseClicked
        // TODO add your handling code here:
                       HomeMain.removeAll();
        HomeMain.repaint();
        HomeMain.revalidate();

        HomeMain.add(InsertData);
        HomeMain.repaint();
        HomeMain.revalidate();
 showDB();
    }//GEN-LAST:event_idlMouseClicked

    private void ddlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ddlMouseClicked
        // TODO add your handling code here:
                       HomeMain.removeAll();
        HomeMain.repaint();
        HomeMain.revalidate();

        HomeMain.add(DeleteData);
        HomeMain.repaint();
        HomeMain.revalidate();
        delc.setEnabled(false);
            delv.setEnabled(false);
            delc.setText("");
             delv.setText("");
            
 showDB();
    }//GEN-LAST:event_ddlMouseClicked

    private void udlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_udlMouseClicked
        // TODO add your handling code here:
                       HomeMain.removeAll();
        HomeMain.repaint();
        HomeMain.revalidate();

        HomeMain.add(UpdateData);
        HomeMain.repaint();
        HomeMain.revalidate();
        upcn.setEnabled(false);
        upv.setEnabled(false);
        wcn.setEnabled(false);
        wv.setEnabled(false);
        upcn.setText("");
        upv.setText("");
        wcn.setText("");
        wv.setText("");
        
 showDB();
    }//GEN-LAST:event_udlMouseClicked

    private void aclMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aclMouseClicked
        // TODO add your handling code here:
                       HomeMain.removeAll();
        HomeMain.repaint();
        HomeMain.revalidate();

        HomeMain.add(AddColumn);
        HomeMain.repaint();
        HomeMain.revalidate();
        acfk.setEnabled(false);
 showDB();
    }//GEN-LAST:event_aclMouseClicked

    private void mclMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mclMouseClicked
        // TODO add your handling code here:
                       HomeMain.removeAll();
        HomeMain.repaint();
        HomeMain.revalidate();

        HomeMain.add(ModifyColumn);
        HomeMain.repaint();
        HomeMain.revalidate();
        mcpk.setEnabled(false);
        mccn.setEnabled(false);
        mcdt.setEnabled(false);
        mcs.setEnabled(false);
        
 showDB();
    }//GEN-LAST:event_mclMouseClicked

    private void dclMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dclMouseClicked
        // TODO add your handling code here:
                       HomeMain.removeAll();
        HomeMain.repaint();
        HomeMain.revalidate();

        HomeMain.add(DeleteColumn);
        HomeMain.repaint();
        HomeMain.revalidate();
 showDB();
    }//GEN-LAST:event_dclMouseClicked

    private void ddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ddMouseClicked
        // TODO add your handling code here:
        this.setVisible(false);
        new SqlLogin().setVisible(true);
    }//GEN-LAST:event_ddMouseClicked

    private void tabeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tabeMouseClicked

    private void dcl1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl1MouseClicked
        // TODO add your handling code here:
         if(dt.getText().toUpperCase().equals("VARCHAR")||dt.getText().toUpperCase().equals("INT")||dt.getText().toUpperCase().equals("BIGINT")||dt.getText().toUpperCase().equals("DATE")||dt.getText().toUpperCase().equals("CHAR")||dt.getText().toUpperCase().equals("DATETIME"))
        {
            if(dt.getText().toUpperCase().equals("DATE")||dt.getText().toUpperCase().equals("DATETIME"))
            {
                    s.setText("0");
            }
                DefaultTableModel model = (DefaultTableModel)tabe.getModel();
        model.addRow(new Object[]{cn.getText(),dt.getText(),s.getText()});
        cn.setText("");
        dt.setText("");
        s.setText("");
        cn.requestFocus();
        }else
        {
        JOptionPane.showMessageDialog(this.CreateTable, "Invalid Data Type");
        dt.setText("");
        dt.requestFocus();
             }
           

    }//GEN-LAST:event_dcl1MouseClicked

    private void dcl1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl1MouseEntered
dcl1.setBackground(new Color(0,255,255));        // TODO add your handling code here:
    }//GEN-LAST:event_dcl1MouseEntered

    private void dcl1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl1MouseExited
      dcl1.setBackground(new Color(0,181,240));  // TODO add your handling code here:
    }//GEN-LAST:event_dcl1MouseExited

    private void tnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tnKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
        {
            pk.requestFocus();
        }
    }//GEN-LAST:event_tnKeyPressed

    private void pkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pkKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
        {
            fk.requestFocus();
        }
    }//GEN-LAST:event_pkKeyPressed

    private void fkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fkKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
        {
            fkt.requestFocus();
        }
    }//GEN-LAST:event_fkKeyPressed

    private void fktKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fktKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
        {
            cn.requestFocus();
        }
    }//GEN-LAST:event_fktKeyPressed

    private void cnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cnKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
        {
            dt.requestFocus();
        }
    }//GEN-LAST:event_cnKeyPressed

    private void dtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dtKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
        {
            s.requestFocus();
        }
    }//GEN-LAST:event_dtKeyPressed

    private void sKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sKeyPressed
        // TODO add your handling code here:
                if(evt.getKeyCode()==10)
        {
             if(dt.getText().toUpperCase().equals("VARCHAR")||dt.getText().toUpperCase().equals("INT")||dt.getText().toUpperCase().equals("BIGINT")||dt.getText().toUpperCase().equals("DATE")||dt.getText().toUpperCase().equals("CHAR")||dt.getText().toUpperCase().equals("DATETIME"))
        {
            if(dt.getText().toUpperCase().equals("DATE")||dt.getText().toUpperCase().equals("DATETIME"))
            {
                    s.setText("0");
            }
                DefaultTableModel model = (DefaultTableModel)tabe.getModel();
        model.addRow(new Object[]{cn.getText(),dt.getText(),s.getText()});
        cn.setText("");
        dt.setText("");
        s.setText("");
        cn.requestFocus();
        }else{
        JOptionPane.showMessageDialog(this.CreateTable, "Invalid Data Type");
        dt.setText("");
        dt.requestFocus();
             }
        }

    }//GEN-LAST:event_sKeyPressed
public boolean chekTable() throws SQLException
  {
        Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+ctdb.getModel().getValueAt(ctdb.getSelectedRow(), 0).toString()),"root","root1799@");
                
                String sql="show tables;";
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rs=pstmt.executeQuery();
                ct.setModel(DbUtils.resultSetToTableModel(rs));           
             TableModel m = ct.getModel();
             String tab[]=new String [1000];
             for(int i=0;i<ct.getRowCount();i++)
             {
                 tab[i]=m.getValueAt(i, 0).toString();
                 if(tab[i].toUpperCase().equals(fkt.getText().toUpperCase()))
                 {
                     return true;
                 }
                 
                     }
      
      return false;
  }
public boolean tp(int i)
{
    TableModel m = ct.getModel();
    try{
    String x = m.getValueAt(i, 3).toString();
    if(x.equals("PRI"))
    {
        fkdt=m.getValueAt(i, 1).toString();
    return true;
    }else if(x.equals("MUL"))
    {
        JOptionPane.showMessageDialog(this.CreateTable, fk.getText()+" is the foreign key in "+fkt.getText()+" table");
        flag60=0;
    }
    else
    {
        JOptionPane.showMessageDialog(this.CreateTable, fk.getText()+" is not primary key in "+fkt.getText()+" table");
        flag60=0;
    }
    }catch (Exception e)
    {
                        
           JOptionPane.showMessageDialog(this.CreateTable, fk.getText()+" is not primary key in "+fkt.getText()+" table");
                            
                         }
    return false;
}
    public boolean chekForiegnKey() throws ClassNotFoundException, SQLException
    {
      if(chekTable())
      {
        Connection con=null;
        String d =tn.getText();
        String fkey=fk.getText();
        String ft=fkt.getText();
        TableModel m = ct.getModel();
        Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+ctdb.getModel().getValueAt(ctdb.getSelectedRow(), 0).toString()), "root", "root1799@");
          String sql="desc "+ft;
                PreparedStatement pstmt=con.prepareStatement(sql);
                ResultSet rs=pstmt.executeQuery();
                ct.setModel(DbUtils.resultSetToTableModel(rs));
                
                String col[]=new String[1000];
                for (int i =0;i<ct.getRowCount();i++)
                {
                    col[i]=ct.getValueAt(i, 0).toString();
                }
                for (int i =0;i<ct.getRowCount();i++)
                {
                    if(col[i].toUpperCase().equals(fkey.toUpperCase()))
                    {
                        try{
                        
                        if(tp(i))
                        {
                            return true;
                        }
                        else if(flag60==0)
                        {
                            
                                break;
                        }
                        }
                        catch(Exception e)
                        {
                         JOptionPane.showMessageDialog(this.CreateTable, e);
                           
                            break;
                        }
                                    
                    }else
                        flag60=1;
                }
                if(flag60==1)
                {
                    JOptionPane.showMessageDialog(this.CreateTable, fk.getText()+" column is not created in the table");
                }
      }
      else
      {
                    JOptionPane.showMessageDialog(this.CreateTable, fkt.getText()+" table is not created in the Selected Database");
      }
                return false;
      
    }
    
    public boolean pkEntry()
    {
        
        if(pk.getText().isEmpty())
            return true;
        else
        {
            for(int i=0;i<tabe.getRowCount();i++)
            {
                if(pk.getText().equals(tabe.getModel().getValueAt(i, 0).toString()))
                return true;        
            }
        }
        JOptionPane.showMessageDialog(this.CreateTable, "Add Primary Key column in the list");
        return false;
        
    }
    public boolean fkEntry() throws ClassNotFoundException, SQLException
    {
        if(pk.getText().equals(fk.getText())&&!pk.getText().isEmpty())
            JOptionPane.showMessageDialog(this.CreateTable, "Primary key and Foreign Key Should be Different.");
        
        else if(fk.getText().isEmpty()&&fkt.getText().isEmpty())
            return true;
        else if(!fk.getText().isEmpty()&&fkt.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this.CreateTable, "Enter reference table name.");
            return false;
        }else if(!fk.getText().isEmpty()&&chekForiegnKey())
        {
            for(int i=0;i<tabe.getRowCount();i++)
            {
                if(fk.getText().equals(tabe.getModel().getValueAt(i, 0).toString()))
                {
                 ctflag=1;
                    return true;
                }
            }
           if(ctflag==0)
           {
               JOptionPane.showMessageDialog(this.CreateTable, "Add Foreign Key column in the list.");
               return false;
           }
            
        }
        return false;
    }
    private void dcl2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl2MouseClicked
        // TODO add your handling code here:
       if(ctdb.getSelectedRow()==-1)
            JOptionPane.showMessageDialog(dt, "Select a Database From List");
        else if(tn.getText().isEmpty())
            JOptionPane.showMessageDialog(dt, "Table Name is Empty");
        
        else if(tabe.getRowCount()==0)
         {
         JOptionPane.showMessageDialog(dt, "Table Entry is Empty.");
         }
        else try {
            if(fkEntry()&&pkEntry())
            {
                int i;
                String column[]=new String[1000];
                String datat[]=new String[1000];
                String size[]=new String[1000];
                TableModel m = tabe.getModel();
                
                
                for( i=0;i<tabe.getRowCount();i++)
                {
                    column[i]=m.getValueAt(i, 0).toString();
                    datat[i]=  m.getValueAt(i, 1).toString();
                    size[i]= m.getValueAt(i, 2).toString();
                    
                }
                
                
                Connection con=null;
                PreparedStatement smt = null;
                
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+ctdb.getModel().getValueAt(ctdb.getSelectedRow(), 0).toString()), "root", "root1799@");
                    
                    for(i=0;i<tabe.getRowCount();i++)
                    {
                        if(i==0)
                        {
                            String q="create table "+tn.getText()+"("+column[i]+" "+datat[i]+"("+size[i]+")"+")";
                            smt=con.prepareStatement(q);
                            smt.executeUpdate();
                            
                        }
                        else if(datat[i].toUpperCase().equals("DATE")||datat[i].toUpperCase().equals("DATETIME"))
                        {
                            String q="alter table "+tn.getText()+" add column "+column[i]+" "+datat[i];
                            smt=con.prepareStatement(q);
                            smt.executeUpdate();
                            
                        }
                        else
                        {
                            String q="alter table "+tn.getText()+" add column "+column[i]+" "+datat[i]+"("+size[i]+")";
                            smt=con.prepareStatement(q);
                            smt.executeUpdate();
                            
                        }
                    }
                    if(!pk.getText().isEmpty())
                    {
                        String q="alter table "+tn.getText()+" add primary key"+"("+pk.getText()+")";
                        smt=con.prepareStatement(q);
                        smt.executeUpdate();
                        
                    }
                    if(!fk.getText().isEmpty()&&chekForiegnKey())
                    {
                        String fkey=fk.getText();
                        String tab=fkt.getText();
                        String q="alter table "+tn.getText()+" add foreign key"+"("+fk.getText()+")"+" references "+fkt.getText()+"("+fk.getText()+")";
                        smt=con.prepareStatement(q);
                        smt.executeUpdate();
                        
                    }
                    DefaultTableModel mk = (DefaultTableModel) tabe.getModel();
                    
                    for(int j=tabe.getRowCount()-1;j>=0;j--)
                        mk.removeRow(j);
                    
                  tn.setText("");
                  pk.setText("");
                  fk.setText("");
                  fkt.setText("");
                String sql="show tables;";
                PreparedStatement pstmt=con.prepareStatement(sql);
                ResultSet rs=pstmt.executeQuery();
                ctab.setModel(DbUtils.resultSetToTableModel(rs));           
               
                  JOptionPane.showMessageDialog(this.CreateTable, "table created");
            
            }
            else
            JOptionPane.showMessageDialog(this.CreateTable, "Table Creation Faild!!");
       } catch (Exception e) {
           if(e.toString().substring(0, 39).equals("java.sql.SQLSyntaxErrorException: Table"))
               JOptionPane.showMessageDialog(this.CreateTable, "Table "+tn.getText()+" is Already Exist");
           else
               JOptionPane.showMessageDialog(this.CreateTable, e);  
                   
       }
        
    
    }//GEN-LAST:event_dcl2MouseClicked

    private void dcl2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl2MouseEntered
dcl2.setBackground(new Color(0,255,255));        // TODO add your handling code here:
    }//GEN-LAST:event_dcl2MouseEntered

    private void dcl2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl2MouseExited
    dcl2.setBackground(new Color(0,181,240));    // TODO add your handling code here:
    }//GEN-LAST:event_dcl2MouseExited

    private void dcl3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl3MouseClicked
        // TODO add your handling code here:
                 DefaultTableModel mk = (DefaultTableModel) tabe.getModel();  
              
            
                  mk.removeRow(tabe.getSelectedRow());
                  
              

    }//GEN-LAST:event_dcl3MouseClicked

    private void dcl3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl3MouseEntered
dcl3.setBackground(new Color(0,255,255));        // TODO add your handling code here:
    }//GEN-LAST:event_dcl3MouseEntered

    private void dcl3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl3MouseExited
        // TODO add your handling code here:
        dcl3.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_dcl3MouseExited

    private void hdbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hdbMouseClicked
        // TODO add your handling code here:
                 int i = hdb.getSelectedRow();
        TableModel mod = hdb.getModel();
        String s = mod.getValueAt(i, 0).toString();
         try {

               Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+s),"root","root1799@");
                
                String sql="show tables;";
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rs=pstmt.executeQuery();
                st.setModel(DbUtils.resultSetToTableModel(rs));           
                
             } 
         catch(Exception e)
           {
            JOptionPane.showMessageDialog(this.ShowData, e);
            
           }

    }//GEN-LAST:event_hdbMouseClicked

    private void stMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stMouseClicked
        // TODO add your handling code here:
    if(hdb.getSelectedRow()==-1)
    JOptionPane.showMessageDialog(this.ShowData, "Select a database.");
    else
    {
        int i = st.getSelectedRow();
        TableModel mod = st.getModel();
        String s = mod.getValueAt(i, 0).toString();
        int x = hdb.getSelectedRow();
        TableModel mode = hdb.getModel();
        String d = mode.getValueAt(x, 0).toString();
       
        try {
               Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
 
                
               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+d),"root","root1799@");
                
                String sql="select * from "+s;
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rs=pstmt.executeQuery();
                testt.setModel(DbUtils.resultSetToTableModel(rs));           
               
                         
               
               String sqld="desc "+s;
                PreparedStatement pstmtd=conn.prepareStatement(sqld);
                ResultSet rsd=pstmtd.executeQuery();
                desct.setModel(DbUtils.resultSetToTableModel(rsd));
               
             } 
         catch(Exception e)
           {
            JOptionPane.showMessageDialog(this.ShowData, e);
            
           }
    }   
    }//GEN-LAST:event_stMouseClicked

    private void dcl4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl4MouseClicked
        // TODO add your handling code here:
          if(dti.getRowCount()==0)
        {
            JOptionPane.showMessageDialog(this.InsertData, "Enter some data in row ");
        }else if(chekAllEntry())
        {
            
    String cname[] = new String[dti.getColumnCount()];
        for(int i =0;i<dti.getColumnCount();i++)
        {
            cname[i]=dti.getColumnName(i);
        }
              
       try {
                 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                
               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+iddb.getModel().getValueAt(iddb.getSelectedRow(), 0).toString()),"root","root1799@");
                String dtyp[]=new String[1000];
                String values[]=new String[dti.getColumnCount()]; 
                for(int i=0;i<tp.getRowCount();i++)
                {
                    dtyp[i]=tp.getModel().getValueAt(i, 1).toString();
                }
                for (int i=0;i<dti.getRowCount();i++)
                {
                    for (int j=0;j<dti.getColumnCount();j++)
                    {
                        values[j]=dti.getModel().getValueAt(i, j).toString();
                    }
                    if(checkValidation(values,dtyp,i))
                    {
                      insertflag=1;      
                    }
                    else
                    {
                        insertflag=0;
                    }
                }
              
               if(insertflag==1&&insertflag2==0)
               {
                  
                         for(int i =0;i<tp.getRowCount();i++)
                         {
                             try{
                             if(tp.getModel().getValueAt(i, 3).toString().toUpperCase().equals("PRI"))
                             {
                                 
                                pkcol=tp.getModel().getValueAt(i, 0).toString();
                                insertflag=1;
                                break;
                             }
                             else 
                             {
                                 insertflag=0;
                             }
                             }catch(Exception e)
                             {
                                 insertflag=0;
                             }
                         }
                        
                     if(insertflag==1)//if table has primary key column.
                     {
                         
                         String val[]=new String[dti.getColumnCount()]; 
                         for(int i=0;i<dti.getRowCount();i++)
                         {
                             for(int j=0;j<dti.getColumnCount();j++)
                             {
                                 val[j]=dti.getModel().getValueAt(i, j).toString();
                             }
                            
                             for(int k=0;k<dti.getColumnCount();k++)
                             {
                                 if(cname[k].equals(pkcol)&&dtyp[k].substring(0, 3).toUpperCase().equals("VAR"))
                                 {
     String sq="insert into "+it.getModel().getValueAt(it.getSelectedRow(), 0).toString()+"("+pkcol+")"+"values(?)";   
                PreparedStatement pstm=conn.prepareStatement(sq);
               pstm.setString(1, val[k]);
                pstm.executeUpdate();
                 pkv=val[k];
                 
                               break;
                                 }else if(cname[k].equals(pkcol)&&dtyp[k].substring(0, 3).toUpperCase().equals("INT"))
                                 {
     String sq="insert into "+it.getModel().getValueAt(it.getSelectedRow(), 0).toString()+"("+pkcol+")"+"values(?)";   
                PreparedStatement pstm=conn.prepareStatement(sq);
               pstm.setInt(1, Integer.parseInt(val[k]));
                pstm.executeUpdate();
                 pkv=val[k];
                           break;
                       }/*else if(cname[k].equals(pkcol)&&dtyp[k].substring(0, 3).toUpperCase().equals("BIG"))
                                 {
     String sq="insert into "+it.getModel().getValueAt(it.getSelectedRow(), 0).toString()+"("+pkcol+")"+"values(?)";   
                PreparedStatement pstm=conn.prepareStatement(sq);
               pstm.setInt(1, Integer.parseInt(val[k]));
                pstm.executeUpdate();
                 pkv=val[k];
                           break;
                       }*/
                             }
                             for(int k=0;k<dti.getColumnCount();k++)
                             {
                                 if(!cname[k].equals(pkcol)&&dtyp[k].substring(0, 3).toUpperCase().equals("VAR"))
                                 {
                 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
               Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+iddb.getModel().getValueAt(iddb.getSelectedRow(), 0).toString()),"root","root1799@");
              
     String sq="update "+it.getModel().getValueAt(it.getSelectedRow(), 0).toString()+" set "+cname[k]+" ="+"?"+" where "+pkcol+" ="+"?";   
     PreparedStatement pstm=con.prepareStatement(sq);
               pstm.setString(2, pkv);
               pstm.setString(1, val[k]);
                pstm.executeUpdate();
                             
                                 }else if(!cname[k].equals(pkcol)&&dtyp[k].substring(0, 3).toUpperCase().equals("INT"))
                                 {
                 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                
               Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+iddb.getModel().getValueAt(iddb.getSelectedRow(), 0).toString()),"root","root1799@");
     String sq="update "+it.getModel().getValueAt(it.getSelectedRow(), 0).toString()+" set "+cname[k]+" ="+"?"+" where "+pkcol+" ="+"?";   
     
     PreparedStatement pstm=con.prepareStatement(sq);
               pstm.setString(2, pkv);
               pstm.setInt(1, Integer.parseInt(val[k]));
                pstm.executeUpdate();
                          
                       }else if(!cname[k].equals(pkcol)&&dtyp[k].substring(0, 3).toUpperCase().equals("BIG"))
                                 {
                 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                
               Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+iddb.getModel().getValueAt(iddb.getSelectedRow(), 0).toString()),"root","root1799@");
     String sq="update "+it.getModel().getValueAt(it.getSelectedRow(), 0).toString()+" set "+cname[k]+" ="+"?"+" where "+pkcol+" ="+"?";   
     
     PreparedStatement pstm=con.prepareStatement(sq);
               pstm.setString(2, pkv);
               pstm.setInt(1, Integer.parseInt(val[k]));
                pstm.executeUpdate();
                          
                       }
                                 else if(!cname[k].equals(pkcol)&&dtyp[k].substring(0, 3).toUpperCase().equals("DAT"))
                                 {
                 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                
               Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+iddb.getModel().getValueAt(iddb.getSelectedRow(), 0).toString()),"root","root1799@");
     String sq="update "+it.getModel().getValueAt(it.getSelectedRow(), 0).toString()+" set "+cname[k]+" ="+"?"+" where "+pkcol+" ="+"?";   
     PreparedStatement pstm=con.prepareStatement(sq);
               pstm.setString(2, pkv);
               SimpleDateFormat f1 = new SimpleDateFormat("dd-MM-yyyy");
         java.util.Date d = f1.parse(val[k]);
         long ms=d.getTime();
         java.sql.Date da = new java.sql.Date(ms);
        String de = da.toString();
               pstm.setString(1, de);
                pstm.executeUpdate();
                          
                       }
                                 
                             }
                         }
                         JOptionPane.showMessageDialog(this.InsertData,"data added successfully");
                     }
                     else// if there is no primary key column in the table.
                     {
                                     
                         String val[]=new String[dti.getColumnCount()]; 
                         for(int i=0;i<dti.getRowCount();i++)
                         {
                             for(int j=0;j<dti.getColumnCount();j++)
                             {
                                 val[j]=dti.getModel().getValueAt(i, j).toString();
                             }
                            
                             for(int k=0;k<dti.getColumnCount();k++)
                             {
                                 if(dtyp[k].substring(0, 3).toUpperCase().equals("VAR"))
                                 {
     String sq="insert into "+it.getModel().getValueAt(it.getSelectedRow(), 0).toString()+"("+cname[k]+")"+"values(?)";   
                PreparedStatement pstm=conn.prepareStatement(sq);
               pstm.setString(1, val[k]);
                pstm.executeUpdate();
                 pkv=val[k];
                  break;
                                 }else if(dtyp[k].substring(0, 3).toUpperCase().equals("INT"))
                                 {
     String sq="insert into "+it.getModel().getValueAt(it.getSelectedRow(), 0).toString()+"("+cname[k]+")"+"values(?)";   
                PreparedStatement pstm=conn.prepareStatement(sq);
               pstm.setInt(1, Integer.parseInt(val[k]));
                pstm.executeUpdate();
                 pkv=val[k];
                           break;
                       }
                             }
                             for(int k=0;k<dti.getColumnCount();k++)
                             {
                                 if(dtyp[k].substring(0, 3).toUpperCase().equals("VAR"))
                                 {
                 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
               Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+iddb.getModel().getValueAt(iddb.getSelectedRow(), 0).toString()),"root","root1799@");
              
     String sq="update "+it.getModel().getValueAt(it.getSelectedRow(), 0).toString()+" set "+cname[k]+" ="+"?"+" where "+cname[0]+" ="+"?";   
     PreparedStatement pstm=con.prepareStatement(sq);
               pstm.setString(2, pkv);
               pstm.setString(1, val[k]);
                pstm.executeUpdate();
                             
                                 }else if(dtyp[k].substring(0, 3).toUpperCase().equals("INT"))
                                 {
                 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                
               Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+iddb.getModel().getValueAt(iddb.getSelectedRow(), 0).toString()),"root","root1799@");
     String sq="update "+it.getModel().getValueAt(it.getSelectedRow(), 0).toString()+" set "+cname[k]+" ="+"?"+" where "+cname[0]+" ="+"?";   
     
     PreparedStatement pstm=con.prepareStatement(sq);
               pstm.setString(2, pkv);
               pstm.setInt(1, Integer.parseInt(val[k]));
                pstm.executeUpdate();
                          
                       }else if(dtyp[k].substring(0, 3).toUpperCase().equals("DAT"))
                                 {
                 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
               Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+iddb.getModel().getValueAt(iddb.getSelectedRow(), 0).toString()),"root","root1799@");
     String sq="update "+it.getModel().getValueAt(it.getSelectedRow(), 0).toString()+" set "+cname[k]+" ="+"?"+" where "+cname[0]+" ="+"?";   
     PreparedStatement pstm=con.prepareStatement(sq);
               pstm.setString(2, pkv);
               SimpleDateFormat f1 = new SimpleDateFormat("dd-MM-yyyy");
         java.util.Date d = f1.parse(val[k]);
         long ms=d.getTime();
         
         java.sql.Date da = new java.sql.Date(ms);
        String de = da.toString();
               pstm.setString(1, de);
                pstm.executeUpdate();
                          
                       }
                             }
                         }
                         JOptionPane.showMessageDialog(this.InsertData,"data added successfully");
                     }//end of else not have primary key
               }
              
        }catch(Exception e )
        {
            JOptionPane.showMessageDialog(this.InsertData, e);
        }
        }
    }//GEN-LAST:event_dcl4MouseClicked

    private void dcl4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl4MouseEntered
dcl4.setBackground(new Color(0,255,255));        // TODO add your handling code here:
    }//GEN-LAST:event_dcl4MouseEntered

    private void dcl4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl4MouseExited
        // TODO add your handling code here:
        dcl4.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_dcl4MouseExited

    private void dcl5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl5MouseClicked
        // TODO add your handling code here:
                          DefaultTableModel model = (DefaultTableModel)dti.getModel();
        model.addRow(new Object[]{});

    }//GEN-LAST:event_dcl5MouseClicked

    private void dcl5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl5MouseEntered
        // TODO add your handling code here:
        dcl5.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_dcl5MouseEntered

    private void dcl5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl5MouseExited
        // TODO add your handling code here:
        dcl5.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_dcl5MouseExited

    private void iddbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iddbMouseClicked
        // TODO add your handling code here:
                         try {

                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+iddb.getModel().getValueAt(iddb.getSelectedRow(), 0).toString()),"root","root1799@");
                
                String sql="show tables;";
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rs=pstmt.executeQuery();
                it.setModel(DbUtils.resultSetToTableModel(rs));           
                
             } 
         catch(Exception e)
           {
            JOptionPane.showMessageDialog(this.InsertData, e);
            
           }

    }//GEN-LAST:event_iddbMouseClicked

    private void itMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itMouseClicked
        // TODO add your handling code here:
              if(iddb.getSelectedRow()==-1)
              JOptionPane.showMessageDialog(this.InsertData, "Select a Database");
              else
              {
        int i = it.getSelectedRow();
        TableModel mod = it.getModel();
        String s = mod.getValueAt(i, 0).toString();
       
        
        try {
                
                 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+iddb.getModel().getValueAt(iddb.getSelectedRow(), 0).toString()),"root","root1799@");
                
                
                String sqlt="desc "+it.getModel().getValueAt(it.getSelectedRow(), 0).toString();
                PreparedStatement pstmtt=conn.prepareStatement(sqlt);
                ResultSet rst=pstmtt.executeQuery();
                tp.setModel(DbUtils.resultSetToTableModel(rst));
               
               
                String sql="select * from "+s;
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rs=pstmt.executeQuery();
                dti.setModel(DbUtils.resultSetToTableModel(rs));           
          DefaultTableModel m = (DefaultTableModel) dti.getModel();  
              
              for(int j=dti.getRowCount()-1;j>=0;j--)
              {
                  m.removeRow(j);
                  
              }
             } 
         catch(Exception e)
           {
            JOptionPane.showMessageDialog(this.InsertData, e);
            
           }
              }
    }//GEN-LAST:event_itMouseClicked

    public int primaryColNum() throws ClassNotFoundException, SQLException
   {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+dddb.getModel().getValueAt(dddb.getSelectedRow(), 0).toString()),"root","root1799@");
       
            String sqlu="desc "+itd.getModel().getValueAt(itd.getSelectedRow(), 0).toString();
            PreparedStatement pstmtu=conn.prepareStatement(sqlu);
            ResultSet rsu=pstmtu.executeQuery();
            ct.setModel(DbUtils.resultSetToTableModel(rsu));

       
           for(int i =0;i<ct.getRowCount();i++)
                         {
                             try{
                             if(ct.getModel().getValueAt(i, 3).toString().toUpperCase().equals("PRI"))
                             {
                                 pkcd=ct.getModel().getValueAt(i, 0).toString();
                               
                                 return i;
                                          
                             }
                             else 
                             {
                              pkcd=ct.getModel().getValueAt(0, 0).toString();
                             }
                             }catch(Exception e)
                             {
                              pkcd=ct.getModel().getValueAt(0, 0).toString();   
                             }
                         }
       
       return -1;
   }

    private void dcl6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl6MouseClicked
        // TODO add your handling code here:
             if(dtd.getRowCount()==0)
                 JOptionPane.showMessageDialog(this.DeleteData, "Data Table is empty");
             else if(dtd.getSelectedRow()==-1)
                 JOptionPane.showMessageDialog(this.DeleteData, "Select any Row");
               else
             {
        try {
            int delcol[]=dtd.getSelectedRows();
        int pkcn=primaryColNum();
       String delcolnam[]=new String[dtd.getSelectedRowCount()];
       if(pkcn!=-1)
       {
        for(int i=0;i<delcol.length;i++)
       delcolnam[i]=dtd.getModel().getValueAt(delcol[i], pkcn).toString();
       
       }else
       {
           for(int i=0;i<delcol.length;i++)
           delcolnam[i]=dtd.getModel().getValueAt(delcol[i], 0).toString();
       }
         DefaultTableModel m = (DefaultTableModel) dtd.getModel();
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+dddb.getModel().getValueAt(dddb.getSelectedRow(), 0).toString()),"root","root1799@");
                for(int k=0;k<dtd.getSelectedRowCount();k++)
                {

                    String sq="delete from "+itd.getModel().getValueAt(itd.getSelectedRow(), 0).toString()+" where "+pkcd+" ="+"?";
                    PreparedStatement pstm=conn.prepareStatement(sq);
                   
                    pstm.setString(1, delcolnam[k]);
                    pstm.executeUpdate();
                   
        
            }
             String sql="select * from "+itd.getModel().getValueAt(itd.getSelectedRow(), 0).toString();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            dtd.setModel(DbUtils.resultSetToTableModel(rs));
   
            JOptionPane.showMessageDialog(this.DeleteData, "Data deleted");

            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this.DeleteData, e);
        }
             }
    }//GEN-LAST:event_dcl6MouseClicked

    private void dcl6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl6MouseEntered
        // TODO add your handling code here:
        dcl6.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_dcl6MouseEntered

    private void dcl6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl6MouseExited
        // TODO add your handling code here:
        dcl6.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_dcl6MouseExited

    private void dcl7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl7MouseClicked
        // TODO add your handling code here:
        if(dtd.getRowCount()==0)
           JOptionPane.showMessageDialog(this.DeleteData, "No Data To Delete");   
        else
            
        {
          try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+dddb.getModel().getValueAt(dddb.getSelectedRow(), 0).toString()),"root","root1799@");

            String sql="delete from "+itd.getModel().getValueAt(itd.getSelectedRow(), 0).toString();
            PreparedStatement pstmt=conn.prepareStatement(sql);
           pstmt.executeUpdate();
         
           
             String sqld="select * from "+itd.getModel().getValueAt(itd.getSelectedRow(), 0).toString();
            PreparedStatement pstm=conn.prepareStatement(sqld);
            ResultSet rs=pstm.executeQuery();
            dtd.setModel(DbUtils.resultSetToTableModel(rs));
           
           JOptionPane.showMessageDialog(this.DeleteData, "All Data Deleted");
                    
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this.DeleteData, e);

        }
        }
    }//GEN-LAST:event_dcl7MouseClicked

    private void dcl7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl7MouseEntered
        // TODO add your handling code here:
        dcl7.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_dcl7MouseEntered

    private void dcl7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl7MouseExited
        // TODO add your handling code here:
        dcl7.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_dcl7MouseExited

    public boolean checkColumn()
    {
        for(int i=0;i<dtd.getColumnCount();i++)
        {
            if(delc.getText().equals(dtd.getColumnName(i)))
                return true;
        }
        return false;
    }
    private void dcl8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl8MouseClicked
        // TODO add your handling code here:
        if(jrd.isSelected())
        {
            if(delc.getText().isEmpty())
                JOptionPane.showMessageDialog(this.DeleteData, "Column Name is Empty");
            else if(delv.getText().isEmpty())
                JOptionPane.showMessageDialog(this.DeleteData, "Column Value is Empty");
            else 
            {
                if(dtd.getRowCount()==0)
                JOptionPane.showMessageDialog(this.DeleteData, "No Data in Data Table to Delete");
                else
                {
                   if(checkColumn())
                   {
                   try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+dddb.getModel().getValueAt(dddb.getSelectedRow(), 0).toString()),"root","root1799@");

            String sql="delete from "+itd.getModel().getValueAt(itd.getSelectedRow(), 0).toString()+" where "+delc.getText()+" = "+delv.getText();
            PreparedStatement pstmt=conn.prepareStatement(sql);
           pstmt.executeUpdate();
           


             String sqld="select * from "+itd.getModel().getValueAt(itd.getSelectedRow(), 0).toString();
            PreparedStatement pstm=conn.prepareStatement(sqld);
            ResultSet rs=pstm.executeQuery();
            dtd.setModel(DbUtils.resultSetToTableModel(rs));
           
           JOptionPane.showMessageDialog(this.DeleteData, "Data Deleted");

        }
        catch(Exception e)
        {

            Connection conn;
                       try {
                           conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+dddb.getModel().getValueAt(dddb.getSelectedRow(), 0).toString()),"root","root1799@");
                     
            
            String sql="delete from "+itd.getModel().getValueAt(itd.getSelectedRow(), 0).toString()+" where "+delc.getText()+" = '"+delv.getText()+"'";
            PreparedStatement pstmt=conn.prepareStatement(sql);
           pstmt.executeUpdate();
           


             String sqld="select * from "+itd.getModel().getValueAt(itd.getSelectedRow(), 0).toString();
            PreparedStatement pstm=conn.prepareStatement(sqld);
            ResultSet rs=pstm.executeQuery();
            dtd.setModel(DbUtils.resultSetToTableModel(rs));
           
           JOptionPane.showMessageDialog(this.DeleteData, "Data Deleted");

            
                       }catch(HeadlessException | SQLException  ee)
                       {
            JOptionPane.showMessageDialog(this.DeleteData, ee);               
                       }
        }    
                   }
                }
            }
        }
    }//GEN-LAST:event_dcl8MouseClicked

    private void dcl8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl8MouseEntered
dcl8.setBackground(new Color(0,255,255));        // TODO add your handling code here:
    }//GEN-LAST:event_dcl8MouseEntered

    private void dcl8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl8MouseExited
        // TODO add your handling code here:
        dcl8.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_dcl8MouseExited

    private void dddbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dddbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_dddbKeyPressed

    private void dddbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dddbMouseClicked
        // TODO add your handling code here:
                try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+dddb.getModel().getValueAt(dddb.getSelectedRow(), 0).toString()),"root","root1799@");

            String sql="show tables;";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            itd.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this.DeleteData, e);

        }

    }//GEN-LAST:event_dddbMouseClicked

    private void itdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itdMouseClicked
        // TODO add your handling code here:
        if(dddb.getSelectedRow()==-1)
        JOptionPane.showMessageDialog(this.DeleteData, "Select a Database");
        else
        {
        int i = itd.getSelectedRow();
        TableModel mod = itd.getModel();
        String s = mod.getValueAt(i, 0).toString();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+dddb.getModel().getValueAt(dddb.getSelectedRow(), 0).toString()),"root","root1799@");

            String sql="select * from "+s;
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            dtd.setModel(DbUtils.resultSetToTableModel(rs));

                    
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this.DeleteData, e);

        }
        }
    }//GEN-LAST:event_itdMouseClicked

    private void jrdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrdActionPerformed
        // TODO add your handling code here:
        if(jrd.isSelected())
        {
            delc.setEnabled(true);
            delv.setEnabled(true);
            
        }else
        {
            delc.setEnabled(false);
            delv.setEnabled(false);
            
        }
    }//GEN-LAST:event_jrdActionPerformed

    private void uddbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_uddbMouseClicked
        // TODO add your handling code here:
          try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+uddb.getModel().getValueAt(uddb.getSelectedRow(), 0).toString()),"root","root1799@");

            String sql="show tables;";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            itud.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this.UpdateData, e);

        }
    }//GEN-LAST:event_uddbMouseClicked

    public int primaryColNumUP()
   {
       
           for(int i =0;i<ct.getRowCount();i++)
                         {
                             try{
                             if(ct.getModel().getValueAt(i, 3).toString().toUpperCase().equals("PRI"))
                             {
                                 uppkc=ct.getModel().getValueAt(i, 0).toString();
                               upflag=1;
                                 return i;
                                          
                             }
                             else 
                             {
                              uppkc=ct.getModel().getValueAt(0, 0).toString();
                             }
                             }catch(Exception e)
                             {
                              uppkc=ct.getModel().getValueAt(0, 0).toString();   
                             }
                         }
       
       return -1;
   }
    private void itudMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itudMouseClicked
        // TODO add your handling code here:
        if(uddb.getSelectedRow()==-1)
            JOptionPane.showMessageDialog(this.UpdateData, "Select a Database");
        else
        {
            
        int i = itud.getSelectedRow();
        TableModel mod = itud.getModel();
        String s = mod.getValueAt(i, 0).toString();

        try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+uddb.getModel().getValueAt(uddb.getSelectedRow(), 0).toString()),"root","root1799@");

            String sql="select * from "+s;
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            dtud.setModel(DbUtils.resultSetToTableModel(rs));

            String sqlu="desc "+s;
            PreparedStatement pstmtu=conn.prepareStatement(sqlu);
            ResultSet rsu=pstmtu.executeQuery();
            ct.setModel(DbUtils.resultSetToTableModel(rsu));
            int pcn = primaryColNumUP();
            
            if(upflag==1)
            {
             for(int j =0;j<dtud.getRowCount();j++)
             prival[j]=dtud.getModel().getValueAt(j, pcn).toString();
             upflag=0;
            }else
            {
             for(int j =0;j<dtud.getRowCount();j++)
             prival[j]=dtud.getModel().getValueAt(j,0).toString();
             
            }
        
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this.UpdateData, e);

        }

        }
    }//GEN-LAST:event_itudMouseClicked

    private void dcl9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl9MouseClicked
        // TODO add your handling code here:
         
  if(dtud.getRowCount()==0)
      JOptionPane.showMessageDialog(this.UpdateData, "No Data To Update");
  else
  {
   String colnam[]=new String[dtud.getColumnCount()];
   String valu[]=new String[dtud.getColumnCount()];
   
   for(int i =0;i< dtud.getColumnCount();i++)
   {
       colnam[i]=dtud.getColumnName(i);
   }
   
  try {
                 Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                
               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+uddb.getModel().getValueAt(uddb.getSelectedRow(), 0).toString()),"root","root1799@");
       for(int i=0;i<dtud.getRowCount();i++)
                         {
                             
                             for(int j=0;j<dtud.getColumnCount();j++)
                             {
                            
                                 valu[j]=dtud.getModel().getValueAt(i, j).toString();
                                  
                             }
                             
                             for(int k=0;k<dtud.getColumnCount();k++)
                             {
                            
                            String sq="update "+itud.getModel().getValueAt(itud.getSelectedRow(), 0).toString()+" set "+colnam[k]+" ="+"?"+" where "+uppkc+" ="+"?";   
         
                            PreparedStatement pstm=conn.prepareStatement(sq);
         pstm.setString(2, prival[i]);
         pstm.setString(1, valu[k]);
                pstm.executeUpdate();
            
                             }
     
                         }
       
       JOptionPane.showMessageDialog(this.UpdateData, "Data updated");
                for(int j=dtud.getRowCount()-1;j>=0;j--)
                   ((DefaultTableModel)dtud.getModel()).removeRow(j);
               
  }catch(Exception e)
  {
      JOptionPane.showMessageDialog(this.UpdateData, e);
  }
  }
    }//GEN-LAST:event_dcl9MouseClicked

    private void dcl9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl9MouseEntered
        // TODO add your handling code here:
        dcl9.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_dcl9MouseEntered

    private void dcl9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl9MouseExited
        // TODO add your handling code here:
        dcl9.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_dcl9MouseExited

    private void upcnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upcnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_upcnActionPerformed

    private void upvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_upvActionPerformed

    private void wcnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wcnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wcnActionPerformed

    private void wvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wvActionPerformed

    private void dcl10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl10MouseClicked
        // TODO add your handling code here:
           if(!jdr.isSelected())
               JOptionPane.showMessageDialog(this.UpdateData, "Update Clause is Unenabled");
           else if(upcn.getText().isEmpty()||wcn.getText().isEmpty()){  
            
               JOptionPane.showMessageDialog(this.UpdateData, "column name is empty");
              
           }else if(upv.getText().isEmpty()||wv.getText().isEmpty()){  
            
               JOptionPane.showMessageDialog(this.UpdateData, "column value is empty");
              
           }
           else{           
           try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+uddb.getModel().getValueAt(uddb.getSelectedRow(), 0).toString()),"root","root1799@");

            String sql="update "+itud.getModel().getValueAt(itud.getSelectedRow(), 0).toString()+" set "+upcn.getText()+" = "+upv.getText()+" where "+wcn.getText()+" = "+wv.getText();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this.UpdateData, "Table Updated");
            
            String sqle="select * from "+itud.getModel().getValueAt(itud.getSelectedRow(), 0).toString();
            PreparedStatement pstmte=conn.prepareStatement(sqle);
            ResultSet rs=pstmte.executeQuery();
            dtud.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(Exception e)
        {
           try{
               Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+uddb.getModel().getValueAt(uddb.getSelectedRow(), 0).toString()),"root","root1799@");

            String sql="update "+itud.getModel().getValueAt(itud.getSelectedRow(), 0).toString()+" set "+upcn.getText()+" = "+upv.getText()+" where "+wcn.getText()+" = '"+wv.getText()+"'";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this.UpdateData, "Table Updated");
           
            
            
            String sqle="select * from "+itud.getModel().getValueAt(itud.getSelectedRow(), 0).toString();
            PreparedStatement pstmte=conn.prepareStatement(sqle);
            ResultSet rs=pstmte.executeQuery();
            dtud.setModel(DbUtils.resultSetToTableModel(rs));
           }catch(HeadlessException | ClassNotFoundException | SQLException ee)
           {
              try{
               Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+uddb.getModel().getValueAt(uddb.getSelectedRow(), 0).toString()),"root","root1799@");

            String sql="update "+itud.getModel().getValueAt(itud.getSelectedRow(), 0).toString()+" set "+upcn.getText()+" = '"+upv.getText()+"' where "+wcn.getText()+" = "+wv.getText();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(this.UpdateData, "Table Updated");
           
              String sqle="select * from "+itud.getModel().getValueAt(itud.getSelectedRow(), 0).toString();
            PreparedStatement pstmte=conn.prepareStatement(sqle);
            ResultSet rs=pstmte.executeQuery();
            dtud.setModel(DbUtils.resultSetToTableModel(rs));
              
              
              }catch(HeadlessException | ClassNotFoundException | SQLException eeu)
           {
                try{
               Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+uddb.getModel().getValueAt(uddb.getSelectedRow(), 0).toString()),"root","root1799@");

            String sql="update "+itud.getModel().getValueAt(itud.getSelectedRow(), 0).toString()+" set "+upcn.getText()+" = '"+upv.getText()+"' where "+wcn.getText()+" = '"+wv.getText()+"'";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this.UpdateData, "Table Updated");
           
            String sqle="select * from "+itud.getModel().getValueAt(itud.getSelectedRow(), 0).toString();
            PreparedStatement pstmte=conn.prepareStatement(sqle);
            ResultSet rs=pstmte.executeQuery();
            dtud.setModel(DbUtils.resultSetToTableModel(rs));
            
                }catch(HeadlessException | ClassNotFoundException | SQLException eet)
           {
               JOptionPane.showMessageDialog(this.UpdateData, eet);
           }
           }
           }

        }
           upcn.setText("");
           upv.setText("");
           wcn.setText("");
           wv.setText("");
           }
    }//GEN-LAST:event_dcl10MouseClicked

    private void dcl10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl10MouseEntered
        // TODO add your handling code here:
        dcl10.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_dcl10MouseEntered

    private void dcl10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl10MouseExited
        // TODO add your handling code here:
        dcl10.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_dcl10MouseExited

    private void jdrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jdrActionPerformed
        // TODO add your handling code here:
        if(jdr.isSelected())
        {
            upcn.setEnabled(true);
        upv.setEnabled(true);
        wcn.setEnabled(true);
        wv.setEnabled(true);
        upcn.setText("");
        upv.setText("");
        wcn.setText("");
        wv.setText("");
        
        }
        else
        {
            upcn.setEnabled(false);
        upv.setEnabled(false);
        wcn.setEnabled(false);
        wv.setEnabled(false);
        upcn.setText("");
        upv.setText("");
        wcn.setText("");
        wv.setText("");
        
        }
    }//GEN-LAST:event_jdrActionPerformed

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseEntered

    private void acdbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acdbMouseClicked
        // TODO add your handling code here:
                  try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+acdb.getModel().getValueAt(acdb.getSelectedRow(), 0).toString()),"root","root1799@");

            String sql="show tables;";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            actb.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this.AddColumn, e);

        }

    }//GEN-LAST:event_acdbMouseClicked

    private void actbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actbMouseClicked
        // TODO add your handling code here:
            if(acdb.getSelectedRow()==-1)
    JOptionPane.showMessageDialog(this.AddColumn, "Select a database.");
    else
    {
        
        try {
               Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
 
                
               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+acdb.getModel().getValueAt(acdb.getSelectedRow(), 0).toString()),"root","root1799@");
                
               
               String sqld="desc "+actb.getModel().getValueAt(actb.getSelectedRow(), 0).toString();
                PreparedStatement pstmtd=conn.prepareStatement(sqld);
                ResultSet rsd=pstmtd.executeQuery();
                acdesc.setModel(DbUtils.resultSetToTableModel(rsd));
               
             } 
         catch(Exception e)
           {
            JOptionPane.showMessageDialog(this.AddColumn, e);
            
           }
    }   

    }//GEN-LAST:event_actbMouseClicked

    private void acnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acnKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
            acdt.requestFocus();
    }//GEN-LAST:event_acnKeyPressed

    private void acdtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acdtKeyPressed
        // TODO add your handling code here:
             if(evt.getKeyCode()==10)
        {
             if(acdt.getText().toUpperCase().equals("VARCHAR")||acdt.getText().toUpperCase().equals("INT")||acdt.getText().toUpperCase().equals("BIGINT")||acdt.getText().toUpperCase().equals("DATE")||acdt.getText().toUpperCase().equals("CHAR")||acdt.getText().toUpperCase().equals("DATETIME"))
        {
            if(acdt.getText().toUpperCase().equals("DATE")||acdt.getText().toUpperCase().equals("DATETIME"))
            {
                    acsi.setText("0");
            }else
       
        acsi.requestFocus();
        }else{
        JOptionPane.showMessageDialog(this.AddColumn, "Invalid Data Type");
        acdt.setText("");
        acdt.requestFocus();
             }
        }
    }//GEN-LAST:event_acdtKeyPressed

    private void acsiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acsiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_acsiKeyPressed

    private void acdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_acdtActionPerformed

    private void acfkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_acfkKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_acfkKeyPressed

    private void colselActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colselActionPerformed
        // TODO add your handling code here:
        if(colsel.getSelectedIndex()==2)
            acfk.setEnabled(true);
        else
            acfk.setEnabled(false);
    }//GEN-LAST:event_colselActionPerformed

    private void dcl11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl11MouseClicked
        // TODO add your handling code here:
            try {
String sql;
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+acdb.getModel().getValueAt(acdb.getSelectedRow(), 0).toString()),"root","root1799@");
            if(colsel.getSelectedIndex()==0)
            {
                if(acdt.getText().toUpperCase().equals("DATE")||acdt.getText().toUpperCase().equals("DATETIME"))
                {
                sql="alter table "+actb.getModel().getValueAt(actb.getSelectedRow(), 0).toString()+" add column "+acn.getText()+" "+acdt.getText();
                
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
                }else
                    {
                sql="alter table "+actb.getModel().getValueAt(actb.getSelectedRow(), 0).toString()+" add column "+acn.getText()+" "+acdt.getText()+"("+acsi.getText()+")";
                
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
                }
                JOptionPane.showMessageDialog(this.AddColumn, "Column Added");
                           
               
               String sqld="desc "+actb.getModel().getValueAt(actb.getSelectedRow(), 0).toString();
                PreparedStatement pstmtd=conn.prepareStatement(sqld);
                ResultSet rsd=pstmtd.executeQuery();
                acdesc.setModel(DbUtils.resultSetToTableModel(rsd));
               
 
            }else if(colsel.getSelectedIndex()==1)
            {
                if(acdt.getText().toUpperCase().equals("DATE")||acdt.getText().toUpperCase().equals("DATETIME"))
                {
                sql="alter table "+actb.getModel().getValueAt(actb.getSelectedRow(), 0).toString()+" add column "+acn.getText()+" "+acdt.getText()+" primary key";
                
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
                }else
                    {
                sql="alter table "+actb.getModel().getValueAt(actb.getSelectedRow(), 0).toString()+" add column "+acn.getText()+" "+acdt.getText()+"("+acsi.getText()+") primary key";
                
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.executeUpdate();
                
                    }
                JOptionPane.showMessageDialog(this.AddColumn, "Column Added");
                
               
               String sqld="desc "+actb.getModel().getValueAt(actb.getSelectedRow(), 0).toString();
                PreparedStatement pstmtd=conn.prepareStatement(sqld);
                ResultSet rsd=pstmtd.executeQuery();
                acdesc.setModel(DbUtils.resultSetToTableModel(rsd));
               
 
            }else if(colsel.getSelectedIndex()==2)
            {
                
                sql="desc "+acfk.getText();
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
             ct.setModel(DbUtils.resultSetToTableModel(rs));
             
             
                for(int i=0;i<ct.getRowCount();i++)
                {
                    try{
                        
                    if(ct.getModel().getValueAt(i, 3).toString().equals("PRI"))
                    {
                      pcol=ct.getModel().getValueAt(i, 0).toString();
                      acflag=1;
                      break;
                    }
                        }catch(Exception e)
                        {
                            
                        }
                }
                if(acflag==0)
                 JOptionPane.showMessageDialog(this.AddColumn, "No Primary key in reference Table");
                else{
                    
                sql="alter table "+actb.getModel().getValueAt(actb.getSelectedRow(), 0).toString()+" add column "+acn.getText()+" "+acdt.getText()+"("+acsi.getText()+")";
                
            PreparedStatement pstmte=conn.prepareStatement(sql);
            pstmte.executeUpdate();
                
                sql="alter table "+actb.getModel().getValueAt(actb.getSelectedRow(), 0).toString()+" add foreign key("+acn.getText()+") references "+acfk.getText()+"("+pcol+")";
                
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this.AddColumn, "Column Added");
                           
               
               String sqld="desc "+actb.getModel().getValueAt(actb.getSelectedRow(), 0).toString();
                PreparedStatement pstmtd=conn.prepareStatement(sqld);
                ResultSet rsd=pstmtd.executeQuery();
                acdesc.setModel(DbUtils.resultSetToTableModel(rsd));
               
 
                }
            }
         acn.setText("");
         acdt.setText("");
         acsi.setText("");
         acfk.setText("");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this.AddColumn, e);

        }
            

    }//GEN-LAST:event_dcl11MouseClicked

    private void dcl11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl11MouseEntered
        // TODO add your handling code here:
        dcl11.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_dcl11MouseEntered

    private void dcl11MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl11MouseExited
        // TODO add your handling code here:
        dcl11.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_dcl11MouseExited

    private void dcdbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcdbMouseClicked
        // TODO add your handling code here:
                          try {

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+dcdb.getModel().getValueAt(dcdb.getSelectedRow(), 0).toString()),"root","root1799@");

            String sql="show tables;";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            ResultSet rs=pstmt.executeQuery();
            dctb.setModel(DbUtils.resultSetToTableModel(rs));

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this.DeleteColumn, e);

        }


    }//GEN-LAST:event_dcdbMouseClicked

    private void dctbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dctbMouseClicked
        // TODO add your handling code here:
          if(dcdb.getSelectedRow()==-1)
    JOptionPane.showMessageDialog(this.DeleteColumn, "Select a database.");
    else
    {
        
        try {
               Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
 
                
               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+dcdb.getModel().getValueAt(dcdb.getSelectedRow(), 0).toString()),"root","root1799@");
                
               
               String sqld="desc "+dctb.getModel().getValueAt(dctb.getSelectedRow(), 0).toString();
                PreparedStatement pstmtd=conn.prepareStatement(sqld);
                ResultSet rsd=pstmtd.executeQuery();
                dcdesc.setModel(DbUtils.resultSetToTableModel(rsd));
        
                for(int j=dcol.getRowCount()-1;j>=0;j--)
                        ((DefaultTableModel)dcol.getModel()).removeRow(j);
                for(int j =0;j<dcdesc.getRowCount();j++)
        {
          DefaultTableModel model = (DefaultTableModel)dcol.getModel();
        model.addRow(new Object[]{});    
        }
                
                
                for(int i =0;i<dcdesc.getRowCount();i++)
                   dcol.getModel().setValueAt(dcdesc.getModel().getValueAt(i, 0).toString(), i, 0);
             } 
         catch(Exception e)
           {
            JOptionPane.showMessageDialog(this.DeleteColumn, e);
            
           }
    }   

    }//GEN-LAST:event_dctbMouseClicked

    private void dcolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcolMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dcolMouseClicked

    private void dcl12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl12MouseClicked
        // TODO add your handling code here:
        
        if(dcol.getSelectedRow()==-1)
            JOptionPane.showMessageDialog(this.DeleteColumn, "Select Column to Delete.");
        else
        {
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
      Connection      conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+dcdb.getModel().getValueAt(dcdb.getSelectedRow(), 0).toString()),"root","root1799@");
      int selrow[]=dcol.getSelectedRows();
      for(int i=0;i<dcol.getSelectedRowCount();i++)
        {
            String sql = "alter table "+dctb.getModel().getValueAt(dctb.getSelectedRow(), 0).toString()+" drop column "+dcol.getModel().getValueAt(selrow[i], 0).toString();
          PreparedStatement pst=conn.prepareStatement(sql);
          pst.executeUpdate();
        }
      String sqld="desc "+dctb.getModel().getValueAt(dctb.getSelectedRow(), 0).toString();
                PreparedStatement pstmtd=conn.prepareStatement(sqld);
                ResultSet rsd=pstmtd.executeQuery();
                dcdesc.setModel(DbUtils.resultSetToTableModel(rsd));
        
                       for(int j=selrow.length-1;j>=0;j--)
                        ((DefaultTableModel)dcol.getModel()).removeRow(selrow[j]);
         
                
      JOptionPane.showMessageDialog(this.DeleteColumn, "Column Deleted");
        }catch( Exception e)
        {
            JOptionPane.showMessageDialog(this.DeleteColumn, e);
        }
        }
    }//GEN-LAST:event_dcl12MouseClicked

    private void dcl12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl12MouseEntered
        // TODO add your handling code here:
        dcl12.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_dcl12MouseEntered

    private void dcl12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl12MouseExited
        // TODO add your handling code here:
        dcl12.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_dcl12MouseExited

    private void ctabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ctabMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ctabMouseClicked

    private void ctdbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ctdbMouseClicked
        // TODO add your handling code here:
                         int i = ctdb.getSelectedRow();
        TableModel mod = ctdb.getModel();
        String s = mod.getValueAt(i, 0).toString();
         try {

               Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+s),"root","root1799@");
                
                String sql="show tables;";
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rs=pstmt.executeQuery();
                ctab.setModel(DbUtils.resultSetToTableModel(rs));           
                
             } 
         catch(Exception e)
           {
            JOptionPane.showMessageDialog(this.CreateTable, e);
            
           }

    }//GEN-LAST:event_ctdbMouseClicked

    private void shjrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shjrActionPerformed
        // TODO add your handling code here:
        if(shjr.isSelected())
        {
            shcol.setEnabled(true);
         shval.setEnabled(true);
        }else
        {
         shcol.setText("");
         shval.setText("");
         shcol.setEnabled(false);
         shval.setEnabled(false);
        }
    }//GEN-LAST:event_shjrActionPerformed

    private void dcl13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl13MouseClicked
        // TODO add your handling code here:
         if(hdb.getSelectedRow()==-1)
    JOptionPane.showMessageDialog(this.ShowData, "Select a database.");
    else
    {
       
        try {
               Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+hdb.getModel().getValueAt(hdb.getSelectedRow(),0).toString()),"root","root1799@");
                String sql="select * from "+st.getModel().getValueAt(st.getSelectedRow(), 0).toString()+" where "+shcol.getText()+" = "+shval.getText();
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rs=pstmt.executeQuery();
                testt.setModel(DbUtils.resultSetToTableModel(rs));           
             } 
         catch(Exception e)
           {
               try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+hdb.getModel().getValueAt(hdb.getSelectedRow(),0).toString()),"root","root1799@");
                String sql="select * from "+st.getModel().getValueAt(st.getSelectedRow(), 0).toString()+" where "+shcol.getText()+" = '"+shval.getText()+"'";
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rs=pstmt.executeQuery();
                testt.setModel(DbUtils.resultSetToTableModel(rs));
            } 
         catch( ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex)
         {
             JOptionPane.showMessageDialog(this.ShowData, ex);
         }
           }
    }
    }//GEN-LAST:event_dcl13MouseClicked

    private void dcl13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl13MouseEntered
dcl13.setBackground(new Color(0,255,255));        // TODO add your handling code here:
    }//GEN-LAST:event_dcl13MouseEntered

    private void dcl13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl13MouseExited
       dcl13.setBackground(new Color(0,181,240)); // TODO add your handling code here:
    }//GEN-LAST:event_dcl13MouseExited

    private void mcdbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mcdbMouseClicked
        // TODO add your handling code here:
                         int i = mcdb.getSelectedRow();
        TableModel mod = mcdb.getModel();
        String s = mod.getValueAt(i, 0).toString();
         try {

               Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+s),"root","root1799@");
                
                String sql="show tables;";
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rs=pstmt.executeQuery();
                mct.setModel(DbUtils.resultSetToTableModel(rs));           
                
             } 
         catch(Exception e)
           {
            JOptionPane.showMessageDialog(this.ModifyColumn, e);
            
           }

    }//GEN-LAST:event_mcdbMouseClicked

    private void mctMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mctMouseClicked
        // TODO add your handling code here:
                  if(mcdb.getSelectedRow()==-1)
    JOptionPane.showMessageDialog(this.ModifyColumn, "Select a database.");
    else
    {
        
        try {
               Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
 
                
               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+mcdb.getModel().getValueAt(mcdb.getSelectedRow(), 0).toString()),"root","root1799@");
                
               
               String sqld="desc "+mct.getModel().getValueAt(mct.getSelectedRow(), 0).toString();
                PreparedStatement pstmtd=conn.prepareStatement(sqld);
                ResultSet rsd=pstmtd.executeQuery();
                mcdesc.setModel(DbUtils.resultSetToTableModel(rsd));
        
                for(int j=mcc.getRowCount()-1;j>=0;j--)
                        ((DefaultTableModel)mcc.getModel()).removeRow(j);
                for(int j =0;j<mcdesc.getRowCount();j++)
        {
          DefaultTableModel model = (DefaultTableModel)mcc.getModel();
        model.addRow(new Object[]{});    
        }
                
                
                for(int i =0;i<mcdesc.getRowCount();i++)
                   mcc.getModel().setValueAt(mcdesc.getModel().getValueAt(i, 0).toString(), i, 0);
             } 
         catch(Exception e)
           {
            JOptionPane.showMessageDialog(this.ModifyColumn, e);
            
           }
    }   


    }//GEN-LAST:event_mctMouseClicked

    private void mccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mccMouseClicked
        // TODO add your handling code here:
        if(mcjdr.isSelected())
        mcpk.setText(mcc.getModel().getValueAt(mcc.getSelectedRow(), 0).toString());
        else if(mcjdr2.isSelected())
        mccn.setText(mcc.getModel().getValueAt(mcc.getSelectedRow(), 0).toString());
            
        
    }//GEN-LAST:event_mccMouseClicked

    private void mcpkKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mcpkKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_mcpkKeyPressed

    private void dcl14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl14MouseClicked
        // TODO add your handling code here:
         try {

               Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

               if(mcjdr.isSelected()){
               Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+mcdb.getModel().getValueAt(mcdb.getSelectedRow(),0).toString()),"root","root1799@");
                
                String sql="alter table "+mct.getModel().getValueAt(mct.getSelectedRow(),0).toString()+" add primary key("+mcpk.getText()+")";
                PreparedStatement pstmt=conn.prepareStatement(sql);
               pstmt.executeUpdate();
               }else if(mcjdr2.isSelected())
               {
                   Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+(uid+mcdb.getModel().getValueAt(mcdb.getSelectedRow(),0).toString()),"root","root1799@");
                
                String sql="alter table "+mct.getModel().getValueAt(mct.getSelectedRow(),0).toString()+" modify column "+mccn.getText()+" "+mcdt.getText()+"("+mcs.getText()+")";
                PreparedStatement pstmt=conn.prepareStatement(sql);
               pstmt.executeUpdate();
               }
               JOptionPane.showMessageDialog(this.ModifyColumn, "Column Modified");
             } 
         catch(Exception e)
           {
               
               if(e.toString().equals("java.sql.SQLSyntaxErrorException: Multiple primary key defined"))
            JOptionPane.showMessageDialog(this.ModifyColumn, mcpk.getText()+"Multiple primary key dosen't exist");
            else
                   JOptionPane.showMessageDialog(this.ModifyColumn, e);
           }

    }//GEN-LAST:event_dcl14MouseClicked

    private void dcl14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl14MouseEntered
        // TODO add your handling code here:
        dcl14.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_dcl14MouseEntered

    private void dcl14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcl14MouseExited
        // TODO add your handling code here:
        dcl14.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_dcl14MouseExited

    private void mcjdr2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mcjdr2ActionPerformed
        // TODO add your handling code here:
        if(mcjdr2.isSelected())
        {
            mcjdr.setSelected(false);
            mccn.setEnabled(true);
            mcdt.setEnabled(true);
            mcs.setEnabled(true);
            mccn.setEditable(false);
            mcpk.setEnabled(false);
        }else
        {
            
            mccn.setEnabled(false);
            mcdt.setEnabled(false);
            mcs.setEnabled(false);
            mccn.setText("");
            mcdt.setText("");
            mcs.setText("");
            
        }
    }//GEN-LAST:event_mcjdr2ActionPerformed

    private void mcpkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mcpkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mcpkActionPerformed

    private void mcjdrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mcjdrActionPerformed
        // TODO add your handling code here:
        if(mcjdr.isSelected())
        {
            mcjdr2.setSelected(false);
            mcpk.setEnabled(true);
            mcpk.setEditable(false);
            mccn.setEnabled(false);
            mcdt.setEnabled(false);
            mcs.setEnabled(false);
            
        }else
        {
            mcpk.setEnabled(false);
            mcpk.setText("");
        }
    }//GEN-LAST:event_mcjdrActionPerformed

    private void mcsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mcsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mcsActionPerformed

    private void mcsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mcsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_mcsKeyPressed

    private void mccnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mccnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mccnActionPerformed

    private void mccnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mccnKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_mccnKeyPressed

    private void mcdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mcdtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mcdtActionPerformed

    private void mcdtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mcdtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_mcdtKeyPressed

    private void mcpkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mcpkMouseClicked
        // TODO add your handling code here:
        if(mcpk.getText().isEmpty()&&mcjdr.isSelected())
            JOptionPane.showMessageDialog(this.ModifyColumn, "Select a Column from Column List");
    }//GEN-LAST:event_mcpkMouseClicked

    private void mccnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mccnMouseClicked
        // TODO add your handling code here:
         if(mccn.getText().isEmpty()&&mcjdr2.isSelected())
            JOptionPane.showMessageDialog(this.ModifyColumn, "Select a Column from Column List");
    }//GEN-LAST:event_mccnMouseClicked

    private void hmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hmMouseClicked
        // TODO add your handling code here:   HomeMain.removeAll();
        HomeMain.removeAll();
        HomeMain.repaint();
        HomeMain.revalidate();

        HomeMain.add(Home);
        HomeMain.repaint();
        HomeMain.revalidate();
    }//GEN-LAST:event_hmMouseClicked

    private void hmMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hmMouseEntered
        // TODO add your handling code here:
         hm.setBackground(new Color(0,255,255));
    }//GEN-LAST:event_hmMouseEntered

    private void hmMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hmMouseExited
        // TODO add your handling code here:
        hm.setBackground(new Color(0,181,240));
    }//GEN-LAST:event_hmMouseExited

    private void upcnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_upcnKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
            upv.requestFocus();
    }//GEN-LAST:event_upcnKeyPressed

    private void upvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_upvKeyPressed
if(evt.getKeyCode()==10)
            wcn.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_upvKeyPressed

    private void wcnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wcnKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
            wv.requestFocus();
    }//GEN-LAST:event_wcnKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SqlMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SqlMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SqlMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SqlMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SqlMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddColumn;
    private javax.swing.JPanel CreateTable;
    private javax.swing.JPanel DeleteColumn;
    private javax.swing.JPanel DeleteData;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Home;
    private javax.swing.JPanel HomeMain;
    private javax.swing.JPanel InsertData;
    private javax.swing.JPanel ModifyColumn;
    private javax.swing.JPanel Rough;
    private javax.swing.JPanel ShowData;
    private javax.swing.JPanel SideBar;
    private javax.swing.JPanel UpdateData;
    private javax.swing.JTable acdb;
    private javax.swing.JTable acdesc;
    private javax.swing.JTextField acdt;
    private javax.swing.JTextField acfk;
    private javax.swing.JLabel acl;
    private javax.swing.JTextField acn;
    private javax.swing.JTextField acsi;
    private javax.swing.JTable actb;
    private javax.swing.JLabel cdl;
    private javax.swing.JTextField cn;
    private javax.swing.JComboBox<String> colsel;
    private javax.swing.JTable ct;
    private javax.swing.JTable ctab;
    private javax.swing.JTable ctdb;
    private javax.swing.JLabel dashboard;
    private javax.swing.JTable dcdb;
    private javax.swing.JTable dcdesc;
    private javax.swing.JLabel dcl;
    private javax.swing.JLabel dcl1;
    private javax.swing.JLabel dcl10;
    private javax.swing.JLabel dcl11;
    private javax.swing.JLabel dcl12;
    private javax.swing.JLabel dcl13;
    private javax.swing.JLabel dcl14;
    private javax.swing.JLabel dcl2;
    private javax.swing.JLabel dcl3;
    private javax.swing.JLabel dcl4;
    private javax.swing.JLabel dcl5;
    private javax.swing.JLabel dcl6;
    private javax.swing.JLabel dcl7;
    private javax.swing.JLabel dcl8;
    private javax.swing.JLabel dcl9;
    private javax.swing.JTable dcol;
    private javax.swing.JTable dctb;
    private javax.swing.JLabel dd;
    private javax.swing.JTable dddb;
    private javax.swing.JLabel ddl;
    private javax.swing.JTextField delc;
    private javax.swing.JTextField delv;
    private javax.swing.JTable desct;
    private javax.swing.JTextField dt;
    private javax.swing.JTable dtd;
    private javax.swing.JTable dti;
    private javax.swing.JLabel dtl;
    private javax.swing.JTable dtud;
    private javax.swing.JTextField fk;
    private javax.swing.JTextField fkt;
    private javax.swing.JLabel hdate;
    private javax.swing.JTable hdb;
    private javax.swing.JLabel hm;
    private javax.swing.JLabel hmpic;
    private javax.swing.JLabel hnam;
    private javax.swing.JTable iddb;
    private javax.swing.JLabel idl;
    private javax.swing.JTable it;
    private javax.swing.JTable itd;
    private javax.swing.JTable itud;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JRadioButton jdr;
    private javax.swing.JRadioButton jrd;
    private javax.swing.JTable mcc;
    private javax.swing.JTextField mccn;
    private javax.swing.JTable mcdb;
    private javax.swing.JTable mcdesc;
    private javax.swing.JTextField mcdt;
    private javax.swing.JRadioButton mcjdr;
    private javax.swing.JRadioButton mcjdr2;
    private javax.swing.JLabel mcl;
    private javax.swing.JTextField mcpk;
    private javax.swing.JTextField mcs;
    private javax.swing.JTable mct;
    private javax.swing.JTextField pk;
    private javax.swing.JTextField s;
    private javax.swing.JTextField shcol;
    private javax.swing.JRadioButton shjr;
    private javax.swing.JTextField shval;
    private javax.swing.JTable st;
    private javax.swing.JTable tabe;
    private javax.swing.JTable testt;
    private javax.swing.JTextField tn;
    private javax.swing.JTable tp;
    private javax.swing.JTable uddb;
    private javax.swing.JLabel udl;
    private javax.swing.JTextField upcn;
    private javax.swing.JTextField upv;
    private javax.swing.JTextField wcn;
    private javax.swing.JTextField wv;
    // End of variables declaration//GEN-END:variables
}