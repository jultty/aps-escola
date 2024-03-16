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
    private static HashMap<Integer, Aula> aulas = new HashMap<>();

    private GerenciadorDeDados() {}

    public static <T> Mensagem inserir(T kind) {
      if (kind instanceof Turma t) {
        turmas.put(t.obterId(), t);
      } else if (kind instanceof Professor t) {
        professores.put(t.obterId(), t);
      } else if (kind instanceof Aluno t) {
        alunos.put(t.obterId(), t);
      } else if (kind instanceof Disciplina t) {
        disciplinas.put(t.obterId(), t);
      } else if (kind instanceof Curso t) {
        cursos.put(t.obterId(), t);
      } else if (kind instanceof Participacao t) {
        participacoes.put(t.obterId(), t);
      } else if (kind instanceof Aula t) {
        aulas.put(t.obterId(), t);
      } else {
        throw new IllegalArgumentException("GerenciadorDeDados.inserir: Classe desconhecida");
      }
      return new Mensagem("OK");
    }

    public static <T> Mensagem listar(T t) throws IOException {
      if (t == Turma.class) {
        return new Mensagem(Serializador.serializar(turmas));
      } else if (t == Professor.class) {
        return new Mensagem(Serializador.serializar(professores));
      } else if (t == Aluno.class) {
        return new Mensagem(Serializador.serializar(alunos));
      } else if (t == Disciplina.class) {
        return new Mensagem(Serializador.serializar(disciplinas));
      } else if (t == Curso.class) {
        return new Mensagem(Serializador.serializar(cursos));
      } else if (t == Participacao.class) {
        return new Mensagem(Serializador.serializar(participacoes));
      } else if (t == Administrador.class) {
        return new Mensagem(Serializador.serializar(administradores));
      } else if (t == Aula.class) {
        return new Mensagem(Serializador.serializar(aulas));
      }
      throw new IllegalArgumentException("GerenciadorDeDados.listar: Classe desconhecida");
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