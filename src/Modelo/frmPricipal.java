package Modelo;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
public class frmPricipal implements ActionListener {

	private JFrame frmSorteio;
	private JTextField txfBrindes;
	private JTextField txfUser;
	private static String Usuarios [][] = {{"0","0"}};
	private static String Brindes [][] = {{"0","0"}};
	private JButton btnSortear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPricipal window = new frmPricipal();
					window.frmSorteio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmPricipal() {
		initialize();
		if(Verificar_brindes() > 0) {
			CarregarArrayFunc();
			CarregarArrayBindes();
			System.out.print(Usuarios.length);
		}
		else {
			JOptionPane.showMessageDialog(null, "Não há brindes cadastrados");
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSorteio = new JFrame();
		frmSorteio.setTitle("SORTEIO");
		frmSorteio.setBounds(100, 100, 719, 744);
		frmSorteio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSorteio.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("FUNCIONARIO");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(148, 172, 138, 23);
		frmSorteio.getContentPane().add(lblNewLabel);

		JLabel lblNmeroDoPremio = new JLabel("NÚMERO DO PREMIO");
		lblNmeroDoPremio.setHorizontalAlignment(SwingConstants.LEFT);
		lblNmeroDoPremio.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNmeroDoPremio.setBounds(200, 467, 318, 23);
		frmSorteio.getContentPane().add(lblNmeroDoPremio);

		txfBrindes = new JTextField();
		txfBrindes.setHorizontalAlignment(SwingConstants.CENTER);
		txfBrindes.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txfBrindes.setEditable(false);
		txfBrindes.setBounds(200, 501, 318, 20);
		frmSorteio.getContentPane().add(txfBrindes);
		txfBrindes.setColumns(10);

		txfUser = new JTextField();
		txfUser.setHorizontalAlignment(SwingConstants.LEFT);
		txfUser.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		txfUser.setEditable(false);
		txfUser.setColumns(10);
		txfUser.setBounds(148, 206, 408, 23);
		frmSorteio.getContentPane().add(txfUser);

		btnSortear = new JButton("SORTEAR");
		btnSortear.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		btnSortear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSortear.setBounds(200, 636, 144, 40);
		frmSorteio.getContentPane().add(btnSortear);

		lblRe = new JLabel("NÚMERO DO RE");
		lblRe.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblRe.setBounds(148, 107, 138, 23);
		frmSorteio.getContentPane().add(lblRe);

		txfRe = new JTextField();
		txfRe.setHorizontalAlignment(SwingConstants.LEFT);
		txfRe.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txfRe.setEditable(false);
		txfRe.setColumns(10);
		txfRe.setBounds(148, 141, 120, 20);
		frmSorteio.getContentPane().add(txfRe);

		lblDescrioDoPremio = new JLabel("DESCRIÇÃO DO PREMIO");
		lblDescrioDoPremio.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblDescrioDoPremio.setBounds(200, 541, 194, 23);
		frmSorteio.getContentPane().add(lblDescrioDoPremio);

		txfDescBrindes = new JTextField();
		txfDescBrindes.setHorizontalAlignment(SwingConstants.LEFT);
		txfDescBrindes.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		txfDescBrindes.setEditable(false);
		txfDescBrindes.setColumns(10);
		txfDescBrindes.setBounds(200, 575, 382, 25);
		frmSorteio.getContentPane().add(txfDescBrindes);

		lblSetor = new JLabel("SETOR");
		lblSetor.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblSetor.setBounds(315, 107, 138, 23);
		frmSorteio.getContentPane().add(lblSetor);

		txfSetor = new JTextField();
		txfSetor.setHorizontalAlignment(SwingConstants.LEFT);
		txfSetor.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txfSetor.setEditable(false);
		txfSetor.setColumns(10);
		txfSetor.setBounds(315, 141, 241, 23);
		frmSorteio.getContentPane().add(txfSetor);
		
		JLabel lblPrefab = new JLabel("NOME DA EMPRESA");
		lblPrefab.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 44));
		lblPrefab.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrefab.setBounds(0, 11, 703, 51);
		frmSorteio.getContentPane().add(lblPrefab);
		
