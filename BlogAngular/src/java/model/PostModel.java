package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class PostModel {

    private static EntityManagerFactory factory = null;

    public Boolean inserir(Usuario autor, String titulo, String conteudo) {
        Post p = new Post();
        p.setAutor(autor);
        p.setData(new Date());
        p.setTitulo(titulo);
        p.setConteudo(conteudo);

//        EntityManager em = getEM();
        EntityManager em = this.getFactory();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();

        return true;
    }

    public void delete(Post p) {
        EntityManager em = this.getFactory();
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
        em.close();
    }

    public List<Post> listarPosts(Usuario usu) {
        EntityManager em = this.getFactory();

        List<Post> lista = em.createQuery("SELECT p FROM Post p where p.autor.id = :id ")
                    .setParameter("id", usu.getId()).getResultList();

        em.close();
        return lista;
    }
    
    public EntityManager getFactory() {
        if (PostModel.factory == null) {
            PostModel.factory = Persistence.createEntityManagerFactory("xrmiPU");
        }
        return PostModel.factory.createEntityManager();
    }
}
