
package crazy.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.InputStream;

public class TicketPDF {
    public static void generar(String numeroTicket, String fecha, String detalles, double total, String ruta) {
        Document document = new Document(new Rectangle(226, 500), 15, 15, 15, 15);
        
        try {
            PdfWriter.getInstance(document, new FileOutputStream(ruta));
            document.open();

            // === LOGO ===
            try {
                InputStream is = TicketPDF.class.getResourceAsStream("/crazy/images/logoCrazy.jpg"); // cambia el nombre
                if (is != null) {
                    byte[] bytes = is.readAllBytes();
                    Image logo = Image.getInstance(bytes);
                    logo.scaleToFit(60, 60);
                    logo.setAlignment(Element.ALIGN_CENTER);
                    document.add(logo);
                    document.add(new Paragraph(" "));
                }
            } catch (Exception e) {
                System.out.println("Logo no cargado");
            }

            // === TÍTULO ===
            Font fontTitulo = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Paragraph titulo = new Paragraph("CRAZY BURGER'S", fontTitulo);
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);

            Font fontSub = new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC);
            Paragraph sub = new Paragraph("SIGEP", fontSub);
            sub.setAlignment(Element.ALIGN_CENTER);
            document.add(sub);

            document.add(new Paragraph(" "));

            // === DATOS ===
            Font fontSmall = new Font(Font.FontFamily.HELVETICA, 8);
            document.add(new Paragraph("Ticket: " + numeroTicket, fontSmall));
            document.add(new Paragraph("Fecha: " + fecha, fontSmall));
            document.add(new Paragraph("--------------------------------"));

            // === PRODUCTOS EN TABLA ===
            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{50, 15, 35});

            Font fontTable = new Font(Font.FontFamily.COURIER, 8);

            String[] lineas = detalles.split("\n");
            for (String linea : lineas) {
                if (linea.trim().isEmpty()) continue;
                
                // Ejemplo: "Volcano b  x3   Q108.0"
                String[] partes = linea.trim().split("\\s+x");
                if (partes.length >= 2) {
                    String nombre = partes[0].trim();
                    String resto = partes[1].trim();
                    String[] cantPrecio = resto.split("\\s+Q");
                    
                    String cantidad = cantPrecio.length > 0 ? cantPrecio[0].trim() : "";
                    String precio = cantPrecio.length > 1 ? "Q" + cantPrecio[1].trim() : "";

                    PdfPCell c1 = new PdfPCell(new Phrase(nombre, fontTable));
                    PdfPCell c2 = new PdfPCell(new Phrase(cantidad, fontTable));
                    PdfPCell c3 = new PdfPCell(new Phrase(precio, fontTable));
                    
                    c1.setBorder(Rectangle.NO_BORDER);
                    c2.setBorder(Rectangle.NO_BORDER);
                    c3.setBorder(Rectangle.NO_BORDER);
                    c3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    
                    table.addCell(c1);
                    table.addCell(c2);
                    table.addCell(c3);
                }
            }
            document.add(table);

            document.add(new Paragraph("--------------------------------"));

            // === TOTAL ===
            Font fontTotal = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
            Paragraph totalP = new Paragraph("TOTAL: Q" + String.format("%.2f", total), fontTotal);
            totalP.setAlignment(Element.ALIGN_RIGHT);
            document.add(totalP);

            document.add(new Paragraph(" "));
            document.add(new Paragraph("--------------------------------"));

            // === PIE ===
            Paragraph pie = new Paragraph("Presente en caja\n¡Gracias!", fontSmall);
            pie.setAlignment(Element.ALIGN_CENTER);
            document.add(pie);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
