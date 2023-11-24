package DAO;

import Conexao_BD.Conexao;
import Entidades.Agendamento;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author guxta
 */
public class AgendamentoDAO {
    
    ArrayList<Agendamento> lista = new ArrayList<>();

    public void agendamentoPaciente(Agendamento agendamento){
        Connection conn;
        PreparedStatement ps;
        
        String sql = "INSERT INTO AGENDAMENTO (USUARIO, DIA, HORA, TRATAMENTO) VALUES (?, ?, ?, ?)";
        
         conn = new Conexao().conecta();
         
         try {
             ps = conn.prepareStatement(sql);
             ps.setString(1, agendamento.getUsuario());
             ps.setString(2, agendamento.getDia());
             ps.setString(3, agendamento.getHora());
             ps.setString(4, agendamento.getTratamento());
             ps.execute();
            JOptionPane.showMessageDialog(null, "Cadastrado");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro! " + erro);
        }
    }
    
    public ArrayList<Agendamento> ListarAgenda(){
        Connection conn;
        PreparedStatement ps;
        ResultSet rst;
        String sql = "SELECT * FROM AGENDAMENTO";
        
        conn = new Conexao().conecta();
        
        try {
            ps = conn.prepareStatement(sql);
            rst = ps.executeQuery();

            while (rst.next()) {
                Agendamento agendamento = new Agendamento();
                agendamento.setUsuario(rst.getNString("usuario"));
                agendamento.setDia(rst.getNString("dia"));
                agendamento.setHora(rst.getNString("hora"));
                agendamento.setTratamento(rst.getNString("tratamento"));

                lista.add(agendamento);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar! " + erro);
        }
        return lista;
    }
    
    public void excluirAgenda(Agendamento agenda){
        
        Connection conn;
        PreparedStatement ps;
        
        String sql = "DELETE FROM AGENDAMENTO WHERE USUARIO = ?";
        
        conn = new Conexao().conecta();
        
         try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, agenda.getUsuario());
            ps.execute();
            ps.close();
                    
            JOptionPane.showMessageDialog(null, "Excluido");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir" + e);
        }
        
    }
}
