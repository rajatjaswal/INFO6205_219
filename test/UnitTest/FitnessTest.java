package UnitTest;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.Test;

import GA.FitnessFunction;
import GA.Genotype;
import GA.IndividualImage;
import ImageProcess.ProcessImage;
import ImageProcess.RGB;

/**
 * Test method of GeneticAlgorithm.
 */
public class FitnessTest {
	
	@Test
    public void testFitness() throws Exception {
		// Read two files
		System.out.println("asdsadasd");
		File filex = new File("testImages/human.png");
		BufferedImage oImage = ImageIO.read(filex);
		File file1 = new File("testImages/1.png");
		BufferedImage image1 = ImageIO.read(file1);
		File file2 = new File("testImages/51.png");
		BufferedImage image2 = ImageIO.read(file2);
		RGB[][] originalRGB=ProcessImage.readValuesAsPixels(oImage);
		
		long fitness1 = FitnessFunction.evaluateFitnessMain(image1, originalRGB);
		long fitness2 = FitnessFunction.evaluateFitnessMain(image2, originalRGB);
		System.out.println(fitness1 + " "+fitness2);
//		assert(fitness1, fitness2);
	}
}
