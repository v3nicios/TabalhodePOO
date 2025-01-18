import java.util.ArrayList;
   
public class Pedido {
    public Cliente nomeCliente;
    ArrayList<Produto> produtos;
    public boolean pronta;
    private String tamanho;


    public Pedido ( Cliente nomeCliente, boolean pronta, String tamanho){
    this.nomeCliente = nomeCliente;
    this.produtos = new ArrayList<>();
    this.pronta = false;
    this.tamanho = tamanho;
    
        
    }

    public Cliente getNomePedido(){
       return nomeCliente;
    }


    public void adicionarProduto(Produto produto) {
         if (produto != null) {
            this.produtos.add(produto);
        }
    }

    void marcarPronta() {
        pronta = true;
    }
    public double ValorTotal() {
        double total = 0;
        for (Produto p : produtos) {
            total += p.getValor();
        }
        return total;
    }
    
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void exibirPedido() {
        System.out.println(" Cliente: " + nomeCliente);
        System.out.println("Produtos:");
        for (Produto p : produtos) {
            System.out.println(" - " + p);
        }
         System.out.println("A Pizza esta Pronta: " + (pronta ? "Sim" : "Não"));
    }
}
