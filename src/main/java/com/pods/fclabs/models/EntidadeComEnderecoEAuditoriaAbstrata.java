package com.pods.fclabs.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.util.Date;
@MappedSuperclass
@Data
public abstract class EntidadeComEnderecoEAuditoriaAbstrata {
    @ApiModelProperty(hidden = true)
    @Column(name = "dt_criacao", updatable = false)
    private Date dtCriacao;
    @ApiModelProperty(hidden = true)
    private Date dtUltAlteracao;
    @ApiModelProperty(dataType = "Endereco", example = "Endereco do usuario", required = true, position = 4)
    @OneToOne
    private Endereco endereco;
}
