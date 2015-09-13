
public class MethodSimplexe {
   
	Object [][]mat;
	
	public MethodSimplexe(Object [][]mat)
	{
		this.mat=mat;
	}
	
	/* 
	 * Retourne la position de la valeur de hors base entante
	*/
	public int RetournPositionVHBE()
	{int pos=2;
		Double max=Double.parseDouble(mat[1][2].toString());
		for( int i=3;i<mat[0].length-2;i++)
		{
			if(mat[1][i].toString().equals("-M"))
				mat[1][i]=-1000;
			if(Double.parseDouble(mat[1][i].toString())>max)
			{ max=Double.parseDouble(mat[1][i].toString());pos=i;}
		}
		
		return pos;
	}
	/* 
	 * Retourne la position de la valeur de base sortante
	*/
	public void remplirRatio()
	{
		double z=0;
		for(int i=2;i<mat.length;i++)
			mat[i][mat[i].length-1]=Double.parseDouble(mat[i][mat[i].length-2].toString())/Double.parseDouble(mat[i][RetournPositionVHBE()].toString());
		  for(int i1=2;i1<mat.length;i1++)
	          
	      { z+=(Double.parseDouble(mat[i1][0].toString())* Double.parseDouble(mat[i1][mat[0].length-2].toString()));}
           mat[1][mat[0].length-1]=z;
		
	}
	public int RetournPositionVBS()
	
	{
		int n=mat[1].length;
		
		Double min=Double.parseDouble(mat[2][mat[0].length-1].toString());
		int pos=2;
	  //  System.out.println(mat[2][mat[2].length-1]);
		
		for(int j=3;j<mat.length;j++)
		{	if(Double.parseDouble(mat[j][mat[0].length-1].toString()) > 0 && Double.parseDouble(mat[j][mat[0].length-1].toString())<min)
		{	
				
				    min=Double.parseDouble(mat[j][mat[0].length-1].toString());pos=j;
		}}
		System.out.println(pos);
	
		return pos;
	}
	
public int rechercheColonneVBS()
	{
		int pos=0;
		for(int i=2;i<mat[0].length-2;i++)
			if(mat[0][i].toString().equals(mat[RetournPositionVBS()][1].toString()))
				{pos= i;break;}
				return pos;
	}
public Object[][] calculTableauInitial()
	{
	Object[][]  tab=new Object[mat.length][mat[0].length];;
		
	      // tab=mat;
		     tab[0]=mat[0];
		     tab[1][0]="Cj";tab[1][mat[0].length-2]="Z=";
		     for(int i=2;i<mat.length;i++)
		          {
			         tab[i][0]=mat[i][0];tab[i][1]=mat[i][1];
		          }
		      int a=RetournPositionVHBE();
		      remplirRatio();
		     int b=RetournPositionVBS();
		      tab[b][0]=mat[1][a];
		      tab[b][1]=mat[0][a];
		     System.out.println("a"+a+" b"+b);
		      double pivot=Double.parseDouble(mat[b][a].toString());
		      //System.out.println(" eee"+mat[0].length+mat.length);
		 
		      for(int i=1;i<mat.length;i++)
	        	{
		        	if  (i!=b )  tab[i][a]=0;
			
		           for(int j=2;j<mat[0].length-1;j++)
		            {
			            if (i==1 && j==mat[0].length-2)  tab[i][j+1]=0;
			             else if(i==b) tab[i][j]=Double.parseDouble(mat[i][j].toString())/pivot;
			 
			              else
			                {  System.out.println("i= "+i+" j= "+j+" "+tab[0][j]);
			               	   System.out.println(Double.parseDouble(mat[i][j].toString())+"-("+Double.parseDouble(mat[i][a].toString())+"*"+Double.parseDouble(mat[b][j].toString())+")/"+pivot);
                                tab[i][j]=Double.parseDouble(mat[i][j].toString())-(Double.parseDouble(mat[i][a].toString())* Double.parseDouble(mat[b][j].toString()))/pivot;
			                }
		             }
		           }
		       double res=0;
				     for(int i1=2;i1<tab.length;i1++)    
				      { 
				    	if(Double.parseDouble(tab[i1][0].toString())>0)
				    	res+=Double.parseDouble(tab[i1][0].toString())*Double.parseDouble(tab[i1][mat[0].length-2].toString());
				    	
				      }
		               tab[1][tab[0].length-1]=res;
		              mat=tab;
		return tab;
		
	}

public static boolean conditionCon(Object[][]mat)

{int i=2; boolean continu=true;int nbre=0;
	while(i<mat[0].length-2)
	{
		if(Double.parseDouble(mat[1][i].toString())<= 0)
			nbre++;
		i++;
	}
	if(nbre==mat[0].length-4)
		continu=false;
return continu;
}

public static void main(String[] args) {
		// TODO Auto-generated method stub

		LectureFich l=new LectureFich();
		l.formerPl();l.mettreSousformeStandrad();
		//MethodSimplexe ms=new MethodSimplexe(l.RetournerTableau());
		// System.out.print(" min ="+ms.RetournPositionVBS());
		 /* for(int i=0;i<l.RetournerTableau().length;i++)
			  {for(int j=0;j<l.RetournerTableau()[0].length;j++)
				  System.out.print("             "+ms.mat[i][j]);System.out.println();}
		  
		  System.out.print(" max ="+ms.mat[1][ms.RetournPositionVHBE()]+"indice "+ms.RetournPositionVHBE());
		 System.out.println( ms.conditionCon(ms.remplirRatio()));
		boolean cond=true;
		 Object [][]tab=l.RetournerTableau();
		 while(cond)
		 {
			
			 MethodSimplexe obj=new MethodSimplexe(tab);
			 for(int i=0;i<tab.length;i++)
			  {
				 for(int j=0;j<tab[0].length;j++)
				 { System.out.print("             "+ obj.calculTableauInitial()[i][j]);}
				 System.out.println();
		   }
			 System.out.println();System.out.println();System.out.println(); 
			 cond=obj.conditionCon( obj.calculTableauInitial());
			tab= obj.calculTableauInitial();
			 
		 }
		// ms.calculTableauInitial(ms.remplirRatio());
		 /*
		 for(int i=0;i<l.RetournerTableau().length;i++)
		  {for(int j=0;j<l.RetournerTableau()[0].length;j++)
			  System.out.print("             "+ ms.calculTableauInitial(ms.remplirRatio())[i][j]);System.out.println();}
		 Object [][] tab=ms.calculTableauSimplex();
		 for(int i=0;i<tab.length;i++)
		 { for(int j=0;j<tab[0].length;j++)
				 System.out.print( "    "+tab [i][j].toString());System.out.println();}
		 
		 System.out.println("\n\n"+ms.rechercheColonneVBS());*/
		 
	}

}
