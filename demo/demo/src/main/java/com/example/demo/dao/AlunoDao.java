package com.example.demo.dao;

import com.example.demo.model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDao {

    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO loja.loja (matricula, nome) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getMatricula());
            stmt.setString(2, aluno.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar aluno", e);
        }
    }

    public List<Aluno> listar() {
        String sql = "SELECT matricula, nome FROM loja.loja";
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                alunos.add(new Aluno(rs.getString("matricula"), rs.getString("nome")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alunos", e);
        }
        return alunos;
    }

    public Aluno buscarPorMatricula(String matricula) {
        String sql = "SELECT matricula, nome FROM loja.loja WHERE matricula = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Aluno(rs.getString("matricula"), rs.getString("nome"));
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno", e);
        }
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE loja.loja SET nome = ? WHERE matricula = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar aluno", e);
        }
    }

    public void deletar(String matricula) {
        String sql = "DELETE FROM loja.loja WHERE matricula = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, matricula);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar aluno", e);
        }
    }
}