package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Post;

public interface IPostDAO extends Remote {
    public boolean inserir(Post post) throws RemoteException;
    public List<Post> buscarPorUsuario(long usuarioId) throws RemoteException;
}
