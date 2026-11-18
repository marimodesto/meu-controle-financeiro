
package meucontrolefinanceiro;

public abstract class Lancamento implements OperacaoFinanceira {

    private String descricao;
    private double valor;

    public Lancamento(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public abstract String getTipo();
}
