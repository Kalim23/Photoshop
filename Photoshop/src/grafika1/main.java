package grafika1;

import java.awt.Color;
import java.awt.EventQueue;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JMenu;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.JTextField;
import java.awt.TextField;
import java.awt.Font;



public class main {

	private JFrame Frame1;
	public static String path, vs;
	public static BufferedImage obraz, lustro, tmp, obraz2;
	public static JLabel edytowany;
	static int outline = 3;
	
	
	public static int[][] Test = {
			{0, 1, 1, 1, 0},
			{1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1},
			{1, 1, 1, 1, 1},
			{0, 1, 1, 1, 0}
	};
	public static int[][] Test2 = {
			{1, 0, -1},
			{1, 0, -1},
			{1, 0, -1}
	};
	
	
	
	public static int[][] Gauss = {
			{1, 2, 1},
			{2, 4, 2},
			{1, 2, 1}
	};
	public static int[][] dolnoprzep = {
			{1, 1, 1},
			{1, 1, 1},
			{1, 1, 1}
	};
	public static int[][] gornoprzep = {
			{-1, -1, -1},
			{-1, 9, -1},
			{-1, -1, -1}
	};
	public static int[][] poziom = { //filtr Sobel'a - wykrywa krawędzie poziome
			{1, 2, 1},
			{0, 0, 0},
			{-1, -2, -1}
	};
	public static int[][] pion = { //filtr Sobel'a - wykrywa krawędzie pionowe
			{1, 0, -1},
			{2, 0, -2},
			{1, 0, -1}
	};
	public static int[][] ukos = { //filtr wykrywajaca krawedzie ukosne: poludniowy-zachod
			{1, -1, -1},
			{1, -2, -1},
			{1,  1,  1}
	};
	public static int[][] Laplace = {
			{0, -1, 0},
			{-1, 4, -1},
			{0, -1, 0}
	};
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;
	private JTextField textField_18;
	private JTextField textField_19;
	private JTextField textField_20;
	private JTextField textField_21;
	private JTextField textField_22;
	private JTextField textField_23;
	private JTextField textField_24;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main okno = new main();
					okno.Frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public main() {
		initialize();
	}
	
	public static void swap(Object a, Object b) {
		Object t = a;
		a = b;
		b = t;
	}
	
	public Color randomC() {
		Random random = new Random();
		int red = random.nextInt(255);
		int green = random.nextInt(255);
		int blue = random.nextInt(255);
		return new Color(red,green,blue);
	}
	
	public static void Filtracja(int maska[][]) {
		
		int r, g, b;
		int nowyR, nowyG, nowyB;
		int w = obraz.getWidth(), h = obraz.getHeight();
		
		
		int nowyP = 0;
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++) {
				nowyP += maska[i][j];
			}
		if (nowyP < 1) nowyP = 1;
		int outP = 1;
		for (int i=outP; i<(w-outP);i++)
			for (int j=outP; j<(h-outP); j++) {
				nowyR = 0; nowyG = 0; nowyB = 0;
				for (int k=-1; k<2; k++)
					for (int l=-1; l<2; l++) {
						Color pixels = new Color(obraz.getRGB(i+l, j+k));
						r = pixels.getRed();
						g = pixels.getGreen();
						b = pixels.getBlue();
						nowyR+=(r*maska[l+1][k+1]);
						nowyG+=(g*maska[l+1][k+1]);
						nowyB+=(b*maska[l+1][k+1]);
					} 
				nowyR/=nowyP; nowyG/=nowyP; nowyB/=nowyP;
				
				if(nowyR<0) 
					nowyR=0;
				if(nowyR>255) 
					nowyR=255;
				if(nowyG<0) 
					nowyG=0;
				if(nowyG>255) 
					nowyG=255;
				if(nowyB<0) 
					nowyB=0;
				if(nowyB>255) 
					nowyB=255;
				
				tmp.setRGB(i, j, new Color(nowyR,nowyG,nowyB).getRGB());
			}
		
	}
	public static int[] BubbleSort(int[] values) {
		int[] array = values;
	    int swap;
	    for (int i=0; i<8; i++)
	        for (int j=0; j<8; j++)
	            if (array[j] > array[j+1]){
	                swap = array[j];
	                array[j] = array[j+1];
	                array[j+1] = swap;
	            }
		return array;
	}
	public static int median(int[][] values) {
		int temp = 0;
		int[] list = new int[9]; 
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++) {
				list[temp++] = values[i][j];
			}
		BubbleSort(list);
		int m = list.length / 2;
		if (list.length % 2 == 1) {
			return list[m];
		} else return ((list[m-1] + list[m]) / 2);
	}
	public static int findMinMax(int[][] values, boolean min) {
		int temp = 0;
		int[] list = new int[9]; 
		for (int i=0; i<3; i++)
			for (int j=0; j<3; j++) {
				list[temp++] = values[i][j];
			}
		BubbleSort(list);
		if (min == true) {
			return list[0];
		} else return list[8];
	}
	
