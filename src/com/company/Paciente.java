package com.company;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static java.time.LocalTime.parse;

public class Paciente {

    private String nome;
    private String sobrenome;
    private char sexo;
    private LocalDate nascimento;
    private byte idade;
    private double altura;
    private double peso;
    private String cpf;
    private double imc;
    private double pesoIdeal;

    public Paciente() {
        nome = "";
        sobrenome = "";
        sexo = '\u0000';
        nascimento = LocalDate.now();
        idade = 0;
        altura = 0;
        peso = 0.0;
        cpf = "";
        imc = 0.0;
    }

    public Paciente(String nome, String sobrenome, char sexo, LocalDate nascimento, double altura, double peso, String cpf) {
        setNome(nome);
        setSobrenome(sobrenome);
        this.sexo = sexo;
        this.nascimento = nascimento;
        setAltura(altura);
        setPeso(peso);
        setCpf(cpf);
        imc = calcularIMC();
    }

    public Paciente(String nome, String sobrenome, char sexo, double altura, double peso, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.altura = altura;
        this.peso = peso;
        this.cpf = cpf;
        imc = calcularIMC();
    }

    public Paciente(String nome, String sobrenome, char sexo, LocalDate nascimento, double altura, double peso) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.nascimento = nascimento;
        this.altura = altura;
        this.peso = peso;
        imc = calcularIMC();
    }

    public Paciente(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != "" && nome != null)
            this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        if (!Objects.equals(sobrenome, "") && sobrenome != null)

            this.sobrenome = sobrenome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getNascimento() {

        return nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        this.idade = calcularIdade();
    }

    public byte getIdade() {
        return idade;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
            this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {

        if (peso >= 0.0)
            this.peso = peso;
        imc = calcularIMC();

    }

    public String getCpf() {
         return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "***.$2.***-**");
    }

    public void setCpf(String cpf) {

        if (cpf.length() == 11 && validarCPF(cpf)) {
            this.cpf = cpf;
        }
    }

    public double getPesoIdeal() {

        return pesoIdeal = obterPesoIdeal();

    }

    public double getImc() {
        return imc;
    }

    public double obterPesoIdeal() {

        //System.out.println(altura);
        //pesoIdeal = 0.0;

        if (sexo == 'M') {
            pesoIdeal = (72.7 * altura) - 58;

        } else {
            pesoIdeal = (62.1 * altura) - 44.7;

        }          //System.out.println(pesoIdeal);
        return pesoIdeal;
    } // fim metodo ObterPesoIdeal

    static String limparCPF(String cpf) {
        String cpfLimpo = "";
        cpfLimpo = cpf.replaceAll("\\D+", "");
        return cpfLimpo;
    }

    static String formatarCPF(String cpf) {

        String cpfFormatado = "";

        cpfFormatado = cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");

        return cpfFormatado;
    }

    private String obterCPFOfuscado() {

        String cpfFormatado = "";

        cpfFormatado = cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "***.$2.***-**");

        return cpfFormatado;

    }

    private int[] calcularDigitoVerificador(char[] cpf) {

        int[] digitosVerificadores = new int[2];
        int[] digitosCPF = new int[9];

        int v1 = -1;
        int v2 = -1;


        // Preenche o vetor digitos CPF de forma inversa
        // o menor índice deve conter o dígito mais à direita do CPF
        for (int i = 8; i >= 0; i--)
            digitosCPF[(digitosCPF.length - 1) - i] = Character.getNumericValue(cpf[i]);


        for (int i = 0; i < digitosCPF.length; i++) {

            v1 = v1 + digitosCPF[i] * (9 - (i % 10));
            v2 = v2 + digitosCPF[i] * (9 - ((i + 1) % 10));

        }


        v1 = (v1 % 11) % 10;
        v2 = v2 + v1 * 9;
        v2 = (v2 % 11) % 10;

        digitosVerificadores[0] = v2;
        digitosVerificadores[1] = v1;

        return digitosVerificadores;

    } // Fim do método calcular dígitos verificadores

    public void obterSituacaoIMC() {

        imc = peso / (altura * altura);

        if (altura <= 0 || altura >= 3 || peso <= 0) {
            System.out.println("Entrada inválida");
        } else {

            if (imc < 17) {

                System.out.println("Muito abaixo do peso.");

            } else if (imc > 17 && imc < 18.49) {

                System.out.println("Abaixo do peso.");

            } else if (imc > 18.50 && imc < 24.99) {

                System.out.println("Peso normal.");

            } else if (imc > 25 && imc < 29.99) {

                System.out.println("Acima do peso.");

            } else if (imc > 30 && imc < 34.99) {

                System.out.println("Obesidade I.");

            } else if (imc > 35 && imc < 39.99) {

                System.out.println("Obesidade II.");

            } else if (imc >= 40) {

                System.out.println("Obesidade III.");

            }

        }
    }

    private double calcularIMC() {
        return (peso / altura * altura);

    } // fim metodo calcularIMC

    private byte calcularIdade() {

        int anoAtual = LocalDate.now().getYear();
        int anoNascimento = nascimento.getYear();

                return (byte)(anoAtual - anoNascimento);
    }

    private boolean validarCPF(String cpf) {

        char[] digitosCPF = cpf.toCharArray();

        int v1 = Character.getNumericValue(cpf.charAt(9));
        int v2 = Character.getNumericValue(cpf.charAt(10));

        int[] digitosVerificados = calcularDigitoVerificador(digitosCPF);

        return (digitosVerificados[0] == v1) && (digitosVerificados[1] == v2);
    }
}// fim class Paciente