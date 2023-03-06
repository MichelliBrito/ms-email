package com.pods.fclabs.models;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class MsgRetorno {
    private Date timestamp;
    private Integer status;
    private String error;
    private String exception;
    private String message;
    private String path;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Usuario usuario;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public MsgRetorno() {

    }

    public static class Builder {
        private Date timestamp;
        private Integer status;
        private String error;
        private String exception;
        private String message;
        private String path;
        private Usuario usuario;

        private Builder() {
            //Not Implemented
        }

        public static final Builder create() {
            return new Builder();
        }

        public Builder timestamp(Date timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        public Builder exception(String exception) {
            this.exception = exception;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder path(String path) {
            this.path = path;
            return this;
        }

        public Builder usuario(Usuario usuario) {
            this.usuario = usuario;
            return this;
        }

        public MsgRetorno build() {
            MsgRetorno msgRetorno = new MsgRetorno();
            msgRetorno.setTimestamp(this.timestamp);
            msgRetorno.setStatus(this.status);
            msgRetorno.setError(this.error);
            msgRetorno.setException(this.exception);
            msgRetorno.setMessage(this.message);
            msgRetorno.setPath(this.path);
            msgRetorno.setUsuario(this.usuario);
            return msgRetorno;
        }
    }
}
