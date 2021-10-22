package RMIInterface;

import java.rmi.RemoteException;

public  class ImplExample implements rmiInterface {


    @Override
    public String test1() throws RemoteException {
        return "Hello there";
    }
}