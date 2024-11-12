package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.connection.ConnectionFactory;
import br.com.fiap.model.Usuario;

public class UsuarioDao {
	private Connection connection;
	
	public UsuarioDao() {
		this.connection = new ConnectionFactory().conectar();
	}
	
	public void insert(Usuario usuario) {
		String sql = "INSERT INTO usuario (nome, email, dataCadastro) VALUES (?, ?, ?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setDate(3, usuario.getDataCadastro());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(long id) {
		String sql = "DELETE FROM usuario WHERE id=?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Usuario usuario) {
		String sql = "UPDATE usuario SET nome=?, email=?, dataCadastro=? WHERE id=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setDate(3, usuario.getDataCadastro());
			stmt.setLong(4, usuario.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Usuario> selectAll() {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		String sql = "SELECT * FROM usuario ORDER BY nome";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				
				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setDataCadastro(rs.getDate("dataCadastro"));
				
				listaUsuarios.add(usuario);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaUsuarios;
	}
	
	public Usuario selectById(long id) {
		String sql = "SELECT * FROM usuario WHERE id=?";
		Usuario usuario = new Usuario();
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {			
				usuario.setId(rs.getLong("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setDataCadastro(rs.getDate("dataCadastro"));
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usuario;
	}
}
