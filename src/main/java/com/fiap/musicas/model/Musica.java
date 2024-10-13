package com.fiap.musicas.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name="musicas")
@Getter @Setter
@NoArgsConstructor
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "musica")
    @SequenceGenerator(name = "musica", sequenceName = "seq_musica", allocationSize = 1)
    @Column(name="id_msc", length = 9)
    private Long id;

    @Column(name = "nm_musica", nullable = false, length = 100)
    private String nome;

    @Column(name = "nm_artista", nullable = false, length = 100)
    private String artista;

    @Column(name = "ano_lancamento", nullable = false)
    private LocalDate lancamento;

    @Column(name = "nm_album", nullable = false, length = 50)
    private String album;


}

/* CREATE SEQUENCE seq_musica
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

CREATE TABLE musicas (
    id_msc NUMBER(9) NOT NULL,
    nm_musica VARCHAR2(100) NOT NULL,
    nm_artista VARCHAR2(100) NOT NULL,
    ano_lancamento DATE NOT NULL,
    nm_album VARCHAR2(50) NOT NULL,
    PRIMARY KEY (id_msc)
);

CREATE OR REPLACE TRIGGER trg_musica_bi
BEFORE INSERT ON musicas
FOR EACH ROW
BEGIN
    SELECT seq_musica.NEXTVAL INTO :NEW.id_msc FROM dual;
END; */

