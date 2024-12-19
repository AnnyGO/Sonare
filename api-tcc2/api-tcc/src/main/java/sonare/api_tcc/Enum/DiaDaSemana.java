package sonare.api_tcc.Enum;

public enum DiaDaSemana {
    DOMINGO ("Domingo"),
    SEGUNDA ("Segunda"),
    TERCA("Terça"),
    QUARTA("Quarta"),
    QUINTA("Quinta"),
    SEXTA("Sexta"),
    SÁBADO("Sábado");

    private final String diaDaSemana;
    DiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }
    public String getDiaDaSemana(){
        return diaDaSemana;
    }
}
