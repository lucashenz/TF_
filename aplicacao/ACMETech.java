package aplicacao;

import entidades.*;
import ui.TelaInicial;
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

    // ... (Método inicializar() - Sem alterações) ...
    public void inicializar() {
        // Inicializa listas vazias (caso não tenha sido feito no construtor)
        this.fornecedores = new Fornecedores(new ArrayList<>());
        this.tecnologias = new Tecnologias(new ArrayList<>());
        this.compradores = new Compradores(new ArrayList<>());
        this.vendas = new Vendas(new ArrayList<>());


        Path pathParticipantes = Paths.get("PARTICIPANTESENTRADA.CSV");
        try (BufferedReader br = newBufferedReader(pathParticipantes, Charset.forName("UTF-8"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("cod")) continue;
                Scanner sc = new Scanner(linha).useDelimiter(";");

                long cod = sc.nextLong();
                String nome = sc.next();
                int tipo = sc.nextInt();

                if (tipo == 1) {
                    String fundacaoStr = sc.next();
                    String areaStr = sc.next();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    Date fundacao = null;
                    try {
                        fundacao = sdf.parse(fundacaoStr);
                    } catch (ParseException e) {
                        System.err.println("Formato de data inválido em PARTICIPANTESENTRADA.CSV: " + fundacaoStr);
                        continue;
                    }

                    Area areaEnum;
                    try {
                        areaEnum = Area.valueOf(areaStr.trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.err.println("Área inválida no CSV: " + areaStr + " — valores válidos: TI, ANDROIDES, EMERGENTE, ALIMENTOS");
                        continue;
                    }

                    fornecedores.addFornecedor(new Fornecedor(cod, nome, fundacao, areaEnum));
                } else if (tipo == 2) {
                    String pais = sc.next();
                    String email = sc.next();
                    compradores.addComprador(new Comprador(cod, nome, pais, email));
                } else {
                    throw new IOException("Tipo de participante inválido.");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler PARTICIPANTESENTRADA.CSV: " + e.getMessage());
        }

        Path pathTecnologias = Paths.get("TECNOLOGIASENTRADA.CSV");
        try (BufferedReader br = newBufferedReader(pathTecnologias, Charset.forName("UTF-8"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.startsWith("id")) continue;
                Scanner sc = new Scanner(linha).useDelimiter(";");

                long id = sc.nextLong(); 
                String modelo = sc.next();
                String descricao = sc.next();
                double valorBase = sc.nextDouble();
                double peso = sc.nextDouble();
                double temperatura = sc.nextDouble();
                String codFornecedorStr = sc.hasNext() ? sc.next() : "";
                Fornecedor fornecedor = null;

                if (!codFornecedorStr.isEmpty()) {
                    try {
                        long codFornecedor = Long.parseLong(codFornecedorStr); 
                        // Corrigido: buscarFornecedorPorCodigo deve aceitar long
                        fornecedor = fornecedores.buscarFornecedorPorCodigo((int)codFornecedor); 
                    } catch (NumberFormatException e) {
                        System.err.println("Código de fornecedor inválido em TECNOLOGIASENTRADA.CSV: " + codFornecedorStr);
                    }
                }
                Tecnologia t = new Tecnologia(id, modelo, descricao, valorBase, peso, temperatura, fornecedor);
                tecnologias.addTecnologia(t);
            }
        }catch (IOException e){
            System.err.println("Erro ao ler TECNOLOGIAS.CSV: " + e.getMessage());
        }

        Path pathVendas = Paths.get("VENDASENTRADA.CSV");
        Queue<Venda> FilaDevendas = new LinkedList<>();
        try (BufferedReader br = Files.newBufferedReader(pathVendas, Charset.forName("UTF-8"))) {
            String linha;
            while((linha= br.readLine())!=null){
                Scanner sc = new Scanner(linha).useDelimiter(";");

                long num = sc.nextLong(); 
                String data = sc.next();
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);

                Date dataAux;
                try {
                    dataAux = sdf.parse(data);
                } catch (ParseException e) {
                    throw new IllegalArgumentException("Data inválida: " + data + ". Use o formato dd/MM/yyyy");
                }
                
                long codComprador = sc.nextLong(); 
                long idTecnologia = sc.nextLong(); 

                Comprador comprador = buscarCompradorPorCodigo(codComprador);
                Tecnologia tecnologia = buscarTecnologiaPorId(idTecnologia);

                Venda v = new Venda(comprador, tecnologia, num, dataAux);
                v.calculaValorFinal(FilaDevendas);
                vendas.addVenda(v); 
                FilaDevendas.add(v); 
            }
        }catch(IOException e){
            System.err.println("Erro ao ler VENDASENTRADA.CSV: " + e.getMessage());
        }
    }


    public void executar() {
        inicializar();
        // A TelaInicial deve ser instanciada com as listas gerenciadas por ACMETech
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
                writer.append(((Fornecedor)f).toCSVString() + "\n"); 
                System.out.println("[DEBUG] Salvando fornecedor: " + ((Fornecedor)f).toCSVString());
            }
            writer.append("\n");

            // --- Seção 2: COMPRADORES ---
            writer.append("---ENTIDADE:COMPRADOR---\n");
            // Cabeçalho: [Participante: cod, nome] + [Comprador: pais, email]
            writer.append("cod" + SEPARADOR + "nome" + SEPARADOR + "pais" + SEPARADOR + "email\n");
            if(compradores.getCompradores() != null){
                for (Object c : compradores.getCompradores()) {
                    writer.append(((Comprador)c).toCSVString() + "\n");
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
                writer.append(((Tecnologia)t).toCSVString() + "\n");
            }
            writer.append("\n");
            
            // --- Seção 4: VENDAS ---
            writer.append("---ENTIDADE:VENDA---\n");
            // Cabeçalho: [Venda: num, data, valorFinal] + [FK: codComprador, idTecnologia]
            writer.append("num" + SEPARADOR + "data (timestamp)" + SEPARADOR + "valorFinal" + SEPARADOR + 
                          "codComprador" + SEPARADOR + "idTecnologia\n");
            for (Object v : vendas.getVendas()) {
                writer.append(((Venda)v).toCSVString() + "\n");
            }
            writer.append("\n");
            
            writer.flush();
            logger.log(Level.INFO, "Dados salvos em CSV com sucesso em: {0}", nomeCompleto);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Erro ao salvar arquivo CSV", e);
            throw e; 
        }
    }
    
    // NOTA: Para CarregarDadosEmCSV() e JSON, a lógica deve ser implementada aqui.
    
    // -----------------------------------------------------------------
    // Métodos Auxiliares
    // -----------------------------------------------------------------

    private Comprador buscarCompradorPorCodigo(long codComprador){
         // Assumindo que Compradores tem getCompradores() que retorna List<Comprador>
        for (Comprador comprador : this.compradores.getCompradores()) { 
            if (comprador.getCod() == codComprador) {
                return comprador;
            }
        }
        return null;
    }

    private Tecnologia buscarTecnologiaPorId(long idTecnologia){
         // Assumindo que Tecnologias tem getTecnologias() que retorna List<Tecnologia>
        for (Tecnologia tecnologia : this.tecnologias.getTecnologias()) { 
            if (tecnologia.getId() == idTecnologia) {
                return tecnologia;
            }
        }
        return null;
    }
}