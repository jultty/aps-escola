package ifsp.jcr.aps;

import java.io.IOException;
import java.util.HashSet;

import ifsp.jcr.aps.Mensageiro;

public class GerenciadorDeDados {

  private Curso[] cursos;
  private Professor[] professores;
  private Disciplina[] disciplinas;
  private static HashSet<Turma> turmas = new HashSet<>();
  private Aluno[] alunos;
  private Participacao[] participacoes;
  private Administrador[] administradores;

  public static <T> Mensagem criar(T t) {
    if (t instanceof Turma) {
      turmas.add((Turma) t);
    };

    return new Mensagem("OK");
  }

  public static <T> Mensagem listar(T t) throws IOException {
    if (t instanceof Turma) {
      return new Mensagem(Mensageiro.decodificar(turmas));
    }
    return new Mensagem();
  }

  private Mensagem gerenciarCurso(Mensagem mensagem) { return new Mensagem(); }
  private Mensagem gerenciarDisciplina(Mensagem mensagem) { return new Mensagem(); }
  private Mensagem gerenciarAluno(Mensagem mensagem) { return new Mensagem(); }
  private Mensagem gerenciarParticipacao(Mensagem mensagem) { return new Mensagem(); }
  private Mensagem gerenciarAdministrador(Mensagem mensagem) { return new Mensagem(); }

}

