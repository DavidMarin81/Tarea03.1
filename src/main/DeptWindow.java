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

import org.hibernate.internal.build.AllowSysOut;

import exceptions.InstanceNotFoundException;
import modelo.AccMovement;
import modelo.Account;
import modelo.Departamento;
import modelo.Empleado;
import modelo.servicio.AccountServicio;
import modelo.servicio.DepartamentoServicio;
import modelo.servicio.IEmpleadoServicio;
import modelo.servicio.EmpleadoServicio;
import modelo.servicio.IAccountServicio;
import modelo.servicio.IDepartamentoServicio;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

public class DeptWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextArea mensajes_text_Area;
	
	private JList<Account> JListAllEmpAccounts;

	private CreateNewAccountDialog createDialog;
	private UpdateAccountDialog updateDialog;
	private JButton btnModificarImporteCuenta;
	private JButton btnEliminarCuenta;
	private JTextField txtIdEmpleado;
	
	
	//Se crea un objeto IEmpleadoServicio
	private IEmpleadoServicio empleadoServicio;
	
	//Se crea un objeto IAccountServicio
	private IAccountServicio accountServicio;
	
	//Se crea un objeto Empleado y Account para pasar los datos al otro JFrame
	Empleado empleadoAPasar;
	Account cuentaAPasar;
	
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

		//Se crea un objeto EmpleadoServicio
		empleadoServicio = new EmpleadoServicio();
		
		//Se crea un objeto AccountServicio
		accountServicio = new AccountServicio();

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

		JListAllEmpAccounts = new JList<Account>();

		JListAllEmpAccounts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		
		JListAllEmpAccounts.setBounds(403, 37, 377, 200);

		JScrollPane scrollPanel_in_JlistAllDepts = new JScrollPane(JListAllEmpAccounts);
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
		
		JLabel lblIntrodEmp = new JLabel("Introduzca el n?? de empleado");
		lblIntrodEmp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIntrodEmp.setBounds(49, 36, 197, 36);
		panel.add(lblIntrodEmp);
		
		txtIdEmpleado = new JTextField();
		txtIdEmpleado.addKeyListener(new KeyAdapter() {
			
			//Acci??n al pulsar la tecla enter
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					int idEmpIntroducido = 0;
					String IdIntroducido = ((JTextField) e.getSource()).getText().trim();
					try {
						idEmpIntroducido = Integer.parseInt(IdIntroducido);
						Empleado empleado = empleadoServicio.findEmpById(idEmpIntroducido);
						empleadoAPasar = empleado;
						
						//Si el empleado existe
						if (empleado.getEmpno() == idEmpIntroducido) {
							addMensaje(true, "El empleado " + idEmpIntroducido + " existe en la BBDD");
						} 
						empleadoAPasar = empleado;
						//Si el empleado no existe, entra en el catch (InstanceNotFoundException)
					} catch (NumberFormatException nfe) {

						addMensaje(true, "Introduzca un n??mero entero");
						txtIdEmpleado.setText("");

					} catch (InstanceNotFoundException infe) {

						addMensaje(true, "El empleado: " + idEmpIntroducido + " no existe");
					} catch (Exception ex) {
						System.out.println("Ha ocurrido una excepci??n: " + ex.getMessage());
						addMensaje(true, "Ha ocurrido un error y no se ha podido recuperar el empleado con id: "
								+ idEmpIntroducido);

					}
				} 
				
			}
		});
		txtIdEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		txtIdEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtIdEmpleado.setBounds(49, 80, 138, 41);
		panel.add(txtIdEmpleado);
		txtIdEmpleado.setColumns(10);

		// Eventos
		ActionListener btnShowAllAccountsActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idEmpIntroducido = 0;
				String IdIntroducido = txtIdEmpleado.getText().trim();
				try {
					idEmpIntroducido = Integer.parseInt(IdIntroducido);
					getAllEmpAccounts(idEmpIntroducido);
					
				} catch (NumberFormatException nfe) {

					addMensaje(true, "Introduzca un n??mero entero");
					txtIdEmpleado.setText("");

				} catch (Exception ex) {
					System.out.println("Ha ocurrido una excepci??n: " + ex.getMessage());
					addMensaje(true, "Ha ocurrido un error y no se ha podido recuperar las cuentas con id: "
							+ idEmpIntroducido);

				}
				
			}
		};
		btnShowAllAccounts.addActionListener(btnShowAllAccountsActionListener);

		ActionListener crearListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFrame owner = (JFrame) SwingUtilities.getRoot((Component) e.getSource());
				createDialog = new CreateNewAccountDialog(owner, "Crear nueva cuenta",
						Dialog.ModalityType.DOCUMENT_MODAL, null, empleadoAPasar);
				showDialog();
			}
		};
		btnCrearNuevaCuenta.addActionListener(crearListener);

		ActionListener modificarListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedIx = JListAllEmpAccounts.getSelectedIndex();
				if (selectedIx > -1) {
					Account cuenta = (Account) JListAllEmpAccounts.getModel().getElementAt(selectedIx);
					if (cuenta != null) {

						JFrame owner = (JFrame) SwingUtilities.getRoot((Component) e.getSource());

						updateDialog = new UpdateAccountDialog(owner, "Modificar cuenta",
								Dialog.ModalityType.DOCUMENT_MODAL, cuenta);
						showUpdateDialog();
					}
				}
			}
		};

		btnModificarImporteCuenta.addActionListener(modificarListener);

		//Para seleccionar un elemento de la JList
		ListSelectionListener selectionListListener = new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					int selectedIx = JListAllEmpAccounts.getSelectedIndex();
					btnModificarImporteCuenta.setEnabled((selectedIx > -1));
					btnEliminarCuenta.setEnabled((selectedIx > -1));
					if (selectedIx > -1) {
						Account c = (Account) DeptWindow.this.JListAllEmpAccounts.getModel().getElementAt(selectedIx);
						if (c != null) {
							addMensaje(true, "Se ha seleccionado la cuenta: " + c);
							cuentaAPasar = c;
						}
					}
				}
			}
		};
		JListAllEmpAccounts.addListSelectionListener(selectionListListener);

		//Borrar cuenta con un id
		ActionListener deleteListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIx = JListAllEmpAccounts.getSelectedIndex();
				if (selectedIx > -1) {
					Account c = (Account) JListAllEmpAccounts.getModel().getElementAt(selectedIx);
					if (c != null) {
						try {
							boolean exito = accountServicio.delete(c.getAccountno());
							if (exito) {
								addMensaje(true, "Se ha eliminado la cuenta con id: " + c.getAccountno());
							}
						} catch (exceptions.InstanceNotFoundException e1) {
							addMensaje(true, "No se ha podido borrar la cuenta. No se ha encontrado con id: "
									+ c.getAccountno());
						} catch (Exception ex) {
							addMensaje(true, "No se ha podido borrar la cuenta. ");
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
		Account cuentaACrear = createDialog.getResult();
		if (cuentaACrear != null) {
			saveOrUpdateCuenta(cuentaACrear);
		}
	}
	
	private void showUpdateDialog() {
		updateDialog.setVisible(true);
		Account cuentaAModificar = updateDialog.getResult();
		BigDecimal cantidadACrear = updateDialog.getMovResult();
		
		if (cantidadACrear != null) {
			createMovement(cuentaAModificar, cantidadACrear);
		}
		
	}
	
	private void saveOrUpdateCuenta(Account cuenta) {
		try {
			Account nueva = accountServicio.saveOrUpdate(cuenta);
			if (nueva != null) {
					addMensaje(true, "Se ha creado/actualizado una cuenta con id: " + nueva.getAccountno());
				
			} else {
				addMensaje(true, "La cuenta no se ha creado/actualizado correctamente");
			}

		} catch (Exception ex) {
			addMensaje(true, "Ha ocurrido un error y no se ha podido crear la cuenta");
		}
	}
	
	private void createMovement(Account cuenta, BigDecimal cantidad) {
		try {
			Account nueva = accountServicio.saveOrUpdate(cuenta);
			AccMovement movimiento = new AccMovement();
			movimiento = 
					accountServicio.transferir(
							cuenta.getAccountno(),
							cuenta.getAccountno(),
							cantidad.doubleValue());
			if (movimiento != null) {
					addMensaje(true, "Se ha creado el movimiento: " + movimiento.getAccountMovId());
				
			} else {
				addMensaje(true, "El movimiento no se ha creado correctamente");
			}

		} catch (Exception ex) {
			addMensaje(true, "Ha ocurrido un error y no se ha podido crear el movimiento");
		}
	}

	//M??todo para mostrar las cuentas de un id pasado por parametro
	private void getAllEmpAccounts(int id) {
		List<Account> cuentas = empleadoServicio.getAllEmpAccounts(id);
		addMensaje(true, "Se han recuperado: " + cuentas.size() + " cuentas");
		DefaultListModel<Account> defModel2 = new DefaultListModel<>();

		defModel2.addAll(cuentas);

		JListAllEmpAccounts.setModel(defModel2);

	}
	
}
