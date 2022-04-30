
package view;



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Articulos;

public class GestionOS {
    // constructor
    public GestionOS() {
    }

    // Vista menu
    public int printMenu() {
        System.out.println("\nSeleccione el metodo: ");
        System.out.println("---------------------");
        System.out.println("1. Gestion de articulos");
        System.out.println("2. Gestion de clientes");
        System.out.println("3. Gestion de pedidos");
        System.out.println("Para salir pulse 0");
        return getInput(3);
    }

    // Sub menu para la gestion de los articulos
    public int printGestionArticulos() {
        System.out.println("\nSeleccione el metodo: ");
        System.out.println("---------------------");
        System.out.println("0. Volver al menu principal");
        System.out.println("1. Agregar articulo");
        System.out.println("2. Mostrar articulos");
        return getInput(2);
    }

    public List<Object> printAgregarArticulo() {
        String codigo, descripcion;
        double pvp, envio;
        int tiempo;
        List<Object> parametros = new ArrayList<>();
        // pedir informacion del artiulo y guardarlo en variables locales
        System.out.println("Introduzca el codigo del producto");
        codigo = getString();
        parametros.add(codigo);
        System.out.println("Introduzca la descripcion del articulo:");
        descripcion = getString();
        parametros.add(descripcion);
        System.out.println("Introduzca el precio de venta:");
        pvp = getDouble();
        parametros.add(pvp);
        System.out.println("Introduzca los gastos de envio:");
        envio = getDouble();
        parametros.add(envio);
        System.out.println("Introduzca el tiempo de preparacion en minutos:");
        tiempo = getInt();
        parametros.add(tiempo);

        // Devolver al controlador los parametros para crear el articulo
        return parametros;
    }
    public void articuloCreado(Boolean creado) {
        if (creado) {
            System.out.println("Se ha creado el articulo");
        } else {
           System.err.println("Ha habido un error al crear el articulo");
        }
    }

    public void printMostrarArticulos(List<Articulos> lista) {
        System.out.println("Lista de articulos");
        System.out.println("---------------------");

        for (Object o : lista) {
            System.out.println(o);
            System.out.println("---------------------");
        }
    }

    // Sub menu para la gestion de los clientes
    public int printGesionClientes() {
        System.out.println("\nSeleccione el metodo: ");
        System.out.println("---------------------");
        System.out.println("0. Volver al menu principal");
        System.out.println("1. Agregar cliente");
        System.out.println("2. Mostrar clientes");
        System.out.println("3. Mostrar clientes Estandard");
        System.out.println("4. Mostrar clientes Premium");
        return getInput(4);
    }

    // Sub menu para agregar un cliente
    public List printAgregarCliente() {
        int tipoDeCliente;
        String nombre, domicilio, nif, email;
        List<Object> parametros = new ArrayList<>();


        System.out.println("Introduzca que tipo  de cliente quiere crear");
        System.out.println("1. Cliente estandard");
        System.out.println("2. Cliente premium");
        tipoDeCliente = getInput(2);
        parametros.add(tipoDeCliente);

        System.out.println("Introduzca el email");
        email = getString();
        System.out.println("Introduzca el nombre");
        nombre = getString();
        parametros.add(nombre);
        System.out.println("Introduzca el domicilio");
        domicilio = getString();
        parametros.add(domicilio);
        System.out.println("Introduzca el nif");
        nif = getString();
        parametros.add(nif);
        parametros.add(email);

        // devolver la lista de parametros para crear el cliente
        return parametros;
    }

    public void clienteCreado(Boolean creado) {
        if (creado) {
            System.out.println("Se ha creado el cliente");
        } else {
            System.err.println("Este cliente ya existe");
        }
    }

    // Sub menu para mostrar los clientes existente
    public void printMostrarClientes(List lista) {
        System.out.println("Lista de clientes");
        System.out.println("---------------------");

        for (Object o : lista) {
            System.out.println(o);
            System.out.println("---------------------");
        }
    }

    // Sub menu para mostrar los clientes estandard
    public void printMostrarClientesEstandard(List lista) {
        System.out.println("Lista de clientes Estandard");
        System.out.println("---------------------");

        for (Object o : lista) {
            System.out.println(o);
            System.out.println("---------------------");
        }
    }

