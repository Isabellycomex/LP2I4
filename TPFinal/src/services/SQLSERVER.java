package services;

import entities.AlunoEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLSERVER {
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=TPfinal;trustServerCertificate=true";
    private final String username = "sa";
    private final String password = "_43690";

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int insertAluno(AlunoEntity aluno) throws SQLException {
        final String query = "INSERT INTO ALUNOS (NOME, IDADE, PESO, ALTURA, OBJETIVO) VALUES (?, ?, ?, ?, ?)";

        Connection connection = DriverManager.getConnection(url, username, password);

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, aluno.nome);
        preparedStatement.setInt(2, aluno.idade);
        preparedStatement.setFloat(3, aluno.peso);
        preparedStatement.setFloat(4, aluno.altura);
        preparedStatement.setString(5, aluno.objetivo);

        return preparedStatement.executeUpdate();
    }
}
