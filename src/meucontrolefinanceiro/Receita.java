
package meucontrolefinanceiro;

public class Receita extends Lancamento {

    public Receita(String descricao, double valor) {
        super(descricao, valor);
    }

    @Override
    public String getTipo() {
        return "Receita";
    }
    @Override
public double calcularImpactoNoSaldo() {
    return getValor();
}
}
