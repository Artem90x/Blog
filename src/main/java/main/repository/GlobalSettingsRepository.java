package main.repository;

import main.model.GlobalSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GlobalSettingsRepository extends JpaRepository<GlobalSetting, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE global_settings s SET s.value = :value WHERE s.code = :code", nativeQuery = true)
    void insertSettings(@Param("code") String code, @Param("value") String value);

    @Query("SELECT s FROM GlobalSetting s WHERE s.code = :code")
    GlobalSetting findAllGlobalSettings(@Param("code") String code);
}
