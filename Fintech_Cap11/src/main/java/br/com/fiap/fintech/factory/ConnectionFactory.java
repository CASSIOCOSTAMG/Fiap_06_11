package br.com.fiap.fintech.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL =  "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
    private static final String USUARIO = "rm557226" ;  // "pf0392";
    private static final String SENHA =   "080378";  //"fiap";

    // metodo para obter uma conexao com o banco de dados
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USUARIO,SENHA);

    }
}
