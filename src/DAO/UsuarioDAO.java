package DAO;

import Conexao_BD.Conexao;
import Entidades.Usuarios;
import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author guxta
 */
public class UsuarioDAO {

    ArrayList<Usuarios> lista = new ArrayList<>();

    public void cadastrarUsuario(Usuarios usuarios) {
        Connection conn;
        PreparedStatement ps;

        String sql = "INSERT INTO USUARIOS (USUARIO, EMAIL, TELEFONE, SENHA) VALUES (?, ?, ?, ?)";

        conn = new Conexao().conecta();

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuarios.getUsuario());
            ps.setString(2, usuarios.getEmail());
            ps.setString(3, usuarios.getTelefone());
            ps.setString(4, usuarios.getSenha());
            ps.execute();
            JOptionPane.showMessageDialog(null, "Cadastrado");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro! " + e);
        }

    }

    public ResultSet autenticarLogin(Usuarios usuarios) {

        Connection conn;
        PreparedStatement ps;
        Resultset rs;

        String sql = "SELECT * FROM USUARIOS WHERE (USUARIO = ? OR EMAIL = ?) AND SENHA = ?";

        conn = new Conexao().conecta();

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuarios.getUsuario());
            ps.setString(2, usuarios.getEmail());
            ps.setString(3, usuarios.getSenha());
            rs = (Resultset) ps.executeQuery();
            return (ResultSet) rs;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao autenticar" + e);
            return null;
        }
    }

    public ArrayList<Usuarios> Listar() {
        Connection conn;
        PreparedStatement ps;
        ResultSet rst;
        String sql = "SELECT * FROM USUARIOS";

        conn = new Conexao().conecta();

        try {
            ps = conn.prepareStatement(sql);
            rst = ps.executeQuery();

            while (rst.next()) {
                Usuarios usuarios = new Usuarios();
                usuarios.setUsuario(rst.getNString("usuario"));
                usuarios.setEmail(rst.getNString("email"));
                usuarios.setTelefone(rst.getNString("telefone"));

                lista.add(usuarios);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar! " + erro);
        }
        return lista;
    }
    
    public void excluirUsuario(Usuarios usuarios){
        
        Connection conn;
        PreparedStatement ps;
        
        String sql = "DELETE FROM USUARIOS WHERE USUARIO = ?";
        
        conn = new Conexao().conecta();
        
         try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuarios.getUsuario());
            ps.execute();
            ps.close();
                    
            JOptionPane.showMessageDialog(null, "Excluido");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir" + e);
        }
        
    }

}
