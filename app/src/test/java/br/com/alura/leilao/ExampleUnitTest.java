package br.com.alura.leilao;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.security.KeyStore;

import br.com.alura.leilao.model.Usuario;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

        assertEquals(4, 2 + 2);
        assertNotEquals(3, 2 + 2);

        assertTrue(true);
        assertFalse(false);

        assertNull(null);
        assertNotNull(new Usuario("Alex"));

        assertThat(2 + 2, equalTo(4));
    }
}