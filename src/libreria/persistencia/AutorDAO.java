package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Autor;

public class AutorDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    private final EntityManager em = emf.createEntityManager();

    // Metodos
    public void guardarAutor(Autor autor) throws Exception {

        try {

            em.getTransaction().begin(); // Inicio la transaccion
            em.persist(autor);  // Agrego el objeto
            em.getTransaction().commit();  // Termino la trasnaccion

        } catch (Exception e) {
            System.out.println("**ERROR AL INGRESAR AUTOR**");
        }
    }

    public Autor buscarPorID(int id) throws Exception {

        Autor autor = em.find(Autor.class, id);
        return autor;
    }

    public void eliminarPorId(int id) throws Exception {

        try {

            Autor autor = em.find(Autor.class, id);

            autor.setAlto(false);
            
            em.getTransaction().begin();
            em.merge(autor);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("**ERROR AL ELIMINAR USUARIO**");
        }
    }

    public void modificarAutor(String nombre, int id) throws Exception {

        try {

            Autor au = em.find(Autor.class, id);

            au.setNombre(nombre);

            em.getTransaction().begin();
            em.merge(au);
            em.getTransaction().commit();

            System.out.println("El nuevo Autor sería: " + buscarPorID(id).toString());

        } catch (Exception e) {
            System.out.println("**ERROR AL MODIFICAR AUTOR**");
        }
    }

    public List<Autor> mostrarAutores() throws Exception {

        try {

            List<Autor> autores = em.createQuery("Select d From Autor d").getResultList();
            return autores;

        } catch (Exception e) {
            System.out.println("**ERROR AL MOSTRAR LOS AUTORES**");
            return null;
        }
    }

}
