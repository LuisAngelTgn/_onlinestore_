/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImpl extends ConexionMysql implements ClienteDAO {
    // instanciar una conexion con la base de datos
    private final Connection con = ConexionMysql.getConexion();

    // Generar las sentencias sql
    private final static String SQL_CREATE_CLIENTE = "INSERT INTO clientes(email, nombre, domicilio, nif, cuota, descuento) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String SQL_GET_CLIENTE = "SELECT * FROM clientes WHERE email = ?";
    private final static String SQL_GET_CLIENTES = "SELECT * FROM clientes";
    @Override
    public List<Cliente> getClientes() {
        // declaramos una nueva lista
        Cliente cliente;
        List<Cliente> list = new ArrayList<>();
        // preparamos el sql statement
        try(PreparedStatement pstm = con.prepareStatement(SQL_GET_CLIENTES);
            ResultSet rs = pstm.executeQuery()){
            // siempre que hayan mas columnas
            while(rs.next()) {
                // comprobar si es cliente premium
                int setCuota = rs.getInt(5);
                if(setCuota > 0) {
                    Cliente clientePremium = new Cliente_Premium();

                    clientePremium.setEmail(rs.getString(1));
                    clientePremium.setNombre(rs.getString(2));
                    clientePremium.setDomicilio(rs.getString(3));
                    clientePremium.setNIF(rs.getString(4));
                    clientePremium.setCuota(rs.getInt(5));
                    clientePremium.setDescuento(rs.getInt(6));

                    cliente = clientePremium;
                    list.add(cliente);
                } else {
                    Cliente clienteEstandard = new Cliente_Estandard();

                    clienteEstandard.setEmail(rs.getString(1));
                    clienteEstandard.setNombre(rs.getString(2));
                    clienteEstandard.setDomicilio(rs.getString(3));
                    clienteEstandard.setNIF(rs.getString(4));
                    clienteEstandard.setCuota(rs.getInt(5));
                    clienteEstandard.setDescuento(rs.getInt(6));

                    cliente = clienteEstandard;
                    list.add(cliente);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public Cliente getClienteByEmail(String email) {
        Cliente cliente = null;
        try (PreparedStatement pstm = con.prepareStatement(SQL_GET_CLIENTE)){
            // declaramos que el email esta en la posicion 1
            pstm.setString(1, email);
            // intentamos lanzar la query
            try (ResultSet rs = pstm.executeQuery()){
                // si el resultado tiene una fila existe
                if (rs.next()) {
                    // comprobar si es cliente premium
                    if(rs.getInt(5) > 0) {
                        Cliente clientePremium = new Cliente_Premium();

                        clientePremium.setEmail(rs.getString(1));
                        clientePremium.setNombre(rs.getString(2));
                        clientePremium.setDomicilio(rs.getString(3));
                        clientePremium.setNIF(rs.getString(4));
                        clientePremium.setCuota(rs.getInt(5));
                        clientePremium.setDescuento(rs.getInt(6));

                        cliente = clientePremium;
                    } else {
                        Cliente clienteEstandard = new Cliente_Estandard();

                        clienteEstandard.setEmail(rs.getString(1));
                        clienteEstandard.setNombre(rs.getString(2));
                        clienteEstandard.setDomicilio(rs.getString(3));
                        clienteEstandard.setNIF(rs.getString(4));
                        clienteEstandard.setCuota(rs.getInt(5));
                        clienteEstandard.setDescuento(rs.getInt(6));

                        cliente = clienteEstandard;
                    }

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cliente;
    }

    @Override
    public boolean addCliente(Cliente cliente) {
        try {
            // preparar el mysql statement
            PreparedStatement pstm = con.prepareStatement(SQL_CREATE_CLIENTE);
            // dar valores a los campos ?
            pstm.setString(1, cliente.getEmail());
            pstm.setString(2, cliente.getNombre());
            pstm.setString(3, cliente.getDomicilio());
            pstm.setString(4, cliente.getNIF());
            pstm.setDouble(5, cliente.getDescuento());
            pstm.setDouble(6, cliente.getCuota());
            // Ejecutamos statement
            pstm.executeUpdate();
            return true;

        } catch (SQLException ex) { //  si ocurre una excepcion
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean clienteExiste(String email) {
        boolean existe = false;

        try (PreparedStatement pstm = con.prepareStatement(SQL_GET_CLIENTE)){
            // declaramos que el email esta en la posicion 1
            pstm.setString(1, email);
            // intentamos lanzar la query
            try (ResultSet rs = pstm.executeQuery()){
                // si el resultado tiene una fila existe
                if (rs.next()) {
                    existe = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return existe;
    }

    @Override
    public List<Cliente> listarClientesEstandard() {
    // declaramos una nueva lista
        Cliente cliente;
        List<Cliente> list = new ArrayList<>();
        // preparamos el sql statement
        try(PreparedStatement pstm = con.prepareStatement(SQL_GET_CLIENTES);
            ResultSet rs = pstm.executeQuery()){
            // siempre que hayan mas columnas
            while(rs.next()) {
            // comprobar si es cliente Estandard
            int getCuota = rs.getInt(5);
                if(getCuota == 0) {
                    Cliente cliente_Estandard = new Cliente_Estandard();

                   cliente_Estandard.setEmail(rs.getString(1));
                    cliente_Estandard.setNombre(rs.getString(2));
                    cliente_Estandard.setDomicilio(rs.getString(3));
                    cliente_Estandard.setNIF(rs.getString(4));
                    cliente_Estandard.setCuota(rs.getInt(5));
                    cliente_Estandard.setDescuento(rs.getInt(6));

                    cliente = cliente_Estandard;
                    list.add(cliente);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }


    @Override
    public List<Cliente> listarClientesPremium() {
        // declaramos una nueva lista
        Cliente cliente;
        List<Cliente> list = new ArrayList<>();
        // preparamos el sql statement
        try(PreparedStatement pstm = con.prepareStatement(SQL_GET_CLIENTES);
            ResultSet rs = pstm.executeQuery()){
            // siempre que hayan mas columnas
            while(rs.next()) {
                // comprobar si es cliente premium
                int setCuota = rs.getInt(5);
                if(setCuota > 0) {
                    Cliente cliente_Premium = new Cliente_Premium();

                    cliente_Premium.setEmail(rs.getString(1));
                    cliente_Premium.setNombre(rs.getString(2));
                    cliente_Premium.setDomicilio(rs.getString(3));
                    cliente_Premium.setNIF(rs.getString(4));
                    cliente_Premium.setCuota(rs.getInt(5));
                    cliente_Premium.setDescuento(rs.getInt(6));

                    cliente = cliente_Premium;
                    list.add(cliente);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

}

