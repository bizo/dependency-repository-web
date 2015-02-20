package com.bizo.depsearch.web

import com.bizo.app.TemplateAppStack
import depsearch.db.DependencyDB

class DepSearchServlet(db: DependencyDB) extends TemplateAppStack {
  
  def user(): Option[String] = {
    Option(request.getHeader("X-Forwarded-User"))
  }
  
  get("/") {
    contentType = "text/html"
    
    scaml("/index", "user" -> user, "stats" -> db.stats)
  }

  get("/search") {
    val results = db.search(params("query"), 500)
    
    if (results.size == 1) {
      val d = results.head
      redirect(s"/d/${d.org}/${d.group}")
    } else {
      contentType = "text/html"      
      scaml("/search-results", "results" -> results, "user" -> user)
    }
  }
  
  get("/d/:org/:group") {
    contentType = "text/html"

    val result = db.dependency(params("org"), params("group"), 5).get
    
    val downstream = db.downstream(params("org"), params("group"))
    
    scaml("dependency", "result" -> result, "downstream" -> downstream, "user" -> user)
  }
}