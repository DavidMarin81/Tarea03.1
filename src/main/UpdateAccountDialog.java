package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.AccMovement;
import modelo.Account;
import modelo.Departamento;
import modelo.Empleado;
import modelo.servicio.AccountServicio;
import modelo.servicio.IAccountServicio;

import java.awt.Color;
import javax.swing.SwingConstants;

public class UpdateAccountDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtImporteCuenta;
	private JButton okButton;
	private Account cuentaACrearOActualizar = null;
	private BigDecimal cantidadIntroducida = null;
	
	
	public Account getResult() {
		return this.cuentaACrearOActualizar;
	}
	
	public BigDecimal getMovResult() {
		return this.cantidadIntroducida;
	}
	
	//Se crea un Account para introducirle los datos que vienen del JFrame principal
	Account cuentaAPasar;
	
	/**
	 * Create the dialog.
	 */
	public void initComponents() {
		
		this.setTitle("Modificar cuenta");
		
		setBounds(100, 100, 598, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel txtMensajeError = new JLabel("");
		txtMensajeError.setForeground(new Color(255, 0, 0));
		txtMensajeError.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMensajeError.setBounds(39, 163, 488, 24);
		contentPanel.add(txtMensajeError);

		txtImporteCuenta = new JTextField();
		txtImporteCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		txtImporteCuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtImporteCuenta.setBounds(236, 104, 197, 23);
		contentPanel.add(txtImporteCuenta);
		txtImporteCuenta.setColumns(10);

		JLabel lblImporteCuenta = new JLabel("Importe");
		lblImporteCuenta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblImporteCuenta.setBounds(156, 103, 72, 24);
		contentPanel.add(lblImporteCuenta);
		
		JLabel lblTitulo = new JLabel("Introduce el importe de la cuenta a crear");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(39, 35, 488, 24);
		contentPanel.add(lblTitulo);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("Guardar");

		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cuentaACrearOActualizar = null;
				UpdateAccountDialog.this.dispose();
				
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		ActionListener crearBtnActionListener = new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					//Se pasa el JTextField a BigDecimal
					cantidadIntroducida = new BigDecimal(txtImporteCuenta.getText().trim());
					
					UpdateAccountDialog.this.dispose();
					
				//Entra en el catch si uno de los datos es incorrecto
				} catch (Exception ex) {
					ex.printStackTrace();
					cuentaACrearOActualizar = null;
					txtImporteCuenta.setText("");
					txtMensajeError.setText("Datos incorrectos");
				}
			} 
		};

		this.okButton.addActionListener(crearBtnActionListener);

	}
	
	public UpdateAccountDialog(Window owner, String title, ModalityType modalityType, Account account) {
		super(owner, title, modalityType);
		initComponents();
		cuentaACrearOActualizar = account;
		txtImporteCuenta.setText(account.getAmount() + "");
		this.setLocationRelativeTo(owner);
	}
}
