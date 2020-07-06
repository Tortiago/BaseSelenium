package br.com.tortiago.utils.evidencias.pdf;

import static br.com.tortiago.utils.LocalDateTimeUtils.getDataAtual;                                                                               
import static br.com.tortiago.utils.LocalDateTimeUtils.getHoraAtual;                                                                               
import static br.com.tortiago.utils.LocalDateTimeUtils.getTempoTotal;                                                                              
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.getNomePrintPNG;                                                                     
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.getNomeRelatorioPDF;                                                                 
import static br.com.tortiago.utils.evidencias.EvidenciasPojo.getPathImgPrint;                                                                     
import static br.com.tortiago.utils.evidencias.pdf.ConstPDF.TTL_SEC_EXECUCAO;                                                                      
import static br.com.tortiago.utils.evidencias.pdf.ConstPDF.TTL_SEC_FLUXO;                                                                         
import static br.com.tortiago.utils.evidencias.pdf.ConstPDF.TTL_SEC_OBJETIVO;                                                                      
import static br.com.tortiago.utils.evidencias.pdf.ConstPDF.TTL_SEC_PR_COND;                                                                       
import static br.com.tortiago.utils.evidencias.pdf.ConstPDF.detalhesExec;                                                                          
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.addCodFluxo;                                                                           
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.addContFluxo;                                                                          
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getCodFluxo;                                                                           
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getContFluxo;                                                                          
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getHrFim;                                                                              
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getHrIni;                                                                              
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getMsgErro;                                                                            
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getNomeTest;                                                                           
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getObjetivoTest;                                                                       
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.getPreCond;                                                                            
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.isStatus;                                                                              
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.setDtExec;                                                                             
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.setHrFim;                                                                              
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.setNomeTest;                                                                           
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.setObjetivoTest;                                                                       
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.setPreCond;                                                                            
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.setStatus;                                                                             
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.setTmpTotalExec;                                                                       
import static br.com.tortiago.utils.evidencias.pdf.PDF_Pojo.setUsuarioLogado;                                                                      
import static com.itextpdf.text.BaseColor.BLACK;                                                                                                   
import static com.itextpdf.text.BaseColor.RED;                                                                                                     
import static com.itextpdf.text.Element.ALIGN_CENTER;                                                                                              
                                                                                                                                                   
import java.io.FileNotFoundException;                                                                                                              
import java.io.FileOutputStream;                                                                                                                   
import java.io.IOException;                                                                                                                        
import java.net.MalformedURLException;                                                                                                             
import java.util.List;                                                                                                                             
                                                                                                                                                   
import com.itextpdf.text.BaseColor;                                                                                                                
import com.itextpdf.text.Document;                                                                                                                 
import com.itextpdf.text.DocumentException;                                                                                                        
import com.itextpdf.text.Element;                                                                                                                  
import com.itextpdf.text.Font;                                                                                                                     
import com.itextpdf.text.Font.FontFamily;                                                                                                          
import com.itextpdf.text.Image;                                                                                                                    
import com.itextpdf.text.Paragraph;                                                                                                                
import com.itextpdf.text.pdf.PdfContentByte;                                                                                                       
import com.itextpdf.text.pdf.PdfGState;                                                                                                            
import com.itextpdf.text.pdf.PdfPCell;                                                                                                             
import com.itextpdf.text.pdf.PdfPTable;                                                                                                            
import com.itextpdf.text.pdf.PdfWriter;                                                                                                            
                                                                                                                                                   
public class PDF_Business {                                                                                                                        
                                                                                                                                                   
	private Document doc;                                                                                                                          
	private PdfWriter writer;                                                                                                                      
	private Font fontTest;                                                                                                                         
	private Font fontTitulo;                                                                                                                       
	private Font fontSubtituloFluxo;                                                                                                               
	private Font fontDetalhes;                                                                                                                     
	private Font fontItemErro;                                                                                                                     
	private String logoBV = ".//src//test//resources//img//pdf//LogoBV.jpg";                                                                       
	private float espacoTitulo = 35f;                                                                                                              
	private float espacoSubtituloFluxo = 48f;                                                                                                      
	private float espacoDetalhes = 60f;                                                                                                            
	private float espacoTituloPrint = 73f;                                                                                                         
                                                                                                                                                   
