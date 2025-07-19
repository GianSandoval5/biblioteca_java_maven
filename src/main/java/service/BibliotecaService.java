package service;

import model.Libro;
import exception.LibroNoEncontradoException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio que gestiona la lógica de la biblioteca.
 */
public class BibliotecaService {
    private List<Libro> libros;

    public BibliotecaService() {
        libros = new ArrayList<>();
    }

    /**
     * Agrega un libro a la biblioteca.
     */
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    /**
     * Lista todos los libros.
     */
    public List<Libro> listarLibros() {
        return new ArrayList<>(libros);
    }

    /**
     * Busca libros por título.
     */
    public List<Libro> buscarPorTitulo(String titulo) {
        return libros.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .collect(Collectors.toList());
    }

    /**
     * Busca libros por autor.
     */
    public List<Libro> buscarPorAutor(String autor) {
        return libros.stream()
                .filter(l -> l.getAutor().equalsIgnoreCase(autor))
                .collect(Collectors.toList());
    }

    /**
     * Elimina un libro por ISBN.
     */
    public void eliminarLibro(String isbn) throws LibroNoEncontradoException {
        boolean eliminado = libros.removeIf(l -> l.getIsbn().equals(isbn));
        if (!eliminado) {
            throw new LibroNoEncontradoException("No se encontró un libro con el ISBN proporcionado.");
        }
    }
    
    /**
     * Exporta todos los libros a un archivo Excel.
     * 
     * @return Nombre del archivo Excel creado
     * @throws IOException Si ocurre un error durante la exportación
     */
    public String exportarLibrosAExcel() throws IOException {
        ExcelExportService excelService = new ExcelExportService();
        return excelService.exportarLibrosConNombreAutomatico(libros);
    }
    
    /**
     * Exporta los libros a un archivo Excel con nombre personalizado.
     * 
     * @param nombreArchivo Nombre del archivo Excel a crear
     * @throws IOException Si ocurre un error durante la exportación
     */
    public void exportarLibrosAExcel(String nombreArchivo) throws IOException {
        ExcelExportService excelService = new ExcelExportService();
        excelService.exportarLibrosAExcel(libros, nombreArchivo);
    }
}
