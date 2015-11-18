package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Comentario;

public interface IComentarioDAO extends Remote {
    
    public void inserir(Comentario comentario) throws RemoteException;
    public List<Comentario> buscarPorPost(long postId) throws RemoteException;  
}
