package model;


import java.util.List;

public interface PedidosDAO {

    public Pedidos getPedido(int numeroPedido);

    public boolean addPedido(Pedidos pedido);

    public List<Pedidos> getPedidos();

    public void actualizarPedidos();

    public boolean eliminarPedido(int numPedido);

    public List getPedidosPendientes();

    public List getPedidosEnviados();

}
