
public class Teste {

	
	public static void main(String[] args) {
		String teste = "KADNKASD";
		String teste1 = "123546";
		try {
			Double.parseDouble("6565asd");
		}catch(NumberFormatException e) {
			
		}
		System.out.println(teste.matches("[0-9]*"));
		System.out.println(teste1.matches("[0-9]*"));

	}
}
