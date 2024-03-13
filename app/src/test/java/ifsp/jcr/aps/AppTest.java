package ifsp.jcr.aps;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

//  1. Formar uma turma, ou seja
//     - 1.1. relacionar um professor com uma disciplina
//     - 1.2. e com um conjunto de alunos

class AppTest {

  Integer[] idAlunos = { 425, 480, 412 };
  Turma turma1 = new Turma(101, "2024.1", 38, 10, idAlunos);
  Turma turma2 = new Turma(102, "2024.1", 31, 12, idAlunos);

  @Test
  void formarUmaTurma() throws IOException, ClassNotFoundException {

    Mensagem resposta1 = Controlador.solicitar(
        new Mensagem(OPERACAO.INCLUIR, Mensageiro.decodificar(turma1))
    );
    Mensagem resposta2 = Controlador.solicitar(
        new Mensagem(OPERACAO.INCLUIR, Mensageiro.decodificar(turma2))
    );

    assertEquals(resposta1.obterCorpo(), "OK");
    assertEquals(resposta2.obterCorpo(), "OK");

    Mensagem respostaListagem = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Mensageiro.decodificar(Turma.class))
    );

    HashSet<Turma> listaDeTurmas = Mensageiro.decodificarVarias(respostaListagem.obterCorpo());

    assertNotNull(listaDeTurmas);
    listaDeTurmas.forEach(t -> System.out.println(t.obterId()));
    assertTrue(listaDeTurmas.contains(turma1));
    assertTrue(listaDeTurmas.contains(turma2));
  }

  @Test
  void umaTurmaPersisteEntreTestes() throws IOException, ClassNotFoundException {
    Mensagem resposta = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Mensageiro.decodificar(Turma.class)));
    HashSet<Turma> listaDeTurmas = Mensageiro.decodificarVarias(resposta.obterCorpo());
    assertNotNull(listaDeTurmas);
    listaDeTurmas.forEach(t -> System.out.println(t.obterId()));
    assertTrue(listaDeTurmas.contains(turma1));
    assertTrue(listaDeTurmas.contains(turma2));
    System.out.println(turma2.obterIdAlunos()[0]);
    System.out.println(turma2.obterIdAlunos()[1]);
    System.out.println(turma2.obterIdAlunos()[2]);
  }

  @Test void turmasRecebidasPorListagemSaoIguaisAsTurmasCriadas() throws IOException, ClassNotFoundException {
    Mensagem resposta = Controlador.solicitar(new Mensagem(OPERACAO.LISTAR, Mensageiro.decodificar(Turma.class)));
    HashSet<Turma> listaDeTurmas = Mensageiro.decodificarVarias(resposta.obterCorpo());
    assertNotNull(listaDeTurmas);
    assertTrue(listaDeTurmas.contains(turma1));
    assertTrue(listaDeTurmas.contains(turma2));
    assertEquals(listaDeTurmas.size(), 2);
  }
}
