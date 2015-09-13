import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;


public class LectureFich {

  private 	 String nomFich;
             BufferedReader sortie;
              ArrayList tab;
             static ArrayList <ArrayList> mat = new ArrayList<ArrayList>();
              /*                              */
             /*                              */
             
     public LectureFich()
     {
    	  try {
			sortie=new BufferedReader(new FileReader(Simplexe.nomFich));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
     }
  
     public void formerPl()
  
     {
    	 String ligne;
    	 tab=new ArrayList();
    	 int i=1;
    	 try {
			    ligne=sortie.readLine();
				fonctionObjectif(ligne);
				ligne=sortie.readLine();
				while(ligne!=null)
		    	{
					contranitePL(ligne,i,tab);
		    		ligne=sortie.readLine();
		    		i++;
		    		
		    	}
		   } 
    	 catch (IOException e) {
			
			e.printStackTrace();
		}
    	 
     }
     
     
    public void fonctionObjectif(String ligne)

     {
    	 ArrayList var =new ArrayList(); 
    	 ArrayList coef =new ArrayList();
         var.add("Coef");
         var.add("VB");
         coef.add("Cj");
         coef.add("");
        
         ligne=ligne.substring(4,ligne.length());
         
         // Retourner la liste de varaible et leur coefficient
         DegagerVarCoef( coef,var,ligne);
     	 
     	 // Cas de Minimisation 
     	 if (ligne.substring(0,3).equals("Min") )
     		{
     		
     		 for(int j=0;j<coef.size();j++)
     			if(Double.parseDouble(coef.get(j).toString())!= 0)
     			 coef.set(j,Double.parseDouble(coef.get(j).toString())*(-1));
     		}
     		mat.add(var)  ;
     		mat.add(coef);
     }
  
	public void contranitePL(String ligne,int i,ArrayList tab)
	{
		
	     StringTokenizer st=new StringTokenizer(ligne," [=]|[<]|[>]",true);
		  String s=st.nextToken();
		  String delim=st.nextToken();
		//  System.out.println(delim);
		  Double val=Double.parseDouble(st.nextToken());
		//  System.out.println(val);
		  ArrayList coef=new ArrayList();
          ArrayList var=new ArrayList();
          ArrayList cont=new ArrayList();
         
          
		 if(val>0)
		  {
			 tab.add(val);
			 contrainteSecondMembrePositif( delim, s, cont, coef, var, i);
		  } 
		  if(val<0)
		  {
			 tab.add(-1*val) ;
			String ss="";
			for(int j=0;j<s.length();j++)
			{
				if(s.charAt(j)=='+') ss+=ss.valueOf('-');
				
				else if(s.charAt(j)=='-') ss+=ss.valueOf('+');
				else
					ss+=ss.valueOf(s.charAt(j));
			}
			
			 System.out.println( ",jhjh  "+ss);
			
			 if(delim.equals("<")) contrainteSecondMembrePositif(">", ss, cont, coef, var, i);
			 if(delim.equals(">")) contrainteSecondMembrePositif("<", ss, cont, coef, var, i);
			 System.out.println(delim.length()+delim);
			 
           // if(ligne.contains("[=]"))
		  } 	
	}
     
	public void contrainteSecondMembrePositif(String delim,String s,ArrayList cont,ArrayList coef,ArrayList var,int i)
	{
		  switch (delim)
          {
              case "<":{
            	            cont.add(0,0);cont.add(1,"e"+i);
            	            s= s.concat("+1*e"+i);
    	                    mat.get(0).add("e"+i);
    	                    mat.get(1).add(0);
            	 
            	  
    	                     DegagerVarCoef(coef,var,s);
    	                     for(int j=2;j<mat.get(0).size();j++)
    	                        { 
    	                	       boolean trouve=false;
    	                	       for(int k=0;k<var.size();k++) 
		    	                      { 
    	                		       if(var.get(k).equals(mat.get(0).get(j)))
    	                             	{ 
    	                			     cont.add(j,coef.get(k));trouve=true;break;
    	                			     }
    	                		 
    	                	           }
    	                	         if(!trouve) cont.add(j,0);
    	                		 
    	                           }
    	               /*  System.out.println();
    	              for(int k=0;k<mat.get(0).size();k++)
    	              	 System.out.print (mat.get(0).get(k)+"  "+cont.get(k));*/
    	                	 mat.add(cont);
    	                      break;
                            }
              case "=":{
            	                   cont.add(0,"-M");cont.add(1,"a"+i);              
            	                   s= s.concat("+1*a"+i);
                                   mat.get(0).add("a"+i);
                                   mat.get(1).add("-M");
                                   DegagerVarCoef(coef,var,s);
                                  for(int j=2;j<mat.get(0).size();j++)
    	                        { 
    	                	       boolean trouve=false;
    	                	       for(int k=0;k<var.size();k++) 
		    	                      { 
    	                		       if(var.get(k).equals(mat.get(0).get(j)))
    	                             	{ 
    	                			     cont.add(j,coef.get(k));trouve=true;break;
    	                			     }
    	                		 
    	                	           }
    	                	         if(!trouve) cont.add(j,0);
    	                        }
                                  mat.add(cont);   
            	       break;
                  }
              case">":{
            	              cont.add(0,"-M");cont.add(1,"a"+i);  
            	              //cont.add(0,0);cont.add(1,"e"+i);     
                              s= s.concat("-1*e"+i+"+1*a"+i);
                               mat.get(0).add("e"+i);
                               mat.get(1).add("0");
                               mat.get(0).add("a"+i);
                               mat.get(1).add("-M");
                  DegagerVarCoef(coef,var,s);
                 for(int j=2;j<mat.get(0).size();j++)
               { 
       	       boolean trouve=false;
       	       for(int k=0;k<var.size();k++) 
                     { 
       		       if(var.get(k).equals(mat.get(0).get(j)))
                    	{ 
       			     cont.add(j,coef.get(k));trouve=true;break;
       			     }
       		 
       	           }
       	         if(!trouve) cont.add(j,0);
               }
                 mat.add(cont);    
            	  break;
                      }
          }
  }
	
	public void DegagerVarCoef(ArrayList coef,ArrayList var,String ligne)
	{int i=0;
	   StringTokenizer st=new StringTokenizer(ligne,"+ - ",true); 
        int n=st.countTokens();
        //System.out.println("n= "+n);
         String c="",s;
        
		 while(i<n)
     	 {
     		  s=st.nextToken();
     		 System.out.println(i+" chai: "+s);
     		   if(s.equals("+"))
     		   { i++;}
     		   else  if(s.equals("-"))
     		  {  c="-";
     			 i++;
     			 }
     		  
     		  else
     		  {
     			   if(c.equals("-"))
     				{s=c.concat(s) ;}
     			  System.out.println(i+" chai: "+s);
     		      String []chaine =s.split("\\*");
     		      coef.add(Double.parseDouble(chaine[0]));  var.add(chaine[1]);
     	         i++;c="";
     	          } 
     	  }
	}
    
	public void  mettreSousformeStandrad()
	{
		
		for(int i=0;i<mat.size();i++)
			if(mat.get(i).size()< mat.get(0).size())
			{
				for(int j=mat.get(i).size()+1;j<=mat.get(0).size();j++)
					mat.get(i).add(0);
			}
		mat.get(0).add("Val");mat.get(0).add("Rat");
		mat.get(1).add("Z=");mat.get(1).add("");
		for(int i=0;i<tab.size();i++)
		  {  mat.get(i+2).add(tab.get(i));mat.get(i+2).add(0);}
		
		//Object [][]tab=new Object[mat.size()][mat.get(0).size()];
		
		
	}
		 
	public Object [][] RetournerTableau()
	{ 
		
		Object[][]tab=new Object[mat.size()][mat.get(3).size()];
		//System.out.println(mat.size()+"   :"+mat.get(3).size());
		for(int i=0;i<mat.size();i++)
			for(int j=0;j<mat.get(i).size();j++)
				tab[i][j]=mat.get(i).get(j);
		
		return tab;
	}
	
    public Object [][] verifierSBR0(Object[][]tab)
    {
    	
    	for(int i=1;i<tab.length-1;i++)
    		
    	{	for(int j=1;j<tab[0].length-4;j++)
    	   {
    		   if(tab[i][1]==tab[0][j]&& tab[1][j].toString()!="0")
    		   {
    			   tab[1][j]= tab[1][j].toString()+tab[i][j].toString()+"*M" ;
    		   }
    	   } }
    	return tab;
    	
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//LectureFich l=new LectureFich("C:\\Users\\Assoum\\Documents\\equa.txt");
		//l.formerPl();
	   // l.mettreSousformeStandrad();//erreur à corriger
		
		//ArrayList <ArrayList> mat = new ArrayList<ArrayList>();
		
		/*for(int i=0;i<mat.size();i++)
			{for(int j=0;j<mat.get(i).size();j++)
				System.out.print("  |"+l.verifierSBR0(l.mettreSousformeStandrad())[i][j]);System.out.println();}
		System.out.print(mat.size());
	    
	  */
	}

}
