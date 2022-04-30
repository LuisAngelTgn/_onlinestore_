package model;


public class Cliente_Premium extends Cliente {
      // Constructor
    public Cliente_Premium() {}
    public Cliente_Premium(String nombre, String domicilio, String NIF, String email) {
        super(nombre, domicilio, NIF, email);
        super.setCuota(30);
        super.setDescuento(20);
    }



    @Override
    public boolean tipoDeCliente(String email) {
        // TODO
        return false;
    }

    @Override
    public double cuotaAnual() {
        return getCuota();
    }

    @Override
    public double descuentoGastosDeEnvio(double totalPedido) {
        return totalPedido - (totalPedido * 0.2);
    }

    @Override
    public String toString(){
        return "Datos del cliente: \n" +
                "Tipo de cliente: Premium" + "\n" +
                "Nombre: " + this.getNombre() + "\n" +
                "Domicilio: " + this.getDomicilio() + "\n" +
                "NIF: " + this.getNIF() + "\n" +
                "Email: " + this.getEmail() + "\n" +
                "Cuota: " + this.getCuota() + "\n" +
                "Descuento en el envio: " + this.getDescuento();
    }
}
