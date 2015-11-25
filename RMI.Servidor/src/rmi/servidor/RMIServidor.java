package rmi.servidor;

import model.dao.UsuarioDAO;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import model.dao.ComentarioDAO;
import model.dao.PostDAO;
import util.JpaProvider;

public class RMIServidor {

    public static void main(String[] args) {
        
                JpaProvider.getInstance().createEntityManager();

        
        System.setProperty("java.security.policy", "security.policy");
        
        
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            UsuarioDAO usuarioDAO = new UsuarioDAO();
            registry.bind("UsuarioDAO", usuarioDAO);
            System.out.println("UsuarioDAO criado!");    
            
            PostDAO postDAO = new PostDAO();         
            registry.bind("PostDAO", postDAO);
            System.out.println("PostDAO criado!");
            
            ComentarioDAO comentarioDAO = new ComentarioDAO();         
            registry.bind("ComentarioDAO", comentarioDAO);
            System.out.println("ComentarioDAO criado!");
            
            System.in.read();
            
        } catch (Exception e) {
            System.out.println(e);
            
        }
        
        
        
        
    }

}
