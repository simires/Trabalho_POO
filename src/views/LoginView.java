package views;

import controllers.LoginViewController;
import models.Model;

import java.util.Scanner;

public class LoginView implements showMessage {
    private Model model;
    private LoginViewController loginViewController;
    private String nickname;
    private String password;

    public void startLoginView(Model model) {
        this.model = model;
        loginViewController = new LoginViewController();
        loginViewController.startLoginViewController(model, this);
        showLogIn();
    }

    /*
    * O método "showLogIn" exibe a tela de login para o usuário.
    * Ele utiliza um objeto "Scanner" para ler as entradas do usuário no terminal.
    * */
    public void showLogIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("╔═════════════════════╗");
        System.out.println("║    TELA DE LOGIN    ║");
        System.out.println("╚═════════════════════╝");
        System.out.println(">> insira o seu nickname: ");
        nickname = scanner.nextLine();
        System.out.println(">> insira a sua senha: ");
        password = scanner.nextLine();
        loginViewController.handleEventUser();
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }
}
