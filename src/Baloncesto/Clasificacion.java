package Baloncesto;

import java.io.Serializable;
import java.util.*;

public class Clasificacion implements Serializable {
    private final TreeSet<Equipo> clasificacion;
    private String competicion = "ACB";

    public Clasificacion() {
        clasificacion = new TreeSet<>();
    }

    public Clasificacion(String competicion) {
        this.competicion = competicion;
        clasificacion = new TreeSet<>();
    }

    public Clasificacion(Set<Equipo> equipos) {
        clasificacion = new TreeSet<>(equipos);
    }

    public String getCompeticion() {
        return competicion;
    }

    public void setCompeticion(String competicion) {
        this.competicion = competicion;
    }

    public boolean addEquipo(Equipo equipo) {
        return clasificacion.add(equipo);
    }

    public boolean removeEquipo(Equipo equipo){
        return clasificacion.remove(equipo);
    }

    public TreeSet<Equipo> getEquipos() {
        return clasificacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clasificacion that = (Clasificacion) o;
        return Objects.equals(competicion, that.competicion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(competicion);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de Equipos:\n");
        for (Equipo e : clasificacion)
            sb.append("\n\t").append(e.toString());
        return sb.toString();
    }
}
