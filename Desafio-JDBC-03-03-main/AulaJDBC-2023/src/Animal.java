import java.math.BigInteger;

public class Animal {
    private BigInteger animalid;
    private String nome;
    private String raca;
    private Profissional treinador;

    private String queryCreateTable = "create table animal (\n" +
            "\tanimalid SERIAL primary key,\n" +
            "\tnome VARCHAR (50) not null,\n" +
            "\tespecie VARCHAR (20) not null,\n" +
            "\ttreinadorid SERIAL not null REFERENCES profissional (profissionalid)\n" +
            ");";

    public Animal() {
    }

    public Animal(String nome, String raca, Profissional treinador) {
        this.nome = nome;
        this.raca = raca;
        this.treinador = treinador;
    }

    public BigInteger getAnimalid() {
        return animalid;
    }

    public void setAnimalid(BigInteger animalid) {
        this.animalid = animalid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Profissional getTreinador() {
        return treinador;
    }

    public void setTreinador(Profissional treinador) {
        this.treinador = treinador;
    }

    public String getQueryCreateTable() {
        return queryCreateTable;
    }
}
