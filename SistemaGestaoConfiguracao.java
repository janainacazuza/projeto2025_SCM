/**
 * Sistema de Gestão de Configuração de Software (SCM)
 *
 * Projeto desenvolvido para a disciplina de Projeto de Software
 * Unidade 4 - Gerenciamento de Configuração
 *
 * @author Janaína Cazuza
 * @version 1.0.0
 * @since 2025
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SistemaGestaoConfiguracao {

    private final List<ItemConfiguracao> itens;
    private final List<String> historicoAlteracoes;
    private final String nomeRepositorio;

    public SistemaGestaoConfiguracao(String nomeRepositorio) {
        this.nomeRepositorio = nomeRepositorio;
        this.itens = new ArrayList<>();
        this.historicoAlteracoes = new ArrayList<>();
        registrarEvento("Repositório '" + nomeRepositorio + "' inicializado");
    }

    public void adicionarItem(String nome, String tipo, String versao) {
        ItemConfiguracao item = new ItemConfiguracao(nome, tipo, versao);
        itens.add(item);
        registrarEvento("Item adicionado: " + nome + " (v" + versao + ")");
    }

    public void atualizarVersao(String nome, String novaVersao) {
        for (ItemConfiguracao item : itens) {
            if (item.getNome().equals(nome)) {
                String versaoAnterior = item.getVersao();
                item.setVersao(novaVersao);
                registrarEvento("Item atualizado: " + nome + " v" + versaoAnterior + " -> v" + novaVersao);
                return;
            }
        }
        System.out.println("Item '" + nome + "' não encontrado no repositório.");
    }

    public void listarItens() {
        System.out.println("\n========================================");
        System.out.println("  Repositório: " + nomeRepositorio);
        System.out.println("  Itens de Configuração Registrados");
        System.out.println("========================================");

        if (itens.isEmpty()) {
            System.out.println("  Nenhum item registrado.");
        } else {
            System.out.printf("  %-25s %-15s %-10s%n", "Nome", "Tipo", "Versão");
            System.out.println("  " + "-".repeat(50));
            for (ItemConfiguracao item : itens) {
                System.out.printf("  %-25s %-15s %-10s%n",
                        item.getNome(), item.getTipo(), item.getVersao());
            }
        }

        System.out.println("========================================");
        System.out.println("  Total de itens: " + itens.size());
        System.out.println("========================================\n");
    }

    public void exibirHistorico() {
        System.out.println("\n========================================");
        System.out.println("  Histórico de Alterações");
        System.out.println("========================================");

        for (String registro : historicoAlteracoes) {
            System.out.println("  " + registro);
        }

        System.out.println("========================================\n");
    }

    private void registrarEvento(String descricao) {
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        historicoAlteracoes.add("[" + timestamp + "] " + descricao);
    }

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("  Sistema de Gestão de Configuração (SCM)");
        System.out.println("  Projeto de Software - Janaína Cazuza");
        System.out.println("==============================================\n");

        SistemaGestaoConfiguracao scm = new SistemaGestaoConfiguracao("projeto2025_SCM");

        scm.adicionarItem("Main.java", "Código-fonte", "1.0.0");
        scm.adicionarItem("README.md", "Documentação", "1.0.0");
        scm.adicionarItem("DiagramaClasses.png", "Design", "1.0.0");
        scm.adicionarItem("RequisitosFuncionais.docx", "Documentação", "1.0.0");
        scm.adicionarItem("TestesUnitarios.java", "Teste", "1.0.0");

        scm.listarItens();

        scm.atualizarVersao("Main.java", "1.1.0");
        scm.atualizarVersao("README.md", "1.0.1");

        scm.listarItens();
        scm.exibirHistorico();
    }
}

class ItemConfiguracao {

    private final String nome;
    private final String tipo;
    private String versao;

    public ItemConfiguracao(String nome, String tipo, String versao) {
        this.nome = nome;
        this.tipo = tipo;
        this.versao = versao;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }
}
