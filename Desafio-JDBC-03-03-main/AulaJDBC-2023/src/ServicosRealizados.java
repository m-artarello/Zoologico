import java.math.BigInteger;
import java.util.Calendar;

public class ServicosRealizados {
    private BigInteger servicosrealizadosid;
    private Servico servico;
    private Animal animal;
    private Calendar datahora;

    private String queryCreateTable = "create table servicosrealizados (\n" +
            "\tservicosrealizadosid  SERIAL primary key,\n" +
            "\tservicoid SERIAL not null references servico (servicoid),\n" +
            "\tanimalid SERIAL not null references animal (animalid),\n" +
            "\tdatahora TIMESTAMP without time zone not null\n" +
            ");";

    public ServicosRealizados() {
    }

    public ServicosRealizados(BigInteger servicosrealizadosid, Servico servico, Animal animal, Calendar datahora) {
        this.servicosrealizadosid = servicosrealizadosid;
        this.servico = servico;
        this.animal = animal;
        this.datahora = datahora;
    }

    public BigInteger getServicosrealizadosid() {
        return servicosrealizadosid;
    }

    public void setServicosrealizadosid(BigInteger servicosrealizadosid) {
        this.servicosrealizadosid = servicosrealizadosid;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Calendar getDatahora() {
        return datahora;
    }

    public void setDatahora(Calendar datahora) {
        this.datahora = datahora;
    }

    public String getQueryCreateTable() {
        return queryCreateTable;
    }
}
