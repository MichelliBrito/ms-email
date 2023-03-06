package com.pods.fclabs.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@ApiModel(value = "Objeto Entereço", subTypes = {Endereco.class})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_ENDERECO")
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @ApiModelProperty(hidden = true)
    private UUID id;

    @ApiModelProperty(dataType = "String", example = "Cep do usuario no formato xx.xxx-xxx ou xxxxxxxx", required = true, position = 1)
    private String cep;
    @ApiModelProperty(dataType = "String", example = "Nome do logadouro", required = true, position = 2)
    private String logadouro;
    @ApiModelProperty(dataType = "String", example = "Tipo de logadouro", required = true, position = 3)
    private Morada morada;
    @ApiModelProperty(dataType = "String", example = "Numero da casa", required = true, position = 4)
    private String numero;
    @ApiModelProperty(dataType = "String", example = "Complemento", required = false, position = 5)
    private String complemento;
    @ApiModelProperty(dataType = "String", example = "Bairro do usuario", required = true, position = 6)
    private String bairro;
    @ApiModelProperty(dataType = "String", example = "Cidade do usuario", required = true, position = 7)
    private String cidade;
    @ApiModelProperty(hidden = true)
    @Column(name = "dt_criacao", updatable = false)
    private Date dtCriacao;
    @ApiModelProperty(hidden = true)
    private Date dtUltAlteracao;
}
