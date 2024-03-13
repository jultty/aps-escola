package ifsp.jcr.aps;

import java.io.IOException;
import java.util.HashMap;

public class GerenciadorDeDados {

  private static HashMap<Integer, Curso> cursos;
  private static HashMap<Integer, Professor> professores;
  private static HashMap<Integer, Disciplina> disciplinas;
  private static HashMap<Integer, Turma> turmas = new HashMap<>();
  private static HashMap<Integer, Aluno> alunos;
  private static HashMap<Integer, Participacao> participacoes;
  private static HashMap<Integer, Administrador> administradores;

  public static <T> Mensagem criar(T t) {
    if (t instanceof Turma) {
      turmas.put(((Turma) t).obterId(), (Turma) t);
    } else if (t instanceof Professor) {
      professores.put(((Professor) t).obterId(), (Professor) t);
    } else if (t instanceof Aluno) {
      alunos.put(((Aluno) t).obterId(), (Aluno) t);
    }
    return new Mensagem("OK");
  }

  public static <T> Mensagem listar(T t) throws IOException {
    if (t instanceof Turma) {
      return new Mensagem(Mensageiro.codificar(turmas));
    } else if (t instanceof Professor) {
      return new Mensagem(Mensageiro.codificar(professores));
    } else if (t instanceof Aluno) {
      return new Mensagem(Mensageiro.codificar(alunos));
    }
    return new Mensagem();
  }
}

