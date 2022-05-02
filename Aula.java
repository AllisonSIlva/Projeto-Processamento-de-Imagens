
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Image;
import java.awt.event.*;

public class Aula extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDHT = 640;
	private static final int HEIGHT = 640;
	
	static JTabbedPane tab = new JTabbedPane();
	File f;
	String path;
	public BufferedImage image = null;
	
	public static void configJanela(JFrame frame) {
		
		frame.setTitle("Processamento de Imagens");
		frame.setSize(WIDHT,HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static BufferedImage fitImage(JLabel label, BufferedImage image) {

        int newWidth = label.getWidth();
        int newHeight = label.getHeight();

        if (newWidth < 1 || newHeight < 1) {
            return null;
        }

        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(image, 0, 0, newWidth, newHeight, null);
        g.dispose();
        label.setIcon(new ImageIcon(resizedImage));
        return resizedImage;
    }
	
	public void conteudo() throws IOException {
		
		JFrame frame = new JFrame();
		
		JLabel originalLabel = new JLabel();
		JPanel original = new JPanel();
		Border blackline = BorderFactory.createTitledBorder("Imagem original, sem edições");
		original.setLayout(null);
		original.setBorder(blackline);
		JButton escolher = new JButton("Escolher imagem");
		
		
		tab.add("Original", original);
		original.add(escolher);
		escolher.setBounds(230,25,150,30);
		originalLabel.setBounds(40,60,540,480);
		original.add(originalLabel);
		escolher.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				original.removeAll();
				JFileChooser arquivo = new JFileChooser();
				arquivo.setDialogTitle("Escolha uma imagem...");
				arquivo.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Escolha uma imagem...","png","jpg","jpeg","gif");
				arquivo.addChoosableFileFilter(filter);
				int res = arquivo.showSaveDialog(null);
				if(res == JFileChooser.APPROVE_OPTION) {
					File f = arquivo.getSelectedFile();
					arquivo.updateUI();
					
					try {
						image = ImageIO.read(f);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					fitImage(originalLabel,image);
					System.out.println("Leitura completa");
					
					BufferedImage saidaR = Processamento.GbandaR(image);
					BufferedImage saidaG = Processamento.GbandaG(image);
					BufferedImage saidaB = Processamento.GbandaB(image);
					BufferedImage saidaGray = Processamento.bandaGray(image);
					BufferedImage neg = Processamento.negativo(image);
					BufferedImage negy = Processamento.negativoYIQ(image);
					

					original.add(originalLabel);
					original.setVisible(true);
					
					JLabel negativoLabel = new JLabel();
					Border blackline0 = BorderFactory.createTitledBorder("Filtro negativo da imagem em RGB");
					negativoLabel.setBorder(blackline0);
					negativoLabel.setBounds(40,60,540,480);
					fitImage(negativoLabel,neg);
					JPanel negativo = new JPanel();
					negativo.add(negativoLabel);
					negativo.setVisible(true);
					
					
					
					JPanel bandaGray = new JPanel();
					JLabel bandaGrayLabel = new JLabel();
					Border blackline1 = BorderFactory.createTitledBorder("Imagem com média de pixels como parâmetro pro GrayScale");
					bandaGray.setBorder(blackline1);
					bandaGrayLabel.setBounds(40,60,540,480);
					fitImage(bandaGrayLabel,saidaGray);
					bandaGray.add(bandaGrayLabel);
					bandaGray.setVisible(true);
					
					
					
					JPanel grayPanel = new JPanel();
					JPanel grayRedPanel = new JPanel();
					grayPanel.setLayout(null);
					Border blackline3 = BorderFactory.createTitledBorder("Selecione a banda para exibir o GrayScale individual da banda");
					grayPanel.setBorder(blackline3);
					grayRedPanel.setBounds(40,60,540,480);
					JLabel grayRedLabel = new JLabel();
					grayRedLabel.setBounds(40,60,540,480);
					fitImage(grayRedLabel,saidaR);
					JLabel grayGreenLabel = new JLabel();
					grayGreenLabel.setBounds(40,60,540,480);
					fitImage(grayGreenLabel,saidaG);
					JLabel grayBlueLabel = new JLabel();
					grayBlueLabel.setBounds(40,60,540,480);
					fitImage(grayBlueLabel,saidaB);
					
					
					JButton bred = new JButton("Banda Red");
					JButton bgreen = new JButton("Banda Green");
					JButton bblue = new JButton("Banda Blue");
					bred.setBounds(110,15,120,40);
					bgreen.setBounds(240,15,120,40);
					bblue.setBounds(370,15,120,40);
					
					grayPanel.add(bred);
					grayPanel.add(bgreen);
					grayPanel.add(bblue);
					grayPanel.add(grayRedPanel);
					
					bred.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							grayRedPanel.removeAll();
							grayRedPanel.add(grayRedLabel);;
							grayRedPanel.updateUI();
							
						}
						
					});

					bgreen.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							grayRedPanel.removeAll();
							grayRedPanel.add(grayGreenLabel);
							grayRedPanel.updateUI();
						}});
					bblue.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							grayRedPanel.removeAll();
							grayRedPanel.add(grayBlueLabel);
							grayRedPanel.updateUI();
						}
						
					});
					
					
					JPanel tomPanel = new JPanel();
					tomPanel.setLayout(null);
					Border blackline4 = BorderFactory.createTitledBorder("Digite um valor para alterar o tom e escolha uma banda (-255 até 255)");
					tomPanel.setBorder(blackline4);
					JLabel tomRedLabel = new JLabel();
					JLabel tomGreenLabel = new JLabel();
					JLabel tomBlueLabel = new JLabel();
					JPanel tomRedPanel = new JPanel();
					JButton bredT = new JButton("Banda Red");
					bredT.setBounds(10,15,120,40);
					JButton bgreenT = new JButton("Banda Green");
					bgreenT.setBounds(135,15,120,40);
					JButton bblueT = new JButton("Banda Blue");
					bblueT.setBounds(260,15,120,40);
					JTextField aumento = new JTextField(3);
					tomRedPanel.setBounds(40,60,540,480);
					aumento.setBounds(420,15,40,40);
					aumento.setText("0");
					
					tomPanel.add(bredT);
					tomPanel.add(bgreenT);
					tomPanel.add(bblueT);
					tomPanel.add(tomRedPanel);
					tomPanel.add(aumento);