public static void Filtracja_statystyczna() {
		
	int[][] r = new int [3][3];
	int[][] g = new int [3][3];
	int[][] b = new int [3][3];
	
	int w = obraz.getWidth(), h = obraz.getHeight();
	
	{
		for (int i=outline; i<(w-outline); i++)
			for (int j=outline; j<(h-outline); j++) 
			{
				int nr = 0, ng = 0, nb = 0;
				for (int n=-1; n<2; n++)
					for (int z=-1; z<2; z++)
					{
						Color pixels = new Color(obraz.getRGB(i+n, j+z));
						r[n+1][z+1] = pixels.getRed();
						g[n+1][z+1] = pixels.getGreen();
						b[n+1][z+1] = pixels.getBlue();
					}
				nr = median(r);
				ng = median(g);
				nb = median(b);
				if(nr<0) nr=-nr;
				if(ng<0) ng=-ng;
				if(nb<0) nb=-nb;
				if(nr>255) nr=255;
				if(ng>255) ng=255;
				if(nb>255) nb=255;
				tmp.setRGB(i, j, new Color(nr,ng,nb).getRGB());
			}
		edytowany.setIcon(new ImageIcon (tmp.getScaledInstance(299, 321, java.awt.Image.SCALE_SMOOTH)));
				
	}
}
public static void Min() {
	
	int[][] r = new int [3][3];
	int[][] g = new int [3][3];
	int[][] b = new int [3][3];
	
	int w = obraz.getWidth(), h = obraz.getHeight();
	
	{
		for (int i=outline; i<(w-outline); i++)
			for (int j=outline; j<(h-outline); j++) 
			{
				int nr = 0, ng = 0, nb = 0;
				for (int n=-1; n<2; n++)
					for (int z=-1; z<2; z++)
					{
						Color pixels = new Color(obraz.getRGB(i+n, j+z));
						r[n+1][z+1] = pixels.getRed();
						g[n+1][z+1] = pixels.getGreen();
						b[n+1][z+1] = pixels.getBlue();
					}
				nr = findMinMax(r,true);
				ng = findMinMax(g,true);
				nb = findMinMax(b,true);
				if(nr<0) nr=-nr;
				if(ng<0) ng=-ng;
				if(nb<0) nb=-nb;
				if(nr>255) nr=255;
				if(ng>255) ng=255;
				if(nb>255) nb=255;
				tmp.setRGB(i, j, new Color(nr,ng,nb).getRGB());
			}
		edytowany.setIcon(new ImageIcon (tmp.getScaledInstance(299, 321, java.awt.Image.SCALE_SMOOTH)));
				
	}
}
public static void Max() {
	
	int[][] r = new int [3][3];
	int[][] g = new int [3][3];
	int[][] b = new int [3][3];

	int w = obraz.getWidth(), h = obraz.getHeight();
	
	{
		for (int i=outline; i<(w-outline); i++)
			for (int j=outline; j<(h-outline); j++) 
			{
				int nr = 0, ng = 0, nb = 0;
				for (int n=-1; n<2; n++)
					for (int z=-1; z<2; z++)
					{
						Color pixels = new Color(obraz.getRGB(i+n, j+z));
						r[n+1][z+1] = pixels.getRed();
						g[n+1][z+1] = pixels.getGreen();
						b[n+1][z+1] = pixels.getBlue();
					}
				nr = findMinMax(r,false);
				ng = findMinMax(g,false);
				nb = findMinMax(b,false);
				if(nr<0) nr=-nr;
				if(ng<0) ng=-ng;
				if(nb<0) nb=-nb;
				if(nr>255) nr=255;
				if(ng>255) ng=255;
				if(nb>255) nb=255;
				tmp.setRGB(i, j, new Color(nr,ng,nb).getRGB());
			}
		edytowany.setIcon(new ImageIcon (tmp.getScaledInstance(299, 321, java.awt.Image.SCALE_SMOOTH)));
				
	}
}
	
	private void initialize() {
		Frame1 = new JFrame();
		Frame1.setResizable(false);
		Frame1.setTitle("Photoshop");
		Frame1.getContentPane().setBackground(Color.WHITE);
		Frame1.getContentPane().setLayout(null);
		
		JLabel oryginal = new JLabel("");
		oryginal.setHorizontalAlignment(SwingConstants.CENTER);
		oryginal.setBounds(0, 0, 311, 350);
		Frame1.getContentPane().add(oryginal);
		oryginal.setBackground(Color.WHITE);
		oryginal.setOpaque(true);
		
		edytowany= new JLabel("");
		edytowany.setHorizontalAlignment(SwingConstants.CENTER);
		edytowany.setBounds(309, 0, 315, 350);
		Frame1.getContentPane().add(edytowany);
		Frame1.setBounds(100, 100, 828, 402);
		Frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		edytowany.setBackground(Color.WHITE);
		edytowany.setOpaque(true);
		
		textField = new JTextField();
		textField.setBackground(Color.GREEN);
		textField.setBounds(637, 30, 21, 20);
		Frame1.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText("1");
		
		textField_1 = new JTextField();
		textField_1.setBackground(Color.GREEN);
		textField_1.setColumns(10);
		textField_1.setBounds(668, 30, 21, 20);
		Frame1.getContentPane().add(textField_1);
		textField_1.setText("1");
		
		textField_2 = new JTextField();
		textField_2.setBackground(Color.GREEN);
		textField_2.setColumns(10);
		textField_2.setBounds(699, 30, 21, 20);
		Frame1.getContentPane().add(textField_2);
		textField_2.setText("1");
		
		textField_3 = new JTextField();
		textField_3.setBackground(Color.GREEN);
		textField_3.setColumns(10);
		textField_3.setBounds(637, 61, 21, 20);
		Frame1.getContentPane().add(textField_3);
		textField_3.setText("1");
		
		textField_4 = new JTextField();
		textField_4.setBackground(Color.GREEN);
		textField_4.setColumns(10);
		textField_4.setBounds(668, 61, 21, 20);
		Frame1.getContentPane().add(textField_4);
		textField_4.setText("1");
		
		textField_5 = new JTextField();
		textField_5.setBackground(Color.GREEN);
		textField_5.setColumns(10);
		textField_5.setBounds(699, 61, 21, 20);
		Frame1.getContentPane().add(textField_5);
		textField_5.setText("1");
		
		textField_6 = new JTextField();
		textField_6.setBackground(Color.GREEN);
		textField_6.setColumns(10);
		textField_6.setBounds(637, 87, 21, 20);
		Frame1.getContentPane().add(textField_6);
		textField_6.setText("1");
		
		textField_7 = new JTextField();
		textField_7.setBackground(Color.GREEN);
		textField_7.setColumns(10);
		textField_7.setBounds(668, 87, 21, 20);
		Frame1.getContentPane().add(textField_7);
		textField_7.setText("1");
		
		textField_8 = new JTextField();
		textField_8.setBackground(Color.GREEN);
		textField_8.setColumns(10);
		textField_8.setBounds(699, 87, 21, 20);
		Frame1.getContentPane().add(textField_8);
		textField_8.setText("1");
		
		JLabel wartosci = new JLabel("3x3");
		wartosci.setFont(new Font("Tahoma", Font.BOLD, 12));
		wartosci.setBounds(634, 5, 45, 14);
		Frame1.getContentPane().add(wartosci);
		
		textField_9 = new JTextField();
		textField_9.setBackground(Color.CYAN);
		textField_9.setText("1");
		textField_9.setColumns(10);
		textField_9.setBounds(730, 30, 21, 20);
		Frame1.getContentPane().add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setBackground(Color.CYAN);
		textField_10.setText("1");
		textField_10.setColumns(10);
		textField_10.setBounds(761, 30, 21, 20);
		Frame1.getContentPane().add(textField_10);
		
		JLabel lblx = new JLabel("5x5");
		lblx.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblx.setBounds(730, 5, 45, 14);
		Frame1.getContentPane().add(lblx);
		
		textField_11 = new JTextField();
		textField_11.setText("1");
		textField_11.setColumns(10);
		textField_11.setBackground(Color.CYAN);
		textField_11.setBounds(730, 61, 21, 20);
		Frame1.getContentPane().add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setText("1");
		textField_12.setColumns(10);
		textField_12.setBackground(Color.CYAN);
		textField_12.setBounds(761, 61, 21, 20);
		Frame1.getContentPane().add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setText("1");
		textField_13.setColumns(10);
		textField_13.setBackground(Color.CYAN);
		textField_13.setBounds(730, 87, 21, 20);
		Frame1.getContentPane().add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setText("1");
		textField_14.setColumns(10);
		textField_14.setBackground(Color.CYAN);
		textField_14.setBounds(761, 87, 21, 20);
		Frame1.getContentPane().add(textField_14);
		
		textField_15 = new JTextField();
		textField_15.setText("1");
		textField_15.setColumns(10);
		textField_15.setBackground(Color.CYAN);
		textField_15.setBounds(637, 118, 21, 20);
		Frame1.getContentPane().add(textField_15);
		
		textField_16 = new JTextField();
		textField_16.setText("1");
		textField_16.setColumns(10);
		textField_16.setBackground(Color.CYAN);
		textField_16.setBounds(668, 118, 21, 20);
		Frame1.getContentPane().add(textField_16);
		
		textField_17 = new JTextField();
		textField_17.setText("1");
		textField_17.setColumns(10);
		textField_17.setBackground(Color.CYAN);
		textField_17.setBounds(699, 118, 21, 20);
		Frame1.getContentPane().add(textField_17);
		
		textField_18 = new JTextField();
		textField_18.setText("1");
		textField_18.setColumns(10);
		textField_18.setBackground(Color.CYAN);
		textField_18.setBounds(730, 118, 21, 20);
		Frame1.getContentPane().add(textField_18);
		
		textField_19 = new JTextField();
		textField_19.setText("1");
		textField_19.setColumns(10);
		textField_19.setBackground(Color.CYAN);
		textField_19.setBounds(761, 118, 21, 20);
		Frame1.getContentPane().add(textField_19);
		
		textField_20 = new JTextField();
		textField_20.setText("1");
		textField_20.setColumns(10);
		textField_20.setBackground(Color.CYAN);
		textField_20.setBounds(637, 146, 21, 20);
		Frame1.getContentPane().add(textField_20);
		
		textField_21 = new JTextField();
		textField_21.setText("1");
		textField_21.setColumns(10);
		textField_21.setBackground(Color.CYAN);
		textField_21.setBounds(668, 146, 21, 20);
		Frame1.getContentPane().add(textField_21);
		
		textField_22 = new JTextField();
		textField_22.setText("1");
		textField_22.setColumns(10);
		textField_22.setBackground(Color.CYAN);
		textField_22.setBounds(699, 146, 21, 20);
		Frame1.getContentPane().add(textField_22);
		
		textField_23 = new JTextField();
		textField_23.setText("1");
		textField_23.setColumns(10);
		textField_23.setBackground(Color.CYAN);
		textField_23.setBounds(730, 146, 21, 20);
		Frame1.getContentPane().add(textField_23);
		
		textField_24 = new JTextField();
		textField_24.setText("1");
		textField_24.setColumns(10);
		textField_24.setBackground(Color.CYAN);
		textField_24.setBounds(761, 146, 21, 20);
		Frame1.getContentPane().add(textField_24);
		
		JMenuBar menu = new JMenuBar();
		Frame1.setJMenuBar(menu);
		
		JMenu File = new JMenu("Plik");
		menu.add(File);
		
		JMenu effects = new JMenu("Efekty");

		JMenu Filtry = new JMenu("Filtry");
		
		JMenu Compare = new JMenu("Porownanie");
		
		

		JMenuItem Import_o = new JMenuItem("Obraz");
		Import_o.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));
				int res = fc.showOpenDialog(null);
				if (res == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					path = file.getAbsolutePath();
					ImageIcon image = new ImageIcon(path);
					oryginal.setIcon(new ImageIcon (image.getImage().getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
				
				}
				
			}
		});
		File.add(Import_o);
		
		
		
		menu.add(effects);
		
		JMenuItem cRed = new JMenuItem("Red");
		cRed.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				obraz = null;
				try {
					Color kolor;
					int w,h, piksel,r,g,b;
				
					obraz = ImageIO.read(new File(path));
					h = obraz.getHeight();
					w = obraz.getWidth();
					for (int i=0; i<w; i++) {
						for (int j=0; j<h; j++) {
							
							piksel = obraz.getRGB(i, j);
							kolor = new Color(piksel,true);
							r = kolor.getRed();
							g = kolor.getGreen();
							b = kolor.getBlue();
							
							g = 0;
							b = 0;
							kolor = new Color(r,g,b);
							piksel = kolor.getRGB();
							
							obraz.setRGB(i, j, piksel);
						}
					}
					edytowany.setIcon(new ImageIcon (obraz.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
				
				} catch (IOException ex){
					ex.printStackTrace();
				}
			}
		});
		effects.add(cRed);
		
		JMenuItem cGreen = new JMenuItem("Green");
		cGreen.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				obraz = null;
				try {
					Color kolor;
					int w,h, piksel,r,g,b;
				
					obraz = ImageIO.read(new File(path));
					h = obraz.getHeight();
					w = obraz.getWidth();
					for (int i=0; i<w; i++) {
						for (int j=0; j<h; j++) {
							
							piksel = obraz.getRGB(i, j);
							kolor = new Color(piksel,true);
							r = kolor.getRed();
							g = kolor.getGreen();
							b = kolor.getBlue();
							
							r = 0;
							b = 0;
							kolor = new Color(r,g,b);
							piksel = kolor.getRGB();
							
							obraz.setRGB(i, j, piksel);
						}
					}
					edytowany.setIcon(new ImageIcon (obraz.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));				
				} catch (IOException ex){
					ex.printStackTrace();
				}
			}
		});
		effects.add(cGreen);
		
		JMenuItem cBlue = new JMenuItem("Blue");
		cBlue.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				obraz = null;
				try {
					Color kolor;
					int w,h, piksel,r,g,b;
				
					obraz = ImageIO.read(new File(path));
					h = obraz.getHeight();
					w = obraz.getWidth();
					for (int i=0; i<w; i++) {
						for (int j=0; j<h; j++) {
							
							piksel = obraz.getRGB(i, j);
							kolor = new Color(piksel,true);
							r = kolor.getRed();
							g = kolor.getGreen();
							b = kolor.getBlue();
							
							g = 0;
							r = 0;
							kolor = new Color(r,g,b);
							piksel = kolor.getRGB();
							
							obraz.setRGB(i, j, piksel);
						}
					}
					edytowany.setIcon(new ImageIcon (obraz.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));				
				} catch (IOException ex){
					ex.printStackTrace();
				}
			}
		});
		effects.add(cBlue);
		
		JMenuItem Generator = new JMenuItem("Generuj");
		Generator.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				BufferedImage generate = new BufferedImage(edytowany.getWidth(), edytowany.getHeight(), BufferedImage.TYPE_INT_RGB);
				int RC = randomC().getRGB();
				for (int i=0; i < (int) 149.5; i++) {
					for (int j=0; j < (int) 160.5; j++) {
						generate.setRGB(i, j, RC);
					}
				}RC = randomC().getRGB();
				for (int i= 0; i < (int) 149.5; i++) {
					for (int j= (int) 160.5; j < 321; j++) {
						generate.setRGB(i, j, RC);
					}
				}RC = randomC().getRGB();
				for (int i= (int) 149.5; i < 299; i++) {
					for (int j=0; j < (int) 160.5; j++) {
						generate.setRGB(i, j, RC);
					}
				}RC = randomC().getRGB();
				for (int i= (int) 149.5; i < 299; i++) {
					for (int j=(int) 160.5; j < 321; j++) {
						generate.setRGB(i, j, RC);
					}
				}RC = randomC().getRGB();
				for (int i= 0; i < 149.5; i++) {
					for (int j=0; j < i ; j++) {
						generate.setRGB(i, j, RC);
					}
				}RC = randomC().getRGB();
				for (int i= (int) 149.5; i < 299; i++) {
					for (int j=(int) 160.5; j < i ; j++) {
						generate.setRGB(i, j, RC);
					}
				}
				obraz = generate;
				edytowany.setIcon(new ImageIcon (generate.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
				
			}
		});
		effects.add(Generator);
		
		JMenuItem Skala_szarosci = new JMenuItem("Grayscale");
		Skala_szarosci.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				obraz = null;
				try {
					obraz = ImageIO.read(new File(path));
					for (int i=0; i<obraz.getWidth(); i++) {
						for (int j=0; j<obraz.getHeight(); j++) {
							Color color = new Color(obraz.getRGB(i, j));
							int r = color.getRed();
							int g = color.getGreen();
							int b = color.getBlue();
							
							int gray = (r+g+b)/3;
		                    Color newColor = new Color(gray,gray,gray);
		                    obraz.setRGB(i, j, newColor.getRGB());
							
						
						}
					}
					edytowany.setIcon(new ImageIcon (obraz.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
					
				} catch (IOException exGray){
					exGray.printStackTrace();
				}
			}
		});
		effects.add(Skala_szarosci);
		
		JMenuItem tresh = new JMenuItem("Binaryzacja");
		tresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JFrame suwak = new JFrame();
				suwak.setSize(290, 105);
			
				suwak.setVisible(true);
				suwak.setTitle("Binaryzacja");
				JPanel sliderWrapper = new JPanel();
				suwak.getContentPane().add(sliderWrapper);
				JSlider slider = new JSlider();
				slider.setSize(225, 50);
				sliderWrapper.setLayout(null);
				sliderWrapper.setBackground(Color.white);
				slider.setLocation(10, 10);
				sliderWrapper.add(slider);
				slider.setMinimum(0);
				slider.setMaximum(255);
				JLabel sliderLabel = new JLabel(Integer.toString(slider.getValue()));
				sliderLabel.setBounds(225 + 20, -18, 100, 100);
				sliderWrapper.add(sliderLabel);
				sliderLabel.setForeground(Color.white);
				
				slider.addChangeListener(new ChangeListener() {
					int value;
		            Color color;
		            
			        @Override
			        public void stateChanged(ChangeEvent ce) {
			        	value = ((JSlider) ce.getSource()).getValue();
			            sliderLabel.setText(String.valueOf(value));
			            
			            try {
							obraz = ImageIO.read(new File(path));
							for (int i=0; i<obraz.getWidth(); i++) {
								for (int j=0; j<obraz.getHeight(); j++) {
									color = new Color(obraz.getRGB(i, j));
				                    if ((color.getRed()+color.getGreen()+color.getBlue())/3 < value) {
				                    	obraz.setRGB(i, j, Color.black.getRGB());
				                    } else {
				                    	obraz.setRGB(i, j, Color.white.getRGB());
				                    }
								}
							}
							
						} catch (IOException ex){
							ex.printStackTrace();
						}
			            
			            edytowany.setIcon(new ImageIcon (obraz.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
			            
			            
			        }
			        
			        
			        
			    });
				
				
			}
		});
		effects.add(tresh);
		
		JMenuItem przesun = new JMenuItem("Przesun");
		przesun.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JFrame win = new JFrame();
				win.setSize(330, 160);
				win.setResizable(false);
				win.setVisible(true);
				win.setTitle("przesun obraz");
				Panel_Przesuwanie wrapper = new Panel_Przesuwanie();
				win.setContentPane(wrapper);
				

				wrapper.ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						wrapper.a = Integer.parseInt(wrapper.textfield.getText());
						wrapper.b = Integer.parseInt(wrapper.textfield2.getText());
						int a = wrapper.a;
						int b = wrapper.b;
						try {
							obraz = ImageIO.read(new File(path));
							tmp = new BufferedImage(obraz.getWidth(), obraz.getHeight(), BufferedImage.TYPE_INT_RGB);
							
							if ((a>=0) && (b>=0)) {
								for (int x=0; x<obraz.getWidth()-a; x++) {
									for (int y=b; y<obraz.getHeight(); y++) {
										Color pixels = new Color(obraz.getRGB(x,y));
										tmp.setRGB(x+a, y-b, pixels.getRGB());
									}
								}
							} else if ((a<=0) && (b<=0)) {
								a = -(a);
								b = -(b);
								for (int x=a; x<obraz.getWidth(); x++) {
									for (int y=0; y<obraz.getHeight()-b; y++) {
										Color pixels = new Color(obraz.getRGB(x,y));
										tmp.setRGB(x-a, y+b, pixels.getRGB());
									}
								}
							} else if ((a<0)&&(b>0)) {
								a = -(a);
								for (int x=a; x<obraz.getWidth(); x++) {
									for (int y=b; y<obraz.getHeight(); y++) {
										Color pixels = new Color(obraz.getRGB(x,y));
										tmp.setRGB(x-a, y-b, pixels.getRGB());
									}
								}
							} else {
								b = -(b);
								for (int x=0; x<obraz.getWidth()-a; x++) {
									for (int y=0; y<obraz.getHeight()-a; y++) {
										Color pixels = new Color(obraz.getRGB(x,y));
										tmp.setRGB(x+a, y+a, pixels.getRGB());
									}
								}
							}
							
							

							
						} catch (IOException ex){
							ex.printStackTrace();
						}
						edytowany.setIcon(new ImageIcon (tmp.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
			          
					}
				});
			}
		});
		effects.add(przesun);
		
		JMenuItem Multi = new JMenuItem("Multi-progowanie");
		effects.add(Multi);
		Multi.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JFrame multiwin = new JFrame();
				multiwin.setTitle("Multi-progowanie");
				multiwin.setVisible(true);
				Panel_multiprogowanie wrapper = new Panel_multiprogowanie();
				multiwin.setSize(304,160);
				multiwin.setResizable(false);
				multiwin.setContentPane(wrapper);
				
				wrapper.ok.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Color color;
						int c;
						try {
							obraz = ImageIO.read(new File(path));
							for (int i=0; i<obraz.getWidth(); i++) {
								for (int j=0; j<obraz.getHeight(); j++) {
									color = new Color(obraz.getRGB(i, j));
									c = (color.getRed()+color.getGreen()+color.getBlue())/3;
				                    if (wrapper.s1 > wrapper.s2) {
				                    	swap(wrapper.s1, wrapper.s2);
				                    	if ((c >= wrapper.s1) && (c <= wrapper.s2)) {
				                    		obraz.setRGB(i, j, Color.white.getRGB());
				                    	} else {
				                    		obraz.setRGB(i, j, Color.black.getRGB());
				                    	}
				                    } else {
				                    	if ((c >= wrapper.s1) && (c <= wrapper.s2)) {
				                    		obraz.setRGB(i, j, Color.white.getRGB());
				                    	} else {
				                    		obraz.setRGB(i, j, Color.black.getRGB());
				                    	}
				                    }
				                    
								}
							}
							
						} catch (IOException ex){
							ex.printStackTrace();
						}
			            
			            edytowany.setIcon(new ImageIcon (obraz.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
			            
					}
				});
			}
		});

		JMenuItem odbicie = new JMenuItem("Odbicie");
		odbicie.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					obraz = ImageIO.read(new File(path));
					lustro = new BufferedImage(obraz.getWidth(), obraz.getHeight(), BufferedImage.TYPE_INT_RGB);
					for (int i=0; i<obraz.getHeight(); i++) {
						for (int j=0; j<obraz.getWidth(); j++) {
							int temp = obraz.getRGB(j, i);
							lustro.setRGB((obraz.getWidth()-1)-j, i, temp);
						}
					}
				} catch (IOException ex){
					ex.printStackTrace();
				}
				edytowany.setIcon(new ImageIcon (lustro.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
	            
			}
		});
		effects.add(odbicie);
		
		
		menu.add(Filtry);
		
		JMenuItem dolno = new JMenuItem("Dolno-przepustowy");
		dolno.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
		
				
				try {
					obraz = ImageIO.read(new File(path));
					int w = obraz.getWidth(), h = obraz.getHeight();
					tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);


					Filtracja(dolnoprzep);
		
						
				} catch (IOException ex){
					ex.printStackTrace();
				}
				edytowany.setIcon(new ImageIcon (tmp.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
				
	
		}
					
			
		});
		Filtry.add(dolno);
		
		JMenuItem gorno = new JMenuItem("Góno przepustowy");
		gorno.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					obraz = ImageIO.read(new File(path));
					int w = obraz.getWidth(), h = obraz.getHeight();
					tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);


					Filtracja(gornoprzep);
		
						
				} catch (IOException ex){
					ex.printStackTrace();
				}
				edytowany.setIcon(new ImageIcon (tmp.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
			}
		});
		Filtry.add(gorno);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Gaussa");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					obraz = ImageIO.read(new File(path));
					int w = obraz.getWidth(), h = obraz.getHeight();
					tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);


					Filtracja(Gauss);
		
						
				} catch (IOException ex){
					ex.printStackTrace();
				}
				edytowany.setIcon(new ImageIcon (tmp.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
			}
		});
		Filtry.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("wykrywający krawędzie poziome");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					obraz = ImageIO.read(new File(path));
					int w = obraz.getWidth(), h = obraz.getHeight();
					tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);


					Filtracja(poziom);
		
						
				} catch (IOException ex){
					ex.printStackTrace();
				}
				edytowany.setIcon(new ImageIcon (tmp.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
			}
		});
		Filtry.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("wykrywający krawędzie pionowe");
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					obraz = ImageIO.read(new File(path));
					int w = obraz.getWidth(), h = obraz.getHeight();
					tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);


					Filtracja(pion);
		
						
				} catch (IOException ex){
					ex.printStackTrace();
				}
				edytowany.setIcon(new ImageIcon (tmp.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
			}
		});
		Filtry.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("wykrywający krawędzie ukośne");
		mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					obraz = ImageIO.read(new File(path));
					int w = obraz.getWidth(), h = obraz.getHeight();
					tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);


					Filtracja(ukos);
		
						
				} catch (IOException ex){
					ex.printStackTrace();
				}
				edytowany.setIcon(new ImageIcon (tmp.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
			}
		});
		Filtry.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Laplace'a");
		mntmNewMenuItem_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					obraz = ImageIO.read(new File(path));
					int w = obraz.getWidth(), h = obraz.getHeight();
					tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);


					Filtracja(Laplace);
		
						
				} catch (IOException ex){
					ex.printStackTrace();
				}
				edytowany.setIcon(new ImageIcon (tmp.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
			}
		});
		Filtry.add(mntmNewMenuItem_4);
		
		
		menu.add(Compare);
		
		JMenu mnNewMenu = new JMenu("Filtracja wlasna");
		menu.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("maska 3x3");
		mntmNewMenuItem_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				int p1,p2,p3,p4,p5,p6,p7,p8,p9;
				
				p1 = Integer.parseInt(textField.getText());
				p2 = Integer.parseInt(textField_1.getText());
				p3 = Integer.parseInt(textField_2.getText());
				p4 = Integer.parseInt(textField_3.getText());
				p5 = Integer.parseInt(textField_4.getText());
				p6 = Integer.parseInt(textField_5.getText());
				p7 = Integer.parseInt(textField_6.getText());
				p8 = Integer.parseInt(textField_7.getText());
				p9 = Integer.parseInt(textField_8.getText());
				
				
				int[][] wlasny = {
						{p1, p2, p3},
						{p4, p5, p6},
						{p7, p8, p9}
				};
				
				try {
					obraz = ImageIO.read(new File(path));
					int w = obraz.getWidth(), h = obraz.getHeight();
					tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);


					Filtracja(wlasny);
		
						
				} catch (IOException ex){
					ex.printStackTrace();
				}
				edytowany.setIcon(new ImageIcon (tmp.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
				

				
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("maska  5x5");
		mntmNewMenuItem_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				int p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13,p14,p15,p16,p17,p18,p,p19,p20,p21,p22,p23,p24,p25;
				
				p1 = Integer.parseInt(textField.getText());
				p2 = Integer.parseInt(textField_1.getText());
				p3 = Integer.parseInt(textField_2.getText());
				p4 = Integer.parseInt(textField_9.getText());
				p5 = Integer.parseInt(textField_10.getText());
				p6 = Integer.parseInt(textField_5.getText());
				p7 = Integer.parseInt(textField_6.getText());
				p8 = Integer.parseInt(textField_7.getText());
				p9 = Integer.parseInt(textField_11.getText());
				p10 = Integer.parseInt(textField_12.getText());
				p11 = Integer.parseInt(textField_10.getText());
				p12 = Integer.parseInt(textField_11.getText());
				p13 = Integer.parseInt(textField_13.getText());
				p14 = Integer.parseInt(textField_13.getText());
				p15 = Integer.parseInt(textField_14.getText());
				p16 = Integer.parseInt(textField_15.getText());
				p17 = Integer.parseInt(textField_16.getText());
				p18 = Integer.parseInt(textField_17.getText());
				p19 = Integer.parseInt(textField_18.getText());
				p20 = Integer.parseInt(textField_19.getText());
				p21 = Integer.parseInt(textField_20.getText());
				p22 = Integer.parseInt(textField_21.getText());
				p23 = Integer.parseInt(textField_22.getText());
				p24 = Integer.parseInt(textField_23.getText());
				p25 = Integer.parseInt(textField_24.getText());

				int[][] wlasny2 = {
						{p1, p2, p3, p4,p5},
						{p6, p7, p8,p9,p10},
						{p11,p12,p13,p14,p15},
						{p16,p17,p18,p19,p20},
						{p21,p22,p23,p24,p25}
				};
				
				try {
					obraz = ImageIO.read(new File(path));
					int w = obraz.getWidth(), h = obraz.getHeight();
					tmp = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);


					Filtracja(wlasny2);
		
						
				} catch (IOException ex){
					ex.printStackTrace();
				}
				edytowany.setIcon(new ImageIcon (tmp.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_1 = new JMenu("Filtracja statystyczna");
		menu.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Mediana");
		mntmNewMenuItem_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				try {
					if (obraz == null) {
						obraz = ImageIO.read(new File(path));
						tmp = ImageIO.read(new File(path));
						
					}
					Filtracja_statystyczna();

				}catch (IOException ex) {
					ex.printStackTrace();
				}
				
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Wartosc minimalna");
		mntmNewMenuItem_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				try {
					if (obraz == null) {
						obraz = ImageIO.read(new File(path));
						tmp = ImageIO.read(new File(path));
						
					}
					
					Min();

				}catch (IOException ex) {
					ex.printStackTrace();
				}	
				
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Wartośc maksymalna");
		mntmNewMenuItem_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if (obraz == null) {
						obraz = ImageIO.read(new File(path));
						tmp = ImageIO.read(new File(path));
						
					}
					
					Max();

				}catch (IOException ex) {
					ex.printStackTrace();
				}	
				
			}
			
		});
		mnNewMenu_1.add(mntmNewMenuItem_9);
		
		
	
		
		JMenuItem pokaz = new JMenuItem("Pokaz");
		pokaz.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					if (obraz == null) obraz = ImageIO.read(new File(path));
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		
		
		Compare.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg"));
				int resvs = fc.showOpenDialog(null);
				if (resvs == JFileChooser.APPROVE_OPTION) {
					File sFile = fc.getSelectedFile();
					vs = sFile.getAbsolutePath();
				}
				try {
					obraz = ImageIO.read(new File(path));
					obraz2 = ImageIO.read(new File(vs));
					for (int x=0; x<obraz.getWidth(); x++) {
						for (int y =0; y<obraz.getHeight(); y++) {
							Color originalPixels = new Color(obraz.getRGB(x, y));
							Color editedPixels = new Color(obraz2.getRGB(x, y));
							
							int r = originalPixels.getRed();
							int g = originalPixels.getGreen();
							int b = originalPixels.getBlue();
							
							int r1 = editedPixels.getRed();
							int g1 = editedPixels.getGreen();
							int b1 = editedPixels.getBlue();
							
							if ( (r1 == r) && (g1 == g) && (b1 == b) ) {
								obraz2.setRGB(x, y, Color.black.getRGB());
							} else {
								obraz2.setRGB(x, y, obraz2.getRGB(x, y));
							}
						}
					}
					edytowany.setIcon(new ImageIcon (obraz2.getScaledInstance(oryginal.getWidth(), oryginal.getHeight(), 0)));
		           
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		
		
	}
}