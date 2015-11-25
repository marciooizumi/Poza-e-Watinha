package model;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;

import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class UsuarioModel {

    private static EntityManagerFactory factory = null;

    public void inserir(String nome, String senha, String email) {
        Usuario usu = new Usuario();

        if (nome.equals("") || senha.equals("") || email.equals("")) {
            return;
        }

        try {
            senha = criptografar(senha);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        usu.setSenha(senha);
        usu.setNome(nome);
        usu.setEmail(email);

//        EntityManager em = getEM();
        EntityManager em = this.getFactory();
        em.getTransaction().begin();
        em.persist(usu);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Usuario usu) {
        EntityManager em = this.getFactory();
        em.getTransaction().begin();
        em.remove(usu);
        em.getTransaction().commit();
        em.close();
    }

    public String criptografar(String senha) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
        String s = hash.toString(16);
        if (s.length() % 2 != 0) {
            s = "0" + s;
        }
        return s;
    }

    public Usuario verLogin(String usu, String senha) {

        try {
            senha = criptografar(senha);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            EntityManager em = this.getFactory();
            Usuario usuario = (Usuario) em.createQuery("SELECT u from Usuario u where u.nome like "
                    + ":usu and u.senha like :senha")
                    .setParameter("usu", usu).setParameter("senha", senha).getSingleResult();
            
            em.close();
            return usuario;
        } catch (NoResultException e) {
            return null;
        }
    }

    public EntityManager getFactory() {
        if (UsuarioModel.factory == null) {
            UsuarioModel.factory = Persistence.createEntityManagerFactory("xrmiPU");
        }
        return UsuarioModel.factory.createEntityManager();
    }

    public List<Usuario> listar(String busca) {
        EntityManager em = this.getFactory();

        @SuppressWarnings("unchecked")
        List<Usuario> lista = em.createQuery("SELECT u FROM Usuario u WHERE u.nome like :busca")
            .setParameter("busca", busca + "%")
            .getResultList();

        em.close();
        return lista;
    }
}
