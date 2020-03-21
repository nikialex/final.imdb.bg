package actors;

public interface ActorService {

    Actor create(String fullName);

    Actor getByName(String fullName);
}
