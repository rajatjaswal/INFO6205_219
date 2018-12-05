package GA;

import java.util.List;

public class IndividualImage implements Comparable<IndividualImage>{
	
	private List<Genotype> genes;
	private Long fitness;
	
	public List<Genotype> getGenes() {
		return genes;
	}

	public void setGenes(List<Genotype> genes) {
		this.genes = genes;
	}

	public Long getFitness() {
		return fitness;
	}

	public void setFitness(Long fitness) {
		this.fitness = fitness;
	}

	public IndividualImage(List<Genotype> genes) {
		this.genes = genes;
	}

	@Override
	public int compareTo(IndividualImage o) {
		// TODO Auto-generated method stub
		return fitness.compareTo(o.getFitness());
	}
	
	@Override
	public String toString() {
		return fitness.toString();
	}
}
