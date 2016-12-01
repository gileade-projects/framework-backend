package gumga.framework.application.nlp;

import gumga.framework.domain.nlp.GumgaNLPThing;
import java.math.BigDecimal;

@GumgaNLPThing("Conta")
public class Conta {

    private TipoConta tipo;

    private BigDecimal valor;

    private String cliente;

    @Override
    public String toString() {
        return "Conta{" + "tipo=" + tipo + ", valor=" + valor + ", cliente=" + cliente + '}';
    }

}
