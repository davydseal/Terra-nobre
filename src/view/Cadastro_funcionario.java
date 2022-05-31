package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class Cadastro_funcionario extends JFrame {

	private JPanel contentPane;
	private JTextField data;
	private JTextField nome;
	private JTextField funcao;
	private JTextField id;
	Connection con;
	Statement st;
	ResultSet rs;

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
					Cadastro_funcionario frame = new Cadastro_funcionario();
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
	public Cadastro_funcionario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cadastro_funcionario.class.getResource("/img/person_user_customer_man_male_man_boy_people_1687.png")));
		setTitle("Cadastro de Funcion\u00E1rios");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 559, 186);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(10, 75, 48, 14);
		panel.add(lblData);
		
		data = new JTextField();
		data.setEnabled(false);
		data.setEditable(false);
		data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		data.setBounds(10, 100, 96, 20);
		panel.add(data);
		data.setColumns(10);
		
		nome = new JTextField();
		nome.setEnabled(false);
		nome.setBounds(116, 100, 280, 20);
		panel.add(nome);
		nome.setColumns(10);
		
		JLabel lblNomeDoFuncionrio = new JLabel("Nome do Funcion\u00E1rio");
		lblNomeDoFuncionrio.setBounds(116, 75, 103, 14);
		panel.add(lblNomeDoFuncionrio);
		
		funcao = new JTextField();
		funcao.setEnabled(false);
		funcao.setBounds(406, 100, 141, 20);
		panel.add(funcao);
		funcao.setColumns(10);
		
		JLabel lblFuno = new JLabel("Fun\u00E7\u00E3o");
		lblFuno.setBounds(406, 75, 48, 14);
		panel.add(lblFuno);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(627, 11, 48, 14);
		panel.add(lblId);
		
		id = new JTextField();
		id.setEnabled(false);
		id.setEditable(false);
		id.setBounds(627, 36, 96, 20);
		panel.add(id);
		id.setColumns(10);
		
		JComboBox nome1 = new JComboBox();
		nome1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
try {
					
					String sql ="select * from cadastro_funcionario where nome=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)nome1.getSelectedItem() );
					
					ResultSet set = statement.executeQuery();

					while (set.next()) {
				      id.setText(set.getString("codigo"));
					  nome.setText(set.getString("nome"));
					  funcao.setText(set.getString("funcao"));
					 

					  nome.setEnabled(true);
					  funcao.setEnabled(true);
						
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
			}
		});
		nome1.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_funcionario where status= 1";
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
		nome1.setEnabled(false);
		nome1.setBounds(10, 43, 537, 21);
		panel.add(nome1);
		
		JLabel label = new JLabel("Nome do Funcion\u00E1rio");
		label.setBounds(10, 18, 103, 14);
		panel.add(label);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 143, 89, 23);
		panel.add(btnNovo);
		
		JButton btnSalvar = new JButton("Incluir");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(nome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo nome!");
				}
				else if(funcao.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo função!");
				}
				
				else {
					
					try {
						Connection con = Conexao.faz_conexao();
						String sql = "insert into cadastro_funcionario(data, nome, funcao, status) value (?, ?, ?, 1)";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						
						stmt.setString(1, data.getText());
						stmt.setString(2, nome.getText());
						stmt.setString(3, funcao.getText());

						
						
						stmt.execute();
						
						stmt.close();
						con.close();
						JOptionPane.showMessageDialog(null, "Funcionario cadastrado com sucesso!");
						nome.setText("");
						funcao.setText("");

						
						
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}
				
				
			}
		});
		btnSalvar.setBounds(109, 143, 89, 23);
		panel.add(btnSalvar);
		btnSalvar.setEnabled(false);
		
		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.setBounds(208, 143, 89, 23);
		panel.add(btnLocalizar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {		
					Connection con = Conexao.faz_conexao();
					
					String sql = "update cadastro_funcionario set nome=?, funcao=? where codigo=?";
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, nome.getText());
					stmt.setString(2, funcao.getText());
					stmt.setString(3, id.getText());

			
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Funcionario editado com sucesso!!!");
					Cadastro_funcionario.this.dispose();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEditar.setBounds(307, 143, 89, 23);
		panel.add(btnEditar);
		btnEditar.setEnabled(false);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(406, 143, 89, 23);
		panel.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro_funcionario.this.dispose();
			}
		});
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome1.setEnabled(true);
				btnNovo.setEnabled(false);
				btnEditar.setEnabled(true);
				btnSalvar.setEnabled(false);
				btnLocalizar.setEnabled(false);
			}
		});
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome.setEnabled(true);
				funcao.setEnabled(true);
				btnNovo.setEnabled(false);
				btnEditar.setEnabled(false);
				btnSalvar.setEnabled(true);
				btnLocalizar.setEnabled(false);
			}
		});
	}
}
