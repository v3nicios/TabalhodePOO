import java.util.ArrayList;
import java.util.Scanner;

public class TrabalhoFinalPOO {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Produto> produtos = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();
        
        while (true) {
            // Exibe o menu.
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Adicionar Cliente");
            System.out.println("3. Registrar Pedido");
            System.out.println("4. Marcar Venda como Pronta");
            System.out.println("5. Listar Produtos");
            System.out.println("6. Listar Clientes");
            System.out.println("7. Exibir Pedidos");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int select = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer.

            if (select == 0) {
                System.out.println("Encerrando o programa...");
                break;
            }

            switch (select) {
                case 1: // Adicionar Produto
                    System.out.println("Escolha o nome do produto:  ");
                    String nomeProduto = scanner.nextLine();
                    System.out.println("Descrição do produto: ");
                    String descricaoProduto = scanner.nextLine();
                    System.out.println("Preço do Produto: ");
                    double valorProduto = scanner.nextDouble();
                    produtos.add(new Produto( nomeProduto, descricaoProduto , valorProduto));
                    System.out.println("Produto adicionado!");
                    break;

                    case 2: // Adicionar Cliente
                    System.out.print("Nome do Cliente: ");
                    String nomeCliente = scanner.nextLine();
                    System.out.print("Endereço completo Rua/Numero/Bairro : ");
                    String enderecoCliente = scanner.nextLine();
                    System.out.print("Telefone por favor incluir o DDD ");
                    int telefoneCliente = scanner.nextInt();
                    System.out.print("Complemento do endereço ");
                    String complementoCliente = scanner.nextLine();
                    clientes.add(new Cliente(nomeCliente,enderecoCliente,telefoneCliente,complementoCliente));
                    System.out.println("Cliente adicionado!");
                    break;    


                    case 3: // Registrar Venda
                    if (clientes.isEmpty() || produtos.isEmpty()) {
                        System.out.println("Cadastre pelo menos um cliente e um produto antes de registrar uma venda.");
                        break;
                    }

                    System.out.println("Selecione o Cliente:");
                    for (int i = 0; i < clientes.size(); i++) {
                        System.out.println((i + 1) + ". " + clientes.get(i).getNomeCliente());
                    }
                    int clienteIndex = scanner.nextInt() - 1;
                    scanner.nextLine();

                    if (clienteIndex < 0 || clienteIndex >= clientes.size()) {
                        System.out.println("Cliente inválido!");
                        break;
                    }

                    System.out.print("A venda é para entrega? (Sim/Não): ");
                    String entregaStr = scanner.nextLine();
                    boolean entrega = entregaStr.equalsIgnoreCase("Sim");

                    System.out.println("Escolha a forma de pagamento:");
                    System.out.println("1. Dinheiro");
                    System.out.println("2. Pix");
                    System.out.println("3. Cartão");
                    int pagamentoOpcao = scanner.nextInt();
                    scanner.nextLine(); // Limpa o buffer.

                    String formadepagamento = switch (pagamentoOpcao) {
                        case 1 -> "Dinheiro";
                        case 2 -> "Pix";
                        case 3 -> "Cartão";
                        default -> {
                            System.out.println("Forma de pagamento inválida! Será usado 'Dinheiro'.");
                            yield "Dinheiro";
                        }
                    
                    };

                    boolean pronta = false;
                        Pedido pedido = new Pedido (clientes.get(clienteIndex),formadepagamento, entrega, pronta);

                    System.out.println("Selecione os Produtos (0 para finalizar):");
                    for (int i = 0; i < produtos.size(); i++) {
                        System.out.println((i + 1) + ". " + produtos.get(i).getNome());
                    }

                    while (true) {
                        int produtoIndex = scanner.nextInt() - 1;
                        if (produtoIndex == -1) break;
                        if (produtoIndex < 0 || produtoIndex >= produtos.size()) {
                            System.out.println("Produto inválido!");
                        } else {
                            pedido.adicionarProduto(produtos.get(produtoIndex));
                            System.out.println("Produto adicionado!");
                        }
                    }

                    pedidos.add(pedido);
                    System.out.println("Pedido registrada!");
                    break;




            }




        }


        scanner.close();
    }
}