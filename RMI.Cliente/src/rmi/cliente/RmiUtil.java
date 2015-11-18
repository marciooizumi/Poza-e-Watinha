package rmi.cliente;


import interfaces.IUsuarioDAO;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiUtil {
    	private static String enderecoServidor = "localhost";
	private static int porta = 1099;
	private static Registry registry;

	private static Registry retornaRegistry() throws RemoteException {
            return registry = LocateRegistry.getRegistry(enderecoServidor,porta);
	}

	public static IUsuarioDAO retornaUsuarioDAO() throws AccessException,
			RemoteException, NotBoundException {
		return (IUsuarioDAO) retornaRegistry().lookup("UsuarioDAO");
	}
}
