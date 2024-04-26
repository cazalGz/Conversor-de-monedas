package models;

import java.util.Map;

public class Moneda {

    private String base_code;
    private Map<String, Double> conversion_rates;
    private double valorEnOtraMoneda;



    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public double getValorEnOtraMoneda() {
        return valorEnOtraMoneda;
    }

    public void setValorEnOtraMoneda(String cambio) {
        this.valorEnOtraMoneda = (double) conversion_rates.get(cambio);
    }

    public String toString() {
        return "moneda='" + base_code +"';"+
                " valor en otra moneda="+valorEnOtraMoneda;
    }



}
