import dao.SeguroDAO;
import entidades.Seguro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        /*char[] clave = {'c', 'l', 'a', 'v', 'e', '2'};

        Seguro seguro = new Seguro(1, "17546586R", "Joaquin", "Soria", "Soria", 17, Seguro.Sexo.HOMBRE, Seguro.Casado.Y, 0, Timestamp.valueOf("2013-04-22 19:05:11"), Seguro.TipoSeguro.VIAJE, LocalDate.of(2003, 1, 20), LocalTime.of(13, 40), clave);
        Seguro seguro1 = new Seguro(2, "22737883Z", "Maria", "Rodriguez", "Martinez", 45, Seguro.Sexo.MUJER, Seguro.Casado.Y, 3, Timestamp.valueOf("2013-04-22 19:05:12"), Seguro.TipoSeguro.COCHE, LocalDate.of(1975, 8, 20), LocalTime.of(13, 40), clave);
        ;

        // Prueba insert
        SeguroDAO.insert(seguro1);
        SeguroDAO.insert(seguro);
        SeguroDAO.insert(seguro1);

        //Prueba search
        System.out.println("----->> Los datos del seguro solicitado son: <<-----");
        Seguro seg = SeguroDAO.search(2);
        System.out.println(seg);


        Seguro seg2 = SeguroDAO.search(1);
        System.out.println(seg2);

        //Prueba update
        Seguro segUp = new Seguro(4, "56987458E", "Pepe", "Rodríguez", "Rodríguez", 60, 0, 's', 2, Timestamp.valueOf("2013-04-22 19:05:12"), "Coche");
        SeguroDAO.update(1, segUp);

        Seguro seg2 = SeguroDAO.search(1);
        System.err.println(seg2);

        //Prueba delete
        SeguroDAO.delete(1);

        Seguro seg2r = SeguroDAO.search(1);
        System.out.println(seg2r);

        SeguroDAO.mayor_edad(seguro);

        if (seg.isMayor_edad()) {
            System.out.println("************ Mayor de edad");

        } else
            System.out.println("************No Mayor de edad");*/
    }
}
