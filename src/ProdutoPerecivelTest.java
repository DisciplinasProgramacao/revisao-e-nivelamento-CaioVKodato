import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProdutoPerecivelTest {

    static ProdutoPerecivel produtoValido;
    static ProdutoPerecivel produtoComDesconto;

    @BeforeAll
    static void setup() {
        produtoValido = new ProdutoPerecivel("Leite", 100, 0.2, LocalDate.now().plusDays(10));
        produtoComDesconto = new ProdutoPerecivel("Iogurte", 100, 0.2, LocalDate.now().plusDays(5));
    }

    @Test
    public void calculaPrecoSemDesconto() {
        assertEquals(120.0, produtoValido.valorDeVenda(), 0.01);
    }

    @Test
    public void calculaPrecoComDesconto() {
        assertEquals(90.0, produtoComDesconto.valorDeVenda(), 0.01);
    }

    @Test
    public void naoCriaProdutoComDataInvalida() {
        assertThrows(IllegalArgumentException.class, () -> 
            new ProdutoPerecivel("Queijo", 50, 0.2, LocalDate.now().minusDays(1)));
    }

    @Test
    public void naoVendeProdutoVencido() {
        ProdutoPerecivel produtoVencido = new ProdutoPerecivel("Carne", 100, 0.2, LocalDate.now());
        assertThrows(IllegalStateException.class, produtoVencido::valorDeVenda);
    }
}
