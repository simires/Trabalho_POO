package controllers;

import models.Model;
import models.User;
import views.RegistrerView;
public class RegistrerViewController {
    private Model model;
    private RegistrerView registrerView;

    public void startRegistrerViewController(Model model, RegistrerView registrerView) {
        this.model = model;
        this.registrerView = registrerView;
    }

    /*
    * O método "handleEventUser" lida com o evento do usuário na tela de registro.
    * Ele verifica se o nickname e a senha fornecidos estão vazios. Se estiverem vazios, exibe uma mensagem de erro.
    * Caso contrário, cria um novo usuário com o nickname e a senha fornecidos.
    * Em seguida, verifica se o nickname já está registrado no modelo.
    * Se não estiver registrado, adiciona o novo usuário ao modelo e exibe uma mensagem de sucesso.
    * Se o nickname já estiver registrado, exibe uma mensagem de aviso.
    * Esse método permite o registro de novos usuários e fornece feedback adequado ao usuário durante o processo de registro.
    * */
    public void handleEventUser() {
        User newUser = new User();
        if (registrerView.getNickname().equals("") || registrerView.getPassword().equals("")) {
            registrerView.showMessage("error", "não foi possível realizar o cadastro");
        } else {
            newUser.setNickname(registrerView.getNickname());
            newUser.setPassword(registrerView.getPassword());
            if (!model.getRegistredUsers().containsKey(newUser.getNickname())) {
                model.setUser(newUser);
                registrerView.showMessage("success", "usuário cadastrado com sucesso");
            } else {
                registrerView.showMessage("warning", "já existe um usuário esse nickname");
            }
        }
    }
}
