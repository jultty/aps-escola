package ifsp.jcr.aps;

import java.io.IOException;

public class Controlador {

  public static Mensagem solicitar(Mensagem mensagem) throws IOException, ClassNotFoundException {
    return switch (mensagem.obterOperacao()) {
      case INCLUIR -> GerenciadorDeDados.inserir(Serializador.desserializar(mensagem.obterCorpo()));
      case LISTAR -> GerenciadorDeDados.listar(Serializador.desserializar(mensagem.obterCorpo()));
      case MATRICULAR -> GerenciadorDeDados.matricular(mensagem.obterIds(), mensagem.obterCorpo());
      default -> throw new IllegalArgumentException("Operação não suportada");
    };
  }
}