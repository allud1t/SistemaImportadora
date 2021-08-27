package menu;

import estoque.Produto;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static principal.AplicacaoImportadora.posicaoAtual;
import static principal.AplicacaoImportadora.produtoList;

public class Menu {

    //Nome da empresa e do sistema
    private static void saudacao() {
        System.out.println("\n\tEMPRESA DE IMPORTAÇÃO DE PRODUTOS LTDA.\n");
        System.out.println("\tSISTEMA DE CONTROLE DE ESTOQUE\n");
    }

    //Solicita e lê os dados dos produtos
    public static Produto dadosProdutos() {
        Scanner ler = new Scanner(System.in);
        System.out.println("INFORME O NOME DO PRODUTO: " + "\n" +
                "(obs: caso nome informado já cadastrado, outro será solicitado ao final).");
        String nome = ler.nextLine();
        System.out.println("INFORME O PREÇO UNITÁRIO: " + "\n" +
                "(obs: o preço do produto deve utilizar ponto na casa decimal ex: 1.99).");
        ler.useLocale(Locale.US);
        float precoUnitario = ler.nextFloat();
        ler.nextLine(); //Esvaziar buffer do teclado
        System.out.println("INFORME A UNIDADE DE MEDIDA: ");
        String unidade = ler.nextLine();
        System.out.println("INFORME A QUANTIDADE EM ESTOQUE: ");
        int qtdEstoque = ler.nextInt();

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setPrecoUnitario(precoUnitario);
        produto.setUnidade(unidade);
        produto.setQtdEstoque(qtdEstoque);
        return produto;
    }

    //Repetição dos menus
    public void telaInicial() {
        int opcao;
        do {
            menuPrincipal();
            opcao = escolhaMenu();
            switch (opcao) {
                case 0:
                    System.out.println("ENCERRANDO O SISTEMA...");
                    break;
                case 1:
                    menuCadastroProdutos();
                    break;
                case 2:
                    menuMovimentacao();
                    break;
                case 3:
                    menuReajustePreco();
                    break;
                case 4:
                    relatorioProdutos();
                    break;
                default:
                    opcaoInvalida();
                    break;
            }
        } while (opcao != 0);
    }

    //Menu principal
    private void menuPrincipal() {
        saudacao();
        System.out.println("------------------MENU PRINCIPAL------------------");
        System.out.println("[1] CADASTRO DE PRODUTOS\n" +
                "[2] MOVIMENTAÇÃO DE PRODUTOS\n" +
                "[3] REAJUSTE DE PREÇOS\n" +
                "[4] RELATÓRIO\n\n" +
                "[0] ENCERRAR PROGRAMA");
        System.out.println("--------------------------------------------------");
    }

    //Menu para cadastramento dos produtos
    private void menuCadastroProdutos() {
        int opcao;
        do {
            saudacao();
            System.out.println("---------------CADASTRO DE PRODUTOS---------------");
            System.out.println("[1] INCLUSÃO\n" +
                    "[2] ALTERAÇÃO\n" +
                    "[3] CONSULTA\n" +
                    "[4] EXCLUSÃO\n\n" +
                    "[0] RETORNAR AO MENU ANTERIOR");
            System.out.println("--------------------------------------------------");
            opcao = escolhaMenu();
            switch (opcao) {
                case 1:
                    incluirProduto();
                    break;
                case 2:
                    alterarProduto();
                    break;
                case 3:
                    consultarProduto();
                    break;
                case 4:
                    excluirProduto();
                    break;
                case 0:
                    menuPrincipal();
                    break;
                default:
                    opcaoInvalida();
                    break;
            }
        } while (opcao != 0);
    }

    //Adiciona o Produto
    private void incluirProduto() {
        String escolha;
        do {
            saudacao();
            System.out.println("---------------INCLUSÃO DE PRODUTO---------------");
            Produto produto = dadosProdutos();
            System.out.println("-------------------------------------------------");
            escolha = confirmarOperacao();
            if (escolha.equalsIgnoreCase("s")) {
                produtoList.add(produto);
                posicaoAtual++;
            }
            escolha = repetirOperacao();
        } while (escolha.equalsIgnoreCase("s"));
    }

