package rmi.cliente;

import interfaces.IUsuarioDAO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;

public class Servicos{

    IUsuarioDAO usuarioDAO;
    BufferedReader bufferRead;

    public Servicos() {
        try {
            usuarioDAO = RmiUtil.retornaUsuarioDAO();
            bufferRead = new BufferedReader(new InputStreamReader(System.in));
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Servicos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void adicionarUsuario() {

        Usuario usuario = new Usuario();
        String nome, email;

        try {
            System.out.println("Nome do usuário");
            nome = bufferRead.readLine();
            System.out.println("Email do usuário");
            email = bufferRead.readLine();

            usuario.setNome(nome);
            usuario.setEmail(email);

            usuarioDAO.inserir(usuario);
            System.out.println("Usuario inserido com sucesso!");
        } catch (IOException ex) {
            Logger.getLogger(Servicos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
