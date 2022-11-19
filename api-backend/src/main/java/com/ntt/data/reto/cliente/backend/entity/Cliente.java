package com.ntt.data.reto.cliente.backend.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "codigo_unico")
    private String codigoUnico;

    @Column
    private String nombre;

    @Column
    private String apellidos;

    @Column(name = "tipo_documento")
    private String tipoDocumento;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        return codigoUnico != null && Objects.equals(codigoUnico, ((Cliente) o).codigoUnico);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
