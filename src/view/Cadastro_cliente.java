package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
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
import java.awt.Color;
import java.awt.Toolkit;

public class Cadastro_cliente extends JFrame {

	private JPanel contentPane;
	private JTextField data;
	private JTextField razao;
	private JTextField cnpj;
	private JTextField logadouro;
	private JLabel lblN;
	private JTextField numero;
	private JLabel lblBairro;
	private JTextField bairro;
	private JLabel lblCep;
	private JTextField cep;
	private JLabel lblEstado;
	private JTextField cidade;
	private JTextField fone;
	Connection con;
	Statement st;
	ResultSet rs;
	private JTextField id;

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
					Cadastro_cliente frame = new Cadastro_cliente();
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
	public Cadastro_cliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cadastro_cliente.class.getResource("/img/business_application_addmale_useradd_insert_add_user_client_2312.png")));
		setTitle("Cadastro de Clientes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 684, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 648, 294);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(10, 67, 48, 14);
		panel.add(lblData);
		
		data = new JTextField();
		data.setEnabled(false);
		data.setEditable(false);
		data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		data.setBounds(10, 92, 154, 20);
		panel.add(data);
		data.setColumns(10);
		
		JLabel lblRazoSocial = new JLabel("Raz\u00E3o Social");
		lblRazoSocial.setBounds(174, 67, 72, 14);
		panel.add(lblRazoSocial);
		
		razao = new JTextField();
		razao.setEnabled(false);
		razao.setBounds(174, 92, 462, 20);
		panel.add(razao);
		razao.setColumns(10);
		
		JLabel lblCnpj = new JLabel("CNPJ");
		lblCnpj.setBounds(10, 123, 48, 14);
		panel.add(lblCnpj);
		
		cnpj = new JTextField();
		try{

	           javax.swing.text.MaskFormatter format_cnpj = new javax.swing.text.MaskFormatter("###.###.###/####-##");

	           cnpj = new javax.swing.JFormattedTextField(format_cnpj);

	        }catch (Exception e){}
		cnpj.setEnabled(false);
		cnpj.setBounds(10, 143, 154, 20);
		panel.add(cnpj);
		cnpj.setColumns(10);
		
		JLabel lblLogadouro = new JLabel("Logadouro");
		lblLogadouro.setBounds(174, 123, 72, 14);
		panel.add(lblLogadouro);
		
		logadouro = new JTextField();
		logadouro.setEnabled(false);
		logadouro.setColumns(10);
		logadouro.setBounds(174, 143, 462, 20);
		panel.add(logadouro);
		
		lblN = new JLabel("N\u00BA");
		lblN.setBounds(10, 174, 48, 14);
		panel.add(lblN);
		
		numero = new JTextField();
		numero.setEnabled(false);
		numero.setBounds(10, 199, 48, 20);
		panel.add(numero);
		numero.setColumns(10);
		
		lblBairro = new JLabel("Bairro");
		lblBairro.setBounds(69, 174, 48, 14);
		panel.add(lblBairro);
		
		bairro = new JTextField();
		bairro.setEnabled(false);
		bairro.setBounds(68, 199, 96, 20);
		panel.add(bairro);
		bairro.setColumns(10);
		
		lblCep = new JLabel("Cep");
		lblCep.setBounds(176, 174, 48, 14);
		panel.add(lblCep);
		
		cep = new JTextField();
		try{

	           javax.swing.text.MaskFormatter format_cep = new javax.swing.text.MaskFormatter("#####-###");

	           cep = new javax.swing.JFormattedTextField(format_cep);

	        }catch (Exception e){}
		cep.setEnabled(false);
		cep.setBounds(174, 199, 96, 20);
		panel.add(cep);
		cep.setColumns(10);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(286, 174, 48, 14);
		panel.add(lblEstado);
		
		JComboBox estado = new JComboBox();
		estado.setEnabled(false);
		estado.setModel(new DefaultComboBoxModel(new String[] {"", "Acre", "Alagoas", "Amap\u00E1", "Amazonas", "Bahia", "Cear\u00E1", "Esp\u00EDrito Santo", "Goi\u00E1s", "Maranh\u00E3o", "Mato Grosso", "Mato Grosso do Sul", "Minas Gerais", "Par\u00E1", "Para\u00EDba", "Paran\u00E1", "Pernambuco", "Piau\u00ED", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rond\u00F4nia", "Roraima", "Santa Catarina", "S\u00E3o Paulo", "Sergipe", "Tocantins", "Distrito Federal"}));
		estado.setBounds(280, 199, 140, 20);
		panel.add(estado);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(431, 174, 48, 14);
		panel.add(lblCidade);
		
		cidade = new JTextField();
		cidade.setEnabled(false);
		cidade.setBounds(430, 199, 100, 20);
		panel.add(cidade);
		cidade.setColumns(10);
		
		JLabel lblFone = new JLabel("Fone");
		lblFone.setBounds(541, 174, 48, 14);
		panel.add(lblFone);
		
		fone = new JTextField();
		try{

	           javax.swing.text.MaskFormatter format_cnpj = new javax.swing.text.MaskFormatter("(##)-#####-####");

	           fone = new javax.swing.JFormattedTextField(format_cnpj);

	        }catch (Exception e){}
		fone.setEnabled(false);
		fone.setBounds(540, 199, 96, 20);
		panel.add(fone);
		fone.setColumns(10);
		
		JLabel label = new JLabel("Raz\u00E3o Social");
		label.setBounds(10, 11, 72, 14);
		panel.add(label);
		
		JComboBox razao1 = new JComboBox();
		razao1.setEnabled(false);
		razao1.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_cliente where status= 1";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				razao1.addItem(rs.getString("razao"));
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
		razao1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
try {

					String sql ="select * from cadastro_cliente where razao=?";
					Connection con = Conexao.faz_conexao();
				//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)razao1.getSelectedItem() );
					
					ResultSet set = statement.executeQuery();

					while (set.next()) {
				      id.setText(set.getString("codigo"));
					  razao.setText(set.getString("razao"));
					  cnpj.setText(set.getString("cnpj"));
					  logadouro.setText(set.getString("logadouro"));
					  numero.setText(set.getString("numero"));
					  bairro.setText(set.getString("bairro"));
					  cep.setText(set.getString("cep"));
					  estado.setSelectedItem(set.getString("estado"));
					  cidade.setText(set.getString("cidade"));
					  fone.setText(set.getString("fone"));
					  
					  razao.setEnabled(true);
					  cnpj.setEnabled(true);
						logadouro.setEnabled(true);
						numero.setEnabled(true);
						bairro.setEnabled(true);
						cep.setEnabled(true);
						estado.setEnabled(true);
						cidade.setEnabled(true);
						fone.setEnabled(true);
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		

		razao1.setBounds(10, 36, 626, 20);
		panel.add(razao1);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(664, 11, 48, 14);
		panel.add(lblId);
		
		id = new JTextField();
		id.setEditable(false);
		id.setEnabled(false);
		id.setBounds(664, 36, 96, 20);
		panel.add(id);
		id.setColumns(10);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 251, 89, 23);
		panel.add(btnNovo);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setBounds(109, 251, 89, 23);
		panel.add(btnIncluir);
		btnIncluir.setEnabled(false);
		
		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.setBounds(208, 251, 89, 23);
		panel.add(btnLocalizar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(307, 251, 89, 23);
		panel.add(btnEditar);
		btnEditar.setEnabled(false);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(505, 251, 89, 23);
		panel.add(btnSair);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id.setText("");
				razao1.setSelectedItem(null);
				razao.setText("");
				cnpj.setText("");
				logadouro.setText("");
				numero.setText("");
				bairro.setText("");
				cep.setText("");
				estado.setSelectedItem(null);
				cidade.setText("");
				fone.setText("");
				
				id.setEnabled(false);
				razao1.setEnabled(false);
				data.setEnabled(false);
				razao.setEnabled(false);
				cnpj.setEnabled(false);
				logadouro.setEnabled(false);
				numero.setEnabled(false);
				bairro.setEnabled(false);
				cep.setEnabled(false);
				estado.setEnabled(false);
				cidade.setEnabled(false);
				fone.setEnabled(false);
				
				btnNovo.setEnabled(true);
				btnIncluir.setEnabled(false);
				btnLocalizar.setEnabled(true);
				btnEditar.setEnabled(false);
				btnLimpar.setEnabled(true);
				btnSair.setEnabled(true);
				
			}
		});
		btnLimpar.setBounds(406, 251, 89, 23);
		panel.add(btnLimpar);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro_cliente.this.dispose();
			}
		});
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(razao.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo razão social!");
				}
				else if(cnpj.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo cnpj!");
				}
				else if(logadouro.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo logadouro!");
				}
				else if(numero.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo numero!");
				}
				else if(bairro.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo bairro!");
				}
				else if(cep.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo cep!");
				}
				else if (((String) estado.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione um estado!");
				}
				else if(cidade.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo cidade!");
				}
				else if(fone.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo fone!");
				}
			
				else {
				try {		
					Connection con = Conexao.faz_conexao();
					
					String sql = "update cadastro_cliente set razao=?, cnpj=?, logadouro=? , numero=? , bairro=?, cep=? , estado=?, cidade=?, fone=? where codigo=?";
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, razao.getText());
					stmt.setString(2, cnpj.getText());
					stmt.setString(3, logadouro.getText());
					stmt.setString(4, numero.getText());
					stmt.setString(5, bairro.getText());
					stmt.setString(6, cep.getText());
					stmt.setString(7, (String) estado.getSelectedItem());
					stmt.setString(8, cidade.getText());
					stmt.setString(9, fone.getText());
					stmt.setString(10, id.getText());

			
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Cliente editado com sucesso!!!");
					Cadastro_cliente.this.dispose();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				razao1.setEnabled(true);
				btnNovo.setEnabled(false);
				btnLocalizar.setEnabled(false);
				btnEditar.setEnabled(true);

			}
		});
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(razao.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo razão social!");
				}
				else if(cnpj.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo cnpj!");
				}
				else if(logadouro.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo logadouro!");
				}
				else if(numero.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo numero!");
				}
				else if(bairro.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo bairro!");
				}
				else if(cep.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo cep!");
				}
				else if (((String) estado.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione um estado!");
				}
				else if(cidade.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo cidade!");
				}
				else if(fone.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo fone!");
				}
			
				else {
					
					try {
						Connection con = Conexao.faz_conexao();
						String sql = "insert into cadastro_cliente(data, razao, cnpj, logadouro, numero, bairro, cep, estado, cidade, fone, status) value (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
						
						PreparedStatement stmt = con.prepareStatement(sql);
						
						
						stmt.setString(1, data.getText());
						stmt.setString(2, razao.getText());
						stmt.setString(3, cnpj.getText());
						stmt.setString(4, logadouro.getText());
						stmt.setString(5, numero.getText());
						stmt.setString(6, bairro.getText());
						stmt.setString(7, cep.getText());
						stmt.setString(8, (String) estado.getSelectedItem());
						stmt.setString(9, cidade.getText());
						stmt.setString(10, fone.getText());
						
						
						stmt.execute();
						
						stmt.close();
						con.close();
						JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
						razao.setText("");
						cnpj.setText("");
						logadouro.setText("");
						numero.setText("");
						bairro.setText("");
						cep.setText("");
						cidade.setText("");
						fone.setText("");
						estado.setSelectedItem(null);
						
						
						
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					}	
			}
		});
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				razao.setEnabled(true);
				cnpj.setEnabled(true);
				logadouro.setEnabled(true);
				numero.setEnabled(true);
				bairro.setEnabled(true);
				cep.setEnabled(true);
				estado.setEnabled(true);
				cidade.setEnabled(true);
				fone.setEnabled(true);
				btnNovo.setEnabled(false);
				btnEditar.setEnabled(false);
				btnIncluir.setEnabled(true);
				btnLocalizar.setEnabled(false);
		
				

			}
		});
	}
}
