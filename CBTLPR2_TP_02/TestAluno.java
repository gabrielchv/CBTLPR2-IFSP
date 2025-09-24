public class TestAluno {
    public static void main(String[] args) {
        // Test creating some Aluno instances
        Aluno aluno1 = new Aluno("Gabriel", "Rua A, 123", 25);
        Aluno aluno2 = new Aluno("Maria", "Rua B, 456", 22);
        Aluno aluno3 = new Aluno("João", "Rua C, 789", 28);

        // Display the students
        System.out.println("=== Teste da Classe Aluno ===");
        System.out.println(aluno1);
        System.out.println(aluno2);
        System.out.println(aluno3);

        // Test getters
        System.out.println("\n=== Teste dos Getters ===");
        System.out.println("Nome: " + aluno1.getNome());
        System.out.println("Endereço: " + aluno1.getEndereco());
        System.out.println("Idade: " + aluno1.getIdade());
        System.out.println("UUID: " + aluno1.getUuid());

        // Test setters
        System.out.println("\n=== Teste dos Setters ===");
        aluno1.setNome("Gabriel Silva");
        aluno1.setEndereco("Rua Nova, 999");
        aluno1.setIdade(26);
        System.out.println("Aluno após alterações: " + aluno1);

        System.out.println("\n=== Para testar a interface gráfica, execute: java AlunoForm ===");
    }
}
