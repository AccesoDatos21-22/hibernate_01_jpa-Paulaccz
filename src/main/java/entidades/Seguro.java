package entidades;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "SEGURO", uniqueConstraints = {@UniqueConstraint(columnNames = {"ID_SEGURO"})})
public class Seguro implements Serializable {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SEGURO", nullable = false, length = 11)
    private int idSeguro;

    @Column(name = "NIF", nullable = false, unique = true, length = 9)
    private String nif;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APE1")
    private String ape1;

    @Column(name = "APE2")
    private String ape2;

    @Column(name = "EDAD", length = 11)
    private int edad;

    @Column(name = "SEXO", length = 11)
    private int sexo;

    @Column(name = "CASADO", length = 1)
    private char casado;

    @Column(name = "NUM_HIJOS", length = 11)
    private int numHijos;

    @Column(name = "FECHA_CREACION")
    private Timestamp fechaCreacion;

    @Column(name = "TIPO_SEGURO")
    private String tipoSeguro;

    @Formula("edad>=18")
    private boolean mayor_edad;

    public Seguro() {

    }

    public Seguro(int idSeguro, String nif, String nombre, String ape1, String ape2, int edad, int sexo, char casado, int numHijos, Timestamp fechaCreacion, String tipoSeguro) {
        this.idSeguro = idSeguro;
        this.nif = nif;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.edad = edad;
        this.sexo = sexo;
        this.casado = casado;
        this.numHijos = numHijos;
        this.fechaCreacion = fechaCreacion;
        this.tipoSeguro = tipoSeguro;
    }

    public boolean isMayor_edad() {
        return mayor_edad;
    }

    public void setMayor_edad(boolean mayor_edad) {
        this.mayor_edad = mayor_edad;
    }

    public int getIdSeguro() {
        return idSeguro;
    }

    public void setIdSeguro(int idSeguro) {
        this.idSeguro = idSeguro;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public char getCasado() {
        return casado;
    }

    public void setCasado(char casado) {
        this.casado = casado;
    }

    public int getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(int numHijos) {
        this.numHijos = numHijos;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(String tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    @Override
    public String toString() {
        return "\nDatos del seguro: " + "\nId: " + idSeguro + "\nNif: " + nif + "\nNombre: " + nombre +
                "\nApellido 1: " + ape1 + "\nApellido 2: " + ape2 + "\nEdad: " + edad + "\nNúmero de hijos: " + numHijos
                + "\nSexo: " + (sexo == 0 ? "Hombre" : "Mujer") + "\nCasad@: " + (casado == 's' || casado == 'S' ? "Si" : "No") +
                "\nFecha de creación: " + fechaCreacion + "\nTipo de seguro: " + tipoSeguro;
    }
}
