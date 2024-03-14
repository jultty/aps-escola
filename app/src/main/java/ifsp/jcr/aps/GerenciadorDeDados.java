package ifsp.jcr.aps;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class GerenciadorDeDados {

    private static HashMap<Integer, Curso> cursos = new HashMap<>();
    private static HashMap<Integer, Professor> professores = new HashMap<>();
    private static HashMap<Integer, Disciplina> disciplinas = new HashMap<>();
    private static HashMap<Integer, Turma> turmas = new HashMap<>();
    private static HashMap<Integer, Aluno> alunos = new HashMap<>();
    private static HashMap<Integer, Participacao> participacoes = new HashMap<>();
    private static HashMap<Integer, Administrador> administradores = new HashMap<>();

    private GerenciadorDeDados() {};

    public static <T> Mensagem inserir(T t) {
      if (t instanceof Turma) {
        turmas.put(((Turma) t).obterId(), (Turma) t);
      } else if (t instanceof Professor) {
        professores.put(((Professor) t).obterId(), (Professor) t);
      } else if (t instanceof Aluno) {
        alunos.put(((Aluno) t).obterId(), (Aluno) t);
      } else if (t instanceof Disciplina) {
        disciplinas.put(((Disciplina) t).obterId(), (Disciplina) t);
      } else if (t instanceof Curso) {
        cursos.put(((Curso) t).obterId(), (Curso) t);
      } else if (t instanceof Participacao) {
        participacoes.put(((Participacao) t).obterId(), (Participacao) t);
      }
      return new Mensagem("OK");
    }

    public static <T> Mensagem listar(T t) throws IOException {
      if (t == Turma.class) {
        return new Mensagem(Mensageiro.codificar(turmas));
      } else if (t == Professor.class) {
        return new Mensagem(Mensageiro.codificar(professores));
      } else if (t == Aluno.class) {
        return new Mensagem(Mensageiro.codificar(alunos));
      } else if (t == Disciplina.class) {
        return new Mensagem(Mensageiro.codificar(disciplinas));
      } else if (t == Curso.class) {
        return new Mensagem(Mensageiro.codificar(cursos));
      } else if (t == Participacao.class) {
        return new Mensagem(Mensageiro.codificar(participacoes));
      } else if (t == Administrador.class) {
        return new Mensagem(Mensageiro.codificar(administradores));
      }
      throw new IllegalArgumentException();
    }

    public static Mensagem matricular(HashSet<Integer> idAlunos, String idDisciplina) {
      if (!disciplinas.containsKey(Integer.valueOf(idDisciplina))) {
        throw new IllegalArgumentException("A disciplina " + idDisciplina + " não existe");
      }
      Disciplina disciplina = disciplinas.get(Integer.valueOf(idDisciplina));
      for (Integer idAluno : idAlunos) {
        if (!alunos.containsKey(idAluno)) {
          throw new IllegalArgumentException("O aluno " + idAluno + " não existe");
        }
        Aluno aluno = alunos.get(idAluno);
        aluno.matricular(disciplina.obterId());
      }
      return new Mensagem("OK");
    }
  }