;
					bredT.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							int Aumento = Integer.parseInt(aumento.getText());
							BufferedImage saidaRT = Processamento.bandaR(image, Aumento);
							tomRedLabel.setBounds(40,60,540,480);
							fitImage(tomRedLabel,saidaRT);
							tomRedPanel.removeAll();
							tomRedPanel.add(tomRedLabel);;
							tomRedPanel.updateUI();
							System.out.println(Aumento);
						}
						
					});

					bgreenT.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int Aumento = Integer.parseInt(aumento.getText());

							BufferedImage saidaGT = Processamento.bandaG(image, Aumento);
							tomGreenLabel.setBounds(40,60,540,480);
							fitImage(tomGreenLabel,saidaGT);
							tomRedPanel.removeAll();
							tomRedPanel.add(tomGreenLabel);
							tomRedPanel.updateUI();
						}});
					bblueT.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int Aumento = Integer.parseInt(aumento.getText());
							BufferedImage saidaBT = Processamento.bandaB(image, Aumento);
							tomBlueLabel.setBounds(40,60,540,480);
							fitImage(tomBlueLabel,saidaBT);
							tomRedPanel.removeAll();
							tomRedPanel.add(tomBlueLabel);
							tomRedPanel.updateUI();
						}
						
					});
					
					
					JPanel biPanelMenu = new JPanel();
					Border blackline5 = BorderFactory.createTitledBorder("Digite um parâmetro para binarizar a imagem (0 até 255)");
					biPanelMenu.setBorder(blackline5);
					JLabel biLabel = new JLabel();
					biPanelMenu.setLayout(null);
					JPanel biPanel = new JPanel();
					biPanel.setBounds(40,60,540,480);
					JTextField biValor = new JTextField(3);
					biValor.setBounds(340,15,40,40);
					JButton biBotao = new JButton("Binarizar");
					biBotao.setBounds(200,15,120,40);
					biPanelMenu.add(biValor);
					biValor.setText("128");
					biPanelMenu.add(biBotao);
					biPanelMenu.add(biPanel);
					biPanel.add(biLabel);
					biBotao.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int valor = Integer.parseInt(biValor.getText());
							BufferedImage saidaBi = Processamento.binarizacao(image, valor);
							biLabel.setBounds(40,60,540,480);
							fitImage(biLabel,saidaBi);
							biPanel.removeAll();
							biPanel.add(biLabel);
							biPanel.updateUI();
							
						}
						
					});
					
					
					JPanel menuYIQ = new JPanel();
					menuYIQ.setLayout(null);
					Border blackline6 = BorderFactory.createTitledBorder("Digite um valor para aplicar o brilho aditivo (-255 até 255)");
					menuYIQ.setBorder(blackline6);
					JPanel brilhoadt = new JPanel();
					brilhoadt.setBounds(40,60,540,480);
					JLabel adtLabel = new JLabel();
					JTextField valorBA = new JTextField(3);
					valorBA.setBounds(340,15,40,40);
					valorBA.setText("20");
					JButton adicionarBrilho = new JButton("Adicionar brilho");
					adicionarBrilho.setBounds(200,15,120,40);
					menuYIQ.add(valorBA);
					menuYIQ.add(adicionarBrilho);
					menuYIQ.add(brilhoadt);
					adicionarBrilho.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							int valor = Integer.parseInt(valorBA.getText());
							BufferedImage brilhoadtv = Processamento.brilhoY(image, valor);
							adtLabel.setBounds(40,60,540,480);
							fitImage(adtLabel,brilhoadtv);
							brilhoadt.removeAll();
							brilhoadt.add(adtLabel);
							brilhoadt.updateUI();
							
						}
						
					});	
					
					
					JPanel menuYIQmt = new JPanel();
					menuYIQmt.setLayout(null);
					Border blackline7 = BorderFactory.createTitledBorder("Digite um valor para multiplicar o brilho (Pode ser float)");
					menuYIQmt.setBorder(blackline7);
					JPanel brilhomt = new JPanel();
					brilhomt.setBounds(40,60,540,480);
					JLabel mtLabel = new JLabel();
					JTextField valorBM = new JTextField(3);
					valorBM.setBounds(340,15,40,40);
					valorBM.setText("1.25");
					JButton mtBrilho = new JButton("Adicionar brilho");
					mtBrilho.setBounds(200,15,120,40);
					menuYIQmt.add(valorBM);
					menuYIQmt.add(mtBrilho);
					menuYIQmt.add(brilhomt);
					mtBrilho.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							float valor = Float.parseFloat(valorBM.getText());
							System.out.println(valor);
							BufferedImage brilhomtv = Processamento.brilhoYM(image, valor);
							mtLabel.setBounds(40,60,540,480);
							fitImage(mtLabel,brilhomtv);
							brilhomt.removeAll();
							brilhomt.add(mtLabel);
							brilhomt.updateUI();
							
						}
						
					});	
					JPanel negativoY = new JPanel();
					Border blackline8 = BorderFactory.createTitledBorder("Filtro negativo do YIQ da imagem");
					negativoY.setBorder(blackline8);
					JLabel negativoYLabel = new JLabel();
					negativoYLabel.setBounds(40,60,540,480);
					fitImage(negativoYLabel,negy);
					negativoY.add(negativoYLabel);
					
					JPanel sobre = new JPanel();
					sobre.setLocation(200,200);
					JLabel legenda = new JLabel("Criado por Allison Silva @2022.");
					JLabel legenda2 = new JLabel("Este programa foi feito para fins acadêmicos. Contato: @allisonguilherme");
				
					sobre.add(legenda);
					sobre.add(legenda2);
					legenda.setHorizontalAlignment(SwingConstants.CENTER);
					
					tab.add("Negativo RGB", negativo);
					tab.add("GrayScale por Média", bandaGray);
					tab.add("GrayScale por Banda", grayPanel);
					tab.add("Tonalidade por banda",tomPanel);
					tab.add("Binarização",biPanelMenu);
					tab.add("Brilho YIQ Aditivo", menuYIQ);
					tab.add("Brilho YIQ Multiplicativo", menuYIQmt);
					tab.add("Negativo YIQ", negativoY);
					tab.add("Sobre",sobre);
				}
					
			}
		});
		
		
		frame.getContentPane().add(tab, BorderLayout.CENTER);
		configJanela(frame);
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}	
} 
