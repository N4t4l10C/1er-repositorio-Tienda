package libreria.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;

public class ServicioMaster {

    AutorServicio as = new AutorServicio();
    EditorialServicio es = new EditorialServicio();
    LibroServicio ls = new LibroServicio();

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    // Menus
    public int MenuPPal() throws Exception {

        try {

            System.out.println("");
            System.out.println("Ingrese la opción que desee: ");
            System.out.println("1- Opciones para 'autores'.");
            System.out.println("2- Opciones para 'editoriales'.");
            System.out.println("3- Opciones para 'libros'.");

            return leer.nextInt();

        } catch (Exception e) {
            System.out.println("**EL DATO INGRESADO NO ES CORRECTO**");
            return 0;
        }
    }

    public int MenuEd() {

        try {

            System.out.println("");
            System.out.println("+++ Editorial +++");
            System.out.println("1- Agregar editorial.");
            System.out.println("2- Buscar editorial por su ID.");
            System.out.println("3- Modificar la editorial.");
            System.out.println("4- Mostrar solo las editoriales que no fueron dadas de baja.");
            System.out.println("5- Mostrar todas las editoriales.");
            System.out.println("6- Dar de baja por su ID.");
            System.out.println("7- Dar de alta por su ID.");

            return leer.nextInt();

        } catch (Exception e) {
            System.out.println("**EL DATO INGRESADO NO ES CORRECTO**");
            return 0;
        }

    }

    public int MenuAut() {

        try {
            System.out.println("");
            System.out.println("+++ Autores +++");
            System.out.println("1- Crear autor.");
            System.out.println("2- Buscar autor por el ID.");
            System.out.println("3- Modificar el autor.");
            System.out.println("4- Mostrar solo autores que no fueron dados de baja.");
            System.out.println("5- Mostrar todos los autores.");
            System.out.println("6- Dar de baja a un autor (junto con sus libros)");
            System.out.println("7- Dar de alta a un autor.");

            return leer.nextInt();

        } catch (Exception e) {
            System.out.println("**EL DATO INGRESADO NO ES CORRECTO**");
            return 0;
        }
    }

    public int MenuLibro() {

        try {
            System.out.println("");
            System.out.println("+++ Libros +++");
            System.out.println("1- Agregar un libro.");
            System.out.println("2- Buscar un libro por el 'autor'.");
            System.out.println("3- Buscar un libro por el 'titulo'.");
            System.out.println("4- Buscar un libro por la 'editorial'.");
            System.out.println("5- Listar todos los libros.");
            System.out.println("6- Dar de baja por su isbn.");
            System.out.println("7- Prestar algun libro.");
            System.out.println("8- Devolver libros.");
            System.out.println("9- Dar de alta por su isbn.");
            System.out.println("10- Listar todos los libros esten o no disponibles.");

            return leer.nextInt();

        } catch (Exception e) {
            System.out.println("**EL DATO INGRESADO NO ES CORRECTO**");
            return 0;
        }
    }

    // Autores -------------
    public void crearAutor() throws Exception {

        try {

            leer.nextLine();

            System.out.println("Ingrese el nombre del autor y el resto se autorrellenará.");
            String sunom = leer.next();

            Autor au = as.crearAutor(sunom);

            System.out.println("");
            System.out.println("Se agregó lo siguiente: ");
            System.out.println(au.toString());

        } catch (Exception e) {
            System.out.println("**ERROR AL CREAR UN AUTOR**");
        }
    }

    public void mostrarAutorPID() throws Exception {

        try {

            System.out.println("Ingrese el ID del autor para buscarlo.");
            System.out.println(as.buscarAutorPorID(leer.nextInt()));

        } catch (Exception e) {
            System.out.println("**ERROR AL MOSTRAR AL AUTOR**");
        }
    }

    public void modificarAutor() throws Exception {

        leer.nextLine();

        System.out.println("Ingrese el nuevo nombre y el ID del autor a modificar: ");
        as.modificarAutor(leer.next(), leer.nextInt());

    }

    public void mostrarTodosLosAutores() throws Exception {

        for (Autor at : as.mostrarAutores()) {

            System.out.println(at.toString());

        }
    }

