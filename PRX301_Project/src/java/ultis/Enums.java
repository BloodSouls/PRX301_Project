package ultis;

public class Enums {
  
  public enum PersonType {
    NORMAL_USER(1), ADMIN(2);

    private final int value;
    
    private PersonType(final int value) {
      this.value = value;
    }
    
    public int getValue() {
      return this.value;
    }
  }
  
  public enum BookStatus {
    COMPLETE(0), NOT_COMPLETED(1), DROP(2);
    
    private final int value;

    private BookStatus(final int value) {
      this.value = value;
    }
    
    public int getValue() {
      return this.value;
    }
  }
  
}
