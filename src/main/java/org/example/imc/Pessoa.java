package org.example.imc;

public class Pessoa {

    private String nome;
    private double altura;
    private double peso;
    private double imc;

    public Pessoa(String nome, double altura, double peso) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
    }

    public void calcularIMC() {
        imc = peso / (altura * altura);
    }

    public String getNome() {
        return nome;
    }

    public double getAltura() {
        return altura;
    }

    public double getImc() {
        return imc;
    }

    public double getPeso() {
        return peso;
    }

    public String getClassificacao() {
        if (imc < 18.5) {
            return "Abaixo do Peso";
        } else if (imc >= 18.5 && imc < 24.9) {
            return "Peso Normal";
        } else if (imc >= 25 && imc < 29.9) {
            return "Sobrepeso";
        } else if (imc >= 30 && imc < 34.9) {
            return "Obesidade Grau 1";
        } else if (imc >= 35 && imc < 39.9) {
            return "Obesidade Grau 2";
        } else if (imc >= 40) {
            return "Obesidade Grau 3";
        }

        return "";
    }


}

