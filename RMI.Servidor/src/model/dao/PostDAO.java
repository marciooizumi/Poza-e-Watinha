package model.dao;

import interfaces.IPostDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import javax.persistence.EntityManager;
import model.Post;
import util.JpaProvider;

public class PostDAO extends UnicastRemoteObject
        implements IPostDAO {
    
    public PostDAO() throws RemoteException {
        super();
    }
    @Override
    public void inserir(Post post) throws RemoteException {
        EntityManager em = JpaProvider.getInstance().createEntityManager();
        em.getTransaction().begin();
        em.persist(post);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Post> buscarPorUsuario(long usuarioId) throws RemoteException {
        EntityManager em = JpaProvider.getInstance().createEntityManager();
        List<Post> posts = em.createQuery("SELECT p FROM Post As p WHERE p.autor.id = :usuario_id ORDER BY p.id DESC",
                Post.class)
                .setParameter("usuario_id", usuarioId)
                .getResultList();

        return posts;
    }
}
