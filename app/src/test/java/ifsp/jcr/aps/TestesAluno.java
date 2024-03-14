package ifsp.jcr.aps;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Order(3)
class TestesAluno {

  //  2.4. Avaliar aprovação, sendo
  //     - 2.4.1. alunos aprovados os que tiverem
  //       - 2.4.1.1. 75% ou mais de presença em relação ao total de aulas cadastradas
  //       - 2.4.1.2. e média de notas igual ou maior que 6,0

  @Test @Order(1)
  void  alunoComMediaIgualOuMaiorQue6EstaAprovadonNoCriterio() throws IOException, ClassNotFoundException {

    Mensagem respostaListagem = Controlador.solicitar(
      new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Aluno.class))
    );
    HashMap<Integer, Aluno> alunos = Serializador.desserializarVarios(respostaListagem.obterCorpo());

    Aluno carolina = alunos.get(808);

    Mensagem respostaListagemParticipacoes = Controlador.solicitar(
      new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Participacao.class))
    );
    Map<Integer, Participacao> participacoes = Serializador.desserializarVarios(respostaListagemParticipacoes.obterCorpo());

    HashMap<Integer, Participacao> participacaoCarolina = new HashMap<>();

    participacoes.forEach((k, v) -> {
      if (v.obterIdAluno().equals(carolina.obterId()))
        participacaoCarolina.put(k, v);
    });

    assertEquals(participacaoCarolina.size(), 1);

    Boolean avaliacaoMediaCarolina = participacaoCarolina.get(970013).avaliarMedia();
    assertTrue(avaliacaoMediaCarolina);
  }

  @Test @Order(2)
  void alunoComMediaInferiorA6EstaReprovadoNoCriterio() throws IOException, ClassNotFoundException {
    HashMap<String, Float> notas = new HashMap<>();
    notas.put("Trabalho", 1.5F);
    notas.put("P1", 5.0F);
    notas.put("P2", 4.2F);
    Participacao participacao = new Participacao(
        900087, 801, 38, notas
    );

    Mensagem respostaInclusao = Controlador.solicitar(new Mensagem(
        OPERACAO.INCLUIR, Serializador.serializar(participacao)
    ));

    Mensagem respostaListagem = Controlador.solicitar(new Mensagem(
        OPERACAO.LISTAR, Serializador.serializar(Participacao.class)
    ));

    HashMap<Integer, Participacao> participacoes =
        Serializador.desserializarVarios(respostaListagem.obterCorpo());

    Participacao participacaoRetornada = participacoes.get(900087);

    assertEquals(participacoes.get(900087), participacao);
    assertFalse(participacaoRetornada.avaliarMedia());
  }

  @Test @Order(3)
  void alunoComFrequenciaSuperiorA075EstaAprovadoNoCriterio() throws IOException, ClassNotFoundException {

    Mensagem respostaListagem = Controlador.solicitar(new Mensagem(
        OPERACAO.LISTAR, Serializador.serializar(Participacao.class)
    ));

    HashMap<Integer, Participacao> participacoes =
        Serializador.desserializarVarios(respostaListagem.obterCorpo());

    Mensagem respostaListagemAulas = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Aula.class))
    );

    HashMap<Integer, Aula> aulas = Serializador.desserializarVarios(respostaListagemAulas.obterCorpo());

    Participacao participacao = participacoes.get(900087);

    assertEquals(801, participacao.obterIdAluno());
    assertTrue(participacao.avaliarFrequencia(aulas));
  }

  @Test @Order(4)
  void alunoComFrequenciaInferiorA075EstaReprovadoNoCriterio() throws IOException, ClassNotFoundException {

    Mensagem respostaListagem = Controlador.solicitar(new Mensagem(
        OPERACAO.LISTAR, Serializador.serializar(Participacao.class)
    ));

    HashMap<Integer, Participacao> participacoes =
        Serializador.desserializarVarios(respostaListagem.obterCorpo());

    Mensagem respostaListagemAulas = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Aula.class))
    );

    HashMap<Integer, Aula> aulas = Serializador.desserializarVarios(respostaListagemAulas.obterCorpo());

    Participacao participacao = participacoes.get(970013);

    assertEquals(808, participacao.obterIdAluno());
    assertTrue(participacao.avaliarFrequencia(aulas));

    HashSet<Integer> presencas = new HashSet<>();
    presencas.add(801);
    Aula aula770014 = new Aula(770014, LocalDate.now(), 38, presencas);
    Aula aula770015 = new Aula(770015, LocalDate.now(), 38, presencas);
    Aula aula770016 = new Aula(770016, LocalDate.now(), 38, presencas);
    Aula aula770017 = new Aula(770017, LocalDate.now(), 38, presencas);

    Controlador.solicitar(new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(aula770014)));
    Controlador.solicitar(new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(aula770015)));
    Controlador.solicitar(new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(aula770016)));
    Controlador.solicitar(new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(aula770017)));

    Mensagem respostaListagemAulasAposInsercoes = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Aula.class))
    );

    HashMap<Integer, Aula> aulasAposInsercoes = Serializador.desserializarVarios(
      respostaListagemAulasAposInsercoes.obterCorpo()
    );

    assertFalse(participacao.avaliarFrequencia(aulasAposInsercoes));
  }

  @Test @Order(5)
  void alunoComFrequenciaInferiora075EMediaSuperiorA6EstaReprovado() throws IOException, ClassNotFoundException {

    Mensagem respostaListagemParticipacoes = Controlador.solicitar(new Mensagem(
        OPERACAO.LISTAR, Serializador.serializar(Participacao.class)
    ));

    HashMap<Integer, Participacao> participacoes =
        Serializador.desserializarVarios(respostaListagemParticipacoes.obterCorpo());

    Mensagem respostaListagemAulas = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Aula.class))
    );
    HashMap<Integer, Aula> aulas = Serializador.desserializarVarios(
        respostaListagemAulas.obterCorpo()
    );

    Participacao participacaoCarolina = participacoes.get(970013);
    Participacao participacaoMariana = participacoes.get(900087);

    assertTrue(participacaoCarolina.avaliarMedia());
    assertFalse(participacaoCarolina.avaliarFrequencia(aulas));
    assertFalse(participacaoCarolina.avaliarAprovacao(aulas));
  }

  @Test @Order(6)
  void alunoComFrequenciaSuperiora075EMediaInferiorA6EstaReprovado() throws IOException, ClassNotFoundException {

    Aluno fernanda = new Aluno(807,"Fernanda");
    Mensagem respostaInclusao = Controlador.solicitar(
        new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(fernanda))
    );
    assertEquals(respostaInclusao.obterCorpo(), "OK");

    HashSet<Integer> presencas = new HashSet<>();
    presencas.add(fernanda.obterId());
    Aula aula770807 = new Aula(770807, LocalDate.now(), 40, presencas);
    Aula aula771807 = new Aula(771807, LocalDate.now(), 40, presencas);
    Aula aula772807 = new Aula(772807, LocalDate.now(), 40, presencas);
    Aula aula773807 = new Aula(773807, LocalDate.now(), 40, presencas);

    Controlador.solicitar(new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(aula770807)));
    Controlador.solicitar(new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(aula771807)));
    Controlador.solicitar(new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(aula772807)));
    Controlador.solicitar(new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(aula773807)));

    HashMap<String, Float> notas = new HashMap<>();
    notas.put("P1", 6.0F);
    notas.put("P2", 5.99F);
    Participacao participacao = new Participacao(
        970807, 807, 40, notas
    );

    Controlador.solicitar(new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(participacao)));

    Mensagem respostaListagemParticipacoes = Controlador.solicitar(new Mensagem(
        OPERACAO.LISTAR, Serializador.serializar(Participacao.class)
    ));

    HashMap<Integer, Participacao> participacoes =
        Serializador.desserializarVarios(respostaListagemParticipacoes.obterCorpo());

    Mensagem respostaListagemAulas = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Aula.class))
    );

    HashMap<Integer, Aula> aulas = Serializador.desserializarVarios(
        respostaListagemAulas.obterCorpo()
    );

    Participacao participacaoFernanda = participacoes.get(970807);

    assertFalse(participacaoFernanda.avaliarMedia());
    assertTrue(participacaoFernanda.avaliarFrequencia(aulas));
    assertFalse(participacaoFernanda.avaliarAprovacao(aulas));
  }

  @Test @Order(7)
  void alunoComFrequenciaSuperiora075EMediaSuperiorA6EstaAprovado() throws IOException, ClassNotFoundException {

    Aluno cleo = new Aluno(804,"Cleo");
    Mensagem respostaInclusao = Controlador.solicitar(
        new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(cleo))
    );
    assertEquals(respostaInclusao.obterCorpo(), "OK");

    HashSet<Integer> presencas = new HashSet<>();
    Aula aula770804 = new Aula(770804, LocalDate.now(), 90, presencas);
    presencas.add(cleo.obterId());
    Aula aula771804 = new Aula(771804, LocalDate.now(), 90, presencas);
    Aula aula772804 = new Aula(772804, LocalDate.now(), 90, presencas);
    Aula aula773804 = new Aula(773804, LocalDate.now(), 90, presencas);

    Controlador.solicitar(new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(aula770804)));
    Controlador.solicitar(new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(aula771804)));
    Controlador.solicitar(new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(aula772804)));
    Controlador.solicitar(new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(aula773804)));

    HashMap<String, Float> notas = new HashMap<>();
    notas.put("P1", 10.0F);
    notas.put("P2", 5.00F);
    notas.put("Trabalho", 4.0F);
    Participacao participacao = new Participacao(
        970804, 804, 90, notas
    );

    Controlador.solicitar(new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(participacao)));

    Mensagem respostaListagemParticipacoes = Controlador.solicitar(new Mensagem(
        OPERACAO.LISTAR, Serializador.serializar(Participacao.class)
    ));

    HashMap<Integer, Participacao> participacoes =
        Serializador.desserializarVarios(respostaListagemParticipacoes.obterCorpo());

    Mensagem respostaListagemAulas = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Aula.class))
    );

    HashMap<Integer, Aula> aulas = Serializador.desserializarVarios(
        respostaListagemAulas.obterCorpo()
    );

    Participacao participacaoCleo = participacoes.get(970804);

    assertTrue(participacaoCleo.avaliarMedia());
    assertTrue(participacaoCleo.avaliarFrequencia(aulas));
    assertTrue(participacaoCleo.avaliarAprovacao(aulas));
  }
}