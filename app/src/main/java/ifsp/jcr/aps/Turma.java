package ifsp.jcr.aps;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

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

  Turma() {};

  public Integer obterId() {
    return id;
  }

  @Serial
  private static final long serialVersionUID = 1L;
}
