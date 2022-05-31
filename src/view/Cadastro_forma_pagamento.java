package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class Cadastro_forma_pagamento extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField data;
	private JTextField forma;
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
					Cadastro_forma_pagamento frame = new Cadastro_forma_pagamento();
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
	public Cadastro_forma_pagamento() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cadastro_forma_pagamento.class.getResource("/img/1495815224-jd15_84582.png")));
		setTitle("Forma de Pagamento");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 542, 297);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 505, 238);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(583, 11, 48, 14);
		panel.add(lblId);
		
		id = new JTextField();
		id.setEditable(false);
		id.setEnabled(false);
		id.setBounds(583, 36, 96, 20);
		panel.add(id);
		id.setColumns(10);
		
		data = new JTextField();
		data.setEditable(false);
		data.setEnabled(false);
		data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		data.setBounds(10, 36, 96, 20);
		panel.add(data);
		data.setColumns(10);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(10, 11, 48, 14);
		panel.add(lblData);
		
		JLabel lblFormaDePagamento = new JLabel("Forma de pagamento");
		lblFormaDePagamento.setBounds(10, 67, 109, 14);
		panel.add(lblFormaDePagamento);
		
		JComboBox forma1 = new JComboBox();
		forma1.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_forma_pagamento where status= 1";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				forma1.addItem(rs.getString("forma"));
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
		
		forma1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
try {
					
					String sql ="select * from cadastro_forma_pagamento where forma=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)forma1.getSelectedItem() );
					
					ResultSet set = statement.executeQuery();

					while (set.next()) {
				      id.setText(set.getString("codigo"));
					  forma.setText(set.getString("forma"));
					 

					  forma.setEnabled(true);
					 
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		
		forma1.setEnabled(false);
		forma1.setBounds(10, 92, 485, 20);
		panel.add(forma1);
		
		JLabel label = new JLabel("Forma de pagamento");
		label.setBounds(10, 123, 109, 14);
		panel.add(label);
		
		forma = new JTextField();
		forma.setEnabled(false);
		forma.setBounds(10, 148, 485, 20);
		panel.add(forma);
		forma.setColumns(10);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 192, 89, 23);
		panel.add(btnNovo);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(forma.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo forma de pagamento!");
				}
				
				else {
					
					try {
						Connection con = Conexao.faz_conexao();
						String sql = "insert into cadastro_forma_pagamento(data, forma, status) value (?, ?, 1)";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						
						stmt.setString(1, data.getText());
						stmt.setString(2, forma.getText());
						
						
						
						stmt.execute();
						
						stmt.close();
						con.close();
						JOptionPane.showMessageDialog(null, "Forma de pagamento castrada com sucesso!");
						forma.setText("");
						
						
						
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}	
			}
		});
		btnIncluir.setBounds(109, 192, 89, 23);
		panel.add(btnIncluir);
		btnIncluir.setEnabled(false);
		
		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.setBounds(208, 192, 89, 23);
		panel.add(btnLocalizar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {		
					Connection con = Conexao.faz_conexao();
					
					String sql = "update cadastro_forma_pagamento set forma=? where codigo=?";
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, forma.getText());
					stmt.setString(2, id.getText());

			
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Forma de pagamento editada com sucesso!!!");
					Cadastro_forma_pagamento.this.dispose();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEditar.setBounds(307, 192, 89, 23);
		panel.add(btnEditar);
		btnEditar.setEnabled(false);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(406, 192, 89, 23);
		panel.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro_forma_pagamento.this.dispose();
			}
		});
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLocalizar.setEnabled(false);
				btnNovo.setEnabled(false);
				btnIncluir.setEnabled(false);
				btnEditar.setEnabled(true);
				forma1.setEnabled(true);
			}
		});
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNovo.setEnabled(false);
				btnIncluir.setEnabled(true);
				btnLocalizar.setEnabled(false);
				btnEditar.setEnabled(false);
				forma.setEnabled(true);
			}
		});
	}
}
