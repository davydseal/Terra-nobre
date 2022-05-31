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
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;

public class Entrada_materia_prima extends JFrame {

	private JPanel contentPane;
	private JTextField data;
	private JTextField quantidade;
	private JTextField unitario;
	private JTextField d_vencimento;
	Connection con;
	Statement st;
	ResultSet rs;
	private JTextField id;
	private JTextField TOTAL;
	private JTextField nf;

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
					Entrada_materia_prima frame = new Entrada_materia_prima();
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
	public Entrada_materia_prima() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Entrada_materia_prima.class.getResource("/img/Red-Cargo-Boxes_35543.png")));
		setTitle("Entrada de Mat\u00E9ria Prima");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 554, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 516, 408);
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
		
		JLabel lblFornecedor = new JLabel("Fornecedor");
		lblFornecedor.setBounds(10, 67, 62, 14);
		panel.add(lblFornecedor);
		
		JComboBox fornecedor = new JComboBox();
		fornecedor.setEnabled(false);
		fornecedor.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_fornecedor_materia_prima where status= 1";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				fornecedor.addItem(rs.getString("razao"));
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
		fornecedor.setBounds(10, 92, 305, 20);
		panel.add(fornecedor);
		
		JComboBox pagamento = new JComboBox();
		pagamento.setEnabled(false);
		pagamento.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			st = con.createStatement();
			String s = "select * from cadastro_forma_pagamento where status= 1";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				pagamento.addItem(rs.getString("forma"));
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
		pagamento.setBounds(325, 92, 180, 20);
		panel.add(pagamento);
		
		JLabel lblFormas = new JLabel("Forma de Pagamento");
		lblFormas.setBounds(325, 67, 114, 14);
		panel.add(lblFormas);
		
		JLabel lblInsumos = new JLabel("Mat\u00E9ria Prima");
		lblInsumos.setBounds(10, 128, 76, 14);
		panel.add(lblInsumos);
		
		JLabel lblMsDeVencimento = new JLabel("M\u00EAs de Vencimento");
		lblMsDeVencimento.setBounds(200, 128, 96, 14);
		panel.add(lblMsDeVencimento);
		
		JComboBox m_vencimento = new JComboBox();
		m_vencimento.setEnabled(false);
		m_vencimento.setModel(new DefaultComboBoxModel(new String[] {"", "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		m_vencimento.setBounds(200, 153, 115, 20);
		panel.add(m_vencimento);
		
		JLabel lblQuantidade = new JLabel("Quantidade");
		lblQuantidade.setBounds(10, 184, 62, 14);
		panel.add(lblQuantidade);
		
		quantidade = new JTextField();
		quantidade.setEnabled(false);
		quantidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int quant;
				Double preco, total;
				
				quant=Integer.parseInt(quantidade.getText());
				preco=Double.parseDouble(unitario.getText());
				
				total = preco * quant;
				
				TOTAL.setText(total+ "");
			}
		});
		quantidade.setBounds(10, 209, 96, 20);
		panel.add(quantidade);
		quantidade.setColumns(10);
		
		unitario = new JTextField();
		unitario.setEnabled(false);
		unitario.setEditable(false);
		unitario.setBounds(116, 209, 96, 20);
		panel.add(unitario);
		unitario.setColumns(10);
		
		d_vencimento = new JTextField();
		d_vencimento.setEnabled(false);
		d_vencimento.setBounds(325, 209, 180, 20);
		panel.add(d_vencimento);
		d_vencimento.setColumns(10);
		
		JLabel lblRUnitario = new JLabel("R$ Unitario");
		lblRUnitario.setBounds(116, 184, 62, 14);
		panel.add(lblRUnitario);
		
		JLabel lblRTotal = new JLabel("R$ Total");
		lblRTotal.setBounds(222, 184, 48, 14);
		panel.add(lblRTotal);
		
		JLabel lblDataDe = new JLabel("Data de Vencimento");
		lblDataDe.setBounds(325, 184, 108, 14);
		panel.add(lblDataDe);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es");
		lblObservaes.setBounds(10, 240, 62, 14);
		panel.add(lblObservaes);
		
		JTextArea observacoes = new JTextArea();
		observacoes.setEnabled(false);
		observacoes.setBackground(SystemColor.control);
		observacoes.setBounds(10, 265, 494, 84);
		panel.add(observacoes);
		
		
		id = new JTextField();
		id.setEnabled(false);
		id.setEditable(false);
		id.setBounds(530, 36, 96, 20);
		panel.add(id);
		id.setColumns(10);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(530, 11, 48, 14);
		panel.add(lblId);
		
		TOTAL = new JTextField();
		TOTAL.setEditable(false);
		TOTAL.setEnabled(false);
		TOTAL.setBounds(222, 209, 96, 20);
		panel.add(TOTAL);
		TOTAL.setColumns(10);
		
		JComboBox materia = new JComboBox();
		materia.setEnabled(false);
		materia.setBounds(10, 152, 180, 21);
		panel.add(materia);
		materia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
