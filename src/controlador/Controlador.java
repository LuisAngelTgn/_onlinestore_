package controlador;
import model.Datos;
import model.Articulos;
import view.GestionOS;
import java.util.ArrayList;
import java.util.List;


public class Controlador {
    private final Datos modelo;
    private final GestionOS vista;
    private int opcion = 0;

    // constructor
    public Controlador(Datos modelo, GestionOS vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    // MENU PRINCIPAL
    // Vista menu general
    public void mostrarMenuPrincipal() throws Exception {
        opcion = vista.printMenu();
        performActionMenu(opcion);
    }


    // Accion del menu principal
    public void performActionMenu(int choice) throws Exception {
        switch (choice) {
            case 0 -> MVCOnlineStore.setExitTrue();
            case 1 -> gestionArticulos();
            case 2 -> gestionClientes();
            case 3 -> gestionDePedidos();
        }
    }

    // GESTION DE ARTICULOS
    public void gestionArticulos() throws Exception {
        opcion = vista.printGestionArticulos();
        performActionArticulo(opcion);

    }

    public void performActionArticulo(int choice) throws Exception {
        switch (choice) {
            case 0 -> mostrarMenuPrincipal();
            case 1 -> agregarArticulo();
            case 2 -> mostrarArticulos();
        }
    }

    public void agregarArticulo() {
        boolean creado = false;
        List parametros = new ArrayList<>();
        parametros = vista.printAgregarArticulo();
        // enviar informacion a Datos si la informacion no esta vacia
        if (!parametros.isEmpty()) {
            // Comprobar si el articulo existe antes de crearlo
            if (!modelo.articuloExiste(parametros.get(0).toString())){
                creado = modelo.crearArticulo(parametros);
            }
        }
        vista.articuloCreado(creado);
    }

    public void mostrarArticulos() {
        // Crear una array temporal para recibir articulos
        List<Articulos> lista = new ArrayList<>();
        // Llenar la array con los articulos
        lista = modelo.listarArticulos();
        // Llamar a la vista para mostrar los articulos
        vista.printMostrarArticulos(lista);
    }
    // FIN GESTION DE ARTICULOS

    // GESTION DE CLIENTES
    private void gestionClientes() throws Exception {
        opcion = vista.printGesionClientes();
        performActionCliente(opcion);
    }

    public void performActionCliente(int choice) throws Exception {
        switch (choice) {
            case 0 -> mostrarMenuPrincipal();
            case 1 -> agregarCliente();
            case 2 -> mostrarClientes();
            case 3 -> mostrarClientesEstandard();
            case 4 -> mostrarClientesPremium();
        }
    }

    // metodo para agregar un cliente
    public void agregarCliente() {
        boolean creado;
        boolean existe;
        List parametros;
        parametros = vista.printAgregarCliente();

        // comprobar que nos lleguen parametros de entrada
        if (!parametros.isEmpty()) {
            // comprobar si el cliente introducido ya existe
            existe = modelo.clienteExiste(parametros.get(1).toString());
            if(!existe) {
                creado = modelo.crearCliente(parametros);
                vista.clienteCreado(creado);
            }
        }

    }



    // metodo para mostrar los clientes
    public void mostrarClientes() {
        List lista = modelo.recibirDatosClientes();
        vista.printMostrarClientes(lista);
    }

    // metodo para mostrar clientes estandard
    public void mostrarClientesEstandard() {
        List lista = modelo.recibirDatosClientesEstandard();
        vista.printMostrarClientesEstandard(lista);
    }

    public void mostrarClientesPremium() {
        List lista = modelo.recibirDatosClientesPremium();
        vista.printMostrarClientesPremium(lista);
    }

    // FIN GESTION DE CLIENTES


    //  GESTION DE PEDIDOS
    private void gestionDePedidos() throws Exception {
        opcion = vista.printGesionPedidos();
        performActionPedido(opcion);
    }

    // Sub menu para los pedidos
    public  void performActionPedido(int choice) throws Exception {
        switch (choice) {
            case 0 -> mostrarMenuPrincipal();
            case 1 -> agregarPedido();
            case 2 -> eliminarPedido();
            case 3 -> mostrarPedidosPendientes();
            case 4 -> mostrarPedidosEnviados();
        }
    }

    // metodo para agregar un pedido
    public void agregarPedido() throws Exception {
        List parametros = new ArrayList<>();
        boolean pedidoCreado = false;
        String codigoArticulo = "";
        String emailCliente = "";
        int cantidad = 0;

        // recibir el codigo del articulo
        try {
            codigoArticulo = vista.printAgregarPedido();
            // comprobar si el articulo existe
            if (!modelo.articuloExiste(codigoArticulo)) {
                // lanzar error si el articulo no existe
                throw new CustomException1("Este articulo no existe");
            } else {
                parametros.add(codigoArticulo);
            }
        } catch (CustomException1 ex) { // manejar la excepcion
            ex.printStackTrace();
            mostrarMenuPrincipal();
        }

        // recibir el email del cliente
        try {
            emailCliente = vista.printGetClientePedido();
            // comprobar si el cliente existe
            if (!modelo.clienteExiste(emailCliente)) {
                // lanzar error si el cliente no existe
                throw new CustomException2("Este cliente no existe");
            } else {
                parametros.add(emailCliente);
            }
        } catch (CustomException2 ex) {
            ex.printStackTrace();
            mostrarMenuPrincipal();
        }

        // recibir la cantidad
        cantidad = vista.printGetCantidadPedido();
        parametros.add(cantidad);


        // Llamar al modelo para crear el pedido
        pedidoCreado = modelo.crearDatosPedido(parametros);

        // informar a la vista si se ha creado el pedido
        vista.pedidoCreado(pedidoCreado);
    }


    // metodo para eliminar un pedido
    public void eliminarPedido() throws Exception {
        int numPedido;
        boolean pedidoEliminado;

        // recibir el nÃºmero del pedido a ser borrado
        numPedido = vista.printEliminarPedido();

        // llamar al modelo para eliminar el pedido
        pedidoEliminado = modelo.eliminarPedido(numPedido);

        // informar si el pedido se ha eliminado
        vista.pedidoEliminado(pedidoEliminado);

    }

    // metodo para mostrar los pedidos pendientes de envio
    public void mostrarPedidosPendientes() {
        List lista = new ArrayList<>();
        // llenar la lista con los pedidos pendientes
        lista = modelo.recibirDatosPedidosPendientes();
        // enviar la lista a vista
        vista.printMostrarPedidosPendientes(lista);
    }

    // metodo para mostrar los pedidos enviados
    public void mostrarPedidosEnviados() {
        List lista = new ArrayList<>();
        // llenar la lista con los pedidos enviados
        lista = modelo.recibirDatosPedidosEnviados();
        // enviar la lista a vista para ser impresa
        vista.printMostrarPedidosEnviados(lista);
    }

    // FIN GESTION DE PEDIDOS




}