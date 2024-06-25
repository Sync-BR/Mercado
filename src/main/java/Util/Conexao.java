
package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class Conexao {
 public Conexao() {}
    private static Connection conexaos = null;
    public static Connection getconnection() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/mercado", "root", "");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e + "");
        }
        return conexaos;
    }
               
               //Fechar conexão
    private static void close(Connection conn, PreparedStatement stmt, ResultSet rs) throws Exception {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
    // Fechar conexão Statement & Conjunto de resultado do ResultSet

    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) throws Exception {
        close(conn, stmt, rs);
    }
    //Fechar conexão sem fechar o conjunto do ResultSet

    public static void closeConnection(Connection conn, PreparedStatement stmt) throws Exception {
        close(conn, stmt, null);
    }
    //Fechar apenas a conexão

    public static void closeConnection(Connection conn) throws Exception {
        close(conn, null, null);
    }

             
}