package fr.sodifrance.hackatoncaf.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import fr.sodifrance.hackatoncaf.model.Caf;

@RepositoryRestResource(collectionResourceRel = "caf", path = "caf")
public interface CafRepository extends CrudRepository<Caf, Long> {

    List<Caf> findByDep(@Param("dep") String dep);
}


