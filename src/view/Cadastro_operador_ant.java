package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import java.awt.Toolkit;

public class Cadastro_operador_ant extends JFrame {

	private JPanel contentPane;
	private JTextField nome;
	private JTextField user;
	private JPasswordField pass;
	private JPasswordField pass2;
	private JPasswordField passadm;
	private JTextField id;
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
					Cadastro_operador_ant frame = new Cadastro_operador_ant();
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
	public Cadastro_operador_ant() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cadastro_operador_ant.class.getResource("/img/icons8-adicionar-usu\u00E1rio-masculino-32.png")));
		setTitle("Cadastro de Operador");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 590, 589);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(116, 11, 48, 14);
		panel.add(lblNome);
		
		JComboBox nome1 = new JComboBox();
		nome1.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_operador where status= 1";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				nome1.addItem(rs.getString("nome"));
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
		nome1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
try {
					
					String sql ="select * from cadastro_operador where nome=?";
					Connection con = Conexao.faz_conexao();
					//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)nome1.getSelectedItem() );
					ResultSet set = statement.executeQuery();
					

					while (set.next()) {
				      id.setText(set.getString("codigo"));
				      nome.setText(set.getString("nome"));
				      user.setText(set.getString("login"));
				      pass.setText(set.getString("senha"));
				      pass2.setText(set.getString("senha"));

				     
					 

					  nome.setEnabled(true);
					  user.setEnabled(true);
						pass.setEnabled(true);
						pass2.setEnabled(true);
						passadm.setEnabled(true);

					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
				
			}
		});
		nome1.setEnabled(false);
		nome1.setBounds(116, 35, 263, 20);
		panel.add(nome1);
		
		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setBounds(10, 67, 48, 14);
		panel.add(lblNome_1);
		
		nome = new JTextField();
		nome.setBounds(10, 92, 369, 20);
		panel.add(nome);
		nome.setColumns(10);
		
		JLabel lblLoginDoUsuario = new JLabel("Login do Usuario");
		lblLoginDoUsuario.setBounds(10, 123, 81, 14);
		panel.add(lblLoginDoUsuario);
		
		user = new JTextField();
		user.setBounds(10, 148, 180, 20);
		panel.add(user);
		user.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBounds(200, 148, 179, 20);
		panel.add(pass);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(200, 123, 48, 14);
		panel.add(lblSenha);
		
		JLabel lblConfirmarSenha = new JLabel("Confirmar Senha");
		lblConfirmarSenha.setBounds(10, 179, 96, 14);
		panel.add(lblConfirmarSenha);
		
		pass2 = new JPasswordField();
		pass2.setEnabled(false);
		pass2.setBounds(10, 204, 180, 20);
		panel.add(pass2);
		
		passadm = new JPasswordField();
		passadm.setEnabled(false);
		passadm.setBounds(200, 204, 179, 20);
		panel.add(passadm);
		
		JLabel lblSenhaDoAdministrador = new JLabel("Senha do Administrador");
		lblSenhaDoAdministrador.setBounds(200, 179, 117, 14);
		panel.add(lblSenhaDoAdministrador);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBounds(10, 11, 48, 14);
		panel.add(lblId);
		
		id = new JTextField();
		id.setEnabled(false);
		id.setEditable(false);
		id.setBounds(10, 35, 96, 20);
		panel.add(id);
		id.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(null, "Cadastro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 235, 180, 288);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JCheckBox chckbxCadastroDepsito = new JCheckBox("Cadastrar Dep\u00F3sito");
		chckbxCadastroDepsito.setBackground(Color.WHITE);
		chckbxCadastroDepsito.setBounds(6, 21, 125, 23);
		panel_1.add(chckbxCadastroDepsito);
		
		JCheckBox chckbxCadastroFbrica = new JCheckBox("Cadastrar F\u00E1brica");
		chckbxCadastroFbrica.setBackground(Color.WHITE);
		chckbxCadastroFbrica.setBounds(6, 47, 115, 23);
		panel_1.add(chckbxCadastroFbrica);
		
		JCheckBox chckbxCadastrarFornecedor = new JCheckBox("Cadastrar Fornecedor");
		chckbxCadastrarFornecedor.setBackground(Color.WHITE);
		chckbxCadastrarFornecedor.setBounds(6, 73, 131, 23);
		panel_1.add(chckbxCadastrarFornecedor);
		
		JCheckBox chckbxCadastrarFuncionrio = new JCheckBox("Cadastrar Funcion\u00E1rio");
		chckbxCadastrarFuncionrio.setBackground(Color.WHITE);
		chckbxCadastrarFuncionrio.setBounds(6, 99, 133, 23);
		panel_1.add(chckbxCadastrarFuncionrio);
		
		JCheckBox chckbxCadastrar = new JCheckBox("Cadastrar Insumos");
		chckbxCadastrar.setBackground(Color.WHITE);
		chckbxCadastrar.setBounds(6, 125, 125, 23);
		panel_1.add(chckbxCadastrar);
		
		JCheckBox chckbxCadastrarMateriaPrima = new JCheckBox("Cadastrar Materia Prima");
		chckbxCadastrarMateriaPrima.setBackground(Color.WHITE);
		chckbxCadastrarMateriaPrima.setBounds(6, 151, 145, 23);
		panel_1.add(chckbxCadastrarMateriaPrima);
		
		JCheckBox chckbxCadastrarMotorista = new JCheckBox("Cadastrar Motorista");
		chckbxCadastrarMotorista.setBackground(Color.WHITE);
		chckbxCadastrarMotorista.setBounds(6, 177, 125, 23);
		panel_1.add(chckbxCadastrarMotorista);
		
		JCheckBox chckbxCadastrarProdutoAcabado = new JCheckBox("Cadastrar Produto Acabado");
		chckbxCadastrarProdutoAcabado.setBackground(Color.WHITE);
		chckbxCadastrarProdutoAcabado.setBounds(6, 203, 159, 23);
		panel_1.add(chckbxCadastrarProdutoAcabado);
		
		JCheckBox chckbxCadastrarTransportadora = new JCheckBox("Cadastrar Transportadora");
		chckbxCadastrarTransportadora.setBackground(Color.WHITE);
		chckbxCadastrarTransportadora.setBounds(6, 229, 159, 23);
		panel_1.add(chckbxCadastrarTransportadora);
		
		JCheckBox chckbxCadastrarVendedor = new JCheckBox("Cadastrar Vendedor");
		chckbxCadastrarVendedor.setBackground(Color.WHITE);
		chckbxCadastrarVendedor.setBounds(6, 255, 125, 23);
		panel_1.add(chckbxCadastrarVendedor);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Pesquisa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(200, 235, 180, 288);
		panel.add(panel_2);
		
		JCheckBox chckbxPesquisarDepsito = new JCheckBox("Pesquisar Dep\u00F3sito");
		chckbxPesquisarDepsito.setBackground(Color.WHITE);
		chckbxPesquisarDepsito.setBounds(6, 21, 125, 23);
		panel_2.add(chckbxPesquisarDepsito);
		
		JCheckBox chckbxPesquisarFbrica = new JCheckBox("Pesquisar F\u00E1brica");
		chckbxPesquisarFbrica.setBackground(Color.WHITE);
		chckbxPesquisarFbrica.setBounds(6, 47, 115, 23);
		panel_2.add(chckbxPesquisarFbrica);
		
		JCheckBox chckbxPesquisarFornecedor = new JCheckBox("Pesquisar Fornecedor");
		chckbxPesquisarFornecedor.setBackground(Color.WHITE);
		chckbxPesquisarFornecedor.setBounds(6, 73, 131, 23);
		panel_2.add(chckbxPesquisarFornecedor);
		
		JCheckBox chckbxPesquisarFuncionrio = new JCheckBox("Pesquisar Funcion\u00E1rio");
		chckbxPesquisarFuncionrio.setBackground(Color.WHITE);
		chckbxPesquisarFuncionrio.setBounds(6, 99, 133, 23);
		panel_2.add(chckbxPesquisarFuncionrio);
		
		JCheckBox chckbxPesquisarInsumos = new JCheckBox("Pesquisar Insumos");
		chckbxPesquisarInsumos.setBackground(Color.WHITE);
		chckbxPesquisarInsumos.setBounds(6, 125, 125, 23);
		panel_2.add(chckbxPesquisarInsumos);
		
		JCheckBox chckbxPesquisarMateriaPrima = new JCheckBox("Pesquisar Materia Prima");
		chckbxPesquisarMateriaPrima.setBackground(Color.WHITE);
		chckbxPesquisarMateriaPrima.setBounds(6, 151, 145, 23);
		panel_2.add(chckbxPesquisarMateriaPrima);
		
		JCheckBox chckbxPesquisarMotorista = new JCheckBox("Pesquisar Motorista");
		chckbxPesquisarMotorista.setBackground(Color.WHITE);
		chckbxPesquisarMotorista.setBounds(6, 177, 125, 23);
		panel_2.add(chckbxPesquisarMotorista);
		
		JCheckBox chckbxPesquisarProdutoAcabado = new JCheckBox("Pesquisar Produto Acabado");
		chckbxPesquisarProdutoAcabado.setBackground(Color.WHITE);
		chckbxPesquisarProdutoAcabado.setBounds(6, 203, 159, 23);
		panel_2.add(chckbxPesquisarProdutoAcabado);
		
		JCheckBox chckbxPesquisarTransportadora = new JCheckBox("Pesquisar Transportadora");
		chckbxPesquisarTransportadora.setBackground(Color.WHITE);
		chckbxPesquisarTransportadora.setBounds(6, 229, 159, 23);
		panel_2.add(chckbxPesquisarTransportadora);
		
		JCheckBox chckbxPesquisarVendedor = new JCheckBox("Pesquisar Vendedor");
		chckbxPesquisarVendedor.setBackground(Color.WHITE);
		chckbxPesquisarVendedor.setBounds(6, 255, 125, 23);
		panel_2.add(chckbxPesquisarVendedor);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new TitledBorder(null, "Estoque", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(389, 36, 191, 76);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JCheckBox chckbxEstoqueDeFabrica = new JCheckBox("Estoque da F\u00E1brica");
		chckbxEstoqueDeFabrica.setBackground(Color.WHITE);
		chckbxEstoqueDeFabrica.setBounds(6, 18, 124, 23);
		panel_3.add(chckbxEstoqueDeFabrica);
		
		JCheckBox chckbxEstoqueDoDposito = new JCheckBox("Estoque do D\u00E9posito");
		chckbxEstoqueDoDposito.setBackground(Color.WHITE);
		chckbxEstoqueDoDposito.setBounds(6, 44, 136, 23);
		panel_3.add(chckbxEstoqueDoDposito);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new TitledBorder(null, "Entrada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(389, 123, 191, 119);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JCheckBox chckbxEntradaMateriaPrima = new JCheckBox("Entrada Materia Prima");
		chckbxEntradaMateriaPrima.setBackground(Color.WHITE);
		chckbxEntradaMateriaPrima.setBounds(6, 20, 135, 23);
		panel_4.add(chckbxEntradaMateriaPrima);
		
		JCheckBox chckbxEntradaDeInsumos = new JCheckBox("Entrada de Insumos");
		chckbxEntradaDeInsumos.setBackground(Color.WHITE);
		chckbxEntradaDeInsumos.setBounds(6, 46, 135, 23);
		panel_4.add(chckbxEntradaDeInsumos);
		
		JCheckBox chckbxEntradaDe = new JCheckBox("Entrada Produto Acabado");
		chckbxEntradaDe.setBackground(Color.WHITE);
		chckbxEntradaDe.setBounds(6, 72, 155, 23);
		panel_4.add(chckbxEntradaDe);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBorder(new TitledBorder(null, "Laborat\u00F3rio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(390, 253, 190, 106);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		JCheckBox chckbxLoteGesso = new JCheckBox("Lote gesso em bloco");
		chckbxLoteGesso.setBackground(Color.WHITE);
		chckbxLoteGesso.setBounds(6, 17, 125, 23);
		panel_5.add(chckbxLoteGesso);
		
		JCheckBox chckbxLoteGessoEm = new JCheckBox("Lote gesso em placa");
		chckbxLoteGessoEm.setBackground(Color.WHITE);
		chckbxLoteGessoEm.setBounds(6, 43, 125, 23);
		panel_5.add(chckbxLoteGessoEm);
		
		JCheckBox chckbxLoteGessoEm_1 = new JCheckBox("Lote gesso em p\u00F3");
		chckbxLoteGessoEm_1.setBackground(Color.WHITE);
		chckbxLoteGessoEm_1.setBounds(6, 69, 111, 23);
		panel_5.add(chckbxLoteGessoEm_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_6.setBorder(new TitledBorder(null, "Fabrica\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(390, 370, 190, 153);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		JCheckBox chckbxFabricaoDeGesso = new JCheckBox("Fabrica\u00E7\u00E3o de gesso em bloco");
		chckbxFabricaoDeGesso.setBackground(Color.WHITE);
		chckbxFabricaoDeGesso.setBounds(6, 18, 171, 23);
		panel_6.add(chckbxFabricaoDeGesso);
		
		JCheckBox chckbxFabricaoDeGesso_1 = new JCheckBox("Fabrica\u00E7\u00E3o de gesso em placa");
		chckbxFabricaoDeGesso_1.setBackground(Color.WHITE);
		chckbxFabricaoDeGesso_1.setBounds(6, 44, 171, 23);
		panel_6.add(chckbxFabricaoDeGesso_1);
		
		JCheckBox chckbxFabricaoDeGesso_2 = new JCheckBox("Fabrica\u00E7\u00E3o de gesso em p\u00F3");
		chckbxFabricaoDeGesso_2.setBackground(Color.WHITE);
		chckbxFabricaoDeGesso_2.setBounds(6, 70, 157, 23);
		panel_6.add(chckbxFabricaoDeGesso_2);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.faz_conexao();
					String sql = "insert into cadastro_operador(login, senha, nome, c_deposito, status) value (?, ?, ?, ?, 1)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1, user.getText());
					stmt.setString(2, pass.getText());
					stmt.setString(3, nome.getText());
					stmt.setBoolean(4, chckbxCadastroDepsito.isSelected());

					
					
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Operador cadastrado com sucesso!");
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSalvar.setBounds(10, 546, 89, 23);
		panel.add(btnSalvar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setBounds(208, 546, 89, 23);
		panel.add(btnEditar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cadastro_operador_ant.this.dispose();
			}
		});
		btnSair.setBounds(307, 546, 89, 23);
		panel.add(btnSair);
		
		JButton btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nome1.setEnabled(true);
				btnLocalizar.setEnabled(false);
				btnEditar.setEnabled(true);
				btnSalvar.setEnabled(false);
			}
		});
		btnLocalizar.setBounds(109, 546, 89, 23);
		panel.add(btnLocalizar);
	}
}
