// Arquivo: Pessoa.java

public class Pessoa {

    // A propriedade "kp" é estática e deve nos informar quantas pessoas distintas
    // foram "setadas", deve funcionar como um contador.
    private static int kp = 0; // # kp: static int

    // Propriedades da instância
    private String nome; // # nome: String
    private char sexo;   // # sexo: char
    private int idade;   // # idade: int

    // + Pessoa()
    public Pessoa() {
        // Construtor padrão: incrementa o contador ao criar uma nova instância.
        setKp(); 
    }

    // + Pessoa(String, char, int)
    public Pessoa(String nome, char sexo, int idade) {
        this(); // Chama o construtor padrão para incrementar kp
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
    }

    // Métodos Setter

    // + setKp()
    // Incrementa o contador estático
    private static void setKp() {
        Pessoa.kp++;
    }

    // + setNome(String)
    public void setNome(String nome) {
        this.nome = nome;
    }

    // + setSexo(char)
    public void setSexo(char sexo) {
        this.sexo = Character.toUpperCase(sexo);
    }

    // + setIdade(int)
    public void setIdade(int idade) {
        this.idade = idade;
    }

    // Métodos Getter

    // + getKp(): int
    public static int getKp() {
        return Pessoa.kp;
    }

    // + getNome(): String
    public String getNome() {
        return nome;
    }

    // + getSexo(): char
    public char getSexo() {
        return sexo;
    }

    // + getIdade(): int
    public int getIdade() {
        return idade;
    }
}