    public void mostrarAutoresDeAlta() throws Exception {

        for (Autor at : as.mostrarAutoresTRUE()) {
            System.out.println(at.toString());
        }
    }

    public void darDeBajaAutor() throws Exception {

        System.out.println("Ingrese el ID del autor a dar de baja.");
        as.darDeBajaAutor(leer.nextInt());

    }

    public void darDeAltaAutor() throws Exception {

        System.out.println("Ingrese el ID del autor a dar de alta.");
        as.darDeAltaAutor(leer.nextInt());

    }

    // Editoriales -----------
    public void agregarEd() throws Exception {

        //leer.next();
        try {

            System.out.println("Ingrese el nombre de la editorial, el resto se llenará automáticamente.");
            String nom = leer.next();

            Editorial ed = es.crearEditorial(nom);

            System.out.println("");
            System.out.println("Se agregó lo siguiente: ");
            System.out.println(ed.toString());

        } catch (Exception e) {
            System.out.println("**ERROR AL CREAR UNA EDITORIAL**");
        }
    }

    public void buscarEdPorId() throws Exception {

        try {

            System.out.println("Ingrese el ID de la editorial a buscar:");
            System.out.println(es.buscarPorID(leer.nextInt()));

        } catch (Exception e) {
            System.out.println("**ERROR AL MOSTRAR EDITORIAL**");
        }
    }

    public void modificarEd() throws Exception {

        //leer.next();
        System.out.println("Ingrese nuevo nombre de la editorial y luego ed ID de la editorial a modificar: ");
        es.modificarEditorial(leer.next(), leer.nextInt());

    }

    public void mostrarTodasLasEd() throws Exception {

        for (Editorial ed : es.mostrarEditoriales()) {
            System.out.println(ed.toString());
        }

    }

    public void mostrarEdTRUE() throws Exception {

        for (Editorial ed : es.mostrarEditorialesTRUE()) {
            System.out.println(ed.toString());
        }
    }

    public void darDeBajaPorId() throws Exception {

        System.out.println("Ingrese el ID de la editorial a dar de baja.");
        es.darDeBaja(leer.nextInt());

    }

    public void darDeAltaPorId() throws Exception {

        System.out.println("Ingrese el ID de la editorial a dar de alta.");
        es.darDeAltaEditorial(leer.nextInt());

    }

    // Libros ------------
    public void agregarLibro() throws Exception {

        try {

            System.out.println("Ingrese los siguientes datos para agregar un libro:");
            System.out.println("Ingrese el titulo:");
            String t = leer.next();
            System.out.println("Ingrese el año:");
            int a = leer.nextInt();
            System.out.println("Ingrese la cantidad de ejemplares:");
            int e = leer.nextInt();
            System.out.println("Ingrese la ID de la editorial:");
            int ed = leer.nextInt();
            System.out.println("Ingrese la ID del autor:");
            int ad = leer.nextInt();

            ls.agregarLibro(t, a, e, ed, ad);

        } catch (Exception e) {
            System.out.println("**ERROR AL CREAR EL LIBRO**");
        }
    }

    public void buscarPorAutor() throws Exception {

        try {

            System.out.println("Ingrese el nombre del auto, a continuación, se listarán los libros de dicho autor.");

            List<Libro> lib = new ArrayList();

            for (Libro lb : ls.buscarPorAutor(leer.next())) {
                System.out.println(lb.toString());
            }

        } catch (Exception e) {
            System.out.println("**ERROR AL BUSCAR POR AUTOR**");
            System.out.println(e.getCause());
        }
    }

    public void buscarPorTitulo() throws Exception {

        try {

            System.out.println("Ingrese el titulo del libro, a continuación, se listarán los libros con dicho titulo.");

            for (Libro lb : ls.buscarPorTitulo(leer.next())) {
                System.out.println(lb.toString());
            }

        } catch (Exception e) {
            System.out.println("**ERROR AL BUSCAR POR AUTOR**");
        }

    }

    public void buscarPorEditorial() throws Exception {

        try {

            System.out.println("Ingrese el nombre de la editorial, a continuación, se listarán los libros de dicha editorial.");

            for (Libro lb : ls.buscarPorEditorial(leer.next())) {
                System.out.println(lb.toString());
            }

        } catch (Exception e) {
            System.out.println("**ERROR AL BUSCAR POR AUTOR**");
        }

    }

