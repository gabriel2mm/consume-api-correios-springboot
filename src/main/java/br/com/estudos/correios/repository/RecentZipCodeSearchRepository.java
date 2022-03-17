package br.com.estudos.correios.repository;

import br.com.estudos.correios.domain.entity.RecentZipCodeSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecentZipCodeSearchRepository extends JpaRepository<RecentZipCodeSearch, Long> {

    Optional<RecentZipCodeSearch> findByZipCode(String zipCode);

    @Query(value = "SELECT * FROM tb_pesquisas_recentes ORDER BY updated DESC LIMIT 10", nativeQuery = true)
    List<RecentZipCodeSearch> findAllZipCode();
}
