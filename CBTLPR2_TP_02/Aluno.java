import java.util.UUID;

public class Aluno {
    private UUID uuid;
    private String endereco;
    private int idade;
    private String nome;

    public Aluno(String nome, String endereco, int idade) {
        this.uuid = UUID.randomUUID();
        this.nome = nome;
        this.endereco = endereco;
        this.idade = idade;
    }

    // Getters
    public UUID getUuid() {
        return uuid;
    }

    public String getEndereco() {
        return endereco;
    }

    public int getIdade() {
        return idade;
    }

    public String getNome() {
        return nome;
    }

    // Setters
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Aluno[uuid=" + uuid + ", nome=" + nome + ", endereco=" + endereco + ", idade=" + idade + "]";
    }
}
