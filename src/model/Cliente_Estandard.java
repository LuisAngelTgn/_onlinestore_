package model;

public class Cliente_Estandard extends Cliente {

     // Contructor
    public Cliente_Estandard() {}
    public Cliente_Estandard(String nombre, String domicilio, String NIF, String email) {
        super(nombre, domicilio, NIF, email);
        super.setCuota(0);
        super.setDescuento(0);
    }



    public boolean tipoDeCliente(String email) {
        boolean encontrado = false;
        // retornar objeto return OnlineStore.getClientes().stream().filter(cliente -> email.equals(cliente.getEmail())).findFirst().orElse(null);
//        for (Cliente cliente : ListaClientes.getClientes()) {
//            if (cliente instanceof ClienteEstandard) {
//                if (cliente.getEmail().equals(email)) {
//                    encontrado = true;
//                }
//            }
//
//        }
        return encontrado;
    }


    public double cuotaAnual() {
        return 0;
    }


    public double descuentoGastosDeEnvio(double totalPedido) {
        return 0;
    }

    @Override
    public String toString(){
        return "Datos del cliente: \n" +
                "Tipo de cliente: Estandard" + "\n" +
                "Nombre: " + this.getNombre() + "\n" +
                "Domicilio: " + this.getDomicilio() + "\n" +
                "NIF: " + this.getNIF() + "\n" +
                "Email: " + this.getEmail() + "\n" +
                "Cuota: " + this.getCuota() + "\n" +
                "Descuento en el envio: " + this.getDescuento();
    }
}
