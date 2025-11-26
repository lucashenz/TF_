package aplicacao;

import entidades.*;
import ui.TelaInicial;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;


import static java.nio.file.Files.newBufferedReader;


public class ACMETech {
    // Logger para persistência (movido de PersistenciaUtils)
    private static final Logger logger = Logger.getLogger(ACMETech.class.getName());
    private static final String SEPARADOR = ";"; // Define o separador CSV

    private Fornecedores fornecedores;
    private Tecnologias tecnologias;
    private Compradores compradores;
    private Vendas vendas; // Adicionado 'vendas' para persistência

    // Construtor para inicializar as listas
    public ACMETech() {
        this.fornecedores = new Fornecedores(new ArrayList<>());
        this.tecnologias = new Tecnologias(new ArrayList<>());
        this.compradores = new Compradores(new ArrayList<>());
        this.vendas = new Vendas(new ArrayList<>());
    }

    public void inicializar() {
        Locale.setDefault(new Locale("en", "US")); // Garante que double usa ponto
        this.fornecedores = new Fornecedores(new ArrayList<>());
        this.tecnologias = new Tecnologias(new ArrayList<>());
        this.compradores = new Compradores(new ArrayList<>());
        this.vendas = new Vendas(new ArrayList<>());

        // ===================== PARTICIPANTESENTRADA.CSV =====================
        Path pathParticipantes = Paths.get("dados/PARTICIPANTESENTRADA.CSV");
        try (BufferedReader br = Files.newBufferedReader(pathParticipantes, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith("cod")) continue;

                String[] partes = linha.split(";", -1); // -1 mantém campos vazios

                if (partes.length < 5) {
                    System.err.println("Linha inválida em PARTICIPANTESENTRADA.CSV: " + linha);
                    continue;
                }

                try {
                    long cod = Long.parseLong(partes[0].trim());
                    String nome = partes[1].trim();
                    int tipo = Integer.parseInt(partes[2].trim());

                    if (tipo == 1) { // Fornecedor
                        String fundacaoStr = partes[3].trim();
                        String areaStr = partes[4].trim();

                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        Date fundacao = sdf.parse(fundacaoStr);

                        Area areaEnum = Area.valueOf(areaStr.toUpperCase());
                        fornecedores.addFornecedor(new Fornecedor(cod, nome, fundacao, areaEnum));

                    } else if (tipo == 2) { // Comprador
                        String pais = partes[3].trim();
                        String email = partes[4].trim();
                        compradores.addComprador(new Comprador(cod, nome, pais, email));
                    }

                } catch (Exception e) {
                    System.err.println("Erro ao processar linha (Participantes): " + linha);
                    System.err.println("   → " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler PARTICIPANTESENTRADA.CSV: " + e.getMessage());
        }

        // ===================== TECNOLOGIASENTRADA.CSV =====================
        Path pathTecnologias = Paths.get("dados/TECNOLOGIASENTRADA.CSV");
        try (BufferedReader br = Files.newBufferedReader(pathTecnologias, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith("id")) continue;

                String[] partes = linha.split(";", -1);

                if (partes.length < 6) {
                    System.err.println("Linha inválida em TECNOLOGIASENTRADA.CSV: " + linha);
                    continue;
                }

                try {
                    long id = Long.parseLong(partes[0].trim());
                    String modelo = partes[1].trim();
                    String descricao = partes[2].trim();
                    double valorBase = Double.parseDouble(partes[3].trim().replace(",", "."));
                    double peso = Double.parseDouble(partes[4].trim().replace(",", "."));
                    double temperatura = Double.parseDouble(partes[5].trim().replace(",", "."));

                    Fornecedor fornecedor = null;
                    if (partes.length > 6 && !partes[6].trim().isEmpty()) {
                        long codForn = Long.parseLong(partes[6].trim());
                        fornecedor = fornecedores.buscarFornecedorPorCodigo(codForn);
                        if (fornecedor == null) {
                            System.err.println("Fornecedor não encontrado (ID " + codForn + "): " + linha);
                        }
                    }

                    Tecnologia t = new Tecnologia(id, modelo, descricao, valorBase, peso, temperatura, fornecedor);
                    tecnologias.addTecnologia(t);

                } catch (Exception e) {
                    System.err.println("Erro ao processar tecnologia: " + linha);
                    System.err.println("   → " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler TECNOLOGIASENTRADA.CSV: " + e.getMessage());
        }

        // ===================== VENDASENTRADA.CSV =====================
        Path pathVendas = Paths.get("dados/VENDASENTRADA.CSV");
        Queue<Venda> filaDeVendas = new LinkedList<>();
        try (BufferedReader br = Files.newBufferedReader(pathVendas, StandardCharsets.UTF_8)) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) continue;

                String[] partes = linha.split(";", -1);
                if (partes.length < 4) {
                    System.err.println("Linha inválida em VENDASENTRADA.CSV: " + linha);
                    continue;
                }

                try {
                    long num = Long.parseLong(partes[0].trim());
                    String dataStr = partes[1].trim();
                    long codComprador = Long.parseLong(partes[2].trim());
                    long idTecnologia = Long.parseLong(partes[3].trim());

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    sdf.setLenient(false);
                    Date data = sdf.parse(dataStr);

                    Comprador comprador = compradores.buscarCompradorPorCodigo(codComprador);
                    Tecnologia tecnologia = tecnologias.buscarTecnologiaPorId(idTecnologia);

                    if (comprador == null) {
                        System.err.println("Comprador não encontrado (código " + codComprador + "): " + linha);
                        continue;
                    }
                    if (tecnologia == null) {
                        System.err.println("Tecnologia não encontrada (ID " + idTecnologia + "): " + linha);
                        continue;
                    }

                    Venda v = new Venda(comprador, tecnologia, num, data);
                    v.calculaValorFinal(filaDeVendas);
                    vendas.addVenda(v);
                    filaDeVendas.add(v);

                } catch (Exception e) {
                    System.err.println("Erro ao processar venda: " + linha);
                    System.err.println("   → " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler VENDASENTRADA.CSV: " + e.getMessage());
        }
    }

    public void executar() {
        inicializar();
        TelaInicial panel = new TelaInicial();
        panel.setVisible(true);
    }

    // -----------------------------------------------------------------
    // Lógica de Persistência (Movida de PersistenciaUtils)
    // -----------------------------------------------------------------

    /**
     * Salva todos os dados em um arquivo CSV, usando os atributos da classe ACMETech.
     * ASSINATURA CORRIGIDA: Não recebe as listas como parâmetros, pois elas já são atributos da classe.
     */
    public void salvarDadosEmCSV(
            String nomeArquivo,
            Fornecedores fornecedores,
            Tecnologias tecnologias,
            Compradores compradores,
            Vendas vendas
    ) throws IOException {

        String nomeCompleto = nomeArquivo + ".csv";

        try (FileWriter writer = new FileWriter(nomeCompleto)) {

            // --- Seção 1: FORNECEDORES ---
            writer.append("---ENTIDADE:FORNECEDOR---\n");
            // Cabeçalho: [Participante: cod, nome] + [Fornecedor: fundacao]
            writer.append("cod" + SEPARADOR + "nome" + SEPARADOR + "fundacao (timestamp)\n");
            for (Object f : fornecedores.getFornecedores()) {
                writer.append(((Fornecedor) f).toCSVString() + "\n");
                System.out.println("[DEBUG] Salvando fornecedor: " + ((Fornecedor) f).toCSVString());
            }
            writer.append("\n");

            // --- Seção 2: COMPRADORES ---
            writer.append("---ENTIDADE:COMPRADOR---\n");
            // Cabeçalho: [Participante: cod, nome] + [Comprador: pais, email]
            writer.append("cod" + SEPARADOR + "nome" + SEPARADOR + "pais" + SEPARADOR + "email\n");
            if (compradores.getCompradores() != null) {
                for (Object c : compradores.getCompradores()) {
                    writer.append(((Comprador) c).toCSVString() + "\n");
                }
                writer.append("\n");
            } else {
                writer.append("\n\n");
            }

            // --- Seção 3: TECNOLOGIAS ---
            writer.append("---ENTIDADE:TECNOLOGIA---\n");
            // Cabeçalho: [Tecnologia: id, modelo, descricao, valorBase, peso, temperatura] + [FK: codFornecedor]
            writer.append("id" + SEPARADOR + "modelo" + SEPARADOR + "descricao" + SEPARADOR +
                    "valorBase" + SEPARADOR + "peso" + SEPARADOR + "temperatura" + SEPARADOR +
                    "codFornecedor\n");
            for (Object t : tecnologias.getTecnologias()) {
                writer.append(((Tecnologia) t).toCSVString() + "\n");
            }
            writer.append("\n");

            // --- Seção 4: VENDAS ---
            writer.append("---ENTIDADE:VENDA---\n");
            // Cabeçalho: [Venda: num, data, valorFinal] + [FK: codComprador, idTecnologia]
            writer.append("num" + SEPARADOR + "data (timestamp)" + SEPARADOR + "valorFinal" + SEPARADOR +
                    "codComprador" + SEPARADOR + "idTecnologia\n");
            for (Object v : vendas.getVendas()) {
                writer.append(((Venda) v).toCSVString() + "\n");
            }
            writer.append("\n");

            writer.flush();
            logger.log(Level.INFO, "Dados salvos em CSV com sucesso em: {0}", nomeCompleto);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao salvar arquivo CSV", e);
            throw e;
        }
    }

    public void salvarDadosEmJSON(
            String nomeArquivo,
            Fornecedores fornecedores,
            Tecnologias tecnologias,
            Compradores compradores,
            Vendas vendas
    ) throws IOException {
        String nomeCompleto = nomeArquivo + ".json";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (FileWriter writer = new FileWriter(nomeCompleto)) {
            writer.write("{\n");
            writer.write("  \"fornecedores\": [\n");

            boolean primeiro = true;
            for (Object f : fornecedores.getFornecedores()) {
                Fornecedor forn = (Fornecedor) f;
                if (!primeiro) writer.write(",\n");
                writer.write(String.format("    {\"cod\": %d, \"nome\": \"%s\", \"fundacao\": \"%s\", \"area\": \"%s\"}",
                        forn.getCod(),
                        forn.getNome().replace("\"", "\\\""),
                        sdf.format(forn.getFundacao()),
                        forn.getArea().toString()));
                primeiro = false;
            }
            if (fornecedores.getFornecedores().isEmpty()) {
                writer.write("    {}"); // evita JSON inválido
            }
            writer.write("\n  ],\n");

            writer.write("  \"compradores\": [\n");
            primeiro = true;
            for (Object c : compradores.getCompradores()) {
                Comprador comp = (Comprador) c;
                if (!primeiro) writer.write(",\n");
                writer.write(String.format("    {\"cod\": %d, \"nome\": \"%s\", \"pais\": \"%s\", \"email\": \"%s\"}",
                        comp.getCod(),
                        comp.getNome().replace("\"", "\\\""),
                        comp.getPais(),
                        comp.getEmail()));
                primeiro = false;
            }
            if (compradores.getCompradores().isEmpty()) writer.write("    {}");
            writer.write("\n  ],\n");

            writer.write("  \"tecnologias\": [\n");
            primeiro = true;
            for (Object t : tecnologias.getTecnologias()) {
                Tecnologia tec = (Tecnologia) t;
                if (!primeiro) writer.write(",\n");
                String fornCod = tec.getFornecedor() != null ? String.valueOf(tec.getFornecedor().getCod()) : "null";
                writer.write(String.format("    {\"id\": %d, \"modelo\": \"%s\", \"descricao\": \"%s\", \"valorBase\": %.2f, \"peso\": %.2f, \"temperatura\": %.2f, \"codFornecedor\": %s}",
                        tec.getId(),
                        tec.getModelo().replace("\"", "\\\""),
                        tec.getDescricao().replace("\"", "\\\""),
                        tec.getValorBase(),
                        tec.getPeso(),
                        tec.getTemperatura(),
                        fornCod));
                primeiro = false;
            }
            if (tecnologias.getTecnologias().isEmpty()) writer.write("    {}");
            writer.write("\n  ],\n");

            writer.write("  \"vendas\": [\n");
            primeiro = true;
            for (Object v : vendas.getVendas()) {
                Venda venda = (Venda) v;
                if (!primeiro) writer.write(",\n");
                writer.write(String.format("    {\"numero\": %d, \"data\": \"%s\", \"valorFinal\": %.2f, \"codComprador\": %d, \"idTecnologia\": %d}",
                        venda.getNum(),
                        sdf.format(venda.getData()),
                        venda.calculaValorFinal((Queue<Venda>) vendas.getVendas()),
                        venda.getComprador().getCod(),
                        venda.getTecnologia().getId()));
                primeiro = false;
            }
            if (vendas.getVendas().isEmpty()) writer.write("    {}");
            writer.write("\n  ]\n");
            writer.write("}\n");

            logger.log(Level.INFO, "Dados salvos em JSON com sucesso em: {0}", nomeCompleto);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao salvar arquivo JSON", e);
            throw e;
        }
    }
}