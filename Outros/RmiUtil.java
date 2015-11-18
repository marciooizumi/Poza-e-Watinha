package util;

import interfaces.IComentarioDAO;
import interfaces.IPostDAO;
import interfaces.IUsuarioDAO;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RmiUtil {

	private static String enderecoServidor = "192.168.1.102";
	private static int porta = 1099;
	private static Registry registry;

	private static Registry retornaRegistry() throws RemoteException {
		if (registry == null)
			return registry = LocateRegistry.getRegistry(enderecoServidor,
					porta);
		return registry;
	}

	public static IUsuarioDAO retornaUsuarioDAO() throws AccessException,
			RemoteException, NotBoundException {
		return (IUsuarioDAO) retornaRegistry().lookup("UsuarioDAO");
	}

	public static IPostDAO retornaPostDAO() throws AccessException,
			RemoteException, NotBoundException {
		return (IPostDAO) retornaRegistry().lookup("PostDAO");
	}
	
	public static IComentarioDAO retornaComentarioDAO() throws AccessException,
			RemoteException, NotBoundException {
		return (IComentarioDAO) retornaRegistry().lookup("ComentarioDAO");
	}
}
