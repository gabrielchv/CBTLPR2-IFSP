// Arquivo: FormPessoaV03.java

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class FormPessoaV03 extends JFrame implements ActionListener {

    // Instância para armazenar a última pessoa cadastrada (UmaPessoa)
    private Pessoa umaPessoa; 

    // Componentes de entrada
    private final JTextField txtNumero;
    private final JTextField txtNome;
    private final JTextField txtIdade;
    private final JRadioButton radioMasculino;
    private final JRadioButton radioFeminino;
    private final ButtonGroup sexoGroup;

    // Componentes de saída para mostrar os dados
    private final JLabel lblDisplay;

    public FormPessoaV03() {
        setTitle("Exercicio 01");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        // 1. Número
        formPanel.add(new JLabel("Número:"));
        txtNumero = new JTextField(15);
        txtNumero.setEditable(false); // O campo "Numero" não deve estar acessível à digitação
        formPanel.add(txtNumero);

        // 2. Nome
        formPanel.add(new JLabel("Nome:"));
        txtNome = new JTextField(15);
        formPanel.add(txtNome);

        // 3. Sexo (JRadioButton - Versão 03)
        formPanel.add(new JLabel("Sexo:"));
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        radioMasculino = new JRadioButton("M");
        radioFeminino = new JRadioButton("F");
        sexoGroup = new ButtonGroup();
        sexoGroup.add(radioMasculino);
        sexoGroup.add(radioFeminino);
        radioPanel.add(radioMasculino);
        radioPanel.add(radioFeminino);
        formPanel.add(radioPanel);

        // 4. Idade
        formPanel.add(new JLabel("Idade:"));
        txtIdade = new JTextField(15);
        formPanel.add(txtIdade);

        add(formPanel);

        // Botões
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        JButton btnOK = new JButton("OK");
        JButton btnLimpar = new JButton("Limpar");
        JButton btnMostrar = new JButton("Mostrar");
        JButton btnSair = new JButton("Sair");

        btnOK.addActionListener(this);
        btnLimpar.addActionListener(this);
        btnMostrar.addActionListener(this);
        btnSair.addActionListener(this);

        buttonPanel.add(btnOK);
        buttonPanel.add(btnLimpar);
        buttonPanel.add(btnMostrar);
        buttonPanel.add(btnSair);

        add(buttonPanel);

        lblDisplay = new JLabel("Status: Aguardando cadastro...");
        add(lblDisplay);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("OK")) {
            cadastrarPessoa();
        } else if (command.equals("Limpar")) {
            limparCampos();
        } else if (command.equals("Mostrar")) {
            mostrarPessoa();
        } else if (command.equals("Sair")) {
            System.exit(0);
        }
    }

    /**
     * Valida e transfere os dados para o objeto "UmaPessoa" (instância da classe Pessoa).
     */
    private void cadastrarPessoa() {
        String nome = txtNome.getText().trim();
        String idadeStr = txtIdade.getText().trim();
        char sexo;

        // Validação de preenchimento obrigatório
        if (nome.isEmpty() || idadeStr.isEmpty() || (!radioMasculino.isSelected() && !radioFeminino.isSelected())) {
            JOptionPane.showMessageDialog(this, "Nome, Sexo e Idade são de preenchimento obrigatório.", "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Determina o sexo (M ou F)
        if (radioMasculino.isSelected()) {
            sexo = 'M';
        } else if (radioFeminino.isSelected()) {
            sexo = 'F';
        } else {
            return; 
        }
        
        // Validação de formato (Idade)
        try {
            int idade = Integer.parseInt(idadeStr);

            // CORREÇÃO: Cria uma NOVA instância de Pessoa a cada OK.
            // Isso chama o construtor Pessoa() e incrementa o contador kp.
            umaPessoa = new Pessoa(); // Dados digitados deverão ser transferidos ao objeto "UmaPessoa".
            
            umaPessoa.setNome(nome);
            umaPessoa.setSexo(sexo); 
            umaPessoa.setIdade(idade);

            // Atualiza o campo "Numero" com o contador estático kp
            txtNumero.setText(String.valueOf(Pessoa.getKp()));

            lblDisplay.setText("Status: Pessoa " + Pessoa.getKp() + " cadastrada. Clique em Mostrar para ver os dados.");
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "A Idade deve ser um número inteiro válido.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Descarrega os dados do objeto "UmaPessoa" inclusive o contador "kp".
     */
    private void mostrarPessoa() {
        if (umaPessoa == null) {
            lblDisplay.setText("Status: Nenhum objeto Pessoa cadastrado.");
            return;
        }

        String displayInfo = String.format(
            "<html>**Pessoa %d** (kp) - Nome: %s, Sexo: %c, Idade: %d</html>",
            Pessoa.getKp(), // Inclusive o contador "kp".
            umaPessoa.getNome(),
            umaPessoa.getSexo(),
            umaPessoa.getIdade()
        );

        lblDisplay.setText("Status: Dados de Pessoa " + Pessoa.getKp() + " descarregados.");
        JOptionPane.showMessageDialog(this, displayInfo, "Dados da Pessoa", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Limpa os campos do formulário.
     */
    private void limparCampos() {
        txtNome.setText("");
        txtIdade.setText("");
        sexoGroup.clearSelection();
        lblDisplay.setText("Status: Campos limpos.");
        txtNome.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FormPessoaV03::new);
    }
}