package br.com.fiap.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.dao.UsuarioDao;
import br.com.fiap.model.Usuario;

public class Teste {
	
	public static void main(String[] args) {
		UsuarioDao usuarioDao = new UsuarioDao();
		
		Usuario emerson = new Usuario("Emerson", "emerson@gmail.com");
		Usuario adriane = new Usuario("Adriane", "adriane@gmail.com");
		Usuario maria = new Usuario("Maria", "maria@gmail.com");
		
		usuarioDao.insert(emerson);
		usuarioDao.insert(adriane);
		usuarioDao.insert(maria);
				
		List<Usuario> lista = new ArrayList<Usuario>();
		lista = usuarioDao.selectAll();
		
		System.out.println(lista.size());
		
		for (Usuario usuario : lista) {
			System.out.println("Nome: " + usuario.getNome());
			System.out.println("Email: " + usuario.getEmail());
		}
		
		usuarioDao.delete(1);
		
		Usuario mariaUpdated = usuarioDao.selectById(2);
		mariaUpdated.setNome("Maria Silva");
		mariaUpdated.setEmail("mariasilva@gmail.com");
		usuarioDao.update(mariaUpdated);
		
		
	}
}
