package RMIInterface;

import java.rmi.RemoteException;

public  class ImplExample implements rmiInterface {


    @Override
    public String test1(String name) throws RemoteException {
        return "Hello " + name;
    }
}