import javax.xml.transform.Result;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException{

        // Abre conexão com o banco
        String driverClassName = "org.postgresql.Driver";
        String url = "jdbc:postgresql://127.0.0.1:5432/Zoologico";
        String user = "postgres";
        String password = "admin";
        Class.forName(driverClassName);
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();

        // Instancia classes vazias, somente para executar a queries para criação das tabelas
        Profissional tblProfissional = new Profissional();
        Animal tblAnimal = new Animal();
        Servico tblServico = new Servico();
        ServicosRealizados tblServicosRealizados = new ServicosRealizados();

        // Executa as queries para criação das tabelas
//        Boolean count = statement.execute(tblProfissional.getQueryCreateTable());
//        System.out.println("Criação da tabela `profissional` - Deu erro: " +  count);
//        count = statement.execute(tblAnimal.getQueryCreateTable());
//        System.out.println("Criação da tabela `animal` - Deu erro: " +  count);
//        count = statement.execute(tblServico.getQueryCreateTable());
//        System.out.println("Criação da tabela `servico` - Deu erro: " +  count);
//        count = statement.execute(tblServicosRealizados.getQueryCreateTable());
//        System.out.println("Criação da tabela `servicosrealizados` - Deu erro: " +  count);



        Profissional profissional = new Profissional("Matheus", "Treinador");
        statement.executeUpdate("insert into profissional values (Default, '" + profissional.getNome() + "', '" + profissional.getFuncao() + "');");

        System.out.println();

//        Animal girafa = new Animal("Josefina", "Girafa", );

        connection.close();


    }
}