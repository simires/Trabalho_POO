package views;

import controllers.InitUserViewController;
import models.Model;
import models.User;

import java.util.Scanner;

public class InitUserView implements showMessage {
    private Model model;
    private InitUserViewController initUserViewController;
    private boolean disabledView = false;

    public void startInitUserView(Model model) {
        this.model = model;
        initUserViewController = new InitUserViewController();
        initUserViewController.startInitUserViewController(model, this);
        showMenuUserOptions();
    }

    /*
    * O método "closeInitUserView" desativa a visualização da tela InitUserView.
    * Ele define a variável "disabledView" como verdadeira, indicando que a visualização deve ser encerrada.
    * */
    public void closeInitUserView() { disabledView = true; }

    /*
    * O método "showMenuUserOptions" exibe um menu de opções para o usuário atual.
    * Ele utiliza um loop "do-while" para repetir o processo até que a visualização seja desativada.
    * */
    public void showMenuUserOptions() {
        String eventUser;
        Scanner scanner = new Scanner(System.in);
        do {
            showLoggedUser();
            String optionsCurrentUser[] = {"[1] - SAIR", "[2] - VISUALIZAR FILMES NÃO ASSISTIDOS", "[3] - VISUALIZAR FILMES ASSISTIDOS", "[4] - O QUE DEVO ASSISTIR HOJE?"};
            for (String option : optionsCurrentUser) { System.out.println(option); }
            System.out.println(">> selecione uma opção para continuar: ");
            eventUser = scanner.nextLine(); 
            initUserViewController.handleEventUser(eventUser);
        } while (!disabledView);
    }

    /*
    * O método "showLoggedUser" exibe o apelido do usuário atualmente logado em um formato decorativo no terminal.
    * Ele obtém o usuário atual, calcula o tamanho do apelido e cria uma linha de decoração baseada nesse tamanho.
    * Em seguida, imprime o apelido do usuário entre linhas decorativas no terminal.
    * */
    public void showLoggedUser() {
        User currentUser = model.getCurrentUser();
        int sizeNickname = currentUser.getNickname().length();
        String decorator = "═";
        String lineDecorator = decorator.repeat(24 + sizeNickname);
        System.out.println("╔" + lineDecorator + "╗");
        System.out.println("║    USUÁRIO LOGADO: " + currentUser.getNickname() + "    ║");
        System.out.println("╚" + lineDecorator + "╝");
    }
}
