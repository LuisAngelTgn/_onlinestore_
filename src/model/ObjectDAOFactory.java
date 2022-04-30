
package model;


public class ObjectDAOFactory {
    
public ArticulosDAO crearArticulosDAO(){
    return new ArticuloDAOImpl();
  
    
}
    
}
