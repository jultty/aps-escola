package ifsp.jcr.aps;

import java.io.IOException;

public class Controlador {

  public static Mensagem solicitar(Mensagem mensagem) throws IOException, ClassNotFoundException {
    return switch (mensagem.obterOperacao()) {
      case INCLUIR -> GerenciadorDeDados.criar(Mensageiro.decodificar(mensagem.obterCorpo()));
      case LISTAR -> GerenciadorDeDados.listar(new Turma());
      default -> null;
    };
  }
}