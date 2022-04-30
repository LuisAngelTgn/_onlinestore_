
package model;


public class ArticulosDAOFactory {
    
    public ArticulosDAO crearArticulosDAO(){
    return new ArticuloDAOImpl();
    }
    
   
}
