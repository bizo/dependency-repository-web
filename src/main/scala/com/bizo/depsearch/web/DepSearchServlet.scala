package com.bizo.depsearch.web

import com.bizo.app.TemplateAppStack
import depsearch.db.DependencyDB

class DepSearchServlet(db: DependencyDB) extends TemplateAppStack {
  get("/") {
    contentType = "text/html"
    
    scaml("/index")
  }
  
  get("/search") {
    val results = db.search(params("query"), 500)
    
    if (results.size == 1) {
      val d = results.head
      redirect(s"/d/${d.org}/${d.group}")
    } else {
      contentType = "text/html"      
      scaml("/search-results", "results" -> results)
    }
  }
  
  get("/d/:org/:group") {
    contentType = "text/html"

    val result = db.dependency(params("org"), params("group"), 5).get
    
    scaml("dependency", "result" -> result)
  }
}