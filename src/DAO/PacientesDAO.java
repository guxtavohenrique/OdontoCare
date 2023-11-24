package DAO;

import Conexao_BD.Conexao;
import Entidades.Pacientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author guxta
 */
public class PacientesDAO {
    
        ArrayList<Pacientes> lista = new ArrayList<>();
            
    public void cadastrarPacientes(Pacientes pacientes){
        
        Connection conn;
        PreparedStatement ps;
        
        String sql = "INSERT INTO PACIENTES (NOME, CPF, SEXO, DT_NASC, EMAIL, NUM_CELULAR, CEP, ENDERECO, COMPLEMENTO, ESTADO, CIDADE, BAIRRO, NOME_RESPONSAVEL, CPF_RESPONSAVEL, TP_TRATAMENTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        conn = new Conexao().conecta();
        
        try {
           ps = conn.prepareStatement(sql);
           ps.setString(1, pacientes.getNome());
           ps.setString(2, pacientes.getCpf());
           ps.setString(3, pacientes.getSexo());
           ps.setString(4, pacientes.getDat_nasc());
           ps.setString(5, pacientes.getEmail());
           ps.setString(6, pacientes.getNum_celular());
           ps.setString(7, pacientes.getCep());
           ps.setString(8, pacientes.getEndereco());
           ps.setString(9, pacientes.getComplemento());
           ps.setString(10, pacientes.getEstado());
           ps.setString(11, pacientes.getCidade());
           ps.setString(12, pacientes.getBairro());
           ps.setString(13, pacientes.getNome_responsavel());
           ps.setString(14, pacientes.getCpf_responsavel());
           ps.setString(15, pacientes.getTp_tratamento());
           ps.execute();
           JOptionPane.showMessageDialog(null, "Cadastrado");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro! " +e);
       }
       
   }
    public ArrayList<Pacientes> Listar() {
        Connection conn;
        PreparedStatement ps;
        ResultSet rst;
        
        String sql = "SELECT * FROM PACIENTES";

        conn = new Conexao().conecta();

        try {
            ps = conn.prepareStatement(sql);
            rst = ps.executeQuery();

            while (rst.next()) {
                Pacientes pacientes = new Pacientes();
                pacientes.setNome(rst.getNString("nome"));
                pacientes.setCpf(rst.getNString("cpf"));
                pacientes.setSexo(rst.getNString("sexo"));
                pacientes.setDat_nasc(rst.getNString("dt_nasc"));
                pacientes.setEmail(rst.getNString("email"));
                pacientes.setNum_celular(rst.getNString("num_celular"));
                pacientes.setCep(rst.getNString("cep"));
                pacientes.setEndereco(rst.getNString("endereco"));
                pacientes.setComplemento(rst.getNString("complemento"));
                pacientes.setEstado(rst.getNString("estado"));
                pacientes.setCidade(rst.getNString("cidade"));
                pacientes.setBairro(rst.getNString("bairro"));
                pacientes.setNome_responsavel(rst.getNString("nome_responsavel"));
                pacientes.setCpf_responsavel(rst.getNString("cpf_responsavel"));
                pacientes.setTp_tratamento(rst.getNString("tp_tratamento"));

                lista.add(pacientes);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar! " + erro);
        }
        return lista;
    }
    
    public void excluirPaciente(Pacientes pacientes){
        
        Connection conn;
        PreparedStatement ps;
        
        String sql = "DELETE FROM PACIENTES WHERE NOME = ?";
        
        conn = new Conexao().conecta();
        
         try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, pacientes.getNome());
            ps.execute();
            ps.close();
                    
            JOptionPane.showMessageDialog(null, "Excluido");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir" + e);
        }
        
    }
}
