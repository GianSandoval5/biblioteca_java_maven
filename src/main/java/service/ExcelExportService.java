package service;

import model.Libro;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Servicio para exportar datos de la biblioteca a archivos Excel.
 */
public class ExcelExportService {
    
    /**
     * Exporta la lista de libros a un archivo Excel.
     * 
     * @param libros Lista de libros a exportar
     * @param nombreArchivo Nombre del archivo Excel a crear
     * @throws IOException Si ocurre un error durante la escritura del archivo
     */
    public void exportarLibrosAExcel(List<Libro> libros, String nombreArchivo) throws IOException {
        // Crear un nuevo workbook
        Workbook workbook = new XSSFWorkbook();
        
        // Crear una hoja de trabajo
        Sheet sheet = workbook.createSheet("Libros de la Biblioteca");
        
        // Crear estilos para el encabezado
        CellStyle headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        
        // Crear estilo para las celdas de datos
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        
        // Crear fila de encabezados
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Título", "Autor", "Año", "ISBN"};
        
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
        
        // Llenar los datos de los libros
        int rowNum = 1;
        for (Libro libro : libros) {
            Row row = sheet.createRow(rowNum++);
            
            Cell cellTitulo = row.createCell(0);
            cellTitulo.setCellValue(libro.getTitulo());
            cellTitulo.setCellStyle(dataStyle);
            
            Cell cellAutor = row.createCell(1);
            cellAutor.setCellValue(libro.getAutor());
            cellAutor.setCellStyle(dataStyle);
            
            Cell cellAnio = row.createCell(2);
            cellAnio.setCellValue(libro.getAnio());
            cellAnio.setCellStyle(dataStyle);
            
            Cell cellIsbn = row.createCell(3);
            cellIsbn.setCellValue(libro.getIsbn());
            cellIsbn.setCellStyle(dataStyle);
        }
        
        // Ajustar automáticamente el ancho de las columnas
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        // Escribir el archivo
        try (FileOutputStream fileOut = new FileOutputStream(nombreArchivo)) {
            workbook.write(fileOut);
        }
        
        // Cerrar el workbook
        workbook.close();
    }
    
    /**
     * Genera un nombre de archivo con timestamp para evitar sobrescribir archivos existentes.
     * 
     * @return Nombre del archivo con timestamp
     */
    public String generarNombreArchivoConTimestamp() {
        long timestamp = System.currentTimeMillis();
        return "biblioteca_libros_" + timestamp + ".xlsx";
    }
    
    /**
     * Exporta los libros con un nombre de archivo predeterminado basado en timestamp.
     * 
     * @param libros Lista de libros a exportar
     * @return Nombre del archivo creado
     * @throws IOException Si ocurre un error durante la exportación
     */
    public String exportarLibrosConNombreAutomatico(List<Libro> libros) throws IOException {
        String nombreArchivo = generarNombreArchivoConTimestamp();
        exportarLibrosAExcel(libros, nombreArchivo);
        return nombreArchivo;
    }
}
