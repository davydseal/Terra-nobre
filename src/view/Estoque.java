package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;

public class Estoque extends JFrame {

	private JPanel contentPane;
	Connection con;
	Statement st;
	ResultSet rs;
	private JTextField textField_10;
	private JTable table_5;
	private JTextField textField_11;
	private JTable table_6;
	private JTextField textField_6;
	private JTable table_1;
	
	

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
					Estoque frame = new Estoque();
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
	public Estoque() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Estoque.class.getResource("/img/Stacked-Boxes_35541.png")));
		setBackground(Color.WHITE);
		setTitle("Produ\u00E7\u00E3o");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1250, 680);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_20 = new JPanel();
		panel_20.setBackground(Color.WHITE);
		tabbedPane.addTab("TIPO DE GESSO", null, panel_20, null);
		panel_20.setLayout(null);
		
		JPanel panel_21 = new JPanel();
		panel_21.setLayout(null);
		panel_21.setBounds(10, 11, 404, 135);
		panel_20.add(panel_21);
		
		JLabel lblProduoDeGesso_1 = new JLabel("Produ\u00E7\u00E3o de Gesso por Tipo");
		lblProduoDeGesso_1.setBounds(10, 11, 135, 14);
		panel_21.add(lblProduoDeGesso_1);
		
		JComboBox comboBox_10 = new JComboBox();
		comboBox_10.setModel(new DefaultComboBoxModel(new String[] {"", "Gesso Lento", "Gesso Intermedi\u00E1rio", "Gesso Fundicao"}));
		comboBox_10.setBounds(10, 36, 287, 22);
		panel_21.add(comboBox_10);
		
		textField_10 = new JTextField();
		textField_10.setEnabled(false);
		textField_10.setEditable(false);
		textField_10.setColumns(10);
		textField_10.setBounds(307, 37, 89, 20);
		panel_21.add(textField_10);
		
		JLabel label_12 = new JLabel("Qtd. Total");
		label_12.setBounds(307, 11, 65, 14);
		panel_21.add(label_12);
		
		JButton button_1 = new JButton("Sair");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estoque.this.dispose();
			}
		});
		button_1.setBounds(307, 82, 89, 23);
		panel_21.add(button_1);
		
		JButton button_4 = new JButton("Ver Todos");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.faz_conexao();
					String sql = "select * from fabricacao_gesso";
					java.sql.PreparedStatement stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel  modelo = (DefaultTableModel) table_5.getModel();
					modelo.setNumRows(0);
					int soma =0;
					while(rs.next()) {
						soma+=Float.parseFloat(rs.getString("qtd"));
						//soma+=Integer.parseInt(rs.getString("qtd"));
						modelo.addRow(new Object[] {rs.getString("data"), rs.getString("mes"), rs.getString("nome"), rs.getString("tipo"), rs.getString("qtd"), rs.getString("obs")});
					}
					textField_10.setText(""+soma);
					rs.first();
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button_4.setBounds(109, 82, 89, 23);
		panel_21.add(button_4);
		
		JButton button_5 = new JButton("Consultar");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql ="select * from fabricacao_gesso where produto=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox_10.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table_5.getModel();
					modelo.setNumRows(0);
					float soma =0;
					while(set.next()) {
						soma+=Float.parseFloat(set.getString("qtd"));
						modelo.addRow(new Object[] {set.getString("data"), set.getString("mes"), set.getString("nome"), set.getString("tipo"), set.getString("qtd"), set.getString("obs")});
						
					}
					textField_10.setText(""+soma);
					set.first();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button_5.setBounds(10, 82, 89, 23);
		panel_21.add(button_5);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_10.setSelectedItem(null);
				textField_10.setText("");
				table_5.setModel(new DefaultTableModel (null, new String [] {"Dia","Mês","Funcionário","Tipo","Produção","Obs"}));
				
			}
		});
		btnLimpar.setBounds(208, 82, 89, 23);
		panel_21.add(btnLimpar);
		
		JPanel panel_22 = new JPanel();
		panel_22.setLayout(null);
		panel_22.setBounds(424, 11, 785, 135);
		panel_20.add(panel_22);
		
		JLabel lblConsultaPorTipo = new JLabel("CONSULTA POR TIPO DE GESSO");
		lblConsultaPorTipo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConsultaPorTipo.setBounds(249, 60, 287, 14);
		panel_22.add(lblConsultaPorTipo);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 157, 1199, 435);
		panel_20.add(scrollPane_5);
		
		table_5 = new JTable();
		table_5.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"Dia", "M\u00EAs", "Funcion\u00E1rio", "Tipo", "Produ\u00E7\u00E3o", "Observa\u00E7\u00F5es"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_5.setViewportView(table_5);
		
		JPanel panel_23 = new JPanel();
		panel_23.setBackground(Color.WHITE);
		tabbedPane.addTab("PLACAS", null, panel_23, null);
		panel_23.setLayout(null);
		
		JPanel panel_24 = new JPanel();
		panel_24.setLayout(null);
		panel_24.setBounds(10, 11, 404, 135);
		panel_23.add(panel_24);
		
		JLabel label_8 = new JLabel("Produ\u00E7\u00E3o de Gesso por M\u00EAs");
		label_8.setBounds(10, 11, 135, 14);
		panel_24.add(label_8);
		
		JComboBox comboBox_11 = new JComboBox();
		comboBox_11.setModel(new DefaultComboBoxModel(new String[] {"", "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		comboBox_11.setBounds(10, 36, 287, 22);
		panel_24.add(comboBox_11);
		
		textField_11 = new JTextField();
		textField_11.setEnabled(false);
		textField_11.setColumns(10);
		textField_11.setBounds(307, 37, 89, 20);
		panel_24.add(textField_11);
		
		JLabel label_13 = new JLabel("Qtd. Total");
		label_13.setBounds(307, 11, 58, 14);
		panel_24.add(label_13);
		
		JButton button_8 = new JButton("Ver Todos");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.faz_conexao();
					String sql = "select * from fabricacao_placas_blocos where tipo='Placas'";
					java.sql.PreparedStatement stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel  modelo = (DefaultTableModel) table_6.getModel();
					modelo.setNumRows(0);
					int soma =0;
					while(rs.next()) {
						soma+=Integer.parseInt(rs.getString("qtd"))-Integer.parseInt(rs.getString("quebra"));
						modelo.addRow(new Object[] {rs.getString("data"), rs.getString("mes"), rs.getString("nome"), rs.getString("qtd"), rs.getString("quebra"), rs.getString("saida"), rs.getString("qtd"), rs.getString("obs")});
					}
					textField_11.setText(""+soma);
					rs.first();
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button_8.setBounds(109, 82, 89, 23);
		panel_24.add(button_8);
		
		JButton button_12 = new JButton("Sair");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estoque.this.dispose();
			}
		});
		button_12.setBounds(307, 82, 89, 23);
		panel_24.add(button_12);
		
		JButton button_13 = new JButton("Consultar");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql ="select * from fabricacao_placas_blocos where mes=? and tipo='Placas'";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox_11.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table_6.getModel();
					modelo.setNumRows(0);
					float soma =0;
					while(set.next()) {
						//soma+=Float.parseFloat(set.getString("qtd"));
						soma+=Integer.parseInt(set.getString("qtd"))-Integer.parseInt(set.getString("quebra"));
						modelo.addRow(new Object[] {set.getString("data"), set.getString("mes"), set.getString("nome"), set.getString("qtd"), set.getString("quebra"), set.getString("saida"), set.getString("qtd"), set.getString("obs")});
						
					}
					textField_11.setText(""+soma);
					set.first();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button_13.setBounds(10, 82, 89, 23);
		panel_24.add(button_13);
		
		JButton btnLimpar_1 = new JButton("Limpar");
		btnLimpar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_11.setSelectedItem(null);
				textField_11.setText("");
				table_6.setModel(new DefaultTableModel (null, new String [] {"Dia","Mês","Funcionário","Qtd","Quebra","Saída","Estoque","Observações"}));
			}
		});
		btnLimpar_1.setBounds(208, 82, 89, 23);
		panel_24.add(btnLimpar_1);
		
		JPanel panel_26 = new JPanel();
		panel_26.setLayout(null);
		panel_26.setBounds(424, 11, 785, 135);
		panel_23.add(panel_26);
		
		JLabel lblPlacas = new JLabel("PLACAS");
		lblPlacas.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPlacas.setBounds(358, 56, 69, 22);
		panel_26.add(lblPlacas);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(10, 157, 1199, 435);
		panel_23.add(scrollPane_6);
		
		table_6 = new JTable();
		table_6.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Dia", "M\u00EAs", "Funcion\u00E1rio", "Qtd", "Quebra", "Sa\u00EDda", "Estoque", "Observa\u00E7\u00F5es"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_6.setViewportView(table_6);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.WHITE);
		tabbedPane.addTab("BLOCOS", null, panel_7, null);
		panel_7.setLayout(null);
		
		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBounds(10, 11, 404, 135);
		panel_7.add(panel_11);
		
		JLabel label_10 = new JLabel("Produ\u00E7\u00E3o de Gesso por M\u00EAs");
		label_10.setBounds(10, 11, 135, 14);
		panel_11.add(label_10);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"", "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		comboBox_6.setBounds(10, 36, 287, 22);
		panel_11.add(comboBox_6);
		
		textField_6 = new JTextField();
		textField_6.setEnabled(false);
		textField_6.setColumns(10);
		textField_6.setBounds(307, 37, 89, 20);
		panel_11.add(textField_6);
		
		JLabel label_11 = new JLabel("Qtd. Total");
		label_11.setBounds(307, 11, 58, 14);
		panel_11.add(label_11);
		
		JButton button_2 = new JButton("Ver Todos");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.faz_conexao();
					String sql = "select * from fabricacao_placas_blocos where tipo='Blocos'";
					java.sql.PreparedStatement stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();
					
					DefaultTableModel  modelo = (DefaultTableModel) table_1.getModel();
					modelo.setNumRows(0);
					int soma =0;
					while(rs.next()) {
						soma+=Integer.parseInt(rs.getString("qtd"))-Integer.parseInt(rs.getString("quebra"));
						modelo.addRow(new Object[] {rs.getString("data"), rs.getString("mes"), rs.getString("nome"), rs.getString("qtd"), rs.getString("quebra"), rs.getString("saida"), rs.getString("qtd"), rs.getString("obs")});
					}
					textField_6.setText(""+soma);
					rs.first();
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button_2.setBounds(109, 82, 89, 23);
		panel_11.add(button_2);
		
		JButton button_6 = new JButton("Sair");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estoque.this.dispose();
			}
		});
		button_6.setBounds(307, 82, 89, 23);
		panel_11.add(button_6);
		
		JButton button_9 = new JButton("Consultar");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql ="select * from fabricacao_placas_blocos where mes=? and tipo='Blocos'";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox_6.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table_1.getModel();
					modelo.setNumRows(0);
					float soma =0;
					while(set.next()) {
						//soma+=Float.parseFloat(set.getString("qtd"));
						soma+=Integer.parseInt(set.getString("qtd"))-Integer.parseInt(set.getString("quebra"));
						modelo.addRow(new Object[] {set.getString("data"), set.getString("mes"), set.getString("nome"), set.getString("qtd"), set.getString("quebra"), set.getString("saida"), set.getString("qtd"), set.getString("obs")});
						
					}
					textField_6.setText(""+soma);
					set.first();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button_9.setBounds(10, 82, 89, 23);
		panel_11.add(button_9);
		
		JButton btnLimpar_2 = new JButton("Limpar");
		btnLimpar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_6.setSelectedItem(null);
				textField_6.setText("");
				table_1.setModel(new DefaultTableModel (null, new String [] {"Dia","Mês","Funcionário","Qtd","Quebra","Saída","Estoque","Observações"}));
			}
		});
		btnLimpar_2.setBounds(208, 82, 89, 23);
		panel_11.add(btnLimpar_2);
		
		JPanel panel_12 = new JPanel();
		panel_12.setLayout(null);
		panel_12.setBounds(424, 11, 785, 135);
		panel_7.add(panel_12);
		
		JLabel lblBlocos = new JLabel("BLOCOS");
		lblBlocos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBlocos.setBounds(356, 56, 73, 22);
		panel_12.add(lblBlocos);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 157, 1199, 435);
		panel_7.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Dia", "M\u00EAs", "Funcion\u00E1rio", "Qtd", "Quebra", "Sa\u00EDda", "Estoque", "Observa\u00E7\u00F5es"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table_1);
	}
}
