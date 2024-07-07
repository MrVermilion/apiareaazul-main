package bancocrudspringboot.model;

public class OperadoresConsulta {

    public static final String OPERADOR_VAZIO          = "";
    public static final String OPERADOR_TODOS          = "todos";

    public static final String OPERADOR_IGUAL          = "=";
    public static final String OPERADOR_MENOR_IGUAL    = "<=";
    public static final String OPERADOR_MENOR_QUE      = "<";
    public static final String OPERADOR_DIFERENTE_DE   = "<>";
    public static final String OPERADOR_MAIOR_QUE      = ">";
    public static final String OPERADOR_MAIOR_OU_IGUAL = ">=";
    
    // NUMERICO, TEXTO, DATA
    public static final String OPERADOR_PREENCHIDO     = "is not null";
    public static final String OPERADOR_NAO_PREENCHIDO = "is null";
    public static final String OPERADOR_ENTRE          = "between";
    
    // NUMERICO
    public static final String OPERADOR_CONTIDO_EM     = "in";
    public static final String OPERADOR_NAO_CONTIDO_EM = "not in";
    
    // TEXTO
    public static final String OPERADOR_CONTEM         = "ilike";
    public static final String OPERADOR_NAO_CONTEM     = "not ilike";
    public static final String OPERADOR_INICIA_COM     = "ilike%";
    public static final String OPERADOR_TERMINA_COM    = "%ilike";
}