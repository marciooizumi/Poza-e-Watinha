package model.dao;

import interfaces.IComentarioDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import javax.persistence.EntityManager;
import model.Comentario;
import util.JpaProvider;

public class ComentarioDAO extends UnicastRemoteObject
        implements IComentarioDAO{
    
    public ComentarioDAO() throws RemoteException {
        super();
    }
    
    @Override
    public void inserir(Comentario comentario) throws RemoteException {
        EntityManager em = JpaProvider.getInstance().createEntityManager();
        em.getTransaction().begin();
        em.persist(comentario);
        em.getTransaction().commit();
        em.close();    
    }

    @Override
    public List<Comentario> buscarPorPost(long postId) throws RemoteException {
        EntityManager em = JpaProvider.getInstance().createEntityManager();

        List<Comentario> comentarios = em
                .createQuery(
                        "SELECT c FROM Comentario As c WHERE c.post.id = :postId",
                        Comentario.class)
                .setParameter("postId", postId)
                .getResultList();
        return comentarios;
    }   
}
