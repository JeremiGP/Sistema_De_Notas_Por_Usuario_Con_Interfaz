package Model;

/**
 * Clase que representa una nota
 */
public class Nota {
    // Atributos de la clase
    private String titulo;
    private String contenido;

    /**
     * Constructor de la clase
     * 
     * @param titulo    Titulo de la nota
     * @param contenido Contenido de la nota
     */
    public Nota(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }

    /**
     * Metodo que obtiene el titulo de la nota
     * 
     * @return Titulo de la nota
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Metodo que establece el titulo de la nota
     * 
     * @param titulo Titulo de la nota
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Metodo que obtiene el contenido de la nota
     * 
     * @return Contenido de la nota
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Metodo que establece el contenido de la nota
     * 
     * @param contenido Contenido de la nota
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    /**
     * Metodo que convierte la nota a string
     * 
     * @return String que representa la nota
     */
    @Override
    public String toString() {
        return titulo;
    }
}