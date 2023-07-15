package views;

import controllers.WatchedMoviesViewController;
import models.Model;
import models.Movie;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WatchedMoviesView implements showMessage {
    private Model model;
    private WatchedMoviesViewController watchedMoviesViewController;
    private boolean disabledView = false;

    public void startWatchedMoviesView(Model model) {
        this.model = model;
        watchedMoviesViewController = new WatchedMoviesViewController();
        watchedMoviesViewController.startWatchedMoviesViewControler(model, this);
        showWatchedMovies();
    }

    /*
     * O método "closeWatchedMoviesView" desativa a visualização da tela WatchedMoviesView.
     * Ele define a variável "disabledView" como verdadeira, indicando que a visualização deve ser encerrada.
     * */
    public void closeWatchedMoviesView() { disabledView = true; }

    /*
     * O método "sshowWatchedMovies" exibe a lista de filmes assistidos na tela e permite ao usuário interagir com ela.
     * Ele exibe os filmes assistidos, oferece a opção de editá-los (apenas quando a lista de filmes assistidos não está vazia) e permite voltar à tela anterior.
     * O método lida com o evento do usuário e direciona para o tratamento adequado com base na opção selecionada.
     * Esse método permite ao usuário visualizar e interagir com a lista de filmes assistidos.
     * */
    public void showWatchedMovies() {
        String eventUser;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("╔══════════════════════════════════╗");
            System.out.println("║    LISTA DE FILMES ASSISTIDOS    ║");
            System.out.println("╚══════════════════════════════════╝");
            HashMap<Integer, Movie> watchedMovies = model.getCurrentUser().getWacthedMovies();
            if (!watchedMovies.isEmpty()) {
                showWatchedMoviesTable(watchedMovies);
                System.out.println("[1] - EDITAR FILMES ASSISTIDOS");
            } else {
                showMessage("warning", "não há mais filmes assistidos");
            }
            System.out.println("[2] - VOLTAR");
            System.out.println(">> selecione uma opção para continuar: ");
            eventUser = scanner.nextLine();
            watchedMoviesViewController.handleEventUserWatchedMovies(eventUser);
        } while (!disabledView);
    }

    /*
     * O método "showWatchedMoviesTable" exibe uma tabela formatada na tela com os filmes assistidos.
     * Ele itera sobre um HashMap de filmes assistidos e imprime o ID, ano de lançamento e título de cada filme em linhas separadas.
     * A tabela inclui um cabeçalho com as colunas correspondentes e uma linha de decoração (que respeita o tamanho do maior filme do sistema).
     * */
    public void showWatchedMoviesTable(HashMap<Integer, Movie> watchedMovies) {
        System.out.println("ID\t║ Release Year\t║ Título");
        String lineDecorator = "";
        if (model.getSizeMaxStringMovie() <= 5) {
            lineDecorator = "═".repeat(28);
        } else {
            lineDecorator = "═".repeat(model.getSizeMaxStringMovie() + 22);
        }
        System.out.println(lineDecorator);
        for (Map.Entry<Integer, Movie> entry : watchedMovies.entrySet()) {
            System.out.println(entry.getKey() + "\t║ " + entry.getValue().getReleaseYear() + "\t\t\t║ " + entry.getValue().getTitle());
        }
        System.out.println(lineDecorator);
    }

    /*
     * O método "showRequestUnwatchedMovies" exibe uma mensagem solicitando ao usuário que selecione o ID do filme não assistido.
     * Ele lê a entrada do usuário e passa o valor para o método "handleEventRequestUnwatchedMovies" no controlador "watchedMoviesViewController".
     * Esse método é responsável por solicitar ao usuário que informe o ID do filme que ele deseja marcar como não assistido.
     * */
    public void showRequestUnwatchedMovies() {
        String eventUser;
        Scanner scanner = new Scanner(System.in);
        System.out.println(">> selecione o ID do filme não assistido: ");
        eventUser = scanner.nextLine();
        watchedMoviesViewController.handleEventRequestUnwatchedMovies(eventUser);
    }
}
