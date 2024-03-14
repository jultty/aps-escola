package ifsp.jcr.aps;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@Order(1)
class TestesTurma {

  //  2.1. Formar uma turma, ou seja
  //     - 2.1.1. relacionar um professor com uma disciplina
  //     - 2.1.2. e com um conjunto de alunos

  Integer[] idAlunos = { 425, 480, 412 };
  Turma turma1 = new Turma(101, "2024.1", 38, 10, idAlunos);
  Turma turma2 = new Turma(102, "2024.1", 31, 12, idAlunos);

  @Test @Order(1)
  void formarUmaTurma() throws IOException, ClassNotFoundException {

    Mensagem resposta1 = Controlador.solicitar(
        new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(turma1))
    );
    Mensagem resposta2 = Controlador.solicitar(
        new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(turma2))
    );

    assertEquals(resposta1.obterCorpo(), "OK");
    assertEquals(resposta2.obterCorpo(), "OK");

    Mensagem respostaListagem = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Turma.class))
    );

    HashMap<Integer, Turma> listaDeTurmas =
        Serializador.desserializarVarios(respostaListagem.obterCorpo());

    assertNotNull(listaDeTurmas);
    assertTrue(listaDeTurmas.containsKey(turma1.obterId()));
    assertTrue(listaDeTurmas.containsKey(turma2.obterId()));
  }

  @Test @Order(2)
  void umaTurmaPersisteEntreTestes() throws IOException, ClassNotFoundException {
    Mensagem resposta = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Turma.class)));
    HashMap<Integer, Turma> listaDeTurmas = Serializador.desserializarVarios(resposta.obterCorpo());
    assertNotNull(listaDeTurmas);
    assertTrue(listaDeTurmas.containsKey(turma1.obterId()));
    assertTrue(listaDeTurmas.containsKey(turma2.obterId()));
  }

  @Test @Order(3)
  void turmasRecebidasPorListagemSaoIguaisAsTurmasCriadas() throws IOException, ClassNotFoundException {
    Mensagem resposta = Controlador.solicitar(new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Turma.class)));
    HashMap<Integer, Turma> listaDeTurmas = Serializador.desserializarVarios(resposta.obterCorpo());
    assertNotNull(listaDeTurmas);
    assertTrue(listaDeTurmas.containsKey(turma1.obterId()));
    assertTrue(listaDeTurmas.containsKey(turma2.obterId()));
    assertEquals(listaDeTurmas.get(turma1.obterId()), turma1);
    assertEquals(listaDeTurmas.get(turma2.obterId()), turma2);
  }

  @Test @Order(4)
  void umAlunoPodeSerMatriculado() throws IOException, ClassNotFoundException {

    Aluno carolina = new Aluno(808,"Carolina");
    Mensagem resposta1 = Controlador.solicitar(
        new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(carolina))
    );
    assertEquals(resposta1.obterCorpo(), "OK");
    assertEquals(carolina.obterIdDisciplinas().size(), 0);

    Aluno mariana = new Aluno(801, "Mariana");
    Mensagem resposta2 = Controlador.solicitar(
        new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(mariana))
    );
    assertEquals(resposta2.obterCorpo(), "OK");
    assertEquals(mariana.obterIdDisciplinas().size(), 0);

    Disciplina disciplina =
        new Disciplina(38, "APSI5", "Arquitetura e Programação de Software");
    Mensagem resposta3 = Controlador.solicitar(
        new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(disciplina))
    );

    HashSet<Integer> idAlunas = new HashSet<>();
    idAlunas.add(carolina.obterId());
    idAlunas.add(mariana.obterId());

    Mensagem resposta4 = Controlador.solicitar(
        new Mensagem(OPERACAO.MATRICULAR, idAlunas, "38")
    );
    assertEquals(resposta4.obterCorpo(), "OK");

    Mensagem resposta5 = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Aluno.class))
    );
    HashMap<Integer, Aluno> alunos =
        Serializador.desserializarVarios(resposta5.obterCorpo());
    assertTrue(alunos.containsKey(carolina.obterId()));
    assertTrue(alunos.containsKey(mariana.obterId()));
    assertTrue(alunos.get(carolina.obterId()).obterIdDisciplinas().contains(38));
    assertTrue(alunos.get(mariana.obterId()).obterIdDisciplinas().contains(38));
  }

  @Test @Order(5)
  void umaTurmaPodeSerAssociadaAUmProfessorEAAlunos() throws IOException, ClassNotFoundException {

    HashSet<Integer> idDisciplinas = new HashSet<>();
    idDisciplinas.add(38);
    Professor rediet = new Professor(10, idDisciplinas, "Rediet Abebe");
    Mensagem resposta = Controlador.solicitar(
        new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(rediet))
    );
    assertEquals(resposta.obterCorpo(), "OK");

    Mensagem resposta2 = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Professor.class))
    );
    HashMap<Integer, Professor> professores =
        Serializador.desserializarVarios(resposta2.obterCorpo());
    assertTrue(professores.containsKey(rediet.obterId()));

    Mensagem respostaListarTurmas = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Turma.class))
    );
    HashMap<Integer, Turma> turmas = Serializador.desserializarVarios(
        respostaListarTurmas.obterCorpo()
    );
    assertTrue(turmas.containsKey(turma1.obterId()));

    Turma turma1Retornada = turmas.get(turma1.obterId());

    Mensagem respostaListarProfessores = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Professor.class))
    );

    HashMap<Integer, Professor> professoresRetornados = Serializador.desserializarVarios(
        respostaListarProfessores.obterCorpo()
    );

    assertTrue(professoresRetornados.containsKey(10));
    Professor professorRetornado = professoresRetornados.get(10);

    Mensagem respostaListarAlunos = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Aluno.class))
    );
    HashMap<Integer, Aluno> alunos = Serializador.desserializarVarios(
        respostaListarAlunos.obterCorpo()
    );

    String frase = ("A turma " + turma1Retornada.obterId()
        + " é lecionada pela professora " + professorRetornado.obterNome()
        + " (id " + turma1Retornada.obterIdProfessor() + ") "
        + "e possui as alunas " + alunos.get(801).obterNome()
        + " e " + alunos.get(808).obterNome()
        + " (ids " + alunos.get(801).obterId() + " e "
        + alunos.get(808).obterId() + ")");

    assertEquals("A turma 101 é lecionada pela professora "
        + "Rediet Abebe (id 10) e possui as alunas Mariana e Carolina "
        + "(ids 801 e 808)", frase);

    System.out.println(frase);
  }
}
