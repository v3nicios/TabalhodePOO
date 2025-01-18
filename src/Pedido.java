import java.util.ArrayList;
   
public class Pedido {
    public Cliente nomeCliente;
    ArrayList<Produto> produtos;
    public String formadepagamento;
    public boolean entrega;
    public boolean pronta;

    public Pedido ( Cliente nomeCliente, String formadepagamento, boolean entrega, boolean pronta){
    this.nomeCliente = nomeCliente;
    this.formadepagamento = formadepagamento;
    this.entrega = entrega;
    this.pronta = false;
    
        
    }

    void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    void marcarPronta() {
        pronta = true;
    }

    void exibirVenda() {
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("Produtos:");
        for (Produto p : produtos) {
            System.out.println(" - " + p);
        }
        System.out.println("Para entregar: " + (entrega ? "Sim" : "Não"));
        System.out.println("Forma de pagamento: " + formadepagamento);
        System.out.println("Pronta: " + (pronta ? "Sim" : "Não"));
    }
}
