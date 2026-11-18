
package meucontrolefinanceiro;

import javax.swing.SwingUtilities;

public class ControleFinanceiro {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setVisible(true);
        });
    }
}
