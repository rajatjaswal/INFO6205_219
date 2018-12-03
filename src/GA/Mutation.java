package GA;

import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import ImageProcess.ProcessImage;
import ImageProcess.RGB;

public class Mutation {
	
	private static char getRandomCharacter(String inputCharStream) {
		Random random=new Random();
		return inputCharStream.charAt(random.nextInt(inputCharStream.length()));
	}
	
	private static Genotype getRandomGenes(int maxFont, BufferedImage original, String inputString) {
		Random random=new Random();
		Genotype genotype = new Genotype();
		genotype.setCharacter(getRandomCharacter(inputString));
		genotype.setColor(ProcessImage.getRandomColor());
		genotype.setSize(random.nextInt(maxFont));
		genotype.setX(random.nextInt(original.getWidth()));
		genotype.setY(random.nextInt(original.getHeight()));
		return genotype;
	}
	
	private static void mutate(List<Genotype> genotypeList, int maxFont, BufferedImage original, String inputString) {
		Random random=new Random();
		int location = random.nextInt(genotypeList.size());
		Genotype genotype = genotypeList.get(location);

		switch (random.nextInt(4)) {
		case 0:
			// remove one Gene and add new Gene
			genotype = getRandomGenes(maxFont, original, inputString);
			genotypeList.remove(location);
			genotypeList.add(genotype);
			break;
		case 1:
			// swap two genes from the list
			Collections.swap(genotypeList, random.nextInt(genotypeList.size()),
					location);
			break;
		case 2:
			// change any one's color
			genotype.setColor(ProcessImage.getRandomColor());
			genotypeList.set(location, genotype);
			break;
		case 3:
			// replace one with new gene
			genotype = getRandomGenes(maxFont, original, inputString);
			genotypeList.set(location, genotype);
			break;
		}

	}
}
