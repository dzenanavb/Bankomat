import java.io.*;

@SuppressWarnings("serial")
public class Racun implements Serializable {
	
	int brojRacuna;	
	String imeVlasnika;
	double stanjeRacuna;
	
	public Racun() {
		
	}
	
	public Racun(int brojRacuna, String imeVlasnika, double stanjeRacuna) throws IOException {
		super();
		this.brojRacuna = brojRacuna;
		this.imeVlasnika = imeVlasnika;
		this.stanjeRacuna = stanjeRacuna;
	}}