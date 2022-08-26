package com.HUSRTbdBiomedica.service;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.springframework.beans.factory.annotation.Autowired;

import com.HUSRTbdBiomedica.app.entity.Hoja_vida;
import com.HUSRTbdBiomedica.app.entity.Mantenimiento_preventivo;
import com.HUSRTbdBiomedica.app.entity.Protocolo_preventivo;
import com.HUSRTbdBiomedica.app.entity.Reporte;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfCell;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;






public class PdfGenarator {
	
	
	
	
	private String image = "./src/main/resources/static/images/Escudo_husrt.png";
	
	public ByteArrayOutputStream getPDF(Reporte reporte, Mantenimiento_preventivo mtto, List<Protocolo_preventivo> protocolos) throws DocumentException, IOException {

        // Creamos la instancia de memoria en la que se escribirá el archivo temporalmente
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        Document document = new Document(PageSize.LETTER);
        document.setMargins(5, 5, 20, 10);
        
        PdfWriter writer = PdfWriter.getInstance(document, bos);
       
        document.open();
        PdfContentByte contentByte = writer.getDirectContent();
       
        Image logo = Image.getInstance(image); 
        logo.setAlignment(Image.ALIGN_CENTER);
        
        logo.scaleAbsolute(65, 35);
        //contentByte.beginText();
        String codigo = "CÓDIGO IB-F-24";
        String Version = "VERSION: 02";
        String fechaformato = "Fecha:16/08/2022";
        String hospital = "E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA";
        String Encabezado = "REPORTE DE MANTENIMIENTO DIGITAL HRCATCH";
        Font fuenteTitulo = new Font();
        fuenteTitulo.setSize(11);
        fuenteTitulo.setStyle(Font.BOLD);
        
        Font fuenteTituloHospital = new Font();
        fuenteTituloHospital.setSize(10);
        fuenteTituloHospital.setStyle(Font.BOLD);

        Font negrita = new Font();
        negrita.setStyle(Font.BOLD);
        negrita.setSize(7);
        
        Font writers = new Font();
        writers.setStyle(Font.BOLD);
        writers.setSize(8);
        
        Font rta = new Font();
        rta.setStyle(Font.NORMAL);
        rta.setSize(10);
        
        Font fontred = new Font();
        fontred.setSize(10);
        fontred.setStyle(Font.BOLD);
        fontred.setColor(100, 0, 0);;
         


        Chunk titulo1 = new Chunk(hospital);
        Chunk titulo2 = new Chunk(Encabezado);
        
        Chunk code = new Chunk(codigo);
        Chunk vs = new Chunk(Version);
        Chunk date = new Chunk(fechaformato);
        //titulo.setUnderline(2f, -2f);

        titulo1.setFont(fuenteTituloHospital);
        titulo2.setFont(fuenteTitulo);
        code.setFont(negrita);
        vs.setFont(negrita);
        date.setFont(negrita);
  
        
       

        

        Chunk firma = new Chunk("__________________ FIRMA");
        firma.setFont(fuenteTituloHospital);
        Chunk reportstyle = new Chunk("REPORTE No.");
        reportstyle.setFont(fuenteTituloHospital);
        
        Chunk numreportestyle = new Chunk(String.valueOf(reporte.getNumero_reporte()));
        numreportestyle.setFont(fontred);
        
        
        Chunk datestyle = new Chunk("FECHA: ");
        datestyle.setFont(fuenteTituloHospital);
        
        Chunk datertastyle = new Chunk(String.valueOf(reporte.getFecha()));
        datertastyle.setFont(rta);
        
        Chunk equipostyle = new Chunk("EQUIPO: ");
        equipostyle.setFont(fuenteTituloHospital);
        
        Chunk equiportastyle = new Chunk(String.valueOf(reporte.getNombre_equipo()));
        equiportastyle.setFont(rta);
        
        Chunk marcastyle = new Chunk("MARCA: ");
        marcastyle.setFont(fuenteTituloHospital);
        
        Chunk marcartastyle = new Chunk(reporte.getMarca());
        marcartastyle.setFont(rta);
        
        Chunk modelostyle = new Chunk("MODELO:");
        modelostyle.setFont(fuenteTituloHospital);
        
        Chunk modelortastyle = new Chunk(reporte.getModelo());
        modelortastyle.setFont(rta);
        
        Chunk seriestyle = new Chunk("SERIE: ");
        seriestyle.setFont(fuenteTituloHospital);
        
        Chunk seriertastyle = new Chunk(reporte.getSerie());
        seriertastyle.setFont(rta);
        
        Chunk placastyle = new Chunk("INVENTARIO:");
        placastyle.setFont(fuenteTituloHospital);
        
        Chunk placartastyle = new Chunk(reporte.getPlaca_inventario());
        placartastyle.setFont(rta);
        
        int Periodicidad  = reporte.getEquipo().getPeriodicidad();
        String freq = new String();
        if(Periodicidad ==1 ) {
        	freq = "ANUAL";
        }
        else if(Periodicidad ==2) {
        	freq = "SEMESTRAL";
        }
        else if(Periodicidad ==3) {
        	freq = "TRIMESTRAL";
        }
        else {
        	freq = "CUATRIMESTRAL";
        }
        
        Chunk freqstyle = new Chunk("PERIODICIDAD: ");
        freqstyle.setFont(fuenteTituloHospital);
        
        Chunk freqrtastyle = new Chunk(freq);
        freqrtastyle.setFont(rta);
        
        Chunk serviceestyle = new Chunk("SERVICIO:");
        serviceestyle.setFont(fuenteTituloHospital);
        
        Chunk servicertastyle = new Chunk(reporte.getServicio());
        servicertastyle.setFont(rta);
        
        Chunk ubstyle = new Chunk("UBICACIÓN: ");
        ubstyle.setFont(fuenteTituloHospital);
        
        Chunk ubrtastyle = new Chunk(reporte.getUbicacion());
        ubrtastyle.setFont(rta);
        		
        PdfPTable tabla = new PdfPTable(5);

        PdfPCell celda0 = new PdfPCell(new Phrase(code));
        celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        celda0.setMinimumHeight(40);
        PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
        celda1.setColspan(3);
        celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        PdfPCell celda2 = new PdfPCell(logo);
        celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
        celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        PdfPCell celda3 = new PdfPCell(new Phrase(vs));
        celda3.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        
        PdfPCell celda4 = new PdfPCell(new Phrase(titulo2));
        celda4.setColspan(3);
        celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        PdfPCell celda5 = new PdfPCell(new Phrase(date));
        celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        

        tabla.addCell(celda0);
        tabla.addCell(celda1);
        tabla.addCell(celda2);
        tabla.addCell(celda3);
        tabla.addCell(celda4);
        tabla.addCell(celda5);
        
        PdfPTable tabla2 = new PdfPTable(10);
        PdfPCell reportenm = new PdfPCell(new Phrase(reportstyle));
        reportenm.setColspan(2);
        PdfPCell numreporte = new PdfPCell(new Phrase(numreportestyle));
        numreporte.setColspan(3);
        PdfPCell celdadatename = new PdfPCell(new Phrase(datestyle));
        celdadatename.setColspan(2);
        PdfPCell celdadate = new PdfPCell(new Phrase(datertastyle));
        celdadate.setColspan(3);
        
        PdfPCell equiponame = new PdfPCell(new Phrase(equipostyle));
        equiponame.setColspan(2);
        PdfPCell equiporta = new PdfPCell(new Phrase(equiportastyle));
        equiporta.setColspan(3);
        PdfPCell marcaname = new PdfPCell(new Phrase(marcastyle));
        marcaname.setColspan(2);
        PdfPCell marcarta = new PdfPCell(new Phrase(marcartastyle));
        marcarta.setColspan(3); 
        
        PdfPCell modeloname = new PdfPCell(new Phrase(modelostyle));
        modeloname.setColspan(2);
        PdfPCell modelorta = new PdfPCell(new Phrase(modelortastyle));
        modelorta.setColspan(3);
        PdfPCell seriename = new PdfPCell(new Phrase(seriestyle));
        seriename.setColspan(2);
        PdfPCell serierta = new PdfPCell(new Phrase(seriertastyle));
        serierta.setColspan(3); 
        
        PdfPCell placaname = new PdfPCell(new Phrase(placastyle));
        placaname.setColspan(2);
        PdfPCell placarta = new PdfPCell(new Phrase(placartastyle));
        placarta.setColspan(3);
        PdfPCell pname = new PdfPCell(new Phrase(freqstyle));
        pname.setColspan(2);
        PdfPCell prta = new PdfPCell(new Phrase(freqrtastyle));
        prta.setColspan(3); 
        
        PdfPCell servicioname = new PdfPCell(new Phrase(serviceestyle));
        servicioname.setColspan(2);
        PdfPCell serviciorta = new PdfPCell(new Phrase(servicertastyle));
        serviciorta.setColspan(3);
        PdfPCell ubicacionname = new PdfPCell(new Phrase(ubstyle));
        ubicacionname.setColspan(2);
        PdfPCell ubicacionrta = new PdfPCell(new Phrase(ubrtastyle));
        ubicacionrta.setColspan(3); 
        
        tabla.setSpacingAfter(10);
        
        tabla2.addCell(reportenm);
        tabla2.addCell(numreporte);
        tabla2.addCell(celdadatename);
        tabla2.addCell(celdadate);
        
        tabla2.addCell(equiponame);
        tabla2.addCell(equiporta);
        tabla2.addCell(marcaname);
        tabla2.addCell(marcarta);
        
        tabla2.addCell(modeloname);
        tabla2.addCell(modelorta);
        tabla2.addCell(seriename);
        tabla2.addCell(serierta);
        
        tabla2.addCell(placaname);
        tabla2.addCell(placarta);
        tabla2.addCell(pname);
        tabla2.addCell(prta);

        tabla2.addCell(servicioname);
        tabla2.addCell(serviciorta);
        tabla2.addCell(ubicacionname);
        tabla2.addCell(ubicacionrta);
        
        tabla2.setSpacingAfter(10);
        
        PdfPTable tabla3 = new PdfPTable(7);
        
        
        
        String pd = new String();
        String pv = new String();
        String cr = new String();
        String ot = new String();
        
        int tipommtto = reporte.getTipo_mantenimiento();
        if(tipommtto ==1 ) {
        	pd = "PREDICTIVO: ";
        	pv = "PREVENTIVO: ";
        	cr = "CORRECTIVO: ";
        	ot = "OTRO: X";
        }
        else if(tipommtto ==2) {
        	pd = "PREDICTIVO: ";
        	pv = "PREVENTIVO: ";
        	cr = "CORRECTIVO: X";
        	ot = "OTRO: ";
        }
        else if(tipommtto ==3) {
        	pd = "PREDICTIVO: ";
        	pv = "PREVENTIVO: X";
        	cr = "CORRECTIVO: ";
        	ot = "OTRO: ";
        }
        else {
        	pd = "PREDICTIVO: X";
        	pv = "PREVENTIVO: ";
        	cr = "CORRECTIVO: ";
        	ot = "OTRO: ";
        }
        Chunk typemttostyle = new Chunk("TIPO DE MANTENIMIENTO");
        typemttostyle.setFont(fuenteTituloHospital);
        
        Chunk predstyle = new Chunk(pd);
        predstyle.setFont(fuenteTituloHospital);
        
        Chunk prevstyle = new Chunk(pv);
        prevstyle.setFont(fuenteTituloHospital);
        
        Chunk corstyle = new Chunk(cr);
        corstyle.setFont(fuenteTituloHospital);
        
        Chunk otrstyle = new Chunk(ot);
        otrstyle.setFont(fuenteTituloHospital);
        
        Chunk typefstyle = new Chunk("TIPO DE FALLA");
        typefstyle.setFont(fuenteTituloHospital);
        
        
        PdfPCell tipomname = new PdfPCell(new Phrase(typemttostyle));
        tipomname.setColspan(7);
        tipomname.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        
        PdfPCell predictivo = new PdfPCell(new Phrase(predstyle));
        predictivo.setColspan(2);
        PdfPCell preventivo = new PdfPCell(new Phrase(prevstyle));
        preventivo.setColspan(2);
        PdfPCell correctivo = new PdfPCell(new Phrase(corstyle));
        correctivo.setColspan(2); 
        correctivo.setMinimumHeight(20);
        PdfPCell otro = new PdfPCell(new Phrase(otrstyle));
        
        PdfPCell tipofname = new PdfPCell(new Phrase(typefstyle));
        tipofname.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tipofname.setColspan(7);
        String tipofalla = reporte.getTipo_falla();
        String desgaste = new String();
        String opi = new String();
        String caex = new String();
        String ac = new String();
        String desc = new String();
        String sfall = new String();
        String otf = new String();
    
        if(tipofalla.contains("1") ) {
        	desgaste = "1.DESGASTE: X";
        	opi = "2.OPERACIÓN INDEBIDA: ";
        	caex = "3.CAUSA EXTERNA: ";
        	ac = "4.ACCESORIOS: ";
        	desc = "5.DESCONOCIDO: ";
        	sfall = "6.SIN FALLAS: ";
        	otf = "7.OTRO: ";
        }
        else if(tipofalla.contains("2")) {
        	desgaste = "1.DESGASTE: ";
        	opi = "2.OPERACIÓN INDEBIDA: X";
        	caex = "3.CAUSA EXTERNA: ";
        	ac = "4.ACCESORIOS: ";
        	desc = "5.DESCONOCIDO: ";
        	sfall = "6.SIN FALLAS: ";
        	otf = "7.OTRO: ";
        }
        else if(tipofalla.contains("3")) {
        	desgaste = "1.DESGASTE: ";
        	opi = "2.OPERACIÓN INDEBIDA: ";
        	caex = "3.CAUSA EXTERNA: X";
        	ac = "4.ACCESORIOS: ";
        	desc = "5.DESCONOCIDO: ";
        	sfall = "6.SIN FALLAS: ";
        	otf = "7.OTRO: ";
        }
        else if(tipofalla.contains("4")) {
        	desgaste = "1.DESGASTE: ";
        	opi = "2.OPERACIÓN INDEBIDA: ";
        	caex = "3.CAUSA EXTERNA: ";
        	ac = "4.ACCESORIOS: X";
        	desc = "5.DESCONOCIDO: ";
        	sfall = "6.SIN FALLAS: ";
        	otf = "7.OTRO: ";
        }
        else if(tipofalla.contains("5")) {
        	desgaste = "1.DESGASTE: ";
        	opi = "2.OPERACIÓN INDEBIDA: ";
        	caex = "3.CAUSA EXTERNA: ";
        	ac = "4.ACCESORIOS: ";
        	desc = "5.DESCONOCIDO: X";
        	sfall = "6.SIN FALLAS: ";
        	otf = "7.OTRO: ";
        }
        else if(tipofalla.contains("6")) {
        	desgaste = "1.DESGASTE: ";
        	opi = "2.OPERACIÓN INDEBIDA: ";
        	caex = "3.CAUSA EXTERNA: ";
        	ac = "4.ACCESORIOS: ";
        	desc = "5.DESCONOCIDO: ";
        	sfall = "6.SIN FALLAS: X";
        	otf = "7.OTRO: ";
        }
        else {
        	desgaste = "1.DESGASTE: ";
        	opi = "2.OPERACIÓN INDEBIDA: ";
        	caex = "3.CAUSA EXTERNA: ";
        	ac = "4.ACCESORIOS: ";
        	desc = "5.DESCONOCIDO: ";
        	sfall = "6.SIN FALLAS: ";
        	otf = "7.OTRO: X";
        }
        
        
        Chunk desestilo = new Chunk(desgaste);
        desestilo.setFont(writers);
        
        Chunk opstyle = new Chunk(opi);
        opstyle.setFont(writers);
        
        Chunk cexstyle = new Chunk(caex);
        cexstyle.setFont(writers);
        
        Chunk accstyle = new Chunk(ac);
        accstyle.setFont(writers);
        
        Chunk desstyle = new Chunk(desc);
        desstyle.setFont(negrita);
        
        Chunk sfstyle = new Chunk(sfall);
        sfstyle.setFont(writers);

        Chunk ofstyle = new Chunk(otf);
        ofstyle.setFont(writers);
        // Asignamos la variable ByteArrayOutputStream bos donde se escribirá el documento
        PdfPCell f1 = new PdfPCell(new Phrase(desestilo));
   
        PdfPCell f2 = new PdfPCell(new Phrase(opstyle));
      
        PdfPCell f3 = new PdfPCell(new Phrase(cexstyle));
        
        PdfPCell f4 = new PdfPCell(new Phrase(accstyle));
        PdfPCell f5 = new PdfPCell(new Phrase(desstyle));
        PdfPCell f6 = new PdfPCell(new Phrase(sfstyle));
        PdfPCell f7 = new PdfPCell(new Phrase(ofstyle));
        
        Chunk motstyle = new Chunk("MOTIVO DEL LLAMADO: ");
        motstyle.setFont(fuenteTituloHospital);
        Chunk motrtastyle = new Chunk(reporte.getMotivo());
        motrtastyle.setFont(rta);
        
        Paragraph motivo = new Paragraph();
        motivo.add(motstyle);
        motivo.add(motrtastyle);
    	
        Chunk ttitlestyle = new Chunk("TRABAJO REALIZADO: ");
        ttitlestyle.setFont(fuenteTituloHospital);
        
        Chunk trtastyle = new Chunk(reporte.getTrabajo_realizado());
        trtastyle.setFont(rta);
        
        Paragraph trealizado = new Paragraph();
        trealizado.add(ttitlestyle);
        trealizado.add(trtastyle);
        
        trealizado.setLeading(5.0f, 1.0f);
        
        PdfPCell llamado = new PdfPCell(motivo);
        llamado.setColspan(7);
        llamado.setMinimumHeight(50);
      
        
        PdfPCell trabajo = new PdfPCell(trealizado);
        trabajo.setColspan(7);
        trabajo.setMinimumHeight(210);
        tabla3.addCell(tipomname);
        tabla3.addCell(predictivo);
        tabla3.addCell(preventivo);
        tabla3.addCell(correctivo);
        tabla3.addCell(otro);
        
        tabla3.addCell(tipofname);
        tabla3.addCell(f1);
        tabla3.addCell(f2);
        tabla3.addCell(f3);
        tabla3.addCell(f4);
        tabla3.addCell(f5);
        tabla3.addCell(f6);
        tabla3.addCell(f7);
        tabla3.addCell(llamado);
        tabla3.addCell(trabajo);
        tabla3.setSpacingAfter(10);
        
        PdfPTable tabla4 = new PdfPTable(7);
        Chunk repstyle = new Chunk("REPUESTOS UTILIZADOS");
        repstyle.setFont(fuenteTituloHospital);
        
        Chunk descrstyle = new Chunk("DESCRIPCIÓN");
        descrstyle.setFont(fuenteTituloHospital);
        
        Chunk cantstyle = new Chunk("CANTIDAD");
        cantstyle.setFont(fuenteTituloHospital);
        
        Chunk cegstyle = new Chunk("C. EGRESO");
        cegstyle.setFont(fuenteTituloHospital);
        
        PdfPCell repuestosname = new PdfPCell(new Phrase(repstyle));
        repuestosname.setColspan(7);
        repuestosname.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        
        
        PdfPCell descres = new PdfPCell(new Phrase(descrstyle));
        descres.setColspan(4);
        descres.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        
        PdfPCell canres = new PdfPCell(new Phrase(cantstyle));
        canres.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        PdfPCell cegres = new PdfPCell(new Phrase(cegstyle));
        cegres.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cegres.setColspan(2);
        
        Chunk rtarptostyle = new Chunk(reporte.getRepuesto_cambiado());
        rtarptostyle.setFont(rta);
        
        Chunk cegresostyle = new Chunk(reporte.getComprobante_ingreso());
        cegresostyle.setFont(rta);
        
        PdfPCell description = new PdfPCell(new Phrase(rtarptostyle));
        description.setColspan(4);
        description.setMinimumHeight(70);
        
        PdfPCell amount = new PdfPCell(new Phrase(""));
        
        PdfPCell egress = new PdfPCell(new Phrase(cegresostyle));
        egress.setColspan(2);
        
        
        tabla4.addCell(repuestosname);
        tabla4.addCell(descres);
        tabla4.addCell(canres);
        tabla4.addCell(cegres);
        
        tabla4.addCell(description);
        tabla4.addCell(amount);
        tabla4.addCell(egress);
        tabla4.setSpacingAfter(10);

        PdfPTable tabla5 = new PdfPTable(1);
        
        Chunk obtitlestyle = new Chunk("OBSERVACIONES: ");
        obtitlestyle.setFont(fuenteTituloHospital);
        
        Chunk obrtastyle = new Chunk(reporte.getObservaciones());
        obrtastyle.setFont(rta);
        
       
        
        Paragraph obs = new Paragraph();
        obs.add(obtitlestyle);
        obs.add(obrtastyle);
        
        PdfPCell Obser = new PdfPCell(obs);
        Obser.setMinimumHeight(55);
     
        tabla5.addCell(Obser);
        tabla5.setSpacingAfter(10);
        
        PdfPTable tabla6 = new PdfPTable(4);
        Chunk realizadostyle = new Chunk("REALIZADO POR: ");
        realizadostyle.setFont(fuenteTituloHospital);
        
        Chunk realizadortastyle = new Chunk(reporte.getAutor_realizado());
        realizadortastyle.setFont(rta);
        
        Chunk recibidostyle = new Chunk("RECIBIDO POR: ");
        recibidostyle.setFont(fuenteTituloHospital);
        
        Chunk recibidortastyle = new Chunk(reporte.getAutor_recibido());
        recibidortastyle.setFont(rta);
        
        Chunk cedula = new Chunk("CEDULA: ");
        cedula.setFont(fuenteTituloHospital);
        
        recibidostyle.setFont(fuenteTituloHospital);
        
        Phrase realize = new Phrase();
        realize.add(realizadostyle);
        realize.add(realizadortastyle);
        
        Phrase recibe = new Phrase();
        recibe.add(recibidostyle);
        recibe.add(recibidortastyle);
        
        PdfPCell realizado = new PdfPCell(realize);
        realizado.setColspan(2);
        realizado.setMinimumHeight(20);
        
        PdfPCell recibido = new PdfPCell(recibe);
        recibido.setColspan(2);
        recibido.setMinimumHeight(20);
        
        
        PdfPCell nombrerea = new PdfPCell(new Phrase(cedula));

        PdfPCell firmrea = new PdfPCell(new Phrase(firma));
        firmrea.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        firmrea.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        firmrea.setMinimumHeight(50);
        
        
        PdfPCell nombrereci = new PdfPCell(new Phrase(cedula));

        PdfPCell firmreci = new PdfPCell(new Phrase(firma));
        firmreci.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        firmreci.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        firmreci.setMinimumHeight(50);
        tabla6.addCell(realizado);
        tabla6.addCell(recibido);
        tabla6.addCell(nombrerea);
        tabla6.addCell(firmrea);
        tabla6.addCell(nombrereci);
        tabla6.addCell(firmreci);
        
        document.add(tabla);
        
        document.add(tabla2);
        document.add(tabla3);
        document.add(tabla4);
        document.add(tabla5);
        document.add(tabla6);
        document.newPage();
        
        if(mtto!=null) {
        	Time timer = mtto.getTiempo_realizacion();
    		LocalTime tiempor = timer.toLocalTime();
    		int hora = tiempor.getHour();int minuto = tiempor.getMinute();
    		int minutos = hora*60+minuto;
    		ArrayList<String> materiales = new ArrayList<String>(Arrays.asList(reporte.getEquipo().getTipo_equipo().getMaterial_consumible().split(",")));
    		ArrayList<String> herramientas = new ArrayList<String>(Arrays.asList(reporte.getEquipo().getTipo_equipo().getHerramienta().split(",")));
    		ArrayList<String> repuestos = new ArrayList<String>(Arrays.asList(reporte.getEquipo().getTipo_equipo().getRepuestos_minimos().split(",")));
    		
    		Chunk rutinatitle = new Chunk("RUTINA DE MANTENIMIENTO PREVENTIVO ");
    		rutinatitle.setFont(fuenteTituloHospital);
    		Chunk mintitle = new Chunk("MINISTERIO DE PROTECCIÓN SOCIAL ");
    		mintitle.setFont(fuenteTituloHospital);
    		Chunk niveltitle = new Chunk("III NIVEL DE ATENCIÓN ");
    		niveltitle.setFont(fuenteTituloHospital);
    		
    		PdfPTable tabla1rutina = new PdfPTable(5);

            PdfPCell cellrutine = new PdfPCell(new Phrase(rutinatitle));
            cellrutine.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            cellrutine.setMinimumHeight(40);
            cellrutine.setRowspan(3);
            
            
            PdfPCell cellmin = new PdfPCell(new Phrase(mintitle));
            cellmin.setColspan(3);
            cellmin.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cellmin.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            
            PdfPCell celllogo = new PdfPCell(logo);
            celllogo.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
            celllogo.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            celllogo.setRowspan(3);
            
            
            PdfPCell cellnivel = new PdfPCell(new Phrase(niveltitle));
            cellnivel.setColspan(3);
            cellnivel.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cellnivel.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            
            tabla1rutina.addCell(cellrutine);
            tabla1rutina.addCell(cellmin);
            tabla1rutina.addCell(celllogo);
            tabla1rutina.addCell(celda1);
            tabla1rutina.addCell(cellnivel);
            tabla1rutina.setSpacingAfter(10);
            
            PdfPTable tabla3rutina = new PdfPTable(7);
            
            Chunk protocoloname = new Chunk("PROTOCOLO ESTABLECIDO POR EL FABRICANTE");
            protocoloname.setFont(fuenteTituloHospital);
            
            Chunk cumplename = new Chunk("CUMPLE");
            cumplename.setFont(fuenteTituloHospital);
            
            Chunk nocumplename = new Chunk("NO CUMPLE");
            nocumplename.setFont(fuenteTituloHospital);
            
            
            PdfPCell pasoprotocolo = new PdfPCell(new Phrase(protocoloname));
            pasoprotocolo.setColspan(5);
            
            PdfPCell checkprotocolo = new PdfPCell(new Phrase(cumplename));
            PdfPCell uncheckprotocolo = new PdfPCell(new Phrase(nocumplename));
            
            tabla3rutina.addCell(pasoprotocolo);
            tabla3rutina.addCell(checkprotocolo);
            tabla3rutina.addCell(uncheckprotocolo);
            
            PdfPCell pasorta = new PdfPCell();
            PdfPCell checkrta = new PdfPCell();
            PdfPCell uncheckrta = new PdfPCell();
            
            Chunk pasostyle = new Chunk("");
            boolean checkpaso = false;
            for(int paso=0;paso<protocolos.size();paso++) {
            	pasostyle = new Chunk(protocolos.get(paso).getPaso());
            	pasostyle.setFont(rta);
            	pasorta = new PdfPCell(new Phrase(pasostyle));
            	pasorta.setColspan(5);
            	checkpaso = protocolos.get(paso).isCumplimiento();
            	if(checkpaso) {
            		checkrta = new PdfPCell(new Phrase("X"));
            		checkrta.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            		uncheckrta = new PdfPCell(new Phrase(""));
            	}
            	else {
            		checkrta = new PdfPCell(new Phrase(""));
            		
            		uncheckrta = new PdfPCell(new Phrase("X"));
            		uncheckrta.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            	}
            	
            	tabla3rutina.addCell(pasorta);
            	tabla3rutina.addCell(checkrta);
            	tabla3rutina.addCell(uncheckrta);
            	
            	
            }
            tabla3rutina.setSpacingAfter(10);
            
            PdfPTable tabla4rutina = new PdfPTable(8);
            
            Chunk materialname = new Chunk("MATERIAL CONSUMIBLE");
            materialname.setFont(fuenteTituloHospital);
            
            Chunk toolsname = new Chunk("HERRAMIENTAS Y EQUIPOS");
            toolsname.setFont(fuenteTituloHospital);
            
            Chunk rptosname = new Chunk("REPUESTOS MÍNIMOS");
            rptosname.setFont(fuenteTituloHospital);
            
            PdfPCell materialnamecell = new PdfPCell(new Phrase(materialname));
            materialnamecell.setColspan(3);
            
            PdfPCell toolnamecell = new PdfPCell(new Phrase(toolsname));
            toolnamecell.setColspan(3);
            
            PdfPCell rptonamecell = new PdfPCell(new Phrase(rptosname));
            rptonamecell.setColspan(2);
            
            Chunk materialstyle = new Chunk("");
            materialstyle.setFont(rta);
            
            PdfPCell materialcell = new PdfPCell();
            materialcell.setColspan(3);
            
            for(int paso=0;paso<materiales.size();paso++) {
            	materialstyle = new Chunk(materiales.get(paso));
            	materialstyle.setFont(rta);
            	materialcell.addElement(materialstyle);
            	
            }
            PdfPCell toolcell = new PdfPCell();
            toolcell.setColspan(3);
            
            for(int paso=0;paso<herramientas.size();paso++) {
            	materialstyle = new Chunk(herramientas.get(paso));
            	materialstyle.setFont(rta);
            	toolcell.addElement(materialstyle);
            	
            }
            PdfPCell rptocell = new PdfPCell();
            rptocell.setColspan(2);
            for(int paso=0;paso<repuestos.size();paso++) {
            	materialstyle = new Chunk(repuestos.get(paso));
            	materialstyle.setFont(rta);
            	rptocell.addElement(materialstyle);
            	
            }
            
            
            tabla4rutina.addCell(materialnamecell);
        	tabla4rutina.addCell(toolnamecell);
        	tabla4rutina.addCell(rptonamecell);
            tabla4rutina.addCell(materialcell);
        	tabla4rutina.addCell(toolcell);
        	tabla4rutina.addCell(rptocell);
        	tabla4rutina.setSpacingAfter(10);
        	
        	PdfPTable tabla5rutina = new PdfPTable(1);
        	obtitlestyle = new Chunk("OBSERVACIONES");
        	obtitlestyle.setFont(fuenteTituloHospital);
        	
        	Obser = new PdfPCell(new Phrase(obtitlestyle));
        	Obser.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        	tabla5rutina.addCell(Obser);
        	
        	obrtastyle = new Chunk(reporte.getObservaciones());
            obrtastyle.setFont(rta);
            
        	Obser = new PdfPCell(new Phrase(obrtastyle));
        	Obser.setMinimumHeight(60);
        	tabla5rutina.addCell(Obser);
        	
        	tabla5rutina.setSpacingAfter(10);
        	
        	
        	PdfPTable tabla6rutina = new PdfPTable(2);
        	
        	Phrase tiempoejec = new Phrase();
        	Chunk tiempotext = new Chunk("TIEMPO DE EJECUCIÓN (APROX. ");
        	tiempotext.setFont(fuenteTituloHospital);
        	tiempoejec.add(tiempotext);
        	tiempotext = new Chunk(String.valueOf(reporte.getEquipo().getTipo_equipo().getTiempo_minutos())+" MINUTOS) :");
        	tiempotext.setFont(fuenteTituloHospital);
        	tiempoejec.add(tiempotext);
        	
        	Obser = new PdfPCell(tiempoejec);
        	tabla6rutina.addCell(Obser);
        	tiempotext = new Chunk(String.valueOf(minutos));
        	Obser = new PdfPCell(new Phrase(tiempotext));
        	tabla6rutina.addCell(Obser);
        	
        	realizadostyle = new Chunk("FIRMA DE QUIEN REALIZA");
        	realizadostyle.setFont(fuenteTituloHospital);
        	
        	recibidostyle = new Chunk("FIRMA DE QUIEN RECIBE");
        	recibidostyle.setFont(fuenteTituloHospital);
        	
        	realizado = new PdfPCell();
        	realizado.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        	realizado.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        	realizado.addElement(new Chunk("_____________________"));
        	realizado.addElement(realizadostyle);
        	
        	
        	recibido = new PdfPCell();
        	recibido.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        	recibido.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        	recibido.addElement(new Chunk("_____________________"));
        	recibido.addElement(recibidostyle);
        	
        	
        	
        	realizado.setMinimumHeight(50);
        	
        	tabla6rutina.addCell(realizado);
        	tabla6rutina.addCell(recibido);
        	
        	document.add(tabla1rutina);
            document.add(tabla2);
            document.add(tabla3rutina);
            document.add(tabla4rutina);
            document.add(tabla5rutina);
            document.add(tabla6rutina);
        }
        
      
  
        //contentByte.endText();
        document.close();
        // Retornamos la variable al finalizar
        return bos;
        
    }
	