try {
					
					String sql ="select * from cadastro_materia_prima where descricao=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)materia.getSelectedItem() );
					
					ResultSet set = statement.executeQuery();

					while (set.next()) {
				      id.setText(set.getString("codigo"));
					  unitario.setText(set.getString("valor"));
					 

					 
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
			}
		});
		materia.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_materia_prima where status= 1";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				materia.addItem(rs.getString("descricao"));
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
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 374, 89, 23);
		panel.add(btnNovo);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(109, 374, 89, 23);
		panel.add(btnIncluir);
		btnIncluir.setEnabled(false);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(208, 374, 89, 23);
		panel.add(btnSair);
		
		nf = new JTextField();
		nf.setEnabled(false);
		nf.setBounds(325, 153, 180, 20);
		panel.add(nf);
		nf.setColumns(10);
		
		JLabel lblN = new JLabel("Nota Fiscal N\u00BA");
		lblN.setBounds(325, 128, 76, 14);
		panel.add(lblN);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Entrada_materia_prima.this.dispose();
			}
		});
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((String) fornecedor.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione um fornecedor!");
				}
				else if (((String) pagamento.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione uma forma de pagamento!");
				}
				else if (((String) materia.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione os matéria prima!");
				}
				else if (((String) m_vencimento.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione o mês de vencimento!");
				}
				else if(nf.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o Nº da nota fiscal!");
				}
				else if(quantidade.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha a quantidade!");
				}
				
				else {
				try {
					Connection con = Conexao.faz_conexao();
					String sql = "insert into entrada_materia_prima(data, fornecedor, forma, materia, mes, qtd, valor, total, data_vencimento, obs, nf, status2, status, data_pagamento, valor_pago) value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'EM ABERTO', 1, '', '')";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					
					stmt.setString(1, data.getText());
					stmt.setString(2, (String) fornecedor.getSelectedItem());
					stmt.setString(3, (String) pagamento.getSelectedItem());
					stmt.setString(4, (String) materia.getSelectedItem());
					stmt.setString(5, (String) m_vencimento.getSelectedItem());
					stmt.setString(6, quantidade.getText());
					stmt.setString(7, unitario.getText());
					stmt.setString(8, TOTAL.getText());
					stmt.setString(9, d_vencimento.getText());
					stmt.setString(10, observacoes.getText());
					stmt.setString(11, nf.getText());

					
					
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Matéria prima incluida com sucesso!");
					fornecedor.setSelectedItem(null);
					pagamento.setSelectedItem(null);
					materia.setSelectedItem(null);
					m_vencimento.setSelectedItem(null);
					id.setText("");
					quantidade.setText("");
					nf.setText("");
					unitario.setText("");
					TOTAL.setText("");
					d_vencimento.setText("");
					observacoes.setText("");

					
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNovo.setEnabled(false);
				btnIncluir.setEnabled(true);
				fornecedor.setEnabled(true);
				pagamento.setEnabled(true);
				materia.setEnabled(true);
				m_vencimento.setEnabled(true);
				quantidade.setEnabled(true);
				nf.setEnabled(true);
				d_vencimento.setEnabled(true);
				observacoes.setEnabled(true);
			}
		});
	}
}