    // Sub menu para mostrar los clientes premium
    public void printMostrarClientesPremium(List lista) {
        System.out.println("Lista de clientes Premium");
        System.out.println("---------------------");

        for (Object o : lista) {
            System.out.println(o);
            System.out.println("---------------------");
        }
    }

    // Sub menu para la gestion de los pedidos
    public static int printGesionPedidos() {
        System.out.println("\nSeleccione el metodo: ");
        System.out.println("---------------------");
        System.out.println("0. Volver al menu principal");
        System.out.println("1. Agregar pedido");
        System.out.println("2. Eliminar pedido");
        System.out.println("3. Mostrar pedidos pendientes de envio");
        System.out.println("4. Mostrar pedidos enviados");
        return getInput(4);
    }

    // Sub menu para agregar un pedido
    public String printAgregarPedido() {
        String codArticulo;
        System.out.println("\nIntroduzca la informacion del pedido: ");
        System.out.println("--------------------------------------");
        // pedir por el input mientras el codigo este vacio
        do {
            System.out.println("Introduzca el articulo");
            codArticulo = getString();
        } while (codArticulo.isEmpty());

        return codArticulo;
    }

    // recibir el cliente para el pedido
    public String printGetClientePedido() {
        String email;
        do {
            System.out.println("Introduzca el email del cliente");
            email = getString();
        } while (email.isEmpty());

        return email;
    }

    // recibir la cantidad para el pedido
    public int printGetCantidadPedido() {
        int cantidad;
        do {
            System.out.println("Introduzca la cantidad de articulos");
            cantidad = getInt();
        } while (cantidad == 0);
        return cantidad;
    }

    public void pedidoCreado(Boolean creado) {
        if (creado) {
            System.out.println("Se ha creado el pedido");
        } else {
            System.err.println("Ha habido un error al crear el pedido");
        }
    }


    // Sub menu para eliminar un pedido
    public int printEliminarPedido() {
        int numPedido;
        // Pedir el numero del pedido
        System.out.println("Introduzca el numero de pedido a borrar");
        numPedido = getInt();

        return numPedido;
    }

    public void pedidoEliminado(boolean eliminado) {
        if (eliminado) {
            System.out.println("se ha eliminado el pedido exitosamente");
        } else {
            System.err.println("Ha ocurrido un error al eliminar el pedido");
        }
    }

    // Sub menu para mostrar pedidos por enviar
    public void printMostrarPedidosPendientes(List lista) {
        System.out.println("Mostrando pedidos pendientes de envio");
        System.out.println("-------------------------------------");
        for (Object o : lista) {
            System.out.println(o);
            System.out.println("---------------------");
        }
    }

    // Sub menu para mostrar los pedidos enviados
    public void printMostrarPedidosEnviados(List lista) {
        System.out.println("Mostrando pedidos enviados");
        System.out.println("-------------------------------------");
        for (Object o : lista) {
            System.out.println(o);
            System.out.println("---------------------");
        }
    }

    // Metodo para recibir el input del usuario para el menu
    public static int getInput(int max){
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while(choice < 0 || choice > max){
            try {
                System.out.print("\nIntroduzca la opcion: \n");
                // recibir input en forma de String y parse a int
                choice = Integer.parseInt(scanner.nextLine());
            }
            catch(NumberFormatException e){
                System.err.println("Introduzca un numero valido\n");
            }
        }
        return choice;
    }

    // metodo para recibir un input en forma de string
    public static String getString() {
        Scanner scanner = new Scanner(System.in);
        // try catch error
        return scanner.nextLine();
    }

    // metodo para recibir un input en forma de int
    public static int getInt() {
        Scanner scanner = new Scanner(System.in);
        int numero = 0;
        try {
            numero = Integer.parseInt(scanner.nextLine());
        } catch (Exception e){
            e.printStackTrace();
        }
        return numero;
    }

    // metodo para recibir un double
    public static double getDouble() {
        Scanner scanner = new Scanner(System.in);
        double numero = 0;
        try {
        numero = Double.parseDouble(scanner.nextLine());
        } catch (Exception e){
            e.printStackTrace();
    }
        return numero;
    }
}
