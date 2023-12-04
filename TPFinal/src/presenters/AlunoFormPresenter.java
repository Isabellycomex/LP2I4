\\Isabelly 
\\João Dias
    
package presenters;

import com.google.gson.Gson;
import controllers.AlunoController;
import entities.AlunoEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class AlunoFormPresenter extends JFrame {
    private final JTextField nomeTextField;
    private final JTextField idadeTextField;
    private final JTextField pesoTextField;
    private final JTextField alturaTextField;
    private final JTextField objetivoTextField;
    public final AlunoController alunoController;

    public AlunoFormPresenter(AlunoController alunoController) {
        this.alunoController = alunoController;

        setTitle("Trabalho FINAL - LP2I4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 200);
        setLocationRelativeTo(null);

        nomeTextField = new JTextField(10);
        idadeTextField = new JTextField(10);
        pesoTextField = new JTextField(10);
        alturaTextField = new JTextField(10);
        objetivoTextField = new JTextField(10);

        final JLabel nomeLabel = new JLabel("Nome: ");
        final JLabel idadeLabel = new JLabel("Idade: ");
        final JLabel pesoLabel = new JLabel("Peso: ");
        final JLabel alturaLabel = new JLabel("Altura: ");
        final JLabel objetivoLabel = new JLabel("Objetivo: ");

        final JButton incluirButton = new JButton("Incluir");
        incluirButton.addActionListener(e -> sendForm());

        final JButton limparButton = new JButton("Limpar");
        limparButton.addActionListener(e -> clearForm());

        final JButton showButton = new JButton("Apresentar Dados");
        showButton.addActionListener(e -> showFormValues());

        final JButton sairButton = new JButton("Sair");
        sairButton.addActionListener(e -> exitApp());

        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        topPanel.add(nomeLabel);
        topPanel.add(nomeTextField);
        topPanel.add(idadeLabel);
        topPanel.add(idadeTextField);
        topPanel.add(pesoLabel);
        topPanel.add(pesoTextField);
        topPanel.add(alturaLabel);
        topPanel.add(alturaTextField);
        topPanel.add(objetivoLabel);
        topPanel.add(objetivoTextField);

        add(topPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        bottomPanel.add(incluirButton);
        bottomPanel.add(limparButton);
        bottomPanel.add(showButton);
        bottomPanel.add(sairButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private boolean getIsFormValid() {
        if (nomeTextField.getText().length() == 0) return false;
        if (idadeTextField.getText().length() == 0) return false;
        if (pesoTextField.getText().length() == 0) return false;
        if (alturaTextField.getText().length() == 0) return false;
        if (objetivoTextField.getText().length() == 0) return false;

        return true;
    }

    private void sendForm() {
        if (!getIsFormValid()) {
            showInvalidForm();
            return;
        }

        final AlunoEntity aluno = new AlunoEntity(
                nomeTextField.getText(),
                Integer.parseInt(idadeTextField.getText()),
                Float.parseFloat(pesoTextField.getText()),
                Float.parseFloat(alturaTextField.getText()),
                objetivoTextField.getText()
        );

        final boolean isSuccess = alunoController.addAluno(aluno);

        JOptionPane.showMessageDialog(
                this,
                isSuccess ? "Aluno cadastrado !" : "Falha ao cadastrar aluno.",
                isSuccess ? "Sucesso" : "Erro desconhecido",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showInvalidForm() {
        JOptionPane.showMessageDialog(
                this,
                "Preencha os campos do formulário corretamente",
                "Formulário inválido",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void clearForm() {
        nomeTextField.setText("");
        idadeTextField.setText("");
        pesoTextField.setText("");
        alturaTextField.setText("");
        objetivoTextField.setText("");
    }

    private void showFormValues() {
        if (!getIsFormValid()) {
            showInvalidForm();
            return;
        }

        final AlunoEntity aluno = new AlunoEntity(
                nomeTextField.getText(),
                Integer.parseInt(idadeTextField.getText()),
                Float.parseFloat(pesoTextField.getText()),
                Float.parseFloat(alturaTextField.getText()),
                objetivoTextField.getText()
        );

        final Gson gson = new Gson();

        JOptionPane.showMessageDialog(
                this,
                gson.toJson(aluno),
                "Dados do formulário",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void exitApp() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSED));

        dispose();
        System.exit(0);
    }
}
