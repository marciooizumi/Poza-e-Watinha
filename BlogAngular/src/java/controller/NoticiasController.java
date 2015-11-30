package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Post;
import model.PostModel;
import model.Usuario;
import model.UsuarioModel;
import util.PostsJSON;
import util.UsuarioJSON;

@WebServlet(name = "NoticiasController", urlPatterns = {"/noticias"})
public class NoticiasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Chegou no get noticias");
        PostModel pModel = new PostModel();
        Usuario usu = (Usuario) request.getSession().getAttribute("usuario"),
                visitante = (Usuario) request.getSession().getAttribute("visitante");

        if (visitante == null) {
            List<Post> lista = pModel.listarPosts(usu);
            response.setContentType("application/json");
            response.getWriter().print((new PostsJSON()).convertPosts(lista));
            System.out.println("Imprimindo o json" + (new PostsJSON()).convertPosts(lista));
        }else{
            List<Post> lista = pModel.listarPosts(visitante);
            response.setContentType("application/json");
            response.getWriter().print((new PostsJSON()).convertPosts(lista));
            System.out.println("Imprimindo o json" + (new PostsJSON()).convertPosts(lista));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PostModel pModel = new PostModel();
        UsuarioModel uModel = new UsuarioModel();

        Boolean result = false;
        System.out.println("Nome: " + request.getParameter("nome"));
        if (request.getParameter("method").equals("adicionar")) {
            result = pModel.inserir((Usuario) (request.getSession().getAttribute("usuario")),
                    request.getParameter("titulo"),
                    request.getParameter("post"));
        }

        response.getWriter().print(result);
    }

}
