package ifsp.jcr.aps;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

class Participacao implements Serializable {
  private Integer id;
  private Integer idAluno;
  private Integer idDisciplina;
  private HashMap<String, Float> notas;
  private Float frequencia;

  public Participacao(Integer id, Integer idAluno, Integer idDisciplina, HashMap<String, Float> notas, Float frequencia) {
    this.id = id;
    this.idAluno = idAluno;
    this.idDisciplina = idDisciplina;
    this.notas = notas;
    this.frequencia = frequencia;
  }

  public Integer obterId() { return id; }
  public Integer obterIdAluno() { return idAluno; }
  public Integer obterIdDisciplina() { return idDisciplina; }
  public HashMap<String, Float> obterNotas() { return notas; }
  public Float obterFrequencia() { return frequencia; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Participacao that = (Participacao) o;
    return Objects.equals(id, that.id) && Objects.equals(idAluno, that.idAluno) && Objects.equals(idDisciplina, that.idDisciplina) && Objects.equals(notas, that.notas) && Objects.equals(frequencia, that.frequencia);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idAluno, idDisciplina, notas, frequencia);
  }

  @Serial
  private static final long serialVersionUID = 1L;
}