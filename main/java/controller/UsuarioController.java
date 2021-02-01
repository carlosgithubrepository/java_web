package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import repository.RepositoryException;
import repository.UsuarioRepository;
import repository.UsuarioRepositoryBanco;
@WebServlet(urlPatterns={"/usucontroller","/usuariocontroller"})
public class UsuarioController extends HttpServlet{

	private UsuarioRepository usuRepository = new UsuarioRepositoryBanco();
	
	//Metodos HTTP
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//leitura
		String nome = req.getParameter("nome");
		String senha = req.getParameter("senha");
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setSenha(senha);
		
		//gravar
		try {
			usuRepository.cadastrar(usuario);
			
		} catch (RepositoryException e) {
			throw new ServletException(e);
		}
		//Resposta
		resp.getWriter().println("Requisitou pelo POST: "+ nome+ " "+senha);
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Usuario> lista = usuRepository.buscarTodos();
		if(req.getParameter("id") !=null) {
			Integer id =  Integer.parseInt(req.getParameter("id"));
			Usuario usu = usuRepository.buscarPorId(id);
			
			String json = "";
			json += "{\"id\" :\"" +usu.getId()+ "\", \"nome\":\""+usu.getNome()+"\", \"senha\":\""+usu.getSenha() +"\"}";
		    json += "";
		    resp.getWriter().println(json);
		}else {
		
		String json = "[";
		for(int i=0; i<lista.size();i++) {
			Usuario usu = lista.get(i);	
			json += "{\"id\" :\"" +usu.getId()+ "\", \"nome\":\""+usu.getNome()+"\", \"senha\":\""+usu.getSenha() +"\"}";
			if(i<lista.size()-1) {
			json +=",";
			}
		}
		json += "]";
		resp.getWriter().println(json);
		}
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String nome = req.getParameter("nome");
		String senha = req.getParameter("senha");
		Usuario usu = new Usuario();
		usu.setId(id);
		usu.setNome(nome);
		usu.setSenha(senha);
		usuRepository.alterar(usu);
		resp.getWriter().println("Requisitou pelo PUT");
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		usuRepository.excluir(id);
		//Resposta
		resp.getWriter().println("Requisitou pelo DELETE");
	}
}
