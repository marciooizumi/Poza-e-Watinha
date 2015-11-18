package model.dao;

import model.Usuario;
import interfaces.IUsuarioDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import javax.persistence.EntityManager;
import util.JpaProvider;

public class UsuarioDAO extends UnicastRemoteObject
        implements IUsuarioDAO {

    public UsuarioDAO() throws RemoteException {
        super();
    }

    @Override
    public void inserir(Usuario usuario) throws RemoteException {
        EntityManager em = JpaProvider.getInstance().createEntityManager();
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void atualizar(Usuario usuario) throws RemoteException {
	EntityManager em = JpaProvider.getInstance().createEntityManager();

	em.getTransaction().begin();
	em.merge(usuario);
	em.getTransaction().commit();
	em.close();    
    }

    @Override
    public Usuario buscarPorId(long id) throws RemoteException {
        EntityManager em = JpaProvider.getInstance().createEntityManager();
        Usuario usuario = em.find(Usuario.class, id);
        return usuario;
    }

    @Override
    public Usuario buscarPorEmailSenha(String email, String senha) throws RemoteException {
        EntityManager em = JpaProvider.getInstance().createEntityManager();

        try {
            Usuario usuario = em
                    .createQuery(
                            "SELECT u FROM Usuario AS u WHERE u.email = :email AND u.senha = :senha",
                            Usuario.class)
                    .setParameter("email", email)
                    .setParameter("senha", senha)
                    .getSingleResult();

            return usuario;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Usuario> buscarPorNome(String nome) throws RemoteException {
        EntityManager em = JpaProvider.getInstance().createEntityManager();

        List<Usuario> usuarios = em
                .createQuery(
                        "SELECT u FROM Usuario As u WHERE UPPER(u.nome) LIKE:busca",
                        Usuario.class)
                .setParameter("busca", nome.toUpperCase() + "%")
                .getResultList();
        return usuarios;
    }
    
    @Override
    public Usuario buscarPorEmail(String email) throws RemoteException {
       EntityManager em = JpaProvider.getInstance().createEntityManager();

        try {
            Usuario usuario = em
                    .createQuery(
                            "SELECT u FROM Usuario AS u WHERE u.email = :email",
                            Usuario.class)
                    .setParameter("email", email)
                    .getSingleResult();

            return usuario;
        } catch (Exception e) {
            return null;
        }
    }
}
