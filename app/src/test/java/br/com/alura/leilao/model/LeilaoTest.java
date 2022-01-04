package br.com.alura.leilao.model;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import br.com.alura.leilao.exception.LanceMenorQueUltimoLanceException;
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioAtingiuLimiteDeCincoLancesException;


public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario ALEX = new Usuario("Alex");
    private final Usuario FRAN = new Usuario("Fran");


    @Test
    public void deve_DevolverDescricao_QuandoRecebeDescricao() {
        String descricaoDevolvida = CONSOLE.getDescricao();
        //assertEquals("Console", descricaoDevolvida);

        assertThat(descricaoDevolvida, equalTo("Console"));
        // assertThat(descricaoDevolvida, is("Console"));
        // assertThat(descricaoDevolvida, is(equalTo("Console")));

    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(ALEX, 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();
       // assertEquals(200.0, maiorLanceDevolvido, DELTA);
        assertThat(maiorLanceDevolvido, closeTo(200.0, DELTA));

        //delta pega a diferença com ponto flutuante e se ele for maior,
        //eles são quivalentes

    }

    @Test
    public void deve_DevolverMaiorLance_QuandoRecebeMaisDeUmLance_EmOrdemCrescente() {
        CONSOLE.propoe(new Lance(ALEX, 100.0));
        CONSOLE.propoe(new Lance(FRAN, 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, DELTA);

    }

    // ----> MENOR LANCE <----
    @Test
    public void deve_DevolverMenorLance_QuandoRecebeUmLance() {
        CONSOLE.propoe(new Lance(ALEX, 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();
        assertEquals(200.0, menorLanceDevolvido, DELTA);

        //delta pega a diferença com ponto flutuante e se ele for maior,
        //eles são quivalentes

    }

    @Test
    public void deve_DevolverMenorLance_QuandoRecebeMaisDeUmLance_EmOrdemCrescente() {
        CONSOLE.propoe(new Lance(ALEX, 100.0));
        CONSOLE.propoe(new Lance(FRAN, 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, DELTA);

    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances() {
        CONSOLE.propoe(new Lance(ALEX, 200.0));
        CONSOLE.propoe(new Lance(FRAN, 300.0));
        CONSOLE.propoe(new Lance(new Usuario("Joao"), 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

//        assertEquals(3, tresMaioresLancesDevolvidos.size());
//        assertThat(tresMaioresLancesDevolvidos, hasSize(equalTo(3)));

//        assertEquals(400.0,
//                tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
//        assertThat(tresMaioresLancesDevolvidos,
//                hasItem(new Lance(new Usuario("Joao"), 400.0)));
//
//        assertEquals(300.0,
//                tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
//        assertEquals(200.0,
//                tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);
//        assertThat(tresMaioresLancesDevolvidos, contains(
//                new Lance(new Usuario("Joao"), 400.0),
//                new Lance(FRAN, 300.0),
//                new Lance(ALEX, 200.0)));
        assertThat(tresMaioresLancesDevolvidos, both(Matchers.<Lance>hasSize(3))
                .and(contains(new Lance(new Usuario("Joao"), 400.0),
                        new Lance(FRAN, 300.0),
                        new Lance(ALEX, 200.0))));
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances() {
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());

    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance() {
        CONSOLE.propoe(new Lance(ALEX, 200.0));
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(200.0,
                tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);

    }

    @Test
    public void deve_DelvolverTresMaioresLances_QuandoRecebeDoisLances() {
        CONSOLE.propoe(new Lance(ALEX, 300.0));
        CONSOLE.propoe(new Lance(FRAN, 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0,
                tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0,
                tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);

    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances() {
        CONSOLE.propoe(new Lance(ALEX, 300.0));
        CONSOLE.propoe(new Lance(FRAN, 400.0));
        CONSOLE.propoe(new Lance(new Usuario("Jose"), 500.0));
        CONSOLE.propoe(new Lance(new Usuario("Ana"), 600.0));

        List<Lance> tresMaioresLancesDevolvidosParaQuartoLances =
                CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaQuartoLances.size());
        assertEquals(600.0,
                tresMaioresLancesDevolvidosParaQuartoLances.get(0).getValor(), DELTA);
        assertEquals(500.0,
                tresMaioresLancesDevolvidosParaQuartoLances.get(1).getValor(), DELTA);
        assertEquals(400.0,
                tresMaioresLancesDevolvidosParaQuartoLances.get(2).getValor(), DELTA);

        CONSOLE.propoe(new Lance(new Usuario("Maria"), 700.0));

        List<Lance> tresMaioresLancesDevolvidosParaCincoLances =
                CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaCincoLances.size());
        assertEquals(700.0,
                tresMaioresLancesDevolvidosParaCincoLances.get(0).getValor(), DELTA);
        assertEquals(600.0,
                tresMaioresLancesDevolvidosParaCincoLances.get(1).getValor(), DELTA);
        assertEquals(500.0,
                tresMaioresLancesDevolvidosParaCincoLances.get(2).getValor(), DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMaiorLance_QuandoNaoTiverLances() {
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(0.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMenorLance_QuandoNaoTiverLances() {
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(0.0, menorLanceDevolvido, DELTA);
    }

    @Test (expected = LanceMenorQueUltimoLanceException.class)
    public void naoDeve_AdicionarLance_QuandoForMenorQueOMaiorLance() {
        CONSOLE.propoe(new Lance(new Usuario("Maria"), 500.0));
        CONSOLE.propoe(new Lance(ALEX, 400.0));

    }

    @Test (expected = LanceSeguidoDoMesmoUsuarioException.class)
    public void naoDeve_AdicionarLance_QuandoForOMesmoUsuarioDoUltimoLance() {
        CONSOLE.propoe(new Lance(ALEX, 300.0));
        CONSOLE.propoe(new Lance(ALEX, 400.0));

    }

    @Test (expected = UsuarioAtingiuLimiteDeCincoLancesException.class)
    public void naoDeve_AdicionarLance_QuandoUsuarioDerCincoLances() {
        CONSOLE.propoe(new Lance(ALEX, 100.0));
        CONSOLE.propoe(new Lance(FRAN, 200.0));
        CONSOLE.propoe(new Lance(ALEX, 300.0));
        CONSOLE.propoe(new Lance(FRAN, 400.0));
        CONSOLE.propoe(new Lance(ALEX, 500.0));
        CONSOLE.propoe(new Lance(FRAN, 600.0));
        CONSOLE.propoe(new Lance(ALEX, 700.0));
        CONSOLE.propoe(new Lance(FRAN, 800.0));
        CONSOLE.propoe(new Lance(ALEX, 900.0));
        CONSOLE.propoe(new Lance(FRAN, 1000.0));
        CONSOLE.propoe(new Lance(ALEX, 1100.0));

    }

}