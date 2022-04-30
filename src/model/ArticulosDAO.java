
package model;

import java.util.List;

public interface ArticulosDAO {

    // metodo para agregar un articulo
    public boolean addArticulo(Articulos articulo);

    // metodo que devuelve una lista con todos los articulos
    public  List getArticulos();

    // lambda para recibir un articulo especifico
    public Articulos getArticuloByID(String codigo);

    // metodo para comprobar si un articulo existe
    public boolean articuloExiste(String codArticulo);
}