	public PDF_Business() {                                                                                                                        
		doc = new Document();                                                                                                                      
	}                                                                                                                                              
                                                                                                                                                   
	                                                                                                                                               
	/**                                                                                                                                            
	 * Inicializa a variaveis necessarias para criação do PDF                                                                                      
	 */                                                                                                                                            
	private void openDocument() {                                                                                                                  
		try {                                                                                                                                      
			writer = PdfWriter.getInstance(doc, new FileOutputStream(getNomeRelatorioPDF()));                                                      
			doc.setMargins(0, 0, 0, 0);                                                                                                            
			doc.open();                                                                                                                            
		} catch (FileNotFoundException e) {                                                                                                        
			e.printStackTrace();                                                                                                                   
		} catch (DocumentException e) {                                                                                                            
			e.printStackTrace();                                                                                                                   
		}                                                                                                                                          
	}                                                                                                                                              
                                                                                                                                                   
                                                                                                                                                   
	                                                                                                                                               
	//			### IMAGENS ###                                                                                                                    
	                                                                                                                                               
	private void addMarcaDagua() throws MalformedURLException, IOException, DocumentException {                                                    
		PdfContentByte canvas = writer.getDirectContentUnder();                                                                                    
		Image image = Image.getInstance(logoBV);                                                                                                   
		image.scaleToFit(750, 650);                                                                                                                
		image.setAbsolutePosition(70, 80);                                                                                                         
		canvas.saveState();                                                                                                                        
		PdfGState state = new PdfGState();                                                                                                         
		state.setFillOpacity(0.1f);                                                                                                                
		canvas.setGState(state);                                                                                                                   
		canvas.addImage(image);                                                                                                                    
		canvas.restoreState();                                                                                                                     
	}                                                                                                                                              
                                                                                                                                                   
	private Image addImg(String img, int largura, int altura) throws DocumentException, MalformedURLException, IOException {                       
		Image logo = Image.getInstance(img);                                                                                                       
		logo.scaleToFit(largura, altura);                                                                                                          
		logo.setAlignment(Element.ALIGN_CENTER);                                                                                                   
		return logo;                                                                                                                               
	}                                                                                                                                              
	                                                                                                                                               
	private void addLogoCabecalhoPrincipal() throws MalformedURLException, IOException, DocumentException {                                        
		doc.add(addImg(logoBV, 80, 80));                                                                                                           
	}                                                                                                                                              
	                                                                                                                                               
	private Image addLogoCabacalhoEvidencias() throws MalformedURLException, DocumentException, IOException {                                      
		return addImg(logoBV, 40, 40);                                                                                                             
	}                                                                                                                                              
	                                                                                                                                               
	private void addImgEvidencia(String pathPrint, BaseColor cor) throws MalformedURLException, IOException, DocumentException {                   
		int bordaImg=5;                                                                                                                            
		                                                                                                                                           
		Image img = Image.getInstance(pathPrint);                                                                                                  
		img.scaleToFit(450, 400);                                                                                                                  
		                                                                                                                                           
		img.setBorderWidthRight(bordaImg);                                                                                                         
		img.setBorderWidthTop(bordaImg);                                                                                                           
		img.setBorderWidthBottom(bordaImg);                                                                                                        
		img.setBorderWidthLeft(bordaImg);                                                                                                          
		                                                                                                                                           
		img.setBorderColor(cor);                                                                                                                   
		img.setAlignment(ALIGN_CENTER);                                                                                                            
		doc.add(img);                                                                                                                              
	}                                                                                                                                              
	                                                                                                                                               
	                                                                                                                                               
	                                                                                                                                               
	//			### FONTES ###                                                                                                                     
	                                                                                                                                               
