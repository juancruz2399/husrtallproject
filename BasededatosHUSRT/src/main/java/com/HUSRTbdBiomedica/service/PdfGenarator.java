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
        motrtastyle.setFont(fuenteTituloHospital);
        
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
	

}
