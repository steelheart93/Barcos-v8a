package puertos.gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import puertos.control.BarcoException;
import puertos.control.ControlPuerto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;

/**
 * Interfaz gráfica sencilla que permite hacer algunas operaciones
 * con barcos en un puerto, principalmente para pruebas.
 * Esta ventana se generó con el WindowsBuilder (plugin de Eclipse).
 * @version 2.5
 */
@SuppressWarnings("serial")
public class VentanaControlBarcos extends JFrame {
	
	private ControlPuerto control;

	private JPanel contentPane;
	private JTextField campoNacionalidad;
	private JTextField campoVolumen;
	private JTextField campoCapacidad;
	private JTextField campoPasajeros;
	private final ButtonGroup opcionesTipoBarco = new ButtonGroup();
	private JCheckBox checkLiquidos;
	private JTextField campoMatricula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaControlBarcos frame = new VentanaControlBarcos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public VentanaControlBarcos() throws IOException {
		
		control = new ControlPuerto();
		
		setTitle("Control Barcos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 472, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{122, 107, 191, 0};
		gbl_contentPane.rowHeights = new int[]{14, 20, 20, 20, 23, 23, 20, 23, 23, 2, 23, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNuevoBarco = new JLabel("Nuevo Barco");
		GridBagConstraints gbc_lblNuevoBarco = new GridBagConstraints();
		gbc_lblNuevoBarco.anchor = GridBagConstraints.NORTH;
		gbc_lblNuevoBarco.insets = new Insets(0, 0, 5, 5);
		gbc_lblNuevoBarco.gridx = 1;
		gbc_lblNuevoBarco.gridy = 0;
		contentPane.add(lblNuevoBarco, gbc_lblNuevoBarco);
		
		JLabel lblMatrcula = new JLabel("Matrícula:");
		GridBagConstraints gbc_lblMatrcula = new GridBagConstraints();
		gbc_lblMatrcula.anchor = GridBagConstraints.WEST;
		gbc_lblMatrcula.insets = new Insets(0, 0, 5, 5);
		gbc_lblMatrcula.gridx = 0;
		gbc_lblMatrcula.gridy = 1;
		contentPane.add(lblMatrcula, gbc_lblMatrcula);
		
		JButton btnCrearBarco = new JButton("Crear Barco");
		btnCrearBarco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearBarco();
			}
		});
		
		campoMatricula = new JTextField();
		GridBagConstraints gbc_campoMatricula = new GridBagConstraints();
		gbc_campoMatricula.anchor = GridBagConstraints.NORTHWEST;
		gbc_campoMatricula.insets = new Insets(0, 0, 5, 0);
		gbc_campoMatricula.gridwidth = 2;
		gbc_campoMatricula.gridx = 1;
		gbc_campoMatricula.gridy = 1;
		contentPane.add(campoMatricula, gbc_campoMatricula);
		campoMatricula.setColumns(10);
		
		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		GridBagConstraints gbc_lblNacionalidad = new GridBagConstraints();
		gbc_lblNacionalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblNacionalidad.gridx = 0;
		gbc_lblNacionalidad.gridy = 2;
		contentPane.add(lblNacionalidad, gbc_lblNacionalidad);
		
		campoNacionalidad = new JTextField();
		GridBagConstraints gbc_campoNacionalidad = new GridBagConstraints();
		gbc_campoNacionalidad.anchor = GridBagConstraints.NORTH;
		gbc_campoNacionalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoNacionalidad.insets = new Insets(0, 0, 5, 0);
		gbc_campoNacionalidad.gridwidth = 2;
		gbc_campoNacionalidad.gridx = 1;
		gbc_campoNacionalidad.gridy = 2;
		contentPane.add(campoNacionalidad, gbc_campoNacionalidad);
		campoNacionalidad.setColumns(10);
		
		JLabel lblVolumen = new JLabel("Volumen:");
		GridBagConstraints gbc_lblVolumen = new GridBagConstraints();
		gbc_lblVolumen.anchor = GridBagConstraints.WEST;
		gbc_lblVolumen.insets = new Insets(0, 0, 5, 5);
		gbc_lblVolumen.gridx = 0;
		gbc_lblVolumen.gridy = 3;
		contentPane.add(lblVolumen, gbc_lblVolumen);
		
