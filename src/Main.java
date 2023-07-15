import models.Model;
import views.InitView;
public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        InitView initView = new InitView();
        initView.startInitView(model);
    }
}
        