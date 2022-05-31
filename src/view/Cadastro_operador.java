package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import java.awt.Toolkit;

public class Cadastro_operador extends JFrame {

	private JPanel contentPane;
	private JTextField nome;
	private JTextField user;
	private JPasswordField pass;
	private JTextField id;
	Connection con;
	Statement st;
	ResultSet rs;
	private JPasswordField pass2;

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
					Cadastro_operador frame = new Cadastro_operador();
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
	public Cadastro_operador() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cadastro_operador.class.getResource("/img/icons8-adicionar-usu\u00E1rio-masculino-32.png")));
		setTitle("Cadastro de Operador");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 351, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 314, 323);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 11, 48, 14);
		panel.add(lblNome);
		
		JComboBox nome1 = new JComboBox();
		nome1.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_operador where status= 1";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				nome1.addItem(rs.getString("nome"));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR");
		}
		finally 
		{
			try {
				st.close();
				rs.close();
				con.close();
				
			} catch (Exception e2) {
				//JOptionPane.showMessageDialog(null, "ERROR CLOSE");
			}
		}
		nome1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
try {
					
					String sql ="select * from cadastro_operador where nome=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)nome1.getSelectedItem() );
					ResultSet set = statement.executeQuery();
					

					while (set.next()) {
				      id.setText(set.getString("codigo"));
				      nome.setText(set.getString("nome"));
				      user.setText(set.getString("login"));
				
				      

				     
					 

					  nome.setEnabled(true);
					  user.setEnabled(true);
						pass.setEnabled(true);
						
						

					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
				
			}
		});
		nome1.setEnabled(false);
		nome1.setBounds(10, 35, 290, 20);
		panel.add(nome1);
		
		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setBounds(10, 67, 48, 14);
		panel.add(lblNome_1);
		
		nome = new JTextField();
		nome.setBounds(10, 92, 290, 20);
		panel.add(nome);
		nome.setColumns(10);
		
		JLabel lblLoginDoUsuario = new JLabel("Login do Usuario");
		lblLoginDoUsuario.setBounds(10, 123, 81, 14);
		panel.add(lblLoginDoUsuario);
		
		user = new JTextField();
		user.setBounds(10, 148, 290, 20);
		panel.add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(10, 204, 140, 20);
		panel.add(pass);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(10, 179, 48, 14);
		panel.add(lblSenha);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(420, 11, 48, 14);
		panel.add(lblId);
		
		id = new JTextField();
		id.setEnabled(false);
		id.setEditable(false);
		id.setBounds(420, 35, 96, 20);
		panel.add(id);
		id.setColumns(10);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if(nome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo nome!");
				}
				else if(user.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo login!");
				}
				else if(pass.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo senha!");
				}
				else if(pass2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo confirmar senha!");
				}
				
				else if(new String(pass.getPassword()).equals(new String(pass2.getPassword()))) 
				{
					
				//else {	
					try {
						
						String sql ="select * from cadastro_operador where login=?";
						Connection con = Conexao.faz_conexao();
						PreparedStatement statement = con.prepareStatement(sql);
						//statement.setString(1, user.getCpf());
						statement.setString(1, (String)user.getText() );
						ResultSet set = statement.executeQuery();
						
						if(set.next()){
							String login = set.getString(2);
							if(login.equals(user.getText())){
								JOptionPane.showMessageDialog(null, "Usuario já existe!");
							}
							
							}
						else {
							try {
								Connection con2 = Conexao.faz_conexao();
								String sql2 = "insert into cadastro_operador(login, senha, nome, status) value (?, ?, ?, 1)";
								
								PreparedStatement stmt = con2.prepareStatement(sql2);
								
								stmt.setString(1, user.getText());
								stmt.setString(2, pass.getText());
								stmt.setString(3, nome.getText());
								

								
								
								stmt.execute();
								
								stmt.close();
								con.close();
								JOptionPane.showMessageDialog(null, "Operador cadastrado com sucesso!");
								nome.setText("");
								user.setText("");
								pass.setText("");
								pass2.setText("");
								
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
					
					
				} 
				
				else 
				{
				    JOptionPane.showMessageDialog(null, "Senhas não conferem!");
				}
				
				
	                
					
				
				
				
				
				
			}
		});
		btnSalvar.setBounds(10, 247, 89, 23);
		panel.add(btnSalvar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo nome!");
				}
				else if(user.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo login!");
				}
				else if(pass.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo senha!");
				}
				else if(pass2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo confirmar senha!");
				}
				
				else if(new String(pass.getPassword()).equals(new String(pass2.getPassword()))) 
				{
					
				//else {	
					try {
						
						String sql ="select * from cadastro_operador where login=?";
						Connection con = Conexao.faz_conexao();
						PreparedStatement statement = con.prepareStatement(sql);
						//statement.setString(1, user.getCpf());
						statement.setString(1, (String)user.getText() );
						ResultSet set = statement.executeQuery();
						
						if(set.next()){
							String login = set.getString(2);
							if(login.equals(user.getText())){
								JOptionPane.showMessageDialog(null, "Usuario já existe!");
							}
							
							}
						else {
							try {		
								Connection con2 = Conexao.faz_conexao();
								
								String sql2 = "update cadastro_operador set nome=?, login=?, senha=? where codigo=?";
								PreparedStatement stmt = con.prepareStatement(sql2);
								
								stmt.setString(1, nome.getText());
								stmt.setString(2, user.getText());
								stmt.setString(3, pass.getText());
								stmt.setString(4, id.getText());

						
								stmt.execute();
								
								stmt.close();
								con.close();
								JOptionPane.showMessageDialog(null, "Usuario editado com sucesso!!!");
								Cadastro_operador.this.dispose();
								
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
					
					
				} 
				
				else 
				{
				    JOptionPane.showMessageDialog(null, "Senhas não conferem!");
				}
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBounds(208, 247, 89, 23);
		panel.add(btnEditar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro_operador.this.dispose();
			}
		});
		btnSair.setBounds(109, 281, 89, 23);
		panel.add(btnSair);
		
		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome1.setEnabled(true);
				btnLocalizar.setEnabled(false);
				btnEditar.setEnabled(true);
				btnSalvar.setEnabled(false);
			}
		});
		btnLocalizar.setBounds(109, 247, 89, 23);
		panel.add(btnLocalizar);
		
		pass2 = new JPasswordField();
		pass2.setBounds(160, 204, 140, 20);
		panel.add(pass2);
		
		JLabel lblNewLabel = new JLabel("Confirmar senha");
		lblNewLabel.setBounds(160, 179, 81, 14);
		panel.add(lblNewLabel);
	}
}
