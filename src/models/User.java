package models;

import java.util.HashMap;

public class User {
    private String nickname;
    private String password;
    private HashMap<Integer, Movie> wacthedMovies = new HashMap<Integer, Movie>();

    public String getNickname() {
        return nickname;
    }

    /*
    * O método "setNickname" define o apelido de um usuário. Ele verifica se o apelido fornecido não é uma string vazia.
    * Se não for vazio, o método atribui o valor fornecido à variável "nickname" do objeto atual.
    * Caso contrário, o valor não é atribuído e permanece inalterado.
    * */
    public void setNickname(String nickname) { if (!nickname.equals("")) { this.nickname = nickname; } }

    public String getPassword() {
        return password;
    }

    /*
     * O método "setPassword" define a senha de um usuário. Ele verifica se a senha fornecida não é uma string vazia.
     * Se não for vazia, o método atribui o valor fornecido à variável "password" do objeto atual.
     * Caso contrário, o valor não é atribuído e permanece inalterado.
     * */
    public void setPassword(String password) {
        if (!password.equals("")) { this.password = password; }
    }

    public HashMap<Integer, Movie> getWacthedMovies() {
        return wacthedMovies;
    }

    public void setWacthedMovies(HashMap<Integer, Movie> wacthedMovies) {
        this.wacthedMovies = wacthedMovies;
    }
}
