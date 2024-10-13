package Baloncesto.PatronDao;

import Baloncesto.Clasificacion;
import Baloncesto.Equipo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class ClasificacionFileDao implements Dao<Clasificacion, String>{
    private final String RUTA;
    private final Path datos;

    public ClasificacionFileDao(String ruta){
        this.RUTA = ruta;
        datos = Paths.get(RUTA);
    }

    @Override
    public Clasificacion get(String id) {
        HashSet<Clasificacion> set = new HashSet<>(getAll());
        for (Clasificacion c : set)
            if (c.equals(new Clasificacion(id)))
                return c;
        return null;
    }

    @Override
    public Set<Clasificacion> getAll() {
        TreeSet<Clasificacion> set = new TreeSet<>();
        TreeSet<Equipo> setEquipo = new TreeSet<>();
        try(var ois = new ObjectInputStream(new FileInputStream(RUTA))){
            setEquipo.add((Equipo) ois.readObject());
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException in getAll()");
        } catch (IOException e) {
            System.err.println("IOException in getAll()");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException in getAll()");
        }
        set.add(new Clasificacion(setEquipo));
        saveAll(set);
        return set;
    }

    @Override
    public boolean save(Clasificacion clasificacion) {
        EquipoDaoFactory equipoDAOFactory = EquipoDaoFactory.getInstance();
        Dao<Equipo, String> equipoDao = equipoDAOFactory.getEquipoDAO("OS", RUTA);
        //clasificacion.getEquipos().forEach(equipoDao::save);
        TreeSet<Equipo> set = clasificacion.getEquipos();
        for (Equipo e : set)
            equipoDao.save(e);
        return true;
    }

    @Override
    public boolean delete(Clasificacion clasificacion) {
        TreeSet<Clasificacion> set = new TreeSet<>(getAll());
        boolean removed = set.removeIf(c -> c.equals(clasificacion));
        if (removed)
            saveAll(set);
        return removed;
    }

    @Override
    public boolean deleteById(String id) {
        TreeSet<Clasificacion> set = new TreeSet<>(getAll());
        boolean removed = set.removeIf(c -> c.getCompeticion().equals(id));
        if (removed)
            saveAll(set);

        return removed;
    }

    @Override
    public boolean deleteAll() {
        try {
            Files.deleteIfExists(datos);
            if (Files.exists(datos))
                return false;
            Files.createFile(datos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void update(Clasificacion clasificacion) {
        TreeSet<Clasificacion> set = new TreeSet<>(getAll());
        set.removeIf(c -> c.equals(clasificacion));
        set.add(clasificacion);
        saveAll(set);
    }

    private boolean saveAll(TreeSet<Clasificacion> set) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RUTA))) {
            set.forEach(c -> {
                try {
                    oos.writeObject(c);
                } catch (IOException ex) {
                    System.err.println("IOException in Stream()");
                }
            });

        } catch (IOException e) {
            System.err.println("IOException in saveAll()");
        }
        return true;
    }
}
