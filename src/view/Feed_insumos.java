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
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Feed_insumos extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField data;
	private JTextField nf;
	private JTextField valor_total;
	private JTextField data_v;
	private JTextField data_p;
	private JTextField valor_p;
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
					Feed_insumos frame = new Feed_insumos();
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
	public Feed_insumos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Feed_insumos.class.getResource("/img/trade_report_reports_documents_2351.png")));
		setTitle("Feedback Pagamento de Mat\u00E9ria Prima");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 414, 358);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 378, 295);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 11, 48, 14);
		panel.add(lblId);
		
		id = new JTextField();
		id.setEnabled(false);
		id.setBounds(10, 36, 65, 20);
		panel.add(id);
		id.setColumns(10);
		
		data = new JTextField();
		data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		data.setEnabled(false);
		data.setBounds(85, 36, 96, 20);
		panel.add(data);
		data.setColumns(10);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(85, 11, 48, 14);
		panel.add(lblData);
		
		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setBounds(10, 67, 65, 14);
		panel.add(lblFornecedor);
	
		
		nf = new JTextField();
		nf.setEnabled(false);
		nf.setBounds(246, 92, 120, 20);
		panel.add(nf);
		nf.setColumns(10);
		
		JLabel lblNotaFiscal = new JLabel("Nota Fiscal");
		lblNotaFiscal.setBounds(246, 67, 65, 14);
		panel.add(lblNotaFiscal);
		
		JLabel lblValorTotal = new JLabel("Valor Total R$");
		lblValorTotal.setBounds(10, 123, 76, 14);
		panel.add(lblValorTotal);
		
		valor_total = new JTextField();
		valor_total.setEnabled(false);
		valor_total.setBounds(10, 148, 96, 20);
		panel.add(valor_total);
		valor_total.setColumns(10);
		
		data_v = new JTextField();
		try{

	           javax.swing.text.MaskFormatter format_data = new javax.swing.text.MaskFormatter("##/##/####");

	           data_v = new javax.swing.JFormattedTextField(format_data);

	        }catch (Exception e){}
		data_v.setEnabled(false);
		data_v.setBounds(116, 148, 120, 20);
		panel.add(data_v);
		data_v.setColumns(10);
		
		JLabel lblDataDoVencimento = new JLabel("Data do Vencimento");
		lblDataDoVencimento.setBounds(116, 123, 111, 14);
		panel.add(lblDataDoVencimento);
		
		data_p = new JTextField();
		try{

	           javax.swing.text.MaskFormatter format_data = new javax.swing.text.MaskFormatter("##/##/####");

	           data_p = new javax.swing.JFormattedTextField(format_data);

	        }catch (Exception e){}
		data_p.setEnabled(false);
		data_p.setBounds(246, 148, 120, 20);
		panel.add(data_p);
		data_p.setColumns(10);
		
		JLabel lblDataDoPagamento = new JLabel("Data do pagamento");
		lblDataDoPagamento.setBounds(246, 123, 96, 14);
		panel.add(lblDataDoPagamento);
		
		JLabel lblValorPago = new JLabel("Valor Pago");
		lblValorPago.setBounds(10, 179, 65, 14);
		panel.add(lblValorPago);
		
		valor_p = new JTextField();
		valor_p.setEnabled(false);
		valor_p.setBounds(10, 204, 226, 20);
		panel.add(valor_p);
		valor_p.setColumns(10);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Feed_insumos.this.dispose();
			}
		});
		btnSair.setBounds(208, 247, 89, 23);
		panel.add(btnSair);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(246, 179, 48, 14);
		panel.add(lblStatus);
		
		JComboBox status = new JComboBox();
		status.setEnabled(false);
		status.setModel(new DefaultComboBoxModel(new String[] {"", "EM ABERTO", "PAGO"}));
		status.setBounds(246, 203, 120, 20);
		panel.add(status);
		
		JComboBox fornecedor = new JComboBox();
		fornecedor.setEnabled(false);
		fornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String sql ="select * from entrada_insumos where fornecedor=?";
					Connection con = Conexao.faz_conexao();
				//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)fornecedor.getSelectedItem() );
					
					ResultSet set = statement.executeQuery();

					while (set.next()) {
					
					id.setText(set.getString("codigo"));
					nf.setText(set.getString("nf"));
					valor_total.setText(set.getString("total"));
					data_v.setText(set.getString("data_vencimento"));
					status.setSelectedItem(set.getString("status2"));
					
					
					  
					 
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		fornecedor.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from entrada_insumos";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				fornecedor.addItem(rs.getString("fornecedor"));
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
		fornecedor.setBounds(10, 92, 226, 20);
		panel.add(fornecedor);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (((String) status.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione um status!");
				}
			
				else {
				try {		
					Connection con = Conexao.faz_conexao();
					
					String sql = "update entrada_insumos set data_pagamento=?, valor_pago=?, status2=? where codigo=?";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, data_p.getText());
					stmt.setString(2, valor_p.getText());
					stmt.setString(3, (String) status.getSelectedItem());
					stmt.setString(4, id.getText());

			
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Feed editado com sucesso!!!");
					Feed_insumos.this.dispose();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			}
		});
		btnSalvar.setEnabled(false);
		btnSalvar.setBounds(109, 247, 89, 23);
		panel.add(btnSalvar);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNovo.setEnabled(false);
				btnSalvar.setEnabled(true);
				fornecedor.setEnabled(true);
				status.setEnabled(true);
				data_p.setEnabled(true);
				valor_p.setEnabled(true);
			}
		});
		btnNovo.setBounds(10, 247, 89, 23);
		panel.add(btnNovo);
	}
}
