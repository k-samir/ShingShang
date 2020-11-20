package model;


/**
 * Tuple<S1, S2> class : structure created to return a boolean and a string at the same time
 * used in MoveValidator for exemple 
 * @author Samir KAMAR
 *
 */
public class Tuple<S1, S2> {
	
	  private Boolean first;
	  private String second;
	  
	    public Tuple(Boolean first, String second){
	        this.first = first;
	        this.second = second;
	    }

		public Boolean getFirst() {
			return first;
		}

		public void setFirst(Boolean first) {
			this.first = first;
		}

		public String getSecond() {
			return second;
		}

		public void setSecond(String second) {
			this.second = second;
		}
	    

}
