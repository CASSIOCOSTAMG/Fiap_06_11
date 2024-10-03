package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.exception.EntidadeNaoEcontradaException;
import br.com.fiap.fintech.factory.ConnectionFactory;
import br.com.fiap.fintech.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {

    private Connection conexao;

    public UsuarioDao() throws SQLException {
       conexao = ConnectionFactory.getConnection();
    }

    public void cadastrar(Usuario usuario) throws SQLException{
        PreparedStatement stm = conexao.prepareStatement(" INSERT INTO T_USUARIO (CODUSU, NOMEUSU,  SOBRENOME, LOGIN, SENHA, EMAIL, DTALTER) VALUES ( seq_usuario.nextval, ?, ?, ?, ?, ? , SYSDATE )" );
        stm.setString(1,usuario.getNome().toUpperCase());
        stm.setString(2,usuario.getSobrenome().toUpperCase());
        stm.setString(3,usuario.getLogin().toUpperCase());
        stm.setString(4,usuario.getSenha());
        stm.setString(5,usuario.getEmail());
        stm.executeUpdate();



    }

    public Usuario pesquisar (long codusu) throws SQLException, EntidadeNaoEcontradaException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM T_USUARIO WHERE CODUSU = ? ");
        stm.setLong(1, codusu);
        ResultSet result = stm.executeQuery();

        if (!result.next()) throw new EntidadeNaoEcontradaException("Usuário não encontrado!");
        Long id = result.getLong("codusu");
        String nome = result.getString("nomeusu");
        String sobrenome = result.getString("sobrenome");
        String login = result.getString("login");
        String senha = result.getString("senha");
        String email = result.getString("email");

        return new Usuario(codusu,nome,sobrenome,login);

    }


    public void remover (long codusu) throws SQLException, EntidadeNaoEcontradaException {
        PreparedStatement stm = conexao.prepareStatement("DELETE FROM T_USUARIO WHERE CODUSU = ? ");
        stm.setLong(1,codusu);
        int linha = stm.executeUpdate();
        if (linha ==0)
            throw new EntidadeNaoEcontradaException("Usuário não encontrado para ser removido!");
    }

    public List<Usuario> listar() throws SQLException{
        PreparedStatement stm = conexao.prepareStatement(" SELECT * FROM T_USUARIO ORDER BY CODUSU ASC ");
        ResultSet result = stm.executeQuery();
        List<Usuario> lista = new ArrayList<>();

        while (result.next()){
            Long codusu = result.getLong("codusu");
            String nome = result.getString("nomeusu");
            String sobrenome = result.getString("sobrenome");
            String email = result.getString("email");

            lista.add(new Usuario(codusu, nome, sobrenome, email));

        }
        return lista;

    }

    public void atualizar(Usuario usuario) throws SQLException{
        PreparedStatement stm = conexao.prepareStatement(" UPDATE T_USUARIO SET NOMEUSU = ?, SOBRENOME=?, EMAIL=?, LOGIN=?, SENHA=? WHERE CODUSU=? ");
        stm.setString(1,usuario.getNome());
        stm.setString(2, usuario.getSobrenome());
        stm.setString(3, usuario.getEmail());
        stm.setString(4, usuario.getLogin());
        stm.setString(5, usuario.getSenha());
        stm.setLong(6, usuario.getCodusu());
        stm.executeUpdate();

    }

    public void fecharConexao() throws SQLException{
        conexao.close();
    }

}
