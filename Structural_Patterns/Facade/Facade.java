//facade is an object that serves as a front-facing interface masking more complex underlying or structural code.

class CPU {
  public void freeze() { ... }
  public void jump(long position) { ... }
  public void execute() { ... }
}

class HardDrive {
  public byte[] read(long lba, int size) { ... }
}

class Memory {
  public void load(long position, byte[] data) { ... }
}

/* Facade */

class ComputerFacade {
  private final CPU processor;
  private final Memory ram;
  private final HardDrive hd;

  public ComputerFacade() {
    this.processor = new CPU();
    this.ram = new Memory();
    this.hd = new HardDrive();
  }

  public void start() {
    processor.freeze();
    ram.load(BOOT_ADDRESS, hd.read(BOOT_SECTOR, SECTOR_SIZE));
    processor.jump(BOOT_ADDRESS);
    processor.execute();
  }
}

/* Client */

class You {
  public static void main(String[] args) {
    var computer = new ComputerFacade();
    computer.start();
  }
}
