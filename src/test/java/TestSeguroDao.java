import dao.SeguroDAO;
import entidades.Seguro;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class TestSeguroDao {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("Persistencia");

    private static EntityManager manager;
    private static EntityTransaction transaction;

    @Test
    void init() {
        assertDoesNotThrow(() -> {
            manager = factory.createEntityManager();
            transaction = manager.getTransaction();
            transaction.begin();
        });
    }

    @Test
    void insert() {
        assertDoesNotThrow(() -> {
            Seguro seguroIn = new Seguro(1, "89652565A", "María", "Costa", "Costa", 35, 1, 'N', 0, Timestamp.valueOf("2013-04-22 19:05:12"), "Ccohe");

            SeguroDAO.insert(seguroIn);
            Seguro s = SeguroDAO.search(1);

            assertEquals(s.toString(), SeguroDAO.search(seguroIn.getIdSeguro()).toString());
        });
    }

    @Test
    void read() {
        assertDoesNotThrow(() -> {

            Seguro segInsert = new Seguro(2, "51413256B", "Pablo", "Fernández", "Fernández", 45, 0, 's', 2, Timestamp.valueOf("2013-04-25 19:05:45"), "Hogar");

            SeguroDAO.insert(segInsert);
            Seguro s = SeguroDAO.search(2);

            //Prueba para seguro que existe
            Seguro segSearch = SeguroDAO.search(segInsert.getIdSeguro());
            assertEquals(s.toString(), segSearch.toString());

            //Prueba para seguro inexistente
            Seguro segNoInsert = new Seguro(365, "51413256C", "Pepe", "Martínez", "Martínez", 58, 0, 'n', 2, Timestamp.valueOf("2013-04-25 19:20:45"), "Coche");
            assertNull(SeguroDAO.search(segNoInsert.getIdSeguro()));

        });
    }

    @Test
    void update() {
        assertDoesNotThrow(() -> {

            //Se inserta seguro para posteriormente modificarlo y compararlo
            Seguro segInsert = new Seguro(4, "65984589D", "Ana", "López", "López", 25, 1, 'n', 0, Timestamp.valueOf("2013-04-22 19:05:13"), "Coche");
            SeguroDAO.insert(segInsert);

            Seguro seguroUpdate = new Seguro(4, "65984589D", "Jose", "Pérez", "Pérez", 45, 0, 'S', 2, Timestamp.valueOf("2013-04-22 19:05:13"), "Hogar");

            SeguroDAO.update(4, seguroUpdate);
            Seguro s = SeguroDAO.search(4);

            assertEquals(s.toString(), SeguroDAO.search(4).toString());


            //Prueba para actualizar seguro inexistente
            SeguroDAO.update(258, seguroUpdate);
            assertNull(SeguroDAO.search(258));
        });
    }

    @Test
    void delete() {
        assertDoesNotThrow(() -> {

            //Se inserta seguro para posteriormente eliminar y comprobar que se elimina correctamente
            Seguro seguroDelete = new Seguro(5, "65984589E", "Pepe", "Soriano", "Soriano", 45, 0, 'S', 2, Timestamp.valueOf("2013-04-22 19:05:13"), "Hogar");
            SeguroDAO.insert(seguroDelete);

            //Prueba para comprobar que se elimina seguro existente
            SeguroDAO.delete(seguroDelete.getIdSeguro());
            assertNull(SeguroDAO.search(5));

            //Prueba para comprobar que no salta error al eliminar seguro inexistente
            SeguroDAO.delete(5689);
            assertNull(SeguroDAO.search(5689));
        });
    }

    @Test
    void mayor_edad() {
        assertDoesNotThrow(() -> {

            //Creamos seguros, uno mayor de edad y otro no
            Seguro seguroMayorEdad = new Seguro(6, "65975539F", "Laura", "Pascual", "Pascual", 45, 1, 'S', 2, Timestamp.valueOf("2013-04-22 19:05:13"), "Hogar");
            Seguro seguroMenorEdad = new Seguro(7, "65975539G", "Mario", "Gutierrez", "Gutierrez", 17, 0, 'N', 0, Timestamp.valueOf("2013-04-22 19:05:13"), "Coche");

            SeguroDAO.insert(seguroMayorEdad);
            SeguroDAO.insert(seguroMenorEdad);

            //Comprobación si detecta correctamente que es mayor de edad
            Seguro segBuscarMayor = SeguroDAO.search(seguroMayorEdad.getIdSeguro());

            assertTrue(segBuscarMayor.isMayor_edad());


            //Comprobación si detecta correctamente que es menor de edad
            Seguro segBuscarMenor = SeguroDAO.search(seguroMayorEdad.getIdSeguro());

            assertTrue(segBuscarMenor.isMayor_edad());
        });
    }

}
