
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Produto> produtos = new ArrayList<>();
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Pedido> pedidos = new ArrayList<>();

        clientes.add(new Cliente("Venicios", "Rua Anari", "6999003266", "Sem complemento"));
        produtos.add(new Produto("Barriga Cheia", "MOLHO, MUSSARELA, BACON, FRANGO, CALABRESA PICADA, MILHO, ERVILHA, CEBOLA, PIMENTÃO, AZEITONA E ORÉGANO", 12.50));
        produtos.add(new Produto("Maria Bonita", "MOLHO, MUSSARELA, CARNE DE SOL, CEBOLA, CATUPIRY, PIMENTA BIQUINHO, AZEITONA E OREGANO", 15.25));
        produtos.add(new Produto("Dengosa", "MOLHO, MUSSARELA, CALABRESA, BACON, MILHO, OVO, TOMATE, AZEITONA E OREGANO", 29.50));

        while (true) {
            try {
                System.out.println("===========================================================================================================================================================");
                System.out.println("\n");
                System.out.println("  #####    ######   ######   ######     ##\r\n"
                        + "  ##  ##     ##        ##       ##     ####\r\n"
                        + "  #####      ##      ##       ##      ##  ##\r\n"
                        + "  ##         ##     ##       ##       ######\r\n"
                        + "  ##       ######   ######   ######   ##  ##\r\n"
                );
                System.out.println("\n ");
                System.out.println("1.  Registrar um sabor de pizza");
                System.out.println("2.  Registrar um cliente");
                System.out.println("3.  Registrar um pedido");
                System.out.println("4.  Marcar um pedido como pronto");
                System.out.println("5.  Listar sabores");
                System.out.println("6.  Listar clientes");
                System.out.println("7.  Pedidos sendo preparadas");
                System.out.println("8.  Pedidos prontas");
                System.out.println("9.  Excluir um sabor de pizza");
                System.out.println("10. Excluir todos os sabores de pizza");
                System.out.println("11. Excluir um cliente");
                System.out.println("12. Excluir todos os clientes");
                System.out.println("13. Excluir um pedido");
                System.out.println("14. Excluir todos os pedidos");
                System.out.println("0.  Sair");
                System.out.println("===========================================================================================================================================================");
                System.out.print("    Escolha: ");
                int select = scanner.nextInt();

                // isso esta aqui pq ficar um buffer apos selicionar uma opção
                scanner.nextLine();

                if (select == 0) {
                    System.out.println("Fim do programa  ");
                    break;
                }

                switch (select) {

                    case 1:

                        System.out.println("===========================================================================================================================================================");

                        System.out.print("Nome do Sabor: ");

                        String nomeProduto = scanner.nextLine();

                        System.out.print("Descrição do Sabor: ");

                        String descricaoProduto = scanner.nextLine();

                        System.out.print("Preço: ");

                        double valorProduto = scanner.nextDouble();

                        produtos.add(new Produto(nomeProduto, descricaoProduto, valorProduto));
                        System.out.println("Produto adicionado!");
                        System.out.println("===========================================================================================================================================================");
                        break;

                    case 2:

                        System.out.println("===========================================================================================================================================================");
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
                        System.out.println("===========================================================================================================================================================");
                        break;

                    case 3:
                        if (clientes.isEmpty() || produtos.isEmpty()) {
                            System.out.println("Cadastre pelo menos um cliente e um produto antes de registrar um pedido");
                            break;
                        }

                        System.out.println("Selecione o Cliente:");
                        for (int i = 0; i < clientes.size(); i++) {
                            System.out.println("===========================================================================================================================================================");
                            System.out.println((i + 1) + ". " + clientes.get(i).getNomeCliente());

                        }
                        int clienteIndex = scanner.nextInt() - 1;
                        scanner.nextLine();

                        if (clienteIndex < 0 || clienteIndex >= clientes.size()) {
                            System.out.println("Opção inválido!");
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
                                System.out.println("Opção inválida! Será considerado tamanho de pizza pequena");
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
                                System.out.println("Sabor adicionado (" + produtosAdicionados + "/" + limiteProdutos + ")");
                            }
                        }

                        if (produtosAdicionados == 0) {
                            System.out.println("Nenhum sabor foi adicionado ao pedido. Registro de pedido cancelado");
                            break;
                        }

                        System.out.println("\nPedido registrado com sucesso!");
                        pedido.exibirPedido();
                        pedidos.add(pedido);
                        System.out.println("===========================================================================================================================================================");
                        break;

                    case 4:
                        if (pedidos.isEmpty()) {
                            System.out.println("Não tem pedidos registrados.");
                            break;
                        }
                        System.out.println("===========================================================================================================================================================");

                        System.out.println("Selecione o pedido para marcar como pronto:");

                        for (int i = 0; i < pedidos.size(); i++) {
                            if (pedidos.get(i).isPronta() == false) {

                                System.out.println((i + 1) + ".Pedido do " + pedidos.get(i).getNomePedido());
                                System.out.println("Tamanho: " + pedidos.get(i).getTamanho());
                                System.out.println("Valor total: R$ " + pedidos.get(i).ValorTotal());
                                System.out.println("Sabores:");
                                for (Produto p : pedidos.get(i).getProdutos()) {
                                    System.out.println(" - " + p.getNome() + " (R$ " + p.getValor() + ")");

                                }
                            } else {
                                System.out.println("===========================================================================================================================================================");
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
                            System.out.println("===========================================================================================================================================================");
                        }
                        break;

                    case 5:
                        if (produtos.isEmpty()) {
                            System.out.println("Não tem sabores cadastrados");
                            break;
                        }
                        System.out.println("===========================================================================================================================================================");
                        System.out.println("Sabores cadastrados:");
                        for (Produto p : produtos) {

                            System.out.println(p);

                            System.out.println("===========================================================================================================================================================");
                        }
                        break;

                    case 6:
                        if (clientes.isEmpty()) {
                            System.out.println("Não tem clientes cadastrados");
                            System.out.println("===========================================================================================================================================================");
                            break;
                        }
                        System.out.println("===========================================================================================================================================================");
                        System.out.println("Clientes cadastrados:");
                        for (Cliente c : clientes) {

                            System.out.println(c);
                            System.out.println("===========================================================================================================================================================");
                        }
                        break;

                    case 7:
                        if (pedidos.isEmpty()) {
                            System.out.println("Nenhum pedido registrado");
                            break;
                        }
                        System.out.println("===========================================================================================================================================================");
                        System.out.println("\n Pizzas sendo preparadas: ");
                        boolean semProntos = false;

                        for (Pedido p : pedidos) {
                            if (p.isPronta() == false) {

                                p.exibirPedido();

                                semProntos = true;

                            }
                        }
                        System.out.println("===========================================================================================================================================================");

                        if (semProntos == false) {
                            System.out.println("Sem pedidos sendo preparados");
                            System.out.println("===========================================================================================================================================================");
                        }
                        break;

                    case 8:
                        if (pedidos.isEmpty()) {
                            System.out.println("Nenhum pedido registrado");

                            break;
                        }
                        System.out.println("===========================================================================================================================================================");

                        System.out.println("\nPedidos que estão prontos:");
                        boolean encontrouProntos = false;

                        for (Pedido p : pedidos) {
                            if (p.isPronta()) {

                                p.exibirPedido();

                                encontrouProntos = true;
                            }
                        }
                        System.out.println("===========================================================================================================================================================");

                        if (encontrouProntos == false) {
                            System.out.println("Não tem pedidos prontos no momento");
                            System.out.println("===========================================================================================================================================================");
                        }
                        break;

                    default:
                        System.out.println("Opção inválida! Tente novamente");
                    case 9:
                        if (produtos.isEmpty()) {

                            System.out.println("Não tem produtos cadastrados");

                            break;
                        }
                        System.out.println("===========================================================================================================================================================");

                        System.out.println("Sabores:");
                        for (int i = 0; i < produtos.size(); i++) {

                            System.out.println((i + 1) + ". " + produtos.get(i).getNome());

                        }
                        System.out.print("Digite o sabor que sera excluir: ");
                        int indiceProduto = scanner.nextInt();

                        if (indiceProduto > 0 && indiceProduto <= produtos.size()) {
                            Produto produtoRemovido = produtos.remove(indiceProduto - 1);
                            System.out.println("Sabor '" + produtoRemovido.getNome() + "' excluído com sucesso");
                        } else {
                            System.out.println("Número inválido. Nenhum Sabor foi excluído");
                            System.out.println("===========================================================================================================================================================");
                        }
                        break;

                    case 10:
                        if (produtos.isEmpty()) {

                            System.out.println("Não tem sabores cadastrados");

                            break;
                        }
                        System.out.println("===========================================================================================================================================================");

                        System.out.println("Você quer excluir todos os sabores?");
                        System.out.println("1. Sim");
                        System.out.println("2. Não");
                        int confirmar = scanner.nextInt();

                        if (confirmar == 1) {
                            produtos.clear();
                            System.out.println("Todos os sabores foram excluídos");
                            System.out.println("===========================================================================================================================================================");
                        } else if (confirmar == 2) {
                            System.out.println("Nenhum sabor foi excluído");
                            System.out.println("===========================================================================================================================================================");
                        } else {
                            System.out.println("Opção inválida! Nenhum sabor foi excluído");
                            System.out.println("===========================================================================================================================================================");
                        }
                        break;

                    case 11:
                        if (clientes.isEmpty()) {
                            System.out.println("===========================================================");
                            System.out.println("Não tem clientes cadastrados");
                            System.out.println("===========================================================");
                            break;
                        }
                        System.out.println("===========================================================================================================================================================");

                        System.out.println("Clientes");
                        for (int i = 0; i < clientes.size(); i++) {

                            System.out.println((i + 1) + ". " + clientes.get(i).getNomeCliente());

                        }
                        System.out.print("Digite o cliente que sera excluir: ");
                        int indiceClientes = scanner.nextInt();

                        if (indiceClientes > 0 && indiceClientes <= clientes.size()) {
                            Cliente clienteRemovido = clientes.remove(indiceClientes - 1);
                            System.out.println("Cliente '" + clienteRemovido.getNomeCliente() + "' excluído com sucesso");
                            System.out.println("===========================================================================================================================================================");
                        } else {
                            System.out.println("Número inválido. Nenhum cliente foi excluído");
                            System.out.println("===========================================================================================================================================================");
                        }
                        break;

                    case 12:
                        if (clientes.isEmpty()) {
                            System.out.println("Não tem clientes cadastrados");
                            break;
                        }
                        System.out.println("===========================================================================================================================================================");

                        System.out.println("Você quer excluir todos os clientes?");
                        System.out.println("1. Sim");
                        System.out.println("2. Não");
                        int confirma = scanner.nextInt();

                        if (confirma == 1) {
                            clientes.clear();
                            System.out.println("Todos os clientes foram excluídos");
                            System.out.println("===========================================================================================================================================================");
                        } else if (confirma == 2) {
                            System.out.println("Nenhum sabor foi excluído");
                            System.out.println("===========================================================================================================================================================");
                        } else {
                            System.out.println("Opção inválida! Nenhum clientes foi excluído");
                            System.out.println("===========================================================================================================================================================");
                        }
                        break;

                    case 13:
                        if (pedidos.isEmpty()) {
                            System.out.println("===========================================================");
                            System.out.println("Não tem pedidos cadastrados");
                            System.out.println("===========================================================");
                            break;
                        }
                        System.out.println("===========================================================================================================================================================");

                        System.out.println("Pedidos");
                        for (int i = 0; i < pedidos.size(); i++) {

                            System.out.println((i + 1) + ". " + pedidos.get(i).getNomePedido());

                        }
                        System.out.print("Digite o cliente que sera excluir: ");
                        int indicePedidos = scanner.nextInt();

                        if (indicePedidos > 0 && indicePedidos <= pedidos.size()) {
                            Pedido pedidoRemovido = pedidos.remove(indicePedidos - 1);
                            System.out.println("Pedido " + pedidoRemovido.getNomePedido() + " excluído com sucesso");
                            System.out.println("===========================================================================================================================================================");
                        } else {
                            System.out.println("Número inválido. Nenhum pedido excluido foi excluído");
                            System.out.println("===========================================================================================================================================================");
                        }
                        break;

                    case 14:
                        if (pedidos.isEmpty()) {
                            System.out.println("Não tem pedidos cadastrados");
                            break;
                        }
                        System.out.println("===========================================================================================================================================================");

                        System.out.println("Você quer excluir todos os pedidos?");
                        System.out.println("1. Sim");
                        System.out.println("2. Não");
                        int confirm = scanner.nextInt();

                        if (confirm == 1) {
                            pedidos.clear();
                            System.out.println("Todos os pedidos foram excluídos");
                            System.out.println("===========================================================================================================================================================");
                        } else if (confirm == 2) {
                            System.out.println("Nenhum sabor foi excluído");
                            System.out.println("===========================================================================================================================================================");
                        } else {
                            System.out.println("Opção inválida! Nenhum pedido foi excluído");
                            System.out.println("===========================================================================================================================================================");
                        }
                        break;

                }
            } catch (InputMismatchException e) {

                System.out.println("Entrada inválida. Por favor, insira um número");

                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: Foi mal mano vou tratar esse erro " + e.getMessage());
            }

        }

        scanner.close();
    }
}
