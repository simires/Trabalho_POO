package views;

import controllers.InitViewController;
import models.Model;

import java.util.ArrayList;
import java.util.Scanner;

public class InitView implements showMessage {
    private Model model;
    private InitViewController initViewController;
    private boolean disabledView = false;

    public void startInitView(Model model) {
        this.model = model;
        initViewController = new InitViewController();
        initViewController.startInitViewController(model, this);
        showMenuOptions();
    }

    /*
     * O método "closeInitView" desativa a visualização da tela InitView.
     * Ele define a variável "disabledView" como verdadeira, indicando que a visualização deve ser encerrada.
     * */
    public void closeInitView() { disabledView = true; }

    /*
     * O método "showMenuOptions" exibe um menu de opções no início da execução do programa.
     * Ele utiliza um loop "do-while" para repetir o processo até que a visualização seja desativada.
     * */
    public void showMenuOptions() {
        String eventUser;
        Scanner scanner = new Scanner(System.in);
        String optionsGuestUser[] = {"[1] - LOGAR", "[2] - CADASTRAR", "[3] - FECHAR SISTEMA"};
        do {
            System.out.println("╔════════════════════════════════╗");
            System.out.println("║   BEM-VINDO AO NOSSO ACERVO    ║");
            System.out.println("║           DE FILMES            ║");
            System.out.println("╚════════════════════════════════╝");
            for (String option : optionsGuestUser) { System.out.println(option); }
            System.out.println(">> selecione uma opção para continuar: ");
            eventUser = scanner.nextLine();
            initViewController.handleEventUser(eventUser);
        } while (!disabledView);
    }
}
