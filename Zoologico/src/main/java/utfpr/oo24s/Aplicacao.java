package utfpr.oo24s;

import javax.persistence.EntityManager;
import utfpr.oo24s.dao.AnimalDAO;
import utfpr.oo24s.dao.ProfissionalDAO;
import utfpr.oo24s.dao.ServicosRealizadosDAO;
import utfpr.oo24s.model.Animal;
import utfpr.oo24s.model.Profissional;
import utfpr.oo24s.model.Servico;
import utfpr.oo24s.model.ServicosRealizados;
import utfpr.oo24s.util.Factory;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Aplicacao {

    public static void main(String[] args) {
        EntityManager em = Factory.getEntityManager(); // Instanciando o Entity Manager através do Factory


        while1:
        while(true){ // Inicia o loop que controla o menu das entidades

            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-\n");
            System.out.println("Escolha uma das entidades abaixo para movimenta-la (Somente numeros): ");
            System.out.println("1 - Profissional;");
            System.out.println("2 - Animal;");
            System.out.println("3 - Servicos");
            System.out.println("0 - Sair");

            Scanner scanner = new Scanner(System.in);
            Integer opcao = scanner.nextInt();

            if (opcao >= 0 && opcao < 4){ // Valida se a opção informada está dentro das opções disponíveis

                switch1: // Label do switch para identificá-lo
                switch (opcao){
                    case 1: // Abre o menu das ações referentes ao Profissional
                        switchProfissional(em);
                        break switch1;
                    case 2: // Abre o menu das ações referentes ao Animal
                        switchAnimal(em);
                        break switch1;
                    case 3: // Abre o menu das ações referentes aos Serviços
                        switchServicos(em);
                        break switch1;
                    case 0: // Finaliza o loop responsável pelo menu
                        break while1;
                }
            } else { // Se a opção informada não bater com nenhuma das alternativas, alerta o usuário e repete o loop.
                System.out.println("A opcao '" + opcao + "' nao condiz com nenhuma das alternativas. Tente novamente \n");
                continue;
            }
        }

        if (em.getTransaction().isActive()){
            em.getTransaction().commit(); // Garante que não tenha nenhuma transação pendente antes de fechar a conexão
        }
        em.close(); // Fecha a conexão
    }

    public static void switchProfissional(EntityManager em){ // Responsável pelo menu dos Profissionais
        while1: // Label para identificar o while
        while (true){
            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=- PROFISSIONAL =-=-=-=-=-=-=-=-=-=-=-=-\n");
            System.out.println("Escolha uma das opcoes abaixo (Somente numeros): ");
            System.out.println("1 - Cadastrar profissional;");
            System.out.println("2 - Consultar todos os profissionais;");
            System.out.println("0 - Voltar");

            Scanner scanner = new Scanner(System.in);
            Integer opcao = scanner.nextInt();

            if(opcao >= 0 && opcao < 3){
                switch1: // Label para identificar o switch
                switch (opcao){
                    case 1: // Cadastrar um novo profissional
                        cadastrarProfissional(em);
                        break switch1;
                    case 2: // Buscar todos os profissionais do banco
                        buscarProfissionais(em);
                        break switch1;
                    case 0: // Voltar para o menu inicial
                        break while1;
                    default:
                        break switch1;
                }
            } else{
                System.out.println("A opcao '" + opcao + "' nao condiz com nenhuma das alternativas. Tente novamente \n");
                continue;
            }
        }
    }

    public static void switchAnimal (EntityManager em){ // Responsável pelo menu dos Animais
        while1: // Label para identificar o while
        while (true){
            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=- ANIMAL =-=-=-=-=-=-=-=-=-=-=-=-\n");
            System.out.println("Escolha uma das opcoes abaixo (Somente numeros): ");
            System.out.println("1 - Cadastrar animal;");
            System.out.println("2 - Buscar todos os animais;");
            System.out.println("3 - Buscar animais por ID do profissional;");
            System.out.println("4 - Buscar animais que ainda nao tiveram nenhum servico realizado no dia;");
            System.out.println("0 - Voltar");

            Scanner scanner = new Scanner(System.in);
            Integer opcao = scanner.nextInt();

            if(opcao >= 0 && opcao < 5){
                switch1: // Label para identificar o switch
                switch (opcao){
                    case 1: // Cadastrar novo animal
                        ProfissionalDAO profissionalDAO = new ProfissionalDAO(em);

                        if(!profissionalDAO.temRegistros()){ // Verifica se existe ao menos um registro de profissional gravado em banco
                            System.out.println("\nNao existe nenhum profissional salvo na base de dados." +
                                    "\n Por favor, cadastre um profissional para que seja possivel cadastrar um novo animal.");
                            break switch1;
                        }
                        cadastrarAnimal(em);
                        break switch1;
                    case 2: // Buscar todos os animais do banco
                        buscarAnimais(em);
                        break switch1;
                    case 3: // Buscar todos os animais relacionados à um profissional específico
                        buscarAnimaisPorProfissional(em);
                        break switch1;
                    case 4: // Busca todos os animais que ainda não tiveram nenhum serviço para o dia
                        buscarAnimaisSemServicoNoDia(em);
                        break switch1;
                    case 0: // Volta para o menu inicial
                        break while1;
                    default:
                        break switch1;
                }
            } else {
                System.out.println("A opcao '" + opcao + "' nao condiz com nenhuma das alternativas. Tente novamente \n");
                continue;
            }
        }
    }

    public static void switchServicos(EntityManager em){ // Responsável pelo menu dos Serviços
        while1: // Label para identificar o while
        while (true) {
            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=- SERVICO =-=-=-=-=-=-=-=-=-=-=-=-\n");
            System.out.println("Escolha uma das opcoes abaixo (Somente numeros): ");
            System.out.println("1 - Consultar lista de servicos;");
            System.out.println("2 - Consultar servicos ainda nao realizados para um animal no dia;");
            System.out.println("3 - Registrar a realizacao de um novo servico;");
            System.out.println("0 - Voltar");

            Scanner scanner = new Scanner(System.in);
            Integer opcao = scanner.nextInt();

            if(opcao >= 0 && opcao < 4) {
                switch1: // Label para identificar o switch
                switch (opcao) {
                    case 1: // Consultar lista completa de servicos
                        consultarListaServicos();
                        break switch1;
                    case 2: // Consultar servicos não realizados em um dia específico para um animal específico
                        consultarServicosNaoRealizadosNoDiaPorAnimal(em);
                        break switch1;
                    case 3: // Registrar a realização de um serviço
                        cadastrarServico(em);
                        break switch1;
                    case 0: // Voltar oa menu inicial
                        break while1;
                }
            }
        }
    }

    public static void cadastrarProfissional(EntityManager em){ // Método para cadastrar um novo profissional
        Scanner scanner = new Scanner(System.in);

        em.getTransaction().begin(); // Inicia uma nova transação

        while (true){
            System.out.println("\nInforme o nome do profissional: ");
            String nome = scanner.nextLine();
            System.out.println("Informe a funcao do profissional: ");
            String funcao = scanner.nextLine();

            if ((nome == "" || nome == null) || (funcao == "" || funcao == null)){ // Se o nome ou a função estiverem vazios, irá solicitar as informações novamente.
                System.out.println("\nUma das opcoes informadas esta vazia. Tente novamente.\n");
                continue;
            }

            Profissional profissional = new Profissional(nome, funcao);
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(em);

            profissionalDAO.gravar(profissional); // Inicia a transação do novo registro
            em.getTransaction().commit(); // Comita a transação no banco de dados

            System.out.println("Profissional de ID " + profissional.getProfissionalid() + " foi cadastrado com sucesso!");

            break;
        }
    }

    public static void buscarProfissionais(EntityManager em){ // Responsável pela busca de todos os profissionais do banco.
        ProfissionalDAO profissionalDAO = new ProfissionalDAO(em);
        List<Profissional> profissionais = profissionalDAO.findAll(); // Executa o método que consulta os profissionais do banco. O ideal seria paginar

        System.out.println("\nLista de profissionais:\n");

        for (Profissional profissional : profissionais){
            System.out.println("ID e nome: " + profissional.getProfissionalid() + " - " + profissional.getNome());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cadastrarAnimal(EntityManager em) { // Responsável pelo cadastro de novos animais
        Scanner scanner = new Scanner(System.in);
        em.getTransaction().begin(); // Inicia uma nova transação

        while (true) {
            System.out.println("\nInforme o nome do animal: ");
            String nome = scanner.nextLine();
            System.out.println("Informe a raca do animal: ");
            String raca = scanner.nextLine();
            System.out.println("Informe o ID do treinador responsavel pelo animal: ");
            Long treinadorid = scanner.nextLong();

            if ((nome == "" || nome == null) || (raca == "" || raca == null) || (treinadorid == 0 || treinadorid == null)) { // Se o nome, raça ou treinador estiverem vazios, irá solicitar as informações novamente.
                System.out.println("\nUma das opcoes informadas esta vazia. Tente novamente.\n");
                continue;
            }

            ProfissionalDAO profissionalDAO = new ProfissionalDAO(em);
            Profissional treinador = profissionalDAO.findById(treinadorid); // Busca o profissional no banco de dados

            if (treinador == null) {
                System.out.println("\n Nao existe nenhum profissional com o ID informado, tente novamente.");
                continue;
            }

            Animal animal = new Animal(nome, raca, treinador);
            AnimalDAO animalDAO = new AnimalDAO(em);

            animalDAO.gravar(animal); // Inicia a transação para inserir o novo animal
            em.getTransaction().commit(); // Comita a inserção no banco de dados

            System.out.println("Animal de ID " + animal.getAnimalid() + " foi cadastrado com sucesso!");

            break;
        }
    }

    public static void buscarAnimais (EntityManager em){ // Busca todos os profissionais do banco de dados
        AnimalDAO animalDAO = new AnimalDAO(em);
        List<Animal> animais = animalDAO.findAll(); // Executa select sem where no banco de dados. O ideal seria paginar

        System.out.println("\nLista de animais:\n");
        for (Animal animal : animais){
            System.out.println("ID e nome: " + animal.getAnimalid() + " - " + animal.getNome() +
                    "\nRaca: " + animal.getRaca() +
                    "\nTreinador: " + animal.getTreinador().getProfissionalid() + " - " + animal.getTreinador().getNome());
        }
    }

    public static void buscarAnimaisPorProfissional(EntityManager em){ // Busca os animais relacionadas à um profissional específico
        AnimalDAO animalDAO = new AnimalDAO(em);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o ID do profissional: ");
        Long profissionalid = scanner.nextLong();

        List<Animal> animais = animalDAO.buscarPorProfissional(profissionalid); // Realiza o select no banco de dados

        System.out.println("\nLista de animais:\n");
        for (Animal animal : animais){
            System.out.println("ID e nome: " + animal.getAnimalid() + " - " + animal.getNome() +
                    "\nRaca: " + animal.getRaca() +
                    "\nTreinador: " + animal.getTreinador().getProfissionalid() + " - " + animal.getTreinador().getNome() + "\n");
        }
    }

    public static void buscarAnimaisSemServicoNoDia(EntityManager em){ // Busca os animais que ainda nao tiveram nenhum servico no dia
        AnimalDAO animalDAO = new AnimalDAO(em);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Informe o dia:");
        Integer dia = scanner.nextInt();

        System.out.println("Informe o mes: ");
        Integer mes = scanner.nextInt() - 1;

        System.out.println("Informe o ano: ");
        Integer ano = scanner.nextInt();

        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mes, dia);
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formatoData.format(calendar.getTime());

        List<Animal> animais = animalDAO.buscarAnimaisComServicosNaoRealizados(calendar); // Realiza o select no banco de dados

        if (animais == null || animais.isEmpty()){
            System.out.println("\nTodos os animais ja tem ao menos um servico registrado para o dia.");
        } else {
            System.out.println("\nAnimais que ainda nao receberam nenhum servico no dia " + dataFormatada + ":");

            for (Animal animal : animais){
                System.out.println(animal.getAnimalid() + " - " + animal.getNome());
            }
        }
    }

    public static void consultarListaServicos(){ // Consulta lista padrão de serviços através do Enum
        System.out.println("=-=-=-=-=-=-=-=-=- LISTA DIARIA DE SERVICOS =-=-=-=-=-=-=-=-=-\n");
        Integer i = 0;

        for (Servico servico : Servico.values()){
            System.out.println(i + " - " + servico.getDescricao());
            i++;
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void consultarServicosNaoRealizadosNoDiaPorAnimal(EntityManager em){ // Consulta os servicos ainda nao realizados no dia para um animal especifico
        Scanner scanner = new Scanner(System.in);
        Long animalid = 0L;

        while (true){
            System.out.println("Informe o ID do animal: ");
            animalid = scanner.nextLong();

            AnimalDAO animalDAO = new AnimalDAO(em);
            Animal animal = animalDAO.findById(animalid); // Busca o animal infdrmado pelo usuário

            if(animal == null){
                System.out.println("Nenhum animal encontrado com o ID informado, tente novamente.");
                continue;
            } else {
                break;
            }
        }

        System.out.println("Informe o dia");
        Integer dia = scanner.nextInt();

        System.out.println("Informe o mes: ");
        Integer mes = scanner.nextInt() - 1;

        System.out.println("Informe o ano: ");
        Integer ano = scanner.nextInt();

        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mes, dia);
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = formatoData.format(calendar.getTime());

        ServicosRealizadosDAO servicosRealizadosDAO = new ServicosRealizadosDAO(em);
        List<Servico> servicosNaoRealizados = servicosRealizadosDAO.buscaServicosNaoRealizadosNoDia(calendar, animalid); // Realiza o select no banco de dados

        System.out.println("\nServicos nao realizados para o animal de ID " + animalid + " no dia " + dataFormatada + "\n");

        for (Servico servico : servicosNaoRealizados){
            System.out.println(servico.getDescricao());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void cadastrarServico(EntityManager em){ // Registrar serviços realizados
        Scanner scanner = new Scanner(System.in);

        em.getTransaction().begin(); // Começa uma nova transação.

        while (true) {
            System.out.println("\nInforme o servico que deseja registrar (Somente numeros): ");
            consultarListaServicos(); // Consome o método que imprime a lista padrão de serviços
            Integer opcao = scanner.nextInt();
            Servico servico = null;

            if (!(opcao >= 0 && opcao < Servico.values().length)){ // Verifica se a opção informada está dentro dos valores disponíveis
                System.out.println("Nenhuma altenativa condiz com o valor informado. \nTente novamente.");
                continue;
            } else {
                servico = Servico.values()[opcao];
            }

            System.out.println("\nInforme o ID do animal que deseja registrar o servico '" + Servico.values()[opcao].getDescricao() + "'.");
            Long animalid = scanner.nextLong();

            AnimalDAO animalDAO = new AnimalDAO(em);
            Animal animal = animalDAO.findById(animalid); // Busca o animal informado pelo usuário no banco de dados

            if(animal == null){
                System.out.println("Nenhum animal com o ID '" + animalid + "' foi encontrado. \nTente novamente.");
                continue;
            }

            System.out.println("\nInforme o ID do treinador/profissional que realizou o servico '" + Servico.values()[opcao].getDescricao() + "'.");
            Long profissionalid = scanner.nextLong();

            ProfissionalDAO profissionalDAO = new ProfissionalDAO(em);
            Profissional profissional = profissionalDAO.findById(profissionalid); // Busca o profissional informado pelo usuário no banco de dados

            if(profissional == null){
                System.out.println("Nenhum profissional com o ID '" + animalid + "' foi encontrado. \nTente novamente.");
                continue;
            }

            System.out.println("Informe o dia do mes em que o servico foi realizado");
            Integer dia = scanner.nextInt();

            System.out.println("Informe o mes: ");
            Integer mes = scanner.nextInt() - 1;

            System.out.println("Informe o ano: ");
            Integer ano = scanner.nextInt();

            System.out.println("Informe a hora: ");
            Integer hora = scanner.nextInt();

            System.out.println("Informe o minuto: ");
            Integer minuto = scanner.nextInt();

            Calendar calendar = Calendar.getInstance();
            calendar.set(ano, mes, dia, hora, minuto);
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String dataFormatada = formato.format(calendar.getTime());

            ServicosRealizados servicoRealizado = new ServicosRealizados(servico, animal, profissional, calendar);
            ServicosRealizadosDAO servicosRealizadosDAO = new ServicosRealizadosDAO(em);

            servicosRealizadosDAO.gravar(servicoRealizado); // Inicia transação de inserção
            em.getTransaction().commit(); // Faz o commit do insert no banco de dados

            System.out.println("\nO servico com o dados abaixo foi gravado com sucesso!");
            System.out.println("Servico: " + servico.getDescricao() + "\n" +
                    "Animal: " + animal.getAnimalid() + " - " + animal.getNome() +
                    "\nProfissional: " + profissional.getProfissionalid() + " - " + profissional.getNome() +
                    "\nData e hora: " +  dataFormatada);
            break;
        }

    }
}