	private void defineFont() {                                                                                                                    
		fontTitulo 			= new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.BOLD);                                                                  
		fontSubtituloFluxo 	= new Font(FontFamily.TIMES_ROMAN, 10.0f, Font.BOLD);                                                                  
		fontDetalhes 		= new Font(FontFamily.TIMES_ROMAN, 10.0f);                                                                             
		fontItemErro 		= new Font(FontFamily.TIMES_ROMAN, 14.0f, Font.BOLD, BaseColor.RED);                                                   
	}                                                                                                                                              
	                                                                                                                                               
	private Paragraph defineFontTest(float tamanho, boolean centralizar) {                                                                         
		if (isStatus()) {                                                                                                                          
			fontTest = new Font(FontFamily.TIMES_ROMAN, tamanho, Font.BOLD, BaseColor.GREEN);                                                      
		} else {                                                                                                                                   
			fontTest = new Font(FontFamily.TIMES_ROMAN, tamanho, Font.BOLD, BaseColor.RED);                                                        
		}                                                                                                                                          
		                                                                                                                                           
		Paragraph test = new Paragraph(getNomeTest(), fontTest);                                                                                   
		if(centralizar) {                                                                                                                          
			test.setAlignment(Element.ALIGN_CENTER);                                                                                               
		}                                                                                                                                          
		return test;                                                                                                                               
	}                                                                                                                                              
		                                                                                                                                           
	//			### NOME TESTE ###                                                                                                                 
	private Paragraph addNomeTestPagPrincipal() throws DocumentException {                                                                         
		return defineFontTest(18.f, true);                                                                                                         
	}                                                                                                                                              
	                                                                                                                                               
	private Paragraph addNomeTestPagEvidencias() {                                                                                                 
		return defineFontTest(12.f, false);                                                                                                        
	}                                                                                                                                              
                                                                                                                                                   
	                                                                                                                                               
	                                                                                                                                               
	//			### SECOES ###                                                                                                                     
	                                                                                                                                               
	private void addParagraph(String conteudo, Font font, float espacamento) throws DocumentException {                                            
		Paragraph p = new Paragraph(conteudo, font);                                                                                               
		p.setFirstLineIndent(espacamento);                                                                                                         
		doc.add(p);		                                                                                                                           
	}                                                                                                                                              
	                                                                                                                                               
	private void addTitulo(String titulo) throws DocumentException {                                                                               
		addDetalhe(" ");                                                                                                                           
		addParagraph(titulo, fontTitulo, espacoTitulo);                                                                                            
	}                                                                                                                                              
	                                                                                                                                               
	private void addDetalhe(String detalhe) throws DocumentException {                                                                             
		addParagraph(detalhe, fontDetalhes, espacoDetalhes);                                                                                       
	}                                                                                                                                              
                                                                                                                                                   
	private void addITemErro(String erro) throws DocumentException {                                                                               
		addParagraph(erro, fontItemErro, espacoDetalhes);                                                                                          
	}                                                                                                                                              
	                                                                                                                                               
	private void addMsgErro(String msgErro) throws DocumentException {                                                                             
		addParagraph(msgErro, fontItemErro, espacoTitulo);                                                                                         
	}                                                                                                                                              
	                                                                                                                                               
	private void addSubtituloFluxo(String subtitulo) throws DocumentException {                                                                    
		addParagraph(subtitulo, fontSubtituloFluxo, espacoSubtituloFluxo);                                                                         
	}                                                                                                                                              
	                                                                                                                                               
	private void addTituloEvidencia(String tituloPrint) throws DocumentException {                                                                 
		addParagraph(tituloPrint, fontDetalhes, espacoTituloPrint);                                                                                
	}                                                                                                                                              
	                                                                                                                                               
	private void addTituloEvidenciaError(String tituloPrint) throws DocumentException {                                                            
		addParagraph(tituloPrint, fontItemErro, espacoTituloPrint);                                                                                
	}                                                                                                                                              
	/**                                                                                                                                            
	 * Adiciona uma secao com mais de uma linha de item                                                                                            
	 *                                                                                                                                             
	 * @param titulo da secao                                                                                                                      
	 * @param detalhe descricao da secao                                                                                                           
	 */                                                                                                                                            
	private void addSecaoLista(String titulo, List<String> detalhes) throws DocumentException {                                                    
		addTitulo(titulo);		                                                                                                                   
		for(String detalhe : detalhes) {                                                                                                           
			addDetalhe(detalhe);                                                                                                                   
		}                                                                                                                                          
	}                                                                                                                                              
                                                                                                                                                   
	private void addSecaoFluxo() throws DocumentException {                                                                                        
		addTitulo(TTL_SEC_FLUXO);                                                                                                                  
		                                                                                                                                           
		for(int i=0; i<getCodFluxo().size(); i++) {                                                                                                
			if(!isStatus() && (i == getCodFluxo().size() -1)) {                                                                                    
				addITemErro("*" + getContFluxo().get(i) + "* ERROR" );                                                                        
			}else if(getCodFluxo().get(i) == 1) {                                                                                                  
				addSubtituloFluxo(getContFluxo().get(i));                                                                                          
			}else {                                                                                                                                
				addDetalhe(getContFluxo().get(i));                                                                                                 
			}                                                                                                                                      
		}                                                                                                                                          
	}                                                                                                                                              
	                                                                                                                                               
	public static void addFluxoPDF(int cod, String conteudo) {                                                                                     
		addCodFluxo(cod);                                                                                                                          
		addContFluxo(conteudo);                                                                                                                    
	}                                                                                                                                              
	                                                                                                                                               
	/**                                                                                                                                            
	 * Adiciona uma secao de uma linha unica 'OBJETIVO' por exemplo                                                                                
	 *                                                                                                                                             
	 * @param titulo da secao                                                                                                                      
	 * @param detalhe descricao da secao                                                                                                           
	 */                                                                                                                                            
	private void addSecaoLinhaUnica(String titulo, String detalhe) throws DocumentException {                                                      
		addTitulo(titulo);                                                                                                                         
		addDetalhe(detalhe);                                                                                                                       
	}                                                                                                                                              
                                                                                                                                                   
	private void addObjtivoTest() throws DocumentException {                                                                                       
		addSecaoLinhaUnica(TTL_SEC_OBJETIVO, getObjetivoTest());                                                                                   
	}                                                                                                                                              
                                                                                                                                                   
	private void addDadosExec() throws DocumentException {                                                                                         
		addSecaoLista(TTL_SEC_EXECUCAO, detalhesExec());                                                                                           
	}                                                                                                                                              
                                                                                                                                                   
	private void addPreCond() throws DocumentException {                                                                                           
		if(getPreCond() != null && !getPreCond().isEmpty()) addSecaoLinhaUnica(TTL_SEC_PR_COND, getPreCond());	                                   
	}                                                                                                                                              
	                                                                                                                                               
	private void addMsgErro() throws DocumentException, MalformedURLException, IOException {                                                       
		if(isStatus() == false) {                                                                                                                  
			addNewPage();                                                                                                                          
			addMsgErro(getMsgErro());                                                                                                              
		}                                                                                                                                          
	}                                                                                                                                              
	                                                                                                                                               
	//			### CORPO RELATORIO ###                                                                                                            
	//			### CABECALHO       ###                                                                                                            
	                                                                                                                                               
	private void addCabecalhoPrincipal() throws MalformedURLException, IOException, DocumentException {                                            
		addMarcaDagua();                                                                                                                           
		addLogoCabecalhoPrincipal();                                                                                                               
		doc.add(addNomeTestPagPrincipal());                                                                                                        
	}                                                                                                                                              
                                                                                                                                                   
	private void addCabecalhoNovaPagina() throws MalformedURLException, DocumentException, IOException {                                           
		float [] cols = {90f, 600f};                                                                                                               
		PdfPTable table = new PdfPTable(cols);                                                                                                     
		                                                                                                                                           
		PdfPCell cell1 = new PdfPCell();                                                                                                           
		cell1.setBorder(0);                                                                                                                        
		cell1.addElement(addLogoCabacalhoEvidencias());                                                                                            
		                                                                                                                                           
		PdfPCell cell2 = new PdfPCell();                                                                                                           
		cell2.setBorder(0);                                                                                                                        
		cell2.setPaddingTop(8);                                                                                                                    
		cell2.addElement(addNomeTestPagEvidencias());                                                                                              
		                                                                                                                                           
		table.addCell(cell1);                                                                                                                      
		table.addCell(cell2);                                                                                                                      
		doc.add(table);                                                                                                                            
	}                                                                                                                                              
	                                                                                                                                               
	                                                                                                                                               
	                                                                                                                                               
	//			### PAGINAS ###                                                                                                                    
	                                                                                                                                               
	private void addNewPage() throws MalformedURLException, IOException, DocumentException {                                                       
		doc.newPage();                                                                                                                             
		addMarcaDagua();                                                                                                                           
		addCabecalhoNovaPagina();                                                                                                                  
	}                                                                                                                                              
	                                                                                                                                               
	private void addPagIni() throws MalformedURLException, IOException, DocumentException {                                                        
		openDocument();                                                                                                                            
		defineFont();                                                                                                                              
		addCabecalhoPrincipal();                                                                                                                   
		addObjtivoTest();                                                                                                                          
		addPreCond();                                                                                                                              
		addDadosExec();                                                                                                                            
		addSecaoFluxo();                                                                                                                           
	}                                                                                                                                              
                                                                                                                                                   
	private void addNewPageEvidencias() throws MalformedURLException, DocumentException, IOException {                                             
		doc.newPage();                                                                                                                             
		addDetalhe(" ");                                                                                                                           
		addMarcaDagua();                                                                                                                           
		addDetalhe(" ");                                                                                                                           
		addCabecalhoNovaPagina();	                                                                                                               
	}                                                                                                                                              
	                                                                                                                                               
	private void addEvidencias() throws MalformedURLException, IOException, DocumentException {                                                    
		addNewPageEvidencias();                                                                                                                    
		                                                                                                                                           
		for (int i = 0; i < getPathImgPrint().size(); i++) {                                                                                       
			if (i > 0  && 0 == i%2) {                                                                                                              
				addNewPageEvidencias();                                                                                                            
			}                                                                                                                                      
			if(!isStatus() && i == getPathImgPrint().size()-1) {                                                                                   
				addDetalhe(" ");                                                                                                                   
				addTituloEvidenciaError(getNomePrintPNG().get(i));                                                                                 
				addImgEvidencia(getPathImgPrint().get(i), RED);                                                                                    
			}else {                                                                                                                                
				addDetalhe(" ");                                                                                                                   
				addTituloEvidencia(getNomePrintPNG().get(i));                                                                                      
				addImgEvidencia(getPathImgPrint().get(i), BLACK);                                                                                  
				addDetalhe(" ");                                                                                                                   
			}                                                                                                                                      
		}                                                                                                                                          
	}                                                                                                                                              
	                                                                                                                                               
	/**                                                                                                                                            
	 * Inicia as variaveis                                                                                                                         
	 * @param nomeTest                                                                                                                             
	 * @param usuarioLogado                                                                                                                        
	 * @param objetivoTest                                                                                                                         
	 * @param preCond                                                                                                                              
	 */                                                                                                                                            
	public static void iniPDF(String nomeTest, String usuarioLogado, String objetivoTest, String preCond) {                                        
		setStatus(true);                                                                                                                           
		setNomeTest(nomeTest);                                                                                                                     
		setUsuarioLogado(usuarioLogado);                                                                                                           
		setObjetivoTest(objetivoTest);                                                                                                             
		setPreCond(preCond);                                                                                                                       
		setDtExec(getDataAtual());                                                                                                                 
	}                                                                                                                                              
	                                                                                                                                               
	private static void fimPDF() {                                                                                                                 
		setHrFim(getHoraAtual());                                                                                                                  
		setTmpTotalExec(getTempoTotal(getHrIni(), getHrFim()));                                                                                    
	}                                                                                                                                              
	                                                                                                                                               
	public void criaPdf() throws MalformedURLException, IOException, DocumentException {                                                           
		fimPDF();                                                                                                                                  
		addPagIni();                                                                                                                               
		addEvidencias();                                                                                                                           
		addMsgErro();                                                                                                                              
		doc.close();                                                                                                                               
	}                                                                                                                                              
	                                                                                                                                               
}                                                                                                                                                  