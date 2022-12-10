/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stock.panic.services;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import com.stock.panic.model.Product;
import com.stock.panic.repository.ReportsRepositoryInterface;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.bson.types.ObjectId;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;

/**
 *
 * @author mauri42
 */
public class ReportsService {
        
    private final ReportsRepositoryInterface reportsRepository;
    
    public ReportsService(ReportsRepositoryInterface reportsRepository){
     
        this.reportsRepository = reportsRepository;
    }
    
    public String positiveStock(HttpServletRequest request){
        
        HttpSession session=request.getSession(); 
        
        String conta_id = session.getAttribute("conta_id").toString();
        
        String pathPDF = "/tmp/" + conta_id + ".pdf";
              
        ObjectId newContaId = new ObjectId(conta_id);
        
        try{
            
             List<Product> products = reportsRepository.positiveStock(newContaId);
             
            PDPage myPage = new PDPage(PDRectangle.A4);
            PDDocument mainDocument = new PDDocument();

            PDPageContentStream contentStream = new PDPageContentStream(mainDocument, myPage);

            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 14);
            contentStream.newLineAtOffset(50, 800);
            contentStream.showText("Relatório estoque disponível.");
            contentStream.endText();
           
            
            //Dummy Table
            float margin = 40;
        // starting y position is whole page height subtracted by top and bottom margin
            float yStartNewPage = myPage.getMediaBox().getHeight() - (2 * margin);
        // we want table across whole page width (subtracted by left and right margin ofcourse)
            float tableWidth = myPage.getMediaBox().getWidth() - (2 * margin);

            boolean drawContent = true;
            float yStart = yStartNewPage;
            int bottomMargin = 70;
        // y position is your coordinate of top left corner of the table
            int yPosition = 780;

            BaseTable table = new BaseTable(yPosition, yStartNewPage, bottomMargin, tableWidth, margin, mainDocument, myPage, true, drawContent);


            Row<PDPage> headerRow = table.createRow(15f);
            Cell<PDPage> headerProduct = headerRow.createCell(30, "PRODUTO");
            Cell<PDPage> headerCod = headerRow.createCell(39, "COD DE BARRAS");
            Cell<PDPage> headerQtd = headerRow.createCell(30, "QUANTIDADE");
            table.addHeaderRow(headerRow);

            for(int i = 0; i < products.size(); i++){
                
                Integer qtd = products.get(i).getQtd();
       
                Row<PDPage> row = table.createRow(12);
                row.createCell(30, products.get(i).getDescricao());
                row.createCell(39, products.get(i).getCodBarras());
                row.createCell(30,qtd.toString());
                
            }
            
            table.draw();
            
            contentStream.close();
            mainDocument.addPage(myPage);
            mainDocument.save(pathPDF);
            mainDocument.close();
            
            Path path = Paths.get(pathPDF);
           
            byte[] fileContent = Files.readAllBytes(path);
            return Base64.getEncoder().encodeToString(fileContent);

        }catch (IOException e){
            
            return null;
        }
        
       
    }
    
}
