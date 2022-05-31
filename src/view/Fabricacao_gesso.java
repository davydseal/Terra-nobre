package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
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
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.Toolkit;

public class Fabricacao_gesso extends JFrame {

	private JPanel contentPane;
	private JTextField data;
	private JTextField qtd;
	private JTextField lote;
	Connection con;
	Statement st;
	ResultSet rs;
	private JTextField qtds;
	private JTextField peso;

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
					Fabricacao_gesso frame = new Fabricacao_gesso();
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
	public Fabricacao_gesso() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fabricacao_gesso.class.getResource("/img/shipping_products_22121.png")));
		setTitle("Fabrica\u00E7\u00E3o de Gesso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 553, 406);
		contentPane.add(panel);
		panel.setLayout(null);
		
		data = new JTextField();
		data.setBounds(10, 36, 96, 20);
		panel.add(data);
		data.setEnabled(false);
		data.setEditable(false);
		data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		data.setColumns(10);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(10, 11, 48, 14);
		panel.add(lblData);
		
		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio");
		lblFuncionrio.setBounds(10, 67, 64, 14);
		panel.add(lblFuncionrio);
		
		JComboBox nome = new JComboBox();
		nome.setEnabled(false);
		nome.setBounds(10, 92, 306, 20);
		panel.add(nome);
		nome.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_funcionario where status= 1";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				nome.addItem(rs.getString("nome"));
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
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setBounds(10, 123, 48, 14);
		panel.add(lblDestino);
		
		JComboBox destino = new JComboBox();
		destino.setEnabled(false);
		destino.setBounds(10, 148, 82, 20);
		panel.add(destino);
		destino.setModel(new DefaultComboBoxModel(new String[] {"", "F\u00E1brica"}));
		
		JLabel lblTipoDeGesso = new JLabel("Tipo de Sacaria");
		lblTipoDeGesso.setBounds(326, 67, 82, 14);
		panel.add(lblTipoDeGesso);


		
		
		JLabel lblQntEmTon = new JLabel("Qtd. em TON");
		lblQntEmTon.setBounds(102, 123, 64, 14);
		panel.add(lblQntEmTon);
		
		qtd = new JTextField();
		qtd.setEditable(false);
		qtd.setEnabled(false);
		qtd.setBounds(102, 148, 102, 20);
		panel.add(qtd);
		qtd.setColumns(10);
		
		lote = new JTextField();
		lote.setEnabled(false);
		lote.setBounds(438, 148, 102, 20);
		panel.add(lote);
		lote.setColumns(10);
		
		JLabel lblMs = new JLabel("M\u00EAs");
		lblMs.setBounds(10, 179, 48, 14);
		panel.add(lblMs);
		
		JComboBox mes = new JComboBox();
		mes.setEnabled(false);
		mes.setBounds(10, 204, 194, 20);
		panel.add(mes);
		mes.setModel(new DefaultComboBoxModel(new String[] {"", "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es");
		lblObservaes.setBounds(10, 235, 64, 14);
		panel.add(lblObservaes);
		
		JTextArea obs = new JTextArea();
		obs.setEnabled(false);
		obs.setBounds(10, 260, 530, 80);
		panel.add(obs);
		obs.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblLote_1 = new JLabel("Lote");
		lblLote_1.setBounds(438, 123, 48, 14);
		panel.add(lblLote_1);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 361, 89, 23);
		panel.add(btnNovo);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(109, 361, 89, 23);
		panel.add(btnIncluir);
		btnIncluir.setEnabled(false);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(208, 361, 89, 23);
		panel.add(btnSair);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Gesso Lento", "Gesso Intermedi\u00E1rio", "Gesso Fundicao"}));
		comboBox.setBounds(116, 36, 278, 20);
		panel.add(comboBox);
		
		JLabel lblTipoDeGesso_2 = new JLabel("Tipo de Gesso");
		lblTipoDeGesso_2.setBounds(116, 11, 74, 14);
		panel.add(lblTipoDeGesso_2);
		
		JComboBox turno = new JComboBox();
		turno.setModel(new DefaultComboBoxModel(new String[] {"", "1\u00BA Turno", "2\u00BA Turno", "3\u00BA Turno"}));
		turno.setEnabled(false);
		turno.setBounds(404, 36, 136, 20);
		panel.add(turno);
		
		JLabel lblTurno = new JLabel("Turno");
		lblTurno.setBounds(404, 11, 48, 14);
		panel.add(lblTurno);
		
		
		JLabel lblQtdDeSacos = new JLabel("Qtd. Sacos");
		lblQtdDeSacos.setBounds(214, 123, 52, 14);
		panel.add(lblQtdDeSacos);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_1.getSelectedItem().toString().equals("")) {
					qtds.setText("0"); 
					peso.setText("0"); 
					qtd.setText("0"); 
		       }
				else if(comboBox_1.getSelectedItem().toString().equals("Sacaria de Papel 40kg")) {
					qtds.setText("40"); 
					peso.setText("40"); 
					qtd.setText(new String().valueOf(Float.parseFloat(qtds.getText()) * Float.parseFloat(peso.getText()))); 
					//qtd.setText(new String().valueOf(Float.parseFloat(qtds.getText()) * Float.parseFloat(peso.getText()) / 1000)); 
		       }
				else if(comboBox_1.getSelectedItem().toString().equals("Sacaria de Nylon 40kg")) {
					qtds.setText("30"); 
					peso.setText("40"); 
					qtd.setText(new String().valueOf(Float.parseFloat(qtds.getText()) * Float.parseFloat(peso.getText()))); 
		       }
				else if(comboBox_1.getSelectedItem().toString().equals("Sacaria de Nylon 50kg")) {
					qtds.setText("30"); 
					peso.setText("50"); 
					qtd.setText(new String().valueOf(Float.parseFloat(qtds.getText()) * Float.parseFloat(peso.getText()))); 
		       }
				else if(comboBox_1.getSelectedItem().toString().equals("Sacaria Terra Nobre papel 40kg")) {
					qtds.setText("40"); 
					peso.setText("40"); 
					qtd.setText(new String().valueOf(Float.parseFloat(qtds.getText()) * Float.parseFloat(peso.getText()))); 
		       }
				else if(comboBox_1.getSelectedItem().toString().equals("Sacaria Terra Nobre Nylon 40kg")) {
					qtds.setText("30"); 
					peso.setText("40"); 
					qtd.setText(new String().valueOf(Float.parseFloat(qtds.getText()) * Float.parseFloat(peso.getText()))); 
		       }
				else if(comboBox_1.getSelectedItem().toString().equals("Sacaria Terra Nobre Nylon 50kg")) {
					qtds.setText("30"); 
					peso.setText("50"); 
					qtd.setText(new String().valueOf(Float.parseFloat(qtds.getText()) * Float.parseFloat(peso.getText()))); 
		       }
				else if(comboBox_1.getSelectedItem().toString().equals("Sacaria Ingenor Papel 40kg")) {
					qtds.setText("40"); 
					peso.setText("40"); 
					qtd.setText(new String().valueOf(Float.parseFloat(qtds.getText()) * Float.parseFloat(peso.getText()))); 
		       }
				
			}
		});
		comboBox_1.setEnabled(false);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {""}));
		
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_tipo_sacaria";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				comboBox_1.addItem(rs.getString("tipo"));
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
		comboBox_1.setBounds(326, 92, 214, 20);
		panel.add(comboBox_1);
		
		qtds = new JTextField();
		qtds.setEnabled(false);
		qtds.setEditable(false);
		qtds.setBounds(214, 148, 102, 20);
		panel.add(qtds);
		qtds.setColumns(10);
		
		peso = new JTextField();
		peso.setEnabled(false);
		peso.setEditable(false);
		peso.setBounds(326, 148, 102, 20);
		panel.add(peso);
		peso.setColumns(10);
		
		JLabel lblPesoPorSaco = new JLabel("Peso em kg");
		lblPesoPorSaco.setBounds(326, 123, 68, 14);
		panel.add(lblPesoPorSaco);
		
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Fabricacao_gesso.this.dispose();	
			}
		});
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (((String) comboBox.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione um tipo de gesso!");
				}
				else if (((String) turno.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione o turno!");
				}
				else if (((String) nome.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione um funcionário!");
				}
				else if (((String) comboBox_1.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione o tipo de gesso!");
				}
				else if (((String) destino.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione o destino!");
				}
				else if(lote.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o lote!");
				}
				else if (((String) mes.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione o mês!");
				}
				
				else {
				try {
					Connection con = Conexao.faz_conexao();
					String sql = "insert into fabricacao_gesso(data, produto, turno, nome, tipo, destino, qtd, qtds, peso, lote, mes, obs, status) value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					
					stmt.setString(1, data.getText());
					stmt.setString(2, (String) comboBox.getSelectedItem());
					stmt.setString(3, (String) turno.getSelectedItem());
					stmt.setString(4, (String) nome.getSelectedItem());
					stmt.setString(5, (String) comboBox_1.getSelectedItem());
					stmt.setString(6, (String) destino.getSelectedItem());
					stmt.setString(7, qtd.getText());
					stmt.setString(8, qtds.getText());
					stmt.setString(9, peso.getText());
					stmt.setString(10, lote.getText());
					stmt.setString(11, (String) mes.getSelectedItem());
					stmt.setString(12, obs.getText());
			
					
					
					
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Gesso cadastrado com sucesso!");
					comboBox.setSelectedItem(null); 
					turno.setSelectedItem(null); 
					nome.setSelectedItem(null); 
					destino.setSelectedItem(null); 
					qtd.setText("");
					qtds.setText("");
					peso.setText("");
					lote.setText("");
					mes.setSelectedItem(null); 
//					ter.setSelectedItem(null); 
					obs.setText("");
				
					
					
					
					
					
					
					
					
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
				comboBox.setEnabled(true);
				nome.setEnabled(true);
				destino.setEnabled(true);
				comboBox_1.setEnabled(true);
				lote.setEnabled(true);
				mes.setEnabled(true);
				obs.setEnabled(true);
				turno.setEnabled(true);
			}
		});
		
	}
}
