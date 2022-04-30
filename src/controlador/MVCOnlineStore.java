package controlador;

import model.Datos;
import view.GestionOS;

public class MVCOnlineStore {
    static boolean exit = false;

    public static void main(String[] args) throws Exception {
        Datos modelo = new Datos();
        GestionOS vista = new GestionOS();
        Controlador controlador = new Controlador(modelo, vista);
        // Precarga de datos en falsa base de datos
//        controlador.cargarDatos();

        //arrancar el menu
        while(!exit) {
            controlador.mostrarMenuPrincipal();
        }
    }


    // Metodo para salir de la aplicacion
    public static void setExitTrue() {
        exit = true;
    }
    
    
    
}
