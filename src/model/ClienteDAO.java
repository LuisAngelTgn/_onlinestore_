
package model;


import java.util.List;

/*
* Interface para definir la estructura DAO con una interfaz de los clientes
*/
public interface ClienteDAO {


    public List<Cliente> getClientes();
    public Cliente getClienteByEmail(String email);
    public boolean addCliente(Cliente cliente);

    public boolean clienteExiste(String email);

    public List<Cliente> listarClientesEstandard();
    public List<Cliente> listarClientesPremium();
}