import java.awt.Color;
import java.awt.image.BufferedImage;

public class Processamento {
	
	public static BufferedImage GbandaR (BufferedImage image) {
		int altura = image.getHeight();
		int largura = image.getWidth();
		
		BufferedImage saidaR = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		for(int linha = 0; linha < altura; linha++) {
			for(int coluna = 0; coluna < largura; coluna++) {
				int rgb = image.getRGB(coluna,linha);
				Color c = new Color(rgb);
				int red = c.getRed();
				Color bandaunicaR = new Color(red,red,red);
				
				saidaR.setRGB(coluna,linha,bandaunicaR.getRGB());
				
			}
		}
		
		return saidaR;
		
		
	}
	public static BufferedImage GbandaG (BufferedImage image) {
		int altura = image.getHeight();
		int largura = image.getWidth();
		
		BufferedImage saidaG = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		for(int linha = 0; linha < altura; linha++) {
			for(int coluna = 0; coluna < largura; coluna++) {
				int rgb = image.getRGB(coluna,linha);
				Color c = new Color(rgb);
				int green = c.getGreen();
				Color bandaunicaG = new Color(green,green,green);
				saidaG.setRGB(coluna,linha,bandaunicaG.getRGB());
				
			}
		}
		
		return saidaG;
		
		
	}
	public static BufferedImage negativo (BufferedImage image) {
		int altura = image.getHeight();
		int largura = image.getWidth();
		
		BufferedImage neg = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		
		for(int linha = 0; linha < altura; linha++) {
			for(int coluna = 0; coluna < largura; coluna++) {
				int rgb = image.getRGB(coluna,linha);
				Color c = new Color(rgb);
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				Color bandaunicaB = new Color((255 - red),(255 - green),(255 - blue));
				neg.setRGB(coluna,linha,bandaunicaB.getRGB());
				
			}
		}
		
		return neg;
	
		
	}
	public static BufferedImage bandaGray (BufferedImage image) {
		int altura = image.getHeight();
		int largura = image.getWidth();
		
		BufferedImage saidaGray = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		
		for(int linha = 0; linha < altura; linha++) {
			for(int coluna = 0; coluna < largura; coluna++) {
				int rgb = image.getRGB(coluna,linha);
				Color c = new Color(rgb);
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				int gray = (red + green + blue) / 3;
				Color bandaunicaB = new Color(gray,gray,gray);
				saidaGray.setRGB(coluna,linha,bandaunicaB.getRGB());
				
			}
		}
		
		return saidaGray;
		
		
	}

	public static BufferedImage GbandaB (BufferedImage image) {
		int altura = image.getHeight();
		int largura = image.getWidth();
		
		BufferedImage saidaB = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		
		for(int linha = 0; linha < altura; linha++) {
			for(int coluna = 0; coluna < largura; coluna++) {
				int rgb = image.getRGB(coluna,linha);
				Color c = new Color(rgb);
				int blue = c.getBlue();
				Color bandaunicaB = new Color(blue,blue,blue);
				saidaB.setRGB(coluna,linha,bandaunicaB.getRGB());
				
			}
		}
		
		return saidaB;
		
		
	}
	
	public static int tonalidade (int aumento, int pixel) {
		if (aumento + pixel < 0) {aumento = 0;}
		else if (aumento + pixel > 255) {aumento = 255;}
		else {aumento = aumento + pixel;}
		return aumento;
		
	};
	
