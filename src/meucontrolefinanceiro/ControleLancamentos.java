
package meucontrolefinanceiro;

import java.util.ArrayList;
import java.util.List;

public class ControleLancamentos {

    private List<Lancamento> lancamentos = new ArrayList<>();

    public void adicionar(Lancamento lancamento) {
        lancamentos.add(lancamento);
    }

    public List<Lancamento> listarTodos() {
        return new ArrayList<>(lancamentos);
    }

    public void editar(int indice, String descricao, double valor) {
        if (indice >= 0 && indice < lancamentos.size()) {
            Lancamento lancamento = lancamentos.get(indice);
            lancamento.setDescricao(descricao);
            lancamento.setValor(valor);
        }
    }

public void substituir(int indice, Lancamento lancamento) {
    if (indice >= 0 && indice < lancamentos.size()) {
        lancamentos.set(indice, lancamento);
    }
}

    
    public void excluir(int indice) {
        if (indice >= 0 && indice < lancamentos.size()) {
            lancamentos.remove(indice);
        }
    }

    public double calcularSaldo() {
    double saldo = 0;

    for (Lancamento lancamento : lancamentos) {
        saldo += lancamento.calcularImpactoNoSaldo();
    }

    return saldo;
}
}
