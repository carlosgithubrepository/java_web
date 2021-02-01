package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioRepositoryBanco implements UsuarioRepository{
	
		Connection conexao = ConexaoFactory.criarConexao();
		
		public void cadastrar(Usuario usuario) throws RepositoryException {
			try {
				PreparedStatement preparadorSQL = conexao.prepareStatement("insert into usuario (nome,senha) values (?,?)");
				preparadorSQL.setString(1, usuario.getNome());
				preparadorSQL.setString(2, usuario.getSenha());
				preparadorSQL.execute();
				preparadorSQL.close();
				
			} catch (SQLException e) {
				throw new RepositoryException(e);
			}
		}
		public void alterar(int indice, Usuario usuario) {
			//para vetor
		}
		public void alterar(Usuario usuario) {
			try {
				PreparedStatement preparadorSQL = conexao.prepareStatement("update usuario set nome = ? , senha = ? where id=?");
				preparadorSQL.setString(1, usuario.getNome());
				preparadorSQL.setString(2, usuario.getSenha());
				preparadorSQL.setInt(3, usuario.getId());
				preparadorSQL.execute();
				preparadorSQL.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public void excluir(int id){
			PreparedStatement preparadorSQL;
			try {
				preparadorSQL = conexao.prepareStatement("delete from usuario where id=?");
				preparadorSQL.setInt(1, id);
				preparadorSQL.execute();
				preparadorSQL.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		public List<Usuario> buscarTodos(){
			PreparedStatement preparadorSQL;
			List<Usuario> usuarios = new ArrayList<>();
			try {
				preparadorSQL = conexao.prepareStatement("select * from usuario");				
				ResultSet resultSet = preparadorSQL.executeQuery();
				while(resultSet.next()) {
					Usuario usuario = new Usuario();
					usuario.setId(resultSet.getInt("id"));
					usuario.setNome(resultSet.getString("nome"));
					usuario.setSenha(resultSet.getString("senha"));
					usuarios.add(usuario);
				}
				preparadorSQL.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return usuarios;
		}
		@Override
		public Usuario buscarPorId(Integer id) {
			PreparedStatement preparadorSQL;
			Usuario usuario = null;
			try {
				preparadorSQL = conexao.prepareStatement("select * from usuario where id = ?");
				preparadorSQL.setInt(1, id);
				ResultSet resultSet = preparadorSQL.executeQuery();
				if(resultSet.next()) {
					usuario = new Usuario();
					usuario.setId(resultSet.getInt("id"));
					usuario.setNome(resultSet.getString("nome"));
					usuario.setSenha(resultSet.getString("senha"));
				}
				preparadorSQL.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return usuario;
		}
}
