package ui;

import model.Libro;
import service.BibliotecaService;

/**
 * Clase para crear datos de ejemplo en la biblioteca.
 */
public class DatosEjemplo {
    
    /**
     * Agrega libros de ejemplo a la biblioteca.
     * 
     * @param biblioteca Servicio de biblioteca donde agregar los libros
     */
    public static void cargarDatosEjemplo(BibliotecaService biblioteca) {
        // Crear algunos libros de ejemplo
        Libro libro1 = new Libro("El Quijote", "Miguel de Cervantes", 1605, "978-84-376-0494-7");
        Libro libro2 = new Libro("Cien años de soledad", "Gabriel García Márquez", 1967, "978-84-376-0495-4");
        Libro libro3 = new Libro("1984", "George Orwell", 1949, "978-84-376-0496-1");
        Libro libro4 = new Libro("El Principito", "Antoine de Saint-Exupéry", 1943, "978-84-376-0497-8");
        Libro libro5 = new Libro("Rayuela", "Julio Cortázar", 1963, "978-84-376-0498-5");
        
        // Agregar los libros a la biblioteca
        biblioteca.agregarLibro(libro1);
        biblioteca.agregarLibro(libro2);
        biblioteca.agregarLibro(libro3);
        biblioteca.agregarLibro(libro4);
        biblioteca.agregarLibro(libro5);
        
        System.out.println("Se han cargado " + 5 + " libros de ejemplo en la biblioteca.");
    }
}
