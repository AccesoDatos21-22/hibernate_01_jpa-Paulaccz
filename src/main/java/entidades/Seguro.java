package entidades;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

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

    public enum Sexo {HOMBRE, MUJER}

    ;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "SEXO")
    private Sexo sexo;

    public enum Casado {Y, N}

    ;

    @Enumerated(EnumType.STRING)
    @Column(name = "CASADO", length = 1)
    private Casado casado;

    @Column(name = "NUM_HIJOS", length = 11)
    private int numHijos;

    @Column(name = "FECHA_CREACION")
    private Timestamp fechaCreacion;

    public enum TipoSeguro {HOGAR, COCHE, MOTO, VIAJE}

    ;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIPO_SEGURO", nullable = false)
    private TipoSeguro tipoSeguro;

    @Formula("edad>=18")
    private boolean mayor_edad;

    @Column(name = "FECHA_NACIMIENTO")
    private LocalDate fecha_nac;

    @Column(name = "HORA_CONTACTO")
    private LocalTime hora_contacto;

    @Lob
    @Column(name = "CLAVE")
    private char[] clave;

    @Lob
    @Column(name = "COMENTARIOS")
    private String comentarios;

    public Seguro() {

    }

    public Seguro(int idSeguro, String nif, String nombre, String ape1, String ape2, int edad, Sexo sexo, Casado casado, int numHijos, Timestamp fechaCreacion, TipoSeguro tipoSeguro, LocalDate fecha_nac, LocalTime hora_contacto, char[] clave, String comentarios) {
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
        this.fecha_nac = fecha_nac;
        this.hora_contacto = hora_contacto;
        this.clave = clave;
        this.comentarios = comentarios;
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

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Casado getCasado() {
        return casado;
    }

    public void setCasado(Casado casado) {
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

    public TipoSeguro getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(TipoSeguro tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public LocalDate getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(LocalDate fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public LocalTime getHora_contacto() {
        return hora_contacto;
    }

    public void setHora_contacto(LocalTime hora_contacto) {
        this.hora_contacto = hora_contacto;
    }

    public char[] getClave() {
        return clave;
    }

    public void setClave(char[] clave) {
        this.clave = clave;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "\nDatos del seguro: " + "\nId: " + idSeguro + "\nNif: " + nif + "\nNombre: " + nombre +
                "\nApellido 1: " + ape1 + "\nApellido 2: " + ape2 + "\nEdad: " + edad + "\nN??mero de hijos: " + numHijos
                + "\nSexo: " + sexo /*(sexo == 0 ? "Hombre" : "Mujer")*/ + "\nCasad@: " + casado /*(casado == 's' || casado == 'S' ? "Si" : "No")*/ +
                "\nFecha de creaci??n: " + fechaCreacion + "\nTipo de seguro: " + tipoSeguro + "\nFecha de nacimiento: " + fecha_nac
                + "\nHora de contacto: " + hora_contacto + "\nClave: " + leerClave(clave);
    }

    private String leerClave(char[] clave) {
        String claves = "";

        for (char caracter : clave)
            claves += caracter + ",";

        return claves;
    }
}
