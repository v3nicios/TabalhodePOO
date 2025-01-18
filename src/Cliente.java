public class Cliente {
    
   private String nomecliente;
    private String endereco;
    private String complemento;
    private int telefone;

    public Cliente (String nomecliente, String endereco, int telefone, String complemento ){
        this.nomecliente = nomecliente;
        this.endereco = endereco;
        this.telefone = telefone;
        this.complemento = complemento;


    }
    public String getNomeCliente(){
       return nomecliente;
    }
    public String getEndereco(){
        return endereco;
     }
     public String getComplemento(){
        return complemento;
     }
     public int getTelefone(){
        return telefone;
     }

      public String toString() {
        return "CLiente" + nomecliente + "|Endereço" + endereco + "|Complemento" + complemento + "|Telefone/Whatsapp" ;
    }

}
