// run with java -Djava.rmi.server.hostname=<ip address> Server

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
	public static void main(String[] argv) throws RemoteException {
		try {
			LocateRegistry.createRegistry(1099);
			System.out.println("java RMI registry created.");
		} catch (RemoteException e) {
			System.out.println("java RMI registry already exists.");
		}

		try {
			Naming.rebind("Hello", new Manager());
			System.out.println("Manager Server is ready.");
		} catch (Exception e) {
			System.out.println("Manager Serverfailed: " + e);
		}
	}
}
