public class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double valor;
    private String entrega;

    public Produto(int id, String nome, String descricao, double valor, boolean entrega) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.entrega = entrega;

    }

    public int getID() {

        return id;
    }

    public String getNome() {

        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public String getEntrega() {
        return entrega;
    }

}
