package dades;

public class DadesUrb {
	private int anyDades;
	private String nomMunicipi;
	private String comarca;
	private String provincia;
	private boolean esMunicipiDeCosta;
	private boolean esMunicipiDeMuntanya;
	private int numHabitants;
	private double superficie_ha;
	private double superfSolUrba_ha;
	private double superfSolUrbanitzable_ha;
	private double superfSolNOurbanitzable_ha;
	private double superfIndustrial_ha;
	private double superfServeis_ha;
	private double superfLogistica_ha;
	private double superfZonesVerdes_ha;
	private double superfEquipHabitant_m2;

	private static String[] comBarcelona = { "Alt Penedès", "Anoia", "Bages", "Baix Llobregat", "Barcelonès",
			"Berguedà", "Garraf", "Lluçanès", "Maresme", "Moianès", "Osona", "Vallès Oriental", "Vallès Occidental" };

	private static String[] comTarragona = { "Alt Camp", "Baix Camp", "Baix Ebre", "Baix Penedès", "Conca de Barberà",
			"Montsià", "Priorat", "Ribera d'Ebre", "Tarragonès", "Terra Alta" };
	private static String[] comLleida = { "Alta Ribagorça", "Alt Urgell", "Garrigues", "Noguera", "Pallars Jussà",
			"Pallars Sobirà", "Pla d'Urgell", "Segarra", "Segrià", "Solsonès", "Urgell", "Val d'Aran" };
	private static String[] comGirona = { "Alt Empordà", "Baix Empordà", "Cerdanya", "Garrotxa", "Gironès",
			"Pla de l'Estany", "Selva", "Ripollès" };

	/**
	 * Contructor de DadesUrb que inicializa el objeto con todos los datos urbanísticos por 
	 * municipio y año proporcionado.
	 * 
	 * @param anyDades (int) Año de los datos.
	 * @param nomMunicipi (String)	Nombre del municipio.
	 * @param comarca (String) Nombre de la comarca.
	 * @param esMunicipiDeCosta (boolean) Indica si es municipio de costa: si (1), no(0).
	 * @param esMunicipiDeMuntanya (boolean) Indica si es municipio de montaña: si (1), no(0).
	 * @param numHabitants (int) Número de habitantes del municipio.
	 * @param superficie_ha (double) Superficie del municipio (hectáreas).
	 * @param superfSolUrba_ha (double) Superficie del suelo urbano (hectáreas).
	 * @param superfSolUrbanitzable_ha (double) Superficie de suelo urbanizable (hectáreas).
	 * @param superfSolNOurbanitzable_ha (double) Superficie de suelo NO urbanizable (hectáreas).
	 * @param superfIndustrial_ha (double) Superficie de suelo Act.Económica(A1) Industrial (hectáreas).
	 * @param superfServeis_ha (double) Supericie de suelo Act.Económica(A2) Servicios (hectáreas).
	 * @param superfLogistica_ha (double) Superficie de suelo Act.Económica(A3) Logística (hectáreas).
	 * @param superfZonesVerdes_ha (double) Superficie de suelo destinado a zonas verdes (hectáreas).
	 * @param superfEquipHabitant_m2 (double) Superficie de suelo de equipamiento por habitante (metros cuadrados).
	 *  */ 
	public DadesUrb(int anyDades, String nomMunicipi, String comarca, boolean esMunicipiDeCosta,
			boolean esMunicipiDeMuntanya, int numHabitants, double superficie_ha, double superfSolUrba_ha,
			double superfSolUrbanitzable_ha, double superfSolNOurbanitzable_ha, double superfIndustrial_ha,
			double superfServeis_ha, double superfLogistica_ha, double superfZonesVerdes_ha,
			double superfEquipHabitant_m2) {
		this.anyDades = anyDades;
		this.nomMunicipi = nomMunicipi;
		this.comarca = comarca;
		this.provincia = cercaProvinciaComarca(comarca);
		this.esMunicipiDeCosta = esMunicipiDeCosta;
		this.esMunicipiDeMuntanya = esMunicipiDeMuntanya;
		this.numHabitants = numHabitants;
		this.superficie_ha = superficie_ha;
		this.superfSolUrba_ha = superfSolUrba_ha;
		this.superfSolUrbanitzable_ha = superfSolUrbanitzable_ha;
		this.superfSolNOurbanitzable_ha = superfSolNOurbanitzable_ha;
		this.superfIndustrial_ha = superfIndustrial_ha;
		this.superfServeis_ha = superfServeis_ha;
		this.superfLogistica_ha = superfLogistica_ha;
		this.superfZonesVerdes_ha = superfZonesVerdes_ha;
		this.superfEquipHabitant_m2 = superfEquipHabitant_m2;
	}

