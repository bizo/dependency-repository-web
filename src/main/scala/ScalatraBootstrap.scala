import org.scalatra._
import com.bizo.app._
import depsearch.db.mongo.MongoDependencyDB
import javax.servlet.ServletContext
import com.bizo.depsearch.web.DepSearchServlet
import depsearch.db.CachedDependencyDB

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    val db = new CachedDependencyDB(MongoDependencyDB())
    context.mount(new DepSearchServlet(db), "/*")
  }
}
