package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Iniciar extends JFrame {
	
	Conexao con = new Conexao();

	private JPanel contentPane;
	private JTextField user;
	private JPasswordField pass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			System.err.println(ex);
		}
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Iniciar frame = new Iniciar();
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
	public Iniciar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Iniciar.class.getResource("/img/HomeServer_icon-icons.com_55232.png")));

		setTitle("TERRA NOBRE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 1382, 741);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setBounds(68, 116, 83, 30);
		contentPane.add(lblLogin);
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		JLabel label_1 = new JLabel("Nome de Usu\u00E1rio");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(68, 226, 105, 14);
		contentPane.add(label_1);
		label_1.setForeground(Color.DARK_GRAY);
		
		user = new JTextField();
		user.setFont(new Font("Tahoma", Font.PLAIN, 16));
		user.setBounds(68, 251, 270, 40);
		contentPane.add(user);
		user.setColumns(10);
		
		JLabel label_2 = new JLabel("Senha");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(68, 302, 48, 14);
		contentPane.add(label_2);
		label_2.setForeground(Color.DARK_GRAY);
		
		pass = new JPasswordField();
		pass.setFont(new Font("Tahoma", Font.PLAIN, 16));
		pass.setBounds(68, 327, 270, 40);
		contentPane.add(pass);
		
		JButton btnEntrar = new JButton("ENTRAR");
		btnEntrar.setBounds(140, 520, 112, 30);
		contentPane.add(btnEntrar);
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.faz_conexao();

					String sql = "Select * from cadastro_operador where login=? and senha=?";

					PreparedStatement stmt = con.prepareStatement(sql);

					stmt.setString(1, user.getText());
					stmt.setString(2, new String(pass.getPassword()));
					ResultSet rs = stmt.executeQuery();
					
					if (rs.next()) {
						System.setProperty("Login", user.getText()); 
						Tela_principal exibir = new Tela_principal();
						exibir.setVisible(true);
						setVisible(false);
						

					}



					else {
						JOptionPane.showMessageDialog(null, "Login ou Senha Incorreta...");

					}
					stmt.close();
					con.close();

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEntrar.setForeground(Color.DARK_GRAY);
		btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEntrar.setBackground(Color.LIGHT_GRAY);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(411, 0, 965, 707);
		contentPane.add(panel);
	}
}
