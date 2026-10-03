package org.example.imc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;

public class IMCController {

    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoAltura;

    @FXML
    private TextField campoPeso;

    @FXML
    private Label labelResultado;

    @FXML
    private Button botaoCalcular;

    @FXML
    private Button botaoSalvar;

    @FXML
    private Button botaoCarregar;

    @FXML
    private TableView<Pessoa> tabelaPessoas;

    @FXML
    private TableColumn<Pessoa, String> colunaNome;

    @FXML
    private TableColumn<Pessoa, Double> colunaAltura;

    @FXML
    private TableColumn<Pessoa, Double> colunaPeso;

    @FXML
    private TableColumn<Pessoa, Double> colunaImc;

    @FXML
    private TableColumn<Pessoa, String> colunaClassificacao;

    private Pessoa pessoaAtual;

    private ObservableList<Pessoa> pessoas =
            FXCollections.observableArrayList();


    // Configura a tabela quando a tela é carregada
    @FXML
    public void initialize() {

        colunaNome.setCellValueFactory(
                new PropertyValueFactory<>("nome")
        );

        colunaAltura.setCellValueFactory(
                new PropertyValueFactory<>("altura")
        );

        colunaPeso.setCellValueFactory(
                new PropertyValueFactory<>("peso")
        );

        colunaImc.setCellValueFactory(
                new PropertyValueFactory<>("imc")
        );

        colunaClassificacao.setCellValueFactory(
                new PropertyValueFactory<>("classificacao")
        );

        tabelaPessoas.setItems(pessoas);
    }


    // Calcula o IMC
    @FXML
    public void calcularIMC() {

        try {

            String nome = campoNome.getText();

            double altura =
                    Double.parseDouble(campoAltura.getText());

            double peso =
                    Double.parseDouble(campoPeso.getText());

            if (nome.isEmpty()) {
                labelResultado.setText(
                        "Digite o nome."
                );
                return;
            }

            if (altura <= 0 || peso <= 0) {
                labelResultado.setText(
                        "Altura e peso devem ser maiores que zero."
                );
                return;
            }

            pessoaAtual =
                    new Pessoa(nome, altura, peso);

            pessoaAtual.calcularIMC();

            labelResultado.setText(
                    "IMC: "
                            + String.format("%.2f",
                            pessoaAtual.getImc())
                            + "\nClassificação: "
                            + pessoaAtual.getClassificacao()
            );

        } catch (NumberFormatException e) {

            labelResultado.setText(
                    "Digite valores numéricos válidos."
            );
        }
    }


    // Salva os dados no arquivo
    @FXML
    public void salvar() {

        if (pessoaAtual == null) {
            labelResultado.setText(
                    "Calcule o IMC antes de salvar."
            );
            return;
        }

        try {

            if (pessoas.isEmpty()) {
                pessoas.addAll(ArquivoUtil.carregar());
            }

            pessoas.add(pessoaAtual);

            ArquivoUtil.salvar(pessoas);

            labelResultado.setText(
                    "Pessoa salva com sucesso!"
            );

        } catch (IOException e) {

            labelResultado.setText(
                    "Erro ao salvar os dados."
            );
        }
    }


    // Carrega os dados do arquivo
    @FXML
    public void carregar() {

        try {

            pessoas.setAll(
                    ArquivoUtil.carregar()
            );

            if (pessoas.isEmpty()) {

                labelResultado.setText(
                        "Nenhuma pessoa encontrada."
                );

            } else {

                labelResultado.setText(
                        pessoas.size()
                                + " pessoa(s) carregada(s)!"
                );
            }

        } catch (IOException e) {

            labelResultado.setText(
                    "Erro ao carregar os dados."
            );
        }
    }
}