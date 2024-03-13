package ifsp.jcr.aps;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//  1. Formar uma turma, ou seja
//     - 1.1. relacionar um professor com uma disciplina
//     - 1.2. e com um conjunto de alunos

class AppTest {

  @Test void formarUmaTurma() throws IOException, ClassNotFoundException {

    Integer[] idAlunos = { 1, 2, 3 };
    Turma turma = new Turma( 1, "2024.1", 1, 1, idAlunos );
    Turma turma2 = new Turma( 2, "2024.1", 2, 1, idAlunos );

    Mensagem resposta = Controlador.solicitar(
      new Mensagem(OPERACAO.INCLUIR, Mensageiro.decodificar(turma))
    );
    Mensagem resposta2 = Controlador.solicitar(
        new Mensagem(OPERACAO.INCLUIR, Mensageiro.decodificar(turma2))
    );

    assertEquals(resposta.obterCorpo(), "OK");
    assertEquals(resposta2.obterCorpo(), "OK");

    Mensagem resposta3 = Controlador.solicitar(
      new Mensagem(OPERACAO.LISTAR, Mensageiro.decodificar(turma))
    );

    HashSet<Turma> listaDeTurmas = Mensageiro.decodificarVarias(resposta3.obterCorpo());

    assertNotNull(listaDeTurmas);
    listaDeTurmas.forEach(t -> System.out.println(t.obterId()));
  }
}
