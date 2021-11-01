package libreria.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Libro;

public class LibroDAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
    private final EntityManager em = emf.createEntityManager();

    // Metodos
    public void agregarLibro(Libro lb) throws Exception {

        try {

            em.getTransaction().begin();
            em.persist(lb);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("**ERROR AL AGREGAR USUARIO**");
        }
    }

    public Libro buscarPoriID(long isbn) throws Exception {

        Libro lb = em.find(Libro.class, isbn);
        return lb;
    }

    public void darDeBajaLibro(long isbn) throws Exception {  // REVISAR

        try {

            Libro lb = buscarPoriID(isbn);

            lb.setEjemplares(0);
            lb.setEjemplaresPrestados(0);
            lb.setEjemplaresRestantes(0);
            lb.setAlta(false);

            em.getTransaction().begin();
            em.merge(lb);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("**OCURRIO UN ERROR**");
        }
    }

    public void prestarLibros(long isbn, int cantidad) throws Exception {

        try {

            Libro lib = buscarPoriID(isbn);

            lib.setEjemplaresPrestados(lib.getEjemplaresPrestados() + cantidad);
            lib.setEjemplaresRestantes(lib.getEjemplares() - lib.getEjemplaresPrestados());

            em.getTransaction().begin();
            em.merge(lib);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("**ERROR AL EJECUTAR LA ACCION**");
        }
    }

    public void devolverLibros(long isbn, int cantidad) throws Exception {

        try {

            Libro lib = buscarPoriID(isbn);

            lib.setEjemplaresPrestados(lib.getEjemplaresPrestados() - cantidad);
            lib.setEjemplaresRestantes(lib.getEjemplaresRestantes() + cantidad);

            em.getTransaction().begin();
            em.merge(lib);
            em.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("**ERROR AL EJECUTAR LA ACCION**");
        }
    }

    public List<Libro> listarLibros() throws Exception {

        try {

            List<Libro> libros = em.createQuery("SELECT d FROM Libro d").getResultList();
            List<Libro> libroSI = new ArrayList();

            libros.stream().filter((lib) -> (lib.isAlta() == true)).forEachOrdered((lib) -> {
                libroSI.add(lib);
            });

            return libroSI;

        } catch (Exception e) {
            System.out.println("**ERROR AL BUSCAR LOS LIBROS**");
            return null;
        }
    }

    public List<Libro> listarTodosLibros() throws Exception {

        try {

            List<Libro> libros = em.createQuery("SELECT d FROM Libro d").getResultList();

            return libros;

        } catch (Exception e) {
            System.out.println("**ERROR AL BUSCAR LOS LIBROS**");
            return null;
        }
    }

    public List<Libro> buscarPorTitulo(String titulo) throws Exception {

        List<Libro> libros = listarLibros();
        List<Libro> libroSI = new ArrayList();

        for (Libro lb : libros) {
            if (lb.getTitulo().equals(titulo)) {
                libroSI.add(lb);
            }
        }

        return libroSI;
    }

    public List<Libro> buscarPorAutor(String na) throws Exception {

        List<Libro> libros = listarLibros();
        List<Libro> libroSI = new ArrayList();

        for (Libro lb : libros) {
            if (lb.getAutor().getNombre().equals(na)) {
                libroSI.add(lb);
            }
        }

        return libroSI;
    }

    public List<Libro> buscarPorEditorial(String nomEditorial) throws Exception {

        List<Libro> libros = listarLibros();
        List<Libro> libroSI = new ArrayList();

        for (Libro lb : libros) {
            if (lb.getEditorial().getNombre().equals(nomEditorial)) {
                libroSI.add(lb);
            }
        }

        return libroSI;
    }

}
