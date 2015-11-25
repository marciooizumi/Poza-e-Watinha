package rmi.cliente;

import java.nio.file.Paths;
import java.rmi.RMISecurityManager;

public class RMICliente {

    public static void main(String[] args) {
//        System.setProperty("java.security.policy", "security.policy");
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new RMISecurityManager());
//        }
        Servicos servicos = new Servicos();
        while(true){
            System.out.println("Adicionar novo usuário");
            System.out.println("----------------------");
            servicos.adicionarUsuario();
        }
    }

}
