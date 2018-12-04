package GA;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Populate {
	public static List<Phenotype> initPool(int poolSize, int popSize, int maxFont, BufferedImage original, String inputString) {
		List<Phenotype> imagePop = new ArrayList<>();
		for (int i = 0; i < poolSize; i++) {
			List<Genotype> genes = new ArrayList<>();
			for (int j = 0; j < popSize; j++) {
				genes.add(Mutation.getRandomGenes(maxFont, original, inputString));
			}
			imagePop.add(new Phenotype(genes));
		}
		return imagePop;
	}
}
