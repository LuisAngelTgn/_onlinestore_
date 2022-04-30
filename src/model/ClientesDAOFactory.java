
package model;

public class ClientesDAOFactory {
    
    public ClienteDAO crearClientesDAO(){
    return new ClienteDAOImpl();
    
    
    }
    
    
}
