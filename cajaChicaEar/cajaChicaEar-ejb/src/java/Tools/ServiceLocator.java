/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class ServiceLocator {

    private Context context = null;
    private Map<String, Object> cache = null;
    private static ServiceLocator me;

    private ServiceLocator() throws ServiceLocatorException {
        try {
            Properties prop = new Properties();
           // prop.put(Context.INITIAL_CONTEXT_FACTORY, AppConstants.INITIAL_CONTEXT_FACTORY);
            //prop.put(Context.PROVIDER_URL, AppConstants.PROVIDER_URL);
            context = new InitialContext(prop);
            cache = Collections.synchronizedMap(new HashMap<String, Object>());
        } catch (NamingException ne) {
            throw new ServiceLocatorException("No se creo el contexto inicial. " + ne.getMessage(), ne);
        } catch (Exception e) {
            throw new ServiceLocatorException("Error no esperado. " + e.getMessage(), e);
        }
    }

    private synchronized static void createInstance() throws ServiceLocatorException {
        if (me == null) {
            me = new ServiceLocator();
        }
    }

    public static ServiceLocator getInstance() throws ServiceLocatorException {
        if (me == null) {
            createInstance();
        }
        return me;
    }

    @SuppressWarnings("unchecked")
    public <T> T getService(String serviceJndi) throws ServiceLocatorException {
        try {
            if (!cache.containsKey(serviceJndi)) {
                T service = (T) context.lookup(serviceJndi);
                cache.put(serviceJndi, service);
            }
            return (T) cache.get(serviceJndi);
        } catch (NamingException ne) {
            throw new ServiceLocatorException("No se encontro el JNDI: " + serviceJndi + " msg: " + ne.getMessage(), ne);
        } catch (Exception e) {
            throw new ServiceLocatorException("Error no esperado. " + e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getServiceLocal(String serviceJndi) throws ServiceLocatorException {
        try {
            if (!cache.containsKey(serviceJndi)) {
                T service = (T) context.lookup(serviceJndi);
                cache.put(serviceJndi, service);
            }
            return (T) cache.get(serviceJndi);
        } catch (NamingException ne) {
            throw new ServiceLocatorException("No se encontro el JNDI: " + serviceJndi + " msg: " + ne.getMessage(), ne);
        } catch (Exception e) {
            throw new ServiceLocatorException("Error no esperado. " + e.getMessage(), e);
        }
    }
}
