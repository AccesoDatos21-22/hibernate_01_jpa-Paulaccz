import dao.SeguroDAO;
import entidades.Seguro;

import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

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
            char[] clave = {'c', 'l', 'a', 'v', 'e', '1'};

            Seguro seguroIn = new Seguro(1, "89652565A", "María", "Costa", "Costa", 35, Seguro.Sexo.MUJER, Seguro.Casado.N, 0, Timestamp.valueOf("2013-04-22 19:05:12"), Seguro.TipoSeguro.MOTO, LocalDate.of(1985, 7, 25), LocalTime.of(12, 30), clave);

            SeguroDAO.insert(seguroIn);
//            Seguro s = SeguroDAO.search(1);

            assertEquals(seguroIn.toString(), SeguroDAO.search(seguroIn.getIdSeguro()).toString());
        });
    }

    @Test
    void read() {
        assertDoesNotThrow(() -> {
            char[] clave = {'c', 'l', 'a', 'v', 'e', '2'};


            Seguro segInsert = new Seguro(2, "51413256B", "Pablo", "Fernández", "Fernández", 45, Seguro.Sexo.HOMBRE, Seguro.Casado.Y, 2, Timestamp.valueOf("2013-04-25 19:05:45"), Seguro.TipoSeguro.HOGAR, LocalDate.of(1977, 1, 20), LocalTime.of(12, 15), clave);

            SeguroDAO.insert(segInsert);
            Seguro s = SeguroDAO.search(2);

            //Prueba para seguro que existe
            Seguro segSearch = SeguroDAO.search(segInsert.getIdSeguro());
            assertEquals(s.toString(), segSearch.toString());

            //Prueba para seguro inexistente
            Seguro segNoInsert = new Seguro(365, "51413256C", "Pepe", "Martínez", "Martínez", 58, Seguro.Sexo.HOMBRE, Seguro.Casado.N, 2, Timestamp.valueOf("2013-04-25 19:20:45"), Seguro.TipoSeguro.COCHE, LocalDate.of(1962, 5, 15), LocalTime.of(10, 30), clave);
            assertNull(SeguroDAO.search(segNoInsert.getIdSeguro()));

        });
    }

    @Test
    void update() {
        assertDoesNotThrow(() -> {
            char[] clave = {'c', 'l', 'a', 'v', 'e', '4'};

            //Se inserta seguro para posteriormente modificarlo y compararlo
            Seguro segInsert = new Seguro(4, "65984589D", "Ana", "López", "López", 25, Seguro.Sexo.MUJER, Seguro.Casado.N, 0, Timestamp.valueOf("2013-04-22 19:05:13"), Seguro.TipoSeguro.VIAJE, LocalDate.of(1995, 12, 12), LocalTime.of(13, 30), clave);
            SeguroDAO.insert(segInsert);

            Seguro seguroUpdate = new Seguro(4, "65984589D", "Jose", "Pérez", "Pérez", 45, Seguro.Sexo.HOMBRE, Seguro.Casado.Y, 2, Timestamp.valueOf("2013-04-22 19:05:13"), Seguro.TipoSeguro.HOGAR, LocalDate.of(1975, 3, 30), LocalTime.of(12, 45), clave);

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
            char[] clave = {'c', 'l', 'a', 'v', 'e', '5'};

            //Se inserta seguro para posteriormente eliminar y comprobar que se elimina correctamente
            Seguro seguroDelete = new Seguro(5, "65984589E", "Pepe", "Soriano", "Soriano", 45, Seguro.Sexo.HOMBRE, Seguro.Casado.Y, 2, Timestamp.valueOf("2013-04-22 19:05:13"), Seguro.TipoSeguro.VIAJE, LocalDate.of(1975, 9, 17), LocalTime.of(9, 30), clave);
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
            char[] clave = {'c', 'l', 'a', 'v', 'e', '6'};

            //Creamos seguros, uno mayor de edad y otro no
            Seguro seguroMayorEdad = new Seguro(6, "65975539F", "Laura", "Pascual", "Pascual", 45, Seguro.Sexo.MUJER, Seguro.Casado.Y, 2, Timestamp.valueOf("2013-04-22 19:05:13"), Seguro.TipoSeguro.HOGAR, LocalDate.of(1975, 1, 20), LocalTime.of(12, 30), clave);
            Seguro seguroMenorEdad = new Seguro(7, "65975539G", "Mario", "Gutierrez", "Gutierrez", 17, Seguro.Sexo.HOMBRE, Seguro.Casado.N, 0, Timestamp.valueOf("2013-04-22 19:05:13"), Seguro.TipoSeguro.MOTO, LocalDate.of(2003, 5, 16), LocalTime.of(10, 30), clave);

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

    @Test
    void testTipoSeguro() {

        assertDoesNotThrow(() -> {
            char[] clave = {'c', 'l', 'a', 'v', 'e', '8'};

            Seguro segInsert = new Seguro(8, "65975556H", "Juan", "Gómez", "Gómez", 30, Seguro.Sexo.HOMBRE, Seguro.Casado.Y, 1, Timestamp.valueOf("2013-04-22 19:05:13"), Seguro.TipoSeguro.VIAJE, LocalDate.of(1990, 6, 20), LocalTime.of(11, 45), clave);
            SeguroDAO.insert(segInsert);

            Seguro segSearch = SeguroDAO.search(8);

            assertEquals(segSearch.getTipoSeguro(), Seguro.TipoSeguro.VIAJE);
        });
    }

    @Test
    void fechaNacimiento() {
        assertDoesNotThrow(() -> {
            char[] clave = {'c', 'l', 'a', 'v', 'e', '9'};

            Seguro segInsert = new Seguro(9, "65975785I", "Paula", "Cabello", "Cano", 21, Seguro.Sexo.MUJER, Seguro.Casado.N, 0, Timestamp.valueOf("2013-04-22 19:05:13"), Seguro.TipoSeguro.VIAJE, LocalDate.of(2001, 9, 05), LocalTime.of(13, 40), clave);
            SeguroDAO.insert(segInsert);

            //Buscamos el objeto y comparamos el campo fecha con la fecha del seguro insertado para comprobar que se ha guardado correctamente
            Seguro segSearch = SeguroDAO.search(9);

            assertEquals(segSearch.getFecha_nac(), LocalDate.of(2001, 9, 5));
        });
    }

    @Test
    void horaContacto() {
        assertDoesNotThrow(() -> {
            char[] clave = {'c', 'l', 'a', 'v', 'e', '1', '0'};

            Seguro segInsert = new Seguro(10, "65977895J", "Jose", "Fernández", "Fernández", 26, Seguro.Sexo.MUJER, Seguro.Casado.N, 0, Timestamp.valueOf("2013-04-22 19:05:13"), Seguro.TipoSeguro.VIAJE, LocalDate.of(1996, 9, 05), LocalTime.of(13, 40), clave);
            SeguroDAO.insert(segInsert);

            //Buscamos el objeto y comparamos el campo hora con la hora del seguro insertado para comprobar que se ha guardado correctament
            Seguro segSearch = SeguroDAO.search(10);

            assertEquals(segSearch.getHora_contacto(), LocalTime.of(13, 40));
        });
    }

    @Test
    void TestClave() {
        char[] clave = {'c', 'l', 'a', 'v', 'e', '1', '1'};
        Seguro segInsert = new Seguro(11, "65977111K", "Jose", "Fernández", "Fernández", 26, Seguro.Sexo.MUJER, Seguro.Casado.N, 0, Timestamp.valueOf("2013-04-22 19:05:13"), Seguro.TipoSeguro.VIAJE, LocalDate.of(1996, 9, 05), LocalTime.of(13, 40), clave);

        SeguroDAO.insert(segInsert);

        Seguro segSearch = SeguroDAO.search(11);

        assertArrayEquals(segSearch.getClave(), segInsert.getClave());

    }
}