	public ByteArrayOutputStream getoriginalPDF() throws DocumentException, IOException {

        // Creamos la instancia de memoria en la que se escribirá el archivo temporalmente
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        Document document = new Document(PageSize.LETTER);
        document.setMargins(5, 5, 20, 10);
        
        PdfWriter writer = PdfWriter.getInstance(document, bos);
       
        document.open();
     
       
        Image logo = Image.getInstance(image); 
        logo.setAlignment(Image.ALIGN_CENTER);
        
        logo.scaleAbsolute(65, 35);
        //contentByte.beginText();
        String codigo = "CÓDIGO IB-F-24";
        String Version = "VERSION: 02";
        String fechaformato = "Fecha:16/08/2022";
        String hospital = "E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA";
        String Encabezado = "REPORTE DE MANTENIMIENTO DIGITAL HRCATCH";
        Font fuenteTitulo = new Font();
        fuenteTitulo.setSize(11);
        fuenteTitulo.setStyle(Font.BOLD);
        
        Font fuenteTituloHospital = new Font();
        fuenteTituloHospital.setSize(10);
        fuenteTituloHospital.setStyle(Font.BOLD);

        Font negrita = new Font();
        negrita.setStyle(Font.BOLD);
        negrita.setSize(7);
        
        Font writers = new Font();
        writers.setStyle(Font.BOLD);
        writers.setSize(8);
        
        Font rta = new Font();
        rta.setStyle(Font.NORMAL);
        rta.setSize(10);
        
        Font fontred = new Font();
        fontred.setSize(10);
        fontred.setStyle(Font.BOLD);
        fontred.setColor(100, 0, 0);;
         


        Chunk titulo1 = new Chunk(hospital);
        Chunk titulo2 = new Chunk(Encabezado);
        
        Chunk code = new Chunk(codigo);
        Chunk vs = new Chunk(Version);
        Chunk date = new Chunk(fechaformato);
        //titulo.setUnderline(2f, -2f);

        titulo1.setFont(fuenteTituloHospital);
        titulo2.setFont(fuenteTitulo);
        code.setFont(negrita);
        vs.setFont(negrita);
        date.setFont(negrita);
  
        
       

        

        Chunk firma = new Chunk("__________________ FIRMA");
        firma.setFont(fuenteTituloHospital);
        Chunk reportstyle = new Chunk("REPORTE No.");
        reportstyle.setFont(fuenteTituloHospital);
        
        Chunk numreportestyle = new Chunk("");
        numreportestyle.setFont(fontred);
        
        
        Chunk datestyle = new Chunk("FECHA: ");
        datestyle.setFont(fuenteTituloHospital);
        
        Chunk datertastyle = new Chunk("");
        datertastyle.setFont(rta);
        
        Chunk equipostyle = new Chunk("EQUIPO: ");
        equipostyle.setFont(fuenteTituloHospital);
        
        Chunk equiportastyle = new Chunk("");
        equiportastyle.setFont(rta);
        
        Chunk marcastyle = new Chunk("MARCA: ");
        marcastyle.setFont(fuenteTituloHospital);
        
        Chunk marcartastyle = new Chunk("");
        marcartastyle.setFont(rta);
        
        Chunk modelostyle = new Chunk("MODELO:");
        modelostyle.setFont(fuenteTituloHospital);
        
        Chunk modelortastyle = new Chunk("");
        modelortastyle.setFont(rta);
        
        Chunk seriestyle = new Chunk("SERIE: ");
        seriestyle.setFont(fuenteTituloHospital);
        
        Chunk seriertastyle = new Chunk("");
        seriertastyle.setFont(rta);
        
        Chunk placastyle = new Chunk("INVENTARIO:");
        placastyle.setFont(fuenteTituloHospital);
        
        Chunk placartastyle = new Chunk("");
        placartastyle.setFont(rta);
      
        
        Chunk freqstyle = new Chunk("PERIODICIDAD: ");
        freqstyle.setFont(fuenteTituloHospital);
        
        Chunk freqrtastyle = new Chunk("");
        freqrtastyle.setFont(rta);
        
        Chunk serviceestyle = new Chunk("SERVICIO:");
        serviceestyle.setFont(fuenteTituloHospital);
        
        Chunk servicertastyle = new Chunk("");
        servicertastyle.setFont(rta);
        
        Chunk ubstyle = new Chunk("UBICACIÓN: ");
        ubstyle.setFont(fuenteTituloHospital);
        
        Chunk ubrtastyle = new Chunk("");
        ubrtastyle.setFont(rta);
        		
        PdfPTable tabla = new PdfPTable(5);

        PdfPCell celda0 = new PdfPCell(new Phrase(code));
        celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        celda0.setMinimumHeight(40);
        PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
        celda1.setColspan(3);
        celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        PdfPCell celda2 = new PdfPCell(logo);
        celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
        celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        PdfPCell celda3 = new PdfPCell(new Phrase(vs));
        celda3.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        
        PdfPCell celda4 = new PdfPCell(new Phrase(titulo2));
        celda4.setColspan(3);
        celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        PdfPCell celda5 = new PdfPCell(new Phrase(date));
        celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        

        tabla.addCell(celda0);
        tabla.addCell(celda1);
        tabla.addCell(celda2);
        tabla.addCell(celda3);
        tabla.addCell(celda4);
        tabla.addCell(celda5);
        
        PdfPTable tabla2 = new PdfPTable(10);
        PdfPCell reportenm = new PdfPCell(new Phrase(reportstyle));
        reportenm.setColspan(2);
        PdfPCell numreporte = new PdfPCell(new Phrase(numreportestyle));
        numreporte.setColspan(3);
        PdfPCell celdadatename = new PdfPCell(new Phrase(datestyle));
        celdadatename.setColspan(2);
        PdfPCell celdadate = new PdfPCell(new Phrase(datertastyle));
        celdadate.setColspan(3);
        
        PdfPCell equiponame = new PdfPCell(new Phrase(equipostyle));
        equiponame.setColspan(2);
        PdfPCell equiporta = new PdfPCell(new Phrase(equiportastyle));
        equiporta.setColspan(3);
        PdfPCell marcaname = new PdfPCell(new Phrase(marcastyle));
        marcaname.setColspan(2);
        PdfPCell marcarta = new PdfPCell(new Phrase(marcartastyle));
        marcarta.setColspan(3); 
        
        PdfPCell modeloname = new PdfPCell(new Phrase(modelostyle));
        modeloname.setColspan(2);
        PdfPCell modelorta = new PdfPCell(new Phrase(modelortastyle));
        modelorta.setColspan(3);
        PdfPCell seriename = new PdfPCell(new Phrase(seriestyle));
        seriename.setColspan(2);
        PdfPCell serierta = new PdfPCell(new Phrase(seriertastyle));
        serierta.setColspan(3); 
        
        PdfPCell placaname = new PdfPCell(new Phrase(placastyle));
        placaname.setColspan(2);
        PdfPCell placarta = new PdfPCell(new Phrase(placartastyle));
        placarta.setColspan(3);
        PdfPCell pname = new PdfPCell(new Phrase(freqstyle));
        pname.setColspan(2);
        PdfPCell prta = new PdfPCell(new Phrase(freqrtastyle));
        prta.setColspan(3); 
        
        PdfPCell servicioname = new PdfPCell(new Phrase(serviceestyle));
        servicioname.setColspan(2);
        PdfPCell serviciorta = new PdfPCell(new Phrase(servicertastyle));
        serviciorta.setColspan(3);
        PdfPCell ubicacionname = new PdfPCell(new Phrase(ubstyle));
        ubicacionname.setColspan(2);
        PdfPCell ubicacionrta = new PdfPCell(new Phrase(ubrtastyle));
        ubicacionrta.setColspan(3); 
        
        tabla.setSpacingAfter(10);
        
        tabla2.addCell(reportenm);
        tabla2.addCell(numreporte);
        tabla2.addCell(celdadatename);
        tabla2.addCell(celdadate);
        
        tabla2.addCell(equiponame);
        tabla2.addCell(equiporta);
        tabla2.addCell(marcaname);
        tabla2.addCell(marcarta);
        
        tabla2.addCell(modeloname);
        tabla2.addCell(modelorta);
        tabla2.addCell(seriename);
        tabla2.addCell(serierta);
        
        tabla2.addCell(placaname);
        tabla2.addCell(placarta);
        tabla2.addCell(pname);
        tabla2.addCell(prta);

        tabla2.addCell(servicioname);
        tabla2.addCell(serviciorta);
        tabla2.addCell(ubicacionname);
        tabla2.addCell(ubicacionrta);
        
        tabla2.setSpacingAfter(10);
        
        PdfPTable tabla3 = new PdfPTable(7);
        
        
        
        String pd = new String();
        String pv = new String();
        String cr = new String();
        String ot = new String();
        pd = "PREDICTIVO: ";
    	pv = "PREVENTIVO: ";
    	cr = "CORRECTIVO: ";
    	ot = "OTRO: ";        
        Chunk typemttostyle = new Chunk("TIPO DE MANTENIMIENTO");
        typemttostyle.setFont(fuenteTituloHospital);
        
        Chunk predstyle = new Chunk(pd);
        predstyle.setFont(fuenteTituloHospital);
        
        Chunk prevstyle = new Chunk(pv);
        prevstyle.setFont(fuenteTituloHospital);
        
        Chunk corstyle = new Chunk(cr);
        corstyle.setFont(fuenteTituloHospital);
        
        Chunk otrstyle = new Chunk(ot);
        otrstyle.setFont(fuenteTituloHospital);
        
        Chunk typefstyle = new Chunk("TIPO DE FALLA");
        typefstyle.setFont(fuenteTituloHospital);
        
        
        PdfPCell tipomname = new PdfPCell(new Phrase(typemttostyle));
        tipomname.setColspan(7);
        tipomname.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        
        PdfPCell predictivo = new PdfPCell(new Phrase(predstyle));
        predictivo.setColspan(2);
        PdfPCell preventivo = new PdfPCell(new Phrase(prevstyle));
        preventivo.setColspan(2);
        PdfPCell correctivo = new PdfPCell(new Phrase(corstyle));
        correctivo.setColspan(2); 
        correctivo.setMinimumHeight(20);
        PdfPCell otro = new PdfPCell(new Phrase(otrstyle));
        
        PdfPCell tipofname = new PdfPCell(new Phrase(typefstyle));
        tipofname.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        tipofname.setColspan(7);
     
        String desgaste = new String();
        String opi = new String();
        String caex = new String();
        String ac = new String();
        String desc = new String();
        String sfall = new String();
        String otf = new String();
        desgaste = "1.DESGASTE: ";
    	opi = "2.OPERACIÓN INDEBIDA: ";
    	caex = "3.CAUSA EXTERNA: ";
    	ac = "4.ACCESORIOS: ";
    	desc = "5.DESCONOCIDO: ";
    	sfall = "6.SIN FALLAS: ";
    	otf = "7.OTRO: ";        
        
        Chunk desestilo = new Chunk(desgaste);
        desestilo.setFont(writers);
        
        Chunk opstyle = new Chunk(opi);
        opstyle.setFont(writers);
        
        Chunk cexstyle = new Chunk(caex);
        cexstyle.setFont(writers);
        
        Chunk accstyle = new Chunk(ac);
        accstyle.setFont(writers);
        
        Chunk desstyle = new Chunk(desc);
        desstyle.setFont(negrita);
        
        Chunk sfstyle = new Chunk(sfall);
        sfstyle.setFont(writers);

        Chunk ofstyle = new Chunk(otf);
        ofstyle.setFont(writers);
        // Asignamos la variable ByteArrayOutputStream bos donde se escribirá el documento
        PdfPCell f1 = new PdfPCell(new Phrase(desestilo));
   
        PdfPCell f2 = new PdfPCell(new Phrase(opstyle));
      
        PdfPCell f3 = new PdfPCell(new Phrase(cexstyle));
        
        PdfPCell f4 = new PdfPCell(new Phrase(accstyle));
        PdfPCell f5 = new PdfPCell(new Phrase(desstyle));
        PdfPCell f6 = new PdfPCell(new Phrase(sfstyle));
        PdfPCell f7 = new PdfPCell(new Phrase(ofstyle));
        
        Chunk motstyle = new Chunk("MOTIVO DEL LLAMADO: ");
        motstyle.setFont(fuenteTituloHospital);
        Chunk motrtastyle = new Chunk("");
        motrtastyle.setFont(fuenteTituloHospital);
        
        Paragraph motivo = new Paragraph();
        motivo.add(motstyle);
        motivo.add(motrtastyle);
    	
        Chunk ttitlestyle = new Chunk("TRABAJO REALIZADO: ");
        ttitlestyle.setFont(fuenteTituloHospital);
        
        Chunk trtastyle = new Chunk("");
        trtastyle.setFont(rta);
        
        Paragraph trealizado = new Paragraph();
        trealizado.add(ttitlestyle);
        trealizado.add(trtastyle);
        
        trealizado.setLeading(5.0f, 1.0f);
        
        PdfPCell llamado = new PdfPCell(motivo);
        llamado.setColspan(7);
        llamado.setMinimumHeight(50);
      
        
        PdfPCell trabajo = new PdfPCell(trealizado);
        trabajo.setColspan(7);
        trabajo.setMinimumHeight(210);
        tabla3.addCell(tipomname);
        tabla3.addCell(predictivo);
        tabla3.addCell(preventivo);
        tabla3.addCell(correctivo);
        tabla3.addCell(otro);
        
        tabla3.addCell(tipofname);
        tabla3.addCell(f1);
        tabla3.addCell(f2);
        tabla3.addCell(f3);
        tabla3.addCell(f4);
        tabla3.addCell(f5);
        tabla3.addCell(f6);
        tabla3.addCell(f7);
        tabla3.addCell(llamado);
        tabla3.addCell(trabajo);
        tabla3.setSpacingAfter(10);
        
        PdfPTable tabla4 = new PdfPTable(7);
        Chunk repstyle = new Chunk("REPUESTOS UTILIZADOS");
        repstyle.setFont(fuenteTituloHospital);
        
        Chunk descrstyle = new Chunk("DESCRIPCIÓN");
        descrstyle.setFont(fuenteTituloHospital);
        
        Chunk cantstyle = new Chunk("CANTIDAD");
        cantstyle.setFont(fuenteTituloHospital);
        
        Chunk cegstyle = new Chunk("C. EGRESO");
        cegstyle.setFont(fuenteTituloHospital);
        
        PdfPCell repuestosname = new PdfPCell(new Phrase(repstyle));
        repuestosname.setColspan(7);
        repuestosname.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        
        
        PdfPCell descres = new PdfPCell(new Phrase(descrstyle));
        descres.setColspan(4);
        descres.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        
        PdfPCell canres = new PdfPCell(new Phrase(cantstyle));
        canres.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        PdfPCell cegres = new PdfPCell(new Phrase(cegstyle));
        cegres.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cegres.setColspan(2);
        
        Chunk rtarptostyle = new Chunk("");
        rtarptostyle.setFont(rta);
        
        Chunk cegresostyle = new Chunk("");
        cegresostyle.setFont(rta);
        
        PdfPCell description = new PdfPCell(new Phrase(rtarptostyle));
        description.setColspan(4);
        description.setMinimumHeight(70);
        
        PdfPCell amount = new PdfPCell(new Phrase(""));
        
        PdfPCell egress = new PdfPCell(new Phrase(cegresostyle));
        egress.setColspan(2);
        
        
        tabla4.addCell(repuestosname);
        tabla4.addCell(descres);
        tabla4.addCell(canres);
        tabla4.addCell(cegres);
        
        tabla4.addCell(description);
        tabla4.addCell(amount);
        tabla4.addCell(egress);
        tabla4.setSpacingAfter(10);

        PdfPTable tabla5 = new PdfPTable(1);
        
        Chunk obtitlestyle = new Chunk("OBSERVACIONES: ");
        obtitlestyle.setFont(fuenteTituloHospital);
        
        Chunk obrtastyle = new Chunk("");
        obrtastyle.setFont(rta);
        
       
        
        Paragraph obs = new Paragraph();
        obs.add(obtitlestyle);
        obs.add(obrtastyle);
        
        PdfPCell Obser = new PdfPCell(obs);
        Obser.setMinimumHeight(55);
     
        tabla5.addCell(Obser);
        tabla5.setSpacingAfter(10);
        
        PdfPTable tabla6 = new PdfPTable(4);
        Chunk realizadostyle = new Chunk("REALIZADO POR: ");
        realizadostyle.setFont(fuenteTituloHospital);
        
        Chunk realizadortastyle = new Chunk("");
        realizadortastyle.setFont(rta);
        
        Chunk recibidostyle = new Chunk("RECIBIDO POR: ");
        recibidostyle.setFont(fuenteTituloHospital);
        
        Chunk recibidortastyle = new Chunk("");
        recibidortastyle.setFont(rta);
        
        Chunk cedula = new Chunk("CEDULA: ");
        cedula.setFont(fuenteTituloHospital);
        
        recibidostyle.setFont(fuenteTituloHospital);
        
        Phrase realize = new Phrase();
        realize.add(realizadostyle);
        realize.add(realizadortastyle);
        
        Phrase recibe = new Phrase();
        recibe.add(recibidostyle);
        recibe.add(recibidortastyle);
        
        PdfPCell realizado = new PdfPCell(realize);
        realizado.setColspan(2);
        realizado.setMinimumHeight(20);
        
        PdfPCell recibido = new PdfPCell(recibe);
        recibido.setColspan(2);
        recibido.setMinimumHeight(20);
        
        
        PdfPCell nombrerea = new PdfPCell(new Phrase(cedula));

        PdfPCell firmrea = new PdfPCell(new Phrase(firma));
        firmrea.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        firmrea.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        firmrea.setMinimumHeight(50);
        
        
        PdfPCell nombrereci = new PdfPCell(new Phrase(cedula));

        PdfPCell firmreci = new PdfPCell(new Phrase(firma));
        firmreci.setVerticalAlignment(PdfPCell.ALIGN_BOTTOM);
        firmreci.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        firmreci.setMinimumHeight(50);
        tabla6.addCell(realizado);
        tabla6.addCell(recibido);
        tabla6.addCell(nombrerea);
        tabla6.addCell(firmrea);
        tabla6.addCell(nombrereci);
        tabla6.addCell(firmreci);
        
        document.add(tabla);
        
        document.add(tabla2);
        document.add(tabla3);
        document.add(tabla4);
        document.add(tabla5);
        document.add(tabla6);

        document.close();
        // Retornamos la variable al finalizar
        return bos;
        
    }
	public ByteArrayOutputStream getHVPDF(Hoja_vida hoja_vida) throws DocumentException, IOException {

        // Creamos la instancia de memoria en la que se escribirá el archivo temporalmente
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        Document document = new Document(PageSize.LETTER);
        document.setMargins(5, 5, 20, 10);
        
        PdfWriter writer = PdfWriter.getInstance(document, bos);
       
        document.open();
     
       
        Image logo = Image.getInstance(image); 
        logo.setAlignment(Image.ALIGN_CENTER);
        
        logo.scaleAbsolute(65, 35);
        //contentByte.beginText();
        
        Font fuenteTitulo = new Font();
        fuenteTitulo.setSize(11);
        fuenteTitulo.setStyle(Font.BOLD);
        
        Font fuenteTituloHospital = new Font();
        fuenteTituloHospital.setSize(10);
        fuenteTituloHospital.setStyle(Font.BOLD);

        Font fuenteEnunciados = new Font();
        fuenteEnunciados.setSize(9);
        fuenteEnunciados.setStyle(Font.BOLD);
        
        Font negrita = new Font();
        negrita.setStyle(Font.BOLD);
        negrita.setSize(7);
        
        Font writers = new Font();
        writers.setStyle(Font.BOLD);
        writers.setSize(8);
        
        Font rta = new Font();
        rta.setStyle(Font.NORMAL);
        rta.setSize(8);
        
        Font rtasmall = new Font();
        rtasmall.setStyle(Font.NORMAL);
        rtasmall.setSize(7);
        
        Font rtaultrasmall = new Font();
        rtaultrasmall.setStyle(Font.NORMAL);
        rtaultrasmall.setSize(6);
        
        Font correo = new Font();
        correo.setStyle(Font.NORMAL);
        correo.setSize(7);
     
        
        Chunk titulo1 = new Chunk("E.S.E HOSPITAL UNIVERSITARIO SAN RAFAEL DE TUNJA");
        Chunk titulo2 = new Chunk("HOJA DE VIDA DIGITAL EQUIPO BIOMEDICO HRCATCH");
        Chunk titulo3 = new Chunk("III NIVEL DE ATENCIÓN");
        Chunk code = new Chunk("CÓDIGO IB-F-25");
        Chunk vs = new Chunk("VERSION: 02");
        Chunk date = new Chunk("Fecha:16/08/2022");
        //titulo.setUnderline(2f, -2f);

        titulo1.setFont(fuenteTituloHospital);
        titulo2.setFont(fuenteTitulo);
        titulo3.setFont(fuenteTituloHospital);
        code.setFont(negrita);
        vs.setFont(negrita);
        date.setFont(negrita);
  
        PdfPTable tabla = new PdfPTable(5);

        PdfPCell celda0 = new PdfPCell(new Phrase(code));
        celda0.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        celda0.setMinimumHeight(40);
        celda0.setRowspan(2);
        
        PdfPCell celda1 = new PdfPCell(new Phrase(titulo1));
        celda1.setColspan(3);
        celda1.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celda1.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        
        PdfPCell celda2 = new PdfPCell(logo);
        celda2.setHorizontalAlignment(PdfCell.ALIGN_CENTER);
        celda2.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        celda2.setRowspan(2);
        
        PdfPCell celda4 = new PdfPCell(new Phrase(titulo3));
        celda4.setColspan(3);
        celda4.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celda4.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        
        PdfPCell celda3 = new PdfPCell(new Phrase(vs));
        celda3.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celda3.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        
        PdfPCell celda6 = new PdfPCell(new Phrase(titulo2));
        celda6.setColspan(3);
        celda6.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        celda6.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        
        
        PdfPCell celda5 = new PdfPCell(new Phrase(date));
        celda5.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        

        tabla.addCell(celda0);
        tabla.addCell(celda1);
        tabla.addCell(celda2);
        tabla.addCell(celda4);
        tabla.addCell(celda3);
        tabla.addCell(celda6);
        tabla.addCell(celda5);
        
        tabla.setSpacingAfter(10);
        
        Chunk idenstyle = new Chunk("IDENTIFICACIÓN");
        idenstyle.setFont(fuenteTituloHospital);
        
        Chunk depstyle = new Chunk("DEPARTAMENTO:");
        depstyle.setFont(fuenteEnunciados);
        
        Chunk deprtastyle = new Chunk(hoja_vida.getDepartamento());
        deprtastyle.setFont(rta);
        
        Chunk munstyle = new Chunk("MUNICIPIO:");
        munstyle.setFont(fuenteEnunciados);
        
        Chunk munrtastyle = new Chunk(hoja_vida.getMunicipio());
        munrtastyle.setFont(rta);
        
        Chunk addresstyle = new Chunk("DIRECCIÓN:");
        addresstyle.setFont(fuenteEnunciados);
        
        Chunk addresrtastyle = new Chunk(hoja_vida.getDireccion());
        addresrtastyle.setFont(rta);
        
        Chunk telestyle = new Chunk("TELÉFONO:");
        telestyle.setFont(fuenteEnunciados);
        
        Chunk telertastyle = new Chunk(hoja_vida.getTelefonoinstitucion());
        telertastyle.setFont(rta);
        
        Chunk emailstyle = new Chunk("E-MAIL:");
        emailstyle.setFont(fuenteEnunciados);
        
        Chunk emailrtastyle = new Chunk(hoja_vida.getEmailinstitucion());
        emailrtastyle.setFont(correo);
        
        Chunk codstyle = new Chunk("COD. INTERNACIONAL:");
        codstyle.setFont(fuenteEnunciados);
        
        Chunk codrtastyle = new Chunk(hoja_vida.getCodinternacional());
        codrtastyle.setFont(rta);
        

        Chunk serviceestyle = new Chunk("SERVICIO:");
        serviceestyle.setFont(fuenteEnunciados);
        
        Chunk servicertastyle = new Chunk(hoja_vida.getEquipo().getServicios());
        servicertastyle.setFont(fuenteEnunciados);
        
        Chunk ubstyle = new Chunk("UBICACIÓN: ");
        ubstyle.setFont(fuenteEnunciados);
        
        Chunk ubrtastyle = new Chunk(hoja_vida.getEquipo().getUbicacion());
        ubrtastyle.setFont(fuenteEnunciados);
        
        String pathroute = "./src/main/resources/static"+hoja_vida.getFoto();
    	
        Image photodevice = Image.getInstance(pathroute); 
        photodevice.setAlignment(Image.ALIGN_CENTER);        
        photodevice.scaleAbsolute(80, 70);
        
        PdfPTable tabladhos = new PdfPTable(5);
        
        PdfPCell idencell = new PdfPCell(new Phrase(idenstyle));
        idencell.setColspan(6);
        idencell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        
        PdfPCell fotocell = new PdfPCell(photodevice);
       
        fotocell.setRowspan(4);
        fotocell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
        fotocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        fotocell.setMinimumHeight(80);
        
        tabladhos.addCell(idencell);
        
        tabladhos.addCell(new PdfPCell(new Phrase(depstyle)));
        tabladhos.addCell(new PdfPCell(new Phrase(deprtastyle)));
        tabladhos.addCell(new PdfPCell(new Phrase(munstyle)));
        tabladhos.addCell(new PdfPCell(new Phrase(munrtastyle)));
        tabladhos.addCell(fotocell);
        tabladhos.addCell(new PdfPCell(new Phrase(addresstyle)));
        tabladhos.addCell(new PdfPCell(new Phrase(addresrtastyle)));
        tabladhos.addCell(new PdfPCell(new Phrase(telestyle)));
        tabladhos.addCell(new PdfPCell(new Phrase(telertastyle)));
 
        tabladhos.addCell(new PdfPCell(new Phrase(emailstyle)));
        tabladhos.addCell(new PdfPCell(new Phrase(emailrtastyle)));
        tabladhos.addCell(new PdfPCell(new Phrase(codstyle)));
        tabladhos.addCell(new PdfPCell(new Phrase(codrtastyle)));
    
        tabladhos.addCell(new PdfPCell(new Phrase(serviceestyle)));
   
        tabladhos.addCell(new PdfPCell(new Phrase(servicertastyle)));
        tabladhos.addCell(new PdfPCell(new Phrase(ubstyle)));
        tabladhos.addCell(new PdfPCell(new Phrase(ubrtastyle)));
       
        tabladhos.setSpacingAfter(10);
        
        Chunk dataeqstyle = new Chunk("DATOS DEL EQUIPO");
        dataeqstyle.setFont(fuenteTituloHospital);
        
        Chunk equipostyle = new Chunk("EQUIPO: ");
        equipostyle.setFont(rta);
        
        Chunk equiportastyle = new Chunk(hoja_vida.getEquipo().getNombre_Equipo());
        equiportastyle.setFont(rta);
        
        Chunk marcastyle = new Chunk("MARCA: ");
        marcastyle.setFont(rta);
        
        Chunk marcartastyle = new Chunk(hoja_vida.getEquipo().getMarca());
        marcartastyle.setFont(rta);
        
        Chunk modelostyle = new Chunk("MODELO:");
        modelostyle.setFont(rta);
        
        Chunk modelortastyle = new Chunk(hoja_vida.getEquipo().getModelo());
        modelortastyle.setFont(rta);
        
        Chunk seriestyle = new Chunk("SERIE: ");
        seriestyle.setFont(rta);
        
        Chunk seriertastyle = new Chunk(hoja_vida.getEquipo().getSerie());
        seriertastyle.setFont(rta);
        
        Chunk placastyle = new Chunk("INVENTARIO:");
        placastyle.setFont(rta);
        
        Chunk placartastyle = new Chunk(hoja_vida.getEquipo().getPlaca_inventario());
        placartastyle.setFont(rta);
        
        Chunk anofstyle = new Chunk("AÑO DE FABRICACIÓN:");
        anofstyle.setFont(rtasmall);
        
        Chunk anofrtastyle = new Chunk(String.valueOf(hoja_vida.getAno_fabricacion()));
        anofrtastyle.setFont(rta);
        
        Chunk invimastyle = new Chunk("REGISTRO INVIMA:");
        invimastyle.setFont(rta);
        
        Chunk invimartastyle = new Chunk(hoja_vida.getRegistro_invima());
        invimartastyle.setFont(rta);
 
        Chunk fadqstyle = new Chunk("FORMA DE ADQUISICIÓN");
        fadqstyle.setFont(fuenteTituloHospital);
        
        Chunk xrtastyle = new Chunk("X");
        fadqstyle.setFont(fuenteTituloHospital);
        
        Chunk compradstyle = new Chunk("COMPRA DIRECTA:");
        compradstyle.setFont(rta);
        
        Chunk convstyle = new Chunk("CONVENIO:");
        convstyle.setFont(rta);

        Chunk donadostyle = new Chunk("DONADO:");
        donadostyle.setFont(rta);
        
        Chunk asignminstyle = new Chunk("ASIGNADO POR EL MINISTERIO:");
        asignminstyle.setFont(rta);
        
        Chunk asigngobstyle = new Chunk("ASIGNADO POR LA GOBERNACIÓN:");
        asigngobstyle.setFont(rtasmall);
        
        Chunk comodatostyle = new Chunk("COMODATO:");
        comodatostyle.setFont(rta);
        
        
        Chunk dcomprastyle = new Chunk("DATOS DE LA COMPRA");
        dcomprastyle.setFont(fuenteTituloHospital);
        
        Chunk fcomprastyle = new Chunk("FECHA DE COMPRA:");
        fcomprastyle.setFont(rta);
        
        Chunk fcomprartastyle = new Chunk(String.valueOf(hoja_vida.getFecha_compra()));
        fcomprartastyle.setFont(rta);
        
        Chunk finstallstyle = new Chunk("FECHA DE INSTALACIÓN:");
        finstallstyle.setFont(rta);
        
        Chunk finstallrtastyle = new Chunk(String.valueOf(hoja_vida.getFecha_instalacion()));
        finstallrtastyle.setFont(rta);
        
        
        Chunk fstartstyle = new Chunk("FECHA DE INICIO DE OPERACIÓN:");
        fstartstyle.setFont(rta);
        
        Chunk fstartrtastyle = new Chunk(String.valueOf(hoja_vida.getFecha_iniciooperacion()));
        fstartrtastyle.setFont(rta);
        
        Chunk fvctistyle = new Chunk("FECHA VENCIMIENTO GARANTÍA:");
        fvctistyle.setFont(rta);       
        
        Chunk fvctirtastyle = new Chunk(String.valueOf(hoja_vida.getFecha_vencimientogarantia()));
        fvctirtastyle.setFont(rta);
        
        Chunk fabstyle = new Chunk("FABRICANTE:");
        fabstyle.setFont(rta);
        
        Chunk fabrtastyle = new Chunk(hoja_vida.getFabricante());
        fabrtastyle.setFont(rta);
        
        Chunk paisstyle = new Chunk("PAIS:");
        paisstyle.setFont(rta);
        
        Chunk paisrtastyle = new Chunk(hoja_vida.getPaisfabricante());
        paisrtastyle.setFont(rta);
        
        Chunk fcostostyle = new Chunk("COSTO EN PESOS:");
        fcostostyle.setFont(rta);
        
        Chunk fcostortastyle = new Chunk(hoja_vida.getCosto_compra());
        fcostortastyle.setFont(rta);
        
        Chunk provstyle = new Chunk("PROVEEDOR:");
        provstyle.setFont(rta);
        
        Chunk provrtastyle = new Chunk(hoja_vida.getProveedor());
        provrtastyle.setFont(rta);
        
        Chunk tefpstyle = new Chunk("TELÉFONO PROVEEDOR:");
        tefpstyle.setFont(rta);
        
        Chunk tefprtastyle = new Chunk(hoja_vida.getTelefonoproveedor());
        tefprtastyle.setFont(rta);
        
        Chunk correostyle = new Chunk("CORREO:");
        correostyle.setFont(rta);
        
        Chunk correortastyle = new Chunk(hoja_vida.getCorreoproveedor());
        correortastyle.setFont(rta);
        
        Chunk ciudadstyle = new Chunk("CIUDAD:");
        ciudadstyle.setFont(rta);
        
        Chunk ciudadrtastyle = new Chunk(hoja_vida.getCiudadproveedor());
        ciudadrtastyle.setFont(rta);
        
        Chunk represtyle = new Chunk("REPRESENTANTE:");
        represtyle.setFont(rtaultrasmall);
        
        Chunk reprertastyle = new Chunk(hoja_vida.getRepresentante());
        reprertastyle.setFont(rta);
        
        Chunk tefrepstyle = new Chunk("TELÉFONO REPRESENTANTE:");
        tefrepstyle.setFont(rtaultrasmall);
        
        Chunk tefreprtastyle = new Chunk(hoja_vida.getTelefonorepresentante());
        tefreprtastyle.setFont(rta);
        
        Chunk contractstyle = new Chunk("CONTRATO:");
        contractstyle.setFont(rta);
        
        Chunk contractrtastyle = new Chunk(hoja_vida.getContrato());
        contractrtastyle.setFont(rta);
        
        PdfPTable tablaeqcom = new PdfPTable(16);
        PdfPCell datoequipo = new PdfPCell(new Phrase(dataeqstyle)); 
        datoequipo.setColspan(5);
        PdfPCell datoadq = new PdfPCell(new Phrase(fadqstyle));
        datoadq.setColspan(3);
        PdfPCell datocompra = new PdfPCell(new Phrase(dcomprastyle));
        datocompra.setColspan(8);
        
        tablaeqcom.addCell(datoequipo);
        tablaeqcom.addCell(datoadq);
        tablaeqcom.addCell(datocompra);
        //first row
        datoequipo = new PdfPCell(new Phrase(equipostyle));
        datoequipo.setColspan(2);
        tablaeqcom.addCell(datoequipo);
        datoequipo = new PdfPCell(new Phrase(equiportastyle));
        datoequipo.setColspan(3);
        tablaeqcom.addCell(datoequipo);
        
        datoadq = new PdfPCell(new Phrase(compradstyle));
        datoadq.setColspan(2);
        tablaeqcom.addCell(datoadq);
        if(hoja_vida.isConvenio()) {
        	datoadq = new PdfPCell(new Phrase(xrtastyle));
        	datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	datoadq = new PdfPCell(new Phrase(""));
        }
        
        tablaeqcom.addCell(datoadq);
        
        datocompra = new PdfPCell(new Phrase(fcomprastyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(fcomprartastyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(provstyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(provrtastyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        
        //second row
        datoequipo = new PdfPCell(new Phrase(marcastyle));
        datoequipo.setColspan(2);
        tablaeqcom.addCell(datoequipo);
        datoequipo = new PdfPCell(new Phrase(marcartastyle));
        datoequipo.setColspan(3);
        tablaeqcom.addCell(datoequipo);
        
        datoadq = new PdfPCell(new Phrase(convstyle));
        datoadq.setColspan(2);
        tablaeqcom.addCell(datoadq);
        if(hoja_vida.isCompraddirecta()) {
        	datoadq = new PdfPCell(new Phrase(xrtastyle));
        	datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	datoadq = new PdfPCell(new Phrase(""));
        }
        
        tablaeqcom.addCell(datoadq);
        
        datocompra = new PdfPCell(new Phrase(finstallstyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(finstallrtastyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(tefpstyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(tefprtastyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        
        //third row
        datoequipo = new PdfPCell(new Phrase(modelostyle));
        datoequipo.setColspan(2);
        tablaeqcom.addCell(datoequipo);
        datoequipo = new PdfPCell(new Phrase(modelortastyle));
        datoequipo.setColspan(3);
        tablaeqcom.addCell(datoequipo);
        
        datoadq = new PdfPCell(new Phrase(donadostyle));
        datoadq.setColspan(2);
        tablaeqcom.addCell(datoadq);
        if(hoja_vida.isDonado()) {
        	datoadq = new PdfPCell(new Phrase(xrtastyle));
        	datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	datoadq = new PdfPCell(new Phrase(""));
        }
        
        tablaeqcom.addCell(datoadq);
        
        datocompra = new PdfPCell(new Phrase(fstartstyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(fstartrtastyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(correostyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(correortastyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        
        
        //fourth row
        datoequipo = new PdfPCell(new Phrase(seriestyle));
        datoequipo.setColspan(2);
        tablaeqcom.addCell(datoequipo);
        datoequipo = new PdfPCell(new Phrase(seriertastyle));
        datoequipo.setColspan(3);
        tablaeqcom.addCell(datoequipo);
        
        datoadq = new PdfPCell(new Phrase(asignminstyle));
        datoadq.setColspan(2);
        tablaeqcom.addCell(datoadq);
        if(hoja_vida.isAsignadoporministerio()) {
        	datoadq = new PdfPCell(new Phrase(xrtastyle));
        	datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	datoadq = new PdfPCell(new Phrase(""));
        }
        
        tablaeqcom.addCell(datoadq);
        
        datocompra = new PdfPCell(new Phrase(fvctistyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(fvctirtastyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(ciudadstyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(ciudadrtastyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        
        //fifth row
        datoequipo = new PdfPCell(new Phrase(placastyle));
        datoequipo.setColspan(2);
        tablaeqcom.addCell(datoequipo);
        datoequipo = new PdfPCell(new Phrase(placartastyle));
        datoequipo.setColspan(3);
        tablaeqcom.addCell(datoequipo);
        
        datoadq = new PdfPCell(new Phrase(asigngobstyle));
        datoadq.setRowspan(2);
        datoadq.setColspan(2);
        tablaeqcom.addCell(datoadq);
        if(hoja_vida.isAsignadoporgobernacion()) {
        	datoadq = new PdfPCell(new Phrase(xrtastyle));
        	datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        	datoadq.setRowspan(2);
       
        }
        else {
        	datoadq = new PdfPCell(new Phrase(""));
        	datoadq.setRowspan(2);
        }
        
        tablaeqcom.addCell(datoadq);
        
        datocompra = new PdfPCell(new Phrase(fabstyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(fabrtastyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(represtyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(reprertastyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        
        
        //sixth row
        datoequipo = new PdfPCell(new Phrase(anofstyle));
        datoequipo.setColspan(2);
        tablaeqcom.addCell(datoequipo);
        datoequipo = new PdfPCell(new Phrase(anofrtastyle));
        datoequipo.setColspan(3);
        tablaeqcom.addCell(datoequipo);
        PdfPCell celdanula = new PdfPCell();
        celdanula.setBorder(Rectangle.NO_BORDER);
        tablaeqcom.addCell(celdanula);
     
        datocompra = new PdfPCell(new Phrase(paisstyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(paisrtastyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(tefrepstyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(tefreprtastyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        
        //seven row
        datoequipo = new PdfPCell(new Phrase(invimastyle));
        datoequipo.setColspan(2);
        tablaeqcom.addCell(datoequipo);
        datoequipo = new PdfPCell(new Phrase(invimartastyle));
        datoequipo.setColspan(3);
        tablaeqcom.addCell(datoequipo);
        
        datoadq = new PdfPCell(new Phrase(comodatostyle));
        datoadq.setColspan(2);
        tablaeqcom.addCell(datoadq);
        if(hoja_vida.isComodato()) {
        	datoadq = new PdfPCell(new Phrase(xrtastyle));
        	datoadq.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	datoadq = new PdfPCell(new Phrase(""));
        }
        
        tablaeqcom.addCell(datoadq);
        
        datocompra = new PdfPCell(new Phrase(fcostostyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(fcostortastyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(contractstyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
        datocompra = new PdfPCell(new Phrase(contractrtastyle));
        datocompra.setColspan(2);
        tablaeqcom.addCell(datocompra);
      
        tablaeqcom.setSpacingAfter(10);
                
        //table data
        
        
        Chunk tecnicstyle = new Chunk("REGISTRO TÉCNICO");
        tecnicstyle.setFont(fuenteEnunciados);
        
        Chunk vacrstyle = new Chunk("V");
        vacrstyle.setFont(rta);
        
        Chunk iacrstyle = new Chunk("A");
        iacrstyle.setFont(rta);
        
        Chunk pacrstyle = new Chunk("PSI");
        pacrstyle.setFont(rta);
        
        Chunk wacrstyle = new Chunk("W");
        wacrstyle.setFont(rta);
        
        Chunk fzacrstyle = new Chunk("Hz");
        fzacrstyle.setFont(rta);
        
        Chunk rpmacrstyle = new Chunk("RPM");
        rpmacrstyle.setFont(rta);
        
        Chunk tempacrstyle = new Chunk("°C");
        tempacrstyle.setFont(rta);
        
        Chunk weightacrstyle = new Chunk("Kg");
        weightacrstyle.setFont(rta);
        
        Chunk capacacrstyle = new Chunk("L");
        capacacrstyle.setFont(rta);
        
        
        Chunk vmaxstyle = new Chunk("VOLTAJE MÁXIMO:");
        vmaxstyle.setFont(rta);
        
        Chunk vmaxrtastyle = new Chunk(hoja_vida.getVmaxoperacion());
        vmaxrtastyle.setFont(rta);
        
        Chunk presionstyle = new Chunk("PRESIÓN:");
        presionstyle.setFont(rta);
        
        Chunk presionrtastyle = new Chunk(hoja_vida.getPresion());
        presionrtastyle.setFont(rta);
        
        Chunk vminstyle = new Chunk("VOLTAJE MÍNIMO:");
        vminstyle.setFont(rta);
        
        Chunk vminrtastyle = new Chunk(hoja_vida.getVminoperacion());
        vminrtastyle.setFont(rta);
        
        Chunk velstyle = new Chunk("VELOCIDAD:");
        velstyle.setFont(rta);
        
        Chunk velrtastyle = new Chunk(hoja_vida.getVelocidad());
        velrtastyle.setFont(rta);
        
        Chunk imaxstyle = new Chunk("CORRIENTE MÁXIMA:");
        imaxstyle.setFont(rta);
        
        Chunk imaxrtastyle = new Chunk(hoja_vida.getImaxoperacion());
        imaxrtastyle.setFont(rta);
        
        Chunk tempstyle = new Chunk("TEMPERATURA:");
        tempstyle.setFont(rta);
        
        Chunk temprtastyle = new Chunk(hoja_vida.getTemperatura());
        temprtastyle.setFont(rta);
        
        Chunk iminstyle = new Chunk("CORRIENTE MÍNIMA:");
        iminstyle.setFont(rta);
        
        Chunk iminrtastyle = new Chunk(hoja_vida.getIminoperacion());
        iminrtastyle.setFont(rta);
        
        Chunk weightstyle = new Chunk("PESO:");
        weightstyle.setFont(rta);
        
        Chunk weightrtastyle = new Chunk(hoja_vida.getPeso());
        weightrtastyle.setFont(rta);
        
        Chunk powerstyle = new Chunk("POTENCIA CONSUMIDA:");
        powerstyle.setFont(rta);
        
        Chunk powerrtastyle = new Chunk(hoja_vida.getWconsumida());
        powerrtastyle.setFont(rta);
        
        Chunk capacitystyle = new Chunk("CAPACIDAD:");
        capacitystyle.setFont(rta);
        
        Chunk capacityrtastyle = new Chunk(hoja_vida.getCapacidad());
        capacityrtastyle.setFont(rta);
        
        Chunk frecuencystyle = new Chunk("FRECUENCIA:");
        frecuencystyle.setFont(rta);
        
        Chunk frecuencyrtastyle = new Chunk(hoja_vida.getFrecuencia());
        frecuencyrtastyle.setFont(rta);
        
        Chunk otrotecstyle = new Chunk("OTROS:");
        otrotecstyle.setFont(rta);
        
        Chunk otrotecrtastyle = new Chunk(hoja_vida.getOtrosdatostecnicos());
        otrotecrtastyle.setFont(rta);
        
        Chunk portstyle = new Chunk("EQUIPO PORTATIL:");
        portstyle.setFont(writers);
        
        Chunk portrtastyle = new Chunk("");
        if(hoja_vida.isEquipotipoportatil()) {
        	portrtastyle = new Chunk("SI");
        	portrtastyle.setFont(rta);
        }
        else {
        	portrtastyle = new Chunk("NO");
        	portrtastyle.setFont(rta);
        }
        
        Chunk fijostyle = new Chunk("EQUIPO FIJO:");
        fijostyle.setFont(writers);
        
        Chunk fijortastyle = new Chunk("");
        if(hoja_vida.isEquipotipofijo()) {
        	fijortastyle = new Chunk("SI");
        	fijortastyle.setFont(rta);
        }
        else {
        	fijortastyle = new Chunk("NO");
        	fijortastyle.setFont(rta);
        }
        
        Chunk sourcestyle = new Chunk("FUENTE DE ALIMENTACIÓN:");
        sourcestyle.setFont(fuenteEnunciados);
        
        Chunk elecsourcestyle = new Chunk("ELECTRICIDAD:");
        elecsourcestyle.setFont(rta);
        
        Chunk solarsourcestyle = new Chunk("ENERGIA SOLAR:");
        solarsourcestyle.setFont(rta);
        
        Chunk watersourcestyle = new Chunk("AGUA:");
        watersourcestyle.setFont(rta);
        
        Chunk gassourcestyle = new Chunk("GAS:");
        gassourcestyle.setFont(rta);
        
        Chunk vaporsourcestyle = new Chunk("VAPOR DE AGUA:");
        vaporsourcestyle.setFont(rta);
        
        Chunk psourcestyle = new Chunk("DERIVADOS DEL PETROLEO:");
        psourcestyle.setFont(rta);
        
        Chunk osourcestyle = new Chunk("OTROS:");
        osourcestyle.setFont(rta);
        
        Chunk apoyostyle = new Chunk("REGISTRO DE APOYO TÉCNICO:");
        apoyostyle.setFont(fuenteEnunciados);
        
        Chunk manualstyle = new Chunk("MANUALES:");
        manualstyle.setFont(writers);
        
        Chunk opuserstyle = new Chunk("OPERACIONAL-USUARIO:");
        opuserstyle.setFont(rta);
        
        Chunk tecmanualstyle = new Chunk("TÉCNICO:");
        tecmanualstyle.setFont(rta);
        
        Chunk usostyle = new Chunk("USO:");
        usostyle.setFont(writers);
        
        Chunk usomedstyle = new Chunk("MÉDICO:");
        usomedstyle.setFont(rta);
        
        Chunk usobacstyle = new Chunk("BÁSICO:");
        usobacstyle.setFont(rta);
        
        Chunk usoapostyle = new Chunk("APOYO:");
        usoapostyle.setFont(rta);
        
        PdfPTable tablatecnica = new PdfPTable(14);
        
        //firstrowtec
        PdfPCell regtecstyle = new PdfPCell(new Phrase(tecnicstyle)); 
        regtecstyle.setColspan(8);
        tablatecnica.addCell(regtecstyle);
        PdfPCell sourcealstyle = new PdfPCell(new Phrase(sourcestyle));
        sourcealstyle.setColspan(3);
        tablatecnica.addCell(sourcealstyle);
        PdfPCell apoyoteccell = new PdfPCell(new Phrase(apoyostyle));
        apoyoteccell.setColspan(3);
        tablatecnica.addCell(apoyoteccell);
        
        //secondrowtec
        regtecstyle = new PdfPCell(new Phrase(vmaxstyle));
        regtecstyle.setColspan(2);
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(vmaxrtastyle));
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(vacrstyle));
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(velstyle));
        regtecstyle.setColspan(2);
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(velrtastyle));
        tablatecnica.addCell(regtecstyle);       
        regtecstyle = new PdfPCell(new Phrase(pacrstyle));
        tablatecnica.addCell(regtecstyle);
        
        sourcealstyle = new PdfPCell(new Phrase(elecsourcestyle));
        sourcealstyle.setColspan(2);
        tablatecnica.addCell(sourcealstyle);
        
        if(hoja_vida.isFuenteaelectricidad()) {       
            sourcealstyle = new PdfPCell(new Phrase(xrtastyle));
            sourcealstyle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	sourcealstyle = new PdfPCell(new Phrase(""));          
        }
        tablatecnica.addCell(sourcealstyle);
        
        apoyoteccell = new PdfPCell(new Phrase(manualstyle));
        apoyoteccell.setColspan(3);
        tablatecnica.addCell(apoyoteccell);
        
        //thirdrowtec
        regtecstyle = new PdfPCell(new Phrase(vminstyle));
        regtecstyle.setColspan(2);
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(vminrtastyle));
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(vacrstyle));
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(presionstyle));
        regtecstyle.setColspan(2);
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(presionrtastyle));
        tablatecnica.addCell(regtecstyle);       
        regtecstyle = new PdfPCell(new Phrase(rpmacrstyle));
        tablatecnica.addCell(regtecstyle);
        
        sourcealstyle = new PdfPCell(new Phrase(solarsourcestyle));
        sourcealstyle.setColspan(2);
        tablatecnica.addCell(sourcealstyle);
        
        if(hoja_vida.isFuenteaenergiasolar()) {       
            sourcealstyle = new PdfPCell(new Phrase(xrtastyle));
            sourcealstyle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	sourcealstyle = new PdfPCell(new Phrase(""));          
        }

        tablatecnica.addCell(sourcealstyle);
        apoyoteccell = new PdfPCell(new Phrase(opuserstyle));
        apoyoteccell.setColspan(2);
        tablatecnica.addCell(apoyoteccell);
        
        if(hoja_vida.isManual_operacion()) {       
        	apoyoteccell = new PdfPCell(new Phrase(xrtastyle));
        	apoyoteccell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	apoyoteccell = new PdfPCell(new Phrase(""));          
        }
        tablatecnica.addCell(apoyoteccell);
        
        //fourthrowtec
        regtecstyle = new PdfPCell(new Phrase(imaxstyle));
        regtecstyle.setColspan(2);
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(imaxrtastyle));
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(iacrstyle));
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(tempstyle));
        regtecstyle.setColspan(2);
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(temprtastyle));
        tablatecnica.addCell(regtecstyle);       
        regtecstyle = new PdfPCell(new Phrase(tempacrstyle));
        tablatecnica.addCell(regtecstyle);
        
        sourcealstyle = new PdfPCell(new Phrase(watersourcestyle));
        sourcealstyle.setColspan(2);
        tablatecnica.addCell(sourcealstyle);
        if(hoja_vida.isFuenteaagua()) {       
            sourcealstyle = new PdfPCell(new Phrase(xrtastyle));
            sourcealstyle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	sourcealstyle = new PdfPCell(new Phrase(""));          
        }

        tablatecnica.addCell(sourcealstyle);
        apoyoteccell = new PdfPCell(new Phrase(tecmanualstyle));
        apoyoteccell.setColspan(2);
        tablatecnica.addCell(apoyoteccell);
        
        if(hoja_vida.isManual_tecnico()) {       
        	apoyoteccell = new PdfPCell(new Phrase(xrtastyle));
        	apoyoteccell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	apoyoteccell = new PdfPCell(new Phrase(""));          
        }
        tablatecnica.addCell(apoyoteccell);
        
        //fifthyrowtec
        regtecstyle = new PdfPCell(new Phrase(iminstyle));
        regtecstyle.setColspan(2);
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(iminrtastyle));
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(iacrstyle));
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(weightstyle));
        regtecstyle.setColspan(2);
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(weightrtastyle));
        tablatecnica.addCell(regtecstyle);       
        regtecstyle = new PdfPCell(new Phrase(weightacrstyle));
        tablatecnica.addCell(regtecstyle);
        
        sourcealstyle = new PdfPCell(new Phrase(gassourcestyle));
        sourcealstyle.setColspan(2);
        tablatecnica.addCell(sourcealstyle);
        if(hoja_vida.isFuenteagas()) {       
            sourcealstyle = new PdfPCell(new Phrase(xrtastyle));
            sourcealstyle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	sourcealstyle = new PdfPCell(new Phrase(""));          
        }

        tablatecnica.addCell(sourcealstyle);
        apoyoteccell = new PdfPCell(new Phrase(usostyle));
        apoyoteccell.setColspan(3);
        tablatecnica.addCell(apoyoteccell);
        
        //sixrowtec
        regtecstyle = new PdfPCell(new Phrase(powerstyle));
        regtecstyle.setColspan(2);
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(powerrtastyle));
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(wacrstyle));
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(capacitystyle));
        regtecstyle.setColspan(2);
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(capacityrtastyle));
        tablatecnica.addCell(regtecstyle);       
        regtecstyle = new PdfPCell(new Phrase(capacacrstyle));
        tablatecnica.addCell(regtecstyle);
    
        sourcealstyle = new PdfPCell(new Phrase(vaporsourcestyle));
        sourcealstyle.setColspan(2);
        tablatecnica.addCell(sourcealstyle);
        if(hoja_vida.isFuenteavaporagua()) {       
            sourcealstyle = new PdfPCell(new Phrase(xrtastyle));
            sourcealstyle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	sourcealstyle = new PdfPCell(new Phrase(""));          
        }

        tablatecnica.addCell(sourcealstyle);
        apoyoteccell = new PdfPCell(new Phrase(usomedstyle));
        apoyoteccell.setColspan(2);
        tablatecnica.addCell(apoyoteccell);
        
        if(hoja_vida.isUsomedico()) {       
        	apoyoteccell = new PdfPCell(new Phrase(xrtastyle));
        	apoyoteccell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	apoyoteccell = new PdfPCell(new Phrase(""));          
        }
        tablatecnica.addCell(apoyoteccell);
        
        //sevenrowtec
        regtecstyle = new PdfPCell(new Phrase(frecuencystyle));
        regtecstyle.setColspan(2);
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(frecuencyrtastyle));
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(fzacrstyle));
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(otrotecstyle));
        regtecstyle.setColspan(2);
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(otrotecrtastyle));
        tablatecnica.addCell(regtecstyle);       
        regtecstyle = new PdfPCell(new Phrase(""));
        tablatecnica.addCell(regtecstyle);
        
        sourcealstyle = new PdfPCell(new Phrase(psourcestyle));
        sourcealstyle.setColspan(2);
        tablatecnica.addCell(sourcealstyle);
        if(hoja_vida.isFuenteaderivadospetroleo()) {       
            sourcealstyle = new PdfPCell(new Phrase(xrtastyle));
            sourcealstyle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	sourcealstyle = new PdfPCell(new Phrase(""));          
        }

        tablatecnica.addCell(sourcealstyle);
        apoyoteccell = new PdfPCell(new Phrase(usobacstyle));
        apoyoteccell.setColspan(2);
        tablatecnica.addCell(apoyoteccell);
        
        if(hoja_vida.isUsobasico()) {       
        	apoyoteccell = new PdfPCell(new Phrase(xrtastyle));
        	apoyoteccell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	apoyoteccell = new PdfPCell(new Phrase(""));          
        }
        tablatecnica.addCell(apoyoteccell);
        
        //eightyrowtec
        regtecstyle = new PdfPCell(new Phrase(portstyle));
        regtecstyle.setColspan(3);
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(portrtastyle));
        tablatecnica.addCell(regtecstyle);        
            
        regtecstyle = new PdfPCell(new Phrase(fijostyle));
        regtecstyle.setColspan(3);
        tablatecnica.addCell(regtecstyle);        
        regtecstyle = new PdfPCell(new Phrase(fijortastyle));
        tablatecnica.addCell(regtecstyle);       
        
        
        sourcealstyle = new PdfPCell(new Phrase(osourcestyle));
        sourcealstyle.setColspan(2);
        tablatecnica.addCell(sourcealstyle);
        if(hoja_vida.isFuenteaotros()) {       
            sourcealstyle = new PdfPCell(new Phrase(xrtastyle));
            sourcealstyle.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	sourcealstyle = new PdfPCell(new Phrase(""));          
        }

        tablatecnica.addCell(sourcealstyle);
        apoyoteccell = new PdfPCell(new Phrase(usoapostyle));
        apoyoteccell.setColspan(2);
        tablatecnica.addCell(apoyoteccell);
        
        if(hoja_vida.isUsoapoyo()) {       
        	apoyoteccell = new PdfPCell(new Phrase(xrtastyle));
        	apoyoteccell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        else {
        	apoyoteccell = new PdfPCell(new Phrase(""));          
        }
        tablatecnica.addCell(apoyoteccell);   
	
        tablatecnica.setSpacingAfter(10);
        //table class
        Chunk riesgotstyle = new Chunk("RIESGO:");
        riesgotstyle.setFont(writers);
        
        Chunk riesgoistyle = new Chunk("I:");
        riesgoistyle.setFont(rta);
        
        Chunk riesgoiiastyle = new Chunk("IIA:");
        riesgoiiastyle.setFont(rta);
        
        Chunk riesgoiibstyle = new Chunk("IIB:");
        riesgoiibstyle.setFont(rta);
        
        Chunk riesgoiiistyle = new Chunk("III:");
        riesgoiiistyle.setFont(rta);
        
        Chunk tecpredstyle = new Chunk("CLASE DE TECNOLOGÍA PREDOMINANTE:");
        tecpredstyle.setFont(rta);
        
        Chunk celecstyle = new Chunk("ELÉCTRICO:");
        celecstyle.setFont(rta);
        
        Chunk celectronicstyle = new Chunk("ELECTRÓNICO:");
        celectronicstyle.setFont(rta);
        
        Chunk cmecstyle = new Chunk("MECÁNICO:");
        cmecstyle.setFont(rta);
        
        Chunk celectromecstyle = new Chunk("ELECTROMECÁNICO:");
        celectromecstyle.setFont(rta);
        
        Chunk chidstyle = new Chunk("HIDRAÚLICO:");
        chidstyle.setFont(rta);
        
        Chunk cneustyle = new Chunk("NEUMÁTICO:");
        cneustyle.setFont(rta);
        
        Chunk cvapstyle = new Chunk("VAPOR:");
        cvapstyle.setFont(rta);    
        
        Chunk csolstyle = new Chunk("SOLAR:");
        csolstyle.setFont(rta);
        
        Chunk periodstyle = new Chunk("PERIODICIDAD DEL MANTENIMIENTO:");
        periodstyle.setFont(rta);
        
        Chunk ptristyle = new Chunk("TRIMESTRAL:");
        ptristyle.setFont(rta);
        
        Chunk pcuatristyle = new Chunk("CUATRIMESTRAL:");
        pcuatristyle.setFont(rta);
        
        Chunk psemstyle = new Chunk("SEMESTRAL:");
        psemstyle.setFont(rta);
        
        Chunk panualstyle = new Chunk("ANUAL:");
        panualstyle.setFont(rta);
        
        Chunk responmttostyle = new Chunk("MANTENIMIENTO ACTUAL:");
        responmttostyle.setFont(rta);
        
        Chunk mttopropstyle = new Chunk("PROPIO:");
        mttopropstyle.setFont(rta);
        
        Chunk mttocontstyle = new Chunk("CONTRATADO:");
        mttocontstyle.setFont(rta);
        
        Chunk mttocomostyle = new Chunk("COMODATO:");
        mttocomostyle.setFont(rta);
        
        Chunk mttogarstyle = new Chunk("GARANTÍA:");
        mttogarstyle.setFont(rta);
        
        Chunk biomedicstyle = new Chunk("CLASIFICACIÓN BIOMÉDICA:");
        usoapostyle.setFont(rta);
        
        PdfPTable tablaclass = new PdfPTable(14);
        
        //firstrowclass
        PdfPCell tecnopredcell = new PdfPCell(new Phrase(tecpredstyle));
        tecnopredcell.setColspan(6);
        PdfPCell riesgocell = new PdfPCell(new Phrase(riesgotstyle));
        riesgocell.setColspan(2);
        PdfPCell freqmttocell = new PdfPCell(new Phrase(periodstyle));
        freqmttocell.setColspan(3);
        PdfPCell responmttocell = new PdfPCell(new Phrase(responmttostyle));
        responmttocell.setColspan(3);
        tablaclass.addCell(tecnopredcell);
        tablaclass.addCell(riesgocell);
        tablaclass.addCell(freqmttocell);
        tablaclass.addCell(responmttocell);
        //secondrowclass
        tecnopredcell = new PdfPCell(new Phrase(celecstyle));
        tecnopredcell.setColspan(2);
        tablaclass.addCell(tecnopredcell);
        if(hoja_vida.isClaseelectrico()) {
        	tecnopredcell = new PdfPCell(new Phrase(xrtastyle));
        	tecnopredcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        
        else {
        	tecnopredcell = new PdfPCell(new Phrase(""));
        }
        tablaclass.addCell(tecnopredcell);
        tecnopredcell = new PdfPCell(new Phrase(celectronicstyle));
        tecnopredcell.setColspan(2);
        tablaclass.addCell(tecnopredcell);
        if(hoja_vida.isClaseelectronico()) {
        	tecnopredcell = new PdfPCell(new Phrase(xrtastyle));
        	tecnopredcell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        
        else {
        	tecnopredcell = new PdfPCell(new Phrase(""));
        }
        tablaclass.addCell(tecnopredcell);
        
        riesgocell = new PdfPCell(new Phrase(riesgoistyle));
        tablaclass.addCell(riesgocell);
        if(hoja_vida.isRiesgoi()) {
        	riesgocell = new PdfPCell(new Phrase(xrtastyle));
        	riesgocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        
        else {
        	riesgocell = new PdfPCell(new Phrase(""));
        }
        tablaclass.addCell(riesgocell);
        
        freqmttocell = new PdfPCell(new Phrase(ptristyle));
        freqmttocell.setColspan(2);
        tablaclass.addCell(freqmttocell);
        if(hoja_vida.getEquipo().getPeriodicidad()==3) {
        	freqmttocell = new PdfPCell(new Phrase(xrtastyle));
        	freqmttocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        
        else {
        	freqmttocell = new PdfPCell(new Phrase(""));
        }
        tablaclass.addCell(freqmttocell);
        
        responmttocell = new PdfPCell(new Phrase(mttopropstyle));
        responmttocell.setColspan(2);
        tablaclass.addCell(responmttocell);
        if(hoja_vida.isMapropio()) {
        	responmttocell = new PdfPCell(new Phrase(xrtastyle));
        	responmttocell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        }
        
        else {
        	responmttocell = new PdfPCell(new Phrase(""));
        }
        tablaclass.addCell(responmttocell);
        
        
        
        
        
        	
        document.add(tabla);
        document.add(tabladhos);
        document.add(tablaeqcom);
        document.add(tablatecnica);
        document.add(tablaclass);

        document.close();
        // Retornamos la variable al finalizar
        return bos;
        
    }
	

}
