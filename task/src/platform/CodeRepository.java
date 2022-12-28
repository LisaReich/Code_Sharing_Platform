package platform;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long> {

    Optional<Code> findByUuid(String uuid);

    @Override
    List<Code> findAll();
    @Override
    List<Code> findAllById(Iterable<Long> longs);
}
