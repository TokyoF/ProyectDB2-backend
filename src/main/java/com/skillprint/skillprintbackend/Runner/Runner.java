package com.skillprint.skillprintbackend.Runner;


import com.skillprint.skillprintbackend.Entity.Category;
import com.skillprint.skillprintbackend.Entity.Profile;
import com.skillprint.skillprintbackend.Entity.ProfileType;
import com.skillprint.skillprintbackend.Repository.CategoryRepository;
import com.skillprint.skillprintbackend.Repository.ProfileRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    final private ProfileRepository profileRepository;

    private final CategoryRepository categoryRepository;


    public Runner(ProfileRepository profileRepository , CategoryRepository categoryRepository){
        this.profileRepository  = profileRepository;
        this.categoryRepository  = categoryRepository;

    }
    @Override
    public void run(String... args) throws Exception {
        if(profileRepository.count() == 0){
            profileRepository.saveAll(List.of(
                    new Profile(ProfileType.BUYER,"This profile allows you to obtain the services"),
                    new Profile(ProfileType.SELLER,"This profile allows you to sell the services"),
                    new Profile(ProfileType.ADMINISTRATOR,"This profile allows you to manage the services")
                    )
            );
        }
        if(categoryRepository.count() == 0){
            categoryRepository.saveAll(List.of(
                    new Category("Diseño Gráfico", 85696.0, 9.0),
                    new Category("Desarrollo Web", 76154.0, 5.0),
                    new Category("Redacción de Contenido", 81965.0, 1.0),
                    new Category("Marketing Digital", 42502.0, 1.0),
                    new Category("SEO (Optimización de Motores de Búsqueda)", 72721.0, 1.0),
                    new Category("Asistente Virtual", 57721.0, 2.0),
                    new Category("Traducción de Idioma", 45225.0, 5.0),
                    new Category("Edición de Video", 53199.0, 2.0),
                    new Category("Animación 3D", 94286.0, 5.0),
                    new Category("Programación de Apps Móvil", 94586.0, 3.0),
                    new Category("Consultoría de Negocio", 89153.0, 7.0),
                    new Category("Contabilidad y Finanzas", 30071.0, 4.0),
                    new Category("Diseño de Logotipo", 38161.0, 10.0),
                    new Category("Creación de Podcast", 30555.0, 8.0),
                    new Category("Producción Musical", 51511.0, 9.0)

            ));
        }
    }



}

