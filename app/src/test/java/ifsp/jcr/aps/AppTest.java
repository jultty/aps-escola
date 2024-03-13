package ifsp.jcr.aps;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

//  1. Formar uma turma, ou seja
//     - 1.1. relacionar um professor com uma disciplina
//     - 1.2. e com um conjunto de alunos

class AppTest {

  Integer[] idAlunos = { 425, 480, 412 };
  Turma turma1 = new Turma(101, "2024.1", 38, 10, idAlunos);
  Turma turma2 = new Turma(102, "2024.1", 31, 12, idAlunos);
  Aluno carolina = new Aluno(808);
  Aluno mariana = new Aluno(801);

  @Test
  void formarUmaTurma() throws IOException, ClassNotFoundException {

    Mensagem resposta1 = Controlador.solicitar(
        new Mensagem(OPERACAO.INCLUIR, Mensageiro.codificar(turma1))
    );
    Mensagem resposta2 = Controlador.solicitar(
        new Mensagem(OPERACAO.INCLUIR, Mensageiro.codificar(turma2))
    );

    assertEquals(resposta1.obterCorpo(), "OK");
    assertEquals(resposta2.obterCorpo(), "OK");

    Mensagem respostaListagem = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Mensageiro.codificar(Turma.class))
    );

    HashMap<Integer, Turma> listaDeTurmas = Mensageiro.decodificarVarias(respostaListagem.obterCorpo());

    assertNotNull(listaDeTurmas);
    listaDeTurmas.forEach((k, v) -> System.out.println(v.obterId()));
    assertTrue(listaDeTurmas.containsKey(turma1.obterId()));
    assertTrue(listaDeTurmas.containsKey(turma2.obterId()));
  }

  @Test
  void umaTurmaPersisteEntreTestes() throws IOException, ClassNotFoundException {
    Mensagem resposta = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Mensageiro.codificar(Turma.class)));
    HashMap<Integer, Turma> listaDeTurmas = Mensageiro.decodificarVarias(resposta.obterCorpo());
    assertNotNull(listaDeTurmas);
    listaDeTurmas.forEach((k, v) -> System.out.println(v.obterId()));
    assertTrue(listaDeTurmas.containsKey(turma1.obterId()));
    assertTrue(listaDeTurmas.containsKey(turma2.obterId()));
    System.out.println(turma2.obterIdAlunos()[0]);
    System.out.println(turma2.obterIdAlunos()[1]);
    System.out.println(turma2.obterIdAlunos()[2]);
  }

  @Test void turmasRecebidasPorListagemSaoIguaisAsTurmasCriadas() throws IOException, ClassNotFoundException {
    Mensagem resposta = Controlador.solicitar(new Mensagem(OPERACAO.LISTAR, Mensageiro.codificar(Turma.class)));
    HashMap<Integer, Turma> listaDeTurmas = Mensageiro.decodificarVarias(resposta.obterCorpo());
    assertNotNull(listaDeTurmas);
    assertTrue(listaDeTurmas.containsKey(turma1.obterId()));
    assertTrue(listaDeTurmas.containsKey(turma2.obterId()));
    assertEquals(listaDeTurmas.size(), 2);
    assertEquals(listaDeTurmas.get(turma1.obterId()).obterIdProfessor(), turma1.obterIdProfessor());
    assertEquals(listaDeTurmas.get(turma2.obterId()).obterIdProfessor(), turma2.obterIdProfessor());
  }
  }
}
