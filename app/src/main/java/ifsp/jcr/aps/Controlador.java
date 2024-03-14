package ifsp.jcr.aps;

import java.io.IOException;

public class Controlador {

  public static Mensagem solicitar(Mensagem mensagem) throws IOException, ClassNotFoundException {
    return switch (mensagem.obterOperacao()) {
      case INCLUIR -> GerenciadorDeDados.inserir(Mensageiro.decodificar(mensagem.obterCorpo()));
      case LISTAR -> GerenciadorDeDados.listar(Mensageiro.decodificar(mensagem.obterCorpo()));
      case MATRICULAR -> GerenciadorDeDados.matricular(mensagem.obterIds(), mensagem.obterCorpo());
      default -> null;
    };
  }
}