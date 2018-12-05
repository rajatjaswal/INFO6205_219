package MultiThread;

import java.awt.image.BufferedImage;
import java.util.List;

import GA.FitnessFunction;
import GA.IndividualImage;
import ImageProcess.RGB;

public class FitnessInParallel implements Runnable{
	
	private int start;
	private int end;
	private boolean done = false;
	private List<IndividualImage> imageLists;

	private BufferedImage original;
	private RGB[][] originalValues;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (imageLists == null) {
			return;
		}

		for (int i = start; i < end; i++) {
			if (imageLists.get(i).getFitness() == null) {
				FitnessFunction.evaluateFitness(imageLists.get(i), original, originalValues);
			}
		}
		done = true;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public List<IndividualImage> getImageLists() {
		return imageLists;
	}

	public void setImageLists(List<IndividualImage> imageLists) {
		this.imageLists = imageLists;
	}

	public BufferedImage getOriginal() {
		return original;
	}

	public void setOriginal(BufferedImage original) {
		this.original = original;
	}

	public RGB[][] getOriginalValues() {
		return originalValues;
	}

	public void setOriginalValues(RGB[][] originalValues) {
		this.originalValues = originalValues;
	}
}
