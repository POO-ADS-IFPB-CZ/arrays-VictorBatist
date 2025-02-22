package view;

import model.Categoria;
import model.Item;
import model.Produto;
import model.Venda;
import java.util.Scanner;


public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Venda venda = new Venda();

    public static void main(String[] args) {
        int opcao = 0;

        do{
            exibirMenuNaTela();
            opcao = scanner.nextInt();
            processarOpcoes(opcao);
        }while (opcao!=5);

    }

    private static void exibirMenuNaTela() {
        System.out.println("\nSeja bem-vindo a Lista de Compras do Mercado do Povo");
        System.out.println("-----------------------------------------------------------");
        System.out.println("Escolha uma das opções a seguir:\n");
        System.out.println("1 - Adicionar Novo Produto");
        System.out.println("2 - Remover Produto");
        System.out.println("3 - Total das Compras");
        System.out.println("4 - Exibir Lista de Produtos");
        System.out.println("5 - Sair");
    }

    private static void processarOpcoes(int opcao) {
        switch (opcao) {
            case 1:
                adicionarProduto();
                break;
            case 2:
                removerProduto();
                break;
            case 3:
                totalVenda();
                break;
            case 4:
                exibirItens();
                break;
            case 5:
                System.out.println("\nEncerrando programa...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void exibirItens() {
        System.out.println("\nItens a Venda:");
        Item[] itens = venda.getItens();
        if (itens.length == 0) {
            System.out.println("OPS!\n\nNão há itens registrados");
            return;
        }
        for (Item item : itens) {
            System.out.printf("Código: %d | Nome: %S | Preço: %.2f | Quantidade: %.2f | Subtotal: %.2f%n",
                    item.getCodigo(), item.getProduto().getDescricao(), item.getProduto().getPreco(),
                    item.getQuantidade(), item.getSubtotal());
        }
    }

    private static void adicionarProduto() {
        Produto produto = lerProduto();
        System.out.print("\nInforme a quantidade do produto: ");
        double quantidade = scanner.nextDouble();
        Item item = new Item(venda.getItens().length + 1, produto, quantidade);
        venda.adicionarItem(item);
        System.out.println("\nObrigado pelas informações.\nO produto foi registrado com sucesso!");
    }

    private static Produto lerProduto() {
        System.out.print("\nInforme o Código do Produto:\n ");
        int codigo = scanner.nextInt();

        System.out.print("\nInforme a Descrição do produto:\n ");
        String descricao = scanner.nextLine();
        scanner.nextLine();

        System.out.print("\nInforme o Preço do produto:\n ");
        float preco = (float) scanner.nextDouble();

        System.out.println("\nEscolha a categoria do produto:\n\n 1.ALIMENTICIO | 2.HIGIENE | 3.LIMPEZA | 4.HORTIFRUTI | 5.PADARIA:\n ");
        int categoriaEscolhida = scanner.nextInt();
        Categoria categoria = Categoria.values()[--categoriaEscolhida];

        return new Produto(codigo, descricao, preco, categoria);
    }

    private static void removerProduto() {
        System.out.print("\nInforme o código do produto que deseja remover:\n");
        int codigo = scanner.nextInt();

        boolean encontrado = false;
        for (int i = 0; i < venda.getItens().length; i++) {
            if (venda.getItens()[i].getProduto().getCodigo() == codigo) {
                venda.removerItem(i);
                encontrado = true;
                return;
            }
        }
    }

    private static void totalVenda() {
        System.out.printf("\nO total das suas compras foi:\n\n %.2f%n", venda.getTotal());
    }
}