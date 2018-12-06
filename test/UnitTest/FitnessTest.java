package UnitTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
		File filex,file1,file2;
		BufferedImage oImage=null,image1 = null,image2=null;
		try {
			// Read from a file
			filex= new File("testImages/human.jpg");
			file1 = new File("testImages/1.png");
			file2 = new File("testImages/51.png");
			oImage= ImageIO.read(filex);
			image1 = ImageIO.read(file1);
			image2 = ImageIO.read(file2);

		} catch (IOException e) {
			System.out.println("File not read!!");
			return;
		}
		
		RGB[][] originalRGB=ProcessImage.readValuesAsPixels(oImage);
		long fitness1 = FitnessFunction.evaluateFitnessMain(image1, originalRGB);
		long fitness2 = FitnessFunction.evaluateFitnessMain(image2, originalRGB);
		assert(fitness1 > fitness2);
	}
}
