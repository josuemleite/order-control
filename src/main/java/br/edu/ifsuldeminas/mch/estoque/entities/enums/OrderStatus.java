package br.edu.ifsuldeminas.mch.estoque.entities.enums;

public enum OrderStatus {

    WAITING_PAYMENT(1, "Aguardando pagamento"),
    PAID(2, "Pago"),
    SHIPPED(3, "Enviado"),
    DELIVERED(4, "Entregue"),
    CANCELED(5, "Cancelado");

    private int code;
    private String description;

    private OrderStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static OrderStatus valueOf(int code) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}