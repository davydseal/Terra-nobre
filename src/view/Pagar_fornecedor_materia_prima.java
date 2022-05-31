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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class Pagar_fornecedor_materia_prima extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
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
					Pagar_fornecedor_materia_prima frame = new Pagar_fornecedor_materia_prima();
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
	public Pagar_fornecedor_materia_prima() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Pagar_fornecedor_materia_prima.class.getResource("/img/bar_code_22177.png")));
		setTitle("Pagamento Fornecedor Mat\u00E9ria Prima");
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
		tabbedPane.addTab("Fornecedor", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(10, 11, 410, 126);
		panel.add(panel_3);
		
		JLabel lblForncedor = new JLabel("Forncedor");
		lblForncedor.setBounds(10, 11, 55, 14);
		panel_3.add(lblForncedor);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_fornecedor_materia_prima";
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
		comboBox.setBounds(10, 36, 386, 21);
		panel_3.add(comboBox);
		
		JButton button = new JButton("Consultar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String sql ="select * from entrada_materia_prima where fornecedor=? and status2='EM ABERTO'";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table.getModel();
					modelo.setNumRows(0);
			
					while(set.next()) {
						

						modelo.addRow(new Object[] {set.getString("fornecedor"), set.getString("mes"), set.getString("materia"), set.getString("qtd"), set.getString("valor"), set.getString("total"), set.getString("data_vencimento"), set.getString("status2"), set.getString("data"), set.getString("nf")});
					}
				
					set.first();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			
			}
		});
		button.setBounds(10, 80, 89, 23);
		panel_3.add(button);
		
		JButton button_1 = new JButton("Limpar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setSelectedItem(null);
				table.setModel(new DefaultTableModel (null, new String [] {"Fornecedor","Mês Vencimento","Matéria Prima","Quantidade","R$ Unitário","R$ Total","Data Vencimento","Status","Data do Pagamento","Nº Nota Fiscal"}));
		
			}
		});
		button_1.setBounds(109, 80, 89, 23);
		panel_3.add(button_1);
		
		JButton button_2 = new JButton("Imprimir");
		button_2.setBounds(208, 80, 89, 23);
		panel_3.add(button_2);
		
		JButton button_3 = new JButton("Sair");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pagar_fornecedor_materia_prima.this.dispose();
			}
		});
		button_3.setBounds(307, 80, 89, 23);
		panel_3.add(button_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(430, 11, 729, 126);
		panel.add(panel_4);
		
		JLabel lblPagamentoPorFornecedor = new JLabel("PAGAMENTO POR FORNECEDOR");
		lblPagamentoPorFornecedor.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPagamentoPorFornecedor.setBounds(219, 48, 290, 29);
		panel_4.add(lblPagamentoPorFornecedor);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 148, 1149, 404);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Fornecedor", "M\u00EAs Vencimento", "Mat\u00E9ria Prima", "Quantidade", "R$ Unit\u00E1rio", "R$ Total", "Data Vencimento", "Status", "Data do pagamento", "N\u00BA Nota Fiscal"
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
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Pagamento no M\u00EAs", null, panel_1, null);
		panel_1.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBounds(10, 11, 410, 126);
		panel_1.add(panel_5);
		
		JLabel lblMs = new JLabel("M\u00EAs");
		lblMs.setBounds(10, 11, 55, 14);
		panel_5.add(lblMs);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		comboBox_1.setBounds(10, 36, 386, 21);
		panel_5.add(comboBox_1);
		
		JButton button_4 = new JButton("Consultar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String sql ="select * from entrada_materia_prima where mes=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox_1.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table_1.getModel();
					modelo.setNumRows(0);
			
					while(set.next()) {
						

						modelo.addRow(new Object[] {set.getString("fornecedor"), set.getString("mes"), set.getString("materia"), set.getString("qtd"), set.getString("valor"), set.getString("total"), set.getString("data_vencimento"), set.getString("status"), set.getString("data"), set.getString("nf")});
					}
				
					set.first();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button_4.setBounds(10, 80, 89, 23);
		panel_5.add(button_4);
		
		JButton button_5 = new JButton("Limpar");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_1.setSelectedItem(null);
				table_1.setModel(new DefaultTableModel (null, new String [] {"Mês Vencimento","Data Vencimento","Fornecedor","Matéria Prima","Quantidade","R$ Unitário","R$ Total","Status","Data do Pagamento","Nº Nota Fiscal"}));
		
			}
		});
		button_5.setBounds(109, 80, 89, 23);
		panel_5.add(button_5);
		
		JButton button_6 = new JButton("Imprimir");
		button_6.setBounds(208, 80, 89, 23);
		panel_5.add(button_6);
		
		JButton button_7 = new JButton("Sair");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pagar_fornecedor_materia_prima.this.dispose();
			}
		});
		button_7.setBounds(307, 80, 89, 23);
		panel_5.add(button_7);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBounds(430, 11, 729, 126);
		panel_1.add(panel_6);
		
		JLabel lblPagamentoNoMs = new JLabel("PAGAMENTO NO M\u00CAS");
		lblPagamentoNoMs.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPagamentoNoMs.setBounds(269, 48, 191, 29);
		panel_6.add(lblPagamentoNoMs);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 148, 1149, 404);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00EAs Vencimento", "Data Vencimento", "Fornecedor", "Mat\u00E9ria Prima", "Quantidade", "R$ Unit\u00E1rio", "R$ Total", "Status", "Data do Pagamento", "N\u00BA Nota Fiscal"
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Mat\u00E9ria Prima", null, panel_2, null);
		panel_2.setLayout(null);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBounds(10, 11, 410, 126);
		panel_2.add(panel_7);
		
		JLabel lblMatriaPrima = new JLabel("Mat\u00E9ria Prima");
		lblMatriaPrima.setBounds(10, 11, 73, 14);
		panel_7.add(lblMatriaPrima);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from entrada_materia_prima";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				comboBox_2.addItem(rs.getString("materia"));
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
		comboBox_2.setBounds(10, 36, 386, 21);
		panel_7.add(comboBox_2);
		
		JButton button_8 = new JButton("Consultar");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String sql ="select * from entrada_materia_prima where materia=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox_2.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table_2.getModel();
					modelo.setNumRows(0);
			
					while(set.next()) {
						

						modelo.addRow(new Object[] {set.getString("fornecedor"), set.getString("mes"), set.getString("materia"), set.getString("qtd"), set.getString("valor"), set.getString("total"), set.getString("data_vencimento"), set.getString("status"), set.getString("data"), set.getString("nf")});
					}
				
					set.first();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button_8.setBounds(10, 80, 89, 23);
		panel_7.add(button_8);
		
		JButton button_9 = new JButton("Limpar");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_2.setSelectedItem(null);
				table_2.setModel(new DefaultTableModel (null, new String [] {"Matéria Prima","Data Vencimento","Fornecedor","Mês Vencimento","Quantidade","R$ Unitário","R$ Total","Status","Data do Pagamento","Nº Nota Fiscal"}));
		
			}
		});
		button_9.setBounds(109, 80, 89, 23);
		panel_7.add(button_9);
		
		JButton button_10 = new JButton("Imprimir");
		button_10.setBounds(208, 80, 89, 23);
		panel_7.add(button_10);
		
		JButton button_11 = new JButton("Sair");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pagar_fornecedor_materia_prima.this.dispose();
			}
		});
		button_11.setBounds(307, 80, 89, 23);
		panel_7.add(button_11);
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBounds(430, 11, 729, 126);
		panel_2.add(panel_8);
		
		JLabel lblPagamentoPorMatria = new JLabel("PAGAMENTO POR MAT\u00C9RIA PRIMA");
		lblPagamentoPorMatria.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPagamentoPorMatria.setBounds(207, 48, 315, 29);
		panel_8.add(lblPagamentoPorMatria);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 148, 1149, 404);
		panel_2.add(scrollPane_2);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Mat\u00E9ria Prima", "Data Vencimento", "Fornecedor", "M\u00EAs Vencimento", "Quantidade", "R$ Unit\u00E1rio", "R$ Total", "Status", "Data do Pagamento", "N\u00BA Nota Fiscal"
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
	}
}
