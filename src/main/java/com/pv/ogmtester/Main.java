package com.pv.ogmtester;

import com.pv.ogmtester.domain.Actor;
import com.pv.ogmtester.domain.Movie;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

/**
 * Created by Panos on 25-Dec-15.
 */
public class Main {

    public static void main(String... args) {

        //Set up the Session
        SessionFactory sessionFactory = new SessionFactory("com.pv.ogmtester.domain");

        Session session = sessionFactory.openSession("http://localhost:7474", "neo4j", "1234");

        Movie movie = new Movie("The Matrix", 1999);

        Actor keanu = new Actor("Keanu Reeves");
        keanu.actsIn(movie);

        Actor carrie = new Actor("Carrie-Ann Moss");
        carrie.actsIn(movie);

        //Persist the movie. This persists the actors as well.
        //session.save(movie);

        //Load a movie
        //Movie matrix = session.load(Movie.class, movie.getId());
        for(Actor actor : movie.getActors()) {
            System.out.println("Actor: " + actor.getName());
        }
    }
}
