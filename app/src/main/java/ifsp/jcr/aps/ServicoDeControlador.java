package ifsp.jcr.aps;

import java.io.IOException;

import static ifsp.jcr.aps.ServicoDeMensageiro.Mensageiro;

public class ServicoDeControlador {
  public static class Controlador {

    public static Mensagem solicitar(Mensagem mensagem) throws IOException, ClassNotFoundException {
      return switch (mensagem.obterOperacao()) {
        case INCLUIR -> {
          yield GerenciadorDeDados.criar(Mensageiro.decodificar(mensagem.obterCorpo()));
        }
        case LISTAR -> { yield GerenciadorDeDados.listar(new Turma()); }
        default -> null;
      };
    }
  }
}
