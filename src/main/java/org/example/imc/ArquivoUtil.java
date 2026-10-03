package org.example.imc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArquivoUtil {

    public static void salvar(List<Pessoa> pessoas) throws IOException {

        FileWriter escritor = new FileWriter("dados_pessoas.txt");

        for (Pessoa pessoa : pessoas) {

            String linha = pessoa.getNome() + ","
                    + pessoa.getAltura() + ","
                    + pessoa.getPeso() + ","
                    + pessoa.getImc();

            escritor.write(linha);
            escritor.write("\n");
        }

        escritor.close();
    }

    public static List<Pessoa> carregar() throws IOException {

        List<Pessoa> pessoas = new ArrayList<>();

        FileReader arquivo = new FileReader("dados_pessoas.txt");
        BufferedReader leitor = new BufferedReader(arquivo);

        String linha;

        while ((linha = leitor.readLine()) != null) {

            String[] dados = linha.split(",");

            String nome = dados[0];
            double altura = Double.parseDouble(dados[1]);
            double peso = Double.parseDouble(dados[2]);

            Pessoa pessoa = new Pessoa(nome, altura, peso);

            pessoa.calcularIMC();

            pessoas.add(pessoa);
        }

        leitor.close();

        return pessoas;
    }
}