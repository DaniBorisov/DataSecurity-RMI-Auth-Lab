package KeyManager;

import java.net.Socket;
import java.security.*;
import java.security.cert.X509Certificate;
import javax.net.ssl.*;

public class MyX509ExtendedKeyManager extends X509ExtendedKeyManager {

    // X509ExtendedKeyManager is an abstract class so your new class 
    // needs to implement all the abstract methods in this class. 
    // The easiest way to do this is to wrap an existing KeyManager
    // and call its methods for each of the methods you need to implement.   

    X509ExtendedKeyManager akm;

    public MyX509ExtendedKeyManager(X509ExtendedKeyManager akm) {
        this.akm = akm;
    }

    @Override
    public String[] getClientAliases(String keyType, Principal[] issuers) {
        return akm.getClientAliases(keyType, issuers);
    }

    @Override
    public String chooseClientAlias(String[] keyType, Principal[] issuers,
                                    Socket socket) {
        return akm.chooseClientAlias(keyType, issuers, socket);
    }

    @Override
    public String chooseServerAlias(String keyType, Principal[] issuers,
                                    Socket socket) {

        // This method has access to a Socket, so it is possible to call the
        // getHandshakeApplicationProtocol method here. Note the cast from 
        // a Socket to an SSLSocket
        String ap = ((SSLSocket) socket).getHandshakeApplicationProtocol();
        System.out.println("In chooseServerAlias, ap is: " + ap);
        return akm.chooseServerAlias(keyType, issuers, socket);
    }

    @Override
    public String[] getServerAliases(String keyType, Principal[] issuers) {
        return akm.getServerAliases(keyType, issuers);
    }

    @Override
    public X509Certificate[] getCertificateChain(String alias) {
        return akm.getCertificateChain(alias);
    }

    @Override
    public PrivateKey getPrivateKey(String alias) {
        return akm.getPrivateKey(alias);
    }
}