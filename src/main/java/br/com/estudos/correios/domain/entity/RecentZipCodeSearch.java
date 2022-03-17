package br.com.estudos.correios.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_pesquisas_recentes")
public class RecentZipCodeSearch {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    @Column(name = "CEP", length = 8, nullable = false, unique = true)
    private String zipCode;

    @UpdateTimestamp
    private LocalDateTime updated;

    @Column(name = "quantidade_de_consulta")
    private int numberQueries;
}
