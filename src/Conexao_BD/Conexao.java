package Conexao_BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
        

public class Conexao {
    public Connection conecta(){
        Connection conn = null;
     
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/odontologia", "root", "root");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao se conectar! " +e);
        }
        return conn;
    }    
}