package ifsp.jcr.aps;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Turma implements Serializable {
  private Integer id;
  private String semestre;
  private Integer idDisciplina;
  private Integer idProfessor;
  private Integer[] idAlunos;

  public Turma(
      Integer id,
      String semestre,
      Integer idDisciplina,
      Integer idProfessor,
      Integer[] idAlunos) {
    this.id = id;
    this.semestre = semestre;
    this.idDisciplina = idDisciplina;
    this.idProfessor = idProfessor;
    this.idAlunos = idAlunos;
  }

  Turma() {}

  public Integer obterId() {
    return id;
  }
  public Integer[] obterIdAlunos() { return idAlunos; }
  public Integer obterIdProfessor() { return idProfessor; }

  @Serial
  private static final long serialVersionUID = 1L;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Turma turma = (Turma) o;
    return Objects.equals(id, turma.id) && Objects.equals(semestre, turma.semestre) && Objects.equals(idDisciplina, turma.idDisciplina) && Objects.equals(idProfessor, turma.idProfessor) && Arrays.equals(idAlunos, turma.idAlunos);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(id, semestre, idDisciplina, idProfessor);
    result = 31 * result + Arrays.hashCode(idAlunos);
    return result;
  }
}
