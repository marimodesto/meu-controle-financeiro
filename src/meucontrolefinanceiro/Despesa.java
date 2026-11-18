
package meucontrolefinanceiro;

public class Despesa extends Lancamento {

    public Despesa(String descricao, double valor) {
        super(descricao, valor);
    }

    @Override
    public String getTipo() {
        return "Despesa";
    }
    @Override
public double calcularImpactoNoSaldo() {
    return -getValor();
}
}
