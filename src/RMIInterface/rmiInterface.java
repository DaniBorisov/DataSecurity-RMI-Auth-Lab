package RMIInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface rmiInterface extends Remote {

    public String test1() throws RemoteException;

}
