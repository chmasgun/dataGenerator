import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		double mean=0.0;
		double range=1;
		int dataSize=100;
		int dimension=2;
		try {
			Random ran=new Random();

			
			BufferedWriter rd=new BufferedWriter(new FileWriter("data2.txt"));
			
			if(dimension==1){ 
				//Creates data points with one dimension. This code generates numbers
				//according to the normal distribution
				while(dataSize>0){
					double X=normalDistribution(mean,4,ran);
					
					rd.write(X+"\n");
					dataSize--;
				}
				rd.close();

				


			}
			else if(dimension==2){
				//Creates data points with two dimensions. This code generates 
				// (x,x+3) points with some noise coefficient
				while(dataSize>0){
					double X=randomUniform(mean,range,ran);
					rd.write(X+" "+lineWithMandX0Noised(X,1,3,ran,0.3)+"\n");
					dataSize--;
				}
				rd.close();
			}
			else{
				System.exit(0);
			}


			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private static double randomUniform(double mean, double range,Random ran){
		return mean+(ran.nextDouble()-0.5)*range;
	}

	private static double centrifugedPoints(double mean, double range,Random ran){
		double a=ran.nextDouble();
		return mean+((a-0.5)/Math.abs(a-0.5))*(1-Math.pow(ran.nextDouble(),2))*range/2;
	}
	private static double funcOfX(double X,double range,Random ran,int rat){
		double a=ran.nextDouble();
		return X+((a-0.5)/Math.abs(a-0.5))*ran.nextDouble()*range/rat;
	}

	private static double lineWithMandX0(double X,double m,double yInter){
		return m*X+yInter ;

	}
	private static double lineWithMandX0Noised(double X,double m,double yInter,Random rand,double noiseFactor){
		double shift=(normalDistribution(0,1,rand))*m*noiseFactor;
		return m*X+yInter+shift ;

	}

	private static double normalDistribution(double mean, double variance,Random rand){
		
		double sum=mean;
		for(int i=0;i<12;i++){
			sum+=rand.nextDouble();
		}
		sum-=6;
		return sum*Math.sqrt(variance);
	}


	private static double CDF(double x){

		if(x>4){
			return 1;
		}
		if(x<-4){
			return 0;
		}
		double prob=x;
		double val=x;
		double coeff1=1/Math.sqrt(2*Math.PI);
		double coeff2=Math.pow(Math.E,-(x*x)/2);
		for(int i=1; i<40;i++){
			val=(val*x*x/(2*i+1));
			prob+=val;

		}
		
		return 0.5+prob*coeff1*coeff2;

	}
	private static double CDF(double x,double mean, double variance){
		return CDF((x-mean)/Math.sqrt(variance));

	}
	

}
