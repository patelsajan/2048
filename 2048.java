import java.util.Arrays;
import java.util.Scanner;

class TwentyFortyEight {
    static int[][] matrix = new int[4][4];
    static boolean gameover = false;
    static String name;
    static Scanner s = new Scanner(System.in);
    static int[][] prevmatrix =matrix;
    public static void main(String[] args)  {
        System.out.println("enter your name to start : ");
        name = s.nextLine();
        if (name.matches("[a-zA-Z ]+")) {
            start();
        } else {
            System.out.println("enter valid name(only letters and spaces allowed).");
        }
    }
    public static void start()  {
        addRandom();
        while(!gameover){
		clearScreen();
            addRandom();
            show();
            System.out.println("enter 2 to move DOWN, 8 to move UP, 6 to move RIGHT and 4 to move LEFT\n[OR]\n q to quit.");
            String c = s.nextLine();
            switch(c){
                case "2":
                    moveDown();
                    break;
                case "8":
                    moveUp();
                    break;
                case "6":
                    moveRight();
                    break;
                case "4":
                    moveLeft();
                    break;
                case "q":
                    gameover = true;
                    break;
                default:
                    System.out.println("please enter valid option:");
                    break;
            }
        }
        System.out.println("!!!GAME OVER!!!");
        s.close();

    }
    public static void show(){
        for(int i =0;i<matrix.length;i++){
            System.out.println("------------------------------");
            System.out.print("|");
            for(int j = 0;j<matrix.length;j++){
                System.out.print(colorcoded(matrix[i][j])+"|");
            }
            System.out.println("\n------------------------------");
        }
    }
    public static void addRandom(){
        int numberofzeroes = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                if(matrix[i][j]==0){
                    numberofzeroes++;
                }
            }
        }
        if(numberofzeroes==0){
            gameover =true;
        }else{
            int randomnumber =(int) (Math.random()*numberofzeroes+1);
            int reached = 0;
            for(int m =0;m<matrix.length;m++){
                for(int n =0;n<matrix.length;n++){
                    if(matrix[m][n]==0){
                        reached ++;
                        if(reached==randomnumber){
                            matrix[m][n] = 2;
                            break;
                        }
                    }
                }
            }    
        }
    }
    public static void moveDown(){
        slide();
        combine();
        slide();
        rotate(360);
    }
    public static void moveUp(){
        rotate(180);
        slide();
        combine();
        slide();
        rotate(180);
    }
    public static void moveRight(){
        rotate(90);
        slide();
        combine();
        slide();
        rotate(270);
    }
    public static void moveLeft(){
        rotate(270);
        slide();
        combine();
        slide();
        rotate(90);
    }
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
    public static void slide(){
        for(int i=0;i<matrix.length-1;i++){
            for (int j =0;j<matrix.length;j++){
                if(matrix[i+1][j]==0){
                    matrix[i+1][j]=matrix[i][j];
                    matrix[i][j]=0;
                }
                if(matrix[i+1][j]==matrix[i][j]){
                }
            }
        }
    }
    public static void combine(){
        for(int i= matrix.length-1;i>0;i--){
            for(int j =0;j<matrix.length;j++){
                if(matrix[i][j]==matrix[i-1][j]){
                    matrix[i][j]+=matrix[i-1][j];
                    matrix[i-1][j]=0;
                }
            }
        }
    }
    public static void rotate(int angle){
        for(int i =0;i<angle/90;i++){
           matrix = transpose(matrix);
            matrix = flip(matrix);
        }
    }
    public static int[][] transpose(int[][] arr){
        int[][] newarr = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                newarr[j][i] = arr[i][j];
            }
        }        
        return newarr;
    }
    public static int[][] flip(int[][] arr){
        int[][] newarr = {{0,0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0,0}};
        for(int i=0;i<arr.length;i++){
            for(int j =0;j<arr.length;j++){
                newarr[i][(arr.length-1)-j]= arr[i][j];
            }
        }
        return newarr;
    }
    public static String colorcoded(int number){
         // /colored background;
        final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
        final String ANSI_RED_BACKGROUND = "\u001B[41m";
        final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
        final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
        final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
        final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
        final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
        final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
        //colored text.
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLACK = "\u001B[30m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";
        switch(number){
            case 0:
                return ANSI_WHITE_BACKGROUND+ANSI_BLACK+"xxxxx"+ANSI_RESET;
            case 2:
                return ANSI_RED_BACKGROUND+ANSI_BLACK+"    2"+ANSI_RESET;
            case 4:
                return ANSI_GREEN_BACKGROUND+ANSI_BLACK+"    4"+ANSI_RESET;
            case 8:
                return ANSI_YELLOW_BACKGROUND+ANSI_BLACK+"    8"+ANSI_RESET;
            case 16:
                return ANSI_BLUE_BACKGROUND+"   16"+ANSI_RESET;
            case 32:
                return ANSI_CYAN_BACKGROUND+ANSI_BLACK+"   32"+ANSI_RESET;
            case 64:
                return ANSI_WHITE_BACKGROUND+ANSI_BLACK+"   64"+ANSI_RESET;
            case 128:
                return ANSI_GREEN_BACKGROUND+ANSI_BLACK+"   64"+ANSI_RESET;
            case 256:
                return ANSI_PURPLE_BACKGROUND +"  256"+ANSI_RESET;
            case 512:
                return ANSI_RED+"  512"+ANSI_RESET;
            case 1024:
                return ANSI_YELLOW+" 1024"+ANSI_RESET;
            case 2048:
                return ANSI_BLUE_BACKGROUND+" 2048"+ANSI_RESET;
            default:
                return Integer.toString(number);
        }
    }
    


}
