package controllers;

import models.Model;
import views.InitView;
import views.RegistrerView;
import views.LoginView;

public class InitViewController {
    private Model model;
    private InitView view;

    public void startInitViewController(Model model, InitView view) {
        this.model = model;
        this.view = view;
    }

    /*
    * O método "handleEventUser" lida com o evento fornecido pelo usuário e executa ações com base na opção selecionada.
    * Ele verifica a opção escolhida e, em seguida, realiza ações específicas, como iniciar a visualização de login, iniciar a visualização de registro ou fechar a visualização inicial.
    * Se nenhuma opção correspondente for selecionada, exibe uma mensagem de erro.
    * Esse método permite direcionar os eventos do usuário para as funcionalidades corretas, como login, registro ou fechamento da visualização inicial.
    * */
    public void handleEventUser(String eventUser) {
        switch (eventUser) {
            case "1":
                if (model.getCurrentUser() == null) {
                    LoginView viewLogin = new LoginView();
                    viewLogin.startLoginView(model);
                } break;
            case "2":
                if (model.getCurrentUser() == null) {
                    RegistrerView viewRegistrer = new RegistrerView();
                    viewRegistrer.startRegistrerView(model);
                } break;
            case "3":
                view.closeInitView();
                break;
            default:
                view.showMessage("error", "opção inválida");
                break;
        }
    }
}
