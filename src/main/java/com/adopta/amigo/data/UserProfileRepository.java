package com.adopta.amigo.data;

import com.adopta.amigo.models.Usuario;
import com.adopta.amigo.models.UsuarioDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository  extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM UsuarioDetalle u WHERE u.usuario.id = :userId")
    List<UsuarioDetalle> findUserProfileByUserId(@Param("userId") Integer userId);




}
