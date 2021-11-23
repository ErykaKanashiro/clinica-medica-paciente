package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.time.LocalTime.parse;

public class Paciente<imc> {

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

    public Paciente(String nome, String sobrenome, char sexo, LocalDate nascimento, short altura, double peso, String cpf) {
        setNome(nome);
        setSobrenome(sobrenome);
        this.sexo = sexo;
        this.nascimento = nascimento;
        setAltura(altura);
        setPeso(peso);
        setCpf(cpf);
        imc = calcularIMC();
    }

    public Paciente(String nome, String sobrenome, char sexo, short altura, double peso, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.sexo = sexo;
        this.altura = altura;
        this.peso = peso;
        this.cpf = cpf;
        imc = calcularIMC();
    }

    public Paciente(String nome, String sobrenome, char sexo, LocalDate nascimento, short altura, double peso) {
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
        if (sobrenome != "" && sobrenome != null)

        this.sobrenome = sobrenome;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public byte getIdade() {
        return idade;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(short altura) {

        if (altura > 0)
        this.altura = altura;
        imc = calcularIMC();

    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {

        if (peso >= 0.0 )
        this.peso = peso;
        imc = calcularIMC();

    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public double getImc() {
        return imc;
    }

    public double obterPesoIdeal(char sexo, short altura) {

        if (sexo == 'M') {
            pesoIdeal = (72.7 * altura) - 58;

        } else if (sexo == 'M') {
            pesoIdeal = (62.1 * altura) - 44.7;

        } else {
            System.out.println("Sexo inválido");

        }
        return pesoIdeal;
    } // fim metodo ObterPesoIdeal

    public void obterCPFOfuscado(){


        getCpf();
        String partes[] = getCpf().split(".",4);
        String partes1[] = getCpf().split(".",8);
        String parte2[] = getCpf().split("-",12);

        return;

    }

    public void obterSituacaoIMC() {

        imc = peso /(altura*altura);

        if ( altura <= 0 || altura >= 3 || peso <= 0){
            System.out.println ("Entrada inválida");
        } else {

            if (imc < 17) {

                System.out.println("Muito abaixo do peso.");

            } else if (imc > 17 && imc < 18.49){

                System.out.println("Abaixo do peso.");

            } else if (imc > 18.50 && imc < 24.99){

                System.out.println("Peso normal.");

            } else if (imc > 25 && imc < 29.99){

                System.out.println("Acima do peso.");

            } else if (imc > 30 && imc < 34.99){

                System.out.println("Obesidade I.");

            } else if (imc > 35 && imc < 39.99){

                System.out.println("Obesidade II.");

            } else if (imc >= 40){

                System.out.println("Obesidade III.");

            }

        }
    }

    private double calcularIMC() {
        return (peso / altura * altura);

    } // fim metodo calcularIMC

    private void calcularIdade(LocalDate data, LocalDate nascimento){

        LocalDate localDate = LocalDate.parse("12/06/1989", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
      //  idade =
    }

    private boolean validarCPF(String cpf) {

        if (cpf == "22222222222" || cpf == "3333333333" || cpf == "44444444444" || cpf == "55555555555"
                || cpf == "66666666666" || cpf == "7777777777" || cpf == "88888888888" || cpf == "99999999999" || cpf.length() != 11)

        return (false);


        cpf = cpf.replaceAll("-", "");
        cpf = cpf.replaceAll("_", "");
        cpf = cpf.replaceAll("/", "");
        cpf = cpf.replaceAll("[^0-9]+", "");

        return (true);
    }

 // FIM DO METODO MAIN
}// fim class Paciente