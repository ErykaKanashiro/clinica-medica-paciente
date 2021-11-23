package com.company;

public class Main {

    public static void main(String[] args) {

        String cpf = "399.601.638-73";
        String[] partes = cpf.split(".",4);
        String cpf2 = obterCFPOfuscado(partes);


        System.out.println(cpf2.toString());
    }

    private static String obterCFPOfuscado(String[] cpf) {

        String CPF = "";
        String partes[] = CPF.split(".",4);
        String partes1[] = CPF.split(".",8);
        String parte2[] = CPF.split("-",12);

        return CPF;
    }
}
