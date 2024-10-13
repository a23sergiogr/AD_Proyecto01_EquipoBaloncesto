package Baloncesto;

import Baloncesto.PatronDao.ClasificacionFileDao;
import Baloncesto.PatronDao.Dao;
import Baloncesto.PatronDao.EquipoDaoFactory;

public class Ej6_Xestion_de_Equipo_de_Baloncesto {
    public static void main(String[] args) {
         EquipoDaoFactory equipoDAOFactory = EquipoDaoFactory.getInstance();
         Dao<Equipo, String> equipoDao = equipoDAOFactory.getEquipoDAO("OS");//FBS, OS

        Clasificacion clasificacion = new Clasificacion(equipoDao.getAll());
        ClasificacionFileDao clasificacionFileDao = new ClasificacionFileDao("src/Baloncesto/Datos/clasificación.dat");

        System.out.println(clasificacion);
        clasificacionFileDao.save(clasificacion);
        System.out.println(clasificacionFileDao.get(clasificacion.getCompeticion()));


        //String nombre, Integer victorias, Integer derrotas, Integer ptnFavor, Integer ptnContra
//        equipoDao.save(new Equipo("Eq1", 1, 0, 1, 0));
//        equipoDao.save(new Equipo("Eq2", 0, 1, 0, 1));
//        equipoDao.save(new Equipo("Eq3", 2, 1, 2, 1));
//        equipoDao.save(new Equipo("Eq4", 5, 0, 5, 0));
//        equipoDao.save(new Equipo("Eq5", 0, 5, 0, 5));
//
//        equipoDao.getAll().forEach(System.out::println);
//
//        System.out.println(equipoDao.delete(new Equipo("Eq1")));
//        System.out.println(equipoDao.deleteById("Eq2"));
//        equipoDao.getAll().forEach(System.out::println);
//
//        System.out.println(equipoDao.deleteAll());
//        equipoDao.getAll().forEach(System.out::println);

    }
}

