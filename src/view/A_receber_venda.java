package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class A_receber_venda extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table_1;
	private JTextField textField_2;
	private JTable table_2;
	private JTextField textField_3;
	private JTable table_3;
	private JTextField textField_4;
	private JTable table_4;
	private JTextField textField_5;
	private JTable table_5;
	private JTextField textField_6;
	private JTable table_6;
	private JTextField textField_7;
	private JTable table_7;
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
					A_receber_venda frame = new A_receber_venda();
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
	public A_receber_venda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(A_receber_venda.class.getResource("/img/Money_27019.png")));
		setTitle("Vendas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Cliente", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 148, 1149, 404);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00EAs", "Data", "Raz\u00E3o Social", "Tipo de Gesso", "Tipo de Sacaria", "Lote", "Quantidade", "R$ Unit\u00E1rio", "R$ Total", "Vencimento"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 410, 126);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(10, 11, 48, 14);
		panel_1.add(lblCliente);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			Connection con = Conexao.faz_conexao();
			st = con.createStatement();
			String s = "select * from cadastro_cliente";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				comboBox.addItem(rs.getString("razao"));
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
		comboBox.setBounds(10, 36, 280, 21);
		panel_1.add(comboBox);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setEnabled(false);
		textField.setBounds(300, 36, 96, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(300, 11, 48, 14);
		panel_1.add(lblValor);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				try {
					String sql ="select * from venda where razao=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table.getModel();
					modelo.setNumRows(0);
					float soma =0;
					while(set.next()) {
						soma+=Float.parseFloat(set.getString("valor_total"));
						modelo.addRow(new Object[] {set.getString("mes_venda"),set.getString("data"),set.getString("razao"),set.getString("tipo_gesso"),set.getString("tipo_sacaria"),set.getString("lote"),set.getString("quantidade"),set.getString("valor"),set.getString("valor_total"),set.getString("data_vencimento")});
						
					}
					textField.setText(""+soma);
					set.first();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnConsultar.setBounds(10, 92, 89, 23);
		panel_1.add(btnConsultar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				comboBox.setSelectedItem(null);
				textField.setText("");
				table.setModel(new DefaultTableModel (null, new String [] {"Mês","Data","Razão Social","Tipo de Gesso","Tipo de Sacaria","Lote","Quantidade","R$ Unitário","R$ Total","Vencimento"}));
				
			}
		});
		btnLimpar.setBounds(109, 92, 89, 23);
		panel_1.add(btnLimpar);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(208, 92, 89, 23);
		panel_1.add(btnImprimir);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A_receber_venda.this.dispose();
			}
		});
		btnSair.setBounds(307, 92, 89, 23);
		panel_1.add(btnSair);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(430, 11, 729, 126);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblVendaPorCliente = new JLabel("VENDA POR CLIENTE");
		lblVendaPorCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVendaPorCliente.setBounds(269, 48, 190, 29);
		panel_2.add(lblVendaPorCliente);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		tabbedPane.addTab("Venda Gesso no M\u00EAs", null, panel_3, null);
		panel_3.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(10, 11, 410, 126);
		panel_3.add(panel_4);
		
		JLabel lblMs = new JLabel("M\u00EAs");
		lblMs.setBounds(10, 11, 48, 14);
		panel_4.add(lblMs);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		comboBox_1.setBounds(10, 36, 280, 21);
		panel_4.add(comboBox_1);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(300, 36, 96, 20);
		panel_4.add(textField_1);
		
		JLabel label_1 = new JLabel("Valor");
		label_1.setBounds(300, 11, 48, 14);
		panel_4.add(label_1);
		
		JButton button = new JButton("Consultar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textField.setText("");
				try {
					String sql ="select * from venda where mes_venda=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox_1.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table_1.getModel();
					modelo.setNumRows(0);
					float soma =0;
					while(set.next()) {
						soma+=Float.parseFloat(set.getString("valor_total"));
						modelo.addRow(new Object[] {set.getString("mes_venda"),set.getString("data"),set.getString("razao"),set.getString("tipo_gesso"),set.getString("tipo_sacaria"),set.getString("lote"),set.getString("quantidade"),set.getString("valor"),set.getString("valor_total"),set.getString("data_vencimento")});
						
					}
					textField_1.setText(""+soma);
					set.first();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		button.setBounds(10, 92, 89, 23);
		panel_4.add(button);
		
		JButton button_1 = new JButton("Limpar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_1.setSelectedItem(null);
				textField_1.setText("");
				table_1.setModel(new DefaultTableModel (null, new String [] {"Mês","Data","Razão Social","Tipo de Gesso","Tipo de Sacaria","Lote","Quantidade","R$ Unitário","R$ Total","Vencimento"}));
				
			}
		});
		button_1.setBounds(109, 92, 89, 23);
		panel_4.add(button_1);
		
		JButton button_2 = new JButton("Imprimir");
		button_2.setBounds(208, 92, 89, 23);
		panel_4.add(button_2);
		
		JButton button_3 = new JButton("Sair");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A_receber_venda.this.dispose();
			}
		});
		button_3.setBounds(307, 92, 89, 23);
		panel_4.add(button_3);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBounds(430, 11, 729, 126);
		panel_3.add(panel_5);
		
		JLabel lblVendaPorMs = new JLabel("VENDA POR M\u00CAS");
		lblVendaPorMs.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVendaPorMs.setBounds(289, 48, 150, 29);
		panel_5.add(lblVendaPorMs);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 148, 1149, 404);
		panel_3.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00EAs", "Data", "Raz\u00E3o Social", "Tipo de Gesso", "Tipo de Sacaria", "Lote", "Quantidade", "R$ Unit\u00E1rio", "R$ Total", "Vencimento"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		tabbedPane.addTab("Tipo de Gesso", null, panel_6, null);
		panel_6.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBounds(10, 11, 410, 126);
		panel_6.add(panel_7);
		
		JLabel lblTipoDeGesso = new JLabel("Tipo de Gesso");
		lblTipoDeGesso.setBounds(10, 11, 68, 14);
		panel_7.add(lblTipoDeGesso);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"", "Gesso Lento", "Gesso Intermedi\u00E1rio", "Gesso Fundicao"}));
		comboBox_2.setBounds(10, 36, 280, 21);
		panel_7.add(comboBox_2);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBounds(300, 36, 96, 20);
		panel_7.add(textField_2);
		
		JLabel label_3 = new JLabel("Valor");
		label_3.setBounds(300, 11, 48, 14);
		panel_7.add(label_3);
		
		JButton button_4 = new JButton("Consultar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_2.setText("");
				try {
					String sql ="select * from venda where tipo_gesso=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox_2.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table_2.getModel();
					modelo.setNumRows(0);
					float soma =0;
					while(set.next()) {
						soma+=Float.parseFloat(set.getString("valor_total"));
						modelo.addRow(new Object[] {set.getString("tipo_gesso"),set.getString("mes_venda"),set.getString("data"),set.getString("razao"),set.getString("tipo_sacaria"),set.getString("lote"),set.getString("quantidade"),set.getString("valor"),set.getString("valor_total"),set.getString("data_vencimento")});
						
					}
					textField_2.setText(""+soma);
					set.first();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button_4.setBounds(10, 92, 89, 23);
		panel_7.add(button_4);
		
		JButton button_5 = new JButton("Limpar");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_2.setSelectedItem(null);
				textField_2.setText("");
				table_2.setModel(new DefaultTableModel (null, new String [] {"Tipo de Gesso","Mês","Data","Razão Social","Tipo de Sacaria","Lote","Quantidade","R$ Unitário","R$ Total","Vencimento"}));
				
			}
		});
		button_5.setBounds(109, 92, 89, 23);
		panel_7.add(button_5);
		
		JButton button_6 = new JButton("Imprimir");
		button_6.setBounds(208, 92, 89, 23);
		panel_7.add(button_6);
		
		JButton button_7 = new JButton("Sair");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A_receber_venda.this.dispose();
			}
		});
		button_7.setBounds(307, 92, 89, 23);
		panel_7.add(button_7);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBounds(430, 11, 729, 126);
		panel_6.add(panel_8);
		
		JLabel lblVendaPorTipo = new JLabel("VENDA POR TIPO DE GESSO");
		lblVendaPorTipo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVendaPorTipo.setBounds(238, 48, 253, 29);
		panel_8.add(lblVendaPorTipo);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 148, 1149, 404);
		panel_6.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Tipo de Gesso", "M\u00EAs", "Data", "Raz\u00E3o Social", "Tipo de Sacaria", "Lote", "Quantidade", "R$ Unit\u00E1rio", "R$ Total", "Vencimento"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_2.setViewportView(table_2);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(Color.WHITE);
		tabbedPane.addTab("Venda por Tipo de Sacaria", null, panel_9, null);
		panel_9.setLayout(null);
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBounds(10, 11, 410, 126);
		panel_9.add(panel_10);
		
		JLabel lblTipoDeSacaria = new JLabel("Tipo de Sacaria");
		lblTipoDeSacaria.setBounds(10, 11, 75, 14);
		panel_10.add(lblTipoDeSacaria);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_tipo_sacaria";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				comboBox_3.addItem(rs.getString("tipo"));
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
		
		comboBox_3.setBounds(10, 36, 280, 21);
		panel_10.add(comboBox_3);
		
		textField_3 = new JTextField();
		textField_3.setEnabled(false);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(300, 36, 96, 20);
		panel_10.add(textField_3);
		
		JLabel label_5 = new JLabel("Valor");
		label_5.setBounds(300, 11, 48, 14);
		panel_10.add(label_5);
		
		JButton button_8 = new JButton("Consultar");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_3.setText("");
				try {
					String sql ="select * from venda where tipo_sacaria=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox_3.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table_3.getModel();
					modelo.setNumRows(0);
					float soma =0;
					while(set.next()) {
						soma+=Float.parseFloat(set.getString("valor_total"));
						modelo.addRow(new Object[] {set.getString("tipo_sacaria"),set.getString("mes_venda"),set.getString("data"),set.getString("razao"),set.getString("tipo_gesso"),set.getString("lote"),set.getString("quantidade"),set.getString("valor"),set.getString("valor_total"),set.getString("data_vencimento")});
						
					}
					textField_3.setText(""+soma);
					set.first();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button_8.setBounds(10, 92, 89, 23);
		panel_10.add(button_8);
		
		JButton button_9 = new JButton("Limpar");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_3.setSelectedItem(null);
				textField_3.setText("");
				table_3.setModel(new DefaultTableModel (null, new String [] {"Tipo de Sacaria","Mês","Data","Razão Social","Tipo de Gesso","Lote","Quantidade","R$ Unitário","R$ Total","Vencimento"}));
				
			}
		});
		button_9.setBounds(109, 92, 89, 23);
		panel_10.add(button_9);
		
		JButton button_10 = new JButton("Imprimir");
		button_10.setBounds(208, 92, 89, 23);
		panel_10.add(button_10);
		
		JButton button_11 = new JButton("Sair");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A_receber_venda.this.dispose();
			}
		});
		button_11.setBounds(307, 92, 89, 23);
		panel_10.add(button_11);
		
		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBounds(430, 11, 729, 126);
		panel_9.add(panel_11);
		
		JLabel lblVendaPorTipo_1 = new JLabel("VENDA POR TIPO DE SACARIA");
		lblVendaPorTipo_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVendaPorTipo_1.setBounds(227, 48, 274, 29);
		panel_11.add(lblVendaPorTipo_1);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 148, 1149, 404);
		panel_9.add(scrollPane_3);
		
		table_3 = new JTable();
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Tipo de Sacaria", "M\u00EAs", "Data", "Raz\u00E3o Social", "Tipo de Gesso", "Lote", "Quantidade", "R$ Unit\u00E1rio", "R$ Total", "Vencimento"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_3.setViewportView(table_3);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.WHITE);
		tabbedPane.addTab("Gesso Recebimento", null, panel_12, null);
		panel_12.setLayout(null);
		
		JPanel panel_13 = new JPanel();
		panel_13.setLayout(null);
		panel_13.setBounds(10, 11, 410, 126);
		panel_12.add(panel_13);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(10, 11, 48, 14);
		panel_13.add(lblData);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from venda";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				comboBox_4.addItem(rs.getString("data"));
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
		comboBox_4.setBounds(10, 36, 280, 21);
		panel_13.add(comboBox_4);
		
		textField_4 = new JTextField();
		textField_4.setEnabled(false);
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(300, 36, 96, 20);
		panel_13.add(textField_4);
		
		JLabel label_7 = new JLabel("Valor");
		label_7.setBounds(300, 11, 48, 14);
		panel_13.add(label_7);
		
		JButton button_12 = new JButton("Consultar");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_4.setText("");
				try {
					String sql ="select * from venda where data=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox_4.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table_4.getModel();
					modelo.setNumRows(0);
					float soma =0;
					while(set.next()) {
						soma+=Float.parseFloat(set.getString("valor_total"));
						modelo.addRow(new Object[] {set.getString("data_vencimento"),set.getString("tipo_gesso"),set.getString("tipo_sacaria"),set.getString("razao"),set.getString("quantidade"),set.getString("valor_total"),set.getString("data")});
						
					}
					textField_4.setText(""+soma);
					set.first();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button_12.setBounds(10, 92, 89, 23);
		panel_13.add(button_12);
		
		JButton button_13 = new JButton("Limpar");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_4.setSelectedItem(null);
				textField_4.setText("");
				table_4.setModel(new DefaultTableModel (null, new String [] {"Data Vencimento","Tipo de Gesso","Tipo de Sacaria","Razão Social","Quantidade","R$ Total","Data"}));
				
			}
		});
		button_13.setBounds(109, 92, 89, 23);
		panel_13.add(button_13);
		
		JButton button_14 = new JButton("Imprimir");
		button_14.setBounds(208, 92, 89, 23);
		panel_13.add(button_14);
		
		JButton button_15 = new JButton("Sair");
		button_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A_receber_venda.this.dispose();
			}
		});
		button_15.setBounds(307, 92, 89, 23);
		panel_13.add(button_15);
		
		JPanel panel_14 = new JPanel();
		panel_14.setLayout(null);
		panel_14.setBounds(430, 11, 729, 126);
		panel_12.add(panel_14);
		
		JLabel lblRecebimetoGessoNo = new JLabel("RECEBIMETOS GESSO NO M\u00CAS");
		lblRecebimetoGessoNo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRecebimetoGessoNo.setBounds(227, 48, 274, 29);
		panel_14.add(lblRecebimetoGessoNo);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 148, 1149, 404);
		panel_12.add(scrollPane_4);
		
		table_4 = new JTable();
		table_4.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Data Vencimento", "Tipo de Gesso", "Tipo de Sacaria", "Raz\u00E3o Social", "Quantidade", "R$ Total", "Data"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_4.setViewportView(table_4);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(Color.WHITE);
		tabbedPane.addTab("Placa / Bloco", null, panel_15, null);
		panel_15.setLayout(null);
		
		JPanel panel_16 = new JPanel();
		panel_16.setLayout(null);
		panel_16.setBounds(10, 11, 410, 126);
		panel_15.add(panel_16);
		
		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setBounds(10, 11, 48, 14);
		panel_16.add(lblTipo);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"", "Placas", "Blocos"}));
		comboBox_5.setBounds(10, 36, 280, 21);
		panel_16.add(comboBox_5);
		
		textField_5 = new JTextField();
		textField_5.setEnabled(false);
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBounds(300, 36, 96, 20);
		panel_16.add(textField_5);
		
		JLabel label_9 = new JLabel("Valor");
		label_9.setBounds(300, 11, 48, 14);
		panel_16.add(label_9);
		
		JButton button_16 = new JButton("Consultar");
		button_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_5.setText("");
				try {
					String sql ="select * from venda2 where tipo_gesso=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox_5.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table_5.getModel();
					modelo.setNumRows(0);
					float soma =0;
					while(set.next()) {
						soma+=Float.parseFloat(set.getString("valor_total"));
						modelo.addRow(new Object[] {set.getString("tipo_gesso"),set.getString("mes_venda"),set.getString("data"),set.getString("razao"),set.getString("quantidade"),set.getString("valor"),set.getString("valor_total"),set.getString("data_vencimento")});
						
					}
					textField_5.setText(""+soma);
					set.first();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button_16.setBounds(10, 92, 89, 23);
		panel_16.add(button_16);
		
		JButton button_17 = new JButton("Limpar");
		button_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_5.setSelectedItem(null);
				textField_5.setText("");
				table_5.setModel(new DefaultTableModel (null, new String [] {"Tipo","Mês","Data","Razão Social","Quantidade","R$ Unitário","R$ Total","Vencimento"}));
				
			}
		});
		button_17.setBounds(109, 92, 89, 23);
		panel_16.add(button_17);
		
		JButton button_18 = new JButton("Imprimir");
		button_18.setBounds(208, 92, 89, 23);
		panel_16.add(button_18);
		
		JButton button_19 = new JButton("Sair");
		button_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A_receber_venda.this.dispose();
			}
		});
		button_19.setBounds(307, 92, 89, 23);
		panel_16.add(button_19);
		
		JPanel panel_17 = new JPanel();
		panel_17.setLayout(null);
		panel_17.setBounds(430, 11, 729, 126);
		panel_15.add(panel_17);
		
		JLabel lblVendaBlocoE = new JLabel("VENDA BLOCO E PLACASTIPO DE GESSO");
		lblVendaBlocoE.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVendaBlocoE.setBounds(181, 48, 366, 29);
		panel_17.add(lblVendaBlocoE);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(10, 148, 1149, 404);
		panel_15.add(scrollPane_5);
		
		table_5 = new JTable();
		table_5.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Tipo", "M\u00EAs", "Data", "Raz\u00E3o Social", "Quantidade", "R$ Unit\u00E1rio", "R$ Total", "Vencimento"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_5.setViewportView(table_5);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBackground(Color.WHITE);
		tabbedPane.addTab("Venda Placa e Bloco no M\u00EAs", null, panel_18, null);
		panel_18.setLayout(null);
		
		JPanel panel_19 = new JPanel();
		panel_19.setLayout(null);
		panel_19.setBounds(10, 11, 410, 126);
		panel_18.add(panel_19);
		
		JLabel lblMs_1 = new JLabel("M\u00EAs");
		lblMs_1.setBounds(10, 11, 48, 14);
		panel_19.add(lblMs_1);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"", "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		comboBox_6.setBounds(10, 36, 280, 21);
		panel_19.add(comboBox_6);
		
		textField_6 = new JTextField();
		textField_6.setEnabled(false);
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		textField_6.setBounds(300, 36, 96, 20);
		panel_19.add(textField_6);
		
		JLabel label_11 = new JLabel("Valor");
		label_11.setBounds(300, 11, 48, 14);
		panel_19.add(label_11);
		
		JButton button_20 = new JButton("Consultar");
		button_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_6.setText("");
				try {
					String sql ="select * from venda2 where mes_venda=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox_6.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table_6.getModel();
					modelo.setNumRows(0);
					float soma =0;
					while(set.next()) {
						soma+=Float.parseFloat(set.getString("valor_total"));
						modelo.addRow(new Object[] {set.getString("tipo_gesso"),set.getString("mes_venda"),set.getString("data"),set.getString("razao"),set.getString("quantidade"),set.getString("valor"),set.getString("valor_total"),set.getString("data_vencimento")});
						
					}
					textField_6.setText(""+soma);
					set.first();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button_20.setBounds(10, 92, 89, 23);
		panel_19.add(button_20);
		
		JButton button_21 = new JButton("Limpar");
		button_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_6.setSelectedItem(null);
				textField_6.setText("");
				table_6.setModel(new DefaultTableModel (null, new String [] {"Mês","Tipo","Data","Razão Social","Quantidade","R$ Unitário","R$ Total","Vencimento"}));
				
			}
		});
		button_21.setBounds(109, 92, 89, 23);
		panel_19.add(button_21);
		
		JButton button_22 = new JButton("Imprimir");
		button_22.setBounds(208, 92, 89, 23);
		panel_19.add(button_22);
		
		JButton button_23 = new JButton("Sair");
		button_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A_receber_venda.this.dispose();
			}
		});
		button_23.setBounds(307, 92, 89, 23);
		panel_19.add(button_23);
		
		JPanel panel_20 = new JPanel();
		panel_20.setLayout(null);
		panel_20.setBounds(430, 11, 729, 126);
		panel_18.add(panel_20);
		
		JLabel lblVendaBlocoE_1 = new JLabel("VENDA BLOCO E PLACAS NO M\u00CAS");
		lblVendaBlocoE_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblVendaBlocoE_1.setBounds(216, 48, 296, 29);
		panel_20.add(lblVendaBlocoE_1);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(10, 148, 1149, 404);
		panel_18.add(scrollPane_6);
		
		table_6 = new JTable();
		table_6.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00EAs", "Tipo", "Data", "Raz\u00E3o Social", "Quantidade", "R$ Unit\u00E1rio", "R$ Total", "Vencimento"
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
		
		JPanel panel_21 = new JPanel();
		panel_21.setBackground(Color.WHITE);
		tabbedPane.addTab("Placa e Bloco Recebimentos", null, panel_21, null);
		panel_21.setLayout(null);
		
		JPanel panel_22 = new JPanel();
		panel_22.setLayout(null);
		panel_22.setBounds(10, 11, 410, 126);
		panel_21.add(panel_22);
		
		JLabel label = new JLabel("M\u00EAs");
		label.setBounds(10, 11, 48, 14);
		panel_22.add(label);
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setBounds(10, 36, 280, 21);
		panel_22.add(comboBox_7);
		
		textField_7 = new JTextField();
		textField_7.setEnabled(false);
		textField_7.setEditable(false);
		textField_7.setColumns(10);
		textField_7.setBounds(300, 36, 96, 20);
		panel_22.add(textField_7);
		
		JLabel label_2 = new JLabel("Valor");
		label_2.setBounds(300, 11, 48, 14);
		panel_22.add(label_2);
		
		JButton button_24 = new JButton("Consultar");
		button_24.setBounds(10, 92, 89, 23);
		panel_22.add(button_24);
		
		JButton button_25 = new JButton("Limpar");
		button_25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_7.setSelectedItem(null);
				textField_7.setText("");
				table_7.setModel(new DefaultTableModel (null, new String [] {"Mês do Vencimento","Data do Vencimento","Tipo","Razão Social","Quantidade","R$ Unitário","R$ Total","Data da Venda"}));
				
			}
		});
		button_25.setBounds(109, 92, 89, 23);
		panel_22.add(button_25);
		
		JButton button_26 = new JButton("Imprimir");
		button_26.setBounds(208, 92, 89, 23);
		panel_22.add(button_26);
		
		JButton button_27 = new JButton("Sair");
		button_27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				A_receber_venda.this.dispose();
			}
		});
		button_27.setBounds(307, 92, 89, 23);
		panel_22.add(button_27);
		
		JPanel panel_23 = new JPanel();
		panel_23.setLayout(null);
		panel_23.setBounds(430, 11, 729, 126);
		panel_21.add(panel_23);
		
		JLabel lblRecebimentoBlocoE = new JLabel("RECEBIMENTO BLOCO E PLACAS NO M\u00CAS");
		lblRecebimentoBlocoE.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRecebimentoBlocoE.setBounds(181, 48, 367, 29);
		panel_23.add(lblRecebimentoBlocoE);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(10, 148, 1149, 404);
		panel_21.add(scrollPane_7);
		
		table_7 = new JTable();
		table_7.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00EAs do Vencimento", "Data do Vencimento", "Tipo", "Raz\u00E3o Social", "Quantidade", "R$ Unit\u00E1rio", "R$ Total", "Data da venda"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane_7.setViewportView(table_7);
	}
}
