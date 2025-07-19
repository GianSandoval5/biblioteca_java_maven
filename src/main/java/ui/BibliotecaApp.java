package ui;

import model.Libro;
import service.BibliotecaService;
import exception.LibroNoEncontradoException;

import java.util.List;
//Un escáner de texto simple que puede analizar tipos primitivos y cadenas mediante expresiones regulares.
import java.util.Scanner;

/**
 * Clase principal de la aplicación de consola para la gestión de la biblioteca.
 */
public class BibliotecaApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final BibliotecaService biblioteca = new BibliotecaService();

    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            switch (opcion) {
                case 1:
                    registrarLibro();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    buscarLibroPorTitulo();
                    break;
                case 4:
                    buscarLibroPorAutor();
                    break;
                case 5:
                    eliminarLibro();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menu Biblioteca ---");
        System.out.println("1. Registrar libro");
        System.out.println("2. Listar libros");
        System.out.println("3. Buscar libro por titulo");
        System.out.println("4. Buscar libro por autor");
        System.out.println("5. Eliminar libro");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static int leerOpcion() {
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void registrarLibro() {
        scanner.nextLine(); // Limpiar buffer
        try {
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            System.out.print("Autor: ");
            String autor = scanner.nextLine();
            System.out.print("Año: ");
            int anio = Integer.parseInt(scanner.nextLine());
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine();
            Libro libro = new Libro(titulo, autor, anio, isbn);
            biblioteca.agregarLibro(libro);
            System.out.println("Libro registrado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: El año debe ser un número entero.");
        }
    }

    private static void listarLibros() {
        List<Libro> libros = biblioteca.listarLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("\nLibros registrados:");
            libros.forEach(System.out::println);
        }
    }

    private static void buscarLibroPorTitulo() {
        scanner.nextLine();
        System.out.print("Ingrese el título a buscar: ");
        String titulo = scanner.nextLine();
        List<Libro> encontrados = biblioteca.buscarPorTitulo(titulo);
        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron libros con ese título.");
        } else {
            encontrados.forEach(System.out::println);
        }
    }

    private static void buscarLibroPorAutor() {
        scanner.nextLine();
        System.out.print("Ingrese el autor a buscar: ");
        String autor = scanner.nextLine();
        List<Libro> encontrados = biblioteca.buscarPorAutor(autor);
        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron libros de ese autor.");
        } else {
            encontrados.forEach(System.out::println);
        }
    }

    private static void eliminarLibro() {
        scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro a eliminar: ");
        String isbn = scanner.nextLine();
        try {
            biblioteca.eliminarLibro(isbn);
            System.out.println("Libro eliminado exitosamente.");
        } catch (LibroNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}
