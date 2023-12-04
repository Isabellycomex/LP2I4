package controllers;

import entities.AlunoEntity;
import services.SQLSERVER;

import java.sql.SQLException;

public class AlunoController {
    private final SQLSERVER mySQLService;

    public AlunoController(SQLSERVER mySQLService) {
        this.mySQLService = mySQLService;
    }

    public boolean addAluno(AlunoEntity aluno) {
        try {
            final int affectedLines = mySQLService.insertAluno(aluno);

            return affectedLines != 0;
        } catch (SQLException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
