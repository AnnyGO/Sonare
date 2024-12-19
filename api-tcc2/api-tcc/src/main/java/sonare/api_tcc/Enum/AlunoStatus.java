package sonare.api_tcc.Enum;

public enum AlunoStatus {
    ATIVO ("Ativo"),
    CURSANDO ("Cursando"),
    SUSPENSO ("Suspenso"),
    INATIVO ("Inativo");

    private final String status;

    AlunoStatus(String status) {
        this.status = status;
    }
    private String getStatus(){return status;}
}
