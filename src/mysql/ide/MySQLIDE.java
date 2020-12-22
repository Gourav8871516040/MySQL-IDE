
package mysql.ide;


public class MySQLIDE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SqlLogin().setVisible(true);
            }
        });
    }
    
}
