
public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String categoria;
    private int numHojas;
    private int stock;

    public Libro() {}

    public Libro(int id, String titulo, String autor, String categoria, int numHojas, int stock) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.numHojas = numHojas;
        this.stock = stock;
    }

    // getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public int getNumHojas() { return numHojas; }
    public void setNumHojas(int numHojas) { this.numHojas = numHojas; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}