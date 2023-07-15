package controllers;

import models.Model;
import views.InitUserView;
import views.RandomMovieView;
import views.UnwatchedMoviesView;
import views.WatchedMoviesView;

public class InitUserViewController {
    private Model model;
    private InitUserView initUserView;

    public void startInitUserViewController(Model model, InitUserView view) {
        this.model = model;
        this.initUserView = view;
    }

    /*
    * O método "handleEventUser" recebe um evento fornecido pelo usuário e executa ações com base na opção selecionada.
    * Ele verifica a opção escolhida e, em seguida, realiza ações específicas, como fazer logout do usuário, exibir filmes não assistidos, exibir filmes assistidos ou exibir um filme aleatório.
    * Se nenhuma opção correspondente for selecionada, exibe uma mensagem de aviso. Esse método permite lidar com eventos do usuário e direcioná-los para as funcionalidades corretas.
    * */
    public void handleEventUser(String eventUser) {
        switch (eventUser) {
            case "1":
                if (model.getCurrentUser() != null) {
                    model.logoutUser();
                    initUserView.closeInitUserView();
                } break;
            case "2":
                if (model.getCurrentUser() != null) {
                    UnwatchedMoviesView unwatchedMoviesView = new UnwatchedMoviesView();
                    unwatchedMoviesView.startUnwatchedMoviesView(model);
                } break;
            case "3":
                if (model.getCurrentUser() != null) {
                    WatchedMoviesView watchedMoviesView = new WatchedMoviesView();
                    watchedMoviesView.startWatchedMoviesView(model);
                } break;
            case "4":
                if (model.getCurrentUser() != null) {
                    RandomMovieView randomMovieView = new RandomMovieView();
                    randomMovieView.startRandomMovieView(model);
                } break;
            default:
                initUserView.showMessage("warning", "opção inválida");
        }
    }
}
