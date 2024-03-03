class questao15{
	//verifica se existe so vogal na string
	static boolean isVogal(String x,int y){
		if(y<x.length()){
			if(x.charAt(y) != 'a' && x.charAt(y) != 'e' && x.charAt(y) != 'i' && x.charAt(y) != 'o' && x.charAt(y) != 'u' &&
			x.charAt(y) != 'A' && x.charAt(y) != 'E' && x.charAt(y) != 'I' && x.charAt(y) != 'O'&& x.charAt(y) != 'U'){
				return false;
			}else{
				y++;
				return isVogal(x,y);
			}
		}else{
			return true;
		}
	}
	//verifica se existe so consoante na string
	static boolean isConsoante(String x,int y){
		if(y<x.length()){
            if (x.charAt(y) != 'b' && x.charAt(y) != 'c' && x.charAt(y) != 'd' &&
                x.charAt(y) != 'f' && x.charAt(y) != 'g' && x.charAt(y) != 'h' && 
                x.charAt(y) != 'j' && x.charAt(y) != 'k' && x.charAt(y) != 'l' && 
                x.charAt(y) != 'm' && x.charAt(y) != 'n' && x.charAt(y) != 'p' && 
                x.charAt(y) != 'q' && x.charAt(y) != 'r' && x.charAt(y) != 's' && 
                x.charAt(y) != 't' && x.charAt(y) != 'v' && x.charAt(y) != 'w' && 
                x.charAt(y) != 'x' && x.charAt(y) != 'y' && x.charAt(y) != 'z' &&
                x.charAt(y) != 'B' && x.charAt(y) != 'C' && x.charAt(y) != 'D' && 
                x.charAt(y) != 'F' && x.charAt(y) != 'G' && x.charAt(y) != 'H' && 
                x.charAt(y) != 'J' && x.charAt(y) != 'K' && x.charAt(y) != 'L' && 
                x.charAt(y) != 'M' && x.charAt(y) != 'N' && x.charAt(y) != 'P' && 
                x.charAt(y) != 'Q' && x.charAt(y) != 'R' && x.charAt(y) != 'S' && 
                x.charAt(y) != 'T' && x.charAt(y) != 'V' && x.charAt(y) != 'W' && 
                x.charAt(y) != 'X' && x.charAt(y) != 'Y' && x.charAt(y) != 'Z') {
                return false;
			}else{
				y++;
				return isConsoante(x, y);
			}
		}else{
			return true;
		}
	}
	//verifica se existe so int na string
	static boolean isInt(String x,int y){
		if(y<x.length()){
			if(x.charAt(y)!='0'&&x.charAt(y)!='1'&&x.charAt(y)!='2'&&x.charAt(y)!='3'&&x.charAt(y)!='4'&&
			   x.charAt(y)!='5'&&x.charAt(y)!='6'&&x.charAt(y)!='7'&&x.charAt(y)!='8'&&x.charAt(y)!='9'){
				return false;
			}else{
				y++;
				return isInt(x, y);
			}
		}else{
			return true;
		}
	}
	//verifica se existe so numero real
	static boolean isReal(String x, int y){
		if(y<x.length()){
			if(x.charAt(0)!='-' &&x.charAt(y)!='0' && x.charAt(y)!='1' && x.charAt(y)!='2' && x.charAt(y)!='3' && x.charAt(y)!='4' &&
			   x.charAt(y)!='5' && x.charAt(y)!='6' && x.charAt(y)!='7' && x.charAt(y)!='8' && x.charAt(y)!='9' && x.charAt(y)!='.' && x.charAt(y)!=','){
				return false;
			}else{
				y++;
				return isReal(x, y); 
			}
		}else{
			return true;
		}
	}


	public static void main(String[] args){
		String x;
		boolean fim=false;
		int y=0;
		//enquanto fim == false o programa continuara rodando
		while(fim==false){
			x=MyIO.readLine();		
			//verifica se a entrada = FIM	
			if(x.length()==3&&x.charAt(0)=='F'&&x.charAt(1)=='I'&&x.charAt(2)=='M'){
				fim=true;//se sim fim=true
			}else{
				boolean vogal= isVogal(x,y);
				if(vogal==true){
					System.out.print("SIM ");
				}else{
					System.out.print("NAO ");
				}
				boolean Consoante= isConsoante(x,y);
				if(Consoante==true){
					System.out.print("SIM ");
				}else{
					System.out.print("NAO ");
				}
				boolean Int= isInt(x,y);
				if(Int==true){
					System.out.print("SIM ");
				}else{
					System.out.print("NAO ");
				}
				boolean Real= isReal(x,y);
				if(Real==true){
					System.out.print("SIM\n");
				}else{
					System.out.print("NAO\n");
				}
			}
		}
	}
}
