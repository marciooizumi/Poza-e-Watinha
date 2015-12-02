package controller;

import interfaces.IUsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import util.RmiUtil;
import util.UsuarioJSON;

@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    IUsuarioDAO usuModel;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            usuModel = RmiUtil.retornaUsuarioDAO();
        } catch (RemoteException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        UsuarioModel usuM = new UsuarioModel();//usado antes do RMI

        Usuario usuario = new Usuario();
        try {
            usuario = usuModel.buscarPorLoginSenha(req.getParameter("buscaLogin"), criptografar(req.getParameter("buscaSenha")));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (usuario == null) {
            res.setContentType("application/json");
            res.getWriter().print("{\"success\":false}");
        }
        req.getSession().setAttribute("usuario", usuario);

        res.setContentType("application/json");
        res.getWriter().print(new UsuarioJSON().convertUser(usuario.getId(), usuario.getNome()));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            usuModel = RmiUtil.retornaUsuarioDAO();
        } catch (RemoteException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(UsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        UsuarioModel usuModel = new UsuarioModel();// usado antes do RMI
        Usuario usuario = new Usuario();
        
        if (request.getParameter("method").equals("verificar")) {
            try {
                System.out.println("entrou no post do loginController com usuario = " + criptografar(request.getParameter("senha")));
                usuario = usuModel.buscarPorLoginSenha(request.getParameter("nome"), criptografar(request.getParameter("senha")));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.getSession().setAttribute("usuario", null);
            request.getSession().setAttribute("visitante", null);

        }
        if (usuario != null) {
            response.setContentType("application/json");
            response.getWriter().print(new UsuarioJSON().convertUser(usuario.getId(), usuario.getNome()));
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
        } else {
            System.out.println("Usuario null");
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
