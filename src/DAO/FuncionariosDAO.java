package DAO;

import Conexao_BD.Conexao;
import Entidades.Funcionarios;
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
public class FuncionariosDAO {
    
        ArrayList<Funcionarios> lista = new ArrayList<>();
        
    public void cadastrarFuncionarios(Funcionarios funcionarios){
        
        Connection conn;
        PreparedStatement ps;
        
        String sql = "INSERT INTO FUNCIONARIOS (NOME, CPF, SEXO, DT_NASC, EMAIL, NUM_CELULAR, CARGO, CRO, UF, ESPECIALIZACAO, CEP, ENDERECO, COMPLEMENTO, ESTADO, CIDADE, BAIRRO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        conn = new Conexao().conecta();
         
        try {
           ps = conn.prepareStatement(sql);
           ps.setString(1, funcionarios.getNome());
           ps.setString(2, funcionarios.getCpf());
           ps.setString(3, funcionarios.getSexo());
           ps.setString(4, funcionarios.getDat_nascimento());
           ps.setString(5, funcionarios.getEmail());
           ps.setString(6, funcionarios.getNum_celular());
           ps.setString(7, funcionarios.getCargo());
           ps.setString(8, funcionarios.getCro());
           ps.setString(9, funcionarios.getUf());
           ps.setString(10, funcionarios.getEspecializacao());
           ps.setString(11, funcionarios.getCep());
           ps.setString(12, funcionarios.getEndereco());
           ps.setString(13, funcionarios.getComplemento());
           ps.setString(14, funcionarios.getEstado());
           ps.setString(15, funcionarios.getCidade());
           ps.setString(16, funcionarios.getBairro());
           ps.execute();
           JOptionPane.showMessageDialog(null, "Cadastrado");
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "Erro! " +e);
       }
       
   }
     public ArrayList<Funcionarios> Listar() {
        Connection conn;
        PreparedStatement ps;
        ResultSet rst;
        
        String sql = "SELECT * FROM FUNCIONARIOS";

        conn = new Conexao().conecta();

        try {
            ps = conn.prepareStatement(sql);
            rst = ps.executeQuery();

            while (rst.next()) {
                Funcionarios funcionarios = new Funcionarios();
                funcionarios.setNome(rst.getNString("nome"));
                funcionarios.setCpf(rst.getNString("cpf"));
                funcionarios.setSexo(rst.getNString("sexo"));
                funcionarios.setDat_nascimento(rst.getNString("dt_nasc"));
                funcionarios.setEmail(rst.getNString("email"));
                funcionarios.setNum_celular(rst.getNString("num_celular"));
                funcionarios.setCargo(rst.getNString("cargo"));
                funcionarios.setCro(rst.getNString("cro"));
                funcionarios.setUf(rst.getNString("uf"));
                funcionarios.setEspecializacao(rst.getNString("especializacao"));
                funcionarios.setCep(rst.getNString("cep"));
                funcionarios.setEndereco(rst.getNString("endereco"));
                funcionarios.setComplemento(rst.getNString("complemento"));
                funcionarios.setEstado(rst.getNString("estado"));
                funcionarios.setCidade(rst.getNString("cidade"));
                funcionarios.setBairro(rst.getNString("bairro"));

                lista.add(funcionarios);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar! " + erro);
        }
        return lista;
    }
     
    public void excluirFuncionario(Funcionarios funcionarios){
        
        Connection conn;
        PreparedStatement ps;
        
        String sql = "DELETE FROM FUNCIONARIOS WHERE NOME = ?";
        
        conn = new Conexao().conecta();
        
         try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, funcionarios.getNome());
            ps.execute();
            ps.close();
                    
            JOptionPane.showMessageDialog(null, "Excluido");
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir" + e);
        }
        
    }
}
