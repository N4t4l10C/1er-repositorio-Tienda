package libreria.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import libreria.entidades.Libro;
import libreria.persistencia.AutorDAO;
import libreria.persistencia.EditorialDAO;
import libreria.persistencia.LibroDAO;

public class LibroServicio {

    LibroDAO ld = new LibroDAO();
    EditorialDAO ed = new EditorialDAO();
    AutorDAO ad = new AutorDAO();

    public Libro crearLibro(String titulo, int anio, int ejemplares, int idEdi, int idAutor) throws Exception {

        try {

            if (titulo.isEmpty() || titulo.trim().isEmpty()) {
                throw new Exception("**EL TITULO ESTA VACIO**");
            }

            if (anio == 0) {
                throw new Exception("**EL AÑO ES INCORRECTO**");
            }

            if (ejemplares <= 0) {
                throw new Exception("**LA CANTIDAD DE EJEMPLARES ES INCORRECTA**");
            }

            if (idEdi <= 0) {
                throw new Exception("**LA ID DEL EDITOR ES INCORRECTA**");
            }

            if (idAutor <= 0) {
                throw new Exception("**LA ID DEL AUTOR ES INCORRECTA**");
            }

            Libro lib = new Libro();

            lib.setAlta(true);
            lib.setAnio(anio);

            lib.setEjemplares(ejemplares);
            lib.setEjemplaresRestantes(ejemplares);
            lib.setTitulo(titulo);
            lib.setIsbn(UUID.randomUUID().getLeastSignificantBits());
            lib.setAutor(ad.buscarPorID(idAutor));
            lib.setEditorial(ed.buscarPorID(idEdi));

            return lib;

        } catch (Exception e) {
            System.out.println("**SE PRODUJO UN ERROR AL CREAR EL LIBRO**");
            return null;
        }
    }

    public void agregarLibro(String titulo, int anio, int ejemplares, int idEdi, int idAutor) throws Exception {

        try {

            ld.agregarLibro(crearLibro(titulo, anio, ejemplares, idEdi, idAutor));

            System.out.println("------------------");
            System.out.println("**El libro fue agregado con exito**");
            System.out.println("------------------");

        } catch (Exception e) {
            System.out.println("**ERROR AL AGREGAR EL LIBRO**");
        }
    }

    public List<Libro> buscarPorAutor(String autor) throws Exception {

        try {

            if (autor.isEmpty() || autor.trim().isEmpty()) {
                throw new Exception("**EL AUTOR INGRESADO ESTA VACIO**");
            }

            List<Libro> libAutor = ld.buscarPorAutor(autor);

            List<Libro> libros = new ArrayList();

            for (Libro lb : libAutor) {
                if (lb.getAutor().isAlto() == true) {
                    libros.add(lb);
                }
            }

            if (libros.isEmpty()) {
                throw new Exception("**NO HAY LIBROS CON ESTE AUTOR**");
            }

            return libros;

        } catch (Exception e) {
            System.out.println("**ERROR AL BUSCAR LOS LIBROS**");
            return null;
        }
    }

    public List<Libro> buscarPorTitulo(String titulo) throws Exception {

        try {

            if (titulo.isEmpty() || titulo.trim().isEmpty()) {
                throw new Exception("**EL TITULO INGRESADO ESTA VACIO**");
            }

            List<Libro> libTit = ld.buscarPorTitulo(titulo);
            List<Libro> libros = new ArrayList();

            for (Libro lib : libTit) {
                if (lib.getAutor().isAlto() == true) {
                    libros.add(lib);
                }
            }

            if (libros.isEmpty()) {
                throw new Exception("**NO HAY LIBROS CON ESTE TITULO**");
            }

            return libros;

        } catch (Exception e) {
            System.out.println("**ERROR AL BUSCAR LOS LIBROS**");
            return null;
        }
    }

