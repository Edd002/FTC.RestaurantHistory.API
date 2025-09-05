package com.fiap.tech.challenge.global.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fiap.tech.challenge.global.util.HttpUtil;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Date;

@Getter
public abstract class BaseResponse {

    protected boolean success;
    protected final int status;
    protected final String path;
    protected final String reason;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT-3")
    protected final Date timestamp;

    public BaseResponse(int status) {
        this.status = status;
        this.path = HttpUtil.hasCurrentRequest() ? ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath() : null;
        this.reason = HttpStatus.valueOf(status).getReasonPhrase();
        this.timestamp = new Date();
    }

    public ResponseEntity<?> buildResponse() {
        return new ResponseEntity<>(this, HttpStatus.valueOf(this.status));
    }

    public ResponseEntity<?> buildResponseWithoutPayload() {
        return new ResponseEntity<>(HttpStatus.valueOf(this.status));
    }
}
