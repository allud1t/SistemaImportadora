package estoque;

import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

import static principal.AplicacaoImportadora.posicaoAtual;
import static principal.AplicacaoImportadora.produtoList;

public class Produto {
    //Atributos
    private String nome;
    private float precoUnitario;
    private String unidade;
    private int qtdEstoque;

    //Construtor
    public Produto() {
    }

    //Getters e Setters
    public String getNome() {
        return nome;
    }

    //Validação para evitar nomes de produtos iguais
    public void setNome(String nome) {
        this.nome = nome;       //O primeiro produto é cadastrado normalmente, pois o vetor está vazio
        for (int i = 0; i < posicaoAtual; i++) {    //Aqui analisa-se os nomes dos produtos cadastrados para evitar ocorrências iguais
            if (Objects.equals(produtoList.get(i).getNome().toLowerCase(), nome.toLowerCase())) {
                System.out.println("\nPRODUTO COM MESMO NOME JÁ CADASTRADO. TENTE OUTRO NOME: ");
                Scanner ler = new Scanner(System.in);
                this.nome = ler.nextLine();
            }
        }
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }

    //validação para que o preço seja sempre maior que zero
    public void setPrecoUnitario(float precoUnitario) {
        if (precoUnitario > 0) {
            this.precoUnitario = precoUnitario;
        } else
            while (this.precoUnitario <= 0) {
                System.out.println("PREÇO DEVE SER MAIOR QUE ZERO." + "\n"
                        + "(obs: digite novamente com ponto na casa decimal ex: 9.99).");
                Scanner ler = new Scanner(System.in);
                ler.useLocale(Locale.US);
                this.precoUnitario = ler.nextFloat();
            }
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    //Validação para que o estoque do produto cadastrado sempre seja maior ou igual a zero
    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = Math.max(qtdEstoque, 0);
        while (qtdEstoque < 0) {
            Scanner ler = new Scanner(System.in);
            System.out.println("A QUANTIDADE EM ESTOQUE DEVE SER MAIOR OU IGUAL A ZERO");
            System.out.println("INFORME A QUANTIDADE EM ESTOQUE: ");
            qtdEstoque = ler.nextInt();
            this.qtdEstoque = qtdEstoque;
        }
    }

    //Método para imprimir o objeto como String
    @Override
    public String toString() {
        return "NOME DO PRODUTO: " + nome + "\n" +
                "PREÇO UNITÁRIO: " + precoUnitario + "\n" +
                "UNIDADE: " + unidade + "\n" +
                "QUANTIDADE EM ESTOQUE: " + qtdEstoque;
    }

    //Método para adicionar quantidade de produto no estoque
    public void adicionarQtd(int quantidade) {
        this.qtdEstoque += quantidade;
    }

    //Método para subtrair quantidade de produto no estoque
    public void diminuirQtd(int quantidade) {
        this.qtdEstoque -= quantidade;
    }
}