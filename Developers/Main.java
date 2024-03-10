import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Cliente c1 = new Cliente("João", "Rm123456!", "joao@example.com", LocalDate.of(2004, 12, 15), "939022312", "Rua A");
        Cliente c2 = new Cliente("Maria", "P@ssw0rd", "maria@example.com", LocalDate.of(1990, 5, 20), "987654321", "Rua B");
        Cliente c3 = new Cliente("Carlos", "C4rl0str!", "carlos@example.com", LocalDate.of(1985, 3, 10), "123456789", "Rua C");
        Cliente c4 = new Cliente("Ana", "Aninha12_", "ana@example.com", LocalDate.of(2000, 8, 25), "567890123", "Rua D");
        Cliente c5 = new Cliente("Pedro", "PedroS@nt0s", "pedro@example.com", LocalDate.of(1975, 9, 5), "246801357", "Rua E");
        Cliente c6 = new Cliente("Marta", "M4rt4linda_", "marta@example.com", LocalDate.of(1995, 7, 12), "135792468", "Rua F");
        Cliente c7 = new Cliente("Paulo", "Paulinho123_", "paulo@example.com", LocalDate.of(1988, 11, 30), "987654321", "Rua G");
        Cliente c8 = new Cliente("Sara", "Sar@nha123_", "sara@example.com", LocalDate.of(2002, 2, 18), "123456789", "Rua H");

        System.out.println("Código gerado para c1: " + c1.getCodC());
        System.out.println("Código gerado para c2: " + c2.getCodC());
        System.out.println("Código gerado para c3: " + c3.getCodC());
        System.out.println("Código gerado para c4: " + c4.getCodC());
        System.out.println("Código gerado para c5: " + c5.getCodC());
        System.out.println("Código gerado para c6: " + c6.getCodC());
        System.out.println("Código gerado para c7: " + c7.getCodC());
        System.out.println("Código gerado para c8: " + c8.getCodC());
    }
}

