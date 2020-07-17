public class Record {

	// instance variables to be used within the class
	private String conf;
	private int sco;

	public Record(String config, int score) {
		conf = config;
		sco = score;
	}

	public String getConfig() {
		return this.conf;
	}

	public int getScore() {
		return this.sco;
	}

}
