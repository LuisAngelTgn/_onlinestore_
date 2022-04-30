package model;

import java.util.Date;
import java.util.List;


public class Datos {
    
     // Instanciar las implementaciones del DAO
    ClientesDAOFactory clientesfactory = new ClientesDAOFactory();
    ArticulosDAOFactory articulosfactory = new ArticulosDAOFactory();
    PedidosDAOFactory pedidosfactory = new PedidosDAOFactory();
  

    // constructor
    public Datos() {
    }

    // GESTION ARTICULOS
    public boolean crearArticulo(List<Object> parametros) {

        // crear un nuevo objeto de tipo Articulo
        Articulos articuloACrear = new Articulos(parametros.get(0).toString(),parametros.get(1).toString(),
                (Double)parametros.get(2), (Double)parametros.get(3), (Integer)parametros.get(4));

        // Comprobar si el articulo existe antes de agregarlo
        if (!articulosfactory.crearArticulosDAO().articuloExiste(parametros.get(0).toString())){
            // agregarlo a la arraylist en main
            articulosfactory.crearArticulosDAO().addArticulo(articuloACrear);
        }

        // enviar al controlador si el articulo se ha creado o no
        return articuloExiste(parametros.get(0).toString());
    }

    // Comprobar si el articulo existe en la base de datos
    public boolean articuloExiste(String codArticulo) {
        return articulosfactory.crearArticulosDAO().articuloExiste(codArticulo);
    }

    public List<Articulos> listarArticulos() {
        return articulosfactory.crearArticulosDAO().getArticulos();
    }
    // FIN GESTION ARTICULOS

    // GESTION CLIENTES
    // comprobar si el cliente existe
    public boolean clienteExiste(String email) {
        return clientesfactory.crearClientesDAO().clienteExiste(email);
    }

    public boolean crearCliente(List<Object> parametros) {
        boolean clienteCreado = false;
        // en la primera posicion de los parametros tenemos el tipo de cliente
        if (parametros.get(0).equals(1)) {
            // crear cliente estandard
            Cliente clienteEstandard = new Cliente_Estandard(parametros.get(1).toString(),parametros.get(2).toString(),
                    parametros.get(3).toString(),parametros.get(4).toString());
            clientesfactory.crearClientesDAO().addCliente(clienteEstandard);
            clienteCreado = clientesfactory.crearClientesDAO().clienteExiste(parametros.get(1).toString());
        } else if (parametros.get(0).equals(2)) {
            // crear cliente premium
            Cliente cp = new Cliente_Premium(parametros.get(1).toString(),parametros.get(2).toString(),
                    parametros.get(3).toString(),parametros.get(4).toString());
            clientesfactory.crearClientesDAO().addCliente(cp);
            clienteCreado = clientesfactory.crearClientesDAO().clienteExiste(parametros.get(1).toString());
        }
        // enviar si el cliente exista en la base de datos al controlador
        return clienteCreado;
    }

    public List<Cliente> recibirDatosClientes() {
        return clientesfactory.crearClientesDAO().getClientes();
    }

    public List<Cliente> recibirDatosClientesEstandard() {
        return clientesfactory.crearClientesDAO().listarClientesEstandard();
    }

    public List<Cliente> recibirDatosClientesPremium() {
        return clientesfactory.crearClientesDAO().listarClientesPremium();
    }

    // FIN GESTION CLIENTES

    // GESTION PEDIDOS
    public boolean crearDatosPedido(List<Object> parametros) {
        boolean existe;
        Pedidos nuevoPedido = new Pedidos();
        Articulos articuloPedido = articulosfactory.crearArticulosDAO().getArticuloByID((String)parametros.get(0));
        Cliente clientePedido = clientesfactory.crearClientesDAO().getClienteByEmail((String)parametros.get(1));

        // Declarar la fecha
        Date date = new Date();
        java.sql.Timestamp dateSQL = new java.sql.Timestamp(date.getTime());
        // Set valores del pedido
        // el numero de pedido es autoincrement, no hace falta agregarlo
        nuevoPedido.setArticulo(articuloPedido);
        nuevoPedido.setCliente(clientePedido);
        nuevoPedido.setCantidad((Integer)parametros.get(2));
        nuevoPedido.setFecha(dateSQL);
        nuevoPedido.setProcesado(false);

        // agregar datos al pedido
        existe = pedidosfactory.crearPedidosDAO().addPedido(nuevoPedido);

        return existe;
    }

    public Pedidos getPedido(int numPedido) {
        return pedidosfactory.crearPedidosDAO().getPedido(numPedido);
    }



    // metodo para eliminar un pedido existente
    public boolean eliminarPedido(int numPedido) {
        return pedidosfactory.crearPedidosDAO().eliminarPedido(numPedido);
    }

    public List<Pedidos> recibirDatosPedidosPendientes() {
        // actualizar los pedidos si se han enviado
        pedidosfactory.crearPedidosDAO().actualizarPedidos();
        // recibir todos los pedidos WHERE enviado == FALSE
        return pedidosfactory.crearPedidosDAO().getPedidosPendientes();
    }

    public List<Pedidos> recibirDatosPedidosEnviados() {
        // actualizar los pedidos
        pedidosfactory.crearPedidosDAO().actualizarPedidos();
        // recibir todos los pedidos enviados
        return pedidosfactory.crearPedidosDAO().getPedidosEnviados();

    }
    // FIN GESTION PEDIDOS
}

