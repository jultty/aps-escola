package ifsp.jcr.aps;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

class Aluno implements Serializable {
  private Integer id;
  private String nome;
  private HashSet<Integer> idDisciplinas = new HashSet<>();

  public Aluno(Integer id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public Aluno(Integer id) {
    this.id = id;
  }

  public void matricular(Integer idDisciplina) {
    idDisciplinas.add(idDisciplina);
  }

  public Integer obterId() { return id; }
  public String obterNome() { return nome; }
  public HashSet<Integer> obterIdDisciplinas() { return idDisciplinas; }

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

  @Serial
  private static final long serialVersionUID = 1L;
}