import java.util.Scanner;

public class ReservationFunction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     int a[]=new int[100];
     while(true) {
     Scanner sc=new Scanner(System.in);
     System.out.println("1번 예약하기 2번 전체 예약 취소 3번 예약 현황");
     int abc=sc.nextInt();
     if(abc==1) {
     System.out.println("1번 예약하기를 선택하셨습니다\n예약할 좌석 번호를 입력해주세요");
     int j=sc.nextInt();
     if(a[j]==1) {
    	 System.out.println("이미 예약된 좌석입니다");
    	 
     }else {
    	  a[j]=1;
    	  System.out.println("좌석이 에약되었습니다");
    	  
     }
     }
     else if(abc==2) {
    System.out.println("2번 전체 예약 취소를 선택하셨습니다(날짜 지날때도 사용,관리자계정에서도 사용)\n예약을 취소합니다"); 	 
    	 for(int ii=0;ii<100;ii++) {
    		 a[ii]=0;
    	 }
     }
     else if(abc==3) {
    	 System.out.println("3번 예약 현황을 선택하셨습니다\n예약 현황");
    	 for(int ii=0;ii<100;ii++) {
    		 if(a[ii]==1) {
    			 System.out.println(ii+"번째 좌석 = 예약되있습니다");
    		 }else {
    			 System.out.println(ii+"번째 좌석 = 비어있습니다.");
    		 }
    	 }
     }
     }     
	}
}
