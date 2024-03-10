import java.util.ArrayList;

public class ListaCliente {
    private ArrayList<Cliente> listaC;

    public ListaCliente(){
        listaC =  new ArrayList<>();
    }

    public boolean adicionarCliente(Cliente c){
        String nomeC = c.getNomeC();
        String emailC = c.getEmailC();
        if(!existeCliente(nomeC, emailC)){
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
            if(c.getNomeC().equals(emailC) && c.getPassC().equals(passC)){
                return c;
            }

        }

        return null;
    }
    public boolean existeCliente(String emailC, String passC){
        for(Cliente c : listaC){
            if(c.getNomeC().equals(emailC) && c.getPassC().equals(passC)){
                return true;
            }
        }
        return false;

    }

    
}
