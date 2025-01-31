package com.samer.portopiccolo.Controller;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.samer.portopiccolo.Service.DipendentiViewService;
import com.samer.portopiccolo.Service.SettoreService;
import com.samer.portopiccolo.modelli.Dipendenti;
import com.samer.portopiccolo.modelli.DipendentiView;
import com.samer.portopiccolo.modelli.Settore;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PdfController {
	 @Autowired
	 DipendentiViewService dvs;
    @Autowired
    private TemplateEngine templateEngine;
@Autowired
SettoreService ss;
//    @GetMapping("/generate-pdf")
//    public void generatePdf(HttpServletResponse response) throws IOException {
//        response.setContentType("application/pdf");
//        response.setHeader("Content-Disposition", "attachment; filename=\"generated.pdf\"");
//
//        PDDocument document = new PDDocument();
//        PDPage page = new PDPage();
//        document.addPage(page);
//
//        PDPageContentStream contentStream = new PDPageContentStream(document, page);
//        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
//        contentStream.beginText();
//        contentStream.newLineAtOffset(100, 700);
//        contentStream.showText("bla bla");
//
//        contentStream.endText();
//        contentStream.close();
//
//        OutputStream os = response.getOutputStream();
//        document.save(os);
//        document.close();
//    }
//    
//    
//   


//        @GetMapping("/generate-pdf")
//        public void generatePdf(HttpServletResponse response,Model model) throws Exception {
//            try {
//                ArrayList<DipendentiView> dipendenti = dvs.tuttiDipendenti();
//               
//                
//             
//                
//                
//                Context context = new Context();
//                context.setVariable("Lista", dipendenti);
//                String htmlContent = templateEngine.process("Dipendenti/dipendenti2", context);
//
//                response.setContentType("application/pdf");
//                response.setHeader("Content-Disposition", "attachment; filename=\"dipendenti.pdf\"");
//
//                // Crea il PDF
//                try (OutputStream os = response.getOutputStream()) {
//                    PdfRendererBuilder builder = new PdfRendererBuilder();
//                    builder.useFastMode();
//                    builder.withHtmlContent(htmlContent, "/");
//                    builder.toStream(os);
//                    builder.run();
//                   
//                }
//            } catch (Exception e) {
//                e.printStackTrace(); // Questo ti aiuterà a vedere più informazioni sugli errori
//                throw new RuntimeException("Error generating PDF", e);
//            }
//        }
    
    @GetMapping("/generate-pdf")
    public void generatePdf(@RequestParam(value = "settore", required = false) String settore, HttpServletResponse response,Model model) throws Exception {
    
    	try {
            // Filtra i dipendenti in base al cognome, se è stato passato un parametro di ricerca
            List<DipendentiView> dipendenti;
            long ListaDipendenti = dvs.dipendentiConteggio();
            model.addAttribute("countSettore",ss.contaSettore());
            model.addAttribute("settore", new Settore());
            model.addAttribute("lDipendenti", ListaDipendenti);
            ArrayList<Settore> settoreList = ss.mostraSettore();
			model.addAttribute("settoreList", settoreList);
			System.out.println(settore);

            if (settore != null && !settore.isEmpty()) {
                dipendenti = dvs.findBySettore(settore);
                // Usa il servizio per cercare per cognome
            } else {
                dipendenti = dvs.tuttiDipendenti(); // Se non ci sono ricerche, prendi tutti i dipendenti
            
            }

            // Crea il contesto di Thymeleaf con i dipendenti filtrati
            Context context = new Context();
            context.setVariable("Lista", dipendenti);
            context.setVariable("count", dipendenti.size());
            context.setVariable("settoreNome", settore);
            // Processa il template Thymeleaf
            String htmlContent = templateEngine.process("Dipendenti/dipendenti2", context);
          
            // Imposta le proprietà per la risposta PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"dipendenti_"+settore+".pdf\"");

            // Crea il PDF e scrivilo sulla risposta
            try (OutputStream os = response.getOutputStream()) {
                PdfRendererBuilder builder = new PdfRendererBuilder();
          
                builder.useFastMode();
                builder.withHtmlContent(htmlContent, "/");
                builder.toStream(os);
                builder.run();
                //System.out.println(htmlContent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error generating PDF", e);
        }
    }

    
    
    }

