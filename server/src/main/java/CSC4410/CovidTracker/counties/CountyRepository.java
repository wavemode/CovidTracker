package CSC4410.CovidTracker.counties;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountyRepository extends CrudRepository<CountyData,String> {
}
