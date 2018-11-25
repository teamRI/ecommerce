package fr.adaming.model;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.Base64.OutputStream;

public class CreatePdf {

	Commande co;
	 public void writePdf(OutputStream outputStream) throws Exception {
	        Document document = new Document();
	        PdfWriter.getInstance(document, outputStream);
	         
	        document.open();
	         
	        document.addTitle("Récapitulatif commande n°"+co.getId());
	        document.addSubject("Récapitulatif commande");
	        document.addKeywords("iText, email");
	        document.addAuthor("ECommerce");
	        document.addCreator("ECommerce");
	         
	        Paragraph paragraph = new Paragraph();
	        paragraph.add(new Chunk("hello!"));
	        document.add(paragraph);
	         
	        document.close();
	    }
	     
}
