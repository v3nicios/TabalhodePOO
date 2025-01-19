
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TrabalhoFinalPOO {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Produto> produtos = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();

        clientes.add(new Cliente("Venicios", "Rua Anari", "6999003266", "Sem complemento"));
        produtos.add(new Produto("Barriga Cheia", "MOLHO, MUSSARELA, BACON, FRANGO, CALABRESA PICADA, MILHO, ERVILHA, CEBOLA, PIMENTÃO, AZEITONA E ORÉGANO", 12.50));
        produtos.add(new Produto("Maria Bonita", "MOLHO, MUSSARELA, CARNE DE SOL, CEBOLA, CATUPIRY, PIMENTA BIQUINHO, AZEITONA E OREGANO", 15.25));

        while (true) {
            try {
                System.out.println("\n");
                System.out.println("  #####    ######   ######   ######     ##\r\n"
                        + "  ##  ##     ##        ##       ##     ####\r\n"
                        + "  #####      ##      ##       ##      ##  ##\r\n"
                        + "  ##         ##     ##       ##       ######\r\n"
                        + "  ##       ######   ######   ######   ##  ##\r\n"
                );
                System.out.println("Menu ");
                System.out.println("1. Adicionar sabor de pizza");
                System.out.println("2. Adicionar Cliente");
                System.out.println("3. Registrar Pedido");
                System.out.println("4. Marcar Pedido como Pronto");
                System.out.println("5. Listar Sabores");
                System.out.println("6. Listar Clientes");
                System.out.println("7. Pizzas sendo preparadas");
                System.out.println("8. Pizzas prontas");
                System.out.println("0. Sair");
                System.out.print("Escolha: ");
                int select = scanner.nextInt();
                scanner.nextLine();
                System.out.print("\n ");

                if (select == 0) {
                    System.out.println("Encerrando o programa...");
                    break;
                }

                switch (select) {
                    case 1:
                        System.out.print("Nome do Produto: ");
                        String nomeProduto = scanner.nextLine();
                        System.out.print("Descrição do Produto: ");
                        String descricaoProduto = scanner.nextLine();
                        System.out.print("Preço do Produto: ");
                        double valorProduto = scanner.nextDouble();
                        produtos.add(new Produto(nomeProduto, descricaoProduto, valorProduto));
                        System.out.println("Produto adicionado!");
                        break;

                    case 2:
                        System.out.print("Nome do Cliente: ");
                        String nomeCliente = scanner.nextLine();
                        System.out.print("Endereço completo (Rua/Numero/Bairro): ");
                        String enderecoCliente = scanner.nextLine();
                        System.out.print("Telefone (com DDD): ");
                        String telefoneCliente = scanner.nextLine();
                        System.out.print("Complemento do endereço: ");
                        String complementoCliente = scanner.nextLine();
                        clientes.add(new Cliente(nomeCliente, enderecoCliente, telefoneCliente, complementoCliente));
                        System.out.println("Cliente adicionado!");
                        break;

                    case 3:
                        if (clientes.isEmpty() || produtos.isEmpty()) {
                            System.out.println("Cadastre pelo menos um cliente e um produto antes de registrar um pedido.");
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

                        System.out.println("Escolha o tamanho da pizza:");
                        System.out.println("1. Pequena (2 sabores)");
                        System.out.println("2. Média (3 sabores)");
                        System.out.println("3. Grande (3 sabores)");
                        int tamanhoEscolha = scanner.nextInt();
                        scanner.nextLine();

                        String tamanhoPedido = switch (tamanhoEscolha) {
                            case 1 ->
                                "Pequena";
                            case 2 ->
                                "Média";
                            case 3 ->
                                "Grande";
                            default -> {
                                System.out.println("Opção inválida! Será considerado tamanho de pizza pequena.");
                                yield "Pequena";
                            }
                        };

                        int limiteProdutos = tamanhoPedido.equals("Pequena") ? 2 : 3;

                        Pedido pedido = new Pedido(clientes.get(clienteIndex), false, tamanhoPedido);

                        System.out.println("Selecione os sabores (0 para finalizar):");
                        for (int i = 0; i < produtos.size(); i++) {
                            System.out.println((i + 1) + ". " + produtos.get(i).getNome());
                        }

                        int produtosAdicionados = 0;

                        while (produtosAdicionados < limiteProdutos) {
                            int produtoIndex = scanner.nextInt() - 1;
                            if (produtoIndex == -1) {
                                break;
                            }
                            if (produtoIndex < 0 || produtoIndex >= produtos.size()) {
                                System.out.println("Sabor inválido!");
                            } else {
                                pedido.adicionarProduto(produtos.get(produtoIndex));
                                produtosAdicionados++;
                                System.out.println("Sabor adicionado! (" + produtosAdicionados + "/" + limiteProdutos + ")");
                            }
                        }

                        if (produtosAdicionados == 0) {
                            System.out.println("Nenhum sabor foi adicionado ao pedido. Registro de pedido cancelado.");
                            break;
                        }

                        System.out.println("\nPedido registrado com sucesso!");
                        pedido.exibirPedido();
                        pedidos.add(pedido);
                        break;

                    case 4:
                        if (pedidos.isEmpty()) {
                            System.out.println("Não há pedidos registrados.");
                            break;
                        }

                        System.out.println("Selecione o Pedido para marcar como pronto:");
                    
                        
                        for (int i = 0; i < pedidos.size(); i++) {
                             if (pedidos.get(i).isPronta() == false){
                            System.out.println((i + 1) + ".Pedido " + pedidos.get(i).getNomePedido());
                            System.out.println("Tamanho: " + pedidos.get(i).getTamanho());
                            System.out.println("Valor total: R$ " + pedidos.get(i).ValorTotal());
                            System.out.println("Sabores:");
                            for (Produto p : pedidos.get(i).getProdutos()) {
                                System.out.println(" - " + p.getNome() + " (R$ " + p.getValor() + ")");
                            }}

                            else{
                                System.out.println("Não tem pedidos para marcar como pronto ");
                            }
                            System.out.println();
                        }

                        int pedidoIndex = scanner.nextInt() - 1;

                        if (pedidoIndex < 0 || pedidoIndex >= pedidos.size()) {
                            System.out.println("Pedido inválido!");
                        } else {
                            pedidos.get(pedidoIndex).marcarPronta();
                            System.out.println("Pedido marcado como pronto!");
                        }
                        break;

                    case 5:
                        System.out.println("Produtos cadastrados:");
                        for (Produto p : produtos) {
                            System.out.println("===========================================================");
                            System.out.println(p);
                            System.out.println("===========================================================");
                        }
                        break;

                    case 6:
                        System.out.println("Clientes cadastrados:");
                        for (Cliente c : clientes) {
                            System.out.println("===========================================================");
                            System.out.println(c);
                            System.out.println("===========================================================");
                        }
                        break;

                    case 7:
                        if (pedidos.isEmpty()) {
                            System.out.println("Nenhum pedido registrado.");
                            break;
                        }

                        System.out.println("\n Pizzas sendo preparadas: ");
                        boolean semProntos = false;

                        for (Pedido p : pedidos) {
                            if (p.isPronta() == false) {
                                System.out.println("===========================================================");
                                p.exibirPedido();
                                System.out.println("===========================================================");
                                semProntos = true;
                            }
                        }

                        if (semProntos == false) {
                            System.out.println("Sem pedidos sendo preparados.");
                        }
                        break;
                        
                    case 8: 
                        if (pedidos.isEmpty()) {
                            System.out.println("Nenhum pedido registrado.");
                            break;
                        }

                        System.out.println("\nPedidos que estão prontos:");
                        boolean encontrouProntos = false;

                        for (Pedido p : pedidos) {
                            if (p.isPronta()) { 
                                System.out.println("===========================================================");
                                p.exibirPedido();
                                encontrouProntos = true;
                            }
                        }

                        if (encontrouProntos == false) {
                            System.out.println("Não há pedidos prontos no momento.");
                        }
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