		JLabel lblSorteioDePremios = new JLabel("SORTEIO DE PRÊMIOS PARA OS FUNCIONÁRIOS");
		lblSorteioDePremios.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		lblSorteioDePremios.setHorizontalAlignment(SwingConstants.CENTER);
		lblSorteioDePremios.setBounds(0, 73, 703, 23);
		frmSorteio.getContentPane().add(lblSorteioDePremios);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 703, 705);
		frmSorteio.getContentPane().add(lblNewLabel_1);
		resizeImageIcon(lblNewLabel_1, "/Modelo/pngwing.com.png");
		btnSortear.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnSortear) {
			if (Verificar_brindes() == 0) {
				JOptionPane.showMessageDialog(null, "Não há Brindes para Sortear");
				return;
			}
			Random random = new Random();
			int randomNextRow = random.nextInt(Usuarios.length);
			int randoNextColumn = random.nextInt(Usuarios[0].length);
			int randorUser = Integer.parseInt(Usuarios[randomNextRow][randoNextColumn]);

			int randomNextRowBrindes = random.nextInt(Brindes.length);
			int randoNextColumnBrindes = random.nextInt(Brindes[0].length);
			int randoValorBrindes = Integer.parseInt(Brindes[randomNextRowBrindes][randoNextColumnBrindes]);
			
			Sorteado = false;
			while (Sorteado == false) {
				randomNextRow = random.nextInt(Usuarios.length);
				randoNextColumn = random.nextInt(Usuarios[0].length);
				randorUser = Integer.parseInt(Usuarios[randomNextRow][randoNextColumn]);
				BuscarNome(randorUser);
			}
			SorteadoBrindes = false;
			while (SorteadoBrindes == false) {
				if (Verificar_brindes2(randoValorBrindes) != 0) {
					SorteadoBrindes = true;
					break;
				}
				randomNextRowBrindes = random.nextInt(Brindes.length);
				randoNextColumnBrindes = random.nextInt(Brindes[0].length);
				randoValorBrindes = Integer.parseInt(Brindes[randomNextRowBrindes][randoNextColumnBrindes]);
				BuscarBrindes(randoValorBrindes);
			}
			if(Sorteado == true && SorteadoBrindes == true){
				BuscarBrindes(randoValorBrindes);
				txfUser.setText(Nome);
				txfRe.setText(String.valueOf(randorUser));
				txfBrindes.setText(String.valueOf(randoValorBrindes));
				txfSetor.setText(Setor);
				txfDescBrindes.setText(DescBrindes);
				System.out.print(txfBrindes.getText() + "\n");
				SalvarSorteado();
				DeletarUserAndBrindes();
				CarregarArrayFunc();
				CarregarArrayBindes();
				System.out.print(Usuarios.length);
				
			}
		}

	}
	
	private String Nome,Setor,DescBrindes;
	private JLabel lblRe;
	private JTextField txfRe;
	private JLabel lblDescrioDoPremio;
	private JTextField txfDescBrindes;
	private JLabel lblSetor;
	private JTextField txfSetor;
	private Boolean Sorteado,SorteadoBrindes;

	private static String[][] CarregarArrayFunc() {
		Connection con = null;
		Statement stmt;
		String Query = "SELECT * FROM Tab_User";
		try {
			con = ConexaoSQL.getConnection();
			stmt = con.createStatement();

			// Contar o número de registros
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Tab_User");
			rs.next();
			int rowCount = rs.getInt(1);
			Usuarios = new String[rowCount][2];

			// Preencher o array com os dados
			rs = stmt.executeQuery(Query);
			int i = 0;
			while (rs.next()) {
				Usuarios[i][0] = String.valueOf(i + 1);
				Usuarios[i][1] = rs.getString("RE");
				i++;
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.print("Erro Banco de Dados" + e.getMessage());
		}
		return Usuarios;
	}

	private static String[][] CarregarArrayBindes() {
		Connection con = null;
		Statement stmt;
		String Query = "SELECT * FROM Tab_Brindes";
		try {
			con = ConexaoSQL.getConnection();
			stmt = con.createStatement();

			// Contar o número de registros
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Tab_Brindes");
			rs.next();
			int rowCount = rs.getInt(1);
			Brindes = new String[rowCount][2];
			// Preencher o array com os dados
			rs = stmt.executeQuery(Query);
			int i = 0;
			while (rs.next()) {
				Brindes[i][0] = String.valueOf(i + 1);
				Brindes[i][1] = rs.getString("ID_Brindes");
				i++;
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.print("Erro Banco de Dados" + e.getMessage());
		}
		return Brindes;
	}

	private void BuscarNome(int id) {

		Connection con = null;
		Statement stmt;
		String Query = "SELECT * FROM Tab_User WHERE RE = " + id + "";
		try {
			con = ConexaoSQL.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(Query);
			if (rs.next()) {
				Nome = rs.getString("Nome");
				Setor = rs.getString("Setor");
				if (Nome.equals("")) {
					Sorteado = false;
				} else {
					Sorteado = true;
				}
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.print("Erro Banco de Dados" + e.getMessage());
		}
	}

	private void BuscarBrindes(int id) {

		Connection con = null;
		Statement stmt;
		String Query = "SELECT * FROM Tab_Brindes WHERE ID_Brindes = " + id + "";
		try {
			con = ConexaoSQL.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(Query);
			if (rs.next()) {
				DescBrindes = rs.getString("Descricao");
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.print("Erro Banco de Dados" + e.getMessage());
		}
	}
	private void SalvarSorteado() {
	    Connection con = null;
	    String QueryCheck = "SELECT COUNT(*) FROM Tab_Sorteio WHERE ID_Brindes = " + txfBrindes.getText();
	    String QueryInsert = "INSERT INTO Tab_Sorteio (ID_User, Nome, Setor, ID_Brindes, DescBrindes) VALUES ('" + txfRe.getText() + "','" + txfUser.getText() + "','" + txfSetor.getText() + "','" + txfBrindes.getText() + "','" + txfDescBrindes.getText() + "')";
	    try {
	        con = ConexaoSQL.getConnection();
	        Statement stmtCheck = con.createStatement();
	        ResultSet rs = stmtCheck.executeQuery(QueryCheck);
	        if (rs.next() && rs.getInt(1) == 0) {
	            PreparedStatement stmtInsert = con.prepareStatement(QueryInsert);
	            stmtInsert.execute();
	            stmtInsert.close();
	        } else {
	            System.out.print("Brinde já sorteado");
	        }
	        stmtCheck.close();
	        con.close();
	    } catch (Exception e) {
	        System.out.print(e);
	    }
	}
	private Integer Verificar_brindes() {
		Connection con = null;
		Statement stmt;
		String Query = "SELECT COUNT(*) FROM Tab_Brindes";
		try {
			con = ConexaoSQL.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(Query);
			if (rs.next()) {
                return rs.getInt(1);
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.print("Erro Banco de Dados" + e.getMessage());
		}
		return 0;
	}
	private void DeletarUserAndBrindes() {
		Connection con = null;
		String Query = "DELETE FROM Tab_User WHERE RE = "+ txfRe.getText() +"";
		String Query2 = "DELETE FROM Tab_Brindes WHERE ID_Brindes = "+ txfBrindes.getText() +"";
		try {
			con = ConexaoSQL.getConnection();
			PreparedStatement stmt = con.prepareStatement(Query);
			PreparedStatement stmt2 = con.prepareStatement(Query2);
			stmt.execute();
			stmt2.execute();
			stmt.close();
			stmt2.close();
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	private Integer Verificar_brindes2(int Brindes) {
		Connection con = null;
		Statement stmt;
		String Query = "SELECT COUNT(*) FROM Tab_Brindes Where ID_Brindes = "+ Brindes +"";
		try {
			con = ConexaoSQL.getConnection();
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(Query);
			if (rs.next()) {
                return rs.getInt(1);
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.print("Erro Banco de Dados" + e.getMessage());
		}
		return 0;
	}
	public static void resizeImageIcon(JLabel label, String imagePath) {
        // Obter as dimensões do JLabel
        int width = label.getWidth();
        int height = label.getHeight();

        // Carregar a imagem original
        ImageIcon originalIcon = new ImageIcon(imagePath);
        Image originalImage = originalIcon.getImage();

        // Redimensionar a imagem
        @SuppressWarnings("unused")
		Image resizedImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Definir o ImageIcon redimensionado no JLabel
        label.setIcon(new ImageIcon(frmPricipal.class.getResource("/Modelo/pngwing.com.png")));
    }
}	
