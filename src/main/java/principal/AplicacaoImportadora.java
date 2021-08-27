/*

MAPA DE PROGRAMAÇÃO 1
AUTOR: ANDRÉ LUÍS FRAY CASANOVA
RA: 20085878-5

*/
package principal;

import estoque.Produto;
import menu.Menu;

import java.util.ArrayList;

public class AplicacaoImportadora {
    //Declaração do vetor fora do main com static
    public static final ArrayList<Produto> produtoList = new ArrayList<>();
    public static int posicaoAtual = 0;

    //Método Principal
    public static void main(String[] args) {
        Menu importadora = new Menu();
        importadora.telaInicial();
    }
}