package top.seiei.saasaps.vo;

import java.math.BigDecimal;

public class SpareCapacityOfProductLineVO {

    private String productLineName;
    private BigDecimal spareCapacity;

    public String getProductLineName() {
        return productLineName;
    }

    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName;
    }

    public BigDecimal getSpareCapacity() {
        return spareCapacity;
    }

    public void setSpareCapacity(BigDecimal spareCapacity) {
        this.spareCapacity = spareCapacity;
    }
}
