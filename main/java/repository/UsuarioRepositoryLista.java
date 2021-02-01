package repository;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import model.Usuario;

public class UsuarioRepositoryLista implements UsuarioRepository{
	//Gerenciador da lista
		private List<Usuario> usuarios = new ArrayList<>();
		public void cadastrar(Usuario usuario) {
			usuarios.add(usuario);
		}
		public void alterar(int indice, Usuario usuario) {
			usuarios.set(indice, usuario);
		}
		public void excluir(int indice){
			usuarios.remove(indice);
		}
		public List<Usuario> buscarTodos(){
			return usuarios;
		}
		@Override
		public void alterar(Usuario usu) {
		}
		@Override
		public Usuario buscarPorId(Integer id) {
			return null;
		}
}
