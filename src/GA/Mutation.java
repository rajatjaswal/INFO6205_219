package GA;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
	
	public static Genotype getRandomGenes(int maxFont, BufferedImage original, String inputString) {
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
	
	private static Phenotype mate(List<Phenotype> newPool, int elite, int population, int mutationCount, int maxFont, BufferedImage image, String inputString) {
		// get random 2 from elite
		Random random=new Random();
		int first = random.nextInt(elite);
		int second = random.nextInt(elite);
		while (first == second) {
			second = random.nextInt(elite);
		}
		// mate the two
		return mate(newPool.get(first), newPool.get(second), population, mutationCount, maxFont, image, inputString);
	}
	
	private static Phenotype mate(Phenotype first, Phenotype second, int population, int mutationCount, int maxFont, BufferedImage image, String inputString) {
		Random random=new Random();
		List<Genotype> genes = new ArrayList<>();
		for (int i = 0; i < population; i++) {
			switch (random.nextInt(2)) {
			case 0:
				genes.add(new Genotype(first.getGenes().get(i)));
				break;
			case 1:
				genes.add(new Genotype(second.getGenes().get(i)));
				break;
			}
		}

		for (int i = 0; i < mutationCount; i++) {
			mutate(genes, maxFont, image, inputString);
		}
		return new Phenotype(genes);
	}
	
	public static List<Phenotype> mateBest(List<Phenotype> pool, int eliteCount, int population, int mutationCount, int maxFont, BufferedImage image, String inputString) {
		List<Phenotype> newPool = new ArrayList<>();
		for (int i = 0; i < pool.size(); i++) {
			if (i < eliteCount) {
				newPool.add(pool.get(i));
			} else {
				newPool.add(mate(newPool, eliteCount, population, mutationCount, maxFont, image, inputString));
			}
		}
		return newPool;
	}
}
