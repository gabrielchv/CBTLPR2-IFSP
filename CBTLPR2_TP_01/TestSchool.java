public class TestSchool {
    public static void main(String[] args) {
        Student s1 = new Student("Gabriel", "Rua A", "ADS", 2025, 1500.0);
        System.out.println(s1);

        Staff st1 = new Staff("Virginia", "Rua B", "IFSP", 4500.0);
        System.out.println(st1);

        // Test setters
        s1.setFee(2000);
        st1.setPay(5000);
        System.out.println("Updated Student Fee: " + s1.getFee());
        System.out.println("Updated Staff Pay: " + st1.getPay());
    }
}
