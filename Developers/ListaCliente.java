import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ListaCliente {
    private ArrayList<Cliente> listaC;
    private Consola con;
    private Scanner scanner = new Scanner(System.in);
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public ListaCliente(Consola con){
        this.con = con;
        listaC =  new ArrayList<>();
    }

    public boolean adicionarCliente(Cliente c){
        String passC = c.getPassC();
        String emailC = c.getEmailC();
        if(!existeCliente(passC, emailC)){
            listaC.add(c);
            return true;
        }else{
            System.out.println("Essas credenciais já foram utilizadas");
            return false;
        }

    }

    public boolean removerCliente(String emailC, String passC){
        Cliente clienteParaRemover;
        if(existeCliente(emailC, passC)){
            clienteParaRemover=encontrarCliente(emailC, passC);
            listaC.remove(clienteParaRemover);
            return true;

        }else{
            return false;
        }
    }

    public Cliente encontrarCliente(String emailC, String passC){
        for(Cliente c : listaC){
            if(c.getEmailC().equals(emailC) && c.getPassC().equals(passC)){
                return c;
            }

        }

        return null;
    }
    public boolean existeCliente(String emailC, String passC){
        for(Cliente c : listaC){
            if(c.getEmailC().equals(emailC) && c.getPassC().equals(passC)){
                return true;
            }
        }
        return false;

    }

    public boolean verificarFormatoData(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    // Método para verificar se a data é válida no calendário
    public boolean verificarDataValida(LocalDate data) {
        int ano = data.getYear();
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();

        // Verifica se o ano é bissexto
        boolean bissexto = ano % 400 == 0 || (ano % 4 == 0 && ano % 100 != 0);

        // Verifica se o mês é válido
        if (mes < 1 || mes > 12) {
            return false;
        }

        // Verifica se o dia está dentro do intervalo válido para o mês
        switch (mes) {
            case 2:
                if (bissexto) {
                    return dia >= 1 && dia <= 29;
                } else {
                    return dia >= 1 && dia <= 28;
                }
            case 4:
            case 6:
            case 9:
            case 11:
                return dia >= 1 && dia <= 30;
            default:
                return dia >= 1 && dia <= 31;
        }
    }

    /*public static boolean verificarDataValida(LocalDate data) {
        int ano = data.getYear();
        int mes = data.getMonthValue();
        int dia = data.getDayOfMonth();

        if (mes < 1 || mes > 12) {
            return false; // Mês inválido
        }

        if (dia < 1 || dia > LocalDate.of(ano, mes, 1).lengthOfMonth()) {
            return false; // Dia inválido para o mês/ano fornecidos
        }

        return true;
    }*/


    public boolean validarSenha(String senha) {
        return senha.matches("^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])(?=.*\\d).{8,}$");
    }

    public boolean validarTelemovel(String nmrtele){
        return nmrtele.length() == 9;
    }

    public boolean validarEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validarGenero(char generoC){
        char generoMinusculo = Character.toLowerCase(generoC);
        return generoMinusculo == 'f' || generoMinusculo == 'm';
    }





    public void RegistoCliente(){


        String nomeC;
        do {
            nomeC = con.lerString("Nome: ");

            // Verifica se o nome já está em uso
            for (Cliente cliente : listaC) {
                if (cliente.getNomeC().equals(nomeC)) {
                    System.out.println("Este Nome já está em uso. Por favor, insira outro.");
                    nomeC = null; // Reseta o valor do nome para que o loop continue pedindo o nome
                    break; // Sai do loop para que não seja feita mais de uma verificação
                }
            }
        } while (nomeC == null); // Continua pedindo o nome até que um nome único seja fornecido

        String passC;
        do {
            passC = con.lerString("Password: ");
            if (!validarSenha(passC)) {
                System.out.println("Erro: A senha deve ter pelo menos 8 caracteres, conter pelo menos uma letra maiúscula, um caractere especial e um numero.");
            }
        } while (!validarSenha(passC));

        String emailC;
        do {
            emailC = con.lerString("Email: ");

            // Verifica se o email é válido
            if (!validarEmail(emailC)) {
                System.err.println("Email inválido");
                continue;
            }

            // Verifica se o email já está em uso
            for (Cliente cliente : listaC) {
                if (cliente.getEmailC().equals(emailC)) {
                    System.out.println("Este email já está em uso. Por favor, insira outro.");
                    break; // Sai do loop de verificação
                }
            }

            // Se o email já estiver em uso, o loop continua
        } while (!validarEmail(emailC));



        LocalDate dataNascimentoC = null;
        boolean dataValida = false;
        while (!dataValida) {
            String input = con.lerString("Data Nascimento (dd/MM/yyyy): ");
            if (!verificarFormatoData(input)) {
                System.err.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
                continue; // Volta ao início do loop para pedir a data novamente
            }

            dataNascimentoC = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (verificarDataValida(dataNascimentoC)) {
                // Verifica se a data passa no critério adicional
                if (dataNascimentoC.getYear() <= LocalDate.now().getYear()) {
                    dataValida = true; // Marca a data como válida para sair do loop
                } else {
                    System.err.println("Data inválida. Por favor, insira novamente.");
                }
            } else {
                System.err.println("Data inválida. Por favor, insira novamente.");
            }
        }

        String teleC;
        do {
            teleC = con.lerString("Telemóvel: ");
            if (!validarTelemovel(teleC)) {
                System.err.println("A senha deve ter pelo menos 9 caracteres, conter pelo menos uma letra maiúscula e um caractere especial.");
            }
        } while (!validarTelemovel(teleC));
        String moradaC = con.lerString("Morada:");

        char generoC;
        do {
            generoC = con.lerChar("Género:");
            if (!validarGenero(generoC)) {
                System.err.println("Género inválido. Por favor, insira 'F' ou 'M'");
            }
        } while (!validarGenero(generoC));

        // Se nenhum problema foi encontrado, adiciona o cliente
        String codigoCliente = Cliente.gerarCodigoUnico();
        Cliente novoCliente = new Cliente(nomeC, passC, emailC,dataNascimentoC,teleC,moradaC,generoC,codigoCliente);
        novoCliente.setDataNascimento(dataNascimentoC);


        if (adicionarCliente(novoCliente)) {
            System.out.println("Cliente registrado com sucesso!");
        }
    }

}
