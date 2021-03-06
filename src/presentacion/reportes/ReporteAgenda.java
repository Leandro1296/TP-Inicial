package presentacion.reportes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import dto.PersonaDTO;

public class ReporteAgenda
{
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint	reporteLleno;
	private List<PersonaReporte> personas;
	private HoroscopoChino calculadoraHoroscopo;
	
	//Recibe la lista de personas para armar el reporte
    public ReporteAgenda(List<PersonaDTO> personas)
    {
    	//Hardcodeado
    	this.calculadoraHoroscopo = new HoroscopoChino();
    	this.personas = asignarHoroscopo(personas);
    	Collections.sort(this.personas);
    	this.personas = this.calculadoraHoroscopo.ordenarHoroscopoChino(this.personas);
		Map<String, Object> parametersMap = new HashMap<String, Object>();
		parametersMap.put("Fecha", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));		
    	try		
    	{
			this.reporte = (JasperReport) JRLoader.loadObjectFromFile( "reportes\\ReporteAgenda.jasper" );
			this.reporteLleno = JasperFillManager.fillReport(this.reporte, parametersMap, 
					new JRBeanCollectionDataSource(this.personas));
		}
		catch( JRException ex ) 
		{
			ex.printStackTrace();
		}
    }       
    
    private List<PersonaReporte> asignarHoroscopo(List<PersonaDTO> personas) 
    {
    	List<PersonaReporte> listaDePersonas = new ArrayList<PersonaReporte>();
    	for(PersonaDTO persona: personas)
    	{
    		String signo = this.calculadoraHoroscopo.calcularSigno(persona.getNacimiento().toString());
    		listaDePersonas.add(new PersonaReporte(persona,signo));
    	}
    	return this.calculadoraHoroscopo.ordenarHoroscopoChino(listaDePersonas);
    }

	public void mostrar()
	{
		this.reporteViewer = new JasperViewer(this.reporteLleno,false);
		this.reporteViewer.setVisible(true);
	}
	
	public void mhc()
	{
		for(PersonaReporte persona : personas){
			System.out.println(persona.getSignoHoroscopoChino());
		}
	}
}	