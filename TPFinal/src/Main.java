import controllers.AlunoController;
import presenters.AlunoFormPresenter;
import services.SQLSERVER;

public class Main {
    public static void main(String[] args) {
        final SQLSERVER SQLSERVER = new SQLSERVER();
        final AlunoController alunoController = new AlunoController(SQLSERVER);

        final AlunoFormPresenter formPresenter = new AlunoFormPresenter(alunoController);

        formPresenter.setVisible(true);
    }
}