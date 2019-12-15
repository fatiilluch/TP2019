package domain;

public enum Color {
	   ROJO ("Rojo"),
	   AZUL ("Azul"),
	   AMARILLO ("Amarillo"),
	   BLANCO ("Blanco"),
	   NEGRO("Negro"),
	   ROSA("Rosa"),
	   VERDE ("Verde"),
	   CELESTE ("Celeste"),
	   GRIS ("Gris"),
	   BORDO ("Bordo"),
	   Nulo ("N/A");
	
	private final String name;
	
	private Color(String color) {
		this.name = color;
	}
	
	 public String toString() {
	       return this.name;
	 }

	 public String getColor(){return this.toString();}

	 public String getColorIngles(){
		if(this.toString() == "Rojo"){
			return "red";
		}
		if(this.toString() == "Azul"){
			 return "blue";
		 }
		if(this.toString() == "Amarillo"){
			 return "yellow";
		 }
		if(this.toString() == "Blanco"){
			 return "white";
		 }
		if(this.toString() == "Negro"){
			 return "black";
		 }
		if(this.toString() == "Rosa"){
			 return "pink";
		 }
		if(this.toString() == "Verde"){
			 return "green";
		 }
		if(this.toString() == "Celeste"){
			 return "lightBlue";
		 }
		if(this.toString() == "Gris"){
			 return "grey";
		 }

		if(this.toString() == "Bordo"){
			 return "maroon";
		 }
		else return null;
	 }
}
