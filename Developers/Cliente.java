import java.time.LocalDate;
import java.util.*;


public class Cliente{
        private String nomeC;
        private String passC;
        private String emailC;
        private LocalDate dataNascimento;
        private String nmrTeleC;
        private String moradaC;
        private String codC;
        private int pontosC;
        private ArrayList listaPremios;
        private static int contador = 1; // contador para os codigos
        private static Set<String> codigosUtilizados = new HashSet<>();// guardas os codigo que ja foram utilizados

        public Cliente(String nomeC, String passC, String emailC, LocalDate dataNascimento, String
                nmrTeleC, String moradaC) {
            this.nomeC = nomeC;
            this.setPassC(passC);
            this.emailC = emailC;
            this.dataNascimento = dataNascimento;
            this.setNmrTeleC(nmrTeleC);
            this.moradaC = moradaC;
            this.pontosC=0;
            this.codC= gerarCodigoUnico();
            this.listaPremios=new ArrayList<>();
        }

        public String getNomeC() {
            return nomeC;
        }

        public void setNomeC(String nomeC) {
            this.nomeC = nomeC;
        }

        public String getPassC() {
            return passC;
        }

        public void setPassC(String passC) {

            if (passC.matches("^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])(?=.*\\d).{8,}$")) {
                this.passC = passC;
            } else {
                System.out.println("Erro: A senha deve ter pelo menos 8 caracteres, conter pelo menos uma letra maiúscula e um caractere especial.");
            }

        }

        public String getEmailC() {
            return emailC;
        }

        public void setEmailC(String emailC) {
            this.emailC = emailC;
        }

        public LocalDate getDataNascimento() {
            return dataNascimento;
        }

        public void setDataNascimento(LocalDate dataNascimento) {
            this.dataNascimento = dataNascimento;
        }

        public String getNmrTeleC() {
            return nmrTeleC;
        }

        public void setNmrTeleC(String nmrTeleC) {
           if(nmrTeleC.length() == 9){
               this.nmrTeleC = nmrTeleC;
           }else{
               System.out.println("Erro");
           }
        }
        public String getMoradaC() {
            return moradaC;
        }

        public void setMoradaC(String moradaC) {
            this.moradaC = moradaC;
        }

        public int getPontosC() {
            return pontosC;
        }

        public void setPontosC(int pontosC) {
            this.pontosC = pontosC;
        }

        public String getCodC() {
            return codC;
        }


        public static String gerarCodigoUnico() { //cria codigo a medida que os clientes se registram
            String codigo;                        // e o hashset verifica se eles não são repetidos se neste caso forem cria outro
            do {
                codigo = "C" + contador++;
            } while (codigosUtilizados.contains(codigo));
            codigosUtilizados.add(codigo);
            return codigo;
        }
    }