    public List<Libro> buscarPorEditorial(String editorial) throws Exception {

        try {

            if (editorial.isEmpty() || editorial.trim().isEmpty()) {
                throw new Exception("**LA EDITORIAL INGRESADA ESTA VACIA**");
            }

            List<Libro> libEd = ld.buscarPorEditorial(editorial);
            List<Libro> libros = new ArrayList();

            for (Libro lib : libEd) {

                if (lib.getAutor().isAlto() == true) {
                    libros.add(lib);
                }
            }

            if (libros.isEmpty()) {
                throw new Exception("**NO HAY LIBROS CON ESTA EDITORIAL**");
            }

            return libros;

        } catch (Exception e) {
            System.out.println("**ERROR AL BUSCAR LOS LIBROS**");
            return null;
        }
    }

    public List<Libro> verTodosLibros() throws Exception {

        try {

            List<Libro> libros = ld.listarLibros();

            if (libros.isEmpty()) {
                throw new Exception("**NO HAY LIBROS INGRESADOS**");
            }

            return libros;

        } catch (Exception e) {
            System.out.println("**ERROR AL BUSCAR LOS LIBROS**");
            return null;
        }
    }

    public List<Libro> verLibrosTyF() throws Exception {

        try {

            List<Libro> libros = ld.listarTodosLibros();

            if (libros.isEmpty()) {
                throw new Exception("**NO HAY LIBROS INGRESADOS**");
            }

            return libros;

        } catch (Exception e) {
            System.out.println("**ERROR AL BUSCAR LOS LIBROS**");
            return null;
        }
    }

    public void darDeBaja(long isbn) throws Exception {

        try {

            if (isbn == 0) {
                throw new Exception("**EL ISBN INGRESADO ES INCORRECTO**");
            }

            ld.darDeBajaLibro(isbn);

        } catch (Exception e) {
            System.out.println("**ERROR AL DAR DE BAJA AL LIBRO**");
        }

        System.out.println("------------------");
        System.out.println("**El libro fue dado de baja con exito**");
        System.out.println("------------------");
    }

    public void darDeAltaLibro(long isbn) throws Exception {

        try {

            if (isbn == 0) {
                throw new Exception("**EL ISBN INGRESADO ES INCORRECTO**");
            }

            Libro lib = ld.buscarPoriID(isbn);

            lib.setAlta(true);

        } catch (Exception e) {
            System.out.println("**ERROR AL DAR DE ALTA AL LIBRO**");
        }

        System.out.println("------------------");
        System.out.println("**El libro fue dado de alta con exito**");
        System.out.println("------------------");
    }

    public void prestarLibros(long isbn, int cantidad) throws Exception {

        try {

            if (isbn == 0) {
                throw new Exception("**EL ISBN INGRESADO ES INCORRECTO**");
            }

            if (cantidad == 0) {
                throw new Exception("LA CANTIDAD A PEDIR DEBE SER MAYOR A 0 (cero)");
            }

            if (ld.buscarPoriID(isbn).isAlta() == false) {
                throw new Exception("**EL LIBRO A RETIRAR ESTA DADO DE BAJA**");
            }

            ld.prestarLibros(isbn, cantidad);

        } catch (Exception e) {
            System.out.println("**ERROR AL PEDIR LOS LIBROS**");
        }

        System.out.println("------------------");
        System.out.println("**Los libros fueron prestados con exito**");
        System.out.println("------------------");
    }

    public void devolverLibros(long isbn, int cantidad) throws Exception {

        try {

            if (isbn == 0) {
                throw new Exception("**EL ISBN INGRESADO ES INCORRECTO**");
            }

            if (cantidad == 0) {
                throw new Exception("LA CANTIDAD A DEVOLVER DEBE SER MAYOR A 0 (cero)");
            }

            if (ld.buscarPoriID(isbn).getEjemplaresPrestados() < cantidad) {
                throw new Exception("**LA CANTIDAD A DEVOLVER ES MAYOR AL NUMERO DE LIBROS PRESTADOS**");
            }

            ld.devolverLibros(isbn, cantidad);

        } catch (Exception e) {
            System.out.println("**ERROR AL DEVOLVER LOS LIBROS**");
        }

        System.out.println("------------------");
        System.out.println("**Los libros fueron devueltos con exito**");
        System.out.println("------------------");
    }

}
