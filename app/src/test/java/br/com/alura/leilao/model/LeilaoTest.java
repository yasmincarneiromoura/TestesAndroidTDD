package br.com.alura.leilao.model;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;


public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario ALEX = new Usuario("Alex");
    private final Usuario FRAN = new Usuario("Fran");


    @Test
    public void getDescricao_QuandoRecebeDescricao_DevolveDescricao(){
         String descricaoDevolvida = CONSOLE.getDescricao();
         assertEquals("Console", descricaoDevolvida);
    }
    @Test
    public void getMaiorLance_QuandoRecebeUmLance_DevolveMaiorLance(){
        CONSOLE.propoe(new Lance(ALEX, 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(200.0, maiorLanceDevolvido, DELTA);

        //delta pega a diferença com ponto flutuante e se ele for maior,
        //eles são quivalentes

    }
    @Test
     public void getMaiorLance_QuandoRecebeMaisDeUmLance_EmOrdemCrescente_DevolveMaiorLance() {
        CONSOLE.propoe(new Lance(ALEX, 100.0));
        CONSOLE.propoe(new Lance(FRAN, 200.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();

        assertEquals(200.0, maiorLanceDevolvido, DELTA);

    }
    @Test
    public void getMaiorLance_QuandoRecebeMaisDeUmLance_EmOrdemDecrescente_DevolveMaiorLance() {
        CONSOLE.propoe((new Lance(ALEX, 10000.0)));
        CONSOLE.propoe(new Lance(FRAN, 9000.0));

        double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(10000.0, maiorLanceDevolvido, DELTA);
    }
// ----> MENOR LANCE <----
    @Test
    public void getMenorLance_QuandoRecebeUmLance_DevolveMenorLance(){
        CONSOLE.propoe(new Lance(ALEX, 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();
        assertEquals(200.0, menorLanceDevolvido, DELTA);

        //delta pega a diferença com ponto flutuante e se ele for maior,
        //eles são quivalentes

    }
    @Test
    public void getMenorLance_QuandoRecebeMaisDeUmLance_EmOrdemCrescente_DevolveMenorLance() {
        CONSOLE.propoe(new Lance(ALEX, 100.0));
        CONSOLE.propoe(new Lance(FRAN, 200.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(100.0, menorLanceDevolvido, DELTA);

    }
    @Test
    public void getMenorLance_QuandoRecebeMaisDeUmLance_EmOrdemDecrescente_DevolveMenorLance() {
        CONSOLE.propoe((new Lance(ALEX, 10000.0)));
        CONSOLE.propoe(new Lance(FRAN, 9000.0));

        double menorLanceDevolvido = CONSOLE.getMenorLance();
        assertEquals(9000.0, menorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeExatosTresLances () {
        CONSOLE.propoe(new Lance(ALEX, 200.0));
        CONSOLE.propoe(new Lance(FRAN, 300.0));
        CONSOLE.propoe(new Lance(new Usuario("Joao"), 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0,
                tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
        assertEquals(300.0,
                tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
        assertEquals(200.0,
                tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);

    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoNaoRecebeLances(){
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());

    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeApenasUmLance(){
        CONSOLE.propoe(new Lance(ALEX, 200.0));
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(1, tresMaioresLancesDevolvidos.size());
        assertEquals(200.0,
                tresMaioresLancesDevolvidos.get(0).getValor(),DELTA);

    }

    @Test
    public void deve_DelvolverTresMaioresLances_QuandoRecebeDoisLances(){
        CONSOLE.propoe(new Lance(ALEX, 300.0));
        CONSOLE.propoe(new Lance(FRAN, 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioresLances();

        assertEquals(2, tresMaioresLancesDevolvidos.size());
        assertEquals(400.0,
                tresMaioresLancesDevolvidos.get(0).getValor(),DELTA);
        assertEquals(300.0,
                tresMaioresLancesDevolvidos.get(1).getValor(),DELTA);

    }

    @Test
    public void deve_DevolverTresMaioresLances_QuandoRecebeMaisDeTresLances(){
        CONSOLE.propoe(new Lance(ALEX, 300.0));
        CONSOLE.propoe(new Lance(FRAN, 400.0));
        CONSOLE.propoe(new Lance(new Usuario("Jose"), 500.0));
        CONSOLE.propoe(new Lance(new Usuario("Ana"), 600.0));

        List<Lance> tresMaioresLancesDevolvidosParaQuartoLances =
                CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaQuartoLances.size());
        assertEquals(600.0,
                tresMaioresLancesDevolvidosParaQuartoLances.get(0).getValor(),DELTA);
        assertEquals(500.0,
                tresMaioresLancesDevolvidosParaQuartoLances.get(1).getValor(),DELTA);
        assertEquals(400.0,
                tresMaioresLancesDevolvidosParaQuartoLances.get(2).getValor(),DELTA);
        
        CONSOLE.propoe(new Lance(new Usuario("Maria"), 700.0));

        List<Lance> tresMaioresLancesDevolvidosParaCincoLances =
                CONSOLE.tresMaioresLances();

        assertEquals(3, tresMaioresLancesDevolvidosParaCincoLances.size());
        assertEquals(700.0,
                tresMaioresLancesDevolvidosParaCincoLances.get(0).getValor(),DELTA);
        assertEquals(600.0,
                tresMaioresLancesDevolvidosParaCincoLances.get(1).getValor(),DELTA);
        assertEquals(500.0,
                tresMaioresLancesDevolvidosParaCincoLances.get(2).getValor(),DELTA);


    }




}

