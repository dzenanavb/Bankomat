import java.util.*;
import java.io.*;

public class Main {
	static ArrayList<Racun> racuni = new ArrayList<Racun>(); 
	static Scanner unos= new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		ucitati();
		boolean ide = true;
		do {
		System.out.println("Unesite 1 ako zelite da kreirate novi racun: ");
		System.out.println("Unesite 2 ako zelite da izvrsite transfer novca: ");
		System.out.println("Unesite 3 ako zelite ispis detalja postojecih racuna: ");
		System.out.println("Unesite 4 ako zelite da izadete iz aplikacije: ");
		int izbor = unos.nextInt();
		
		switch(izbor) {
		case 1: kreirajRacun(); break;
		
		case 2: transferNovca(); break;
			
		case 3: ispisRacuna(); break;
		
		case 4: cuvanje(); ide = false; break;
			
		}}
		while(ide);

	}

	public static void ucitati () throws IOException {
		try {
		File f = new File ("racuni.txt");
		FileInputStream novi = new FileInputStream(f);
		@SuppressWarnings("resource") //ne moram zatvoriti oin
		ObjectInputStream oin = new ObjectInputStream(novi);
			while (true) 
			racuni.add((Racun)oin.readObject());}
		
		catch (Exception e) {}
	}
	
	public static void kreirajRacun() throws IOException{
		boolean greska = true;
		
		do {
		try {
		System.out.println("Unesite broj racuna: ");
		int brojRacuna = unos.nextInt();
		unos.nextLine();
		System.out.println("Unesite ime korisnika: ");
		String imeKorisnika = unos.nextLine();
		System.out.println("Unesite stanje na racunu: ");
		double stanjeRacuna = unos.nextDouble();
		greska = false;
		
		if (provjeraRacuna(brojRacuna, stanjeRacuna)) {
			racuni.add(new Racun(brojRacuna, imeKorisnika, stanjeRacuna));
			System.out.println("Racun je uspjesno kreiran!");
		}
		
		}
		
		catch(java.util.InputMismatchException e) {
			System.out.println("Neki od unosa nije bio tacan! Pokusajte opet!");
			unos.nextLine();
		}}
		while(greska);
		
	}
	
	public static boolean provjeraRacuna (int brojRacuna, double stanjeRacuna) throws IOException {
		if(stanjeRacuna < 0) {
			System.out.println("Stanje racuna ne smije biti negativno. Racun nije kreiran!");
			return false;}
		
		if (brojRacuna < 0) {
			System.out.println("Broj racuna ne smije biti negativan. Racun nije kreiran!");
			return false;}

		for (int i = 0; i < racuni.size(); i++)
			if (racuni.get(i).brojRacuna == brojRacuna) {
				System.out.println("Broj racuna vec postoji. Racun nije kreiran!");
				return false;}
		return true;}
	
	public static void transferNovca() throws IOException {
		
		boolean greska = true;
		
		do {try {
			System.out.println("Unesite broj source racuna: ");
			int sourceRacun = unos.nextInt();
			System.out.println("Unesite broj target racuna: ");
			int targetRacun = unos.nextInt();
			System.out.println("Unesite iznos transfera: ");
			double iznosTransfera = unos.nextDouble();
			greska = false;
			if(provjeraTransfera (sourceRacun, targetRacun, iznosTransfera)) {
				for (int i = 0; i < racuni.size(); i++) {
					if (racuni.get(i).brojRacuna == sourceRacun)
						racuni.get(i).stanjeRacuna -= iznosTransfera;

					if (racuni.get(i).brojRacuna == targetRacun)
						racuni.get(i).stanjeRacuna += iznosTransfera;
					
			}System.out.println("Transfer je izvrsen!");}
			
			else
			{System.out.println("Transfer nije izvrsen!");}
		}
		catch(java.util.InputMismatchException e) {
			System.out.println("Neki od unosa nije bio tacan! Pokusajte opet!");
			unos.nextLine();
		}
		}
		
		while(greska);
		
	}
		
	public static boolean provjeraTransfera(int sourceRacun, int targetRacun, double iznos) throws IOException {
			boolean sourcePostoji = false;
			boolean targetPostoji = false;
			boolean novac = false;

			for (int i = 0; i < racuni.size(); i++) {
				if (racuni.get(i).brojRacuna == sourceRacun) 
					sourcePostoji = true;

				if ((racuni.get(i).stanjeRacuna - iznos) >= 0)
						novac = true;

				if (racuni.get(i).brojRacuna == targetRacun)
					targetPostoji = true;

			}
			if (sourcePostoji && targetPostoji && novac)
				return true;
			else return false;
	}

	public static void ispisRacuna() throws IOException {
		for(int i = 0; i <racuni.size(); i++) {
			System.out.println("Ime korisnika racuna: "+racuni.get(i).imeVlasnika);
			System.out.println("Broj racuna: "+racuni.get(i).brojRacuna);
			System.out.println("Stanje racuna je: "+racuni.get(i).stanjeRacuna);
		}}
	
	public static boolean cuvanje() throws IOException {
		FileOutputStream novi = new FileOutputStream("racuni.txt");
		ObjectOutputStream novi2 = new ObjectOutputStream(novi);

		for (int i = 0; i < racuni.size(); i++) 
			novi2.writeObject(racuni.get(i));
		
		novi2.close();
		return true;}
	
	}
