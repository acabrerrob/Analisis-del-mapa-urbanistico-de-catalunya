package dades;

public class LlistaDadesUrb {
    private DadesUrb[] listaDadesUrb;
    private int nDades;


    /**
     * Constructor de LlistaDadesUrb que inicializa la tabla para guardar las DadesUrb
     * y inicializa la cantidad de datos de la tabla en 0
     * 
     * @param dimension (int) Tamaño de la lista para guardar DadesUrb
     */
    public LlistaDadesUrb(int dimension){
        listaDadesUrb = new DadesUrb[dimension];
        this.nDades = 0;
    }

    /**
     * Método que devuelve el numero total de DadesUrb en la lista
     * 
     * @return total de DadesUrb en la lista
     */
    public int getNDades(){
        return nDades;
    }

    /**
     * Método que devuelve los datos de una cierta posicion pasada por parametro,
     * si la posicion es mayor que el total de datos en la tabla o menor que 0, devuelve null
     * 
     * @param n (int) posicion de la tabla de la que se quieren obtener los datos
     * @return datos de la posicion n, o null si no existe dicha posicion
     */
    public DadesUrb enesimaDadesUrb (int n){
        if ((n < nDades) && (n >= 0)){
            return listaDadesUrb[n].copia();
        }
        return null;
    }

    /**
     * Método que crea y devuelve un duplicado de la instancia actual
     * 
     * @return copia de la intancia
     */
    public LlistaDadesUrb copia(){
        LlistaDadesUrb duplicado = new LlistaDadesUrb(listaDadesUrb.length);

        duplicado.nDades = this.nDades;
        
        for (int i = 0; i < this.nDades; i++){
            duplicado.listaDadesUrb[i] = this.listaDadesUrb[i].copia();
        }
        return duplicado;
    }

    /**
     * Metodo que devuelve una string de todos los datos urbanisticos de distintas poblaciones
     * y anualidades
     * 
     * @return Cadena de texto con todos los datos de la instancia actual
     */
    public String toString(){
        String aux = "Listado de datos => nDades " + nDades;

        for (int i = 0; i < nDades; i++){
            aux = aux + "\n\t" + listaDadesUrb[i];
        }
        return aux;
    }

    /**
     * Método para añadir una nueva instancia de DadesUrb al final de la lista solo si cabe,
     * si no cabe no se añade
     * 
     * @param datos (DadesUrb) instancia a añadir en la tabla
     */
    public void afegirDadesUrb (DadesUrb datos){
        if (nDades < listaDadesUrb.length){
            listaDadesUrb[nDades] = datos.copia();
            nDades++;
        }
    }

    /**
     * Método que consulta los datos del municipio que tiene la superficie                                          MÉTODO 2
     * total mas grande de Catalunya.
     * 
     * @return Intancia de datos identificada (si coinciden en valor, 
     * la primera de ellas) o null si no hay ningún elemento.
    */
    public DadesUrb municipiSuperfMesGran() {
        //Comprobación de que la lista no esta vacia.
        if(nDades == 0){
            return null;    
        }

        int mesGran = 0;

        for (int i = 0 ; i < nDades ; i++){
            if ( listaDadesUrb[mesGran].getSuperficie_ha() < listaDadesUrb[i].getSuperficie_ha()) {
                mesGran = i;
            }
        }
        return listaDadesUrb[mesGran].copia();       
    }

    /**
     * Método que comprueba cual es el porcentaje de Zonas Verdes de cada municipio (instancia)
     * y devuelve el mas grande de la lista. Si la lista esta vacía, devuelve null
     * 
     * @return instancia de datos encontrada (si hay dos iguales, devuelve la primera)
     * y si la lista esta vacía null.
     */
    public DadesUrb percZonesVerdesMesGran(){
        //comprobar que no sea una instancia vacía
        if(nDades == 0){
            return null;    
        }

        int mesGran = 0;
        
        for (int i = 0; i < nDades; i++){
            if (percZonesVerdes(listaDadesUrb[mesGran]) < percZonesVerdes(listaDadesUrb[i])){
                mesGran = i;
            }
        }
        return listaDadesUrb[mesGran].copia();
    }

    /**
     * Método que consulta si un municipio ha modificado su superficie de suelo                                 MÉTODO 4
     * urbanizable a lo largo de los años y devolviendo la variación.
     * 
     * @param nomMunicipi nombre del municipio.
     * @return variación del suelo urbanizable del municipio entre los años.
    */
    public double municipiModSolUrbanitzable(String nomMunicipi){
        double superfIni = 0;
        double superfFin = 0;
        boolean encontrado = false;

        //Recorro la lista de esta clase
        for (int i = 0; i < listaDadesUrb.length ; i++){        

            //Entra si el nombre de la lista es del municipio que buscamos.
            if (listaDadesUrb[i].getNomMunicipi().equalsIgnoreCase(nomMunicipi)){    
                //La primera vez que encuentre el municipio será el primer año.
                if(!encontrado){                                            
                    superfIni = listaDadesUrb[i].getSuperfSolUrbanitzable_ha();
                    encontrado = true;
                }
                //Se actualizara hasta la última vez que encuentre el municipio y este será  el último.
                superfFin = listaDadesUrb[i].getSuperfSolUrbanitzable_ha();
            }
        }
        return superfFin - superfIni;
    }

