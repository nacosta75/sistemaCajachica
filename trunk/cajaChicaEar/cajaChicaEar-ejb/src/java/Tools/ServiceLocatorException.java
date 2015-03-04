package Tools;



/**
 * Excepcion de utilidad ServiceLocator
* @author José David Martínez Rico - Informática & Tecnología
 * @version ActivacionesHome - 1.0_20110427
 */
public class ServiceLocatorException extends Exception {


    public ServiceLocatorException(String message){
        super(message);
    }

    public ServiceLocatorException(String message, Throwable cause){
        super(message, cause);
    }

}
