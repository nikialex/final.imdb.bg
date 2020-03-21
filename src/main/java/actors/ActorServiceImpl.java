package actors;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@Log4j2
public class ActorServiceImpl implements ActorService {

//    private final ActorConverter extraConverter;
//    private final ExtraRepository extraRepository;

    @Override
    public Actor create(String fullName) {
        log.info("Create actor BEGIN: {}", fullName);

        if (isNull(fullName)) {
            return null;
        }
        return null;
    }

    @Override
    public Actor getByName(String fullName) {
        return null;
    }
}
