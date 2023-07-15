package controllers;

import models.Model;
import views.WatchedMoviesView;

public class WatchedMoviesViewController {
    private Model model;
    private WatchedMoviesView watchedMoviesView;

    public void startWatchedMoviesViewControler(Model model, WatchedMoviesView watchedMoviesView) {
        this.model = model;
        this.watchedMoviesView = watchedMoviesView;
    }

    /*
     * O método "handleEventUserWatchedMovies" recebe um evento do usuário como entrada e, com base nesse evento, executa uma determinada ação.
     * Se o evento for "1", verifica se o usuário ainda tem filmes assistidos para editar e, em seguida, chama o método "showRequestUnwatchedMovies" na
     * view "watchedMoviesView" para solicitar ao usuário que informe o ID do filme não assistido. Se o evento for "2", fecha a vista "watchedMoviesView".
     * Caso contrário, exibe uma mensagem de aviso informando que a opção selecionada é inválida.
     */
    public void handleEventUserWatchedMovies(String eventUser) {
        switch (eventUser) {
            case "1":
                if (!model.getCurrentUser().getWacthedMovies().isEmpty()) {
                    watchedMoviesView.showRequestUnwatchedMovies();
                } else {
                    watchedMoviesView.showMessage("error", "você não tem mais filmes para editar");
                } break;
            case "2":
                watchedMoviesView.closeWatchedMoviesView();
                break;
            default:
                watchedMoviesView.showMessage("warning", "opção inválida");
                break;
        }
    }

    /*
     * O método "handleEventRequestUnwatchedMovies" recebe o ID de um filme como entrada e realiza algumas verificações.
     * Primeiro, converte o ID para um número inteiro. Em seguida, verifica se o ID corresponde a um filme registrado no sistema.
     * Se o ID for válido e o filme tiver sido marcado como assistido pelo usuário atual, remove-se o filme correspondente ao ID passado.
     * Em seguida, exibe uma mensagem de sucesso na view "watchedMoviesView". Se o ID já tiver sido registrado como não assistido pelo usuário atual, exibe uma mensagem de aviso.
     * Se o ID não existir no sistema, exibe uma mensagem de erro. Se ocorrer uma exceção durante o processamento do ID,
     * exibe uma mensagem de aviso solicitando que o usuário insira um ID válido.
     * */
    public void handleEventRequestUnwatchedMovies(String id) {
        try {
            int idMovie = Integer.parseInt(id);
            if (model.getRegistredMovies().containsKey(idMovie)) {
                if (model.getCurrentUser().getWacthedMovies().containsKey(idMovie)) {
                    model.getCurrentUser().getWacthedMovies().remove(idMovie);
                    watchedMoviesView.showMessage("success", "filme adicionado");
                } else {
                    watchedMoviesView.showMessage("warning", "o ID já foi registrado");
                }
            } else {
                watchedMoviesView.showMessage("error", "o ID não existe");
            }
        } catch (Exception e) {
            watchedMoviesView.showMessage("warning", "insira um ID válido");
        }
    }
}
