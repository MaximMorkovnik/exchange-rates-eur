package ua.kiev.prog.dto;

import lombok.Data;

@Data
public class ExchangeRateDTO {

    private String date;
    private Double exchangeRate;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ExchangeRateDTO{");
        sb.append("data='").append(date).append('\'');
        sb.append(", exchangeRate=").append(exchangeRate);
        sb.append('}');
        return sb.toString();
    }
}
