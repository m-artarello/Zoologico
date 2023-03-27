import java.math.BigInteger;

public class Profissional {
    private BigInteger profissionalid;
    private String nome;
    private String funcao;

    private String queryCreateTable = "create table profissional (\n" +
            "\tprofissionalid SERIAL primary key,\n" +
            "\tnome VARCHAR (100) not null,\n" +
            "\tfuncao VARCHAR (30) not null\n" +
            ");";

    public Profissional() {
    }

    public Profissional(String nome, String funcao) {
        this.nome = nome;
        this.funcao = funcao;
    }

    public BigInteger getProfissionalid() {
        return profissionalid;
    }

    public void setProfissionalid(BigInteger profissionalid) {
        this.profissionalid = profissionalid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public String getQueryCreateTable() {
        return queryCreateTable;
    }
}
