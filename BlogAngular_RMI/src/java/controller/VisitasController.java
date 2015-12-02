/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.UsuarioModel;

/**
 *
 * @author Márcio
 */
@WebServlet(name = "VisitasController", urlPatterns = {"/visita"})
public class VisitasController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse res)
            throws ServletException, IOException {
        System.out.println("chegou o visitante = " + req.getParameter("nome"));
        UsuarioModel usuModel = new UsuarioModel();
        if(req.getSession().getAttribute("usuario") == req.getSession().getAttribute("visitante"))
            req.getSession().setAttribute("visitante", null);
        else 
            req.getSession().setAttribute("visitante", usuModel.verUsuario(req.getParameter("nome")));
        RequestDispatcher rd = req.getRequestDispatcher("noticias");
        rd.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}
