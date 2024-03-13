package ifsp.jcr.aps;

import java.util.Arrays;
import java.util.Objects;

public class Professor {
  private Integer id;
  private Integer[] idTurmas;

  public Integer obterId() {
    return id;
  }

  public Integer[] obterIdTurmas() {
    return idTurmas;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Professor professor = (Professor) o;
    return Objects.equals(id, professor.id) && Arrays.equals(idTurmas, professor.idTurmas);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(id);
    result = 31 * result + Arrays.hashCode(idTurmas);
    return result;
  }
}
