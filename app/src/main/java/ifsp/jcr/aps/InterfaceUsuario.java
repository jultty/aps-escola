package ifsp.jcr.aps;

public class InterfaceUsuario {

  public String login(Mensagem mensagem) { return "token"; }
  public Boolean logout(String token) { return true; }
  public Mensagem solicitarTela(Mensagem mensagem) {
    return new Mensagem();
  }
  public void exibirTela(Mensagem mensagem) {}

}
