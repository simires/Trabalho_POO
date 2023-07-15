package views;

import controllers.*;
import models.*;
import java.util.*;

public class RandomMovieView implements showMessage {
    private Model model;
    private RandomMovieViewController randomMovieViewController;
    private boolean disabledView = false;

    public void startRandomMovieView(Model model) {
        this.model = model;
        randomMovieViewController = new RandomMovieViewController();
        randomMovieViewController.startRandomMovieViewController(model, this);
        showRandomMovieDefault();
    }

    public void closeRandomMovieView() {
        disabledView = true;
    }

    /*
    * O método "showRandomMovie" exibe informações sobre um filme aleatório selecionado.
    * Ele verifica se o filme está na lista de filmes não assistidos do modelo e exibe uma mensagem adequada.
    * Se o filme estiver na lista de não assistidos, exibe "vale a pena ver".
    * Se o filme estiver na lista de filmes assistidos pelo usuário atualmente logado, exibe "vale a pena ver de novo".
    * Esse método fornece informações sobre a recomendação de assistir ou revisitar um filme aleatório selecionado.
    * */
    public void showRandomMovie(Map.Entry<Integer, Movie> randomMovie) {
        if (model.unwatchedMovies().containsKey(randomMovie.getKey())) {
            System.out.println("vale a pena ver: [" + randomMovie.getValue().getTitle() + "]");
        } else if (model.getCurrentUser().getWacthedMovies().containsKey(randomMovie.getKey())) {
            System.out.println("vale a pena ver de novo: [" + randomMovie.getValue().getTitle() + "]");
        }
    }

    /*
    * O método "showRandomMovieDefault" exibe a tela de seleção de filmes aleatórios para o usuário.
    * Ele utiliza um objeto "Scanner" para ler as entradas do usuário no terminal.
    * */
    public void showRandomMovieDefault() {
        String eventUser;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║      O QUE DEVO ASSISTIR HOJE?       ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.println("[1] PROCURAR UM FILME");
            System.out.println("[2] VOLTAR");
            System.out.println(">> selecione uma opção para continuar: ");
            eventUser = scanner.nextLine();
            randomMovieViewController.handleEventUserRandomMovie(eventUser);
        } while (!disabledView);
    }
}