	public static BufferedImage bandaR (BufferedImage image, int aumento) {
		int altura = image.getHeight();
		int largura = image.getWidth();
		
		BufferedImage saidaR = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		for(int linha = 0; linha < altura; linha++) {
			for(int coluna = 0; coluna < largura; coluna++) {
				int rgb = image.getRGB(coluna,linha);
				Color c = new Color(rgb);
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				int aumentoTon = tonalidade(aumento, red);
				Color bandaunicaR = new Color(aumentoTon,green,blue);
				
				saidaR.setRGB(coluna,linha,bandaunicaR.getRGB());
				
			}
		}
		
		return saidaR;
	
	
	}
	public static BufferedImage bandaG (BufferedImage image, int aumento) {
		int altura = image.getHeight();
		int largura = image.getWidth();
		
		BufferedImage saidaG = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		for(int linha = 0; linha < altura; linha++) {
			for(int coluna = 0; coluna < largura; coluna++) {
				int rgb = image.getRGB(coluna,linha);
				Color c = new Color(rgb);
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				int aumentoTon = tonalidade(aumento, green);
				Color bandaunicaG = new Color(red,aumentoTon,blue);
				saidaG.setRGB(coluna,linha,bandaunicaG.getRGB());
				
			}
		}
		
		return saidaG;
	
	
	}
	public static BufferedImage bandaB (BufferedImage image, int aumento) {
		int altura = image.getHeight();
		int largura = image.getWidth();
		
		BufferedImage saidaB = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		
		for(int linha = 0; linha < altura; linha++) {
			for(int coluna = 0; coluna < largura; coluna++) {
				int rgb = image.getRGB(coluna,linha);
				Color c = new Color(rgb);
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				int aumentoTon = tonalidade(aumento,blue);
				Color bandaunicaB = new Color(red,green,aumentoTon);
				saidaB.setRGB(coluna,linha,bandaunicaB.getRGB());
				
			}
		}
		
		return saidaB;
	
	
	}
	public static BufferedImage binarizacao (BufferedImage image, int aumento) {
		int altura = image.getHeight();
		int largura = image.getWidth();
		
		BufferedImage saidaB = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		
		for(int linha = 0; linha < altura; linha++) {
			for(int coluna = 0; coluna < largura; coluna++) {
				int rgb = image.getRGB(coluna,linha);
				Color c = new Color(rgb);
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				int valor = (red + green + blue)/3;
				int binarizar = binario(aumento,valor);
				Color bandaunicaB = new Color(binarizar,binarizar,binarizar);
				saidaB.setRGB(coluna,linha,bandaunicaB.getRGB());
				
			}
		}
		
		return saidaB;
	
	
	}
	public static int binario (int aumento, int pixel) {
		if (pixel < aumento) {aumento = 0;}
		else if (pixel >= aumento) {aumento = 255;}
		
		return aumento;
		
	}
	public static BufferedImage brilhoY (BufferedImage image, int aumento) {
			
		int altura = image.getHeight();
		int largura = image.getWidth();
		
		BufferedImage saidaB = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		
		for(int linha = 0; linha < altura; linha++) {
			for(int coluna = 0; coluna < largura; coluna++) {
				int rgb = image.getRGB(coluna,linha);
				Color c = new Color(rgb);
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				double y = 0.299 * red + 0.587 * green + 0.114 * blue;
				double i = 0.596 * red - 0.274 * green - 0.322 * blue;
				double q = 0.211 * red - 0.523 * green + 0.312 * blue;
				double aumento2 = aumento;
				double brilhoaum = aumento2+ y;
				
				double R = 1.000 * brilhoaum + 0.956 * i + 0.621 * q;
				double G = 1.000 * brilhoaum - 0.272 * i - 0.647 * q;
				double B = 1.000 * brilhoaum - 1.106 * i + 1.703 * q;
				
				int Ry = (int) R;
				int Gy = (int) G;
				int By = (int) B;
				Ry = brilhoadtregra(Ry);
				Gy = brilhoadtregra(Gy);
				By = brilhoadtregra(By);
				Color yiq = new Color(Ry,Gy,By);
				saidaB.setRGB(coluna,linha,yiq.getRGB());
								
			}
		}
		return saidaB;
		
	}
	
	public static BufferedImage brilhoYM (BufferedImage image, float aumento) {
		
		int altura = image.getHeight();
		int largura = image.getWidth();
		
		BufferedImage saidaB = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		
		for(int linha = 0; linha < altura; linha++) {
			for(int coluna = 0; coluna < largura; coluna++) {
				int rgb = image.getRGB(coluna,linha);
				Color c = new Color(rgb);
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				double y = 0.299 * red + 0.587 * green + 0.114 * blue;
				double i = 0.596 * red - 0.274 * green - 0.322 * blue;
				double q = 0.211 * red - 0.523 * green + 0.312 * blue;
				double aumento2 = aumento;
				double brilhoaum = aumento2 * y;
				
				double R = 1.000 * brilhoaum + 0.956 * i + 0.621 * q;
				double G = 1.000 * brilhoaum - 0.272 * i - 0.647 * q;
				double B = 1.000 * brilhoaum - 1.106 * i + 1.703 * q;
				
				int Ry = (int) R;
				int Gy = (int) G;
				int By = (int) B;
				Ry = brilhoadtregra(Ry);
				Gy = brilhoadtregra(Gy);
				By = brilhoadtregra(By);
				Color yiq = new Color(Ry,Gy,By);
				saidaB.setRGB(coluna,linha,yiq.getRGB());
								
			}
		}
		return saidaB;
		
	}
	public static BufferedImage negativoYIQ (BufferedImage image) {
		
		int altura = image.getHeight();
		int largura = image.getWidth();
		
		BufferedImage saidaB = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
		
		for(int linha = 0; linha < altura; linha++) {
			for(int coluna = 0; coluna < largura; coluna++) {
				int rgb = image.getRGB(coluna,linha);
				Color c = new Color(rgb);
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				double y = 0.299 * red + 0.587 * green + 0.114 * blue;
				double yn = 255 - y;
				double i = 0.596 * red - 0.274 * green - 0.322 * blue;
				double q = 0.211 * red - 0.523 * green + 0.312 * blue;
				
				double R = 1.000 * yn + 0.956 * i + 0.621 * q;
				double G = 1.000 * yn - 0.272 * i - 0.647 * q;
				double B = 1.000 * yn - 1.106 * i + 1.703 * q;
				
				int Ry = (int) R;
				int Gy = (int) G;
				int By = (int) B;
				Ry = brilhoadtregra(Ry);
				Gy = brilhoadtregra(Gy);
				By = brilhoadtregra(By);
				Color yiq = new Color(Ry,Gy,By);
				saidaB.setRGB(coluna,linha,yiq.getRGB());
								
			}
		}
		return saidaB;
		
	}
	public static int brilhoadtregra (int pixel) {
		if (pixel < 0) {pixel = 0;}
		else if ( pixel > 255) {pixel = 255;}
		return pixel;
		
	};
	
	
}
