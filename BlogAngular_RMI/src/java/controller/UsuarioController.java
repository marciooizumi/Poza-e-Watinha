/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.IUsuarioDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Usuario;
import model.UsuarioModel;
import util.RmiUtil;
import util.UsuarioJSON;

/**
 *
 * @author Carol
 */
@WebServlet(name = "UsuarioController", urlPatterns = {"/usuario"})
public class UsuarioController extends HttpServlet {

    IUsuarioDAO usuarioDAO;
    Usuario usuario = new Usuario();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            usuarioDAO = RmiUtil.retornaUsuarioDAO();
        } catch (RemoteException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("entrou no get do usuarioController");
//        UsuarioModel uModel = new UsuarioModel(); // Usado antes do RMI
        List<Usuario> lista = usuarioDAO.listarTodos();
        response.setContentType("application/json");
        response.getWriter().print((new UsuarioJSON()).convert(lista));
        System.out.println("saindo do get do usuarioController");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            usuarioDAO = RmiUtil.retornaUsuarioDAO();
        } catch (RemoteException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //utilizado sem RMI
//        UsuarioModel usuModel = new UsuarioModel();
        Boolean result = false;
        if (request.getParameter("method").equals("adicionar")) {
//            result = usuModel.inserir(request.getParameter("nome"), //utilizado sem RMI
//                    request.getParameter("senha"),
//                    request.getParameter("email"));
            try {
                usuario.setSenha(criptografar(request.getParameter("senha")));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
            usuario.setNome(request.getParameter("nome"));
            usuario.setEmail(request.getParameter("email"));
            result = usuarioDAO.inserir(usuario);

            response.getWriter().print(result);
        }

        //para pegar usuario e mostrar no cabeçario
        if (request.getParameter("method").equals("procurar")) {
            usuario = (Usuario) request.getSession().getAttribute("usuario");
            response.getWriter().print(usuario.getNome());
        }

    }

    public String criptografar(String senha) throws NoSuchAlgorithmException {
        System.out.println("criptografando");
        senha += "abobrinha";
        MessageDigest md = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        String s = hash.toString(16);
        if (s.length() % 2 != 0) {
            s = "0" + s;
        }
        return s;
    }
}