    public void listarLibros() throws Exception {

        try {

            for (Libro lb : ls.verTodosLibros()) {
                System.out.println(lb.toString());
            }

        } catch (Exception e) {
            System.out.println("");
            System.out.println("**ERROR AL LISTAR LOS LIBROS**");
        }
    }

    public void listarTODOSLibros() throws Exception {

        try {

            for (Libro lb : ls.verLibrosTyF()) {
                System.out.println(lb.toString());
            }

        } catch (Exception e) {
            System.out.println("");
            System.out.println("**ERROR AL LISTAR LOS LIBROS**");
        }
    }

    public void darDeBajaPorISBN() throws Exception {

        System.out.println("Ingrese el numero de ISBN para dar de baja a dicho libro.");
        ls.darDeBaja(leer.nextLong());

    }

    public void darDeAltaPorISBN() throws Exception {

        System.out.println("Ingrese el numero de ISBN para dar de alta a dicho libro.");
        ls.darDeAltaLibro(leer.nextLong());

    }

    public void prestarLibros() throws Exception {

        System.out.println("Ingrese el ISBN del libro a prestar y posteriormente la cantidad:");
        ls.prestarLibros(leer.nextLong(), leer.nextInt());

    }

    public void devolverLibros() throws Exception {

        System.out.println("Ingrese el ISBN del libro a devolver y posteriormente la cantidad:");
        ls.devolverLibros(leer.nextLong(), leer.nextInt());

    }

    //Programa
    public void elPrograma() throws Exception {

        int resp = MenuPPal();
        int rE = 0;
        int rA = 0;
        int rL = 0;

        switch (resp) {
            case 1:
                rA = MenuAut();
                break;
            case 2:
                rE = MenuEd();
                break;
            case 3:
                rL = MenuLibro();
                break;
            default:
                System.out.println("Soy la otra opcion.");
                break;
        }

        if (resp == 1) {
            switch (rA) {
                case 1:
                    crearAutor();
                    break;
                case 2:
                    mostrarAutorPID();
                    break;
                case 3:
                    modificarAutor();
                    break;
                case 4:
                    mostrarAutoresDeAlta();
                    break;
                case 5:
                    mostrarTodosLosAutores();
                    break;
                case 6:
                    darDeBajaAutor();
                    break;
                case 7:
                    darDeAltaAutor();
                    break;
                default:
                    System.out.println("Opa, al parecer hubo un error. No te preocupes.");
                    break;
            }
        }

        if (resp == 2) {
            switch (rE) {
                case 1:
                    agregarEd();
                    break;
                case 2:
                    buscarEdPorId();
                    break;
                case 3:
                    modificarEd();
                    break;
                case 4:
                    mostrarEdTRUE();
                    break;
                case 5:
                    mostrarTodasLasEd();
                    break;
                case 6:
                    darDeBajaPorId();
                    break;
                case 7:
                    darDeAltaPorId();
                    break;
                default:
                    System.out.println("Opa, al parecer hubo un error. No te preocupes.");
                    break;
            }
        }

        if (resp == 3) {
            switch (rL) {
                case 1:
                    agregarLibro();
                    break;
                case 2:
                    buscarPorAutor();
                    break;
                case 3:
                    buscarPorTitulo();
                    break;
                case 4:
                    buscarPorEditorial();
                    break;
                case 5:
                    listarLibros();
                    break;
                case 6:
                    darDeBajaPorISBN();
                    break;
                case 7:
                    prestarLibros();
                    break;
                case 8:
                    devolverLibros();
                    break;
                case 9:
                    darDeAltaPorISBN();
                    break;
                case 10:
                    listarTODOSLibros();
                    break;
                default:
                    System.out.println("Opa, al parecer hubo un error. No te preocupes.");
                    break;
            }
        }
    }

    public String pregunta() throws Exception {

        System.out.println("");
        System.out.println("¿Quiere realizar alguna otra operación?");
        System.out.println("S=Si");
        System.out.println("N=No");

        leer.nextLine();
        return leer.nextLine();
    }

}
