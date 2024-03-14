package ifsp.jcr.aps;

import java.util.HashSet;

public class Mensagem {
  private String token;
  private OPERACAO operacao;
  private HashSet<Integer> ids;
  private String corpo;

  Mensagem() {}

  Mensagem(String token, OPERACAO operacao, HashSet<Integer> ids, String corpo) {
    this.token = token;
    this.operacao = operacao;
    this.ids = ids;
    this.corpo = corpo;
  }

  Mensagem(OPERACAO operacao, String corpo) {
    this.operacao = operacao;
    this.corpo = corpo;
  }

  Mensagem(OPERACAO operacao, HashSet<Integer> ids, String corpo) {
    this.operacao = operacao;
    this.ids = ids;
    this.corpo = corpo;
  }

  Mensagem(String corpo) {
    this.corpo = corpo;
  }

  public OPERACAO obterOperacao() { return operacao; }
  public String obterCorpo() {
    return corpo;
  }
  public HashSet<Integer> obterIds() { return ids; }
}
