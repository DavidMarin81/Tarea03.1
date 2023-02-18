package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exceptions.InstanceNotFoundException;
import modelo.Departamento;
import modelo.Empleado;
import modelo.servicio.DepartamentoServicio;
import modelo.servicio.IEmpleadoServicio;
import modelo.servicio.EmpleadoServicio;
import modelo.servicio.IDepartamentoServicio;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DeptWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextArea mensajes_text_Area;
	private JList<Departamento> JListAllDepts;

	private IDepartamentoServicio departamentoServicio;
	private CreateNewDeptDialog createDialog;
	private JButton btnModificarImporteCuenta;
	private JButton btnEliminarCuenta;
	private JTextField txtIdEmpleado;
	
	
	//Se crea un objeto IEmpleadoServicio
	private IEmpleadoServicio empleadoServicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeptWindow frame = new DeptWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DeptWindow() {

		departamentoServicio = new DepartamentoServicio();
		
		//Se crea un objeto EmpleadoServicio
		empleadoServicio = new EmpleadoServicio();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 847, 772);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(8, 8, 821, 714);
		contentPane.add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 475, 669, 228);
		panel.add(scrollPane);
		
				mensajes_text_Area = new JTextArea();
				scrollPane.setViewportView(mensajes_text_Area);
				mensajes_text_Area.setEditable(false);
				mensajes_text_Area.setText("Panel de mensajes");
				mensajes_text_Area.setForeground(new Color(255, 0, 0));
				mensajes_text_Area.setFont(new Font("Monospaced", Font.PLAIN, 13));

		JButton btnShowAllAccounts = new JButton("Mostrar cuentas");

		btnShowAllAccounts.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnShowAllAccounts.setBounds(49, 148, 208, 36);
		panel.add(btnShowAllAccounts);

		btnModificarImporteCuenta = new JButton("Modificar importe cuenta");

		JListAllDepts = new JList<Departamento>();

		JListAllDepts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		JListAllDepts.setBounds(403, 37, 377, 200);

		JScrollPane scrollPanel_in_JlistAllDepts = new JScrollPane(JListAllDepts);
		scrollPanel_in_JlistAllDepts.setLocation(300, 34);
		scrollPanel_in_JlistAllDepts.setSize(500, 382);
		
		panel.add(scrollPanel_in_JlistAllDepts);
	

		JButton btnCrearNuevaCuenta = new JButton("Crear nueva cuenta");

		btnCrearNuevaCuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCrearNuevaCuenta.setBounds(49, 218, 208, 36);
		panel.add(btnCrearNuevaCuenta);

		btnModificarImporteCuenta.setEnabled(false);
		btnModificarImporteCuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModificarImporteCuenta.setBounds(49, 295, 208, 36);
		panel.add(btnModificarImporteCuenta);

		btnEliminarCuenta = new JButton("Eliminar cuenta");

		btnEliminarCuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEliminarCuenta.setEnabled(false);
		btnEliminarCuenta.setBounds(49, 380, 208, 36);
		panel.add(btnEliminarCuenta);
		
		JLabel lblIntrodEmp = new JLabel("Introduzca el nº de empleado");
		lblIntrodEmp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIntrodEmp.setBounds(49, 36, 197, 36);
		panel.add(lblIntrodEmp);
		
		txtIdEmpleado = new JTextField();
		txtIdEmpleado.addKeyListener(new KeyAdapter() {
			
			//Acción al pulsar la tecla enter
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					int idEmpIntroducido = 0;
					String IdIntroducido = ((JTextField) e.getSource()).getText().trim();
					try {
						idEmpIntroducido = Integer.parseInt(IdIntroducido);
						Empleado empleado = empleadoServicio.findEmpById(idEmpIntroducido);
						//Si el empleado existe
						if (empleado.getEmpno() == idEmpIntroducido) {
							addMensaje(true, "El empleado " + idEmpIntroducido + " existe en la BBDD");
						} 
						//Si el empleado no existe, entra en el catch (InstanceNotFoundException)
					} catch (NumberFormatException nfe) {

						addMensaje(true, "Introduzca un número entero");
						txtIdEmpleado.setText("");

					} catch (InstanceNotFoundException infe) {

						addMensaje(true, "El empleado: " + idEmpIntroducido + " no existe");
					} catch (Exception ex) {
						System.out.println("Ha ocurrido una excepción: " + ex.getMessage());
						addMensaje(true, "Ha ocurrido un error y no se ha podido recuperar el empleado con id: "
								+ idEmpIntroducido);

					}
					//Se limpia el txtField
					txtIdEmpleado.setText("");
				} 
				
			}
		});
		txtIdEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIdEmpleado.setBounds(49, 80, 138, 41);
		panel.add(txtIdEmpleado);
		txtIdEmpleado.setColumns(10);

		// Eventos
		ActionListener showAllDepartamentosActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getAllDepartamentos();
			}
		};
		btnShowAllAccounts.addActionListener(showAllDepartamentosActionListener);

		ActionListener crearListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				JFrame owner = (JFrame) SwingUtilities.getRoot((Component) e.getSource());
				createDialog = new CreateNewDeptDialog(owner, "Crear nuevo departamento",
						Dialog.ModalityType.DOCUMENT_MODAL, null);
				showDialog();
			}
		};
		btnCrearNuevaCuenta.addActionListener(crearListener);

		ActionListener modificarListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIx = JListAllDepts.getSelectedIndex();
				if (selectedIx > -1) {
					Departamento departamento = (Departamento) JListAllDepts.getModel().getElementAt(selectedIx);
					if (departamento != null) {

						JFrame owner = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

						createDialog = new CreateNewDeptDialog(owner, "Modificar departamento",
								Dialog.ModalityType.DOCUMENT_MODAL, departamento);
						showDialog();
					}
				}
			}
		};

		btnModificarImporteCuenta.addActionListener(modificarListener);

		ListSelectionListener selectionListListener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					int selectedIx = JListAllDepts.getSelectedIndex();
					btnModificarImporteCuenta.setEnabled((selectedIx > -1));
					btnEliminarCuenta.setEnabled((selectedIx > -1));
					if (selectedIx > -1) {
						Departamento d = (Departamento) DeptWindow.this.JListAllDepts.getModel().getElementAt(selectedIx);
						if (d != null) {
							addMensaje(true, "Se ha seleccionado el d: " + d);
						}
					}
				}
			}
		};
		JListAllDepts.addListSelectionListener(selectionListListener);

		ActionListener deleteListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIx = JListAllDepts.getSelectedIndex();
				if (selectedIx > -1) {
					Departamento d = (Departamento) JListAllDepts.getModel().getElementAt(selectedIx);
					if (d != null) {
						try {
							boolean exito = departamentoServicio.delete(d.getDeptno());
							if (exito) {
								addMensaje(true, "Se ha eliminado el dept con id: " + d.getDeptno());
								getAllDepartamentos();
							}
						} catch (exceptions.InstanceNotFoundException e1) {
							addMensaje(true, "No se ha podido borrar el departamento. No se ha encontrado con id: "
									+ d.getDeptno());
						} catch (Exception ex) {
							addMensaje(true, "No se ha podido borrar el departamento. ");
							System.out.println("Exception: " + ex.getMessage());
							ex.printStackTrace();
						}
					}
				}
			}
		};
		btnEliminarCuenta.addActionListener(deleteListener);
	}
	
	private void addMensaje(boolean keepText, String msg) {
		String oldText = "";
		if (keepText) {
			oldText = mensajes_text_Area.getText();

		}
		oldText = oldText + "\n" + msg;
		mensajes_text_Area.setText(oldText);

	}

	private void showDialog() {
		createDialog.setVisible(true);
		Departamento departamentoACrear = createDialog.getResult();
		if (departamentoACrear != null) {

			saveOrUpdate(departamentoACrear);
		}
	}

	private void saveOrUpdate(Departamento dept) {
		try {
			Departamento nuevo = departamentoServicio.saveOrUpdate(dept);
			if (nuevo != null) {
				addMensaje(true, "Se ha creado un departamento con id: " + nuevo.getDeptno());
				getAllDepartamentos();
			} else {
				addMensaje(true, " El departamento no se ha creado/actualizado correctamente");
			}

		} catch (Exception ex) {
			addMensaje(true, "Ha ocurrido un error y no se ha podido crear el departamento");
		}
	}

	private void getAllDepartamentos() {
		List<Departamento> departamentos = departamentoServicio.getAll();
		addMensaje(true, "Se han recuperado: " + departamentos.size() + " departamentos");
		DefaultListModel<Departamento> defModel = new DefaultListModel<>();

		defModel.addAll(departamentos);

		JListAllDepts.setModel(defModel);

	}
}
