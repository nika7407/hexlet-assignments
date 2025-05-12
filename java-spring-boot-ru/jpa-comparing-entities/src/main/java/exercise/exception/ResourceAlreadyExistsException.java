package exercise.exception;

// BEGIN
public class ResourceAlreadyExistsException extends RuntimeException {
  public ResourceAlreadyExistsException(String text){
      super(text);
  }
}
// END
