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
import java.awt.Toolkit;

public class Fabricacao_placas_blocos extends JFrame {

	private JPanel contentPane;
	private JTextField data;
	private JTextField qtd;
	private JTextField quebra;
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
					Fabricacao_placas_blocos frame = new Fabricacao_placas_blocos();
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
	public Fabricacao_placas_blocos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fabricacao_placas_blocos.class.getResource("/img/cube_111065.png")));
		setTitle("Fabrica\u00E7\u00E3o de Placas/Blocos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 422, 438);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 385, 372);
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
		
		JComboBox funcionario = new JComboBox();
		funcionario.setEnabled(false);
		funcionario.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from cadastro_funcionario where status= 1";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				funcionario.addItem(rs.getString("nome"));
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
		funcionario.setBounds(10, 92, 225, 20);
		panel.add(funcionario);
		
		JLabel lblFuncionrio = new JLabel("Funcion\u00E1rio");
		lblFuncionrio.setBounds(10, 67, 62, 14);
		panel.add(lblFuncionrio);
		
		qtd = new JTextField();
		qtd.setEnabled(false);
		qtd.setBounds(10, 148, 48, 20);
		panel.add(qtd);
		qtd.setColumns(10);
		
		JLabel lblQtd = new JLabel("Qtd");
		lblQtd.setBounds(10, 123, 48, 14);
		panel.add(lblQtd);
		
		quebra = new JTextField();
		quebra.setEnabled(false);
		quebra.setBounds(68, 148, 48, 20);
		panel.add(quebra);
		quebra.setColumns(10);
		
		JLabel lblQuebra = new JLabel("Quebra");
		lblQuebra.setBounds(68, 123, 48, 14);
		panel.add(lblQuebra);
		
		JComboBox mes = new JComboBox();
		mes.setEnabled(false);
		mes.setModel(new DefaultComboBoxModel(new String[] {"", "Janeiro", "Fevereiro", "Mar\u00E7o", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		mes.setBounds(126, 148, 109, 20);
		panel.add(mes);
		
		JLabel lblMs = new JLabel("M\u00EAs");
		lblMs.setBounds(126, 123, 48, 14);
		panel.add(lblMs);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es");
		lblObservaes.setBounds(20, 179, 62, 14);
		panel.add(lblObservaes);
		
		JTextArea obs = new JTextArea();
		obs.setEnabled(false);
		obs.setBackground(SystemColor.control);
		obs.setBounds(10, 204, 362, 94);
		panel.add(obs);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(10, 325, 89, 23);
		panel.add(btnNovo);
		
		JButton btnIncluir = new JButton("Incluir");
		btnIncluir.setEnabled(false);
		btnIncluir.setBounds(109, 325, 89, 23);
		panel.add(btnIncluir);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(208, 325, 89, 23);
		panel.add(btnSair);
		
		JComboBox destino = new JComboBox();
		destino.setEnabled(false);
		destino.setModel(new DefaultComboBoxModel(new String[] {"", "Estoque F\u00E1brica", "Estoque D\u00E9posito"}));
		destino.setBounds(245, 148, 127, 21);
		panel.add(destino);
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setBounds(245, 123, 48, 14);
		panel.add(lblDestino);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Placas", "Blocos"}));
		comboBox.setEnabled(false);
		comboBox.setBounds(245, 92, 127, 21);
		panel.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Tipo");
		lblNewLabel.setBounds(245, 67, 48, 14);
		panel.add(lblNewLabel);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fabricacao_placas_blocos.this.dispose();
			}
		});
		btnIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((String) funcionario.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione um funcionário!");
				}
				else if(qtd.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo quantidade!");
				}
				else if(quebra.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo quebra!");
				}
				
				
				else {
				try {
					Connection con = Conexao.faz_conexao();
					String sql = "insert into fabricacao_placas_blocos(data, nome, tipo, qtd, quebra, mes, destino, obs, status) value (?, ?, ?, ?, ?, ?, ?, ?, 1)";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					
					stmt.setString(1, data.getText());
					stmt.setString(2, (String) funcionario.getSelectedItem());
					stmt.setString(3, (String) comboBox.getSelectedItem());
					stmt.setString(4, qtd.getText());
					stmt.setString(5, quebra.getText());
					stmt.setString(6, (String) mes.getSelectedItem());
					stmt.setString(7, (String) destino.getSelectedItem());
					stmt.setString(8, obs.getText());
					

					
					
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Gesso Salvo com sucesso!");
					funcionario.setSelectedItem(null);
					comboBox.setSelectedItem(null);
					qtd.setText("");
					quebra.setText("");
					mes.setSelectedItem(null);
					destino.setSelectedItem(null);
					obs.setText("");
				
					
				
					
					
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}}
		});
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNovo.setEnabled(false);
				btnIncluir.setEnabled(true);
				comboBox.setEnabled(true);
				funcionario.setEnabled(true);
				destino.setEnabled(true);
				qtd.setEnabled(true);
				quebra.setEnabled(true);
				mes.setEnabled(true);
				obs.setEnabled(true);
				
			}
		});
	}
}
