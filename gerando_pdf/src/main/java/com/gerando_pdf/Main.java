/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gerando_pdf;

import com.gerando_pdf.PdfModel;
import com.itextpdf.text.BadElementException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Willi
 */
public class Main {

    public static void main(String[] args) {
        PdfModel model = new PdfModel();
        model.setNome("Willian Moreno");
        model.setEmail("willian_moreno@outlook.com");
        model.setMensagem("Lorem ipsum dolor sit amet, consectetur adipiscing elit, "
                + "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
                + "Neque aliquam vestibulum morbi blandit. Sit amet porttitor eget dolor morbi. "
                + "Maecenas accumsan lacus vel facilisis volutpat est velit. In fermentum posuere "
                + "urna nec tincidunt praesent semper feugiat. Nec feugiat nisl pretium fusce id. "
                + "Quis risus sed vulputate odio ut enim blandit volutpat. Leo a diam sollicitudin "
                + "tempor id eu nisl. Est ultricies integer quis auctor elit sed vulputate mi. "
                + "Convallis a cras semper auctor neque vitae tempus. Vel pharetra vel turpis "
                + "nunc eget lorem. Fermentum posuere urna nec tincidunt. Velit ut tortor pretium "
                + "viverra suspendisse potenti nullam. Non blandit massa enim nec. Tempor orci "
                + "eu lobortis elementum nibh tellus molestie.");

        Main main = new Main();
        main.gerarPdf(model);
        //main.teste();
    }

    public void teste() {
        System.out.println("Path: " + System.getProperty("user.home"));
        System.out.println("Path: " + System.getProperty("user.dir"));
    }

    public void gerarPdf(PdfModel pdf) {
        Document document = new Document();
        try {
            String pathDesktop = System.getProperty("user.home").toString() + "/Desktop";
            String pathImage = System.getProperty("user.dir").toString() + "/src/main/java/assets/logojava.png";
            PdfWriter.getInstance(document, new FileOutputStream("GerandoPdf.pdf"));
            document.open();
            document.setPageSize(PageSize.A3);
            document.setMargins(35, 25, 35, 25);

            Image figura = Image.getInstance(pathImage);
            figura.scaleToFit(figura.getScaledWidth() / 4, figura.getScaledHeight() / 4);
            figura.setAlignment(1);
            document.add(figura);

            document.add(new Paragraph("\n\n"));

            Paragraph p = new Paragraph("Olá, " + pdf.getNome().toString());
            p.setAlignment(1);
            document.add(new Paragraph(p));

            document.add(new Paragraph("\n"));

            Paragraph p2 = new Paragraph("E-mail: " + pdf.getEmail().toString());
            p2.setAlignment(0);
            p2.setFirstLineIndent(25);
            document.add(new Paragraph(p2));

            document.add(new Paragraph("\n"));

            Paragraph p3 = new Paragraph(pdf.getMensagem().toString());
            p3.setAlignment(0);
            p3.setFirstLineIndent(25);
            document.add(new Paragraph(p3));

            System.out.println("PDF gerado com sucesso!");
        } catch (DocumentException | FileNotFoundException e) {
            Logger.getLogger(Class.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Erro ao gerar PDF");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao gerar PDF");
        } finally {
            document.close();
        }

        try {
            Desktop.getDesktop().open(new File("GerandoPdf.pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
