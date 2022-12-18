package spring.jsf.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferRequest {

    @JsonProperty(value = "em_id")
    private Long emId;

    @JsonProperty(value = "dep_id")
    private Long depId;

    public TransferRequest() {
    }

    public TransferRequest(Long emId, Long depId) {
        this.emId = emId;
        this.depId = depId;
    }

    public Long getEmId() {
        return emId;
    }

    public void setEmId(Long emId) {
        this.emId = emId;
    }

    public Long getDepId() {
        return depId;
    }

    public void setDepId(Long depId) {
        this.depId = depId;
    }
}
