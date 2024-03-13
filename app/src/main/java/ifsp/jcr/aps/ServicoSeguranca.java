package ifsp.jcr.aps;

public class ServicoSeguranca {

  public Boolean autenticar(Mensagem mensagem) { return true; }
  public Boolean autorizar(Mensagem mensagem) { return true; }
  private String gerarToken(Mensagem mensagem) { return ""; }

}
