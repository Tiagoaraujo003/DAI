package com.example.dai;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ListaCliente implements Serializable {
    private ArrayList<Cliente> listaC;
    private Consola con;
    private Scanner scanner = new Scanner(System.in);
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    public ListaCliente(Consola con) {
        this.con = con;
        listaC = new ArrayList<>();
    }

    public ArrayList<Cliente> getListaC() {
        return listaC;
    }


    public boolean adicionarCliente(Cliente c) {
        String passC = c.getPassC();
        String emailC = c.getEmailC();
        if (!existeCliente(passC, emailC)) {
            listaC.add(c);
            return true;
        } else {
            System.out.println("Essas credenciais já foram utilizadas");
            return false;
        }

    }

    public boolean removerCliente(String emailC, String passC) {
        Cliente clienteParaRemover;
        if (existeCliente(emailC, passC)) {
            clienteParaRemover = encontrarCliente(emailC, passC);
            listaC.remove(clienteParaRemover);
            return true;

        } else {
            return false;
        }
    }

    public Cliente encontrarCliente(String emailC, String passC) {
        for (Cliente c : listaC) {
            if (c.getEmailC().equals(emailC) && c.getPassC().equals(passC)) {
                return c;
            }

        }

        return null;
    }

    public boolean existeCliente(String emailC, String passC) {
        for (Cliente c : listaC) {
            if (c.getEmailC().equals(emailC) && c.getPassC().equals(passC)) {
                return true;
            }
        }
        return false;

    }

    public Cliente encontrarClientePorCodigo(String codC) {
        for (Cliente c : listaC) {
            if (c.getCodC().equals(codC)) {
                return c; // Retorna o cliente encontrado
            }
        }
        return null; // Retorna null se nenhum cliente com o código especificado for encontrado
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


    public boolean validarSenha(String senha) {
        return senha.matches("^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])(?=.*\\d).{8,}$");
    }

    public boolean validarEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validarGenero(char generoC) {
        char generoMinusculo = Character.toLowerCase(generoC);
        return generoMinusculo == 'f' || generoMinusculo == 'm';
    }

    public void RegistoCliente() {

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
        boolean emailEmUso = false;
        do {
            emailC = con.lerString("Email: ");
            // Verifica se o email é válido
            if (!validarEmail(emailC)) {
                System.err.println("Email inválido");
                continue; // Volta ao início do loop para pedir o email novamente
            }

            // Verifica se o email já está em uso
            emailEmUso = false; // Define como false por padrão
            for (Cliente cliente : listaC) {
                if (cliente.getEmailC().equals(emailC)) {
                    emailEmUso = true;
                    System.out.println("Este email já está em uso. Por favor, insira outro.");
                    break; // Sai do loop de verificação
                }
            }
            // Se o email já estiver em uso, o loop continua
        } while (emailEmUso);


        LocalDate dataNascimentoC = null;
        boolean dataValida = false;
        while (!dataValida) {
            String input = con.lerString("Data Nascimento (dd/MM/yyyy): ");
            if (!verificarFormatoData(input)) {
                System.err.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
                continue; // Volta ao início do loop para pedir a data novamente
            }

            dataNascimentoC = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            // Verifica se a data passa no critério adicional
            if (dataNascimentoC.getYear() <= LocalDate.now().getYear()) {
                dataValida = true; // Marca a data como válida para sair do loop
            } else {
                System.err.println("Data inválida. Por favor, insira novamente.");
            }

        }


        char generoC;
        do {
            generoC = con.lerChar("Género:");
            if (!validarGenero(generoC)) {
                System.err.println("Género inválido. Por favor, insira 'F' ou 'M'");
            }
        } while (!validarGenero(generoC));

        // Se nenhum problema foi encontrado, adiciona o cliente
        String codigoCliente = Cliente.gerarCodigoUnico();
        Cliente novoCliente = new Cliente(nomeC, passC, emailC, dataNascimentoC, generoC, codigoCliente, 0);
        novoCliente.setDataNascimento(dataNascimentoC);


        if (adicionarCliente(novoCliente)) {
            System.out.println("Cliente registrado com sucesso!");
        }
    }

    public void editarCliente(String nomeC) {
        try (Scanner scanner = new Scanner(System.in)) {
            for (Cliente cliente : listaC) {
                if (cliente.getNomeC().equals(nomeC)) {
                    System.out.println("O que deseja editar?");
                    System.out.println("1- Nome");
                    System.out.println("2- Password");
                    System.out.println("3- Email");
                    System.out.println("4- Data Nascimento");
                    System.out.println("5- Número de Telemóvel");
                    System.out.println("6- Morada");
                    System.out.println("7- Gênero");

                    int escolha = scanner.nextInt();
                    scanner.nextLine(); // Consumir a quebra de linha após o próximo inteiro

                    switch (escolha) {
                        case 1:
                            String novoNome;
                            do {
                                novoNome = con.lerString("Insira o novo nome:");
                                // Verifica se o nome já está em uso
                                for (Cliente c : listaC) {
                                    if (c.getNomeC().equals(novoNome)) {
                                        System.out.println("Este nome já está em uso. Por favor, insira outro.");
                                        novoNome = null; // Reseta o valor do nome para que o loop continue pedindo o nome
                                        break; // Sai do loop de verificação
                                    }
                                }
                            } while (novoNome == null); // Continua pedindo o nome até que um nome único seja fornecido
                            cliente.setNomeC(novoNome);
                            System.out.println("Nome alterado com sucesso");
                            break;
                        case 2:
                            String novaPass;
                            do {
                                novaPass = con.lerString("Insira a nova password:");
                                if (!validarSenha(novaPass)) {
                                    System.out.println("Erro: A senha deve ter pelo menos 8 caracteres, conter pelo menos uma letra maiúscula, um caractere especial e um número.");
                                }
                            } while (!validarSenha(novaPass));
                            cliente.setPassC(novaPass);
                            System.out.println("Password alterada com sucesso");
                            break;
                        case 3:
                            String novoEmail;
                            do {
                                novoEmail = con.lerString("Insira o novo email:");
                                // Verifica se o email é válido
                                if (!validarEmail(novoEmail)) {
                                    System.err.println("Email inválido");
                                    continue;
                                }
                                // Verifica se o email já está em uso
                                for (Cliente c : listaC) {
                                    if (c.getEmailC().equals(novoEmail)) {
                                        System.out.println("Este email já está em uso. Por favor, insira outro.");
                                        novoEmail = null; // Reseta o valor do email para que o loop continue pedindo o email
                                        break; // Sai do loop de verificação
                                    }
                                }
                            } while (novoEmail == null || !validarEmail(novoEmail)); // Continua pedindo o email até que um email válido e único seja fornecido
                            cliente.setEmailC(novoEmail);
                            System.out.println("Email alterado com sucesso");
                            break;
                        case 4:
                            // Adicionar as verificações para a data de nascimento como foi feito no método RegistoCliente
                            LocalDate novaData = null;
                            boolean dataValida = false;
                            while (!dataValida) {
                                String novaDataString = con.lerString("Insira a nova data de nascimento (dd/MM/yyyy):");
                                if (!verificarFormatoData(novaDataString)) {
                                    System.err.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
                                    continue; // Volta ao início do loop para pedir a data novamente
                                }
                                novaData = LocalDate.parse(novaDataString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                if (novaData.getYear() <= LocalDate.now().getYear()) {
                                    dataValida = true; // Marca a data como válida para sair do loop
                                } else {
                                    System.err.println("Data inválida. Por favor, insira novamente.");
                                }
                            }
                            cliente.setDataNascimento(novaData);
                            System.out.println("Data de nascimento alterada com sucesso");
                            break;
                        case 5:
                            char novoGenero;
                            do {
                                novoGenero = con.lerChar("Insira o novo género (M/F):");
                                if (!validarGenero(novoGenero)) {
                                    System.err.println("Género inválido. Por favor, insira 'F' ou 'M'");
                                }
                            } while (!validarGenero(novoGenero));
                            cliente.setGeneroC(novoGenero);
                            System.out.println("Género alterado com sucesso");
                            break;
                        default:
                            System.out.println("Opção inválida");
                    }
                }
            }
        }
    }



}
