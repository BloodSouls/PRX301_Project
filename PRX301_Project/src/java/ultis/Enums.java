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
    COMPLETE(0, "Hoàn thiện"), 
    NOT_COMPLETED(1, "Đang phát hành"), 
    DROP(2, "Ngừng nữa đường");
    
    private final int value;
    private final String name;

    private BookStatus(final int value, final String name) {
      this.value = value;
      this.name = name;
    }
    
    public int getValue() {
      return this.value;
    }
    
    public String getName() {
      return this.name;
    }
  }
  
}
