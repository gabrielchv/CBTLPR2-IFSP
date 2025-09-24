public class TestAuthor {
    public static void main(String[] args) {
        Author a1 = new Author("Gabriel Chaves", "gabriel.chaves@aluno.ifsp.edu.br", 'm');
        System.out.println(a1); // toString()

        System.out.println("Name: " + a1.getName());
        System.out.println("Email: " + a1.getEmail());
        System.out.println("Gender: " + a1.getGender());

        a1.setEmail("novoemail@gmail.com");
        System.out.println("Updated email: " + a1.getEmail());
    }
}
