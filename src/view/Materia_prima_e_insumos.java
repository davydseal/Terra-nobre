package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
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
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;

public class Materia_prima_e_insumos extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	Connection con;
	Statement st;
	ResultSet rs;
	private JTable table_2;
	private JTextField textField_1;

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
					Materia_prima_e_insumos frame = new Materia_prima_e_insumos();
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
	public Materia_prima_e_insumos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Materia_prima_e_insumos.class.getResource("/img/Box_1_35524.png")));
		setTitle("Mat\u00E9ria Prima e Insumos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1192, 680);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Insumos", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(10, 11, 411, 135);
		panel.add(panel_3);
		
		JLabel lblInsumos = new JLabel("Insumos");
		lblInsumos.setBounds(10, 11, 135, 14);
		panel_3.add(lblInsumos);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		comboBox_1.setBounds(10, 36, 287, 22);
		panel_3.add(comboBox_1);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(307, 37, 89, 20);
		panel_3.add(textField);
		
		JLabel label_1 = new JLabel("Qtd. Total");
		label_1.setBounds(307, 11, 58, 14);
		panel_3.add(label_1);
		
		JButton button = new JButton("Imprimir");
		button.setBounds(208, 79, 89, 23);
		panel_3.add(button);
		
		JButton button_1 = new JButton("Sair");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Materia_prima_e_insumos.this.dispose();
			}
		});
		button_1.setBounds(307, 79, 89, 23);
		panel_3.add(button_1);
		
		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				try {
					String sql ="select * from entrada_insumos where mes=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox_1.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table.getModel();
					modelo.setNumRows(0);
					float soma =0;
					while(set.next()) {
						soma+=Float.parseFloat(set.getString("qtd"));

						modelo.addRow(new Object[] {set.getString("data"), set.getString("fornecedor"), set.getString("forma"), set.getString("insumo"), set.getString("mes"), set.getString("qtd"), set.getString("valor"), set.getString("total"), set.getString("data_vencimento"), set.getString("obs")});
					}
					textField.setText(""+soma);
					set.first();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		btnNewButton.setBounds(10, 79, 89, 23);
		panel_3.add(btnNewButton);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox_1.setSelectedItem(null);
				textField.setText("");
				table.setModel(new DefaultTableModel (null, new String [] {"Data","Fornecedor","Forma de Pagamento","Insumo","Mês de Vencimento","Quantidade","R$ Unitário","R$ Total","Data de Vencimento","Obs"}));
			}
		});
		btnLimpar.setBounds(109, 79, 89, 23);
		panel_3.add(btnLimpar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 157, 1140, 435);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Data", "Fornecedor", "Forma de Pagamento", "Insumo", "M\u00EAs de Vencimento", "Quantidade", "R$ Unit\u00E1rio", "R$ Total", "Data de Vencimento", "Obs"
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
		tabbedPane.addTab("Mat\u00E9ria Prima", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 157, 1141, 435);
		panel_1.add(scrollPane_1);
		
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Data", "Fornecedor", "Forma de Pagamento", "Mat\u00E9ria Prima", "M\u00EAs de Vencimento", "Quantidade", "R$ Uni\u00E1rio", "R$ Total", "Data de Vencimento", "Obs"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, true, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_2.getColumnModel().getColumn(2).setPreferredWidth(122);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(85);
		table_2.getColumnModel().getColumn(4).setPreferredWidth(113);
		table_2.getColumnModel().getColumn(8).setPreferredWidth(115);
		scrollPane_1.setViewportView(table_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(10, 11, 411, 135);
		panel_1.add(panel_2);
		
		JLabel lblMatriaPrima = new JLabel("Mat\u00E9ria Prima");
		lblMatriaPrima.setBounds(10, 11, 135, 14);
		panel_2.add(lblMatriaPrima);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		comboBox.setBounds(10, 36, 287, 22);
		panel_2.add(comboBox);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(307, 37, 89, 20);
		panel_2.add(textField_1);
		
		JLabel label_2 = new JLabel("Qtd. Total");
		label_2.setBounds(307, 11, 58, 14);
		panel_2.add(label_2);
		
		JButton button_2 = new JButton("Imprimir");
		button_2.setBounds(208, 79, 89, 23);
		panel_2.add(button_2);
		
		JButton button_3 = new JButton("Sair");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Materia_prima_e_insumos.this.dispose();
			}
		});
		button_3.setBounds(307, 79, 89, 23);
		panel_2.add(button_3);
		
		JButton button_4 = new JButton("Consultar");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				try {
					String sql ="select * from entrada_materia_prima where mes=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table_2.getModel();
					modelo.setNumRows(0);
					float soma =0;
					while(set.next()) {
						soma+=Float.parseFloat(set.getString("qtd"));

						modelo.addRow(new Object[] {set.getString("data"), set.getString("fornecedor"), set.getString("forma"), set.getString("materia"), set.getString("mes"), set.getString("qtd"), set.getString("valor"), set.getString("total"), set.getString("data_vencimento"), set.getString("obs")});
					}
					textField_1.setText(""+soma);
					set.first();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button_4.setBounds(10, 79, 89, 23);
		panel_2.add(button_4);
		
		JButton button_5 = new JButton("Limpar");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setSelectedItem(null);
				textField_1.setText("");
				table_2.setModel(new DefaultTableModel (null, new String [] {"Data","Fornecedor","Forma de Pagamento","Matéria Prima","Mês de Vencimento","Quantidade","R$ Unitário","R$ Total","Data de Vencimento","Obs"}));
		
			}
		});
		button_5.setBounds(109, 79, 89, 23);
		panel_2.add(button_5);
	}
}
