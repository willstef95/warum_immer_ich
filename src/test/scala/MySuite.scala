// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html

package warum_immer_ich
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import scala.util.matching.Regex.Match
import org.scalatest.matchers.must.Matchers

class WiiTests extends WordSpec with Matchers {
  "Ausgaben" when {
    "not set to any value " should {
      val ausgaben = ausgaben()
    }
    "have value 4" in {
      ausgaben should be(4)
    }

  }
}
