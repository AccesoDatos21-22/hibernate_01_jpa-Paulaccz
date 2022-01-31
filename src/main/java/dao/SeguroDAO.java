package dao;

import entidades.Seguro;
import utils.Utilidades;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class SeguroDAO {

//    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Persistencia");

    private static EntityManager manager;
    private static EntityTransaction transaction;


    public static void init() {
        manager = Utilidades.getConexion();
        transaction = manager.getTransaction();
        transaction.begin();
    }

    public static void insert(Seguro seguroInsert) {

        init();

        if (!isExists(seguroInsert.getIdSeguro())) {
            manager.persist(seguroInsert);
        } else
            System.err.println("ERROR. Id duplicado. No se insertará el seguro con id " + seguroInsert.getIdSeguro());

        transaction.commit();

        close();
    }

    public static void update(int id, Seguro seguro) {

        init();

        if (isExists(id)) {
            Seguro seguroUpdate = manager.find(Seguro.class, id);

            seguroUpdate.setNif(seguro.getNif());
            seguroUpdate.setNombre(seguro.getNombre());
            seguroUpdate.setApe1(seguro.getApe1());
            seguroUpdate.setApe2(seguro.getApe2());
            seguroUpdate.setEdad(seguro.getEdad());
            seguroUpdate.setNumHijos(seguro.getNumHijos());
            seguroUpdate.setSexo(seguro.getSexo());
            seguroUpdate.setCasado(seguro.getCasado());
            seguroUpdate.setFechaCreacion(seguro.getFechaCreacion());
            seguroUpdate.setTipoSeguro(seguro.getTipoSeguro());
            seguroUpdate.setFecha_nac(seguro.getFecha_nac());
            seguroUpdate.setHora_contacto(seguro.getHora_contacto());

            transaction.commit();
        } else
            System.err.println("ERROR. No se puede modificar el seguro con el id " + id + ". No se encuentra en la base de datos");

        close();
    }

    public static void delete(int id) {

        init();

        //Comprobación de que existe el seguro llamando al método correspondiente
        if (isExists(id)) {
            Seguro segDelete = manager.find(Seguro.class, id);
            manager.remove(segDelete);
            transaction.commit();
        } else
            System.err.println("ERROR. No se puede eliminar el seguro con el id " + id + ". No se encuentra en la base de datos");

        close();
    }

    public static Seguro search(int id) {

        init();

        if (!isExists(id)) {
            System.err.println("ERROR. No se encuentra ningún seguro con el id " + id);
            close();
            return null;
        } else {
            Seguro seguroSearch = manager.find(Seguro.class, id);
            close();
            return seguroSearch;
        }
    }

    private static boolean isExists(int id) {

        Seguro segExist = manager.find(Seguro.class, id);

        if (segExist == null)
            return false;
        return true;
    }

    private static void close() {
        if (transaction.isActive())
            transaction.rollback();

        manager.close();
    }

}
