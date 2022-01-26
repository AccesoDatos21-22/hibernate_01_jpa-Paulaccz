import dao.SeguroDAO;
import entidades.Seguro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Timestamp;

public class Main {

    public static void main(String[] args) {

        /*Seguro seguro = new Seguro(1, "17546586R", "Joaquin", "Soria", "Soria", 20, 0, 's', 0, Timestamp.valueOf("2013-04-22 19:05:11"), "Hogar");
        Seguro seguro1 = new Seguro(2, "22737883Z", "Maria", "Rodriguez", "Martinez", 40, 1, 'S', 3, Timestamp.valueOf("2013-04-22 19:05:12"), "Hogar");

        // Prueba insert
        SeguroDAO.insert(seguro1);
        SeguroDAO.insert(seguro);
        SeguroDAO.insert(seguro1);

        //Prueba search
        System.out.println("----->> Los datos del seguro solicitado son: <<-----");
        Seguro seg = SeguroDAO.search(2);
        System.out.println(seg);


        //Prueba update
        Seguro segUp = new Seguro(4, "56987458E", "Pepe", "Rodríguez", "Rodríguez", 60, 0, 's', 2, Timestamp.valueOf("2013-04-22 19:05:12"), "Coche");
        SeguroDAO.update(1, segUp);

        Seguro seg2 = SeguroDAO.search(1);
        System.err.println(seg2);


        //Prueba delete
        SeguroDAO.delete(1);

        Seguro seg2r = SeguroDAO.search(1);
        System.out.println(seg2r);

        SeguroDAO.mayor_edad(seguro);*/

    }
}
