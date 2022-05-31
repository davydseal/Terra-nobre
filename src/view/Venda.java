package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.Toolkit;

public class Venda extends JFrame {

	private JPanel contentPane;
	private JTextField data;
	private JTextField cnpj;
	private JTextField logadouro;
	private JTextField numero;
	private JTextField bairro;
	private JTextField cep;
	private JTextField estado;
	private JTextField cidade;
	private JTextField fone;
	private JTextField tipo_gesso;
	private JTextField tipo_sacaria;
	private JTextField qtd;
	private JTextField quantidade;
	private JTextField unitario;
	private JTextField TOTAL;
	private JTextField data_v;
	private JTextField data_vencimento;
	private JTable table;
	Connection con;
	Statement st;
	ResultSet rs;
	private JTextField id;
	private JTextField estoquef;
	private JTextField nota_fiscal;
	private JTextField valor_frete;
	private JTextField data_p;
	private JTextField data_pagamento;
	private JTextField percent;
	private JTextField valor_comissao;
	private JRadioButton Motorista;
	private JRadioButton Transportadora;
	private JTextField datad;
	private JTextField datam;
	private JTextField dataa;
	private JComboBox frete2;

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
					Venda frame = new Venda();
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
	public Venda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Venda.class.getResource("/img/shipping_products_22121.png")));
		setTitle("Venda");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 643, 727);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 604, 189);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(10, 23, 48, 14);
		panel.add(lblData);
		
		data = new JTextField();
		data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		data.setEditable(false);
		data.setEnabled(false);
		data.setBounds(10, 40, 96, 20);
		panel.add(data);
		data.setColumns(10);
		
		JComboBox razao = new JComboBox();
		razao.setEnabled(false);
		razao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String sql ="select * from cadastro_cliente where razao=?";
					Connection con = Conexao.faz_conexao();
				//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)razao.getSelectedItem() );
					
					ResultSet set = statement.executeQuery();

					while (set.next()) {
					  cnpj.setText(set.getString("cnpj"));
					  logadouro.setText(set.getString("logadouro"));
					  numero.setText(set.getString("numero"));
					  bairro.setText(set.getString("bairro"));
					  cep.setText(set.getString("cep"));
					  estado.setText(set.getString("estado"));
					  cidade.setText(set.getString("cidade"));
					  fone.setText(set.getString("fone"));


					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		razao.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_cliente where status= 1";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				razao.addItem(rs.getString("razao"));
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
		razao.setBounds(116, 40, 476, 20);
		panel.add(razao);
		
		JLabel lblRazoSocial = new JLabel("Raz\u00E3o Social");
		lblRazoSocial.setBounds(118, 23, 62, 14);
		panel.add(lblRazoSocial);
		
		JLabel lblCnpj = new JLabel("Cnpj");
		lblCnpj.setBounds(10, 61, 48, 14);
		panel.add(lblCnpj);
		
		cnpj = new JTextField();
		cnpj.setEditable(false);
		cnpj.setEnabled(false);
		cnpj.setBounds(10, 78, 204, 20);
		panel.add(cnpj);
		cnpj.setColumns(10);
		
		logadouro = new JTextField();
		logadouro.setEditable(false);
		logadouro.setEnabled(false);
		logadouro.setBounds(222, 78, 370, 20);
		panel.add(logadouro);
		logadouro.setColumns(10);
		
		JLabel lblLogadouro = new JLabel("Logadouro");
		lblLogadouro.setBounds(224, 61, 62, 14);
		panel.add(lblLogadouro);
		
		JLabel lblN = new JLabel("N\u00BA");
		lblN.setBounds(10, 101, 48, 14);
		panel.add(lblN);
		
		numero = new JTextField();
		numero.setEditable(false);
		numero.setEnabled(false);
		numero.setBounds(10, 118, 96, 20);
		panel.add(numero);
		numero.setColumns(10);
		
		bairro = new JTextField();
		bairro.setEditable(false);
		bairro.setEnabled(false);
		bairro.setBounds(114, 118, 96, 20);
		panel.add(bairro);
		bairro.setColumns(10);
		
		JLabel lblBairri = new JLabel("Bairro");
		lblBairri.setBounds(114, 101, 48, 14);
		panel.add(lblBairri);
		
		cep = new JTextField();
		cep.setEditable(false);
		cep.setEnabled(false);
		cep.setBounds(222, 118, 96, 20);
		panel.add(cep);
		cep.setColumns(10);
		
		JLabel lblCep = new JLabel("Cep");
		lblCep.setBounds(222, 101, 48, 14);
		panel.add(lblCep);
		
		estado = new JTextField();
		estado.setEditable(false);
		estado.setEnabled(false);
		estado.setBounds(328, 118, 96, 20);
		panel.add(estado);
		estado.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(328, 101, 48, 14);
		panel.add(lblEstado);
		
		cidade = new JTextField();
		cidade.setEditable(false);
		cidade.setEnabled(false);
		cidade.setBounds(434, 118, 158, 20);
		panel.add(cidade);
		cidade.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(434, 101, 48, 14);
		panel.add(lblCidade);
		
		JLabel lblFone = new JLabel("Fone");
		lblFone.setBounds(10, 139, 48, 14);
		panel.add(lblFone);
		
		fone = new JTextField();
		fone.setEditable(false);
		fone.setEnabled(false);
		fone.setBounds(10, 156, 96, 20);
		panel.add(fone);
		fone.setColumns(10);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(116, 155, 89, 23);
		panel.add(btnNovo);
		
		datad = new JTextField();
		datad.setText(new SimpleDateFormat("dd").format(new Date(System.currentTimeMillis())));
		datad.setEnabled(false);
		datad.setEditable(false);
		datad.setBounds(670, 40, 96, 20);
		panel.add(datad);
		datad.setColumns(10);
		
		datam = new JTextField();
		datam.setText(new SimpleDateFormat("MM").format(new Date(System.currentTimeMillis())));
		datam.setEnabled(false);
		datam.setEditable(false);
		datam.setBounds(670, 78, 96, 20);
		panel.add(datam);
		datam.setColumns(10);
		
		dataa = new JTextField();
		dataa.setText(new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis())));
		dataa.setEnabled(false);
		dataa.setEditable(false);
		dataa.setBounds(670, 118, 96, 20);
		panel.add(dataa);
		dataa.setColumns(10);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"", "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		comboBox_5.setBounds(670, 155, 96, 20);
		panel.add(comboBox_5);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				razao.setEnabled(true);
				btnNovo.setEnabled(false);
				

			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 211, 604, 84);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDescrioDoProduto = new JLabel("Descri\u00E7\u00E3o do Produto");
		lblDescrioDoProduto.setBounds(10, 23, 103, 14);
		panel_1.add(lblDescrioDoProduto);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String sql ="select * from fabricacao_gesso where produto=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					java.sql.PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)comboBox_1.getSelectedItem() );
					ResultSet set = statement.executeQuery();

					
					DefaultTableModel  modelo = (DefaultTableModel) table.getModel();
					modelo.setNumRows(0);
					
					while(set.next()) {
						
						modelo.addRow(new Object[] {set.getString("data"), set.getString("tipo"), set.getString("qtd"), set.getString("lote")});
						
					}
					
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "Gesso Lento", "Gesso Intermedi\u00E1rio", "Gesso Fundicao"}));
		comboBox_1.setBounds(10, 48, 170, 22);
		panel_1.add(comboBox_1);
		
		JLabel lblVendaDeGesso = new JLabel("VENDA DE GESSO");
		lblVendaDeGesso.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblVendaDeGesso.setBounds(280, 27, 212, 29);
		panel_1.add(lblVendaDeGesso);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(10, 306, 604, 240);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblLote = new JLabel("Lote");
		lblLote.setBounds(10, 11, 48, 14);
		panel_2.add(lblLote);
		
		tipo_gesso = new JTextField();
		tipo_gesso.setEditable(false);
		tipo_gesso.setEnabled(false);
		tipo_gesso.setBounds(135, 28, 96, 22);
		panel_2.add(tipo_gesso);
		tipo_gesso.setColumns(10);
		
		JLabel lblDescrioDoProduto_1 = new JLabel("Tipo de Gesso");
		lblDescrioDoProduto_1.setBounds(135, 11, 75, 14);
		panel_2.add(lblDescrioDoProduto_1);
		
		tipo_sacaria = new JTextField();
		tipo_sacaria.setEditable(false);
		tipo_sacaria.setEnabled(false);
		tipo_sacaria.setBounds(246, 28, 115, 22);
		panel_2.add(tipo_sacaria);
		tipo_sacaria.setColumns(10);
		
		JLabel lblTipoDeSacaria = new JLabel("Tipo de sacaria");
		lblTipoDeSacaria.setBounds(246, 11, 80, 14);
		panel_2.add(lblTipoDeSacaria);
		
		qtd = new JTextField();
		qtd.setEditable(false);
		qtd.setEnabled(false);
		qtd.setBounds(371, 28, 55, 22);
		panel_2.add(qtd);
		qtd.setColumns(10);
		
		JLabel lblExist = new JLabel("Toneladas");
		lblExist.setBounds(371, 11, 55, 14);
		panel_2.add(lblExist);
		
		quantidade = new JTextField();
		quantidade.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Float quant;
				Float preco;
				Float total;
	
				
				quant=Float.parseFloat(quantidade.getText());
				preco=Float.parseFloat(qtd.getText());
				
				total = preco - quant;
				
				estoquef.setText(total+ "");
			}
		});
		quantidade.setBounds(436, 28, 73, 22);
		panel_2.add(quantidade);
		quantidade.setColumns(10);
		
		JLabel lblDesejada = new JLabel("Quant.");
		lblDesejada.setBounds(436, 11, 48, 14);
		panel_2.add(lblDesejada);
		
		JLabel lblRUnitrio = new JLabel("R$ Unit\u00E1rio");
		lblRUnitrio.setBounds(10, 55, 66, 14);
		panel_2.add(lblRUnitrio);
		
		unitario = new JTextField();
		unitario.addKeyListener(new KeyAdapter() {
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
		unitario.setBounds(10, 72, 115, 22);
		panel_2.add(unitario);
		unitario.setColumns(10);
		
		TOTAL = new JTextField();
		TOTAL.setEditable(false);
		TOTAL.setEnabled(false);
		TOTAL.setBounds(135, 72, 96, 22);
		panel_2.add(TOTAL);
		TOTAL.setColumns(10);
		
		JLabel lblRTotal = new JLabel("R$ Total");
		lblRTotal.setBounds(135, 55, 48, 14);
		panel_2.add(lblRTotal);
		
		JComboBox forma_pagamento = new JComboBox();
		forma_pagamento.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_forma_pagamento where status= 1";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				forma_pagamento.addItem(rs.getString("forma"));
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
		forma_pagamento.setBounds(246, 72, 115, 22);
		panel_2.add(forma_pagamento);
		
		JLabel lblFormaDePagamento = new JLabel("Forma de pagamento");
		lblFormaDePagamento.setBounds(246, 55, 109, 14);
		panel_2.add(lblFormaDePagamento);
		
		JComboBox mes_venda = new JComboBox();
		mes_venda.setModel(new DefaultComboBoxModel(new String[] {"", "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		mes_venda.setBounds(371, 72, 106, 22);
		panel_2.add(mes_venda);
		
		JLabel lblMs = new JLabel("M\u00EAs da Venda");
		lblMs.setBounds(371, 55, 75, 14);
		panel_2.add(lblMs);
		
		data_v = new JTextField();
		try{

	           javax.swing.text.MaskFormatter format_data = new javax.swing.text.MaskFormatter("##/##/####");

	           data_vencimento = new javax.swing.JFormattedTextField(format_data);

	        }catch (Exception e){}
		data_vencimento.setBounds(487, 72, 107, 22);
		panel_2.add(data_vencimento);
		data_vencimento.setColumns(10);
		
		JLabel lblDataDeVencimento = new JLabel("Data de Vencimento");
		lblDataDeVencimento.setBounds(487, 55, 99, 14);
		panel_2.add(lblDataDeVencimento);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Venda.this.dispose();
			}
		});
		btnSair.setBounds(505, 203, 89, 23);
		panel_2.add(btnSair);
		
		JComboBox lote = new JComboBox();
		lote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_1.getSelectedItem().toString().equals("Gesso Lento")) {
				try {

					String sql ="select * from fabricacao_gesso where lote=? and produto='Gesso Lento'";
					Connection con = Conexao.faz_conexao();
				//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)lote.getSelectedItem() );
					
					ResultSet set = statement.executeQuery();

					while (set.next()) {
					  tipo_sacaria.setText(set.getString("tipo"));
					  tipo_gesso.setText(set.getString("produto"));
					  qtd.setText(set.getString("qtd"));
					  id.setText(set.getString("codigo"));


					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
				
				if(comboBox_1.getSelectedItem().toString().equals("Gesso Intermediário")) {
					try {

						String sql ="select * from fabricacao_gesso where lote=? and produto='Gesso Intermediário'";
						Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setString(1, (String)lote.getSelectedItem() );
						
						ResultSet set = statement.executeQuery();

						while (set.next()) {
						  tipo_sacaria.setText(set.getString("tipo"));
						  tipo_gesso.setText(set.getString("produto"));
						  qtd.setText(set.getString("qtd"));
						  id.setText(set.getString("codigo"));


						}
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				
				if(comboBox_1.getSelectedItem().toString().equals("Gesso Fundicao")) {
					try {

						String sql ="select * from fabricacao_gesso where lote=? and produto='Gesso Fundicao'";
						Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
						PreparedStatement statement = con.prepareStatement(sql);
						statement.setString(1, (String)lote.getSelectedItem() );
						
						ResultSet set = statement.executeQuery();

						while (set.next()) {
						  tipo_sacaria.setText(set.getString("tipo"));
						  tipo_gesso.setText(set.getString("produto"));
						  qtd.setText(set.getString("qtd"));
						  id.setText(set.getString("codigo"));


						}
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		});
		lote.setBounds(10, 28, 115, 22);
		panel_2.add(lote);
		lote.setModel(new DefaultComboBoxModel(new String[] {""}));
		//if(comboBox_1.getSelectedItem().toString().equals("Gesso Lento")) {
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from fabricacao_gesso";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				lote.addItem(rs.getString("lote"));
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
		
		//}
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(406, 203, 89, 23);
		panel_2.add(btnIncluir);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(626, 11, 11, 14);
		panel_2.add(lblId);
		
		id = new JTextField();
		id.setEditable(false);
		id.setEnabled(false);
		id.setBounds(626, 28, 40, 20);
		panel_2.add(id);
		id.setColumns(10);
		
		estoquef = new JTextField();
		estoquef.setEditable(false);
		estoquef.setEnabled(false);
		estoquef.setBounds(519, 28, 75, 22);
		panel_2.add(estoquef);
		estoquef.setColumns(10);
		
		JLabel lblEstoqurFinal = new JLabel("Estoque final");
		lblEstoqurFinal.setBounds(519, 11, 75, 14);
		panel_2.add(lblEstoqurFinal);
		
		JLabel lblMsDoRecebimento = new JLabel("M\u00EAs do Recebimento");
		lblMsDoRecebimento.setBounds(10, 99, 105, 14);
		panel_2.add(lblMsDoRecebimento);
		
		JComboBox mes_recebimento = new JComboBox();
		mes_recebimento.setModel(new DefaultComboBoxModel(new String[] {"", "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		mes_recebimento.setBounds(10, 116, 115, 22);
		panel_2.add(mes_recebimento);
		
		JLabel lblNNotaFiscal = new JLabel("N\u00BA Nota Fiscal");
		lblNNotaFiscal.setBounds(135, 99, 75, 14);
		panel_2.add(lblNNotaFiscal);
		
		nota_fiscal = new JTextField();
		nota_fiscal.setBounds(135, 116, 96, 22);
		panel_2.add(nota_fiscal);
		nota_fiscal.setColumns(10);
		
		JLabel lblFrete = new JLabel("Frete");
		lblFrete.setBounds(246, 99, 48, 14);
		panel_2.add(lblFrete);
		
		JComboBox frete = new JComboBox();
		frete.setModel(new DefaultComboBoxModel(new String[] {"", "SIM", "N\u00C3O"}));
		frete.setBounds(246, 116, 115, 22);
		panel_2.add(frete);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(371, 116, 223, 22);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		Motorista = new JRadioButton("Motorista");
		Motorista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Motorista.isSelected()) 
				{
					frete2.removeAllItems();
					Transportadora.setSelected(false);
					try {
						Connection con = Conexao.faz_conexao();
						//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
						st = con.createStatement();
						String s = "select * from cadastro_motorista";
						rs = st.executeQuery(s);
						
						while(rs.next()){
							frete2.addItem(rs.getString("nome"));
						}
						
					} catch (Exception e2) {
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
				}
			}
		});
		Motorista.setBounds(6, 1, 71, 23);
		panel_3.add(Motorista);
		
		Transportadora = new JRadioButton("Transportadora");
		Transportadora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Transportadora.isSelected()) 
				{
					frete2.removeAllItems();
					Motorista.setSelected(false);
					
					try {
						Connection con = Conexao.faz_conexao();
						//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
						st = con.createStatement();
						String s = "select * from cadastro_transportadora";
						rs = st.executeQuery(s);
						
						while(rs.next()){
							
							frete2.addItem(rs.getString("razao"));
						}
						
					} catch (Exception e2) {
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
				}
			}
		});
		Transportadora.setBounds(92, 1, 109, 23);
		panel_3.add(Transportadora);
		
		JLabel lblFrete_1 = new JLabel("Frete");
		lblFrete_1.setBounds(10, 143, 48, 14);
		panel_2.add(lblFrete_1);
		
		valor_frete = new JTextField();
		valor_frete.setBounds(135, 160, 96, 22);
		panel_2.add(valor_frete);
		valor_frete.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(135, 143, 48, 14);
		panel_2.add(lblValor);
		
		data_p = new JTextField();
		try{

	           javax.swing.text.MaskFormatter format_data = new javax.swing.text.MaskFormatter("##/##/####");

	           data_pagamento = new javax.swing.JFormattedTextField(format_data);

	        }catch (Exception e){}
		data_pagamento.setBounds(246, 160, 115, 22);
		panel_2.add(data_pagamento);
		data_pagamento.setColumns(10);
		
		JLabel lblDataDePagamento = new JLabel("Data Pagamento");
		lblDataDePagamento.setBounds(246, 143, 88, 14);
		panel_2.add(lblDataDePagamento);
		
		JComboBox comissao = new JComboBox();
		comissao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comissao.getSelectedItem().toString().equals("SIM")) {
					percent.setEnabled(true);
				}
				else if(comissao.getSelectedItem().toString().equals("NÃO")) {
					percent.setText("");
					percent.setEnabled(false);
					valor_comissao.setText("");
				}
				
			}
		});
		comissao.setModel(new DefaultComboBoxModel(new String[] {"", "SIM", "N\u00C3O"}));
		comissao.setBounds(371, 160, 106, 22);
		panel_2.add(comissao);
		
		JLabel lblComisso = new JLabel("Comiss\u00E3o S/N");
		lblComisso.setBounds(371, 143, 75, 14);
		panel_2.add(lblComisso);
		
		percent = new JTextField();
		percent.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				float quant;
				Double preco, total;
				
				
				quant=Float.parseFloat(percent.getText());
				preco=Double.parseDouble(TOTAL.getText());
				
				total = preco * quant/100;
				
				valor_comissao.setText(total+ "");
			}
		});
		percent.setEnabled(false);
		percent.setBounds(487, 160, 86, 22);
		panel_2.add(percent);
		percent.setColumns(10);
		
		JLabel lblPercent = new JLabel("Percent");
		lblPercent.setBounds(487, 143, 48, 14);
		panel_2.add(lblPercent);
		
		JLabel label = new JLabel("%");
		label.setBounds(583, 164, 11, 14);
		panel_2.add(label);
		
		JLabel lblVendedor = new JLabel("Vendedor");
		lblVendedor.setBounds(10, 187, 48, 14);
		panel_2.add(lblVendedor);
		
		JComboBox vendedor = new JComboBox();
		vendedor.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_vendedor";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				vendedor.addItem(rs.getString("nome"));
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
		vendedor.setBounds(10, 204, 115, 22);
		panel_2.add(vendedor);
		
		valor_comissao = new JTextField();
		valor_comissao.setEnabled(false);
		valor_comissao.setEditable(false);
		valor_comissao.setBounds(135, 204, 96, 22);
		panel_2.add(valor_comissao);
		valor_comissao.setColumns(10);
		
		JLabel lblValorComisso = new JLabel("Valor da Comiss\u00E3o");
		lblValorComisso.setBounds(135, 187, 96, 14);
		panel_2.add(lblValorComisso);
		
		frete2 = new JComboBox();
		frete2.setModel(new DefaultComboBoxModel(new String[] {""}));
		frete2.setBounds(10, 160, 115, 22);
		panel_2.add(frete2);
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if (((String) razao.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione um cliente!");
				}
				
				else if (((String) lote.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione o lote!");
				}

				
				else {
					
					try {		
						Connection con = Conexao.faz_conexao();
						
						String sql = "update fabricacao_gesso set qtd=? where codigo=?";
						PreparedStatement stmt = con.prepareStatement(sql);
						
						stmt.setString(1, estoquef.getText());
						stmt.setString(2, id.getText());
						

				
						stmt.execute();
						
						stmt.close();
						con.close();
						
						
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				try {
					Connection con = Conexao.faz_conexao();
			
					String sql = "insert into venda(data, razao, cnpj, logadouro, numero, bairro, cep, estado, cidade, fone, lote, tipo_gesso, tipo_sacaria, quantidade, valor, valor_total, forma_pagamento, mes_venda, data_vencimento, mes_recebimento, nota_fiscal, frete, motorista_transportadora, frete2, valor_frete, data_pagamento, comissao, percentual, vendedor, valor_comissao, status, status2, data_recebimento, valor_pago) value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '', ?, ?, ?, ?, ?, ?, ?, 1, 'EM ABERTO', '', '')";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					
					stmt.setString(1, data.getText());
					stmt.setString(2, (String) razao.getSelectedItem());
					stmt.setString(3, cnpj.getText());
					stmt.setString(4, logadouro.getText());
					stmt.setString(5, numero.getText());
					stmt.setString(6, bairro.getText());
					stmt.setString(7, cep.getText());
					stmt.setString(8, estado.getText());
					stmt.setString(9, cidade.getText());
					stmt.setString(10, fone.getText());
					stmt.setString(11, (String) lote.getSelectedItem());
					stmt.setString(12, tipo_gesso.getText());
					stmt.setString(13, tipo_sacaria.getText());
					stmt.setString(14, quantidade.getText());
					stmt.setString(15, unitario.getText());
					stmt.setString(16, TOTAL.getText());
					stmt.setString(17, (String) forma_pagamento.getSelectedItem());
					stmt.setString(18, (String) mes_venda.getSelectedItem());
					stmt.setString(19, data_vencimento.getText());
					stmt.setString(20, (String) mes_recebimento.getSelectedItem());
					stmt.setString(21, nota_fiscal.getText());
					stmt.setString(22, (String) frete.getSelectedItem());
					stmt.setString(23, (String) frete2.getSelectedItem());
					stmt.setString(24, valor_frete.getText());
					stmt.setString(25, data_pagamento.getText());
					stmt.setString(26, (String) comissao.getSelectedItem());
					stmt.setString(27, percent.getText());
					stmt.setString(28, (String) vendedor.getSelectedItem());
					stmt.setString(29, valor_comissao.getText());
					
					
					
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Venda realizada com sucesso!");
					

					
					lote.setSelectedItem(null);
					tipo_gesso.setText("");
					tipo_sacaria.setText("");
					qtd.setText("");
					estoquef.setText("");
					quantidade.setText("");
					unitario.setText("");
					TOTAL.setText("");
					forma_pagamento.setSelectedItem(null);
					mes_venda.setSelectedItem(null);
					data_vencimento.setText("");
					mes_recebimento.setSelectedItem(null);
					nota_fiscal.setText("");
					frete.setSelectedItem(null);
					Motorista.setSelected(false);
					Transportadora.setSelected(false);
					frete2.removeAllItems();
					valor_frete.setText("");
					data_pagamento.setText("");
					valor_comissao.setText("");
					percent.setText("");
					percent.setEnabled(false);
					
					vendedor.setSelectedItem(null);
					table.setModel(new DefaultTableModel (null, new String [] {"Data","Descrição","Quantidade","Lote"}));
					
					comboBox_1.setSelectedItem(null);
					//comissao.setSelectedItem(null);
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				}
			
		});
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 557, 604, 120);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"Data", "Descri\u00E7\u00E3o", "Quantidade", "Lote"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPane.setViewportView(table);
	}
}
