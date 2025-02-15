import java.time.LocalDate;

public class ProdutoPerecivel extends Produto {
    
    private static final double DESCONTO = 0.25;
    private static final int PRAZO_FINAL = 7;
    private LocalDate dataValidade;

    public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate dataValidade) {
        super(desc, precoCusto, margemLucro);
        if (dataValidade.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data de validade inválida");
        }
        this.dataValidade = dataValidade;
    }

    @Override
    public double valorDeVenda() {
        if (dataValidade.isBefore(LocalDate.now())) {
            throw new IllegalStateException("Produto vencido não pode ser vendido.");
        }
        
        long diasParaVencer = LocalDate.now().until(dataValidade).getDays();
        double precoBase = precoCusto * (1 + margemLucro);
        boolean aplicarDesconto = diasParaVencer <= 7;
        
        return aplicarDesconto ? precoBase * 0.75 : precoBase;
    }

    public LocalDate getDataValidade() {
        return this.dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }



    @Override
    public String toString() {
        return "{" +
            " dataValidade='" + getDataValidade() + "'" +
            "}";
    }





}

    

