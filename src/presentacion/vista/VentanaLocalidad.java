package presentacion.vista;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import dto.LocalidadDTO;
import presentacion.controlador.Controlador;

public class VentanaLocalidad extends JDialog
{
	private static final long serialVersionUID = 1L;
	private static VentanaLocalidad instancia;
	private JButton btnAgregar, btnModificar, btnEliminar;
	private JLabel mensaje;
	private JList<LocalidadDTO> listaNombres;
	private DefaultListModel<LocalidadDTO> modelo;
	private JScrollPane scrollLista;
	private JTextField txtNombre;
	private Controlador controlador;

	public static VentanaLocalidad getVentana(Controlador controlador){
		if (instancia == null)
			return new VentanaLocalidad(controlador);
		else
			return instancia;
	}
	
	private VentanaLocalidad(Controlador controlador) {
		super();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.controlador = controlador;
		inicializar();
	}

	private void inicializar() {
		inicializarVentana();
		inicializarBotones();
		inicializarLabels();
		inicializarTxtFields();
		inicializarLista();
		inicializarScroll();
		this.setVisible(true);
	}

	private void inicializarVentana() {
		this.setBounds(500, 250, 451, 319);
		this.setTitle("Localidades");
		this.getContentPane().setLayout(null);
	}

	private void inicializarLista() {
		modelo = new DefaultListModel<LocalidadDTO>();
		listaNombres = new JList<LocalidadDTO>(modelo);
		listaNombres.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void inicializarScroll() {
		scrollLista = new JScrollPane();
		scrollLista.setBounds(25, 45, 383, 180);
		scrollLista.setViewportView(listaNombres);
		this.getContentPane().add(scrollLista);
	}

	private void inicializarBotones() {
		botonAgregar();
		botonEliminar();
		botonModificar();
	}
	
	private void botonAgregar() {
		btnAgregar = new JButton();
		btnAgregar.setText("Agregar");
		btnAgregar.setBounds(25, 236, 117, 33);
		btnAgregar.addActionListener(controlador);
		this.getContentPane().add(btnAgregar);
	}

	private void botonModificar() {
		btnModificar = new JButton("Modificar");
		btnModificar.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnModificar.setVerticalTextPosition(SwingConstants.CENTER);
		btnModificar.setBounds(297, 236, 111, 33);
		btnModificar.addActionListener(controlador);
		this.getContentPane().add(btnModificar);
	}

	private void botonEliminar() {
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(163, 236, 117, 33);
		btnEliminar.addActionListener(controlador);
		this.getContentPane().add(btnEliminar);
	}
	
	private void inicializarLabels() {
		mensaje = new JLabel();
		mensaje.setVerticalAlignment(SwingConstants.TOP);
		mensaje.setHorizontalAlignment(SwingConstants.CENTER);
		mensaje.setText("Nombre: ");
		mensaje.setBounds(39, 14, 56, 23);
		this.getContentPane().add(mensaje);
	}

	private void inicializarTxtFields() {
		txtNombre = new JTextField();
		txtNombre.setBounds(96, 10, 312, 23);
		this.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
	}
	
	public JButton getBtnAgregar(){return this.btnAgregar;}
	
	public JButton getBtnEliminar(){return this.btnEliminar;}
	
	public JButton getBtnModificar(){return this.btnModificar;}
	
	public JList<LocalidadDTO> getJList(){return this.listaNombres;}
	
	public DefaultListModel<LocalidadDTO> getModelo(){return this.modelo;}
	
	public JTextField getTxtNombre(){return this.txtNombre;}
	
	public JScrollPane getScrollPane(){return this.scrollLista;}
	
}
 