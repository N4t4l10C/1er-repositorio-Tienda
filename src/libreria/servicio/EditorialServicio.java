package libreria.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;

public class EditorialServicio {

    EditorialDAO ed = new EditorialDAO();

    public Editorial crearEditorial(String nombre) throws Exception {

        try {

            if (nombre.trim().isEmpty()) {
                throw new Exception("**EL NOMBRE ESTA VACIO**");
            }

            Editorial edt = new Editorial();

            edt.setNombre(nombre);
            edt.setId(mostrarEditoriales().size() + 1);  // Puede que lance error al dar de baja
            edt.setAlto(true);

            ed.guardarEditorial(edt);

            System.out.println("------------------");
            System.out.println("**La editorial fue agregada con exito**");
            System.out.println("------------------");

            return edt;

        } catch (Exception e) {
            System.out.println("**ERROR AL CREAR EDITORIAL**");
            return null;
        }
    }

    public void agregarEditorial(String nombre) throws Exception {

        try {

            ed.guardarEditorial(crearEditorial(nombre));

        } catch (Exception e) {
            System.out.println("**ERROR AL AGREGAR LA EDITORIAL**");
        }

        System.out.println("------------------");
        System.out.println("**La Editorial fue agregada con exito**");
        System.out.println("------------------");
    }

    public Editorial buscarPorID(int id) throws Exception {

        try {

            return ed.buscarPorID(id);

        } catch (Exception e) {
            System.out.println("***ERROR AL ENCONTRAR USUARIO*");
            return null;
        }
    }

    public void modificarEditorial(String nombre, int id) throws Exception {

        try {

            ed.modificarEditorial(nombre, id);

        } catch (Exception e) {
            System.out.println("SE PRODUJO UN ERROR AL MODIFICAR UNA EDITORIAL");
        }

        System.out.println("------------------");
        System.out.println("**La editorial fue modificada con exito**");
        System.out.println("------------------");

    }

    public List<Editorial> mostrarEditoriales() throws Exception {

        try {

            return ed.mostrarEditoriales();

        } catch (Exception e) {
            System.out.println("**ERROR AL MOSTRAR LAS EDITORIALES**");
            return null;
        }
    }

    public List<Editorial> mostrarEditorialesTRUE() throws Exception {

        try {

            List<Editorial> eds = ed.mostrarEditoriales();
            List<Editorial> edsHIGH = new ArrayList();

            for (Editorial ed : eds) {
                if (ed.isAlto()) {
                    edsHIGH.add(ed);
                }
            }

            return edsHIGH;

        } catch (Exception e) {
            System.out.println("**ERROR AL MOSTRAR LAS EDITORIALES**");
            return null;
        }
    }

    public void darDeBaja(int id) throws Exception {

        try {

            ed.eliminarPorID(id);

            System.out.println("------------------");
            System.out.println("**La editorial fue dada de baja con exito**");
            System.out.println("------------------");

        } catch (Exception e) {
            System.out.println("**ERROR AL DAR DE BAJA A UNA EDITORIAL**");
        }
    }

    public void darDeAltaEditorial(int id) throws Exception {

        try {

            if (id == 0) {
                throw new Exception("**EL ID INGRESADO ES INCORRECTO**");
            }

            Editorial edi= ed.buscarPorID(id);

            edi.setAlto(true);

        } catch (Exception e) {
            System.out.println("**ERROR AL DAR DE ALTA A LA EDITORIAL**");
        }

        System.out.println("------------------");
        System.out.println("**La editorial fue dado de alta con exito**");
        System.out.println("------------------");
    }

}
