package seedu.address.model.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.ObservableList;
import seedu.address.model.ingredient.Ingredient;



/**
 * Exports the given arguments to a pdf file.
 */
public class PdfExporter {

    /**
     * Exports the given list of {@code ingredients} from the cart to a pdf file.
     * @param ingredients The list of ingredients in the cart.
     * @throws IOException if cart.pdf is open and therefore unmodifiable.
     */
    public static void exportCart(ObservableList<Ingredient> ingredients) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("cart.pdf"));

        document.open();

        Font font = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Chunk title = new Chunk("Shopping List\n\n", font);
        Phrase phrase = new Phrase();
        phrase.add(title);
        Paragraph paragraph = new Paragraph();
        paragraph.add(phrase);
        paragraph.setAlignment(Element.ALIGN_CENTER);

        PdfPTable table = new PdfPTable(2);
        addTableHeader(table);
        addRows(table, ingredients);

        document.add(paragraph);
        document.add(table);
        document.close();
    }

    /**
     * Adds rows to a table for exporting a cart.
     */
    private static void addRows(PdfPTable table, ObservableList<Ingredient> ingredients) {
        ingredients.forEach(ingredient -> {
            table.addCell(ingredient.getName().toString());
            table.addCell(ingredient.getQuantity().toString());
        });
    }

    /**
     * Adds headers to a table for exporting a cart.
     */
    private static void addTableHeader(PdfPTable table) {
        Stream.of("Name", "Quantity")
            .forEach(columnTitle -> {
                Font bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
                PdfPCell header = new PdfPCell();
                header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                Phrase phrase = new Phrase(columnTitle, bold);
                header.setPhrase(phrase);
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(header);
            });
    }

}
