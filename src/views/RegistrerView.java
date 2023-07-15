package views;

import controllers.RegistrerViewController;
import models.Model;

import java.util.Scanner;

public class RegistrerView implements showMessage {
    private Model model;
    private RegistrerViewController registrerViewController;
    private String nickname;
    private String password;

    public void startRegistrerView(Model model) {
        this.model = model;
        registrerViewController = new RegistrerViewController();
        registrerViewController.startRegistrerViewController(model, this);
        showRegistrer();
    }

    /*
     * O método "showRegistrer" exibe a tela de registro para o usuário.
     * Ele utiliza um objeto "Scanner" para ler as entradas do usuário no terminal.
     * */
    public void showRegistrer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("╔════════════════════════╗");
        System.out.println("║    TELA DE CADASTRO    ║");
        System.out.println("╚════════════════════════╝");
        System.out.println(">> nickname: ");
        nickname = scanner.nextLine();
        System.out.println(">> senha: ");
        password = scanner.nextLine();
        registrerViewController.handleEventUser();
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

}