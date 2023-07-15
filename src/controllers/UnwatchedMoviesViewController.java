package controllers;

import models.Model;
import models.Movie;
import views.UnwatchedMoviesView;

public class UnwatchedMoviesViewController {
    private Model model;
    private UnwatchedMoviesView unwatchedMoviesView;

    public void startUnwatchedMoviesViewController (Model model, UnwatchedMoviesView unwatchedMoviesView) {
        this.model = model;
        this.unwatchedMoviesView = unwatchedMoviesView;
    }

    /*
     * O método "handleEventUserUnwatchedMovies" recebe um evento do usuário como entrada e, com base nesse evento, executa uma determinada ação.
     * Se o evento for "1", verifica se o usuário ainda tem filmes não assistidos para editar e, em seguida, chama o método "showRequestWatchedMovies" na
     * view "unwatchedMoviesView" para solicitar ao usuário que informe o ID do filme assistido. Se o evento for "2", fecha a vista "unwatchedMoviesView".
     * Caso contrário, exibe uma mensagem de aviso informando que a opção selecionada é inválida.
     */
    public void handleEventUserUnwatchedMovies(String eventUser) {
        switch (eventUser){
            case "1":
                if (model.getCurrentUser().getWacthedMovies().size() != model.getCountMovies()) {
                    unwatchedMoviesView.showRequestWatchedMovies();
                } else {
                    unwatchedMoviesView.showMessage("error", "você não tem mais filmes para editar");
                } break;
            case "2":
                unwatchedMoviesView.closeUnwatchedMoviesView();
                break;
            default:
                unwatchedMoviesView.showMessage("warning", "opção inválida");
                break;
        }
    }

    /*
    * O método "handleEventRequestWatchedMovies" recebe o ID de um filme como entrada e realiza algumas verificações.
    * Primeiro, converte o ID para um número inteiro. Em seguida, verifica se o ID corresponde a um filme registrado no sistema.
    * Se o ID for válido e o filme ainda não tiver sido marcado como assistido pelo usuário atual, cria um novo objeto "Movie" com as informações do filme correspondente
    * e chama o método "setWatchedMovie" do modelo para adicionar o filme assistido ao usuário atual. Em seguida, exibe uma mensagem de sucesso na view "unwatchedMoviesView".
    * Se o ID já tiver sido registrado como assistido pelo usuário atual, exibe uma mensagem de aviso. Se o ID não existir no sistema, exibe uma mensagem de erro.
    * Se ocorrer uma exceção durante o processamento do ID, exibe uma mensagem de aviso solicitando que o usuário insira um ID válido.
    * */
    public void handleEventRequestWatchedMovies (String id) {
        try {
            int idMovie = Integer.parseInt(id);
            if (model.getRegistredMovies().containsKey(idMovie)) {
                if (!model.getCurrentUser().getWacthedMovies().containsKey(idMovie)) {
                    Movie currentMovie = model.getRegistredMovies().get(idMovie);
                    Movie newUserMovie = new Movie(idMovie, currentMovie.getTitle(), currentMovie.getReleaseYear());
                    model.setWatchedMovie(newUserMovie);
                    unwatchedMoviesView.showMessage("success", "filme adicionado");
                } else {
                    unwatchedMoviesView.showMessage("warning", "o ID já foi registrado");
                }
            } else {
                unwatchedMoviesView.showMessage("error", "o ID não existe");
            }
        } catch (Exception e) {
            unwatchedMoviesView.showMessage("warning","insira um ID válido");
        }
    }
}
