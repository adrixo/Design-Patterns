// In software engineering, the adapter pattern is a software design pattern (also known as wrapper, an alternative naming shared with the decorator pattern) that allows the interface of an existing class to be used as another interface.[1] It is often used to make existing classes work with others without modifying their source code.

public class AdapterWrapperPattern {

	public static void main(String args[]){
		Guitar eGuitar = new ElectricGuitar();
		eGuitar.onGuitar();
		eGuitar.offGuitar();
		Guitar eAGuitar = new ElectricAcousticGuitar();
		eAGuitar.onGuitar();
		eAGuitar.offGuitar();
	}


	public abstract class Guitar{
		abstract public void onGuitar();
		abstract public void offGuitar();
	}

	public class ElectricGuitar extends Guitar{

		public void onGuitar() {
			System.out.println("Playing Guitar");
		}

		public void offGuitar() {
			System.out.println("I'm tired to play the guitar");
		}
	}

	/**
	 * Class to Adapter/Wrapper
	 */
	public class AcousticGuitar{

		public void play(){
			System.out.println("Playing Guitar");
		}
		public void leaveGuitar(){
			System.out.println("I'm tired to play the guitar");
		}
	}

	/**
	 * we Adapter/Wrapper AcousticGuitar into
         * ElectricAcousticGuitar to adapt into the GuitarModel
	 */
	public class ElectricAcousticGuitar extends Guitar{
		AcousticGuitar acoustic = new AcousticGuitar();

		public void onGuitar() {
			acoustic.play();
		}

		public void offGuitar() {
			acoustic.leaveGuitar();
		}
	}
}
