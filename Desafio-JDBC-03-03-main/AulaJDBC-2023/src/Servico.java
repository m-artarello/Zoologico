import java.math.BigInteger;

public class Servico {
    private BigInteger servicoid;
    private String descricao;

    private String queryCreateTable = "create table servico (\n" +
            "\tservicoid SERIAL primary key,\n" +
            "\tdescricao VARCHAR (75) not null\n" +
            ");\n";

    public Servico() {
    }

    public Servico(BigInteger servicoid, String descricao) {
        this.servicoid = servicoid;
        this.descricao = descricao;
    }

    public BigInteger getServicoid() {
        return servicoid;
    }

    public void setServicoid(BigInteger servicoid) {
        this.servicoid = servicoid;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getQueryCreateTable() {
        return queryCreateTable;
    }
}
