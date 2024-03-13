package ifsp.jcr.aps;

import java.util.HashSet;
import java.util.Objects;

class Aluno {
  private Integer id;
  private HashSet<Integer> idDisciplinas;

  public Aluno(Integer id) {
    this.id = id;
  }

  public Integer obterId() {
    return id;
  }

  public HashSet<Integer> obterIdDisciplinas() {
    return idDisciplinas;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Aluno aluno = (Aluno) o;
    return Objects.equals(id, aluno.id) && Objects.equals(idDisciplinas, aluno.idDisciplinas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idDisciplinas);
  }
}