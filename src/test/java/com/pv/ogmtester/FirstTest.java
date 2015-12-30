package com.pv.ogmtester;

import com.pv.ogmtester.domain.Actor;
import com.pv.ogmtester.domain.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.neo4j.ogm.testutil.Neo4jIntegrationTestRule;

import java.util.Collection;

/**
 * First Test Class
 * Created by Panos on 27-Dec-15.
 */
public class FirstTest{

    @Rule
    public Neo4jIntegrationTestRule databaseServerRule = new Neo4jIntegrationTestRule();

    private Session session;

    @Before
    public void init()  {
        SessionFactory sessionFactory =
                new SessionFactory("com.pv.ogmtester.domain");
        session = sessionFactory.openSession(databaseServerRule.url());
        //use the session
        Movie movie = new Movie("The Matrix", 1999);

        Actor keanu = new Actor("Keanu Reeves");
        keanu.actsIn(movie);

        Actor carrie = new Actor("Carrie-Ann Moss");
        carrie.actsIn(movie);

        //Persist the movie. This persists the actors as well.
        session.save(movie);
    }

    @After
    public void clearDatabase() {
        databaseServerRule.clearDatabase();
    }

    @Test
    public void countActors() {

        Collection<Actor> actors = session.loadAll(Actor.class);
        assertEquals(2, actors.size());
    }

}