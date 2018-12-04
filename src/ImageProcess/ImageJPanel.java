package ImageProcess;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImageJPanel extends JPanel{
	//Original Image and Current Image Side by side
	private BufferedImage currimage;
	private BufferedImage originalImage;
	private int generation;
	private long fitness;
	
	public ImageJPanel(BufferedImage image) {
		this.originalImage = image;
		this.currimage = image;
		this.generation = 0;
		this.fitness = Long.MAX_VALUE;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(currimage, 0, 0, null);
		g.drawImage(originalImage, originalImage.getWidth() + 10, 0, null);
	}
	
	public int getGeneration() {
		return generation;
	}

	public void setGeneration(int generation) {
		this.generation = generation;
	}

	public long getFitness() {
		return fitness;
	}

	public void setFitness(long fitness) {
		this.fitness = fitness;
	}

	public BufferedImage getCurrimage() {
		return currimage;
	}

	public void setCurrimage(BufferedImage currimage) {
		this.currimage = currimage;
	}

	public BufferedImage getOriginalImage() {
		return originalImage;
	}

	public void setOriginalImage(BufferedImage originalImage) {
		this.originalImage = originalImage;
	}
	
}
