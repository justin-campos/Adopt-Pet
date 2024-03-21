package com.adopta.amigo.data;

import com.adopta.amigo.models.Mascota;
import com.adopta.amigo.models.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
    List<Mascota> findByNombrePerro(String nombrePerro);

    List<Mascota> findAllById(Integer id);

    @Query("SELECT m.raza.name FROM Mascota m WHERE m.id = :mascotaId")
    String findRazaNameByMascotaId(Integer mascotaId);

    @Query("SELECT m.usuario FROM Mascota m WHERE m.id = :mascotaId")
    List<Object[]> findUserIdAndNameByMascotaId(@Param("mascotaId") Integer mascotaId);

    @Query("SELECT m FROM Mascota m ORDER BY m.id ASC")
    List<Mascota> findFirst4ByOrderByIdAsc();


}
