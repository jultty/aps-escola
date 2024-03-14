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
  private Float pontuacao;
  private Float media;
  private Float presencas;
  private Float aulas;
  private Float frequencia;

  public Participacao(Integer id, Integer idAluno, Integer idDisciplina, HashMap<String, Float> notas) {
    this.id = id;
    this.idAluno = idAluno;
    this.idDisciplina = idDisciplina;
    this.notas = notas;
    this.pontuacao = 0F;
    this.media = 0F;
    this.presencas = 0F;
    this.aulas = 0F;
    this.frequencia = 0F;
  }

  public Boolean avaliarMedia() {
    media = 0F;
    pontuacao = 0F;
    notas.forEach((k, v) -> pontuacao += v);
    media = (pontuacao/notas.size());
    return media >= 6.0F;
  }

  public Boolean avaliarFrequencia(HashMap<Integer, Aula> aulas) {
    aulas.forEach((k, v) -> {
      if (v.obterIdDisciplina().equals(idDisciplina)) {
        this.aulas += 1;
        if (v.obterPresencas().contains(idAluno)) {
          presencas += 1;
        }
      }
    });
    frequencia = (presencas / this.aulas);
    return frequencia >= 0.75F;
  }

  public Boolean avaliarAprovacao(HashMap<Integer, Aula> aulas) {
    return avaliarFrequencia(aulas) && avaliarMedia();
  }

  public Integer obterId() { return id; }
  public Integer obterIdAluno() { return idAluno; }
  public Integer obterIdDisciplina() { return idDisciplina; }
  public HashMap<String, Float> obterNotas() { return notas; }
  public Float obterFrequencia() { return frequencia; }
  public Float obterPontuacao() { return pontuacao; }
  public Float obterMedia() { return media; }
  public Float obterPresencas() { return presencas; }
  public Float obterAulas() { return aulas; }

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