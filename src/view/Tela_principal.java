package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import java.awt.Toolkit;


public class Tela_principal extends JFrame {
	
	ResultSet rs;
	private JPanel contentPane;


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
					Tela_principal frame = new Tela_principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	A_receber_venda A_receber_venda;
	Cadastro_cliente Cadastro_cliente;
	Cadastro_deposito Cadastro_deposito;
	Cadastro_fabrica Cadastro_fabrica;
	Cadastro_forma_pagamento Cadastro_forma_pagamento;
	Cadastro_fornecedor_insumos Cadastro_fornecedor_insumos;
	Cadastro_fornecedor_materia_prima Cadastro_fornecedor_materia_prima;
	Cadastro_funcionario Cadastro_funcionario;
	Cadastro_insumos Cadastro_insumos;
	Cadastro_materia_prima Cadastro_materia_prima;
	Cadastro_motorista Cadastro_motorista;
	Cadastro_operador Cadastro_operador;
	Cadastro_terceiros Cadastro_terceiros;
	Cadastro_tipo_sacaria Cadastro_tipo_sacaria;
	Cadastro_transportadora Cadastro_transportadora;
	Cadastro_vendedor Cadastro_vendedor;
	Entrada_insumos Entrada_insumos;
	Entrada_materia_prima Entrada_materia_prima;
	Estoque Estoque;
	Fabricacao_gesso Fabricacao_gesso;
	Fabricacao_placas_blocos Fabricacao_placas_blocos;
	Feed_gesso Feed_gesso;
	Feed_insumos Feed_insumos;
	Feed_materia_prima Feed_materia_prima;
	Feed_placa_bloco Feed_placa_bloco;
	Materia_prima_e_insumos Materia_prima_e_insumos;
	Pagar_fornecedor_insumos Pagar_fornecedor_insumos;
	Pagar_fornecedor_materia_prima Pagar_fornecedor_materia_prima;
	Status_pagamento_fornecedor_insumos Status_pagamento_fornecedor_insumos;
	Venda Venda;
	Venda_placa_bloco Venda_placa_bloco;
	Status_receber_gesso Status_receber_gesso;
	Status_receber_placa_bloco Status_receber_placa_bloco;
	private JLabel usuario;
	
	
	/**
	 * Create the frame.
	 */
	public Tela_principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tela_principal.class.getResource("/img/HomeServer_icon-icons.com_55232.png")));
		
		
		
	setTitle("Terra Nobre");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		setBounds(100, 100, 1385, 740);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArquivo = new JMenu("Arquivo");
		mnArquivo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnArquivo);
		
		JMenuItem mntmCalculadora = new JMenuItem("Calculadora");
		mntmCalculadora.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmCalculadora.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/Calculator_30001.png")));
		mntmCalculadora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("calc.exe");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		mnArquivo.add(mntmCalculadora);
		
		JMenuItem mntmBlocoDeNotas = new JMenuItem("Bloco de notas");
		mntmBlocoDeNotas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmBlocoDeNotas.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/bloc_notes_21874.png")));
		mntmBlocoDeNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Runtime.getRuntime().exec("notepad.exe");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnArquivo.add(mntmBlocoDeNotas);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmSobre.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/icons8-editar-propriedade-32.png")));
		mnArquivo.add(mntmSobre);
		
		JMenuItem mntmCadastroDeOperador = new JMenuItem("Cadastro de Operador");
		mntmCadastroDeOperador.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmCadastroDeOperador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Cadastro_operador == null) 
				{
					Cadastro_operador = new Cadastro_operador();
					Cadastro_operador.setLocationRelativeTo(null);
				}
				Cadastro_operador.setVisible(true);
				//Cadastro_operador exibir = new Cadastro_operador();
			//	exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mntmCadastroDeOperador.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/icons8-adicionar-usu\u00E1rio-masculino-32.png")));
		mnArquivo.add(mntmCadastroDeOperador);
		
		JMenuItem mntmSair = new JMenuItem("Sair");
		mntmSair.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmSair.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/icons8-desligar-32.png")));
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnArquivo.add(mntmSair);
		
		JMenu mnCadastro = new JMenu("Cadastro");
		mnCadastro.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnCadastro);
		
		JMenu mnFornecedor = new JMenu("Fornecedor");
		mnFornecedor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnFornecedor.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/company_22169.png")));
		mnCadastro.add(mnFornecedor);
		
		JMenuItem mntmMteriaPrima = new JMenuItem("M\u00E1teria Prima");
		mntmMteriaPrima.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmMteriaPrima.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/Red-Cargo-Boxes_35543.png")));
		mntmMteriaPrima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Cadastro_fornecedor_materia_prima == null) 
				{
					Cadastro_fornecedor_materia_prima = new Cadastro_fornecedor_materia_prima();
					Cadastro_fornecedor_materia_prima.setLocationRelativeTo(null);
				}
				Cadastro_fornecedor_materia_prima.setVisible(true);
				
				//Cadastro_fornecedor_materia_prima exibir = new Cadastro_fornecedor_materia_prima();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnFornecedor.add(mntmMteriaPrima);
		
		JMenuItem mntmInsumo = new JMenuItem("Insumo");
		mntmInsumo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmInsumo.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/tools_22686.png")));
		mntmInsumo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Cadastro_fornecedor_insumos == null) 
				{
					Cadastro_fornecedor_insumos = new Cadastro_fornecedor_insumos();
					Cadastro_fornecedor_insumos.setLocationRelativeTo(null);
				}
				Cadastro_fornecedor_insumos.setVisible(true);
				//Cadastro_fornecedor_insumos exibir = new Cadastro_fornecedor_insumos();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnFornecedor.add(mntmInsumo);
		
		JMenuItem mntmFbrica = new JMenuItem("F\u00E1brica");
		mntmFbrica.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmFbrica.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/42498factory_99134.png")));
		mntmFbrica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Cadastro_fabrica == null) 
				{
					Cadastro_fabrica = new Cadastro_fabrica();
					Cadastro_fabrica.setLocationRelativeTo(null);
				}
				Cadastro_fabrica.setVisible(true);
				
				//Cadastro_fabrica exibir = new Cadastro_fabrica();
			//	exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		
		JMenuItem mntmMatriaPrima = new JMenuItem("Materia Prima");
		mntmMatriaPrima.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmMatriaPrima.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/Red-Cargo-Boxes_35543.png")));
		mntmMatriaPrima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Cadastro_materia_prima == null) 
				{
					Cadastro_materia_prima = new Cadastro_materia_prima();
					Cadastro_materia_prima.setLocationRelativeTo(null);
				}
				Cadastro_materia_prima.setVisible(true);
				//Cadastro_materia_prima exibir = new Cadastro_materia_prima();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnCadastro.add(mntmMatriaPrima);
		
		JMenuItem mntmTipoDeSacaria = new JMenuItem("Tipo de Sacaria");
		mntmTipoDeSacaria.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmTipoDeSacaria.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/Sack_of_Flour_icon-icons.com_68694.png")));
		mntmTipoDeSacaria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Cadastro_tipo_sacaria == null) 
				{
					Cadastro_tipo_sacaria = new Cadastro_tipo_sacaria();
					Cadastro_tipo_sacaria.setLocationRelativeTo(null);
				}
				Cadastro_tipo_sacaria.setVisible(true);
				//Cadastro_tipo_sacaria exibir = new Cadastro_tipo_sacaria();
			//	exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnCadastro.add(mntmTipoDeSacaria);
		
		JMenuItem mntmTransportadora = new JMenuItem("Transportadora");
		mntmTransportadora.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmTransportadora.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/OOCL-Truck_35571.png")));
		mntmTransportadora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Cadastro_transportadora == null) 
				{
					Cadastro_transportadora = new Cadastro_transportadora();
					Cadastro_transportadora.setLocationRelativeTo(null);
				}
				Cadastro_transportadora.setVisible(true);
			//	Cadastro_transportadora exibir = new Cadastro_transportadora();
			//	exibir.setVisible(true);
			//	exibir.setLocationRelativeTo(null);
			}
		});
		mnCadastro.add(mntmTransportadora);
		
		JMenuItem mntmTerceiros = new JMenuItem("Terceiros");
		mntmTerceiros.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmTerceiros.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/engineer-avatar_114343.png")));
		mntmTerceiros.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Cadastro_terceiros == null) 
				{
					Cadastro_terceiros = new Cadastro_terceiros();
					Cadastro_terceiros.setLocationRelativeTo(null);
				}
				Cadastro_terceiros.setVisible(true);
				//Cadastro_terceiros exibir = new Cadastro_terceiros();
			//	exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnCadastro.add(mntmTerceiros);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmClientes.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/business_application_addmale_useradd_insert_add_user_client_2312.png")));
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Cadastro_cliente == null) 
				{
					Cadastro_cliente = new Cadastro_cliente();
					Cadastro_cliente.setLocationRelativeTo(null);
				}
				Cadastro_cliente.setVisible(true);
				
				
			//	Cadastro_cliente exibir = new Cadastro_cliente();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnCadastro.add(mntmClientes);
		
		JMenuItem mntmFormaDePagamento = new JMenuItem("Forma de Pagamento");
		mntmFormaDePagamento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmFormaDePagamento.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/1495815224-jd15_84582.png")));
		mntmFormaDePagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Cadastro_forma_pagamento == null) 
				{
					Cadastro_forma_pagamento = new Cadastro_forma_pagamento();
					Cadastro_forma_pagamento.setLocationRelativeTo(null);
				}
				Cadastro_forma_pagamento.setVisible(true);
				//Cadastro_forma_pagamento exibir = new Cadastro_forma_pagamento();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnCadastro.add(mntmFormaDePagamento);
		
		JMenuItem mntmMotorista = new JMenuItem("Motorista");
		mntmMotorista.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmMotorista.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/Truck_Yellow_icon-icons.com_54884.png")));
		mntmMotorista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Cadastro_motorista == null) 
				{
					Cadastro_motorista = new Cadastro_motorista();
					Cadastro_motorista.setLocationRelativeTo(null);
				}
				Cadastro_motorista.setVisible(true);
				//Cadastro_motorista exibir = new Cadastro_motorista();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnCadastro.add(mntmMotorista);
		mnCadastro.add(mntmFbrica);
		
		JMenuItem mntmDepsito = new JMenuItem("Dep\u00F3sito");
		mntmDepsito.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmDepsito.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/ForkliftTruck_Loaded_Black_icon-icons.com_54893.png")));
		mntmDepsito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Cadastro_deposito == null) 
				{
					Cadastro_deposito = new Cadastro_deposito();
					Cadastro_deposito.setLocationRelativeTo(null);
				}
				Cadastro_deposito.setVisible(true);
				//Cadastro_deposito exibir = new Cadastro_deposito();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
				
			}
		});
		
		JMenuItem mntmInsumos = new JMenuItem("Insumos");
		mntmInsumos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmInsumos.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/tools_22686.png")));
		mntmInsumos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Cadastro_insumos == null) 
				{
					Cadastro_insumos = new Cadastro_insumos();
					Cadastro_insumos.setLocationRelativeTo(null);
				}
				Cadastro_insumos.setVisible(true);
				//Cadastro_insumos exibir = new Cadastro_insumos();
			//	exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnCadastro.add(mntmInsumos);
		
		JMenuItem mntmFuncionrios = new JMenuItem("Funcion\u00E1rios");
		mntmFuncionrios.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmFuncionrios.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/person_user_customer_man_male_man_boy_people_1687.png")));
		mntmFuncionrios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Cadastro_funcionario == null) 
				{
					Cadastro_funcionario = new Cadastro_funcionario();
					Cadastro_funcionario.setLocationRelativeTo(null);
				}
				Cadastro_funcionario.setVisible(true);
				//Cadastro_funcionario exibir = new Cadastro_funcionario();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnCadastro.add(mntmFuncionrios);
		mnCadastro.add(mntmDepsito);
		
		JMenuItem mntmVendedor = new JMenuItem("Vendedor");
		mntmVendedor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmVendedor.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/Sales-report_25411.png")));
		mntmVendedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Cadastro_vendedor == null) 
				{
					Cadastro_vendedor = new Cadastro_vendedor();
					Cadastro_vendedor.setLocationRelativeTo(null);
				}
				Cadastro_vendedor.setVisible(true);
				//Cadastro_vendedor exibir = new Cadastro_vendedor();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnCadastro.add(mntmVendedor);
		
		JMenu mnEntrada = new JMenu("Entrada");
		mnEntrada.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnEntrada);
		
		JMenuItem mntmMateriaPrima = new JMenuItem("Materia Prima");
		mntmMateriaPrima.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmMateriaPrima.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/Red-Cargo-Boxes_35543.png")));
		mntmMateriaPrima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Entrada_materia_prima == null) 
				{
					Entrada_materia_prima = new Entrada_materia_prima();
					Entrada_materia_prima.setLocationRelativeTo(null);
				}
				Entrada_materia_prima.setVisible(true);
				//Entrada_materia_prima exibir = new Entrada_materia_prima();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnEntrada.add(mntmMateriaPrima);
		
		JMenuItem mntmInsumos_1 = new JMenuItem("Insumos");
		mntmInsumos_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmInsumos_1.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/tools_22686.png")));
		mntmInsumos_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Entrada_insumos == null) 
				{
					Entrada_insumos = new Entrada_insumos();
					Entrada_insumos.setLocationRelativeTo(null);
				}
				Entrada_insumos.setVisible(true);
				//Entrada_insumos exibir = new Entrada_insumos();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnEntrada.add(mntmInsumos_1);
		
		JMenu mnFabricao = new JMenu("Fabrica\u00E7\u00E3o");
		mnFabricao.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnFabricao);
		
		JMenuItem mntmGessoLento = new JMenuItem("Gesso");
		mntmGessoLento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmGessoLento.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/shipping_products_22121.png")));
		mntmGessoLento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Fabricacao_gesso == null) 
				{
					Fabricacao_gesso = new Fabricacao_gesso();
					Fabricacao_gesso.setLocationRelativeTo(null);
				}
				Fabricacao_gesso.setVisible(true);
				//Fabricacao_gesso exibir = new Fabricacao_gesso();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnFabricao.add(mntmGessoLento);
		
		JMenuItem mntmPlaca = new JMenuItem("Placa/Blocos");
		mntmPlaca.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmPlaca.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/cube_111065.png")));
		mntmPlaca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Fabricacao_placas_blocos == null) 
				{
					Fabricacao_placas_blocos = new Fabricacao_placas_blocos();
					Fabricacao_placas_blocos.setLocationRelativeTo(null);
				}
				Fabricacao_placas_blocos.setVisible(true);
				//Fabricacao_placas_blocos exibir = new Fabricacao_placas_blocos();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnFabricao.add(mntmPlaca);
		
		JMenu mnEstoque = new JMenu("Estoque");
		mnEstoque.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnEstoque);
		
		JMenuItem mntmConsultar = new JMenuItem("Produ\u00E7\u00E3o");
		mntmConsultar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmConsultar.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/Stacked-Boxes_35541.png")));
		mntmConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Estoque == null) 
				{
					Estoque = new Estoque();
					Estoque.setLocationRelativeTo(null);
				}
				Estoque.setVisible(true);
				//Estoque exibir = new Estoque();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnEstoque.add(mntmConsultar);
		
		JMenuItem mntmMatriaPrimaE = new JMenuItem("Mat\u00E9ria Prima e Insumos");
		mntmMatriaPrimaE.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmMatriaPrimaE.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/Box_1_35524.png")));
		mntmMatriaPrimaE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Materia_prima_e_insumos == null) 
				{
					Materia_prima_e_insumos = new Materia_prima_e_insumos();
					Materia_prima_e_insumos.setLocationRelativeTo(null);
				}
				Materia_prima_e_insumos.setVisible(true);
				//Materia_prima_e_insumos exibir = new Materia_prima_e_insumos();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnEstoque.add(mntmMatriaPrimaE);
		
		JMenu mnVenda = new JMenu("Venda");
		mnVenda.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnVenda);
		
		JMenuItem mntmGessoLento_1 = new JMenuItem("Gesso");
		mntmGessoLento_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmGessoLento_1.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/shipping_products_22121.png")));
		mntmGessoLento_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Venda == null) 
				{
					Venda = new Venda();
					Venda.setLocationRelativeTo(null);
				}
				Venda.setVisible(true);
				//Venda exibir = new Venda();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnVenda.add(mntmGessoLento_1);
		
		JMenuItem mntmPlacaEBloco = new JMenuItem("Placa e Bloco");
		mntmPlacaEBloco.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmPlacaEBloco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Venda_placa_bloco == null) 
				{
					Venda_placa_bloco = new Venda_placa_bloco();
					Venda_placa_bloco.setLocationRelativeTo(null);
				}
				Venda_placa_bloco.setVisible(true);
				//Venda exibir = new Venda();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			
			}
		});
		mntmPlacaEBloco.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/cube_111065.png")));
		mnVenda.add(mntmPlacaEBloco);
		
		JMenu mnPesquisar = new JMenu("Pesquisar");
		mnPesquisar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnPesquisar);
		
		JMenuItem mntmAReceber = new JMenuItem("A Receber Vendas");
		mntmAReceber.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmAReceber.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/Money_27019.png")));
		mntmAReceber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(A_receber_venda == null) 
				{
					A_receber_venda = new A_receber_venda();
					A_receber_venda.setLocationRelativeTo(null);
				}
				A_receber_venda.setVisible(true);
				
			//	A_receber_venda exibir = new A_receber_venda();
			//	exibir.setVisible(true);
			//	exibir.setLocationRelativeTo(null);
			}
		});
		mnPesquisar.add(mntmAReceber);
		
		JMenuItem mntmAPagar = new JMenuItem("A Pagar Materia Prima");
		mntmAPagar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmAPagar.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/bar_code_22177.png")));
		mntmAPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Pagar_fornecedor_materia_prima == null) 
				{
					Pagar_fornecedor_materia_prima = new Pagar_fornecedor_materia_prima();
					Pagar_fornecedor_materia_prima.setLocationRelativeTo(null);
				}
				Pagar_fornecedor_materia_prima.setVisible(true);
				//Pagar_fornecedor_materia_prima exibir = new Pagar_fornecedor_materia_prima();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnPesquisar.add(mntmAPagar);
		
		JMenuItem mntmAPagarInsumos = new JMenuItem("A Pagar Insumos");
		mntmAPagarInsumos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmAPagarInsumos.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/price-scan-scanner-bar-barcode-code_108573.png")));
		mntmAPagarInsumos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Pagar_fornecedor_insumos == null) 
				{
					Pagar_fornecedor_insumos = new Pagar_fornecedor_insumos();
					Pagar_fornecedor_insumos.setLocationRelativeTo(null);
				}
				Pagar_fornecedor_insumos.setVisible(true);
				//Pagar_fornecedor_insumos exibir = new Pagar_fornecedor_insumos();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnPesquisar.add(mntmAPagarInsumos);
		
		JMenuItem mntmStatusPlacasE = new JMenuItem("Status Placas e Blocos");
		mntmStatusPlacasE.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmStatusPlacasE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Status_receber_placa_bloco == null) 
				{
					Status_receber_placa_bloco = new Status_receber_placa_bloco();
					Status_receber_placa_bloco.setLocationRelativeTo(null);
				}
				Status_receber_placa_bloco.setVisible(true);
			}
		});
		mntmStatusPlacasE.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/documentediting_editdocuments_text_documentedi_2820.png")));
		mnPesquisar.add(mntmStatusPlacasE);
		
		JMenuItem mntmStatusGesso = new JMenuItem("Status Gesso");
		mntmStatusGesso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmStatusGesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Status_receber_gesso == null) 
				{
					Status_receber_gesso = new Status_receber_gesso();
					Status_receber_gesso.setLocationRelativeTo(null);
				}
				Status_receber_gesso.setVisible(true);
			}
		});
		mntmStatusGesso.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/document_write_22637.png")));
		mnPesquisar.add(mntmStatusGesso);
		
		JMenuItem mntmStatusPagamento = new JMenuItem("Status Pagamentos Insumos");
		mntmStatusPagamento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmStatusPagamento.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/copy_paste_document_file_1557.png")));
		mntmStatusPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Status_pagamento_fornecedor_insumos == null) 
				{
					Status_pagamento_fornecedor_insumos = new Status_pagamento_fornecedor_insumos();
					Status_pagamento_fornecedor_insumos.setLocationRelativeTo(null);
				}
				Status_pagamento_fornecedor_insumos.setVisible(true);

				//Status_pagamento_fornecedor_insumos exibir = new Status_pagamento_fornecedor_insumos();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnPesquisar.add(mntmStatusPagamento);
		
		JMenu mnFeedback = new JMenu("FeedBack");
		mnFeedback.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		menuBar.add(mnFeedback);
		
		JMenuItem mntmRecebimentoPlacasE = new JMenuItem("Recebimento Placas e Blocos");
		mntmRecebimentoPlacasE.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmRecebimentoPlacasE.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/1486564180-finance-financial-report_81493.png")));
		mntmRecebimentoPlacasE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Feed_placa_bloco == null) 
				{
					Feed_placa_bloco = new Feed_placa_bloco();
					Feed_placa_bloco.setLocationRelativeTo(null);
				}
				Feed_placa_bloco.setVisible(true);
				//Feed_placa_bloco exibir = new Feed_placa_bloco();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnFeedback.add(mntmRecebimentoPlacasE);
		
		JMenuItem mntmRecebimentoGesso = new JMenuItem("Recebimento Gesso");
		mntmRecebimentoGesso.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmRecebimentoGesso.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/profits_78367.png")));
		mntmRecebimentoGesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Feed_gesso == null) 
				{
					Feed_gesso = new Feed_gesso();
					Feed_gesso.setLocationRelativeTo(null);
				}
				Feed_gesso.setVisible(true);
				//Feed_gesso exibir = new Feed_gesso();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnFeedback.add(mntmRecebimentoGesso);
		
		JSeparator separator = new JSeparator();
		mnFeedback.add(separator);
		
		JMenuItem mntmPagamento = new JMenuItem("Pagamentos Materia Prima");
		mntmPagamento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmPagamento.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/Product-sale-report_25407.png")));
		mntmPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Feed_materia_prima == null) 
				{
					Feed_materia_prima = new Feed_materia_prima();
					Feed_materia_prima.setLocationRelativeTo(null);
				}
				Feed_materia_prima.setVisible(true);
				//Feed_materia_prima exibir = new Feed_materia_prima();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnFeedback.add(mntmPagamento);
		
		JMenuItem mntmPagamentoInsumos = new JMenuItem("Pagamento Insumos");
		mntmPagamentoInsumos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mntmPagamentoInsumos.setIcon(new ImageIcon(Tela_principal.class.getResource("/img/trade_report_reports_documents_2351.png")));
		mntmPagamentoInsumos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Feed_insumos == null) 
				{
					Feed_insumos = new Feed_insumos();
					Feed_insumos.setLocationRelativeTo(null);
				}
				Feed_insumos.setVisible(true);
				//Feed_insumos exibir = new Feed_insumos();
				//exibir.setVisible(true);
				//exibir.setLocationRelativeTo(null);
			}
		});
		mnFeedback.add(mntmPagamentoInsumos);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 646, 1369, 45);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTerra = new JLabel("Sistema Terra Nobre");
		lblTerra.setForeground(Color.ORANGE);
		lblTerra.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTerra.setBounds(600, 9, 168, 14);
		panel.add(lblTerra);
		
		usuario = new JLabel("New label");
		usuario.setBounds(10, 602, 229, 33);
		contentPane.add(usuario);
		usuario.setForeground(Color.DARK_GRAY);
		usuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		usuario.setText("Bem Vindo "+ System.getProperty("Login"));

	}
}
