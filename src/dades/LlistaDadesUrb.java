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
            duplicado.listaDadesUrb[i] = this.listaDadesUrb[i];
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
            aux = aux + "\n\tDadesUrb " + (i+1) + ":\t" + listaDadesUrb[i];
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
     * total mas grnde de Catalunya.
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
     * Método que consulta si un municipio ha modificado
     * su superficie de suelo urbanizable a lo largo de los años y devolviendo la variación.
     * 
     * 
    */
    public double municipiModSolUrbanitzable(String nomMunicipi){
        
    }

    /**
     * Método que comprueba si hay algun municipio de costa que no tenga suelo urbanizable,
     * 
     * @return primer municipio encontrado o null si no hay ninguno
     */
    public DadesUrb municipiCostaNoSolUrbanitzable(){

        for (int i = 0; i < nDades; i++){
            if (listaDadesUrb[i].getEsMunicipiDeCosta()){
                if (listaDadesUrb[i].getSuperfSolUrbanitzable_ha() == 0){
                    return listaDadesUrb[i];
                }
            }
        }
        return null;
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