		campoVolumen = new JTextField();
		GridBagConstraints gbc_campoVolumen = new GridBagConstraints();
		gbc_campoVolumen.anchor = GridBagConstraints.NORTHWEST;
		gbc_campoVolumen.insets = new Insets(0, 0, 5, 5);
		gbc_campoVolumen.gridx = 1;
		gbc_campoVolumen.gridy = 3;
		contentPane.add(campoVolumen, gbc_campoVolumen);
		campoVolumen.setColumns(10);
		
		JLabel lblM = new JLabel("m3");
		GridBagConstraints gbc_lblM = new GridBagConstraints();
		gbc_lblM.anchor = GridBagConstraints.WEST;
		gbc_lblM.insets = new Insets(0, 0, 5, 0);
		gbc_lblM.gridx = 2;
		gbc_lblM.gridy = 3;
		contentPane.add(lblM, gbc_lblM);
		
		JLabel lblTipo = new JLabel("Tipo:");
		GridBagConstraints gbc_lblTipo = new GridBagConstraints();
		gbc_lblTipo.anchor = GridBagConstraints.WEST;
		gbc_lblTipo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipo.gridx = 0;
		gbc_lblTipo.gridy = 4;
		contentPane.add(lblTipo, gbc_lblTipo);
		
		JRadioButton opcionVelero = new JRadioButton("Velero");
		opcionVelero.setActionCommand("velero");
		opcionesTipoBarco.add(opcionVelero);
		GridBagConstraints gbc_opcionVelero = new GridBagConstraints();
		gbc_opcionVelero.anchor = GridBagConstraints.NORTH;
		gbc_opcionVelero.fill = GridBagConstraints.HORIZONTAL;
		gbc_opcionVelero.insets = new Insets(0, 0, 5, 5);
		gbc_opcionVelero.gridx = 1;
		gbc_opcionVelero.gridy = 4;
		contentPane.add(opcionVelero, gbc_opcionVelero);
		
		JRadioButton opcionCarguero = new JRadioButton("Carguero");
		opcionCarguero.setActionCommand("carguero");
		opcionesTipoBarco.add(opcionCarguero);
		GridBagConstraints gbc_opcionCarguero = new GridBagConstraints();
		gbc_opcionCarguero.anchor = GridBagConstraints.NORTH;
		gbc_opcionCarguero.fill = GridBagConstraints.HORIZONTAL;
		gbc_opcionCarguero.insets = new Insets(0, 0, 5, 5);
		gbc_opcionCarguero.gridx = 1;
		gbc_opcionCarguero.gridy = 5;
		contentPane.add(opcionCarguero, gbc_opcionCarguero);
		
		JLabel lblPasajeros = new JLabel("Pasajeros:");
		GridBagConstraints gbc_lblPasajeros = new GridBagConstraints();
		gbc_lblPasajeros.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblPasajeros.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasajeros.gridx = 0;
		gbc_lblPasajeros.gridy = 6;
		contentPane.add(lblPasajeros, gbc_lblPasajeros);
		
		campoPasajeros = new JTextField();
		GridBagConstraints gbc_campoPasajeros = new GridBagConstraints();
		gbc_campoPasajeros.anchor = GridBagConstraints.NORTHWEST;
		gbc_campoPasajeros.insets = new Insets(0, 0, 5, 5);
		gbc_campoPasajeros.gridx = 1;
		gbc_campoPasajeros.gridy = 6;
		contentPane.add(campoPasajeros, gbc_campoPasajeros);
		campoPasajeros.setColumns(10);
		
		JLabel lblLlevaLquidos = new JLabel("Lleva líquidos:");
		GridBagConstraints gbc_lblLlevaLquidos = new GridBagConstraints();
		gbc_lblLlevaLquidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLlevaLquidos.insets = new Insets(0, 0, 5, 5);
		gbc_lblLlevaLquidos.gridx = 0;
		gbc_lblLlevaLquidos.gridy = 7;
		contentPane.add(lblLlevaLquidos, gbc_lblLlevaLquidos);
		
		checkLiquidos = new JCheckBox("Sí");
		GridBagConstraints gbc_checkLiquidos = new GridBagConstraints();
		gbc_checkLiquidos.anchor = GridBagConstraints.NORTH;
		gbc_checkLiquidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_checkLiquidos.insets = new Insets(0, 0, 5, 5);
		gbc_checkLiquidos.gridx = 1;
		gbc_checkLiquidos.gridy = 7;
		contentPane.add(checkLiquidos, gbc_checkLiquidos);
		GridBagConstraints gbc_btnCrearBarco = new GridBagConstraints();
		gbc_btnCrearBarco.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCrearBarco.insets = new Insets(0, 0, 5, 5);
		gbc_btnCrearBarco.gridx = 1;
		gbc_btnCrearBarco.gridy = 8;
		contentPane.add(btnCrearBarco, gbc_btnCrearBarco);
		
