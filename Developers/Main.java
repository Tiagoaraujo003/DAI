import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Consola c = new Consola();
        ListaCliente listaCliente = new ListaCliente(c);
        while(true){listaCliente.RegistoCliente();}

    }
}

