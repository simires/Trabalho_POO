package models;

import java.time.LocalDate;

public class Movie {
    private int id;
    private String title;
    private int releaseYear;

    public Movie(int id, String title, int releaseYear) {
        this.id = id;
        setTitle(title);
        setReleaseYear(releaseYear);
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    /*
    * O método "setTitle" define o título de um filme. Ele verifica se o título fornecido é uma string vazia.
    * Se não for, o método atribui o valor fornecido à variável "title" do objeto atual.
    * Caso contrário, o valor não é atribuído e permanece inalterado.
    * */
    public void setTitle(String title) {
        if (!title.equals("")) { this.title = title; }
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    /*
    * O método "setReleaseYear" define o ano de lançamento de um filme. Ele utiliza a classe LocalDate para obter o ano atual com base na data do sistema.
    * Em seguida, verifica se o ano de lançamento fornecido está dentro do intervalo válido de 1895 (ano do primeiro filme) até o ano atual.
    * Se o ano de lançamento estiver dentro desse intervalo, o método atribui o valor fornecido à variável "releaseYear" do objeto atual.
    * Caso contrário, o valor não é atribuído e permanece inalterado.
    * */
    public void setReleaseYear(int releaseYear) {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        if (releaseYear >= 1895 && releaseYear <= currentYear) { this.releaseYear = releaseYear; }
    }
}
