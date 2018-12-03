package ImageProcess;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import GA.Genotype;
import GA.Phenotype;

public class ProcessImage {
	
	private static Random random =new Random();
	
	public static RGB[][] readValuesAsPixels(BufferedImage image) {
		
		RGB[][] rgbValue = new RGB[image.getWidth()][image.getHeight()];

		// add values
		int red = 0;
		int green = 0;
		int blue = 0;
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				int pixel = image.getRGB(i, j);
				red = (pixel >> 16) & 0xff;
				green = (pixel >> 8) & 0xff;
				blue = (pixel) & 0xff;
				rgbValue[i][j] = new RGB(red, green, blue);
			}
		}
		
		return rgbValue;
	}
	
	public static Color getRandomColor() {
		return new Color(random.nextInt(255), random.nextInt(255),
				random.nextInt(255));
	}
	
	private static BufferedImage getBufferedImage(Phenotype phenoType, BufferedImage original) {
		BufferedImage image = new BufferedImage(original.getWidth(),
				original.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		graphics.setPaint(new Color(255, 255, 255));
		graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
		for (Genotype genotype : phenoType.getGenes()) {
			graphics.setFont(new Font(null, Font.PLAIN, genotype.getSize()));
			graphics.setColor(genotype.getColor());
			graphics.drawString(Character.toString(genotype.getCharacter()),
					genotype.getX(), genotype.getY());
		}
		graphics.dispose();
		return image;
	}
}
