package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Editorial;

public class EditorialDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    private final EntityManager em = emf.createEntityManager();

    // Metodos
    public void guardarEditorial(Editorial ed) throws Exception {

        try {

            em.getTransaction().begin();
            em.persist(ed);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("**ERROR AL GUARDAR EDITORIAL**");
        }
    }

    public Editorial buscarPorID(int id) throws Exception {

        Editorial ed = em.find(Editorial.class, id);
        return ed;
    }

    public void eliminarPorID(int id) throws Exception {

        try {

            Editorial ed = em.find(Editorial.class, id);

            ed.setAlto(false);
            
            em.getTransaction().begin();
            em.merge(ed);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("**NO SE PUDO ENCONTRAR LA EDITORIAL**");
        }
    }

    public void modificarEditorial(String nombre, int id) throws Exception {

        try {

            Editorial ed = em.find(Editorial.class, id);

            ed.setNombre(nombre);

            em.getTransaction().begin();
            em.merge(ed);
            em.getTransaction().commit();

            System.out.println("La nueva editorial sería: " + ed.toString());

        } catch (Exception e) {
            System.out.println("**ERROR AL MODIFICAR EDITORIAL**");
        }
    }

    public List<Editorial> mostrarEditoriales() throws Exception {

        try {

            List<Editorial> eds = em.createQuery("SELECT d FROM Editorial d").getResultList();

            return eds;

        } catch (Exception e) {
            System.out.println("**ERROR AL MOSTRAR LAS EDITORIALES**");
            return null;
        }
    }
    
}
