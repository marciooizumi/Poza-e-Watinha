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
            return;
        }
        
        res.setContentType("application/json");
        res.getWriter().print(new UsuarioJSON().convertUser(usuario.getId(), usuario.getNome()));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
