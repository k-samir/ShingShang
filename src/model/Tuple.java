package model;

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
