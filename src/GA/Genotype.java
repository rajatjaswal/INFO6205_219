package GA;

import java.awt.Color;

public class Genotype implements Comparable<Genotype>{
	
	// My current GenoType is the representation of a character in a frame i.e the basic traits.
	private char character;
	private int size;
	private Color color;
	private int x;
	private int y;
	private Long fitness;
	
	public Genotype(Genotype gene) {
		this.character = gene.getCharacter();
		this.size = gene.getSize();
		this.color = new Color(gene.getColor().getRed(),
				gene.getColor().getGreen(), gene
						.getColor().getBlue());
		this.x = gene.getX();
		this.y = gene.getY();
	}
	
	public Genotype() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Genotype gene) {
		// TODO Auto-generated method stub
		return fitness.compareTo(gene.getFitness());
	}
	
	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Long getFitness() {
		return fitness;
	}

	public void setFitness(Long fitness) {
		this.fitness = fitness;
	}
}
