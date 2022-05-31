package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
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
import java.awt.Toolkit;
import javax.swing.DefaultComboBoxModel;

public class Status_receber_placa_bloco extends JFrame {

	private JPanel contentPane;
	private JTable table;
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
					Status_receber_placa_bloco frame = new Status_receber_placa_bloco();
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
	public Status_receber_placa_bloco() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Status_receber_placa_bloco.class.getResource("/img/document_write_22637.png")));
		setTitle("Status receber gesso");
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
		tabbedPane.addTab("Status", null, panel, null);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBounds(10, 11, 410, 126);
		panel.add(panel_3);
		
		JLabel lblForncedor = new JLabel("Status");
		lblForncedor.setBounds(10, 11, 55, 14);
		panel_3.add(lblForncedor);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Placas", "Blocos"}));
		comboBox.setBounds(10, 36, 386, 21);
		panel_3.add(comboBox);
		
		JButton button = new JButton("Consultar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String sql ="select * from venda2 where tipo_gesso=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table.getModel();
					modelo.setNumRows(0);
					
					while(set.next()) {
						
						modelo.addRow(new Object[] {set.getString("status2"),set.getString("data_vencimento"),set.getString("tipo_gesso"),set.getString("razao"),set.getString("quantidade"),set.getString("valor"),set.getString("valor_total"),set.getString("nota_fiscal")});
						
					}
					
					
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
				table.setModel(new DefaultTableModel (null, new String [] {"Status","Data Vencimento","Tipo de Gesso","Razão Social","Quantidade","R$ Unitário","R$ Total","Nº Nota Fiscal"}));
		
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
				Status_receber_placa_bloco.this.dispose();
			}
		});
		button_3.setBounds(307, 80, 89, 23);
		panel_3.add(button_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(430, 11, 729, 126);
		panel.add(panel_4);
		
		JLabel lblPagamentoPorFornecedor = new JLabel("STATUS RECEBER PLACAS E BLOCOS");
		lblPagamentoPorFornecedor.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPagamentoPorFornecedor.setBounds(202, 48, 325, 29);
		panel_4.add(lblPagamentoPorFornecedor);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 148, 1149, 404);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Status", "Data Vencimento", "Tipo de Gesso", "Raz\u00E3o Social", "Quantidade", "R$ Unit\u00E1rio", "R$ Total", "N\u00BA Nota Fiscal"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
	}
}
