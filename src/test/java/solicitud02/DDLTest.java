package solicitud02;

import static org.junit.Assert.assertNotNull;
import javax.persistence.*;
import org.junit.*;

public class DDLTest {
  private static EntityManagerFactory emf;

  @BeforeClass public static void init() {
    emf = Persistence.createEntityManagerFactory("solicitud02PU");
  }
  @AfterClass public static void close() { if (emf != null) emf.close(); }

  @Test public void emfDebeCrearse() {
    assertNotNull(emf);
    EntityManager em = emf.createEntityManager();
    assertNotNull(em);
    em.close();
  }
}

