import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import GA.Genotype;
import GA.Mutation;
import GA.Phenotype;
import GA.Populate;
import ImageProcess.ImageJPanel;
import ImageProcess.ProcessImage;
import ImageProcess.RGB;
import MultiThread.FitnessInParallel;

public class Main {
	private static final String IMAGE = "human.jpg";
	private static final String IMAGE_LOCATION_SAVE = "SavedImages/";
	private static final int POOL_SIZE = 500;
	private static final String INPUTSTRING = "õäöüxywzabcdefghijklmnopqrstuv1234567890";
	private static final int CHAR_COUNT = 1800;
	private static final int ELITECOUNT = 10;
	private static final int MAX_GENERATIONS = 10000;
	private static final int MUTATION_COUNT = 1;
	private static final int MAXFONT = 30;
	private static final int THREADS = 3;
	private static final boolean SHOW_UI = true;
	
	private static BufferedImage initImage;
	private static RGB[][] imageAsPixels;
	
	private static List<Phenotype> poolOfImages;
	private static List<FitnessInParallel> parallelFitnessEvaluators;
	
	public static void main(String args[]) {
		//Load an Image
		readInitImage();
		imageAsPixels=ProcessImage.readValuesAsPixels(initImage);
		
		ImageJPanel jPanel = null;
		if (SHOW_UI) {
			jPanel = new ImageJPanel(initImage);
			JFrame frame = new JFrame();
			frame.setPreferredSize(new Dimension(initImage.getWidth() * 2 + 25,
					initImage.getHeight() + 40));
			frame.getContentPane().add(jPanel);
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		
		//Populate 
		poolOfImages=Populate.initPool(POOL_SIZE, CHAR_COUNT, MAXFONT, initImage, INPUTSTRING);
		//Initialise threads
		initThreads();
		
		for (int i = 0; i < MAX_GENERATIONS; i++) {
			//Compute Fitness
			fitnessOfEntireImage(poolOfImages);
			Collections.sort(poolOfImages);
			BufferedImage bestImage = ProcessImage.getBufferedImage(poolOfImages.get(0),initImage);
			if (SHOW_UI) {
				jPanel.setCurrimage(bestImage);
			}
			double fitness = 100 - 100.0 / 765 * (1.0* poolOfImages.get(0).getFitness() / initImage.getWidth() / initImage.getHeight());
			System.out.printf("GenerationNo: %s Fitness: %s%% Population: %s %s\n", i + 1,fitness, CHAR_COUNT, IMAGE);
			if (SHOW_UI) {
				jPanel.repaint();
			}
			saveImage(bestImage, i + 1, fitness);
			// throw away bad results and substitute with children
			poolOfImages = Mutation.mateBest(poolOfImages, ELITECOUNT, CHAR_COUNT, MUTATION_COUNT, MAXFONT, bestImage, INPUTSTRING);
			
		}
	}
	
	
	private static void saveImage(BufferedImage best, int generation,
			double fitness) {
		try {
			File file = new File(IMAGE_LOCATION_SAVE + generation + ".png");
			new File(IMAGE_LOCATION_SAVE).mkdir();
			ImageIO.write(best, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void fitnessOfEntireImage(List<Phenotype> poolOfImages) {
		// split between threads
		int forOneThread = poolOfImages.size() / THREADS;
		for (int i = 0; i < THREADS; i++) {
			FitnessInParallel evaluator = parallelFitnessEvaluators.get(i);
			evaluator.setStart(i * forOneThread);
			evaluator.setEnd((i + 1) * forOneThread + 1);
			evaluator.setImageLists(poolOfImages);
			if (i + 1 == THREADS) {
				evaluator.setEnd(poolOfImages.size());
			}
			new Thread(evaluator).start();
		}

		// see if all done
		while (!parallelFitnessEvaluatorDone()) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static void populate() {
		List<Phenotype> imagePool = new ArrayList<>();
		for (int i = 0; i < POOL_SIZE; i++) {
			List<Genotype> genes = new ArrayList<>();
			for (int j = 0; j < CHAR_COUNT; j++) {
				genes.add(Mutation.getRandomGenes(MAXFONT, initImage, INPUTSTRING));
			}
			imagePool.add(new Phenotype(genes));
		}
		poolOfImages = imagePool;
	}
	
	private static void readInitImage() {
		try {
			// Read from a file
			File file = new File(IMAGE);
			initImage = ImageIO.read(file);

		} catch (IOException e) {
			System.out.println("File not read!!");
		}
	}
	
	private static void initThreads() {
		parallelFitnessEvaluators = new ArrayList<>();
		for (int i = 0; i < THREADS; i++) {
			FitnessInParallel evaluator = new FitnessInParallel();
			evaluator.setImageLists(poolOfImages);
			evaluator.setOriginal(initImage);
			evaluator.setOriginalValues(imageAsPixels);
			parallelFitnessEvaluators.add(evaluator);
		}
	}
	
	private static boolean parallelFitnessEvaluatorDone() {
		for (FitnessInParallel evaluator : parallelFitnessEvaluators) {
			if (!evaluator.isDone()) {
				return false;
			}
		}
		resetparallelFitnessEvaluator();
		return true;
	}

	private static void resetparallelFitnessEvaluator() {
		for (FitnessInParallel evaluator : parallelFitnessEvaluators) {
			evaluator.setDone(false);
		}
	}
}
