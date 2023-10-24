/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dao.IDao;
import entities.Machine;
import entities.Salle;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import service.MachineService;
import service.SalleService;

/**
 *
 * @author youssef
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException {
        try {
            IDao<Machine> dao = new  MachineService();
            IDao<Salle> sdao=new SalleService();
            LocateRegistry.createRegistry(1099);
            final Hashtable<Object,Object> config=new Hashtable<Object,Object>();
            config.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
            config.put(Context.PROVIDER_URL, "rmi://localhost:1099");
            InitialContext context=new InitialContext(config);
            context.bind("salle",sdao);
            context.bind("dao",dao);
            System.out.println("En attente d'un client ");
        } catch (NamingException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