		JButton btnBorrarBarco = new JButton("Borrar Barco");
		btnBorrarBarco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrarBarco();
			}
		});
		GridBagConstraints gbc_btnBorrarBarco = new GridBagConstraints();
		gbc_btnBorrarBarco.insets = new Insets(0, 0, 5, 0);
		gbc_btnBorrarBarco.gridx = 2;
		gbc_btnBorrarBarco.gridy = 8;
		contentPane.add(btnBorrarBarco, gbc_btnBorrarBarco);
		
		JSeparator separator = new JSeparator();
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.anchor = GridBagConstraints.NORTH;
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridwidth = 3;
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 9;
		contentPane.add(separator, gbc_separator);
		
		JLabel lblCapacidadTotal = new JLabel("Capacidad total:");
		GridBagConstraints gbc_lblCapacidadTotal = new GridBagConstraints();
		gbc_lblCapacidadTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblCapacidadTotal.insets = new Insets(0, 0, 0, 5);
		gbc_lblCapacidadTotal.gridx = 0;
		gbc_lblCapacidadTotal.gridy = 10;
		contentPane.add(lblCapacidadTotal, gbc_lblCapacidadTotal);
		
		JButton btnCalcularCapacidad = new JButton("Calcular Capacidad");
		btnCalcularCapacidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcularCapacidad();
			}
		});
		
		campoCapacidad = new JTextField();
		campoCapacidad.setEditable(false);
		GridBagConstraints gbc_campoCapacidad = new GridBagConstraints();
		gbc_campoCapacidad.anchor = GridBagConstraints.WEST;
		gbc_campoCapacidad.insets = new Insets(0, 0, 0, 5);
		gbc_campoCapacidad.gridx = 1;
		gbc_campoCapacidad.gridy = 10;
		contentPane.add(campoCapacidad, gbc_campoCapacidad);
		campoCapacidad.setColumns(10);
		GridBagConstraints gbc_btnCalcularCapacidad = new GridBagConstraints();
		gbc_btnCalcularCapacidad.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCalcularCapacidad.gridx = 2;
		gbc_btnCalcularCapacidad.gridy = 10;
		contentPane.add(btnCalcularCapacidad, gbc_btnCalcularCapacidad);
	}
	
	/**
	 * Acciones que se toman cuando se presiona el botón "crear Barco".
	 * Se deben obtener los datos necesarios, enviarlos a la clase
	 * de control y mostrar mensaje (dependiendo del resultado).
	 */
	public void crearBarco() {
		String matricula = campoMatricula.getText();
		String nacionalidad = campoNacionalidad.getText();
		String volumen = campoVolumen.getText();
		ButtonModel botonSeleccionado = opcionesTipoBarco.getSelection();
		String tipo = botonSeleccionado.getActionCommand();
		String pasajeros = campoPasajeros.getText();
		String liquidos = checkLiquidos.isSelected()?"true":"false";
		
		try {
			control.adicionarBarco(matricula, nacionalidad, volumen, tipo, pasajeros, liquidos);
			JOptionPane.showMessageDialog(this,"El barco fue registrado exitosamente.");
		}
		catch (BarcoException errorRegistro) {
			JOptionPane.showMessageDialog(this,errorRegistro.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Acciones que se toman cuando se presiona el botón "borrar Barco".
	 * Se deben obtener los datos necesarios, enviarlos a la clase
	 * de control y mostrar mensaje (dependiendo del resultado).
	 */
	public void borrarBarco() {
		String matricula = campoMatricula.getText();
		
		try {
			control.borrarBarco(matricula);
			JOptionPane.showMessageDialog(this,"El registro del barco fue borrado");
		}
		catch (BarcoException errorBorrado) {
			JOptionPane.showMessageDialog(this,errorBorrado.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	
	
	/**
	 * Acciones que se toman cuando se presiona el botón "calcular capacidad".
	 * Se deben obtener los datos necesarios, enviarlos a la clase
	 * de control y mostrar el valor en el campo correspondiente
	 */
	public void calcularCapacidad() {
		String capacidad = control.calcularCapacidadTotal();
		campoCapacidad.setText(capacidad);
	}
}
