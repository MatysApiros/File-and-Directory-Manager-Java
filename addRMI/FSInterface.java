import java.io.File;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FSInterface extends Remote {
    public File[] ls(String path) throws RemoteException;
    public void mkdir(String path) throws RemoteException;
    public void mkdirs(String path) throws RemoteException;
    public void create(String path) throws RemoteException;
    public void unlink(String path) throws RemoteException;
    public void write(byte[] data, String path) throws IOException;
    public void append(byte[] data, String path) throws RemoteException;
    public String read(String path) throws IOException;
}
