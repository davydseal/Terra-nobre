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

public class Cadastro_tipo_sacaria extends JFrame {

	private JPanel contentPane;
	private JTextField data;
	private JTextField tipo2;
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
					Cadastro_tipo_sacaria frame = new Cadastro_tipo_sacaria();
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
	public Cadastro_tipo_sacaria() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cadastro_tipo_sacaria.class.getResource("/img/Sack_of_Flour_icon-icons.com_68694.png")));
		setTitle("Tipo de Sacaria");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 545, 298);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 508, 237);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(10, 11, 48, 14);
		panel.add(lblData);
		
		data = new JTextField();
		data.setEnabled(false);
		data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		data.setEditable(false);
		data.setBounds(10, 36, 96, 20);
		panel.add(data);
		data.setColumns(10);
		
		JLabel lblTipoDeSacaria = new JLabel("Tipo de Sacaria");
		lblTipoDeSacaria.setBounds(10, 67, 75, 14);
		panel.add(lblTipoDeSacaria);
		
		JComboBox tipo1 = new JComboBox();
		tipo1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String sql ="select * from cadastro_tipo_sacaria where tipo=?";
					Connection con = Conexao.faz_conexao();
				//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)tipo1.getSelectedItem() );
					
					ResultSet set = statement.executeQuery();

					while (set.next()) {
				      id.setText(set.getString("codigo"));
					  tipo2.setText(set.getString("tipo"));
					 

					  tipo2.setEnabled(true);
					  
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		tipo1.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_tipo_sacaria where status= 1";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				tipo1.addItem(rs.getString("tipo"));
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
		tipo1.setEnabled(false);
		tipo1.setBounds(10, 92, 485, 20);
		panel.add(tipo1);
		
		JLabel label = new JLabel("Tipo de Sacaria");
		label.setBounds(10, 123, 75, 14);
		panel.add(label);
		
		tipo2 = new JTextField();
		tipo2.setEnabled(false);
		tipo2.setBounds(10, 148, 485, 20);
		panel.add(tipo2);
		tipo2.setColumns(10);
		
		id = new JTextField();
		id.setEnabled(false);
		id.setEditable(false);
		id.setBounds(540, 92, 96, 20);
		panel.add(id);
		id.setColumns(10);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(540, 67, 48, 14);
		panel.add(lblId);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 191, 89, 23);
		panel.add(btnNovo);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(109, 191, 89, 23);
		panel.add(btnIncluir);
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tipo2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo tipo de sacaria!");
				}
				else {
				try {
					Connection con = Conexao.faz_conexao();
					String sql = "insert into cadastro_tipo_sacaria(data, tipo, status) value (?, ?, 1)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					
					stmt.setString(1, data.getText());
					stmt.setString(2, tipo2.getText());
					
					
					
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Tipo de sacaria cadastrado com sucesso!");
					tipo2.setText("");
					
					
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btnIncluir.setEnabled(false);
		
		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.setBounds(208, 191, 89, 23);
		panel.add(btnLocalizar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tipo2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo tipo de sacaria!");
				}
				else {
				try {		
					Connection con = Conexao.faz_conexao();
					
					String sql = "update cadastro_tipo_sacaria set tipo=? where codigo=?";
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, tipo2.getText());
					stmt.setString(2, id.getText());

			
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Tipo de sacaria editado com sucesso!!!");
	
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		btnEditar.setBounds(307, 191, 89, 23);
		panel.add(btnEditar);
		btnEditar.setEnabled(false);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(406, 191, 89, 23);
		panel.add(btnSair);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro_tipo_sacaria.this.dispose();
			}
		});
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNovo.setEnabled(false);
				btnLocalizar.setEnabled(false);
				btnEditar.setEnabled(true);
				tipo1.setEnabled(true);
			}
		});
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNovo.setEnabled(false);
				btnIncluir.setEnabled(true);
				btnLocalizar.setEnabled(false);
				btnEditar.setEnabled(false);
				tipo2.setEnabled(true);
			}
		});
	}
}
