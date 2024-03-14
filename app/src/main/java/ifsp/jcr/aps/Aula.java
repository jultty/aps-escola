package ifsp.jcr.aps;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

public class Aula implements Serializable {
  Integer id;
  Date data;
  Integer idDisciplina;
  HashSet<Integer> presencas;

  public Aula(Integer id, Date data, Integer idDisciplina, HashSet<Integer> presencas) {
    this.id = id;
    this.data = data;
    this.idDisciplina = idDisciplina;
    this.presencas = presencas;
  }

  public Integer obterId() { return id; }
  public Date obterData() { return data; }
  public Integer obterIdDisciplina() { return idDisciplina; }
  public HashSet<Integer> obterPresencas() { return presencas; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Aula aula = (Aula) o;
    return Objects.equals(id, aula.id) && Objects.equals(data, aula.data) && Objects.equals(idDisciplina, aula.idDisciplina) && Objects.equals(presencas, aula.presencas);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, data, idDisciplina, presencas);
  }
}
