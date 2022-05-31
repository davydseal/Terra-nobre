package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
import java.awt.Toolkit;

public class Feed_gesso extends JFrame {

	private JPanel contentPane;
	private JTextField data;
	private JTextField valor;
	private JTextField data_v;
	private JTextField data_r;
	private JTextField valor_r;
	Connection con;
	Statement st;
	ResultSet rs;
	private JTextField nf;
	private JTextField razao;
	private JComboBox cliente;

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
					Feed_gesso frame = new Feed_gesso();
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
	public Feed_gesso() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Feed_gesso.class.getResource("/img/profits_78367.png")));
		setTitle("Feedback Recebimento Gesso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 452, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 416, 267);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(10, 11, 48, 14);
		panel.add(lblData);
		
		data = new JTextField();
		data.setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date(System.currentTimeMillis())));
		data.setEditable(false);
		data.setEnabled(false);
		data.setBounds(10, 36, 96, 20);
		panel.add(data);
		data.setColumns(10);
		
		JLabel lblCliente = new JLabel("Id");
		lblCliente.setBounds(10, 67, 48, 14);
		panel.add(lblCliente);
		
		JLabel lblNNotaFiscal = new JLabel("N\u00BA Nota fiscal");
		lblNNotaFiscal.setBounds(270, 67, 74, 14);
		panel.add(lblNNotaFiscal);
		
		JLabel lblValorTotal = new JLabel("Valor Total R$");
		lblValorTotal.setBounds(10, 117, 74, 14);
		panel.add(lblValorTotal);
		
		valor = new JTextField();
		valor.setEnabled(false);
		valor.setBounds(10, 142, 121, 20);
		panel.add(valor);
		valor.setColumns(10);
		
		data_v = new JTextField();
		data_v.setEnabled(false);
		data_v.setBounds(141, 142, 119, 20);
		panel.add(data_v);
		data_v.setColumns(10);
		
		JLabel lblDataDoVencimento = new JLabel("Data do Vencimento");
		lblDataDoVencimento.setBounds(141, 117, 109, 14);
		panel.add(lblDataDoVencimento);
		
		data_r = new JTextField();
		try{

	           javax.swing.text.MaskFormatter format_data = new javax.swing.text.MaskFormatter("##/##/####");

	           data_r = new javax.swing.JFormattedTextField(format_data);

	        }catch (Exception e){}
		data_r.setEnabled(false);
		data_r.setBounds(270, 142, 134, 20);
		panel.add(data_r);
		data_r.setColumns(10);
		
		JLabel lblDataDoRecebimento = new JLabel("Data do Recebimento");
		lblDataDoRecebimento.setBounds(270, 117, 109, 14);
		panel.add(lblDataDoRecebimento);
		
		JLabel lblValorRecebido = new JLabel("Valor Recebido R$");
		lblValorRecebido.setBounds(10, 173, 90, 14);
		panel.add(lblValorRecebido);
		
		valor_r = new JTextField();
		valor_r.setEnabled(false);
		valor_r.setBounds(10, 198, 121, 20);
		panel.add(valor_r);
		valor_r.setColumns(10);
		
		JComboBox status = new JComboBox();
		status.setModel(new DefaultComboBoxModel(new String[] {"", "EM ABERTO", "PAGO"}));
		status.setEnabled(false);
		status.setBounds(141, 198, 123, 20);
		panel.add(status);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(141, 173, 48, 14);
		panel.add(lblStatus);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Feed_gesso.this.dispose();
			}
		});
		btnSair.setBounds(315, 229, 89, 23);
		panel.add(btnSair);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.setBounds(117, 229, 89, 23);
		panel.add(btnNovo);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (((String) status.getSelectedItem()).isEmpty()) {
					JOptionPane.showMessageDialog(null, "Selecione um status!");
				}
			
				else {
				try {		
					Connection con = Conexao.faz_conexao();
					
					String sql = "update venda set data_recebimento=?, valor_pago=?, status2=? where codigo=?";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, data_r.getText());
					stmt.setString(2, valor_r.getText());
					stmt.setString(3, (String) status.getSelectedItem());
					stmt.setString(4, (String) cliente.getSelectedItem());
					//stmt.setString(4, id.getText());

			
					stmt.execute();
					
					stmt.close();
					con.close();
					JOptionPane.showMessageDialog(null, "Feed editado com sucesso!!!");
					Feed_gesso.this.dispose();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			}
		});
		btnSalvar.setBounds(216, 229, 89, 23);
		panel.add(btnSalvar);
		btnSalvar.setEnabled(false);
		
		cliente = new JComboBox();
		cliente.setBounds(10, 86, 121, 20);
		panel.add(cliente);
		cliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String sql ="select * from venda where codigo=?";
					Connection con = Conexao.faz_conexao();
				//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
					PreparedStatement statement = con.prepareStatement(sql);
					statement.setString(1, (String)cliente.getSelectedItem() );
					
					ResultSet set = statement.executeQuery();

					while (set.next()) {
						
					
					  razao.setText(set.getString("razao"));
					  nf.setText(set.getString("nota_fiscal"));
					  valor.setText(set.getString("valor"));
					  data_v.setText(set.getString("data_vencimento"));
					  data_r.setText(set.getString("data_recebimento"));
					  valor_r.setText(set.getString("valor_pago"));
					  status.setSelectedItem(set.getString("status2"));
					  
					  data_r.setEnabled(true);
					  valor_r.setEnabled(true);
					  status.setEnabled(true);
					  
					 
					}
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		cliente.setModel(new DefaultComboBoxModel(new String[] {""}));
		try {
			Connection con = Conexao.faz_conexao();
			//con = DriverManager.getConnection("jdbc:mysql://localhost/terra", "root", "");
			st = con.createStatement();
			String s = "select * from venda";
			rs = st.executeQuery(s);
			
			while(rs.next()){
				cliente.addItem(rs.getString("codigo"));
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
		
		cliente.setEnabled(false);
		
		nf = new JTextField();
		nf.setEnabled(false);
		nf.setBounds(270, 86, 134, 20);
		panel.add(nf);
		nf.setColumns(10);
		
		razao = new JTextField();
		razao.setEnabled(false);
		razao.setBounds(141, 86, 121, 20);
		panel.add(razao);
		razao.setColumns(10);
		
		JLabel lblRazoSocial = new JLabel("Raz\u00E3o Social");
		lblRazoSocial.setBounds(141, 67, 72, 14);
		panel.add(lblRazoSocial);
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNovo.setEnabled(false);
				btnSalvar.setEnabled(true);
				cliente.setEnabled(true);
				
				
			}
		});
	}
}
