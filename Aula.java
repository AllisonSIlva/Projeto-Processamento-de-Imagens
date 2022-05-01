import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.*;
import java.awt.*;

public class Aula extends JFrame implements ActionListener {
	
	private static final int WIDHT = 640;
	private static final int HEIGHT = 640;
	
	static JTabbedPane tab = new JTabbedPane();
	
	public static void configJanela(JFrame frame) {
		new Processamento();
		frame.setTitle("Processamento de Imagens");
		frame.setSize(WIDHT,HEIGHT);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
		
	public void conteudo() throws IOException {
		File f = new File("C:\\Users\\allis\\Documents\\mel.png");
		BufferedImage image = ImageIO.read(f);
		System.out.println("Leitura completa");
		int largura = image.getWidth();
		int altura = image.getHeight();
		BufferedImage saidaR = Processamento.GbandaR(image);
		BufferedImage saidaG = Processamento.GbandaG(image);
		BufferedImage saidaB = Processamento.GbandaB(image);
		BufferedImage saidaGray = Processamento.bandaGray(image);
		BufferedImage neg = Processamento.negativo(image);
		BufferedImage negy = Processamento.negativoYIQ(image);
		
		JFrame frame = new JFrame();
		
		JLabel originalLabel = new JLabel(new ImageIcon(image));
		JPanel original = new JPanel();
		original.add(originalLabel);
		original.setVisible(true);
		
		JLabel negativoLabel = new JLabel(new ImageIcon(neg));
		JPanel negativo = new JPanel();
		negativo.add(negativoLabel);
		negativo.setVisible(true);
		
		JLabel bandaGrayLabel = new JLabel(new ImageIcon(saidaGray));
		JPanel bandaGray = new JPanel();
		bandaGray.add(bandaGrayLabel);
		bandaGray.setVisible(true);
		
		JLabel grayRedLabel = new JLabel(new ImageIcon(saidaR));
		JLabel grayGreenLabel = new JLabel(new ImageIcon(saidaG));
		JLabel grayBlueLabel = new JLabel(new ImageIcon(saidaB));
		
		JPanel grayRedPanel = new JPanel();
		JPanel grayPanel = new JPanel();
		JButton bred = new JButton("Banda Red");
		JButton bgreen = new JButton("Banda Green");
		JButton bblue = new JButton("Banda Blue");
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

		JLabel tomRedLabel = new JLabel();
		JLabel tomGreenLabel = new JLabel();
		JLabel tomBlueLabel = new JLabel();
		JPanel tomRedPanel = new JPanel();
		JPanel tomPanel = new JPanel();
		JButton bredT = new JButton("Banda Red");
		JButton bgreenT = new JButton("Banda Green");
		JButton bblueT = new JButton("Banda Blue");
		JTextField aumento = new JTextField(3);
		aumento.setText("0");
		
		tomPanel.add(bredT);
		tomPanel.add(bgreenT);
		tomPanel.add(bblueT);
		tomPanel.add(tomRedPanel);
		tomPanel.add(aumento);
		
		bredT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int Aumento = Integer.parseInt(aumento.getText());
				BufferedImage saidaRT = Processamento.bandaR(image, Aumento);
				tomRedLabel.setIcon(new ImageIcon(saidaRT));
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
				tomGreenLabel.setIcon(new ImageIcon(saidaGT));
				tomRedPanel.removeAll();
				tomRedPanel.add(tomGreenLabel);
				tomRedPanel.updateUI();
			}});
		bblueT.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int Aumento = Integer.parseInt(aumento.getText());
				BufferedImage saidaBT = Processamento.bandaB(image, Aumento);
				tomBlueLabel.setIcon(new ImageIcon(saidaBT));
				tomRedPanel.removeAll();
				tomRedPanel.add(tomBlueLabel);
				tomRedPanel.updateUI();
			}
			
		});
		
		JLabel biLabel = new JLabel();
		JPanel biPanelMenu = new JPanel();
		JPanel biPanel = new JPanel();
		JTextField biValor = new JTextField(3);
		JButton biBotao = new JButton("Binarizar");
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
				biLabel.setIcon(new ImageIcon(saidaBi));
				biPanel.removeAll();
				biPanel.add(biLabel);
				biPanel.updateUI();
				
			}
			
		});
		
		JPanel brilhoadt = new JPanel();
		JPanel menuYIQ = new JPanel();
		JLabel adtLabel = new JLabel();
		JTextField valorBA = new JTextField(3);
		JButton adicionarBrilho = new JButton("Adicionar brilho");
		menuYIQ.add(valorBA);
		menuYIQ.add(adicionarBrilho);
		menuYIQ.add(brilhoadt);
		adicionarBrilho.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int valor = Integer.parseInt(valorBA.getText());
				BufferedImage brilhoadtv = Processamento.brilhoY(image, valor);
				adtLabel.setIcon(new ImageIcon(brilhoadtv));
				brilhoadt.removeAll();
				brilhoadt.add(adtLabel);
				brilhoadt.updateUI();
				
			}
			
		});	
		
		JPanel brilhomt = new JPanel();
		JPanel menuYIQmt = new JPanel();
		JLabel mtLabel = new JLabel();
		JTextField valorBM = new JTextField(3);
		JButton mtBrilho = new JButton("Adicionar brilho");
		menuYIQmt.add(valorBM);
		menuYIQmt.add(mtBrilho);
		menuYIQmt.add(brilhomt);
		mtBrilho.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				float valor = Float.parseFloat(valorBM.getText());
				System.out.println(valor);
				BufferedImage brilhomtv = Processamento.brilhoYM(image, valor);
				mtLabel.setIcon(new ImageIcon(brilhomtv));
				brilhomt.removeAll();
				brilhomt.add(mtLabel);
				brilhomt.updateUI();
				
			}
			
		});	
		JPanel negativoY = new JPanel();
		JLabel negativoYLabel = new JLabel(new ImageIcon(negy));
		negativoY.add(negativoYLabel);
		
		tab.add("Original", original);
		tab.add("Negativo RGB", negativo);
		tab.add("GrayScale por Média", bandaGray);
		tab.add("GrayScale por Banda", grayPanel);
		tab.add("Tonalidade por banda",tomPanel);
		tab.add("Binarização",biPanelMenu);
		tab.add("Brilho YIQ Aditivo", menuYIQ);
		tab.add("Brilho YIQ Multiplicativo", menuYIQmt);
		tab.add("Negativo YIQ", negativoY);
		frame.getContentPane().add(tab, BorderLayout.CENTER);
		configJanela(frame);
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}	
} 
