package ifsp.jcr.aps;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@Order(2)
class TestesParticipacao {

  @Test
  void dadosPersistemEntreClassesDeTestes() throws IOException, ClassNotFoundException {
    Mensagem resposta = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Aluno.class))
    );
    HashMap<Integer, Aluno> alunos =
        Serializador.desserializarVarios(resposta.obterCorpo());
    assertTrue(alunos.containsKey(808));
    assertTrue(alunos.containsKey(801));
    assertTrue(alunos.get(808).obterIdDisciplinas().contains(38));
    assertTrue(alunos.get(801).obterIdDisciplinas().contains(38));
  }

//  2.2. Registrar em cada aula
//     - 2.2.1. aulas
//     - 2.2.2. e a presença de cada aluno

  @Test @Order(6)
  void  presencasPodemSerRegistradas() throws IOException, ClassNotFoundException {
    HashSet<Integer> presencas = new HashSet<>();
    presencas.add(808);
    presencas.add(801);
    Aula aula = new Aula(770013, LocalDate.now(), 38, presencas);

    Mensagem respostaInsercao = Controlador.solicitar(
        new Mensagem(OPERACAO.INCLUIR, Serializador.serializar(aula))
    );
    assertEquals(respostaInsercao.obterCorpo(), "OK");

    Mensagem respostaListagem = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Aula.class))
    );

    HashMap<Integer, Aula> aulas = Serializador.desserializarVarios(respostaListagem.obterCorpo());
    HashSet<Integer> presencasRetornadas = aulas.get(770013).obterPresencas();
    assertTrue(presencasRetornadas.contains(808));
    assertTrue(presencasRetornadas.contains(801));

    Mensagem respostaListagemAlunos = Controlador.solicitar(
        new Mensagem(OPERACAO.LISTAR, Serializador.serializar(Aluno.class))
    );
    HashMap<Integer, Aluno> alunos = Serializador.desserializarVarios(respostaListagemAlunos.obterCorpo());

    String frase = "A aula 770013 de " + aulas.get(770013).obterData() + " teve a presença das alunas " +
        alunos.get(808).obterNome() + " (id 808) e " + alunos.get(801).obterNome() + " (id 801)";

    assertEquals("A aula 770013 de 2024-03-14 teve a presença das alunas Carolina (id 808) e Mariana (id 801)", frase);

    System.out.println(frase);
  }

//  2.3. Registrar
//     - 2.3.1. notas para cada aluno

  @Test
  void  notasPodemSerRegistradas() throws IOException, ClassNotFoundException {

    HashMap<String, Float> notas = new HashMap<>();
    notas.put("Trabalho", 3.8F);
    notas.put("P1", 7.8F);
    notas.put("P2", 9.0F);
    Participacao participacao = new Participacao(
        970013, 808, 38, notas
    );

    Mensagem respostaInclusao = Controlador.solicitar(new Mensagem(
        OPERACAO.INCLUIR, Serializador.serializar(participacao)
    ));

    Mensagem respostaListagem = Controlador.solicitar(new Mensagem(
        OPERACAO.LISTAR, Serializador.serializar(Participacao.class)
    ));

    HashMap<Integer, Participacao> participacoes =
        Serializador.desserializarVarios(respostaListagem.obterCorpo());

    Participacao participacaoRetornada = participacoes.get(970013);

    assertNotNull(participacoes.get(970013));
    assertEquals(participacoes.get(970013), participacao);
    assertEquals(participacaoRetornada.obterIdDisciplina(), 38);
    assertEquals(participacaoRetornada.obterNotas().get("Trabalho"), 3.8F);
    assertEquals(participacaoRetornada.obterNotas().get("P1"), 7.8F);
    assertEquals(participacaoRetornada.obterNotas().get("P2"), 9.0F);
  }
}