package rmi.cliente;

public class RMICliente {

    public static void main(String[] args) {
        
        Servicos servicos = new Servicos();
        while(true){
            System.out.println("Adicionar novo usuário");
            System.out.println("----------------------");
            servicos.adicionarUsuario();
        }
    }

}
