package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class Cadastro_materia_prima extends JFrame {

	private JPanel contentPane;
	private JTextField data;
	private JTextField descricao;
	private JTextField valor;
	private JTextField unidade;
	Connection con;
	Statement st;
	ResultSet rs;
	private JTextField id;

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
					Cadastro_materia_prima frame = new Cadastro_materia_prima();
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
	public Cadastro_materia_prima() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cadastro_materia_prima.class.getResource("/img/Red-Cargo-Boxes_35543.png")));
		setTitle("Cadastro de Materia Prima");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 645, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 608, 178);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(10, 11, 48, 14);
		panel.add(lblData);
		
		data = new JTextField();
		data.setEnabled(false);
		data.setEditable(false);
		data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		data.setBounds(10, 36, 96, 20);
		panel.add(data);
		data.setColumns(10);
		
		descricao = new JTextField();
		descricao.setEnabled(false);
		descricao.setBounds(10, 92, 426, 20);
		panel.add(descricao);
		descricao.setColumns(10);
		
		valor = new JTextField();
		valor.setEnabled(false);
		valor.setBounds(446, 92, 83, 20);
		panel.add(valor);
		valor.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor R$");
		lblValor.setBounds(446, 67, 48, 14);
		panel.add(lblValor);
		
		unidade = new JTextField();
		unidade.setEnabled(false);
		unidade.setBounds(539, 92, 55, 20);
		panel.add(unidade);
		unidade.setColumns(10);
		
		JLabel lblUnidade = new JLabel("Unidade");
		lblUnidade.setBounds(539, 67, 48, 14);
		panel.add(lblUnidade);
		
		JLabel lblDescrioDaMateria = new JLabel("Descri\u00E7\u00E3o da Materia Prima");
		lblDescrioDaMateria.setBounds(116, 11, 133, 14);
		panel.add(lblDescrioDaMateria);
		
		JComboBox descricao1 = new JComboBox();
		descricao1.setEnabled(false);
		descricao1.setModel(new DefaultComboBoxModel(new String[] {""}));
		
		
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from Cadastro_materia_prima where status= 1";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				descricao1.addItem(rs.getString("descricao"));
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
		
		
		descricao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
try {
					
					String sql ="select * from Cadastro_materia_prima where descricao=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)descricao1.getSelectedItem() );
					
					ResultSet set = statement.executeQuery();

					while (set.next()) {
				      id.setText(set.getString("codigo"));
					  descricao.setText(set.getString("descricao"));
					  valor.setText(set.getString("valor"));
					  unidade.setText(set.getString("unidade"));
					 

					  descricao.setEnabled(true);
					  valor.setEnabled(true);
						unidade.setEnabled(true);
						
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
			}
		});
		descricao1.setBounds(116, 36, 478, 21);
		panel.add(descricao1);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 134, 89, 23);
		panel.add(btnNovo);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(descricao.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo descrição!");
				}
				else if(valor.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo valor!");
				}
				else if(unidade.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo unidade!");
				}
			
				else {
					
					try {
						Connection con = Conexao.faz_conexao();
						String sql = "insert into cadastro_materia_prima(data, descricao, valor, unidade, status) value (?, ?, ?, ?, 1)";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						
						stmt.setString(1, data.getText());
						stmt.setString(2, descricao.getText());
						stmt.setString(3, valor.getText());
						stmt.setString(4, unidade.getText());

						
						
						stmt.execute();
						
						stmt.close();
						con.close();
						JOptionPane.showMessageDialog(null, "Materia prima cadastrada com sucesso!");
						descricao.setText("");
						valor.setText("");
						unidade.setText("");

						
						
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}	
			}
		});
		btnSalvar.setBounds(109, 134, 89, 23);
		panel.add(btnSalvar);
		btnSalvar.setEnabled(false);
		
		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.setBounds(208, 134, 89, 23);
		panel.add(btnLocalizar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {		
					Connection con = Conexao.faz_conexao();
					
					String sql = "update cadastro_materia_prima set descricao=?, valor=?, unidade=? where codigo=?";
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, descricao.getText());
					stmt.setString(2, valor.getText());
					stmt.setString(3, unidade.getText());
					stmt.setString(4, id.getText());

			
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Materia Prima editada com sucesso!!!");
					Cadastro_materia_prima.this.dispose();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnEditar.setBounds(307, 134, 89, 23);
		panel.add(btnEditar);
		btnEditar.setEnabled(false);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(406, 134, 89, 23);
		panel.add(btnSair);
		
		id = new JTextField();
		id.setEnabled(false);
		id.setEditable(false);
		id.setBounds(739, 36, 50, 20);
		panel.add(id);
		id.setColumns(10);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(739, 11, 48, 14);
		panel.add(lblId);
		
		JLabel label = new JLabel("Descri\u00E7\u00E3o da Materia Prima");
		label.setBounds(10, 67, 133, 14);
		panel.add(label);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro_materia_prima.this.dispose();
			}
		});
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				descricao1.setEnabled(true);
				btnLocalizar.setEnabled(false);
				btnNovo.setEnabled(false);
				btnEditar.setEnabled(true);
			}
		});
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				descricao.setEnabled(true);
				valor.setEnabled(true);
				unidade.setEnabled(true);
				btnSalvar.setEnabled(true);
				btnLocalizar.setEnabled(false);
				btnNovo.setEnabled(false);
				btnEditar.setEnabled(false);
			}
		});
	}
}
