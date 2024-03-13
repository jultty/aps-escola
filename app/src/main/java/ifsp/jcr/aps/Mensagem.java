package ifsp.jcr.aps;

public class Mensagem {
  private String token;
  private OPERACAO operacao;
  private Integer[] ids;
  private String corpo;

  Mensagem() {}

  Mensagem(String token, OPERACAO operacao, Integer[] ids, String corpo) {
    this.token = token;
    this.operacao = operacao;
    this.ids = ids;
    this.corpo = corpo;
  }

  Mensagem(OPERACAO operacao, String corpo) {
    this.operacao = operacao;
    this.corpo = corpo;
  }

  Mensagem(String corpo) {
    this.corpo = corpo;
  }

  OPERACAO obterOperacao() {
    return operacao;
  }

  String obterCorpo() {
    return corpo;
  }
}
