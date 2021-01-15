package recommend_system;

import model.*;
import parse.parseData;
import java.lang.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class movie_rater {

    public static void main(String[] args) throws IOException {
//            user[] user_data = new user[1000];                 //initially i make arrays of each class
        ArrayList<user> user_data = new ArrayList<>();            //then i switch to arraylist(so that there will be no problem of resizing)
        parseData obj1 = new parseData();
        obj1.fetchuserdata(user_data);

        ArrayList<rating> rate_data = new ArrayList<>();
        parseData obj2 = new parseData();
        obj2.fetchratingdata(rate_data);
//        Collections.sort(rate_data, rate_data.comp);

        ArrayList<movie> movie_data = new ArrayList<>();
        parseData obj3 = new parseData();
        obj3.fetchMoviedata(movie_data);

        genredata genre_data[] = new genredata[20];
        parseData obj4 = new parseData();
        obj4.fetchgenredata(genre_data);


        Map<Integer, String> map1 = new HashMap<Integer, String>();    //map for storing movie and movieid
        for (int i = 0; i < movie_data.size(); i++) {
            map1.put(movie_data.get(i).id, movie_data.get(i).title);
        }

        Map<Integer, String> map2 = new HashMap<Integer, String>();     //map for storing genre and genreid
        for (int i = 0; i < 19; i++) {
            map2.put(genre_data[i].genreid, genre_data[i].genre);
        }

//        Map<Integer,Integer> map3 = new HashMap<Integer,Integer>();     //map for storing genre and genreid
//        for (int i = 0; i < 19; i++) {
//            map2.put(genre_data[i].genre,0);
//        }


        int[] p = obj4.MostActiveUser_Movie(rate_data, user_data.size(), movie_data.size());
        System.out.println("Most Active User: " + p[0]);
        System.out.println("Most Watched Movie: " + p[1]);


        int MaxGenreId = obj4.mostwatchedgenre(rate_data, movie_data);
        System.out.println("Most watched genre: " + map2.get(MaxGenreId));

        Collections.sort(rate_data, rating.comp);

        int HighestRatedGenre = obj4.getHighestRatedGenre(rate_data, movie_data);
        System.out.println("Highest Rated Genre: " + map2.get(HighestRatedGenre));


//        Map<Integer,Integer> map3 = new HashMap<Integer,Integer>();
        int TopMovieByGenre[]=obj4.getTopMovieByGenre(rate_data, movie_data);
        for (int i = 0; i < 19 ; i++) {
            System.out.println("top movie of genre " + map2.get(i) + " is " +map1.get(TopMovieByGenre[i])  );
        }
    }
    }