    //Método para alterar produto
    private void alterarProduto() {
        String escolha;
        do {
            Scanner ler = new Scanner(System.in);
            saudacao();
            System.out.println("---------------ALTERAÇÃO DE PRODUTO---------------");
            System.out.println("INFORME O NOME DO PRODUTO PARA ALTERAR: ");
            String nomeConsulta = ler.nextLine();
            System.out.println("--------------------------------------------------");
            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (Objects.equals(produtoList.get(i).getNome().toLowerCase(), nomeConsulta.toLowerCase())) {
                    controle = false;
                    System.out.println("\nPRODUTO ENCONTRADO\n");
                    String nome = produtoList.get(i).getNome();
                    System.out.println("INFORME O PREÇO UNITÁRIO: " + "\n" +
                            "(obs: o preço do produto deve utilizar ponto na casa decimal ex: 1.99).");
                    ler.useLocale(Locale.US);
                    float precoUnitario = ler.nextFloat();
                    ler.nextLine(); //Esvaziar buffer do teclado
                    System.out.println("INFORME A UNIDADE DE MEDIDA: ");
                    String unidade = ler.nextLine();
                    System.out.println("INFORME A QUANTIDADE EM ESTOQUE: ");
                    int qtdEstoque = ler.nextInt();
                    System.out.println("--------------------------------------------------");
                    escolha = confirmarOperacao();
                    if (escolha.equalsIgnoreCase("s")) {
                        Produto produtoSubstituir = new Produto();
                        produtoSubstituir.setNome("Vazio");
                        produtoList.set(i, produtoSubstituir);
                        Produto produto = new Produto();
                        produto.setNome(nome);
                        produto.setPrecoUnitario(precoUnitario);
                        produto.setUnidade(unidade);
                        produto.setQtdEstoque(qtdEstoque);
                        produtoList.add(i, produto);
                        produtoList.remove(i + 1);
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = repetirOperacao();
        } while (escolha.equalsIgnoreCase("s"));
    }

    //Método para consultar produto
    private void consultarProduto() {
        String escolha;
        do {
            Scanner ler = new Scanner(System.in);
            saudacao();
            System.out.println("---------------CONSULTA DE PRODUTO---------------");
            System.out.println("INFORME O NOME DO PRODUTO PARA CONSULTAR: ");
            String nomeConsulta = ler.nextLine();
            System.out.println("--------------------------------------------------");
            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (Objects.equals(produtoList.get(i).getNome().toLowerCase(), nomeConsulta.toLowerCase())) {
                    controle = false;
                    System.out.println("\nPosição " + (i + 1) + "\n" + produtoList.get(i).toString());
                    System.out.println("--------------------------------------------------");
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = repetirOperacao();
        } while (escolha.equalsIgnoreCase("s"));
    }

    //Método para excluir 1 ou mais produtos
    private void excluirProduto() {
        String escolha;
        do {
            Scanner ler = new Scanner(System.in);
            saudacao();
            System.out.println("---------------EXCLUSÃO DE PRODUTO---------------");
            System.out.println("INFORME O NOME DO PRODUTO PARA EXCLUIR: ");
            String nomeConsulta = ler.nextLine();
            System.out.println("-------------------------------------------------");
            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++) {
                ler = new Scanner(System.in);
                if (Objects.equals(produtoList.get(i).getNome().toLowerCase(), nomeConsulta.toLowerCase())) {
                    controle = false;
                    System.out.println("\nPosição " + (i + 1) + "\n" + produtoList.get(i).toString()); //verificar se index é igual ao i
                    System.out.println("-------------------------------------------------");
                    System.out.println("\nCONFIRMA A EXCLUSÃO? (S/N)");
                    escolha = ler.next();
                    if (escolha.equalsIgnoreCase("s")) {
                        produtoList.remove(i);
                        posicaoAtual--;
                    }
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = repetirOperacao();
        } while (escolha.equalsIgnoreCase("s"));
    }

    //Menu de movimentação dos produtos
    private void menuMovimentacao() {
        int opcaoMovimentacao;
        do {
            saudacao();
            Scanner ler = new Scanner(System.in);
            System.out.println("----------------ENTRADAS E SAÍDAS----------------");
            System.out.println("[1] ENTRADA\n" +
                    "[2] SAÍDA\n\n" +
                    "[0] RETORNAR AO MENU ANTERIOR");
            System.out.println("-------------------------------------------------");
            opcaoMovimentacao = ler.nextInt();
            switch (opcaoMovimentacao) {
                case 1:
                    entradaProduto();
                    break;
                case 2:
                    saidaProduto();
                    break;
                case 0:
                    menuPrincipal();
                    break;
                default:
                    opcaoInvalida();
                    break;
            }
        } while (opcaoMovimentacao != 0);
    }

    //Método para entrada de produtos no estoque
    private void entradaProduto() {
        String escolha;
        do {
            saudacao();
            Scanner ler = new Scanner(System.in);
            System.out.println("----------------ENTRADA DE PRODUTOS----------------");
            System.out.println("INFORME O NOME DO PRODUTO: ");
            String nomeProduto = ler.nextLine();
            System.out.println("---------------------------------------------------");
            Produto produtoMovimentacao;
            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (Objects.equals(produtoList.get(i).getNome().toLowerCase(), nomeProduto.toLowerCase())) {
                    controle = false;
                    produtoMovimentacao = produtoList.get(i);
                    System.out.println("\nQUANTIDADE ATUAL: " + produtoMovimentacao.getQtdEstoque());
                    System.out.println("\nQUANTIDADE DE ENTRADA: ");
                    int quantidadeEntrada = ler.nextInt();
                    if (quantidadeEntrada > 0) {        //A quantidade de entrada precisa ser positiva, do contrário estará subtraindo
                        System.out.println("QUANTIDADE FINAL: " + (produtoMovimentacao.getQtdEstoque() + quantidadeEntrada));
                        System.out.println("---------------------------------------------------");
                        escolha = confirmarOperacao();
                        if (escolha.equalsIgnoreCase("s")) {
                            produtoMovimentacao.adicionarQtd(quantidadeEntrada); //Adicionar a qtd atual mais a entrada
                            produtoList.add(produtoMovimentacao);
                        }
                        break;
                    } else {
                        System.out.println("\nPARA A ENTRADA DE PRODUTOS, POR FAVOR DIGITE UM VALOR POSITIVO");
                    }
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = repetirOperacao();
        } while (escolha.equalsIgnoreCase("s"));
    }

    //Método para saída de produtos do estoque
    private void saidaProduto() {
        String escolha;
        do {
            saudacao();
            Scanner ler = new Scanner(System.in);
            System.out.println("----------------SAÍDA DE PRODUTOS----------------");
            System.out.println("INFORME O NOME DO PRODUTO: ");
            String nomeProduto = ler.nextLine();
            System.out.println("---------------------------------------------------");
            Produto produtoMovimentacao;
            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (Objects.equals(produtoList.get(i).getNome().toLowerCase(), nomeProduto.toLowerCase())) {
                    controle = false;
                    produtoMovimentacao = produtoList.get(i);
                    System.out.println("\nQUANTIDADE ATUAL: " + produtoMovimentacao.getQtdEstoque());
                    System.out.println("\nQUANTIDADE DE SAÍDA: ");
                    int quantidadeSaida = ler.nextInt();
                    if (quantidadeSaida > 0) {      //A quantidade de saída precisa ser positiva, caso contrário terá o efeito inverso no cálculo
                        System.out.println("QUANTIDADE FINAL: " + (produtoMovimentacao.getQtdEstoque() - quantidadeSaida));
                        System.out.println("---------------------------------------------------");
                        escolha = confirmarOperacao();
                        if (escolha.equalsIgnoreCase("s")) {
                            produtoMovimentacao.diminuirQtd(quantidadeSaida); //Subtrair a qtd atual mais a entrada
                            produtoList.add(produtoMovimentacao);
                        }
                        break;
                    } else {
                        System.out.println("O PRÓPRIO SISTEMA SUBTRAI AS QUANTIDADES, POR FAVOR INFORME UM VALOR POSITIVO");
                    }
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = repetirOperacao();
        } while (escolha.equalsIgnoreCase("s"));
    }

    //Método para reajuste percentual do produto
    private void menuReajustePreco() {
        String escolha;
        saudacao();
        do {
            Scanner ler = new Scanner(System.in);
            System.out.println("----------REAJUSTE DE PREÇO DOS PRODUTOS----------");
            System.out.println("INFORME O NOME DO PRODUTO");
            String nomeProduto = ler.nextLine();
            System.out.println("--------------------------------------------------");
            Produto produtoReajuste;
            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (Objects.equals(produtoList.get(i).getNome().toLowerCase(), nomeProduto.toLowerCase())) {
                    controle = false;
                    produtoReajuste = produtoList.get(i);
                    System.out.println("\nPREÇO ATUAL: " + produtoReajuste.getPrecoUnitario());
                    System.out.println("\nINFORME EM QUANTOS % SERÁ O REAJUSTE: " + "\n" +
                            "(obs: caso queira diminuir o preço percentualmente, utilize o sinal de - ).");
                    int porcentagemReajuste = ler.nextInt();
                    System.out.println("PREÇO FINAL: " + (produtoReajuste.getPrecoUnitario() + ((produtoReajuste.getPrecoUnitario() * porcentagemReajuste) / 100)));
                    System.out.println("--------------------------------------------------");
                    escolha = confirmarOperacao();
                    if (escolha.equalsIgnoreCase("s")) {
                        produtoReajuste.setPrecoUnitario((produtoReajuste.getPrecoUnitario() + ((produtoReajuste.getPrecoUnitario() * porcentagemReajuste) / 100)));
                        produtoList.add(produtoReajuste);
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = repetirOperacao();
        } while (escolha.equalsIgnoreCase("s"));
    }

    //Método que imprime o relatório de produtos cadastrados até o momento da solicitação
    private void relatorioProdutos() {
        saudacao();
        System.out.println("\t\t[RELATÓRIO]");
        for (int i = 0; i < posicaoAtual; i++) {
            System.out.println("-------------------------------------------------");
            System.out.println("CÓDIGO: " + (i + 1) + "\n" + produtoList.get(i).toString());
        }
        System.out.println("-------------------------------------------------");
        Scanner ler = new Scanner(System.in);
        System.out.println("\n\n");
        System.out.println("PRESSIONE QUALQUER TECLA + ENTER PARA CONTINUAR");
        ler.next();
    }

    //Método informativo quando a opção selecionada no menu não existe
    private void opcaoInvalida() {
        System.out.println("\nOPÇÃO INVÁLIDA");
    }

    //Método mensagem para comparação de String não encontrada
    private void mensagemConsultaNaoEncontrada(boolean controle) {
        if (controle) {
            System.out.println("\nPRODUTO NÃO ENCONTRADO");
        }
    }

    //Ler opção do usuário repetir a operação
    private String repetirOperacao() {
        Scanner ler = new Scanner(System.in);
        String escolha;
        System.out.println("\nDESEJA REPETIR A OPERAÇÃO? (S/N)");
        escolha = ler.next();
        return escolha;
    }

    //Confirmação das operações
    private String confirmarOperacao() {
        Scanner ler = new Scanner(System.in);
        String escolha;
        System.out.println("\nDESEJA CONFIRMAR A OPERAÇÃO? (S/N)");
        escolha = ler.next();
        return escolha;
    }

    //Ler a escolha de menu do usuário
    private int escolhaMenu() {
        Scanner ler = new Scanner(System.in);
        return Integer.parseInt(ler.next());
    }
}