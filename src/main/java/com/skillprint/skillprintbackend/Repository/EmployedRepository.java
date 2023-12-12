    package com.skillprint.skillprintbackend.Repository;

    import com.skillprint.skillprintbackend.Entity.Employed;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;
    import java.util.Optional;

    @Repository
    public interface EmployedRepository extends JpaRepository<Employed,Long> {

        Optional<Employed> findByUsername(String username);


    }