	/**
	 * Método que devuelve el nombre de la provincia a la que pertenece 
	 * la comarca pasada por parámetro. Si la comarca no se encuentra, 
	 * se imprime mensaje de error.
	 * 
	 * @param comarca Nombre de la comarca que se desea identificar.
	 * @return Nombre de la provincia correspondiente y si no se encuentra, string de error.
	 */
	private String cercaProvinciaComarca(String comarca) {
		String nomProvincia = null;
		if (estaEn(comarca, comBarcelona))
			nomProvincia = "Barcelona";
		else if (estaEn(comarca, comTarragona))
			nomProvincia = "Tarragona";
		else if (estaEn(comarca, comLleida))
			nomProvincia = "Lleida";
		else if (estaEn(comarca, comGirona))
			nomProvincia = "Girona";
		else
			System.out.println("PROBLEMA, no trobem " + comarca);
		return nomProvincia;
	}

	/**
	 * Método que busca una comarca introducida por parámetro en una lista 
	 * de comarcas de la provincia deseada.
	 * 
	 * @param comarca	Nombre de la comarca a buscar.
	 * @param comarquesProvincia	Lista de comarcas de la provincia.
	 * @return (True) Si la comarca se encuentra en la lista, (False) en caso contrario.
	 */
	private boolean estaEn(String comarca, String[] comarquesProvincia) {
		int pos = 0;
		boolean trobat = false;

		while (!trobat && pos < comarquesProvincia.length) {
			if (comarquesProvincia[pos].equalsIgnoreCase(comarca))
				trobat = true;
			else
				pos++;
		}
		return trobat;
	}

	/**
	 * Método que devuelve una string de todos los datos urbanísticos contenidos en la instancia DadesUrb.
	 * 
	 * @return Cadena de texto que representa todos los campos de la instancia actual.
	 */
	@Override
	public String toString() {
		String aux="\n\nDadesUrb";
		aux = aux +"\n\tanyDades=" + anyDades + "\n\tnomMunicipi=" + nomMunicipi + "\n\tcomarca=" + comarca
				+ "\n\tprovincia=" + provincia + "\n\tesMunicipiDeCosta=" + esMunicipiDeCosta + "\n\tesMunicipiDeMuntanya="
				+ esMunicipiDeMuntanya + "\n\tnumHabitants=" + numHabitants + "\n\tsuperficie_ha=" + superficie_ha
				+ "\n\tsuperfSolUrba_ha=" + superfSolUrba_ha + "\n\tsuperfSolUrbanitzable_ha=" + superfSolUrbanitzable_ha
				+ "\n\tsuperfSolNOurbanitzable_ha=" + superfSolNOurbanitzable_ha + "\n\tsuperfIndustrial_ha="
				+ superfIndustrial_ha + "\n\tsuperfServeis_ha=" + superfServeis_ha + "\n\tsuperfLogistica_ha="
				+ superfLogistica_ha + "\n\tsuperfZonesVerdes_ha=" + superfZonesVerdes_ha + "\n\tsuperfEquipHabitant_m2="
				+ superfEquipHabitant_m2;
		return aux;
	}

	/**
	 * Método que crea y devuelve una nueva instancia igual a la actual.
	 * 
	 * @return Nueva instancia con los mismos datos que el objeto actual.
	 */
	public DadesUrb copia() {
		DadesUrb duplicat = new DadesUrb(anyDades, nomMunicipi, comarca, esMunicipiDeCosta,
				esMunicipiDeMuntanya, numHabitants, superficie_ha, superfSolUrba_ha,
				superfSolUrbanitzable_ha, superfSolNOurbanitzable_ha, superfIndustrial_ha,
				superfServeis_ha, superfLogistica_ha, superfZonesVerdes_ha,
				superfEquipHabitant_m2);
		return duplicat;
	}

	
	/**
	 * Método getter que devuelve el año de los datos
	 * 
	 * @return Año de los datos actuales.
	 */
	public int getAnyDades(){
		return anyDades;
	}

	/**
	 * Método getter que devuelve el nombre del municipio
	 * 
	 * @return String con el nombre del municipio
	 */
	public String getNomMunicipi(){
		return nomMunicipi;
	}

	/**
	 * Método getter que devuelve si es un municipio de costa o no (0 no lo es, 1 si)
	 * 
	 * @return cierto si es municipio de costa, falso en caso contrario
	 */
	public boolean getEsMunicipiDeCosta(){
		return esMunicipiDeCosta;
	}

	/**
	 * Método getter que devuelve la superficie total del municipio en hectáreas.
	 * @return Valor de la superficie total del municipo.
	 */
	public double getSuperficie_ha(){
		return superficie_ha;
	}

	/**
	 * Método getter que devuelve la superficie urbanizable.
	 * 
	 * @return Superficie urbanizable en hectáreas.
	*/
	public double getSuperfSolUrbanitzable_ha(){
		return superfSolUrba_ha;
	}

	/**
	 * Método getter que devuelve la superficie del munnicipio destinado a zonas verdes
	 * 
	 * @return superficie de zonas verdes en hectareas
	 */
	public double getSuperfZonesVerdes_ha(){
		return superfZonesVerdes_ha;
	}






}
