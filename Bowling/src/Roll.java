public class Roll {
	
	private boolean second;
	private int pinsHit;
	
	
	public Roll() {
		this.second = false;
		this.pinsHit = 0;
	}
	
	public Roll(int pinsHit) {
		this.second = false;
		this.pinsHit = pinsHit;
	}
	
	public Roll(int pinsHit, boolean second) {
		if (second) {
			this.second = true;
			this.pinsHit = pinsHit;
		}
			
	}
	
	public boolean second() {
		return second;
	}
	
	public int getPinsHit() {
		return this.pinsHit;
	}
	

	
	
}
