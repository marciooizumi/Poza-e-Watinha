package interfaces;

import model.Usuario;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IUsuarioDAO extends Remote{
    public void inserir(Usuario usuario) throws RemoteException;
    public void atualizar(Usuario usuario) throws RemoteException;
    public Usuario buscarPorId(long id) throws RemoteException;
    public List<Usuario> buscarPorNome(String nome) throws RemoteException;
    public Usuario buscarPorEmailSenha(String email,String senha) throws RemoteException;
    public Usuario buscarPorEmail(String email) throws RemoteException;
} 