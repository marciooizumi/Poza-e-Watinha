package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;
import model.UsuarioModel;
import util.UsuarioJSON;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        UsuarioModel usuM = new UsuarioModel();

        Usuario usuario = usuM.verLogin(req.getParameter("buscaLogin"), req.getParameter("buscaSenha"));
        if (usuario == null) {
            res.setContentType("application/json");
            res.getWriter().print("{\"success\":false}");
        }

        res.setContentType("application/json");
        res.getWriter().print(new UsuarioJSON().convertUser(usuario.getId(), usuario.getNome()));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        UsuarioModel usuModel = new UsuarioModel();
        Usuario usuario = new Usuario();
        if (request.getParameter("method").equals("verificar")) {
            usuario = usuModel.verLogin(request.getParameter("nome"), request.getParameter("senha"));
        }
        if (usuario != null) {
            response.setContentType("application/json");
            response.getWriter().print(new UsuarioJSON().convertUser(usuario.getId(), usuario.getNome()));
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
        }else{
            System.out.println("Usuario null");
        }
        

    }
}
