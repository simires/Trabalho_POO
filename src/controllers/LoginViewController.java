package controllers;

import models.Model;
import models.User;
import views.LoginView;
import views.InitUserView;

public class LoginViewController {
    private Model model;
    private LoginView loginView;

    public void startLoginViewController(Model model, LoginView view) {
        this.model = model;
        this.loginView = view;
    }

    /*
    * O método "handleEventUser" lida com o evento do usuário na tela de login.
    * Ele valida as credenciais do usuário chamando o método "validateUser" do objeto "model".
    * Se as credenciais forem inválidas, exibe uma mensagem de aviso.
    * Se as credenciais forem válidas, exibe uma mensagem de sucesso e inicia a visualização do usuário.
    * Esse método permite autenticar o usuário e redirecioná-lo para a visualização apropriada.
    * */
    public void handleEventUser() {
        User user = model.validateUser(loginView.getNickname(), loginView.getPassword());
        if (user == null) {
            loginView.showMessage("warning", "nickname e/ou senha inválidos");
        } else {
            loginView.showMessage("success", "usuário autenticado");
            InitUserView initUserView = new InitUserView();
            initUserView.startInitUserView(model);
        }
    }
}
