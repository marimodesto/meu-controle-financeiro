
package meucontrolefinanceiro;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaPrincipal extends JFrame {

    private final ControleLancamentos controle = new ControleLancamentos();

    private final JTextField campoDescricao = new JTextField(15);
    private final JTextField campoValor = new JTextField(10);

    private final JComboBox<String> campoTipo =
            new JComboBox<>(new String[]{"Receita", "Despesa"});

    private final DefaultListModel<String> modeloLista =
            new DefaultListModel<>();

    private final JList<String> lista = new JList<>(modeloLista);

    private final JLabel labelSaldo = new JLabel("Saldo: R$ 0,00");

    public TelaPrincipal() {
        setTitle("Meu Controle Financeiro");
        setSize(650, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        montarTela();
    }

    private void montarTela() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titulo = new JLabel("Meu Controle Financeiro");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.add(titulo, BorderLayout.NORTH);

        labelSaldo.setFont(new Font("Arial", Font.BOLD, 18));
        painelTopo.add(labelSaldo, BorderLayout.SOUTH);

        JPanel painelCampos = new JPanel(new GridLayout(3, 2, 8, 8));

        painelCampos.add(new JLabel("Descrição:"));
        painelCampos.add(campoDescricao);

        painelCampos.add(new JLabel("Valor (R$):"));
        painelCampos.add(campoValor);

        painelCampos.add(new JLabel("Tipo:"));
        painelCampos.add(campoTipo);

        JButton botaoAdicionar = new JButton("Adicionar");
        JButton botaoEditar = new JButton("Editar");
        JButton botaoExcluir = new JButton("Excluir");
        JButton botaoLimpar = new JButton("Limpar campos");

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoEditar);
        painelBotoes.add(botaoExcluir);
        painelBotoes.add(botaoLimpar);

        JPanel painelFormulario = new JPanel(new BorderLayout(8, 8));
        painelFormulario.add(painelCampos, BorderLayout.CENTER);
        painelFormulario.add(painelBotoes, BorderLayout.SOUTH);

        painelPrincipal.add(painelTopo, BorderLayout.NORTH);
        painelPrincipal.add(new JScrollPane(lista), BorderLayout.CENTER);
        painelPrincipal.add(painelFormulario, BorderLayout.SOUTH);

        add(painelPrincipal);

        botaoAdicionar.addActionListener(e -> adicionarLancamento());
        botaoEditar.addActionListener(e -> editarLancamento());
        botaoExcluir.addActionListener(e -> excluirLancamento());
        botaoLimpar.addActionListener(e -> limparCampos());

        lista.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherCampos();
            }
        });
    }

    private double lerValor() {
        String texto = campoValor.getText().trim().replace(",", ".");
        double valor = Double.parseDouble(texto);

        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException(
                    "Digite um valor maior que zero.");
        }

        return valor;
    }

    private Lancamento criarLancamento(String descricao, double valor) {
        String tipo = (String) campoTipo.getSelectedItem();

        if ("Receita".equals(tipo)) {
            return new Receita(descricao, valor);
        }

        return new Despesa(descricao, valor);
    }

    private void adicionarLancamento() {
        try {
            String descricao = campoDescricao.getText().trim();

            if (descricao.isEmpty()) {
                throw new IllegalArgumentException(
                        "Digite a descrição do lançamento.");
            }

            double valor = lerValor();
            controle.adicionar(criarLancamento(descricao, valor));

            atualizarLista();
            limparCampos();

        } catch (NumberFormatException ex) {
            mostrarErro("Digite um valor numérico válido. Ex.: 45,50.");
        } catch (IllegalArgumentException ex) {
            mostrarErro(ex.getMessage());
        }
    }

    
private void editarLancamento() {
    int indice = lista.getSelectedIndex();

    if (indice == -1) {
        mostrarErro("Selecione um lançamento para editar.");
        return;
    }

    try {
        String descricao = campoDescricao.getText().trim();

        if (descricao.isEmpty()) {
            throw new IllegalArgumentException(
                    "Digite a descrição do lançamento.");
        }

        double valor = lerValor();

        Lancamento lancamentoAtual =
                controle.listarTodos().get(indice);

        if (lancamentoAtual.getTipo().equals(
                (String) campoTipo.getSelectedItem())) {

            controle.editar(indice, descricao, valor);

        } else {
    controle.substituir(
            indice,
            criarLancamento(descricao, valor)
    );
}

        atualizarLista();
        limparCampos();

    } catch (NumberFormatException ex) {
        mostrarErro("Digite um valor numérico válido. Ex.: 45,50.");
    } catch (IllegalArgumentException ex) {
        mostrarErro(ex.getMessage());
    }
}


    private void excluirLancamento() {
        int indice = lista.getSelectedIndex();

        if (indice == -1) {
            mostrarErro("Selecione um lançamento para excluir.");
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(
                this,
                "Deseja excluir este lançamento?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacao == JOptionPane.YES_OPTION) {
            controle.excluir(indice);
            atualizarLista();
            limparCampos();
        }
    }

    private void preencherCampos() {
        int indice = lista.getSelectedIndex();

        if (indice >= 0) {
            List<Lancamento> lancamentos = controle.listarTodos();
            Lancamento lancamento = lancamentos.get(indice);

            campoDescricao.setText(lancamento.getDescricao());
            campoValor.setText(String.valueOf(lancamento.getValor()));
            campoTipo.setSelectedItem(lancamento.getTipo());
        }
    }

    private void atualizarLista() {
        modeloLista.clear();

        for (Lancamento lancamento : controle.listarTodos()) {
            modeloLista.addElement(
                    lancamento.getTipo() + " | "
                    + lancamento.getDescricao() + " | R$ "
                    + String.format("%.2f", lancamento.getValor())
            );
        }

        labelSaldo.setText(
                String.format("Saldo: R$ %.2f", controle.calcularSaldo())
        );
    }

    private void limparCampos() {
        campoDescricao.setText("");
        campoValor.setText("");
        lista.clearSelection();
        campoDescricao.requestFocusInWindow();
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(
                this,
                mensagem,
                "Atenção",
                JOptionPane.WARNING_MESSAGE
        );
    }
}
