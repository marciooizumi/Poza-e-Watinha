/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UsuarioModel;

/**
 *
 * @author Carol
 */
@WebServlet(name = "CadastroController", urlPatterns = {"/CadastroController"})
public class CadastroController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioModel usuModel = new UsuarioModel();

        System.out.println("Nome: " + request.getParameter("nome"));
        if (request.getParameter("method").equals("adicionar")) {
            usuModel.inserir(request.getParameter("nome"), 
                    request.getParameter("senha"), 
                    request.getParameter("email"));
        }
        

    }

}
