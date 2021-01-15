package parse;

import model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class parseData {
    public static void fetchuserdata(ArrayList<user> nuser_data) throws FileNotFoundException, IOException {
        String path = "/home/tishyagoyal/Documents/user.csv";
        BufferedReader br = new BufferedReader(new FileReader(path));

        StringTokenizer token;
        String line;

        while ((line = br.readLine()) != null) {
            token = new StringTokenizer(line, "|");
            user temp= new user();
            temp.id = Integer.parseInt(token.nextToken());
            temp.age = Integer.parseInt(token.nextToken());
            temp.gender = token.nextToken();
            temp.occ = token.nextToken();
            temp.zip = token.nextToken();
            nuser_data.add(temp);
        }

        //to check code is working or not
//        for (int j = 0; j < nuser_data; j++) {
//            System.out.println(nuser_data.get(j).id + " " + nuser_data.get(j).gender);
//        }


    }

    public static void fetchratingdata(ArrayList<rating> rate_data) throws FileNotFoundException, IOException {
        File path = new File("/home/tishyagoyal/Documents/ratings.data");
        Scanner scan1=new Scanner (path);
        while (scan1.hasNext()) {
                rating temp= new rating();
            temp.id = Integer.parseInt(scan1.next());
            temp.movieid = Integer.parseInt(scan1.next());
            temp.rate = Integer.parseInt(scan1.next());
            temp.time = Integer.parseInt(scan1.next());
           rate_data.add(temp);
        }

        //to check code is working or not
//        for (int j = 0; j < rate_data.size(); j++) {
//            System.out.println(rate_data.get(j).id + " " + rate_data.get(j).rate);
//        }
    }

    public static void fetchMoviedata(ArrayList<movie> movie_data) throws FileNotFoundException, IOException {
        String path = "/home/tishyagoyal/Documents/movie.csv";
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values=line.split("\\|");
            movie temp = new movie();
            temp.id = Integer.parseInt(values[0]);
            temp.title =values[1];
            temp.date = values[2];
            temp.imdb = values[4];
            for (int j = 0; j <19; j++) {
                temp.genre.add(Integer.parseInt(values[5+j]));
            }
            movie_data.add(temp);
        }

        //checking code is working or not
//        for (int j = 0; j < movie_data.size(); j++) {
//            System.out.println(movie_data.get(j).id + " " + movie_data.get(j).title);
//        }
    }


public static void fetchgenredata(genredata[] genre_data) throws FileNotFoundException, IOException {
    String path = "/home/tishyagoyal/Documents/genre.csv";
    BufferedReader br = new BufferedReader(new FileReader(path));

    StringTokenizer token;
    String line;
    int i = 0;
    int k = 0;
    while ((line = br.readLine()) != null && i < 19) {
        token = new StringTokenizer(line, "|");

        genre_data[i] = new genredata();
        genre_data[i].genre = token.nextToken();
        genre_data[i].genreid = Integer.parseInt(token.nextToken());

        i++;
    }


//        for (int j = 0; j < 19; j++) {
//            System.out.println(genre_data[j].genre + " " + genre_data[j].genreid);
//        }


}

    public static int[] MostActiveUser_Movie(ArrayList<rating>rate_data,int NumUsers,int NumMovies) {
        int[] UserFreq=new int[NumUsers+2];
        int[] MovieFreq=new int[NumMovies+2];
        int MaxUser=0,MaxMovie=0,MaxUserId=0,MaxMovieId=0;
        for(int i=0;i<rate_data.size();i++)
        {
            UserFreq[rate_data.get(i).id]++;
            MovieFreq[rate_data.get(i).movieid]++;
        }
        for(int i=1;i<=NumUsers;i++)
        {
            if(MaxUser<UserFreq[i])
            {
                MaxUser=UserFreq[i];
                MaxUserId=i;
            }
        }
        for(int i=1;i<=NumMovies;i++)
        {
            if(MaxMovie<MovieFreq[i])
            {
                MaxMovie=MovieFreq[i];
                MaxMovieId=i;
            }
        }
        int[] Obj=new int[2];
        Obj[0]=MaxUserId;
        Obj[1]=MaxMovieId;
        return Obj;
    }
     public static int  mostwatchedgenre(ArrayList<rating> rate_data,ArrayList<movie> movie_data) {
        int[] genreFreq=new int[19];
         for (int i = 0; i < rate_data.size(); i++) {
             int mid=rate_data.get(i).movieid;
             for(int j=0;j<19;j++){
                 if(movie_data.get(mid-1).genre.get(j)==1){
                     genreFreq[j]++;
                 }
             }
         }
         int MaxGenre=0;
         int MaxGenreId=0;
         for(int i=0;i<19;i++){
             if (MaxGenre < genreFreq[i]) {
                 MaxGenre=genreFreq[i];
                 MaxGenreId=i;
             }
         }
         return MaxGenreId;
     }

    public static int getHighestRatedGenre(ArrayList<rating> rate_data, ArrayList<movie> movie_data) {
        int[] GenreFreq=new int[19];
        for(int i=0;rate_data.get(i).rate==5;i++)
        {
            int mid=rate_data.get(i).movieid;
            for(int j=0;j<19;j++)
            {
                if(movie_data.get(mid-1).genre.get(j)==1)
                {
                    GenreFreq[j]++;
                }
            }
        }
        int MaxGenre=0,MaxGenreId=0;
        for(int i=0;i<19;i++)
        {
            if(MaxGenre<GenreFreq[i])
            {
                MaxGenre=GenreFreq[i];
                MaxGenreId=i;
            }
        }
        return MaxGenreId;
    }

}





