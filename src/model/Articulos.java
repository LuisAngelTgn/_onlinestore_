package model;


public class Articulos{
   private String codigoProducto;
    private String descripcion;
    private double pvp;
    private double gastosDeEnvio;
    private int tiempoDePreparacion;

    // constructores
    public Articulos(){};
    public Articulos(String codigoProducto, String descripcion, double pvp, double gastosDeEnvio, int tiempoDePreparacion) {
        this.codigoProducto = codigoProducto;
        this.descripcion = descripcion;
        this.pvp = pvp;
        this.gastosDeEnvio = gastosDeEnvio;
        this.tiempoDePreparacion = tiempoDePreparacion;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

//     gastos de envio no tendrian que ser en pedido?
    public double getGastosDeEnvio() {
        return gastosDeEnvio;
    }

    public void setGastosDeEnvio(double gastosDeEnvio) {
        this.gastosDeEnvio = gastosDeEnvio;
    }

    public int getTiempoDePreparacion() {
        return tiempoDePreparacion;
    }

    public void setTiempoDePreparacion(int tiempoDePreparacion) {
        this.tiempoDePreparacion = tiempoDePreparacion;
    }

    @Override
    public String toString() {
      return "Codigo del producto:" + this.codigoProducto + "\n" +
              "Descripcion: " + this.descripcion + "\n" +
              "PVP: " + this.pvp + "\n" +
              "Gastos de envio: " + this.gastosDeEnvio + "\n" +
              "Tiempo de preparacion: " + this.tiempoDePreparacion + "\n";
    }
}
