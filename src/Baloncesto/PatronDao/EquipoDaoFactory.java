package Baloncesto.PatronDao;

import Baloncesto.Equipo;

import java.io.IOException;


public class EquipoDaoFactory {
    private static EquipoDaoFactory instancia;
    private Dao<Equipo, String> dao;

    private EquipoDaoFactory() {
    }

    //Sigelton
    public static synchronized EquipoDaoFactory getInstance() {
        if (instancia == null) {
            instancia = new EquipoDaoFactory();
        }
        return instancia;
    }

    public Dao<Equipo, String> getEquipoDAO(String tipo) {
        try {
            switch (tipo) {
                case "FBS":
                    dao = new EquipoFileBufferedStream();
                    break;
                case "OS":
                    dao = new EquipoObjectStreamDao();
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de DAO no soportado");

            }
        } catch (IOException e) {
            System.err.println("No se pudo crear el Archivo");
            System.exit(1);
        }
        return dao;
    }

    public Dao<Equipo, String> getEquipoDAO(String tipo, String ruta) {
        try {
            switch (tipo) {
                case "FBS":
                    dao = new EquipoFileBufferedStream(ruta);
                    break;
                case "OS":
                    dao = new EquipoObjectStreamDao(ruta);
                    break;
                default:
                    throw new IllegalArgumentException("Tipo de DAO no soportado");
            }
        } catch (IOException e) {
            System.err.println("No se pudo crear el Archivo");
            System.exit(1);
        }
        return dao;
    }
}