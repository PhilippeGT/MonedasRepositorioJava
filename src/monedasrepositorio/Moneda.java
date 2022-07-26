package monedasrepositorio;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Moneda {

    //Variable privada
    private int id;
    private String moneda;
    private String sigla;
    private String simbolo;
    private String emisor;

    //Metodo constructor qie inicializara los campos obligatorios.
    public Moneda(int id,
            String moneda,
            String sigla,
            String simbolo,
            String emisor
    ) {

        this.id = id;
        this.moneda = moneda;
        this.sigla = sigla;
        this.simbolo = simbolo;
        this.emisor = emisor;
    }

    //Seter
    public int getId() {
        return id;
    }

    //Metodo para llevar Datos(setID ---seter
    public void setId(int id) {
        this.id = id;
    }

    public String getMoneda() {
        return moneda;
    }

    //Metodo para llevar Datos(setID ---seter
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getSigla() {
        return sigla;
    }

    //Metodo para llevar Datos(setID ---seter
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getSimbolo() {
        return simbolo;
    }

    //Metodo para llevar Datos(setID ---seter
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getEmisor() {
        return emisor;
    }

    //Metodo para llevar Datos(setID ---seter
    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

//**********Metodos Estaticos
//Metodo que lista todas las monedas (Read - CRUD)
    public static List<Moneda> obtener() throws Exception {
        List<Moneda> monedas = new ArrayList<>();
        try {
            BaseDatos bd = ConexionBD.obtenerBaseDatos();
            if (bd != null) {
                ResultSet rs = bd.consultar("SELECT * FROM Moneda ORDER BY Moneda");
                if (rs != null) {
                    rs.beforeFirst();
                    while (rs.next()) {
                        Moneda n = new Moneda(Util.leerEntero(rs, "Id"),
                                Util.leerTexto(rs, "Moneda"),
                                Util.leerTexto(rs, "Sigla"),
                                Util.leerTexto(rs, "Simbolo"),
                                Util.leerTexto(rs, "Emisor")
                        );
                        monedas.add(n);
                    }
                }
            } else {
                throw new Exception("No se ha conectado a la Base de Datos");
            }
        } catch (Exception ex) {
            throw new Exception("Error al listar monedas: \n [** " + ex + " **]");
        }
        return monedas;
    }

    //Metodo que devuelve un objeto moneda con base en la clase primaria
    public static Moneda obtener(int id) throws Exception {
        try {
            BaseDatos bd = ConexionBD.obtenerBaseDatos();
            if (bd != null) {
                ResultSet rs = bd.consultar("SELECT * FROM Moneda WHERE Id=" + id);
                if (rs != null) {
                    rs.beforeFirst();
                   rs.next();
                        return new Moneda(Util.leerEntero(rs, "Id"),
                                Util.leerTexto(rs, "Moneda"),
                                Util.leerTexto(rs, "Sigla"),
                                Util.leerTexto(rs, "Simbolo"),
                                Util.leerTexto(rs, "Emisor")
                        );
                }
            } else {
                throw new Exception("No se ha conectado a la Base de Datos");
            }
        } catch (Exception ex) {
            throw new Exception("Error al obtener una moneda: \n [** " + ex + " **]");
        }
        return null;

    }

}
