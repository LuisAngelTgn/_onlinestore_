
package model;


public class PedidosDAOFactory {
    
    public PedidosDAO crearPedidosDAO(){
    return new PedidosDAOImpl();
    
    
    }
    
}
