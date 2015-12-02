package controller;

import interfaces.IPostDAO;
import interfaces.IUsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import util.RmiUtil;
import util.UsuarioJSON;

@WebServlet(name = "NoticiasController", urlPatterns = {"/noticias"})
public class NoticiasController extends HttpServlet {

    IUsuarioDAO uModel;
    IPostDAO pModel;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            pModel = RmiUtil.retornaPostDAO();
        } catch (RemoteException ex) {
            Logger.getLogger(NoticiasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(NoticiasController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            uModel = RmiUtil.retornaUsuarioDAO();
        } catch (RemoteException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Este é o visitante atual = " + (Usuario) request.getSession().getAttribute("visitante") + ""
                + " \n e este é o usuario =" + (Usuario) request.getSession().getAttribute("usuario"));

//        PostModel pModel = new PostModel();// usado antes do RMI
//        UsuarioModel uModel = new UsuarioModel(); // usado antes do RMI
        Usuario usu = (Usuario) request.getSession().getAttribute("usuario"),
                visitante = (Usuario) request.getSession().getAttribute("visitante");

//         visitante = uModel.verUsuario(request.getParameter("visitante"));
//        System.out.println("esse é o visitante = " + visitante.getNome());
        System.out.println("Esse é o parametro controle = " + request.getParameter("controle"));

        if (request.getParameter("controle") != null) {
            System.out.println("entrou no controle diferente de NULL");
            request.getSession().setAttribute("visitante", uModel.buscarPorId(Long.parseLong(request.getParameter("visitaId"))));
        }

        if (visitante != null) {
            if (visitante.getId() == usu.getId()) {
                System.out.println("visitante e usuario são iguais - noticiasController");
                request.getSession().setAttribute("visitante", null);
            }
        }

        if (visitante == null) {
            List<Post> lista = pModel.buscarPorUsuario(usu.getId());
            response.setContentType("application/json");
            response.getWriter().print((new PostsJSON()).convertPosts(lista));
//            System.out.println("Imprimindo o json" + (new PostsJSON()).convertPosts(lista));
        } else {
            List<Post> lista = pModel.buscarPorUsuario(visitante.getId());
            response.setContentType("application/json");
            response.getWriter().print((new PostsJSON()).convertPosts(lista));
            System.out.println("Imprimindo o json do visitante" + (new PostsJSON()).convertPosts(lista));

            // response.sendRedirect("noticias");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Post p = new Post();
        Usuario u = new Usuario();

        try {
            pModel = RmiUtil.retornaPostDAO();
        } catch (RemoteException ex) {
            Logger.getLogger(NoticiasController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(NoticiasController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            uModel = RmiUtil.retornaUsuarioDAO();
        } catch (RemoteException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        PostModel pModel = new PostModel();
//        UsuarioModel uModel = new UsuarioModel();
        Boolean result = false;
        System.out.println("Nome: " + request.getParameter("nome"));

        if (request.getParameter("method").equals("adicionar")) {
            request.getSession().setAttribute("visitante", null);
            
            
            p.setAutor((Usuario) (request.getSession().getAttribute("usuario")));
            p.setConteudo(request.getParameter("post"));
            p.setTitulo(request.getParameter("titulo"));
            p.setData(new Date());

            result = pModel.inserir(p);
//            result = pModel.inserir((Usuario) (request.getSession().getAttribute("usuario")),
//                    request.getParameter("titulo"),
//                    request.getParameter("post"));
        }

        response.getWriter().print(result);
    }

}
