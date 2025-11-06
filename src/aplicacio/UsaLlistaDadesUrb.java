package aplicacio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import dades.*;

public class UsaLlistaDadesUrb {
	static Scanner teclat = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		int numLinies, liniaIni, liniaFi, opcio;

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

		mostraMenu();
		opcio = Integer.parseInt(teclat.nextLine());
		while (opcio != 7) {
			switch (opcio) {
			case 1:
				opcio1(lista);
				break;
			case 2:
				opcio2(lista);
				break;
			case 3:
				opcio3(lista);
				break;
			case 4:
				opcio4(lista);
				break;
			case 5:
				opcio5(lista);
				break;
			case 6:
				opcio6(lista);
				break;
			case 7:
				opcio7(lista);
				break;
			case 8:
				opcio8(lista);
				break;
			case 9:
				opcio9(lista);
				break;
			case 10:
				opcio10(lista);
				break;
			case 11:
				opcio11(lista);
				break;
			}

			mostraMenu();
			opcio = Integer.parseInt(teclat.nextLine());

		
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
		Scanner f = new Scanner(new File("Dades_Urban.csv"), "UTF-8");

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


	public static void mostraMenu() {
		System.out.println("\n\nOpcions del menu:");
		System.out.println("\n\t1. Mostrar el conjunt de dades de la llista");
		System.out.println("\t2. Esborrar les dades d'un municipi");
		System.out.println("\t3. Mostrar les dades del municipi amb la superfície total més gran de Catalunya");
		System.out.println("\t4. Mostrar la informació del municipi amb el percentatge de zones verdes més alt de Catalunya");
		System.out.println("\t5. Mostrar la variació de la superfície de sòl urbanitzable");
		System.out.println("\t6. Mostrar les dades del primer municipi de costa sense sòl urbanitzable");
		System.out.println("\t7. Mostrar el nom dels municipis que tenen una densitat de població superior a X");
		System.out.println("\t8. Mostrar el nom Mostrar el nom dels municipis de muntanya que tenen una densitat de població superior a un"+
						"valor");
		System.out.println("\t9. Mostrar la informació del municipi de costa que té el percentatge de superfície destinat a"+
						"zones verdes més alt de Catalunya");
		System.out.println("\t10. Mostrar la informació del municipi que no és ni de costa ni de muntanya que té la superfície"+
						"total més gran de Catalunya");
		System.out.println("\t11. Mostrar l’evolució de la població anual d’un municipi en els diferents anys on tenim dades");
		System.out.println("\t12. Sortir");
		System.out.print("\n\t\t\tIndica opcio: \n");
	}


	public static void opcio1(LlistaDadesUrb lista) {
		// 1. Mostrar el conjunt de dades de la llista
		System.out.println("El contingut de la llista és\n" + lista);
	}


	public static void opcio2(LlistaDadesUrb lista) {
		// 2. Esborrar les dades d'un municipi
		String municipi;
		System.out.print("\n\n\tIndica municipi a esborrar:\t");
		municipi = teclat.nextLine();

		//Mostrar los datos del municipio a eliminar
		LlistaDadesUrb municipiEliminat = lista.dadesUnMunicipi(municipi);
		System.out.println("\nLos datos que se borraran son:"+ municipiEliminat);

		//Eliminar el municipio
		lista.eliminaMunicipi(municipi);

		//Volver a mostrar el municipio eliminado
		municipiEliminat = lista.dadesUnMunicipi(municipi);
		System.out.println("\nPrueba de que ya no quedan los datos:"+ municipiEliminat);
	}


	public static void opcio3(LlistaDadesUrb lista) {
		// 3. Mostrar les dades del municipi amb la superfície total més gran de Catalunya
		DadesUrb municipiMaxSup = lista.municipiSuperfMesGran();
		System.out.println("Les dades del municipi amb la superfície total més gran de Catalunya són:\n" + municipiMaxSup);
	}
	

	public static void opcio7(LlistaDadesUrb lista){
		// 7. Mostrar el nombre de los municipios que tienen una densidad de poblaición superior al valor introducido por teclado

		//Solicitamos el valor al usuario
		System.out.println("\n\n\tIndique el valor mínimo de densidad de población (habitantes/km²):\t ");
		double vMinDensidad = Double.parseDouble(teclat.nextLine());

		//Llamamos al método dde la clase
		String[] municipios = lista.municipiMesDensos(vMinDensidad);

		//En caso de que no se encuentre ningún municipio
		if(municipios.length == 0){
			System.out.println("Lamentablemente no se han hallado municipios con densidad de publación superior a " + vMinDensidad + " habitants/km²");
		}else{
			//Si se encuentra municipio
			System.out.println("Municipios con densidad de publación superior a " + vMinDensidad + " habitants/km²:\n");
			for(int i = 0 ; i < municipios.length ; i++){
				System.out.println("\t- " + municipios[i]);
			}
		}
	}
}