    /**
     * Método que comprueba si hay algun municipio de costa que no tenga suelo urbanizable,
     * 
     * @return primer municipio encontrado o null si no hay ninguno
     */
    public DadesUrb municipiCostaNoSolUrbanitzable(){

        for (int i = 0; i < nDades; i++){
            if ((listaDadesUrb[i].getEsMunicipiDeCosta()) && 
                (listaDadesUrb[i].getSuperfSolUrbanitzable_ha() == 0)){
                return listaDadesUrb[i];
            }
        }
        return null;
    }

    /**
     * Método que retorna un array con los municipio que                            MÉTODO 6
     * superan el umbral de densidad de población por km 
     * cuadrado indicado por parámetro.
     * 
     * @param vMinDensitat
     * @return
     */
    public String[] municipiMesDensos(double vMinDensitat){
        //Variable para medida total del array del return.
        int mida = 0;
        //Creo una instancia de array string con la misma longitud que la del array de la clase
        String[] listaTemporal = new String[nDades];

        double densidadPob;
        //Recorro la lista de la clase en busca de la cantidad de municipios que cumplen con las características para entrar en el array resultado.
        for(int i = 0 ; i < listaDadesUrb.length ; i++){
            
            //Fórmula de dennsidad
            densidadPob = listaDadesUrb[i].getNumHabitants() * 100 / listaDadesUrb[i].getSuperficie_ha();
            
            //Solo entra si cumple el requisito del valor mínimo de densidad
            if(densidadPob > vMinDensitat){

                //Variable para comprobar repeticiones
                boolean repetido = false;
                int j = 0;
                //Comprobar que NO está en el array
                while(!repetido && j < listaTemporal.length){

                    //Condición para encontrar repetido
                    if(listaTemporal[j].equals(listaDadesUrb[i].getNomMunicipi())){
                        repetido = true;
                    }else{
                        mida += 1;
                        j++;
                    }
                }
                if(!repetido){
                    listaTemporal[mida - 1] = listaDadesUrb[i].getNomMunicipi();
                }
            }
        }

        String[] listaMesDensos = new String [mida];
        
        return listaMesDensos;
    }




    /**
     * Método para guardar los datos de un unico municipio, pasado por parametro,
     * en una nueva lista de datos creada
     * 
     * @param municipi nombre del municipio de los datos a guardar
     * @return nueva lista de datos con los datos del municipio pasado por parametro,
     * si no hay ningun dato del municipio, devuelve null
     */
    public LlistaDadesUrb dadesUnMunicipi(String municipi){
        int aux = 0;        //auxiliar para crear la tabla
        for (int i = 0; i < this.nDades; i++){      //bucle para saber el tamaño de la nueva lista
            if (this.listaDadesUrb[i].getNomMunicipi().equalsIgnoreCase(municipi)){
                aux++;
            }
        }

        if ((aux == 0) || (this.nDades == 0)){      //en caso de que no haya ningun dato del municipio, devuelve null
            return null;
        }

        LlistaDadesUrb dadesMunicipi = new LlistaDadesUrb(aux);
        for (int i = 0; i < this.nDades; i++){
            if (this.listaDadesUrb[i].getNomMunicipi().equalsIgnoreCase(municipi)){
                dadesMunicipi.afegirDadesUrb(this.listaDadesUrb[i]);
            }
        }
        return dadesMunicipi;
    }





    /**
     * Método que elimina todo el conjunto de datos de un municipio sin dejar posiciones
     * vacías en la lista
     * 
     * @param municipi nombre del municipio del que se quieren borrar los datos
     */
    public void eliminaMunicipi(String municipi){
        int i = 0;
        while (i < nDades){
            if (listaDadesUrb[i].getNomMunicipi().equalsIgnoreCase(municipi)){
                for (int j = i; j < (nDades-1); j++){
                    listaDadesUrb[j] = listaDadesUrb[j+1];
                }
                nDades--;
                listaDadesUrb[nDades] = null;
            }
            else {
                i++;
            }
        }
    }

    //Metodos private

    /**
     * Método que calcula el porcentaje de superficie de una instancia dedicado a
     * las zonas verdes
     * 
     * @param dades instancia de la cual se calcula el porcentaje
     * @return porcentaje de zonas verdes en funcion de su superficie
     */
    private double percZonesVerdes(DadesUrb dades){
        return ((dades.getSuperfZonesVerdes_ha()/dades.getSuperficie_ha())*100);
    }

    
}
