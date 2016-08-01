package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import view.observer.LoginObserver;
import javax.swing.JButton;

public class LoginPanelImpl extends JPanel implements LoginPanel, ActionListener{
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private static final long serialVersionUID = 1;
	private JButton btnLogin;
	private JButton btnClear;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private LoginObserver observer;
	private JButton btnRecorded;
	private JLabel lblRecorded;

	/**
	 * Create the panel.
	 */
	public LoginPanelImpl() {
		setBackground(Color.WHITE);
		this.setLayout(null);
		
		lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Calibri", Font.ITALIC, 14));
		lblPassword.setBounds(430, 21, 160, 25);
		add(lblPassword);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsername.setFont(new Font("Calibri", Font.ITALIC, 14));
		lblUsername.setBounds(10, 21, 160, 25);
		add(lblUsername);
		
		txtPassword = new JPasswordField();
		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		txtPassword.setFont(new Font("Calibri", Font.PLAIN, 13));
		txtPassword.setBounds(430, 46, 160, 20);
		add(txtPassword);
		
		txtUsername = new JTextField();
		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		txtUsername.setFont(new Font("Calibri", Font.PLAIN, 13));
		add(txtUsername);
		txtUsername.setColumns(10);
		txtUsername.setBounds(10, 46, 160, 20);
		
		
		btnLogin = new JButton("Accedi");
		btnLogin.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
		btnLogin.setBounds(236, 36, 120, 40);
		add(btnLogin);
		
		btnClear = new JButton("Pulisci tutto");
		btnClear.setFont(new Font("Calibri", Font.ITALIC, 13));
		btnClear.setBounds(485, 424, 105, 30);
		add(btnClear);
		
		btnRecorded = new JButton("Registrati");
		btnRecorded.setEnabled(false);
		btnRecorded.setFont(new Font("Calibri", Font.ITALIC, 13));
		btnRecorded.setBounds(10, 424, 89, 30);
		add(btnRecorded);
		
		lblRecorded = new JLabel("Registra nuovo dipendente");
		lblRecorded.setEnabled(false);
		lblRecorded.setFont(new Font("Calibri", Font.ITALIC, 13));
		lblRecorded.setBounds(10, 399, 143, 25);
		add(lblRecorded);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object isPressed = e.getSource();

		if (isPressed == btnLogin) {
			this.observer.loginEmployee(txtUsername.getText(), new String(txtPassword.getPassword()));
		} else if (isPressed == btnClear) {
			this.clearLogin();
		}		
	}

	@Override
	public void clearLogin() {
		this.txtUsername.setText("");
		this.txtPassword.setText("");
		
	}

	@Override
	public void attachObserver(LoginObserver observer) {
		this.observer=observer;
		
	}
}
