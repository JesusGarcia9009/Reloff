package reloff.project.org.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import reloff.project.org.entity.Client;
import reloff.project.org.entity.Letter_config;

public class GeneratePdfReport {
	
	private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public static ByteArrayInputStream clientsReport(List<Client> clients, String fix_data_subject, Letter_config letter) throws Exception, IOException {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
        	
        	URL url = GeneratePdfReport.class.getResource("dgPinnacle.png");
        	Image imagen = Image.getInstance(url);
            imagen.setAlignment(Image.ALIGN_CENTER);
            imagen.scaleAbsoluteHeight(150);
            imagen.scaleAbsoluteWidth(300);
        	
            //Image imagen = Image.getInstance("static/img/bicycle.jpg"); 
        	
        	//clientes
        	
        	Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        	
        	Chunk texto1 = new Chunk("Re: Pre-Qualification Notification                                               Thursday, June 27, 2019", FontFactory.getFont(BaseFont.HELVETICA_BOLD, 11));
        	//Chunk texto2 = new Chunk("Dear: " + letter.getClients(), new Font());
        	
        	Paragraph paragraphSubject = new Paragraph();
        	
        	paragraphSubject.setSpacingAfter(25);
        	paragraphSubject.setSpacingBefore(25);
        	paragraphSubject.setIndentationLeft(50);
        	paragraphSubject.setIndentationRight(50);
        	
        	paragraphSubject.add(fix_data_subject);
        	
        	Chunk texto4 = new Chunk("Secure by ", headFont);
        	Chunk texto5 = new Chunk("Secure by the property located at: " + letter.getLocation(), FontFactory.getFont(BaseFont.HELVETICA, 8));
        	
        	Paragraph paragraphData = new Paragraph();
        	
        	paragraphData.setSpacingAfter(25);
        	paragraphData.setSpacingBefore(25);
        	paragraphData.setIndentationLeft(50);
        	paragraphData.setIndentationRight(50);
        	
        	paragraphData.add("Loan Terms: " + String.valueOf(letter.getLoanTerm()) + "\n");
        	paragraphData.add("Sales Price: "+ String.valueOf(letter.getPrice()) + "\n");
        	paragraphData.add("Loan Amount: "+ String.valueOf(letter.getLoanAmount()) + "\n");
        	paragraphData.add("LTV: "+ String.valueOf(letter.getLtv()) + "\n");
        	paragraphData.add("Loan Type: "+ String.valueOf(letter.getLoanType()) + "\n");
        	
        	Paragraph paragraphLorem = new Paragraph();
        	
        	paragraphLorem.setSpacingAfter(25);
        	paragraphLorem.setSpacingBefore(25);
        	paragraphLorem.setIndentationLeft(50);
        	paragraphLorem.setIndentationRight(50);
        	
            paragraphLorem.add("Specific conditions required prior to closing include the following" + "\n");
            paragraphLorem.add("1) Evidence of Hazard Insurance and Flood insurance (if	required)" + "\n");
            paragraphLorem.add("2) Satisfactory property appraisal." + "\n");
            paragraphLorem.add("3) Clear title work." + "\n");
            paragraphLorem.add("4) Any other condition as per the underwriter request." + "\n");
            
            Paragraph paragraphNoel = new Paragraph();
        	
            paragraphNoel.setSpacingAfter(25);
            paragraphNoel.setSpacingBefore(25);
            paragraphNoel.setAlignment(Element.ALIGN_JUSTIFIED);
            paragraphNoel.setIndentationLeft(50);
            paragraphNoel.setIndentationRight(50);
            
            paragraphNoel.add("Noel Veitia" + "\n");
            paragraphNoel.add("NMLS: 277729" + "\n");
            paragraphNoel.add("Your Home Financing Partner." + "\n");
            paragraphNoel.add("Mortgage Loan Originator" + "\n");
            
        	Paragraph saltolinea1 = new Paragraph();
        	//saltolinea1.setLeading((float) 1.0);
            saltolinea1.add("\n");
            
            Paragraph saltolinea2 = new Paragraph();
            saltolinea2.add("\n\n");

            PdfWriter.getInstance(document, out);
            document.open();
            
            document.add(imagen);
            document.add(saltolinea1);
            document.add(texto1);
            document.add(saltolinea1);
            ///document.add(texto2);
            
            document.add(paragraphSubject);
            
            document.add(texto4);
            document.add(saltolinea1);
            document.add(texto5);
            document.add(saltolinea1);
            
            document.add(paragraphData);
            
            document.add(saltolinea1);
            
            document.add(paragraphLorem);
            
            document.add(saltolinea2);
            document.add(paragraphNoel);
            
            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    
    
    public static ByteArrayInputStream ReportTable(PdfPTable table, Chunk texto, String pie) throws Exception, IOException {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
        	
        	URL url = GeneratePdfReport.class.getResource("clients.png");
        	Image imagen = Image.getInstance(url);
            imagen.setAlignment(Image.ALIGN_LEFT);
            
            imagen.scaleAbsoluteHeight(80);
            imagen.scaleAbsoluteWidth(200);
        	
            //Image imagen = Image.getInstance("static/img/bicycle.jpg"); 
        	
        	//clientes
        	
            PdfWriter.getInstance(document, out);
            document.open();
            
            Paragraph saltolinea1 = new Paragraph();
        	//saltolinea1.setLeading((float) 1.0);
            saltolinea1.add("\n");
            
            document.add(imagen);
            document.add(saltolinea1);
            document.add(texto);
            document.add(saltolinea1);
            document.add(table);
            
            Chunk texto1 = new Chunk(pie, FontFactory.getFont(BaseFont.HELVETICA_BOLD, 11));
                        
            Paragraph saltolinea2 = new Paragraph();
            saltolinea2.add("\n\n");
            
            document.add(saltolinea2);
            document.add(texto1);
            
            document.close();

        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    
    
}