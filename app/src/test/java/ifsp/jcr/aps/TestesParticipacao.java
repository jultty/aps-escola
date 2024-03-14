package ifsp.jcr.aps;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Order(2)
class TestesParticipacao {

  @Test
  void dadosPersistemEntreClassesDeTestes() throws IOException, ClassNotFoundException {
    Mensagem resposta = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Mensageiro.codificar(Aluno.class))
    );
    HashMap<Integer, Aluno> alunos =
        Mensageiro.decodificarVarias(resposta.obterCorpo());
    assertTrue(alunos.containsKey(808));
    assertTrue(alunos.containsKey(801));
    assertTrue(alunos.get(808).obterIdDisciplinas().contains(38));
    assertTrue(alunos.get(801).obterIdDisciplinas().contains(38));
  }

  @Test
  void formarUmaParticipacao() throws IOException, ClassNotFoundException {

    HashMap<String, Float> notas = new HashMap<>();
    Participacao participacao = new Participacao(
        808, 38, notas, 3.8F
    );
  }

//  2.2. Registrar em cada aula
//     - 2.2.1. aulas
//     - 2.2.2. e a presença de cada aluno

  @Test @Order(6)
  void  presencasPodemSerRegistradas() {}

//  2.3. Registrar
//     - 2.3.1. notas para cada aluno

  @Test @Order(6)
  void  notasPodemSerRegistradas() {}

}