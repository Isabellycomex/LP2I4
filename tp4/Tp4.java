import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

//Isabelly Barbosa Gonçalves
//JOÃO ANTONIO DIAS
public class Tp4 {
    public static void main(String[] args) throws Exception {
        Connection connection = DatabaseConnection.getConnection();

        try {
            createTables(connection);
        } catch (Exception e) {
        }

        JFrame frame = new JFrame("TP 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 250);
        frame.setLayout(new GridLayout(2, 1));

        JLabel nomeLabel = new JLabel("Nome: ");
        JTextField nomeInput = new JTextField(20);
        JButton pesquisarButton = new JButton("Pesquisar");
        JPanel topRow = new JPanel(new FlowLayout());
        topRow.add(nomeLabel);
        topRow.add(nomeInput);
        topRow.add(pesquisarButton);

        JLabel nameLabel = new JLabel("Nome: ");
        JTextField nameInput = new JTextField(20);
        JLabel salarioLabel = new JLabel("Salário: ");
        JTextField salarioInput = new JTextField(20);
        JLabel cargoLabel = new JLabel("Cargo: ");
        JTextField cargoInput = new JTextField(20);
        JButton anteriorButton = new JButton("Anterior");
        JButton proximoButton = new JButton("Próximo");
        JPanel bottomRow = new JPanel(new GridLayout(4, 2));
        bottomRow.add(nameLabel);
        bottomRow.add(nameInput);
        bottomRow.add(salarioLabel);
        bottomRow.add(salarioInput);
        bottomRow.add(cargoLabel);
        bottomRow.add(cargoInput);
        bottomRow.add(anteriorButton);
        bottomRow.add(proximoButton);

        frame.add(topRow);
        frame.add(bottomRow);

        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeInput.getText();
                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(
                            "SELECT cod_func, nome_func, sal_func, ds_cargo FROM tbfuncs INNER JOIN tbcargos ON tbfuncs.cod_cargo = tbcargos.cd_cargo WHERE nome_func = '"
                                    + nome + "'");
                    if (resultSet.next()) {
                        nameInput.setText(resultSet.getString("nome_func"));
                        salarioInput.setText(resultSet.getString("sal_func"));
                        cargoInput.setText(resultSet.getString("ds_cargo"));
                        currentRecord = resultSet.getInt("cod_func");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Funcionário não encontrado");
                        nameInput.setText("");
                        salarioInput.setText("");
                        cargoInput.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        proximoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(
                            "SELECT cod_func, nome_func, sal_func, ds_cargo FROM tbfuncs INNER JOIN tbcargos ON tbfuncs.cod_cargo = tbcargos.cd_cargo WHERE cod_func > "
                                    + currentRecord + " ORDER BY cod_func ASC");
                    if (resultSet.next()) {
                        nameInput.setText(resultSet.getString("nome_func"));
                        salarioInput.setText(resultSet.getString("sal_func"));
                        cargoInput.setText(resultSet.getString("ds_cargo"));
                        currentRecord = resultSet.getInt("cod_func");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Não há mais registros");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        anteriorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(
                            "SELECT cod_func, nome_func, sal_func, ds_cargo FROM tbfuncs INNER JOIN tbcargos ON tbfuncs.cod_cargo = tbcargos.cd_cargo WHERE cod_func < "
                                    + currentRecord + " ORDER BY cod_func DESC");
                    if (resultSet.next()) {
                        nameInput.setText(resultSet.getString("nome_func"));
                        salarioInput.setText(resultSet.getString("sal_func"));
                        cargoInput.setText(resultSet.getString("ds_cargo"));
                        currentRecord = resultSet.getInt("cod_func");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Não há mais registros");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        frame.setVisible(true);
    }

    public static void createTables(Connection connection) throws Exception {
        Statement statement = connection.createStatement();
        statement.executeUpdate(
                "CREATE TABLE tbcargos (cd_cargo SMALLINT NOT NULL, ds_cargo CHAR(20) NULL, PRIMARY KEY (cd_cargo))");
        statement.executeUpdate(
                "CREATE TABLE tbfuncs (cod_func DECIMAL(9) NOT NULL, nome_func CHAR(30) NULL, sal_func MONEY NULL, cod_cargo SMALLINT NULL, PRIMARY KEY (cod_func), FOREIGN KEY (cod_cargo) REFERENCES tbcargos(cd_cargo))");
        statement.executeUpdate("INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (1, 'Gerente')");
        statement.executeUpdate("INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (2, 'Analista')");
        statement.executeUpdate("INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (3, 'Programador')");
        statement.executeUpdate("INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (4, 'Tech Lead')");
        statement.executeUpdate("INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (5, 'Assistente')");
        statement.executeUpdate("INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (6, 'Aprendiz')");
        statement.executeUpdate("INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (7, 'Consultor')");
        statement.executeUpdate("INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (8, 'Lider')");
        statement.executeUpdate("INSERT INTO tbcargos (cd_cargo, ds_cargo) VALUES (9, 'Professor')");
        statement.executeUpdate(
                "INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (1, 'João', 5600.00, 1)");
        statement.executeUpdate(
                "INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (2, 'Maria', 4000.00, 2)");
        statement.executeUpdate(
                "INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (3, 'Pedro', 3500.00, 3)");
        statement.executeUpdate(
                "INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (4, 'Isabelly', 9000.00, 4)");
        statement.executeUpdate(
                "INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (5, 'Alisson', 3000.00, 5)");
        statement.executeUpdate(
                "INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (6, 'Bruna', 1300.00, 5)");
         statement.executeUpdate(
                "INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (7, 'Giovanni', 2300.00, 7)");
        statement.executeUpdate(
                "INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (8, 'Sheila', 4200.00, 8)");
        statement.executeUpdate(
                "INSERT INTO tbfuncs (cod_func, nome_func, sal_func, cod_cargo) VALUES (9, 'Jessica', 2000.00, 9)");
                statement.close();
    }

    private static Integer currentRecord;
}
