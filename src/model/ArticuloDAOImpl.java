package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class ArticuloDAOImpl extends ConexionMysql implements ArticulosDAO {
    // instanciar la conexion con la base de datos
    private final Connection con = ConexionMysql.getConexion();
    private final static String SQL_CREATE_PRODUCT = "INSERT INTO articulos(codigo, descripcion, pvp, gastos_envio, tiempo_preparacion) VALUES (?, ?, ?, ?, ?)";
    private final static String SQL_GET_PRODUCT_ID = "SELECT * FROM articulos WHERE codigo = ?";
    private final static String SQL_GET_ALL_PRODUCTS = "SELECT * FROM articulos";
    @Override
    public boolean addArticulo(Articulos articulo) {
        try {
            // preparar el mysql statement
            PreparedStatement pstm = con.prepareStatement(SQL_CREATE_PRODUCT);
            // dar valores a los campos ?
            pstm.setString(1, articulo.getCodigoProducto());
            pstm.setString(2, articulo.getDescripcion());
            pstm.setDouble(3, articulo.getPvp());
            pstm.setDouble(4, articulo.getGastosDeEnvio());
            pstm.setInt(5, articulo.getTiempoDePreparacion());
            // Ejecutar statement
            pstm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Articulos> getArticulos() {
        // Declarar lista para guardar los articulos
        List<Articulos> list = new ArrayList<>();
        // preparar el mysql statement
        try (PreparedStatement psmt = con.prepareStatement(SQL_GET_ALL_PRODUCTS)) {
            try(ResultSet rs = psmt.executeQuery()) {
                // iniciar loop para todos los resultados
                while (rs.next()) {
                    Articulos articulo = new Articulos();
                    articulo.setCodigoProducto(rs.getString(1));
                    articulo.setDescripcion(rs.getString(2));
                    articulo.setPvp(rs.getDouble(3));
                    articulo.setGastosDeEnvio(rs.getDouble(4));
                    articulo.setTiempoDePreparacion(rs.getInt(5));

                    // Una vez adquiridos todos los campos, agregar la informacion a la lista
                    list.add(articulo);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        //
        return list;
    }

    @Override
    public Articulos getArticuloByID(String codArticulo) {
        Articulos articulo = new Articulos();
        try (PreparedStatement pstm= con.prepareStatement((SQL_GET_PRODUCT_ID))) {
            //Declaramos que el codigo esta en la primera posicion
            pstm.setString(1, codArticulo);
            try (ResultSet rs = pstm.executeQuery()) {
                // comprobar si la query devuelve algun resultado
                if (rs.next()) {
                    articulo.setCodigoProducto(rs.getString(1));
                    articulo.setDescripcion(rs.getString(2));
                    articulo.setPvp(rs.getDouble(3));
                    articulo.setGastosDeEnvio(rs.getDouble(4));
                    articulo.setTiempoDePreparacion(rs.getInt(5));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return articulo;
    }

    @Override
    public boolean articuloExiste(String codArticulo) {
        boolean existe = false;
        try (PreparedStatement pstm= con.prepareStatement((SQL_GET_PRODUCT_ID))) {
            //Declaramos que el codigo esta en la primera posicion
            pstm.setString(1, codArticulo);
            try (ResultSet rs = pstm.executeQuery()) {
                // comprobar si la query devuelve algun resultado
                if (rs.next()) {
                    existe = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return existe;
    }
}
