package controllers;

import models.*;
import views.RandomMovieView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RandomMovieViewController {
    private Model model;
    private RandomMovieView randomMovieView;

    public void startRandomMovieViewController(Model model, RandomMovieView randomMovieView) {
        this.model = model;
        this.randomMovieView = randomMovieView;
    }

    /*
    * O método "handleEventUserRandomMovie" lida com o evento do usuário na tela de seleção de filmes aleatórios.
    * Ele verifica a opção selecionada pelo usuário e executa a ação correspondente.
    * No caso "1", o método gera um índice aleatório e seleciona um filme aleatório da lista de filmes registrados no modelo.
    * Em seguida, chama o método "showRandomMovie" para exibir informações sobre o filme selecionado.
    * No caso "2", o método fecha a visualização de filmes aleatórios.
    * Em caso de opção inválida, o método exibe uma mensagem de aviso.
    * */
    public void handleEventUserRandomMovie(String eventUser) {
        switch (eventUser) {
            case "1":
                List<Map.Entry<Integer, Movie>> moviesList = new ArrayList<>(model.getRegistredMovies().entrySet());
                int randomIndex = model.getIndexRandomMovie(moviesList.size());
                Map.Entry<Integer, Movie> randomMovie = moviesList.get(randomIndex);
                randomMovieView.showRandomMovie(randomMovie);
                break;
            case "2":
                randomMovieView.closeRandomMovieView();
                break;
            default:
                randomMovieView.showMessage("warning", "opção inválida");
                break;
        }
    }
}
