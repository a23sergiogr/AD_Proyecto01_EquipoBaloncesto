package Baloncesto;

import java.io.Serializable;
import java.util.Objects;

public class Equipo implements Serializable, Comparable<Equipo> {
    private String nombre;
    private Integer victorias;
    private Integer derrotas;
    private Integer ptnFavor;
    private Integer ptnContra;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.victorias = 0;
        this.derrotas = 0;
        this.ptnFavor = 0;
        this.ptnContra = 0;
    }

    public Equipo(String nombre, Integer victorias, Integer derrotas, Integer ptnFavor, Integer ptnContra) {
        this.nombre = nombre;
        this.victorias = victorias;
        this.derrotas = derrotas;
        this.ptnFavor = ptnFavor;
        this.ptnContra = ptnContra;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getVictorias() {
        return victorias;
    }

    public Integer getDerrotas() {
        return derrotas;
    }

    public Integer getPtnFavor() {
        return ptnFavor;
    }

    public Integer getPtnContra() {
        return ptnContra;
    }

    /**
     * @return Puntos
     */
    public Integer getPuntos() {
        return ptnFavor + ptnContra;
    }

    /**
     * @return Partidos jugados
     */
    public Integer getpartidosJugados() {
        return victorias + derrotas;
    }

    /**
     * @return Diferencia de puntos
     */
    public Integer getdifPtn() {
        return ptnFavor - ptnContra;
    }


    /**
     * @param eq the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Equipo eq) {
        if (this.getdifPtn() > eq.getdifPtn())
            return -2;
        else if (this.getdifPtn().equals(eq.getdifPtn())) {
            if (this.ptnFavor >= eq.ptnFavor)
                return -1;
            return 0;
        }
        return 1;
    }

    /**
     * @return super.hashCode()
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase());
    }

    /**
     * @param obj the object to be compared
     * @return true if the name is the same, falso if else
     */
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (!(obj instanceof Equipo equipo)) return false;
        return this.nombre.equalsIgnoreCase(((Equipo) obj).nombre);
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", victorias=" + victorias +
                ", derrotas=" + derrotas +
                ", ptnFavor=" + ptnFavor +
                ", ptnContra=" + ptnContra +
                ", Puntos=" + getdifPtn() +
                '}';
    }
}