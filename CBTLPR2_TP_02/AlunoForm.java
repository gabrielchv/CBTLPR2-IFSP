import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AlunoForm extends JFrame {
    private List<Aluno> alunos;
    
    // Form components
    private JTextField nomeField;
    private JTextField enderecoField;
    private JTextField idadeField;
    private JButton okButton;
    private JButton limparButton;
    private JButton mostrarButton;
    private JButton sairButton;

    public AlunoForm() {
        alunos = new ArrayList<>();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        
        setTitle("Cadastro de Alunos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 180);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    private void initializeComponents() {
        // Text fields
        nomeField = new JTextField(20);
        enderecoField = new JTextField(20);
        idadeField = new JTextField(20);
        
        // Buttons
        okButton = new JButton("Ok");
        limparButton = new JButton("Limpar");
        mostrarButton = new JButton("Mostrar");
        sairButton = new JButton("Sair");
    }

    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Nome
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nomeField, gbc);

        // Endereço
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Endereço:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(enderecoField, gbc);

        // Idade
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Idade:"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        add(idadeField, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 5, 0));
        buttonPanel.add(okButton);
        buttonPanel.add(limparButton);
        buttonPanel.add(mostrarButton);
        buttonPanel.add(sairButton);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(buttonPanel, gbc);
    }

    private void setupEventHandlers() {
        // Ok button - save student data
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = nomeField.getText().trim();
                    String endereco = enderecoField.getText().trim();
                    String idadeText = idadeField.getText().trim();

                    if (nome.isEmpty() || endereco.isEmpty() || idadeText.isEmpty()) {
                        JOptionPane.showMessageDialog(AlunoForm.this, 
                            "Todos os campos devem ser preenchidos!", 
                            "Erro", 
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    int idade = Integer.parseInt(idadeText);
                    if (idade < 0) {
                        JOptionPane.showMessageDialog(AlunoForm.this, 
                            "Idade deve ser um número positivo!", 
                            "Erro", 
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Aluno aluno = new Aluno(nome, endereco, idade);
                    alunos.add(aluno);
                    
                    JOptionPane.showMessageDialog(AlunoForm.this, 
                        "Aluno cadastrado com sucesso!", 
                        "Sucesso", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    limparCampos();
                    
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AlunoForm.this, 
                        "Idade deve ser um número válido!", 
                        "Erro", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Limpar button - clear all fields
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });

        // Mostrar button - display all students
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAlunos();
            }
        });

        // Sair button - exit application
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(AlunoForm.this,
                    "Deseja realmente sair da aplicação?",
                    "Confirmar Saída",
                    JOptionPane.YES_NO_OPTION);
                
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private void limparCampos() {
        nomeField.setText("");
        enderecoField.setText("");
        idadeField.setText("");
        nomeField.requestFocus();
    }

    private void mostrarAlunos() {
        if (alunos.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Nenhum aluno cadastrado ainda!", 
                "Lista Vazia", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder mensagem = new StringBuilder("Alunos Cadastrados:\n\n");
        for (Aluno aluno : alunos) {
            mensagem.append("Id: ").append(aluno.getUuid().toString().substring(0, 8))
                   .append("... Nome: ").append(aluno.getNome()).append("\n");
        }

        JOptionPane.showMessageDialog(this, mensagem.toString(), "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Use default look and feel
                new AlunoForm().setVisible(true);
            }
        });
    }
}
