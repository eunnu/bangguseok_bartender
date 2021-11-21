package com.cocktail.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Service
public class UUIDGenerator {

    @Autowired
    UUIDRepository uuidRepository;

    @Transactional
    public String getUUID() {
        String id;
        do {
            id = UUID.randomUUID().toString();
        } while (uuidRepository.findById(id).isPresent());

        uuidRepository.save(new UUIDEntity(id));

        return id;
    }
}

@Entity
@Table(name = "uuid")
@Data
@NoArgsConstructor
class UUIDEntity {
    @Id
    @Column(name = "uuid")
    private String uuid;

    public UUIDEntity(String id) {
        this.uuid = id;
    }
}

@Repository
interface UUIDRepository extends JpaRepository<UUIDEntity, String> {
}
