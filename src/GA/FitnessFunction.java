package GA;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import ImageProcess.RGB;


public class FitnessFunction {
	
	public static void evaluateFitness(IndividualImage imageData, BufferedImage original, RGB[][] originalValues) {
		BufferedImage image = new BufferedImage(original.getWidth(),original.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		graphics.setPaint(new Color(255, 255, 255));
		graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
		for (Genotype genotype : imageData.getGenes()) {
			graphics.setColor(genotype.getColor());
			graphics.setFont(new Font(null, Font.PLAIN, genotype.getSize()));
			graphics.drawString(Character.toString(genotype.getCharacter()),genotype.getX(), genotype.getY());
		}
		graphics.dispose();

		int red = 0;
		int green = 0;
		int blue = 0;
		long difference = 0L;
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				int pixel = image.getRGB(i, j);
				red = (pixel >> 16) & 0xff;
				green = (pixel >> 8) & 0xff;
				blue = (pixel) & 0xff;
				RGB rgb = new RGB(red, green, blue);
				difference += originalValues[i][j].difference(rgb);
			}
		}

		imageData.setFitness(difference);
	}
}
