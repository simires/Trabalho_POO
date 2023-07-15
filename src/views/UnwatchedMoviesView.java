package views;

import controllers.*;
import models.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UnwatchedMoviesView implements showMessage {
    private Model model;
    private UnwatchedMoviesViewController unwatchedMoviesViewController;
    private boolean disabledView = false;

    public void startUnwatchedMoviesView(Model model) {
        this.model = model;
        unwatchedMoviesViewController = new UnwatchedMoviesViewController();
        unwatchedMoviesViewController.startUnwatchedMoviesViewController(model, this);
        showUnwatchedMovies();
    }

    /*
     * O método "closeUnwatchedMoviesView" desativa a visualização da tela UnwatchedMoviesView.
     * Ele define a variável "disabledView" como verdadeira, indicando que a visualização deve ser encerrada.
     * */
    public void closeUnwatchedMoviesView() { disabledView = true; }

    /*
    * O método "showUnwatchedMovies" exibe a lista de filmes não assistidos na tela e permite ao usuário interagir com ela.
    * Ele exibe os filmes não assistidos, oferece a opção de editá-los (apenas quando a lista de filmes não assistidos não está vazia) e permite voltar à tela anterior.
    * O método lida com o evento do usuário e direciona para o tratamento adequado com base na opção selecionada.
    * Esse método permite ao usuário visualizar e interagir com a lista de filmes não assistidos.
    * */
    public void showUnwatchedMovies() {
        String eventUser;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║    LISTA DE FILMES NÃO ASSISTIDOS    ║");
            System.out.println("╚══════════════════════════════════════╝");
            HashMap<Integer, Movie> unwatchedMovies = model.unwatchedMovies();
            if (!unwatchedMovies.isEmpty()) {
                showUnwatchedMoviesTable(unwatchedMovies);
                System.out.println("[1] - EDITAR FILMES NÃO ASSISTIDOS");
            } else {
                showMessage("warning", "não há mais filmes não-assistidos");
            }
            System.out.println("[2] - VOLTAR");
            System.out.println(">> selecione uma opção para continuar: ");
            eventUser = scanner.nextLine();
            unwatchedMoviesViewController.handleEventUserUnwatchedMovies(eventUser);
        } while (!disabledView);
    }

    /*
    * O método "showUnwatchedMoviesTable" exibe uma tabela formatada na tela com os filmes não assistidos.
    * Ele itera sobre um HashMap de filmes não assistidos e imprime o ID, ano de lançamento e título de cada filme em linhas separadas.
    * A tabela inclui um cabeçalho com as colunas correspondentes e uma linha de decoração (que respeita o tamanho do maior filme do sistema).
    * */
    public void showUnwatchedMoviesTable(HashMap<Integer, Movie> unwatchedMovies) {
        System.out.println("ID\t║ Release Year\t║ Título");
        String lineDecorator = "";
        if (model.getSizeMaxStringMovie() <= 5) {
            lineDecorator = "═".repeat(28);
        } else {
            lineDecorator = "═".repeat(model.getSizeMaxStringMovie() + 22);
        }
        System.out.println(lineDecorator);
        for (Map.Entry<Integer, Movie> entry : unwatchedMovies.entrySet()) {
            System.out.println(entry.getKey() + "\t║ " + entry.getValue().getReleaseYear() + "\t\t\t║ " + entry.getValue().getTitle());
        }
        System.out.println(lineDecorator);
    }

    /*
    * O método "showRequestWatchedMovies" exibe uma mensagem solicitando ao usuário que selecione o ID do filme assistido.
    * Ele lê a entrada do usuário e passa o valor para o método "handleEventRequestWatchedMovies" no controlador "unwatchedMoviesViewController".
    * Esse método é responsável por solicitar ao usuário que informe o ID do filme que ele deseja marcar como assistido.
    * */
    public void showRequestWatchedMovies() {
        String eventUser;
        Scanner scanner = new Scanner(System.in);
        System.out.println(">> selecione o ID do filme assistido: ");
        eventUser = scanner.nextLine();
        unwatchedMoviesViewController.handleEventRequestWatchedMovies(eventUser);
    }
}
