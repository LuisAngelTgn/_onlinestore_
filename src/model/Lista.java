
package model;

/*En la clase Lista el arraylist es genérico,
y en esta clase ponemos métodos que pueden ser comunes para clientes, artículos y pedidos, 
por ejemplo borrar un objeto, getsize para saber cuantos objetos hay en el arraylist, etc*/

import java.util.ArrayList;
import java.util.List;

// clase generica
public class Lista<T> {

    // metodo generico para llenar una lista con objetos
    protected static <T> ArrayList<T>  retrieveObjetosClase (ArrayList<T> arrayOrigen) {
        // declarar array nueva
        ArrayList<T> lista = new ArrayList<T>();
        // iniciar iterador
        for (int i = 0; i < lista.size(); i++) {
            lista.add(lista.get(i));
        }
        // Devolver array
        return lista;
    }

}
