
public class Cliente {

    private String nomecliente;
    private String endereco;
    private final String complemento;
    private String telefone;

    public Cliente(String nomecliente, String endereco, String telefone, String complemento) {
        this.nomecliente = nomecliente;
        this.endereco = endereco;
        this.telefone = telefone;
        this.complemento = complemento;

    }

    public String getNomeCliente() {
        return nomecliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String toString() {
        return "Cliente: " + nomecliente + " Endereço: " + endereco + " Complemento: " + complemento + "  Telefone/Whatsapp: " + telefone;
    }

}
