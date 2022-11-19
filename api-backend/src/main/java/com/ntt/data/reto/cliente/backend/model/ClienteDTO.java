package com.ntt.data.reto.cliente.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    @JsonProperty("codigoUnico")
    @NotBlank(message = "Ingrese código único")
    private String codigoUnico;
    @NotBlank(message = "Ingrese nombres")
    @JsonProperty("nombres")
    private String nombre;
    @NotBlank(message = "Ingrese apellidos")
    private String apellidos;
    @NotBlank(message = "Ingrese tipo de documento")
    @JsonProperty("tipoDocumento")
    private String tipoDocumento;
    @NotBlank(message = "Ingrese número de documento")
    @JsonProperty("numeroDocumento")
    private String numeroDocumento;
}
