import java.time.LocalDate;
import java.util.*;


public class Cliente{
        private String nomeC;
        private String passC;
        private String emailC;
        private LocalDate dataNascimento;
        private String nmrTeleC;
        private String moradaC;
        private char generoC;
        private String codC;
        private int pontosC;
        private ArrayList listaPremios;
        private static int contador = 1; // contador para os codigos
        private static Set<String> codigosUtilizados = new HashSet<>();// guardas os codigo que ja foram utilizados


    public Cliente(String nomeC, String passC, String emailC, LocalDate dataNascimento, String
                nmrTeleC, String moradaC, char generoC, String codC) {
            this.nomeC = nomeC;
            this.passC=passC;
            this.emailC = emailC;
            this.dataNascimento = dataNascimento;
            this.nmrTeleC=nmrTeleC;
            this.moradaC = moradaC;
            this.setGeneroC(generoC);
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
                this.passC = passC;
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
        this.dataNascimento=dataNascimento;
    }

        public String getNmrTeleC() {
            return nmrTeleC;
        }

        public void setNmrTeleC(String nmrTeleC) {
          this.nmrTeleC=nmrTeleC;
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

    public char getGeneroC() {
        return generoC;
    }

    public void setGeneroC(char generoC) {
       this.generoC=generoC;
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

