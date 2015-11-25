package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.UsuarioModel;
import util.UsuarioJSON;

@WebServlet(name = "NoticiasController", urlPatterns = {"/noticias"})
public class NoticiasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioModel uModel = new UsuarioModel();
        List <Usuario> lista = uModel.listar(request.getParameter("busca"));
        response.setContentType("application/json");
        response.getWriter().print((new UsuarioJSON()).convert(lista));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
