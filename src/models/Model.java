package models;

import java.util.HashMap;
import java.util.Random;
import java.util.Map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Model {
    private int sizeMaxStringMovie;
    private int countMovies;
    private HashMap<String, User> registredUsers;
    private HashMap<Integer, Movie> registredMovies;
    private User currentUser;

    public Model() {
        this.sizeMaxStringMovie = 0;
        this.countMovies = 0;
        this.registredUsers = new HashMap<>();
        this.registredMovies = loadMovies();
        this.currentUser = null;
    }

    /*
    *  O método "getUser" retorna um objeto do tipo "User" com base em um nickname fornecido.
    *  Ele verifica se o nickname não é uma string vazia e, em seguida, procura o usuário correspondente no HashMap "registeredUsers".
    *  Se encontrado, retorna o objeto "User"; caso contrário, retorna nulo.
    * */
    public User getUser(String nickname) {
        User user = null;
        if (!nickname.equals("")) { user = registredUsers.get(nickname); }
        return user;
    }

    /*
    * O método "setUser" adiciona um usuário ao HashMap "registeredUsers" se o objeto "User" não for nulo e se o nickname e a senha do usuário não forem vazios.
    * */
    public void setUser(User user) {
        if (user != null && !user.getNickname().equals("") && !user.getPassword().equals("")) { registredUsers.put(user.getNickname(), user); }
    }

    /*
    * O método "validateUser" verifica se um usuário é válido com base no nickname e senha fornecidos.
    * Se o nickname e a senha não forem vazios, o método tenta recuperar o objeto "User" correspondente ao nickname do HashMap "registeredUsers".
    * Se o objeto "User" for encontrado, verifica se o nickname e a senha fornecidos correspondem aos do usuário.
    * Se forem iguais, o objeto "User" é atribuído à variável "currentUser" (usuário atualmente logado) e é retornado.
    * Caso contrário, "currentUser" será nulo.
    * */
    public User validateUser(String nickname, String password) {
        User user;
        if (!nickname.equals("") && !password.equals("")) {
            user = registredUsers.get(nickname);
            if (user != null) {
                if (nickname.equals(user.getNickname()) && password.equals(user.getPassword())) {
                    currentUser = user;
                }
            }
        }
        return currentUser;
    }

    /*
    * O método "loadMovies" lê um arquivo de texto contendo informações de filmes, cria objetos "Movie" com os dados lidos e os armazena em um HashMap.
    * O método retorna esse HashMap com os filmes registrados. Além disso, variáveis importantes para o sistema, como "countMovies" e "sizeMaxStringMovie",
    * são compreendidas nesse método.
    * */
    private HashMap<Integer, Movie> loadMovies() {
        HashMap<Integer, Movie> registredMovies = new HashMap<Integer, Movie>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/movies.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                countMovies += 1;
                String[] parts = line.split(",");
                String title = parts[0];
                int releaseYear = Integer.parseInt(parts[1]);
                Movie movie = new Movie(countMovies, title, releaseYear);
                registredMovies.put(countMovies, movie);
                if (title.length() > sizeMaxStringMovie) { sizeMaxStringMovie = title.length(); }
            }
        } catch (IOException e) {
           e.printStackTrace();
        }
        return registredMovies;
    }

    /*
    * O método "unwatchedMovies" retorna um HashMap com os filmes não assistidos pelo usuário atual.
    * Ele itera sobre os filmes registrados, verificando se o usuário não os assistiu, e os adiciona ao HashMap de filmes não assistidos.
    * */
    public HashMap<Integer, Movie> unwatchedMovies() {
        HashMap<Integer, Movie> unwatchedMovies = new HashMap<Integer, Movie>();
        if (currentUser != null) {
            for (Map.Entry<Integer, Movie> entry : registredMovies.entrySet()) {
                int id = entry.getKey();
                if (!currentUser.getWacthedMovies().containsKey(id)) {
                    Movie currentMovie = new Movie(id, entry.getValue().getTitle(), entry.getValue().getReleaseYear());
                    unwatchedMovies.put(id, currentMovie);
                }
            }
        }
        return unwatchedMovies;
    }

    /*
    * O método "getIndexRandomMovie" retorna um índice aleatório com base no tamanho fornecido.
    * */
    public int getIndexRandomMovie(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }

    /*
    * O método "setWatchedMovie" adiciona um filme assistido ao mapa de filmes assistidos pelo usuário atual.
    * Ele verifica se o "currentUser" não é nulo e, em seguida, adiciona o filme ao mapa "currentUser.getWatchedMovies()" usando
    * o ID do filme como chave e o objeto "Movie" como valor.
    * */
    public void setWatchedMovie(Movie movie) {
        if (currentUser != null) {
            currentUser.getWacthedMovies().put(movie.getId(), movie);
        }
    }

    /*
    * O método "logoutUser" redefine o valor da variável "currentUser" como nulo, efetuando assim o logout do usuário atual.
    * Isso significa que o usuário atualmente logado no sistema será desconectado, uma vez que a referência para esse usuário será apagada.
    * */
    public void logoutUser() { currentUser = null; }

    public User getCurrentUser() {
        return currentUser;
    }

    public HashMap<String, User> getRegistredUsers() {
        return registredUsers;
    }

    public HashMap<Integer, Movie> getRegistredMovies() {
        return registredMovies;
    }

    public int getCountMovies() {
        return countMovies;
    }

    public int getSizeMaxStringMovie() {
        return sizeMaxStringMovie;
    }
}
