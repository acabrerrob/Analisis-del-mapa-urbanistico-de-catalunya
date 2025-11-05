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
		LlistaDadesUrb lista =  new LlistaDadesUrb(numLinies);	//Creo instancia de LlistaDadesUrb con el número de lineas.

		for (int i = 0; i < dataset.length; i++) {

			String[] split = dataset[i].split(";");

			DadesUrb linea = new DadesUrb( Integer.parseInt(split[0]) , 						//Any dades paso a int
											split[1] , 											// Nommunicipi
											split[2] , 											// Comarca.
											"1".equals(split[3]) ,								//Es municipi de costa paso a bolleano
											"Zona de muntanya".equals(split[4]) ,				//Es municipi de muntanya paso a bolleano
											Integer.parseInt(split[5]) ,						//Número de habitantes.
											Double.parseDouble(split[6].replace(',', '.')) ,	//supHectarea
											Double.parseDouble(split[7].replace(',', '.')) ,	//Suelo urbano
											Double.parseDouble(split[8].replace(',', '.')) ,  	//Cambio de string a double superficie suelo urbanizable hectareas.
											Double.parseDouble(split[9].replace(',', '.')) ,  	//Cambio de string a double superficie suelo NO urbanizable hectareas.
											Double.parseDouble(split[10].replace(',', '.')) ,  	//Cambio de string a double supperficie suelo insutrial.
											Double.parseDouble(split[11].replace(',', '.')) ,	//Cambio de string a double superficie de servicios.
											Double.parseDouble(split[12].replace(',', '.')) , 	//Cambio de string a double superficie logistica.
											Double.parseDouble(split[13].replace(',', '.')) , 	//Cambio de string a double superifcie zonas verdes.
											Double.parseDouble(split[14].replace(',', '.'))); 	//Cambio de string a double superficie equipo habitante.
			lista.afegirDadesUrb(linea);	
		}
		/**COMPROBACIÓN DE QUE SE LEE BIEN DEL CSV PARA PASAR A CLASE:
		 * System.out.println("Listado de datos => nDades " + lista.getNDades());
		 * System.out.println(lista);*/
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