package model;


public abstract class Cliente {

    private String email;
    private String nombre;
    private String domicilio;
    private String NIF;
    private int cuota;
    private int descuento;

    // Constructor
    public Cliente() {}

    public Cliente(String nombre, String domicilio, String NIF, String email) {
            this.nombre = nombre;
            this.domicilio = domicilio;
            this.NIF = NIF;
            this.email = email;
    }


    public String getNombre() { return nombre;}

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }

    public String getNIF() { return NIF; }
    public void setNIF(String NIF) { this.NIF = NIF; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getCuota() {
        return cuota;
    }
    public void setCuota(int cuota) {
        this.cuota = cuota;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public abstract boolean tipoDeCliente (String email); //  implemented in override in childs

    public abstract double cuotaAnual(); //  implemented in override in childs

    public abstract double descuentoGastosDeEnvio(double totalPedido); // implemented in override in childs


    @Override
    public String toString() {
        return "\"Datos del cliente: \\n\" + \n" +
                "Tipo de cliente: Premium\" + \"\\n\" +\n" +
                "Nombre: \" + this.getNombre() + \"\\n\" +\n" +
                "Domicilio: \" + this.getDomicilio() + \"\\n\" +\n" +
                "NIF: \" + this.getNIF() + \"\\n\" +   \n" +
                "Email: \" + this.getEmail() + \"\\n\" +\n" +
                "Cuota: " + this.cuota + "\n" +
                "Descuento en el envio: " + this.descuento;
    }

}
