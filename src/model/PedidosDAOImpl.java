package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidosDAOImpl extends ConexionMysql implements PedidosDAO{
    // crear una instancia de la conexion a la base de datos de MySQL
    private final Connection con = ConexionMysql.getConexion();
    private final ClienteDAOImpl cliente = new ClienteDAOImpl();
    private final ArticuloDAOImpl articulo = new ArticuloDAOImpl();

    // Preparar las sentencias de mysql
    private final static String SQL_CREATE_PEDIDO = "INSERT INTO pedidos(email_cliente, codigo_articulo, cantidad, fecha, procesado) VALUES(?,?,?,?,?)";
    private final static String SQL_DELETE_PEDIDO = "DELETE FROM pedidos WHERE numero_pedido = ?";
    private final static String SQL_SELECT_PEDIDOS = "SELECT * FROM pedidos";
    private final static String SQL_SELECT_PEDIDOS_PENDIENTES = "SELECT * FROM pedidos WHERE procesado = 0";
    private final static String SQL_SELECT_PEDIDOS_ENVIADOS = "SELECT * FROM pedidos WHERE procesado = 1";
    private final static String SQL_ACTUALIZAR_PROCESADO = "UPDATE pedidos SET procesado = 1 WHERE numero_pedido = ?";

    @Override
    public Pedidos getPedido(int numeroPedido) {
        return null;
    }

    @Override
    public boolean addPedido(Pedidos pedido) {
        try {
            // preparar el statement
            PreparedStatement pstm = con.prepareStatement(SQL_CREATE_PEDIDO);
            // Dar los valores a los campos
            pstm.setObject(1, pedido.getCliente().getEmail());
            pstm.setString(2, pedido.getArticulo().getCodigoProducto());
            pstm.setInt(3, pedido.getCantidad());
            pstm.setTimestamp(4, pedido.getFecha());
            pstm.setBoolean(5, pedido.getProcesado());
            // Ejecutamos el prepared statement
            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Pedidos> getPedidos() {
        return getPedidos(SQL_SELECT_PEDIDOS);
    }

    private List<Pedidos> getPedidos(String sqlSelectPedidos) {
        List<Pedidos> lista = new ArrayList<>();
        try (PreparedStatement pstm = con.prepareStatement(sqlSelectPedidos)) {
            try(ResultSet rs = pstm.executeQuery()) {
                while(rs.next()) {
                    Pedidos pedido = new Pedidos();
                    pedido.setNumeroDePedido(rs.getInt(1));
                    pedido.setCliente(cliente.getClienteByEmail(rs.getString(2)));
                    pedido.setArticulo(articulo.getArticuloByID(rs.getString(3)));
                    pedido.setCantidad(rs.getInt(4));
                    pedido.setFecha(rs.getTimestamp(5));
                    pedido.setProcesado(rs.getBoolean(6));

                    lista.add(pedido);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    // comprueba la diferencia horaria entre el pedido y ahora y comprueba el tiempo de preparacion
    @Override
    public void actualizarPedidos() {
        // recibir todos los pedidos
        for (Pedidos pedido : getPedidos()) {

            if (!pedido.getProcesado()) {
                /* comprobar si el tiempo de preparacion se ha cumplido */
                Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
                Timestamp fechaPedido = pedido.getFecha();
                long diferencia = fechaActual.getTime() - fechaPedido.getTime();
                int seconds = (int) diferencia / 1000;
                int minutes = (seconds % 3600) / 60;
                if (minutes >= pedido.getArticulo().getTiempoDePreparacion()) {
                    try {
                        PreparedStatement pstm = con.prepareStatement(SQL_ACTUALIZAR_PROCESADO);
                        int numeroPedido = pedido.getNumeroDePedido();
                        pstm.setInt(1,numeroPedido);
                        pstm.executeUpdate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        }
    }


    @Override
    public boolean eliminarPedido(int numPedido) {
        boolean eliminado;
        try (PreparedStatement pstm = con.prepareStatement(SQL_DELETE_PEDIDO)) {
            pstm.setInt(1, numPedido);
            pstm.executeUpdate();
            eliminado = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            eliminado = false;
        }

        return eliminado;
    }

    @Override
    public List<Pedidos> getPedidosPendientes() {
        return getPedidos(SQL_SELECT_PEDIDOS_PENDIENTES);
    }

    @Override
    public List<Pedidos> getPedidosEnviados() {
        return getPedidos(SQL_SELECT_PEDIDOS_ENVIADOS);
    }
}
