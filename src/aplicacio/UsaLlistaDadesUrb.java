package aplicacio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import dades.*;

public class UsaLlistaDadesUrb {
	static Scanner teclat = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		int numLinies, liniaIni, liniaFi;

		System.out.println("Indica les línies a llegir del fitxer");
		System.out.println("Quina és la primera línia a llegir del fitxer (valor >=1)");
		liniaIni = Integer.parseInt(teclat.nextLine());
		System.out.println("Quina és l'última línia a llegir del fitxer (valor <=12324)");
		liniaFi = Integer.parseInt(teclat.nextLine());

		numLinies = liniaFi - liniaIni + 1;
		String[] dataset = llegirLiniesFitxer(liniaIni, liniaFi);

		// mostrem el contingut que hem llegit. Això ho eliminarem en les
		// versions finals del codi
		for (int i = 0; i < dataset.length; i++) {
			System.out.println("Linia " + (i + 1) + " conté " + dataset[i]);
		}

		// Completar el codi a partir d'aquí

		

	}

	private static String[] llegirLiniesFitxer(int liniaIni, int liniaFi) throws FileNotFoundException {
		String[] result;
		String dada;
		int nLinies;
		if (liniaIni < 1)
			liniaIni = 1;
		if (liniaFi > 12324)
			liniaFi = 12324;
		nLinies = liniaFi - liniaIni + 1;
		result = new String[nLinies];
		Scanner f = new Scanner(new File("Dades_Urban.csv"));

		String capcalera = f.nextLine();
		System.out.println("El format de les dades en cada línia és el següent\n" + capcalera);
		// ens saltem les línies fins a la liniaIni
		for (int i = 1; i < liniaIni; i++) {
			dada = f.nextLine();
		}
		// ara anirem guardant les nLinies que sí volem llegir
		for (int i = 0; i < nLinies; i++) {
			result[i] = f.nextLine();
		}
		f.close();
		return result;
	}
}