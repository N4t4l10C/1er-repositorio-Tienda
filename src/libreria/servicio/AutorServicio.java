package libreria.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import libreria.entidades.Autor;
import libreria.persistencia.AutorDAO;
import libreria.persistencia.EditorialDAO;
import libreria.persistencia.LibroDAO;

public class AutorServicio {

    AutorDAO ad = new AutorDAO();
    EditorialDAO ed = new EditorialDAO();
    LibroDAO ld = new LibroDAO();

    public Autor crearAutor(String nom) throws Exception {
        try {

            if (nom.trim().isEmpty()) { //  || nom.trim().isEmpty()
                throw new Exception("**EL NOMBRE ESTA VACIO**");
            }

            Autor a = new Autor();

            a.setAlto(true);
            a.setId(mostrarAutores().size() + 1);
            a.setNombre(nom);

            ad.guardarAutor(a);

            System.out.println("------------------");
            System.out.println("**El autor fue agregado con exito**");
            System.out.println("------------------");

            return a;

        } catch (Exception e) {
            System.out.println("**ERROR AL CREAR AUTOR**");
            return null;
        }
    }

    public Autor buscarAutorPorID(int id) throws Exception {

        try {

            return ad.buscarPorID(id);

        } catch (Exception e) {
            System.out.println("**SE PRODUJO UN ERROR AL BUSCAR EL AUTOR**");
            return null;
        }
    }

    public void modificarAutor(String nombre, int id) throws Exception {

        try {

            ad.modificarAutor(nombre, id);

        } catch (Exception e) {
            System.out.println("**SE PRODUJO UN ERROR AL MODIFICAR UN AUTOR**");
        }

        System.out.println("------------------");
        System.out.println("**El autor fue modificado con exito**");
        System.out.println("------------------");
    }

    public List<Autor> mostrarAutores() throws Exception {

        try {

            return ad.mostrarAutores();

        } catch (Exception e) {
            return null;
        }
    }

    public List<Autor> mostrarAutoresTRUE() throws Exception {

        try {

            List<Autor> aures = ad.mostrarAutores();
            List<Autor> auresHIGH = new ArrayList();

            for (Autor aut : aures) {
                if (aut.isAlto()) {
                    auresHIGH.add(aut);
                }
            }

            return auresHIGH;

        } catch (Exception e) {
            return null;
        }
    }

    public void darDeBajaAutor(int id) throws Exception {

        try {

            ad.eliminarPorId(id);

            System.out.println("------------------");
            System.out.println("**El autor fue dado de baja con exito**");
            System.out.println("------------------");

        } catch (Exception e) {
            System.out.println("**ERROR AL DAR DE BAJA AL AUTOR**");
        }

    }

    public void darDeAltaAutor(int id) throws Exception {

        try {

            if (id == 0) {
                throw new Exception("**EL ID INGRESADO ES INCORRECTO**");
            }

            Autor au = ad.buscarPorID(id);

            au.setAlto(true);

        } catch (Exception e) {
            System.out.println("**ERROR AL DAR DE ALTA A LA EDITORIAL**");
        }

        System.out.println("------------------");
        System.out.println("**La editorial fue dado de alta con exito**");
        System.out.println("------------------");
    }
}
