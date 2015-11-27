/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Carol
 */
@WebServlet(name = "UsuarioController", urlPatterns = {"/usuario"})
public class UsuarioController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("entrou no get");
        UsuarioModel uModel = new UsuarioModel();
        List<Usuario> lista = uModel.listar(request.getParameter("busca"));
        response.setContentType("application/json");
        response.getWriter().print((new UsuarioJSON()).convert(lista));
        System.out.println("saindo do get");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioModel usuModel = new UsuarioModel();
        Boolean result = false;
        System.out.println("Nome: " + request.getParameter("nome"));
        if (request.getParameter("method").equals("adicionar")) {
            result = usuModel.inserir(request.getParameter("nome"),
                    request.getParameter("senha"),
                    request.getParameter("email"));
        }

        response.getWriter().print(result);

    }

}
